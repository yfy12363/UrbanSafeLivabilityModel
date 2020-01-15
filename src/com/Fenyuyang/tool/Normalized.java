package com.Fenyuyang.tool;

public class Normalized {
	public static double[] arrayNormalized(double[] rate) {
		double sum = 0;
		double[] rateNormalized = new double[rate.length];
		for (int i = 0; i < rate.length; i++) {
			sum = sum + rate[i];
		}
		if (sum != 0) {
			for (int i = 0; i < rateNormalized.length; i++) {
				rateNormalized[i] = rate[i] / sum;
			}
		} else {
			for (int i = 0; i < rateNormalized.length; i++) {
				rateNormalized[i] = 1 / rateNormalized.length;
			}
		}

		return rateNormalized;
	}

	public static String keepFourDecimal(double d) {
		return String.format("%.4f", d);
	}
}
