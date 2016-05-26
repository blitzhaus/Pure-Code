package Model;

public class MmCurveStripper {
	PkPdInfo pkpdInst;
	int firstIndex;
	double Slope;
	double Intercept;
	public double[] calculateInitialValue(double[][] time1, double[][] conc1, double dose) throws Exception {
		pkpdInst = PkPdInfo.createPKPDInstance();
		double[] result = new double[pkpdInst.noOfParam];
		double[] time = new double[conc1.length];
		double[] conc = new double[conc1.length];
	
		

		for (int i = 0; i < conc.length; i++) {
			time[i] = time1[i][0];
			conc[i] = conc1[i][0];
			
		}
		int modelNumber = pkpdInst.modelNumber;
		int noOfParam = pkpdInst.noOfParam;

		result = chooseMmLibraryModel(modelNumber, time, conc, noOfParam, dose);

		return result;
	}

	private double[] chooseMmLibraryModel(int modelNumber, double[] time,
			double[] conc, int noOfParam, double dose) throws Exception {
		double[] param = new double[noOfParam];

		if (modelNumber == 1)
			param = mmModel1(time, conc, noOfParam, dose);
		else if (modelNumber == 2)
			param = mmModel2(time, conc, noOfParam, dose);
		else if (modelNumber == 3)
			param = mmModel3(time, conc, noOfParam, dose);
		else if (modelNumber == 4)
			param = mmModel4(time, conc, noOfParam, dose);
		
		return param;
	}

	private double[] mmModel4(double[] time, double[] conc, int noOfParam, double dose) throws Exception {
		double[] param = new double[noOfParam];
		PdCurveStripper pdCurveStripInst = new PdCurveStripper();
		CurveStripper lambdaZCalInst = new CurveStripper();
		double Cmax = pdCurveStripInst.maxVal(conc);
		param[0] = dose/Cmax;
		double slopeA;
		double slopeB;
		double KD;
		double C0A;
		double C0B;
		double VmaxDByKm;
		double Km;
		double Vmax;
		double VmaxD;
		
		int cMaxIndex = pdCurveStripInst.findVal(conc, Cmax);
		
		slopeA = (Math.log(conc[cMaxIndex+2]) - Math.log(conc[cMaxIndex+1]))/(time[cMaxIndex+2] - time[cMaxIndex+1]);
		KD = slopeA;
		C0A = conc[cMaxIndex+2] - slopeA*time[cMaxIndex+2];
		
		double[] temp = lambdaZCalInst.lambdaZCalculationForCA(time, conc, 0, 0, 1);
		slopeB = -temp[1];
		C0B = temp[0];
		VmaxDByKm = Math.abs(slopeB) -Math.abs(KD); 
		
		
		Km = (C0A*KD/(KD + VmaxDByKm))/(Math.pow((C0B/C0A), KD/VmaxDByKm) -1);
		
		Vmax = VmaxDByKm * Km *param[0];
		
		param[2] = Vmax;
		param[3] = Km;
		
		// for computing K01
		
		double[] x1 = new double[cMaxIndex +1];
		double[] y1 = new double[cMaxIndex +1];
		
		for (int i = 0; i < y1.length; i++) {
			x1[i] = time[i];
			y1[i] = Math.abs(conc[i] - (Math.exp(time[i] * slopeA + C0A)));
		}
		
		double[] temp1 = lambdaZCalInst.lambdaZCalculationForCA(x1, y1, 0, 0, 1);
		
		param[1] = temp[1];
		
		double tlag = Math.abs(Math.log(C0A) - Math
				.log(C0B))
				/ (slopeA - slopeB);
		
		int index = firstNonZero(conc);
		
		
			/*finalParam[3] = Math.abs((Math.log(tempResult[1][0]) - Math
			.log(tempResult[0][0]))
			/ (tempResult[1][1] - tempResult[0][1]));*/
	
		
		param[4] = Math.min(time[index], tlag); 
		
		return param;
	}

	int firstNonZero(double[] mat) {

		int index = 0;
		for (int i = 0; i < mat.length; i++) {
			if (mat[i] != 0.0) {
				index = i;
				break;
			}
		}
		return index;
	}

	private double[] mmModel3(double[] time, double[] conc, int noOfParam, double dose) throws Exception {
		double[] param = new double[noOfParam];
		PdCurveStripper pdCurveStripInst = new PdCurveStripper();
		CurveStripper lambdaZCalInst = new CurveStripper();
		double Cmax = pdCurveStripInst.maxVal(conc);
		param[0] = dose/Cmax;
		double slopeA;
		double slopeB;
		double KD;
		double C0A;
		double C0B;
		double VmaxDByKm;
		double Km;
		double Vmax;
		double VmaxD;
		
		int cMaxIndex = pdCurveStripInst.findVal(conc, Cmax);
		
		slopeA = (Math.log(conc[cMaxIndex+2]) - Math.log(conc[cMaxIndex+1]))/(time[cMaxIndex+2] - time[cMaxIndex+1]);
		KD = slopeA;
		C0A = conc[cMaxIndex+2] - slopeA*time[cMaxIndex+2];
		
		double[] temp = lambdaZCalInst.lambdaZCalculationForCA(time, conc, 0, 0, 1);
		slopeB = -temp[1];
		C0B = temp[0];
		VmaxDByKm = Math.abs(slopeB) -Math.abs(KD); 
		
		
		Km = (C0A*KD/(KD + VmaxDByKm))/(Math.pow((C0B/C0A), KD/VmaxDByKm) -1);
		
		Vmax = VmaxDByKm * Km *param[0];
		
		param[2] = Vmax;
		param[3] = Km;
		
		// for computing K01
		
		double[] x1 = new double[cMaxIndex +1];
		double[] y1 = new double[cMaxIndex +1];
		
		for (int i = 0; i < y1.length; i++) {
			x1[i] = time[i];
			y1[i] = Math.abs(conc[i] - (Math.exp(time[i] * slopeA + C0A)));
		}
		
		double[] temp1 = lambdaZCalInst.lambdaZCalculationForCA(x1, y1, 0, 0, 1);
		
		param[1] = temp[1];
		
		
		return param;
	}

	private double[] mmModel2(double[] time, double[] conc, int noOfParam, double dose) throws Exception {
		double[] param = new double[noOfParam];
		PdCurveStripper pdCurveStripInst = new PdCurveStripper();
		CurveStripper lambdaZCalInst = new CurveStripper();
		double Cmax = pdCurveStripInst.maxVal(conc);
		param[0] = dose/Cmax;
		double slopeA;
		double slopeB;
		double KD;
		double C0A;
		double C0B;
		double VmaxDByKm;
		double Km;
		double Vmax;
		double VmaxD;
		
		int cMaxIndex = pdCurveStripInst.findVal(conc, Cmax);
		
		slopeA = (Math.log(conc[cMaxIndex+2]) - Math.log(conc[cMaxIndex+1]))/(time[cMaxIndex+2] - time[cMaxIndex+1]);
		KD = slopeA;
		C0A = conc[cMaxIndex+2] - slopeA*time[cMaxIndex+2];
		
		double[] temp = lambdaZCalInst.lambdaZCalculationForCA(time, conc, 0, 0, 1);
		slopeB = -temp[1];
		C0B = temp[0];
		VmaxDByKm = Math.abs(slopeB) -Math.abs(KD); 
		
		
		Km = (C0A*KD/(KD + VmaxDByKm))/(Math.pow((C0B/C0A), KD/VmaxDByKm) -1);
		
		Vmax = VmaxDByKm * Km *param[0];
		
		param[1] = Vmax;
		param[2] = Km;
		
		return param;
	}

	private double[] mmModel1(double[] time, double[] conc, int noOfParam, double dose) {
		double[] param = new double[noOfParam];
		double C0 = 0;
		double[] x;
		double[] y;
		
		if(time[0] == 0)
		{
			param[0] = dose/conc[0];
		}else
		{
			x = new double[2];
			y = new double[2];
			
			x[0] = time[0];
			x[1] = time[1];
			y[0] = Math.log(conc[0]);
			y[1] = Math.log(conc[1]);
			// y1[0] =Y[0]; y1[1] =Y[1];
			simpleLinRegression(x, y);
			
			Intercept = Math.exp(Intercept);
			C0 = Intercept;
			
			param[0] = dose/C0;
		}
		
		x = new double[3];
		y = new double[3];
		
		simpleLinRegression(x, y);
		Intercept = Math.exp(Intercept);
		
		if(time[0] == 0)
		{
			param[2] = conc[0]/(Math.log((Intercept/conc[0])));
		}else
		{
			param[2] = C0/(Math.log((Intercept/C0)));
		}
		
		param[1] = param[0] * param[2] * (-Slope);
		return param;
	}
	
	public void simpleLinRegression(double[] x, double[] y) {
		int n = x.length;
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
		int weightingIndex =0;

		for (int i = 0; i < n; i++) {
			sumX = sumX + x[i];
			sumY = sumY + y[i];
			// weightSum=weightSum+1/Math.pow(y[i],(weightingIndex-2));
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

		// Slope = (n*sumXY - sumX * sumY)/ (n*sumX2 - sumX*sumX);
		Slope = (sumW * sumWXY - sumWX * sumWY)
				/ (sumW * sumWX2 - sumWX * sumWX);
		Intercept = (sumWX2 * sumWY - sumWX * sumWXY)
				/ (sumW * sumWX2 - sumWX * sumWX);

		// Intercept = (sumY - Slope*sumX)/n;
		Sxx = (sumWX2 - (sumWX * sumWX / sumW));
		Syy = (sumWY2 - (sumWY * sumWY / sumW));
		Sxy = (sumWXY - (sumWX * sumWY / sumW));
		// Rsq=((Sxy*Sxy)/(Sxx*Syy));

	}


}
