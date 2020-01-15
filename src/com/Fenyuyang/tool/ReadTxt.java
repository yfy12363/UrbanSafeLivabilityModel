package com.Fenyuyang.tool;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadTxt {

	public static ArrayList<Point> ReadPointData(String fileName, int xllcorner, int yllcorner) {
		ArrayList<Point> pointTeam = new ArrayList<Point>();

		FileReader fileReader;
		BufferedReader bufferedReader;
		try {
			
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);

			String stringLine;
			String[] arrayLine;
			try {
				while (bufferedReader.ready()) {
					stringLine = bufferedReader.readLine();
					arrayLine = stringLine.split("\\s+");
					pointTeam.add(new Point((Integer.parseInt(arrayLine[0]) - xllcorner) / 50,
							(Integer.parseInt(arrayLine[1]) - yllcorner) / 50));
				}
			} catch (IOException e) {
				System.out.println("Read point data exception");
				e.printStackTrace();
			}

			try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return pointTeam;
	}

	public static ArrayList<Double> ReadSalaryData(String fileName) {
		ArrayList<Double> salaryDataTeam = new ArrayList<Double>();

		FileReader fileReader;
		BufferedReader bufferedReader;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);

			String stringLine;
			String[] arrayLine;
			try {
				while (bufferedReader.ready()) {
					stringLine = bufferedReader.readLine();
					arrayLine = stringLine.split("\\s+");
					salaryDataTeam.add(Double.valueOf(arrayLine[0]));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return salaryDataTeam;
	}

}
