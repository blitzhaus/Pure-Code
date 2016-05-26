package Model;

public class PkPdLinkCurveStripper {
	PkPdInfo pkpdInst;
	int firstIndex;
	CurveStripper  lambdaZCalInst;

	public double[] calculateInitialValue(double[][] time, double[][] conc, double[][] effect) {
		pkpdInst = PkPdInfo.createPKPDInstance();
		lambdaZCalInst = new CurveStripper();
		double[] result = new double[pkpdInst.noOfParam];
		double[] X = new double[conc.length];
		double[] Y = new double[conc.length];
		double[] Z = new double[conc.length];

		for (int i = 0; i < conc.length; i++) {
			Z[i] = time[i][0];
			X[i] = conc[i][0];
			Y[i] = effect[i][0];
		}
		int modelNumber = pkpdInst.modelNumber;
		int noOfParam = pkpdInst.noOfParam;

		try {
			result = choosePkPdLinkLibraryModel(modelNumber,Z, X, Y, noOfParam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	private double[] choosePkPdLinkLibraryModel(int modelNumber, double[] z,double[] x,
			double[] y, int noOfParam) throws Exception {
		double[] param = new double[noOfParam];

		if (modelNumber == 1)
			param = pdModel1(z,x, y, noOfParam);
		else if (modelNumber == 2)
			param = pdModel2(z,x, y, noOfParam);
		else if (modelNumber == 3)
			param = pdModel3(z,x, y, noOfParam);
		else if (modelNumber == 4)
			param = pdModel4(z,x, y, noOfParam);
		else if (modelNumber == 5)
			param = pdModel5(z,x, y, noOfParam);
		else if (modelNumber == 6)
			param = pdModel6(z,x, y, noOfParam);
		else if (modelNumber == 7)
			param = pdModel7(z,x, y, noOfParam);
		else if (modelNumber == 8)
			param = pdModel8(z,x, y, noOfParam);

		return param;
	}

	private double[] pdModel8(double[] time,double[] x, double[] y, int noOfParam) throws Exception {

		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = maxResponse - minResponse;
		param[2] = y[0];

		double requiredResponse = minResponse + param[0] / 2.0;

		param[1] = linearInterpolation(y, x, requiredResponse);
		double slope = (y[firstIndex +1] - y[firstIndex])/(Math.log(y[firstIndex +1]) - Math.log(y[firstIndex]));
		param[4] = 4*slope/param[0]; 
		
		double[] temp=lambdaZCalInst.lambdaZCalculationForCA(time, y, 0, 0, 1);
		
		param[3] = temp[1];
		
		return param;
	}

	private double[] pdModel7(double[] time,double[] x, double[] y, int noOfParam) throws Exception {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = y[0];
		double temp = maxResponse - minResponse;

		double requiredResponse = minResponse + temp / 2.0;

		param[1] = linearInterpolation(y, x, requiredResponse);
		double slope = (y[firstIndex +1] - y[firstIndex])/(Math.log(y[firstIndex +1]) - Math.log(y[firstIndex]));
		param[3] = 4*slope/(maxResponse - minResponse); 
		
		double[] temp1=lambdaZCalInst.lambdaZCalculationForCA(time, y, 0, 0, 1);
		
		param[2] = temp1[1];
		return param;
		
	}

	private double[] pdModel6(double[] time,double[] x, double[] y, int noOfParam) throws Exception {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = maxResponse - minResponse;
		param[2] = y[0];

		double requiredResponse = minResponse + param[0] / 2.0;

		param[1] = linearInterpolation(y, x, requiredResponse);
		
		double slope = (y[firstIndex +1] - y[firstIndex])/(Math.log(y[firstIndex +1]) - Math.log(y[firstIndex]));
		param[4] = 4*slope/param[0]; 
		
		double[] temp=lambdaZCalInst.lambdaZCalculationForCA(time, y, 0, 0, 1);
		
		param[3] = temp[1];
		
		return param;
	}

	private double[] pdModel5(double[] time,double[] x, double[] y, int noOfParam) throws Exception {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = maxResponse - minResponse;

		double requiredResponse = minResponse + param[0] / 2.0;

		param[1] = linearInterpolation(y, x, requiredResponse);
		
		double slope = (y[firstIndex +1] - y[firstIndex])/(Math.log(y[firstIndex +1]) - Math.log(y[firstIndex]));
		param[3] = 4*slope/param[0]; 
		
		
		double[] temp=lambdaZCalInst.lambdaZCalculationForCA(time, y, 0, 0, 1);
		
		param[2] = temp[1];
		
		
		return param;
	}

	private double[] pdModel4(double[] time,double[] x, double[] y, int noOfParam) throws Exception {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = maxResponse - minResponse;
		param[2] = y[0];

		double requiredResponse = minResponse + param[0] / 2.0;

		param[1] = linearInterpolation(y, x, requiredResponse);
		
		double[] temp=lambdaZCalInst.lambdaZCalculationForCA(time, y, 0, 0, 1);
		
		param[3] = temp[1];	
		return param;
	}

	private double[] pdModel3(double[] time,double[] x, double[] y, int noOfParam) throws Exception {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = y[0];
		double temp = maxResponse - minResponse;

		double requiredResponse = minResponse + temp / 2.0;

		param[1] = linearInterpolation(y, x, requiredResponse);
		double[] temp1 = lambdaZCalInst.lambdaZCalculationForCA(time, y, 0, 0, 1);
		
		param[2] = temp1[1];
		return param;
	}

	private double[] pdModel2(double[] time,double[] x, double[] y, int noOfParam) throws Exception {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = maxResponse - minResponse;
		param[1] = y[0];

		double requiredResponse = minResponse + param[0] / 2.0;

		param[2] = linearInterpolation(y, x, requiredResponse);
		double[] temp=lambdaZCalInst.lambdaZCalculationForCA(time, y, 0, 0, 1);
		
		param[3] = temp[1];
		return param;
	}

	private double[] pdModel1(double[] time,double[] x, double[] y, int noOfParam) throws Exception {
		double[] param = new double[noOfParam];
		double maxResponse = maxVal(y);
		double minResponse = minVal(y);
		param[0] = maxResponse - minResponse;

		double requiredResponse = minResponse + param[0] / 2.0;
		param[1] = linearInterpolation(y, x, requiredResponse);
		
		double[] temp=lambdaZCalInst.lambdaZCalculationForCA(time, y, 0, 0, 1);
		
		param[2] = temp[1];
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
