package Model;

import java.io.IOException;
import java.nio.charset.CodingErrorAction;

import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import pkpd_library.integration.DFunction;
import pkpd_library.integration.ODEsolver;
import view.ApplicationInfo;

public class DeLibModelDefiner implements DFunction {

	double[] parameter;
	double[] dose;
	double[] dosingTime;
	double[] lengthOfInfusion;
	double[] infusionDose;
	int odeSolverChoice;
	int noOfParam;
	int noOfFunction;
	int noOfDet;
	// int currentProfileNo;
	// ApplicationInfo appInfo;
	PkPdInfo pkPdInst;
	int noOfDose;
	int currentDoseNumber;

	public DeLibModelDefiner() {

		parameter = new double[1];
		dose = new double[1];
		dosingTime = new double[1];
		lengthOfInfusion = new double[1];
		odeSolverChoice = 1;
		infusionDose = new double[1];
		// currentProfileNo = 0;
		noOfDose = 1;
	}

	public double chooseLibraryModel(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		pkPdInst = PkPdInfo.createPKPDInstance();
		noOfParam = pkPdInst.noOfParam;
		noOfFunction = pkPdInst.nFun;
		noOfDet = pkPdInst.nDer;
		noOfDose = pkPdInst.numberOfDose;

		odeSolverChoice = pkPdInst.odeSolverChoice;

		int modelNumber = pkPdInst.modelNumber;

		if (modelNumber == 21 || modelNumber == 22 || modelNumber == 23
				|| modelNumber == 24) {
			infusionDose = pkPdInst.infusionDose;
		}

		// currentProfileNo = PK_PD.createPKPDInstance().currentProfileNumber;

		double[] lengthOfInfusion = new  double[infusionTime.length];
		for(int i = 0; i<lengthOfInfusion.length; i++)
		{
			lengthOfInfusion[i] = infusionTime[i] - conDosingTime[i];
		}
		
		setParameter(par);
		setDose(conDose);
		setDosingTime(conDosingTime);
		setLengthOfInfusion(lengthOfInfusion);
		setInfusionDose(infusionDose);
		double value = 0;
		if (modelNumber == 1)
			value = func_diff1(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 2)
			value = func_diff2(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 3)
			value = func_diff3(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 4)
			value = func_diff4(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);
		else if (modelNumber == 5)
			value = func_diff5(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 6)
			value = func_diff6(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 7)
			value = func_diff7(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 8)
			value = func_diff8(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 9)
			value = func_diff9(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 10)
			value = func_diff10(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 11)
			value = func_diff11(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 12)
			value = func_diff12(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 13)
			value = func_diff13(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 14)
			value = func_diff14(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 15)
			value = func_diff15(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 16)
			value = func_diff16(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 17)
			value = func_diff17(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 18)
			value = func_diff18(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 19)
			value = func_diff19(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 20)
			value = func_diff20(par, conDose, conDosingTime, x, fn_no,
					Extra_DATA, rowIdx, row);

		else if (modelNumber == 21)
			value = func_diff21(par, conDose,conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 22)
			value = func_diff22(par, conDose,conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 23)
			value = func_diff23(par, conDose,conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 24)
			value = func_diff24(par, conDose,conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);

		return value;
	}

	double[] getInfusionDose() {
		return infusionDose;
	}

	void setInfusionDose(double[] infusionDose) {
		this.infusionDose = infusionDose;
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
			dydx = function2(t, C, getParameter());
		if (modelNumber == 3)
			dydx = function3(t, C, getParameter());
		if (modelNumber == 4)
			dydx = function4(t, C, getParameter());
		if (modelNumber == 5)
			dydx = function5(t, C, getParameter());
		if (modelNumber == 6)
			dydx = function6(t, C, getParameter());
		if (modelNumber == 7)
			dydx = function7(t, C, getParameter(), getDose(),
					getLengthOfInfusion());
		if (modelNumber == 8)
			dydx = function8(t, C, getParameter(), getDose(),
					getLengthOfInfusion());
		if (modelNumber == 9)
			dydx = function9(t, C, getParameter());
		if (modelNumber == 10)
			dydx = function10(t, C, getParameter());

		if (modelNumber == 11)
			dydx = function11(t, C, getParameter());
		if (modelNumber == 12)
			dydx = function12(t, C, getParameter());
		if (modelNumber == 13)
			dydx = function13(t, C, getParameter());
		if (modelNumber == 14)
			dydx = function14(t, C, getParameter());
		if (modelNumber == 15)
			dydx = function15(t, C, getParameter(), getDose(),
					getLengthOfInfusion());
		if (modelNumber == 16)
			dydx = function16(t, C, getParameter(), getDose(),
					getLengthOfInfusion());
		if (modelNumber == 17)
			dydx = function17(t, C, getParameter());
		if (modelNumber == 18)
			dydx = function18(t, C, getParameter());
		if (modelNumber == 19)
			dydx = function19(t, C, getParameter(), getDose(),
					getLengthOfInfusion());
		if (modelNumber == 20)
			dydx = function20(t, C, getParameter(), getDose(),
					getLengthOfInfusion());

		if (modelNumber == 21)
			dydx = function21(t, C, getParameter());
		if (modelNumber == 22)
			dydx = function22(t, C, getParameter());
		if (modelNumber == 23)
			dydx = function23(t, C, getParameter(), infusionDose,
					getLengthOfInfusion());
		if (modelNumber == 24)
			dydx = function24(t, C, getParameter(), infusionDose,
					getLengthOfInfusion());

		return dydx;
	}

	private double func_diff21(double[] par, double[] conDose,double[] conDosingTime, double x,
			int fnNo, double[][] extraDATA, int rowIdx, int[] row) {
		double[] fn = new double[noOfFunction];
		double t = x;

		double V = par[0];

		double[] C0 = new double[noOfDet]; // initial value of y
		double Dose ;
		double[] Yc = new double[noOfDet];

		double h = 0.01; // step size
		double t0 = 0;

				
		int nDose = conDose.length;

		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;

			Dose = conDose[j];
			t = t - conDosingTime[j];

			C0[0] = Dose / V;
			
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}
		
		return fn[fnNo];

	}

	
	private double[] function21(double t, double[] C, double[] param) {
		double[] dydx = new double[C.length];
		double Vc = param[0];
		double K10 = param[1];
		
		double Cl = K10*Vc;
		
		double C1 = C[0];
		

		double con;
		if (t < lengthOfInfusion[currentDoseNumber])
			con = getInfusionDose()[currentDoseNumber]
					/ lengthOfInfusion[currentDoseNumber];
		else
			con = 0;
		dydx[0] = (-1) * Cl  * C1 / Vc + con / Vc;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			System.out.println("number is infinite" );
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			
		}

		return dydx;

	}

	private double func_diff22(double[] par, double[] conDose,double[] conDosingTime, double x,
			int fnNo, double[][] extraDATA, int rowIdx, int[] row) {
		double[] fn = new double[noOfFunction];
		double t = x;

		double V = par[0];

		double[] C0 = new double[noOfDet]; // initial value of y
		double Dose ;
		double[] Yc = new double[noOfDet];

		
		double h = 0.01; // step size
		double t0 = 0;
		
		int nDose = conDose.length;

		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;

			Dose = conDose[j];
			t = t - conDosingTime[j];

			C0[0] = Dose / V;
			
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}
		
		return fn[fnNo];

	}

	
	
	private double[] function22(double t, double[] C, double[] param) {
		double[] dydx = new double[C.length];
		double Vc = param[0];
		double Cl = param[1];
	
		double C1 = C[0];
		

		double con;
		if (t < lengthOfInfusion[currentDoseNumber])
			con = getInfusionDose()[currentDoseNumber]
					/ lengthOfInfusion[currentDoseNumber];
		else
			con = 0;
		dydx[0] = (-1) * Cl  * C1 / Vc + con / Vc;
		
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			System.out.println("number is infinite" );
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
		}

		return dydx;

	}
	
	// Two compartments, IV- bolus + IV-infusion, parameterized as rate
	// constants, without lag time.

	private double func_diff23(double[] par, double[] conDose,double[] conDosingTime, double x,
			int fnNo, double[][] extraDATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[noOfFunction];
		double t = x;

		double V = par[0];

		double[] C0 = new double[noOfDet]; // initial value of y
		double Dose ;
		double[] Yc = new double[noOfDet];


		double h = 0.01; // step size
		double t0 = 0;

		
		int nDose = conDose.length;

		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;

			Dose = conDose[j];
			t = t - conDosingTime[j];

			C0[0] = Dose / V;
			C0[1] = 0;

			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}
		
		return fn[fnNo];

	}

	private double[] function23(double t, double[] C, double[] param,
			double[] dose2, double[] lengthOfInfusion) {

		double[] dydx = new double[C.length];
		double Vc = param[0];
		double K10 = param[1];
		double K12 = param[2];
		double K21 = param[3];
		double Vt = param[4];

		double Cl = K10 * Vc;
		double Cld = K12 * Vc;

		double C1 = C[0];
		double C2 = C[1];

		double con;
		if (t < lengthOfInfusion[currentDoseNumber])
			con = getInfusionDose()[currentDoseNumber]
					/ lengthOfInfusion[currentDoseNumber];
		else
			con = 0;
		dydx[0] = (-1) * (Cl + Cld) * C1 / Vc + Cld * C2 / Vc + con / Vc;
		dydx[1] = Cld * C1 / Vt + (-Cld) * C2 / Vt;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] = 1;
			
		}

		return dydx;

	}

	// Two compartments, IV- bolus + IV-infusion, parameterized as clearance,
	// without lag time.
	private double func_diff24(double[] par, double[] conDose,
			double[] conDosingTime, double x,
			int fnNo, double[][] extraDATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[noOfFunction];
		double t = x;

		double V = par[0];

		double[] C0 = new double[noOfDet]; // initial value of y
		double Dose ;
		double[] Yc = new double[noOfDet];


		double h = 0.01; // step size
		double t0 = 0;
			
		int nDose = conDose.length;

		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;

			Dose = conDose[j];
			t = t - conDosingTime[j];

			C0[0] = Dose / V;
			C0[1] = 0;

			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}
		
		return fn[fnNo];

	}

	private double[] function24(double t, double[] C, double[] parameter2,
			double[] dose2, double[] lengthOfInfusion) {

		double[] dydx = new double[C.length];
		double Vc = parameter[0];
		double Cl = parameter[1];
		double Cld = parameter[2];
		double Vt = parameter[3];

		double C1 = C[0];
		double C2 = C[1];

		double con;
		if (t < lengthOfInfusion[currentDoseNumber])
			con = getInfusionDose()[currentDoseNumber]
					/ lengthOfInfusion[currentDoseNumber];
		else
			con = 0;
		dydx[0] = (-1) * (Cl + Cld) * C1 / Vc + Cld * C2 / Vc + con / Vc;
		dydx[1] = Cld * C1 / Vt + (-Cld) * C2 / Vt;
		
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] = 1;
			
		}
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

	// one compartment iv bolus, rate constant parameterization
	private double func_diff1(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];

		double V = par[0];
		double Dose;
		double h = 0.01; // step size
		double t0 = 0;
		double t = x;
		double[] C0 = new double[noOfDet];
		double[] Yc = new double[noOfDet];

		int nDose = conDose.length;
		double dose1 = conDose[0];		
		double lastDose;
		double lastDoseTime = conDosingTime[0];
		C0[0] = dose1/V;
		
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;

			Dose = conDose[j];
			
			if(j>0)
			{
				/*if(x == conDosingTime[j])
				{*/
					lastDose = conDose[j];
					lastDoseTime = conDosingTime[j];
					C0[0] = 0 +lastDose / V;
				/*}else
				{
					lastDose = conDose[j-1];
					lastDoseTime = conDosingTime[j-1];
				}*/
			}
			/*else
			{
				lastDose = dose1;
				lastDoseTime = conDosingTime[0];
			}*/
			
			t = x - conDosingTime[j];
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		
		// fn[0] = Yc[0];
		checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function1(double t, double[] C, double[] par) {
		double[] dydx = new double[C.length];
		double V = par[0];
		double K = par[1];

		double C1 = C[0];
		dydx[0] = -1.0 * K * C1;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
						
		}


		return dydx;
	}

	// one compartment iv bolus, clearance parameterization
	private double func_diff2(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];
		double Cl = par[1];
		double V = par[0];

		double Dose;
		double h = 0.01; // step size
		double t0 = 0;
		double t = x;
		double[] C0 = new double[noOfDet];
		double[] Yc = new double[noOfDet];
		double dose1 = conDose[0];		
		double lastDose;
		double lastDoseTime = conDosingTime[0];
		C0[0] = dose1/V;
		int nDose = conDose.length;

		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;

			Dose = conDose[j];
			
			if(j>0)
			{
				lastDose = conDose[j];
					lastDoseTime = conDosingTime[j];
					C0[0] = 0 +lastDose / V;
				
			}
			
			
			t = x - conDosingTime[j];
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function2(double t, double[] C, double[] par) {
		double[] dydx = new double[C.length];
		double V = par[0];
		double Cl = par[1];

		double C1 = C[0];

		dydx[0] = -1.0 * Cl * C1 / V;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
						
		}


		return dydx;
	}

	// one compartment,oral dose, parameterized as rate constant

	private double func_diff3(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[1];
		double t = x;
		double[] C0 = new double[1]; // initial value of y
		double[] Yc = new double[1];

		double h = 0.01; // step size
		double t0 = 0;

		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;
		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			C0[0] = 0;
			t = x - conDosingTime[j];
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function3(double t, double[] C, double[] parameter) {
		double[] dydx = new double[C.length];
		double Kel = parameter[0];
		double V = parameter[1];
		double Ka = parameter[2];
		double dose = getDoseAt(currentDoseNumber);
		double C1 = C[0];

		dydx[0] = (-Kel) * C1 + Ka * dose * Math.exp(-Ka * t) / V;

		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
						
		}

		return dydx;
	}

	// one compartment,oral dose, parameterized as clearance
	private double func_diff4(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[1];
		double t = x;
		double[] C0 = new double[1]; // initial value of y
		double[] Yc = new double[1];

		double h = 0.01; // step size
		double t0 = 0;

		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			C0[0] = 0;
			t = x - conDosingTime[j];

			Yc = solvingODE(h, t0, t, C0, Yc);
			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);
		return fn[fn_no];
	}

	private double[] function4(double t, double[] C, double[] parameter) {
		double[] dydx = new double[C.length];
		double Cl = parameter[0];
		double V = parameter[1];
		double Ka = parameter[2];
		double dose = getDoseAt(currentDoseNumber);
		double C1 = C[0];

		dydx[0] = (-Cl / V) * C1 + Ka * dose * Math.exp(-Ka * t) / V;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
						
		}


		return dydx;
	}

	// one compartment, oral, parameterized as rate constant with lag time.
	private double func_diff5(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[1];
		double t = x;
		double tlag = par[3];

		double[] C0 = new double[1]; // initial value of y

		double[] Yc = new double[1];

		double h = 0.01; // step size
		double t0 = 0;

		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			C0[0] = 0;
			t = x - tlag - conDosingTime[j];
			if (t < 0)
				Yc[0] = 0;
			else {

				Yc = solvingODE(h, t0, t, C0, Yc);
			}

			fn[0] = fn[0] + Yc[0];

		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function5(double t, double[] C, double[] parameter) {
		double[] dydx = new double[C.length];
		double Kel = parameter[0];
		double V = parameter[1];
		double Ka = parameter[2];
		double tlag = parameter[3];
		double dose = getDoseAt(currentDoseNumber);
		double C1 = C[0];

		dydx[0] = (-Kel) * C1 + Ka * dose * Math.exp(-Ka * (t)) / V;
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
						
		}


		return dydx;
	}

	// one compartment,oral dose, parameterized as clearance, with lag time.
	private double func_diff6(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[1];
		double t = x;
		double V = par[1];
		double tlag = par[3];
		double[] C0 = new double[1]; // initial value of y

		double[] Yc = new double[1];

		double h = 0.01; // step size
		double t0 = 0;

		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			C0[0] = 0;
			t = x - tlag - conDosingTime[j];
			if (t < 0)
				Yc[0] = 0;
			else {
				Yc = solvingODE(h, t0, t, C0, Yc);
			}

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function6(double t, double[] C, double[] parameter) {
		double[] dydx = new double[C.length];
		double Cl = parameter[0];
		double V = parameter[1];
		double Ka = parameter[2];
		double dose = getDoseAt(currentDoseNumber);
		double C1 = C[0];

		dydx[0] = (-Cl / V) * C1 + Ka * dose * Math.exp(-Ka * (t)) / V;
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
						
		}


		return dydx;
	}

	// one compartment, constant infusion, parameterized as rate constant.
	private double func_diff7(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[noOfFunction];
		double t = x;
		double[] C0 = new double[noOfDet]; // initial
		// value
		// of
		// y

		double[] Yc = new double[noOfDet];

		double h = 0.01; // step size
		double t0 = 0;
		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			t = x - conDosingTime[j];
			C0[0] = 0;
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function7(double t, double[] C, double[] parameter,
			double[] conDose, double[] lengthOfinfusionTime) {
		double[] dydx = new double[C.length];
		double Kel = parameter[0];
		double V = parameter[1];
		double C1 = C[0];

		double con = 0;
		if (t < lengthOfinfusionTime[currentDoseNumber])
			con = conDose[currentDoseNumber] / lengthOfinfusionTime[currentDoseNumber];
		else
			con = 0;

		dydx[0] = (-Kel) * C1 + con / V;
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
						
		}

		return dydx;
	}

	// one compartment, constant infusion, parameterized as clearance.
	private double func_diff8(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[noOfFunction];
		double t = x;
		double[] C0 = new double[noOfDet]; // initial value of y

		double[] Yc = new double[noOfDet];

		// initial values

		double h = 0.01; // step size
		double t0 = 0;

		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			C0[0] = 0;
			t = x - conDosingTime[j];
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function8(double t, double[] C, double[] parameter,
			double[] conDose, double[] infusionTime) {
		double[] dydx = new double[C.length];
		double Cl = parameter[0];
		double V = parameter[1];
		double C1 = C[0];
		double con = 0;
		if (t < infusionTime[currentDoseNumber])
			con = conDose[currentDoseNumber] / infusionTime[currentDoseNumber];
		else
			con = 0;

		dydx[0] = (-Cl / V) * C1 + con / V;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
						
		}

		return dydx;
	}

	// Two compartment,IV bolus, parameterized as rate constant

	private double func_diff9(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];

		double V = par[0];

		double Dose;
		double h = 0.01; // step size
		double t0 = 0;
		double t = x;
		double[] C0 = new double[noOfDet];
		double[] Yc = new double[noOfDet];
		double dose1 = conDose[0];		
		double lastDose;
		double lastDoseTime = conDosingTime[0];
		C0[0] = dose1/V;
		C0[1] = 0;
		

		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;

			Dose = conDose[j];
			
			if(j>0)
			{
				lastDose = conDose[j];
					lastDoseTime = conDosingTime[j];
					C0[0] = 0 +lastDose / V;
					C0[1] = 0;
					
				
			}
			
			
			t = x - conDosingTime[j];
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function9(double t, double[] C, double[] par) {
		double[] dydx = new double[C.length];
		double Vc = par[0];
		double K10 = par[1];
		double K12 = par[2];
		double K21 = par[3];

		double C1 = C[0];
		double C2 = C[1];

		double Cl = K10 * Vc;
		double Cld = K12 * Vc;
		double Vt = Cld / K21;

		dydx[0] = (-1) * Cl * C1 / Vc - Cld * C1 / Vc + Cld * C2 / Vc;
		dydx[1] = Cld * C1 / Vt - Cld * C2 / Vt;

		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] =1;
						
		}

		return dydx;
	}

	private double func_diff10(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];

		double V = par[1];
		double Dose;
		double h = 0.01; // step size
		double t0 = 0;
		double t = x;
		double[] C0 = new double[noOfDet];
		double[] Yc = new double[noOfDet];
		double dose1 = conDose[0];		
		double lastDose;
		double lastDoseTime = conDosingTime[0];
		C0[0] = dose1/V;
		C0[1] = 0;
		

		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;

			Dose = conDose[j];
			
			if(j>0)
			{
				lastDose = conDose[j];
					lastDoseTime = conDosingTime[j];
					C0[0] = 0 +lastDose / V;
					C0[1] = 0;
					
				
			}
			
			
			t = x - conDosingTime[j];
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function10(double t, double[] C, double[] par) {
		double[] dydx = new double[C.length];
		double Vc = par[1];
		double Cl = par[0];
		double Cld = par[2];
		double Vt = par[3];

		double C1 = C[0];
		double C2 = C[1];

		dydx[0] = (-1) * Cl * C1 / Vc - Cld * C1 / Vc + Cld * C2 / Vc;
		dydx[1] = Cld * C1 / Vt - Cld * C2 / Vt;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] =1;
						
		}

		return dydx;
	}

	// Two compartment,oral dose, parameterized as rate constant

	private double func_diff11(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];
		double t = x;

		double V = par[1];

		double[] C0 = new double[noOfDet]; // initial value of/ y
		double[] Yc = new double[noOfDet];

		// initial values

		double h = 0.01; // step size
		double t0 = 0;

		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			t = x - conDosingTime[j];
			C0[0] = 0;
			C0[1] = 0;

			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function11(double t, double[] C, double[] parameter) {
		double[] dydx = new double[C.length];
		double Kel = parameter[0];
		double Vc = parameter[1];
		double Ka = parameter[2];
		double Kcp = parameter[3];
		double Kpc = parameter[4];
		double dose = getDoseAt(currentDoseNumber);
		double C1 = C[0];
		double C2 = C[1];

		double CLt = Kel * Vc;
		double CLd = Kcp * Vc;
		double Vp = Vc * Kcp / Kpc;

		dydx[0] = Ka * dose * Math.exp(-Ka * t) / Vc + (-1) * (CLt + CLd) * C1
				/ Vc + CLd * C2 / Vc;
		dydx[1] = CLd * C1 / Vp + (-CLd) * C2 / Vp;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] =1;
						
		}

		return dydx;
	}

	// Two compartment,oral dose, parameterized as clearance

	private double func_diff12(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];
		double t = x;

		double V = par[1];

		double[] C0 = new double[noOfDet]; // initial

		double[] Yc = new double[noOfDet];

		// initial values
		double h = 0.01; // step size
		double t0 = 0;

		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			C0[0] = 0;
			C0[1] = 0;
			t = x - conDosingTime[j];
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function12(double t, double[] C, double[] parameter) {
		double[] dydx = new double[C.length];
		double CLt = parameter[0];
		double Vc = parameter[1];
		double Ka = parameter[2];
		double CLd = parameter[3];
		double Vp = parameter[4];
		double dose = getDoseAt(currentDoseNumber);
		double C1 = C[0];
		double C2 = C[1];

		dydx[0] = Ka * dose * Math.exp(-Ka * t) / Vc + (-1) * (CLt + CLd) * C1
				/ Vc + CLd * C2 / Vc;
		dydx[1] = CLd * C1 / Vp + (-CLd) * C2 / Vp;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] =1;
						
		}

		return dydx;
	}

	// Two compartment,oral dose, parameterized as rate constant, with lag time.

	private double func_diff13(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];
		double t = x;

		double V = par[1];
		double tlag = par[5];

		double[] C0 = new double[noOfDet]; // initial value of y

		double[] Yc = new double[noOfDet];

		// initial values

		double h = 0.01; // step size
		double t0 = 0;
		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			t = x - tlag - conDosingTime[j];
			C0[0] = 0;
			C0[1] = 0;
			if (t <= 0)
				Yc[0] = 0;
			else {
				Yc = solvingODE(h, t0, t, C0, Yc);
			}

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function13(double t, double[] C, double[] parameter) {
		double[] dydx = new double[C.length];
		double Kel = parameter[0];
		double Vc = parameter[1];
		double Ka = parameter[2];
		double Kcp = parameter[3];
		double Kpc = parameter[4];
		double dose = getDoseAt(currentDoseNumber);
		double C1 = C[0];
		double C2 = C[1];

		double CLt = Kel * Vc;
		double CLd = Kcp * Vc;
		double Vp = Vc * Kcp / Kpc;

		dydx[0] = Ka * dose * Math.exp(-Ka * t) / Vc + (-1) * (CLt + CLd) * C1
				/ Vc + CLd * C2 / Vc;
		dydx[1] = CLd * C1 / Vp + (-CLd) * C2 / Vp;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] =1;
						
		}
		return dydx;
	}

	// Two compartment,oral dose, parameterised as clearance, with lag time.

	private double func_diff14(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];
		double t = x;

		double V = par[1];
		double tlag = par[5];

		double[] C0 = new double[noOfDet]; // initial value of y

		double[] Yc = new double[noOfDet];

		// initial values
		double h = 0.01; // step size
		double t0 = 0;
		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;
		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			t = x - tlag - conDosingTime[j];
			C0[0] = 0;
			C0[1] = 0;
			if (t <= 0)
				Yc[0] = 0;
			else {
				Yc = solvingODE(h, t0, t, C0, Yc);
			}

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function14(double t, double[] C, double[] parameter) {
		double[] dydx = new double[C.length];
		double CLt = parameter[0];
		double Vc = parameter[1];
		double Ka = parameter[2];
		double CLd = parameter[3];
		double Vp = parameter[4];
		double dose = getDoseAt(currentDoseNumber);
		double C1 = C[0];
		double C2 = C[1];

		dydx[0] = Ka * dose * Math.exp(-Ka * t) / Vc + (-1) * (CLt + CLd) * C1
				/ Vc + CLd * C2 / Vc;
		dydx[1] = CLd * C1 / Vp + (-CLd) * C2 / Vp;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] =1;
						
		}

		return dydx;
	}

	// Two compartment,iv infusion, parameterized as rate constant

	private double func_diff15(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];
		double t = x;

		double V = par[1];

		double[] C0 = new double[noOfDet]; // initial

		double[] Yc = new double[noOfDet];

		// initial values

		double h = 0.01; // step size
		double t0 = 0;

		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			C0[0] = 0;
			C0[1] = 0;
			t = x - conDosingTime[j];
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function15(double t, double[] C, double[] parameter,
			double[] conDose, double[] infusionTime) {
		double[] dydx = new double[C.length];
		double Kel = parameter[0];
		double Vc = parameter[1];
		double Kcp = parameter[2];
		double Kpc = parameter[3];

		double CLt = Kel * Vc;
		double CLd = Kcp * Vc;
		double Vp = Vc * Kcp / Kpc;

		double C1 = C[0];
		double C2 = C[1];
		double con;
		if (t < infusionTime[currentDoseNumber])
			con = conDose[currentDoseNumber] / infusionTime[currentDoseNumber];
		else
			con = 0;

		dydx[0] = (-1) * (CLt + CLd) * C1 / Vc + CLd * C2 / Vc + con / Vc;
		dydx[1] = CLd * C1 / Vp + (-CLd) * C2 / Vp;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] =1;
						
		}

		return dydx;
	}

	// Two compartment,iv infusion, parameterized as clearance

	private double func_diff16(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];
		double t = x;

		double V = par[1];

		double[] C0 = new double[noOfDet]; // initial
		// value
		// of
		// y

		double[] Yc = new double[noOfDet];

		// initial values

		double h = 0.01; // step size
		double t0 = 0;

		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;
		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			t = x - conDosingTime[j];
			C0[0] = 0;
			C0[1] = 0;
			Yc = solvingODE(h, t0, t, C0, Yc);
			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function16(double t, double[] C, double[] parameter,
			double[] conDose, double[] infusionTime) {
		double[] dydx = new double[C.length];
		double CLt = parameter[0];
		double Vc = parameter[1];
		double CLd = parameter[2];
		double Vp = parameter[3];

		double C1 = C[0];
		double C2 = C[1];
		double con = 0;
		if (t < infusionTime[currentDoseNumber])
			con = conDose[currentDoseNumber] / infusionTime[currentDoseNumber];

		dydx[0] = (-1) * (CLt + CLd) * C1 / Vc + CLd * C2 / Vc + con / Vc;
		dydx[1] = CLd * C1 / Vp + (-CLd) * C2 / Vp;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] =1;
						
		}

		return dydx;
	}

	// Three compartments,IV bolus, parameterized as rate constant

	private double func_diff17(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];

		double V = par[0];

		double Dose;
		double h = 0.01; // step size
		double t0 = 0;
		double t = x;
		double[] C0 = new double[noOfDet];
		double[] Yc = new double[noOfDet];
		int nDose = conDose.length;
		double dose1 = conDose[0];		
		double lastDose;
		double lastDoseTime = conDosingTime[0];
		C0[0] = dose1/V;
		C0[1] = 0;
		C0[2] = 0;

		
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;
		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;

			Dose = conDose[j];
			
			if(j>0)
			{
				lastDose = conDose[j];
					lastDoseTime = conDosingTime[j];
					C0[0] = 0 +lastDose / V;
					C0[1] = 0;
					C0[2] = 0;
				
			}
			
			
			t = x - conDosingTime[j];
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function17(double t, double[] C, double[] par) {
		double[] dydx = new double[C.length];
		double K10 = par[0];
		double K12 = par[1];
		double K21 = par[2];
		double K13 = par[3];
		double K31 = par[4];
		double V1 = par[5];

		double Clt = K10 * V1;
		double Cl2 = K12 * V1;
		double Cl3 = K13 * V1;
		double V2 = V1 * K12 / K21;
		double V3 = V1 * K13 / K31;

		double C1 = C[0];
		double C2 = C[1];
		double C3 = C[2];

		dydx[0] = (-1) * (Clt + Cl2 + Cl3) * C1 / V1 + Cl2 * C2 / V1 + Cl3 * C3
				/ V1;
		dydx[1] = Cl2 * C1 / V2 - Cl2 * C2 / V2;
		dydx[2] = Cl3 * C1 / V3 - Cl3 * C3 / V3;
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] = 1;
			dydx[2] = 1;
						
		}

		return dydx;
	}

	private double func_diff18(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];

		double V = par[1];

		double Dose;
		double h = 0.01; // step size
		double t0 = 0;
		double t = x;
		double[] C0 = new double[noOfDet];
		double[] Yc = new double[noOfDet];
		
		double dose1 = conDose[0];		
		double lastDose;
		double lastDoseTime = conDosingTime[0];
		C0[0] = dose1/V;
		C0[1] = 0;
		C0[2] = 0;


		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;
		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;

			Dose = conDose[j];
			
			if(j>0)
			{
				lastDose = conDose[j];
					lastDoseTime = conDosingTime[j];
					C0[0] = 0 +lastDose / V;
					C0[1] = 0;
					C0[2] = 0;
				
			}
			
			
			t = x - conDosingTime[j];
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function18(double t, double[] C, double[] par) {
		double[] dydx = new double[C.length];
		double Clt = par[0];
		double V1 = par[1];
		double Cl2 = par[2];
		double V2 = par[3];
		double Cl3 = par[4];
		double V3 = par[5];

		double C1 = C[0];
		double C2 = C[1];
		double C3 = C[2];

		dydx[0] = (-1) * (Clt + Cl2 + Cl3) * C1 / V1 + Cl2 * C2 / V1 + Cl3 * C3
				/ V1;
		dydx[1] = Cl2 * C1 / V2 - Cl2 * C2 / V2;
		dydx[2] = Cl3 * C1 / V3 - Cl3 * C3 / V3;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] = 1;
			dydx[2] = 1;
						
		}

		return dydx;
	}

	// Three compartments,iv infusion, parameterized as rate constant

	public double func_diff19(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];
		double t = x;

		double V = par[5];

		double[] C0 = new double[noOfDet];

		double[] Yc = new double[noOfDet];

		// initial values

		double h = 0.01; // step size
		double t0 = 0;

		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			t = x - conDosingTime[j];
			C0[0] = 0;
			C0[1] = 0;
			C0[2] = 0;
			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function19(double t, double[] C, double[] parameter,
			double[] conDose, double[] infusionTime) {
		double[] dydx = new double[C.length];
		double K10 = parameter[0];
		double K12 = parameter[1];
		double K21 = parameter[2];
		double K13 = parameter[3];
		double K31 = parameter[4];
		double V1 = parameter[5];

		double CLt = K10 * V1;
		double CL2 = K12 * V1;
		double V2 = V1 * K12 / K21;
		double CL3 = K13 * V1;
		double V3 = V1 * K13 / K31;

		double C1 = C[0];
		double C2 = C[1];
		double C3 = C[2];
		double con;
		if (t < infusionTime[currentDoseNumber])
			con = conDose[currentDoseNumber] / infusionTime[currentDoseNumber];
		else
			con = 0;
		dydx[0] = (-1) * (CLt + CL2 + CL3) * C1 / V1 + CL2 * C2 / V1 + CL3 * C3
				/ V1 + con / V1;
		dydx[1] = CL2 * C1 / V2 + (-CL2) * C2 / V2;
		dydx[2] = CL3 * C1 / V3 + (-CL3) * C3 / V3;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] = 1;
			dydx[2] = 1;
						
		}
		return dydx;
	}

	// Three compartments,iv infusion, parameterized as rate constant

	private double func_diff20(double[] par, double[] conDose,
			double[] conDosingTime, double x, int fn_no, double[][] Extra_DATA,
			int rowIdx, int[] row) throws RowsExceededException,
			WriteException, BiffException, IOException {
		double[] fn = new double[noOfFunction];
		double t = x;

		double V = par[5];

		double[] C0 = new double[noOfDet]; // initial

		double[] Yc = new double[noOfDet];

		// initial values

		double h = 0.01; // step size
		double t0 = 0;

		int nDose = conDose.length;
		int i = 0;
		do {
			i++;
		} while (i < nDose && x >= conDosingTime[i]);

		int ndose = i - 1;

		for (int j = 0; j <= ndose; j++) {
			currentDoseNumber = j;
			C0[0] = 0;
			C0[1] = 0;
			C0[2] = 0;

			t = x - conDosingTime[j];

			Yc = solvingODE(h, t0, t, C0, Yc);

			fn[0] = fn[0] + Yc[0];
		}

		//checkingForNanOrInfinite(fn_no, fn);

		return fn[fn_no];
	}

	private double[] function20(double t, double[] C, double[] parameter,
			double[] conDose, double[] infusionTime) {
		double[] dydx = new double[C.length];
		double CLt = parameter[0];
		double V1 = parameter[1];
		double CL2 = parameter[2];
		double V2 = parameter[3];
		double CL3 = parameter[4];
		double V3 = parameter[5];

		double C1 = C[0];
		double C2 = C[1];
		double C3 = C[2];
		double con;
		if (t < infusionTime[currentDoseNumber])
			con = conDose[currentDoseNumber] / infusionTime[currentDoseNumber];
		else
			con = 0;
		dydx[0] = (-1) * (CLt + CL2 + CL3) * C1 / V1 + CL2 * C2 / V1 + CL3 * C3
				/ V1 + con / V1;
		dydx[1] = CL2 * C1 / V2 + (-CL2) * C2 / V2;
		dydx[2] = CL3 * C1 / V3 + (-CL3) * C3 / V3;
		
		if (Double.isInfinite(dydx[0]) == true
				|| Double.isNaN(dydx[0]) == true) {
			
			pkPdInst.exceptionForCurrentProfile = 1;
			dydx[0] = 1;
			dydx[1] = 1;
			dydx[2] = 1;
						
		}

		return dydx;
	}

	/*
	 * // Three compartments,oral dose, parameterized as clearance
	 * 
	 * public double func_diff15(double[] par, double[] conDose, double x, int
	 * fn_no, double[][] Extra_DATA, int rowIdx, int[] row) throws
	 * RowsExceededException, WriteException, BiffException, IOException {
	 * double[] fn = new double[noOfFunction]; double t = x;
	 * 
	 * double V = par[5];
	 * 
	 * double[] C0 = new double[noOfDet]; // initial // value // of // y double
	 * Dose = conDose[0]; double[] Yc = new double[noOfDet];
	 * 
	 * // initial values C0[0] = 0; C0[1] = 0; C0[2] = 0;
	 * 
	 * double h = 0.01; // step size double t0 = 0;
	 * 
	 * Yc = solvingODE(h, t0, t, C0, Yc);
	 * 
	 * fn[0] = Yc[0] / V; checkingForNanOrInfinite(fn_no, fn);
	 * 
	 * return fn[fn_no]; }
	 * 
	 * public double[] function15(double t, double[] C, double[] parameter) {
	 * double[] dydx = new double[C.length]; double CLt = parameter[0]; double
	 * V1 = parameter[1]; double CL2 = parameter[2]; double V2 = parameter[3];
	 * double CL3 = parameter[4]; double V3 = parameter[5];
	 * 
	 * double C1 = C[0]; double C2 = C[1]; double C3 = C[2];
	 * 
	 * dydx[0] = (-1) * (CLt + CL2 + CL3) * C1 / V1 + CL2 * C2 / V2 + CL3 * C3 /
	 * V3; dydx[1] = CLt * C1 / V1 + (-CL2) * C2 / V2; dydx[2] = CLt * C1 / V1 +
	 * (-CL3) * C3 / V3;
	 * 
	 * return dydx; }
	 */

	// Three compartments,iv infusion, parameterized as clearance

	/*
	 * 
	 * // Two compartments, oral dose, Michaelis-Menten elimination,
	 * parameterized // as rate constant
	 * 
	 * public double func_diff18(double[] par, double[] conDose, double x, int
	 * fn_no, double[][] Extra_DATA, int rowIdx, int[] row) throws
	 * RowsExceededException, WriteException, BiffException, IOException {
	 * double[] fn = new double[noOfFunction]; double t = x;
	 * 
	 * double V = par[2];
	 * 
	 * double[] C0 = new double[noOfDet]; // initial // value // of // y double
	 * Dose = conDose[0]; double[] Yc = new double[noOfDet];
	 * 
	 * // initial values C0[0] = 0; C0[1] = 0; C0[2] = 0;
	 * 
	 * double h = 0.01; // step size double t0 = 0;
	 * 
	 * Yc = solvingODE(h, t0, t, C0, Yc);
	 * 
	 * fn[0] = Yc[0] / V; checkingForNanOrInfinite(fn_no, fn);
	 * 
	 * return fn[fn_no]; }
	 * 
	 * public double[] function18(double t, double[] C, double[] parameter,
	 * double[] con) { double[] dydx = new double[C.length]; double Km =
	 * parameter[0]; double Vmax = parameter[1]; double Vc = parameter[2];
	 * double Ka = parameter[3]; double Kcp = parameter[4]; double Kpc =
	 * parameter[5];
	 * 
	 * double C1 = C[0]; double C2 = C[1]; double C3 = C[2];
	 * 
	 * dydx[0] = (-1) * (Vmax / (Km + C1 / Vc)) * (C1 / Vc) - Kcp * C1 + Ka C2 +
	 * Kpc * C3; dydx[1] = Ka * C2; dydx[2] = Kcp * C1 - Kpc * C3;
	 * 
	 * return dydx; }
	 */
	// Two compartments,iv infusion, Michaelis-Menten elimination, parameterized
	// as clearance

	/*
	 * public double func_diff19(double[] par, double[] conDose, double x, int
	 * fn_no, double[][] Extra_DATA, int rowIdx, int[] row) throws
	 * RowsExceededException, WriteException, BiffException, IOException {
	 * double[] fn = new double[noOfFunction]; double t = x;
	 * 
	 * double V = par[2];
	 * 
	 * double[] C0 = new double[noOfDet]; double Dose = conDose[0]; double[] Yc
	 * = new double[noOfDet];
	 * 
	 * // initial values C0[0] = 0; C0[1] = 0;
	 * 
	 * double h = 0.01; // step size double t0 = 0;
	 * 
	 * Yc = solvingODE(h, t0, t, C0, Yc);
	 * 
	 * fn[0] = Yc[0] / V; checkingForNanOrInfinite(fn_no, fn);
	 * 
	 * return fn[fn_no]; }
	 * 
	 * public double[] function19(double t, double[] C, double[] parameter,
	 * double[] con) { double[] dydx = new double[C.length]; double Km =
	 * parameter[0]; double Vmax = parameter[1]; double Vc = parameter[2];
	 * double Kcp = parameter[3]; double Kpc = parameter[4];
	 * 
	 * double C1 = C[0]; double C2 = C[1];
	 * 
	 * dydx[0] = (-1) * (Vmax / (Km + C1 / Vc)) * (C1 / Vc) - Kcp * C1 + Kpc C2;
	 * dydx[2] = Kcp * C1 - Kpc * C2;
	 * 
	 * return dydx; }
	 * 
	 * // Two compartments,oral dose, Michaelis-Menten elimination,
	 * parameterized // as clearance, with lag time
	 * 
	 * public double func_diff20(double[] par, double[] conDose, double x, int
	 * fn_no, double[][] Extra_DATA, int rowIdx, int[] row) throws
	 * RowsExceededException, WriteException, BiffException, IOException {
	 * double[] fn = new double[noOfFunction]; double t = x;
	 * 
	 * double V = par[2]; double tlag = par[6];
	 * 
	 * double[] C0 = new double[noOfDet]; double Dose = conDose[0]; double[] Yc
	 * = new double[noOfDet];
	 * 
	 * // initial values C0[0] = 0; C0[1] = 0; C0[2] = 0;
	 * 
	 * double h = 0.01; // step size double t0 = 0;
	 * 
	 * t = t - tlag; if (t < 0) Yc[0] = 0; else {
	 * 
	 * Yc = solvingODE(h, t0, t, C0, Yc); }
	 * 
	 * fn[0] = Yc[0] / V; checkingForNanOrInfinite(fn_no, fn);
	 * 
	 * return fn[fn_no]; }
	 * 
	 * public double[] function20(double t, double[] C, double[] parameter,
	 * double[] con) { double[] dydx = new double[C.length]; double Km =
	 * parameter[0]; double Vmax = parameter[1]; double Vc = parameter[2];
	 * double Ka = parameter[3]; double Kcp = parameter[4]; double Kpc =
	 * parameter[5];
	 * 
	 * double C1 = C[0]; double C2 = C[1]; double C3 = C[2];
	 * 
	 * dydx[0] = (-1) * (Vmax / (Km + C1 / Vc)) * (C1 / Vc) - Kcp * C1 + Ka C2 +
	 * Kpc * C3; dydx[1] = Ka * C2; dydx[2] = Kcp * C1 - Kpc * C3;
	 * 
	 * return dydx; }
	 */

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
