package Model;

public class IdrCurveStripper {
	

	PkPdInfo pkpdInst;
	int firstIndex;

	public double[] calculateInitialValue(double[][] time1, double[][] conc1, double[][] effect1) {
		pkpdInst = PkPdInfo.createPKPDInstance();
		double[] result = new double[pkpdInst.noOfParam];
		double[] time = new double[conc1.length];
		double[] conc = new double[conc1.length];
		double[] effect = new double[conc1.length];
		

		for (int i = 0; i < conc.length; i++) {
			time[i] = time1[i][0];
			conc[i] = conc1[i][0];
			effect[i] = effect1[i][0];
		}
		int modelNumber = pkpdInst.modelNumber;
		int noOfParam = pkpdInst.noOfParam;

		result = chooseIrmLibraryModel(modelNumber, time, conc,effect, noOfParam);

		return result;
	}

	private double[] chooseIrmLibraryModel(int modelNumber, double[] time,
			double[] conc, double[] effect, int noOfParam) {
		double[] param = new double[noOfParam];

		if (modelNumber == 1)
			param = idrModel1(time, conc, effect, noOfParam);
		else if (modelNumber == 2)
			param = idrModel2(time, conc, effect, noOfParam);
		else if (modelNumber == 3)
			param = idrModel3(time, conc, effect, noOfParam);
		else if (modelNumber == 4)
			param = idrModel4(time, conc, effect, noOfParam);
		
		return param;
	}

	

	private double[] idrModel4(double[] time, double[] conc, double[] effect, int noOfParam) {
		double[] param = new double[noOfParam];
		double r0 = effect[0];
		double maxResponse = maxVal(effect);
		double minResponse = minVal(effect);
		
		double slope = calculateSlope(time, effect);
		param[1] = Math.abs(-slope);
		param[0] = Math.abs(r0 * param[1]);

		double requiredResponse = minResponse + (maxResponse - minResponse) / 2.0;

		param[2] = linearInterpolation(effect, conc, requiredResponse);
		param[3] = maxResponse - minResponse;
		return param;
	}

	private double[] idrModel3(double[] time, double[] conc, double[] effect, int noOfParam) {
		double[] param = new double[noOfParam];
		double r0 = effect[0];
		double maxResponse = maxVal(effect);
		double minResponse = minVal(effect);
		
		double slope = calculateSlope(time, effect);
		param[1] = Math.abs(-slope);
		param[0] = Math.abs(r0 * param[1]);

		double requiredResponse = minResponse + (maxResponse - minResponse) / 2.0;

		param[2] = linearInterpolation(effect, conc, requiredResponse);
		param[3] = maxResponse - minResponse;
		return param;
	}

	private double[] idrModel2(double[] time, double[] conc, double[] effect, int noOfParam) {
		double[] param = new double[noOfParam];
		double r0 = effect[0];
		double maxResponse = maxVal(effect);
		double minResponse = minVal(effect);
		
		double slope = calculateSlope(time, effect);
		param[1] = Math.abs(-slope);
		param[0] = Math.abs(r0 * param[1]);

		double requiredResponse = minResponse + (maxResponse - minResponse) / 2.0;

		param[2] = linearInterpolation(effect, conc, requiredResponse);
		return param;
	}

	private double[] idrModel1(double[] time, double[] conc, double[] effect, int noOfParam) {
		double[] param = new double[noOfParam];
		double r0 = effect[0];
		double maxResponse = maxVal(effect);
		double minResponse = minVal(effect);
		
		double slope = calculateSlope(time, effect);
		param[1] = Math.abs(-slope);
		param[0] = Math.abs(r0 * param[1]);

		/*double Rm = maxVal(effect);
		double R0 = param[0]/param[1];
		double Imax = 
		*/
		double requiredResponse = minResponse + (maxResponse - minResponse) / 2.0;

		param[2] = linearInterpolation(effect, conc, requiredResponse);
		return param;
	}

	private double calculateSlope(double[] time, double[] effect) {
		double slope = 0;
		int i;
		for (i = 0; i < effect.length; i++) {
			if(effect[i]<= effect[i+1])
				break;
		}
		
		if(i>0)
		{
			slope = (Math.log(effect[0])- Math.log(effect[i]))/(time[0] - time[i]);
		}
		else
		{
			slope = (Math.log(effect[0])- Math.log(effect[1]))/(time[0] - time[1]);
		}
		return slope;
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
