package Model;

import Common.TestException;
import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class PkCurveStripper {

	CurveStripping csInstance;
	CurveStripper lambdaZCalInst;

	public double[] parameterByModel(int modelNumber, double[][] time,
			double[][] conc, int noParam, double dose, double lengthOfInfusion) {
		csInstance = CurveStripping.createCSInstance();
		lambdaZCalInst = new CurveStripper();

		double[] param = new double[noParam];

		double[] X = new double[time.length];
		double[] Y = new double[conc.length];

		for (int i = 0; i < time.length; i++) {
			X[i] = time[i][0];
			Y[i] = conc[i][0];
		}

		try {
			param = chooseLibraryModel(modelNumber, X, Y, noParam, dose,
					lengthOfInfusion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paramValExceptionChecking(param);
		return param;
	}

	void paramValExceptionChecking(double[] param) {
		PkParamEstimator caParamCalInst = PkParamEstimator
				.createPkParamEstimateInstance();

		for (int k = 0; k < param.length; k++) {
			if (Double.isInfinite(param[k]) == true
					|| Double.isNaN(param[k]) == true) {
				TestException inst = new TestException();
				inst.throwCustomException(118);
				PkPdInfo.createPKPDInstance().exceptionForCurrentProfile = 1;
				break;
			}
		}

	}

	public double[] chooseLibraryModel(int modelNumber, double[] X,
			double[] Y, int noParam, double dose, double lengthOfInfusion)
			throws Exception {
		double[] param = new double[noParam];

		int noOfNonZeroPts = 0;

		for (int i = 0; i < Y.length; i++) {
			if (Y[i] != 0)
				noOfNonZeroPts++;
		}

		double[] xTemp = new double[noOfNonZeroPts];
		double[] yTemp = new double[noOfNonZeroPts];

		int count = 0;
		for (int i = 0; i < Y.length; i++) {

			if (Y[i] != 0) {
				xTemp[count] = X[i];
				yTemp[count] = Y[i];
				count++;
			}
		}

		ApplicationInfo appInfo = PkParamEstimator
				.createPkParamEstimateInstance().appInfoinst;
		PkPdInfo pkPdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkPdInst.copyProcessingInput();

		if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == true) {
			if (modelNumber == 1)
				param = modelAE1(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 2)
				param = modelAE2(xTemp, yTemp, noParam, dose, lengthOfInfusion);
			else if (modelNumber == 3)
				param = modelAE3(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 4)
				param = modelAE4(X, Y, noParam, dose);
			else if (modelNumber == 5)
				param = modelAE5(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 6)
				param = modelAE6(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 7)
				param = modelAE7(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 8)
				param = modelAE8(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 9)
				param = modelAE9(xTemp, yTemp, noParam, dose, lengthOfInfusion);
			else if (modelNumber == 10)
				param = modelAE10(xTemp, yTemp, noParam, dose, lengthOfInfusion);
			else if (modelNumber == 11)
				param = modelAE11(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 12)
				param = modelAE12(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 13)
				param = modelAE13(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 14)
				param = modelAE14(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 15)
				param = modelAE15(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 16)
				param = modelAE16(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 17)
				param = modelAE17(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 18)
				param = modelAE18(xTemp, yTemp, noParam, dose);
			else if (modelNumber == 19)
				param = modelAE19(xTemp, yTemp, noParam, dose);
		} else {

			DeModelCurveStripper deModelInitValCalInst = new DeModelCurveStripper();

			deModelInitValCalInst.chooseDEModels(modelNumber, xTemp, yTemp,
					noParam, dose, lengthOfInfusion);

		}

		return param;
	}

	public PkCurveStripper() {

	}

	private double[] modelAE19(double[] X, double[] Y, int noParam, double dose)
			throws Exception {

		double[] param = new double[noParam];
		double[] finalParam = new double[noParam];
		double[] result = new double[6];

		int noOfExp = 3;
		int tempNoOfPts = (int) (X.length / (3 + noOfExp)) + 1;
		int noOfPts = Math.max(2, tempNoOfPts);
		double[][] tempResult = new double[noOfExp][2];

		double[] xForStrip = new double[noOfPts];
		double[] yForStrip = new double[noOfPts];

		for (int i = 0; i < yForStrip.length; i++) {
			xForStrip[i] = X[X.length - noOfPts + i];
			yForStrip[i] = Y[X.length - noOfPts + i];
		}

		tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
				yForStrip, 0, 0, 1);

		double[] X1 = new double[noOfPts];
		double[] Y1 = new double[noOfPts];

		for (int i = 0; i < noOfPts; i++) {
			X1[i] = X[X.length - 2 * noOfPts + i];
			Y1[i] = Math.abs((tempResult[0][0]
					* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y[X.length - 2
					* noOfPts + i]));
		}

		tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y1, 0, 0, 1);

		double[] X2 = new double[X.length - 2 * noOfPts];
		double[] Y2 = new double[X.length - 2 * noOfPts];

		for (int i = 0; i < X.length - 2 * noOfPts; i++) {
			X2[i] = X[i];
			Y2[i] = Math.abs((tempResult[0][0]
					* Math.exp(X[i] * (-1) * tempResult[0][1])
					+ (tempResult[1][0] * Math.exp(X[i] * (-1)
							* tempResult[1][1])) - Y[i]));
		}

		tempResult[2] = lambdaZCalInst.lambdaZCalculationForCA(X2, Y2, 0, 0, 1);

		for (int i = 0; i < 100; i++) {
			double[] Y3 = new double[yForStrip.length];
			double[] Y4 = new double[Y1.length];
			double[] Y5 = new double[Y2.length];
			for (int j = 0; j < yForStrip.length; j++) {
				// xForStrip [j] = X[X.length-1 -j];
				Y3[j] = Math.abs(tempResult[1][0]
						* Math.exp(xForStrip[j] * (-1) * tempResult[1][1])
						+ tempResult[2][0]
						* Math.exp(X1[j] * (-1) * tempResult[2][1])
						- yForStrip[j]);
			}
			tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
					Y3, 0, 0, 1);

			for (int j = 0; j < X1.length; j++) {
				// xForStrip [j] = X[X.length-1 -j];
				Y4[j] = Math.abs(tempResult[2][0]
						* Math.exp(X1[j] * (-1) * tempResult[2][1])
						+ tempResult[0][0]
						* Math.exp(X1[j] * (-1) * tempResult[0][1]) - Y1[j]);
			}

			tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y4, 0,
					0, 1);

			for (int j = 0; j < X.length - 2 * noOfPts; j++) {

				Y5[j] = Math.abs((tempResult[0][0]
						* Math.exp(X2[j] * (-1) * tempResult[0][1])
						+ (tempResult[1][0] * Math.exp(X2[j] * (-1)
								* tempResult[1][1])) - Y2[i]));
			}
			tempResult[2] = lambdaZCalInst.lambdaZCalculationForCA(X2, Y5, 0,
					0, 1);

			finalParam[0] = Math.abs(tempResult[1][0]);
			finalParam[1] = Math.abs(tempResult[0][0]);
			finalParam[2] = Math.abs(tempResult[2][0]);
			finalParam[3] = Math.abs(tempResult[1][1]);
			finalParam[4] = Math.abs(tempResult[0][1]);
			finalParam[5] = Math.abs(tempResult[2][1]);

			double sumPar = 0;
			for (int j = 0; j < noParam; i++) {
				double ratio = Math.abs((finalParam[j] - param[j]) / param[j]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar / noParam;
			for (int j = 0; j < noParam; i++) {
				param[j] = finalParam[j];

			}

			if (check <= PkPdInfo.createPKPDInstance().convergence)
				break;

		}

		return param;

	}

	private double[] modelAE17(double[] X, double[] Y, int noParam, double dose) {
		double[] param = new double[noParam];
		param[0] = 1 / 0;
		return param;
	}

	private double[] modelAE16(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		param[0] = 1 / 0;
		return param;
	}

	private double[] modelAE15(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		param[0] = 1 / 0;
		return param;
	}

	private double[] modelAE6(double[] X, double[] Y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] finalParam = new double[noParam];

		CurveStripper inst = new CurveStripper();

		double Cmax = maxVal(Y);

		int noOfExp = 2;
		int tempNoOfPts = (int) (X.length / (3 + noOfExp)) + 1;
		int noOfPts = Math.max(2, tempNoOfPts);
		double[][] tempResult = new double[2][2];

		double[] xForStrip = new double[noOfPts];
		double[] yForStrip = new double[noOfPts];

		for (int i = 0; i < yForStrip.length; i++) {
			xForStrip[i] = X[X.length - noOfPts + i];
			yForStrip[i] = Y[X.length - noOfPts + i];
		}

		try {
			tempResult[0] = inst.lambdaZCalculationForCA(xForStrip, yForStrip,
					0, 0, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * tempResult[0] = csInstance.parameterCalculationUsingCurveStripping(
		 * xForStrip, yForStrip, 2);
		 */

		double[] X1 = new double[X.length - noOfPts];
		double[] Y1 = new double[X.length - noOfPts];

		for (int i = 0; i < Y.length - noOfPts; i++) {
			X1[i] = X[i];
			Y1[i] = Math.abs((tempResult[0][0]
					* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y[i]));
		}

		try {
			tempResult[1] = inst.lambdaZCalculationForCA(X1, Y1, 0, 0, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * tempResult[1] =
		 * csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);
		 */

		for (int k = 0; k < 100; k++) {

			double[] Y2 = new double[yForStrip.length];
			double[] Y3 = new double[Y1.length];

			for (int j = 0; j < yForStrip.length; j++) {
				// xForStrip [j] = X[X.length-1 -j];
				Y2[j] = Math
						.abs((tempResult[1][0]
								* Math.exp(xForStrip[j] * (-1)
										* tempResult[1][1]) - yForStrip[j]));

			}

			try {
				tempResult[0] = inst.lambdaZCalculationForCA(xForStrip, Y2, 0,
						0, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * tempResult[0] =
			 * csInstance.parameterCalculationUsingCurveStripping( xForStrip,
			 * Y2, 2);
			 */

			for (int i = 0; i < Y.length - noOfPts; i++) {

				Y3[i] = Math.abs((tempResult[0][0]
						* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y1[i]));
			}

			try {
				tempResult[1] = inst.lambdaZCalculationForCA(X1, Y3, 0, 0, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * tempResult[1] =
			 * csInstance.parameterCalculationUsingCurveStripping( X1, Y1, 2);
			 */

			finalParam[2] = Math.abs(tempResult[0][1]);
			finalParam[1] = Math.abs(tempResult[1][1]);

			finalParam[0] = dose / Cmax;

			double sumPar = 0;
			for (int i = 0; i < noParam; i++) {
				double ratio = Math.abs((finalParam[i] - param[i]) / param[i]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar / noParam;
			for (int i = 0; i < noParam; i++) {
				param[i] = finalParam[i];

			}

			if (check <= PkPdInfo.createPKPDInstance().convergence)
				break;

		}

		param[1] = Math.abs(tempResult[0][1]);
		param[0] = dose / Cmax;
		
		int firstNonZeroIndex = firstNonZero(Y);
		
		param[2] = X[firstNonZeroIndex];

		/*param[2] = Math.abs((Math.log(tempResult[1][0]) - Math
				.log(tempResult[0][0]))
				/ (tempResult[1][1] - tempResult[0][1]));*/

		return param;
	}

	private double[] modelAE1(double[] X, double[] Y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[2];
		result = csInstance.parameterCalculationUsingCurveStripping(X, Y,
				noParam);
		param[1] = Math.abs(result[1]);

		double max = maxVal(Y);
		param[0] = dose / max;

		return param;
	}

	private double[] modelAE2(double[] X, double[] Y, int noParam, double dose,
			double lengthOfInfusion) {
		double[] param = new double[noParam];
		double max = maxVal(Y);
		int maxIndex = findVal(Y, max);
		double[] X1 = new double[X.length - maxIndex - 1];
		double[] Y1 = new double[X.length - maxIndex - 1];
		int count = 0;
		for (int i = maxIndex + 1; i < X.length; i++) {
			X1[count] = X[i];
			Y1[count] = Y[i];
			count++;
		}

		double[] result = new double[2];
		result = csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);
		param[1] = Math.abs(result[1]);

		double k0 = dose / lengthOfInfusion;

		param[0] = k0 / (max);

		return param;
	}

	private double[] modelAE3(double[] X, double[] Y, int noParam, double dose) {

		double[] param = new double[noParam];
		double[] finalParam = new double[noParam];
		CurveStripper inst = new CurveStripper();

		double Cmax = maxVal(Y);

		int noOfExp = 2;
		int tempNoOfPts = (int) (X.length / (3 + noOfExp)) + 1;
		int noOfPts = Math.max(2, tempNoOfPts);

		double[][] tempResult = new double[2][2];

		double[] xForStrip = new double[noOfPts];
		double[] yForStrip = new double[noOfPts];
		double[] C = new double[noOfPts];

		for (int i = 0; i < yForStrip.length; i++) {
			xForStrip[i] = X[X.length - noOfPts + i];
			yForStrip[i] = Y[X.length - noOfPts + i];
			C[i] = Y[X.length - noOfPts + i];
		}

		
		try {
			tempResult[0] = inst.lambdaZCalculationForCA(xForStrip, yForStrip,
					0, 0, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double[] X1 = new double[X.length - noOfPts];
		double[] Y1 = new double[X.length - noOfPts];
		double[] C1 = new double[X.length - noOfPts];

		for (int i = 0; i < Y.length - noOfPts; i++) {
			X1[i] = X[i];
			Y1[i] = Math.abs((tempResult[0][0]
					* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y[i]));
			C1[i] = Y[i];
		}

		
		try {
			tempResult[1] = inst.lambdaZCalculationForCA(X1, Y1, 0, 0, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int k = 0; k < 100; k++) {

			double[] Y2 = new double[yForStrip.length];
			double[] Y3 = new double[Y1.length];
			for (int j = 0; j < yForStrip.length; j++) {
				// xForStrip [j] = X[X.length-1 -j];
				Y2[j] = Math.abs((yForStrip[j] - tempResult[1][0]
						* Math.exp(xForStrip[j] * (-1) * tempResult[1][1])));

			}

			

			try {
				tempResult[0] = inst.lambdaZCalculationForCA(xForStrip, Y2, 0,
						0, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i < Y.length - noOfPts; i++) {

				Y3[i] = Math.abs((Y1[i] - tempResult[0][0]
						* Math.exp(X[i] * (-1) * tempResult[0][1])));
			}

			

			try {
				tempResult[1] = inst.lambdaZCalculationForCA(X1, Y3, 0, 0, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			finalParam[2] = Math.abs(tempResult[0][1]);
			finalParam[1] = Math.abs(tempResult[1][1]);

			finalParam[0] = dose / Cmax;

			double sumPar = 0;
			for (int i = 0; i < noParam; i++) {
				double ratio = Math.abs((finalParam[i] - param[i]) / param[i]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar / noParam;
			for (int i = 0; i < noParam; i++) {
				param[i] = finalParam[i];

			}

			if (check <= PkPdInfo.createPKPDInstance().convergence)
				break;

		}

		return param;
	}

	private double[] modelAE4(double[] X, double[] Y, int noParam, double dose) {

		double[] param = new double[noParam];
		double[] finalParam = new double[noParam];
		CurveStripper inst = new CurveStripper();

		double Cmax = maxVal(Y);

		int noOfExp = 2;
		int tempNoOfPts = (int) (X.length / (3 + noOfExp)) + 1;
		int noOfPts = Math.max(2, tempNoOfPts);

		double[][] tempResult = new double[2][2];

		double[] xForStrip = new double[noOfPts];
		double[] yForStrip = new double[noOfPts];
		double[] C = new double[noOfPts];

		for (int i = 0; i < yForStrip.length; i++) {
			xForStrip[i] = X[X.length - noOfPts + i];
			yForStrip[i] = Y[X.length - noOfPts + i];
			C[i] = Y[X.length - noOfPts + i];
		}

		try {
			tempResult[0] = inst.lambdaZCalculationForCA(xForStrip, yForStrip,
					0, 0, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double[] X1 = new double[X.length - noOfPts];
		double[] Y1 = new double[X.length - noOfPts];
		double[] C1 = new double[X.length - noOfPts];

		for (int i = 0; i < Y.length - noOfPts; i++) {
			X1[i] = X[i];
			Y1[i] = Math.abs((tempResult[0][0]
					* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y[i]));
			C1[i] = Y[i];
		}

		try {
			tempResult[1] = inst.lambdaZCalculationForCA(X1, Y1, 0, 0, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int k = 0; k < 100; k++) {
			double[] Y2 = new double[yForStrip.length];
			double[] Y3 = new double[Y1.length];
			for (int j = 0; j < yForStrip.length; j++) {
				// xForStrip [j] = X[X.length-1 -j];
				Y2[j] = Math.abs((yForStrip[j] - tempResult[1][0]
						* Math.exp(xForStrip[j] * (-1) * tempResult[1][1])));

			}

			try {
				tempResult[0] = inst.lambdaZCalculationForCA(xForStrip, Y2, 0,
						0, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i < Y.length - noOfPts; i++) {

				Y3[i] = Math.abs((Y1[i] - tempResult[0][0]
						* Math.exp(X[i] * (-1) * tempResult[0][1])));
			}

			try {
				tempResult[1] = inst.lambdaZCalculationForCA(X1, Y3, 0, 0, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			finalParam[2] = Math.abs(tempResult[0][1]);
			finalParam[1] = Math.abs(tempResult[1][1]);

			finalParam[0] = dose / Cmax;

			
			finalParam[3] = X[firstNonZero(Y)];
			
			finalParam[3] = Math.min(X[firstNonZero(Y)], finalParam[3]);
			double sumPar = 0;
			for (int i = 0; i < noParam; i++) {
				double ratio = Math.abs((finalParam[i] - param[i]) / param[i]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar / noParam;
			for (int i = 0; i < noParam; i++) {
				param[i] = finalParam[i];

			}

			if (check <= PkPdInfo.createPKPDInstance().convergence)
				break;

		}

		return param;
	}

	private double[] modelAE5(double[] X, double[] Y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[2];
		double Cmax = maxVal(Y);

		CurveStripper inst = new CurveStripper();
		try {
			result = inst.lambdaZCalculationForCA(X, Y, 0, 0, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = csInstance.parameterCalculationUsingCurveStripping(X, Y, 2);
		param[1] = Math.abs(result[1]);

		param[0] = dose / Cmax;
		return param;
	}

	private double[] modelAE7(double[] X, double[] Y, int noParam, double dose)
			throws Exception {
		double[] param = new double[noParam];
		double[] temp = new double[noParam];
		double[] finalParam = new double[noParam];

		CurveStripper inst = new CurveStripper();

		double Cmax = maxVal(Y);

		int noOfExp = 2;
		int tempNoOfPts = (int) (X.length / (3 + noOfExp)) + 1;
		int noOfPts = Math.max(2, tempNoOfPts);

		double[][] tempResult = new double[2][2];

		double[] xForStrip = new double[noOfPts];
		double[] yForStrip = new double[noOfPts];

		for (int i = 0; i < yForStrip.length; i++) {
			xForStrip[i] = X[X.length - noOfPts + i];
			yForStrip[i] = Y[X.length - noOfPts + i];
		}

		tempResult[0] = inst.lambdaZCalculationForCA(xForStrip, yForStrip, 0,
				0, 1);

		/*
		 * tempResult[0] = csInstance.parameterCalculationUsingCurveStripping(
		 * xForStrip, yForStrip, 2);
		 */
		double[] X1 = new double[X.length - noOfPts];
		double[] Y1 = new double[X.length - noOfPts];

		for (int i = 0; i < Y.length - noOfPts; i++) {
			X1[i] = X[i];
			Y1[i] = Math.abs((tempResult[0][0]
					* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y[i]));
		}

		tempResult[1] = inst.lambdaZCalculationForCA(X1, Y1, 0, 0, 1);
		/*
		 * tempResult[1] =
		 * csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);
		 */

		for (int k = 0; k < 100; k++) {
			double[] Y2 = new double[yForStrip.length];
			double[] Y3 = new double[Y1.length];

			for (int j = 0; j < yForStrip.length; j++) {
				// xForStrip [j] = X[X.length-1 -j];
				Y2[j] = Math
						.abs((tempResult[1][0]
								* Math.exp(xForStrip[j] * (-1)
										* tempResult[1][1]) - yForStrip[j]));

			}

			tempResult[0] = inst
					.lambdaZCalculationForCA(xForStrip, Y2, 0, 0, 1);

			/*
			 * tempResult[0] =
			 * csInstance.parameterCalculationUsingCurveStripping( xForStrip,
			 * yForStrip, 2);
			 */
			for (int i = 0; i < Y.length - noOfPts; i++) {

				Y3[i] = Math.abs((tempResult[0][0]
						* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y1[i]));
			}

			tempResult[1] = inst.lambdaZCalculationForCA(X1, Y3, 0, 0, 1);
			/*
			 * tempResult[1] =
			 * csInstance.parameterCalculationUsingCurveStripping( X1, Y1, 2);
			 */

			temp[1] = tempResult[0][0];
			temp[3] = tempResult[0][1];
			temp[0] = tempResult[1][0];
			temp[2] = tempResult[1][1];

			finalParam[0] = Math.abs(dose / (temp[0] + temp[1]));
			finalParam[3] = Math.abs((temp[0] * temp[3] + temp[1] * temp[2])
					/ (temp[0] + temp[1]));
			finalParam[1] = Math.abs(temp[2] * temp[3] / finalParam[3]);
			finalParam[2] = Math.abs(temp[2] + temp[3] - finalParam[3]
					- finalParam[1]);

			double sumPar = 0;
			for (int i = 0; i < noParam; i++) {
				double ratio = Math.abs((finalParam[i] - param[i]) / param[i]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar / noParam;
			for (int i = 0; i < noParam; i++) {
				param[i] = finalParam[i];

			}

			if (check <= PkPdInfo.createPKPDInstance().convergence)
				break;

		}

		return param;
	}

	private double[] modelAE8(double[] X, double[] Y, int noParam, double dose)
			throws Exception {
		double[] param = new double[noParam];

		double[] temp = new double[noParam];
		double[] finalParam = new double[noParam];

		double Cmax = maxVal(Y);

		int noOfExp = 2;
		int tempNoOfPts = (int) (X.length / (3 + noOfExp)) + 1;
		int noOfPts = Math.max(2, tempNoOfPts);
		double[][] tempResult = new double[2][2];

		double[] xForStrip = new double[noOfPts];
		double[] yForStrip = new double[noOfPts];

		for (int i = 0; i < yForStrip.length; i++) {
			xForStrip[i] = X[X.length - noOfPts + i];
			yForStrip[i] = Y[X.length - noOfPts + i];
		}

		tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
				yForStrip, 0, 0, 1);
		/*
		 * tempResult[0] = csInstance.parameterCalculationUsingCurveStripping(
		 * xForStrip, yForStrip, 2);
		 */

		double[] X1 = new double[X.length - noOfPts];
		double[] Y1 = new double[X.length - noOfPts];

		for (int i = 0; i < Y.length - noOfPts; i++) {
			X1[i] = X[i];
			Y1[i] = Math.abs((tempResult[0][0]
					* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y[i]));
		}

		tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y1, 0, 0, 1);
		/*
		 * tempResult[1] =
		 * csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);
		 */

		for (int k = 0; k < 100; k++) {
			double[] Y2 = new double[yForStrip.length];
			double[] Y3 = new double[Y1.length];

			for (int j = 0; j < yForStrip.length; j++) {
				// xForStrip [j] = X[X.length-1 -j];
				Y2[j] = Math
						.abs((tempResult[1][0]
								* Math.exp(xForStrip[j] * (-1)
										* tempResult[1][1]) - yForStrip[j]));

			}

			tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
					Y2, 0, 0, 1);

			/*
			 * tempResult[0] =
			 * csInstance.parameterCalculationUsingCurveStripping( xForStrip,
			 * yForStrip, 2);
			 */

			for (int i = 0; i < Y.length - noOfPts; i++) {

				Y3[i] = Math.abs((tempResult[0][0]
						* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y1[i]));
			}

			tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y3, 0,
					0, 1);
			/*
			 * tempResult[1] =
			 * csInstance.parameterCalculationUsingCurveStripping( X1, Y1, 2);
			 */

			finalParam[1] = Math.abs(tempResult[0][0]);
			finalParam[3] = Math.abs(tempResult[0][1]);
			finalParam[0] = Math.abs(tempResult[1][0]);
			finalParam[2] = Math.abs(tempResult[1][1]);

			double sumPar = 0;
			for (int i = 0; i < noParam; i++) {
				double ratio = Math.abs((finalParam[i] - param[i]) / param[i]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar / noParam;
			for (int i = 0; i < noParam; i++) {
				param[i] = finalParam[i];

			}

			if (check <= PkPdInfo.createPKPDInstance().convergence)
				break;

		}
		param[1] = Math.abs(tempResult[0][0]);
		param[3] = Math.abs(tempResult[0][1]);
		param[0] = Math.abs(tempResult[1][0]);
		param[2] = Math.abs(tempResult[1][1]);

		return param;
	}

	private double[] modelAE9(double[] X, double[] Y, int noParam, double dose,
			double lengthOfInfusion) throws Exception {
		double[] param = new double[noParam];
		double[] temp = new double[noParam];
		double[] finalParam = new double[noParam];

		double Cmax = maxVal(Y);

		int noOfExp = 2;
		int tempNoOfPts = (int) (X.length / (3 + noOfExp)) + 1;
		int noOfPts = Math.max(2, tempNoOfPts);
		double[][] tempResult = new double[2][2];

		double[] xForStrip = new double[noOfPts];
		double[] yForStrip = new double[noOfPts];

		for (int i = 0; i < yForStrip.length; i++) {
			xForStrip[i] = X[X.length - noOfPts + i];
			yForStrip[i] = Y[X.length - noOfPts + i];
		}

		tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
				yForStrip, 0, 0, 1);
		/*
		 * tempResult[0] = csInstance.parameterCalculationUsingCurveStripping(
		 * xForStrip, yForStrip, 2);
		 */

		double[] X1 = new double[X.length - noOfPts];
		double[] Y1 = new double[X.length - noOfPts];

		for (int i = 0; i < Y.length - noOfPts; i++) {
			X1[i] = X[i];
			Y1[i] = Math.abs((tempResult[0][0]
					* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y[i]));
		}

		tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y1, 0, 0, 1);
		/*
		 * tempResult[1] =
		 * csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);
		 */

		for (int k = 0; k < 100; k++) {
			double[] Y2 = new double[yForStrip.length];
			double[] Y3 = new double[Y1.length];
			for (int j = 0; j < yForStrip.length; j++) {
				// xForStrip [j] = X[X.length-1 -j];
				Y2[j] = Math
						.abs((tempResult[1][0]
								* Math.exp(xForStrip[j] * (-1)
										* tempResult[1][1]) - yForStrip[j]));

			}

			tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
					Y2, 0, 0, 1);
			/*
			 * tempResult[0] =
			 * csInstance.parameterCalculationUsingCurveStripping( xForStrip,
			 * yForStrip, 2);
			 */

			for (int i = 0; i < Y.length - noOfPts; i++) {

				Y3[i] = Math.abs((tempResult[0][0]
						* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y1[i]));
			}

			tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y3, 0,
					0, 1);
			/*
			 * tempResult[1] =
			 * csInstance.parameterCalculationUsingCurveStripping( X1, Y1, 2);
			 */

			temp[1] = tempResult[0][0];
			temp[3] = tempResult[0][1];

			temp[0] = tempResult[1][0];
			temp[2] = tempResult[1][1];

			finalParam[0] = Math.abs(dose / (temp[0] + temp[1]));

			finalParam[3] = Math.abs((temp[0] * temp[3] + temp[1] * temp[2])
					/ (temp[0] + temp[1]));
			finalParam[1] = Math.abs((temp[2] * temp[3]) / finalParam[3]);
			finalParam[2] = Math.abs(temp[2] + temp[3] - finalParam[1]
					- finalParam[3]);

			double sumPar = 0;
			for (int i = 0; i < noParam; i++) {
				double ratio = Math.abs((finalParam[i] - param[i]) / param[i]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar / noParam;
			for (int i = 0; i < noParam; i++) {
				param[i] = finalParam[i];

			}

			if (check <= PkPdInfo.createPKPDInstance().convergence)
				break;

		}
		temp[1] = tempResult[0][0];
		temp[3] = tempResult[0][1];

		temp[0] = tempResult[1][0];
		temp[2] = tempResult[1][1];

		param[0] = Math.abs(dose / (temp[0] + temp[1]));

		param[3] = Math.abs((temp[0] * temp[3] + temp[1] * temp[2])
				/ (temp[0] + temp[1]));
		param[1] = Math.abs((temp[2] * temp[3]) / param[3]);
		param[2] = Math.abs(temp[2] + temp[3] - param[1] - param[3]);

		return param;
	}

	private double[] modelAE10(double[] X, double[] Y, int noParam, double dose,
			double lengthOfInfusion) throws Exception {
		double[] param = new double[noParam];
		double[] temp = new double[noParam];
		double[] finalParam = new double[noParam];

		double Cmax = maxVal(Y);

		int noOfExp = 2;
		int tempNoOfPts = (int) (X.length / (3 + noOfExp)) + 1;
		int noOfPts = Math.max(2, tempNoOfPts);
		double[][] tempResult = new double[2][2];

		double[] xForStrip = new double[noOfPts];
		double[] yForStrip = new double[noOfPts];

		for (int i = 0; i < yForStrip.length; i++) {
			xForStrip[i] = X[X.length - noOfPts + i];
			yForStrip[i] = Y[X.length - noOfPts + i];
		}

		tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
				yForStrip, 0, 0, 1);
		/*
		 * tempResult[0] = csInstance.parameterCalculationUsingCurveStripping(
		 * xForStrip, yForStrip, 2);
		 */

		double[] X1 = new double[X.length - noOfPts];
		double[] Y1 = new double[X.length - noOfPts];

		for (int i = 0; i < Y.length - noOfPts; i++) {
			X1[i] = X[i];
			Y1[i] = Math.abs((tempResult[0][0]
					* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y[i]));
		}

		tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y1, 0, 0, 1);
		/*
		 * tempResult[1] =
		 * csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);
		 */

		for (int k = 0; k < 100; k++) {
			double[] Y2 = new double[yForStrip.length];
			double[] Y3 = new double[Y1.length];
			for (int j = 0; j < yForStrip.length; j++) {
				// xForStrip [j] = X[X.length-1 -j];
				Y2[j] = Math
						.abs((tempResult[1][0]
								* Math.exp(xForStrip[j] * (-1)
										* tempResult[1][1]) - yForStrip[j]));

			}

			tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
					Y2, 0, 0, 1);
			/*
			 * tempResult[0] =
			 * csInstance.parameterCalculationUsingCurveStripping( xForStrip,
			 * yForStrip, 2);
			 */

			for (int i = 0; i < Y.length - noOfPts; i++) {

				Y3[i] = Math.abs((tempResult[0][0]
						* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y1[i]));
			}

			tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y3, 0,
					0, 1);
			/*
			 * tempResult[1] =
			 * csInstance.parameterCalculationUsingCurveStripping( X1, Y1, 2);
			 */
			finalParam[3] = Math.abs(tempResult[0][1]);
			temp[1] = tempResult[0][0];
			temp[3] = tempResult[0][1];

			finalParam[2] = Math.abs(tempResult[1][1]);
			temp[0] = tempResult[1][0];
			temp[2] = tempResult[1][1];

			finalParam[0] = Math.abs(dose / (temp[0] + temp[1]));
			finalParam[1] = Math.abs((temp[0] * temp[3] + temp[1] * temp[2])
					/ (temp[0] + temp[1]));

			double sumPar = 0;
			for (int i = 0; i < noParam; i++) {
				double ratio = Math.abs((finalParam[i] - param[i]) / param[i]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar / noParam;
			for (int i = 0; i < noParam; i++) {
				param[i] = finalParam[i];

			}

			if (check <= PkPdInfo.createPKPDInstance().convergence)
				break;

		}
		param[3] = Math.abs(tempResult[0][1]);
		temp[1] = tempResult[0][0];
		temp[3] = tempResult[0][1];

		param[2] = Math.abs(tempResult[1][1]);
		temp[0] = tempResult[1][0];
		temp[2] = tempResult[1][1];

		param[0] = Math.abs(dose / (temp[0] + temp[1]));
		param[1] = Math.abs((temp[0] * temp[3] + temp[1] * temp[2])
				/ (temp[0] + temp[1]));

		return param;
	}

	private double[] modelAE11(double[] X, double[] Y, int noParam, double dose)
			throws Exception {
		double[] param = new double[noParam];
		double[] finalParam = new double[noParam];

		int noOfExp = 3;
		int tempNoOfPts = (int) (X.length / (3 + noOfExp)) + 1;
		int noOfPts = Math.max(2, tempNoOfPts);
		double[][] tempResult = new double[noOfExp][2];

		double[] xForStrip = new double[noOfPts];
		double[] yForStrip = new double[noOfPts];

		for (int i = 0; i < yForStrip.length; i++) {
			xForStrip[i] = X[X.length - noOfPts + i];
			yForStrip[i] = Y[X.length - noOfPts + i];
		}

		tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
				yForStrip, 0, 0, 1);
		/*
		 * tempResult[0] = csInstance.parameterCalculationUsingCurveStripping(
		 * xForStrip, yForStrip, 2);
		 */

		double[] X1 = new double[noOfPts];
		double[] Y1 = new double[noOfPts];

		for (int i = 0; i < noOfPts; i++) {
			X1[i] = X[X.length - 2 * noOfPts + i];
			Y1[i] = Math.abs((tempResult[0][0]
					* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y[X.length - 2
					* noOfPts + i]));
		}

		tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y1, 0, 0, 1);

		/*
		 * tempResult[1] =
		 * csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);
		 */

		double[] X2 = new double[X.length - 2 * noOfPts];
		double[] Y2 = new double[X.length - 2 * noOfPts];

		for (int i = 0; i < X.length - 2 * noOfPts; i++) {
			X2[i] = X[i];
			Y2[i] = Math.abs((tempResult[0][0]
					* Math.exp(X[i] * (-1) * tempResult[0][1])
					+ (tempResult[1][0] * Math.exp(X[i] * (-1)
							* tempResult[1][1])) - Y[i]));
		}

		tempResult[2] = lambdaZCalInst.lambdaZCalculationForCA(X2, Y2, 0, 0, 1);
		/*
		 * tempResult[2] =
		 * csInstance.parameterCalculationUsingCurveStripping(X2, Y2, 2);
		 */

		for (int k = 0; k < 100; k++) {
			double[] Y3 = new double[yForStrip.length];
			double[] Y4 = new double[Y1.length];
			double[] Y5 = new double[Y2.length];

			for (int j = 0; j < yForStrip.length; j++) {
				// xForStrip [j] = X[X.length-1 -j];
				Y3[j] = Math.abs(tempResult[1][0]
						* Math.exp(xForStrip[j] * (-1) * tempResult[1][1])
						+ tempResult[2][0]
						* Math.exp(X1[j] * (-1) * tempResult[2][1])
						- yForStrip[j]);
			}

			tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
					Y3, 0, 0, 1);

			/*
			 * tempResult[0] =
			 * csInstance.parameterCalculationUsingCurveStripping( xForStrip,
			 * yForStrip, 2);
			 */

			for (int j = 0; j < X1.length; j++) {
				// xForStrip [j] = X[X.length-1 -j];
				Y4[j] = Math.abs(tempResult[2][0]
						* Math.exp(X1[j] * (-1) * tempResult[2][1])
						+ tempResult[0][0]
						* Math.exp(X1[j] * (-1) * tempResult[0][1]) - Y1[j]);
			}

			tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y4, 0,
					0, 1);
			/*
			 * tempResult[1] =
			 * csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);
			 */

			for (int i = 0; i < X.length - 2 * noOfPts; i++) {

				Y5[i] = Math.abs((tempResult[0][0]
						* Math.exp(X2[i] * (-1) * tempResult[0][1])
						+ (tempResult[1][0] * Math.exp(X2[i] * (-1)
								* tempResult[1][1])) - Y2[i]));
			}

			tempResult[2] = lambdaZCalInst.lambdaZCalculationForCA(X2, Y5, 0,
					0, 1);
			/*
			 * tempResult[2] =
			 * csInstance.parameterCalculationUsingCurveStripping(X2, Y2, 2);
			 */

			finalParam[1] = Math.abs(tempResult[1][1]);
			finalParam[2] = Math.abs(tempResult[0][1]);

			finalParam[0] = Math.abs(dose
					/ (tempResult[0][0] + tempResult[1][0]));
			finalParam[4] = Math
					.abs((tempResult[1][0] * tempResult[0][1] + tempResult[0][0]
							* tempResult[1][1])
							/ (tempResult[0][0] + tempResult[1][0]));
			finalParam[3] = Math.abs(tempResult[1][1] + tempResult[0][1]
					- finalParam[4] - finalParam[2]);

			double sumPar = 0;
			for (int i = 0; i < noParam; i++) {
				double ratio = Math.abs((finalParam[i] - param[i]) / param[i]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar / noParam;
			for (int i = 0; i < noParam; i++) {
				param[i] = finalParam[i];

			}

			if (check <= PkPdInfo.createPKPDInstance().convergence)
				break;
		}

		return param;
	}

	private double[] modelAE12(double[] X, double[] Y, int noParam, double dose)
			throws Exception {
		double[] param = new double[noParam];
		double[] finalParam = new double[noParam];

		double[] result = new double[2];

		double[] temp = new double[6];

		int noOfExp = 3;
		int tempNoOfPts = (int) (X.length / (3 + noOfExp)) + 1;
		int noOfPts = Math.max(2, tempNoOfPts);
		double[][] tempResult = new double[noOfExp][2];

		double[] xForStrip = new double[noOfPts];
		double[] yForStrip = new double[noOfPts];

		for (int i = 0; i < yForStrip.length; i++) {
			xForStrip[i] = X[X.length - noOfPts + i];
			yForStrip[i] = Y[X.length - noOfPts + i];
		}

		tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
				yForStrip, 0, 0, 1);
		/*
		 * tempResult[0] = csInstance.parameterCalculationUsingCurveStripping(
		 * xForStrip, yForStrip, 2);
		 */

		double[] X1 = new double[noOfPts];
		double[] Y1 = new double[noOfPts];

		for (int i = 0; i < noOfPts; i++) {
			X1[i] = X[X.length - 2 * noOfPts + i];
			Y1[i] = Math.abs((tempResult[0][0]
					* Math.exp(X[i] * (-1) * tempResult[0][1]) - Y[X.length - 2
					* noOfPts + i]));
		}

		tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y1, 0, 0, 1);
		/*
		 * tempResult[1] =
		 * csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);
		 */

		double[] X2 = new double[X.length - 2 * noOfPts];
		double[] Y2 = new double[X.length - 2 * noOfPts];

		for (int i = 0; i < X.length - 2 * noOfPts; i++) {
			X2[i] = X[i];
			Y2[i] = Math.abs((tempResult[0][0]
					* Math.exp(X[i] * (-1) * tempResult[0][1])
					+ (tempResult[1][0] * Math.exp(X[i] * (-1)
							* tempResult[1][1])) - Y[i]));
		}

		tempResult[2] = lambdaZCalInst.lambdaZCalculationForCA(X2, Y2, 0, 0, 1);
		/*
		 * tempResult[2] =
		 * csInstance.parameterCalculationUsingCurveStripping(X2, Y2, 2);
		 */

		for (int i = 0; i < 100; i++) {
			double[] Y3 = new double[yForStrip.length];
			double[] Y4 = new double[Y1.length];
			double[] Y5 = new double[Y2.length];

			for (int j = 0; j < yForStrip.length; j++) {
				// xForStrip [j] = X[X.length-1 -j];
				Y3[j] = Math.abs(tempResult[1][0]
						* Math.exp(xForStrip[j] * (-1) * tempResult[1][1])
						+ tempResult[2][0]
						* Math.exp(X1[j] * (-1) * tempResult[2][1])
						- yForStrip[j]);
			}

			tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
					Y3, 0, 0, 1);
			for (int j = 0; j < X1.length; j++) {
				// xForStrip [j] = X[X.length-1 -j];
				Y4[j] = Math.abs(tempResult[2][0]
						* Math.exp(X1[j] * (-1) * tempResult[2][1])
						+ tempResult[0][0]
						* Math.exp(X1[j] * (-1) * tempResult[0][1]) - Y1[j]);
			}

			tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y4, 0,
					0, 1);
			/*
			 * tempResult[1] =
			 * csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);
			 */

			for (int j = 0; j < X.length - 2 * noOfPts; i++) {

				Y5[j] = Math.abs((tempResult[0][0]
						* Math.exp(X2[j] * (-1) * tempResult[0][1])
						+ (tempResult[1][0] * Math.exp(X2[j] * (-1)
								* tempResult[1][1])) - Y2[i]));
			}

			tempResult[2] = lambdaZCalInst.lambdaZCalculationForCA(X2, Y5, 0,
					0, 1);

			double Cmax = maxVal(Y);

			finalParam[2] = Math.abs(tempResult[0][1]);
			temp[0] = tempResult[0][0];
			temp[1] = tempResult[0][1];

			temp[2] = tempResult[1][0];
			temp[3] = tempResult[1][1];

			finalParam[1] = Math.abs(tempResult[2][1]);

			temp[4] = tempResult[2][0];
			temp[5] = tempResult[2][1];

			finalParam[0] = dose / Cmax;
			finalParam[4] = Math.abs((temp[4] * temp[1] + temp[0] * temp[5]));
			finalParam[3] = Math.abs(temp[5] + temp[1] - finalParam[4]
					- finalParam[2]);
			finalParam[5] = X[firstNonZero(Y)];

			double sumPar = 0;
			for (int j = 0; j < noParam; i++) {
				double ratio = Math.abs((finalParam[j] - param[j]) / param[j]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar / noParam;
			for (int j = 0; j < noParam; i++) {
				param[j] = finalParam[j];

			}

			if (check <= PkPdInfo.createPKPDInstance().convergence)
				break;

		}

		return param;
	}

	private double[] modelAE13(double[] X, double[] Y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[6];

		result = csInstance.parameterCalculationUsingCurveStripping(X, Y, 6);

		param[4] = Math.abs(result[1]);
		param[1] = Math.abs(result[0]);
		param[2] = Math.abs(result[3]);
		param[0] = Math.abs(result[2]);
		param[3] = Math.abs(result[5]);

		return param;
	}

	private double[] modelAE14(double[] X, double[] Y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[6];

		result = csInstance.parameterCalculationUsingCurveStripping(X, Y, 6);

		param[4] = Math.abs(result[1]);
		param[1] = Math.abs(result[0]);
		param[2] = Math.abs(result[3]);
		param[0] = Math.abs(result[2]);
		param[3] = Math.abs(result[5]);

		param[5] = X[firstNonZero(Y)];

		return param;
	}

	private double[] modelAE18(double[] X, double[] Y, int noParam, double dose)
			throws Exception {
		double[] param = new double[noParam];
		double[] finalParam = new double[noParam];
		
		int noOfExp = 3;
		int tempNoOfPts = (int) (X.length / (3 + noOfExp)) + 1;
		int noOfPts = Math.max(2, tempNoOfPts);
		double[][] tempResult = new double[noOfExp][2];

		double[] xForStrip = new double[noOfPts];
		double[] yForStrip = new double[noOfPts];

		for (int i = 0; i < yForStrip.length; i++) {
			xForStrip[i] = X[X.length - noOfPts + i];
			yForStrip[i] = Y[X.length - noOfPts + i];
		}

		tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
				yForStrip, 0, 0, 1);
		

		double[] X1 = new double[noOfPts];
		double[] Y1 = new double[noOfPts];

		for (int i = 0; i < noOfPts; i++) {
			X1[i] = X[X.length - 2 * noOfPts + i];
			Y1[i] = Math.abs((tempResult[0][0]
					* Math.exp(X1[i] * (-1) * tempResult[0][1]) - Y[X.length - 2
					* noOfPts + i]));
		}

		tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y1, 0, 0, 1);
		

		double[] X2 = new double[X.length - 2 * noOfPts];
		double[] Y2 = new double[X.length - 2 * noOfPts];

		for (int i = 0; i < X.length - 2 * noOfPts; i++) {
			X2[i] = X[i];
			Y2[i] = Math.abs(((tempResult[1][0] * Math.exp(X[i] * (-1)
							* tempResult[1][1])) - Y[i]));
		}

		tempResult[2] = lambdaZCalInst.lambdaZCalculationForCA(X2, Y2, 0, 0, 1);
		

		for (int i = 0; i < 100; i++) {
			double[] Y3 = new double[yForStrip.length];
			double[] Y4 = new double[Y1.length];
			double[] Y5 = new double[Y2.length];
			for (int j = 0; j < yForStrip.length; j++) {
				Y3[j] = Math.abs(tempResult[1][0]
						* Math.exp(xForStrip[j] * (-1) * tempResult[1][1])
						+ tempResult[2][0]
						* Math.exp(xForStrip[j] * (-1) * tempResult[2][1])
						- yForStrip[j]);
			}
			tempResult[0] = lambdaZCalInst.lambdaZCalculationForCA(xForStrip,
					Y3, 0, 0, 1);
			
			for (int j = 0; j < X1.length; j++) {
				Y4[j] = Math.abs(tempResult[2][0]
						* Math.exp(X1[j] * (-1) * tempResult[2][1])
						+ tempResult[0][0]
						* Math.exp(X1[j] * (-1) * tempResult[0][1]) - Y1[j]);
			}

			tempResult[1] = lambdaZCalInst.lambdaZCalculationForCA(X1, Y4, 0,
					0, 1);
			

			for (int j = 0; j < X.length - 2 * noOfPts; j++) {

				Y5[j] = Math.abs((tempResult[0][0]
						* Math.exp(X2[j] * (-1) * tempResult[0][1])
						+ (tempResult[1][0] * Math.exp(X2[j] * (-1)
								* tempResult[1][1])) - Y2[j]));
			}
			tempResult[2] = lambdaZCalInst.lambdaZCalculationForCA(X2, Y5, 0,
					0, 1);

			finalParam[0] = Math.abs(tempResult[1][0]);
			finalParam[1] = Math.abs(tempResult[0][0]);
			finalParam[2] = Math.abs(tempResult[2][0]);
			finalParam[3] = Math.abs(tempResult[1][1]);
			finalParam[4] = Math.abs(tempResult[0][1]);
			finalParam[5] = Math.abs(tempResult[2][1]);

			double sumPar = 0;
			for (int j = 0; j < noParam; j++) {
				double ratio = Math.abs((finalParam[j] - param[j]) / param[j]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar / noParam;
			for (int j = 0; j < noParam; j++) {
				param[j] = finalParam[j];

			}

			if (check <= PkPdInfo.createPKPDInstance().convergence)
				break;

		}

		return param;
	}

	private double maxVal(double[] mat) {
		double max = mat[0];
		for (int i = 1; i < mat.length; i++) {
			if (mat[i] > max) {
				max = mat[i];
			}
		}
		return max;
	}

	private int findVal(double[] mat, double val) {

		for (int i = 0; i < mat.length; i++) {
			if (mat[i] == val)
				return i;
		}
		return -1;
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

}
