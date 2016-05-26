package Model;

import java.util.ArrayList;

public class CStrip {

	double slope;
	double intercept;

	ArrayList<double[]> parameterValue;
	ArrayList<double[]> weightedSRS;

	public double[] curveStrippingOneExp(double[] time, double[] conc,
			int ifLogY) {

		parameterValue = new ArrayList<double[]>();
		weightedSRS = new ArrayList<double[]>();

		double[] param = new double[2];

		double[] X = new double[time.length];
		double[] Y = new double[conc.length];

		for (int i = 0; i < time.length; i++) {
			X[i] = time[i];
			Y[i] = conc[i];
		}

		int noOfPoint = X.length;
		int noOfExp = 1;
		int minPoint = Math.max(2, (int) Math.floor(noOfPoint / (3 + noOfExp)));

		double[] x1;
		double[] y1;

		double[] x2;
		double[] y2;
		double[] wrss = new double[1];
		double cPred;

		int count = 0;
		double minWrss = 9.9E+20;
		int minIndex = 0;

		for (int i = minPoint; i <= X.length; i++) {

			param = new double[2];
			wrss = new double[1];
			x1 = new double[i];
			y1 = new double[i];

			for (int j = 0; j < i; j++) {
				x1[j] = X[X.length - j - 1];
				y1[j] = Y[Y.length - j - 1];
			}

			simpleLinRegression(x1, y1, ifLogY);
			param[0] = Math.exp(intercept);
			param[1] = Math.abs(slope);

			for (int j = 0; j < X.length; j++) {
				cPred = param[0] * Math.exp((-1) * param[1] * X[j]);
				wrss[0] = wrss[0] + (Y[j] - cPred) * (Y[j] - cPred);
			}

			if (wrss[0] < minWrss) {
				minIndex = count;
				minWrss = wrss[0];
			}

			parameterValue.add(param);
			weightedSRS.add(wrss);

			count++;

		}

		param = parameterValue.get(minIndex);

		// implementation of jana

		/*
		 * minPoint = 2;
		 * 
		 * for (int iter = 0; iter < 100; iter++) { parameterValue = new
		 * ArrayList<double[]>(); weightedSRS = new ArrayList<double[]>(); count
		 * = 0; i = minPoint; while (noOfPoint - i >= minPoint) { // param = new
		 * double[noParam]; wrss = new double[1]; x1 = new double[i]; y1 = new
		 * double[i];
		 * 
		 * for (int j = 0; j < i; j++) { x1[j] = X[X.length - j - 1]; y1[j] =
		 * Math.abs(Y[Y.length - j - 1] - param[0] Math.exp((-1) * param[2] *
		 * x1[j])); }
		 * 
		 * simpleLinRegression(x1, y1); param[1] = Math.exp(intercept); param[3]
		 * = Math.abs(slope);
		 * 
		 * x2 = new double[X.length - i]; y2 = new double[X.length - i];
		 * 
		 * for (int j = 0; j < y2.length; j++) { x2[j] = X[j]; y2[j] =
		 * Math.abs(Y[j] - param[1] Math.exp((-1) * param[3] * x2[j])); }
		 * 
		 * simpleLinRegression(x2, y2); param[0] = Math.exp(intercept); param[2]
		 * = Math.abs(slope);
		 * 
		 * for (int j = 0; j < X.length; j++) { cPred = param[1] * Math.exp((-1)
		 * * param[3] * X[j]) + param[0] * Math.exp((-1) * param[2] * X[j]);
		 * wrss[0] = wrss[0] + (Y[j] - cPred) * (Y[j] - cPred); }
		 * 
		 * if (wrss[0] < minWrss) { minIndex = count; minWrss = wrss[0]; }
		 * 
		 * parameterValue.add(param); weightedSRS.add(wrss);
		 * 
		 * count++; i++; }
		 * 
		 * param = parameterValue.get(minIndex);
		 * 
		 * System.out.println("ITER NO: " + iter); for (int k = 0; k <
		 * param.length; k++) {
		 * 
		 * System.out.print("\t" + param[k]);
		 * 
		 * }
		 * 
		 * System.out.println();
		 * 
		 * }
		 */
		System.out.println();
		return param;
	}

	public double[] curveStrippingTwoExp(double[] time, double[] conc) {

		parameterValue = new ArrayList<double[]>();
		weightedSRS = new ArrayList<double[]>();

		double[] param = new double[4];

		double[] X = new double[time.length];
		double[] Y = new double[conc.length];

		for (int i = 0; i < time.length; i++) {
			X[i] = time[i];
			Y[i] = conc[i];
		}

		int noOfPoint = X.length;
		int noOfExp = 2;
		int minPoint = Math.max(2, (int) Math.floor(noOfPoint / (3 + noOfExp)));

		int i = minPoint;

		double[] x1;
		double[] y1;

		double[] x2;
		double[] y2;
		double[] wrss = new double[1];
		double cPred;

		int count = 0;
		double minWrss = 9.9E+20;
		int minIndex = 0;

		while (noOfPoint - i >= minPoint) {
			param = new double[4];
			wrss = new double[1];
			x1 = new double[i];
			y1 = new double[i];

			for (int j = 0; j < i; j++) {
				x1[j] = X[X.length - j - 1];
				y1[j] = Y[Y.length - j - 1];
			}

			simpleLinRegression(x1, y1, 1);
			param[1] = Math.exp(intercept);
			param[3] = Math.abs(slope);

			x2 = new double[X.length - i];
			y2 = new double[X.length - i];

			for (int j = 0; j < y2.length; j++) {
				x2[j] = X[j];
				y2[j] = Math.abs(Y[j] - param[1]
						* Math.exp((-1) * param[3] * x2[j]));
			}

			int signOfCoefficient = -1;

			if ((Y[0] - param[1] * Math.exp((-1) * param[3] * x2[0])) > 0) {
				signOfCoefficient = 1;
			}

			simpleLinRegression(x2, y2, 1);
			param[0] = signOfCoefficient * Math.exp(intercept);
			param[2] = Math.abs(slope);

			for (int j = 0; j < X.length; j++) {
				cPred = param[1] * Math.exp((-1) * param[3] * X[j]) + param[0]
						* Math.exp((-1) * param[2] * X[j]);
				wrss[0] = wrss[0] + (Y[j] - cPred) * (Y[j] - cPred);
			}

			if (wrss[0] < minWrss) {
				minIndex = count;
				minWrss = wrss[0];
			}

			parameterValue.add(param);
			weightedSRS.add(wrss);

			count++;
			i++;
		}

		param = parameterValue.get(minIndex);

		// implementation of jana

		/*
		 * minPoint = 2;
		 * 
		 * for (int iter = 0; iter < 100; iter++) { parameterValue = new
		 * ArrayList<double[]>(); weightedSRS = new ArrayList<double[]>(); count
		 * = 0; i = minPoint; while (noOfPoint - i >= minPoint) { // param = new
		 * double[noParam]; wrss = new double[1]; x1 = new double[i]; y1 = new
		 * double[i];
		 * 
		 * for (int j = 0; j < i; j++) { x1[j] = X[X.length - j - 1]; y1[j] =
		 * Math.abs(Y[Y.length - j - 1] - param[0] Math.exp((-1) * param[2] *
		 * x1[j])); }
		 * 
		 * simpleLinRegression(x1, y1,1); param[1] = Math.exp(intercept);
		 * param[3] = Math.abs(slope);
		 * 
		 * x2 = new double[X.length - i]; y2 = new double[X.length - i];
		 * 
		 * for (int j = 0; j < y2.length; j++) { x2[j] = X[j]; y2[j] =
		 * Math.abs(Y[j] - param[1] Math.exp((-1) * param[3] * x2[j])); }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * simpleLinRegression(x2, y2,1); param[0] = Math.exp(intercept);
		 * param[2] = Math.abs(slope);
		 * 
		 * for (int j = 0; j < X.length; j++) { cPred = param[1] * Math.exp((-1)
		 * * param[3] * X[j]) + param[0] * Math.exp((-1) * param[2] * X[j]);
		 * wrss[0] = wrss[0] + (Y[j] - cPred) * (Y[j] - cPred); }
		 * 
		 * if (wrss[0] < minWrss) { minIndex = count; minWrss = wrss[0]; }
		 * 
		 * parameterValue.add(param); weightedSRS.add(wrss);
		 * 
		 * count++; i++; }
		 * 
		 * param = parameterValue.get(minIndex);
		 * 
		 * System.out.println("ITER NO: " + iter); for (int k = 0; k <
		 * param.length; k++) {
		 * 
		 * System.out.print("\t" + param[k]);
		 * 
		 * }
		 * 
		 * System.out.println();
		 * 
		 * }
		 */
		return param;
	}

	public double[] curveStrippingTwoExpTlag(double[] time, double[] conc) {

		parameterValue = new ArrayList<double[]>();
		weightedSRS = new ArrayList<double[]>();

		double[] param = new double[5];

		double[] X = new double[time.length];
		double[] Y = new double[conc.length];

		for (int i = 0; i < time.length; i++) {
			X[i] = time[i];
			Y[i] = conc[i];
		}

		int noOfPoint = X.length;
		int noOfExp = 2;
		int minPoint = Math.max(2, (int) Math.floor(noOfPoint / (3 + noOfExp)));

		int i = minPoint;

		double[] x1;
		double[] y1;

		double[] x2;
		double[] y2;
		double[] wrss = new double[1];
		double cPred;

		int count = 0;
		double minWrss = 9.9E+20;
		int minIndex = 0;

		double tlag = 0;
		boolean ifTlagIsPresent = false;

		while (noOfPoint - i >= minPoint) {
			param = new double[5];
			wrss = new double[1];
			x1 = new double[i];
			y1 = new double[i];

			for (int j = 0; j < i; j++) {
				x1[j] = X[X.length - j - 1];
				y1[j] = Y[Y.length - j - 1];
			}

			simpleLinRegression(x1, y1, 1);
			param[1] = Math.exp(intercept);
			param[3] = slope * (-1);

			x2 = new double[X.length - i];
			y2 = new double[X.length - i];

			for (int j = 0; j < y2.length; j++) {
				x2[j] = X[j];
				y2[j] = Math.abs(Y[j] - param[1]
						* Math.exp((-1) * param[3] * x2[j]));
				/*
				 * y2[j] = Y[j] - param[1] Math.exp((-1) * param[3] * x2[j]);
				 */
			}

			int signOfCoefficient = -1;

			if ((Y[0] - param[1] * Math.exp((-1) * param[3] * x2[0])) > 0) {
				signOfCoefficient = 1;
			}

			simpleLinRegression(x2, y2, 1);
			param[0] = signOfCoefficient * Math.exp(intercept);
			param[2] = slope * (-1);

			if ((param[0] + param[1]) < 0) {
				ifTlagIsPresent = true;
				tlag = Math.log((-1) * param[1] / param[0])
						/ (param[3] - param[2]);

				param[0] = param[0] / Math.exp(param[2] * tlag);
				param[1] = param[1] / Math.exp(param[3] * tlag);
				param[4] = tlag;

			} else {
				ifTlagIsPresent = false;
			}

			for (int j = 0; j < X.length; j++) {

				if (ifTlagIsPresent == true) {
					cPred = param[1]
							* Math.exp((-1) * param[3] * (X[j] - tlag))
							+ param[0]
							* Math.exp((-1) * param[2] * (X[j] - tlag));
				} else {
					cPred = param[1] * Math.exp((-1) * param[3] * X[j])
							+ param[0] * Math.exp((-1) * param[2] * X[j]);
				}

				wrss[0] = wrss[0] + (Y[j] - cPred) * (Y[j] - cPred);
			}

			if (wrss[0] < minWrss) {
				minIndex = count;
				minWrss = wrss[0];
			}

			parameterValue.add(param);
			weightedSRS.add(wrss);

			count++;
			i++;
		}

		param = parameterValue.get(minIndex);

		System.out.println();
		/*
		 * // jana with tlag
		 * 
		 * minPoint = 2; for (int iter = 0; iter < 100; iter++) { parameterValue
		 * = new ArrayList<double[]>(); weightedSRS = new ArrayList<double[]>();
		 * count = 0; i = minPoint; while (noOfPoint - i >= minPoint) { // param
		 * = new double[noParam]; wrss = new double[1]; x1 = new double[i]; y1 =
		 * new double[i];
		 * 
		 * for (int j = 0; j < i; j++) { x1[j] = X[X.length - j - 1]; y1[j] =
		 * Math.abs(Y[Y.length - j - 1] - param[0] Math.exp((-1) * param[2] *
		 * x1[j])); }
		 * 
		 * simpleLinRegression(x1, y1,1); param[1] = Math.exp(intercept);
		 * param[3] = Math.abs(slope);
		 * 
		 * x2 = new double[X.length - i]; y2 = new double[X.length - i];
		 * 
		 * for (int j = 0; j < y2.length; j++) { x2[j] = X[j]; y2[j] =
		 * Math.abs(Y[j] - param[1] Math.exp((-1) * param[3] * x2[j])); }
		 * 
		 * int signOfCoefficient = -1;
		 * 
		 * if((Y[0] - param[1] * Math.exp((-1) * param[3] * x2[0])) >0) {
		 * signOfCoefficient = 1; }
		 * 
		 * 
		 * 
		 * 
		 * simpleLinRegression(x2, y2,1); param[0] =
		 * signOfCoefficient*Math.exp(intercept); param[2] = slope*(-1);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * if((param[0] + param[1]) <0) { ifTlagIsPresent = true; tlag =
		 * Math.log((-1)*param[1]/param[0])/(param[3]-param[2]);
		 * 
		 * param[0] = param[0]/Math.exp(param[2]*tlag); param[1] =
		 * param[1]/Math.exp(param[3]*tlag); param[4] = tlag;
		 * 
		 * //newtonRaphson(2, param);
		 * 
		 * }else { ifTlagIsPresent = false; }
		 * 
		 * 
		 * 
		 * for (int j = 0; j < X.length; j++) {
		 * 
		 * if(ifTlagIsPresent == true) { cPred = param[1] * Math.exp((-1) *
		 * param[3] * (X[j]-tlag)) + param[0] Math.exp((-1) * param[2] *
		 * (X[j]-tlag)); }else { cPred = param[1] * Math.exp((-1) * param[3] *
		 * X[j]) + param[0] Math.exp((-1) * param[2] * X[j]); }
		 * 
		 * wrss[0] = wrss[0] + (Y[j] - cPred) * (Y[j] - cPred); }
		 * 
		 * 
		 * 
		 * if (wrss[0] < minWrss) { minIndex = count; minWrss = wrss[0]; }
		 * 
		 * parameterValue.add(param); weightedSRS.add(wrss);
		 * 
		 * count++; i++; }
		 * 
		 * param = parameterValue.get(minIndex);
		 * 
		 * System.out.println("ITER NO: " + iter); for (int k = 0; k <
		 * param.length; k++) {
		 * 
		 * System.out.print("\t" + param[k]);
		 * 
		 * }
		 * 
		 * System.out.println();
		 * 
		 * }
		 */

		return param;
	}

	public double[] curveStrippingThreeExp(double[] time, double[] conc) {

		parameterValue = new ArrayList<double[]>();
		weightedSRS = new ArrayList<double[]>();

		double[] param = new double[6];

		double[] X = new double[time.length];
		double[] Y = new double[conc.length];

		for (int i = 0; i < time.length; i++) {
			X[i] = time[i];
			Y[i] = conc[i];
		}

		int noOfPoint = X.length;
		int noOfExp = 3;
		int minPoint = Math.max(2, (int) Math.floor(noOfPoint / (3 + noOfExp)));

		double[] x1;
		double[] y1;

		double[] x2;
		double[] y2;

		double[] x3;
		double[] y3;

		double[] wrss = new double[1];
		double cPred;

		int count = 0;
		double minWrss = 9.9E+20;
		int minIndex = 0;

		for (int i = minPoint; i <= (X.length - 2 * minPoint); i++) {

			for (int j1 = minPoint; j1 + i <= (X.length - minPoint); j1++) {

				param = new double[6];
				wrss = new double[1];
				x1 = new double[i];
				y1 = new double[i];

				for (int j = 0; j < i; j++) {
					x1[j] = X[X.length - j - 1];
					y1[j] = Y[Y.length - j - 1];
				}

				simpleLinRegression(x1, y1, 1);
				param[1] = Math.exp(intercept);
				param[4] = Math.abs(slope);

				x2 = new double[j1];
				y2 = new double[j1];

				for (int j = 0; j < j1; j++) {
					x2[j] = X[X.length - i - j - 1];
					y2[j] = Math.abs(Y[X.length - i - j - 1]
							- param[1]
							* Math.exp((-1) * param[4]
									* X[X.length - i - j - 1]));
				}

				int signOfCoefficient = -1;

				/*
				 * if((y2[0] - param[1]* Math.exp((-1) * param[4] * x2[0])) >0)
				 * { signOfCoefficient = 1; }
				 */

				if ((Y[X.length - i - 1] - param[1]
						* Math.exp((-1) * param[4] * X[X.length - i - 1])) > 0) {
					signOfCoefficient = 1;
				}

				simpleLinRegression(x2, y2, 1);
				param[0] = signOfCoefficient * Math.exp(intercept);
				param[3] = Math.abs(slope);

				x3 = new double[X.length - i - j1];
				y3 = new double[X.length - i - j1];

				for (int j = 0; j < (X.length - i - j1); j++) {
					x3[j] = X[j];
					y3[j] = Math.abs(Y[j] - param[1]
							* Math.exp((-1) * param[4] * X[j]) - param[0]
							* Math.exp((-1) * param[3] * X[j]));
				}

				signOfCoefficient = -1;

				/*
				 * if((y3[0] - param[0]* Math.exp((-1) * param[3] * x3[0])) >0)
				 * { signOfCoefficient = 1; }
				 */

				if ((Y[0] - param[1] * Math.exp((-1) * param[4] * X[0]) - param[0]
						* Math.exp((-1) * param[3] * X[0])) > 0) {
					signOfCoefficient = 1;
				}

				simpleLinRegression(x3, y3, 1);
				param[2] = signOfCoefficient * Math.exp(intercept);
				param[5] = Math.abs(slope);

				for (int j = 0; j < X.length; j++) {
					cPred = param[0] * Math.exp((-1) * param[3] * X[j])
							+ param[1] * Math.exp((-1) * param[4] * X[j])
							+ param[2] * Math.exp((-1) * param[5] * X[j]);
					wrss[0] = wrss[0] + (Y[j] - cPred) * (Y[j] - cPred);
				}

				if (wrss[0] < minWrss) {
					minIndex = count;
					minWrss = wrss[0];

				}

				parameterValue.add(param);
				weightedSRS.add(wrss);

				count++;

			}

		}

		param = parameterValue.get(minIndex);

		/*
		 * for (int j = 0; j < param.length; j++) {
		 * 
		 * System.out.print("\t" + param[j]);
		 * 
		 * }
		 * 
		 * System.out.println();
		 * 
		 * 
		 * 
		 * minPoint = 2;
		 * 
		 * for (int iter = 0; iter < 100; iter++) { parameterValue = new
		 * ArrayList<double[]>(); weightedSRS = new ArrayList<double[]>(); count
		 * = 0; for (int i = minPoint; i <= (X.length - 2 * minPoint); i++) {
		 * 
		 * for (int j1 = minPoint; j1 + i <= (X.length - minPoint); j1++) {
		 * 
		 * // param = new double[noParam]; wrss = new double[1]; x1 = new
		 * double[i]; y1 = new double[i];
		 * 
		 * for (int j = 0; j < i; j++) { x1[j] = X[X.length - j - 1]; y1[j] =
		 * Math.abs(Y[Y.length - j - 1] - param[2] Math.exp((-1) * param[5] *
		 * x1[j]) - param[0] Math.exp((-1) * param[3] * x1[j])); }
		 * 
		 * simpleLinRegression(x1, y1,1); param[1] = Math.exp(intercept);
		 * param[4] = Math.abs(slope);
		 * 
		 * x2 = new double[j1]; y2 = new double[j1];
		 * 
		 * for (int j = 0; j < j1; j++) { x2[j] = X[X.length - i - j - 1]; y2[j]
		 * = Math.abs(Y[X.length - i - j - 1] - param[1] Math.exp((-1) *
		 * param[4] * x2[j]) - param[2] Math.exp((-1) * param[5] * x2[j])); }
		 * 
		 * 
		 * simpleLinRegression(x2, y2,1); param[0] = Math.exp(intercept);
		 * param[3] = Math.abs(slope);
		 * 
		 * x3 = new double[X.length - i - j1]; y3 = new double[X.length - i -
		 * j1];
		 * 
		 * for (int j = 0; j < (X.length - i - j1); j++) { x3[j] = X[j]; y3[j] =
		 * Math.abs(Y[j] - param[1] Math.exp((-1) * param[4] * x3[j]) - param[0]
		 * Math.exp((-1) * param[3] * x3[j])); }
		 * 
		 * simpleLinRegression(x3, y3,1); param[2] = Math.exp(intercept);
		 * param[5] = Math.abs(slope);
		 * 
		 * for (int j = 0; j < X.length; j++) { cPred = param[0] * Math.exp((-1)
		 * * param[3] * X[j]) + param[1] * Math.exp((-1) * param[4] * X[j]) +
		 * param[2] * Math.exp((-1) * param[5] * X[j]); wrss[0] = wrss[0] +
		 * (Y[j] - cPred) * (Y[j] - cPred); }
		 * 
		 * if (wrss[0] < minWrss) { minIndex = count; minWrss = wrss[0];
		 * 
		 * 
		 * }
		 * 
		 * parameterValue.add(param); weightedSRS.add(wrss);
		 * 
		 * count++;
		 * 
		 * }
		 * 
		 * }
		 * 
		 * param = parameterValue.get(minIndex);
		 * 
		 * System.out.println("ITER NO: " + iter); for (int k = 0; k <
		 * param.length; k++) {
		 * 
		 * System.out.print("\t" + param[k]);
		 * 
		 * }
		 * 
		 * System.out.println();
		 * 
		 * }
		 */
		return param;
	}

	public double[] curveStrippingThreeExpTlag(double[] time, double[] conc) {

		parameterValue = new ArrayList<double[]>();
		weightedSRS = new ArrayList<double[]>();

		double[] param = new double[7];

		double[] X = new double[time.length];
		double[] Y = new double[conc.length];

		for (int i = 0; i < time.length; i++) {
			X[i] = time[i];
			Y[i] = conc[i];
		}

		int noOfPoint = X.length;
		int noOfExp = 3;
		int minPoint = Math.max(2, (int) Math.floor(noOfPoint / (3 + noOfExp)));

		double[] x1;
		double[] y1;

		double[] x2;
		double[] y2;

		double[] x3;
		double[] y3;

		double[] wrss = new double[1];
		double cPred;

		int count = 0;
		double minWrss = 9.9E+20;
		int minIndex = 0;

		boolean ifTlagIsPresent = false;
		double tLag = 0;

		for (int i = minPoint; i <= (X.length - 2 * minPoint); i++) {

			for (int j1 = minPoint; j1 + i <= (X.length - minPoint); j1++) {

				param = new double[7];
				wrss = new double[1];
				x1 = new double[i];
				y1 = new double[i];

				for (int j = 0; j < i; j++) {
					x1[j] = X[X.length - j - 1];
					y1[j] = Y[Y.length - j - 1];
				}

				simpleLinRegression(x1, y1, 1);
				param[1] = Math.exp(intercept);
				param[4] = (-1) * slope;

				x2 = new double[j1];
				y2 = new double[j1];

				for (int j = 0; j < j1; j++) {
					x2[j] = X[X.length - i - j - 1];
					y2[j] = Math.abs(Y[X.length - i - j - 1]
							- param[1]
							* Math.exp((-1) * param[4]
									* X[X.length - i - j - 1]));
				}

				int signOfCoefficient = -1;

				/*
				 * if((y2[0] - param[1] * Math.exp((-1) * param[4] * x2[0])) >0)
				 * { signOfCoefficient = 1; }
				 */

				if ((Y[X.length - i - 1] - param[1]
						* Math.exp((-1) * param[4] * X[X.length - i - 1])) > 0) {
					signOfCoefficient = 1;
				}

				simpleLinRegression(x2, y2, 1);
				param[0] = signOfCoefficient * Math.exp(intercept);
				param[3] = (-1) * slope;

				x3 = new double[X.length - i - j1];
				y3 = new double[X.length - i - j1];

				for (int j = 0; j < (X.length - i - j1); j++) {
					x3[j] = X[j];
					y3[j] = Math.abs(Y[j] - param[1]
							* Math.exp((-1) * param[4] * X[j]) - param[0]
							* Math.exp((-1) * param[3] * X[j]));
				}

				signOfCoefficient = -1;

				/*
				 * if((y3[0] - param[0] * Math.exp((-1) * param[3] * x3[0])) >0)
				 * { signOfCoefficient = 1; }
				 */

				if ((Y[0] - param[1] * Math.exp((-1) * param[4] * X[0]) - param[0]
						* Math.exp((-1) * param[3] * X[0])) > 0) {
					signOfCoefficient = 1;
				}

				simpleLinRegression(x3, y3, 1);
				param[2] = signOfCoefficient * Math.exp(intercept);
				param[5] = (-1) * slope;

				if ((param[0] + param[1] + param[2]) < 0) {
					ifTlagIsPresent = true;
					// tLag =
					// Math.log((-1)*param[1]/param[0])/(param[3]-param[2]);
					tLag = bisectionMethod(3, param, X, Y);
					// tLag = trialAndErrorMethod(time, conc, param);
					param[0] = param[0] / Math.exp(param[3] * tLag);
					param[1] = param[1] / Math.exp(param[4] * tLag);
					param[2] = param[2] / Math.exp(param[5] * tLag);
					param[6] = tLag;

					// newtonRaphson(3, param);

				} else {
					ifTlagIsPresent = false;
				}

				for (int j = 0; j < X.length; j++) {

					if (ifTlagIsPresent == true) {
						cPred = param[0]
								* Math.exp((-1) * param[3] * (X[j] - tLag))
								+ param[1]
								* Math.exp((-1) * param[4] * (X[j] - tLag))
								+ param[2]
								* Math.exp((-1) * param[5] * (X[j] - tLag));
					} else {
						cPred = param[0] * Math.exp((-1) * param[3] * X[j])
								+ param[1] * Math.exp((-1) * param[4] * X[j])
								+ param[2] * Math.exp((-1) * param[5] * X[j]);
					}

					wrss[0] = wrss[0] + (Y[j] - cPred) * (Y[j] - cPred);
				}

				/*
				 * for (int j = 0; j < X.length; j++) { cPred = param[0] *
				 * Math.exp((-1) * param[3] * X[j]) + param[1] * Math.exp((-1) *
				 * param[4] * X[j]) + param[2] * Math.exp((-1) * param[5] *
				 * X[j]); wrss[0] = wrss[0] + (Y[j] - cPred) * (Y[j] - cPred); }
				 */

				if (wrss[0] < minWrss) {
					minIndex = count;
					minWrss = wrss[0];

				}

				parameterValue.add(param);
				weightedSRS.add(wrss);

				count++;

			}

		}

		param = parameterValue.get(minIndex);

		return param;
	}

	public double trialAndErrorMethod(double[] time, double[] conc,
			double[] param) {
		double tLag = 0;
		double stepLength;
		int noOfPts = 100;

		double endPoint;

		int firstNonZeroIndex = firstNonZero(conc);

		endPoint = time[firstNonZeroIndex];
		double cPred = 0;
		double mincPred = 9.9E+20;

		stepLength = endPoint / noOfPts;
		double t = 0;
		for (int i = 0; i < 100; i++) {
			t = t + stepLength;
			cPred = param[0] * Math.exp((-1) * param[3] * t) + param[1]
					* Math.exp((-1) * param[4] * t) + param[2]
					* Math.exp((-1) * param[5] * t);

			if (0 <= cPred && cPred < mincPred) {
				mincPred = cPred;
				tLag = t;
			}

		}

		return tLag;
	}

	private int firstNonZero(double[] mat) {

		int index = 0;
		for (int i = 0; i < mat.length; i++) {
			if (mat[i] != 0.0) {
				index = i;
				break;
			}
		}
		return index;
	}

	public double newtonRaphson(int noOfExponent, double[] param) {
		double tLag = 1;
		double prevTLag = 0;
		double h = 0;
		double funcVal = 0;
		double diffFuncVal = 0;

		do {

			funcVal = evaluateFunc1(noOfExponent, param, tLag);
			diffFuncVal = evaluateDiffFunc1(noOfExponent, param, tLag);

			h = (-1) * funcVal / diffFuncVal;

			prevTLag = tLag;
			tLag = tLag + h;
		} while (Math.abs(tLag - prevTLag) > 0.000001);

		tLag = -Math.log(tLag);
		return tLag;
	}

	public double evaluateFunc(int noOfExponent, double[] param, double x0) {
		double value = 0;

		if (noOfExponent == 2) {
			value = param[0] * Math.exp((-1) * param[2] * x0) + param[1]
					* Math.exp((-1) * param[3] * x0);
		} else if (noOfExponent == 3) {
			value = param[0] * Math.exp((-1) * param[3] * x0) + param[1]
					* Math.exp((-1) * param[4] * x0) + param[2]
					* Math.exp((-1) * param[5] * x0);
		}

		return value;

	}

	public double evaluateFunc1(int noOfExponent, double[] param, double x0) {
		double value = 0;

		if (noOfExponent == 2) {
			value = param[0] * Math.exp((-1) * x0 * param[2]) + param[1]
					* Math.exp((-1) * x0 * param[3]);

		} else if (noOfExponent == 3) {
			value = param[0] * Math.exp((-1) * x0 * param[3]) + param[1]
					* Math.exp((-1) * x0 * param[4]) + param[2]
					* Math.exp((-1) * x0 * param[5]);
		}

		// value = (-0.552471546)*x0*x0*x0 + (-1.770298985)*x0*x0 +
		// 3.461162955*x0 -1.523181738;

		return value;

	}

	public double evaluateDiffFunc(int noOfExponent, double[] param, double x0) {
		double value = 0;

		if (noOfExponent == 2) {
			value = (-1) * param[2] * param[0] * Math.exp((-1) * param[2] * x0)
					+ (-1) * param[3] * param[1]
					* Math.exp((-1) * param[3] * x0);
		} else if (noOfExponent == 3) {
			value = (-1) * param[3] * param[0] * Math.exp((-1) * param[3] * x0)
					+ (-1) * param[4] * param[1]
					* Math.exp((-1) * param[4] * x0) + (-1) * param[5]
					* param[2] * Math.exp((-1) * param[5] * x0);
		}

		return value;

	}

	public double evaluateDiffFunc1(int noOfExponent, double[] param, double x0) {
		double value = 0;

		if (noOfExponent == 2) {
			value = param[0] * param[2] * Math.pow(x0, (param[2] - 1))
					+ param[1] * param[3] * Math.pow(x0, (param[3] - 1));

		} else if (noOfExponent == 3) {
			value = param[0] * param[3] * Math.pow(x0, (param[3] - 1))
					+ param[1] * param[4] * Math.pow(x0, (param[4] - 1))
					+ param[2] * param[5] * Math.pow(x0, (param[5] - 1));
		}

		return value;

	}

	public double bisectionMethod(int noOfEponent, double[] param, double[] X,
			double[] Y) {
		double a, b, c, value_a, value_b, value_c;
		double result = 0;

		int firstNonZeroIndex = firstNonZero(Y);
		a = 0;
		b = X[firstNonZeroIndex];

		double preResult = 0;

		do {
			value_a = evaluateFunc1(noOfEponent, param, a);
			value_b = evaluateFunc1(noOfEponent, param, b);
			c = (a + b) / 2;

			value_c = evaluateFunc1(noOfEponent, param, c);

			if (value_c > 0) {
				if (value_b > 0)
					b = c;
				else
					a = c;

			} else if (value_c < 0) {
				if (value_b < 0)
					b = c;
				else
					a = c;

			}

		} while (Math.abs(a - b) > 0.001);

		return c;

	}

	public double calculateValueOfTLag(double[] param, double[] X, double[] Y) {
		double tLag = 0;

		double a = (param[0] * param[3] * param[3] + param[1] * param[4]
				* param[4] + param[2] * param[5] * param[5]) / 2;
		double b = -param[0] * param[3] - param[1] * param[4] - param[2]
				* param[5];
		double c = param[0] + param[1] + param[2];

		double temp = Math.sqrt(b * b - 4 * a * c);

		double root1 = (-b + temp) / (2 * a);
		double root2 = (-b - temp) / (2 * a);

		int firstNonZeroIndex = firstNonZero(X);

		if (root1 > 0 && root1 <= X[firstNonZeroIndex]) {
			if (root1 > root2)
				tLag = root1;
			else if (root2 < X[firstNonZeroIndex] && root2 > 0)
				tLag = root2;
			else
				tLag = root1;
		} else if (root2 > 0 && root2 <= X[firstNonZeroIndex]) {
			tLag = root2;
		} else {
			tLag = X[firstNonZeroIndex];
		}

		return tLag;

	}

	void simpleLinRegression(double[] x1, double[] y1, int ifLogOfY) {
		int n = x1.length;
		double sumX = 0;
		double sumY = 0;
		double sumXY = 0;
		double sumX2 = 0;
		double sumW = 0;
		double sumWXY = 0;
		double sumWX = 0;
		double sumWY = 0;
		double sumWX2 = 0;
		double sumWY2 = 0;
		double Sxx = 0;
		double Syy = 0;
		double Sxy = 0;

		double[] y = new double[y1.length];
		double[] x = new double[x1.length];

		if (ifLogOfY == 1) {
			for (int i = 0; i < y.length; i++) {
				y[i] = Math.log(y1[i]);
			}

			for (int i = 0; i < y.length; i++) {
				x[i] = x1[i];
			}
		} else if (ifLogOfY == 0) {
			for (int i = 0; i < y.length; i++) {
				y[i] = y1[i];
			}

			for (int i = 0; i < y.length; i++) {
				x[i] = Math.log(x1[i]);
			}
		} else if (ifLogOfY == -1) {
			for (int i = 0; i < y.length; i++) {
				y[i] = y1[i];
			}

			for (int i = 0; i < y.length; i++) {
				x[i] = x1[i];
			}
		}

		int weightingIndex = 0;

		for (int i = 0; i < n; i++) {
			sumX = sumX + x[i];
			sumY = sumY + y[i];
			sumXY = sumXY + x[i] * y[i];
			sumX2 = sumX2 + x[i] * x[i];
			sumW = sumW + 1 / Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWX = sumWX + x[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWXY = sumWXY + x[i] * y[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWY = sumWY + y[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWX2 = sumWX2 + x[i] * x[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWY2 = sumWX2 + y[i] * y[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
		}
		slope = (sumW * sumWXY - sumWX * sumWY)
				/ (sumW * sumWX2 - sumWX * sumWX);
		intercept = (sumWX2 * sumWY - sumWX * sumWXY)
				/ (sumW * sumWX2 - sumWX * sumWX);

		// intercept = (sumY - slope*sumX)/n;
		Sxx = (sumWX2 - (sumWX * sumWX / sumW));
		Syy = (sumWY2 - (sumWY * sumWY / sumW));
		Sxy = (sumWXY - (sumWX * sumWY / sumW));
	}

}
