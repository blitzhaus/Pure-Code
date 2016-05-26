package Model;

import Common.TestException;
import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class PkCurveStripper1 {

	CStrip cStripInst;

	public double[] parameterByModel(int modelNumber, double[][] time,
			double[][] conc, int noParam, double dose, double infusionDose,
			double lengthOfInfusion, boolean ifAlgebraicModel,
			boolean ifClearanceParam) {

		cStripInst = new CStrip();

		double[] param = new double[noParam];

		double[] X = new double[time.length];
		double[] Y = new double[conc.length];

		for (int i = 0; i < time.length; i++) {
			X[i] = time[i][0];
			Y[i] = conc[i][0];
		}

		if (ifClearanceParam == true) {

			ClearanceModelCurveStripper clearanceCSInst = new ClearanceModelCurveStripper();
			param = clearanceCSInst.chooseClearanceModels(modelNumber, X, Y,
					noParam, dose, infusionDose, lengthOfInfusion);

		} else {
			try {
				param = chooseLibraryModel(modelNumber, X, Y, noParam, dose,
						infusionDose, lengthOfInfusion, ifAlgebraicModel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		paramValExceptionChecking(param);

		for (int i = 0; i < param.length; i++) {
			param[i] = Math.abs(param[i]);
		}
		return param;
	}

	void paramValExceptionChecking(double[] param) {
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

	public double[] chooseLibraryModel(int modelNumber, double[] X1,
			double[] Y1, int noParam, double dose, double infusionDose,
			double lengthOfInfusion, boolean ifAlgebraicModel) throws Exception {
		double[] param = new double[noParam];

		/*
		 * int noOfNonZeroPts = 0;
		 * 
		 * for (int i = 0; i < Y.length; i++) { if (Y[i] != 0) noOfNonZeroPts++;
		 * }
		 * 
		 * double[] xTemp = new double[noOfNonZeroPts]; double[] yTemp = new
		 * double[noOfNonZeroPts];
		 * 
		 * int count = 0; for (int i = 0; i < Y.length; i++) {
		 * 
		 * if (Y[i] != 0) { xTemp[count] = X[i]; yTemp[count] = Y[i]; count++; }
		 * }
		 */

		int lastNonZeroIndex = 0;

		for (int i = 0; i < Y1.length; i++) {
			if (Y1[Y1.length - 1 - i] != 0) {
				lastNonZeroIndex = Y1.length - 1 - i;
				break;
			}
		}

		double[] X = new double[lastNonZeroIndex + 1];
		double[] Y = new double[lastNonZeroIndex + 1];

		for (int i = 0; i < Y.length; i++) {
			X[i] = X1[i];
			Y[i] = Y1[i];
		}

		if (ifAlgebraicModel == true) {

			if (modelNumber == 1)
				param = modelAE1(X, Y, noParam, dose);
			else if (modelNumber == 2)
				param = modelAE2(X, Y, noParam, dose, lengthOfInfusion);
			else if (modelNumber == 3)
				param = modelAE3(X, Y, noParam, dose);
			else if (modelNumber == 4)
				param = modelAE4(X, Y, noParam, dose);
			else if (modelNumber == 5)
				param = modelAE5(X, Y, noParam, dose);
			else if (modelNumber == 6)
				param = modelAE6(X, Y, noParam, dose);
			else if (modelNumber == 7)
				param = modelAE7(X, Y, noParam, dose);
			else if (modelNumber == 8)
				param = modelAE8(X, Y, noParam, dose);
			else if (modelNumber == 9)
				param = modelAE9(X, Y, noParam, dose, lengthOfInfusion);
			else if (modelNumber == 10)
				param = modelAE10(X, Y, noParam, dose, lengthOfInfusion);
			else if (modelNumber == 11)
				param = modelAE11(X, Y, noParam, dose);
			else if (modelNumber == 12)
				param = modelAE12(X, Y, noParam, dose);
			else if (modelNumber == 13)
				param = modelAE13(X, Y, noParam, dose);
			else if (modelNumber == 14)
				param = modelAE14(X, Y, noParam, dose);
			else if (modelNumber == 15)
				param = modelAE15(X, Y, noParam, infusionDose, lengthOfInfusion);
			else if (modelNumber == 16)
				param = modelAE16(X, Y, noParam, infusionDose);
			else if (modelNumber == 17)
				param = modelAE17(X, Y, noParam, dose);
			else if (modelNumber == 18)
				param = modelAE18(X, Y, noParam, dose);
			else if (modelNumber == 19)
				param = modelAE19(X, Y, noParam, dose);
		} else {

			DeModelCurveStripper deModelInitValCalInst = new DeModelCurveStripper();

			param = deModelInitValCalInst.chooseDEModels(modelNumber, X, Y, noParam,
					dose, infusionDose, lengthOfInfusion);

		}

		return param;
	}

	private double[] modelAE19(double[] X, double[] Y, int noParam, double dose)
			throws Exception {

		double[] param = new double[noParam];

		double[] temp = cStripInst.curveStrippingThreeExp(X, Y);

		param[0] = dose / (Math.abs(temp[0] + temp[1] + temp[2]));

		param[3] = Math.abs(temp[3]);
		param[4] = Math.abs(temp[4]);
		param[5] = Math.abs(temp[5]);

		double cmax = maxVal(Y);
		double e2 = param[5] * temp[1] + param[5] * temp[0] + param[4]
				* temp[2] + param[4] * temp[0] + param[3] * temp[2] + param[3]
				* temp[1];
		e2 = -dose * e2 / cmax;
		double e3 = dose
				* (param[3] * param[5] * temp[1] + param[5] * param[4]
						* temp[0] + param[3] * param[4] * temp[2]) / cmax;
		double k21 = .5 * (-e2 + Math.sqrt(e2 * e2 - 4 * e3));
		double k31 = .5 * (-e2 - Math.sqrt(e2 * e2 - 4 * e3));
		param[1] = Math.abs(k21);
		param[2] = Math.abs(k31);

		return param;

	}

	private double[] modelAE17(double[] X, double[] Y, int noParam, double dose) {
		double[] param = new double[noParam];

		param = cStripInst.curveStrippingTwoExp(X, Y);
		return param;
	}

	private double[] modelAE16(double[] X, double[] Y, int noParam, double dose) {
		double[] param = new double[noParam];

		double[] temp = cStripInst.curveStrippingTwoExp(X, Y);

		param[0] = Math.abs(dose / (temp[0] + temp[1]));

		param[3] = Math.abs((temp[0] * temp[3] + temp[1] * temp[2])
				/ (temp[0] + temp[1]));
		param[1] = Math.abs((temp[2] * temp[3]) / param[3]);
		param[2] = Math.abs(temp[2] + temp[3] - param[1] - param[3]);

		return param;
	}

	private double[] modelAE15(double[] X, double[] Y, int noParam,
			double dose, double lengthOfInfusion) {
		/*
		 * double[] param = new double[noParam]; double[] result = new
		 * double[2];
		 * 
		 * result = cStripInst.curveStrippingOneExp(X, Y,1); param[1] =
		 * Math.abs(result[1]);
		 * 
		 * double max = maxVal(Y); param[0] = dose / max;
		 * 
		 * return param;
		 */

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
		result = cStripInst.curveStrippingOneExp(X1, Y1, 1);
		param[1] = Math.abs(result[1]);

		double k0 = dose / lengthOfInfusion;

		param[0] = k0 / (max);

		return param;
	}

	private double[] modelAE1(double[] X, double[] Y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[2];

		result = cStripInst.curveStrippingOneExp(X, Y, 1);
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
		result = cStripInst.curveStrippingOneExp(X1, Y1, 1);
		param[1] = Math.abs(result[1]);

		double k0 = dose / lengthOfInfusion;

		param[0] = k0 / (max);

		return param;
	}

	private double[] modelAE3(double[] X, double[] Y, int noParam, double dose) {

		double[] param = new double[noParam];

		double Cmax = maxVal(Y);

		double[] result = cStripInst.curveStrippingTwoExp(X, Y);

		param[2] = Math.abs(result[3]);
		param[1] = Math.abs(result[2]);

		param[0] = dose / Cmax;

		return param;
	}

	private double[] modelAE4(double[] X, double[] Y, int noParam, double dose) {
		double[] param = new double[noParam];

		double Cmax = maxVal(Y);

		int firstNonZeroIndex = firstNonZero(Y);

		double[] X1 = new double[X.length - firstNonZeroIndex];
		double[] Y1 = new double[X.length - firstNonZeroIndex];

		for (int i = 0; i < Y1.length; i++) {
			X1[i] = X[firstNonZeroIndex + i];
			Y1[i] = Y[firstNonZeroIndex + i];
		}

		double[] result = cStripInst.curveStrippingTwoExpTlag(X1, Y1);

		param[2] = Math.abs(result[3]);
		param[1] = Math.abs(result[2]);

		param[0] = dose / Cmax;
		param[3] = result[4];

		return param;
	}

	private double[] modelAE5(double[] X, double[] Y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[2];

		result = cStripInst.curveStrippingOneExp(X, Y, 1);
		param[1] = Math.abs(result[1]);

		double max = maxVal(Y);
		param[0] = dose / max;

		return param;
	}

	private double[] modelAE6(double[] X, double[] Y, int noParam, double dose) {
		double[] param = new double[noParam];

		double Cmax = maxVal(Y);

		int firstNonZeroIndex = firstNonZero(Y);

		double[] X1 = new double[X.length - firstNonZeroIndex];
		double[] Y1 = new double[X.length - firstNonZeroIndex];

		for (int i = 0; i < Y1.length; i++) {
			X1[i] = X[firstNonZeroIndex + i];
			Y1[i] = Y[firstNonZeroIndex + i];
		}

		double[] result = cStripInst.curveStrippingTwoExpTlag(X1, Y1);

		param[1] = Math.abs(result[2]);

		param[0] = dose / Cmax;
		param[2] = result[4];

		return param;
	}

	private double[] modelAE7(double[] X, double[] Y, int noParam, double dose)
			throws Exception {
		double[] param = new double[noParam];
		double[] temp = cStripInst.curveStrippingTwoExp(X, Y);

		param[0] = Math.abs(dose / (temp[0] + temp[1]));
		param[3] = Math.abs((temp[0] * temp[3] + temp[1] * temp[2])
				/ (temp[0] + temp[1]));
		param[1] = Math.abs(temp[2] * temp[3] / param[3]);
		param[2] = Math.abs(temp[2] + temp[3] - param[3] - param[1]);

		return param;
	}

	private double[] modelAE8(double[] X, double[] Y, int noParam, double dose)
			throws Exception {
		double[] param = new double[noParam];

		param = cStripInst.curveStrippingTwoExp(X, Y);
		return param;
	}

	private double[] modelAE9(double[] X, double[] Y, int noParam, double dose,
			double lengthOfInfusion) throws Exception {
		double[] param = new double[noParam];

		double[] temp = cStripInst.curveStrippingTwoExp(X, Y);

		param[0] = Math.abs(dose / (temp[0] + temp[1]));

		param[3] = Math.abs((temp[0] * temp[3] + temp[1] * temp[2])
				/ (temp[0] + temp[1]));
		param[1] = Math.abs((temp[2] * temp[3]) / param[3]);
		param[2] = Math.abs(temp[2] + temp[3] - param[1] - param[3]);

		return param;
	}

	private double[] modelAE10(double[] X, double[] Y, int noParam,
			double dose, double lengthOfInfusion) throws Exception {
		double[] param = new double[noParam];
		double[] temp = cStripInst.curveStrippingTwoExp(X, Y);

		param[3] = Math.abs(temp[3]);

		param[2] = Math.abs(temp[2]);

		param[0] = Math.abs(dose / (temp[0] + temp[1]));
		param[1] = Math.abs((temp[0] * temp[3] + temp[1] * temp[2])
				/ (temp[0] + temp[1]));

		return param;
	}

	private double[] modelAE11(double[] X, double[] Y, int noParam, double dose)
			throws Exception {
		double[] param = new double[noParam];

		int firstNonZeroIndex = firstNonZero(Y);

		double[] X1 = new double[X.length - firstNonZeroIndex];
		double[] Y1 = new double[X.length - firstNonZeroIndex];

		for (int i = 0; i < Y1.length; i++) {
			X1[i] = X[firstNonZeroIndex + i];
			Y1[i] = Y[firstNonZeroIndex + i];
		}
		double[] temp = cStripInst.curveStrippingThreeExp(X1, Y1);

		double Cmax = maxVal(Y);

		param[1] = Math.abs(temp[5]);

		param[0] = dose / Cmax;

		double d1 = dose
				* (temp[0] * (param[1] - temp[3]) + temp[1]
						* (param[1] - temp[4]));
		double k21 = dose
				* (temp[0] * temp[4] * param[1] + temp[1] * temp[3] * param[1] - (temp[0] + temp[1])
						* temp[3] * temp[4]) / d1;
		param[2] = temp[3] * temp[4] / k21;
		double k12 = temp[3] + temp[4] - k21 - param[2];
		param[3] = k12;
		param[4] = k21;

		return param;
	}

	private double[] modelAE12(double[] X, double[] Y, int noParam, double dose)
			throws Exception {
		double[] param = new double[noParam];

		int firstNonZeroIndex = firstNonZero(Y);

		double[] X1 = new double[X.length - firstNonZeroIndex];
		double[] Y1 = new double[X.length - firstNonZeroIndex];

		for (int i = 0; i < Y1.length; i++) {
			X1[i] = X[firstNonZeroIndex + i];
			Y1[i] = Y[firstNonZeroIndex + i];
		}
		double[] temp = cStripInst.curveStrippingThreeExpTlag(X1, Y1);

		double Cmax = maxVal(Y);

		param[2] = Math.abs(temp[4]);
		param[1] = Math.abs(temp[5]);

		param[0] = dose / Cmax;

		/*
		 * param[4] = Math.abs((temp[4] * temp[1] + temp[0] * temp[5]));
		 * param[3] = Math.abs(temp[5] + temp[1] - param[4] - param[2]);
		 */

		double d1 = dose
				* (temp[0] * (param[1] - temp[3]) + temp[1]
						* (param[1] - temp[4]));
		double k21 = dose
				* (temp[0] * temp[4] * param[1] + temp[1] * temp[3] * param[1] - (temp[0] + temp[1])
						* temp[3] * temp[4]) / d1;
		param[2] = temp[3] * temp[4] / k21;
		double k12 = temp[3] + temp[4] - k21 - param[2];
		param[3] = k12;
		param[4] = k21;

		param[5] = Math.abs(temp[6]);

		return param;
	}

	private double[] modelAE13(double[] X, double[] Y, int noParam, double dose)
			throws Exception {
		double[] param = new double[noParam];
		double[] temp = cStripInst.curveStrippingThreeExp(X, Y);

		param[4] = Math.abs(temp[4]);
		param[1] = Math.abs(temp[1]);
		param[3] = Math.abs(temp[3]);
		param[0] = Math.abs(temp[0]);
		param[2] = Math.abs(temp[5]);

		return param;
	}

	private double[] modelAE14(double[] X, double[] Y, int noParam, double dose)
			throws Exception {
		double[] param = new double[noParam];
		int firstNonZeroIndex = firstNonZero(Y);

		double[] X1 = new double[X.length - firstNonZeroIndex];
		double[] Y1 = new double[X.length - firstNonZeroIndex];

		for (int i = 0; i < Y1.length; i++) {
			X1[i] = X[firstNonZeroIndex + i];
			Y1[i] = Y[firstNonZeroIndex + i];
		}
		double[] temp = cStripInst.curveStrippingThreeExpTlag(X1, Y1);
		param[4] = Math.abs(temp[4]);
		param[1] = Math.abs(temp[0]);
		param[3] = Math.abs(temp[3]);
		param[0] = Math.abs(temp[1]);
		param[2] = Math.abs(temp[5]);

		// param[5] = X[firstNonZero(Y)];
		param[5] = Math.abs(temp[6]);

		return param;
	}

	private double[] modelAE18(double[] X, double[] Y, int noParam, double dose)
			throws Exception {
		double[] param = new double[noParam];
		param = cStripInst.curveStrippingThreeExp(X, Y);

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
