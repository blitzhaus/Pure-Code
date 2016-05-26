package Model;

import java.io.IOException;
import java.lang.Math;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class BeeColony {

	int NP = 20; 
	int foodNumber = NP / 2; 
	int limit = 100;
	int maxCycle = 2500; 
	int D = 6;  
	double[] lb = null;   
	double[] ub = null;
	
	int runtime = 200; 
	
	int dizi1[] = new int[10];
	double foods[][]; 
	double f[]; 
	double fitness[]; 
	double trial[];
	double prob[]; 
	double solution[]; 
	double objValSol; 
	double fitnessSol; 
	int neighbour, param2change; 
	double globalMin; 
	double globalParams[]; 
	double globalMins[] ;
	double r;

	int[] row;
	double[][] X;
	double[][] Y;
	
	PkPdInfo pkPdInfoInst;
	ListOfDataStructures dataStructInst;
	PkParamEstimator pkParamEstInst;
	
	
	void initialization ()
	{
		foods = new double[foodNumber][D];
		f = new double[foodNumber];
		fitness = new double[foodNumber]; 
		trial = new double[foodNumber]; 
		prob = new double[foodNumber]; 
		solution = new double[D]; 
		globalParams = new double[D]; 
		globalMins = new double[runtime];
	}
	
	double calculateRefitness(double fun) {
		double result = 0;
		if (fun >= 0) {
			result = 1 / (fun + 1);
		} else {

			result = 1 + Math.abs(fun);
		}
		return result;
	}

	void memorizeBestSource() {
		int i, j;

		for (i = 0; i < foodNumber; i++) {
			if (f[i] < globalMin) {
				globalMin = f[i];
				for (j = 0; j < D; j++)
					globalParams[j] = foods[i][j];
			}
		}
	}

	void init(int index) throws RowsExceededException, WriteException, BiffException, IOException {
		int j;
		for (j = 0; j < D; j++) {
			r = ((double) Math.random() * 32767 / ((double) 32767 + (double) (1)));
			foods[index][j] = r * (ub[j] - lb[j]) + lb[j];
			solution[j] = foods[index][j];
		}
		f[index] = evaluateFunctionVal(solution, X, Y, row);
		fitness[index] = calculateRefitness(f[index]);
		trial[index] = 0;
	}

	void initial(double[] lBound, double[] uBound) throws RowsExceededException, WriteException, BiffException, IOException {
		int i;
		lb = lBound;
		ub = uBound;
		
		for (i = 0; i < foodNumber; i++) {
			init(i);
		}
		globalMin = f[0];
		for (i = 0; i < D; i++)
			globalParams[i] = foods[0][i];

	}

	void delegateToEmployedBees() throws RowsExceededException, WriteException, BiffException, IOException {
		int i, j;
			for (i = 0; i < foodNumber; i++) {
				r = ((double) Math.random() * 32767 / ((double) (32767) + (double) (1)));
			param2change = (int) (r * D);

			r = ((double) Math.random() * 32767 / ((double) (32767) + (double) (1)));
			neighbour = (int) (r * foodNumber);

			for (j = 0; j < D; j++)
				solution[j] = foods[i][j];

			r = ((double) Math.random() * 32767 / ((double) (32767) + (double) (1)));
			solution[param2change] = foods[i][param2change]
					+ (foods[i][param2change] - foods[neighbour][param2change])
					* (r - 0.5) * 2;

			if (solution[param2change] < lb[param2change])
				solution[param2change] = lb[param2change];
			if (solution[param2change] > ub[param2change])
				solution[param2change] = ub[param2change];
			objValSol = evaluateFunctionVal(solution, X, Y, row);
			fitnessSol = calculateRefitness(objValSol);

			if (fitnessSol > fitness[i]) {

				trial[i] = 0;
				for (j = 0; j < D; j++)
					foods[i][j] = solution[j];
				f[i] = objValSol;
				fitness[i] = fitnessSol;
			} else { 
				trial[i] = trial[i] + 1;
			}

		}
	}

	void computeProbs() {
		int i;
		double maxfit;
		maxfit = fitness[0];
		for (i = 1; i < foodNumber; i++) {
			if (fitness[i] > maxfit)
				maxfit = fitness[i];
		}

		for (i = 0; i < foodNumber; i++) {
			prob[i] = (0.9 * (fitness[i] / maxfit)) + 0.1;
		}

	}

	void delegateToOnlookerBees() throws RowsExceededException, WriteException, BiffException, IOException {

		int i, j, t;
		i = 0;
		t = 0;

		while (t < foodNumber) {

			r = ((double) Math.random() * 32767 / ((double) (32767) + (double) (1)));
			if (r < prob[i]) 
			{
				t++;

				r = ((double) Math.random() * 32767 / ((double) (32767) + (double) (1)));
				param2change = (int) (r * D);

				r = ((double) Math.random() * 32767 / ((double) (32767) + (double) (1)));
				neighbour = (int) (r * foodNumber);

				while (neighbour == i) {
					r = ((double) Math.random() * 32767 / ((double) (32767) + (double) (1)));
					neighbour = (int) (r * foodNumber);
				}
				for (j = 0; j < D; j++)
					solution[j] = foods[i][j];

				r = ((double) Math.random() * 32767 / ((double) (32767) + (double) (1)));
				solution[param2change] = foods[i][param2change]
						+ (foods[i][param2change] - foods[neighbour][param2change])
						* (r - 0.5) * 2;

				if (solution[param2change] < lb[param2change])
					solution[param2change] = lb[param2change];
				if (solution[param2change] > ub[param2change])
					solution[param2change] = ub[param2change];
				objValSol = evaluateFunctionVal(solution,X,Y,row);
				fitnessSol = calculateRefitness(objValSol);

				if (fitnessSol > fitness[i]) {
					trial[i] = 0;
					for (j = 0; j < D; j++)
						foods[i][j] = solution[j];
					f[i] = objValSol;
					fitness[i] = fitnessSol;
				} else { 
					trial[i] = trial[i] + 1;
				}
			} 
			i++;
			if (i == foodNumber)
				i = 0;
		}
	}

	void delegateToScoutBees() throws RowsExceededException, WriteException, BiffException, IOException {
		int maxtrialindex, i;
		maxtrialindex = 0;
		for (i = 1; i < foodNumber; i++) {
			if (trial[i] > trial[maxtrialindex])
				maxtrialindex = i;
		}
		if (trial[maxtrialindex] >= limit) {
			init(maxtrialindex);
		}
	}

	double evaluateFunctionVal(double sol[], double[][] X, double[][] Y, int[] row) throws RowsExceededException, WriteException, BiffException, IOException {
		return Objective(sol, X, Y, row);
	}

	
	public double Objective(double[] par, double[][] X, double[][] Y, int[] row) throws RowsExceededException, WriteException, BiffException, IOException 
	{
		int Nfun = 1;
		double[] weightResSq = new double[Nfun];
		double WRSS = 0;
		for(int i = 0; i < Nfun; i++)
		{
			int r = row[i];
						
			double[][] errorMat = error(par, X, Y, i, row);
			
			for(int j = 0; j < r; j++)
			{
				
				double WtresSq =  Math.pow(errorMat[j][0], 2);
				weightResSq[i] = weightResSq[i] + WtresSq;
			}

			WRSS = WRSS + weightResSq[i];
		}

		return WRSS;
	}

	// error matrix

	
	public double[][] error(double[] par,  double[][] X, double[][] Y, int fn_no, int[] row) throws RowsExceededException, WriteException, BiffException, IOException 
	{
		int r = row[fn_no];
		double[][] errorMat = new double[r][1];
		double[][] extraData = new double[1][1];
		
		
		LibraryModelDefiner libModelInst= new LibraryModelDefiner();
		double fn = 0;
		for(int i = 0; i < r; i++)
		{
			double xf = X[i][fn_no];
			double yf = Y[i][fn_no];

			if(xf == 999999.9)
				errorMat[i][0] = 0;
			else
			{
				if(pkPdInfoInst.analysisType.equals("pk"))
				{
					fn = libModelInst.chooseLibraryModel(par, pkParamEstInst.infusionTime,pkParamEstInst.dose,
							pkParamEstInst.dosingTime , xf, fn_no, extraData, i, row);
				}
				else
				{
					fn = libModelInst.chooseLibraryModel(par,
							dataStructInst.infusionTime, dataStructInst.dose, dataStructInst.dosingTime,
							xf, fn_no, extraData, i, row);
				}
								
				
				errorMat[i][0] = yf - fn;
			}
		}
		return errorMat;
	}

	

	public double fnEval(double[] par, double x) {
		int fn_no = 0;
		double[] infusionTime = new double[1];
		double[] conDose = new double[1];
		conDose[0] = 100;
		double[] conDosingTime = new double[1];
		conDosingTime[0] = 0;
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

	private double[][] convertToDouble(String[][] data) {
		double[][] dblArray = new double[data.length][data[0].length];

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				dblArray[i][j] = Double.parseDouble(data[i][j]);
			}
		}

		return dblArray;
	}

	private double[][] getX(double[][] XY) {
		double[][] x = new double[XY.length][1];

		for (int i = 0; i < XY.length; i++) {
			x[i][0] = XY[i][0];
		}
		return x;
	}

	private double[][] getY(double[][] XY) {
		double[][] y = new double[XY.length][1];

		for (int i = 0; i < XY.length; i++) {
			y[i][0] = XY[i][1];
		}

		return y;
	}

	private void setData(double[][] X_, double[][] Y_, double[][] Extra_DATA_,
			int[] row_) {
		X = X_;
		Y = Y_;
		//Extra_DATA = Extra_DATA_;
		row = row_;
	}

	public void loadData() {
		pkPdInfoInst = PkPdInfo.createPKPDInstance();
		pkParamEstInst = PkParamEstimator.createPkParamEstimateInstance();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		
		X = pkPdInfoInst.X;
		Y = pkPdInfoInst.Y;
		row = pkPdInfoInst.row;
		
	}

	
	
}
