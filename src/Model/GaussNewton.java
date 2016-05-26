package Model;

import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import Jama.SingularValueDecomposition;
import Common.TestException;;

public class GaussNewton {


	
	int IT;
	PkPdInfo pkpdInstance;
	LibraryModelDefiner libModelInst;
	public GaussNewton() {
		pkpdInstance = PkPdInfo.createPKPDInstance();
	}
	
	 String systemTime() {
		java.sql.Timestamp currentTime = new java.sql.Timestamp(
				new java.util.Date().getTime());
		String currenttime = String.valueOf(currentTime);
		String date = currenttime.substring(0, 10);
		String time = currenttime.substring(11, 19);
		return (date + "/" + time);
	}
	
	static long startTimeafterGNBeforeMC;
		public double[] gaussNewtonLM(int modelNumber, double[] initial,
			double[][] X, double[][] Y, double[][] Extra_DATA, int[] row,
			double[] delta, double[] infusionTime, double[] dose, double[] dosingTime, int numberOfSortVariable,
			String[][] dataSortVariables, int noOfParam, double pdInc, int profileNo) throws RowsExceededException, WriteException,
			BiffException, IOException {
		int Npar = initial.length;
		double[] parameter = new double[Npar];
		for (int i = 0; i < Npar; i++) {
			parameter[i] = initial[i];
		}
		IT = 0;
		while (IT < pkpdInstance.iter) {
			IT++;
/*			 System.out.println(systemTime());
			 System.gc();*/
			if(pkpdInstance.exceptionForCurrentProfile == 1)
			{				
				break;
			}
			double RSS_old = Objective(parameter, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);
			if(pkpdInstance.exceptionForCurrentProfile == 1)
			{				
				break;
			}
			double[][] deltaBeta = detParameters(parameter,infusionTime, dose, dosingTime, X, Y, Extra_DATA,
					row, delta, profileNo);
			if(pkpdInstance.exceptionForCurrentProfile == 1)
			{				
				break;
			}
			double[] finalBeta = calculateNewParameter(parameter, deltaBeta,
					pkpdInstance.mu);
			int boundCount = 0;
			for (int i = 0; i < Npar; i++) {
				if (finalBeta[i] < pkpdInstance.lowerBound[i]
						|| finalBeta[i] > pkpdInstance.upperBound[i]) {
					boundCount++;
					break;
				}
			}
			double RSS_new = 0;
			RSS_new = Objective(finalBeta, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);
			if(pkpdInstance.exceptionForCurrentProfile == 1)
			{				
				break;
			}
			if (RSS_new < RSS_old) {
				double sumPar = 0;
				for (int i = 0; i < Npar; i++) {
					double ratio = Math.abs((finalBeta[i] - parameter[i])
							/ parameter[i]);
					sumPar = sumPar + ratio;
				}
				double check = sumPar / Npar;
				for (int i = 0; i < Npar; i++) {
					parameter[i] = finalBeta[i];
				}
				if (pkpdInstance.isIfCallingForParameterCalculation() == true) {
					String[] s = new String[numberOfSortVariable + 2
							+ noOfParam];
					for (int k = 0; k < numberOfSortVariable; k++) {
						s[k] = dataSortVariables[pkpdInstance.currentProfileNumber][k];
					}
					s[numberOfSortVariable] = IT +"";
					s[numberOfSortVariable + 1] = pkpdInstance.convertToScientificNumber(RSS_new);
					for (int i = 0; i < noOfParam; i++)
						s[numberOfSortVariable + 2 + i] = pkpdInstance.formatting(parameter[i], false) + "";
					pkpdInstance.minimizationProcessAL.add(s);
				}
				if (check <= pkpdInstance.convergence)
					break;
				pkpdInstance.iterNoForProgBar ++;
				pkpdInstance.setProgressBarIterNo(pkpdInstance.iterNoForProgBar );
				pkpdInstance.lambda = pkpdInstance.lambda / 10.0;
			} else {
				pkpdInstance.iterNoForProgBar ++;
				pkpdInstance.setProgressBarIterNo(pkpdInstance.iterNoForProgBar );
				pkpdInstance.mu = pkpdInstance.mu / 2.0;
				pkpdInstance.lambda = Math.min(1, 10 * pkpdInstance.lambda);
			}
		}
		pkpdInstance.setRequiredIter(IT);
		return parameter;
	}

	public double[] GaussNewtonLH(int modelNumber, double[] initial,
			double[][] X, double[][] Y, double[][] Extra_DATA, int[] row,
			double[] delta, double[] infusionTime, double[] dose, double[] dosingTime
			, int numberOfSortVariable, String[][] dataSortVariables, int noOfParam, 
			double pdInc, int profileNo) throws RowsExceededException, WriteException,
			BiffException, IOException {
		int Npar = initial.length;
		double mu_min = 0;
		double[] parameter = new double[Npar];
		IT = 1;
		for (int i = 0; i < Npar; i++)
			parameter[i] = initial[i];
		while (IT <= pkpdInstance.iter) {
			double WRSS0 = Objective(parameter,infusionTime,dose,dosingTime, X, Y, Extra_DATA, row, profileNo);
			if(WRSS0 == 0)
			{
				pkpdInstance.exceptionForCurrentProfile = 1;
				TestException inst = new TestException();
				inst.throwCustomException(125);
				
				pkpdInstance.workSheetOutputInst.getTextoutput().add("\r\n"
						+ StringUtils.rightPad(
								"*************************************************"
										+ "**********************************", 80)
						+ "\r\n\r\n");
				pkpdInstance.getWorkSheetOutputInst().getTextoutput()
				.add(StringUtils.rightPad("125:  Problem during WRSS calculation", 100));
				pkpdInstance.getWorkSheetOutputInst().getTextoutput()
				.add("\r\n\r\n");
			}
			if(pkpdInstance.exceptionForCurrentProfile == 1)
			{
				
				break;
			}

			double[][] deltaBeta = detParameters(parameter,infusionTime, dose,dosingTime, X, Y, Extra_DATA,
					row, delta, profileNo);

			if(pkpdInstance.exceptionForCurrentProfile == 1)
			{
				
				break;
			}

			double[] Beta05 = calculateNewParameter(parameter, deltaBeta, 0.5);

			double WRSS05 = Objective(Beta05, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);
			
			if(pkpdInstance.exceptionForCurrentProfile == 1)
			{
				
				break;
			}

			double[] Beta1 = calculateNewParameter(parameter, deltaBeta, 1);
			
			if(pkpdInstance.exceptionForCurrentProfile == 1)
			{
				
				break;
			}
			double WRSS1 = Objective(Beta1, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);

			
			if (WRSS0 < WRSS1) {
				mu_min = pkpdInstance.mu;
				while (WRSS1 > WRSS0) {
					mu_min = mu_min / 2.0;
					Beta1 = calculateNewParameter(parameter, deltaBeta, mu_min);
					WRSS1 = Objective(Beta1, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);
				}

				pkpdInstance.lambda = Math.min(1, 10 * pkpdInstance.lambda);

			}

			else {
				mu_min = 0.5 + 0.25 * ((WRSS0 - WRSS1) / (WRSS1 - 2.0 * WRSS05 + WRSS0));

				pkpdInstance.lambda = Math.min(1, pkpdInstance.lambda / 10.0);

			}

		
			double[] finalBeta = calculateNewParameter(parameter, deltaBeta,
					mu_min);

			double srs = Objective(finalBeta, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);

			
			double sumPar = 0;
			for (int i = 0; i < Npar; i++) {
				
				double ratio = Math.abs((finalBeta[i] - parameter[i])
						/ parameter[i]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar / Npar;
			
			
			if(pkpdInstance.exceptionForCurrentProfile == 1)
			{
				
				break;
			}
			for (int i = 0; i < Npar; i++) {
				parameter[i] = finalBeta[i];
			}

			if (pkpdInstance.isIfCallingForParameterCalculation() == true) {

				String[] s = new String[numberOfSortVariable + 2 + noOfParam];
				for (int k = 0; k < numberOfSortVariable; k++) {
					s[k] = dataSortVariables[pkpdInstance
							.getCurrentProfileNumber()][k];
				}
				s[numberOfSortVariable] = IT + "";
				s[numberOfSortVariable + 1] = pkpdInstance.convertToScientificNumber(srs) ;

				for (int i = 0; i < noOfParam; i++)
					s[numberOfSortVariable + 2 + i] = pkpdInstance.formatting(parameter[i], false) + "";

				pkpdInstance.minimizationProcessAL.add(s);
			}
			if (check <= pkpdInstance.convergence)
				break;

			///System.out.println(" iteration number:" + IT);
			IT = IT + 1;
			pkpdInstance.iterNoForProgBar ++;
			pkpdInstance.setProgressBarIterNo(pkpdInstance.iterNoForProgBar );
			
			if(IT > pkpdInstance.iter)
			{
				TestException inst = new TestException();
				inst.throwCustomException(22);
				pkpdInstance.workSheetOutputInst.getTextoutput().add("\r\n"
						+ StringUtils.rightPad(
								"*************************************************"
										+ "**********************************", 80)
						+ "\r\n\r\n");
				pkpdInstance.getWorkSheetOutputInst().getTextoutput()
				.add(StringUtils.rightPad("122: Insufficient Iteration Number. Continue with 100 more iterations", 100));
				pkpdInstance.getWorkSheetOutputInst().getTextoutput()
				.add("\r\n\r\n");
				
				pkpdInstance.iter = pkpdInstance.iter + 1000;
			}
		}

		IT = IT -1;
		pkpdInstance.setRequiredIter(IT);
		return parameter;

	}

	public double[] HartleyMethod(double[] initial, double[][] X, double[][] Y,
			double[][] Extra_DATA, int[] row, double[] delta, double[] infusionTime, 
			double[] dose, double[] dosingTime, int numberOfSortVariable, 
			String[][] dataSortVariables, int noOfParam, double pdInc, int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int Npar = initial.length;
		double mu_min = 0;
		double[] parameter = new double[Npar];
		//System.out.println(" inside the Hartley method");

		IT = 1;
		for (int i = 0; i < Npar; i++)
			parameter[i] = initial[i];

		while (IT <= pkpdInstance.iter) {

			
			double WRSS0 = Objective(parameter, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);
			

			double[][] deltaBeta = detParameters(parameter, infusionTime, dose, dosingTime, X, Y, Extra_DATA,
					row, delta, profileNo);

			double[] Beta05 = calculateNewParameter(parameter, deltaBeta, 0.5);

			double WRSS05 = Objective(Beta05, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);
			
			double[] Beta1 = calculateNewParameter(parameter, deltaBeta, 1);
			double WRSS1 = Objective(Beta1, infusionTime, dose, dosingTime, X, Y, Extra_DATA, row, profileNo);

			
			if (WRSS0 < WRSS1) {
				mu_min = pkpdInstance.mu;
				while (WRSS1 > WRSS0) {
					mu_min = mu_min / 2.0;
					Beta1 = calculateNewParameter(parameter, deltaBeta, mu_min);
					WRSS1 = Objective(Beta1,  infusionTime, dose, dosingTime,  X, Y, Extra_DATA, row, profileNo);
				}

			}

			else
				mu_min = 0.5 + 0.25 * ((WRSS0 - WRSS1) / (WRSS1 - 2.0 * WRSS05 + WRSS0));

			
			double[] finalBeta = calculateNewParameter(parameter, deltaBeta,
					mu_min);

			
			double sumPar = 0;
			for (int i = 0; i < Npar; i++) {
				
				double ratio = Math.abs((finalBeta[i] - parameter[i])
						/ parameter[i]);
				sumPar = sumPar + ratio;
			}
			double check = sumPar / Npar;
			
			
			
			if (check <= pkpdInstance.convergence)
				break;

			for (int i = 0; i < Npar; i++) {
				parameter[i] = finalBeta[i];
			}

			if (pkpdInstance.isIfCallingForParameterCalculation() == true) {

				String[] s = new String[numberOfSortVariable + 2 + noOfParam];
				for (int k = 0; k < numberOfSortVariable; k++) {
					s[k] = dataSortVariables[pkpdInstance
							.getCurrentProfileNumber()][k];
				}
				s[numberOfSortVariable] = IT + "";
				
				for (int i = 0; i < noOfParam; i++)
					s[numberOfSortVariable + 2 + i] = parameter[i] + "";

				pkpdInstance.minimizationProcessAL.add(s);
			}

			//System.out.println(" iteration number:" + IT);
			IT = IT + 1;
			pkpdInstance.iterNoForProgBar ++;
			pkpdInstance.setProgressBarIterNo(pkpdInstance.iterNoForProgBar );
			
			if(IT > pkpdInstance.iter)
			{
				TestException inst = new TestException();
				inst.throwCustomException(121);
				
				pkpdInstance.workSheetOutputInst.getTextoutput().add("\r\n"
						+ StringUtils.rightPad(
								"*************************************************"
										+ "**********************************", 80)
						+ "\r\n\r\n");
				pkpdInstance.getWorkSheetOutputInst().getTextoutput()
				.add(StringUtils.rightPad("121: Insufficient Iteration Number. Continue with 100 more iterations", 100));
				pkpdInstance.getWorkSheetOutputInst().getTextoutput()
				.add("\r\n\r\n");
			}
		}

		pkpdInstance.setRequiredIter(IT);
		return parameter;

	}

	private double[] calculateNewParameter(double[] parameter,
			double[][] deltaBeta, double muValue) {
		double[] newParameter = new double[parameter.length];

		for (int i = 0; i < parameter.length; i++) {
			newParameter[i] = parameter[i] + muValue * deltaBeta[i][0];
		}

		
		return newParameter;

	}

	static long enTimeafterGnBeforeMC;
	// Determine parameters
	private double[][] detParameters(double[] par, double[] infusionTime, double[] dose, double[] dosingTime, double[][] X, double[][] Y,
			double[][] Extra_DATA, int[] row, double[] delta, int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		// System.out.println(" Inside detParameters");

		int Npar = par.length;
		double[][] A = new double[Npar][Npar];
		double[][] B = new double[Npar][1];
		int Nfun = X[0].length;

		for (int f = 0; f < Nfun; f++) {
			int r = row[f];
			double[][] temp_J = Jacobian(par, infusionTime, dose, dosingTime, X, Y, Extra_DATA, delta, f, row, profileNo);
			double[][] temp_Q = new double[r][r];
			if (pkpdInstance.ifScaling != 0)
				temp_Q = ScaledWeights(par, infusionTime, dose, dosingTime, X, Y, Extra_DATA, f, row, profileNo);
			else
				temp_Q = weights(par,infusionTime, dose, dosingTime, X, Y, Extra_DATA, f, row, profileNo);

			double[][] temp_err = error(par, X, Y, Extra_DATA, f, row, infusionTime,
					dose, dosingTime, profileNo);
			double[][] temp_A = computeA(temp_J, temp_Q);
			double[][] temp_B = computeB(temp_J, temp_Q, temp_err);

			A = sumMatrices(A, temp_A);
			B = sumMatrices(B, temp_B);
		}
		//enTimeafterGnBeforeMC = System.currentTimeMillis();
		//long time = enTimeafterGnBeforeMC - startTimeafterGNBeforeMC;
		//System.out.println(systemTime());
		//System.out.println(" before Marquardt correction in Gauss Newton");
		
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				
				if (Double.isInfinite(A[i][j]) == true
						|| Double.isNaN(A[i][j]) == true) {
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
		
		for (int i = 0; i < B.length; i++) {
			for (int j = 0; j < B[0].length; j++) {
				if (Double.isInfinite(B[i][j]) == true
						|| Double.isNaN(B[i][j]) == true) {
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
		
		for (int i = 0; i < Npar; i++)
			for (int j = 0; j < Npar; j++) {
				if (i == j) {
					if (pkpdInstance.methodNo == 1)
						A[i][j] = A[i][j] + pkpdInstance.lambda * A[i][j];
					else if (pkpdInstance.methodNo == 3) {
						A[i][j] = A[i][j] + pkpdInstance.lambda;
						// A[i][j] = A[i][j] ;
					}
				} else
					A[i][j] = A[i][j];
			}
		
		
	
		double[][] deltaBeta = determineDeltaBeta(A, B);

			return deltaBeta;
	}

	// Determine A matrix
	double[][] CalculateA_mat(double[] par,double[] infusionTime, double[] dose, double[] dosingTime, double[][] X, double[][] Y,
			double[][] Extra_DATA, int[] row, int profileNo) throws RowsExceededException,
			WriteException, BiffException, IOException {
		int Npar = par.length;
		double[][] A_mat = new double[Npar][Npar];
		int Nfun = X[0].length;

		for (int f = 0; f < Nfun; f++) {
			int r = row[f];
			double[][] temp_J = Jacobian(par, infusionTime, dose, dosingTime, X, Y, Extra_DATA,
					pkpdInstance.delta, f, row, profileNo);

			double[][] temp_Q = new double[r][r];
			if (pkpdInstance.ifScaling != 0)
				temp_Q = ScaledWeights(par, infusionTime,  dose, dosingTime,  X, Y, Extra_DATA, f, row, profileNo);
			else
				temp_Q = weights(par, infusionTime,  dose, dosingTime, X, Y, Extra_DATA, f, row, profileNo);

			double[][] temp_A = computeA(temp_J, temp_Q);

			A_mat = sumMatrices(A_mat, temp_A);
		}

		return A_mat;

	}

	// Objective function: (Weighted sum of residuals)
	public double Objective(double[] par, double[] infusionTime, double[] dose, double[] dosingTime, double[][] X, double[][] Y,
			double[][] Extra_DATA, int[] row, int profileNo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		int Nfun = 1;
		double[] weightResSq = new double[Nfun];
		double SumWts = 0;
		double WRSS = 0;
		
		for (int i = 0; i < Nfun; i++) {
			int r = row[0];
			double[][] scaledWts = new double[r][r];
			double[][] weightMat = weights(par, infusionTime,  dose, dosingTime,  X, Y, Extra_DATA, i, row, profileNo);
			
			if(pkpdInstance.exceptionForCurrentProfile == 1)
			break;
			double[][] errorMat =  error(par, X, Y, Extra_DATA, i, row, infusionTime,
					 dose, dosingTime, profileNo);

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

			WRSS = WRSS + weightResSq[i];
		}

		return WRSS;
	}

	// Scaled weights
	public double[][] ScaledWeights(double[] par, double[] infusionTime, double[] dose, double[] dosingTime, double[][] X, double[][] Y,
			double[][] Extra_DATA, int fn_no, int[] row, int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		// int Nfun = row.length;
		fn_no = 0;
		double SumWts = 0;
		int r = row[fn_no];
		double[][] scaledWts = new double[r][r];
		double[][] weightMat = weights(par, infusionTime,  dose,  dosingTime,  X, Y, Extra_DATA, fn_no, row, profileNo);

		for (int k = 0; k < r; k++)
			SumWts = SumWts + weightMat[k][k];

		for (int j = 0; j < r; j++) {
			if (pkpdInstance.ifScaling != 0)
				scaledWts[j][j] = weightMat[j][j] * r / SumWts;
			else
				scaledWts[j][j] = weightMat[j][j];
		}

		return scaledWts;
	}

	// Parameter Increment
	private double[] par_inc(double[] par, double increment, int idx) {
		double incPar_set[] = new double[par.length];
		for (int i = 0; i < par.length; i++) {
			if (i == idx)
				incPar_set[i] = par[i] + increment;
			else
				incPar_set[i] = par[i];
		}
		return incPar_set;
	}

	// Jacobian
	public double[][] Jacobian(double[] par, double[] infusionTime, double[] dose, double[] dosingTime, double[][] X, double[][] Y,
			double[][] Extra_DATA, double[] delta, int fn_no, int[] row, int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int Npar = par.length;
		fn_no = 0;
		int r = row[fn_no];
		double j_mat[][] = new double[r][Npar];
		double inc_Par[] = new double[Npar];
		double fn = 0;
		libModelInst = new LibraryModelDefiner();
		
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		AsciiParameterEstimator asciiEvaluationInst = AsciiParameterEstimator.createAsciiParamEstimationInst();
		
		for (int i = 0; i < r; i++) {
			double xf = X[i][fn_no];

			if (xf != 999999.9) {

				if(pkpdInst.analysisType.equals("ascii"))
				{
					fn = asciiEvaluationInst.evaluateCode(par, xf, fn_no, profileNo, PkPdInfo.createPKPDInstance().isFirstIteration());
					
				}else
				{
					fn = libModelInst.chooseLibraryModel(par,
							infusionTime, dose,
							dosingTime, xf, fn_no, Extra_DATA, i,
							row);
				}
				
				
				
				if(pkpdInst.exceptionForCurrentProfile  == 1)
				{

					TestException inst = new TestException();
					inst.throwCustomException(123);
					pkpdInst.getWorkSheetOutputInst().getTextoutput().add("\r\n"
							+ StringUtils.rightPad(
									"*************************************************"
											+ "**********************************", 80)
							+ "\r\n\r\n");
					pkpdInst.getWorkSheetOutputInst().getTextoutput()
					.add(StringUtils.rightPad("121: Parameters value can not be estimated , Problem during function evaluation", 100));
					pkpdInst.getWorkSheetOutputInst().getTextoutput()
					.add("\r\n\r\n");	
					break;
				
				}

			}
			// System.out.println(" function value in jacobian=" + fn);

			for (int j = 0; j < Npar; j++) {
				if (xf == 999999.9)
					j_mat[i][j] = 0;
				else {

					inc_Par = par_inc(par, pkpdInst.pdIncrement, j);
					double inc_fn = 0;

					if(pkpdInst.analysisType.equals("ascii"))
					{
						inc_fn = asciiEvaluationInst.evaluateCode(inc_Par, xf, fn_no, profileNo, PkPdInfo.createPKPDInstance().isFirstIteration());
					}else
					{
					inc_fn = libModelInst.chooseLibraryModel(inc_Par,
							infusionTime,dose, dosingTime, xf, fn_no, Extra_DATA, i,
							row);
					}
					if(pkpdInst.exceptionForCurrentProfile  == 1)
					{

						TestException inst = new TestException();
						inst.throwCustomException(123);
						pkpdInst.getWorkSheetOutputInst().getTextoutput().add("\r\n"
								+ StringUtils.rightPad(
										"*************************************************"
												+ "**********************************", 80)
								+ "\r\n\r\n");
						pkpdInst.getWorkSheetOutputInst().getTextoutput()
						.add(StringUtils.rightPad("121: Parameters value can not be estimated , Problem during function evaluation", 100));
						pkpdInst.getWorkSheetOutputInst().getTextoutput()
						.add("\r\n\r\n");	
						break;
					
					}

					// System.out.println(" inc function value in jacobian="
					// + inc_fn);
					
					
						j_mat[i][j] = (inc_fn - fn)
						/ pkpdInst.pdIncrement;

						if (Double.isInfinite(j_mat[i][j]) == true
								|| Double.isNaN(j_mat[i][j]) == true) {
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
		}
		return j_mat;
	}

	// Weight matrix (Q)
	public double[][] weights(double[] par,double[] infusionTime, double[] dose, double[] dosingTime, double[][] X, double[][] Y,
			double[][] Extra_DATA, int fn_no, int[] row, int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		pkpdInstance = PkPdInfo.createPKPDInstance();
		fn_no = 0;
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
				fn = asciiEvaluationInst.evaluateCode(par, xf, fn_no, profileNo,PkPdInfo.createPKPDInstance().isFirstIteration() );	
			}else
			{

			fn = libModelInst.chooseLibraryModel(par, infusionTime, dose,
					dosingTime, xf, fn_no, Extra_DATA, i,
				row);
			}
			if(pkpdInstance.exceptionForCurrentProfile  == 1)
			{

				TestException inst = new TestException();
				inst.throwCustomException(123);
				pkpdInstance.getWorkSheetOutputInst().getTextoutput().add("\r\n"
						+ StringUtils.rightPad("*************************************************"
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
							if (fn != 0)
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
		
		
		return wts;
	}

	// error matrix
	public double[][] error(double[] par, double[][] X, double[][] Y,
			double[][] Extra_DATA, int fn_no, int[] row, double[] infusionTime,
			double[] dose, double[] dosingTime, int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		fn_no = 0;
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
					fn = asciiEvaluationInst.evaluateCode(par, xf, fn_no, profileNo, PkPdInfo.createPKPDInstance().isFirstIteration());
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
				
				}				
			
					errorMat[i][0] = yf - fn;
					//System.out.println(errorMat[i][0]);
					
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

	// Computing A
	private double[][] computeA(double[][] J_mat, double[][] Q_mat)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		PkPdInfo pkPdInst = PkPdInfo.createPKPDInstance();

		int Npar = J_mat[0].length;
		double[][] A = new double[Npar][Npar];

		Matrix J = new Matrix(J_mat);
		Matrix Q = new Matrix(Q_mat);
		Matrix JT = J.transpose();

		Matrix JTQ = JT.times(Q);
		Matrix A_mat = JTQ.times(J);
		A = A_mat.getArray();

		int rank = A_mat.rank();

		double conditionNumber = ConditionNumber(A);

		
		if (pkPdInst.isIfCallingForParameterCalculation() == true) {

			String[] s = new String[pkPdInst.numberOfSortVar+ 3];
			for (int k = 0; k < pkPdInst.numberOfSortVar; k++) {
				s[k] = pkPdInst.dataSortVariables[pkPdInst
						.getCurrentProfileNumber()][k];
			}
			s[pkPdInst.numberOfSortVar] = IT + "";
			s[pkPdInst.numberOfSortVar + 1] = rank + "";

			s[pkPdInst.numberOfSortVar + 2] = pkPdInst.formatting(conditionNumber,
					false)
					+ "";

			pkPdInst.conditionNumberAL.add(s);

		}

		return A;
	}

	// Computing B
	private double[][] computeB(double[][] J_mat, double[][] Q_mat,
			double[][] error_mat) {
		int Npar = J_mat[0].length;
		double[][] B = new double[Npar][1];

		Matrix J = new Matrix(J_mat);
		Matrix Q = new Matrix(Q_mat);
		Matrix JT = J.transpose();

		Matrix error = new Matrix(error_mat);
		Matrix JTQ = JT.times(Q);
		Matrix B_mat = JTQ.times(error);
		B = B_mat.getArray();

		return B;
	}

	// Evaluating delta beta
	private double[][] determineDeltaBeta(double[][] A, double[][] B) {
		int row = A.length;
		double[][] A_inv = new double[row][row];

		try{
			A_inv = inverseMatrix(A);
			
			for (int j = 0; j < A_inv.length; j++) {
				for (int j2 = 0; j2 < A_inv[0].length; j2++) {
					if (Double.isInfinite(A_inv[j][j2]) == true
							|| Double.isNaN(A_inv[j][j2]) == true) {
						TestException inst = new TestException();
						inst.throwCustomException(122);
						pkpdInstance.exceptionForCurrentProfile = 1;
						pkpdInstance.getWorkSheetOutputInst().getTextoutput().add("\r\n"
								+ StringUtils.rightPad(
										"*************************************************"
												+ "**********************************", 80)
								+ "\r\n\r\n");
						pkpdInstance.getWorkSheetOutputInst().getTextoutput()
						.add(StringUtils.rightPad("122: Parameters value can not be estimated , Problem during matrix inversion", 100));
						pkpdInstance.getWorkSheetOutputInst().getTextoutput()
						.add("\r\n\r\n");	
						break;
						
					}
				}
			}
			
			
		}catch (Exception e) {
			TestException inst = new TestException();
			inst.throwCustomException(122);
			pkpdInstance.exceptionForCurrentProfile = 1;
			pkpdInstance.getWorkSheetOutputInst().getTextoutput().add("\r\n"
					+ StringUtils.rightPad(
							"*************************************************"
									+ "**********************************", 80)
					+ "\r\n\r\n");
			pkpdInstance.getWorkSheetOutputInst().getTextoutput()
			.add(StringUtils.rightPad("122: Parameters value can not be estimated , Problem during matrix inversion", 100));
			pkpdInstance.getWorkSheetOutputInst().getTextoutput()
			.add("\r\n\r\n");	
			
		}
		Matrix A_inv_Mat = new Matrix(A_inv);
		Matrix B_Mat = new Matrix(B);
		Matrix deltaBeta_mat = A_inv_Mat.times(B_Mat);
		double[][] deltaBeta = deltaBeta_mat.getArray();

		return deltaBeta;
	}

	// Inverse A matrix (Variance covariance matrix)
	double[][] inverseMatrix(double[][] A) {
		int row = A.length;
		double[][] A_inv = new double[row][row];

		Matrix M = new Matrix(A);
		SingularValueDecomposition USV = null ;
		try
		{
		USV = new SingularValueDecomposition(M);
		}catch (Exception e) {
			pkpdInstance.exceptionForCurrentProfile = 1;
			pkpdInstance.getWorkSheetOutputInst().getTextoutput().add("\r\n"
					+ StringUtils.rightPad(
							"*************************************************"
									+ "**********************************", 80)
					+ "\r\n\r\n");
			pkpdInstance.getWorkSheetOutputInst().getTextoutput()
			.add(StringUtils.rightPad("124: Prolem during correlation matrix calculation.", 100));
			pkpdInstance.getWorkSheetOutputInst().getTextoutput()
			.add("\r\n\r\n");	
			
		}

		Matrix U = USV.getU();
		Matrix V = USV.getV();
		Matrix S = USV.getS();
		double[][] U_mat = U.getArray();
		double[][] S_mat = S.getArray();
		double[][] S_inv = new double[row][row];

		for (int i = 0; i < row; i++) {
			if (S_mat[i][i] <= 0.00000001)
				S_inv[i][i] = 0.0;
			else
				S_inv[i][i] = 1.0 / S_mat[i][i];

		}
		Matrix Sinv = new Matrix(S_inv);

		double[][] UT_mat = transpose(U_mat);
		Matrix UT = new Matrix(UT_mat);

		Matrix VS_inv = V.times(Sinv);
		Matrix VSinvUT = VS_inv.times(UT);
		A_inv = VSinvUT.getArray();

		return A_inv;
	}

	// Matrix sum
	private double[][] sumMatrices(double[][] mat1, double[][] mat2) {
		int rowCount = mat1.length;
		int colCount = mat1[0].length;
		double[][] sumMat = new double[rowCount][colCount];
		for (int i = 0; i < rowCount; i++)
			for (int j = 0; j < colCount; j++) {
				sumMat[i][j] = mat1[i][j] + mat2[i][j];
			}
		return sumMat;
	}

	// Matrix Transpose
	double[][] transpose(double[][] mat) {
		int rowCount = mat.length, colCount = mat[0].length;

		double[][] transposedMat = new double[colCount][rowCount];

		for (int i = 0; i < rowCount; i++)
			for (int j = 0; j < colCount; j++)
				transposedMat[j][i] = mat[i][j];

		return transposedMat;
	}

	// Product matrix
	double[][] multMatrix(double[][] A_mat, double[][] B_mat) {
		int rowCount = A_mat.length, colCount = B_mat[0].length;
		int p = A_mat[0].length;
		double[][] product_mat = new double[rowCount][colCount];

		if (A_mat[0].length != B_mat.length) {
			// System.out.println("Product not defined !!!!!! ");
		} else {
			for (int i = 0; i < rowCount; i++)
				for (int j = 0; j < colCount; j++)
					for (int k = 0; k < p; k++)
						product_mat[i][j] = product_mat[i][j] + A_mat[i][k]
								* B_mat[k][j];
		}
		return product_mat;
	}

	// Condition number
	double ConditionNumber(double[][] A) {
		int Npar = A.length;
		Matrix A_mat = new Matrix(A);
		EigenvalueDecomposition evd = A_mat.eig();
		double[] EigVals = evd.getRealEigenvalues();
		Arrays.sort(EigVals);
		double condNumber = Math.sqrt(EigVals[Npar - 1] / EigVals[0]);
		return condNumber;
	}
}