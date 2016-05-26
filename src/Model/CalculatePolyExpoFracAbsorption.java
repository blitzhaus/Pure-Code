package Model;

public class CalculatePolyExpoFracAbsorption {

	DeConvolutionVariables deConvoVarInst;
	double[] timeVal;
	double[] concVal;
	double[] fracAbsorption;

	void fracAbsorptionCalculation() {
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		timeVal = deConvoVarInst.time;
		concVal = deConvoVarInst.conc;
		
		if(deConvoVarInst.noOfExpTerm == 2)
			biExpoDeConvolution();
		else if(deConvoVarInst.noOfExpTerm == 3)
			triExpoPolyDeConvolution();
		
		

	}

	void biExpoDeConvolution() {

		double A = deConvoVarInst.expValues[0];
		double B = deConvoVarInst.expValues[2];

		double alpha = deConvoVarInst.expValues[1];
		double beta = deConvoVarInst.expValues[3];

		double[] a = new double[timeVal.length];
		double[] b = new double[timeVal.length];
		double[] Fa = new double[timeVal.length];

		double[] fInp = new double[timeVal.length];
		double[] df = new double[timeVal.length];
		double[] Civ = new double[timeVal.length];
		double[] Inp = new double[timeVal.length];
		for (int i = 0; i < timeVal.length; i++) {
			Civ[i] = (A * Math.exp(-1.0 * alpha * timeVal[i]))
					+ (B * Math.exp(-1.0 * beta * timeVal[i]));

		}

		Inp[0] = 0.0;

		fInp[0] = 0.0;

		df[0] = 0.0;

		Fa[0] = 0.0;
		a[0] = (A / alpha) * (Math.exp(alpha * timeVal[0]) - 1);
		b[0] = (B / beta) * (Math.exp(beta * timeVal[0]) - 1);
		
		Inp[0] = concVal[0]/(a[0]*Math.exp((-1)*alpha*timeVal[0]) + b[0]*Math.exp((-1)*beta*timeVal[0]));
		
		Fa[0] = Inp[0]*timeVal[0];
		
		
		for (int j = 1; j < timeVal.length; j++) {
			a[j] = (A / alpha)
					* (Math.exp(alpha * timeVal[j]) - Math.exp(alpha
							* timeVal[j - 1]));
			b[j] = (B / beta)
					* (Math.exp(beta * timeVal[j]) - Math.exp(beta
							* timeVal[j - 1]));

			double sum1 = 0.0;

			double sum2 = 0.0;
			
			for (int k = 0; k < j; k++) {
				double term1 = Inp[k] * a[k];

				double term2 = Inp[k] * b[k];
				
				sum1 = sum1 + term1;

				sum2 = sum2 + term2;
			
			}

			double temp1 = sum1 * Math.exp(-1.0 * alpha * timeVal[j]);

			double temp2 = sum2 * Math.exp(-1.0 * beta * timeVal[j]);
			Inp[j] = (concVal[j] - (temp1 + temp2))
					/ ((a[j] * Math.exp(-1.0 * alpha * timeVal[j])) + (b[j] * Math
							.exp(-1.0 * beta * timeVal[j])));

			df[j] = timeVal[j] - timeVal[j - 1];

			fInp[j] = Inp[j] * df[j];

			Fa[j] = Fa[j - 1] + fInp[j];

		}

		deConvoVarInst.fracAbsorption = Fa;
		deConvoVarInst.absRate = Inp;
		

	}

	void triExpoPolyDeConvolution() {

		double A = deConvoVarInst.expValues[0];
		double B = deConvoVarInst.expValues[2];
		double C = deConvoVarInst.expValues[4];
		double alpha = deConvoVarInst.expValues[1];
		double beta = deConvoVarInst.expValues[3];
		double gama = deConvoVarInst.expValues[5];

		double[] a = new double[timeVal.length]; // time.length
		double[] b = new double[timeVal.length];
		double[] c = new double[timeVal.length];
		double[] fInp = new double[timeVal.length];
		double[] df = new double[timeVal.length];

		double[] Fa = new double[timeVal.length];
		double[] Civ = new double[timeVal.length];
		double[] Inp = new double[timeVal.length];
		for (int i = 0; i < timeVal.length; i++) {

			Civ[i] = (A * Math.exp(-1.0 * alpha * timeVal[i]))
					+ (B * Math.exp(-1.0 * beta * timeVal[i]))
					+ (C * Math.exp(-1.0 * gama * timeVal[i]));

		}

		Inp[0] = 0.0;

		fInp[0] = 0.0;

		df[0] = 0.0;

		Fa[0] = 0.0;
		a[0] = (A / alpha) * (Math.exp(alpha * timeVal[0]) - 1);
		b[0] = (B / beta) * (Math.exp(beta * timeVal[0]) - 1);
		c[0] = (C / gama) * (Math.exp(gama * timeVal[0]) - 1);

	Inp[0] = concVal[0]/(a[0]*Math.exp((-1)*alpha*timeVal[0]) + b[0]*Math.exp((-1)*beta*timeVal[0]) +c[0]*Math.exp((-1)*gama*timeVal[0]));
		
		Fa[0] = Inp[0]*timeVal[0];
		
		for (int j = 1; j < timeVal.length; j++) {
			a[j] = (A / alpha)
					* (Math.exp(alpha * timeVal[j]) - Math.exp(alpha
							* timeVal[j - 1]));
			b[j] = (B / beta)
					* (Math.exp(beta * timeVal[j]) - Math.exp(beta
							* timeVal[j - 1]));
			c[j] = (C / gama)
					* (Math.exp(gama * timeVal[j]) - Math.exp(gama
							* timeVal[j - 1]));

			double sum1 = 0.0;

			double sum2 = 0.0;
			double sum3 = 0.0;
			for (int k = 0; k < j; k++) {
				double term1 = Inp[k] * a[k];

				double term2 = Inp[k] * b[k];
				double term3 = Inp[k] * c[k];
				sum1 = sum1 + term1;

				sum2 = sum2 + term2;
				sum3 = sum3 + term3;
			}

			double temp1 = sum1 * Math.exp(-1.0 * alpha * timeVal[j]);

			double temp2 = sum2 * Math.exp(-1.0 * beta * timeVal[j]);
			double temp3 = sum3 * Math.exp(-1.0 * gama * timeVal[j]);

			Inp[j] = (concVal[j] - (temp1 + temp2 + temp3))
					/ ((a[j] * Math.exp(-1.0 * alpha * timeVal[j]))
							+ (b[j] * Math.exp(-1.0 * beta * timeVal[j])) + (c[j] * Math
							.exp(-1.0 * gama * timeVal[j])));
			
			df[j] = timeVal[j] - timeVal[j - 1];

			fInp[j] = Inp[j] * df[j];

			Fa[j] = Fa[j - 1] + fInp[j];

		}
		
		deConvoVarInst.fracAbsorption = Fa;
		deConvoVarInst.absRate = Inp;

	}

}
