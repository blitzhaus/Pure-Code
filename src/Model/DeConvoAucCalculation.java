package Model;

public class DeConvoAucCalculation {
	
	double[] AUCValue;
	double AUCall;
	double AUClast;
	
	public void linearTrapezoidal(double[] t, double[] C) {
		
		AUCValue = new double[C.length];
		double AUC = 0;
		double AUMC = 0;
		int lastNonZeroIndex = lastNonZero(C);
		for (int i = 0; i < t.length - 1; i++) {
			double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
			double tempAUMC = (t[i + 1] - t[i])
					* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
			AUC = AUC + tempAUC;
			AUMC = AUMC + tempAUMC;
			AUCValue[i+1] = Math.abs(AUC);
			
		}
		AUCall = Math.abs(AUC);
		

		if (lastNonZeroIndex == t.length - 1) {
			AUClast = AUCall;
			
		} else {
			AUClast = AUCall - (t[lastNonZeroIndex + 1] - t[lastNonZeroIndex])
					* (C[lastNonZeroIndex + 1] + C[lastNonZeroIndex]) / 2.0;
			
		}
	}
	
	
	public void logTrapezoidal(double[] t, double[] C) {
	
		AUCValue = new double[C.length];
		double AUC = 0;
		double AUMC = 0;
		int lastNonZeroIndex = lastNonZero(C);
		for (int i = 0; i < t.length - 1; i++) {
			if (C[i] == 0 || C[i + 1] == 0) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
				AUCValue[i+1] = Math.abs(AUC);
				

			} else {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
						/ Math.log(C[i + 1] / C[i]);
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] - t[i] * C[i])
						/ Math.log(C[i + 1] / C[i])
						- Math.pow((t[i + 1] - t[i]), 2) * (C[i + 1] - C[i])
						/ Math.pow(Math.log(C[i + 1] / C[i]), 2);
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
				AUCValue[i+1] = Math.abs(AUC);
				
			}
		}

		AUCall = Math.abs(AUC);
		

		if (lastNonZeroIndex == t.length - 1) {
			AUClast = AUCall;
			
		} else {
			AUClast = AUCall - (t[lastNonZeroIndex + 1] - t[lastNonZeroIndex])
					* (C[lastNonZeroIndex + 1] + C[lastNonZeroIndex]) / 2.0;
			
		}

	}
	
	
	public void linearlogTrapezoidal(double[] t, double[] C) {
		
		AUCValue = new double[C.length];
		double AUC = 0;
		double AUMC = 0;
		
		double cMax = maxVal(C);
		
		int maxConcIndex = findVal(C, cMax);

		int lastNonZeroIndex = lastNonZero(C);
		for (int i = 0; i < t.length - 1; i++) {
			if (i + 1 <= maxConcIndex) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
				AUCValue[i+1] = Math.abs(AUC);
				
			} else {
				if (C[i] == 0 || C[i + 1] == 0) {
					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i])
							/ 2.0;
					double tempAUMC = (t[i + 1] - t[i])
							* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;
					AUCValue[i+1] = Math.abs(AUC);
					
				} else {
					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
							/ Math.log(C[i + 1] / C[i]);
					double tempAUMC = (t[i + 1] - t[i])
							* (t[i + 1] * C[i + 1] - t[i] * C[i])
							/ Math.log(C[i + 1] / C[i])
							- Math.pow((t[i + 1] - t[i]), 2)
							* (C[i + 1] - C[i])
							/ Math.pow(Math.log(C[i + 1] / C[i]), 2);
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;
					AUCValue[i+1] = Math.abs(AUC);
					
				}

			}
		}

		AUCall = Math.abs(AUC);
		
		if (lastNonZeroIndex == t.length - 1) {

			AUClast = AUCall;
			
		} else {

			AUClast = AUCall - (t[lastNonZeroIndex + 1] - t[lastNonZeroIndex])
					* (C[lastNonZeroIndex + 1] + C[lastNonZeroIndex]) / 2.0;
			
		}

	}


	
	public void linearUplogDownTrapezoidal(double[] t, double[] C) {

		AUCValue = new double[C.length];
		double AUC = 0;
		double AUMC = 0;
		int lastNonZeroIndex = lastNonZero(C);

		for (int i = 0; i < t.length - 1; i++) {
			if (C[i + 1] >= C[i]) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
				AUCValue[i+1] = Math.abs(AUC);
				
			} else {
				if (C[i] == 0 || C[i + 1] == 0) {
					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i])
							/ 2.0;
					double tempAUMC = (t[i + 1] - t[i])
							* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;
					AUCValue[i+1] = Math.abs(AUC);
					
				} else {

					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
							/ Math.log(C[i + 1] / C[i]);
					double tempAUMC = (t[i + 1] - t[i])
							* (t[i + 1] * C[i + 1] - t[i] * C[i])
							/ Math.log(C[i + 1] / C[i])
							- Math.pow((t[i + 1] - t[i]), 2)
							* (C[i + 1] - C[i])
							/ Math.pow(Math.log(C[i + 1] / C[i]), 2);
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;
					AUCValue[i+1] = Math.abs(AUC);
					
				}
			}
		}

		AUCall = Math.abs(AUC);
		

		if (lastNonZeroIndex == t.length - 1) {
			AUClast = AUCall;
			
		} else {
			AUClast = AUCall - (t[lastNonZeroIndex + 1] - t[lastNonZeroIndex])
					* (C[lastNonZeroIndex + 1] + C[lastNonZeroIndex]) / 2.0;
			
		}

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

	int findVal(double[] mat, double val) {

		for (int i = 0; i < mat.length; i++) {
			if (mat[i] == val)
				return i;
		}
		return -1;
	}

	
	int lastNonZero(double[] mat) {

		int index = 0;
		for (int i = mat.length - 1; i >= 0; i--) {
			if (mat[i] != 0.0) {
				index = i;
				break;
			}
		}
		return index;
	}



}
