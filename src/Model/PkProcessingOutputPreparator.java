package Model;

import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;

import javax.print.attribute.standard.Copies;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math.random.CorrelatedRandomVectorGenerator;

import view.ApplicationInfo;
import view.MyJTable;
import view.ProcessingInputsInfo;
import Common.WriteAppinfoForUnitTest;
import Jama.CholeskyDecomposition;
import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class PkProcessingOutputPreparator {
	int noOfParam;
	int noOfSortVariables;
	ApplicationInfo appInfo;
	int[] subjectObsNos;
	int noOfFunction;
	String[][] dataSortVariables;
	String[] paramName;
	PkParamEstimator caParamCalInst;
	CaGraphGenerator caGrapkGenerationInst;

	public PkProcessingOutputPreparator() {

		caParamCalInst = PkParamEstimator.createPkParamEstimateInstance();
		appInfo = caParamCalInst.appInfoinst;
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();

		noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();

		noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		subjectObsNos = procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo();

		noOfFunction = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfFunction();

		dataSortVariables = procInputInst.getProfileAndParamInfoObj()
				.getDataSortVariables();

		paramName = procInputInst.getProfileAndParamInfoObj()
				.getParameterNameForCA();
	}

	void createAndPrintingOfSummaryAndSummaryTable(
			PkParamEstimator pkpdMain, double[] parameter, double[][] x,
			double[][] y, double[][] extraData, int[] row, int profileNo,
			int numberOfSortVariable) throws RowsExceededException,
			WriteException, BiffException, IOException {

		GaussNewton gnInstance = new GaussNewton();
		LibraryModelDefiner libModelInst = new LibraryModelDefiner();

		double[] srs = new double[noOfFunction];
		double Combined_srs = 0;

		double[] time = new double[subjectObsNos[profileNo]];
		double[] conc = new double[subjectObsNos[profileNo]];
		double[] obsYForCorr = new double[subjectObsNos[profileNo]];
		double[] predYForCorr = new double[subjectObsNos[profileNo]];

		double[] obsYForWtCorr = new double[subjectObsNos[profileNo]];
		double[] predYForWtCorr = new double[subjectObsNos[profileNo]];

		pkpdMain.caProcOpPrep.writeHeaderForSummaryTable();

		double[] residual = new double[subjectObsNos[profileNo]];
		double WRSS = gnInstance.Objective(parameter,pkpdMain.infusionTime,
				pkpdMain.dose, pkpdMain.dosingTime,pkpdMain.pkpdInst.X,
				pkpdMain.pkpdInst.Y, pkpdMain.pkpdInst.extraData,
				pkpdMain.pkpdInst.row, profileNo);
			
		for (int i = 0; i < x[0].length; i++) {

			double[] X_val = new double[subjectObsNos[profileNo]];
			double[] Y_val = new double[subjectObsNos[profileNo]];

			pkpdMain.calcYval = new double[subjectObsNos[profileNo]];
			double[][] scaledWts = new double[subjectObsNos[profileNo]][subjectObsNos[profileNo]];
			scaledWts = gnInstance.ScaledWeights(parameter,pkpdMain.infusionTime,pkpdMain.dose,
					pkpdMain.dosingTime,
					pkpdMain.pkpdInst.X, pkpdMain.pkpdInst.Y,
					pkpdMain.pkpdInst.extraData, i, pkpdMain.pkpdInst.row, profileNo);
			double[][] Error = new double[subjectObsNos[profileNo]][1];
			Error = gnInstance.error(parameter, pkpdMain.pkpdInst.X,
					pkpdMain.pkpdInst.Y, pkpdMain.pkpdInst.extraData, i,
					pkpdMain.pkpdInst.row, pkpdMain.infusionTime, pkpdMain.dose,
					pkpdMain.dosingTime, profileNo);
			double error = 0;

			for (int rr = 0; rr < subjectObsNos[profileNo]; rr++) {
				X_val[rr] = pkpdMain.pkpdInst.X[rr][0];
				Y_val[rr] = pkpdMain.pkpdInst.Y[rr][0];
				obsYForCorr[rr] = pkpdMain.pkpdInst.Y[rr][0];
				obsYForWtCorr[rr] = pkpdMain.pkpdInst.Y[rr][0];
			}
			int idx = 0;

			for (int r = 0; r < pkpdMain.pkpdInst.row[0]; r++) {
				double x1 = pkpdMain.pkpdInst.X[r][i];
				pkpdMain.calcYval[r] = libModelInst.chooseLibraryModel(
						parameter, pkpdMain.infusionTime, pkpdMain.dose,
						pkpdMain.dosingTime, x1, i,
						pkpdMain.pkpdInst.extraData, r, pkpdMain.pkpdInst.row);

				predYForCorr[r] = pkpdMain.calcYval[r];
				predYForWtCorr[r] = pkpdMain.calcYval[r];
				X_val[r] = pkpdMain.pkpdInst.X[r][i];
				Y_val[r] = pkpdMain.pkpdInst.Y[r][i];

				

				if (Y_val[r] == 999999.9) {

				} else {
					error = Y_val[r] - pkpdMain.calcYval[r];

					double SqRes = Math.pow(Error[idx][0], 2);
					srs[i] = srs[i] + SqRes;
					idx = idx + 1;
				}

				residual[r] = error;

				
			}
			
			
			PkPdInfo pkPdInfoInst = PkPdInfo.createPKPDInstance();
			CalculateResidual calResInst = new CalculateResidual();
			calResInst.residualCalculation(pkPdInfoInst.parameter, pkpdMain.infusionTime, pkpdMain.dose, 
					pkpdMain.dosingTime, pkPdInfoInst.X, pkPdInfoInst.Y, pkPdInfoInst.extraData, 
					pkPdInfoInst.delta, profileNo, pkPdInfoInst.row, profileNo);
			
			double[] stdResidual = pkPdInfoInst.stdResidual;
			double[] sdPred = pkPdInfoInst.sePredy;
			
			int df = X_val.length - noOfParam;
			double SD = Math.sqrt(WRSS / df);
		
			
			for (int r = 0; r < pkpdMain.pkpdInst.row[0]; r++) {
				
				String[] s = new String[noOfSortVariables + 6];
				for (int k = 0; k < noOfSortVariables; k++) {
					s[k] = dataSortVariables[profileNo][k];
				}

				String[] s1 = new String[noOfSortVariables + 7];
				for (int k = 0; k < noOfSortVariables; k++) {
					s1[k] = dataSortVariables[profileNo][k];
				}
				pkpdMain.caProcOpPrep.writingsummaryDataToTextArea(pkpdMain,
						X_val, Y_val, scaledWts, residual[r],sdPred, stdResidual, idx, r);

				
				pkpdMain.caProcOpPrep.writingSummaryDataToSummaryTable(
						pkpdMain, X_val, Y_val, scaledWts, residual[r],sdPred, stdResidual, idx, r, s1);

			}
			
			writingSummaryDataToPredictedValTable(X_val ,profileNo);
			calculateCorrObsYAndPredY(obsYForCorr, predYForCorr);
			calculateWeightedCorrObsYAndPredY(obsYForWtCorr, predYForWtCorr);

			time = pkpdMain.caProcOpPrep
					.generateDataAndPlotWeightedPredYAndResidualY(pkpdMain, profileNo,
							numberOfSortVariable, appInfo, residual, scaledWts);

			pkpdMain.caProcOpPrep.writingRequiredIterationToOutput(pkpdMain);

			Combined_srs = Combined_srs + srs[i];
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad("Sum of Squared Residuals",
									50)

							+ StringUtils.rightPad(":"
									+ pkpdMain.pkpdInst
											.convertToScientificNumber(srs[i]),
									20) + "\r\n");

		}

			
			pkpdMain.caProcOpPrep.wrinitingSrsAndWRSSToTextArea(
				pkpdMain, parameter, Combined_srs,WRSS);

		pkpdMain.caProcOpPrep.misOutpuAndSecondParamCal(pkpdMain,
				pkpdMain.pkpdInst.parameter, pkpdMain.pkpdInst.X,
				pkpdMain.pkpdInst.Y, pkpdMain.pkpdInst.extraData,
				pkpdMain.pkpdInst.row, srs[0], WRSS, time, profileNo,
				numberOfSortVariable);

	}
	
	
	private void writingSummaryDataToPredictedValTable(double[] xVal,
			int ii) {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		double stepLength = (xVal[xVal.length - 1] - xVal[0])
				/ pkpdInst.noOfPredval;
		double predVal;
		double xValForPred = xVal[0];
		PdCurveStripper csInst = new PdCurveStripper();
		for (int i = 0; i < pkpdInst.noOfPredval; i++) {
			xValForPred = xValForPred + stepLength;
			predVal = csInst.linearInterpolation(xVal, PkParamEstimator.createPkParamEstimateInstance().calcYval,
					xValForPred);

			String[] s = new String[noOfSortVariables + 6];
			for (int k = 0; k < noOfSortVariables; k++) {
				s[k] = dataSortVariables[ii][k];
			}

			s[noOfSortVariables] = 1 + "";
			s[noOfSortVariables + 1] = pkpdInst.formatting(xValForPred,
					true);
			s[noOfSortVariables + 2] = pkpdInst.formatting(predVal,
					true);

			PkParamEstimator.createPkParamEstimateInstance().pkpdInst.predictedValueAL.add(s);
		}

	}
	
	private double[] computeSeOfPredConc(double sd)
	{
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		double[] se = new double[pkpdInst.X.length];
		double meanX = 0;
		for (int i = 0; i < se.length; i++) {
			meanX = meanX + pkpdInst.X[i][0]; 
		}
		
		meanX = meanX/pkpdInst.X.length;
		double diffSqure = 0;
		
		for (int i = 0; i < se.length; i++) {
			diffSqure = diffSqure + (pkpdInst.X[i][0] - meanX) * (pkpdInst.X[i][0] - meanX); 
		}
		
		for (int i = 0; i < se.length; i++) {
			se[i] = Math.sqrt(sd*sd*((1.0/pkpdInst.X.length) + 
				(pkpdInst.X[i][0] - meanX)* (pkpdInst.X[i][0] - meanX)/diffSqure)); 
		}
		
		
		
		return se;
	}

	private  double[]calculateStandRes(double[] residual) {
		double[] stdResidual = new double[residual.length];
		double meanRes = 0;
		for (int i = 0; i < stdResidual.length; i++) {
			meanRes = meanRes +residual[i];
		}
		meanRes = meanRes / stdResidual.length;
		
		double diffSuqreRes = 0;
		for (int i = 0; i < stdResidual.length; i++) {
			diffSuqreRes = diffSuqreRes + (residual[i] -meanRes)*(residual[i] -meanRes);
		}
		
		diffSuqreRes = diffSuqreRes/stdResidual.length;
		
		for (int i = 0; i < stdResidual.length; i++) {
			stdResidual[i] = (residual[i] -meanRes)/Math.sqrt(diffSuqreRes); 
		}
		return stdResidual;
	}

	private void calculateWeightedCorrObsYAndPredY(double[] x, double[] y) {
	
		PkParamEstimator caParamCalInst = PkParamEstimator
				.createPkParamEstimateInstance();
		PkPdInfo pkpdInstance = PkPdInfo.createPKPDInstance();
		double Ra = 0;
		// System.out.println("########### In correlation coefficient calculation");
		int row = x.length;

		

		double sumX = 0;
		double sumY = 0;
		double sumXY = 0;
		double sumX2 = 0;
		double sumW = 0;
		double sumWXY = 0;
		double sumWX = 0;
		double sumWY = 0;
		double sumWX2 = 0;
		double sumWY2 = 0;
		double Sxx = 0;
		double Syy = 0;
		double Sxy = 0;

		for (int i = 0; i < x.length; i++) {
			sumX = sumX + x[i];
			sumY = sumY + y[i];
			// weightSum=weightSum+1/Math.pow(y[i],(weightingIndex-2));
			sumXY = sumXY + x[i] * y[i];
			sumX2 = sumX2 + x[i] * x[i];
			sumW = sumW + 1
					/ Math.pow(Math.exp(y[i]), (-1) * pkpdInstance.wtExp);
			sumWX = sumWX + x[i] * 1
					/ Math.pow(Math.exp(y[i]), (-1) * pkpdInstance.wtExp);
			sumWXY = sumWXY + x[i] * y[i] * 1
					/ Math.pow(Math.exp(y[i]), (-1) * pkpdInstance.wtExp);
			sumWY = sumWY + y[i] * 1
					/ Math.pow(Math.exp(y[i]), (-1) * pkpdInstance.wtExp);
			sumWX2 = sumWX2 + x[i] * x[i] * 1
					/ Math.pow(Math.exp(y[i]), (-1) * pkpdInstance.wtExp);
			sumWY2 = sumWY2 + y[i] * y[i] * 1
					/ Math.pow(Math.exp(y[i]), (-1) * pkpdInstance.wtExp);

		}

		caParamCalInst.weightedCorrObsAndPredY = (sumW * sumWXY - sumWX * sumWY)
				/ (Math.sqrt(sumW * sumWX2 - sumWX * sumWX) * Math.sqrt(sumW
						* sumWY2 - sumWY * sumWY));

	}

	private void calculateCorrObsYAndPredY(double[] obsYForCorr,
			double[] predYForCorr) {

		double[] Ax = obsYForCorr;
		double[] Bx = predYForCorr;
		PkParamEstimator caParamCalInst = PkParamEstimator
				.createPkParamEstimateInstance();

		int row = Ax.length;

	

		double SumAx = 0;
		double SumBx = 0;
		double SumABx = 0;
		double SumA_Sq = 0;
		double SumB_Sq = 0;

		for (int i = 0; i < row; i++) {
			SumAx = SumAx + Ax[i];
			SumBx = SumBx + Bx[i];
			SumABx = SumABx + (Ax[i] * Bx[i]);
			SumA_Sq = SumA_Sq + (Ax[i] * Ax[i]);
			SumB_Sq = SumB_Sq + (Bx[i] * Bx[i]);
		}

		double t1 = row * SumA_Sq - Math.pow(SumAx, 2);
		t1 = Math.sqrt(t1);
		double t2 = row * SumB_Sq - Math.pow(SumBx, 2);
		t2 = Math.sqrt(t2);

		caParamCalInst.corrObsAndPredY = ((row * SumABx) - (SumAx * SumBx))
				/ (t1 * t2);
		System.out.println();
	}

	double[] generateDataAndPlotWeightedPredYAndResidualY(
			PkParamEstimator pkpdMain, int ii, int numberOfSortVariable,
			ApplicationInfo appInfo, double[] residual, double[][] scaledWts)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		double[] time;
		double[] conc;
		String profile = "";
		if (numberOfSortVariable != 0)
			for (int k = 0; k < numberOfSortVariable; k++) {
				profile = profile
						+ (procInputInst.getMappingDataObj()
								.getSortVariablesListVector().get(k)
								+ " = " + dataSortVariables[ii][k] + ", ");

			}

		time = new double[subjectObsNos[ii]];
		conc = new double[subjectObsNos[ii]];
		for (int k = 0; k < subjectObsNos[ii]; k++) {
			time[k] = pkpdMain.pkpdInst.X[k][0];
			conc[k] = pkpdMain.pkpdInst.Y[k][0];

		}

		double[] weightedPredictedY = new double[subjectObsNos[ii]];
		double[] weightedResidualY = new double[subjectObsNos[ii]];

		for (int count = 0; count < subjectObsNos[ii]; count++) {
			weightedPredictedY[count] = pkpdMain.calcYval[count]
					* scaledWts[count][count];

			weightedResidualY[count] = residual[count]
					* scaledWts[count][count];
		}

		pkpdMain.caProcOpPrep.generatePlotOutput(pkpdMain, time, conc,
				pkpdMain.calcYval, profile, weightedPredictedY,
				weightedResidualY);
		return time;
	}

	void writingRequiredIterationToOutput(PkParamEstimator pkpdMain)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				"\r\n\r\n"
						+ StringUtils.rightPad("", 5)
						+ StringUtils
								.rightPad("Number of iterations taken", 52)

						+ StringUtils.rightPad(":" + pkpdMain.pkpdInst.requiredIter, 10)
						+ "\r\n");

		String[] forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "Required Iteration";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkpdMain.pkpdInst.requiredIter
				+ "";

		pkpdMain.pkpdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);
	}

	void wrinitingSrsAndWRSSToTextArea(PkParamEstimator pkpdMain,
			double[] parameter, double Combined_srs, double WRSS)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();

		if (pkpdMain.pkpdInst.ifRoundingOff != 0) {
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
					"Combined SRS = "
							+ pkpdInst.formatting(Combined_srs,
									false));
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

		}

		

		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5)
						+ StringUtils.rightPad(
								"Sum of Weighted Squared Residuals", 43)

						+ StringUtils.rightPad(":"
								+ pkpdMain.pkpdInst
										.convertToScientificNumber(WRSS), 10)
						+ "\r\n");

		
	}

	void writingSummaryDataToSummaryTable(PkParamEstimator pkpdMain,
			double[] X_val, double[] Y_val, double[][] scaledWts,
			double error,double[] sdPred, double[] stdResidual,
			
			int idx, int r, String[] s1) throws RowsExceededException,
			WriteException, BiffException, IOException {

		s1[noOfSortVariables] = pkpdMain.pkpdInst.formatting(X_val[r],
				true);
		s1[noOfSortVariables + 1] = pkpdMain.pkpdInst.formatting(Y_val[r],
				false);
		s1[noOfSortVariables + 2] = pkpdMain.pkpdInst.formatting(
				pkpdMain.calcYval[r], false);
		s1[noOfSortVariables + 3] = pkpdMain.pkpdInst.formatting(error,
				false);
		s1[noOfSortVariables + 4] = pkpdMain.pkpdInst.formatting(
				scaledWts[r][r], false);
		s1[noOfSortVariables + 5] = pkpdMain.pkpdInst.formatting(sdPred[r],
				false);
		s1[noOfSortVariables + 6] = pkpdMain.pkpdInst.formatting(stdResidual[r],
				false);
		pkpdMain.pkpdInst.summaryTableAL.add(s1);
	}
	
	void calculatePredConc() throws RowsExceededException, WriteException, BiffException, IOException
	{
		PkPdInfo pkPdinst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkPdinst.copyProcessingInput();
		LibraryModelDefiner libModelInst = new LibraryModelDefiner();
		
		int noOfPredVal = Integer.parseInt(procInputInst.getModelInputTab2Obj().getNumberOfPredictedValue());
		
		int noOfdataPts = pkPdinst.X.length;
		double stepLength = (pkPdinst.X[noOfdataPts - 1][0] - pkPdinst.X[0][0])/noOfPredVal;
		int rowIdx = 0;
		
		double[] predConc = new double[noOfPredVal];
		double x = pkPdinst.X[0][0];
		
		String[] s = new String[noOfSortVariables + 6];
		for (int k = 0; k < noOfSortVariables; k++) {
			s[k] = dataSortVariables[pkPdinst.currentProfileNumber][k];
		}
		
		
		for (int i = 0; i < noOfPredVal; i++) {
			
			predConc[i] = libModelInst.chooseLibraryModel(pkPdinst.parameter, PkParamEstimator.createPkParamEstimateInstance().infusionTime,
					PkParamEstimator.createPkParamEstimateInstance().dose, PkParamEstimator.createPkParamEstimateInstance().dosingTime, x, 1,
					pkPdinst.extraData, rowIdx, pkPdinst.row); 
			 x = x + stepLength;
			 
			 s[noOfSortVariables] = 1+"";
			 s[noOfSortVariables + 1] = pkPdinst.formatting(x,
						true);
			 s[noOfSortVariables + 2] = pkPdinst.formatting(predConc[i],
						false);
			 pkPdinst.predictedValueAL.add(s);
			 
			 
		}
		
	}
	
	
	

	void writingSummaryDataToPredictedValTable(
			PkParamEstimator pkpdMain, double[] X_val, double[] Y_val,
			double[][] scaledWts,  double error,double[] sdPred, double[] stdResidual, int idx, int r, String[] s)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		s[noOfSortVariables] = 1 + "";
		s[noOfSortVariables + 1] = pkpdMain.pkpdInst.formatting(X_val[r],
				true);
		s[noOfSortVariables + 2] = pkpdMain.pkpdInst.formatting(Y_val[r],
				false);
		s[noOfSortVariables + 3] = pkpdMain.pkpdInst.formatting(
				pkpdMain.calcYval[r], false)
				+ "";
		s[noOfSortVariables + 4] = pkpdMain.pkpdInst.formatting(error,
				false);
		s[noOfSortVariables + 5] = pkpdMain.pkpdInst.formatting(
				scaledWts[idx - 1][idx - 1], false);
		pkpdMain.pkpdInst.predictedValueAL.add(s);
	}

	void writingsummaryDataToTextArea(PkParamEstimator pkpdMain,
			double[] X_val, double[] Y_val, double[][] scaledWts, double error,
			double[] sdPred, double[] stdResidual,
			int idx, int r) throws RowsExceededException, WriteException,
			BiffException, IOException {

		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5)
						+ StringUtils.rightPad(pkpdMain.pkpdInst.formatting(
								X_val[r], true), 10)
						+ "\t"
						+ StringUtils.rightPad(pkpdMain.pkpdInst.formatting(
								Y_val[r], false), 10)
						+ "\t"
						+ StringUtils.rightPad(pkpdMain.pkpdInst
								.formatting(pkpdMain.calcYval[r],
										false), 10)
						+ "\t"
						+ StringUtils.rightPad(pkpdMain.pkpdInst.formatting(
								error, false), 10)
						+ "\t"
						+ StringUtils.rightPad(pkpdMain.pkpdInst.formatting(
								scaledWts[r][r],
								false), 10)
						+ "\t"
						+ StringUtils.rightPad(pkpdMain.pkpdInst.formatting(
								sdPred[r],
								false), 10) 
						+ "\t"
						+ StringUtils.rightPad(pkpdMain.pkpdInst.formatting(
								stdResidual[r],
								false), 10) + "\r\n");

	}

	void writeHeaderForSummaryTable() throws RowsExceededException,
			WriteException, BiffException, IOException {

		PkParamEstimator pkpdMain = PkParamEstimator
				.createPkParamEstimateInstance();

		pkpdMain.pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 40)

				+ StringUtils.rightPad("SUMMARY OF NOLINEAR ESTIMATION", 20)
						+ "\r\n\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5) + StringUtils.rightPad("X", 10)
						+ "\t" + StringUtils.rightPad("Observed Y", 10) + "\t"
						+ StringUtils.rightPad("Predicted Y", 10) + "\t"
						+ StringUtils.rightPad("Residual", 10) + "\t"
						+ StringUtils.rightPad("Weight", 10) +"\t"
						+ StringUtils.rightPad("SE-Pred", 10) + "\t"
						+ StringUtils.rightPad("Stand Res", 10) +"\r\n"	);
		
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5) + StringUtils.rightPad("", 10)
						+ "\t" + StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\r\n");
	}

	void misOutpuAndSecondParamCal(PkParamEstimator pkpdMain,
			double[] parameter, double[][] x, double[][] y,
			double[][] extraData, int[] row, double srs, double WRSS,
			double[] time, int profileNo, int numberOfSortVariable)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		GaussNewton gnInstance = new GaussNewton();
		double[][] A = gnInstance.CalculateA_mat(pkpdMain.pkpdInst.parameter, pkpdMain.infusionTime,
				pkpdMain.dose, pkpdMain.dosingTime,
				pkpdMain.pkpdInst.X, pkpdMain.pkpdInst.Y,
				pkpdMain.pkpdInst.extraData, pkpdMain.pkpdInst.row, profileNo);
		double condNumber = gnInstance.ConditionNumber(A);
		
		double[][] A_inv = gnInstance.inverseMatrix(A);

		int NObs = 0;
		for (int i = 0; i < x[0].length; i++) {
			NObs = NObs + pkpdMain.pkpdInst.row[i];
		}
		int df = NObs - noOfParam;
		double SD = Math.sqrt(WRSS / df);

		double[][] varienceCovarienceMatrix = pkpdMain.caProcOpPrep
				.computeVarCovarianceMatrix(pkpdMain, A_inv, SD);

		double[] SE = new double[noOfParam];
		double[] CV = new double[noOfParam];

		pkpdMain.caProcOpPrep.writingParametersToTextAndHDT(pkpdMain,
				pkpdMain.pkpdInst.parameter, pkpdMain.initial, A_inv, SD, profileNo,
				SE, CV);
		pkpdMain.caProcOpPrep
				.fillingUpVarienceCoVarienceMatrixTableAndTextOutput(pkpdMain,
						varienceCovarienceMatrix, profileNo);

		pkpdMain.caProcOpPrep.fillingUpPartialDerivativeTable(pkpdMain, time,
				numberOfSortVariable, profileNo);
		double[][] C = pkpdMain.caProcOpPrep.computeCorrelationMatrix(pkpdMain,
				A_inv);
		pkpdMain.caProcOpPrep.fillingUpCorrelationMatrix(pkpdMain, profileNo, C);

		pkpdMain.pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("No. of Observations", 23) + "\t"
						+ StringUtils.rightPad(":" + NObs, 10) + "\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				
				StringUtils.rightPad("Degrees of Freedom", 23) + "\t"
						+ StringUtils.rightPad(":" + df, 10) + "\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				
				StringUtils.rightPad("Standard Deviation ", 23)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdMain.pkpdInst.formatting(SD,
										false), 10)
						+ "\r\n");

		double AIC = 1.0 * (NObs) * Math.log(WRSS) + 2.0 * noOfParam;
		double SC = 1.0 * (NObs) * Math.log(WRSS) + noOfParam
				* Math.log(1.0 * (NObs));
		pkpdMain.pkpdInst.aicValue[pkpdMain.pkpdInst.aicCount] = AIC;
		
		

		Matrix A_mat = new Matrix(A);
		int rank = A_mat.rank();
		double trace = A_mat.trace();
		EigenvalueDecomposition ED = A_mat.eig();

		double[] eigenValue = ED.getRealEigenvalues();
		Arrays.sort(eigenValue);

		pkpdMain.caProcOpPrep.fillingUpEigenValueDisplayTable(pkpdMain,
				eigenValue);

		pkpdMain.caProcOpPrep.writingDiagnosticsToTextArea(pkpdMain,
				condNumber, AIC, SC, rank, trace);

		caParamCalInst = PkParamEstimator.createPkParamEstimateInstance();
		SecondaryParamComputer secondaryParamCalInst = SecondaryParamComputer.createSecParamCalInstance();
		if (procInputInst.getModelInputTab1Obj()
				.getAlgebraicModel() == true)

			secondaryParamCalInst.calculateSecondaryParameter(pkpdInst.parameter,
					pkpdMain.pkpdInst.modelNumber, pkpdMain.dose[0], A_inv, SD);

		pkpdMain.caProcOpPrep.fillingUpDiagnosticsSpreedSheet(pkpdMain, srs,
				WRSS, trace, AIC, SC, condNumber);

	}

	double[][] computeVarCovarianceMatrix(PkParamEstimator pkpdMain,
			double[][] aInv, double SD) {
		
		
		double[][] varianceCovarianceMatrix = new double[aInv.length][aInv[0].length];

	

		for (int i = 0; i < aInv.length; i++) {
			for (int j = 0; j < aInv[0].length; j++) {

				
				varianceCovarianceMatrix[i][j] = aInv[i][j] * SD * SD;

			}
		}

		
		return varianceCovarianceMatrix;
	}
	
	
	

	private double[] computeVariance() {
		ResultsStructureStorer inst = new ResultsStructureStorer();
		PkParamEstimator caParamCalInst = PkParamEstimator
				.createPkParamEstimateInstance();
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		int noOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		String[][] temp = inst
				.retrievingResultsFromAL(caParamCalInst.pkpdInst.minimizationProcessAL);

		double[][] paramVal = new double[temp.length - 1][temp[0].length
				- noOfSortVar - 2];

		for (int i = 0; i < paramVal.length; i++) {
			for (int j = 0; j < temp[0].length - noOfSortVar - 2; j++) {
				paramVal[i][j] = Double.parseDouble(temp[i + 1][noOfSortVar + 2
						+ j]);
			}
		}

		double[] varForParam = new double[paramVal[0].length];
		double sum = 0;
		double squreSum = 0;
		for (int i = 0; i < varForParam.length; i++) {

			sum = 0;

			for (int j = 0; j < paramVal.length; j++) {
				sum = sum + paramVal[j][i];
			}

			double mean = sum / paramVal.length;
			squreSum = 0;
			for (int j = 0; j < paramVal.length; j++) {
				squreSum = squreSum + (paramVal[j][i] - mean)
						* (paramVal[j][i] - mean);
			}
			varForParam[i] = squreSum / paramVal.length;
		}

		return varForParam;
	}

	double[][] computeCorrelationMatrix(PkParamEstimator pkpdMain,
			double[][] aInv) {
		Matrix M = new Matrix(aInv);
		CholeskyDecomposition LLT = new CholeskyDecomposition(M);
		Matrix Low = LLT.getL();
		double[][] Low_mat = Low.getArray();
		double[][] diag = new double[noOfParam][noOfParam];
		GaussNewton gnInstance = new GaussNewton();

		for (int i = 0; i < noOfParam; i++) {
			for (int j = 0; j < noOfParam; j++) {
				if (i == j)
					diag[i][j] = Math.sqrt(aInv[i][j]);
				else
					diag[i][j] = 0;
			}
		}

		double[][] diag_inv = gnInstance.inverseMatrix(diag);
		double[][] L = gnInstance.multMatrix(diag_inv, Low_mat);

		double[][] L_trans = gnInstance.transpose(L);
		double[][] C = gnInstance.multMatrix(L, L_trans);

		return C;
	}

	void writingDiagnosticsToTextArea(PkParamEstimator pkpdMain,
			double condNumber, double AIC, double SC, int rank, double trace)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("Cond #", 23)
						+ StringUtils.rightPad("", 5)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdMain.pkpdInst.formatting(condNumber,
										false), 10)
						+ "\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("Rank", 23) + StringUtils.rightPad("", 5)
						+ "\t" + StringUtils.rightPad(":" + rank, 10) + "\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("Trace", 23)
						+ StringUtils.rightPad("", 5)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdMain.pkpdInst.formatting(trace,
										false) + "", 10)
						+ "\r\n");

		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("Corr(Obs, Pred)", 23)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdMain.pkpdInst.formatting(
										pkpdMain.corrObsAndPredY,
										false), 10)
						+ "\r\n");

		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				
				StringUtils.rightPad("WtdCorr(Obs, Pred)", 23)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdMain.pkpdInst.formatting(
										pkpdMain.weightedCorrObsAndPredY,
										false), 10)
						+ "\r\n");

		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("AIC", 23)
						+ StringUtils.rightPad("", 5)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdMain.pkpdInst.formatting(AIC,
										false), 10)
						+ "\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("SC", 23)
						+ StringUtils.rightPad("", 5)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdMain.pkpdInst.formatting(SC,
										false), 10)
						+ "\r\n");

	}

	void fillingUpInitialParameterTable(PkParamEstimator pkpdMain,
			double[] parameter, double[] lowerBound, double[] upperBound, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(
						"*************************************************"
								+ "**********************************", 80)
						+ "\r\n\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 40)

						+ StringUtils.rightPad(
								"INITIAL PARAMETER VALUE AND BOUNDS", 40)
						+ "\r\n\r\n");

		if (procInputInst.getModelInputTab2Obj()
				.getInitialParamByCS() == 1)
			pkpdMain.pkpdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 5)

									+ StringUtils
											.rightPad(
													"INITIAL PARAMETER CALCULATION METHOD : Curve Stripping",
													60) + "\r\n\r\n");

		else if (procInputInst.getModelInputTab2Obj()
				.getInitialParamBYGA() == 1)
			pkpdMain.pkpdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 5)
									+ StringUtils
											.rightPad(
													"INITIAL PARAMETER CALCULATION METHOD : Genetic Algorithm",
													60) + "\r\n\r\n");
		
		else if (procInputInst.getModelInputTab2Obj()
				.getInitialParamBYGS() == 1)
			pkpdMain.pkpdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 5)
									+ StringUtils
											.rightPad(
													"INITIAL PARAMETER CALCULATION METHOD : Grid Search",
													60) + "\r\n\r\n");
		else if (procInputInst.getModelInputTab2Obj()
				.getPreviouslyGenInitVal() == 1)
			pkpdMain.pkpdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 5)
									+ StringUtils
											.rightPad(
													"INITIAL PARAMETER CALCULATION METHOD : Previously Generated",
													60) + "\r\n\r\n");

		else
			pkpdMain.pkpdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 5)
									+ StringUtils
											.rightPad(
													"INITIAL PARAMETER CALCULATION METHOD : User Defined",
													60) + "\r\n\r\n");

		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5)
						+ StringUtils.rightPad("Parameter", 10) + "\t"
						+ StringUtils.rightPad("Initial Value", 14) + "\t"
						+ StringUtils.rightPad("Lower Bound", 12) + "\t"
						+ StringUtils.rightPad("Upper Bound", 12) + "\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5) + StringUtils.rightPad("", 10)
						+ "\t" + StringUtils.rightPad("", 14) + "\t"
						+ StringUtils.rightPad("", 12) + "\t"
						+ StringUtils.rightPad("", 12) + "\r\n");
		for (int i = 0; i < noOfParam; i++) {

			String[] s = new String[noOfSortVariables + 4];
			for (int k = 0; k < noOfSortVariables; k++) {
				s[k] = dataSortVariables[ii][k];
			}

			s[noOfSortVariables] = paramName[i];
			
			if(procInputInst.getModelInputTab2Obj()
					.isPropagateFinalEstimae() == true && pkpdMain.pkpdInst.currentProfileNumber >0 )
			{
				s[noOfSortVariables + 1] = pkpdMain.pkpdInst.formatting(
						pkpdMain.propFinalEstimate[i], false);
				
				s[noOfSortVariables + 2] = pkpdMain.pkpdInst.formatting(
						0, false);
				s[noOfSortVariables + 3] = pkpdMain.pkpdInst.formatting(
						pkpdMain.propFinalEstimate[i] *10, false);
			}
			else
			{
				s[noOfSortVariables + 1] = pkpdMain.pkpdInst.formatting(
						parameter[i],false);
				
				if (pkpdMain.valueForParameterBoundary == 1) {
					s[noOfSortVariables + 2] = pkpdMain.pkpdInst.formatting(
							lowerBound[i], false);
					s[noOfSortVariables + 3] = pkpdMain.pkpdInst.formatting(
							upperBound[i], false);

				} else {
					s[noOfSortVariables + 2] = pkpdMain.pkpdInst.formatting(0,
							false);
					
					s[noOfSortVariables + 3] = pkpdMain.pkpdInst.formatting(
							parameter[i] * 10, false);
				}
			}
			
			

			

			pkpdMain.pkpdInst.initialParameterAL.add(s);

			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(paramName[i], 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdMain.pkpdInst
									.formatting(parameter[i],
											false), 14)
							+ "\t"
							+ StringUtils.rightPad(pkpdMain.pkpdInst
									.formatting(lowerBound[i],
											false), 12)
							+ "\t"
							+ StringUtils.rightPad(pkpdMain.pkpdInst
									.formatting(upperBound[i],
											false), 12)
							+ "\r\n\r\n");

		}

	}

	void generatePlotOutput(PkParamEstimator pkpdMain, double[] time,
			double[] conc, double[] calcYval, String profile,
			double[] weightedPredictedY, double[] weightedResidualY)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caGrapkGenerationInst = new CaGraphGenerator();
		
		caGrapkGenerationInst.plotGeneration(time, conc, calcYval, profile, weightedPredictedY, weightedResidualY);
		
	}

	void fillingUpCorrelationMatrix(PkParamEstimator pkpdMain, int ii,
			double[][] C) throws RowsExceededException, WriteException,
			BiffException, IOException {

		pkpdMain.pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						"\r\n\r\n"
								+ StringUtils
										.rightPad(
												"*************************************************"
														+ "*****************************************************************",
												80) + "\r\n\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 40)
						/* + "\t" */
						+ StringUtils.rightPad(
								"CORRELATION MATRIX OF THE ESTIMATES", 10)
						+ "\r\n\r\n");

		String temp = "";

		temp = StringUtils.rightPad("", 5)
				+ StringUtils.rightPad("Parameter", 10) + "\t";

		for (int i = 0; i < pkpdMain.pkpdInst.parameter.length; i++) {

			temp = temp + StringUtils.rightPad(paramName[i], 10) + "\t";

		}

		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(temp);

		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add("\r\n");
		for (int i = 0; i < noOfParam; i++) {

			String[] s = new String[noOfSortVariables + noOfParam + 1];
			for (int k = 0; k < noOfSortVariables; k++) {
				s[k] = dataSortVariables[ii][k];
			}

			temp = "";
			s[noOfSortVariables] = paramName[i];
			for (int j = 0; j <= i; j++) {
				if (j == 0) {

					temp = temp
							+ StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(paramName[i], 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdMain.pkpdInst
									.formatting(C[i][j],
											false), 10);

					s[noOfSortVariables + 1 + j] = pkpdMain.pkpdInst
							.formatting(C[i][j],false);

				} else {

					temp = temp
							+ "\t"
							+ StringUtils.rightPad(pkpdMain.pkpdInst
									.formatting(C[i][j],
											false), 10);

					s[noOfSortVariables + 1 + j] = pkpdMain.pkpdInst
							.formatting(C[i][j],false);

				}

			}
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(temp);
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add("\r\n");
			pkpdMain.pkpdInst.correlationMatrixAL.add(s);

		}

	}

	void fillingUpPartialDerivativeTable(PkParamEstimator pkpdMain,
			double[] time, int numberOfSortVariable, int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caParamCalInst = PkParamEstimator.createPkParamEstimateInstance();
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		for (int f = 0; f < pkpdMain.pkpdInst.X[0].length; f++) {

			String profile = "";
			if (numberOfSortVariable != 0)
				for (int k = 0; k < numberOfSortVariable; k++) {
					profile = profile
							+ (procInputInst
									.getMappingDataObj()
									.getSortVariablesListVector().get(k)
									+ " = " + dataSortVariables[profileNo][k] + ", ");
				}

			GaussNewton gnInstance = new GaussNewton();
			double[][] temp_J = gnInstance.Jacobian(
					pkpdMain.pkpdInst.parameter,
					pkpdMain.infusionTime,
					pkpdMain.dose, pkpdMain.dosingTime,
					pkpdMain.pkpdInst.X,
					pkpdMain.pkpdInst.Y, pkpdMain.pkpdInst.extraData,
					pkpdMain.pkpdInst.delta, f, pkpdMain.pkpdInst.row, profileNo);
			for (int i = 0; i < pkpdMain.pkpdInst.row[f]; i++) {

				String[] s = new String[noOfSortVariables + 1 + noOfParam];
				for (int k = 0; k < noOfSortVariables; k++) {
					s[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];

				}

				s[noOfSortVariables] = pkpdMain.pkpdInst.formatting(time[i],
						true);

				for (int j = 0; j < noOfParam; j++) {

					s[noOfSortVariables + 1 + j] = pkpdMain.pkpdInst
							.formatting(temp_J[i][j], false);
				}

				pkpdMain.pkpdInst.partialDerivativeAL.add(s);

			}

			caGrapkGenerationInst = new CaGraphGenerator();
			caGrapkGenerationInst.partialDetPlotGeneration(time, temp_J, profile,
					noOfParam);
		}
	}

	void fillingUpVarienceCoVarienceMatrixTableAndTextOutput(
			PkParamEstimator pkpdMain,
			double[][] varienceCovarienceMatrix, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		
		calculateSdOfPredConc(varienceCovarienceMatrix);

		pkpdMain.pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						"\r\n\r\n"
								+ StringUtils
										.rightPad(
												"*************************************************"
														+ "*****************************************************************",
												80) + "\r\n\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 40)
						/* + "\t" */
						+ StringUtils.rightPad(
								"VARIANCE CO-VARIANCE MATRIX OF THE ESTIMATES",
								10) + "\r\n\r\n");

		String temp = "";

		temp = temp + StringUtils.rightPad("", 5)
				+ StringUtils.rightPad("Parameter", 10) + "\t";

		for (int i = 0; i < pkpdMain.pkpdInst.parameter.length; i++) {

			temp = temp + StringUtils.rightPad(paramName[i], 10) + "\t";

		}
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(temp);

		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add("\r\n");
		for (int i = 0; i < noOfParam; i++) {
			String[] s = new String[noOfSortVariables + noOfParam + 1];
			for (int k = 0; k < noOfSortVariables; k++) {
				s[k] = dataSortVariables[ii][k];
			}
			temp = "";
			s[noOfSortVariables] = paramName[i];
			for (int j = 0; j <= i; j++) {
				if (j == 0) {

					
					s[noOfSortVariables + 1 + j] = pkpdMain.pkpdInst
							.convertToScientificNumber(varienceCovarienceMatrix[i][j]);

					temp = temp
							+ StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(paramName[i], 10)
							+ "\t"
							
							+ StringUtils
									.rightPad(
											pkpdMain.pkpdInst
													.convertToScientificNumber(varienceCovarienceMatrix[i][j]),
											10);

				} else {

					
					s[noOfSortVariables + 1 + j] = pkpdMain.pkpdInst
							.convertToScientificNumber(varienceCovarienceMatrix[i][j]);

					temp = temp
							+ "\t"
							

							+ StringUtils
									.rightPad(
											pkpdMain.pkpdInst
													.convertToScientificNumber(varienceCovarienceMatrix[i][j]),
											10);

				}

			}
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(temp);
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add("\r\n");
			pkpdMain.pkpdInst.varienceCovarienceMatrixAL.add(s);

		}
	}

	private void calculateSdOfPredConc(double[][] varienceCovarienceMatrix) {
		
		
		
	}

	void writingParametersToTextAndHDT(PkParamEstimator pkpdMain,
			double[] parameter, double[] initial, double[][] aInv, double sD,
			int ii, double[] CV, double[] SE) throws RowsExceededException,
			WriteException, BiffException, IOException {
		caParamCalInst = PkParamEstimator.createPkParamEstimateInstance();
		EstimatedParamValStorer printParamValInst = EstimatedParamValStorer.createParamPrintInstance();
		DeModelsParamValStorer printParamValForDEModInst = new DeModelsParamValStorer();

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		if (procInputInst.getModelInputTab1Obj()
				.getAlgebraicModel() == true)

			printParamValInst.parameterValuePrinting(
					pkpdMain.pkpdInst.parameter, initial, aInv, sD);
		else
			printParamValForDEModInst.parameterValuePrinting(
					pkpdMain.pkpdInst.parameter, initial, aInv, sD);

	}

	void fillingUpDosingDisplayTable(PkParamEstimator pkpdMain)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		caParamCalInst = PkParamEstimator.createPkParamEstimateInstance();
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		int noOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();

		String[] forDosingSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDosingSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
		}

		forDosingSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDosingSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
		}

		forDosingSpreedSheet[noOfSortVariables] = "Number of Doses";
		forDosingSpreedSheet[noOfSortVariables + 1] = noOfDose + "";
		pkpdMain.pkpdInst.dosingValueAL.add(forDosingSpreedSheet);

		for (int i = 0; i < noOfDose; i++) {
			forDosingSpreedSheet = new String[noOfSortVariables + 2];
			for (int k = 0; k < noOfSortVariables; k++) {
				forDosingSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
			}

			forDosingSpreedSheet[noOfSortVariables] = "Dose No: " + (i + 1);
			forDosingSpreedSheet[noOfSortVariables + 1] = caParamCalInst.pkpdInst
					.formatting(pkpdMain.dose[i],
							false);
			pkpdMain.pkpdInst.dosingValueAL.add(forDosingSpreedSheet);

			forDosingSpreedSheet = new String[noOfSortVariables + 2];
			for (int k = 0; k < noOfSortVariables; k++) {
				forDosingSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
			}

			forDosingSpreedSheet[noOfSortVariables] = "Dosing Time of Dose No: "
					+ (i + 1);
			forDosingSpreedSheet[noOfSortVariables + 1] = caParamCalInst.pkpdInst
					.formatting(pkpdMain.dosingTime[i],
							true);
			pkpdMain.pkpdInst.dosingValueAL.add(forDosingSpreedSheet);

			if (caParamCalInst.infusionTime[i] != 0) {
				forDosingSpreedSheet = new String[noOfSortVariables + 2];
				for (int k = 0; k < noOfSortVariables; k++) {
					forDosingSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
				}

				forDosingSpreedSheet[noOfSortVariables] = "End Time of Dose No: "
						+ (i + 1);
				forDosingSpreedSheet[noOfSortVariables + 1] = caParamCalInst.pkpdInst
						.formatting(pkpdMain.infusionTime[i],
								true);
				pkpdMain.pkpdInst.dosingValueAL.add(forDosingSpreedSheet);
			}

		}

	}

	void fillingUpUserSettingsTable(PkParamEstimator pkpdMain)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[] forUserSettingsSpreedSheet = new String[1];

		forUserSettingsSpreedSheet[0] = "Model Number:" + pkpdMain.pkpdInst.modelNumber;
		pkpdMain.pkpdInst.userSettingsAL.add(forUserSettingsSpreedSheet);

		forUserSettingsSpreedSheet = new String[1];
		if (pkpdMain.pkpdInst.methodNo == 1)
			forUserSettingsSpreedSheet[0] = "Method used: Gauss-Newton";
		else if (pkpdMain.pkpdInst.methodNo == 2)
			forUserSettingsSpreedSheet[0] = "Method used: Nelder-Mead";
		else if (pkpdMain.pkpdInst.methodNo == 3)
			forUserSettingsSpreedSheet[0] = "Method used: Levenberg- Hartley";

		pkpdMain.pkpdInst.userSettingsAL.add(forUserSettingsSpreedSheet);

		forUserSettingsSpreedSheet = new String[1];
		forUserSettingsSpreedSheet[0] = "Convergence Criteria:"
				+ pkpdMain.pkpdInst.convergence;
		pkpdMain.pkpdInst.userSettingsAL.add(forUserSettingsSpreedSheet);

		forUserSettingsSpreedSheet = new String[1];
		forUserSettingsSpreedSheet[0] = "Iteration:" + pkpdMain.pkpdInst.iter;
		pkpdMain.pkpdInst.userSettingsAL.add(forUserSettingsSpreedSheet);

	}

	void writingHeaderToTextOutput(String stringTime)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		String[] s11 = new String[2];
		s11[0] = stringTime.substring(0, 10);
		s11[1] = stringTime.substring(11, 19);

		PkParamEstimator pkpdMain = PkParamEstimator
				.createPkParamEstimateInstance();
		
	
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		

		boolean ifSimulation = procInputInst.getModelInputTab1Obj().isSimulation();
		
		if(ifSimulation == true)
		{
			SimulationForLibraryModels.createSimulationInst().wsOutput.getTextoutput().add(
					StringUtils.rightPad(" ", 100) + "\t"
							+ StringUtils.rightPad("DATE", 5) + "\t"
							+ StringUtils.rightPad(s11[0], 30) + "\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput.getTextoutput().add(
					StringUtils.rightPad(" ", 100) + "\t"
							+ StringUtils.rightPad("TIME", 5) + "\t"
							+ StringUtils.rightPad(s11[1], 30) + "\r\n\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput.getTextoutput().add(
					StringUtils.rightPad(" ", 30)
							+ "\t"
							+ StringUtils.rightPad("TATA CONSULTANCY SERVICES LTD",
									30) + "\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput()
					.add(
							StringUtils.rightPad(" ", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													"DRUG DEVELOPMENT PLATFORM, COMPARTMENTAL ANALYSIS",
													55) + "\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput.getTextoutput().add(
					StringUtils.rightPad(" ", 40) + "\t"
							+ StringUtils.rightPad("VERSION 1.0 30SEP 2012", 25)
							+ "\r\n\r\n\r\n");

			
		}
		else
		{
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad(" ", 100) + "\t"
							+ StringUtils.rightPad("DATE", 5) + "\t"
							+ StringUtils.rightPad(s11[0], 30) + "\r\n");
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad(" ", 100) + "\t"
							+ StringUtils.rightPad("TIME", 5) + "\t"
							+ StringUtils.rightPad(s11[1], 30) + "\r\n\r\n");
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad(" ", 30)
							+ "\t"
							+ StringUtils.rightPad("TATA CONSULTANCY SERVICES LTD",
									30) + "\r\n");
			pkpdMain.pkpdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad(" ", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													"DRUG DEVELOPMENT PLATFORM, COMPARTMENTAL ANALYSIS",
													55) + "\r\n");
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad(" ", 40) + "\t"
							+ StringUtils.rightPad("VERSION 1.0 30SEP 2012", 25)
							+ "\r\n\r\n\r\n");
		}
		
		

	}

	void writingInputToTextOutput(PkParamEstimator pkpdMain)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caParamCalInst = PkParamEstimator.createPkParamEstimateInstance();
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();

		ModelRelatedInformations mRI = new ModelRelatedInformations();
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(" ", 5)
						+ StringUtils
								.rightPad("Listing of Input Commands ", 40)
						+ "\r\n\r\n");

		if( procInputInst.getModelInputTab1Obj()
				.getAlgebraicModel() == true) {
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
					
					StringUtils.rightPad("MODEL TYPE", 20) + "\t"
							+ StringUtils.rightPad(":" + "Algebraic Model", 50)
							+ "\r\n");
		} else {

			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
					
					StringUtils.rightPad("MODEL TYPE", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Differential Equation Model", 50)
							+ "\r\n");

		}

		pkpdMain.pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						
						StringUtils.rightPad("MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(pkpdMain.pkpdInst.modelNumber - 1),
												50) + "\r\n");

		if (procInputInst.getModelInputTab1Obj()
				.getAlgebraicModel() == false) {
			if (procInputInst.getModelInputTab2Obj()
					.getOdeSolverMethod() == 1) {
				pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
						
						StringUtils.rightPad("ODE SOLVER METHOD", 20)
								+ "\t"
								+ StringUtils.rightPad(":"
										+ "Runge Kutta Cashcarp", 50) + "\r\n");
			} else {

				pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
						
						StringUtils.rightPad("ODE SOLVER METHOD", 20)
								+ "\t"
								+ StringUtils.rightPad(":"
										+ "Runge Kutta Fehlberg", 50) + "\r\n");

			}

		}

		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				
				StringUtils.rightPad("NUMBER OF VARIABLES", 20) + "\t"
						+ StringUtils.rightPad(":" + 2, 50) + "\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				
				StringUtils.rightPad("NUMBER OF PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad(":" + noOfParam, 50) + "\r\n");

		int temp = procInputInst.getMappingDataObj()
				.getxColumnCorrespondinOriginalIndex() + 1;
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				
				StringUtils.rightPad("TIME COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");

		temp = procInputInst.getMappingDataObj()
				.getyColumnCorrespondinOriginalIndex() + 1;

		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				
				StringUtils.rightPad("CONCENTRATION COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				
				StringUtils.rightPad("CONVERGENCE CRITERIA", 20)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdMain.pkpdInst.convergence, 50) + "\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				
				StringUtils.rightPad("ITERATION NUMBER", 20)
						+ "\t"
						+ StringUtils
								.rightPad(":" + pkpdMain.pkpdInst.iter, 50)
						+ "\r\n");

		if (pkpdMain.pkpdInst.methodNo == 1)
			pkpdMain.pkpdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							
							StringUtils.rightPad("MINIMIZATION METHOD", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													":"
															+ "Gauss Newton Method (Levenberg-Marquardt Algorithm)",
													50) + "\r\n");

		else if (pkpdMain.pkpdInst.methodNo == 3)
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
					
					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":" + "Nelder Mead Method",
									50) + "\r\n");

		else if (pkpdMain.pkpdInst.methodNo == 2)
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
					
					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Gauss Newton Method (Hartley)", 50)
							+ "\r\n");

		if (pkpdMain.pkpdInst.wtScheme == 0)
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
					
					StringUtils.rightPad("WEIGHTING", 20) + "\t"
							+ StringUtils.rightPad(":" + "Observed", 50)
							+ "\r\n");

		else
			pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
					
					StringUtils.rightPad("WEIGHTING", 20) + "\t"
							+ StringUtils.rightPad(":" + "Predicted", 50)
							+ "\r\n");
		double temp1 = ((-1) * pkpdMain.pkpdInst.wtExp);
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				
				StringUtils.rightPad("WEIGHTING EXPONENT", 20) + "\t"
						+ StringUtils.rightPad(":" + temp1, 50) + "\r\n");

	}

	void fillingUpEigenValueDisplayTable(PkParamEstimator pkpdMain,
			double[] eigenValue) throws RowsExceededException, WriteException,
			BiffException, IOException {

		for (int i = 0; i < eigenValue.length; i++) {
			String[] s = new String[noOfSortVariables + 2];
			for (int k = 0; k < noOfSortVariables; k++) {
				s[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
			}

			s[noOfSortVariables] = (i + 1) + "";
			s[noOfSortVariables + 1] = pkpdMain.pkpdInst.formatting(
					eigenValue[i],false)
					+ "";
			pkpdMain.pkpdInst.eigenValuesAL.add(s);

		}

	}

	void fillingUpHistoryDisplayTable(PkParamEstimator pkpdMain,
			String string) throws RowsExceededException, WriteException,
			BiffException, IOException {

		String[] s = new String[7];
		s[0] = string.substring(0, 10);
		s[1] = string.substring(11, 19);
		s[2] = System.getProperty("user.name");
		s[3] = "";
		s[4] = "";
		s[5] = "CA Analysis completed successfully";
		s[6] = "";

		pkpdMain.pkpdInst.historyAL.add(s);
	}

	void fillingUpDiagnosticsSpreedSheet(PkParamEstimator pkpdMain,
			double srs, double WRSS, double trace, double AIC, double SC,
			double condNumber) throws RowsExceededException, WriteException,
			BiffException, IOException {

		String[] forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "SRS";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkpdMain.pkpdInst
				.formatting(srs, false);

		pkpdMain.pkpdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "WSRS";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkpdMain.pkpdInst
				.formatting(WRSS, false);

		pkpdMain.pkpdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "Trace";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkpdMain.pkpdInst
				.formatting(trace, false);

		pkpdMain.pkpdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "Corr(Obs, Pred)";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkpdMain.pkpdInst
				.formatting(pkpdMain.corrObsAndPredY, false
						);

		pkpdMain.pkpdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "WtdCorr(Obs, Pred)";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkpdMain.pkpdInst
				.formatting(pkpdMain.weightedCorrObsAndPredY,
						false);

		pkpdMain.pkpdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "AIC";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkpdMain.pkpdInst
				.formatting(AIC, false);

		pkpdMain.pkpdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "SC";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkpdMain.pkpdInst
				.formatting(SC, false);

		pkpdMain.pkpdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkpdMain.pkpdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "Cond Number";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkpdMain.pkpdInst
				.formatting(condNumber, false);

		pkpdMain.pkpdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);
	}

}
