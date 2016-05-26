package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import view.ApplicationInfo;

public class ClearanceLibModelDefiner {

	int modelNumber;
	
	PkPdInfo pkPdInst;
	
	public double chooseAlgebraicModel(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		pkPdInst = PkPdInfo.createPKPDInstance();
		modelNumber = pkPdInst.modelNumber;

		double value = 0;
		if (modelNumber == 1)
			value = func_diff1(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 2)
			value = func_diff2(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 3)
			value = func_diff3(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 4)
			value = func_diff4(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 5)
			value = func_diff5(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 6)
			value = func_diff6(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 7)
			value = func_diff7(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		
		else if (modelNumber == 9)
			value = func_diff9(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		
		else if (modelNumber == 11)
			value = func_diff11(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 12)
			value = func_diff12(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		
		else if (modelNumber == 15)
			value = func_diff15(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 16)
			value = func_diff16(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		

		return value;

	}

	private double func_diff1(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double[] fn = new double[1];
		double V = par[0];
		double cl = par[1];
		int NDose = conDose.length;
		double Dose;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;

		double K10 = cl / V;
		

			int i = 0;
			do {
				i++;
			} while (i < NDose && x >= conDosingTime[i]);

			int ndose = i - 1;

			for (int j = 0; j <= ndose; j++) {

				Dose = conDose[j];
				tr = t - conDosingTime[j];

				double amt = 0;
				amt = Dose * (Math.exp(-1.0 * K10 * tr)) / V;
				res = Math.max(0, amt);
				fn[0] = fn[0] + res;

			}
		
		return fn[fn_no];
	}

	private double func_diff2(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[1];
		double V = par[0];
		double cl = par[1];
		double Dose;
		double Tinf;
		int NDose = conDose.length;
		double t = x;
		double Tstar;
		double tr = 0;
		double res = 0;
		
		double K10 = cl / V;

		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose ; j++) {

			Dose = conDose[j];
			tr = t - conDosingTime[j];
			Tinf = infusionTime[j] - conDosingTime[j];
			Tstar = tr - Tinf;
			double amt = 0;
			if (Tstar < 0)
				Tstar = 0;

			amt = (Dose / Tinf)
					* (1 / (V * K10))
					* (Math.exp(-1.0 * K10 * Tstar) - Math.exp(-1.0 * K10 * tr));
			res = Math.max(0, amt);
			fn[0] = fn[0] + res;

		}

		
		return fn[fn_no];
	}

	private double func_diff3(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[1];
		double V_f = par[0];
		double K01 = par[1];
		double cl = par[2];
		double NDose = conDose.length;
		double Dpo;
		double tp = 0;
		double tr = 0;
		double res = 0;
		double tau = 24;
		double K10 = cl / V_f;
		
	

			int i = 0;
			do {
				i++;
			} while (i < NDose && x >= conDosingTime[i]);

			int ndose = i - 1;
			for (int j = 0; j <= ndose; j++) {
				Dpo = conDose[j];
				tr = x - conDosingTime[j];
				double amt = 0;

				if (tr > 0) {
					amt = K01
							* Dpo
							* (Math.exp(-1.0 * K10 * tr) - Math.exp(-1.0 * K01
									* tr)) / (V_f * (K01 - K10));
					res = amt;
					fn[0] = fn[0] + res;
				}

			}

		return fn[0];
	}

	private double func_diff4(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[1];
		double V_f = par[0];
		double K01 = par[1];
		double cl = par[2];
		double t_lag = par[3];
		double NDose = conDose.length;
		double Dpo;
		double tp = 0;
		double tr = 0;
		double res = 0;
		double tau = 24;

		double K10 = cl / V_f;
		
		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			Dpo = conDose[j];
			tr = x - conDosingTime[j];

			double amt = 0;

			// * if((tr - t_lag) == 0) t_lag = t_lag - 0.1 * t_lag;

			amt = K01
					* Dpo
					* (Math.exp(-1.0 * K10 * (tr - t_lag)) - Math.exp(-1.0
							* K01 * (tr - t_lag))) / (V_f * (K01 - K10));
			res = Math.max(0, amt);
			fn[0] = fn[0] + res;

		}

		
		return fn[0];
	}

	private double func_diff5(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[1];
		double V_f = par[0];
		double cl = par[1];
		int NDose = conDose.length;
		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;
		double K = cl / V_f;

		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			Dpo = conDose[j];
			tr = t - conDosingTime[j];
			double amt = 0;

			amt = Dpo * K * tr * Math.exp(-1.0 * K * tr) / V_f;
			res = Math.max(0, amt);
			fn[0] = fn[0] + res;
		}
		
		return fn[fn_no];
	}

	private double func_diff6(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[1];
		double V_f = par[0];
		double cl = par[1];
		double t_lag = par[2];
		int NDose = conDose.length;
		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;
		double K = cl / V_f;

		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			Dpo = conDose[j];
			tr = t - conDosingTime[j];
			double amt = 0;

			amt = Dpo * K * (tr - t_lag) * Math.exp(-1.0 * K * (tr - t_lag))
					/ V_f;
			res = Math.max(0, amt);
			fn[0] = fn[0] + res;

		}

		return fn[fn_no];
	}

	private double func_diff7(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double V1 = par[0];
		double cl = par[1];
		double V2 = par[2];
		double cld2 = par[3];
		double A, B, alpha, beta;
		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;
		int NDose = conDose.length;

		double K10 = cl / V1;
		double K12 = cld2/ V1;
		double K21 = V1 * K12 / V2;
		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			Dpo = conDose[j];
			tr = t - conDosingTime[j];
			double amt = 0;
			beta = (K12 + K21 + K10 - Math.sqrt(Math.pow(K12 + K21 + K10, 2)
					- 4 * K21 * K10)) / 2;
			alpha = (K12 + K21 + K10 + Math.sqrt(Math.pow(K12 + K21 + K10, 2)
					- 4 * K21 * K10)) / 2;
			A = (Dpo / V1) * ((alpha - K21) / (alpha - beta));
			B = (Dpo / V1) * ((beta - K21) / (beta - alpha));

			amt = A * Math.exp(-1.0 * alpha * tr) + B
					* Math.exp(-1.0 * beta * tr);
			res = Math.max(0, amt);
			fn[0] = fn[0] + res;

		}

		
		return fn[fn_no];
	}

	

	private double func_diff9(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int Nfun = 1;
		double[] fn = new double[Nfun];

		double V1 = par[0];
		double cl = par[1];
		double V2 = par[2];
		double cld2 = par[3];

		double Tinf;
		double A, B, alpha, beta;
		double Tstar;
		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;
		int NDose = conDose.length;
		
		double K10 = cl / V1;
		double K12 = cld2/ V1;
		double K21 = V1 * K12 / V2;

		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			Dpo = conDose[j];
			tr = t - conDosingTime[j];
			Tinf = infusionTime[j] - conDosingTime[j];
			Tstar = tr - Tinf;
			double amt = 0;
			if (Tstar < 0)
				Tstar = 0;

			beta = (K12 + K21 + K10 - Math.sqrt(Math.pow(K12 + K21 + K10, 2)
					- 4 * K21 * K10)) / 2;
			alpha = (K12 + K21 + K10 + Math.sqrt(Math.pow(K12 + K21 + K10, 2)
					- 4 * K21 * K10)) / 2;
			A = (Dpo / (Tinf * V1)) * (K21 - alpha) / ((alpha - beta) * alpha);
			B = (-1.0 * Dpo / (Tinf * V1)) * (K21 - beta)
					/ ((alpha - beta) * beta);

			amt = A
					* (Math.exp(-1.0 * alpha * tr) - Math.exp(-1.0 * alpha
							* Tstar))
					+ B
					* (Math.exp(-1.0 * beta * tr) - Math.exp(-1.0 * beta
							* Tstar));
			res = Math.max(0, amt);
			fn[0] = fn[0] + res;

		}

		return fn[fn_no];
	}

	
	private double func_diff11(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int Nfun = 1;
		double[] fn = new double[Nfun];
		double V1 = par[0];
		double K01 = par[1];
		double cl_f = par[2];
		double V2_f = par[3];
		double cld2_f = par[4];

		double A, B, C, alpha, beta;

		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;

		double amt = 0;
		int NDose = conDose.length;
		
		double K10 = cl_f / V1;
		double K12 = cld2_f/ V1;
		double K21 = V1 * K12 / V2_f;

		
		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			

			Dpo = conDose[j];
			tr = t - conDosingTime[j];

			
				double sq1 = Math.pow((K12 + K21 + K10), 2) - 4.0 * K10
						* K21;
				beta = 0.5 * ((K12 + K21 + K10) - Math.sqrt(sq1));
				alpha = 0.5 * ((K12 + K21 + K10) + Math.sqrt(sq1));
				double coeff = K01 * Dpo / V1;

				double exp1 = Math.exp(-1.0 * alpha * t);
				double exp2 = Math.exp(-1.0 * beta * t);
				double exp3 = Math.exp(-1.0 * K01 * t);

				double deno1 = (alpha - beta) * (alpha - K01);
				double deno2 = (beta - K01) * (alpha - beta);
				double deno3 = (alpha - K01) * (beta - K01);

				double term1, term2, term3;
				term1 = (K21 - alpha) * exp1 / deno1;
				term2 = (beta - K21) * exp2 / deno2;
				term3 = (K21 - K01) * exp3 / deno3;

				amt = coeff * (term1 + term2 + term3);
				res = Math.max(0, amt);
				fn[0] = fn[0] + res;
			
		}
				
		return fn[fn_no];
	}

	private double func_diff12(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int Nfun = 1;
		double[] fn = new double[Nfun];
		double V1 = par[0];
		double K01 = par[1];
		double cl_f = par[2];
		double V2_f = par[3];
		double cld2_f = par[4];
		double t_lag = par[5];

		double A, B, C, alpha, beta;
		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;
		double amt = 0;
		int NDose = conDose.length;
		
		double K10 = cl_f / V1;
		double K12 = cld2_f/ V1;
		double K21 = V1 * K12 / V2_f;


		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;
		
		for (int j = 0; j <= ndose; j++) {
			

			Dpo = conDose[j];
			tr = t - conDosingTime[j];
			if (tr <= t_lag)
				amt = 0;
			else {

				
					alpha = (K12 + K21 + K10 + Math.sqrt(Math.pow(K12 + K21
							+ K10, 2)
							- 4 * K21 * K10)) / 2;
					beta = (K12 + K21 + K10 - Math.sqrt(Math.pow(K12 + K21
							+ K10, 2)
							- 4 * K21 * K10)) / 2;

					A = (Dpo / V1)
							* K01
							* ((K21 - alpha) / (alpha - beta) * (alpha - K01));
					B = (-1.0 * Dpo / V1)
							* K01
							* ((K21 - beta) / (alpha - beta) * (beta - K01));
					C = (Dpo / V1) * K01
							* ((K21 - K01) / (beta - K01) * (alpha - K01));

					amt = A * (Math.exp(-1.0 * alpha * (tr - t_lag))) + B
							* (Math.exp(-1.0 * beta * (tr - t_lag))) + C
							* (Math.exp(-1.0 * K01 * (tr - t_lag)));
				
			}

			res = Math.max(0, amt);
			fn[0] = fn[0] + res;
		
		}
	
		return fn[fn_no];
	}

	

	private double func_diff15(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int Nfun = 1;
		double[] fn = new double[Nfun];
		double V = par[0];
		double cl = par[1];
		double K10 = cl/V;

		double Db = conDose[0];
		double Div = pkPdInst.infusionDose[0];
		double Tinf = infusionTime[0];
		double t = x;

		double Tstar;
		double tp = 0;
		tp = t - conDosingTime[0];

		double Cb = (Db / V) * (Math.exp(-1.0 * K10 * tp));
		double Civ = 0;
		Tstar = tp - Tinf;
		if (Tstar < 0)
			Tstar = 0;
		Civ = (Div / (Tinf * V * K10))
				* (Math.exp(-1.0 * K10 * Tstar) - Math.exp(-1.0 * K10 * tp));

		fn[0] = Cb + Civ;

		
		return fn[fn_no];
	}

	private double func_diff16(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int Nfun = 1;
		double[] fn = new double[Nfun];

		double V1 = par[0];
		double cl = par[1];
		double V2 = par[2];
		double cld2 = par[3];
		
		double K10 = cl / V1;
		double K12 = cld2/ V1;
		double K21 = V1 * K12 / V2;

		double Tinf;

		double A, B, alpha, beta;
		double Tstar;
		double Dpo;
		double t = x;

		double tp = 0;
		double Db = conDose[0];
		double Div = pkPdInst.infusionDose[0];
		Tinf = infusionTime[0];
		tp = t - conDosingTime[0];

		beta = (K12 + K21 + K10 - Math.sqrt(Math.pow(K12 + K21 + K10, 2) - 4
				* K21 * K10)) / 2;
		alpha = (K12 + K21 + K10 + Math.sqrt(Math.pow(K12 + K21 + K10, 2) - 4
				* K21 * K10)) / 2;
		A = (Db / V1) * ((alpha - K21) / (alpha - beta));
		B = (-1.0 * Db / V1) * ((beta - K21) / (alpha - beta));

		double Cb = A * Math.exp(-1.0 * alpha * tp) + B
				* Math.exp(-1.0 * beta * tp);
		double Civ = 0;

		tp = t - conDosingTime[0];
		Tstar = tp - Tinf;

		A = (Div / (Tinf * V1)) * (K21 - alpha) / ((alpha - beta) * alpha);
		B = (-1.0 * Div / (Tinf * V1)) * (K21 - beta) / ((alpha - beta) * beta);

		if (Tstar < 0)
			Tstar = 0;
		Civ = A
				* (Math.exp(-1.0 * alpha * tp) - Math.exp(-1.0 * alpha * Tstar))
				+ B
				* (Math.exp(-1.0 * beta * tp) - Math.exp(-1.0 * beta * Tstar));
		fn[0] = Cb + Civ;

		
		return fn[fn_no];
	}
	

}
