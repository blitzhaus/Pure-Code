package Model;

import java.io.*;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.LeastSquaresConverter;
import org.apache.commons.math.optimization.RealPointValuePair;

import view.ApplicationInfo;
import Common.TestException;
import Jama.Matrix;
import edu.umbc.cs.maple.utils.JamaUtils;

public class NelderMead  {
	String[][] inputMatrix;// took leeway for public access
	double[][] inputData;
	int rowCount = 0;
	int colCount = 0;
	String[] stringArray;

	int[] row;
	double[][] EXTRA_DATA;
	PkPdInfo pkpdInstance;
	int numberOfSortVariable;

	int noOfParam;
	String[][] dataSortVariables;
	int IT;
	NelderMeadCore simplex ;
	LibraryModelDefiner libModelInst;
	public NelderMead() {
		
		pkpdInstance = PkPdInfo.createPKPDInstance();
	}

	public double[] nMProcess(double[] initial, double[][] X,
			double[][] Y, double[][] EXTRA_DAT, int[] rowz, double[] delta,
			double[][] init_simplex, double[] infusionTime,double[] dose,double[] dosingTime, int profileNo) {
		
	
		
		noOfParam = pkpdInstance.noOfParam;
		dataSortVariables = pkpdInstance.dataSortVariables;
		numberOfSortVariable = pkpdInstance.numberOfSortVar;
		
		int Npar = initial.length;
		double[] finalParameter = new double[Npar];

		row = rowz;
		EXTRA_DATA = EXTRA_DAT;

		Matrix xMat = new Matrix(X);
		Matrix yMat = new Matrix(Y);

		inputData = JamaUtils.columnAppend(yMat, xMat).getArray();
		rowCount = inputData.length;
		finalParameter = initiateNelderMeadProcess(initial, X, Y, EXTRA_DAT, rowz, infusionTime, dose, dosingTime,profileNo);

		
		return finalParameter;
	}

	private void stringToDouble() {
		for (int i = 0; i < inputMatrix.length; i++) {
			for (int j = 0; j < inputMatrix[0].length; j++) {
				inputData[i][j] = Double.parseDouble(inputMatrix[i][j]);
			}
		}
	}

	private double[] pickResponsesColumn() {
		double[] obsns = new double[rowCount];
		for (int i = 0; i < inputData.length; i++) {
			obsns[i] = inputData[i][0];
		}
		return obsns;
	}

	private double[] weights() {
		double[] obsns = new double[rowCount];
		for (int i = 0; i < inputData.length; i++) {
			obsns[i] = Math.pow(inputData[i][0], (-1)*pkpdInstance.wtExp);

		}
		return obsns;
	}

	private double[] weights(double[] params,double[] infusionTime,double[] dose,double[] dosingTime) {
		double[] obsns = new double[rowCount];

		double[] preds = value(params, infusionTime, dose, dosingTime );

		for (int i = 0; i < inputData.length; i++) {
			obsns[i] = Math.pow(preds[i], (-1)*pkpdInstance.wtExp);
		}
		return obsns;
	}

	public double[] value(double[] point,double[] infusionTime,double[] dose,double[] dosingTime) {
		
		libModelInst=new LibraryModelDefiner();
		double[] val = new double[rowCount];

		int fn_no = 0;

		try {

			double objFuncVal = 0.0;

			for (int i = 0; i < inputData.length; i++) {
				double x = inputData[i][1];
				double fEval = libModelInst.chooseLibraryModel(point,
						infusionTime, dose,dosingTime, x, fn_no, EXTRA_DATA, i, row);
				val[i] = fEval;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return val;
	}

	public double[] initiateNelderMeadProcess(double[] start, double[][] X,
			double[][] Y, double[][] EXTRA_DAT, int[] rowz, double[] infusionTime,
			double[] dose,double[] dosingTime, int profileNo) {
		int dim = start.length;
		double eps = 1.0e-4;
		double scale = 1.0;
		
		GaussNewton gnInst = new GaussNewton();
	
		RealPointValuePair rlPointPair = null;

		double[] optParams = new double[start.length];
		double[] parameter =  new double[start.length];

		try {
			simplex = new NelderMeadCore();
			MyMultivariateVectorialFunction mvVecFn = new MyMultivariateVectorialFunction(
					inputData, EXTRA_DATA, row);
			double[] yData = pickResponsesColumn();

			double[] weights;

			if (pkpdInstance.wtScheme == 0) {
				weights = weights();
			} else {
				weights = weights(start, infusionTime, dose, dosingTime);
			}
			LeastSquaresConverter lsc = new LeastSquaresConverter(mvVecFn,
					yData, weights);

			IT = 1;
			for (int j = 0; j < pkpdInstance.iter; j++) {

				rlPointPair = simplex.optimize(lsc, GoalType.MINIMIZE, start);
				optParams = rlPointPair.getPoint();

				for (int j1 = 0; j1 < optParams.length; j1++) {
					start[j1] = optParams[j1];
				}

				
				if (pkpdInstance.wtScheme == 0) {
					weights = weights();
				} else {
					weights = weights(start, infusionTime, dose, dosingTime);
				}

				lsc = new LeastSquaresConverter(mvVecFn, yData, weights);
				
				// convergence checking 
				
				double sumPar = 0;
				for (int i = 0; i < optParams.length; i++) {
				
					double ratio = Math.abs((optParams[i] - parameter[i])
							/ parameter[i]);
					sumPar = sumPar + ratio;
				}
				double check = sumPar / optParams.length;
				
				for (int i = 0; i < optParams.length; i++) {
					parameter[i] = optParams[i];
				}
				
				double srs = gnInst.Objective(parameter,infusionTime, dose,
						dosingTime,X, Y, EXTRA_DAT, rowz, profileNo);
				
				if(srs == 0)
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
					.add(StringUtils.rightPad("125: Problem during WRSS calculation", 100));
					pkpdInstance.getWorkSheetOutputInst().getTextoutput()
					.add("\r\n\r\n");
					
				}
				
				
				
				if (pkpdInstance.isIfCallingForParameterCalculation() == true) {

					String[] s = new String[numberOfSortVariable+ 2
							+ noOfParam];
					for (int k = 0; k < numberOfSortVariable; k++) {
						s[k] = dataSortVariables[pkpdInstance.getCurrentProfileNumber()][k];
					}
					s[numberOfSortVariable] = IT + "";
					 s[numberOfSortVariable + 1]
					 = pkpdInstance.convertToScientificNumber(srs);

					for (int i = 0; i < noOfParam; i++)
						s[numberOfSortVariable + 2 	+ i] = pkpdInstance.formatting(parameter[i], false) + "";

					pkpdInstance.minimizationProcessAL.add(s);
				}
				
				
				
				if (check <= pkpdInstance.convergence )
					break;
				
				IT = IT +1;
			}
		} catch (Exception e) {
		}
		pkpdInstance.setRequiredIter(IT);
		return optParams;
	}

	

}
