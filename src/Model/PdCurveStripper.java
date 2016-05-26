package Model;

public class PdCurveStripper {
	PkPdInfo pkpdInst;
	int firstIndex;

	public double[] calculateInitialValue(double[][] conc, double[][] effect) {
		pkpdInst = PkPdInfo.createPKPDInstance();
		double[] result = new double[pkpdInst.noOfParam];
		double[] X = new double[conc.length];
		double[] Y = new double[conc.length];

		for (int i = 0; i < conc.length; i++) {
			X[i] = conc[i][0];
			Y[i] = effect[i][0];
		}
		int modelNumber = pkpdInst.modelNumber;
		int noOfParam = pkpdInst.noOfParam;

		result = choosePdLibraryModel(modelNumber, X, Y, noOfParam);

		return result;
	}

	private double[] choosePdLibraryModel(int modelNumber, double[] x,
			double[] y, int noOfParam) {
		double[] param = new double[noOfParam];

		if (modelNumber == 1)
			param = pdModel1(x, y, noOfParam);
		else if (modelNumber == 2)
			param = pdModel2(x, y, noOfParam);
		else if (modelNumber == 3)
			param = pdModel3(x, y, noOfParam);
		else if (modelNumber == 4)
			param = pdModel4(x, y, noOfParam);
		else if (modelNumber == 5)
			param = pdModel5(x, y, noOfParam);
		else if (modelNumber == 6)
			param = pdModel6(x, y, noOfParam);
		else if (modelNumber == 7)
			param = pdModel7(x, y, noOfParam);
		else if (modelNumber == 8)
			param = pdModel8(x, y, noOfParam);

		return param;
	}

	private double[] pdModel8(double[] x, double[] y, int noOfParam) {

		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = maxResponse - minResponse;
		param[2] = y[0];

		double requiredResponse = minResponse + param[0] / 2.0;

		param[1] = linearInterpolation(y, x, requiredResponse);
		double slope = (y[firstIndex +1] - y[firstIndex])/(Math.log(y[firstIndex +1]) - Math.log(y[firstIndex]));
		param[3] = 4*slope/param[0]; 
		
		return param;
	}

	private double[] pdModel7(double[] x, double[] y, int noOfParam) {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = y[0];
		double temp = maxResponse - minResponse;

		double requiredResponse = minResponse + temp / 2.0;

		param[1] = linearInterpolation(y, x, requiredResponse);
		double slope = (y[firstIndex +1] - y[firstIndex])/(Math.log(y[firstIndex +1]) - Math.log(y[firstIndex]));
		param[2] = 4*slope/(maxResponse - minResponse); 
		
		return param;
		
	}

	private double[] pdModel6(double[] x, double[] y, int noOfParam) {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = maxResponse - minResponse;
		param[2] = y[0];

		double requiredResponse = minResponse + param[0] / 2.0;

		param[1] = linearInterpolation(y, x, requiredResponse);
		
		double slope = (y[firstIndex +1] - y[firstIndex])/(Math.log(y[firstIndex +1]) - Math.log(y[firstIndex]));
		param[3] = 4*slope/param[0]; 
		
		return param;
	}

	private double[] pdModel5(double[] x, double[] y, int noOfParam) {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = maxResponse - minResponse;

		double requiredResponse = minResponse + param[0] / 2.0;

		param[1] = linearInterpolation(y, x, requiredResponse);
		
		double slope = (y[firstIndex +1] - y[firstIndex])/(Math.log(y[firstIndex +1]) - Math.log(y[firstIndex]));
		param[2] = 4*slope/param[0]; 
		
		
		return param;
	}

	private double[] pdModel4(double[] x, double[] y, int noOfParam) {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = maxResponse - minResponse;
		param[2] = y[0];

		double requiredResponse = minResponse + param[0] / 2.0;

		param[1] = linearInterpolation(y, x, requiredResponse);
		return param;
	}

	private double[] pdModel3(double[] x, double[] y, int noOfParam) {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = y[0];
		double temp = maxResponse - minResponse;

		double requiredResponse = minResponse + temp / 2.0;

		param[1] = linearInterpolation(y, x, requiredResponse);
		return param;
	}

	private double[] pdModel2(double[] x, double[] y, int noOfParam) {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = maxResponse - minResponse;
		param[1] = y[0];

		double requiredResponse = minResponse + param[0] / 2.0;

		param[2] = linearInterpolation(y, x, requiredResponse);
		return param;
	}

	private double[] pdModel1(double[] x, double[] y, int noOfParam) {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = maxResponse - minResponse;

		double requiredResponse = minResponse + param[0] / 2.0;

		param[1] = linearInterpolation(y, x, requiredResponse);
		return param;
	}

	public double linearInterpolation(double[] t, double[] C, double tstar) {
		double Cstar = 0;
		int ifInterpReq = findVal(t, tstar);
		int interpIdx = 0;
		if (ifInterpReq == -1) {
			for (int i = 0; i < t.length - 1; i++) {
				if ((tstar > t[i] && tstar < t[i + 1]) || (tstar < t[i] && tstar > t[i + 1])) {
					interpIdx = i;
					break;
				}
			}

			if (C[interpIdx] <= C[interpIdx + 1]) {
				firstIndex = interpIdx;
				Cstar = C[interpIdx]
						+ Math.abs((tstar - t[interpIdx])
								/ (t[interpIdx + 1] - t[interpIdx]))
						* (C[interpIdx + 1] - C[interpIdx]);
			} else {
				Cstar = logInterpolation(t, C, tstar);
			}

		} else
			Cstar = C[ifInterpReq];

		return Cstar;
	}

	private double logInterpolation(double[] t, double[] C, double tstar) {
		double Cstar = 0;
		int ifInterpReq = findVal(t, tstar);
		int interpIdx = 0;
		if (ifInterpReq == -1) {
			for (int i = 0; i < t.length - 1; i++) {
				if ((tstar > t[i] && tstar < t[i + 1]) || (tstar < t[i] && tstar > t[i + 1])) {
					interpIdx = i;
					break;
				}
			}
			firstIndex = interpIdx;
			double term1 = Math.log(C[interpIdx]);
			double term2 = Math.abs((tstar - t[interpIdx])
					/ (t[interpIdx + 1] - t[interpIdx]));
			double term3 = Math.log(C[interpIdx + 1]) - Math.log(C[interpIdx]);
			Cstar = Math.exp(term1 + term2 * term3);
		} else
			Cstar = C[ifInterpReq];

		return Cstar;
	}

	double maxVal(double[] mat) {
		double max = mat[0];
		for (int i = 1; i < mat.length; i++) {
			if (mat[i] > max) {
				max = mat[i];
			}
		}
		return max;
	}

	int findVal(double[] mat, double val) {

		for (int i = 0; i < mat.length; i++) {
			if (mat[i] == val)
				return i;
		}
		return -1;
	}

	private double minVal(double[] mat) {
		double min = mat[0];
		for (int i = 1; i < mat.length; i++) {
			if (mat[i] < min) {
				min = mat[i];
			}
		}
		return min;
	}
}
