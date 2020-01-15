package com.Fenyuyang.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Fenyuyang.controller.DataCollection;
import com.Fenyuyang.controller.DataStorage;
import com.Fenyuyang.controller.Parameter;
import com.Fenyuyang.tool.Normalized;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class Family {

	// ========================== Constructors ===========================
	public Family(int x, int y, Context<Object> context, ContinuousSpace<Object> space, Grid<Object> grid) {
		id = ++Parameter.familyId;
		this.x = x;
		this.y = y;
		this.context = context;
		this.space = space;
		this.grid = grid;
	}

	// ======================= Private Properties ========================
	private int x;
	private int y;
	private Context<Object> context;
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private Cell cell;

	private int id;
	private ArrayList<Resident> inhabitantTeam = new ArrayList<Resident>();

	private double income;
	private double expense;
	private double deposit;

	private ArrayList<Cell> desiredLocationTeam = new ArrayList<Cell>();

	double familySatisfaction;
	double streetDiagram;
	double gridDiagram;
	double moveDesire = 0;
	int[] environmentPreference = { 0, 0, 0, 0, 0, 0, 0, 0 };
	int[] safePreference = { 0, 0, 0, 0, 0 };

	// ========================== Public Methods =========================

	@ScheduledMethod(start = 1, interval = 1, priority = 8)
	public void step() throws Exception {
		this.randomBirth();
		this.randomDeath();

		this.countIncome();
		this.countExpense();

		this.countFamilySatisfaction();
		this.countMoveDesire();
	}

	public void printInformation() {
		System.out.println("家庭编号�?" + id);
		System.out.println("家庭收入�?" + income);
		System.out.println("成员信息�?");
		for (int k = 0; k < this.getInhabitantTeam().size(); k++) {
			System.out.println("�?" + (k + 1) + "个人�?");
			System.out.println("性别�?" + this.getInhabitantTeam().get(k).getGender());
			System.out.println("年龄�?" + this.getInhabitantTeam().get(k).getAge());
			System.out.println("教育水平�?" + this.getInhabitantTeam().get(k).getEducationLevel());
			System.out.println("工资（年）：" + this.getInhabitantTeam().get(k).getIncome());
		}
	}

	private void countExpense() {
		double expense = 0;
		for (int i = 0; i < inhabitantTeam.size(); i++) {
			expense = expense + inhabitantTeam.get(i).getExpense();
		}
		this.setExpense(expense);
	}

	public void countIncome() {
		double income = 0;
		for (int i = 0; i < this.getInhabitantTeam().size(); i++) {
			income = income + this.getInhabitantTeam().get(i).getIncome();
		}
		this.setIncome(income);
	}

	public void randomBirth() {
		int birthNumber = 0;
		double[] rate = Parameter.birthRate;
		int age_0_14 = 0;

		for (int i = 0; i < inhabitantTeam.size(); i++) {
			if (inhabitantTeam.get(i).getAge() <= 14) {
				age_0_14++;
			}
		}

		if (age_0_14 < Parameter.fertilityPolicy && this.inhabitantTeam.size() < 10) {
			Resident inhabitant = new Resident(x, y, context, space, grid);
			inhabitant.setAge(0);
			inhabitant.randomGender();
			inhabitant.setEducationLevel(0);

			int j = this.getInhabitantTeam().size();
			for (int i = 0; i < j; i++) {
				double randomNumber = Math.random();
				int age = this.getInhabitantTeam().get(i).getAge();
				int gender = this.getInhabitantTeam().get(i).getGender();
				if (age >= 18 && age <= 45 && gender == 2 && randomNumber < rate[0]) {
					birthNumber++;
					this.inhabitantTeam.add(inhabitant);
					context.add(inhabitant);
					grid.moveTo(inhabitant, x, y);
				}
			}
		}

		DataStorage.getInstance().setBirthNumber(DataStorage.getInstance().getBirthNumber() + birthNumber);
		DataCollection.getInstance().setBirthNumber(DataStorage.getInstance().getBirthNumber());
	}

	public void randomDeath() {
		int mortalityNumber = 0;
		double[] rate = Parameter.mortalityRate;
		ArrayList<Resident> toDieInhabitantTeam = new ArrayList<Resident>();
		for (int i = 0; i < this.getInhabitantTeam().size(); i++) {
			int age = this.getInhabitantTeam().get(i).getAge();
			double randomNumber = Math.random();
			if (randomNumber < rate[0] || age >= 100) {
				toDieInhabitantTeam.add(this.getInhabitantTeam().get(i));
			}
		}

		for (int j = 0; j < toDieInhabitantTeam.size(); j++) {
			this.getInhabitantTeam().remove(toDieInhabitantTeam.get(j));
			context.remove(toDieInhabitantTeam.get(j));
			mortalityNumber++;
		}

		DataStorage.getInstance().setDeathNumber(DataStorage.getInstance().getDeathNumber() + mortalityNumber);
		DataCollection.getInstance().setDeathNumber(DataStorage.getInstance().getDeathNumber());
	}

	public <T> List<T> getNghObject(Class<T> clazz, int minDistance, int maxDistance) {

		List<T> objectTeam = new ArrayList<T>();

		GridPoint gridPoint = grid.getLocation(this);
		GridCellNgh<T> nghCreator = new GridCellNgh<>(grid, gridPoint, clazz, maxDistance, maxDistance);
		List<GridCell<T>> gridCells = nghCreator.getNeighborhood(true);

		for (GridCell<T> gridCell : gridCells) {
			GridPoint gridPointTemp = gridCell.getPoint();
			int gridPointTemp_x = gridPointTemp.getX();
			int gridPointTemp_y = gridPointTemp.getY();
			if ((Math.pow((x - gridPointTemp_x), 2) + Math.pow((y - gridPointTemp_y), 2)) <= Math.pow(maxDistance, 2)
					&& (Math.pow((x - gridPointTemp_x), 2) + Math.pow((y - gridPointTemp_y), 2)) >= Math
							.pow(minDistance, 2)) {
				for (int i = 0; i < gridCell.size(); i++) {
					objectTeam.add((T) ((ArrayList<T>) gridCell.items()).get(i));
				}
			}
		}
		return objectTeam;
	}

	public boolean judgeMigrationCondition() {
		boolean isMigration = false;
		double moveIncome = Parameter.moveIncomeLimit_min;

		if (this.income > moveIncome && this.moveDesire > Parameter.moveDesireLimitRate_min) {
			isMigration = true;
		}

		return isMigration;
	}

	public void countSafePreference() {
		boolean falg = true;
		for (int i = 0; i < inhabitantTeam.size(); i++) {
			if (inhabitantTeam.get(i).getAge() > 0 && inhabitantTeam.get(i).getAge() < 18) {
				falg = false;
				this.setSafePreference(inhabitantTeam.get(i).getSafePreference());
				break;
			} else if (inhabitantTeam.get(i).getAge() > 60) {
				falg = false;
				this.setSafePreference(inhabitantTeam.get(i).getSafePreference());
				break;
			}
		}

		if (falg) {
			Random random = new Random();
			this.setSafePreference(inhabitantTeam.get(random.nextInt(inhabitantTeam.size())).getSafePreference());
		}
	}

	public void countEnvironmentPreference() {

		boolean falg = true;
		for (int i = 0; i < inhabitantTeam.size(); i++) {
			if (inhabitantTeam.get(i).getAge() > 0 && inhabitantTeam.get(i).getAge() < 18) {
				falg = false;
				this.setEnvironmentPreference(inhabitantTeam.get(i).getEnvironmentPreference());
				break;
			} else if (inhabitantTeam.get(i).getAge() > 60) {
				falg = false;
				this.setEnvironmentPreference(inhabitantTeam.get(i).getEnvironmentPreference());
				break;
			}
		}

		if (falg) {
			Random random = new Random();
			this.setEnvironmentPreference(
					inhabitantTeam.get(random.nextInt(inhabitantTeam.size())).getEnvironmentPreference());
		}
	}

	public void randomMoveDesire() {
		double rate[] = { 0.0213, 0.25000, 0.3670, 0.33510, 0.02130 };
		double randomNumber = Math.random();
		if (randomNumber >= 0 && randomNumber < rate[0]) {
			this.moveDesire = 1;
		} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
			this.moveDesire = 2;
		} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
			this.moveDesire = 3;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
			this.moveDesire = 4;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
			this.moveDesire = 0;
		}

		this.moveDesire = 0;
	}

	public void countMoveDesire() {
		this.setMoveDesire(1 - this.familySatisfaction);
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


	public void randomFamilySatisfaction() {
		if (this.streetDiagram != 2 && this.streetDiagram != 3 && this.streetDiagram != 5 && this.streetDiagram != 6
				&& this.streetDiagram != 8) {
			double randomNumber = Math.random();
			if (randomNumber <= 0.2) {
				this.streetDiagram = 2;
			} else if (randomNumber <= 0.4) {
				this.streetDiagram = 3;
			} else if (randomNumber <= 0.6) {
				this.streetDiagram = 5;
			} else if (randomNumber <= 0.8) {
				this.streetDiagram = 6;
			} else {
				this.streetDiagram = 8;
			}
		}
		if (this.streetDiagram == 2) {
			double rate[] = { 0.1000, 0.4333, 0.4000, 0.0667, 0.0000 };
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				this.familySatisfaction = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				this.familySatisfaction = 2;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				this.familySatisfaction = 3;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				this.familySatisfaction = 4;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				this.familySatisfaction = 0;
			} else {
				this.familySatisfaction = 0;
			}
		}
		if (this.streetDiagram == 3) {
			double rate[] = { 0.0323, 0.3548, 0.4516, 0.1613, 0.0000 };
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				this.familySatisfaction = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				this.familySatisfaction = 2;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				this.familySatisfaction = 3;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				this.familySatisfaction = 4;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				this.familySatisfaction = 0;
			} else {
				this.familySatisfaction = 0;
			}
		}
		if (this.streetDiagram == 5) {
			double rate[] = { 0.0976, 0.6341, 0.2439, 0.0244, 0.0000 };
			double randomNumber = Math.random();// 产生随机数（0-1�?
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				this.familySatisfaction = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				this.familySatisfaction = 2;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				this.familySatisfaction = 3;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				this.familySatisfaction = 4;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				this.familySatisfaction = 0;
			} else {
				this.familySatisfaction = 0;
			}
		}
		if (this.streetDiagram == 6) {
			double rate[] = { 0.0000, 0.3226, 0.4194, 0.2581, 0.0000 };
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				this.familySatisfaction = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				this.familySatisfaction = 2;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				this.familySatisfaction = 3;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				this.familySatisfaction = 4;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				this.familySatisfaction = 0;
			} else {
				this.familySatisfaction = 0;
			}
		}
		if (this.streetDiagram == 8) {
			double rate[] = { 0.0727, 0.4909, 0.4000, 0.0182, 0.0182 };
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				this.familySatisfaction = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				this.familySatisfaction = 2;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				this.familySatisfaction = 3;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				this.familySatisfaction = 4;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				this.familySatisfaction = 0;
			} else {
				this.familySatisfaction = 0;
			}
		}

		if (this.familySatisfaction == 1) {
			this.familySatisfaction = 0;
		} else if (this.familySatisfaction == 2) {
			this.familySatisfaction = 0.5;
		} else if (this.familySatisfaction == 3) {
			this.familySatisfaction = 0.75;
		} else if (this.familySatisfaction == 4) {
			this.familySatisfaction = 1;
		} else if (this.familySatisfaction == 0) {
			this.familySatisfaction = 0.25;
		}

		this.familySatisfaction = 0;
	}

	public void countFamilySatisfaction() {
		double familySatisfaction = 0;
		double S_noAgent_family = 0;
		double S_agent_family = 0;

		List<Cell> CellTeam = new ArrayList<Cell>();
		CellTeam = getNghObject(Cell.class, 1);

		S_noAgent_family = countS_noAgent_family(CellTeam);

		S_agent_family = countS_agent_family();

		familySatisfaction = 0.5 * S_noAgent_family + 0.5 * S_agent_family;

		if (familySatisfaction < 0) {
			familySatisfaction = 0;
		}
		if (familySatisfaction > 1) {
			familySatisfaction = 1;
		}
		this.setFamilySatisfaction(familySatisfaction);
	}

	public double countS_noAgent_family(List<Cell> CellTeam) {
		double S_noAgent_family = 0;

		double[] alpha = new double[8];
		double[] beta = new double[5];

		for (int i = 0; i < alpha.length; i++) {
			alpha[i] = Parameter.environmentWeight[i];
		}

		for (int i = 0; i < beta.length; i++) {
			beta[i] = Parameter.safeWeight[i];
		}

		for (int i = 0; i < alpha.length; i++) {
			alpha[i] = alpha[i] * environmentPreference[i];
		}
		for (int i = 0; i < beta.length; i++) {
			beta[i] = beta[i] * safePreference[i];
		}
		alpha = Normalized.arrayNormalized(alpha);
		beta = Normalized.arrayNormalized(beta);

		double S_main_family = 0;
		double S_minor_family = 0;

		double ES_1 = 0;
		double SS_1 = 0;
		double[] FC_i = countFC_i();
		double[] Safe_i = countSafe_i();

		for (int i = 0; i < alpha.length; i++) {
			ES_1 = ES_1 + alpha[i] * FC_i[i];
		}

		for (int i = 0; i < beta.length; i++) {
			SS_1 = SS_1 + beta[i] * Safe_i[i];
		}

		S_main_family = SS_1;// debug

		double ES_2 = 0;
		double SS_2 = 0;
		double[] FC_a_average = countFC_a_average(CellTeam);
		double[] Safe_b_average = countSafe_b_average(CellTeam);

		for (int i = 0; i < alpha.length; i++) {
			ES_2 = ES_2 + alpha[i] * (FC_i[i] - FC_a_average[i]);
		}
		for (int i = 0; i < beta.length; i++) {
			SS_2 = SS_2 + beta[i] * (Safe_i[i] - Safe_b_average[i]);
		}

		S_minor_family = SS_2;// debug

		S_noAgent_family = S_main_family + S_minor_family;

		return S_noAgent_family;
	}

	public double countS_agent_family() {
		double S_agent_family = 0;
		List<Family> familyTeam = new ArrayList<Family>();
		familyTeam = getNghObject(Family.class, 0);

		if (familyTeam.size() > 10) {
			double[] familyTeamSatisfaction = new double[familyTeam.size()];
			for (int i = 0; i < familyTeam.size(); i++) {
				familyTeamSatisfaction[i] = familyTeam.get(i).getFamilySatisfaction();
			}

			int min = 0;
			int max = 0;
			for (int i = 0; i < familyTeamSatisfaction.length; i++) {
				if (this.familySatisfaction < familyTeamSatisfaction[i]) {
					min++;
				}
				if (this.familySatisfaction > familyTeamSatisfaction[i]) {
					max++;
				}
			}

			double randomNumber = Math.random();
			if ((double) min / (familyTeamSatisfaction.length) < 0.1 && randomNumber < 0.5) {
				S_agent_family = -0.1;
			}
			if ((double) max / (familyTeamSatisfaction.length) < 0.1) {
				S_agent_family = -0.2;
			}
		}

		return S_agent_family;
	}

	public double[] countFC_a_average(List<Cell> CellTeam) {
		double FC_traffic_average = 0;
		double FC_education_average = 0;
		double FC_green_average = 0;
		double FC_population_average = 0;
		double FC_commercial_average = 0;
		double FC_health_average = 0;
		double FC_stylistic_average = 0;
		double FC_pension_average = 0;

		for (int i = 0; i < CellTeam.size(); i++) {
			if (i != CellTeam.size() / 2) {
				FC_traffic_average = FC_traffic_average + CellTeam.get(i).getTrafficCoverage();
				FC_education_average = FC_education_average + CellTeam.get(i).getEducationCoverage();
				FC_green_average = FC_green_average + CellTeam.get(i).getGreenCoverage();
				FC_population_average = FC_population_average + CellTeam.get(i).getPopulationDensity();
				FC_commercial_average = FC_commercial_average + CellTeam.get(i).getCommercialFacilityCoverage();
				FC_health_average = FC_health_average + CellTeam.get(i).getHealthCoverage();
				FC_stylistic_average = FC_stylistic_average + CellTeam.get(i).getStylisticCoverage();
				FC_pension_average = FC_pension_average + CellTeam.get(i).getPensionFacilityCoverage();
			}
		}

		FC_traffic_average = FC_traffic_average / 8;
		FC_education_average = FC_education_average / 8;
		FC_green_average = FC_green_average / 8;
		FC_population_average = FC_population_average / 8;
		FC_commercial_average = FC_commercial_average / 8;
		FC_health_average = FC_health_average / 8;
		FC_stylistic_average = FC_stylistic_average / 8;
		FC_pension_average = FC_pension_average / 8;

		double[] FC_a_average = { FC_traffic_average, FC_education_average, FC_green_average, FC_population_average,
				FC_commercial_average, FC_health_average, FC_stylistic_average, FC_pension_average };

		return FC_a_average;

	}

	public double[] countSafe_b_average(List<Cell> CellTeam) {
		double Safe_security_average = 0;
		double Safe_fire_average = 0;
		double Safe_traffic_average = 0;
		double Safe_emergency_average = 0;
		double Safe_resident_average = 0;

		for (int i = 0; i < CellTeam.size(); i++) {
			if (i != CellTeam.size() / 2) {
				Safe_security_average = Safe_security_average + CellTeam.get(i).getSecuritySafetyValue();
				Safe_fire_average = Safe_fire_average + CellTeam.get(i).getFireSafetyValue();
				Safe_traffic_average = Safe_traffic_average + CellTeam.get(i).getTrafficSafetyValue();
				Safe_emergency_average = Safe_emergency_average + CellTeam.get(i).getEmergencySafetyValue();
				Safe_resident_average = Safe_resident_average + CellTeam.get(i).getResidentSafetyValue();
			}
		}

		Safe_security_average = Safe_security_average / 8;
		Safe_fire_average = Safe_fire_average / 8;
		Safe_traffic_average = Safe_traffic_average / 8;
		Safe_emergency_average = Safe_emergency_average / 8;
		Safe_resident_average = Safe_resident_average / 8;

		double[] Safe_b_average = { Safe_security_average, Safe_fire_average, Safe_traffic_average,
				Safe_emergency_average, Safe_resident_average };

		return Safe_b_average;
	}

	public double[] countSafe_i() {
		int[] safePreference = this.getSafePreference();
		Cell cell = this.getCell();
		double Safe_security = cell.getSecuritySafetyValue();
		double Safe_fire = cell.getFireSafetyValue();
		double Safe_traffic = cell.getTrafficSafetyValue();
		double Safe_emergency = cell.getEmergencySafetyValue();
		double Safe_resident = cell.getResidentSafetyValue();

		double[] Safe_i = { Safe_security, Safe_fire, Safe_traffic, Safe_emergency, Safe_resident };

		for (int i = 0; i < Safe_i.length; i++) {
			Safe_i[i] = Safe_i[i] * safePreference[i];
		}

		return Safe_i;
	}

	public double[] countFC_i() {
		int[] environmentPreference = this.getEnvironmentPreference();
		Cell cell = this.getCell();
		double FC_traffic = cell.getTrafficCoverage();
		double FC_education = cell.getEducationCoverage();
		double FC_green = cell.getGreenCoverage();
		double FC_population = cell.getPopulationDensity();
		double FC_commercial = cell.getCommercialFacilityCoverage();
		double FC_health = cell.getHealthCoverage();
		double FC_stylistic = cell.getStylisticCoverage();
		double FC_pension = cell.getPensionFacilityCoverage();

		double[] FC_i = { FC_traffic, FC_education, FC_green, FC_population, FC_commercial, FC_health, FC_stylistic,
				FC_pension };

		for (int i = 0; i < FC_i.length; i++) {
			FC_i[i] = FC_i[i] * environmentPreference[i];
		}

		return FC_i;
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

	public ArrayList<Resident> getInhabitantTeam() {
		return inhabitantTeam;
	}

	public void setInhabitantTeam(ArrayList<Resident> inhabitantTeam) {
		this.inhabitantTeam = inhabitantTeam;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Grid<Object> getGrid() {
		return grid;
	}

	public void setGrid(Grid<Object> grid) {
		this.grid = grid;
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}

	public ArrayList<Cell> getDesiredLocationTeam() {
		return desiredLocationTeam;
	}

	public void setDesiredLocationTeam(ArrayList<Cell> desiredLocationTeam) {
		this.desiredLocationTeam = desiredLocationTeam;
	}

	public double getFamilySatisfaction() {
		return familySatisfaction;
	}

	public void setFamilySatisfaction(double familySatisfaction) {
		this.familySatisfaction = familySatisfaction;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public int[] getEnvironmentPreference() {
		return environmentPreference;
	}

	public void setEnvironmentPreference(int[] environmentPreference) {
		this.environmentPreference = environmentPreference;
	}

	public int[] getSafePreference() {
		return safePreference;
	}

	public void setSafePreference(int[] safePreference) {
		this.safePreference = safePreference;
	}

	public double getMoveDesire() {
		return moveDesire;
	}

	public void setMoveDesire(double moveDesire) {
		this.moveDesire = moveDesire;
	}

	public double getStreetDiagram() {
		return streetDiagram;
	}

	public void setStreetDiagram(double streetDiagram) {
		this.streetDiagram = streetDiagram;
	}

	public double getGridDiagram() {
		return gridDiagram;
	}

	public void setGridDiagram(double gridDiagram) {
		this.gridDiagram = gridDiagram;
	}

}
