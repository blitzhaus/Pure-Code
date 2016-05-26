package Model;

import org.apache.commons.math.analysis.MultivariateVectorialFunction;


public class MyMultivariateVectorialFunction implements MultivariateVectorialFunction {
	
	public double[][] inputData;
	public int rowCount = 0;
	public int colCount = 0;
	
	double[][] EXTRA_DATA;
	int[] row;
	
	MyMultivariateVectorialFunction(double[][] data, double[][] EXTRA_DATA, int[] row)
	{
		rowCount = data.length;
		colCount = data[0].length;
		inputData = data;
		
		this.EXTRA_DATA = EXTRA_DATA;
		this.row = row;
	}
	
	
	private double fnEval(double[] par, double x)
	{
		int Nfun = 1;
		double[] fn = new double[Nfun];

		double V1 = par[0];
		double K01 = par[1];
		double K10 = par[2];
		double K12 = par[3];
		double K21 = par[4];

		double A, B, C, alpha, beta;
		double Tstar;
		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;
		double conc = 0;
		double amt = 0;

		Dpo = 23158.0;
		double sq1 = Math.pow((K12 + K21 + K10), 2) - 4.0 * K10 * K21;
		beta = 0.5 * ((K12 + K21 + K10) - Math.sqrt(sq1));
		alpha = 0.5 * ((K12 + K21 + K10) + Math.sqrt(sq1));
		double coeff = K01 * Dpo / V1;

		double exp1 = Math.exp(-1.0 * alpha * t);
		double exp2 = Math.exp(-1.0 * beta * t);
		double exp3 = Math.exp(-1.0 * K01 * t);

		double deno1 = (alpha - beta) * (alpha - K01);
		double deno2 = (beta - K01) * (alpha - beta);
		double deno3 = (alpha - K01) * (beta - K01);

		double term1 = (K21 - alpha) * exp1 / deno1;
		double term2 = (beta - K21) * exp2 / deno2;
		double term3 = (K21 - K01) * exp3 / deno3;
		fn[0] = coeff * (term1 + term2 + term3);

		return fn[0];
	}
		
	public double[] value(double[] point)
	{
		PkParamEstimator pkpdMainInstance = PkParamEstimator.createPkParamEstimateInstance();
		double[] val = new double[rowCount];
		int fn_no = 0;
		try {
			
			double objFuncVal = 0.0;
			LibraryModelDefiner libModelInst=new LibraryModelDefiner();
			for (int i = 0; i < inputData.length; i++) {
				double x = inputData[i][1];
				//double fEval = fnEval(point, x);
				double fEval = libModelInst.chooseLibraryModel(point,
						pkpdMainInstance.infusionTime, pkpdMainInstance.dose,
						pkpdMainInstance.dosingTime, x, fn_no, EXTRA_DATA, i, row);
				
				val[i] = fEval;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return val;
	}

}
