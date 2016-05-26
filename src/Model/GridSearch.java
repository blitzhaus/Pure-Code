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
import java.util.Vector;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import Jama.SingularValueDecomposition;

public class GridSearch {
	
	PkParamEstimator pkpdMainInst;
	int noOfParam;
	int noOfFunction;
	ApplicationInfo appInfo ;
	ListOfDataStructures dataStructInst;
	PkPdInfo pkpdInst;
	
	PrintStream P2;
	public GridSearch() {
		
		
		
	}

	public double[] GridSearch(double[] upperBound, double[] lowerBound,  double[][] X, double[][] Y, double[][] Extra_DATA, int[] row, double[] delta) throws RowsExceededException, WriteException, BiffException, IOException
	{
		P2 = new PrintStream(new FileOutputStream("GSInitValStoring.txt"));
		pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		
		
		if (pkpdInst.analysisType.equals("pk")) {
			pkpdMainInst = PkParamEstimator.createPkParamEstimateInstance();
			appInfo = pkpdMainInst.appInfoinst;
		} else {
			ListOfDataStructures dataStructInst = ListOfDataStructures
					.createListOfDataStructInstance();
			appInfo = dataStructInst.appInfo;
		}
		
		
		noOfParam = procInputInst.getProfileAndParamInfoObj().getNumberOfParameter();
		noOfFunction = procInputInst.getProfileAndParamInfoObj().getNumberOfFunction();
		
		pkpdInst.gridPts = 3;
		
		
		int Npar = upperBound.length;
		double[] BestInd = new double[Npar];
		double prevSRS = 99999999.9;
		double[] parameter = new double[Npar];

			double[] temp_SRS = new double[pkpdInst.nPop];
			
			Vector Par = new Vector();
			double[] bestSet = new double[Npar];
			
		
				String startTime = systemTime();
				P2.println("---------------------------------------");
				P2.println("Start Time: "+startTime);
				P2.println("Number of grid Point: "+pkpdInst.gridPts);
				System.out.println("---------------------------------------");
				System.out.println("Start Time: "+startTime);
				System.out.println("Number of grid Point: "+pkpdInst.gridPts);
				
				
				int[] gridIdx = new int[Npar];
				double grid[][] = createGrid(upperBound, lowerBound);
				/*LinkedHashMap<Integer, Double> unSorted = new LinkedHashMap<Integer, Double>();
				LinkedHashMap<Integer, Double> sorted = new LinkedHashMap<Integer, Double>();*/
				
				
			/*	for(int i = 0; i < Npar; i++)
				{
					for(int j = 0; j <gridPts; j++)
						P2.print(grid[i][j]+"  ");
					P2.println();
					P2.println();				
				}*/
				
				double[] temp = new double[Npar];
				double minSRS = 9999999999.0;
				
				int IT = 0;
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
					double SRS = Objective(parameter, X, Y, Extra_DATA, row);
					
					for (int i = 0; i < parameter.length; i++) {
						System.out.print("\t" + parameter[i]);
					}
					
					System.out.println("\t" + SRS);
					
					/*if(SRS <= 0.0)
						SRS = 999999999.9;*/
					//P2.println(round(SRS,4));
				//	unSorted.put(IT, SRS);
					//System.out.println("param value at iteration:"+IT);
					
				// modification start	
					if(SRS< minSRS)
					{
						minSRS = SRS;
						for (int i = 0; i < Npar; i++) {
							temp[i] = parameter[i];
						}
					}
					
					
				// modification end	
					/*for(int i = 0; i < Npar; i++)
					{

						P2.print("\t"+parameter[i]);
						

					}
					IT++;
					P2.println();
					P2.println("grid point No= "+IT+"\tSRS = "+SRS +"\t minSRS="+minSRS);*/
					//IT++;
					
					//System.out.println();
					
				}
				while((gridIdx = next(gridIdx, pkpdInst.gridPts)) != null);
				
			/*	for(int i = 0; i < Par.size(); i++)
				{
					Object parSet1[] = (Object[]) Par.get(i);
					P2.print(i + "  ");
					for(int j = 0; j < Npar; j++)
						P2.print(parSet1[j] + " " );	
					P2.println();
					
				}*/
				
				
				/*sorted = sortHashMapByValuesD(unSorted);
				int cnt = sorted.size()-1;						
				int Idx[] = new int[sorted.size()];
				for (Integer best : sorted.keySet())
				{
					Idx[cnt] = best;
					cnt--;
				}

			//	P2.println("best idx = " + Idx[Idx.length-1]);
				double[] bestSet = new double[Npar];
				double minSRS = sorted.get(Idx[Idx.length-1]);
				
				for(int i = 0; i < Par.size(); i++)
				{
					Object parSet1[] = (Object[]) Par.get(i);
					P2.print(i + "  ");
					for(int j = 0; j < Npar; j++)
						P2.print(parSet1[j] + " " );	
					P2.println();
					
				}
				Object parSet[] = (Object[]) Par.get(Idx[Idx.length-1]);
				
				for(int i = 0; i < Npar; i++)
				{
					bestSet[i] = ((Double) parSet[i]).doubleValue();
			//		P2.print(bestSet[i] + " " );
				}*/
			//	P2.print("~~~~~~~~~~~~~~~~~~ SRS = ");
			//	P2.println(minSRS);

				//modification start
				
				//P2.println("---------------------------------------");
				P2.println("Output of Grid Search");
				System.out.println("Output of Grid Search");
			
				for(int i = 0; i < Npar; i++)
				{
					bestSet[i] = temp[i];
					P2.print(bestSet[i] + " " );
					System.out.print(bestSet[i] + " " );
				}
				P2.println("\t Min SRS=" +minSRS);
				System.out.println("\t Min SRS=" +minSRS);
				
				
				String endTime = systemTime();
				
				P2.println("End Time: "+endTime);
				P2.println("---------------------------------------");
				
				System.out.println("End Time: "+endTime);
				System.out.println("---------------------------------------");
			
			

			System.out.println("Final result of Grid Search");
			for(int i = 0; i < Npar; i++)
			{
				System.out.print("\t"+ bestSet[i] );
			}
			
		return bestSet;

	}
	
	
	String systemTime() {
		java.sql.Timestamp currentTime = new java.sql.Timestamp(
				new java.util.Date().getTime());
		String currenttime = String.valueOf(currentTime);
		String date = currenttime.substring(0, 10);
		String time = currenttime.substring(11, 19);
		// logArea.append("     " + date + "  " + time+"\n");
		return time;
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

	private int[] next(int[] current, int radix) {
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
		double[][] grid = new double[noOfParam][pkpdInst.gridPts];
		for(int i = 0; i < noOfParam; i++)
		{
			double cellSize = (upperBound[i] - lowerBound[i])/(pkpdInst.gridPts +1);
			for(int j = 0; j < pkpdInst.gridPts; j++)
			grid[i][j] = lowerBound[i] + (j+1) * cellSize;
					
		}
		
		
		return grid;
	}
	
	public double Objective(double[] par, double[][] X, double[][] Y, double[][] Extra_DATA, int[] row) throws RowsExceededException, WriteException, BiffException, IOException
	{
		int Nfun = 1;
		double[] weightResSq = new double[Nfun];
		double SumWts = 0;
		double WRSS = 0;
		for(int i = 0; i < Nfun; i++)
		{
			int r = row[i];
			double[][] scaledWts = new double[r][r];
			//double[][] weightMat = weights(par,  X, Y, Extra_DATA, i, row);
			double[][] errorMat = error(par, X, Y, Extra_DATA, i, row);

			/*for(int k = 0; k < row[i]; k++)
				SumWts = SumWts + weightMat[k][k];*/

			for(int j = 0; j < r; j++)
			{
				/*if(pkpdInst.ifScaling != 0)
					scaledWts[j][j] = weightMat[j][j] * r / SumWts;
				else
					scaledWts[j][j] = weightMat[j][j];*/

				//double WtresSq = scaledWts[j][j] * Math.pow(errorMat[j][0], 2);
				weightResSq[i] = weightResSq[i] +Math.pow(errorMat[j][0], 2);
			}

			WRSS = WRSS + weightResSq[i];
		}

		return WRSS;
	}


	
	
	
	// error matrix
	private double[][] error(double[] par,  double[][] X, double[][] Y, double[][] Extra_DATA, int fn_no, int[] row) throws RowsExceededException, WriteException, BiffException, IOException
	{
		int r = row[fn_no];
		double[][] errorMat = new double[r][1];
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
				if(pkpdInst.analysisType.equals("pk"))
				{
					fn = libModelInst.chooseLibraryModel(par, pkpdMainInst.infusionTime,pkpdMainInst.dose,
							pkpdMainInst.dosingTime , xf, fn_no, Extra_DATA, i, row);
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




	
	

	
	
}