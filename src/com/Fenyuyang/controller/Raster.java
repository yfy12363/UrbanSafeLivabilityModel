package com.Fenyuyang.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;

public class Raster {
	// ========================== Constructors ===========================
	public Raster() {

	}

	public Raster(String fileName) {
		ReadArcgisRasterData(fileName);
	}

	// ======================= Private Properties ========================
	private int ncols;
	private int nrows;
	private String xllcorner;
	private String yllcorner;
	private String cellsize;
	private String NODATA_value;
	private double[][] data;

	// ========================== Public Methods =========================

	public void ReadArcgisRasterData(String fileName) {
		FileReader fileReader;
		BufferedReader bufferedReader;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			readHeader(bufferedReader, fileName);
			data = new double[nrows][ncols];
			readBody(bufferedReader);
			try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				System.out.println("关闭字符流�?�文件流异常");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("读取ArcgisTxt数据文件异常");
			e.printStackTrace();
		}
	}

	private void readHeader(BufferedReader bufferedReader, String fileName) {
		String stringLine;
		String[] arrayLine;
		try {
			stringLine = bufferedReader.readLine();
			arrayLine = stringLine.split("\\s+");
			ncols = Integer.parseInt(arrayLine[1]);

			stringLine = bufferedReader.readLine();
			arrayLine = stringLine.split("\\s+");
			nrows = Integer.parseInt(arrayLine[1]);

			stringLine = bufferedReader.readLine();
			arrayLine = stringLine.split("\\s+");
			xllcorner = arrayLine[1];

			stringLine = bufferedReader.readLine();
			arrayLine = stringLine.split("\\s+");
			yllcorner = arrayLine[1];

			stringLine = bufferedReader.readLine();
			arrayLine = stringLine.split("\\s+");
			cellsize = arrayLine[1];

			stringLine = bufferedReader.readLine();
			arrayLine = stringLine.split("\\s+");
			NODATA_value = arrayLine[1];
		} catch (IOException e) {
			System.out.println("Reading header file exception");
			e.printStackTrace();
		}

	}

	private void readBody(BufferedReader bufferedReader) {
		String stringLine;
		String[] arrayLine;
		int nLine = nrows - 1;
		try {
			while (bufferedReader.ready()) {
				stringLine = bufferedReader.readLine();
				arrayLine = stringLine.split("\\s+");
				for (int i = 0; i < arrayLine.length; i++) {
					data[nLine][i] = Double.parseDouble(arrayLine[i]);
				}
				nLine--;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void WriteArcgisRasterData(String fileName, String format) {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		try {
			fileWriter = new FileWriter(fileName);
			bufferedWriter = new BufferedWriter(fileWriter);
			writeHead(bufferedWriter);
			writeBody(bufferedWriter, format);
			try {
				bufferedWriter.close();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeHead(BufferedWriter bufferedWriter) {
		try {
			bufferedWriter.append("ncols         " + Integer.toString(ncols) + "\r\n");
			bufferedWriter.append("nrows         " + Integer.toString(nrows) + "\r\n");
			bufferedWriter.append("xllcorner     " + xllcorner + "\r\n");
			bufferedWriter.append("yllcorner     " + yllcorner + "\r\n");
			bufferedWriter.append("cellsize      " + cellsize + "\r\n");
			bufferedWriter.append("NODATA_value  " + NODATA_value + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeBody(BufferedWriter bufferedWriter, String format) {
		if (format.equals("double")) {
			int nLine = nrows - 1;
			try {
				for (int i = 0; i <= nLine; nLine--) {
					for (int j = 0; j < ncols; j++) {
						if (data[nLine][j] != -9999) {
							NumberFormat nf = NumberFormat.getNumberInstance();
							nf.setMaximumFractionDigits(2);
							bufferedWriter.append((nf.format(data[nLine][j])) + " ");
						} else {
							bufferedWriter.append((int) (data[nLine][j]) + " ");
						}
					}
					bufferedWriter.append("\r\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (format.equals("integer")) {
			int nLine = nrows - 1;
			try {
				for (int i = 0; i <= nLine; nLine--) {
					for (int j = 0; j < ncols; j++) {
						bufferedWriter.append((int) data[nLine][j] + " ");
					}
					bufferedWriter.append("\r\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			int nLine = nrows - 1;
			try {
				for (int i = 0; i <= nLine; nLine--) {
					for (int j = 0; j < ncols; j++) {
						bufferedWriter.append((int) data[nLine][j] + " ");
					}
					bufferedWriter.append("\r\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// ======================= Getters and Setters =======================
	public int getNcols() {
		return ncols;
	}

	public void setNcols(int ncols) {
		this.ncols = ncols;
	}

	public int getNrows() {
		return nrows;
	}

	public void setNrows(int nrows) {
		this.nrows = nrows;
	}

	public String getXllcorner() {
		return xllcorner;
	}

	public void setXllcorner(String xllcorner) {
		this.xllcorner = xllcorner;
	}

	public String getYllcorner() {
		return yllcorner;
	}

	public void setYllcorner(String yllcorner) {
		this.yllcorner = yllcorner;
	}

	public String getCellsize() {
		return cellsize;
	}

	public void setCellsize(String cellsize) {
		this.cellsize = cellsize;
	}

	public String getNODATA_value() {
		return NODATA_value;
	}

	public void setNODATA_value(String nODATA_value) {
		NODATA_value = nODATA_value;
	}

	public double[][] getData() {
		return data;
	}

	public void setData(double[][] data) {
		this.data = data;
	}

	public double getData(int x, int y) {
		return data[y][x];
	}

	public void setData(double data, int x, int y) {
		this.data[y][x] = data;
	}
}
