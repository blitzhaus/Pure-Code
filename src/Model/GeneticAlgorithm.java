package Model;

import java.util.*;
import java.io.*;

import view.ApplicationInfo;
import view.DisplayContents;


import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

class Individual {
	double fitnessValue;
	int noOfParams;
	double[] parameters = new double[noOfParams];

	public double getFitnessValue() {
		return fitnessValue;
	}

	public void setFitnessValue(double fitnessValue) {
		this.fitnessValue = fitnessValue;
	}

	public int getNoOfParams() {
		return parameters.length;
	}

	public void setNoOfParams(int noOfParams) {
		this.noOfParams = noOfParams;
	}

	public double[] getParameters() {
		return parameters;
	}

	public void setParameters(double[] params) {
		noOfParams = params.length;
		this.parameters = new double[noOfParams];
		parameters = params;
	}

	public Individual() {
		super();
		this.fitnessValue = 0.0;
		noOfParams = 0;
		parameters = new double[noOfParams];
	}

	public Individual(double fitnessValue, double[] params) {
		super();
		this.fitnessValue = fitnessValue;
		noOfParams = params.length;
		parameters = new double[noOfParams];
		parameters = params;
	}

	public String toString() {
		String str = "[";
		for (int i = 0; i < noOfParams; i++) {
			str += parameters[i] + ",";
		}
		str += "]";
		str += "\t\t\t" + fitnessValue;

		return str;
	}
}

class IndividualsComparator implements Comparator {
	public int compare(Object ind1, Object ind2) {
		double distance1 = ((Individual) ind1).getFitnessValue();
		double distance2 = ((Individual) ind2).getFitnessValue();

		if (distance1 > distance2)
			return 1;
		else if (distance1 < distance2)
			return -1;
		else
			return 0;
	}

}

public class GeneticAlgorithm  {
	double prevR = 999999999999.999;
	double[] prevDescrs;
	int nPop;
	double mutationProb;
	double crossOverProb;
	PkPdInfo pkpdInst;
	ListOfDataStructures dataStructInst;
	ApplicationInfo appInfo;
	

	Vector popOfIndividuals = new Vector();

	Vector bestIndividuals = new Vector();
	int IT;
	PkParamEstimator pkpdMainInstance;

	PrintStream P22 = null;

	Individual bestInd = new Individual();

	private void FUNCTION_ENTER(String func_name) {
		try {
			P22.println("Entering\t" + func_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void FUNCTION_EXIT(String func_name) {

		try {
			P22.println("Leaving\t" + func_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public double[] GeneticAlgorithm(double[] upperBound,
			double[] lowerBound, double[][] X, double[][] Y,
			double[][] Extra_DATA, int[] row, double[] delta) throws RowsExceededException, WriteException, BiffException, IOException {
		try {
			P22 = new PrintStream(new FileOutputStream("Ga_trace.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pkpdMainInstance = PkParamEstimator.createPkParamEstimateInstance();
		pkpdInst = PkPdInfo.createPKPDInstance();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		
		if (pkpdInst.analysisType.equals("pk")) {
			PkParamEstimator caParamCalInst = PkParamEstimator
					.createPkParamEstimateInstance();
			appInfo = caParamCalInst.appInfoinst;
		} else {
			ListOfDataStructures dataStructInst = ListOfDataStructures
					.createListOfDataStructInstance();
			appInfo = dataStructInst.appInfo;
		}
		nPop = 50;
		mutationProb = 0.2;
		crossOverProb = 0.8;
		int Npar = upperBound.length;
		prevDescrs = new double[Npar];
		double[] BestInd = new double[Npar];
		double prevSRS = 99999999.9;
		double[] parameter = new double[Npar];

		double[][] population = generateIndividuals(upperBound, lowerBound,
				nPop);

		for (int i = 0; i < nPop; i++) {
			parameter = population[i];

			double fitnessValue = Objective(parameter, X, Y, Extra_DATA, row);

			if (fitnessValue < prevR) {
				prevR = fitnessValue;
				for (int h = 0; h < parameter.length; h++)
					prevDescrs[h] = parameter[h];

			}

			Individual ind = new Individual(fitnessValue, parameter);
			popOfIndividuals.insertElementAt(ind, i);
		}

		Collections.sort(popOfIndividuals, new IndividualsComparator());

		int iter_GA = 200;

		for (int i = 0; i < iter_GA; i++) {

			performCrossOverMutation(0, nPop, Npar, X, Y, Extra_DATA, row,
					upperBound, lowerBound, nPop);
			Collections.sort(popOfIndividuals, new IndividualsComparator());
			System.gc();
			IT = i;
			pkpdInst.iterNoForProgBar ++;
			
			pkpdInst.setProgressBarIterNo(pkpdInst.iterNoForProgBar );
			
			
		}

		Individual ind1 = (Individual) popOfIndividuals.get(0);

		return prevDescrs;
	}

	

	public void performCrossOverMutation(int startIdx, int popSize,
			int descCount, double[][] X, double[][] Y, double[][] Extra_DATA,
			int[] row, double[] upperBound, double[] lowerBound, int Npop) {
		boolean crossOverMutationSuccessful = false;
		try {
			for (int j = startIdx; j < popSize - 1; j += 2) {
				performCrossOver(j, j + 1, descCount, X, Y, Extra_DATA, row,
						upperBound, lowerBound, Npop);
				performMutation(j, j + 1, descCount, X, Y, Extra_DATA, row,
						upperBound, lowerBound, Npop);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	 private void performCrossOver(int firstIdx, int secIdx, int descCount,
			double[][] X, double[][] Y, double[][] Extra_DATA, int[] row,
			double[] upperBound, double[] lowerBound, int Npop) throws RowsExceededException, WriteException, BiffException, IOException {
		Vector crossOverMasks = new Vector();

		Individual ind1 = (Individual) popOfIndividuals.get(firstIdx);
		Individual ind2 = (Individual) popOfIndividuals.get(secIdx);

		double[] descrs1 = ind1.getParameters();
		double[] descrs2 = ind2.getParameters();

		for (int k = 0; k < descCount; k++) {
			crossOverMasks.add(new Double(Math.random()));
		}

		for (int k = 0; k < descCount; k++) {
			Double coProb = (Double) crossOverMasks.get(k);
			double crossOverProb1 = coProb.doubleValue();
			if (crossOverProb1 > crossOverProb) {
				double temp = descrs1[k];
				descrs1[k] = descrs2[k];
				descrs2[k] = temp;
			}
		}

		double R = Objective(descrs1, X, Y, Extra_DATA, row);
		double firstReg = R;

		if (firstReg < prevR) {

			prevR = firstReg;
			for (int h = 0; h < descrs1.length; h++)
				prevDescrs[h] = descrs1[h];

		}

		R = Objective(descrs2, X, Y, Extra_DATA, row);
		double secReg = R;

		if (secReg < prevR) {
			prevR = secReg;
			for (int h = 0; h < descrs2.length; h++)
				prevDescrs[h] = descrs2[h];

		}

		ind1 = new Individual(firstReg, descrs1);
		ind2 = new Individual(secReg, descrs2);

		popOfIndividuals.remove(firstIdx);
		popOfIndividuals.insertElementAt(ind1, firstIdx);

		popOfIndividuals.remove(secIdx);
		popOfIndividuals.insertElementAt(ind2, secIdx);
	}

	private void performMutation(int firstIdx, int secIdx, int descCount,
			double[][] X, double[][] Y, double[][] Extra_DATA, int[] row,
			double[] upperBound, double[] lowerBound, int Npop) throws RowsExceededException, WriteException, BiffException, IOException {

		Vector mutation1Masks = new Vector();
		Vector mutation2Masks = new Vector();

		double[][] individuals = generateIndividuals(upperBound, lowerBound, 2);

		Individual ind1 = (Individual) popOfIndividuals.get(firstIdx);
		Individual ind2 = (Individual) popOfIndividuals.get(secIdx);

		double[] descrs1 = ind1.getParameters();
		double[] descrs2 = ind2.getParameters();

		for (int k = 0; k < descCount; k++) {
			mutation1Masks.add(new Double(Math.random()));
			mutation2Masks.add(new Double(Math.random()));
		}
		for (int k = 0; k < descCount; k++) {
			Double muProb1 = (Double) mutation1Masks.get(k);
			Double muProb2 = (Double) mutation2Masks.get(k);

			double muProbability1 = muProb1.doubleValue();
			double muProbability2 = muProb2.doubleValue();

			if (muProbability1 < mutationProb || muProbability2 < mutationProb) {
				if (muProbability1 < mutationProb) {
					descrs1[k] = individuals[0][k];
				}
				if (muProbability2 < mutationProb) {
					descrs2[k] = individuals[1][k];
				}
			}
		}

		double R = Objective(descrs1, X, Y, Extra_DATA, row);
		double firstReg = R;

		if (firstReg < prevR) {
			prevR = firstReg;
			for (int h = 0; h < descrs1.length; h++)
				prevDescrs[h] = descrs1[h];

		}
		R = Objective(descrs2, X, Y, Extra_DATA, row);
		double secReg = R;

		if (secReg < prevR) {
			prevR = secReg;
			for (int h = 0; h < descrs2.length; h++)
				prevDescrs[h] = descrs2[h];

		}
		ind1 = new Individual(firstReg, descrs1);
		ind2 = new Individual(secReg, descrs2);

		popOfIndividuals.remove(firstIdx);
		popOfIndividuals.insertElementAt(ind1, firstIdx);

		popOfIndividuals.remove(secIdx);
		popOfIndividuals.insertElementAt(ind2, secIdx);
	}

	// Objective function: (Weighted sum of residuals)
	public double Objective(double[] par, double[][] X, double[][] Y,
			double[][] Extra_DATA, int[] row) throws RowsExceededException, WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int Nfun = 1;
		double[] weightResSq = new double[Nfun];
		double WRSS = 0;
		for (int i = 0; i < Nfun; i++) {
			int r = row[i];
			double[][] scaledWts = new double[r][r];
			double[][] errorMat = error(par, X, Y, Extra_DATA, i, row);

			for (int j = 0; j < r; j++) {
				double WtresSq = Math.pow(errorMat[j][0], 2);
				weightResSq[i] = weightResSq[i] + WtresSq;
			}

			WRSS = WRSS + weightResSq[i];
		}

		return WRSS;
	}

	// Weight matrix (Q)
	private double[][] weights(double[] par, double[][] X, double[][] Y,
			double[][] Extra_DATA, int fn_no, int[] row) throws RowsExceededException, WriteException, BiffException, IOException {
		int r = row[fn_no];
		double[][] wts = new double[r][r];
		LibraryModelDefiner libModelInst= new LibraryModelDefiner();
		PkPdInfo pkpdInst= PkPdInfo.createPKPDInstance();
		
		for (int i = 0; i < r; i++) {
			double xf = X[i][fn_no];
			double yf = Y[i][fn_no];
			
			double fn =0;
			if(pkpdInst.analysisType.equals("pk"))
			{
			 fn = libModelInst.chooseLibraryModel(par,
					pkpdMainInstance.infusionTime, pkpdMainInstance.dose, pkpdMainInstance.dosingTime,
					xf, fn_no, Extra_DATA, i, row);
			}
			else
			{
				fn = libModelInst.chooseLibraryModel(par,
						dataStructInst.infusionTime, dataStructInst.dose, dataStructInst.dosingTime,
						xf, fn_no, Extra_DATA, i, row);
			}

			for (int j = 0; j < r; j++) {
				if (i != j)
					wts[i][j] = 0;
				else {
					if (xf == 999999.9)
						wts[i][j] = 1.0;
					else {
						if (pkpdInst.wtScheme == 0) // WLS
							wts[i][j] = Math.pow(yf, pkpdInst.wtExp);
						else if (pkpdInst.wtScheme == 1) // IRLS
							wts[i][j] = Math.pow(fn, pkpdInst.wtExp);
						
					}
				}
			}
		}
		return wts;
	}

	// error matrix
	public double[][] error(double[] par, double[][] X, double[][] Y,
			double[][] Extra_DATA, int fn_no, int[] row) throws RowsExceededException, WriteException, BiffException, IOException {
		int r = row[fn_no];
		double[][] errorMat = new double[r][1];
		LibraryModelDefiner libModelInst= new LibraryModelDefiner();

		for (int i = 0; i < r; i++) {
			double xf = X[i][fn_no];
			double yf = Y[i][fn_no];

			if (xf == 999999.9)
				errorMat[i][0] = 0;
			else {
				double fn = 0;
				
				if(pkpdInst.analysisType.equals("pk"))
				{
				
				 fn = libModelInst.chooseLibraryModel(par,
						pkpdMainInstance.infusionTime, pkpdMainInstance.dose,
						pkpdMainInstance.dosingTime, xf, fn_no, Extra_DATA, i, row);
				}else
				{
					 fn = libModelInst.chooseLibraryModel(par,
								dataStructInst.infusionTime, dataStructInst.dose,
								dataStructInst.dosingTime, xf, fn_no, Extra_DATA, i, row);
				}
				
				
				errorMat[i][0] = yf - fn;
			}
		}
		return errorMat;
	}

	// Define population
	private double[][] generateIndividuals(double[] upperBound,
			double[] lowerBound, int Npop) {
		int Npar = upperBound.length;
		double[][] individual = new double[Npop][Npar];
		for (int i = 0; i < Npop; i++) {
			for (int j = 0; j < Npar; j++) {
				double max = upperBound[j];
				double min = lowerBound[j];
				individual[i][j] = Math.random() * (max - min) + min;
			}
		}
		return individual;
	}

	private void printMatrix(String str, double[][] matrix, int rowDim,
			int colDim) {

		P22.println("Input String:" + str);
		P22.println("row Dimension:" + rowDim + "\n");
		P22.println("col Dimension:" + colDim + "\n");

		if (str.length() != 0) {

			for (int r = 0; r < rowDim; r++) {
				for (int c = 0; c < colDim; c++) {
					P22.print(matrix[r][c] + "\t");
				}
				P22.println();
			}
			P22.println("\n");
		}
	}

	private void printVector(String str, double[] vector, int colDim) {

		P22.println("Input String:" + str);
		P22.println("col Dimension:" + colDim + "\n");

		for (int c = 0; c < colDim; c++) {
			P22.print(vector[c] + "\t");
		}

		P22.println("\n");
	}

	private double round(double value, int decimalPlace) {
		double power_of_ten = 1;
		while (decimalPlace-- > 0) {
			power_of_ten *= 10.0;
		}
		return Math.round(value * power_of_ten) / power_of_ten;
	}

	public PrintStream P2;

	private void main(String[] aArguments) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
