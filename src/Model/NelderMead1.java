package Model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import Common.TestException;


public class NelderMead1 
{

	PrintStream P2;
	int IT;
	PkPdInfo pkpdInstance;
	LibraryModelDefiner libModelInst;
	boolean ifAutomaticRestart;
	int numberOfRestart;
	
	public NelderMead1()
	{
		pkpdInstance = PkPdInfo.createPKPDInstance();
		ifAutomaticRestart = false;
		numberOfRestart = 0;
	}
	
	
	static NelderMead1 nmInstance = null;
	public static NelderMead1 CreateNMInstance(){
		if(nmInstance == null)
		{
			nmInstance = new NelderMead1();
		}
		
		return nmInstance;
	}

	public double[] NelderMead(int modelNumber, double[] initial,
			double[][] X, double[][] Y, double[][] Extra_DATA, int[] row,
			double[] delta, double[] infusionTime, double[] dose, double[] dosingTime, int numberOfSortVariable,
			String[][] dataSortVariables, int noOfParam, double pdInc, int profileNo) throws RowsExceededException, WriteException, BiffException, IOException
	{
		
	
		int Npar = initial.length;
		double[] finalParameter = new double[Npar];

		double[][] setofpoints = new double[Npar+1][Npar];
		
		try {
			P2 = new PrintStream("NMTrace.txt");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

		
		updateBound(initial);
		
		
		setofpoints = generateInitial(initial);
		
		
		
		//setofpoints = newInitialGeneration(initial);

		IT = 0;

		while(IT < pkpdInstance.iter)
		{
			IT = IT + 1;
			System.out.println("IT number = "+IT);
			double[] SRS = new double[Npar+1];
			double[] temp_SRS = new double[Npar+1];
			double[] parameter = new double[Npar];

			for(int i = 0; i < Npar + 1; i++)
			{
				for(int j = 0; j < Npar; j++)
					parameter[j] = setofpoints[i][j];

				SRS[i] = Objective(parameter, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);
			}

			for(int i = 0; i < Npar+1; i++)
				temp_SRS[i] = SRS[i];

			Arrays.sort(temp_SRS);

			double bestSRS = temp_SRS[0];
			double goodSRS = temp_SRS[Npar-1];
			double worstSRS = temp_SRS[Npar];


			/*if(round(worstSRS,4) == round(bestSRS,4))
			{
				for(int i = 0; i < Npar; i++)
					finalParameter[i] = parameter[i];
				break;
			}*/

			int bestSRSIndex = 0;
			int goodSRSIndex = 0;
			int worstSRSIndex = 0;

			double[] B = new double[Npar];
			double[] G = new double[Npar];
			double[] W = new double[Npar];

			for(int i = 0; i < Npar+1; i++)
			{
				if(bestSRS == SRS[i])
					bestSRSIndex = i;
				if(goodSRS == SRS[i])
					goodSRSIndex = i;
				if(worstSRS == SRS[i])
					worstSRSIndex = i;

			}
			for(int i = 0; i < Npar; i++)
			{
				B[i] = setofpoints[bestSRSIndex][i];
				G[i] = setofpoints[goodSRSIndex][i];
				W[i] = setofpoints[worstSRSIndex][i];
			}

	
			double[] M = centroid(setofpoints, W, worstSRSIndex);
			double[] R = reflection(setofpoints, W, worstSRSIndex);
			
			R = addConstraint(R, initial);
			
			int count = 0;
			int boundCount = 0;
			for(int i = 0; i < Npar; i++)
			{
				if(R[i] <= 0.0)
				{
					count++;
					break;
				}
			}
		
			double R_SRS = 0;
			R_SRS = Objective(R, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);

		

			if(R_SRS < goodSRS)	// fr < fn
			{
				if(bestSRS <= R_SRS) //f1 <= fr
				{
					for(int i = 0; i < Npar; i++)
					{
						W[i] = R[i];
					}
					
					for(int i = 0; i < Npar; i++)
					{
						setofpoints[worstSRSIndex][i] = W[i];
						
					}
				}
				else	// fr < f1
				{
					double[] E = expansion(setofpoints, W, worstSRSIndex);
				
					E = addConstraint(E, initial);
					
					
					count = 0;
					boundCount = 0;
					for(int i = 0; i < Npar; i++)
					{
						if(E[i] <= 0.0)
						{
							count++;
							break;
						}
					}
			

					double E_SRS = 0;
					E_SRS = Objective(E, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);
					if(count == 0 && boundCount == 0)
					{
						if(E_SRS < R_SRS)  	// fe < fr
						{
							for(int i = 0; i < Npar; i++)
							{
								W[i] = E[i];
								
							}
							
							
							for(int i = 0; i < Npar; i++)
							{
								setofpoints[worstSRSIndex][i] = W[i];
								
							}
						}
						else
						{
							for(int i = 0; i < Npar; i++)
							{
								W[i] = R[i];
								
							}
							
							
							for(int i = 0; i < Npar; i++)
							{
								setofpoints[worstSRSIndex][i] = W[i];
								
							}
						}
					}
				

				}
			}
			else 	// fr >= fn
			{
				if(R_SRS < worstSRS)  // fr < fn+1
				{
					
					double[] C = outsideContraction(setofpoints, W, worstSRSIndex);
					
					C = addConstraint(C, initial);
					double C_SRS = Objective(C, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);
					
					if(C_SRS <= R_SRS )
					{
						for(int i = 0; i < Npar; i++)
						{
							W[i] = C[i];
						}
						
						for(int i = 0; i < Npar; i++)
						{
							setofpoints[worstSRSIndex][i] = W[i];
							
						}
						
					}else   // perform shrink
					{
						
						
						for (int i = 0; i < Npar+1; i++) {
							
							for (int j = 0; j < Npar; j++) {
								
								if(i != bestSRSIndex)
								{
									setofpoints[i][j] = B[j] + (setofpoints[i][j] - B[j])/2;
								}
								
							}
						}
						
						System.out.println();
						
					}
					
					
				}
				else
				{
					double[] C1 = insideContraction(W, M, worstSRSIndex);
					
					C1 = addConstraint(C1, initial);
					int count1 = 0; int boundCount1 = 0;
					for(int i = 0; i < Npar; i++)
					{
						if(C1[i] <= 0.0)
						{
							count1++;
							break;
						}
					}

			
					double C1_SRS = 0;
					C1_SRS = Objective(C1, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);
					
					
					if( C1_SRS < worstSRS)
					{
						for(int i = 0; i < Npar; i++)
						{
							W[i] = C1[i];
						}
						
						
						for(int i = 0; i < Npar; i++)
						{
							setofpoints[worstSRSIndex][i] = W[i];
							
						}
					}else  // perform shrink
					{
						for (int i = 0; i < Npar+1; i++) {
							
							for (int j = 0; j < Npar; j++) {
								
								if(i != bestSRSIndex)
								{
									setofpoints[i][j] = B[j] + (setofpoints[i][j] - B[j])/2;
								}
								
							}
						}
						
						System.out.println();
						
					}

					
				}
			}


			/*for(int i = 0; i < Npar; i++)
			{
				setofpoints[worstSRSIndex][i] = W[i];
				setofpoints[goodSRSIndex][i] = G[i];
			}*/

			double sumPar = 0;
			for(int i = 0; i < Npar; i++)
			{
				double ratio = Math.abs((B[i] - W[i])/W[i]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar/ Npar;

			for(int i = 0; i < Npar; i++)
				finalParameter[i] = B[i];
			if (check <= pkpdInstance.convergence)
			{
				pkpdInstance.setRequiredIter(IT);
				break;
			}
				
					

			if(IT == pkpdInstance.iter && numberOfRestart < 10)// automatic restart of Nelder mead using the so far best value achieved 
			{
				numberOfRestart++;
				pkpdInstance.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				pkpdInstance.getWorkSheetOutputInst().getTextoutput()
				.add(
						StringUtils
						.rightPad(
								"Convergence not achieved, Automatic Restart of Nelder mead",
								80)
						+ "\r\n\r\n");
				
				pkpdInstance.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				ifAutomaticRestart = true;
				NMAutomaticRestart nmAutomatic = new NMAutomaticRestart();
				finalParameter = nmAutomatic.restartNM(finalParameter, profileNo);
				
			}
			
			
			System.out.println("Iter = "+IT);
		
		}


		P2.println("******************************************************************");
		P2.println("     Total Iterations = " + IT);
		P2.println("******************************************************************");
		P2.println();
		
		System.out.println("END");
				
		//if(ifAutomaticRestart == false)
		
		return finalParameter;

	}

	
	
	double[] addConstraint(double[] parameter, double[] initial)
	{
		
		for (int i = 0; i < parameter.length; i++) {
			
			if(parameter[i] <= 0 || parameter[i] > 10*initial[i])
			{
				parameter[i] = initial[i];
			}
		}
		
		
		return parameter;
	}
	
	
	private void updateBound(double[] initial) {
		
		for (int i = 1; i < initial.length; i++) {
			for (int j = 0; j < i; j++) {
				
				if((initial[i] == initial[j]) || (initial[i] == initial[j]/2))
				{
					initial[i] = initial[i] + initial[i]/100;
				}
			}
		}
		
		
	}

	public double round(double value, int decimalPlace)
	{
		double power_of_ten = 1;
		while (decimalPlace-- > 0)
		{
			power_of_ten *= 10.0;
		}
		return Math.round(value * power_of_ten)/ power_of_ten;
	}
	// Define initial vertices
	public double[][] generateInitial(double[] initial)
	{
		int Npar = initial.length;
		double[][] set = new double[Npar+1][Npar];
		
		
		for(int i = 0; i < Npar+1; i++)
		{
			for(int j = 0; j < Npar; j++)
			{
				if(i == j)
					set[i][j] = 0.5 * initial[i];
				else
					set[i][j] = initial[j];
			}
		}
		
		
		/*for(int i = 0; i < Npar+1; i++)
		{
			for(int j = 0; j < Npar; j++)
			{
				if(i == j)
					set[i][j] = 1.1*initial[i];
				else
					set[i][j] = initial[j];
			}
		}*/
		
		
		P2.println("Initial Simplex generated : ");
		P2.println();
		for(int i = 0; i < Npar+1; i++)
		{
			for(int j = 0; j < Npar; j++)
			{
				P2.print(set[i][j]  + "\t");
			}
			P2.println();
		}
		P2.println();
		return set;
	}

	
	
	// reference: ON CONVERGENCE OF THE NELDER-MEAD SIMPLEX ALGORITHM FOR UNCONSTRAINED STOCHASTIC OPTIMIZATION
	
	public double[][] newInitialGeneration(double[] initial)
	{
		double[][] initSimplex = new double[initial.length+1][initial.length];
		
		int n = initial.length;
		double d = 1;
		double p = d*(Math.sqrt(n+1) + n -1)/(n*Math.sqrt(2));
		double q = d*(Math.sqrt(n+1) - 1)/(n*Math.sqrt(2));
		
		for (int i = 0; i < n; i++) {
			//initSimplex[0][i] = initial[i] - (p + (n-1)*q)/(n+1);
			initSimplex[0][i] = initial[i];
		}
		
		
		for (int i = 1; i < initSimplex.length; i++) {
			
			for (int j = 0; j < n; j++) {
				
				if( j == i-1)
					initSimplex[i][j] =  initSimplex[0][j] + p;
				else
					initSimplex[i][j] =  initSimplex[0][j] + q;
			}
		}
		
		P2.println("Initial Simplex generated : ");
		P2.println();
		for(int i = 0; i < n+1; i++)
		{
			for(int j = 0; j < n; j++)
			{
				P2.print(initSimplex[i][j]  + "\t");
			}
			P2.println();
		}
		P2.println();
		
		return initSimplex;
		
	}
	
	
	// Centroid
	public double[] centroid(double[][] set, double[] W, int worstIdx)
	{
		int Npar = set[0].length;
		double[] M = new double[Npar];
		double[] coor_sum = new double[Npar];

		for(int i = 0; i < Npar; i++)
		{
			double sum = 0;
			for(int j = 0; j < Npar + 1; j++)
			{
				if(j != worstIdx)
					sum = sum + set[j][i];
			}
			coor_sum[i] = sum;
			M[i] = coor_sum[i] / Npar;
		}
		return M;
	}

	// Reflection
	public double[] reflection(double[][] set, double[] W, int worstIdx)
	{
		int Npar = set[0].length;
		double[] R = new double[Npar];
		double[] M = centroid(set, W, worstIdx);

		for(int i = 0; i < Npar; i++)
		{
			R[i] = 2.0 * M[i] - W[i];
		}
		return R;
	}

	// Expansion
	public double[] expansion(double[][] set, double[] W, int worstIdx)
	{
		int Npar = set[0].length;
		double[] E = new double[Npar];
		double[] R = reflection(set, W, worstIdx);
		double[] M = centroid(set, W, worstIdx);

		for(int i = 0; i < Npar; i++)
		{
			E[i] = 2.0 * R[i] - M[i];
		}
		return E;
	}

	
	// Outside Contraction 
	
	public double[] outsideContraction(double[][] set, double[] W, int worstIdx)
	{
		int Npar = set[0].length;
		double[] outsideContrac = new double[Npar];
				
		double[] R = reflection(set, W, worstIdx);
		double[] M = centroid(set, W, worstIdx);

		for(int i = 0; i < Npar; i++)
		{
			outsideContrac[i] = ( R[i] + M[i])/2;
		}
		
		
		return outsideContrac;
	}
	
	
	

	// Contraction
	public double[] insideContraction(double[] mat, double[] M, int worstIdx)
	{
		int Npar = M.length;
		double[] C = new double[Npar];

		for(int i = 0; i < Npar; i++)
		{
			C[i] = (M[i] + mat[i]) / 2.0;
		}
		return C;
	}

	
	
	
	
	// Shrink
	public double[] shrink(double[][] set, double[] G, int goodIdx)
	{
		int Npar = set[0].length;
		double[] S = new double[Npar];
		double[] coor_sum = new double[Npar];

		for(int i = 0; i < Npar; i++)
		{
			double sum = 0;
			for(int j = 0; j < Npar + 1; j++)
			{
				if(j != goodIdx)
					sum = sum + set[j][i];
			}
			coor_sum[i] = sum;
			S[i] = coor_sum[i] / Npar;
		}
		return S;
	}

	

	// Objective function: (Weighted sum of residuals)
	public double Objective(double[] par, double[] infusionTime, double[] dose, double[] dosingTime, double[][] X, double[][] Y,
			double[][] Extra_DATA, int[] row, int profileNo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		int Nfun = X[0].length;
		double[] weightResSq = new double[Nfun];
		double SumWts = 0;
		double WRSS = 0;
		
		for (int i = 0; i < Nfun; i++) {
			int r = row[i];
			double[][] scaledWts = new double[r][r];
			double[][] weightMat = weights(par, infusionTime,  dose, dosingTime,  X, Y, Extra_DATA, i, row, profileNo);
			
			if(pkpdInstance.exceptionForCurrentProfile == 1)
				break;
			double[][] errorMat =  error(par, X, Y, Extra_DATA, i, row, infusionTime,
					 dose, dosingTime, profileNo);

			if(pkpdInstance.exceptionForCurrentProfile == 1)
			{
				break;
			}else
			{
				for (int k = 0; k < row[i]; k++)
					SumWts = SumWts + weightMat[k][k];

				for (int j = 0; j < r; j++) {
					if (pkpdInstance.ifScaling != 0)
						scaledWts[j][j] = weightMat[j][j] * r / SumWts;
					else
						scaledWts[j][j] = weightMat[j][j];

					double WtresSq = 0;
					if (pkpdInstance.ifELS != 0) // ELS
					{
						double var = 1.0 / scaledWts[j][j];
						WtresSq = scaledWts[j][j] * Math.pow(errorMat[j][0], 2)
								+ Math.log(var);
					} else
						WtresSq = scaledWts[j][j] * Math.pow(errorMat[j][0], 2);
					weightResSq[i] = weightResSq[i] + WtresSq;
				}
			}
			
			

			WRSS = WRSS + weightResSq[i];
		}

		return WRSS;
	}


	// Scaled weights
	public double[][] ScaledWeights(double[] par, double[] infusionTime, double[] dose, double[] dosingTime, double[][] X, double[][] Y,
			double[][] Extra_DATA, int fn_no, int[] row, int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		
		
		double SumWts = 0;
		int r = row[fn_no];
		double[][] scaledWts = new double[r][r];
		double[][] weightMat = weights(par, infusionTime,  dose,  dosingTime,  X, Y, Extra_DATA, fn_no, row, profileNo);

		if(pkpdInstance.exceptionForCurrentProfile == 1)
		{
			
		}else
		{
			for (int k = 0; k < r; k++)
				SumWts = SumWts + weightMat[k][k];

			for (int j = 0; j < r; j++) {
				if (pkpdInstance.ifScaling != 0)
					scaledWts[j][j] = weightMat[j][j] * r / SumWts;
				else
					scaledWts[j][j] = weightMat[j][j];
			}
			
		}
		
		

		return scaledWts;
	}
	
	
	// Weight matrix (Q)
	public double[][] weights(double[] par,double[] infusionTime, double[] dose, double[] dosingTime, double[][] X, double[][] Y,
			double[][] Extra_DATA, int fn_no, int[] row, int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		pkpdInstance = PkPdInfo.createPKPDInstance();
	
		int r = row[fn_no];
		double[][] wts = new double[r][r];
		libModelInst = new LibraryModelDefiner();
		
		AsciiParameterEstimator asciiEvaluationInst = AsciiParameterEstimator.createAsciiParamEstimationInst(); 

		for (int i = 0; i < r; i++) {
			double xf = X[i][fn_no];
			double yf = Y[i][fn_no];
			double fn = 0;
			
			if((IT == 1) && (i == 0)){
				PkPdInfo.createPKPDInstance().setFirstIteration(true);
			} else {
				PkPdInfo.createPKPDInstance().setFirstIteration(false);
			}
			
			
			if(pkpdInstance.analysisType.equals("ascii"))
			{
				fn = asciiEvaluationInst.evaluateCode(par, xf, fn_no, profileNo ,PkPdInfo.createPKPDInstance().isFirstIteration() );
			}else
			{

			fn = libModelInst.chooseLibraryModel(par, infusionTime, dose,
					dosingTime, xf, fn_no, Extra_DATA, i,
				row);
			}
			if(pkpdInstance.exceptionForCurrentProfile  == 1)
			{

				TestException inst = new TestException();
				inst.throwCustomException(121);
				pkpdInstance.getWorkSheetOutputInst().getTextoutput().add("\r\n"
						+ StringUtils.rightPad(
								"*************************************************"
										+ "**********************************", 80)
						+ "\r\n\r\n");
				pkpdInstance.getWorkSheetOutputInst().getTextoutput()
				.add(StringUtils.rightPad("121: Parameters value can not be estimated , Problem during function evaluation", 100));
				pkpdInstance.getWorkSheetOutputInst().getTextoutput()
				.add("\r\n\r\n");	
				break;
			
			}
			if (Double.isInfinite(fn) == true
					|| Double.isNaN(fn) == true) {
				TestException inst = new TestException();
				inst.throwCustomException(123);
				pkpdInstance.exceptionForCurrentProfile = 1;
				pkpdInstance.getWorkSheetOutputInst().getTextoutput().add("\r\n"
						+ StringUtils.rightPad(
								"*************************************************"
										+ "**********************************", 80)
						+ "\r\n\r\n");
				pkpdInstance.getWorkSheetOutputInst().getTextoutput()
				.add(StringUtils.rightPad("121: Parameters value can not be estimated , Problem during function evaluation", 100));
				pkpdInstance.getWorkSheetOutputInst().getTextoutput()
				.add("\r\n\r\n");	
				break;
				
			}

			if(pkpdInstance.exceptionForCurrentProfile  == 1)
			{
				break;
			}else
			{
				for (int j = 0; j < r; j++) {
					if (i != j)
						wts[i][j] = 0;
					else {
						if (xf == 999999.9)
							wts[i][j] = 1.0;
						else {

							if (pkpdInstance.wtScheme == 0) // WLS
							{
								if(pkpdInstance.wtExp != 0 && yf == 0 )
									wts[i][j] = 0;
								else 
									wts[i][j] = Math.pow(yf, (-1)*pkpdInstance.wtExp);
								
							}
								
							else if (pkpdInstance.wtScheme == 1) // IRLS
							{
								if (fn > 0)
									wts[i][j] = Math.pow(fn,(-1)* pkpdInstance.wtExp);
								else
									wts[i][j] = 0;
							} else if (pkpdInstance.wtScheme == 2) // User defined
																	// function
							{
								if (fn != 0)
									wts[i][j] = pkpdInstance.weighting(fn, fn_no);
								else
									wts[i][j] = 0;
							}
						}
					}
				}
			}
			}
			
			
		if(pkpdInstance.exceptionForCurrentProfile  == 1)
		{
			
		}else
		{
			if(pkpdInstance.wtScheme == 0 && pkpdInstance.wtExp>0)
			{
				double sumWeights = 0;
				int noOfObs = X.length;
				
				for (int j = 0; j < wts.length; j++) {
					sumWeights = sumWeights + wts[j][j];
				} 
				
				
				for (int j = 0; j < wts.length; j++) {
					 wts[j][j] = wts[j][j]*noOfObs/sumWeights;
				} 
				
				
			}
		}
		
		
		
		return wts;
	}
	// error matrix
	public double[][] error(double[] par, double[][] X, double[][] Y,
			double[][] Extra_DATA, int fn_no, int[] row, double[] infusionTime,
			double[] dose, double[] dosingTime, int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
	
		int r = row[fn_no];
		double[][] errorMat = new double[r][1];
		double fn = 0;

		AsciiParameterEstimator asciiEvaluationInst = AsciiParameterEstimator.createAsciiParamEstimationInst();
		libModelInst = new LibraryModelDefiner();
		for (int i = 0; i < r; i++) {
			double xf = X[i][fn_no];
			double yf = Y[i][fn_no];

			if (xf == 999999.9)
				errorMat[i][0] = 0;
			else {

				if(pkpdInstance.analysisType.equals("ascii"))
				{
					fn = asciiEvaluationInst.evaluateCode(par, xf, fn_no, profileNo,PkPdInfo.createPKPDInstance().isFirstIteration() );
				}else
				{
				fn = libModelInst.chooseLibraryModel(par, infusionTime, dose,
						dosingTime, xf, fn_no,
						Extra_DATA, i, row);
				}
				if(pkpdInstance.exceptionForCurrentProfile  == 1)
				{

					TestException inst = new TestException();
					inst.throwCustomException(123);
					pkpdInstance.getWorkSheetOutputInst().getTextoutput().add("\r\n"
							+ StringUtils.rightPad(
									"*************************************************"
											+ "**********************************", 80)
							+ "\r\n\r\n");
					pkpdInstance.getWorkSheetOutputInst().getTextoutput()
					.add(StringUtils.rightPad("121: Parameters value can not be estimated , Problem during function evaluation", 100));
					pkpdInstance.getWorkSheetOutputInst().getTextoutput()
					.add("\r\n\r\n");	
					break;
				
				}	else
				{
					errorMat[i][0] = yf - fn;
				}
			
										
					if (Double.isInfinite(errorMat[i][0]) == true
							|| Double.isNaN(errorMat[i][0]) == true) {
						TestException inst = new TestException();
						inst.throwCustomException(123);
						pkpdInstance.exceptionForCurrentProfile = 1;
						pkpdInstance.getWorkSheetOutputInst().getTextoutput().add("\r\n"
								+ StringUtils.rightPad(
										"*************************************************"
												+ "**********************************", 80)
								+ "\r\n\r\n");
						pkpdInstance.getWorkSheetOutputInst().getTextoutput()
						.add(StringUtils.rightPad("123: Parameters value can not be estimated , Problem during function evaluation", 100));
						pkpdInstance.getWorkSheetOutputInst().getTextoutput()
						.add("\r\n\r\n");	
						break;
 					
					}
					
			}
		}

		return errorMat;
	}
		
	
	
}
