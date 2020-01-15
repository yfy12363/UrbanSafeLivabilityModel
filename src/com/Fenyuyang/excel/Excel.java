package com.Fenyuyang.excel;

import java.io.File;
import java.io.IOException;

import com.Fenyuyang.controller.DataCollection;
import com.Fenyuyang.tool.Normalized;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Excel {

	File f;

	public Excel() throws Exception {
		f = new File("output//data.xlt");
		this.createUserExcelFile(f);
	}

	private int row = 0;
	private int column = 0;
	private WritableSheet sheet = null;
	private WritableWorkbook book = null;
	private static WritableCellFormat fontFormat = null;

	private static boolean init = false;

	public void step(DataCollection dataCollection) throws WriteException, IOException, BiffException {
		Workbook wb = Workbook.getWorkbook(f);
		WritableWorkbook wwb = Workbook.createWorkbook(f, wb);
		WritableSheet ws = wwb.getSheet(0);

		ws.setColumnView(0, 40);

		column++;
		row = 0;
		ws.addCell(new Label(column, row++, Integer.toString(column), fontFormat));

		ws.addCell(new Label(column, row++, Integer.toString(
				dataCollection.getGender_1() + dataCollection.getGender_2() + dataCollection.getForeignPopulation()),
				fontFormat));
		ws.addCell(new Label(column, row++,
				Integer.toString(dataCollection.getGender_1() + dataCollection.getGender_2()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getForeignPopulation()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getBirthNumber()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getDeathNumber()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getMoveInPopulation()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getMoveOutPopulation()), fontFormat));

		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getGender_1()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getGender_2()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getAge_0_10()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getAge_11_20()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getAge_21_30()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getAge_31_40()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getAge_41_50()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getAge_51_60()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getAge_61_70()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getAge_71_80()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getAge_81_90()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getAge_91_100()), fontFormat));

		ws.addCell(new Label(column, row++,
				Integer.toString(dataCollection.getFamilyInhabitantNumber_1()
						+ dataCollection.getFamilyInhabitantNumber_2() + dataCollection.getFamilyInhabitantNumber_3()
						+ dataCollection.getFamilyInhabitantNumber_4() + dataCollection.getFamilyInhabitantNumber_5()
						+ dataCollection.getFamilyInhabitantNumber_6() + dataCollection.getFamilyInhabitantNumber_7()
						+ dataCollection.getFamilyInhabitantNumber_8() + dataCollection.getFamilyInhabitantNumber_9()
						+ dataCollection.getFamilyInhabitantNumber_10()),
				fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getMoveInFamilyNumber_now()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getMoveOutFamilyNumber_now()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getDeathFamilyNumber_now()), fontFormat));
		ws.addCell(
				new Label(column, row++, Integer.toString(dataCollection.getFamilyInhabitantNumber_1()), fontFormat));
		ws.addCell(
				new Label(column, row++, Integer.toString(dataCollection.getFamilyInhabitantNumber_2()), fontFormat));
		ws.addCell(
				new Label(column, row++, Integer.toString(dataCollection.getFamilyInhabitantNumber_3()), fontFormat));
		ws.addCell(
				new Label(column, row++, Integer.toString(dataCollection.getFamilyInhabitantNumber_4()), fontFormat));
		ws.addCell(
				new Label(column, row++, Integer.toString(dataCollection.getFamilyInhabitantNumber_5()), fontFormat));
		ws.addCell(
				new Label(column, row++, Integer.toString(dataCollection.getFamilyInhabitantNumber_6()), fontFormat));
		ws.addCell(
				new Label(column, row++, Integer.toString(dataCollection.getFamilyInhabitantNumber_7()), fontFormat));
		ws.addCell(
				new Label(column, row++, Integer.toString(dataCollection.getFamilyInhabitantNumber_8()), fontFormat));
		ws.addCell(
				new Label(column, row++, Integer.toString(dataCollection.getFamilyInhabitantNumber_9()), fontFormat));
		ws.addCell(
				new Label(column, row++, Integer.toString(dataCollection.getFamilyInhabitantNumber_10()), fontFormat));

		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getEducationLevel_0()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getEducationLevel_1()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getEducationLevel_2()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getEducationLevel_3()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getEducationLevel_4()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getEducationLevel_5()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getEducationLevel_6()), fontFormat));

		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getSatisfaction_1()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getSatisfaction_2()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getSatisfaction_3()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getSatisfaction_4()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getSatisfaction_5()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getSatisfaction_6()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getSatisfaction_7()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getSatisfaction_8()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getSatisfaction_9()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getSatisfaction_10()), fontFormat));

		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getLivability_1()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getLivability_2()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getLivability_3()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getLivability_4()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getLivability_5()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getLivability_6()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getLivability_7()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getLivability_8()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getLivability_9()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getLivability_10()), fontFormat));

		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getIncome_1()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getIncome_2()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getIncome_3()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getIncome_4()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getIncome_5()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getIncome_6()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getIncome_7()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getIncome_8()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getIncome_9()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getIncome_10()), fontFormat));

		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getStreetPopulation_1()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getStreetPopulation_2()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getStreetPopulation_3()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getStreetPopulation_4()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getStreetPopulation_5()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getStreetPopulation_6()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getStreetPopulation_7()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getStreetPopulation_8()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getStreetPopulation_9()), fontFormat));
		ws.addCell(new Label(column, row++, Integer.toString(dataCollection.getStreetPopulation_10()), fontFormat));

		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetSatisfaction()[0]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetSatisfaction()[1]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetSatisfaction()[2]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetSatisfaction()[3]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetSatisfaction()[4]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetSatisfaction()[5]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetSatisfaction()[6]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetSatisfaction()[7]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetSatisfaction()[8]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetSatisfaction()[9]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getFutianSatisfaction()),
				fontFormat));

		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetLivability()[0]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetLivability()[1]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetLivability()[2]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetLivability()[3]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetLivability()[4]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetLivability()[5]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetLivability()[6]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetLivability()[7]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetLivability()[8]),
				fontFormat));
		ws.addCell(new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getStreetLivability()[9]),
				fontFormat));
		ws.addCell(
				new Label(column, row++, Normalized.keepFourDecimal(dataCollection.getFutianLivability()), fontFormat));

		wwb.write();
		
		if (wb != null)
			wwb.close();
		wb.close();
	}

	private void init() throws WriteException {
		WritableFont font1;
		font1 = new WritableFont(WritableFont.TAHOMA, 11, WritableFont.BOLD, false);
		fontFormat = new WritableCellFormat(font1);
		fontFormat.setBackground(Colour.WHITE);
		fontFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		fontFormat.setAlignment(Alignment.CENTRE);

		init = true;
	}

	public void createUserExcelFile(File destFile) throws WriteException, IOException {
		if (init == false)
			init();

		book = Workbook.createWorkbook(destFile);
		sheet = book.createSheet("PopulationStatistics", 0);
		sheet.setColumnView(0, 50);
		sheet.addCell(new Label(column, row++, "Category", fontFormat));

		sheet.addCell(new Label(column, row++, "The total population of Futian District", fontFormat));
		sheet.addCell(new Label(column, row++, "Total resident population", fontFormat));
		sheet.addCell(new Label(column, row++, "Total floating population", fontFormat));
		sheet.addCell(new Label(column, row++, "Total births", fontFormat));
		sheet.addCell(new Label(column, row++, "Total deaths", fontFormat));
		sheet.addCell(new Label(column, row++, "Total immigrant population", fontFormat));
		sheet.addCell(new Label(column, row++, "Total number of migrants", fontFormat));

		sheet.addCell(new Label(column, row++, "Man", fontFormat));
		sheet.addCell(new Label(column, row++, "Woman", fontFormat));
		sheet.addCell(new Label(column, row++, "Age0-10", fontFormat));
		sheet.addCell(new Label(column, row++, "Age11-20", fontFormat));
		sheet.addCell(new Label(column, row++, "Age21-30", fontFormat));
		sheet.addCell(new Label(column, row++, "Age31-40", fontFormat));
		sheet.addCell(new Label(column, row++, "Age41-50", fontFormat));
		sheet.addCell(new Label(column, row++, "Age51-60", fontFormat));
		sheet.addCell(new Label(column, row++, "Age61-70", fontFormat));
		sheet.addCell(new Label(column, row++, "Age71-80", fontFormat));
		sheet.addCell(new Label(column, row++, "Age81-90", fontFormat));
		sheet.addCell(new Label(column, row++, "Age91-100", fontFormat));

		sheet.addCell(new Label(column, row++, "total houses", fontFormat));
		sheet.addCell(new Label(column, row++, "Number of moving families", fontFormat));
		sheet.addCell(new Label(column, row++, "Number of families moving out", fontFormat));
		sheet.addCell(new Label(column, row++, "Number of dead families", fontFormat));
		sheet.addCell(new Label(column, row++, "1 person household", fontFormat));
		sheet.addCell(new Label(column, row++, "2 person household", fontFormat));
		sheet.addCell(new Label(column, row++, "3 person household", fontFormat));
		sheet.addCell(new Label(column, row++, "4 person household", fontFormat));
		sheet.addCell(new Label(column, row++, "5 person household", fontFormat));
		sheet.addCell(new Label(column, row++, "6 person household", fontFormat));
		sheet.addCell(new Label(column, row++, "7 person household", fontFormat));
		sheet.addCell(new Label(column, row++, "8 person household", fontFormat));
		sheet.addCell(new Label(column, row++, "9 person household", fontFormat));
		sheet.addCell(new Label(column, row++, "10 person household", fontFormat));

		sheet.addCell(new Label(column, row++, "Education level 0", fontFormat));
		sheet.addCell(new Label(column, row++, "Education level 1", fontFormat));
		sheet.addCell(new Label(column, row++, "Education level 2", fontFormat));
		sheet.addCell(new Label(column, row++, "Education level 3", fontFormat));
		sheet.addCell(new Label(column, row++, "Education level 4", fontFormat));
		sheet.addCell(new Label(column, row++, "Education level 5", fontFormat));
		sheet.addCell(new Label(column, row++, "Education level 6", fontFormat));

		sheet.addCell(new Label(column, row++, "Family satisfaction 1", fontFormat));
		sheet.addCell(new Label(column, row++, "Family satisfaction 2", fontFormat));
		sheet.addCell(new Label(column, row++, "Family satisfaction 3", fontFormat));
		sheet.addCell(new Label(column, row++, "Family satisfaction 4", fontFormat));
		sheet.addCell(new Label(column, row++, "Family satisfaction 5", fontFormat));
		sheet.addCell(new Label(column, row++, "Family satisfaction 6", fontFormat));
		sheet.addCell(new Label(column, row++, "Family satisfaction 7", fontFormat));
		sheet.addCell(new Label(column, row++, "Family satisfaction 8", fontFormat));
		sheet.addCell(new Label(column, row++, "Family satisfaction 9", fontFormat));
		sheet.addCell(new Label(column, row++, "Family satisfaction 10", fontFormat));

		sheet.addCell(new Label(column, row++, "Livability level 1", fontFormat));
		sheet.addCell(new Label(column, row++, "Livability level 2", fontFormat));
		sheet.addCell(new Label(column, row++, "Livability level 3", fontFormat));
		sheet.addCell(new Label(column, row++, "Livability level 4", fontFormat));
		sheet.addCell(new Label(column, row++, "Livability level 5", fontFormat));
		sheet.addCell(new Label(column, row++, "Livability level 6", fontFormat));
		sheet.addCell(new Label(column, row++, "Livability level 7", fontFormat));
		sheet.addCell(new Label(column, row++, "Livability level 8", fontFormat));
		sheet.addCell(new Label(column, row++, "Livability level 9", fontFormat));
		sheet.addCell(new Label(column, row++, "Livability level 10", fontFormat));

		sheet.addCell(new Label(column, row++, "Salary grade 1", fontFormat));
		sheet.addCell(new Label(column, row++, "Salary grade 2", fontFormat));
		sheet.addCell(new Label(column, row++, "Salary grade 3", fontFormat));
		sheet.addCell(new Label(column, row++, "Salary grade 4", fontFormat));
		sheet.addCell(new Label(column, row++, "Salary grade 5", fontFormat));
		sheet.addCell(new Label(column, row++, "Salary grade 6", fontFormat));
		sheet.addCell(new Label(column, row++, "Salary grade 7", fontFormat));
		sheet.addCell(new Label(column, row++, "Salary grade 8", fontFormat));
		sheet.addCell(new Label(column, row++, "Salary grade 9", fontFormat));
		sheet.addCell(new Label(column, row++, "Salary grade 10", fontFormat));

		sheet.addCell(new Label(column, row++, "YUAN LING street population ", fontFormat));
		sheet.addCell(new Label(column, row++, "HUA QIANG BEI street population", fontFormat));
		sheet.addCell(new Label(column, row++, "NAN YUAN street population", fontFormat));
		sheet.addCell(new Label(column, row++, "HUA FU street population", fontFormat));
		sheet.addCell(new Label(column, row++, "LIAN HUA street population", fontFormat));
		sheet.addCell(new Label(column, row++, "FU TIAN street population", fontFormat));
		sheet.addCell(new Label(column, row++, "FU BAO street population", fontFormat));
		sheet.addCell(new Label(column, row++, "SHA TOU street population", fontFormat));
		sheet.addCell(new Label(column, row++, "XIANG MI HU street population", fontFormat));
		sheet.addCell(new Label(column, row++, "MEI LIN street population", fontFormat));

		sheet.addCell(new Label(column, row++, "YUAN LING street satisfaction", fontFormat));
		sheet.addCell(new Label(column, row++, "HUA QIANG BEI street satisfaction", fontFormat));
		sheet.addCell(new Label(column, row++, "NAN YUAN street satisfaction", fontFormat));
		sheet.addCell(new Label(column, row++, "HUA FU street satisfaction", fontFormat));
		sheet.addCell(new Label(column, row++, "LIAN HUA street satisfaction", fontFormat));
		sheet.addCell(new Label(column, row++, "FU TIAN street satisfaction", fontFormat));
		sheet.addCell(new Label(column, row++, "FU BAO street satisfaction", fontFormat));
		sheet.addCell(new Label(column, row++, "SHA TOU street satisfaction", fontFormat));
		sheet.addCell(new Label(column, row++, "XIANG MI HU street satisfaction", fontFormat));
		sheet.addCell(new Label(column, row++, "MEI LIN street satisfaction", fontFormat));
		sheet.addCell(new Label(column, row++, "FU TIAN District satisfaction", fontFormat));

		sheet.addCell(new Label(column, row++, "YUAN LING street livability", fontFormat));
		sheet.addCell(new Label(column, row++, "HUA QIANG BEI street livability", fontFormat));
		sheet.addCell(new Label(column, row++, "NAN YUAN street livability", fontFormat));
		sheet.addCell(new Label(column, row++, "HUA FU street livability", fontFormat));
		sheet.addCell(new Label(column, row++, "LIAN HUA street livability", fontFormat));
		sheet.addCell(new Label(column, row++, "FU TIAN street livability", fontFormat));
		sheet.addCell(new Label(column, row++, "FU BAO street livability", fontFormat));
		sheet.addCell(new Label(column, row++, "SHA TOU street livability", fontFormat));
		sheet.addCell(new Label(column, row++, "XIANG MI HU street livability", fontFormat));
		sheet.addCell(new Label(column, row++, "MEI LIN street livability", fontFormat));
		sheet.addCell(new Label(column, row++, "FU TIAN District livability", fontFormat));

		book.write();
		if (book != null)
			book.close();
	}

}