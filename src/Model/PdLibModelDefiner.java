package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class PdLibModelDefiner {
	
	
	int modelNumber;
	/*CaParametersCalculation pkpdMainInst;
	boolean clearancaParam;*/
	
	ListOfDataStructures dataStructInst;

	public double choosePdModel(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		/*pkpdMainInst = CaParametersCalculation.createPKPDMainInstance();
		ApplicationInfo appInfo = pkpdMainInst.getAppInfo();*/
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance(); 
		ProcessingInputsInfo procInputInst = PkPdInfo.createPKPDInstance().copyProcessingInput();

		modelNumber = procInputInst.getModelInputTab1Obj()
				.getModelNumberForCA();

	

		double value = 0;

		
		if (modelNumber == 1)
			value = func_diff1(par, infusionTime,  conDose, conDosingTime,  x, fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 2)
			value = func_diff2(par, infusionTime,  conDose, conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 3)
			value = func_diff3(par, infusionTime,  conDose, conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 4)
			value = func_diff4(par, infusionTime,  conDose, conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);
		else if (modelNumber == 5)
			value = func_diff5(par, infusionTime,  conDose, conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 6)
			value = func_diff6(par, infusionTime,  conDose, conDosingTime, x, fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 7)
			value = func_diff7(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);

		else if (modelNumber == 8)
			value = func_diff8(par, infusionTime, conDose, conDosingTime, x,
					fn_no, Extra_DATA, rowIdx, row);

	

		return value;
	}
	
	private double func_diff1(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		
		double[] fn = new double[1];
		double Emax = par[0];
		double EC50 = par[1];
		double c= x;
		
		double res = 0;

		double amt = 0;
		amt = Emax*c/(c + EC50);
		res = Math.max(0, amt);
		fn[0] = res;

		return fn[fn_no];
	}
	
	private double func_diff2(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double[] fn = new double[1];
		double Emax = par[0];
		double E0 =  par[1];
		double EC50 = par[2];
		double c = x;
		double res = 0;

		double amt = 0;
		amt = E0 + (Emax*c)/(c + EC50);
		res = Math.max(0, amt);
		fn[0] = res;

		return fn[fn_no];
	}
	
	private double func_diff3(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double[] fn = new double[1];
		double E0 = par[0];
		double IC50 = par[1];
		double c = x;
		double res = 0;

		double amt = 0;
		amt = E0*(1 - (c/(c + IC50)));
		res = Math.max(0, amt);
		fn[0] = res;

		return fn[fn_no];
	}

	
	private double func_diff4(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double[] fn = new double[1];
		double Imax = par[0];
		double IC50 = par[1];
		double E0 = par[2];
		double c = x;
		double res = 0;

		
		double amt = 0;
	//	amt = Emax -  (Emax - E0)*(c/(c + EC50));
		
		amt = E0 - ((Imax * c)/(IC50 + c));
		res = Math.max(0, amt);
		fn[0] = res;

		return fn[fn_no];
	}

	private double func_diff5(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double[] fn = new double[1];
		double Emax = par[0];
		double EC50 = par[1];
		double gamma = par[2];
		double c = x;
		double res = 0;

		double amt = 0;
		amt = (Emax * Math.pow(c, gamma))/(Math.pow(c, gamma) + Math.pow(EC50, gamma));
		res = Math.max(0, amt);
		fn[0] = res;

		return fn[fn_no];
	}

	private double func_diff6(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double[] fn = new double[1];
		double Emax = par[0];
		double EC50 = par[1];
		double E0 = par[2];
		double gamma = par[3];
		double c = x;
		double res = 0;

		double amt = 0;
		amt = E0 + (Emax*Math.pow(c, gamma)) /(Math.pow(c, gamma) + Math.pow(EC50, gamma));
		res = Math.max(0, amt);
		fn[0] = res;

		return fn[fn_no];
	}

	private double func_diff7(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double[] fn = new double[1];
		double E0 = par[0];
		double IC50 = par[1];
		double gamma = par[2];
		double c = x;
		double res = 0;

		double amt = 0;
		amt = E0 *(1- (Math.pow(c, gamma) /(Math.pow(c, gamma) + Math.pow(IC50, gamma))));
		res = Math.max(0, amt);
		fn[0] = res;

		return fn[fn_no];
	}
	
	
	private double func_diff8(double[] par, double[] infusionTime,
			double[] conDose, double[] conDosingTime, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		double[] fn = new double[1];
		double Imax = par[0];
		double IC50 = par[1];
		double E0 = par[2];
		double gamma = par[3];
		double c = x;
		double res = 0;

		double amt = 0;
		amt = E0 - (Imax *Math.pow(c, gamma)) /(Math.pow(c, gamma) + Math.pow(IC50, gamma));
		res = Math.max(0, amt);
		fn[0] = res;

		return fn[fn_no];
	}
}
