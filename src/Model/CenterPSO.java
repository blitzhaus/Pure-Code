package Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.itextpdf.text.log.SysoLogger;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CenterPSO {

	PkPdInfo pkPdInfoInst;
	PkParamEstimator pkParamEstInst;
	ListOfDataStructures dataStructInst;
	
	PrintStream P22 = null;
	Vector popOfIndividuals = new Vector();
	Vector copyOfPopulation = new Vector();
	double prevR = 999999999999.999;
	double[] prevDescrs;
	int nPop = 0;
	double mutationProb = 0.0;
	double crossOverProb = 0.0;
	double psoProcProb = 0.0;
	int iterMax = 300;
	IndividualParticle glblBest = null;
	
	IndividualParticle bestInd;
	boolean ifTerminate = false; 
	

	double value1;
	double value2;
	double Pbest;
	double Pold;
	double sValue;
	

	int[] row;
	double[][] X;
	double[][] Y;
	double[][] Extra_DATA;
	
	double[] upperBound = null;
	double[] lowerBound = null;
	
	public IndividualParticle getGlblBest() {
		return glblBest;
	}

	public void setGlblBest(IndividualParticle glblBest) {
		this.glblBest = glblBest;
	}

	
	
	void printPopulation(String str)
	{
		P22.println(str);
		
		for (int i = 0; i < popOfIndividuals.size(); i++) {
			IndividualParticle indPart = (IndividualParticle) popOfIndividuals.get(i);
				P22.println(indPart);
		}
	}

	// Define population
	public double[][] generateIndividuals(double[] upperBound,
			double[] lowerBound, int Npop) {
		int Npar = upperBound.length;
		
		Random rand = new Random(99999);
		double[][] individual = new double[Npop][Npar];
		for (int i = 0; i < Npop; i++) {
			for (int j = 0; j < Npar; j++) {
				double max = upperBound[j];
				double min = lowerBound[j];
				individual[i][j] = rand.nextFloat() * (max - min) + min;
			}
		}
		return individual;
		
		
		
	}
	
	
	double[][] createPopUsingGS(double[] upperBound,
			double[] lowerBound, int Npop) throws RowsExceededException, WriteException, BiffException, IOException 
	{
		int noOfAvlPop = (int) Math.pow(4, upperBound.length);
		double[][] avlPop = new double[noOfAvlPop][upperBound.length];
		double[][] populatuon = new double[Npop][upperBound.length];
		
		int Npar = upperBound.length;
		double[] parameter= new double[Npar];
		Vector Par = new Vector();
		int[] gridIdx = new int[upperBound.length];
		int gridPts = 4;
		double grid[][] = createGrid(upperBound, lowerBound);
		LinkedHashMap<Integer, Double> unSorted = new LinkedHashMap<Integer, Double>();
		LinkedHashMap<Integer, Double> sorted = new LinkedHashMap<Integer, Double>();
		int IT = 0;
		
		double[] associatedSRS = new double[Npop];
		
		do {
			//P2.print("~~~~ IT = " + IT + " ~~~~ ");
			Vector par1 = new Vector();
			for(int i = 0; i < Npar; i++)
			{

				parameter[i] = grid[i][gridIdx[i]];
				par1.add(parameter[i]);
				//P2.print(round(parameter[i],4) + " ");

			}
		//	P2.print("~~~~~~~~~~~~~~~~~~ SRS = ");
			Object[] par11 = par1.toArray();
			Par.add(par11);				
			double SRS = Objective(parameter, X, Y, row);
			
			/*for (int i = 0; i < parameter.length; i++) {
				System.out.print("\t" + parameter[i]);
			}*/
			
			System.out.println("\t" + SRS);
			if(SRS <= 0.0)
				SRS = 999999999.9;
			
			if(IT <Npop)
			{
				populatuon[IT] = parameter;
				associatedSRS[IT] = SRS;
				
				if(IT == Npop -1)
					Arrays.sort(associatedSRS);
			}else
			{
				int i = 0;
				do{
					
					i++;
				}while(i<Npop && SRS>associatedSRS[i]);
				
				if(i<Npop)
				{
					populatuon[i] = parameter;
					associatedSRS[i]  = SRS;
				}
			}
			
			
		
			
			//unSorted.put(IT, SRS);
			System.out.println("param value at iteration:"+IT);
			for(int i = 0; i < Npar; i++)
			{

				System.out.print(""+parameter[i]);
				

			}
			System.out.println();
			IT++;
		}
		while((gridIdx = next(gridIdx, gridPts)) != null);
		
		//sorted = sortHashMapByValuesD(unSorted);
		
		return populatuon;
		
	}
	
	
	public LinkedHashMap<Integer, Double> sortHashMapByValuesD(HashMap passedMap) {
	    List mapKeys = new ArrayList(passedMap.keySet());
	    List mapValues = new ArrayList(passedMap.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);

	    LinkedHashMap sortedMap =
	        new LinkedHashMap();

	    java.util.Iterator<Double> valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	        Object val = valueIt.next();
	        java.util.Iterator<Integer> keyIt = mapKeys.iterator();

	        while (keyIt.hasNext()) {
	            Object key = keyIt.next();
	            String comp1 = passedMap.get(key).toString();
	            String comp2 = val.toString();

	            if (comp1.equals(comp2)){
	                passedMap.remove(key);
	                mapKeys.remove(key);
	                sortedMap.put((Integer)key, val);
	                break;
	            }

	        }

	    }
	    return sortedMap;
	}

	
	public int[] next(int[] current, int radix) {
		int[] n= new int[current.length];

		for (int i= n.length; i-- > 0;) {
			if (current[i]+1 == radix)
				n[i]= 0;
			else {
 				n[i]= current[i]+1;
 				for (; i-- > 0; n[i]= current[i]);
 				return n;
			}
		}
		return null;
	}

	
	public double[][] createGrid(double[] upperBound, double[] lowerBound)
	{
		int noOfParam = upperBound.length;
		int gridPts = 4;
		double[][] grid = new double[noOfParam][gridPts];
		for(int i = 0; i < noOfParam; i++)
		{
			double cellSize = (upperBound[i] - lowerBound[i])/(gridPts +1);
			for(int j = 0; j < gridPts; j++)
			grid[i][j] = lowerBound[i] + (j+1) * cellSize;
					
		}
		
		
		return grid;
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
	
	/*void loadData()
	{
		ReadFile2dStrArray RF = new ReadFile2dStrArray();
		rowCount = RF.rowCount;
		colCount = RF.colCount;
		inputMatrix = new String[rowCount][colCount];
		stringArray = new String[rowCount];
		inputMatrix = RF.fileArray;
		stringArray = RF.stringArray;
		double[][] data = convertToDouble(inputMatrix);
		X = getX(data);
		Y = getY(data);
		Extra_DATA = new double[1][1];
		row = new int[X.length];
		row[0] = X.length;
	}
	*/
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
		Extra_DATA = Extra_DATA_;
		row = row_;
	}
	
	double[] runPSO(double[] uB, double[] lB,  double[][] xData, double[][] yData, double[][] Extra_DATA, int[] nData, double[] delta) throws RowsExceededException, WriteException, BiffException, IOException
	{
		
		pkPdInfoInst = PkPdInfo.createPKPDInstance();
		pkParamEstInst = PkParamEstimator.createPkParamEstimateInstance();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		
		X = xData;
		Y = yData;
		row = nData;
		upperBound = uB;
		lowerBound = lB;
		
		double[] paramVal = new double[upperBound.length];
		
		IndividualParticle indGlblBestPart = initiateProcess(30, 0.2, 0.8, 0.2, X, Y, row);
		
		paramVal = indGlblBestPart.getParameters();
		
		return paramVal;
	}
	
	private IndividualParticle findGlobalBest(Vector popOfIndivids)
	{
		Collections.sort(popOfIndivids, new IndividualParticlesComparator());
		IndividualParticle glblBestParticle = (IndividualParticle) popOfIndivids.get(popOfIndivids.size()-1);
		//P22.println("glblBestParticle is given by..\n"+glblBestParticle);
		return glblBestParticle;
	}

	public IndividualParticle initiateProcess(int nPop, double mutationProb, double crossOverProb, double psoProcProb,
			double[][] X, double[][] Y, int[] row) throws RowsExceededException, WriteException, BiffException, IOException {
		/*try {
			P22 = new PrintStream(new FileOutputStream("HybPSO_trace1.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		this.nPop = nPop;
		this.mutationProb = mutationProb;
		this.crossOverProb = crossOverProb;
		this.psoProcProb = psoProcProb;
		int Npar = upperBound.length;
		
		prevDescrs = new double[Npar];
		
		double[] BestInd = new double[Npar];
		double prevSRS = 99999999.9;
		double[] parameter = new double[Npar];
		double[] pbParameter = new double[Npar];
		double[] vel = new double[Npar];

		double[][] population = generateIndividuals(upperBound, lowerBound,
				nPop);

	/*	double[][] population = createPopUsingGS(upperBound, lowerBound,
				nPop);*/
		
		
		double[] centerInd = new double[Npar];
		
		for (int i = 0; i < nPop; i++) {
			parameter = population[i];

			for (int j = 0; j < centerInd.length; j++) {
				centerInd[j] = population[i][j];
			}
			
			double fitnessValue = Objective(parameter, X, Y, row);
			
			IndividualParticle ind = new IndividualParticle(fitnessValue,
					parameter, pbParameter, vel);
			ind.applyConstraints(lowerBound, upperBound, lowerBound, upperBound);
			popOfIndividuals.insertElementAt(ind, i);
		}
		
	
		
		//printPopulation("initial population...");
		
		//System.exit(1);
		
		Pbest = findGlobalBest(popOfIndividuals).getFitnessValue();
		value1 = 0;
		value2 = 0;
		Pold = Pbest;
		sValue = 0;
		
		bestInd = findGlobalBest(popOfIndividuals);
		
		for (int i = 0; i < iterMax; i++) {

			copyOfPopulation = popOfIndividuals;
			
			// modification for center PSO
			
			
			for (int j = 0; j < nPop; j++) {
				
				IndividualParticle tempInd =  (IndividualParticle) popOfIndividuals.elementAt(j);
				double[] temp = tempInd.getParameters();

				for (int k = 0; k < temp.length; k++) {
					parameter[k] = temp[k]/nPop;
				}
				
			}
			
			double fitnessValue = Objective(parameter, X, Y, row);
			
			IndividualParticle ind = new IndividualParticle(fitnessValue,
					parameter, pbParameter, vel);
			//ind.applyConstraints(lowerBound, upperBound, lowerBound, upperBound);
			copyOfPopulation.insertElementAt(ind, nPop);
			
			
			
			
			performCoMutPSO(0, nPop, Npar, X, Y, Extra_DATA, row, upperBound,
					lowerBound, nPop, i);
			Collections.sort(popOfIndividuals, new IndividualParticlesComparator());
			
			/*P22.println(i+" th iteration starts.\n\n\n");
				printPopulation("population.for.."+i);
			P22.println(i+" th iteration ends.\n\n\n");*/
			
			IndividualParticle indGlblBestPart = findGlobalBest(popOfIndividuals);
			
			/*P22.println("glblBest after iteration.."+i);
			P22.println(indGlblBestPart);*/
			//System.exit(1);
			
			System.out.println("iter number: " + i);
			
			//  modification for global best
			if( indGlblBestPart.getFitnessValue()<bestInd.getFitnessValue() ){
				bestInd = indGlblBestPart;
			}
			
	
			ifTerminate = checkTerminationCond(i+1);
			
			if(ifTerminate == true)
			{
				System.out.println("iteration required: "+i);
				
				double[] bestParam = bestInd.getParameters();
				
				for (int j = 0; j < bestParam.length; j++) {
					System.out.print("\t" + bestParam[j]);
				}
				System.out.println();
				System.out.println("Fitness value: "+bestInd.getFitnessValue());
				
				
				System.out.println("Current best Fitness value: "+indGlblBestPart.getFitnessValue());
				
				
				break;
			}
		}
		
		IndividualParticle indGlblBestPart = findGlobalBest(popOfIndividuals);
		
	
	
		
		
		return indGlblBestPart;
	}

	

	private boolean checkTerminationCond(int iterNo) {
		boolean ifTerminate = false;
		Pbest = findGlobalBest(copyOfPopulation).getFitnessValue();
		value1 = value1 + Pbest;
		value2 = value2 + Pbest*Pbest;
		
		double value = (value2/iterNo) - (value1/iterNo)*(value1/iterNo);

		
		if(Pbest != Pold)
		{
			sValue = value/2;
			Pold = Pbest;
		}
		
		if(value < sValue)
			ifTerminate = true;
		
		
		return ifTerminate;
	}

	public void performCoMutPSO(int startIdx, int popSize, int descCount,
			double[][] X, double[][] Y, double[][] Extra_DATA, int[] row,
			double[] upperBound, double[] lowerBound, int Npop, int iterationIndex) {
		boolean crossOverMutationSuccessful = false;
		try {
			for (int j = startIdx; j < popSize - 1; j += 2) {
			
				if(pkPdInfoInst.methodNo == 2)
				{
					performCrossOver(j, j + 1, descCount, X, Y, Extra_DATA, row,
							upperBound, lowerBound, Npop, iterationIndex);
					performMutation(j, j + 1, descCount, X, Y, Extra_DATA, row,
							upperBound, lowerBound, Npop, iterationIndex);
					
				}
				
				//printPopulation("population.after ga operations.."+j);
				
				performPSOProc(j, j + 1, descCount, X, Y, Extra_DATA, row,
						upperBound, lowerBound, Npop, iterationIndex);
				
				//printPopulation("population.after (ga&pso) operations.."+j);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void performCrossOver(int firstIdx, int secIdx, int descCount,
			double[][] X, double[][] Y, double[][] Extra_DATA, int[] row,
			double[] upperBound, double[] lowerBound, int Npop, int iterationIndex) throws RowsExceededException, WriteException, BiffException, IOException {
		Vector crossOverMasks = new Vector();

		IndividualParticle ind1 = (IndividualParticle) popOfIndividuals
				.get(firstIdx);
		IndividualParticle ind2 = (IndividualParticle) popOfIndividuals
				.get(secIdx);

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

		double R = Objective(descrs1, X, Y, row);
		double firstReg = R;

		if (firstReg < prevR) {

			prevR = firstReg;
			for (int h = 0; h < descrs1.length; h++)
				prevDescrs[h] = descrs1[h];

		}

		R = Objective(descrs2, X, Y, row);
		double secReg = R;

		if (secReg < prevR) {
			prevR = secReg;
			for (int h = 0; h < descrs2.length; h++)
				prevDescrs[h] = descrs2[h];

		}

		ind1 = new IndividualParticle(firstReg, descrs1, ind1.getPbParameters(), ind1.getVelocity());
		ind2 = new IndividualParticle(secReg, descrs2, ind2.getPbParameters(), ind2.getVelocity());

		popOfIndividuals.remove(firstIdx);
		popOfIndividuals.insertElementAt(ind1, firstIdx);

		popOfIndividuals.remove(secIdx);
		popOfIndividuals.insertElementAt(ind2, secIdx);
	}

	void performMutation(int firstIdx, int secIdx, int descCount, double[][] X,
			double[][] Y, double[][] Extra_DATA, int[] row,
			double[] upperBound, double[] lowerBound, int Npop, int iterationIndex) throws RowsExceededException, WriteException, BiffException, IOException {

		Vector mutation1Masks = new Vector();
		Vector mutation2Masks = new Vector();

		double[][] individuals = generateIndividuals(upperBound, lowerBound, 2);

		IndividualParticle ind1 = (IndividualParticle) popOfIndividuals
				.get(firstIdx);
		IndividualParticle ind2 = (IndividualParticle) popOfIndividuals
				.get(secIdx);

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

		double R = Objective(descrs1, X, Y, row);
		double firstReg = R;

		if (firstReg < prevR) {
			prevR = firstReg;
			for (int h = 0; h < descrs1.length; h++)
				prevDescrs[h] = descrs1[h];

		}
		R = Objective(descrs2, X, Y, row);
		double secReg = R;

		if (secReg < prevR) {
			prevR = secReg;
			for (int h = 0; h < descrs2.length; h++)
				prevDescrs[h] = descrs2[h];

		}
		ind1 = new IndividualParticle(firstReg, descrs1, ind1.getPbParameters(), ind1.getVelocity());
		ind2 = new IndividualParticle(secReg, descrs2, ind2.getPbParameters(), ind2.getVelocity());

		popOfIndividuals.remove(firstIdx);
		popOfIndividuals.insertElementAt(ind1, firstIdx);

		popOfIndividuals.remove(secIdx);
		popOfIndividuals.insertElementAt(ind2, secIdx);
	}

	void performPSOProc(int firstIdx, int secIdx, int descCount, double[][] X,
			double[][] Y, double[][] Extra_DATA, int[] row,
			double[] upperBound, double[] lowerBound, int Npop, int iterationIndex) throws RowsExceededException, WriteException, BiffException, IOException {

		IndividualParticle ind1 = (IndividualParticle) popOfIndividuals
				.get(firstIdx);
		IndividualParticle ind2 = (IndividualParticle) popOfIndividuals
				.get(secIdx);
		
		double[] descrs1 = ind1.getParameters();
		double[] descrs2 = ind2.getParameters();
		double[] vel1 = null;
		double[] vel2 = null;
		
		double fVal1 = 0.0;
		double fVal2 = 0.0;
		
		//if (Math.random() < psoProcProb) // for individual - 1
		{
			//vel1 = updateVelocityByCFA(iterationIndex, ind1);
			vel1 = updateVelocityByIWA(iterationIndex, ind1);
			descrs1 = updateNewPosVec(iterationIndex, ind1, vel1);
			fVal1 = Objective(descrs1, X, Y, row);
			
			if (fVal1 < ind1.getFitnessValue())
			{
				ind1.setPbParameters(descrs1);
			}
		}
		
		//if (Math.random() < psoProcProb) // for individual - 2
		{
			//vel2 = updateVelocityByCFA(iterationIndex, ind2);
			vel2 = updateVelocityByIWA(iterationIndex, ind2);
			descrs2 = updateNewPosVec(iterationIndex, ind2, vel2);
			
			fVal2 = Objective(descrs2, X, Y, row);
			
			if (fVal2 < ind2.getFitnessValue())
			{
				ind2.setPbParameters(descrs2);
			}
		}
		
		
		ind1 = new IndividualParticle(fVal1, descrs1, ind1.getPbParameters(), vel1);
		ind1.applyConstraints(lowerBound, upperBound, lowerBound, upperBound);
		
		ind2 = new IndividualParticle(fVal2, descrs2, ind2.getPbParameters(), vel2);
		ind2.applyConstraints(lowerBound, upperBound, lowerBound, upperBound);
		
		popOfIndividuals.remove(firstIdx);
		popOfIndividuals.insertElementAt(ind1, firstIdx);

		popOfIndividuals.remove(secIdx);
		popOfIndividuals.insertElementAt(ind2, secIdx);
}

	
	
	
	public double Objective(double[] par, double[][] X, double[][] Y, int[] row) throws RowsExceededException, WriteException, BiffException, IOException 
	{
		int Nfun = row.length;
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
		LibraryModelDefiner libModelInst = new LibraryModelDefiner();
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
							pkParamEstInst.dosingTime , xf, fn_no, Extra_DATA, i, row);
				}
				else
				{
					fn = libModelInst.chooseLibraryModel(par,
							dataStructInst.infusionTime, dataStructInst.dose, dataStructInst.dosingTime,
							xf, fn_no, Extra_DATA, i, row);
				}
								
				
				errorMat[i][0] = yf - fn;
			}
		}
		return errorMat;
	}

	
	
	////////////////////////////////////////////////////////////////////////
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	
	
	private	double computeInertiaWeight(int iterIndex)
	{
		double weight1 = 0.9;
		double weight2 = ( (0.9 - 0.4) / iterMax ) * iterIndex;
		double w = weight1 - weight2;
		return w;
	}
	
	
	
	private double[] updateVelocityByIWA(int indexItr, IndividualParticle indParticle)
	{
		double[] si = indParticle.getParameters();
		double[] pbest_i = indParticle.getPbParameters();
		double[] vi = indParticle.getVelocity();
		double w = computeInertiaWeight(indexItr);
		//double w = 0.729844;
		
		//P22.println("inertial weight.."+w);
		
		IndividualParticle glblBest = findGlobalBest(copyOfPopulation);
		double[] gBest_i = glblBest.getParameters();
				
		int noOfParams = si.length;
		
		double rand1 = Math.random();
		double rand2 = Math.random();
		
		double c1 = 1.49618;
		double c2 = 1.49618;
		
		double[] vi_1 = new double[noOfParams];
		
		for (int i = 0; i < noOfParams; i++) {
			
			//double term1 = w * vi[i];
			//double term2 = c1 * rand1 * (pbest_i[i] - si[i]);
			double term2 = rand1 * (pbest_i[i] - si[i]);
			//double term3 = c2 * rand2 * (gBest_i[i] - si[i]);
			double term3 = rand2 * (gBest_i[i] - si[i]);
			
			//vi_1[i] = term1 + term2 + term3;
			vi_1[i] = term2 + term3;
			
		}
		
		return vi_1;		
	}
	
	private double computeConstrictionFactor() {
		double phi = 4.1;

		double nr = 2.0;

		double drTerm = Math.pow(phi, 2) - 4 * phi;
		drTerm = Math.sqrt(drTerm);

		double dr = 2 - phi - drTerm;
		dr = Math.abs(dr);

		double K = nr / dr;

		return K;
	}
	
	private double[] updateNewPosVec(int indexItr, IndividualParticle indParticle, double[] vel)
	{
		double[] si = indParticle.getParameters();
		double[] vikp1 = updateVelocityByCFA(indexItr, indParticle);
		
		int noOfParams = si.length;
		
		double[] si_1 = new double[noOfParams];
		
		for (int i = 0; i < noOfParams; i++) {
			
			double term1 = si[i];
			double term2 = vel[i];
			
			si_1[i] = (term1 + term2);
		}
	
		return si_1;		
	}
	
	private double[] updateVelocityByCFA(int indexItr, IndividualParticle indParticle)
	{
		double[] si = indParticle.getParameters();
		double[] pbest_i = indParticle.getPbParameters();
		double[] vi = indParticle.getVelocity();
		double K = computeConstrictionFactor();
		IndividualParticle glblBest = findGlobalBest(copyOfPopulation);
		double[] gBest_i = glblBest.getParameters();
		
		int noOfParams = si.length;
		
		double c1 = 1.49618;
		double c2 = 1.49618;
		
		double rand1 = Math.random();
		double rand2 = Math.random();
		
		double[] vi_1 = new double[noOfParams];
		
		for (int i = 0; i < noOfParams; i++) {
			
			double term1 = vi[i];
			double term2 = c1 * rand1 * (pbest_i[i] - si[i]);
			double term3 = c2 * rand2 * (gBest_i[i] - si[i]);
			
			vi_1[i] = K * (term1 + term2 + term3);
		}
		
		return vi_1;		
	}

	

}
