package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import pkpd_library.integration.DFunction;
import pkpd_library.integration.ODEsolver;
import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class MmLibModelDefiner implements DFunction {

	double[] parameter;
	double[] dose;
	double[] dosingTime;
	double[] lengthOfInfusion;

	int odeSolverChoice;
	int noOfParam;
	int noOfFunction;
	int noOfDet;
	int currentProfileNo;
	int currentDosingNo;
	ApplicationInfo appInfo;
	ListOfDataStructures dataStructInst;
	PkPdInfo pkPdInst;
	ProcessingInputsInfo procInputInst;
	double coeff;

	public MmLibModelDefiner() {

		parameter = new double[1];
		dose = new double[1];
		dosingTime = new double[1];
		lengthOfInfusion = new double[1];
		odeSolverChoice = 1;

		currentProfileNo = 0;
	}

	public double chooseMmModel(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		pkPdInst = PkPdInfo.createPKPDInstance();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		appInfo = dataStructInst.getAppInfo();
		procInputInst = pkPdInst.copyProcessingInput();

		noOfParam = pkPdInst.noOfParam;

		noOfFunction = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfFunction();
		noOfDet = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfDeterminant();

		odeSolverChoice = procInputInst.getModelInputTab2Obj()
				.getOdeSolverMethod() + 1;

		ProcessingInputsInfo procInputInst = PkPdInfo.createPKPDInstance()
				.copyProcessingInput();

		int modelNumber = procInputInst.getModelInputTab1Obj()
				.getModelNumberForCA();

		currentProfileNo = pkPdInst.currentProfileNumber;
		coeff = 0;

		setParameter(par);
		setDose(conDose);
		setDosingTime(conDosingTime);

		double[] lengthOfInfusion = new double[conDose.length];
		for (int i = 0; i < lengthOfInfusion.length; i++) {
			lengthOfInfusion[i] = infusionTime[i] - conDosingTime[i];
		}

		setLengthOfInfusion(lengthOfInfusion);

		double value = 0;
		if (modelNumber == 1)
			value = func_diff1(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 2)
			value = func_diff2(par, conDose, conDosingTime, lengthOfInfusion,
					x, fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 3)
			value = func_diff3(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 4)
			value = func_diff4(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);
		else if (modelNumber == 5)
			value = func_diff5(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		return value;
	}

	double[] getParameter() {
		return parameter;
	}

	void setParameter(double[] parameter) {
		this.parameter = parameter;
	}

	double[] getDose() {
		return dose;
	}

	double getDoseAt(int i) {
		return dose[i];
	}

	void setDose(double[] dose) {
		this.dose = dose;
	}

	double[] getDosingTime() {
		return dosingTime;
	}

	double getDosingTimeAt(int i) {
		return dosingTime[i];
	}

	void setDosingTime(double[] dosingTime) {
		this.dosingTime = dosingTime;
	}

	double[] getLengthOfInfusion() {
		return lengthOfInfusion;
	}

	void setLengthOfInfusion(double[] lengthOfInfusion) {
		this.lengthOfInfusion = lengthOfInfusion;
	}

	@Override
	public double[] function(double t, double[] C) {

		double[] dydx = new double[C.length];
		int modelNumber = pkPdInst.modelNumber;

		double[] rateOfInfusion = new double[lengthOfInfusion.length];
		for (int i = 0; i < rateOfInfusion.length; i++) {
			rateOfInfusion[i] = dose[i] / lengthOfInfusion[i];
		}

		if (modelNumber == 1)
			dydx = function1(t, C, getParameter());
		if (modelNumber == 2)
			dydx = function2(t, C, getParameter(),
					lengthOfInfusion[currentDosingNo], dose[currentDosingNo]);
		if (modelNumber == 3)
			dydx = function3(t, C, getParameter());
		if (modelNumber == 4)
			dydx = function4(t, C, getParameter());
		if (modelNumber == 5)
			dydx = function5(t, C, getParameter(),
					lengthOfInfusion[currentDosingNo], dose[currentDosingNo]);

		return dydx;
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

	// one compartment iv bolus, Michaelis-Menten output
	private double func_diff1(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];

		double V = par[0];

		double dose;
		double h = 0.01; // step size
		double t0 = 0;
		double t;
		double[] C0 = new double[1];
		double[] Yc = new double[1];
		dose = conDose[0];
		C0[0] = dose/V;

		int nDose = conDose.length;

		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			dose = conDose[j];
			t = x - conDosingTime[j];
			if (x>0 && t == 0) {
				C0[0] = C0[0] + dose / V;
			}

			Yc = solvingODE(h, t0, t, C0, Yc);
			fn[0] = fn[0] + Math.max(0, Yc[0]);

		}

		return fn[fn_no];
	}

	private double[] function1(double t, double[] C, double[] par) {
		double[] dydx = new double[C.length];

		double VM = par[1];
		double KM = par[2];

		double C1 = C[0];

		dydx[0] = (-1.0 * VM * C1) / (KM + C1);
		if (Double.isInfinite(dydx[0]) == true || Double.isNaN(dydx[0]) == true) {

			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
		}

		return dydx;
	}

	// one compartment constant IV input, Michaelis-Menten output
	private double func_diff2(double[] par, double[] conDose,
			double[] conDosingTime, double[] lengthOfInfusion, double x,
			int fn_no, double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[noOfFunction];

		double V = par[0];

		double dose;
		double h = 0.01; // step size
		double t0 = 0;
		double t;
		double[] C0 = new double[1];
		double[] Yc = new double[1];
		coeff = 0;

		int nDose = conDose.length;

		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			currentDosingNo = j;
			dose = conDose[j];
			t = x - conDosingTime[j];
			if (t <= lengthOfInfusion[j])
				coeff = coeff + dose / lengthOfInfusion[j];

			C0[0] = 0;

			Yc = solvingODE(h, t0, t, C0, Yc);
			fn[0] = fn[0] + Math.max(0, Yc[0]);

		}

		return fn[fn_no];
	}

	private double[] function2(double t, double[] C, double[] par,
			double lengthOfInfusion, double dose) {
		double[] dydx = new double[C.length];
		double V = par[0];
		double VM = par[1];
		double KM = par[2];

		double C1 = C[0];

		dydx[0] = (coeff / V) - ( VM * C1) / (KM + C1);

		if (Double.isInfinite(dydx[0]) == true || Double.isNaN(dydx[0]) == true) {

			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;

		}
		return dydx;
	}

	// one compartment,oral dose, Michaelis-Menten output, no lag time

	private double func_diff3(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];

		double V = par[0];
		double K01 = par[1];

		double dose;
		double h = 0.01; // step size
		double t0 = 0;
		double t;
		double[] C0 = new double[noOfDet];
		double[] Yc = new double[noOfDet];

		coeff = 0;
		int nDose = conDose.length;

		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			currentDosingNo = j;
			dose = conDose[j];
			t = x - conDosingTime[j];
			if (x > 0 && t >= 0)
				coeff = coeff + (dose / V) * Math.exp(-K01 * t);
			C0[0] = 0;

			Yc = solvingODE(h, t0, t, C0, Yc);
			fn[0] = fn[0] + Math.max(0, Yc[0]);

		}

		return fn[fn_no];
	}

	private double[] function3(double t, double[] C, double[] parameter) {
		double[] dydx = new double[C.length];
		double V_F = parameter[0];
		double K01 = parameter[1];
		double VM = parameter[2];
		double KM = parameter[3];
		double dose = getDoseAt(currentProfileNo);
		double C1 = C[0];

		dydx[0] = K01 * coeff - (VM * C1) / (KM + C1);
		if (Double.isInfinite(dydx[0]) == true || Double.isNaN(dydx[0]) == true) {

			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;

		}
		return dydx;
	}

	// one compartment,oral dose, Michaelis-Menten output, with lag time
	private double func_diff4(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];

		double dose;
		double h = 0.01; // step size
		double t0 = 0;
		double t;
		double[] C0 = new double[noOfDet];
		double[] Yc = new double[noOfDet];
		double V = par[0];
		double K01 = par[1];
		double tlag = par[4];

		int nDose = conDose.length;

		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			currentDosingNo = j;
			dose = conDose[j];
			t = x - conDosingTime[j];
			t = t - tlag;
			if (x > 0 && t >= 0)
				coeff = coeff + (dose / V) * Math.exp(-K01 * t);
			C0[0] = 0;

			if (t < 0)
				Yc[0] = 0;
			else {

				Yc = solvingODE(h, t0, t, C0, Yc);
			}

			fn[0] = fn[0] + Math.max(0, Yc[0]);

		}

		return fn[fn_no];
	}

	private double[] function4(double t, double[] C, double[] parameter) {
		double[] dydx = new double[C.length];
		double V_F = parameter[0];
		double K01 = parameter[1];
		double VM = parameter[2];
		double KM = parameter[3];
		double dose = getDoseAt(currentProfileNo);
		double C1 = C[0];

		dydx[0] = K01 * (dose / V_F) * Math.exp(-1 * K01 * t) - VM * C1
				/ (KM + C1);
		if (Double.isInfinite(dydx[0]) == true || Double.isNaN(dydx[0]) == true) {

			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;

		}
		return dydx;
	}

	// one compartment constant IV input, Michaelis-Menten output
	private double func_diff5(double[] par, double[] conDose,
			double[] conDosingTime, double x,
			int fn_no, double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[2];

		
		double dose;
		double h = 0.01; // step size
		double t0 = 0;
		double t;
		double[] C0 = new double[2];
		double[] Yc = new double[2];
		coeff = 0;

		int nDose = conDose.length;

		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {

			currentDosingNo = j;
			dose = conDose[j];
			t = x - conDosingTime[j];
			/*if (t <= lengthOfInfusion[j])
				coeff = coeff + dose / lengthOfInfusion[j];*/

			C0[0] = 0;
			C0[1] = 0;

			Yc = solvingODE(h, t0, t, C0, Yc);
			fn[0] = fn[0] + Math.max(0, Yc[0]);

		}

		return fn[0];
	}

	private double[] function5(double t, double[] C, double[] par,
			double lengthOfInfusion, double dose) {
		double[] dydx = new double[C.length];
		double Vc = par[0];
		double CLD = par[1];
		double Vt = par[2];
		double Vmax = par[3];
		double Km = par[4];
		
		

		double C1 = C[0];
		double C2 = C[1];
		
		double Cl = Vmax/(Km + C1);
		
		double input = 0;
		if(t <= lengthOfInfusion )
			input = dose/lengthOfInfusion;

		dydx[0] = (input - (Cl * C1) - (CLD * C1) + (CLD * C2))/ Vc;
		dydx[1] = (CLD * (C1 - C2)) / Vt;

		if (Double.isInfinite(dydx[0]) == true || Double.isNaN(dydx[0]) == true) {

			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;

		}
		return dydx;
	}

	
	
	
	private void checkingForNanOrInfinite(int fn_no, double[] fn)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (Double.isInfinite(fn[fn_no]) == true
				|| Double.isNaN(fn[fn_no]) == true) {/*
													 * 
													 * CAMainScreen
													 * caMainScreenInst =
													 * CAMainScreen
													 * .createCAMainScreenInstance
													 * ();
													 * JOptionPane.showMessageDialog
													 * (caMainScreenInst.
													 * getCaMainInternalFrame(),
													 * "Error in Function Evaluation"
													 * , "Confirm",
													 * JOptionPane.YES_OPTION);
													 * System.exit(0);
													 */
		}
	}

}
