package Model;

import java.io.IOException;

import pkpd_library.integration.DFunction;
import pkpd_library.integration.ODEsolver;

import view.ProcessingInputsInfo;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class EffectSiteConcCalculation1 implements DFunction {

	int modelNumber;

	boolean clearanceParam;

	double[] linkParam;

	double Ke0;
	ProcessingInputsInfo procInputInst;
	boolean ifSimulation;
	int odeSolverChoice;
	double[] parameter;
	double Cp;
	double[] conDose;
	double[] conDosingTime;
	double[] infusionTime;
	double[] infusionDose;

	public double chooseLibraryModel(ProcessingInputsInfo processInput, double[] param, double par,
			double[] infusionTime, double[] conDose, double[] conDosingTime,
			double x, int fn_no, double[][] Extra_DATA, int rowIdx, int[] row,
			double[] conInfusionDose) throws RowsExceededException,
			WriteException, BiffException, IOException {

		procInputInst = processInput;
		ifSimulation = procInputInst.getModelInputTab1Obj().isSimulation();
		odeSolverChoice = procInputInst.getModelInputTab2Obj()
				.getOdeSolverMethod() + 1;
		this.conDose = conDose;
		this.conDosingTime = conDosingTime;
		this.infusionTime = infusionTime;

		infusionDose = conInfusionDose;
		linkParam = param;

		this.parameter = linkParam;
		Ke0 = par;
		double value = 0;

		value = choosePkModel(linkParam, infusionTime, conDose, conDosingTime,
				x, fn_no, Extra_DATA, rowIdx, row);

		return value;
	}

	public double choosePkModel(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double value = 0;
		modelNumber = procInputInst.getModelInputTab1Obj()
				.getModelNumberForLinkModel();

		value = chooseEffectSiteModel(par, infusionTime, conDose,
				conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);

		return value;

	}

	public double chooseEffectSiteModel(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		// modelNumber = pkPdInst.modelNumber;

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

	private double[] solvingODE(double h, double t0, double t, double[] C0,
			double[] Yc) {

		if (odeSolverChoice == 1)
			Yc = ODEsolver.RKF(this, t0, C0, t, h, 1e-4, 0.1, 1e-5, 1e-5);
		if (odeSolverChoice == 2)
			Yc = ODEsolver.RKC(this, t0, C0, t, h, 1e-4, 0.1, 1e-5, 1e-5);

		if (odeSolverChoice == 3)
			Yc = ODEsolver.RK4(this, t0, C0, t, h);
		if (odeSolverChoice == 4)
			Yc = ODEsolver.RKG(this, t0, C0, t, h);
		if (odeSolverChoice == 5)
			Yc = ODEsolver.APC(this, t0, C0, t, h);
		if (odeSolverChoice == 6)
			Yc = ODEsolver.Gear4(this, t0, C0, t, h);
		if (odeSolverChoice == 7)
			Yc = ODEsolver.Gear5(this, t0, C0, t, h);
		if (odeSolverChoice == 8)
			Yc = ODEsolver.Gear6(this, t0, C0, t, h);
		if (odeSolverChoice == 9)
			Yc = ODEsolver.Implicit_trap(this, t0, C0, t, h);
		if (odeSolverChoice == 10)
			Yc = ODEsolver.Explicit_Euler(this, t0, C0, t, h);
		if (odeSolverChoice == 11)
			Yc = ODEsolver.Implicit_Euler(this, t0, C0, t, h);

		return Yc;

	}

	@Override
	public double[] function(double t, double[] C) {

		double[] dydx = new double[C.length];
		// int modelNumber = pkPdInst.modelNumber;

		if (modelNumber == 1)
			dydx = function1(t, C);
		else if (modelNumber == 2)
			dydx = function2(t, C);
		/*
		 * else if (modelNumber == 3) dydx = function3(t, C); else if
		 * (modelNumber == 4) dydx = function4(t, C);
		 */
		else if (modelNumber == 5)
			dydx = function5(t, C);
		else if (modelNumber == 6)
			dydx = function6(t, C);
		/*
		 * else if (modelNumber == 7) dydx = function7(t, C); else if
		 * (modelNumber == 8) dydx = function8(t, C);
		 */
		else if (modelNumber == 9)
			dydx = function9(t, C);
		else if (modelNumber == 10)
			dydx = function10(t, C);
		/*
		 * else if (modelNumber == 11) dydx = function11(t, C); else if
		 * (modelNumber == 12) dydx = function12(t, C);
		 */
		else if (modelNumber == 13)
			dydx = function13(t, C);
		else if (modelNumber == 14)
			dydx = function14(t, C);
		else if (modelNumber == 15)
			dydx = function15(t, C);
		else if (modelNumber == 16)
			dydx = function16(t, C);
		else if (modelNumber == 17)
			dydx = function17(t, C);
		else if (modelNumber == 18)
			dydx = function18(t, C);
		else if (modelNumber == 19)
			dydx = function19(t, C);
		return dydx;
	}

	public double func_diff1(double[] par, double[] infusionTime,
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
			// amt = Dose * (Math.exp(-1.0 * K10 * tr)) / V;

			amt = (Dose * Ke0 / (V * (Ke0 - K10)))
					* (Math.exp(-1.0 * K10 * tr) - Math.exp(-1.0 * Ke0 * tr));
			res = Math.max(0, amt);
			fn[0] = fn[0] + res;

		}

		return fn[fn_no];
	}

	/*
	 * public double func_diff1(double[] par, double[] infusionTime, double[]
	 * conDose, double[] conDosingTime, double x, int fn_no, double[][]
	 * Extra_DATA, int rowIdx, int[] row) throws RowsExceededException,
	 * WriteException, BiffException, IOException {
	 * 
	 * double h = 0.01; // step size double t0 = 0;
	 * 
	 * double[] C0 = new double[1]; double[] Yc = new double[1];
	 * 
	 * int Nfun = 1; double[] fn = new double[Nfun]; double t = x;
	 * 
	 * double[] fn = new double[1]; double V = par[0]; double K10 = par[1]; int
	 * NDose = conDose.length; double Dose; double t = x; double tr = 0; double
	 * res = 0;
	 * 
	 * int i = 0; do { i++; } while (i < NDose && x >= conDosingTime[i]);
	 * 
	 * int ndose = i - 1;
	 * 
	 * for (int j = 0; j <= ndose; j++) {
	 * 
	 * Dose = conDose[j]; tr = t - conDosingTime[j];
	 * 
	 * double amt = 0; // amt = Dose * (Math.exp(-1.0 * K10 * tr)) / V;
	 * 
	 * amt = (Dose * Ke0 / (V * (Ke0 - K10))) (Math.exp(-1.0 * K10 * tr) -
	 * Math.exp(-1.0 * Ke0 * tr)); res = Math.max(0, amt); fn[0] = fn[0] + res;
	 * 
	 * }
	 * 
	 * Yc = solvingODE(h, t0, t, C0, Yc); fn[0] = Yc[0]; return fn[fn_no]; }
	 */

	public double[] function1(double t, double[] C) {

		double V = parameter[0];
		double K10 = parameter[1];
		int NDose = conDose.length;
		double Dose;

		double tr = 0;
		double res = 0;

		Cp = 0;
		double[] dydx = new double[C.length];
		double Ce = C[0];

		int i = 0;
		do {
			i++;
		} while (i < NDose && t >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			Dose = conDose[j];
			tr = t - conDosingTime[j];

			double amt = 0;
			// amt = Dose * (Math.exp(-1.0 * K10 * tr)) / V;

			amt = (Dose * Ke0 / (V * (Ke0 - K10)))
					* (Math.exp(-1.0 * K10 * tr) - Math.exp(-1.0 * Ke0 * tr));
			res = Math.max(0, amt);
			Cp = Cp + res;

		}

		dydx[0] = Ke0 * (Cp - Ce);

		return dydx;
	}

	public double func_diff2(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double h = 0.01; // step size
		double t0 = 0;

		double[] C0 = new double[1];
		double[] Yc = new double[1];

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double t = x;

		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = Yc[0];
		return fn[fn_no];
	}

	public double[] function2(double t, double[] C) {

		double V = parameter[0];
		double K10 = parameter[1];
		double Dose;
		double Tinf;
		int NDose = conDose.length;

		double Tstar;
		double tr = 0;
		double res = 0;

		Cp = 0;
		double[] dydx = new double[C.length];
		double Ce = C[0];

		int i = 0;
		do {
			i++;
		} while (i < NDose && t >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			Dose = conDose[j];
			tr = t - conDosingTime[j];
			Tinf = infusionTime[j] - conDosingTime[j];
			Tstar = tr - Tinf;
			double amt = 0;
			if (Tstar < 0)
				Tstar = 0;

			/*
			 * amt = (Dose / Tinf) Ke0 (1 / (V * K10 * (Ke0 - K10)))
			 * (Math.exp(-1.0 * K10 * Tstar) - Math.exp(-1.0 * K10 * tr)) +
			 * (Dose / Tinf) Ke0 (1 / (V * Ke0 * (K10 - Ke0))) (Math.exp(-1.0 *
			 * Ke0 * Tstar) - Math.exp(-1.0 * Ke0 * tr));
			 */

			amt = (Dose / Tinf)
					* (1 / (V * K10))
					* (Math.exp(-1.0 * K10 * Tstar) - Math.exp(-1.0 * K10 * tr));
			res = Math.max(0, amt);
			Cp = Cp + res;

		}

		dydx[0] = Ke0 * (Cp - Ce);

		return dydx;
	}

	/*
	 * public double func_diff3(double[] par, double[] infusionTime, double[]
	 * conDose, double[] conDosingTime, double x, int fn_no, double[][]
	 * Extra_DATA, int rowIdx, int[] row) throws RowsExceededException,
	 * WriteException, BiffException, IOException {
	 * 
	 * double h = 0.01; // step size double t0 = 0;
	 * 
	 * double[] C0 = new double[1]; double[] Yc = new double[1];
	 * 
	 * int Nfun = 1; double[] fn = new double[Nfun]; double t = x;
	 * 
	 * 
	 * Yc = solvingODE(h, t0, t, C0, Yc); fn[0] = Yc[0]; return fn[fn_no]; }
	 * 
	 * 
	 * public double[] function3(double t, double[] C){
	 * 
	 * double V_f = parameter[0]; double K01 = parameter[1]; double K10 =
	 * parameter[2]; double NDose = conDose.length; double Dpo; double tr = 0;
	 * double res = 0; int i = 0;
	 * 
	 * Cp = 0; double[] dydx = new double[C.length]; double Ce = C[0];
	 * 
	 * 
	 * do { i++; } while (i < NDose && t >= conDosingTime[i]);
	 * 
	 * int ndose = i - 1; for (int j = 0; j <= ndose; j++) { Dpo = conDose[j];
	 * tr = t - conDosingTime[j]; double amt = 0;
	 * 
	 * if (tr > 0) { amt = K01 Ke0 (Dpo / V_f) (Math.exp(-1.0 * K10 * tr) /
	 * ((K01 - K10) * (Ke0 - K10)) + Math.exp(-1.0 * K01 * tr) / ((K10 - K01) *
	 * (Ke0 - K01)) + Math.exp(-1.0 Ke0 * tr) / ((K10 - Ke0) * (K10 - Ke0)));
	 * res = Math.max(0, amt); Cp = Cp + res; }
	 * 
	 * }
	 * 
	 * dydx[0] = Ke0 * (Cp - Ce);
	 * 
	 * return dydx; }
	 * 
	 * 
	 * 
	 * public double func_diff4(double[] par, double[] infusionTime, double[]
	 * conDose, double[] conDosingTime, double x, int fn_no, double[][]
	 * Extra_DATA, int rowIdx, int[] row) throws RowsExceededException,
	 * WriteException, BiffException, IOException {
	 * 
	 * double h = 0.01; // step size double t0 = 0;
	 * 
	 * double[] C0 = new double[1]; double[] Yc = new double[1];
	 * 
	 * int Nfun = 1; double[] fn = new double[Nfun]; double t = x;
	 * 
	 * 
	 * Yc = solvingODE(h, t0, t, C0, Yc); fn[0] = Yc[0]; return fn[fn_no]; }
	 * 
	 * public double[] function4(double t, double[] C){
	 * 
	 * double V_f = parameter[0]; double K01 = parameter[1]; double K10 =
	 * parameter[2]; double t_lag = parameter[3]; double NDose = conDose.length;
	 * double Dpo; double tr = 0; double res = 0;
	 * 
	 * Cp = 0; double[] dydx = new double[C.length]; double Ce = C[0];
	 * 
	 * int i = 0; do { i++; } while (i < NDose && t >= conDosingTime[i]);
	 * 
	 * int ndose = i - 1;
	 * 
	 * for (int j = 0; j <= ndose; j++) { Dpo = conDose[j]; tr = t -
	 * conDosingTime[j];
	 * 
	 * double amt = 0;
	 * 
	 * amt = K01 Ke0 (Dpo / V_f) (Math.exp(-1.0 * K10 * (tr - t_lag)) / ((K01 -
	 * K10) * (Ke0 - K10)) + Math.exp(-1.0 * K01 * (tr - t_lag)) / ((K10 - K01)
	 * * (Ke0 - K01)) + Math.exp(-1.0 * Ke0 (tr - t_lag)) / ((K10 - Ke0) * (K10
	 * - Ke0))); res = Math.max(0, amt); Cp = Cp + res;
	 * 
	 * }
	 * 
	 * dydx[0] = Ke0 * (Cp - Ce);
	 * 
	 * return dydx; }
	 */

	public double func_diff3(double[] par, double[] infusionTime,
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
						* Ke0
						* (Dpo / V_f)
						* (Math.exp(-1.0 * K10 * tr)
								/ ((K01 - K10) * (Ke0 - K10))
								+ Math.exp(-1.0 * K01 * tr)
								/ ((K10 - K01) * (Ke0 - K01)) + Math.exp(-1.0
								* Ke0 * tr)
								/ ((K10 - Ke0) * (K01 - Ke0)));
				res = Math.max(0, amt);
				fn[0] = fn[0] + res;
			}

		}

		return fn[0];
	}

	public double func_diff4(double[] par, double[] infusionTime,
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
					* Ke0
					* (Dpo / V_f)
					* (Math.exp(-1.0 * K10 * (tr - t_lag))
							/ ((K01 - K10) * (Ke0 - K10))
							+ Math.exp(-1.0 * K01 * (tr - t_lag))
							/ ((K10 - K01) * (Ke0 - K01)) + Math.exp(-1.0 * Ke0
							* (tr - t_lag))
							/ ((K10 - Ke0) * (K01 - Ke0)));
			res = Math.max(0, amt);
			fn[0] = fn[0] + res;

		}

		return fn[0];
	}

	public double func_diff5(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double h = 0.01; // step size
		double t0 = 0;

		double[] C0 = new double[1];
		double[] Yc = new double[1];

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double t = x;

		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = Yc[0];
		return fn[fn_no];
	}

	public double[] function5(double t, double[] C) {

		double V_f = parameter[0];
		double K = parameter[1];
		int NDose = conDose.length;
		double Dpo;

		double tr = 0;
		double res = 0;

		Cp = 0;
		double[] dydx = new double[C.length];
		double Ce = C[0];

		int i = 0;
		do {
			i++;
		} while (i < NDose && t >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			Dpo = conDose[j];
			tr = t - conDosingTime[j];
			double amt = 0;

			amt = Dpo * K * tr * Math.exp(-1.0 * K * tr) / V_f;
			res = Math.max(0, amt);
			Cp = Cp + res;
		}

		dydx[0] = Ke0 * (Cp - Ce);
		return dydx;
	}

	public double func_diff6(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double h = 0.01; // step size
		double t0 = 0;

		double[] C0 = new double[1];
		double[] Yc = new double[1];

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double t = x;

		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = Yc[0];
		return fn[fn_no];
	}

	public double[] function6(double t, double[] C) {

		double V_f = parameter[0];
		double K = parameter[1];
		double t_lag = parameter[2];
		int NDose = conDose.length;
		double Dpo;

		double tr = 0;
		double res = 0;

		Cp = 0;
		double[] dydx = new double[C.length];
		double Ce = C[0];

		int i = 0;
		do {
			i++;
		} while (i < NDose && t >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			Dpo = conDose[j];
			tr = t - conDosingTime[j];
			double amt = 0;

			amt = Dpo * K * (tr - t_lag) * Math.exp(-1.0 * K * (tr - t_lag))
					/ V_f;
			res = Math.max(0, amt);
			Cp = Cp + res;

		}

		dydx[0] = Ke0 * (Cp - Ce);
		return dydx;
	}

	/*
	 * public double func_diff7(double[] par, double[] infusionTime, double[]
	 * conDose, double[] conDosingTime, double x, int fn_no, double[][]
	 * Extra_DATA, int rowIdx, int[] row) throws RowsExceededException,
	 * WriteException, BiffException, IOException {
	 * 
	 * double h = 0.01; // step size double t0 = 0;
	 * 
	 * double[] C0 = new double[1]; double[] Yc = new double[1];
	 * 
	 * int Nfun = 1; double[] fn = new double[Nfun]; double t = x;
	 * 
	 * 
	 * Yc = solvingODE(h, t0, t, C0, Yc); fn[0] = Yc[0]; return fn[fn_no]; }
	 */

	/*
	 * public double[] function7(double t, double[] C){
	 * 
	 * 
	 * double V1 = parameter[0]; double K10 = parameter[1]; double K12 =
	 * parameter[2]; double K21 = parameter[3]; double A, B, C1, alpha, beta;
	 * double Div;
	 * 
	 * double tr = 0; double res = 0; int NDose = conDose.length;
	 * 
	 * Cp = 0; double[] dydx = new double[C.length]; double Ce = C[0];
	 * 
	 * int i = 0; do { i++; } while (i < NDose && t >= conDosingTime[i]);
	 * 
	 * int ndose = i - 1;
	 * 
	 * for (int j = 0; j <= ndose; j++) {
	 * 
	 * Div = conDose[j]; tr = t - conDosingTime[j]; double amt = 0; beta = (K12
	 * + K21 + K10 - Math.sqrt(Math.pow(K12 + K21 + K10, 2) - 4 * K21 * K10)) /
	 * 2; alpha = (K12 + K21 + K10 + Math.sqrt(Math.pow(K12 + K21 + K10, 2) - 4
	 * * K21 * K10)) / 2; A = (Ke0 * Div / V1) ((K21 - alpha) / ((Ke0 - alpha) *
	 * (beta - alpha))); B = (Ke0 * Div / V1) ((K21 - beta) / ((Ke0 - beta) *
	 * (alpha - beta))); C1 = (Ke0 * Div / V1) ((K21 - Ke0) / ((alpha - Ke0) *
	 * (beta - Ke0)));
	 * 
	 * amt = A * Math.exp(-1.0 * alpha * tr) + B Math.exp(-1.0 * beta * tr) + C1
	 * Math.exp(-1.0 * Ke0 * tr);
	 * 
	 * res = Math.max(0, amt); Cp = Cp + res;
	 * 
	 * }
	 * 
	 * dydx[0] = Ke0 * (Cp - Ce); return dydx; }
	 * 
	 * public double func_diff8(double[] par, double[] infusionTime, double[]
	 * conDose, double[] conDosingTime, double x, int fn_no, double[][]
	 * Extra_DATA, int rowIdx, int[] row) throws RowsExceededException,
	 * WriteException, BiffException, IOException {
	 * 
	 * double h = 0.01; // step size double t0 = 0;
	 * 
	 * double[] C0 = new double[1]; double[] Yc = new double[1];
	 * 
	 * int Nfun = 1; double[] fn = new double[Nfun]; double t = x;
	 * 
	 * 
	 * Yc = solvingODE(h, t0, t, C0, Yc); fn[0] = Yc[0]; return fn[fn_no]; }
	 * 
	 * 
	 * public double[] function8(double t, double[] C){
	 * 
	 * 
	 * double A = parameter[0]; double B = parameter[1]; double alpha =
	 * parameter[2]; double beta = parameter[3];
	 * 
	 * double Dpo; double tr = 0; double res = 0; int NDose = conDose.length;
	 * 
	 * Cp = 0; double[] dydx = new double[C.length]; double Ce = C[0];
	 * 
	 * int i = 0; do { i++; } while (i < NDose && t >= conDosingTime[i]);
	 * 
	 * int ndose = i - 1;
	 * 
	 * for (int j = 0; j <= ndose; j++) { double A1 = A / conDose[0]; double B1
	 * = B / conDose[0];
	 * 
	 * Dpo = conDose[j]; tr = t - conDosingTime[j]; double amt = 0;
	 * 
	 * double K21 = (A * beta + B * alpha) / (A + B); double V1 = conDose[0] /
	 * (A + B);
	 * 
	 * double A2 = (Ke0 / V1) ((K21 - alpha) / ((Ke0 - alpha) * (beta -
	 * alpha))); double B2 = (Ke0 / V1) ((K21 - beta) / ((Ke0 - beta) * (alpha -
	 * beta))); double C2 = (Ke0 / V1) ((K21 - Ke0) / ((alpha - Ke0) * (beta -
	 * Ke0)));
	 * 
	 * amt = A2 * Math.exp(-1.0 * alpha * tr) + B2 Math.exp(-1.0 * beta * tr) +
	 * C2 Math.exp(-1.0 * Ke0 * tr);
	 * 
	 * res = Math.max(0, Dpo * amt); Cp = Cp + res;
	 * 
	 * }
	 * 
	 * dydx[0] = Ke0 * (Cp - Ce); return dydx; }
	 */

	public double func_diff7(double[] par, double[] infusionTime,
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
		double A, B, C, alpha, beta;
		double Div;
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

			Div = conDose[j];
			tr = t - conDosingTime[j];
			double amt = 0;
			beta = (K12 + K21 + K10 - Math.sqrt(Math.pow(K12 + K21 + K10, 2)
					- 4 * K21 * K10)) / 2;
			alpha = (K12 + K21 + K10 + Math.sqrt(Math.pow(K12 + K21 + K10, 2)
					- 4 * K21 * K10)) / 2;
			A = (Ke0 * Div / V1)
					* ((K21 - alpha) / ((Ke0 - alpha) * (beta - alpha)));
			B = (Ke0 * Div / V1)
					* ((K21 - beta) / ((Ke0 - beta) * (alpha - beta)));
			C = (Ke0 * Div / V1)
					* ((K21 - Ke0) / ((alpha - Ke0) * (beta - Ke0)));

			amt = A * Math.exp(-1.0 * alpha * tr) + B
					* Math.exp(-1.0 * beta * tr) + C
					* Math.exp(-1.0 * Ke0 * tr);

			res = Math.max(0, amt);
			fn[0] = fn[0] + res;

		}

		return fn[fn_no];
	}

	public double func_diff8(double[] par, double[] infusionTime,
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

			double K21 = (A * beta + B * alpha) / (A + B);
			double V1 = conDose[0] / (A + B);

			double A2 = (Ke0 / V1)
					* ((K21 - alpha) / ((Ke0 - alpha) * (beta - alpha)));
			double B2 = (Ke0 / V1)
					* ((K21 - beta) / ((Ke0 - beta) * (alpha - beta)));
			double C2 = (Ke0 / V1)
					* ((K21 - Ke0) / ((alpha - Ke0) * (beta - Ke0)));

			amt = A2 * Math.exp(-1.0 * alpha * tr) + B2
					* Math.exp(-1.0 * beta * tr) + C2
					* Math.exp(-1.0 * Ke0 * tr);

			res = Math.max(0, Dpo * amt);
			fn[0] = fn[0] + res;

		}

		return fn[fn_no];
	}

	public double func_diff9(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double h = 0.01; // step size
		double t0 = 0;

		double[] C0 = new double[1];
		double[] Yc = new double[1];

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double t = x;
		/*
		 * int i = 0; do { i++; } while (i < NDose && x >= conDosingTime[i]);
		 * 
		 * int ndose = i - 1;
		 * 
		 * for (int j = 0; j <= ndose; j++) {
		 * 
		 * Dpo = conDose[j]; tr = t - conDosingTime[j]; Tinf = infusionTime[j] -
		 * conDosingTime[j]; Tstar = tr - Tinf; double amt = 0; if (Tstar < 0)
		 * Tstar = 0;
		 * 
		 * A = (Dpo / Tinf) * (K21 - alpha) / ((beta - alpha) * alpha * V1); B =
		 * (Dpo / Tinf) * (K21 - beta) / ((alpha - beta) * beta * V1); amt = A
		 * (Math.exp(-1.0 * alpha * Tstar) - Math.exp(-1.0 * alpha tr)) + B
		 * (Math.exp(-1.0 * beta * Tstar) - Math.exp(-1.0 * beta tr)); res =
		 * Math.max(0, amt);
		 * 
		 * Cp = res; Yc = solvingODE(h, t0, t, C0, Yc); fn[0] = fn[0] + Yc[0];
		 * 
		 * }
		 */
		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = Yc[0];
		return fn[fn_no];
	}

	public double[] function9(double t, double[] C) {

		double V1 = parameter[0];
		double K10 = parameter[1];
		double K12 = parameter[2];
		double K21 = parameter[3];

		double Tinf;
		double A, B, alpha, beta;
		double Tstar;
		double Dpo;

		double tr = 0;
		double res = 0;
		int NDose = conDose.length;
		Cp = 0;
		double[] dydx = new double[C.length];

		double Ce = C[0];

		int i = 0;
		do {
			i++;
		} while (i < NDose && t >= conDosingTime[i]);

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
			Cp = Cp + res;

		}

		dydx[0] = Ke0 * (Cp - Ce);

		return dydx;
	}

	public double func_diff10(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double h = 0.01; // step size
		double t0 = 0;

		double[] C0 = new double[1];
		double[] Yc = new double[1];

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double t = x;
		/*
		 * int i = 0; do { i++; } while (i < NDose && x >= conDosingTime[i]);
		 * 
		 * int ndose = i - 1;
		 * 
		 * for (int j = 0; j <= ndose; j++) {
		 * 
		 * Dpo = conDose[j]; tr = t - conDosingTime[j]; Tinf = infusionTime[j] -
		 * conDosingTime[j]; Tstar = tr - Tinf; double amt = 0; if (Tstar < 0)
		 * Tstar = 0;
		 * 
		 * A = (Dpo / Tinf) * (K21 - alpha) / ((beta - alpha) * alpha * V1); B =
		 * (Dpo / Tinf) * (K21 - beta) / ((alpha - beta) * beta * V1); amt = A
		 * (Math.exp(-1.0 * alpha * Tstar) - Math.exp(-1.0 * alpha tr)) + B
		 * (Math.exp(-1.0 * beta * Tstar) - Math.exp(-1.0 * beta tr)); res =
		 * Math.max(0, amt);
		 * 
		 * Cp = res; Yc = solvingODE(h, t0, t, C0, Yc); fn[0] = fn[0] + Yc[0];
		 * 
		 * }
		 */

		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = Yc[0];

		System.out.println(fn[0] + "\t" + x);
		return fn[fn_no];
	}

	/*
	 * public double func_diff10(double[] par, double[] infusionTime, double[]
	 * conDose, double[] conDosingTime, double x, int fn_no, double[][]
	 * Extra_DATA, int rowIdx, int[] row) throws RowsExceededException,
	 * WriteException, BiffException, IOException { int Nfun = 1; double[] fn =
	 * new double[Nfun]; double V1 = par[0]; double K21 = par[1]; double alpha =
	 * par[2]; double beta = par[3]; double Tinf;
	 * 
	 * double A, B, C; double Tstar; double Dpo; double t = x; // double tp = 0;
	 * double tr = 0; double res = 0; int NDose = conDose.length;
	 * 
	 * int i = 0; do { i++; } while (i < NDose && x >= conDosingTime[i]);
	 * 
	 * int ndose = i - 1;
	 * 
	 * for (int j = 0; j <= ndose; j++) {
	 * 
	 * Dpo = conDose[j]; tr = t - conDosingTime[j]; Tinf = infusionTime[j] -
	 * conDosingTime[j]; Tstar = tr - Tinf; double amt = 0; if (Tstar < 0) Tstar
	 * = 0;
	 * 
	 * A = (Dpo / Tinf) * (K21 - alpha) / ((beta - alpha) * alpha * V1); B =
	 * (Dpo / Tinf) * (K21 - beta) / ((alpha - beta) * beta * V1); amt = A
	 * (Math.exp(-1.0 * alpha * Tstar) - Math.exp(-1.0 * alpha tr)) + B
	 * (Math.exp(-1.0 * beta * Tstar) - Math.exp(-1.0 * beta tr));
	 * 
	 * double coeff = Ke0*(Dpo / Tinf)/V1;
	 * 
	 * A = (K21 - alpha)/(alpha*(beta - alpha)*(Ke0 - alpha)); B = (K21 -
	 * beta)/(beta*(alpha -beta)*(Ke0 - beta)); C = (K21 - Ke0)/(Ke0*(alpha -
	 * Ke0)*(beta - Ke0));
	 * 
	 * double exp1 = (1 - Math.exp((-alpha)*Tinf))*(Math.exp((-alpha)*Tstar));
	 * double exp2 = (1 - Math.exp((-beta)*Tinf))*(Math.exp((-beta)*Tstar));
	 * double exp3 = (1 - Math.exp((-Ke0)*Tinf))*(Math.exp((-Ke0)*Tstar));
	 * 
	 * amt = coeff*(A*exp1 + B*exp2 + C*exp3);
	 * 
	 * res = Math.max(0, amt); fn[0] = fn[0] + res;
	 * 
	 * }
	 * 
	 * return fn[fn_no]; }
	 */

	private double[] function10(double t, double[] C) {

		double Tinf;
		double tr;
		double Tstar;
		double Dpo;
		int NDose = conDose.length;

		double V1 = parameter[0];
		double K21 = parameter[1];
		double alpha = parameter[2];
		double beta = parameter[3];
		double res;

		double A, B;
		Cp = 0;
		double[] dydx = new double[C.length];
		double Ce = C[0];

		int i = 0;
		do {
			i++;
		} while (i < NDose && t >= conDosingTime[i]);

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

			Cp = Cp + res;

		}

		dydx[0] = Ke0 * (Cp - Ce);

		return dydx;
	}

	/*
	 * public double func_diff11(double[] par, double[] infusionTime, double[]
	 * conDose, double[] conDosingTime, double x, int fn_no, double[][]
	 * Extra_DATA, int rowIdx, int[] row) throws RowsExceededException,
	 * WriteException, BiffException, IOException { int Nfun = 1; double[] fn =
	 * new double[Nfun]; double h = 0.01; // step size double t0 = 0;
	 * 
	 * double[] C0 = new double[1]; double[] Yc = new double[1];
	 * 
	 * double t = x;
	 * 
	 * double V1 = par[0]; double K01 = par[1]; double K10 = par[2]; double K12
	 * = par[3]; double K21 = par[4];
	 * 
	 * double alpha, beta;
	 * 
	 * double Dpo; double t = x; double res = 0;
	 * 
	 * double amt = 0; int NDose = conDose.length;
	 * 
	 * int i = 0; do { i++; } while (i < NDose && x >= conDosingTime[i]);
	 * 
	 * int ndose = i - 1;
	 * 
	 * for (int j = 0; j <= ndose; j++) {
	 * 
	 * Dpo = conDose[j]; double sq1 = Math.pow((K12 + K21 + K10), 2) - 4.0 * K10
	 * * K21; beta = 0.5 * ((K12 + K21 + K10) - Math.sqrt(sq1)); alpha = 0.5 *
	 * ((K12 + K21 + K10) + Math.sqrt(sq1)); double coeff = K01 * Dpo / V1;
	 * 
	 * double exp1 = Math.exp(-1.0 * alpha * t); double exp2 = Math.exp(-1.0 *
	 * beta * t); double exp3 = Math.exp(-1.0 * K01 * t);
	 * 
	 * double deno1 = (alpha - beta) * (alpha - K01); double deno2 = (beta -
	 * K01) * (alpha - beta); double deno3 = (alpha - K01) * (beta - K01);
	 * 
	 * double term1, term2, term3; term1 = (K21 - alpha) * exp1 / deno1; term2 =
	 * (beta - K21) * exp2 / deno2; term3 = (K21 - K01) * exp3 / deno3;
	 * 
	 * amt = coeff * (term1 + term2 + term3); res = Math.max(0, amt); fn[0] =
	 * fn[0] + res;
	 * 
	 * }
	 * 
	 * 
	 * Yc = solvingODE(h, t0, t, C0, Yc); fn[0] = Yc[0]; return fn[fn_no]; }
	 * 
	 * 
	 * 
	 * public double[] function11(double t, double[] C){
	 * 
	 * double V1 = parameter[0]; double K01 = parameter[1]; double K10 =
	 * parameter[2]; double K12 = parameter[3]; double K21 = parameter[4];
	 * 
	 * double[] dydx = new double[C.length]; double Ce = C[0]; Cp = 0;
	 * 
	 * double alpha, beta;
	 * 
	 * double Dpo;
	 * 
	 * double res = 0;
	 * 
	 * double amt = 0; int NDose = conDose.length;
	 * 
	 * 
	 * int i = 0; do { i++; } while (i < NDose && t >= conDosingTime[i]);
	 * 
	 * int ndose = i - 1;
	 * 
	 * for (int j = 0; j <= ndose; j++) {
	 * 
	 * Dpo = conDose[j]; double sq1 = Math.pow((K12 + K21 + K10), 2) - 4.0 * K10
	 * * K21; beta = 0.5 * ((K12 + K21 + K10) - Math.sqrt(sq1)); alpha = 0.5 *
	 * ((K12 + K21 + K10) + Math.sqrt(sq1)); double coeff = K01 * Dpo / V1;
	 * 
	 * double exp1 = Math.exp(-1.0 * alpha * t); double exp2 = Math.exp(-1.0 *
	 * beta * t); double exp3 = Math.exp(-1.0 * K01 * t);
	 * 
	 * double deno1 = (alpha - beta) * (alpha - K01); double deno2 = (beta -
	 * K01) * (alpha - beta); double deno3 = (alpha - K01) * (beta - K01);
	 * 
	 * double term1, term2, term3; term1 = (K21 - alpha) * exp1 / deno1; term2 =
	 * (beta - K21) * exp2 / deno2; term3 = (K21 - K01) * exp3 / deno3;
	 * 
	 * amt = coeff * (term1 + term2 + term3); res = Math.max(0, amt); Cp = Cp +
	 * res;
	 * 
	 * }
	 * 
	 * dydx[0] = Ke0 * (Cp - Ce);
	 * 
	 * return dydx; }
	 * 
	 * 
	 * public double func_diff12(double[] par, double[] infusionTime, double[]
	 * conDose, double[] conDosingTime, double x, int fn_no, double[][]
	 * Extra_DATA, int rowIdx, int[] row) throws RowsExceededException,
	 * WriteException, BiffException, IOException {
	 * 
	 * double h = 0.01; // step size double t0 = 0;
	 * 
	 * double[] C0 = new double[1]; double[] Yc = new double[1];
	 * 
	 * int Nfun = 1; double[] fn = new double[Nfun]; double t = x;
	 * 
	 * 
	 * Yc = solvingODE(h, t0, t, C0, Yc); fn[0] = Yc[0]; return fn[fn_no]; }
	 * 
	 * public double[] function12(double t, double[] C){
	 * 
	 * double V1 = parameter[0]; double K01 = parameter[1]; double K10 =
	 * parameter[2]; double K12 = parameter[3]; double K21 = parameter[4];
	 * double t_lag = parameter[5];
	 * 
	 * double A, B, C1, alpha, beta; double Dpo;
	 * 
	 * double tr = 0; double res = 0; double amt = 0; int NDose =
	 * conDose.length;
	 * 
	 * Cp = 0; double[] dydx = new double[C.length]; double Ce = C[0];
	 * 
	 * int i = 0; do { i++; } while (i < NDose && t >= conDosingTime[i]);
	 * 
	 * int ndose = i - 1;
	 * 
	 * for (int j = 0; j <= ndose; j++) {
	 * 
	 * Dpo = conDose[j]; tr = t - conDosingTime[j]; if (tr <= t_lag) amt = 0;
	 * else {
	 * 
	 * alpha = (K12 + K21 + K10 + Math.sqrt(Math.pow(K12 + K21 + K10, 2) - 4 *
	 * K21 * K10)) / 2; beta = (K12 + K21 + K10 - Math.sqrt(Math .pow(K12 + K21
	 * + K10, 2) - 4 * K21 * K10)) / 2;
	 * 
	 * A = (Dpo / V1) * K01 ((K21 - alpha) / (alpha - beta) * (alpha - K01)); B
	 * = (-1.0 * Dpo / V1) * K01 ((K21 - beta) / (alpha - beta) * (beta - K01));
	 * C1 = (Dpo / V1) * K01 ((K21 - K01) / (beta - K01) * (alpha - K01));
	 * 
	 * amt = A * (Math.exp(-1.0 * alpha * (tr - t_lag))) + B (Math.exp(-1.0 *
	 * beta * (tr - t_lag))) + C1 (Math.exp(-1.0 * K01 * (tr - t_lag)));
	 * 
	 * }
	 * 
	 * res = Math.max(0, amt); Cp = Cp + res;
	 * 
	 * }
	 * 
	 * dydx[0] = Ke0 * (Cp - Ce); return dydx; }
	 */

	public double func_diff11(double[] par, double[] infusionTime,
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
			double coeff = Ke0 * K01 * Dpo / V1;

			double exp1 = Math.exp(-1.0 * K01 * tr);
			double exp2 = Math.exp(-1.0 * alpha * tr);
			double exp3 = Math.exp(-1.0 * beta * tr);
			double exp4 = Math.exp(-1.0 * Ke0 * tr);

			double deno1 = (alpha - K01) * (beta - K01) * (Ke0 - K01);
			double deno2 = (K01 - alpha) * (beta - alpha) * (Ke0 - alpha);
			double deno3 = (K01 - beta) * (alpha - beta) * (Ke0 - beta);
			double deno4 = (K01 - Ke0) * (alpha - Ke0) * (beta - Ke0);

			double term1, term2, term3, term4;
			term1 = (K21 - K01) * exp1 / deno1;
			term2 = (K21 - alpha) * exp2 / deno2;
			term3 = (K21 - beta) * exp3 / deno3;
			term4 = (K21 - Ke0) * exp4 / deno4;

			amt = coeff * (term1 + term2 + term3 + term4);
			res = Math.max(0, amt);
			fn[0] = fn[0] + res;

		}

		return fn[fn_no];
	}

	public double func_diff12(double[] par, double[] infusionTime,
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

				double coeff = Ke0 * K01 * Dpo / V1;

				double exp1 = Math.exp(-1.0 * K01 * (tr - t_lag));
				double exp2 = Math.exp(-1.0 * alpha * (tr - t_lag));
				double exp3 = Math.exp(-1.0 * beta * (tr - t_lag));
				double exp4 = Math.exp(-1.0 * Ke0 * (tr - t_lag));

				double deno1 = (alpha - K01) * (beta - K01) * (Ke0 - K01);
				double deno2 = (K01 - alpha) * (beta - alpha) * (Ke0 - alpha);
				double deno3 = (K01 - beta) * (alpha - beta) * (Ke0 - beta);
				double deno4 = (K01 - Ke0) * (alpha - Ke0) * (beta - Ke0);

				double term1, term2, term3, term4;
				term1 = (K21 - K01) * exp1 / deno1;
				term2 = (K21 - alpha) * exp2 / deno2;
				term3 = (K21 - beta) * exp3 / deno3;
				term4 = (K21 - Ke0) * exp4 / deno4;

				amt = coeff * (term1 + term2 + term3 + term4);

			}

			res = Math.max(0, amt);
			fn[0] = fn[0] + res;

		}

		return fn[fn_no];
	}

	public double func_diff13(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double h = 0.01; // step size
		double t0 = 0;

		double[] C0 = new double[1];
		double[] Yc = new double[1];

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double t = x;

		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = Yc[0];
		return fn[fn_no];
	}

	public double[] function13(double t, double[] C) {

		double A = parameter[0];
		double B = parameter[1];
		double K01 = parameter[2];
		double alpha = parameter[3];
		double beta = parameter[4];
		double C1 = (-1) * (A + B);

		double Dpo;

		double tr = 0;
		double res = 0;
		double amt = 0;
		int NDose = conDose.length;
		double A1, B1;
		A1 = A / conDose[0];
		B1 = B / conDose[0];
		C1 = (-1) * (A1 + B1);

		Cp = 0;
		double[] dydx = new double[C.length];
		double Ce = C[0];
		int i = 0;
		do {
			i++;
		} while (i < NDose && t >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			Dpo = conDose[j];
			tr = t - conDosingTime[j];

			amt = A1 * (Math.exp(-1.0 * alpha * tr)) + B1
					* (Math.exp(-1.0 * beta * tr)) + C1
					* (Math.exp(-1.0 * K01 * tr));
			res = Math.max(0, Dpo * amt);
			Cp = Cp + res;

		}

		dydx[0] = Ke0 * (Cp - Ce);
		return dydx;
	}

	public double func_diff14(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double h = 0.01; // step size
		double t0 = 0;

		double[] C0 = new double[1];
		double[] Yc = new double[1];

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double t = x;

		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = Yc[0];
		return fn[fn_no];
	}

	public double[] function14(double t, double[] C) {

		double A = parameter[0];
		double B = parameter[1];
		double K01 = parameter[2];
		double alpha = parameter[3];
		double beta = parameter[4];
		double t_lag = parameter[5];

		double Dpo = 0;

		double tr = 0;
		double res = 0;
		double amt = 0;
		int NDose = conDose.length;
		double A1, B1;
		double C1;
		A1 = A / conDose[0];
		B1 = B / conDose[0];
		C1 = (-1) * (A1 + B1);

		Cp = 0;
		double[] dydx = new double[C.length];
		double Ce = C[0];

		int i = 0;
		do {
			i++;
		} while (i < NDose && t > conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			Dpo = conDose[j];
			tr = t - conDosingTime[j] - t_lag;

			amt = A1 * (Math.exp(-1.0 * alpha * (tr))) + B1
					* (Math.exp(-1.0 * beta * (tr))) + C1
					* (Math.exp(-1.0 * K01 * (tr)));
			res = Math.max(0, amt * Dpo);
			Cp = Cp + res;

		}

		dydx[0] = Ke0 * (Cp - Ce);
		return dydx;
	}

	public double func_diff15(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double h = 0.01; // step size
		double t0 = 0;

		double[] C0 = new double[1];
		double[] Yc = new double[1];

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double t = x;

		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = Yc[0];
		return fn[fn_no];
	}

	public double[] function15(double t, double[] C) {

		double V = parameter[0];
		double K10 = parameter[1];

		double Db = conDose[0];
		double Div = infusionDose[0];
		double Tinf = infusionTime[0];

		Cp = 0;
		double[] dydx = new double[C.length];
		double Ce = C[0];

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

		Cp = Cb + Civ;

		dydx[0] = Ke0 * (Cp - Ce);
		return dydx;
	}

	public double func_diff16(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double h = 0.01; // step size
		double t0 = 0;

		double[] C0 = new double[1];
		double[] Yc = new double[1];

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double t = x;

		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = Yc[0];
		return fn[fn_no];
	}

	public double[] function16(double t, double[] C) {

		double V1 = parameter[0];
		double K10 = parameter[1];
		double K12 = parameter[2];
		double K21 = parameter[3];

		double Tinf;

		double A, B, alpha, beta;
		double Tstar;

		Cp = 0;
		double[] dydx = new double[C.length];
		double Ce = C[0];

		double tp = 0;
		double Db = conDose[0];
		double Div = infusionDose[0];
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
		Cp = Cb + Civ;

		dydx[0] = Ke0 * (Cp - Ce);
		return dydx;
	}

	public double func_diff17(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double h = 0.01; // step size
		double t0 = 0;

		double[] C0 = new double[1];
		double[] Yc = new double[1];

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double t = x;

		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = Yc[0];
		return fn[fn_no];
	}

	public double[] function17(double t, double[] C) {

		double A = parameter[0];
		double B = parameter[1];
		double alpha = parameter[2];
		double beta = parameter[3];
		double Tinf;

		Cp = 0;
		double[] dydx = new double[C.length];
		double Ce = C[0];

		double Db = conDose[0];
		double Div = infusionDose[0];
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
		Cp = Cb + Civ;

		dydx[0] = Ke0 * (Cp - Ce);
		return dydx;
	}

	public double func_diff18(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double h = 0.01; // step size
		double t0 = 0;

		double[] C0 = new double[1];
		double[] Yc = new double[1];

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double t = x;

		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = Yc[0];
		return fn[fn_no];
	}

	public double[] function18(double t, double[] C) {

		double A = parameter[0];
		double B = parameter[1];
		double Cc = parameter[2];
		double alpha = parameter[3];
		double beta = parameter[4];
		double gamma = parameter[5];
		double NDose = conDose.length;
		double A1, B1, C1;
		double amt;
		double Dpo;

		Cp = 0;
		double[] dydx = new double[C.length];
		double Ce = C[0];

		double tp = 0;
		double res = 0;

		int i = 0;
		do {
			i++;
		} while (i < NDose && t >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			A1 = A / conDose[j];
			B1 = B / conDose[j];
			C1 = Cc / conDose[j];
			Dpo = conDose[j];
			tp = t - conDosingTime[j];
			amt = A1 * Math.exp(-1.0 * alpha * tp) + B1
					* Math.exp(-1.0 * beta * tp) + C1
					* Math.exp(-1.0 * gamma * tp);
			amt = Dpo * amt;

			res = res + Math.max(0, amt);
			Cp = Cp + res;

		}

		dydx[0] = Ke0 * (Cp - Ce);
		return dydx;
	}

	public double func_diff19(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double h = 0.01; // step size
		double t0 = 0;

		double[] C0 = new double[1];
		double[] Yc = new double[1];

		int Nfun = 1;
		double[] fn = new double[Nfun];
		double t = x;

		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = Yc[0];
		return fn[fn_no];
	}

	public double[] function19(double t, double[] C) {

		double V1 = parameter[0];
		double K21 = parameter[1];
		double K31 = parameter[2];
		double alpha = parameter[3];
		double beta = parameter[4];
		double gamma = parameter[5];
		double NDose = conDose.length;

		double Dpo;
		double A, B, C1;

		double tp = 0;
		// double tr = 0;
		double res = 0;
		double conc = 0;
		double Tinf, Tstar;

		Cp = 0;
		double[] dydx = new double[C.length];
		double Ce = C[0];

		int i = 0;
		do {
			i++;
		} while (i < NDose && t >= conDosingTime[i]);

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
			C1 = (((Dpo / Tinf) * (K21 - gamma) * (K31 - gamma)) / (V1 * gamma
					* (alpha - gamma) * (beta - gamma)));

			amt = A
					* (Math.exp(-1.0 * alpha * Tstar) - Math.exp(-1.0 * alpha
							* tp))
					+ B
					* (Math.exp(-1.0 * beta * Tstar) - Math.exp(-1.0 * beta
							* tp))
					+ C1
					* (Math.exp(-1.0 * gamma * Tstar) - Math.exp(-1.0 * gamma
							* tp));
			res = Math.max(0, amt);
			Cp = Cp + res;

		}

		dydx[0] = Ke0 * (Cp - Ce);
		return dydx;
	}

}
