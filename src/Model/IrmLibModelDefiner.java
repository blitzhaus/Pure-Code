package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import pkpd_library.integration.DFunction;
import pkpd_library.integration.ODEsolver;
import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class IrmLibModelDefiner implements DFunction{

	double[] parameter;
	double[] dose;
	double[] dosingTime;
	double[] lengthOfInfusion;
	double[] infusionDose;
	int odeSolverChoice;
	int noOfParam;
	int noOfFunction;
	int noOfDet;
	int currentProfileNo;
	int currentDosingNo;
	ApplicationInfo appInfo;
	ListOfDataStructures dataStructInst;
	ProcessingInputsInfo procInputInst;
	double[][] concForLinkModel;
	double plasmaConc;
	boolean ifSimulation;
	

	public IrmLibModelDefiner() {

		parameter = new double[1];
		dose = new double[1];
		dosingTime = new double[1];
		lengthOfInfusion = new double[1];
		odeSolverChoice = 1;
		infusionDose = new double[1];
		currentProfileNo = 0;
	}

	public double chooseIrmModel(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		
		procInputInst = PkPdInfo.createPKPDInstance().copyProcessingInput();

		noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();
		noOfFunction = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfFunction();
		noOfDet = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfDeterminant();

		odeSolverChoice = procInputInst.getModelInputTab2Obj()
				.getOdeSolverMethod() + 1;

		int modelNumber = procInputInst.getModelInputTab1Obj()
				.getModelNumberForCA();


		currentProfileNo = PkPdInfo.createPKPDInstance().currentProfileNumber;
		
		ifSimulation = procInputInst.getModelInputTab1Obj().isSimulation();

		if(ifSimulation == true)
		{
			SimulationForLibraryModels simulationInst = SimulationForLibraryModels.createSimulationInst();
			concForLinkModel = new double[simulationInst.plasmaConc.length][1];
			
			for (int i = 0; i < concForLinkModel.length; i++) {
				concForLinkModel[i][0] = simulationInst.plasmaConc[i];
			}
		}else
		{
			dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
			appInfo = dataStructInst.appInfo;
			concForLinkModel = new double[dataStructInst.concForLinkModel.length][dataStructInst.concForLinkModel[0].length];
			
			for (int i = 0; i < concForLinkModel.length; i++) {
				concForLinkModel[i][0] = dataStructInst.concForLinkModel[i][0];
			}
		}
		
		
		
		setParameter(par);
		setDose(conDose);
		setDosingTime(conDosingTime);
		setLengthOfInfusion(infusionTime);
		setInfusionDose(infusionDose);
		double value = 0;
		if (modelNumber == 1)
			value = func_diff1(par, conDose,conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 2)
			value = func_diff2(par, conDose, conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 3)
			value = func_diff3(par, conDose, conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 4)
			value = func_diff4(par, conDose, conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);
		

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
		int modelNumber = procInputInst.getModelInputTab1Obj()
				.getModelNumberForCA();

		double[] rateOfInfusion = new double[lengthOfInfusion.length];
		for (int i = 0; i < rateOfInfusion.length; i++) {
			rateOfInfusion[i] = dose[i] / lengthOfInfusion[i];
		}

		if (modelNumber == 1)
			dydx = function1(t, C, getParameter());
		if (modelNumber == 2)
			dydx = function2(t, C, getParameter(), lengthOfInfusion[currentDosingNo], dose[currentDosingNo]);
		if (modelNumber == 3)
			dydx = function3(t, C, getParameter());
		if (modelNumber == 4)
			dydx = function4(t, C, getParameter());
		

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
	
	
	// Inhibition of input
		
	private double func_diff1(double[] par, double[] conDose,double[] conDosingTime, double x,
			int fn_no, double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[noOfFunction];

		double h = 0.01; // step size
		double t0 = 0;
		double t=x ;
		double[] C0 = new double[noOfDet];
		double[] Yc = new double[noOfDet];
		double Kin = par[0];
		double Kout = par[1];
				
		C0[0] = Kin/Kout;
		
		plasmaConc = concForLinkModel[rowIdx][0];
		//C0[0] = 0;
			
		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = fn[0] +  Math.max(0, Yc[0]);

		

		return fn[fn_no];
	}

	private double[] function1(double t, double[] C, double[] par) {
		double[] dydx = new double[C.length];
		
		double Kin = par[0];
		double Kout = par[1];
		double IC50 = par[2];

		double C1 = C[0];

		dydx[0] = Kin*(1- plasmaConc/(plasmaConc + IC50)) - Kout *C1;

		return dydx;
	}

	//Inhibition of output
	private double func_diff2(double[] par, double[] conDose, double[] conDosingTime, double x,
			int fn_no, double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[noOfFunction];

		double h = 0.01; // step size
		double t0 = 0;
		double t = x ;
		double[] C0 = new double[noOfDet];
		double[] Yc = new double[noOfDet];
		double Kin = par[0];
		double Kout = par[1];
				
		C0[0] = Kin/Kout;
		plasmaConc = concForLinkModel[rowIdx][0];
			
		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = fn[0] +  Math.max(0, Yc[0]);

		return fn[fn_no];
	}

	private double[] function2(double t, double[] C, double[] par, double lengthOfInfusion, double dose) {
		double[] dydx = new double[C.length];
		double Kin = par[0];
		double Kout = par[1];
		double IC50 = par[2];
		
		double C1 = C[0];

		dydx[0] = Kin - Kout*(1 - plasmaConc / (plasmaConc + IC50))*C1;

		
		return dydx;
	}

	// Stimulation of input

	private double func_diff3(double[] par, double[] conDose, double[] conDosingTime, double x,
			int fn_no, double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[noOfFunction];

		double h = 0.01; // step size
		double t0 = 0;
		double t = x;
		double[] C0 = new double[noOfDet];
		double[] Yc = new double[noOfDet];
	
		double Kin = par[0];
		double Kout = par[1];
				
		C0[0] = Kin/Kout;
		plasmaConc = concForLinkModel[rowIdx][0];
			
		Yc = solvingODE(h, t0, t, C0, Yc);
		fn[0] = fn[0] +  Math.max(0, Yc[0]);

		return fn[fn_no];
	}

	private double[] function3(double t, double[] C, double[] par) {
		double[] dydx = new double[C.length];
		double Kin = par[0];
		double Kout = par[1];
		double EC50 = par[2];
		double Emax = par[3];
		
		double C1 = C[0];

		dydx[0] = Kin * (1 + (Emax * plasmaConc)/(plasmaConc + EC50)) - Kout *C1;

		return dydx;
	}

	// Stimulation of output
	private double func_diff4(double[] par, double[] conDose, double[] conDosingTime, double x,
			int fn_no, double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] fn = new double[noOfFunction];

		double h = 0.01; // step size
		double t0 = 0;
		double t = x;
		double[] C0 = new double[noOfDet];
		double[] Yc = new double[noOfDet];
		
		double Kin = par[0];
		double Kout = par[1];
				
		C0[0] = Kin/Kout;
		plasmaConc = concForLinkModel[rowIdx][0];
			
		Yc = solvingODE(h, t0, t, C0, Yc);				
		fn[0] = fn[0] +  Math.max(0, Yc[0]);

		return fn[fn_no];
	}

	private double[] function4(double t, double[] C, double[] par) {
		double[] dydx = new double[C.length];
		double Kin = par[0];
		double Kout = par[1];
		double EC50 = par[2];
		double Emax = par[3];
		double C1 = C[0];

		dydx[0] = Kin - Kout * (1 + (Emax * plasmaConc)/(plasmaConc + EC50))*C1;

		return dydx;
	}


	private void checkingForNanOrInfinite(int fn_no, double[] fn)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (Double.isInfinite(fn[fn_no]) == true
				|| Double.isNaN(fn[fn_no]) == true) {
		}
	}

}
