package com.Fenyuyang.main;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import com.Fenyuyang.agent.Cell;
import com.Fenyuyang.controller.DataCollection;
import com.Fenyuyang.controller.DataStorage;
import com.Fenyuyang.controller.Parameter;
import com.Fenyuyang.controller.Raster;
import com.Fenyuyang.excel.Excel;
import com.Fenyuyang.tool.BackgroundPicture;
import com.Fenyuyang.tool.Point;
import com.Fenyuyang.tool.ReadTxt;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.SimpleCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * Urban Safe Livability Model
 */
public class UrbanSafeLivabilityModel implements ContextBuilder<Object> {

	private static String propertiesFileName = "/UrbanSafeLivabilityModel.properties";
	private static Properties properties = new Properties();
	private static String MAP_URL;
	private static int MAP_WIDTH;
	private static int MAP_HEIGHT;
	private static int MAP_X;
	private static int MAP_Y;

	private Raster borderData = new Raster();
	private Raster buildingCoverageData = new Raster();
	private Raster streetDiagramData = new Raster();
	private Raster populationDensityData = new Raster();
	private Raster foreignResidentRatioData = new Raster();
	private Raster gridDiagramData = new Raster();

	private Raster trafficSafetyData = new Raster();
	private Raster livingSafetyData = new Raster();
	private Raster emergencySafetyData = new Raster();
	private Raster policeSafetyData = new Raster();
	private Raster fireSafetyData = new Raster();

	private Raster moveInFamilyNumberData = new Raster();
	private Raster moveOutFamilyNumberData = new Raster();

	private Raster moveInFamilyNumber_nowData = new Raster();
	private Raster moveOutFamilyNumber_nowData = new Raster();

	private Raster safetyLivabilityData = new Raster();
	private Raster satisfactionData = new Raster();

	private Raster gridAverageSatisfactionData = new Raster();
	private Raster streetAverageSatisfactionData = new Raster();
	private Raster futianAverageSatisfactionData = new Raster();

	private Raster gridAverageLivabilityData = new Raster();
	private Raster streetAverageLivabilityData = new Raster();
	private Raster futianAverageLivabilityData = new Raster();

	private Raster gridAveragePopulationDensityData = new Raster();
	private Raster streetAveragePopulationDensityData = new Raster();
	private Raster futianAveragePopulationDensityData = new Raster();

	private Raster gridPoliceSafetyData = new Raster();
	private Raster streetPoliceSafetyData = new Raster();
	private Raster futianPoliceSafetyData = new Raster();

	private Raster gridFireSafetyData = new Raster();
	private Raster streetFireSafetyData = new Raster();
	private Raster futianFireSafetyData = new Raster();

	private Raster gridTrafficSafetyData = new Raster();
	private Raster streetTrafficSafetyData = new Raster();
	private Raster futianTrafficSafetyData = new Raster();

	private Raster gridEmergencySafetyData = new Raster();
	private Raster streetEmergencySafetyData = new Raster();
	private Raster futianEmergencySafetyData = new Raster();

	private Raster gridLivingSafetyData = new Raster();
	private Raster streetLivingSafetyData = new Raster();
	private Raster futianLivingSafetyData = new Raster();

	private GridValueLayer border;
	private GridValueLayer buildingCoverage;
	private GridValueLayer streetDiagram;
	private GridValueLayer populationDensity;
	private GridValueLayer foreignResidentRatio;
	private GridValueLayer gridDiagram;

	private GridValueLayer trafficSafety;
	private GridValueLayer livingSafety;
	private GridValueLayer emergencySafety;
	private GridValueLayer policeSafety;
	private GridValueLayer fireSafety;

	private GridValueLayer moveInFamilyNumber;
	private GridValueLayer moveOutFamilyNumber;

	private GridValueLayer moveInFamilyNumber_now;
	private GridValueLayer moveOutFamilyNumber_now;

	private GridValueLayer safetyLivability;
	private GridValueLayer satisfaction;

	private GridValueLayer gridAverageSatisfaction;
	private GridValueLayer streetAverageSatisfaction;
	private GridValueLayer futianAverageSatisfaction;

	private GridValueLayer gridAverageLivability;
	private GridValueLayer streetAverageLivability;
	private GridValueLayer futianAverageLivability;

	private GridValueLayer gridAveragePopulationDensity;
	private GridValueLayer streetAveragePopulationDensity;
	private GridValueLayer futianAveragePopulationDensity;

	private GridValueLayer gridPoliceSafety;
	private GridValueLayer streetPoliceSafety;
	private GridValueLayer futianPoliceSafety;
	private GridValueLayer gridFireSafety;
	private GridValueLayer streetFireSafety;
	private GridValueLayer futianFireSafety;
	private GridValueLayer gridTrafficSafety;
	private GridValueLayer streetTrafficSafety;
	private GridValueLayer futianTrafficSafety;
	private GridValueLayer gridEmergencySafety;
	private GridValueLayer streetEmergencySafety;
	private GridValueLayer futianEmergencySafety;
	private GridValueLayer gridLivingSafety;
	private GridValueLayer streetLivingSafety;
	private GridValueLayer futianLivingSafety;

	public static ArrayList<Cell> cellTeam = new ArrayList<Cell>();

	private double futianSatisfaction = 0;
	private double[] streetSatisfaction = new double[Parameter.streetNumber];
	private double[] gridSatisfaction = new double[Parameter.gridNumber];

	private double futianLivability = 0;
	private double[] streetLivability = new double[Parameter.streetNumber];
	private double[] gridLivability = new double[Parameter.gridNumber];

	private double futianPopulationDensity = 0;
	private double[] streetPopulationDensity = new double[Parameter.streetNumber];
	private double[] gridPopulationDensity = new double[Parameter.gridNumber];

	private double[] streetSecuritySafetyValue = new double[Parameter.streetNumber];
	private double[] streetFireSafetyValue = new double[Parameter.streetNumber];
	private double[] streetTrafficSafetyValue = new double[Parameter.streetNumber];
	private double[] streetEmergencySafetyValue = new double[Parameter.streetNumber];
	private double[] streetResidentSafetyValue = new double[Parameter.streetNumber];

	private double[] gridSecuritySafetyValue = new double[Parameter.gridNumber];
	private double[] gridFireSafetyValue = new double[Parameter.gridNumber];
	private double[] gridTrafficSafetyValue = new double[Parameter.gridNumber];
	private double[] gridEmergencySafetyValue = new double[Parameter.gridNumber];
	private double[] gridResidentSafetyValue = new double[Parameter.gridNumber];

	private ArrayList<Point> availableHouse = new ArrayList<Point>();

	private ArrayList<Double> salaryData = new ArrayList<Double>();

	Excel excel;

	@Override
	public Context<Object> build(Context<Object> context) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(f.format(new Date()) + " Model initialization begins!");
		// Set environment ID
		context.setId("UrbanSafeLivabilityModel");

		try {
			excel = new Excel();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			InputStream inputStream = getClass().getResourceAsStream(propertiesFileName);
			properties.load(inputStream);
			MAP_URL = properties.getProperty("MAP_URL");
			MAP_WIDTH = Integer.parseInt(properties.getProperty("MAP_WIDTH"));
			MAP_HEIGHT = Integer.parseInt(properties.getProperty("MAP_HEIGHT"));
			MAP_X = Integer.parseInt(properties.getProperty("MAP_X"));
			MAP_Y = Integer.parseInt(properties.getProperty("MAP_Y"));

			availableHouse = ReadTxt.ReadPointData(MAP_URL + "HouseCoordinates.txt", MAP_X, MAP_Y);
			for (int i = 0; i < availableHouse.size(); i++) {
				if (i % Parameter.minRate == 0) {
					Parameter.availableHouse.add(availableHouse.get(i));
					Parameter.availableHouseBackups.add(availableHouse.get(i));
				}
			}

			salaryData = ReadTxt.ReadSalaryData(MAP_URL + "WageData.txt");
			for (int i = 0; i < salaryData.size(); i++) {
				if (i % Parameter.minRate == 0) {
					Parameter.salaryData.add(salaryData.get(i));
				}
			}

			System.out.println("MAP_URL = " + MAP_URL);
			System.out.println("MAP_WIDTH = " + MAP_WIDTH);
			System.out.println("MAP_HEIGHT = " + MAP_HEIGHT);
			System.out.println("MAP_X = " + MAP_X);
			System.out.println("MAP_Y = " + MAP_Y);
		} catch (IOException e) {
			System.out.println("Reading configuration file exception");
			e.printStackTrace();
		}

		// Create Space
		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space = spaceFactory.createContinuousSpace("space", context,
				new SimpleCartesianAdder<Object>(), new repast.simphony.space.continuous.WrapAroundBorders(), MAP_WIDTH,
				MAP_HEIGHT);

		// Create Grid
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Object> grid = gridFactory.createGrid("grid", context,
				new GridBuilderParameters<Object>(new repast.simphony.space.grid.WrapAroundBorders(),
						new SimpleGridAdder<Object>(), true, MAP_WIDTH, MAP_HEIGHT));

		Parameters parameters = RunEnvironment.getInstance().getParameters();
		double foreignResidentGrowthRate_up = (double) parameters.getValue("ImmigrantsChangeRate_up");
		double foreignResidentGrowthRate_down = (double) parameters.getValue("ImmigrantsChangeRate_down");
		double governmentToFacilityCoverageChange_up = (double) parameters.getValue("GovernmentInvestmentIntensity_up");
		double governmentToFacilityCoverageChange_down = (double) parameters
				.getValue("GovernmentInvestmentIntensity_down");

		double jobChangeRate_up = (double) parameters.getValue("JobChangeRate_up");
		double jobChangeRate_down = (double) parameters.getValue("JobChangeRate_down");

		double vacancyChangeRate_up = (double) parameters.getValue("VacancyChangeRate_up");
		double vacancyChangeRate_down = (double) parameters.getValue("VacancyChangeRate_down");

		context.add(new Parameter(foreignResidentGrowthRate_up, foreignResidentGrowthRate_down,
				governmentToFacilityCoverageChange_up, governmentToFacilityCoverageChange_down, jobChangeRate_up,
				jobChangeRate_down, vacancyChangeRate_up, vacancyChangeRate_down, MAP_X, MAP_Y));

		border = new GridValueLayer("border", false, MAP_WIDTH, MAP_HEIGHT);
		buildingCoverage = new GridValueLayer("buildingCoverage", false, MAP_WIDTH, MAP_HEIGHT);
		streetDiagram = new GridValueLayer("streetDiagram", false, MAP_WIDTH, MAP_HEIGHT);
		populationDensity = new GridValueLayer("populationDensity", false, MAP_WIDTH, MAP_HEIGHT);
		foreignResidentRatio = new GridValueLayer("foreignResidentRatio", false, MAP_WIDTH, MAP_HEIGHT);
		gridDiagram = new GridValueLayer("gridDiagram", false, MAP_WIDTH, MAP_HEIGHT);

		policeSafety = new GridValueLayer("policeSafety", false, MAP_WIDTH, MAP_HEIGHT);
		fireSafety = new GridValueLayer("fireSafety", false, MAP_WIDTH, MAP_HEIGHT);
		trafficSafety = new GridValueLayer("trafficSafety", false, MAP_WIDTH, MAP_HEIGHT);
		emergencySafety = new GridValueLayer("emergencySafety", false, MAP_WIDTH, MAP_HEIGHT);
		livingSafety = new GridValueLayer("livingSafety", false, MAP_WIDTH, MAP_HEIGHT);

		moveInFamilyNumber = new GridValueLayer("moveInFamilyNumber", false, MAP_WIDTH, MAP_HEIGHT);
		moveOutFamilyNumber = new GridValueLayer("moveOutFamilyNumber", false, MAP_WIDTH, MAP_HEIGHT);
		moveInFamilyNumber_now = new GridValueLayer("moveInFamilyNumber_now", false, MAP_WIDTH, MAP_HEIGHT);
		moveOutFamilyNumber_now = new GridValueLayer("moveOutFamilyNumber_now", false, MAP_WIDTH, MAP_HEIGHT);

		safetyLivability = new GridValueLayer("safetyLivability", false, MAP_WIDTH, MAP_HEIGHT);
		satisfaction = new GridValueLayer("satisfaction", false, MAP_WIDTH, MAP_HEIGHT);

		gridAverageSatisfaction = new GridValueLayer("gridAverageSatisfaction", false, MAP_WIDTH, MAP_HEIGHT);
		streetAverageSatisfaction = new GridValueLayer("streetAverageSatisfaction", false, MAP_WIDTH, MAP_HEIGHT);
		futianAverageSatisfaction = new GridValueLayer("futianAverageSatisfaction", false, MAP_WIDTH, MAP_HEIGHT);

		gridAverageLivability = new GridValueLayer("gridAverageLivability", false, MAP_WIDTH, MAP_HEIGHT);
		streetAverageLivability = new GridValueLayer("streetAverageLivability", false, MAP_WIDTH, MAP_HEIGHT);
		futianAverageLivability = new GridValueLayer("futianAverageLivability", false, MAP_WIDTH, MAP_HEIGHT);

		gridAveragePopulationDensity = new GridValueLayer("gridAveragePopulationDensity", false, MAP_WIDTH, MAP_HEIGHT);
		streetAveragePopulationDensity = new GridValueLayer("streetAveragePopulationDensity", false, MAP_WIDTH,
				MAP_HEIGHT);
		futianAveragePopulationDensity = new GridValueLayer("futianAveragePopulationDensity", false, MAP_WIDTH,
				MAP_HEIGHT);

		gridPoliceSafety = new GridValueLayer("gridSecuritySafety", false, MAP_WIDTH, MAP_HEIGHT);
		streetPoliceSafety = new GridValueLayer("streetSecuritySafety", false, MAP_WIDTH, MAP_HEIGHT);
		futianPoliceSafety = new GridValueLayer("futianSecuritySafety", false, MAP_WIDTH, MAP_HEIGHT);
		gridFireSafety = new GridValueLayer("gridFireSafety", false, MAP_WIDTH, MAP_HEIGHT);
		streetFireSafety = new GridValueLayer("streetFireSafety", false, MAP_WIDTH, MAP_HEIGHT);
		futianFireSafety = new GridValueLayer("futianFireSafety", false, MAP_WIDTH, MAP_HEIGHT);
		gridTrafficSafety = new GridValueLayer("gridTrafficSafety", false, MAP_WIDTH, MAP_HEIGHT);
		streetTrafficSafety = new GridValueLayer("streetTrafficSafety", false, MAP_WIDTH, MAP_HEIGHT);
		futianTrafficSafety = new GridValueLayer("futianTrafficSafety", false, MAP_WIDTH, MAP_HEIGHT);
		gridEmergencySafety = new GridValueLayer("gridEmergencySafety", false, MAP_WIDTH, MAP_HEIGHT);
		streetEmergencySafety = new GridValueLayer("streetEmergencySafety", false, MAP_WIDTH, MAP_HEIGHT);
		futianEmergencySafety = new GridValueLayer("futianEmergencySafety", false, MAP_WIDTH, MAP_HEIGHT);
		gridLivingSafety = new GridValueLayer("gridResidentSafety", false, MAP_WIDTH, MAP_HEIGHT);
		streetLivingSafety = new GridValueLayer("streetResidentSafety", false, MAP_WIDTH, MAP_HEIGHT);
		futianLivingSafety = new GridValueLayer("futianResidentSafety", false, MAP_WIDTH, MAP_HEIGHT);

		context.addValueLayer(border);
		context.addValueLayer(buildingCoverage);
		context.addValueLayer(streetDiagram);
		context.addValueLayer(populationDensity);
		context.addValueLayer(foreignResidentRatio);
		context.addValueLayer(gridDiagram);

		context.addValueLayer(trafficSafety);
		context.addValueLayer(livingSafety);
		context.addValueLayer(emergencySafety);
		context.addValueLayer(policeSafety);
		context.addValueLayer(fireSafety);

		context.addValueLayer(moveInFamilyNumber);
		context.addValueLayer(moveOutFamilyNumber);

		context.addValueLayer(moveInFamilyNumber_now);
		context.addValueLayer(moveOutFamilyNumber_now);

		context.addValueLayer(safetyLivability);
		context.addValueLayer(satisfaction);

		context.addValueLayer(gridAverageSatisfaction);
		context.addValueLayer(streetAverageSatisfaction);
		context.addValueLayer(futianAverageSatisfaction);

		context.addValueLayer(gridAverageLivability);
		context.addValueLayer(streetAverageLivability);
		context.addValueLayer(futianAverageLivability);

		context.addValueLayer(gridAveragePopulationDensity);
		context.addValueLayer(streetAveragePopulationDensity);
		context.addValueLayer(futianAveragePopulationDensity);

		context.addValueLayer(gridPoliceSafety);
		context.addValueLayer(streetPoliceSafety);
		context.addValueLayer(futianPoliceSafety);
		context.addValueLayer(gridFireSafety);
		context.addValueLayer(streetFireSafety);
		context.addValueLayer(futianFireSafety);
		context.addValueLayer(gridTrafficSafety);
		context.addValueLayer(streetTrafficSafety);
		context.addValueLayer(futianTrafficSafety);
		context.addValueLayer(gridEmergencySafety);
		context.addValueLayer(streetEmergencySafety);
		context.addValueLayer(futianEmergencySafety);
		context.addValueLayer(gridLivingSafety);
		context.addValueLayer(streetLivingSafety);
		context.addValueLayer(futianLivingSafety);

		borderData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		buildingCoverageData.ReadArcgisRasterData(MAP_URL + "BuildingCoverage.txt");
		streetDiagramData.ReadArcgisRasterData(MAP_URL + "StreetMap.txt");
		populationDensityData.ReadArcgisRasterData(MAP_URL + "PopulationDensity.txt");
		foreignResidentRatioData.ReadArcgisRasterData(MAP_URL + "PermanentResidentRatio.txt");
		gridDiagramData.ReadArcgisRasterData(MAP_URL + "GridMap.txt");

		trafficSafetyData.ReadArcgisRasterData(MAP_URL + "Traffic.txt");
		livingSafetyData.ReadArcgisRasterData(MAP_URL + "Living.txt");
		emergencySafetyData.ReadArcgisRasterData(MAP_URL + "Emergency.txt");
		policeSafetyData.ReadArcgisRasterData(MAP_URL + "Police.txt");
		fireSafetyData.ReadArcgisRasterData(MAP_URL + "Fire.txt");

		moveInFamilyNumberData.ReadArcgisRasterData(MAP_URL + "MoveIn.txt");
		moveOutFamilyNumberData.ReadArcgisRasterData(MAP_URL + "MoveOut.txt");

		moveInFamilyNumber_nowData.ReadArcgisRasterData(MAP_URL + "MoveOut.txt");
		moveOutFamilyNumber_nowData.ReadArcgisRasterData(MAP_URL + "MoveOut.txt");

		safetyLivabilityData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		satisfactionData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");

		gridAverageSatisfactionData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		streetAverageSatisfactionData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		futianAverageSatisfactionData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");

		gridAverageLivabilityData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		streetAverageLivabilityData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		futianAverageLivabilityData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");

		gridAveragePopulationDensityData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		streetAveragePopulationDensityData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		futianAveragePopulationDensityData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");

		gridPoliceSafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		streetPoliceSafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		futianPoliceSafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		gridFireSafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		streetFireSafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		futianFireSafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		gridTrafficSafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		streetTrafficSafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		futianTrafficSafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		gridEmergencySafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		streetEmergencySafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		futianEmergencySafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		gridLivingSafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		streetLivingSafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");
		futianLivingSafetyData.ReadArcgisRasterData(MAP_URL + "FutianDistrictBoundary.txt");

		BackgroundPicture backgroundPicture = new BackgroundPicture(MAP_WIDTH / 2, MAP_HEIGHT / 2, context, space,
				grid);
		context.add(backgroundPicture);
		grid.moveTo(backgroundPicture, backgroundPicture.getX(), backgroundPicture.getY());

		for (int i = 0; i < MAP_WIDTH; i++) {
			for (int j = 0; j < MAP_HEIGHT; j++) {
				double borderTemp = borderData.getData(i, j);
				double buildingCoverageTemp = buildingCoverageData.getData(i, j);
				double streetDiagramTemp = streetDiagramData.getData(i, j);
				double populationDensityTemp = populationDensityData.getData(i, j);
				double foreignResidentRatioTemp = foreignResidentRatioData.getData(i, j);
				double gridDiagramTemp = gridDiagramData.getData(i, j);

				double trafficSafetyTemp = trafficSafetyData.getData(i, j);
				double livingSafetyTemp = livingSafetyData.getData(i, j);
				double emergencySafetyTemp = emergencySafetyData.getData(i, j);
				double policeSafetyTemp = policeSafetyData.getData(i, j);
				double fireSafetyTemp = fireSafetyData.getData(i, j);

				double moveInFamilyNumberTemp = moveInFamilyNumberData.getData(i, j);
				double moveOutFamilyNumberTemp = moveOutFamilyNumberData.getData(i, j);

				double moveInFamilyNumber_nowTemp = moveInFamilyNumber_nowData.getData(i, j);
				double moveOutFamilyNumber_nowTemp = moveOutFamilyNumber_nowData.getData(i, j);

				double safetyLivabilityTemp = safetyLivabilityData.getData(i, j);
				double satisfactionTemp = satisfactionData.getData(i, j);

				double gridAverageSatisfactionTemp = gridAverageSatisfactionData.getData(i, j);
				double streetAverageSatisfactionTemp = streetAverageSatisfactionData.getData(i, j);
				double futianAverageSatisfactionTemp = futianAverageSatisfactionData.getData(i, j);

				double gridAverageLivabilityTemp = gridAverageLivabilityData.getData(i, j);
				double streetAverageLivabilityTemp = streetAverageLivabilityData.getData(i, j);
				double futianAverageLivabilityTemp = futianAverageLivabilityData.getData(i, j);

				double gridAveragePopulationDensityTemp = gridAveragePopulationDensityData.getData(i, j);
				double streetAveragePopulationDensityTemp = streetAveragePopulationDensityData.getData(i, j);
				double futianAveragePopulationDensityTemp = futianAveragePopulationDensityData.getData(i, j);

				double gridSecuritySafetyTemp = gridPoliceSafetyData.getData(i, j);
				double streetSecuritySafetyTemp = streetPoliceSafetyData.getData(i, j);
				double futianSecuritySafetyTemp = futianPoliceSafetyData.getData(i, j);
				double gridFireSafetyTemp = gridFireSafetyData.getData(i, j);
				double streetFireSafetyTemp = streetFireSafetyData.getData(i, j);
				double futianFireSafetyTemp = futianFireSafetyData.getData(i, j);
				double gridTrafficSafetyTemp = gridTrafficSafetyData.getData(i, j);
				double streetTrafficSafetyTemp = streetTrafficSafetyData.getData(i, j);
				double futianTrafficSafetyTemp = futianTrafficSafetyData.getData(i, j);
				double gridEmergencySafetyTemp = gridEmergencySafetyData.getData(i, j);
				double streetEmergencySafetyTemp = streetEmergencySafetyData.getData(i, j);
				double futianEmergencySafetyTemp = futianEmergencySafetyData.getData(i, j);
				double gridResidentSafetyTemp = gridLivingSafetyData.getData(i, j);
				double streetResidentSafetyTemp = streetLivingSafetyData.getData(i, j);
				double futianResidentSafetyTemp = futianLivingSafetyData.getData(i, j);

				border.set(borderTemp, i, j);
				buildingCoverage.set(buildingCoverageTemp, i, j);
				streetDiagram.set(streetDiagramTemp, i, j);
				populationDensity.set(populationDensityTemp, i, j);
				foreignResidentRatio.set(foreignResidentRatioTemp, i, j);
				gridDiagram.set(gridDiagramTemp, i, j);

				trafficSafety.set(trafficSafetyTemp, i, j);
				livingSafety.set(livingSafetyTemp, i, j);
				emergencySafety.set(emergencySafetyTemp, i, j);
				policeSafety.set(policeSafetyTemp, i, j);
				fireSafety.set(fireSafetyTemp, i, j);

				moveInFamilyNumber.set(moveInFamilyNumberTemp, i, j);
				moveOutFamilyNumber.set(moveOutFamilyNumberTemp, i, j);

				moveInFamilyNumber_now.set(moveInFamilyNumber_nowTemp, i, j);
				moveOutFamilyNumber_now.set(moveOutFamilyNumber_nowTemp, i, j);

				satisfaction.set(satisfactionTemp, i, j);
				gridAverageSatisfaction.set(gridAverageSatisfactionTemp, i, j);
				streetAverageSatisfaction.set(streetAverageSatisfactionTemp, i, j);
				futianAverageSatisfaction.set(futianAverageSatisfactionTemp, i, j);

				safetyLivability.set(safetyLivabilityTemp, i, j);
				gridAverageLivability.set(gridAverageLivabilityTemp, i, j);
				streetAverageLivability.set(streetAverageLivabilityTemp, i, j);
				futianAverageLivability.set(futianAverageLivabilityTemp, i, j);
				gridAveragePopulationDensity.set(gridAveragePopulationDensityTemp, i, j);
				streetAveragePopulationDensity.set(streetAveragePopulationDensityTemp, i, j);
				futianAveragePopulationDensity.set(futianAveragePopulationDensityTemp, i, j);

				gridPoliceSafety.set(gridSecuritySafetyTemp, i, j);
				streetPoliceSafety.set(streetSecuritySafetyTemp, i, j);
				futianPoliceSafety.set(futianSecuritySafetyTemp, i, j);
				gridFireSafety.set(gridFireSafetyTemp, i, j);
				streetFireSafety.set(streetFireSafetyTemp, i, j);
				futianFireSafety.set(futianFireSafetyTemp, i, j);
				gridTrafficSafety.set(gridTrafficSafetyTemp, i, j);
				streetTrafficSafety.set(streetTrafficSafetyTemp, i, j);
				futianTrafficSafety.set(futianTrafficSafetyTemp, i, j);
				gridEmergencySafety.set(gridEmergencySafetyTemp, i, j);
				streetEmergencySafety.set(streetEmergencySafetyTemp, i, j);
				futianEmergencySafety.set(futianEmergencySafetyTemp, i, j);
				gridLivingSafety.set(gridResidentSafetyTemp, i, j);
				streetLivingSafety.set(streetResidentSafetyTemp, i, j);
				futianLivingSafety.set(futianResidentSafetyTemp, i, j);

				if (borderTemp != -9999) {
					Cell cell = new Cell(i, j, context, space, grid);
					cellTeam.add(cell);

					cell.setBorder(borderTemp);
					cell.setBuildingCoverage(buildingCoverageTemp);
					cell.setTrafficCoverage(trafficSafetyTemp);
					cell.setStreetDiagram(streetDiagramTemp);
					cell.setPopulationDensity(populationDensityTemp);
					cell.setForeignResidentRatio(foreignResidentRatioTemp);
					cell.setGridDiagram(gridDiagramTemp);
					cell.setDisgustingFacilityCoverage(livingSafetyTemp);
					cell.setCityEmergencyFacilitiesCoverage(emergencySafetyTemp);
					cell.setPoliceFacilityCoverage(policeSafetyTemp);
					cell.setFireFacilityCoverage(fireSafetyTemp);

					cell.countSafetyLivabilityValue_1();

					int inhabitantNumber = (int) (Parameter.maxRate * populationDensityTemp * Parameter.cellSize
							/ Parameter.minRate);
					cell.setInhabitantNumber(inhabitantNumber);

					if (inhabitantNumber != 0) {
						cell.decideFamily();
					}
					cell.countInhabitantNumber();
					cell.setInhabitantNumber_last(cell.getInhabitantNumber());
					cell.countMigrantPopulation();
					cell.countForeignPopulation();
					cell.countCellSatisfaction();

					double Safe_police = policeSafetyTemp;
					double Safe_fire = fireSafetyTemp;
					double Safe_traffic = trafficSafetyTemp;
					double Safe_emergency = emergencySafetyTemp;
					double Safe_living = livingSafetyTemp;
					double SafetyLivability = Safe_police * Parameter.securitySafetyWeight
							+ Safe_fire * Parameter.fireSafetyWeight + Safe_traffic * Parameter.trafficSafetyWeight
							+ Safe_emergency * Parameter.emergencySafetyWeight
							+ Safe_living * Parameter.livingSafetyWeight;

					safetyLivability.set(SafetyLivability, i, j);
					satisfaction.set(cell.getCellSatisfaction(), i, j);

					cell.setSecuritySafetyValue(Safe_police);
					cell.setFireSafetyValue(Safe_fire);
					cell.setTrafficSafetyValue(Safe_traffic);
					cell.setEmergencySafetyValue(Safe_emergency);
					cell.setResidentSafetyValue(Safe_living);
					cell.setSafetyLivabilityValue(SafetyLivability);

					context.add(cell);
					grid.moveTo(cell, i, j);

				}
			}
		}

		this.countGridSatisfaction();
		this.countStreetSatisfaction();
		this.countFutianSatisfaction();

		for (int i1 = 0; i1 < cellTeam.size(); i1++) {
			satisfaction.set(cellTeam.get(i1).getCellSatisfaction(), cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
			for (int j1 = 0; j1 < gridSatisfaction.length; j1++) {
				if (cellTeam.get(i1).getGridDiagram() == j1) {
					gridAverageSatisfaction.set(gridSatisfaction[j1], cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
				}
			}
			for (int j1 = 0; j1 < streetSatisfaction.length; j1++) {
				if (cellTeam.get(i1).getStreetDiagram() == j1 + 1) {
					streetAverageSatisfaction.set(streetSatisfaction[j1], cellTeam.get(i1).getX(),
							cellTeam.get(i1).getY());
				}
			}
			futianAverageSatisfaction.set(futianSatisfaction, cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
		}

		this.countGridLivability();
		this.countStreetLivability();
		this.countFutianLivability();

		for (int i1 = 0; i1 < cellTeam.size(); i1++) {
			safetyLivability.set(cellTeam.get(i1).getSafetyLivabilityValue(), cellTeam.get(i1).getX(),
					cellTeam.get(i1).getY());
			for (int j1 = 0; j1 < gridLivability.length; j1++) {
				if (cellTeam.get(i1).getGridDiagram() == j1) {
					gridAverageLivability.set(gridLivability[j1], cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
				}
			}
			for (int j1 = 0; j1 < streetLivability.length; j1++) {
				if (cellTeam.get(i1).getStreetDiagram() == j1 + 1) {
					streetAverageLivability.set(streetLivability[j1], cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
				}
			}
			futianAverageLivability.set(futianLivability, cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
		}

		this.countGridPopulationDensity();
		this.countStreetPopulationDensity();
		this.countFutianPopulationDensity();

		for (int i1 = 0; i1 < cellTeam.size(); i1++) {
			populationDensity.set(cellTeam.get(i1).getPopulationDensity(), cellTeam.get(i1).getX(),
					cellTeam.get(i1).getY());
			for (int j1 = 0; j1 < gridPopulationDensity.length; j1++) {
				if (cellTeam.get(i1).getGridDiagram() == j1) {
					gridAveragePopulationDensity.set(gridPopulationDensity[j1], cellTeam.get(i1).getX(),
							cellTeam.get(i1).getY());
				}
			}
			for (int j1 = 0; j1 < streetPopulationDensity.length; j1++) {
				if (cellTeam.get(i1).getStreetDiagram() == j1 + 1) {
					streetAveragePopulationDensity.set(streetPopulationDensity[j1], cellTeam.get(i1).getX(),
							cellTeam.get(i1).getY());
				}
			}
			futianAveragePopulationDensity.set(futianPopulationDensity, cellTeam.get(i1).getX(),
					cellTeam.get(i1).getY());
		}

		countStreetSafetyValue();
		countGridSafeValue();

		for (int i1 = 0; i1 < cellTeam.size(); i1++) {

			for (int j1 = 0; j1 < Parameter.gridNumber; j1++) {
				if (cellTeam.get(i1).getGridDiagram() == j1) {
					gridPoliceSafety.set(gridSecuritySafetyValue[j1], cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
					gridFireSafety.set(gridFireSafetyValue[j1], cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
					gridTrafficSafety.set(gridTrafficSafetyValue[j1], cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
					gridEmergencySafety.set(gridEmergencySafetyValue[j1], cellTeam.get(i1).getX(),
							cellTeam.get(i1).getY());
					gridLivingSafety.set(gridResidentSafetyValue[j1], cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
				}
			}

			for (int j1 = 0; j1 < Parameter.streetNumber; j1++) {
				if (cellTeam.get(i1).getStreetDiagram() == j1 + 1) {
					streetPoliceSafety.set(streetSecuritySafetyValue[j1], cellTeam.get(i1).getX(),
							cellTeam.get(i1).getY());
					streetFireSafety.set(streetFireSafetyValue[j1], cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
					streetTrafficSafety.set(streetTrafficSafetyValue[j1], cellTeam.get(i1).getX(),
							cellTeam.get(i1).getY());
					streetEmergencySafety.set(streetEmergencySafetyValue[j1], cellTeam.get(i1).getX(),
							cellTeam.get(i1).getY());
					streetLivingSafety.set(streetResidentSafetyValue[j1], cellTeam.get(i1).getX(),
							cellTeam.get(i1).getY());
				}
			}
		}
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(f1.format(new Date()) + "Environment is about to be generated!");

		DataStorage dataStorage = DataStorage.getInstance();
		context.add(dataStorage);
		grid.moveTo(dataStorage, 0, 0);
		context.add(this);
		grid.moveTo(this, 0, 0);

		try {
			for (int i = 0; i < cellTeam.size(); i++) {
				DataStorage.getInstance().setResidentPopulation((int) cellTeam.get(i).getInhabitantNumber()
						+ DataStorage.getInstance().getResidentPopulation());

				int streetID = (int) cellTeam.get(i).getStreetDiagram();
				if (streetID == 1) {
					DataStorage.getInstance().setStreetPopulation_1((int) cellTeam.get(i).getInhabitantNumber()
							+ DataStorage.getInstance().getStreetPopulation_1());
				} else if (streetID == 2) {
					DataStorage.getInstance().setStreetPopulation_2((int) cellTeam.get(i).getInhabitantNumber()
							+ DataStorage.getInstance().getStreetPopulation_2());
				} else if (streetID == 3) {
					DataStorage.getInstance().setStreetPopulation_3((int) cellTeam.get(i).getInhabitantNumber()
							+ DataStorage.getInstance().getStreetPopulation_3());
				} else if (streetID == 4) {
					DataStorage.getInstance().setStreetPopulation_4((int) cellTeam.get(i).getInhabitantNumber()
							+ DataStorage.getInstance().getStreetPopulation_4());
				} else if (streetID == 5) {
					DataStorage.getInstance().setStreetPopulation_5((int) cellTeam.get(i).getInhabitantNumber()
							+ DataStorage.getInstance().getStreetPopulation_5());
				} else if (streetID == 6) {
					DataStorage.getInstance().setStreetPopulation_6((int) cellTeam.get(i).getInhabitantNumber()
							+ DataStorage.getInstance().getStreetPopulation_6());
				} else if (streetID == 7) {
					DataStorage.getInstance().setStreetPopulation_7((int) cellTeam.get(i).getInhabitantNumber()
							+ DataStorage.getInstance().getStreetPopulation_7());
				} else if (streetID == 8) {
					DataStorage.getInstance().setStreetPopulation_8((int) cellTeam.get(i).getInhabitantNumber()
							+ DataStorage.getInstance().getStreetPopulation_8());
				} else if (streetID == 9) {
					DataStorage.getInstance().setStreetPopulation_9((int) cellTeam.get(i).getInhabitantNumber()
							+ DataStorage.getInstance().getStreetPopulation_9());
				} else if (streetID == 10) {
					DataStorage.getInstance().setStreetPopulation_10((int) cellTeam.get(i).getInhabitantNumber()
							+ DataStorage.getInstance().getStreetPopulation_10());
				}

				if (cellTeam.get(i).getSafetyLivabilityValue() <= 0.1) {
					DataStorage.getInstance().setLivability_1(DataStorage.getInstance().getLivability_1() + 1);
				} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.1
						&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.2) {
					DataStorage.getInstance().setLivability_2(DataStorage.getInstance().getLivability_2() + 1);
				} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.2
						&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.3) {
					DataStorage.getInstance().setLivability_3(DataStorage.getInstance().getLivability_3() + 1);
				} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.3
						&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.4) {
					DataStorage.getInstance().setLivability_4(DataStorage.getInstance().getLivability_4() + 1);
				} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.4
						&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.5) {
					DataStorage.getInstance().setLivability_5(DataStorage.getInstance().getLivability_5() + 1);
				} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.5
						&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.6) {
					DataStorage.getInstance().setLivability_6(DataStorage.getInstance().getLivability_6() + 1);
				} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.6
						&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.7) {
					DataStorage.getInstance().setLivability_7(DataStorage.getInstance().getLivability_7() + 1);
				} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.7
						&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.8) {
					DataStorage.getInstance().setLivability_8(DataStorage.getInstance().getLivability_8() + 1);
				} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.8
						&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.9) {
					DataStorage.getInstance().setLivability_9(DataStorage.getInstance().getLivability_9() + 1);
				} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.9) {
					DataStorage.getInstance().setLivability_10(DataStorage.getInstance().getLivability_10() + 1);
				}
				for (int j = 0; j < cellTeam.get(i).getFamilyTeam().size(); j++) {
					int inhabitantNumber = cellTeam.get(i).getFamilyTeam().get(j).getInhabitantTeam().size();
					double satisfaction = cellTeam.get(i).getFamilyTeam().get(j).getFamilySatisfaction();
					double income = cellTeam.get(i).getFamilyTeam().get(j).getIncome();

					DataStorage.getInstance().setAllFamilyNumber(DataStorage.getInstance().getAllFamilyNumber() + 1);
					if (inhabitantNumber == 1) {
						DataStorage.getInstance().setFamilyInhabitantNumber_1(
								DataStorage.getInstance().getFamilyInhabitantNumber_1() + 1);
					} else if (inhabitantNumber == 2) {
						DataStorage.getInstance().setFamilyInhabitantNumber_2(
								DataStorage.getInstance().getFamilyInhabitantNumber_2() + 1);
					} else if (inhabitantNumber == 3) {
						DataStorage.getInstance().setFamilyInhabitantNumber_3(
								DataStorage.getInstance().getFamilyInhabitantNumber_3() + 1);
					} else if (inhabitantNumber == 4) {
						DataStorage.getInstance().setFamilyInhabitantNumber_4(
								DataStorage.getInstance().getFamilyInhabitantNumber_4() + 1);
					} else if (inhabitantNumber == 5) {
						DataStorage.getInstance().setFamilyInhabitantNumber_5(
								DataStorage.getInstance().getFamilyInhabitantNumber_5() + 1);
					} else if (inhabitantNumber == 6) {
						DataStorage.getInstance().setFamilyInhabitantNumber_6(
								DataStorage.getInstance().getFamilyInhabitantNumber_6() + 1);
					} else if (inhabitantNumber == 7) {
						DataStorage.getInstance().setFamilyInhabitantNumber_7(
								DataStorage.getInstance().getFamilyInhabitantNumber_7() + 1);
					} else if (inhabitantNumber == 8) {
						DataStorage.getInstance().setFamilyInhabitantNumber_8(
								DataStorage.getInstance().getFamilyInhabitantNumber_8() + 1);
					} else if (inhabitantNumber == 9) {
						DataStorage.getInstance().setFamilyInhabitantNumber_9(
								DataStorage.getInstance().getFamilyInhabitantNumber_9() + 1);
					} else if (inhabitantNumber == 10) {
						DataStorage.getInstance().setFamilyInhabitantNumber_10(
								DataStorage.getInstance().getFamilyInhabitantNumber_10() + 1);
					}
					if (satisfaction <= 0.1) {
						DataStorage.getInstance().setSatisfaction_1(DataStorage.getInstance().getSatisfaction_1() + 1);
					} else if (satisfaction >= 0.1 && satisfaction <= 0.2) {
						DataStorage.getInstance().setSatisfaction_2(DataStorage.getInstance().getSatisfaction_2() + 1);
					} else if (satisfaction >= 0.2 && satisfaction <= 0.3) {
						DataStorage.getInstance().setSatisfaction_3(DataStorage.getInstance().getSatisfaction_3() + 1);
					} else if (satisfaction >= 0.3 && satisfaction <= 0.4) {
						DataStorage.getInstance().setSatisfaction_4(DataStorage.getInstance().getSatisfaction_4() + 1);
					} else if (satisfaction >= 0.4 && satisfaction <= 0.5) {
						DataStorage.getInstance().setSatisfaction_5(DataStorage.getInstance().getSatisfaction_5() + 1);
					} else if (satisfaction >= 0.5 && satisfaction <= 0.6) {
						DataStorage.getInstance().setSatisfaction_6(DataStorage.getInstance().getSatisfaction_6() + 1);
					} else if (satisfaction >= 0.6 && satisfaction <= 0.7) {
						DataStorage.getInstance().setSatisfaction_7(DataStorage.getInstance().getSatisfaction_7() + 1);
					} else if (satisfaction >= 0.7 && satisfaction <= 0.8) {
						DataStorage.getInstance().setSatisfaction_8(DataStorage.getInstance().getSatisfaction_8() + 1);
					} else if (satisfaction >= 0.8 && satisfaction <= 0.9) {
						DataStorage.getInstance().setSatisfaction_9(DataStorage.getInstance().getSatisfaction_9() + 1);
					} else if (satisfaction >= 0.9) {
						DataStorage.getInstance()
								.setSatisfaction_10(DataStorage.getInstance().getSatisfaction_10() + 1);
					}
					if (income >= 0 && income <= 50000) {
						DataStorage.getInstance().setIncome_1(DataStorage.getInstance().getIncome_1() + 1);
					} else if (income >= 50000 && income <= 100000) {
						DataStorage.getInstance().setIncome_2(DataStorage.getInstance().getIncome_2() + 1);
					} else if (income >= 100000 && income <= 150000) {
						DataStorage.getInstance().setIncome_3(DataStorage.getInstance().getIncome_3() + 1);
					} else if (income >= 150000 && income <= 200000) {
						DataStorage.getInstance().setIncome_4(DataStorage.getInstance().getIncome_4() + 1);
					} else if (income >= 200000 && income <= 250000) {
						DataStorage.getInstance().setIncome_5(DataStorage.getInstance().getIncome_5() + 1);
					} else if (income >= 250000 && income <= 300000) {
						DataStorage.getInstance().setIncome_6(DataStorage.getInstance().getIncome_6() + 1);
					} else if (income >= 300000 && income <= 350000) {
						DataStorage.getInstance().setIncome_7(DataStorage.getInstance().getIncome_7() + 1);
					} else if (income >= 350000 && income <= 400000) {
						DataStorage.getInstance().setIncome_8(DataStorage.getInstance().getIncome_8() + 1);
					} else if (income >= 400000 && income <= 450000) {
						DataStorage.getInstance().setIncome_9(DataStorage.getInstance().getIncome_9() + 1);
					} else {
						DataStorage.getInstance().setIncome_10(DataStorage.getInstance().getIncome_10() + 1);
					}

					for (int k = 0; k < cellTeam.get(i).getFamilyTeam().get(j).getInhabitantTeam().size(); k++) {
						int gender = cellTeam.get(i).getFamilyTeam().get(j).getInhabitantTeam().get(k).getGender();
						int age = cellTeam.get(i).getFamilyTeam().get(j).getInhabitantTeam().get(k).getAge();
						int educationLevel = cellTeam.get(i).getFamilyTeam().get(j).getInhabitantTeam().get(k)
								.getEducationLevel();
						if (gender == 1) {
							DataStorage.getInstance().setGender_1(DataStorage.getInstance().getGender_1() + 1);
						} else {
							DataStorage.getInstance().setGender_2(DataStorage.getInstance().getGender_2() + 1);
						}
						if (age >= 0 && age <= 10) {
							DataStorage.getInstance().setAge_0_10(DataStorage.getInstance().getAge_0_10() + 1);
						} else if (age >= 11 && age <= 20) {
							DataStorage.getInstance().setAge_11_20(DataStorage.getInstance().getAge_11_20() + 1);
						} else if (age >= 21 && age <= 30) {
							DataStorage.getInstance().setAge_21_30(DataStorage.getInstance().getAge_21_30() + 1);
						} else if (age >= 31 && age <= 40) {
							DataStorage.getInstance().setAge_31_40(DataStorage.getInstance().getAge_31_40() + 1);
						} else if (age >= 41 && age <= 50) {
							DataStorage.getInstance().setAge_41_50(DataStorage.getInstance().getAge_41_50() + 1);
						} else if (age >= 51 && age <= 60) {
							DataStorage.getInstance().setAge_51_60(DataStorage.getInstance().getAge_51_60() + 1);
						} else if (age >= 61 && age <= 70) {
							DataStorage.getInstance().setAge_61_70(DataStorage.getInstance().getAge_61_70() + 1);
						} else if (age >= 71 && age <= 80) {
							DataStorage.getInstance().setAge_71_80(DataStorage.getInstance().getAge_71_80() + 1);
						} else if (age >= 81 && age <= 90) {
							DataStorage.getInstance().setAge_81_90(DataStorage.getInstance().getAge_81_90() + 1);
						} else if (age >= 91 && age <= 100) {
							DataStorage.getInstance().setAge_91_100(DataStorage.getInstance().getAge_91_100() + 1);
						}
						if (educationLevel == 0) {
							DataStorage.getInstance()
									.setEducationLevel_0(DataStorage.getInstance().getEducationLevel_0() + 1);
						} else if (educationLevel == 1) {
							DataStorage.getInstance()
									.setEducationLevel_1(DataStorage.getInstance().getEducationLevel_1() + 1);
						} else if (educationLevel == 2) {
							DataStorage.getInstance()
									.setEducationLevel_2(DataStorage.getInstance().getEducationLevel_2() + 1);
						} else if (educationLevel == 3) {
							DataStorage.getInstance()
									.setEducationLevel_3(DataStorage.getInstance().getEducationLevel_3() + 1);
						} else if (educationLevel == 4) {
							DataStorage.getInstance()
									.setEducationLevel_4(DataStorage.getInstance().getEducationLevel_4() + 1);
						} else if (educationLevel == 5) {
							DataStorage.getInstance()
									.setEducationLevel_5(DataStorage.getInstance().getEducationLevel_5() + 1);
						} else if (educationLevel == 6) {
							DataStorage.getInstance()
									.setEducationLevel_6(DataStorage.getInstance().getEducationLevel_6() + 1);
						} else if (educationLevel == 7) {
							DataStorage.getInstance()
									.setEducationLevel_7(DataStorage.getInstance().getEducationLevel_7() + 1);
						} else if (educationLevel == 8) {
							DataStorage.getInstance()
									.setEducationLevel_8(DataStorage.getInstance().getEducationLevel_8() + 1);
						} else {
							DataStorage.getInstance()
									.setEducationLevel_9(DataStorage.getInstance().getEducationLevel_9() + 1);
						}
					}
				}
			}
			DataStorage.getInstance().setGridSatisfaction(gridSatisfaction);
			DataStorage.getInstance().setStreetSatisfaction(streetSatisfaction);
			DataStorage.getInstance().setFutianSatisfaction(futianSatisfaction);
			DataStorage.getInstance().setGridLivability(gridLivability);
			DataStorage.getInstance().setStreetLivability(streetLivability);
			DataStorage.getInstance().setFutianLivability(futianLivability);

			DataCollection dataCollection = DataCollection.getInstance();
			dataCollection.setGridSatisfaction(DataStorage.getInstance().getGridSatisfaction());
			dataCollection.setStreetSatisfaction(DataStorage.getInstance().getStreetSatisfaction());
			dataCollection.setFutianSatisfaction(DataStorage.getInstance().getFutianSatisfaction());
			dataCollection.setGridLivability(DataStorage.getInstance().getGridLivability());
			dataCollection.setStreetLivability(DataStorage.getInstance().getStreetLivability());
			dataCollection.setFutianLivability(DataStorage.getInstance().getFutianLivability());
			dataCollection.setResidentPopulation(DataStorage.getInstance().getResidentPopulation());
			dataCollection.setForeignPopulation(DataStorage.getInstance().getForeignPopulation());

			dataCollection.setGender_1(DataStorage.getInstance().getGender_1());
			dataCollection.setGender_2(DataStorage.getInstance().getGender_2());
			dataCollection.setAge_0_10(DataStorage.getInstance().getAge_0_10());
			dataCollection.setAge_11_20(DataStorage.getInstance().getAge_11_20());
			dataCollection.setAge_21_30(DataStorage.getInstance().getAge_21_30());
			dataCollection.setAge_31_40(DataStorage.getInstance().getAge_31_40());
			dataCollection.setAge_41_50(DataStorage.getInstance().getAge_41_50());
			dataCollection.setAge_51_60(DataStorage.getInstance().getAge_51_60());
			dataCollection.setAge_61_70(DataStorage.getInstance().getAge_61_70());
			dataCollection.setAge_71_80(DataStorage.getInstance().getAge_71_80());
			dataCollection.setAge_81_90(DataStorage.getInstance().getAge_81_90());
			dataCollection.setAge_91_100(DataStorage.getInstance().getAge_91_100());
			dataCollection.setEducationLevel_0(DataStorage.getInstance().getEducationLevel_0());
			dataCollection.setEducationLevel_1(DataStorage.getInstance().getEducationLevel_1());
			dataCollection.setEducationLevel_2(DataStorage.getInstance().getEducationLevel_2());
			dataCollection.setEducationLevel_3(DataStorage.getInstance().getEducationLevel_3());
			dataCollection.setEducationLevel_4(DataStorage.getInstance().getEducationLevel_4());
			dataCollection.setEducationLevel_5(DataStorage.getInstance().getEducationLevel_5());
			dataCollection.setEducationLevel_6(DataStorage.getInstance().getEducationLevel_6());
			dataCollection.setEducationLevel_7(DataStorage.getInstance().getEducationLevel_7());
			dataCollection.setEducationLevel_8(DataStorage.getInstance().getEducationLevel_8());
			dataCollection.setEducationLevel_9(DataStorage.getInstance().getEducationLevel_9());
			dataCollection.setFamilyInhabitantNumber_1(DataStorage.getInstance().getAllFamilyNumber());
			dataCollection.setFamilyInhabitantNumber_1(DataStorage.getInstance().getFamilyInhabitantNumber_1());
			dataCollection.setFamilyInhabitantNumber_2(DataStorage.getInstance().getFamilyInhabitantNumber_2());
			dataCollection.setFamilyInhabitantNumber_3(DataStorage.getInstance().getFamilyInhabitantNumber_3());
			dataCollection.setFamilyInhabitantNumber_4(DataStorage.getInstance().getFamilyInhabitantNumber_4());
			dataCollection.setFamilyInhabitantNumber_5(DataStorage.getInstance().getFamilyInhabitantNumber_5());
			dataCollection.setFamilyInhabitantNumber_6(DataStorage.getInstance().getFamilyInhabitantNumber_6());
			dataCollection.setFamilyInhabitantNumber_7(DataStorage.getInstance().getFamilyInhabitantNumber_7());
			dataCollection.setFamilyInhabitantNumber_8(DataStorage.getInstance().getFamilyInhabitantNumber_8());
			dataCollection.setFamilyInhabitantNumber_9(DataStorage.getInstance().getFamilyInhabitantNumber_9());
			dataCollection.setFamilyInhabitantNumber_10(DataStorage.getInstance().getFamilyInhabitantNumber_10());

			dataCollection.setStreetPopulation_1(DataStorage.getInstance().getStreetPopulation_1());
			dataCollection.setStreetPopulation_2(DataStorage.getInstance().getStreetPopulation_2());
			dataCollection.setStreetPopulation_3(DataStorage.getInstance().getStreetPopulation_3());
			dataCollection.setStreetPopulation_4(DataStorage.getInstance().getStreetPopulation_4());
			dataCollection.setStreetPopulation_5(DataStorage.getInstance().getStreetPopulation_5());
			dataCollection.setStreetPopulation_6(DataStorage.getInstance().getStreetPopulation_6());
			dataCollection.setStreetPopulation_7(DataStorage.getInstance().getStreetPopulation_7());
			dataCollection.setStreetPopulation_8(DataStorage.getInstance().getStreetPopulation_8());
			dataCollection.setStreetPopulation_9(DataStorage.getInstance().getStreetPopulation_9());
			dataCollection.setStreetPopulation_10(DataStorage.getInstance().getStreetPopulation_10());

			dataCollection.setSatisfaction_1(DataStorage.getInstance().getSatisfaction_1());
			dataCollection.setSatisfaction_2(DataStorage.getInstance().getSatisfaction_2());
			dataCollection.setSatisfaction_3(DataStorage.getInstance().getSatisfaction_3());
			dataCollection.setSatisfaction_4(DataStorage.getInstance().getSatisfaction_4());
			dataCollection.setSatisfaction_5(DataStorage.getInstance().getSatisfaction_5());
			dataCollection.setSatisfaction_6(DataStorage.getInstance().getSatisfaction_6());
			dataCollection.setSatisfaction_7(DataStorage.getInstance().getSatisfaction_7());
			dataCollection.setSatisfaction_8(DataStorage.getInstance().getSatisfaction_8());
			dataCollection.setSatisfaction_9(DataStorage.getInstance().getSatisfaction_9());
			dataCollection.setSatisfaction_10(DataStorage.getInstance().getSatisfaction_10());

			dataCollection.setLivability_1(DataStorage.getInstance().getLivability_1());
			dataCollection.setLivability_2(DataStorage.getInstance().getLivability_2());
			dataCollection.setLivability_3(DataStorage.getInstance().getLivability_3());
			dataCollection.setLivability_4(DataStorage.getInstance().getLivability_4());
			dataCollection.setLivability_5(DataStorage.getInstance().getLivability_5());
			dataCollection.setLivability_6(DataStorage.getInstance().getLivability_6());
			dataCollection.setLivability_7(DataStorage.getInstance().getLivability_7());
			dataCollection.setLivability_8(DataStorage.getInstance().getLivability_8());
			dataCollection.setLivability_9(DataStorage.getInstance().getLivability_9());
			dataCollection.setLivability_10(DataStorage.getInstance().getLivability_10());

			dataCollection.setIncome_1(DataStorage.getInstance().getIncome_1());
			dataCollection.setIncome_2(DataStorage.getInstance().getIncome_2());
			dataCollection.setIncome_3(DataStorage.getInstance().getIncome_3());
			dataCollection.setIncome_4(DataStorage.getInstance().getIncome_4());
			dataCollection.setIncome_5(DataStorage.getInstance().getIncome_5());
			dataCollection.setIncome_6(DataStorage.getInstance().getIncome_6());
			dataCollection.setIncome_7(DataStorage.getInstance().getIncome_7());
			dataCollection.setIncome_8(DataStorage.getInstance().getIncome_8());
			dataCollection.setIncome_9(DataStorage.getInstance().getIncome_9());
			dataCollection.setIncome_10(DataStorage.getInstance().getIncome_10());

			excel.step(dataCollection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return context;
	}

	private void countStreetSatisfaction() {
		int[] cellNumber = new int[Parameter.streetNumber];
		double[] satisfaction = new double[Parameter.streetNumber];
		for (int i = 0; i < satisfaction.length; i++) {
			for (int j = 0; j < cellTeam.size(); j++) {
				if (cellTeam.get(j).getStreetDiagram() == i + 1 && cellTeam.get(j).getInhabitantNumber() > 0) {
					cellNumber[i]++;
					satisfaction[i] = satisfaction[i] + cellTeam.get(j).getCellSatisfaction();
				}
			}
		}

		for (int i = 0; i < cellNumber.length; i++) {
			streetSatisfaction[i] = satisfaction[i] / cellNumber[i];
		}
	}

	private void countGridSatisfaction() {
		int[] cellNumber = new int[Parameter.gridNumber];
		double[] satisfaction = new double[Parameter.gridNumber];
		for (int i = 0; i < satisfaction.length; i++) {
			for (int j = 0; j < cellTeam.size(); j++) {
				if (cellTeam.get(j).getGridDiagram() == i && cellTeam.get(j).getInhabitantNumber() > 0) {
					cellNumber[i]++;
					satisfaction[i] = satisfaction[i] + cellTeam.get(j).getCellSatisfaction();
				}
			}
		}

		for (int i = 0; i < cellNumber.length; i++) {
			gridSatisfaction[i] = satisfaction[i] / cellNumber[i];
		}
	}

	private void countFutianSatisfaction() {
		int cellNumber = 0;
		double satisfaction = 0;
		for (int j = 0; j < cellTeam.size(); j++) {
			if (cellTeam.get(j).getInhabitantNumber() > 0) {
				cellNumber++;
				satisfaction = satisfaction + cellTeam.get(j).getCellSatisfaction();
			}
		}

		futianSatisfaction = satisfaction / cellNumber;
	}

	private void countStreetLivability() {
		int[] cellNumber = new int[Parameter.streetNumber];
		double[] Livability = new double[Parameter.streetNumber];
		for (int i = 0; i < Livability.length; i++) {
			for (int j = 0; j < cellTeam.size(); j++) {
				if (cellTeam.get(j).getStreetDiagram() == i + 1) {
					cellNumber[i]++;
					Livability[i] = Livability[i] + cellTeam.get(j).getSafetyLivabilityValue();
				}
			}
		}

		for (int i = 0; i < cellNumber.length; i++) {
			streetLivability[i] = Livability[i] / cellNumber[i];
		}

	}

	private void countGridLivability() {
		int[] cellNumber = new int[Parameter.gridNumber];
		double[] Livability = new double[Parameter.gridNumber];
		for (int i = 0; i < Livability.length; i++) {
			for (int j = 0; j < cellTeam.size(); j++) {
				if (cellTeam.get(j).getGridDiagram() == i) {
					cellNumber[i]++;
					Livability[i] = Livability[i] + cellTeam.get(j).getSafetyLivabilityValue();
				}
			}
		}

		for (int i = 0; i < cellNumber.length; i++) {
			gridLivability[i] = Livability[i] / cellNumber[i];
		}

	}

	private void countFutianLivability() {
		int cellNumber = 0;
		double Livability = 0;
		for (int j = 0; j < cellTeam.size(); j++) {
			cellNumber++;
			Livability = Livability + cellTeam.get(j).getSafetyLivabilityValue();
		}

		futianLivability = Livability / cellNumber;
	}

	private void countStreetPopulationDensity() {
		int[] cellNumber = new int[Parameter.streetNumber];
		double[] PopulationDensity = new double[Parameter.streetNumber];
		for (int i = 0; i < PopulationDensity.length; i++) {
			for (int j = 0; j < cellTeam.size(); j++) {
				if (cellTeam.get(j).getStreetDiagram() == i + 1) {
					cellNumber[i]++;
					PopulationDensity[i] = PopulationDensity[i] + cellTeam.get(j).getPopulationDensity();
				}
			}
		}

		for (int i = 0; i < cellNumber.length; i++) {
			streetPopulationDensity[i] = PopulationDensity[i] / cellNumber[i];
		}
	}

	private void countGridPopulationDensity() {
		int[] cellNumber = new int[Parameter.gridNumber];
		double[] PopulationDensity = new double[Parameter.gridNumber];
		for (int i = 0; i < PopulationDensity.length; i++) {
			for (int j = 0; j < cellTeam.size(); j++) {
				if (cellTeam.get(j).getGridDiagram() == i) {
					cellNumber[i]++;
					PopulationDensity[i] = PopulationDensity[i] + cellTeam.get(j).getPopulationDensity();
				}
			}
		}

		for (int i = 0; i < cellNumber.length; i++) {
			gridPopulationDensity[i] = PopulationDensity[i] / cellNumber[i];
		}

	}

	private void countFutianPopulationDensity() {
		int cellNumber = 0;
		double PopulationDensity = 0;
		for (int j = 0; j < cellTeam.size(); j++) {
			cellNumber++;
			PopulationDensity = PopulationDensity + cellTeam.get(j).getPopulationDensity();
		}

		futianPopulationDensity = PopulationDensity / cellNumber;
	}

	public static Cell searchCell(int x, int y) {
		Cell cell = null;

		for (int i = 0; i < cellTeam.size(); i++) {
			if (x == cellTeam.get(i).getX() && y == cellTeam.get(i).getY()) {
				cell = cellTeam.get(i);
			}
		}

		return cell;
	}

	private void countStreetSafetyValue() {
		int[] cellNumber = new int[Parameter.streetNumber];
		double[] streetSecuritySafetyValueTemp = new double[Parameter.streetNumber];
		double[] streetFireSafetyValueTemp = new double[Parameter.streetNumber];
		double[] streetTrafficSafetyValueTemp = new double[Parameter.streetNumber];
		double[] streetEmergencySafetyValueTemp = new double[Parameter.streetNumber];
		double[] streetResidentSafetyValueTemp = new double[Parameter.streetNumber];

		for (int i = 0; i < Parameter.streetNumber; i++) {
			for (int j = 0; j < cellTeam.size(); j++) {
				if (cellTeam.get(j).getStreetDiagram() == i + 1) {
					cellNumber[i]++;
					streetSecuritySafetyValueTemp[i] = streetSecuritySafetyValueTemp[i]
							+ cellTeam.get(j).getSecuritySafetyValue();
					streetFireSafetyValueTemp[i] = streetFireSafetyValueTemp[i] + cellTeam.get(j).getFireSafetyValue();
					streetTrafficSafetyValueTemp[i] = streetTrafficSafetyValueTemp[i]
							+ cellTeam.get(j).getTrafficSafetyValue();
					streetEmergencySafetyValueTemp[i] = streetEmergencySafetyValueTemp[i]
							+ cellTeam.get(j).getEmergencySafetyValue();
					streetResidentSafetyValueTemp[i] = streetResidentSafetyValueTemp[i]
							+ cellTeam.get(j).getResidentSafetyValue();
				}
			}
		}

		for (int i = 0; i < cellNumber.length; i++) {
			streetSecuritySafetyValue[i] = streetSecuritySafetyValueTemp[i] / cellNumber[i];
			streetFireSafetyValue[i] = streetFireSafetyValueTemp[i] / cellNumber[i];
			streetTrafficSafetyValue[i] = streetTrafficSafetyValueTemp[i] / cellNumber[i];
			streetEmergencySafetyValue[i] = streetEmergencySafetyValueTemp[i] / cellNumber[i];
			streetResidentSafetyValue[i] = streetResidentSafetyValueTemp[i] / cellNumber[i];
		}

	}

	private void countGridSafeValue() {
		int[] cellNumber = new int[Parameter.gridNumber];
		double[] gridSecuritySafetyValueTemp = new double[Parameter.gridNumber];
		double[] gridFireSafetyValueTemp = new double[Parameter.gridNumber];
		double[] gridTrafficSafetyValueTemp = new double[Parameter.gridNumber];
		double[] gridEmergencySafetyValueTemp = new double[Parameter.gridNumber];
		double[] gridResidentSafetyValueTemp = new double[Parameter.gridNumber];
		for (int i = 0; i < Parameter.gridNumber; i++) {
			for (int j = 0; j < cellTeam.size(); j++) {
				if (cellTeam.get(j).getGridDiagram() == i) {
					cellNumber[i]++;
					gridSecuritySafetyValueTemp[i] = gridSecuritySafetyValueTemp[i]
							+ cellTeam.get(j).getSecuritySafetyValue();
					gridFireSafetyValueTemp[i] = gridFireSafetyValueTemp[i] + cellTeam.get(j).getFireSafetyValue();
					gridTrafficSafetyValueTemp[i] = gridTrafficSafetyValueTemp[i]
							+ cellTeam.get(j).getTrafficSafetyValue();
					gridEmergencySafetyValueTemp[i] = gridEmergencySafetyValueTemp[i]
							+ cellTeam.get(j).getEmergencySafetyValue();
					gridResidentSafetyValueTemp[i] = gridResidentSafetyValueTemp[i]
							+ cellTeam.get(j).getResidentSafetyValue();
				}
			}
		}

		for (int i = 0; i < cellNumber.length; i++) {
			gridSecuritySafetyValue[i] = gridSecuritySafetyValueTemp[i] / cellNumber[i];
			gridFireSafetyValue[i] = gridFireSafetyValueTemp[i] / cellNumber[i];
			gridTrafficSafetyValue[i] = gridTrafficSafetyValueTemp[i] / cellNumber[i];
			gridEmergencySafetyValue[i] = gridEmergencySafetyValueTemp[i] / cellNumber[i];
			gridResidentSafetyValue[i] = gridResidentSafetyValueTemp[i] / cellNumber[i];
		}

	}

	@ScheduledMethod(start = 1, interval = 1, priority = 0)
	public void step() throws Exception {
		Parameter.foreignResidentGrowthRate = Parameter.foreignResidentGrowthRate_down
				+ (Parameter.foreignResidentGrowthRate_up - Parameter.foreignResidentGrowthRate_down) * Math.random();
		Parameter.governmentToFacilityCoverageChange = Parameter.governmentToFacilityCoverageChange_down
				+ (Parameter.governmentToFacilityCoverageChange_up - Parameter.governmentToFacilityCoverageChange_down)
						* Math.random();

		for (int i = 0; i < cellTeam.size(); i++) {
			cellTeam.get(i).countInhabitantNumber();
			cellTeam.get(i).countMigrantPopulation();
			cellTeam.get(i).countForeignPopulation();
			cellTeam.get(i).countPopulationDensity();

			if (cellTeam.get(i).getInhabitantNumber() != 0) {

				cellTeam.get(i).countCellSatisfaction();

				cellTeam.get(i).countSafetyLivabilityValue_2();

			}
		}

		this.countGridSatisfaction();
		this.countStreetSatisfaction();
		this.countFutianSatisfaction();

		this.countGridLivability();
		this.countStreetLivability();
		this.countFutianLivability();

		this.countGridPopulationDensity();
		this.countStreetPopulationDensity();
		this.countFutianPopulationDensity();

		this.countStreetSafetyValue();
		this.countGridSafeValue();

		for (int i1 = 0; i1 < cellTeam.size(); i1++) {
			for (int j1 = 0; j1 < Parameter.gridNumber; j1++) {
				if (cellTeam.get(i1).getGridDiagram() == j1) {
					gridPoliceSafety.set(gridSecuritySafetyValue[j1], cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
					gridFireSafety.set(gridFireSafetyValue[j1], cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
					gridTrafficSafety.set(gridTrafficSafetyValue[j1], cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
					gridEmergencySafety.set(gridEmergencySafetyValue[j1], cellTeam.get(i1).getX(),
							cellTeam.get(i1).getY());
					gridLivingSafety.set(gridResidentSafetyValue[j1], cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
				}
			}

			for (int j1 = 0; j1 < Parameter.streetNumber; j1++) {
				if (cellTeam.get(i1).getStreetDiagram() == j1 + 1) {
					streetPoliceSafety.set(streetSecuritySafetyValue[j1], cellTeam.get(i1).getX(),
							cellTeam.get(i1).getY());
					streetFireSafety.set(streetFireSafetyValue[j1], cellTeam.get(i1).getX(), cellTeam.get(i1).getY());
					streetTrafficSafety.set(streetTrafficSafetyValue[j1], cellTeam.get(i1).getX(),
							cellTeam.get(i1).getY());
					streetEmergencySafety.set(streetEmergencySafetyValue[j1], cellTeam.get(i1).getX(),
							cellTeam.get(i1).getY());
					streetLivingSafety.set(streetResidentSafetyValue[j1], cellTeam.get(i1).getX(),
							cellTeam.get(i1).getY());
				}
			}
		}

		for (int i = 0; i < cellTeam.size(); i++) {
			if (cellTeam.get(i).getCellSatisfaction() > 0) {
				satisfactionData.setData(cellTeam.get(i).getCellSatisfaction(), cellTeam.get(i).getX(),
						cellTeam.get(i).getY());
				satisfaction.set(cellTeam.get(i).getCellSatisfaction(), cellTeam.get(i).getX(), cellTeam.get(i).getY());
			}

			for (int j = 0; j < Parameter.gridNumber; j++) {
				if (cellTeam.get(i).getGridDiagram() == j) {
					gridAverageLivabilityData.setData(gridLivability[j], cellTeam.get(i).getX(),
							cellTeam.get(i).getY());
				}
			}
			for (int j = 0; j < Parameter.streetNumber; j++) {
				if (cellTeam.get(i).getStreetDiagram() == j + 1) {
					streetAverageLivabilityData.setData(streetLivability[j], cellTeam.get(i).getX(),
							cellTeam.get(i).getY());
				}
			}

			if (cellTeam.get(i).getSafetyLivabilityValue() > 0) {
				safetyLivabilityData.setData(cellTeam.get(i).getSafetyLivabilityValue(), cellTeam.get(i).getX(),
						cellTeam.get(i).getY());
				safetyLivability.set(cellTeam.get(i).getSafetyLivabilityValue(), cellTeam.get(i).getX(),
						cellTeam.get(i).getY());
			}
			if (cellTeam.get(i).getPopulationDensity() > 0) {
				populationDensity.set(cellTeam.get(i).getPopulationDensity(), cellTeam.get(i).getX(),
						cellTeam.get(i).getY());
				populationDensityData.setData(cellTeam.get(i).getPopulationDensity(), cellTeam.get(i).getX(),
						cellTeam.get(i).getY());
			}

			trafficSafety.set(cellTeam.get(i).getTrafficSafetyValue(), cellTeam.get(i).getX(), cellTeam.get(i).getY());
			livingSafety.set(cellTeam.get(i).getResidentSafetyValue(), cellTeam.get(i).getX(), cellTeam.get(i).getY());
			fireSafety.set(cellTeam.get(i).getFireSafetyValue(), cellTeam.get(i).getX(), cellTeam.get(i).getY());
			policeSafety.set(cellTeam.get(i).getSecuritySafetyValue(), cellTeam.get(i).getX(), cellTeam.get(i).getY());
			emergencySafety.set(cellTeam.get(i).getEmergencySafetyValue(), cellTeam.get(i).getX(),
					cellTeam.get(i).getY());

			for (int j = 0; j < gridSatisfaction.length; j++) {
				if (cellTeam.get(i).getGridDiagram() == j) {
					gridAverageSatisfaction.set(gridSatisfaction[j], cellTeam.get(i).getX(), cellTeam.get(i).getY());
				}
			}
			for (int j = 0; j < streetSatisfaction.length; j++) {
				if (cellTeam.get(i).getStreetDiagram() == j + 1) {
					streetAverageSatisfaction.set(streetSatisfaction[j], cellTeam.get(i).getX(),
							cellTeam.get(i).getY());
				}
			}
			futianAverageSatisfaction.set(futianSatisfaction, cellTeam.get(i).getX(), cellTeam.get(i).getY());

			for (int j = 0; j < gridLivability.length; j++) {
				if (cellTeam.get(i).getGridDiagram() == j) {
					gridAverageLivability.set(gridLivability[j], cellTeam.get(i).getX(), cellTeam.get(i).getY());
				}
			}
			for (int j = 0; j < streetLivability.length; j++) {
				if (cellTeam.get(i).getStreetDiagram() == j + 1) {
					streetAverageLivability.set(streetLivability[j], cellTeam.get(i).getX(), cellTeam.get(i).getY());
				}
			}
			futianAverageLivability.set(futianLivability, cellTeam.get(i).getX(), cellTeam.get(i).getY());

			for (int j = 0; j < gridPopulationDensity.length; j++) {
				if (cellTeam.get(i).getGridDiagram() == j) {
					gridAveragePopulationDensity.set(gridPopulationDensity[j], cellTeam.get(i).getX(),
							cellTeam.get(i).getY());
				}
			}
			for (int j = 0; j < streetPopulationDensity.length; j++) {
				if (cellTeam.get(i).getStreetDiagram() == j + 1) {
					streetAveragePopulationDensity.set(streetPopulationDensity[j], cellTeam.get(i).getX(),
							cellTeam.get(i).getY());
				}
			}
			futianAveragePopulationDensity.set(futianPopulationDensity, cellTeam.get(i).getX(), cellTeam.get(i).getY());

			if (cellTeam.get(i).getMoveInFamilyNumber() != 0) {
				moveInFamilyNumberData.setData(cellTeam.get(i).getMoveInFamilyNumber(), cellTeam.get(i).getX(),
						cellTeam.get(i).getY());
				moveInFamilyNumber.set(cellTeam.get(i).getMoveInFamilyNumber(), cellTeam.get(i).getX(),
						cellTeam.get(i).getY());
			}
			if (cellTeam.get(i).getMoveOutFamilyNumber() != 0) {
				moveOutFamilyNumberData.setData(cellTeam.get(i).getMoveOutFamilyNumber(), cellTeam.get(i).getX(),
						cellTeam.get(i).getY());
				moveOutFamilyNumber.set(cellTeam.get(i).getMoveOutFamilyNumber(), cellTeam.get(i).getX(),
						cellTeam.get(i).getY());
			}
			if (cellTeam.get(i).getMoveInFamilyNumber_now() != 0) {
				moveInFamilyNumber_nowData.setData(cellTeam.get(i).getMoveInFamilyNumber_now(), cellTeam.get(i).getX(),
						cellTeam.get(i).getY());
				moveInFamilyNumber_now.set(cellTeam.get(i).getMoveInFamilyNumber_now(), cellTeam.get(i).getX(),
						cellTeam.get(i).getY());
			}
			if (cellTeam.get(i).getMoveOutFamilyNumber_now() != 0) {
				moveOutFamilyNumber_nowData.setData(cellTeam.get(i).getMoveOutFamilyNumber_now(),
						cellTeam.get(i).getX(), cellTeam.get(i).getY());
				moveOutFamilyNumber_now.set(cellTeam.get(i).getMoveOutFamilyNumber_now(), cellTeam.get(i).getX(),
						cellTeam.get(i).getY());
			}
		}

		safetyLivabilityData.WriteArcgisRasterData("./output/宜居度" + Parameter.year + ".txt", "double");
		populationDensityData.WriteArcgisRasterData("./output/人口密度" + Parameter.year + ".txt", "double");
		satisfactionData.WriteArcgisRasterData("./output/满意度" + Parameter.year + ".txt", "double");
		gridAverageLivabilityData.WriteArcgisRasterData("./output/网格宜居度" + Parameter.year + ".txt", "double");
		streetAverageLivabilityData.WriteArcgisRasterData("./output/街道宜居度" + Parameter.year + ".txt", "double");

		moveInFamilyNumberData.WriteArcgisRasterData("./output/迁入家庭度" + Parameter.year + ".txt", "double");
		moveOutFamilyNumberData.WriteArcgisRasterData("./output/迁出家庭度" + Parameter.year + ".txt", "double");

		for (int i = 0; i < cellTeam.size(); i++) {

			DataStorage.getInstance().setResidentPopulation(
					(int) cellTeam.get(i).getInhabitantNumber() + DataStorage.getInstance().getResidentPopulation());
			DataStorage.getInstance().setForeignPopulation(
					(int) cellTeam.get(i).getForeignPopulation() + DataStorage.getInstance().getForeignPopulation());

			int streetID = (int) cellTeam.get(i).getStreetDiagram();
			if (streetID == 1) {
				DataStorage.getInstance()
						.setStreetPopulation_1((int) cellTeam.get(i).getInhabitantNumber()
								+ (int) cellTeam.get(i).getForeignPopulation()
								+ DataStorage.getInstance().getStreetPopulation_1());
			} else if (streetID == 2) {
				DataStorage.getInstance()
						.setStreetPopulation_2((int) cellTeam.get(i).getInhabitantNumber()
								+ (int) cellTeam.get(i).getForeignPopulation()
								+ DataStorage.getInstance().getStreetPopulation_2());
			} else if (streetID == 3) {
				DataStorage.getInstance()
						.setStreetPopulation_3((int) cellTeam.get(i).getInhabitantNumber()
								+ (int) cellTeam.get(i).getForeignPopulation()
								+ DataStorage.getInstance().getStreetPopulation_3());
			} else if (streetID == 4) {
				DataStorage.getInstance()
						.setStreetPopulation_4((int) cellTeam.get(i).getInhabitantNumber()
								+ (int) cellTeam.get(i).getForeignPopulation()
								+ DataStorage.getInstance().getStreetPopulation_4());
			} else if (streetID == 5) {
				DataStorage.getInstance()
						.setStreetPopulation_5((int) cellTeam.get(i).getInhabitantNumber()
								+ (int) cellTeam.get(i).getForeignPopulation()
								+ DataStorage.getInstance().getStreetPopulation_5());
			} else if (streetID == 6) {
				DataStorage.getInstance()
						.setStreetPopulation_6((int) cellTeam.get(i).getInhabitantNumber()
								+ (int) cellTeam.get(i).getForeignPopulation()
								+ DataStorage.getInstance().getStreetPopulation_6());
			} else if (streetID == 7) {
				DataStorage.getInstance()
						.setStreetPopulation_7((int) cellTeam.get(i).getInhabitantNumber()
								+ (int) cellTeam.get(i).getForeignPopulation()
								+ DataStorage.getInstance().getStreetPopulation_7());
			} else if (streetID == 8) {
				DataStorage.getInstance()
						.setStreetPopulation_8((int) cellTeam.get(i).getInhabitantNumber()
								+ (int) cellTeam.get(i).getForeignPopulation()
								+ DataStorage.getInstance().getStreetPopulation_8());
			} else if (streetID == 9) {
				DataStorage.getInstance()
						.setStreetPopulation_9((int) cellTeam.get(i).getInhabitantNumber()
								+ (int) cellTeam.get(i).getForeignPopulation()
								+ DataStorage.getInstance().getStreetPopulation_9());
			} else if (streetID == 10) {
				DataStorage.getInstance()
						.setStreetPopulation_10((int) cellTeam.get(i).getInhabitantNumber()
								+ (int) cellTeam.get(i).getForeignPopulation()
								+ DataStorage.getInstance().getStreetPopulation_10());
			}

			if (cellTeam.get(i).getSafetyLivabilityValue() <= 0.1) {
				DataStorage.getInstance().setLivability_1(DataStorage.getInstance().getLivability_1() + 1);
			} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.1
					&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.2) {
				DataStorage.getInstance().setLivability_2(DataStorage.getInstance().getLivability_2() + 1);
			} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.2
					&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.3) {
				DataStorage.getInstance().setLivability_3(DataStorage.getInstance().getLivability_3() + 1);
			} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.3
					&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.4) {
				DataStorage.getInstance().setLivability_4(DataStorage.getInstance().getLivability_4() + 1);
			} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.4
					&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.5) {
				DataStorage.getInstance().setLivability_5(DataStorage.getInstance().getLivability_5() + 1);
			} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.5
					&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.6) {
				DataStorage.getInstance().setLivability_6(DataStorage.getInstance().getLivability_6() + 1);
			} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.6
					&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.7) {
				DataStorage.getInstance().setLivability_7(DataStorage.getInstance().getLivability_7() + 1);
			} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.7
					&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.8) {
				DataStorage.getInstance().setLivability_8(DataStorage.getInstance().getLivability_8() + 1);
			} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.8
					&& cellTeam.get(i).getSafetyLivabilityValue() <= 0.9) {
				DataStorage.getInstance().setLivability_9(DataStorage.getInstance().getLivability_9() + 1);
			} else if (cellTeam.get(i).getSafetyLivabilityValue() >= 0.9) {
				DataStorage.getInstance().setLivability_10(DataStorage.getInstance().getLivability_10() + 1);
			}
			for (int j = 0; j < cellTeam.get(i).getFamilyTeam().size(); j++) {
				int inhabitantNumber = cellTeam.get(i).getFamilyTeam().get(j).getInhabitantTeam().size();
				double satisfaction = cellTeam.get(i).getFamilyTeam().get(j).getFamilySatisfaction();
				double income = cellTeam.get(i).getFamilyTeam().get(j).getIncome();

				DataStorage.getInstance().setAllFamilyNumber(DataStorage.getInstance().getAllFamilyNumber() + 1);
				if (inhabitantNumber == 1) {
					DataStorage.getInstance()
							.setFamilyInhabitantNumber_1(DataStorage.getInstance().getFamilyInhabitantNumber_1() + 1);
				} else if (inhabitantNumber == 2) {
					DataStorage.getInstance()
							.setFamilyInhabitantNumber_2(DataStorage.getInstance().getFamilyInhabitantNumber_2() + 1);
				} else if (inhabitantNumber == 3) {
					DataStorage.getInstance()
							.setFamilyInhabitantNumber_3(DataStorage.getInstance().getFamilyInhabitantNumber_3() + 1);
				} else if (inhabitantNumber == 4) {
					DataStorage.getInstance()
							.setFamilyInhabitantNumber_4(DataStorage.getInstance().getFamilyInhabitantNumber_4() + 1);
				} else if (inhabitantNumber == 5) {
					DataStorage.getInstance()
							.setFamilyInhabitantNumber_5(DataStorage.getInstance().getFamilyInhabitantNumber_5() + 1);
				} else if (inhabitantNumber == 6) {
					DataStorage.getInstance()
							.setFamilyInhabitantNumber_6(DataStorage.getInstance().getFamilyInhabitantNumber_6() + 1);
				} else if (inhabitantNumber == 7) {
					DataStorage.getInstance()
							.setFamilyInhabitantNumber_7(DataStorage.getInstance().getFamilyInhabitantNumber_7() + 1);
				} else if (inhabitantNumber == 8) {
					DataStorage.getInstance()
							.setFamilyInhabitantNumber_8(DataStorage.getInstance().getFamilyInhabitantNumber_8() + 1);
				} else if (inhabitantNumber == 9) {
					DataStorage.getInstance()
							.setFamilyInhabitantNumber_9(DataStorage.getInstance().getFamilyInhabitantNumber_9() + 1);
				} else if (inhabitantNumber == 10) {
					DataStorage.getInstance()
							.setFamilyInhabitantNumber_10(DataStorage.getInstance().getFamilyInhabitantNumber_10() + 1);
				}
				if (satisfaction <= 0.1) {
					DataStorage.getInstance().setSatisfaction_1(DataStorage.getInstance().getSatisfaction_1() + 1);
				} else if (satisfaction >= 0.1 && satisfaction <= 0.2) {
					DataStorage.getInstance().setSatisfaction_2(DataStorage.getInstance().getSatisfaction_2() + 1);
				} else if (satisfaction >= 0.2 && satisfaction <= 0.3) {
					DataStorage.getInstance().setSatisfaction_3(DataStorage.getInstance().getSatisfaction_3() + 1);
				} else if (satisfaction >= 0.3 && satisfaction <= 0.4) {
					DataStorage.getInstance().setSatisfaction_4(DataStorage.getInstance().getSatisfaction_4() + 1);
				} else if (satisfaction >= 0.4 && satisfaction <= 0.5) {
					DataStorage.getInstance().setSatisfaction_5(DataStorage.getInstance().getSatisfaction_5() + 1);
				} else if (satisfaction >= 0.5 && satisfaction <= 0.6) {
					DataStorage.getInstance().setSatisfaction_6(DataStorage.getInstance().getSatisfaction_6() + 1);
				} else if (satisfaction >= 0.6 && satisfaction <= 0.7) {
					DataStorage.getInstance().setSatisfaction_7(DataStorage.getInstance().getSatisfaction_7() + 1);
				} else if (satisfaction >= 0.7 && satisfaction <= 0.8) {
					DataStorage.getInstance().setSatisfaction_8(DataStorage.getInstance().getSatisfaction_8() + 1);
				} else if (satisfaction >= 0.8 && satisfaction <= 0.9) {
					DataStorage.getInstance().setSatisfaction_9(DataStorage.getInstance().getSatisfaction_9() + 1);
				} else if (satisfaction >= 0.9) {
					DataStorage.getInstance().setSatisfaction_10(DataStorage.getInstance().getSatisfaction_10() + 1);
				}
				if (income >= 0 && income <= 50000) {
					DataStorage.getInstance().setIncome_1(DataStorage.getInstance().getIncome_1() + 1);
				} else if (income >= 50000 && income <= 100000) {
					DataStorage.getInstance().setIncome_2(DataStorage.getInstance().getIncome_2() + 1);
				} else if (income >= 100000 && income <= 150000) {
					DataStorage.getInstance().setIncome_3(DataStorage.getInstance().getIncome_3() + 1);
				} else if (income >= 150000 && income <= 200000) {
					DataStorage.getInstance().setIncome_4(DataStorage.getInstance().getIncome_4() + 1);
				} else if (income >= 200000 && income <= 250000) {
					DataStorage.getInstance().setIncome_5(DataStorage.getInstance().getIncome_5() + 1);
				} else if (income >= 250000 && income <= 300000) {
					DataStorage.getInstance().setIncome_6(DataStorage.getInstance().getIncome_6() + 1);
				} else if (income >= 300000 && income <= 350000) {
					DataStorage.getInstance().setIncome_7(DataStorage.getInstance().getIncome_7() + 1);
				} else if (income >= 350000 && income <= 400000) {
					DataStorage.getInstance().setIncome_8(DataStorage.getInstance().getIncome_8() + 1);
				} else if (income >= 400000 && income <= 450000) {
					DataStorage.getInstance().setIncome_9(DataStorage.getInstance().getIncome_9() + 1);
				} else {
					DataStorage.getInstance().setIncome_10(DataStorage.getInstance().getIncome_10() + 1);
				}

				for (int k = 0; k < cellTeam.get(i).getFamilyTeam().get(j).getInhabitantTeam().size(); k++) {
					int gender = cellTeam.get(i).getFamilyTeam().get(j).getInhabitantTeam().get(k).getGender();
					int age = cellTeam.get(i).getFamilyTeam().get(j).getInhabitantTeam().get(k).getAge();
					int educationLevel = cellTeam.get(i).getFamilyTeam().get(j).getInhabitantTeam().get(k)
							.getEducationLevel();
					if (gender == 1) {
						DataStorage.getInstance().setGender_1(DataStorage.getInstance().getGender_1() + 1);
					} else {
						DataStorage.getInstance().setGender_2(DataStorage.getInstance().getGender_2() + 1);
					}
					if (age >= 0 && age <= 10) {
						DataStorage.getInstance().setAge_0_10(DataStorage.getInstance().getAge_0_10() + 1);
					} else if (age >= 11 && age <= 20) {
						DataStorage.getInstance().setAge_11_20(DataStorage.getInstance().getAge_11_20() + 1);
					} else if (age >= 21 && age <= 30) {
						DataStorage.getInstance().setAge_21_30(DataStorage.getInstance().getAge_21_30() + 1);
					} else if (age >= 31 && age <= 40) {
						DataStorage.getInstance().setAge_31_40(DataStorage.getInstance().getAge_31_40() + 1);
					} else if (age >= 41 && age <= 50) {
						DataStorage.getInstance().setAge_41_50(DataStorage.getInstance().getAge_41_50() + 1);
					} else if (age >= 51 && age <= 60) {
						DataStorage.getInstance().setAge_51_60(DataStorage.getInstance().getAge_51_60() + 1);
					} else if (age >= 61 && age <= 70) {
						DataStorage.getInstance().setAge_61_70(DataStorage.getInstance().getAge_61_70() + 1);
					} else if (age >= 71 && age <= 80) {
						DataStorage.getInstance().setAge_71_80(DataStorage.getInstance().getAge_71_80() + 1);
					} else if (age >= 81 && age <= 90) {
						DataStorage.getInstance().setAge_81_90(DataStorage.getInstance().getAge_81_90() + 1);
					} else if (age >= 91 && age <= 100) {
						DataStorage.getInstance().setAge_91_100(DataStorage.getInstance().getAge_91_100() + 1);
					}
					if (educationLevel == 0) {
						DataStorage.getInstance()
								.setEducationLevel_0(DataStorage.getInstance().getEducationLevel_0() + 1);
					} else if (educationLevel == 1) {
						DataStorage.getInstance()
								.setEducationLevel_1(DataStorage.getInstance().getEducationLevel_1() + 1);
					} else if (educationLevel == 2) {
						DataStorage.getInstance()
								.setEducationLevel_2(DataStorage.getInstance().getEducationLevel_2() + 1);
					} else if (educationLevel == 3) {
						DataStorage.getInstance()
								.setEducationLevel_3(DataStorage.getInstance().getEducationLevel_3() + 1);
					} else if (educationLevel == 4) {
						DataStorage.getInstance()
								.setEducationLevel_4(DataStorage.getInstance().getEducationLevel_4() + 1);
					} else if (educationLevel == 5) {
						DataStorage.getInstance()
								.setEducationLevel_5(DataStorage.getInstance().getEducationLevel_5() + 1);
					} else if (educationLevel == 6) {
						DataStorage.getInstance()
								.setEducationLevel_6(DataStorage.getInstance().getEducationLevel_6() + 1);
					} else if (educationLevel == 7) {
						DataStorage.getInstance()
								.setEducationLevel_7(DataStorage.getInstance().getEducationLevel_7() + 1);
					} else if (educationLevel == 8) {
						DataStorage.getInstance()
								.setEducationLevel_8(DataStorage.getInstance().getEducationLevel_8() + 1);
					} else {
						DataStorage.getInstance()
								.setEducationLevel_9(DataStorage.getInstance().getEducationLevel_9() + 1);
					}
				}
			}
		}
		DataStorage.getInstance().setGridSatisfaction(gridSatisfaction);
		DataStorage.getInstance().setStreetSatisfaction(streetSatisfaction);
		DataStorage.getInstance().setFutianSatisfaction(futianSatisfaction);
		DataStorage.getInstance().setGridLivability(gridLivability);
		DataStorage.getInstance().setStreetLivability(streetLivability);
		DataStorage.getInstance().setFutianLivability(futianLivability);

		DataCollection dataCollection = DataCollection.getInstance();
		dataCollection.setGridSatisfaction(DataStorage.getInstance().getGridSatisfaction());
		dataCollection.setStreetSatisfaction(DataStorage.getInstance().getStreetSatisfaction());
		dataCollection.setFutianSatisfaction(DataStorage.getInstance().getFutianSatisfaction());
		dataCollection.setGridLivability(DataStorage.getInstance().getGridLivability());
		dataCollection.setStreetLivability(DataStorage.getInstance().getStreetLivability());
		dataCollection.setFutianLivability(DataStorage.getInstance().getFutianLivability());
		dataCollection.setResidentPopulation(DataStorage.getInstance().getResidentPopulation());
		dataCollection.setForeignPopulation(DataStorage.getInstance().getForeignPopulation());

		dataCollection.setGender_1(DataStorage.getInstance().getGender_1());
		dataCollection.setGender_2(DataStorage.getInstance().getGender_2());
		dataCollection.setAge_0_10(DataStorage.getInstance().getAge_0_10());
		dataCollection.setAge_11_20(DataStorage.getInstance().getAge_11_20());
		dataCollection.setAge_21_30(DataStorage.getInstance().getAge_21_30());
		dataCollection.setAge_31_40(DataStorage.getInstance().getAge_31_40());
		dataCollection.setAge_41_50(DataStorage.getInstance().getAge_41_50());
		dataCollection.setAge_51_60(DataStorage.getInstance().getAge_51_60());
		dataCollection.setAge_61_70(DataStorage.getInstance().getAge_61_70());
		dataCollection.setAge_71_80(DataStorage.getInstance().getAge_71_80());
		dataCollection.setAge_81_90(DataStorage.getInstance().getAge_81_90());
		dataCollection.setAge_91_100(DataStorage.getInstance().getAge_91_100());
		dataCollection.setEducationLevel_0(DataStorage.getInstance().getEducationLevel_0());
		dataCollection.setEducationLevel_1(DataStorage.getInstance().getEducationLevel_1());
		dataCollection.setEducationLevel_2(DataStorage.getInstance().getEducationLevel_2());
		dataCollection.setEducationLevel_3(DataStorage.getInstance().getEducationLevel_3());
		dataCollection.setEducationLevel_4(DataStorage.getInstance().getEducationLevel_4());
		dataCollection.setEducationLevel_5(DataStorage.getInstance().getEducationLevel_5());
		dataCollection.setEducationLevel_6(DataStorage.getInstance().getEducationLevel_6());
		dataCollection.setEducationLevel_7(DataStorage.getInstance().getEducationLevel_7());
		dataCollection.setEducationLevel_8(DataStorage.getInstance().getEducationLevel_8());
		dataCollection.setEducationLevel_9(DataStorage.getInstance().getEducationLevel_9());
		dataCollection.setFamilyInhabitantNumber_1(DataStorage.getInstance().getAllFamilyNumber());
		dataCollection.setFamilyInhabitantNumber_1(DataStorage.getInstance().getFamilyInhabitantNumber_1());
		dataCollection.setFamilyInhabitantNumber_2(DataStorage.getInstance().getFamilyInhabitantNumber_2());
		dataCollection.setFamilyInhabitantNumber_3(DataStorage.getInstance().getFamilyInhabitantNumber_3());
		dataCollection.setFamilyInhabitantNumber_4(DataStorage.getInstance().getFamilyInhabitantNumber_4());
		dataCollection.setFamilyInhabitantNumber_5(DataStorage.getInstance().getFamilyInhabitantNumber_5());
		dataCollection.setFamilyInhabitantNumber_6(DataStorage.getInstance().getFamilyInhabitantNumber_6());
		dataCollection.setFamilyInhabitantNumber_7(DataStorage.getInstance().getFamilyInhabitantNumber_7());
		dataCollection.setFamilyInhabitantNumber_8(DataStorage.getInstance().getFamilyInhabitantNumber_8());
		dataCollection.setFamilyInhabitantNumber_9(DataStorage.getInstance().getFamilyInhabitantNumber_9());
		dataCollection.setFamilyInhabitantNumber_10(DataStorage.getInstance().getFamilyInhabitantNumber_10());

		dataCollection.setStreetPopulation_1(DataStorage.getInstance().getStreetPopulation_1());
		dataCollection.setStreetPopulation_2(DataStorage.getInstance().getStreetPopulation_2());
		dataCollection.setStreetPopulation_3(DataStorage.getInstance().getStreetPopulation_3());
		dataCollection.setStreetPopulation_4(DataStorage.getInstance().getStreetPopulation_4());
		dataCollection.setStreetPopulation_5(DataStorage.getInstance().getStreetPopulation_5());
		dataCollection.setStreetPopulation_6(DataStorage.getInstance().getStreetPopulation_6());
		dataCollection.setStreetPopulation_7(DataStorage.getInstance().getStreetPopulation_7());
		dataCollection.setStreetPopulation_8(DataStorage.getInstance().getStreetPopulation_8());
		dataCollection.setStreetPopulation_9(DataStorage.getInstance().getStreetPopulation_9());
		dataCollection.setStreetPopulation_10(DataStorage.getInstance().getStreetPopulation_10());

		dataCollection.setSatisfaction_1(DataStorage.getInstance().getSatisfaction_1());
		dataCollection.setSatisfaction_2(DataStorage.getInstance().getSatisfaction_2());
		dataCollection.setSatisfaction_3(DataStorage.getInstance().getSatisfaction_3());
		dataCollection.setSatisfaction_4(DataStorage.getInstance().getSatisfaction_4());
		dataCollection.setSatisfaction_5(DataStorage.getInstance().getSatisfaction_5());
		dataCollection.setSatisfaction_6(DataStorage.getInstance().getSatisfaction_6());
		dataCollection.setSatisfaction_7(DataStorage.getInstance().getSatisfaction_7());
		dataCollection.setSatisfaction_8(DataStorage.getInstance().getSatisfaction_8());
		dataCollection.setSatisfaction_9(DataStorage.getInstance().getSatisfaction_9());
		dataCollection.setSatisfaction_10(DataStorage.getInstance().getSatisfaction_10());

		dataCollection.setLivability_1(DataStorage.getInstance().getLivability_1());
		dataCollection.setLivability_2(DataStorage.getInstance().getLivability_2());
		dataCollection.setLivability_3(DataStorage.getInstance().getLivability_3());
		dataCollection.setLivability_4(DataStorage.getInstance().getLivability_4());
		dataCollection.setLivability_5(DataStorage.getInstance().getLivability_5());
		dataCollection.setLivability_6(DataStorage.getInstance().getLivability_6());
		dataCollection.setLivability_7(DataStorage.getInstance().getLivability_7());
		dataCollection.setLivability_8(DataStorage.getInstance().getLivability_8());
		dataCollection.setLivability_9(DataStorage.getInstance().getLivability_9());
		dataCollection.setLivability_10(DataStorage.getInstance().getLivability_10());

		dataCollection.setIncome_1(DataStorage.getInstance().getIncome_1());
		dataCollection.setIncome_2(DataStorage.getInstance().getIncome_2());
		dataCollection.setIncome_3(DataStorage.getInstance().getIncome_3());
		dataCollection.setIncome_4(DataStorage.getInstance().getIncome_4());
		dataCollection.setIncome_5(DataStorage.getInstance().getIncome_5());
		dataCollection.setIncome_6(DataStorage.getInstance().getIncome_6());
		dataCollection.setIncome_7(DataStorage.getInstance().getIncome_7());
		dataCollection.setIncome_8(DataStorage.getInstance().getIncome_8());
		dataCollection.setIncome_9(DataStorage.getInstance().getIncome_9());
		dataCollection.setIncome_10(DataStorage.getInstance().getIncome_10());

		excel.step(dataCollection);

		Parameter.availableHouse = Parameter.availableHouseBackups;

		Random random = new Random();
		if (Math.random() < 0.5) {
			double addRate = Math.random() / 10;
			int addNumber = (int) (addRate * Parameter.availableHouseBackups.size());
			for (int i = 0; i < addNumber; i++) {
				Parameter.availableHouse.add(
						Parameter.availableHouseBackups.get(random.nextInt(Parameter.availableHouseBackups.size())));
			}
		} else {
			double minusRate = Math.random() / 10;
			int minusNumber = (int) (minusRate * Parameter.availableHouseBackups.size());
			for (int i = 0; i < minusNumber; i++) {
				Parameter.availableHouse
						.remove(Parameter.availableHouse.get(random.nextInt(Parameter.availableHouse.size())));
			}
		}

		int constractNumber = 10;
		double changeRate = (Parameter.governmentToFacilityCoverageChange_down
				+ (Parameter.governmentToFacilityCoverageChange_up - Parameter.governmentToFacilityCoverageChange_down)
						* Math.random())
				* Parameter.E;

		int[] index = new int[constractNumber];
		double[] livability = new double[constractNumber];

		for (int i = 0; i < constractNumber; i++) {
			index[i] = i;
			livability[i] = gridLivability[i];
		}

		for (int i = constractNumber; i < gridLivability.length; i++) {
			int indexMax = 0;
			double livabilityMin = livability[0];
			for (int j = 0; j < livability.length; j++) {
				if (livabilityMin < livability[j]) {
					indexMax = j;
				}
			}
			if (livability[indexMax] > gridLivability[i]) {
				index[indexMax] = i;
				livability[indexMax] = gridLivability[i];
			}
		}

		for (int i = 0; i < cellTeam.size(); i++) {
			for (int j = 0; j < index.length; j++) {
				if (cellTeam.get(i).getGridDiagram() == index[j]) {
					cellTeam.get(i).setSecuritySafetyValue(cellTeam.get(i).getSecuritySafetyValue() + changeRate);
					cellTeam.get(i).setFireSafetyValue(cellTeam.get(i).getFireSafetyValue() + changeRate);
					cellTeam.get(i).setTrafficSafetyValue(cellTeam.get(i).getTrafficSafetyValue() + changeRate);
					cellTeam.get(i).setEmergencySafetyValue(cellTeam.get(i).getEmergencySafetyValue() + changeRate);
					cellTeam.get(i).setResidentSafetyValue(cellTeam.get(i).getResidentSafetyValue() + changeRate);

					cellTeam.get(i).setSafetyLivabilityValue(cellTeam.get(i).getSafetyLivabilityValue() + changeRate);
					cellTeam.get(i).setCellSatisfaction(cellTeam.get(i).getCellSatisfaction() + changeRate);
				}
			}
		}

		System.out.println("End of the " + Parameter.year + " year, will start the " + (++Parameter.year) + " year.");
	}

}
