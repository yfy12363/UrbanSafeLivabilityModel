package com.Fenyuyang.controller;

import repast.simphony.engine.schedule.ScheduledMethod;

public class DataStorage {

	private static DataStorage dataStorage;

	public static DataStorage getInstance() {
		if (dataStorage == null) {
			dataStorage = new DataStorage();
		}

		return dataStorage;
	}

	private int Livability_1 = 0;
	private int Livability_2 = 0;
	private int Livability_3 = 0;
	private int Livability_4 = 0;
	private int Livability_5 = 0;
	private int Livability_6 = 0;
	private int Livability_7 = 0;
	private int Livability_8 = 0;
	private int Livability_9 = 0;
	private int Livability_10 = 0;

	private int Satisfaction_1 = 0;
	private int Satisfaction_2 = 0;
	private int Satisfaction_3 = 0;
	private int Satisfaction_4 = 0;
	private int Satisfaction_5 = 0;
	private int Satisfaction_6 = 0;
	private int Satisfaction_7 = 0;
	private int Satisfaction_8 = 0;
	private int Satisfaction_9 = 0;
	private int Satisfaction_10 = 0;

	private int Income_1 = 0;
	private int Income_2 = 0;
	private int Income_3 = 0;
	private int Income_4 = 0;
	private int Income_5 = 0;
	private int Income_6 = 0;
	private int Income_7 = 0;
	private int Income_8 = 0;
	private int Income_9 = 0;
	private int Income_10 = 0;

	private int birthNumber = 0;
	private int deathNumber = 0;

	private int residentPopulation = 0;
	private int foreignPopulation = 0;
	private int moveInPopulation = 0;
	private int moveOutPopulation = 0;

	private int gender_1 = 0;
	private int gender_2 = 0;

	private int age_0_10 = 0;
	private int age_11_20 = 0;
	private int age_21_30 = 0;
	private int age_31_40 = 0;
	private int age_41_50 = 0;
	private int age_51_60 = 0;
	private int age_61_70 = 0;
	private int age_71_80 = 0;
	private int age_81_90 = 0;
	private int age_91_100 = 0;

	private int educationLevel_0 = 0;
	private int educationLevel_1 = 0;
	private int educationLevel_2 = 0;
	private int educationLevel_3 = 0;
	private int educationLevel_4 = 0;
	private int educationLevel_5 = 0;
	private int educationLevel_6 = 0;
	private int educationLevel_7 = 0;
	private int educationLevel_8 = 0;
	private int educationLevel_9 = 0;

	private int allFamilyNumber = 0;

	private int moveInFamilyNumber_now = 0;
	private int moveOutFamilyNumber_now = 0;
	private int deathFamilyNumber_now = 0;

	private int familyInhabitantNumber_1 = 0;
	private int familyInhabitantNumber_2 = 0;
	private int familyInhabitantNumber_3 = 0;
	private int familyInhabitantNumber_4 = 0;
	private int familyInhabitantNumber_5 = 0;
	private int familyInhabitantNumber_6 = 0;
	private int familyInhabitantNumber_7 = 0;
	private int familyInhabitantNumber_8 = 0;
	private int familyInhabitantNumber_9 = 0;
	private int familyInhabitantNumber_10 = 0;

	private int streetPopulation_1 = 0;
	private int streetPopulation_2 = 0;
	private int streetPopulation_3 = 0;
	private int streetPopulation_4 = 0;
	private int streetPopulation_5 = 0;
	private int streetPopulation_6 = 0;
	private int streetPopulation_7 = 0;
	private int streetPopulation_8 = 0;
	private int streetPopulation_9 = 0;
	private int streetPopulation_10 = 0;

	private double futianSatisfaction = 0;
	private double[] streetSatisfaction = new double[Parameter.streetNumber];
	private double[] gridSatisfaction = new double[Parameter.gridNumber];

	/**
	 * 宜居度收�?
	 */
	private double futianLivability = 0;
	private double[] streetLivability = new double[Parameter.streetNumber];
	private double[] gridLivability = new double[Parameter.gridNumber];

	@ScheduledMethod(start = 1, interval = 1, priority = 10)
	public void step() {
		Livability_1 = 0;
		Livability_2 = 0;
		Livability_3 = 0;
		Livability_4 = 0;
		Livability_5 = 0;
		Livability_6 = 0;
		Livability_7 = 0;
		Livability_8 = 0;
		Livability_9 = 0;
		Livability_10 = 0;

		Satisfaction_1 = 0;
		Satisfaction_2 = 0;
		Satisfaction_3 = 0;
		Satisfaction_4 = 0;
		Satisfaction_5 = 0;
		Satisfaction_6 = 0;
		Satisfaction_7 = 0;
		Satisfaction_8 = 0;
		Satisfaction_9 = 0;
		Satisfaction_10 = 0;

		Income_1 = 0;
		Income_2 = 0;
		Income_3 = 0;
		Income_4 = 0;
		Income_5 = 0;
		Income_6 = 0;
		Income_7 = 0;
		Income_8 = 0;
		Income_9 = 0;
		Income_10 = 0;

		birthNumber = 0;
		deathNumber = 0;

		residentPopulation = 0;
		foreignPopulation = 0;
		moveInPopulation = 0;
		moveOutPopulation = 0;

		gender_1 = 0;
		gender_2 = 0;

		age_0_10 = 0;
		age_11_20 = 0;
		age_21_30 = 0;
		age_31_40 = 0;
		age_41_50 = 0;
		age_51_60 = 0;
		age_61_70 = 0;
		age_71_80 = 0;
		age_81_90 = 0;
		age_91_100 = 0;

		educationLevel_0 = 0;
		educationLevel_1 = 0;
		educationLevel_2 = 0;
		educationLevel_3 = 0;
		educationLevel_4 = 0;
		educationLevel_5 = 0;
		educationLevel_6 = 0;
		educationLevel_7 = 0;
		educationLevel_8 = 0;
		educationLevel_9 = 0;

		allFamilyNumber = 0;
		moveInFamilyNumber_now = 0;
		moveOutFamilyNumber_now = 0;
		deathFamilyNumber_now = 0;

		familyInhabitantNumber_1 = 0;
		familyInhabitantNumber_2 = 0;
		familyInhabitantNumber_3 = 0;
		familyInhabitantNumber_4 = 0;
		familyInhabitantNumber_5 = 0;
		familyInhabitantNumber_6 = 0;
		familyInhabitantNumber_7 = 0;
		familyInhabitantNumber_8 = 0;
		familyInhabitantNumber_9 = 0;
		familyInhabitantNumber_10 = 0;

		streetPopulation_1 = 0;
		streetPopulation_2 = 0;
		streetPopulation_3 = 0;
		streetPopulation_4 = 0;
		streetPopulation_5 = 0;
		streetPopulation_6 = 0;
		streetPopulation_7 = 0;
		streetPopulation_8 = 0;
		streetPopulation_9 = 0;
		streetPopulation_10 = 0;

	}

	public int getStreetPopulation_1() {
		return streetPopulation_1;
	}

	public void setStreetPopulation_1(int streetPopulation_1) {
		this.streetPopulation_1 = streetPopulation_1;
	}

	public int getStreetPopulation_2() {
		return streetPopulation_2;
	}

	public void setStreetPopulation_2(int streetPopulation_2) {
		this.streetPopulation_2 = streetPopulation_2;
	}

	public int getStreetPopulation_3() {
		return streetPopulation_3;
	}

	public void setStreetPopulation_3(int streetPopulation_3) {
		this.streetPopulation_3 = streetPopulation_3;
	}

	public int getStreetPopulation_4() {
		return streetPopulation_4;
	}

	public void setStreetPopulation_4(int streetPopulation_4) {
		this.streetPopulation_4 = streetPopulation_4;
	}

	public int getStreetPopulation_5() {
		return streetPopulation_5;
	}

	public void setStreetPopulation_5(int streetPopulation_5) {
		this.streetPopulation_5 = streetPopulation_5;
	}

	public int getStreetPopulation_6() {
		return streetPopulation_6;
	}

	public void setStreetPopulation_6(int streetPopulation_6) {
		this.streetPopulation_6 = streetPopulation_6;
	}

	public int getStreetPopulation_7() {
		return streetPopulation_7;
	}

	public void setStreetPopulation_7(int streetPopulation_7) {
		this.streetPopulation_7 = streetPopulation_7;
	}

	public int getStreetPopulation_8() {
		return streetPopulation_8;
	}

	public void setStreetPopulation_8(int streetPopulation_8) {
		this.streetPopulation_8 = streetPopulation_8;
	}

	public int getStreetPopulation_9() {
		return streetPopulation_9;
	}

	public void setStreetPopulation_9(int streetPopulation_9) {
		this.streetPopulation_9 = streetPopulation_9;
	}

	public int getStreetPopulation_10() {
		return streetPopulation_10;
	}

	public void setStreetPopulation_10(int streetPopulation_10) {
		this.streetPopulation_10 = streetPopulation_10;
	}

	public int getBirthNumber() {
		return birthNumber;
	}

	public void setBirthNumber(int birthNumber) {
		this.birthNumber = birthNumber;
	}

	public int getLivability_1() {
		return Livability_1;
	}

	public void setLivability_1(int livability_1) {
		Livability_1 = livability_1;
	}

	public int getLivability_2() {
		return Livability_2;
	}

	public void setLivability_2(int livability_2) {
		Livability_2 = livability_2;
	}

	public int getLivability_3() {
		return Livability_3;
	}

	public void setLivability_3(int livability_3) {
		Livability_3 = livability_3;
	}

	public int getLivability_4() {
		return Livability_4;
	}

	public void setLivability_4(int livability_4) {
		Livability_4 = livability_4;
	}

	public int getLivability_5() {
		return Livability_5;
	}

	public void setLivability_5(int livability_5) {
		Livability_5 = livability_5;
	}

	public int getLivability_6() {
		return Livability_6;
	}

	public void setLivability_6(int livability_6) {
		Livability_6 = livability_6;
	}

	public int getLivability_7() {
		return Livability_7;
	}

	public void setLivability_7(int livability_7) {
		Livability_7 = livability_7;
	}

	public int getLivability_8() {
		return Livability_8;
	}

	public void setLivability_8(int livability_8) {
		Livability_8 = livability_8;
	}

	public int getLivability_9() {
		return Livability_9;
	}

	public void setLivability_9(int livability_9) {
		Livability_9 = livability_9;
	}

	public int getLivability_10() {
		return Livability_10;
	}

	public void setLivability_10(int livability_10) {
		Livability_10 = livability_10;
	}

	public int getGender_1() {
		return gender_1;
	}

	public void setGender_1(int gender_1) {
		this.gender_1 = gender_1;
	}

	public int getGender_2() {
		return gender_2;
	}

	public void setGender_2(int gender_2) {
		this.gender_2 = gender_2;
	}

	public int getAge_0_10() {
		return age_0_10;
	}

	public void setAge_0_10(int age_0_10) {
		this.age_0_10 = age_0_10;
	}

	public int getAge_11_20() {
		return age_11_20;
	}

	public void setAge_11_20(int age_11_20) {
		this.age_11_20 = age_11_20;
	}

	public int getAge_21_30() {
		return age_21_30;
	}

	public void setAge_21_30(int age_21_30) {
		this.age_21_30 = age_21_30;
	}

	public int getAge_31_40() {
		return age_31_40;
	}

	public void setAge_31_40(int age_31_40) {
		this.age_31_40 = age_31_40;
	}

	public int getAge_41_50() {
		return age_41_50;
	}

	public void setAge_41_50(int age_41_50) {
		this.age_41_50 = age_41_50;
	}

	public int getAge_51_60() {
		return age_51_60;
	}

	public void setAge_51_60(int age_51_60) {
		this.age_51_60 = age_51_60;
	}

	public int getAge_61_70() {
		return age_61_70;
	}

	public void setAge_61_70(int age_61_70) {
		this.age_61_70 = age_61_70;
	}

	public int getAge_71_80() {
		return age_71_80;
	}

	public void setAge_71_80(int age_71_80) {
		this.age_71_80 = age_71_80;
	}

	public int getAge_81_90() {
		return age_81_90;
	}

	public void setAge_81_90(int age_81_90) {
		this.age_81_90 = age_81_90;
	}

	public int getAge_91_100() {
		return age_91_100;
	}

	public void setAge_91_100(int age_91_100) {
		this.age_91_100 = age_91_100;
	}

	public int getEducationLevel_0() {
		return educationLevel_0;
	}

	public void setEducationLevel_0(int educationLevel_0) {
		this.educationLevel_0 = educationLevel_0;
	}

	public int getEducationLevel_1() {
		return educationLevel_1;
	}

	public void setEducationLevel_1(int educationLevel_1) {
		this.educationLevel_1 = educationLevel_1;
	}

	public int getEducationLevel_2() {
		return educationLevel_2;
	}

	public void setEducationLevel_2(int educationLevel_2) {
		this.educationLevel_2 = educationLevel_2;
	}

	public int getEducationLevel_3() {
		return educationLevel_3;
	}

	public void setEducationLevel_3(int educationLevel_3) {
		this.educationLevel_3 = educationLevel_3;
	}

	public int getEducationLevel_4() {
		return educationLevel_4;
	}

	public void setEducationLevel_4(int educationLevel_4) {
		this.educationLevel_4 = educationLevel_4;
	}

	public int getEducationLevel_5() {
		return educationLevel_5;
	}

	public void setEducationLevel_5(int educationLevel_5) {
		this.educationLevel_5 = educationLevel_5;
	}

	public int getEducationLevel_6() {
		return educationLevel_6;
	}

	public void setEducationLevel_6(int educationLevel_6) {
		this.educationLevel_6 = educationLevel_6;
	}

	public int getEducationLevel_7() {
		return educationLevel_7;
	}

	public void setEducationLevel_7(int educationLevel_7) {
		this.educationLevel_7 = educationLevel_7;
	}

	public int getEducationLevel_8() {
		return educationLevel_8;
	}

	public void setEducationLevel_8(int educationLevel_8) {
		this.educationLevel_8 = educationLevel_8;
	}

	public int getEducationLevel_9() {
		return educationLevel_9;
	}

	public void setEducationLevel_9(int educationLevel_9) {
		this.educationLevel_9 = educationLevel_9;
	}

	public int getFamilyInhabitantNumber_1() {
		return familyInhabitantNumber_1;
	}

	public void setFamilyInhabitantNumber_1(int familyInhabitantNumber_1) {
		this.familyInhabitantNumber_1 = familyInhabitantNumber_1;
	}

	public int getFamilyInhabitantNumber_2() {
		return familyInhabitantNumber_2;
	}

	public void setFamilyInhabitantNumber_2(int familyInhabitantNumber_2) {
		this.familyInhabitantNumber_2 = familyInhabitantNumber_2;
	}

	public int getFamilyInhabitantNumber_3() {
		return familyInhabitantNumber_3;
	}

	public void setFamilyInhabitantNumber_3(int familyInhabitantNumber_3) {
		this.familyInhabitantNumber_3 = familyInhabitantNumber_3;
	}

	public int getFamilyInhabitantNumber_4() {
		return familyInhabitantNumber_4;
	}

	public void setFamilyInhabitantNumber_4(int familyInhabitantNumber_4) {
		this.familyInhabitantNumber_4 = familyInhabitantNumber_4;
	}

	public int getFamilyInhabitantNumber_5() {
		return familyInhabitantNumber_5;
	}

	public void setFamilyInhabitantNumber_5(int familyInhabitantNumber_5) {
		this.familyInhabitantNumber_5 = familyInhabitantNumber_5;
	}

	public int getFamilyInhabitantNumber_6() {
		return familyInhabitantNumber_6;
	}

	public void setFamilyInhabitantNumber_6(int familyInhabitantNumber_6) {
		this.familyInhabitantNumber_6 = familyInhabitantNumber_6;
	}

	public int getFamilyInhabitantNumber_7() {
		return familyInhabitantNumber_7;
	}

	public void setFamilyInhabitantNumber_7(int familyInhabitantNumber_7) {
		this.familyInhabitantNumber_7 = familyInhabitantNumber_7;
	}

	public int getFamilyInhabitantNumber_8() {
		return familyInhabitantNumber_8;
	}

	public void setFamilyInhabitantNumber_8(int familyInhabitantNumber_8) {
		this.familyInhabitantNumber_8 = familyInhabitantNumber_8;
	}

	public int getFamilyInhabitantNumber_9() {
		return familyInhabitantNumber_9;
	}

	public void setFamilyInhabitantNumber_9(int familyInhabitantNumber_9) {
		this.familyInhabitantNumber_9 = familyInhabitantNumber_9;
	}

	public int getFamilyInhabitantNumber_10() {
		return familyInhabitantNumber_10;
	}

	public void setFamilyInhabitantNumber_10(int familyInhabitantNumber_10) {
		this.familyInhabitantNumber_10 = familyInhabitantNumber_10;
	}

	public int getSatisfaction_1() {
		return Satisfaction_1;
	}

	public void setSatisfaction_1(int satisfaction_1) {
		Satisfaction_1 = satisfaction_1;
	}

	public int getSatisfaction_2() {
		return Satisfaction_2;
	}

	public void setSatisfaction_2(int satisfaction_2) {
		Satisfaction_2 = satisfaction_2;
	}

	public int getSatisfaction_3() {
		return Satisfaction_3;
	}

	public void setSatisfaction_3(int satisfaction_3) {
		Satisfaction_3 = satisfaction_3;
	}

	public int getSatisfaction_4() {
		return Satisfaction_4;
	}

	public void setSatisfaction_4(int satisfaction_4) {
		Satisfaction_4 = satisfaction_4;
	}

	public int getSatisfaction_5() {
		return Satisfaction_5;
	}

	public void setSatisfaction_5(int satisfaction_5) {
		Satisfaction_5 = satisfaction_5;
	}

	public int getSatisfaction_6() {
		return Satisfaction_6;
	}

	public void setSatisfaction_6(int satisfaction_6) {
		Satisfaction_6 = satisfaction_6;
	}

	public int getSatisfaction_7() {
		return Satisfaction_7;
	}

	public void setSatisfaction_7(int satisfaction_7) {
		Satisfaction_7 = satisfaction_7;
	}

	public int getSatisfaction_8() {
		return Satisfaction_8;
	}

	public void setSatisfaction_8(int satisfaction_8) {
		Satisfaction_8 = satisfaction_8;
	}

	public int getSatisfaction_9() {
		return Satisfaction_9;
	}

	public void setSatisfaction_9(int satisfaction_9) {
		Satisfaction_9 = satisfaction_9;
	}

	public int getSatisfaction_10() {
		return Satisfaction_10;
	}

	public void setSatisfaction_10(int satisfaction_10) {
		Satisfaction_10 = satisfaction_10;
	}

	public int getAllFamilyNumber() {
		return allFamilyNumber;
	}

	public void setAllFamilyNumber(int allFamilyNumber) {
		this.allFamilyNumber = allFamilyNumber;
	}

	public int getResidentPopulation() {
		return residentPopulation;
	}

	public void setResidentPopulation(int residentPopulation) {
		this.residentPopulation = residentPopulation;
	}

	public int getForeignPopulation() {
		return foreignPopulation;
	}

	public void setForeignPopulation(int foreignPopulation) {
		this.foreignPopulation = foreignPopulation;
	}

	public int getMoveInPopulation() {
		return moveInPopulation;
	}

	public void setMoveInPopulation(int moveInPopulation) {
		this.moveInPopulation = moveInPopulation;
	}

	public int getMoveOutPopulation() {
		return moveOutPopulation;
	}

	public void setMoveOutPopulation(int moveOutPopulation) {
		this.moveOutPopulation = moveOutPopulation;
	}

	public int getIncome_1() {
		return Income_1;
	}

	public void setIncome_1(int income_1) {
		Income_1 = income_1;
	}

	public int getIncome_2() {
		return Income_2;
	}

	public void setIncome_2(int income_2) {
		Income_2 = income_2;
	}

	public int getIncome_3() {
		return Income_3;
	}

	public void setIncome_3(int income_3) {
		Income_3 = income_3;
	}

	public int getIncome_4() {
		return Income_4;
	}

	public void setIncome_4(int income_4) {
		Income_4 = income_4;
	}

	public int getIncome_5() {
		return Income_5;
	}

	public void setIncome_5(int income_5) {
		Income_5 = income_5;
	}

	public int getIncome_6() {
		return Income_6;
	}

	public void setIncome_6(int income_6) {
		Income_6 = income_6;
	}

	public int getIncome_7() {
		return Income_7;
	}

	public void setIncome_7(int income_7) {
		Income_7 = income_7;
	}

	public int getIncome_8() {
		return Income_8;
	}

	public void setIncome_8(int income_8) {
		Income_8 = income_8;
	}

	public int getIncome_9() {
		return Income_9;
	}

	public void setIncome_9(int income_9) {
		Income_9 = income_9;
	}

	public int getIncome_10() {
		return Income_10;
	}

	public void setIncome_10(int income_10) {
		Income_10 = income_10;
	}

	public int getDeathNumber() {
		return deathNumber;
	}

	public void setDeathNumber(int deathNumber) {
		this.deathNumber = deathNumber;
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

	public int getDeathFamilyNumber_now() {
		return deathFamilyNumber_now;
	}

	public void setDeathFamilyNumber_now(int deathFamilyNumber_now) {
		this.deathFamilyNumber_now = deathFamilyNumber_now;
	}

	public double getFutianSatisfaction() {
		return futianSatisfaction;
	}

	public void setFutianSatisfaction(double futianSatisfaction) {
		this.futianSatisfaction = futianSatisfaction;
	}

	public double[] getStreetSatisfaction() {
		return streetSatisfaction;
	}

	public void setStreetSatisfaction(double[] streetSatisfaction) {
		this.streetSatisfaction = streetSatisfaction;
	}

	public double[] getGridSatisfaction() {
		return gridSatisfaction;
	}

	public void setGridSatisfaction(double[] gridSatisfaction) {
		this.gridSatisfaction = gridSatisfaction;
	}

	public double getFutianLivability() {
		return futianLivability;
	}

	public void setFutianLivability(double futianLivability) {
		this.futianLivability = futianLivability;
	}

	public double[] getStreetLivability() {
		return streetLivability;
	}

	public void setStreetLivability(double[] streetLivability) {
		this.streetLivability = streetLivability;
	}

	public double[] getGridLivability() {
		return gridLivability;
	}

	public void setGridLivability(double[] gridLivability) {
		this.gridLivability = gridLivability;
	}
}
