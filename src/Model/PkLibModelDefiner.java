package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PkLibModelDefiner {
	


	int modelNumber;

	boolean clearanceParam;
	PkPdInfo pkPdInst;
	ClearanceLibModelDefiner clearanceLibModelInst;
	DeLibModelDefiner deLibModelInst;

	
	public double choosePkModel(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double value = 0;
		pkPdInst = PkPdInfo.createPKPDInstance();
		

		modelNumber = pkPdInst.modelNumber;

		boolean algebraicModel = pkPdInst.ifAlgebraicModel;

		clearanceParam = pkPdInst.ifClearanceParam;

		if (algebraicModel == true) {

			if (clearanceParam == false) {
				value = chooseAlgebraicModel(par, infusionTime, conDose,
						conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);
			} else {
				clearanceLibModelInst = new ClearanceLibModelDefiner();
				value = clearanceLibModelInst.chooseAlgebraicModel(par, infusionTime, conDose,
						conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);
			}

			if (Double.isInfinite(value) == true || Double.isNaN(value) == true) {

				pkPdInst.exceptionForCurrentProfile = 1;

			}
			

		} else

		{
			deLibModelInst = new DeLibModelDefiner();
			value = deLibModelInst.chooseLibraryModel(par, infusionTime, conDose,
					conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);
		}

		return value;

	}

	public double chooseAlgebraicModel(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
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
		else if (modelNumber == 8)
			value = func_diff8(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 9)
			value = func_diff9(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 10)
			value = func_diff10(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 11)
			value = func_diff11(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 12)
			value = func_diff12(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 13)
			value = func_diff13(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 14)
			value = func_diff14(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 15)
			value = func_diff15(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 16)
			value = func_diff16(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 17)
			value = func_diff17(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 18)
			value = func_diff18(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 19)
			value = func_diff19(par, infusionTime, conDose, conDosingTime, x,
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
		double K10 = par[1];
		int NDose = conDose.length;
		double Dose;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;

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
		double K10 = par[1];
		double Dose;
		double Tinf;
		int NDose = conDose.length;
		double t = x;
		double Tstar;
		double tr = 0;
		double res = 0;

		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

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
		double K10 = par[2];
		double NDose = conDose.length;
		double Dpo;
		// double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;
		double tau = 24;

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
						* (Math.exp(-1.0 * K10 * tr) - Math
								.exp(-1.0 * K01 * tr)) / (V_f * (K01 - K10));
				res = Math.max(0, amt);
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
		double K10 = par[2];
		double t_lag = par[3];
		double NDose = conDose.length;
		double Dpo;
		double tp = 0;
		double tr = 0;
		double res = 0;
		double tau = 24;

		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			Dpo = conDose[j];
			tr = x - conDosingTime[j];

			double amt = 0;

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
		double K = par[1];
		int NDose = conDose.length;
		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;

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
		double K = par[1];
		double t_lag = par[2];
		int NDose = conDose.length;
		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;

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
		double K10 = par[1];
		double K12 = par[2];
		double K21 = par[3];
		double A, B, alpha, beta;
		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;
		int NDose = conDose.length;

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

	private double func_diff8(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double A = par[0];
		double B = par[1];
		double alpha = par[2];
		double beta = par[3];

		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;
		int NDose = conDose.length;

		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			double A1 = A / conDose[0];
			double B1 = B / conDose[0];

			Dpo = conDose[j];
			tr = t - conDosingTime[j];
			double amt = 0;

			amt = A1 * Math.exp(-1.0 * alpha * tr) + B1
					* Math.exp(-1.0 * beta * tr);
			res = Math.max(0, Dpo * amt);
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
		double K10 = par[1];
		double K12 = par[2];
		double K21 = par[3];

		double Tinf;
		double A, B, alpha, beta;
		double Tstar;
		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;
		int NDose = conDose.length;

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

	private double func_diff10(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int Nfun = 1;
		double[] fn = new double[Nfun];
		double V1 = par[0];
		double K21 = par[1];
		double alpha = par[2];
		double beta = par[3];
		double Tinf;

		double A, B;
		double Tstar;
		double Dpo;
		double t = x;
		// double tp = 0;
		double tr = 0;
		double res = 0;
		int NDose = conDose.length;

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

			A = (Dpo / Tinf) * (K21 - alpha) / ((beta - alpha) * alpha * V1);
			B = (Dpo / Tinf) * (K21 - beta) / ((alpha - beta) * beta * V1);
			amt = A
					* (Math.exp(-1.0 * alpha * Tstar) - Math.exp(-1.0 * alpha
							* tr))
					+ B
					* (Math.exp(-1.0 * beta * Tstar) - Math.exp(-1.0 * beta
							* tr));
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
		double K10 = par[2];
		double K12 = par[3];
		double K21 = par[4];

		double A, B, C, alpha, beta;

		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;

		double amt = 0;
		int NDose = conDose.length;

		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			Dpo = conDose[j];
			tr = t - conDosingTime[j];

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
		double K10 = par[2];
		double K12 = par[3];
		double K21 = par[4];
		double t_lag = par[5];

		double A, B, C, alpha, beta;
		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;
		double amt = 0;
		int NDose = conDose.length;

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

				alpha = (K12 + K21 + K10 + Math.sqrt(Math.pow(K12 + K21 + K10,
						2)
						- 4 * K21 * K10)) / 2;
				beta = (K12 + K21 + K10 - Math.sqrt(Math
						.pow(K12 + K21 + K10, 2)
						- 4 * K21 * K10)) / 2;

				A = (Dpo / V1) * K01
						* ((K21 - alpha) / (alpha - beta) * (alpha - K01));
				B = (-1.0 * Dpo / V1) * K01
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

	private double func_diff13(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int Nfun = 1;
		double[] fn = new double[Nfun];
		double A = par[0];
		double B = par[1];
		double K01 = par[2];
		double alpha = par[3];
		double beta = par[4];
		double C = (-1) * (A + B);

		double Dpo;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;
		double amt = 0;
		int NDose = conDose.length;
		double A1, B1;
		A1 = A / conDose[0];
		B1 = B / conDose[0];
		C = (-1) * (A1 + B1);

		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			Dpo = conDose[j];
			tr = t - conDosingTime[j];

			amt = A1 * (Math.exp(-1.0 * alpha * tr)) + B1
					* (Math.exp(-1.0 * beta * tr)) + C
					* (Math.exp(-1.0 * K01 * tr));
			res = Math.max(0, Dpo * amt);
			fn[0] = fn[0] + res;

		}

		return fn[fn_no];
	}

	private double func_diff14(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int Nfun = 1;
		double[] fn = new double[Nfun];

		double A = par[0];
		double B = par[1];
		double K01 = par[2];
		double alpha = par[3];
		double beta = par[4];
		double t_lag = par[5];

		double Dpo = 0;
		double t = x;
		double tp = 0;
		double tr = 0;
		double res = 0;
		double amt = 0;
		int NDose = conDose.length;
		double A1, B1;
		double C;
		A1 = A / conDose[0];
		B1 = B / conDose[0];
		C = (-1) * (A1 + B1);

		int i = 0;
		do {
			i++;
		} while (i < NDose && x > conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			Dpo = conDose[j];
			tr = t - conDosingTime[j] - t_lag;

			amt = A1 * (Math.exp(-1.0 * alpha * (tr))) + B1
					* (Math.exp(-1.0 * beta * (tr))) + C
					* (Math.exp(-1.0 * K01 * (tr)));
			res = Math.max(0, amt * Dpo);
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
		double K10 = par[1];

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
		double K10 = par[1];
		double K12 = par[2];
		double K21 = par[3];

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

	private double func_diff17(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int Nfun = 1;
		double[] fn = new double[Nfun];

		double A = par[0];
		double B = par[1];
		double alpha = par[2];
		double beta = par[3];
		double Tinf;

		double t = x;
		double Db = conDose[0];
		double Div = pkPdInst.infusionDose[0];
		double vol = 0;
		double K10, K12, K21;
		Tinf = infusionTime[0];

		t = t - conDosingTime[0];

		double A1 = A / Db;
		double B1 = B / Db;

		double Cb = Db
				* (A1 * Math.exp(-1.0 * alpha * t) + B1
						* Math.exp(-1.0 * beta * t));
		double Civ = 0;

		K21 = (A1 * beta + B1 * alpha) / (A1 + B1);
		K10 = alpha * beta / K21;
		K12 = alpha + beta - K10 - K21;
		vol = Db / ((A1 + B1) * Db);
		if (t <= Tinf) {

			double ac = (Div / Tinf) / (vol * K10);
			double bc = (beta - K10) / (alpha - beta);
			double cc = (K10 - alpha) / (alpha - beta);
			Civ = ac
					* (1 + bc * Math.exp(-1.0 * alpha * t) + cc
							* Math.exp(-1.0 * beta * t));
		} else {
			double ac = (Div / Tinf) * (K21 - alpha)
					/ (vol * alpha * (alpha - beta));
			double bc = (Div / Tinf) * (beta - K21)
					/ (vol * beta * (alpha - beta));
			ac = ac * (Math.exp(-1.0 * alpha * Tinf) - 1.0);
			bc = bc * (Math.exp(-1.0 * beta * Tinf) - 1.0);
			double Tstar = t - Tinf;
			Civ = ac * Math.exp(-1.0 * alpha * Tstar) + bc
					* Math.exp(-1.0 * beta * Tstar);

		}
		fn[0] = Cb + Civ;

		return fn[fn_no];
	}

	private double func_diff18(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int Nfun = 1;
		double[] fn = new double[Nfun];
		double A = par[0];
		double B = par[1];
		double C = par[2];
		double alpha = par[3];
		double beta = par[4];
		double gamma = par[5];
		double NDose = conDose.length;
		double A1, B1, C1;
		double amt;
		double Dpo;

		double t = x;
		double tp = 0;
		double res = 0;

		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			A1 = A / conDose[j];
			B1 = B / conDose[j];
			C1 = C / conDose[j];
			Dpo = conDose[j];
			tp = t - conDosingTime[j];
			amt = A1 * Math.exp(-1.0 * alpha * tp) + B1
					* Math.exp(-1.0 * beta * tp) + C1
					* Math.exp(-1.0 * gamma * tp);
			amt = Dpo * amt;

			res = Math.max(0, amt);
			fn[0] = fn[0] + res;

		}

		return fn[fn_no];
	}

	private double func_diff19(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int Nfun = 1;
		double[] fn = new double[Nfun];
		double V1 = par[0];
		double K21 = par[1];
		double K31 = par[2];
		double alpha = par[3];
		double beta = par[4];
		double gamma = par[5];
		double NDose = conDose.length;

		double Dpo;
		double A, B, C;
		double t = x;
		double tp = 0;
		// double tr = 0;
		double res = 0;
		double conc = 0;
		double Tinf, Tstar;

		if((alpha== beta)|| (alpha == gamma) || (beta == gamma))
			System.out.println(" two vales are same");
		
		int i = 0;
		do {
			i++;
		} while (i < NDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			Dpo = conDose[j];
			tp = t - conDosingTime[j];
			Tinf = infusionTime[j] - conDosingTime[0];
			Tstar = tp - Tinf;
			double amt = 0;
			if (Tstar < 0)
				Tstar = 0;

			A = (((Dpo / Tinf) * (K21 - alpha) * (K31 - alpha)) / (V1 * alpha
					* (gamma - alpha) * (beta - alpha)));
			B = (((Dpo / Tinf) * (K21 - beta) * (K31 - beta)) / (V1 * beta
					* (gamma - beta) * (alpha - beta)));
			C = (((Dpo / Tinf) * (K21 - gamma) * (K31 - gamma)) / (V1 * gamma
					* (alpha - gamma) * (beta - gamma)));

			amt = A
					* (Math.exp(-1.0 * alpha * Tstar) - Math.exp(-1.0 * alpha
							* tp))
					+ B
					* (Math.exp(-1.0 * beta * Tstar) - Math.exp(-1.0 * beta
							* tp))
					+ C
					* (Math.exp(-1.0 * gamma * Tstar) - Math.exp(-1.0 * gamma
							* tp));
			res = Math.max(0, amt);
			fn[0] = fn[0] + res;

		}

		/*
		 * if (NDose == 1) { Dpo = conDose[0]; tp = t - conDosingTime[0]; Tinf =
		 * infusionTime[0] - conDosingTime[0]; Tstar = tp - Tinf; if (Tstar < 0)
		 * Tstar = 0; A = (((Dpo / Tinf) * (K21 - alpha) * (K31 - alpha)) / (V1
		 * * alpha (gamma - alpha) * (beta - alpha))); B = (((Dpo / Tinf) * (K21
		 * - beta) * (K31 - beta)) / (V1 * beta (gamma - beta) * (alpha -
		 * beta))); C = (((Dpo / Tinf) * (K21 - gamma) * (K31 - gamma)) / (V1 *
		 * gamma (alpha - gamma) * (beta - gamma)));
		 * 
		 * fn[0] = A (Math.exp(-1.0 * alpha * Tstar) - Math.exp(-1.0 * alpha
		 * tp)) + B (Math.exp(-1.0 * beta * Tstar) - Math.exp(-1.0 * beta tp)) +
		 * C (Math.exp(-1.0 * gamma * Tstar) - Math.exp(-1.0 * gamma tp));
		 * 
		 * } else { for (int j = 0; j < NDose; j++) { Dpo = conDose[j]; tp = t -
		 * conDosingTime[j]; Tinf = infusionTime[j] - conDosingTime[0]; Tstar =
		 * tp - Tinf; double amt = 0; if (Tstar < 0) Tstar = 0;
		 * 
		 * if (tp >= 0) { A = (((Dpo / Tinf) * (K21 - alpha) * (K31 - alpha)) /
		 * (V1 alpha * (gamma - alpha) * (beta - alpha))); B = (((Dpo / Tinf) *
		 * (K21 - beta) * (K31 - beta)) / (V1 beta * (gamma - beta) * (alpha -
		 * beta))); C = (((Dpo / Tinf) * (K21 - gamma) * (K31 - gamma)) / (V1
		 * gamma * (alpha - gamma) * (beta - gamma)));
		 * 
		 * amt = A (Math.exp(-1.0 * alpha * Tstar) - Math.exp(-1.0 alpha * tp))
		 * + B (Math.exp(-1.0 * beta * Tstar) - Math.exp(-1.0 beta * tp)) + C
		 * (Math.exp(-1.0 * gamma * Tstar) - Math.exp(-1.0 gamma * tp)); res =
		 * res + Math.max(0, amt); fn[0] = fn[0] + res; } else break; }
		 * 
		 * }
		 */
		return fn[fn_no];
	}



}
