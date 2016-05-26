package Model;

public class DeModelCurveStripper {
	
	CurveStripping csInstance;

	public double[] chooseDEModels(int modelNumber, double[] xTemp,
			double[] yTemp, int noParam, double dose, double lengthOfInfusion) {
		double[] param = new double[noParam];

		csInstance = CurveStripping.createCSInstance();
		if (modelNumber == 1)
			param = modelDE1(xTemp, yTemp, noParam, dose);
		else if (modelNumber == 2)
			param = modelDE2(xTemp, yTemp, noParam, dose);
		else if (modelNumber == 3)
			param = modelDE3(xTemp, yTemp, noParam, dose);
		else if (modelNumber == 4)
			param = modelDE4(xTemp, yTemp, noParam, dose);
		else if (modelNumber == 5)
			param = modelDE5(xTemp, yTemp, noParam, dose);
		else if (modelNumber == 6)
			param = modelDE6(xTemp, yTemp, noParam, dose);
		else if (modelNumber == 7)
			param = modelDE7(xTemp, yTemp, noParam, dose, lengthOfInfusion);
		else if (modelNumber == 8)
			param = modelDE8(xTemp, yTemp, noParam, dose, lengthOfInfusion);
		else if (modelNumber == 9)
			param = modelDE9(xTemp, yTemp, noParam, dose);
		else if (modelNumber == 10)
			param = modelDE10(xTemp, yTemp, noParam, dose);
		else if (modelNumber == 11)
			param = modelDE11(xTemp, yTemp, noParam, dose);
		else if (modelNumber == 12)
			param = modelDE12(xTemp, yTemp, noParam, dose);
		else if (modelNumber == 13)
			param = modelDE13(xTemp, yTemp, noParam, dose);
		else if (modelNumber == 14)
			param = modelDE14(xTemp, yTemp, noParam, dose);
		else if (modelNumber == 15)
			param = modelDE15(xTemp, yTemp, noParam, dose, lengthOfInfusion);
		else if (modelNumber == 16)
			param = modelDE16(xTemp, yTemp, noParam, dose, lengthOfInfusion);
		

		return param;

	}

	private double[] modelDE16(double[] x, double[] y, int noParam,
			double dose, double lengthOfInfusion) {
		double[] param = new double[noParam];
		double[] result = new double[2];
		double[] temp = new double[4];
		double cMax = maxVal(y);

		int cMaxIndex = findVal(y, cMax);

		double[] Y1 = new double[y.length - cMaxIndex - 1];
		double[] X1 = new double[y.length - cMaxIndex - 1];

		for (int i = 0; i < y.length - cMaxIndex - 1; i++) {
			Y1[i] = y[i + cMaxIndex + 1];
			X1[i] = x[i + cMaxIndex + 1];
		}

		result = csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);

		temp[1] = result[0];
		temp[3] = result[1];

		for (int i = 0; i < y.length - cMaxIndex - 1; i++) {
			Y1[i] = Math.exp(result[0]) * Math.exp(x[i] * result[1]) - Y1[i];

		}

		result = csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);

		temp[0] = result[0];
		temp[2] = result[1];

		param[1] = dose / (temp[0] + temp[1]);

		double kpc = (temp[0] * temp[3] + temp[1] * temp[2])
				/ (temp[0] + temp[1]);
		double kel = (temp[2] * temp[3]) / param[3];
		double kcp = temp[2] + temp[3] - param[1] - param[3];

		param[0] = kel * param[1];
		param[2] = kcp * param[1];
		param[3] = param[1] * kcp / kpc;

		return param;
	}

	private double[] modelDE15(double[] x, double[] y, int noParam,
			double dose, double lengthOfInfusion) {
		double[] param = new double[noParam];
		double[] result = new double[2];
		double[] temp = new double[4];
		double cMax = maxVal(y);

		int cMaxIndex = findVal(y, cMax);

		double[] Y1 = new double[y.length - cMaxIndex - 1];
		double[] X1 = new double[y.length - cMaxIndex - 1];

		for (int i = 0; i < y.length - cMaxIndex - 1; i++) {
			Y1[i] = y[i + cMaxIndex + 1];
			X1[i] = x[i + cMaxIndex + 1];
		}

		result = csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);

		temp[1] = result[0];
		temp[3] = result[1];

		for (int i = 0; i < y.length - cMaxIndex - 1; i++) {
			Y1[i] = Math.exp(result[0]) * Math.exp(x[i] * result[1]) - Y1[i];

		}

		result = csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);

		temp[0] = result[0];
		temp[2] = result[1];

		param[1] = dose / (temp[0] + temp[1]);

		param[3] = (temp[0] * temp[3] + temp[1] * temp[2])
				/ (temp[0] + temp[1]);
		param[0] = (temp[2] * temp[3]) / param[3];
		param[2] = temp[2] + temp[3] - param[1] - param[3];

		return param;
	}

	private double[] modelDE14(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[2];

		double[] temp = new double[6];

		double Cmax = maxVal(y);
		result = csInstance.parameterCalculationUsingCurveStripping(x, y, 2);

		double kel = result[1];
		temp[0] = result[0];
		temp[1] = result[1];
		double[] Y1 = new double[y.length - 3];
		double[] X1 = new double[y.length - 3];
		for (int i = 0; i < Y1.length; i++) {
			X1[i] = x[i];
			Y1[i] = (-result[0] * Math.exp(-x[i] * result[1]) + y[i]);

		}

		result = csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);

		temp[2] = result[0];
		temp[3] = result[1];

		double[] Y2 = new double[Y1.length - 3];
		double[] X2 = new double[Y1.length - 3];
		for (int i = 0; i < Y2.length; i++) {
			X2[i] = x[i];
			Y2[i] = (result[0] * Math.exp(-x[i] * result[1]) - Y1[i]);
		}

		result = csInstance.parameterCalculationUsingCurveStripping(X2, Y2, 2);
		param[2] = result[1];

		temp[4] = result[0];
		temp[5] = result[1];

		param[1] = dose / Cmax;
		param[0] = param[1] * kel;
		double kpc = (temp[4] * temp[1] + temp[0] * temp[5]);
		double kcp = temp[5] + temp[1] - param[4] - param[2];
		param[5] = (temp[4] - temp[0]) / (temp[1] - temp[5]);

		param[3] = kcp * param[1];
		param[4] = param[1] * kcp / kpc;

		return param;
	}

	private double[] modelDE13(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[2];

		double[] temp = new double[6];

		double Cmax = maxVal(y);
		result = csInstance.parameterCalculationUsingCurveStripping(x, y, 2);

		param[0] = result[1];
		temp[0] = result[0];
		temp[1] = result[1];
		double[] Y1 = new double[y.length - 3];
		double[] X1 = new double[y.length - 3];
		for (int i = 0; i < Y1.length; i++) {
			X1[i] = x[i];
			Y1[i] = (-result[0] * Math.exp(-x[i] * result[1]) + y[i]);

		}

		result = csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);

		temp[2] = result[0];
		temp[3] = result[1];

		double[] Y2 = new double[Y1.length - 3];
		double[] X2 = new double[Y1.length - 3];
		for (int i = 0; i < Y2.length; i++) {
			X2[i] = x[i];
			Y2[i] = (result[0] * Math.exp(-x[i] * result[1]) - Y1[i]);
		}

		result = csInstance.parameterCalculationUsingCurveStripping(X2, Y2, 2);
		param[2] = result[1];

		temp[4] = result[0];
		temp[5] = result[1];

		param[1] = dose / Cmax;
		param[4] = (temp[4] * temp[1] + temp[0] * temp[5]);
		param[3] = temp[5] + temp[1] - param[4] - param[2];
		param[5] = (temp[4] - temp[0]) / (temp[1] - temp[5]);

		return param;
	}

	private double[] modelDE12(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[4];

		result = csInstance.parameterCalculationUsingCurveStripping(x, y, 4);

		param[2] = result[3];

		param[1] = dose / (result[0] + result[2]);
		param[0] = result[1] * param[1];

		double kpc = (result[2] * result[1] + result[0] * result[3])
				/ (result[0] + result[2]);
		double kcp = result[3] + result[1] - param[4] - param[2];

		param[3] = param[1] * kcp;
		param[4] = param[1] * kcp / kpc;

		return param;
	}

	private double[] modelDE11(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[4];

		result = csInstance.parameterCalculationUsingCurveStripping(x, y, 4);

		param[2] = result[3];
		param[0] = result[1];

		param[1] = dose / (result[0] + result[2]);
		param[4] = (result[2] * result[1] + result[0] * result[3])
				/ (result[0] + result[2]);
		param[3] = result[3] + result[1] - param[4] - param[2];

		return param;
	}

	private double[] modelDE10(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[4];
		double[] temp = new double[4];
		result = csInstance.parameterCalculationUsingCurveStripping(x, y,
				noParam);

		temp[0] = result[2];
		temp[1] = result[0];
		temp[2] = result[3];
		temp[3] = result[1];

		param[1] = dose / (temp[0] + temp[1]);
		param[3] = (temp[0] * temp[3] + temp[1] * temp[2])
				/ (temp[0] + temp[1]);

		double kel = temp[2] * temp[3] / param[3];

		param[0] = kel * param[1];
		param[2] = temp[2] + temp[3] - param[3] - param[1];

		return param;
	}

	private double[] modelDE9(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[4];
		double[] temp = new double[4];
		result = csInstance.parameterCalculationUsingCurveStripping(x, y,
				noParam);

		temp[0] = result[2];
		temp[1] = result[0];
		temp[2] = result[3];
		temp[3] = result[1];

		param[0] = dose / (temp[0] + temp[1]);
		param[3] = (temp[0] * temp[3] + temp[1] * temp[2])
				/ (temp[0] + temp[1]);
		param[1] = temp[2] * temp[3] / param[3];
		param[2] = temp[2] + temp[3] - param[3] - param[1];

		return param;
	}

	private double[] modelDE8(double[] x, double[] y, int noParam, double dose,
			double lengthOfInfusion) {
		double[] param = new double[noParam];
		double max = maxVal(y);
		int maxIndex = findVal(y, max);
		double[] X1 = new double[x.length - maxIndex - 1];
		double[] Y1 = new double[x.length - maxIndex - 1];
		int count = 0;
		for (int i = maxIndex + 1; i < x.length; i++) {
			X1[count] = x[i];
			Y1[count] = y[i];
			count++;
		}

		double[] result = new double[2];
		result = csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);

		double k0 = dose / lengthOfInfusion;

		param[1] = k0 / (max);
		param[0] = result[1] * param[1];
		return param;
	}

	private double[] modelDE7(double[] x, double[] y, int noParam, double dose,
			double lengthOfInfusion) {
		double[] param = new double[noParam];
		double max = maxVal(y);
		int maxIndex = findVal(y, max);
		double[] X1 = new double[x.length - maxIndex - 1];
		double[] Y1 = new double[x.length - maxIndex - 1];
		int count = 0;
		for (int i = maxIndex + 1; i < x.length; i++) {
			X1[count] = x[i];
			Y1[count] = y[i];
			count++;
		}

		double[] result = new double[2];
		result = csInstance.parameterCalculationUsingCurveStripping(X1, Y1, 2);
		param[0] = result[1];

		double k0 = dose / lengthOfInfusion;

		param[1] = k0 / (max);

		return param;
	}

	private double[] modelDE6(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[4];

		double Cmax = maxVal(y);
		result = csInstance.parameterCalculationUsingCurveStripping(x, y, 4);

		param[2] = result[3];
		param[1] = dose / Cmax;
		param[0] = result[1] * param[1];
		param[3] = Math.abs((Math.log(result[2]) - Math.log(result[0]))
				/ (result[3] - result[1]));

		return param;
	}

	private double[] modelDE5(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[4];

		double Cmax = maxVal(y);
		result = csInstance.parameterCalculationUsingCurveStripping(x, y, 4);
		param[0] = result[1];
		param[2] = result[3];
		param[1] = dose / Cmax;

		param[3] = Math.abs((Math.log(result[2]) - Math.log(result[0]))
				/ (result[3] - result[1]));

		return param;

	}

	private double[] modelDE4(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[4];
		double Cmax = maxVal(x);
		result = csInstance.parameterCalculationUsingCurveStripping(x, y, 4);

		param[2] = result[3];

		param[1] = dose / Cmax;

		param[0] = result[1] * param[1];

		return param;
	}

	private double[] modelDE3(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[4];
		double Cmax = maxVal(x);
		result = csInstance.parameterCalculationUsingCurveStripping(x, y, 4);
		param[0] = result[1];
		param[2] = result[3];

		param[1] = dose / Cmax;

		return param;
	}

	private double[] modelDE2(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[2];
		result = csInstance.parameterCalculationUsingCurveStripping(x, y,
				noParam);

		double max = maxVal(y);

		param[0] = dose / max;
		param[1] = result[0] * result[1];

		return param;
	}

	private double[] modelDE1(double[] x, double[] y, int noParam, double dose) {
		double[] param = new double[noParam];
		double[] result = new double[2];
		result = csInstance.parameterCalculationUsingCurveStripping(x, y,
				noParam);
		param[1] = result[1];

		double max = maxVal(y);

		param[0] = dose / max;

		return param;
	}

	public double maxVal(double[] mat) {
		double max = mat[0];
		for (int i = 1; i < mat.length; i++) {
			if (mat[i] > max) {
				max = mat[i];
			}
		}
		return max;
	}

	public int findVal(double[] mat, double val) {

		for (int i = 0; i < mat.length; i++) {
			if (mat[i] == val)
				return i;
		}
		return -1;
	}

}
