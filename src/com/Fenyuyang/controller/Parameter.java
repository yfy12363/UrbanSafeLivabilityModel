package com.Fenyuyang.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.Fenyuyang.tool.Point;

public class Parameter {

	public Parameter(double foreignResidentGrowthRate_up, double foreignResidentGrowthRate_down,
			double governmentToFacilityCoverageChange_up, double governmentToFacilityCoverageChange_down,
			double jobChangeRate_up, double jobChangeRate_down, double vacancyChangeRate_up,
			double vacancyChangeRate_down, double xllcorner, double yllcorner) {

		Parameter.foreignResidentGrowthRate_up = foreignResidentGrowthRate_up;
		Parameter.foreignResidentGrowthRate_down = foreignResidentGrowthRate_down;
		Parameter.governmentToFacilityCoverageChange_up = governmentToFacilityCoverageChange_up;
		Parameter.governmentToFacilityCoverageChange_down = governmentToFacilityCoverageChange_down;

		Parameter.xllcorner = xllcorner;
		Parameter.yllcorner = yllcorner;

		setFamilyPeferenceMap();
		setEducationLevelMap();
	}

	public static int year = 1;

	/**
	 * 地图左下角的坐标x
	 */
	public static double xllcorner;

	/**
	 * 地图左下角的坐标y
	 */
	public static double yllcorner;

	/**
	 * 有效区域半径
	 */
	public static double validRadius;
	/**
	 * 城市应急设施覆盖率（权重）
	 */
	public static double cityEmergencyFacilitiesCoverageWeight;
	/**
	 * 环境嫌恶设施影像范围（权重）
	 */
	public static double environmentallyDisgustingFacilityImageRangeWeight;
	/**
	 * 建筑覆盖率（权重）
	 */
	public static double buildingCoverageWeight;
	/**
	 * 平均街区大小
	 */
	public static double averageBlockSize;
	/**
	 * 平均街区大小（权重）
	 */
	public static double averageBlockSizeWeight;
	/**
	 * 街道公共安全指数
	 */
	public static double streetPublicSafetyIndex = 0.925;
	/**
	 * 街道公共安全指数（权重）
	 */
	public static double streetPublicSafetyIndexWeight;

	/**
	 * 治安安全值（没有数据）
	 */
	public static double securitySafetyWeight = 0.2509;
	/**
	 * 消防安全值（没有数据）
	 */
	public static double fireSafetyWeight = 0.2196;
	/**
	 * 交通安全值（交通（已有））
	 */
	public static double trafficSafetyWeight = 0.2435;
	/**
	 * 应急安全值（应急避难场所覆盖率（已有），人均避难场所面积（需要计算））
	 */
	public static double emergencySafetyWeight = 0.1550;
	/**
	 * 居住安全值（嫌恶设施覆盖率（已有））
	 */
	public static double livingSafetyWeight = 0.1162;

	/**
	 * 家庭ID，为了使家庭ID格式可以从1开始的整数类型
	 */
	public static int familyId = 0;

	/**
	 * 个人ID，为了使个人ID格式可以从1开始的整数类型
	 */
	public static int inhabitantId = 0;

	/**
	 * 性别比：男：1；女：2
	 */
	public static double[] genderRate = { 0.4894, 0.5106 };

	/**
	 * 年龄比例0-90每一个年龄对应一个比例
	 */
	public static double[] ageRate = { 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000,
			0.0069, 0.0069, 0.0069, 0.0069, 0.0069, 0.0069, 0.0069, 0.0069, 0.0069, 0.0069, 0.0293, 0.0293, 0.0293,
			0.0293, 0.0293, 0.0293, 0.0293, 0.0293, 0.0293, 0.0293, 0.0213, 0.0213, 0.0213, 0.0213, 0.0213, 0.0213,
			0.0213, 0.0213, 0.0213, 0.0213, 0.0223, 0.0223, 0.0223, 0.0223, 0.0223, 0.0223, 0.0223, 0.0223, 0.0223,
			0.0223, 0.0154, 0.0154, 0.0154, 0.0154, 0.0154, 0.0154, 0.0154, 0.0154, 0.0154, 0.0154, 0.0043, 0.0043,
			0.0043, 0.0043, 0.0043, 0.0043, 0.0043, 0.0043, 0.0043, 0.0043, 0.0005, 0.0005, 0.0005, 0.0005, 0.0005,
			0.0005, 0.0005, 0.0005, 0.0005, 0.0005, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000,
			0.0000, 0.0000, 0.0000 };

	/**
	 * 文化程度比例（0-6）18岁以下都上学
	 */
	public static double[] educationLevelRate = { 0.1968, 0.3351, 0.1543, 0.2606, 0.0426, 0.0106 };

	/**
	 * 家庭人口数比例（1-10）调研数据：对应1~10人户的概率
	 */
	public static double[] familyInhabitantNumberRate = { 0.0064, 0.1218, 0.2372, 0.3590, 0.1218, 0.0705, 0.0385,
			0.0192, 0.0192, 0.0064 };

	/**
	 * 婚姻状况比例（1,2,3,4,9）
	 */
	public static double[] maritalStatusRate = { 0.5384, 0.4483, 0.0016, 0.0051, 0.0066 };

	/**
	 * 职业比例：政府工作人员：1；事业单位工作人员：2；企业员工：3；进城务工人员：4；待业人员：5；退休人员：6；在校学生：7；个体经营者：8
	 */
	public static double[] careerRate = { 0.0511, 0.1420, 0.1818, 0.1591, 0.0568, 0.0511, 0.1136, 0.2443 };

	/**
	 * 行业比例(0,1,2,3,4,5,6)
	 */
	public static double[] industryRate = { 0.0010, 0.0004, 0.0004, 0.0015, 0.0012, 0.0009, 0.9946 };

	/**
	 * 职称比例(1-9)
	 */
	public static double[] jobTitleRate = { 0.0382, 0.0977, 0.1893, 0.0045, 0.0004, 0.0079, 0.4373, 0.1034, 0.1213 };

	/**
	 * 出生率（深圳市2017年统计年鉴）
	 */
	public static double[] birthRate = { 0.2233 };

	/**
	 * 死亡率（深圳市2017年统计年鉴）
	 */
	public static double[] mortalityRate = { 0.0134 };

	/**
	 * 工资分配
	 * 
	 * 3K以下，3K-5K，5K-10K，10K-15K，15K-20K，20K-25K，25K-30K，30K以上
	 */
	public static double[] incomeRate = { 0.1677, 0.3832, 0.3114, 0.0958, 0.0299, 0.0120, 0.0000, 0.0000 };

	/**
	 * 人口密度（搬迁限制最大值）
	 */
	public static double populationDensityLimitRate_max = 0.8;
	/**
	 * 人口密度（搬迁限制最小值）
	 */
	public static double populationDensityLimitRate_min = 0.4;
	/**
	 * 人口迁入比例
	 */
	public static double moveInPopulationLimitRate = 1;
	/**
	 * 失业率
	 */
	public static double unemploymentRate = 0.5;
	/**
	 * 就业率
	 */
	public static double employmentRate = 0.5;
	/**
	 * 生育政策
	 */
	public static double fertilityPolicy = 2;
	/**
	 * 建筑密度（搬迁限制最大值）
	 */
	public static double buildingDensityLimitRate_max = 0.8;
	/**
	 * 建筑密度（搬迁限制最小值）
	 */
	public static double buildingDensityLimitRate_min = 0.4;
	/**
	 * 搬迁欲望（搬迁限制最小值）
	 */
	public static double moveDesireLimitRate_min = 0.5;
	/**
	 * 外来人口增长比（上确界）
	 */
	public static double foreignResidentGrowthRate_up;
	/**
	 * 外来人口增长比（下确界）
	 */
	public static double foreignResidentGrowthRate_down;
	/**
	 * 政府提高设施覆盖率（上确界）
	 */
	public static double governmentToFacilityCoverageChange_up;
	/**
	 * 政府提高设施覆盖率（下确界）
	 */
	public static double governmentToFacilityCoverageChange_down;
	/**
	 * 政府提高设施覆盖率（下确界）
	 */
	public static double governmentToFacilityCoverageChange;
	/**
	 * 外来人口增长比（随机值）
	 */
	public static double foreignResidentGrowthRate;
	/**
	 * 搬迁收入最小阈值
	 */
	public static double moveIncomeLimit_min = 15604 * 12;

	/**
	 * 环境因素重要性权重（安全性除外）
	 * 
	 * 交通、教育、绿化、人口、商业、卫生、文体、养老
	 * 
	 * 1、园岭街道；2华强北街道；3、南园街道；4、华富街道；5、莲花街道；6、福田街道；7、福保街道；8、沙头街道；9、香蜜湖街道；10、梅林街道
	 */
	public final static double[] environmentWeight = { 0.1781, 0.1586, 0.2106, 0.2116, 0.0297, 0.0998, 0.0647, 0.0467 };// 整体数据
	public final static double[] environmentWeight_1 = {};
	public final static double[] environmentWeight_2 = { 0.2000, 0.1412, 0.2000, 0.2000, 0.0235, 0.0824, 0.1176,
			0.0353 };// 华强北街道
	public final static double[] environmentWeight_3 = { 0.2118, 0.1176, 0.2353, 0.2706, 0.0235, 0.0471, 0.0588,
			0.0353 };// 南园街道
	public final static double[] environmentWeight_4 = {};
	public final static double[] environmentWeight_5 = { 0.1729, 0.1805, 0.1429, 0.1805, 0.0451, 0.0902, 0.0827,
			0.1053 };// 莲花街道
	public final static double[] environmentWeight_6 = { 0.1234, 0.1605, 0.2593, 0.2469, 0.0123, 0.1358, 0.0370,
			0.0247 };// 福田街道
	public final static double[] environmentWeight_7 = {};
	public final static double[] environmentWeight_8 = { 0.1823, 0.1934, 0.2155, 0.1602, 0.0441, 0.1436, 0.0276,
			0.0331 };// 沙头街道
	public final static double[] environmentWeight_9 = {};
	public final static double[] environmentWeight_10 = {};

	/**
	 * 安全性权重
	 * 
	 * 治安安全、消防安全、交通安全、应急安全、居住安全
	 * 
	 * 1、园岭街道；2华强北街道；3、南园街道；4、华富街道；5、莲花街道；6、福田街道；7、福保街道；8、沙头街道；9、香蜜湖街道；10、梅林街道
	 */
	public final static double[] safeWeight = { 0.2571, 0.2263, 0.2433, 0.1574, 0.1159 };// 整体数据
	public final static double[] safeWeight_1 = {};
	public final static double[] safeWeight_2 = { 0.2577, 0.2062, 0.2165, 0.1753, 0.1443 };// 华强北街道
	public final static double[] safeWeight_3 = { 0.2658, 0.2405, 0.2406, 0.1772, 0.0759 };// 南园街道
	public final static double[] safeWeight_4 = {};
	public final static double[] safeWeight_5 = { 0.2328, 0.2414, 0.2069, 0.1897, 0.1293 };// 莲花街道
	public final static double[] safeWeight_6 = { 0.2809, 0.2472, 0.2584, 0.1011, 0.1124 };// 福田街道
	public final static double[] safeWeight_7 = {};
	public final static double[] safeWeight_8 = { 0.2484, 0.1961, 0.2941, 0.1438, 0.1176 };// 沙头街道
	public final static double[] safeWeight_9 = {};
	public final static double[] safeWeight_10 = {};

	/**
	 * 家庭偏好对应值（键值对）
	 */
	public static Map<Integer, String> familyPeferenceMap = new HashMap<Integer, String>();

	public static void setFamilyPeferenceMap() {
		familyPeferenceMap.put(1, "交通");
		familyPeferenceMap.put(2, "教育");
		familyPeferenceMap.put(3, "绿化");
		familyPeferenceMap.put(4, "人口 ");
		familyPeferenceMap.put(5, "商业");
		familyPeferenceMap.put(6, "卫生");
		familyPeferenceMap.put(7, "文体");
		familyPeferenceMap.put(8, "养老");
	}

	/**
	 * 性别对应值（键值对）
	 */
	public static Map<Integer, String> genderMap = new HashMap<Integer, String>();

	public static void setGenderMap() {
		genderMap.put(1, "男");
		genderMap.put(2, "女");
	}

	/**
	 * 教育水平对应值（键值对）
	 */
	public static Map<Integer, String> educationLevelMap = new HashMap<Integer, String>();

	public static void setEducationLevelMap() {
		educationLevelMap.put(0, "正在上学");
		educationLevelMap.put(1, "初中及以下");
		educationLevelMap.put(2, "高中");
		educationLevelMap.put(3, "大专");
		educationLevelMap.put(4, "本科");
		educationLevelMap.put(5, "硕士研究生");
		educationLevelMap.put(6, "博士及以上");
	}

	/**
	 * 网格社区对应值（键值对）
	 */
	public static Map<Integer, String> streetCorrespondMap = new HashMap<Integer, String>();

	public static void setStreetCorrespondMap() {
		streetCorrespondMap.put(1, "园岭街道");
		streetCorrespondMap.put(2, "华强北街道");
		streetCorrespondMap.put(3, "南园街道");
		streetCorrespondMap.put(4, "华富街道");
		streetCorrespondMap.put(5, "莲花街道");
		streetCorrespondMap.put(6, "福田街道");
		streetCorrespondMap.put(7, "福保街道");
		streetCorrespondMap.put(8, "沙头街道");
		streetCorrespondMap.put(9, "香蜜湖街道");
		streetCorrespondMap.put(10, "梅林街道");
	}

	/**
	 * 空房点坐标备份（Parameter中为准）
	 */
	public static ArrayList<Point> availableHouseBackups = new ArrayList<Point>();// 可利用的房子集合（备份）

	/**
	 * 空房点坐标（Parameter中为准）
	 */
	public static ArrayList<Point> availableHouse = new ArrayList<Point>();// 可利用的房子集合

	/**
	 * 工资数据（Parameter中为准）
	 */
	public static ArrayList<Double> salaryData = new ArrayList<Double>();// 工资数据

	/**
	 * 栅格大小
	 */
	public static double cellSize = 250;

	/**
	 * 街道数
	 */
	public final static int streetNumber = 10;
	/**
	 * 网格数
	 */
	public final static int gridNumber = 887;

	/**
	 * 缩小比例
	 */
	public static int minRate = 1;

	/**
	 * 扩大比例
	 */
	public static double maxRate = 1;

	/**
	 * 经济系数
	 */
	public static double E = 1;
}
