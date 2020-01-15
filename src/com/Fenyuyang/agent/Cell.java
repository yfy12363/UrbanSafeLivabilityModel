package com.Fenyuyang.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Fenyuyang.controller.ActionController;
import com.Fenyuyang.controller.DataCollection;
import com.Fenyuyang.controller.DataStorage;
import com.Fenyuyang.controller.Parameter;
import com.Fenyuyang.main.UrbanSafeLivabilityModel;
import com.Fenyuyang.tool.Point;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class Cell {
	// ========================== Constructors ===========================
	public Cell(int x, int y, Context<Object> context, ContinuousSpace<Object> space, Grid<Object> grid) {
		this.x = x;
		this.y = y;
		this.context = context;
		this.space = space;
		this.grid = grid;
	}

	// ======================= Private Properties ========================
	int x;
	int y;
	Context<Object> context;
	ContinuousSpace<Object> space;
	Grid<Object> grid;

	private int inhabitantNumber_last;
	private int inhabitantNumber;
	private ArrayList<Family> familyTeam = new ArrayList<Family>();

	double border;
	double busStopCoverage;
	double buildingCoverage;
	double trafficCoverage;
	double educationCoverage;
	double streetDiagram;
	double greenCoverage;
	double greenSupplyDemandRatio;
	double populationDensity;
	double commercialFacilityCoverage;
	double foreignResidentRatio;
	double gridDiagram;
	double healthCoverage;
	double stylisticCoverage;
	double disgustingFacilityCoverage;
	double pensionFacilityCoverage;
	double cityEmergencyFacilitiesCoverage;
	double averageBlockSize;
	double streetPublicSafetyIndex;
	double medicalPensionCoverage;

	int moveInFamilyNumber = 0;
	int moveOutFamilyNumber = 0;

	int moveInFamilyNumber_now = 0;
	int moveOutFamilyNumber_now = 0;
	int deathFamilyNumber_now = 0;

	double policeFacilityCoverage;
	double fireFacilityCoverage;

	int migrantPopulation;
	int foreignPopulation;

	private double safetyLivabilityValue;
	private double cellSatisfaction;
	private double securitySafetyValue;
	private double fireSafetyValue;
	private double trafficSafetyValue;
	private double emergencySafetyValue;
	private double residentSafetyValue;
	// ========================== Public Methods =========================

	@ScheduledMethod(start = 1, interval = 1, priority = 7)
	public void step() {
		this.setInhabitantNumber_last(this.getInhabitantNumber());

		this.errorDataProcess();

		this.moveOutFuTian();

		if (Parameter.availableHouse.size() > 0) {
			this.moveInFuTian();
		}

	}

	public void countPopulationDensity() {
		double populationDensity = (this.inhabitantNumber + this.foreignPopulation) / Parameter.cellSize * 2;

		this.setPopulationDensity(populationDensity);
	}

	private void moveInFuTian() {
		ArrayList<Integer> inhabitantArray = this.devideFamilyNumber(this.getInhabitantNumber());// 获取每户的人�?
		for (int i = 0; i < inhabitantArray.size(); i++) {
			int familyInhabitantNumber = inhabitantArray.get(i);
			Family family = new Family(x, y, context, space, grid);
			ArrayList<Resident> inhabitantTeam = new ArrayList<Resident>();
			for (int j = 0; j < familyInhabitantNumber; j++) {
				Resident inhabitant = new Resident(x, y, context, space, grid);
				inhabitant.randomGender();
				inhabitant.randomAge();
				inhabitant.randomEducationLevel(inhabitant.getAge());
				inhabitant.randomIncome();
				inhabitant.setStreetDiagram(this.getStreetDiagram());
				inhabitant.setGridDiagram(this.getGridDiagram());
				int[] environmentPreference = { 0, 0, 0, 0, 0, 0, 0, 0 };
				int[] safePreference = { 0, 0, 0, 0, 0 };
				inhabitant.setEnvironmentPreference(environmentPreference);
				inhabitant.setSafePreference(safePreference);

				inhabitantTeam.add(inhabitant);
			}
			family.countIncome();
			family.setInhabitantTeam(inhabitantTeam);
			family.setStreetDiagram((int) this.getStreetDiagram());
			family.setGridDiagram(this.getGridDiagram());
			family.randomFamilySatisfaction();
			int[] environmentPreference = { 0, 0, 0, 0, 0, 0, 0, 0 };
			int[] safePreference = { 0, 0, 0, 0, 0 };
			family.setEnvironmentPreference(environmentPreference);
			family.setSafePreference(safePreference);
			family.randomMoveDesire();
			family.setCell(this);
			if (Parameter.availableHouse.size() > 0) {
				Random random = new Random();
				int index = random.nextInt(Parameter.availableHouse.size());
				Point point = Parameter.availableHouse.get(index);

				Cell cell = UrbanSafeLivabilityModel.searchCell(point.getX(), point.getY());
				if (cell != null) {
					ActionController.moveInFuTian(family, point, context, grid);
					Parameter.availableHouse.remove(point);
				} else {
					Parameter.availableHouse.remove(point);
				}
			}
		}

	}

	private void moveOutFuTian() {
		ArrayList<Family> toMigrationFamily = new ArrayList<Family>();
		for (int i = 0; i < familyTeam.size(); i++) {
			if (familyTeam.get(i).judgeMigrationCondition()) {
				toMigrationFamily.add(familyTeam.get(i));
			}
		}

		for (int i = 0; i < toMigrationFamily.size(); i++) {
			ActionController.moveOutFuTian(toMigrationFamily.get(i), this, context);
			Parameter.availableHouse.add(new Point(this.x, this.y));
		}
	}

	public void countMigrantPopulation() {
		int migrantPopulation = (int) (this.getInhabitantNumber() * this.getForeignResidentRatio()
				* (1 + Parameter.foreignResidentGrowthRate) * Parameter.moveInPopulationLimitRate);

		this.setMigrantPopulation(migrantPopulation);
	}

	public void countForeignPopulation() {
		int foreignPopulation = 0;
		if (this.getForeignPopulation() == 0) {
			foreignPopulation = (int) (this.getInhabitantNumber() * this.getForeignResidentRatio()
					* (1 + Parameter.foreignResidentGrowthRate));
		} else {
			foreignPopulation = (int) (this.getForeignPopulation() * (1 + Parameter.foreignResidentGrowthRate));
		}
		this.setForeignPopulation(foreignPopulation);
	}

	public void countInhabitantNumber() {
		int inhabitantNumber = 0;
		for (int i = 0; i < this.familyTeam.size(); i++) {
			for (int j = 0; j < this.familyTeam.get(i).getInhabitantTeam().size(); j++) {
				inhabitantNumber++;
			}
		}
		this.setInhabitantNumber(inhabitantNumber);
	}

	public void countCellSatisfaction() {
		double cellSatisfaction = 0;

		if (this.getFamilyTeam().size() == 0) {
			cellSatisfaction = 0;
		} else {
			for (int i = 0; i < this.getFamilyTeam().size(); i++) {
				cellSatisfaction = cellSatisfaction + this.getFamilyTeam().get(i).getFamilySatisfaction();
			}
			cellSatisfaction = cellSatisfaction / this.getFamilyTeam().size() + 0.08;
		}

		this.setCellSatisfaction(cellSatisfaction);
	}

	public void countSafetyLivabilityValue_1() {

		double changeRate = ((double) inhabitantNumber_last + (double) inhabitantNumber_last * this.foreignResidentRatio
				* (1 + Parameter.foreignResidentGrowthRate))
				/ ((double) inhabitantNumber + (double) inhabitantNumber * this.foreignResidentRatio
						* (1 + Parameter.foreignResidentGrowthRate));
		double Safe_security = this.getPoliceFacilityCoverage();
		double Safe_fire = this.getFireFacilityCoverage();
		double Safe_traffic = this.getTrafficCoverage() * changeRate;
		double Safe_emergency = this.getCityEmergencyFacilitiesCoverage() * changeRate;
		double Safe_residential = this.getDisgustingFacilityCoverage() * changeRate;
		double SafetyLivability = Safe_security * Parameter.securitySafetyWeight
				+ Safe_fire * Parameter.fireSafetyWeight + Safe_traffic * Parameter.trafficSafetyWeight
				+ Safe_emergency * Parameter.emergencySafetyWeight + Safe_residential * Parameter.livingSafetyWeight;

		this.setSecuritySafetyValue(Safe_security);
		this.setFireSafetyValue(Safe_fire);
		this.setTrafficSafetyValue(Safe_traffic);
		this.setEmergencySafetyValue(Safe_emergency);
		this.setResidentSafetyValue(Safe_residential);
		this.setSafetyLivabilityValue(SafetyLivability);
	}

	public void countSafetyLivabilityValue_2() {

		double changeRate = ((double) inhabitantNumber_last + (double) inhabitantNumber_last * this.foreignResidentRatio
				* (1 + Parameter.foreignResidentGrowthRate))
				/ ((double) inhabitantNumber + (double) inhabitantNumber * this.foreignResidentRatio
						* (1 + Parameter.foreignResidentGrowthRate));

		double Safe_security = this.getSecuritySafetyValue();
		double Safe_fire = this.getFireSafetyValue();
		double Safe_traffic = this.getTrafficSafetyValue() * changeRate;
		double Safe_emergency = this.getEmergencySafetyValue() * changeRate;
		double Safe_residential = this.getResidentSafetyValue() * changeRate;
		double SafetyLivability = Safe_security * Parameter.securitySafetyWeight
				+ Safe_fire * Parameter.fireSafetyWeight + Safe_traffic * Parameter.trafficSafetyWeight
				+ Safe_emergency * Parameter.emergencySafetyWeight + Safe_residential * Parameter.livingSafetyWeight;

		this.setSecuritySafetyValue(Safe_security);
		this.setFireSafetyValue(Safe_fire);
		this.setTrafficSafetyValue(Safe_traffic);
		this.setEmergencySafetyValue(Safe_emergency);
		this.setResidentSafetyValue(Safe_residential);
		this.setSafetyLivabilityValue(SafetyLivability);

	}

	public void decideFamily() {
		ArrayList<Family> familyTeam = new ArrayList<Family>();
		ArrayList<Integer> inhabitantArray = this.devideFamilyNumber();
		for (int i = 0; i < inhabitantArray.size(); i++) {
			int familyInhabitantNumber = inhabitantArray.get(i);
			Family family = new Family(x, y, context, space, grid);
			ArrayList<Resident> inhabitantTeam = new ArrayList<Resident>();
			for (int j = 0; j < familyInhabitantNumber; j++) {
				Resident inhabitant = new Resident(x, y, context, space, grid);
				inhabitant.setStreetDiagram(this.getStreetDiagram());
				inhabitant.setGridDiagram(this.getGridDiagram());
				inhabitant.randomGender();
				inhabitant.randomAge();
				inhabitant.randomEducationLevel(inhabitant.getAge());
				inhabitant.randomIncome();
				inhabitant.randomSafePreference();
				inhabitant.randomEnvironmentPreference();

				context.add(inhabitant);
				grid.moveTo(inhabitant, x, y);
				inhabitantTeam.add(inhabitant);
			}
			family.setInhabitantTeam(inhabitantTeam);
			family.countIncome();
			family.setStreetDiagram((int) this.getStreetDiagram());
			family.setGridDiagram(this.getGridDiagram());

			family.randomFamilySatisfaction();
			family.randomMoveDesire();

			family.countEnvironmentPreference();
			family.countSafePreference();
			family.setCell(this);
			context.add(family);
			grid.moveTo(family, x, y);
			familyTeam.add(family);
		}
		this.setFamilyTeam(familyTeam);
	}

	public ArrayList<Integer> devideFamilyNumber() {

		ArrayList<Integer> inhabitantArray = new ArrayList<Integer>();
		int inhabitantNumber = this.getInhabitantNumber();
		while (inhabitantNumber > 10) {
			int familyInhabitantNumber = this.randomFamilyInhabitantNumber();
			inhabitantArray.add(familyInhabitantNumber);
			inhabitantNumber = inhabitantNumber - familyInhabitantNumber;
		}
		if (inhabitantNumber != 0) {
			inhabitantArray.add(inhabitantNumber);
		}
		return inhabitantArray;
	}

	public ArrayList<Integer> devideFamilyNumber(int moveInNumber) {

		ArrayList<Integer> inhabitantArray = new ArrayList<Integer>();
		int inhabitantNumber = moveInNumber;
		while (inhabitantNumber > 10) {
			int familyInhabitantNumber = this.randomFamilyInhabitantNumber();
			inhabitantArray.add(familyInhabitantNumber);
			inhabitantNumber = inhabitantNumber - familyInhabitantNumber;
		}
		if (inhabitantNumber != 0) {
			inhabitantArray.add(inhabitantNumber);
		}
		return inhabitantArray;
	}

	public int randomFamilyInhabitantNumber() {
		int familyInhabitantNumber = 0;
		double[] rate = Parameter.familyInhabitantNumberRate;
		double randomNumber = Math.random();
		if (randomNumber >= 0 && randomNumber <= rate[0]) {
			familyInhabitantNumber = 1;
		} else if (randomNumber >= rate[0] && randomNumber <= rate[0] + rate[1]) {
			familyInhabitantNumber = 2;
		} else if (randomNumber >= rate[0] + rate[1] && randomNumber <= rate[0] + rate[1] + rate[2]) {
			familyInhabitantNumber = 3;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2]
				&& randomNumber <= rate[0] + rate[1] + rate[2] + rate[3]) {
			familyInhabitantNumber = 4;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
				&& randomNumber <= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
			familyInhabitantNumber = 5;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
				&& randomNumber <= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
			familyInhabitantNumber = 6;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
				&& randomNumber <= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
			familyInhabitantNumber = 7;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
				&& randomNumber <= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
			familyInhabitantNumber = 8;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				&& randomNumber <= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8]) {
			familyInhabitantNumber = 9;
		} else {
			familyInhabitantNumber = 10;
		}

		return familyInhabitantNumber;
	}

	public <T> List<T> getNghObject(Class<T> clazz, int validRadius) {

		List<T> objectTeam = new ArrayList<T>();

		GridPoint gridPoint = grid.getLocation(this);
		GridCellNgh<T> nghCreator = new GridCellNgh<>(grid, gridPoint, clazz, validRadius, validRadius);
		List<GridCell<T>> gridCells = nghCreator.getNeighborhood(true);

		for (GridCell<T> gridCell : gridCells) {
			GridPoint gridPointTemp = gridCell.getPoint();
			int gridPointTemp_x = gridPointTemp.getX();
			int gridPointTemp_y = gridPointTemp.getY();
			if ((Math.pow((x - gridPointTemp_x), 2) + Math.pow((y - gridPointTemp_y), 2)) <= Math.pow(validRadius, 2)) {
				for (int i = 0; i < gridCell.size(); i++) {
					objectTeam.add((T) ((ArrayList<T>) gridCell.items()).get(i));
				}
			}
		}
		return objectTeam;
	}

	public void errorDataProcess() {
		ArrayList<Family> remove = new ArrayList<Family>();

		for (int i = 0; i < this.familyTeam.size(); i++) {
			if (this.familyTeam.get(i).getInhabitantTeam().size() == 0) {
				remove.add(this.familyTeam.get(i));
			}
		}
		if (remove.size() > 0) {
			for (int i = 0; i < remove.size(); i++) {
				this.familyTeam.remove(remove.get(i));
				context.remove(this.familyTeam.remove(remove.get(i)));
				Parameter.availableHouse.add(new Point(this.getX(), this.getY()));
				DataStorage.getInstance()
						.setDeathFamilyNumber_now(DataStorage.getInstance().getDeathFamilyNumber_now() + 1);
				DataCollection.getInstance()
						.setDeathFamilyNumber_now(DataStorage.getInstance().getDeathFamilyNumber_now());
			}
		}
	}

	// ======================= Getters and Setters =======================
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ContinuousSpace<Object> getSpace() {
		return space;
	}

	public void setSpace(ContinuousSpace<Object> space) {
		this.space = space;
	}

	public Grid<Object> getGrid() {
		return grid;
	}

	public void setGrid(Grid<Object> grid) {
		this.grid = grid;
	}

	public int getInhabitantNumber() {
		return inhabitantNumber;
	}

	public void setInhabitantNumber(int inhabitantNumber) {
		this.inhabitantNumber = inhabitantNumber;
	}

	public ArrayList<Family> getFamilyTeam() {
		return familyTeam;
	}

	public void setFamilyTeam(ArrayList<Family> familyTeam) {
		this.familyTeam = familyTeam;
	}

	public double getPopulationDensity() {
		return populationDensity;
	}

	public void setPopulationDensity(double populationDensity) {
		this.populationDensity = populationDensity;
	}

	public double getCityEmergencyFacilitiesCoverage() {
		return cityEmergencyFacilitiesCoverage;
	}

	public void setCityEmergencyFacilitiesCoverage(double cityEmergencyFacilitiesCoverage) {
		this.cityEmergencyFacilitiesCoverage = cityEmergencyFacilitiesCoverage;
	}

	public double getDisgustingFacilityCoverage() {
		return disgustingFacilityCoverage;
	}

	public void setDisgustingFacilityCoverage(double disgustingFacilityCoverage) {
		this.disgustingFacilityCoverage = disgustingFacilityCoverage;
	}

	public int getMoveInFamilyNumber_now() {
		return moveInFamilyNumber_now;
	}

	public void setMoveInFamilyNumber_now(int moveInFamilyNumber_now) {
		this.moveInFamilyNumber_now = moveInFamilyNumber_now;
	}

	public int getMoveOutFamilyNumber_now() {
		return moveOutFamilyNumber_now;
	}

	public void setMoveOutFamilyNumber_now(int moveOutFamilyNumber_now) {
		this.moveOutFamilyNumber_now = moveOutFamilyNumber_now;
	}

	public double getBuildingCoverage() {
		return buildingCoverage;
	}

	public void setBuildingCoverage(double buildingCoverage) {
		this.buildingCoverage = buildingCoverage;
	}

	public double getAverageBlockSize() {
		return averageBlockSize;
	}

	public void setAverageBlockSize(double averageBlockSize) {
		this.averageBlockSize = averageBlockSize;
	}

	public double getStreetPublicSafetyIndex() {
		return streetPublicSafetyIndex;
	}

	public void setStreetPublicSafetyIndex(double streetPublicSafetyIndex) {
		this.streetPublicSafetyIndex = streetPublicSafetyIndex;
	}

	public Context<Object> getContext() {
		return context;
	}

	public void setContext(Context<Object> context) {
		this.context = context;
	}

	public double getMedicalPensionCoverage() {
		return medicalPensionCoverage;
	}

	public void setMedicalPensionCoverage(double medicalPensionCoverage) {
		this.medicalPensionCoverage = medicalPensionCoverage;
	}

	public double getCommercialFacilityCoverage() {
		return commercialFacilityCoverage;
	}

	public void setCommercialFacilityCoverage(double commercialFacilityCoverage) {
		this.commercialFacilityCoverage = commercialFacilityCoverage;
	}

	public double getGreenCoverage() {
		return greenCoverage;
	}

	public void setGreenCoverage(double greenCoverage) {
		this.greenCoverage = greenCoverage;
	}

	public double getBorder() {
		return border;
	}

	public void setBorder(double border) {
		this.border = border;
	}

	public double getBusStopCoverage() {
		return busStopCoverage;
	}

	public void setBusStopCoverage(double busStopCoverage) {
		this.busStopCoverage = busStopCoverage;
	}

	public double getTrafficCoverage() {
		return trafficCoverage;
	}

	public void setTrafficCoverage(double trafficCoverage) {
		this.trafficCoverage = trafficCoverage;
	}

	public double getGreenSupplyDemandRatio() {
		return greenSupplyDemandRatio;
	}

	public void setGreenSupplyDemandRatio(double greenSupplyDemandRatio) {
		this.greenSupplyDemandRatio = greenSupplyDemandRatio;
	}

	public double getForeignResidentRatio() {
		return foreignResidentRatio;
	}

	public void setForeignResidentRatio(double foreignResidentRatio) {
		this.foreignResidentRatio = foreignResidentRatio;
	}

	public double getGridDiagram() {
		return gridDiagram;
	}

	public void setGridDiagram(double gridDiagram) {
		this.gridDiagram = gridDiagram;
	}

	public double getHealthCoverage() {
		return healthCoverage;
	}

	public void setHealthCoverage(double healthCoverage) {
		this.healthCoverage = healthCoverage;
	}

	public double getStylisticCoverage() {
		return stylisticCoverage;
	}

	public void setStylisticCoverage(double stylisticCoverage) {
		this.stylisticCoverage = stylisticCoverage;
	}

	public double getPensionFacilityCoverage() {
		return pensionFacilityCoverage;
	}

	public void setPensionFacilityCoverage(double pensionFacilityCoverage) {
		this.pensionFacilityCoverage = pensionFacilityCoverage;
	}

	public double getEducationCoverage() {
		return educationCoverage;
	}

	public void setEducationCoverage(double educationCoverage) {
		this.educationCoverage = educationCoverage;
	}

	public int getMoveInFamilyNumber() {
		return moveInFamilyNumber;
	}

	public void setMoveInFamilyNumber(int moveInFamilyNumber) {
		this.moveInFamilyNumber = moveInFamilyNumber;
	}

	public int getMoveOutFamilyNumber() {
		return moveOutFamilyNumber;
	}

	public void setMoveOutFamilyNumber(int moveOutFamilyNumber) {
		this.moveOutFamilyNumber = moveOutFamilyNumber;
	}

	public double getSafetyLivabilityValue() {
		return safetyLivabilityValue;
	}

	public void setSafetyLivabilityValue(double safetyLivabilityValue) {
		this.safetyLivabilityValue = safetyLivabilityValue;
	}

	public double getSecuritySafetyValue() {
		return securitySafetyValue;
	}

	public void setSecuritySafetyValue(double securitySafetyValue) {
		this.securitySafetyValue = securitySafetyValue;
	}

	public double getFireSafetyValue() {
		return fireSafetyValue;
	}

	public void setFireSafetyValue(double fireSafetyValue) {
		this.fireSafetyValue = fireSafetyValue;
	}

	public double getTrafficSafetyValue() {
		return trafficSafetyValue;
	}

	public void setTrafficSafetyValue(double trafficSafetyValue) {
		this.trafficSafetyValue = trafficSafetyValue;
	}

	public double getEmergencySafetyValue() {
		return emergencySafetyValue;
	}

	public void setEmergencySafetyValue(double emergencySafetyValue) {
		this.emergencySafetyValue = emergencySafetyValue;
	}

	public double getStreetDiagram() {
		return streetDiagram;
	}

	public void setStreetDiagram(double streetDiagram) {
		this.streetDiagram = streetDiagram;
	}

	public double getCellSatisfaction() {
		return cellSatisfaction;
	}

	public void setCellSatisfaction(double cellSatisfaction) {
		this.cellSatisfaction = cellSatisfaction;
	}

	public double getResidentSafetyValue() {
		return residentSafetyValue;
	}

	public void setResidentSafetyValue(double residentSafetyValue) {
		this.residentSafetyValue = residentSafetyValue;
	}

	public double getFireFacilityCoverage() {
		return fireFacilityCoverage;
	}

	public void setFireFacilityCoverage(double fireFacilityCoverage) {
		this.fireFacilityCoverage = fireFacilityCoverage;
	}

	public double getPoliceFacilityCoverage() {
		return policeFacilityCoverage;
	}

	public void setPoliceFacilityCoverage(double policeFacilityCoverage) {
		this.policeFacilityCoverage = policeFacilityCoverage;
	}

	public int getMigrantPopulation() {
		return migrantPopulation;
	}

	public void setMigrantPopulation(int migrantPopulation) {
		this.migrantPopulation = migrantPopulation;
	}

	public int getForeignPopulation() {
		return foreignPopulation;
	}

	public void setForeignPopulation(int foreignPopulation) {
		this.foreignPopulation = foreignPopulation;
	}

	public int getInhabitantNumber_last() {
		return inhabitantNumber_last;
	}

	public void setInhabitantNumber_last(int inhabitantNumber_last) {
		this.inhabitantNumber_last = inhabitantNumber_last;
	}

}
