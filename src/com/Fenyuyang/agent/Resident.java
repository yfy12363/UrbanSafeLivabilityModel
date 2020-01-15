package com.Fenyuyang.agent;

import java.util.Random;

import com.Fenyuyang.controller.Parameter;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Resident {

	// ========================== Constructors ===========================

	public Resident() {

	}

	public Resident(int x, int y, Context<Object> context, ContinuousSpace<Object> space, Grid<Object> grid) {
		setId(++Parameter.inhabitantId);
		this.x = x;
		this.y = y;
		this.context = context;
		this.space = space;
		this.grid = grid;
	}

	// ======================= Private Properties ========================
	private int x;
	private int y;
	@SuppressWarnings("unused")
	private Context<Object> context;
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;

	private int id;
	private int gender;
	private int age;
	private int educationLevel;
	private int residentPopulation;

	private double career;
	private double income;
	private double expense;

	double streetDiagram;
	double gridDiagram;
	double moveDesire = 0;
	int[] environmentPreference = { 0, 0, 0, 0, 0, 0, 0, 0 };
	int[] safePreference = { 0, 0, 0, 0, 0 };
	// ========================== Public Methods =========================

	@ScheduledMethod(start = 1, interval = 1, priority = 9)
	public void step() throws Exception {
		age = this.age + 1;
		this.educationLevelChange();
		this.countIncome();
		this.countExpense();
		if (this.career == 5) {
			this.searchJob();
		} else {
			this.lostJob();
		}
	}

	private void educationLevelChange() {
		int age = this.getAge();
		if (age >= 0 && age <= 5) {
			this.setEducationLevel(0);
		} else if (age >= 6 && age <= 22) {
			this.setEducationLevel(1);
		} else if (age >= 6 && age <= 22) {
			this.setEducationLevel(2);
		}
	}

	public int randomGender() {
		int gender;
		double[] rate = Parameter.genderRate;
		double randomNumber = Math.random();
		if (0 < randomNumber && randomNumber < rate[0]) {
			gender = 1;
		} else {
			gender = 2;
		}
		this.setGender(gender);
		return gender;
	}

	public void randomEnvironmentPreference() {
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
			double rate[] = { 0.0333, 0.1000, 0.0333, 0.0333, 0.0333, 0.0333, 0.0333, 0.0333, 0.0333, 0.0667, 0.0333,
					0.0333, 0.0333, 0.0333, 0.0333, 0.0333, 0.0333, 0.0667, 0.0333, 0.0333, 0.0333, 0.0333, 0.0333,
					0.1000 };
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11]) {
				environmentPreference[0] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]) {
				environmentPreference[0] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]) {
				environmentPreference[0] = 1;
				environmentPreference[3] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]) {
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16]) {
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17]) {
				environmentPreference[2] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18]) {
				environmentPreference[2] = 1;
				environmentPreference[6] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19]) {
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20]) {
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21]) {
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22]) {
				environmentPreference[4] = 1;
				environmentPreference[5] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]) {
			} else {

			}
		}
		if (this.streetDiagram == 3) {
			double rate[] = { 0.0323, 0.0323, 0.0323, 0.0323, 0.0645, 0.0323, 0.0323, 0.0323, 0.0323, 0.0645, 0.0323,
					0.0323, 0.1290, 0.0323, 0.0323, 0.1290, 0.0323, 0.0323, 0.0323, 0.0323, 0.0645, 0.0323 };
			double randomNumber = Math.random();

			if (randomNumber >= 0 && randomNumber < rate[0]) {
				environmentPreference[0] = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				environmentPreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]) {
				environmentPreference[0] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]) {
				environmentPreference[0] = 1;
				environmentPreference[3] = 1;
				environmentPreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]) {
				environmentPreference[0] = 1;
				environmentPreference[3] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16]) {
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17]) {
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18]) {
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19]) {
				environmentPreference[2] = 1;
				environmentPreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20]) {
				environmentPreference[2] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21]) {
				environmentPreference[3] = 1;
				environmentPreference[7] = 1;
			} else {

			}
		}
		if (this.streetDiagram == 5) {
			double rate[] = { 0.0244, 0.0244, 0.0244, 0.0244, 0.0732, 0.0732, 0.0488, 0.0488, 0.0244, 0.0244, 0.0244,
					0.0244, 0.0244, 0.0244, 0.0244, 0.0244, 0.0244, 0.0488, 0.0488, 0.0488, 0.0244, 0.0244, 0.0244,
					0.0732, 0.0244, 0.0244, 0.0244, 0.0244, 0.0488 };
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				environmentPreference[0] = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				environmentPreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[4] = 1;
				environmentPreference[5] = 1;
				environmentPreference[6] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[4] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]) {
				environmentPreference[0] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16]) {
				environmentPreference[0] = 1;
				environmentPreference[3] = 1;
				environmentPreference[6] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17]) {
				environmentPreference[0] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[5] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21]) {
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
				environmentPreference[6] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22]) {
				environmentPreference[1] = 1;
				environmentPreference[4] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]) {
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;

			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24]) {
				environmentPreference[2] = 1;
				environmentPreference[6] = 1;
				environmentPreference[7] = 1;

			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24] + rate[25]) {
				environmentPreference[3] = 1;
				environmentPreference[4] = 1;
				environmentPreference[5] = 1;

			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24] + rate[25] + rate[26]) {
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
				environmentPreference[6] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
					+ rate[26]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24] + rate[25] + rate[26] + rate[27]) {
				environmentPreference[3] = 1;
				environmentPreference[6] = 1;

			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
					+ rate[26] + rate[27]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24] + rate[25] + rate[26] + rate[27] + rate[28]) {
				environmentPreference[5] = 1;
				environmentPreference[7] = 1;

			} else {

			}

		}
		if (this.streetDiagram == 6) {
			double rate[] = { 0.0323, 0.0323, 0.0323, 0.0323, 0.0323, 0.0323, 0.0323, 0.0323, 0.0323, 0.0323, 0.0645,
					0.0323, 0.0323, 0.0645, 0.0323, 0.1290, 0.0645, 0.0323, 0.0323, 0.0645, 0.0323, 0.0323, 0.0323,
					0.0323 };
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8]) {
				environmentPreference[0] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9]) {
				environmentPreference[0] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]) {
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]) {
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]) {
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16]) {
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17]) {
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18]) {
				environmentPreference[2] = 1;
				environmentPreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19]) {
				environmentPreference[2] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20]) {
				environmentPreference[2] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21]) {
				environmentPreference[2] = 1;
				environmentPreference[6] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22]) {
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]) {
			} else {

			}
		}
		if (this.streetDiagram == 8) {
			double rate[] = { 0.0182, 0.0727, 0.0727, 0.0182, 0.0182, 0.1091, 0.0182, 0.0364, 0.0182, 0.0182, 0.0727,
					0.0182, 0.0182, 0.0182, 0.0182, 0.0182, 0.0182, 0.0182, 0.0364, 0.0364, 0.0182, 0.0182, 0.0364,
					0.0182, 0.0182, 0.0364, 0.0364, 0.0182, 0.0364, 0.0182, 0.0182, 0.0182, 0.0364 };
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				environmentPreference[0] = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
				environmentPreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8]) {
				environmentPreference[0] = 1;
				environmentPreference[1] = 1;
				environmentPreference[5] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[4] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]) {
				environmentPreference[0] = 1;
				environmentPreference[2] = 1;
				environmentPreference[4] = 1;
				environmentPreference[5] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]) {
				environmentPreference[0] = 1;
				environmentPreference[3] = 1;
				environmentPreference[4] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16]) {
				environmentPreference[0] = 1;
				environmentPreference[3] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17]) {
				environmentPreference[0] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[4] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[5] = 1;

			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24]) {
				environmentPreference[1] = 1;
				environmentPreference[2] = 1;
				environmentPreference[6] = 1;

			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24] + rate[25]) {
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24] + rate[25] + rate[26]) {
				environmentPreference[1] = 1;
				environmentPreference[3] = 1;
				environmentPreference[7] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
					+ rate[26]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24] + rate[25] + rate[26] + rate[27]) {
				environmentPreference[1] = 1;
				environmentPreference[5] = 1;

			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
					+ rate[26] + rate[27]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24] + rate[25] + rate[26] + rate[27] + rate[28]) {
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;

			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
					+ rate[26] + rate[27] + rate[28]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24] + rate[25] + rate[26] + rate[27] + rate[28] + rate[29]) {
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
					+ rate[26] + rate[27] + rate[28] + rate[29]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24] + rate[25] + rate[26] + rate[27] + rate[28] + rate[30]) {
				environmentPreference[2] = 1;
				environmentPreference[3] = 1;
				environmentPreference[5] = 1;
				environmentPreference[6] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
					+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24] + rate[25] + rate[26] + rate[27] + rate[28] + rate[30] + rate[31]) {
				environmentPreference[3] = 1;
				environmentPreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
					+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
							+ rate[24] + rate[25] + rate[26] + rate[27] + rate[28] + rate[30] + rate[31] + rate[32]) {
			} else {

			}
		}

	}

	public void randomSafePreference() {
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
			double rate[] = { 0.0667, 0.2667, 0.0333, 0.1000, 0.0667, 0.1000, 0.0333, 0.0667, 0.1000, 0.0333, 0.0667,
					0.0667 };
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9]) {
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10]) {
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11]) {

			}
		}

		if (this.streetDiagram == 3) {
			double rate[] = { 0.0323, 0.0968, 0.1935, 0.0968, 0.0323, 0.0645, 0.0323, 0.0323, 0.0968, 0.0323, 0.0323,
					0.0323, 0.0323, 0.0968, 0.0323, 0.0645 };
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9]) {
				safePreference[0] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10]) {
				safePreference[1] = 1;
				safePreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11]) {
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;

			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]) {
				safePreference[1] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]) {
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]) {
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]) {

			}
		}

		if (this.streetDiagram == 5) {
			double rate[] = { 0.0244, 0.0976, 0.1463, 0.0976, 0.0488, 0.0244, 0.0732, 0.0488, 0.0488, 0.0244, 0.0244,
					0.0244, 0.0488, 0.0244, 0.0488, 0.0244, 0.0488, 0.0244, 0.0488, 0.0488 };
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				safePreference[0] = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11]) {
				safePreference[0] = 1;
				safePreference[4] = 1;
			}

			else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]) {
				safePreference[1] = 1;
				safePreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]) {
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]) {
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]) {
				safePreference[1] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16]) {
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17]) {
				safePreference[2] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18]) {
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19]) {

			}

		}
		if (this.streetDiagram == 6) {
			double rate[] = { 0.1613, 0.1935, 0.0645, 0.0645, 0.0645, 0.0323, 0.0323, 0.0645, 0.0323, 0.0968, 0.0968,
					0.0323, 0.0323, 0.0323 };
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10]) {
				safePreference[1] = 1;
				safePreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11]) {
				safePreference[2] = 1;
				safePreference[3] = 1;

			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]) {
				safePreference[2] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]) {
				safePreference[3] = 1;
				safePreference[4] = 1;
			}
		}
		if (this.streetDiagram == 8) {
			double rate[] = { 0.0364, 0.0182, 0.0182, 0.1455, 0.0727, 0.0545, 0.0909, 0.0182, 0.0182, 0.1455, 0.0545,
					0.0182, 0.0545, 0.0182, 0.0364, 0.0182, 0.0364, 0.0364, 0.0364, 0.0364, 0.0364 };
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				safePreference[2] = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				safePreference[0] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8]) {
				safePreference[0] = 1;
				safePreference[1] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			}

			else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]) {
				safePreference[0] = 1;
				safePreference[2] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]) {
				safePreference[1] = 1;
				safePreference[2] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]) {
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]) {
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16]) {
				safePreference[1] = 1;
				safePreference[2] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17]) {
				safePreference[1] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18]) {
				safePreference[2] = 1;
				safePreference[3] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19]) {
				safePreference[2] = 1;
				safePreference[3] = 1;
				safePreference[4] = 1;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
					+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
					+ rate[17] + rate[18] + rate[19]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
							+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
							+ rate[16] + rate[17] + rate[18] + rate[19] + rate[20]) {

			}
		}
	}

	public int randomAge() {
		double[] rate = Parameter.ageRate;
		int age = 0;
		double randomNumber = Math.random();
		Random random = new Random();
		if (randomNumber >= 0 && randomNumber < rate[0]) {
			age = 0;
		} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
			age = 1;
		} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
			age = 2;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
			age = 3;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
			age = 4;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
			age = 5;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
			age = 6;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
			age = 7;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8]) {
			age = 8;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9]) {
			age = 9;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10]) {
			age = 10;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11]) {
			age = 11;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]) {
			age = 12;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]) {
			age = 13;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]) {
			age = 14;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]) {
			age = 15;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15]
						+ rate[16]) {
			age = 16;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17]) {
			age = 17;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18]) {
			age = 18;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19]) {
			age = 19;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20]) {
			age = 20;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21]) {
			age = 21;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22]) {
			age = 22;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]) {
			age = 23;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]) {
			age = 24;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25]) {
			age = 25;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26]) {
			age = 26;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27]) {
			age = 27;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28]) {
			age = 28;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29]) {
			age = 29;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30]) {
			age = 30;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31]) {
			age = 31;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]) {
			age = 32;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33]) {
			age = 33;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34]) {
			age = 34;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35]) {
			age = 35;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36]) {
			age = 36;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37]) {
			age = 37;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38]) {
			age = 38;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39]) {
			age = 39;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]) {
			age = 40;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41]) {
			age = 41;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42]) {
			age = 42;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43]) {
			age = 43;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44]) {
			age = 44;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45]) {
			age = 45;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46]) {
			age = 46;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47]) {
			age = 47;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]) {
			age = 48;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49]) {
			age = 49;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50]) {
			age = 50;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51]) {
			age = 51;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52]) {
			age = 52;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53]) {
			age = 53;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54]) {
			age = 54;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55]) {
			age = 55;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]) {
			age = 56;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57]) {
			age = 57;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58]) {
			age = 58;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59]) {
			age = 59;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60]) {
			age = 60;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61]) {
			age = 61;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62]) {
			age = 62;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63]) {
			age = 63;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]) {
			age = 64;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65]) {
			age = 65;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66]) {
			age = 65;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67]) {
			age = 67;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68]) {
			age = 68;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69]) {
			age = 69;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]) {
			age = 70;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71]) {
			age = 71;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]) {
			age = 72;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73]) {
			age = 73;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74]) {
			age = 74;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75]) {
			age = 75;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76]) {
			age = 76;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77]) {
			age = 77;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76] + rate[77]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78]) {
			age = 78;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79]) {
			age = 79;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79] + rate[80]) {
			age = 80;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79]
				+ rate[80]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79] + rate[80]
						+ rate[81]) {
			age = 81;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79]
				+ rate[80] + rate[81]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79] + rate[80]
						+ rate[81] + rate[82]) {
			age = 82;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79]
				+ rate[80] + rate[81] + rate[82]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79] + rate[80]
						+ rate[81] + rate[82] + rate[83]) {
			age = 83;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79]
				+ rate[80] + rate[81] + rate[82] + rate[83]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79] + rate[80]
						+ rate[81] + rate[82] + rate[83] + rate[84]) {
			age = 84;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79]
				+ rate[80] + rate[81] + rate[82] + rate[83] + rate[84]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79] + rate[80]
						+ rate[81] + rate[82] + rate[83] + rate[84] + rate[85]) {
			age = 85;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79]
				+ rate[80] + rate[81] + rate[82] + rate[83] + rate[84] + rate[85]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79] + rate[80]
						+ rate[81] + rate[82] + rate[83] + rate[84] + rate[85] + rate[86]) {
			age = 86;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79]
				+ rate[80] + rate[81] + rate[82] + rate[83] + rate[84] + rate[85] + rate[86]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79] + rate[80]
						+ rate[81] + rate[82] + rate[83] + rate[84] + rate[85] + rate[86] + rate[87]) {
			age = 87;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79]
				+ rate[80] + rate[81] + rate[82] + rate[83] + rate[84] + rate[85] + rate[86] + rate[87]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79] + rate[80]
						+ rate[81] + rate[82] + rate[83] + rate[84] + rate[85] + rate[86] + rate[87] + rate[88]) {
			age = 88;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79]
				+ rate[80] + rate[81] + rate[82] + rate[83] + rate[84] + rate[85] + rate[86] + rate[87] + rate[88]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79] + rate[80]
						+ rate[81] + rate[82] + rate[83] + rate[84] + rate[85] + rate[86] + rate[87] + rate[88]
						+ rate[89]) {
			age = 89;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
				+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
				+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24] + rate[25]
				+ rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32] + rate[33] + rate[34]
				+ rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40] + rate[41] + rate[42] + rate[43]
				+ rate[44] + rate[45] + rate[46] + rate[47] + rate[48] + rate[49] + rate[50] + rate[51] + rate[52]
				+ rate[53] + rate[54] + rate[55] + rate[56] + rate[57] + rate[58] + rate[59] + rate[60] + rate[61]
				+ rate[62] + rate[63] + rate[64] + rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70]
				+ rate[71] + rate[72] + rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79]
				+ rate[80] + rate[81] + rate[82] + rate[83] + rate[84] + rate[85] + rate[86] + rate[87] + rate[88]
				+ rate[89]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]
						+ rate[8] + rate[9] + rate[10] + rate[11] + rate[12] + rate[13] + rate[14] + rate[15] + rate[16]
						+ rate[17] + rate[18] + rate[19] + rate[20] + rate[21] + rate[22] + rate[23] + rate[24]
						+ rate[25] + rate[26] + rate[27] + rate[28] + rate[29] + rate[30] + rate[31] + rate[32]
						+ rate[33] + rate[34] + rate[35] + rate[36] + rate[37] + rate[38] + rate[39] + rate[40]
						+ rate[41] + rate[42] + rate[43] + rate[44] + rate[45] + rate[46] + rate[47] + rate[48]
						+ rate[49] + rate[50] + rate[51] + rate[52] + rate[53] + rate[54] + rate[55] + rate[56]
						+ rate[57] + rate[58] + rate[59] + rate[60] + rate[61] + rate[62] + rate[63] + rate[64]
						+ rate[65] + rate[66] + rate[67] + rate[68] + rate[69] + rate[70] + rate[71] + rate[72]
						+ rate[73] + rate[74] + rate[75] + rate[76] + rate[77] + rate[78] + rate[79] + rate[80]
						+ rate[81] + rate[82] + rate[83] + rate[84] + rate[85] + rate[86] + rate[87] + rate[88]
						+ rate[89] + rate[90]) {
			age = 90 + random.nextInt(10);
		}
		this.setAge(age);
		return age;
	}

	public void randomCareer(int age) {
		double[] rate = Parameter.careerRate;
		double randomNumber = Math.random();
		int career = -1;
		if (randomNumber >= 0 && randomNumber < rate[0]) {
			career = 1;
		} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
			career = 2;
		} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
			career = 3;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
			career = 4;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
			career = 5;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
			career = 6;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
			career = 7;
		} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
				&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
			career = 8;
		} else {
			career = 4;
		}

		if (age <= 18) {
			career = 7;
		} else if (age > 23 && career == 7) {
			career = 4;
		}

		this.setCareer(career);
	}

	public int randomEducationLevel(int age) {
		double[] rate = Parameter.educationLevelRate;
		double randomNumber = Math.random();
		int educationLevel = -1;

		if (age <= 22) {
			educationLevel = 0;
		} else {
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				educationLevel = 1;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				educationLevel = 2;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				educationLevel = 3;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				educationLevel = 4;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				educationLevel = 5;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				educationLevel = 6;
			} else {
				educationLevel = 1;
			}
		}
		this.setEducationLevel(educationLevel);
		return educationLevel;
	}

	public void randomIncome() {
		double[] rate = Parameter.incomeRate;
		double randomNumber = Math.random();
		double income = 0;
		if (age >= 0 && age <= 22) {
			income = 0;
		} else if (gender == 1 && age >= 23 && age <= 60) {
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				income = 1500 * 12;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				income = 4500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				income = 7500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				income = 12500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				income = 17500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				income = 22500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				income = 27500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				income = 35000 * 12;
			}
		} else if (gender == 2 && age >= 23 && age <= 55) {
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				income = 1500 * 12;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				income = 4500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				income = 7500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				income = 12500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				income = 17500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				income = 22500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				income = 27500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				income = 35000 * 12;
			}
		} else {
			income = 4843 * 12;
		}
		this.setIncome(income);
	}

	public void countIncome() {
		double[] rate = Parameter.incomeRate;
		double randomNumber = Math.random();
		double income = 0;
		if (age >= 0 && age <= 22) {
			income = 0;
		} else if (income == 0 && gender == 1 && age >= 23 && age <= 60) {
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				income = 2000 * 12;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				income = 2500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				income = 3750 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				income = 5250 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				income = 7000 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				income = 9000 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				income = 12500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				income = 17500 * 12;
			}
		} else if (income == 0 && gender == 2 && age >= 23 && age <= 55) {
			if (randomNumber >= 0 && randomNumber < rate[0]) {
				income = 2000 * 12;
			} else if (randomNumber >= rate[0] && randomNumber < rate[0] + rate[1]) {
				income = 2500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] && randomNumber < rate[0] + rate[1] + rate[2]) {
				income = 3750 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3]) {
				income = 5250 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4]) {
				income = 7000 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]) {
				income = 9000 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]) {
				income = 12500 * 12;
			} else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6]
					&& randomNumber < rate[0] + rate[1] + rate[2] + rate[3] + rate[4] + rate[5] + rate[6] + rate[7]) {
				income = 17500 * 12;
			}
		} else {
			income = 4843 * 12;
		}
		this.setIncome(income);
	}

	public void countExpense() {
		double expense = 0;
		Random random = new Random();
		if (age >= 0 && age <= 3) {
			expense = 33333;
		} else if (age >= 3 && age <= 6) {
			expense = 25200;
		} else if (age >= 6 && age <= 12) {
			expense = 12000;
		} else if (age >= 12 && age <= 15) {
			expense = 9666 + random.nextInt(3333);
		} else if (age >= 15 && age <= 18) {
			expense = 12333 + random.nextInt(4000);
		} else if (age >= 18 && age <= 22) {
			expense = 60000;
		} else if (gender == 1 && age >= 18 && age <= 60) {
			expense = income * 0.5;
		} else if (gender == 2 && age >= 18 && age <= 55) {
			expense = income * 0.5;
		} else {
			expense = 3040 * 12;
		}

		this.setExpense(expense);
	}

	public void searchJob() {
		double randomNumber_searchJob = Math.random();
		double randomNumber = Math.random();
		Random random = new Random();
		if (randomNumber_searchJob <= Parameter.employmentRate) {
			int[] career = { 1, 2, 3, 4, 8 };

			double[] rate = { 0.1, 0.9 };
			if (this.career == 5) {
				if (randomNumber <= rate[0]) {
					this.career = career[random.nextInt(5)];
				} else if (randomNumber >= rate[0]) {
					this.career = career[random.nextInt(5)];
				}
			}

			this.income = Parameter.salaryData.get(random.nextInt(Parameter.salaryData.size()));
		}
	}

	public void lostJob() {
		double randomNumber_lostJob = Math.random();

		if (randomNumber_lostJob <= Parameter.unemploymentRate) {
			this.career = 5;
			this.income = 0;
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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(int educationLevel) {
		this.educationLevel = educationLevel;
	}

	public int getResidentPopulation() {
		return residentPopulation;
	}

	public void setResidentPopulation(int residentPopulation) {
		this.residentPopulation = residentPopulation;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}

	public double getMoveDesire() {
		return moveDesire;
	}

	public void setMoveDesire(double moveDesire) {
		this.moveDesire = moveDesire;
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

	public double getCareer() {
		return career;
	}

	public void setCareer(double career) {
		this.career = career;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
