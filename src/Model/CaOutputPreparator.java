package Model;

import java.io.IOException;
import java.util.Arrays;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import Jama.CholeskyDecomposition;
import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class CaOutputPreparator {

	int noOfParam;
	int noOfSortVariables;

	int[] subjectObsNos;
	int noOfFunction;
	String[][] dataSortVariables;
	String[] paramName;
	PkPdInfo pkPdInst;
	ListOfDataStructures dataStructList;
	CaGraphGenerator caGrapkGenerationInst;
	SecondaryParamComputer secondaryParamCalInst;
	EstimatedParamValStorer printParamValInst;

	public CaOutputPreparator() {

		pkPdInst = PkPdInfo.createPKPDInstance();
		dataStructList = ListOfDataStructures.createListOfDataStructInstance();

		noOfParam = pkPdInst.noOfParam;

		noOfSortVariables = pkPdInst.numberOfSortVar;

		subjectObsNos = dataStructList.subjectObsNos;

		noOfFunction = dataStructList.noOfFunc;

		dataSortVariables = pkPdInst.dataSortVariables;

		paramName = pkPdInst.paramName;
	}

	public void createAndPrintingOfSummaryAndSummaryTable(double[] parameter,
			double[][] x, double[][] y, double[][] extraData, int[] row,
			int profileNo, int numberOfSortVariable)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		GaussNewton gnInstance = new GaussNewton();
		LibraryModelDefiner libModelInst = new LibraryModelDefiner();

		double[] srs = new double[noOfFunction];
		double Combined_srs = 0;

		double[] time = new double[subjectObsNos[profileNo]];
		double[] obsYForCorr = new double[subjectObsNos[profileNo]];
		double[] predYForCorr = new double[subjectObsNos[profileNo]];

		double[] obsYForWtCorr = new double[subjectObsNos[profileNo]];
		double[] predYForWtCorr = new double[subjectObsNos[profileNo]];

		writeHeaderForSummaryTable();

		double[] residual = new double[subjectObsNos[profileNo]];
		double WRSS = gnInstance.Objective(parameter,
				dataStructList.infusionTime, dataStructList.dose,
				dataStructList.dosingTime, pkPdInst.X, pkPdInst.Y,
				pkPdInst.extraData, pkPdInst.row, profileNo);

		for (int i = 0; i < 1; i++) {

			double[] X_val = new double[subjectObsNos[profileNo]];
			double[] Y_val = new double[subjectObsNos[profileNo]];

			dataStructList.calcYval = new double[subjectObsNos[profileNo]];
			double[][] scaledWts = new double[subjectObsNos[profileNo]][subjectObsNos[profileNo]];
			scaledWts = gnInstance.ScaledWeights(parameter,
					dataStructList.infusionTime, dataStructList.dose,
					dataStructList.dosingTime, pkPdInst.X, pkPdInst.Y,
					pkPdInst.extraData, i, pkPdInst.row, profileNo);
			double[][] Error = new double[subjectObsNos[profileNo]][1];
			Error = gnInstance.error(parameter, pkPdInst.X, pkPdInst.Y,
					pkPdInst.extraData, i, pkPdInst.row,
					dataStructList.infusionTime, dataStructList.dose,
					dataStructList.dosingTime, profileNo);
			double error = 0;

			for (int rr = 0; rr < subjectObsNos[profileNo]; rr++) {
				X_val[rr] = pkPdInst.X[rr][0];
				Y_val[rr] = pkPdInst.Y[rr][0];
				obsYForCorr[rr] = pkPdInst.Y[rr][0];
				obsYForWtCorr[rr] = pkPdInst.Y[rr][0];
			}
			int idx = 0;

			for (int r = 0; r < pkPdInst.row[0]; r++) {
				double x1 = pkPdInst.X[r][i];
				dataStructList.calcYval[r] = libModelInst.chooseLibraryModel(
						parameter, dataStructList.infusionTime,
						dataStructList.dose, dataStructList.dosingTime, x1, i,
						pkPdInst.extraData, r, pkPdInst.row);

				predYForCorr[r] = dataStructList.calcYval[r];
				predYForWtCorr[r] = dataStructList.calcYval[r];
				X_val[r] = pkPdInst.X[r][i];
				Y_val[r] = pkPdInst.Y[r][i];

				if (Y_val[r] == 999999.9) {

				} else {
					error = Y_val[r] - dataStructList.calcYval[r];

					double SqRes = Math.pow(Error[idx][0], 2);
					srs[i] = srs[i] + SqRes;
					idx = idx + 1;
				}

				residual[r] = error;

			}

			int df = X_val.length - noOfParam;
			double SD = Math.sqrt(WRSS / df);

			double[] delta = new double[1];
			CalculateResidual resCalInstance = new CalculateResidual();
			resCalInstance
					.residualCalculation(
							parameter,
							PkParamEstimator.createPkParamEstimateInstance().infusionTime,
							PkParamEstimator.createPkParamEstimateInstance().dose,
							PkParamEstimator.createPkParamEstimateInstance().dosingTime,
							x, y, extraData, delta, 0, row, profileNo);

			for (int r = 0; r < pkPdInst.row[0]; r++) {

				String[] s = new String[noOfSortVariables + 6];
				for (int k = 0; k < noOfSortVariables; k++) {
					s[k] = dataSortVariables[profileNo][k];
				}

				String[] s1 = null;
				if (pkPdInst.analysisType.equals("pk")
						|| pkPdInst.analysisType.equals("pd")
						|| pkPdInst.analysisType.equals("mm")) {
					s1 = new String[noOfSortVariables + 7];

				} else if (pkPdInst.analysisType.equals("irm")
						|| pkPdInst.analysisType.equals("pkpdlink")) {
					s1 = new String[noOfSortVariables + 8];
				}

				for (int k = 0; k < noOfSortVariables; k++) {
					s1[k] = dataSortVariables[profileNo][k];
				}

				writingSummaryDataToTextArea(X_val, Y_val, scaledWts,
						residual[r], pkPdInst.sePredy, pkPdInst.stdResidual, i,
						r);

				writingSummaryDataToSummaryTable(X_val, Y_val, scaledWts,
						residual[r], pkPdInst.sePredy, pkPdInst.stdResidual,
						idx, r, s1);

			}

			writingSummaryDataToPredictedValTable(X_val, Y_val, profileNo);

			calculateCorrObsYAndPredY(obsYForCorr, predYForCorr);
			calculateWeightedCorrObsYAndPredY(obsYForWtCorr, predYForWtCorr);

			time = generateDataAndPlotWeightedPredYAndResidualY(profileNo,
					numberOfSortVariable, residual, scaledWts);

			writingRequiredIterationToOutput();

			Combined_srs = Combined_srs + srs[i];
			pkPdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad("Sum of Squared Residuals",
									50)

							+ StringUtils.rightPad(":"
									+ pkPdInst
											.convertToScientificNumber(srs[i]),
									20) + "\r\n");

		}

		wrinitingSrsAndWRSSToTextArea(parameter, Combined_srs, WRSS);

		misOutpuAndSecondParamCal(pkPdInst.parameter, pkPdInst.X, pkPdInst.Y,
				pkPdInst.extraData, pkPdInst.row, srs[0], WRSS, time,
				profileNo, numberOfSortVariable);

	}

	private void writingSummaryDataToPredictedValTable(double[] xVal,
			double[] yVal, int ii) {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		double stepLength = (xVal[xVal.length - 1] - xVal[0])
				/ pkpdInst.noOfPredval;
		double predVal;
		double xValForPred = xVal[0];
		PdCurveStripper csInst = new PdCurveStripper();
		for (int i = 0; i < pkpdInst.noOfPredval; i++) {
			xValForPred = xValForPred + stepLength;
			predVal = csInst.linearInterpolation(xVal, dataStructList.calcYval,
					xValForPred);

			String[] s = new String[noOfSortVariables + 6];
			for (int k = 0; k < noOfSortVariables; k++) {
				s[k] = dataSortVariables[ii][k];
			}

			s[noOfSortVariables] = 1 + "";
			s[noOfSortVariables + 1] = pkpdInst.formatting(xValForPred, true);
			s[noOfSortVariables + 2] = pkpdInst.formatting(predVal, false);

			pkpdInst.predictedValueAL.add(s);
		}

	}

	private double[] computeSeOfPredConc(double sd) {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		double[] se = new double[pkpdInst.X.length];
		double meanX = 0;
		for (int i = 0; i < se.length; i++) {
			meanX = meanX + pkpdInst.X[i][0];
		}

		meanX = meanX / pkpdInst.X.length;
		double diffSqure = 0;

		for (int i = 0; i < se.length; i++) {
			diffSqure = diffSqure + (pkpdInst.X[i][0] - meanX)
					* (pkpdInst.X[i][0] - meanX);
		}

		for (int i = 0; i < se.length; i++) {
			se[i] = Math.sqrt(sd
					* sd
					* ((1.0 / pkpdInst.X.length) + (pkpdInst.X[i][0] - meanX)
							* (pkpdInst.X[i][0] - meanX) / diffSqure));
		}

		return se;
	}

	private double[] calculateStandRes(double[] residual) {
		double[] stdResidual = new double[residual.length];
		double meanRes = 0;
		for (int i = 0; i < stdResidual.length; i++) {
			meanRes = meanRes + residual[i];
		}
		meanRes = meanRes / stdResidual.length;

		double diffSuqreRes = 0;
		for (int i = 0; i < stdResidual.length; i++) {
			diffSuqreRes = diffSuqreRes + (residual[i] - meanRes)
					* (residual[i] - meanRes);
		}

		diffSuqreRes = diffSuqreRes / stdResidual.length;

		for (int i = 0; i < stdResidual.length; i++) {
			stdResidual[i] = (residual[i] - meanRes) / Math.sqrt(diffSuqreRes);
		}
		return stdResidual;
	}

	public void calculateWeightedCorrObsYAndPredY(double[] x, double[] y) {
		// int row = Ax.length;

		PkPdInfo pkpdInstance = PkPdInfo.createPKPDInstance();
		double Ra = 0;
		// System.out.println("########### In correlation coefficient calculation");
		int row = x.length;

		for (int i = 0; i < y.length; i++) {
			if (y[i] > 0)
				y[i] = Math.log(y[i]);
			else
				y[i] = 0;
		}

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

		dataStructList.weightedCorrObsAndPredY = (sumW * sumWXY - sumWX * sumWY)
				/ (Math.sqrt(sumW * sumWX2 - sumWX * sumWX) * Math.sqrt(sumW
						* sumWY2 - sumWY * sumWY));

	}

	private void calculateCorrObsYAndPredY(double[] obsYForCorr,
			double[] predYForCorr) {

		double[] Ax = obsYForCorr;
		double[] Bx = predYForCorr;

		int row = Ax.length;

		for (int i = 0; i < Bx.length; i++) {
			if (Bx[i] > 0)
				Bx[i] = Math.log(Bx[i]);
			else
				Bx[i] = 0;
		}

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

		dataStructList.corrObsAndPredY = ((row * SumABx) - (SumAx * SumBx))
				/ (t1 * t2);

	}

	double[] generateDataAndPlotWeightedPredYAndResidualY(int ii,
			int numberOfSortVariable, double[] residual, double[][] scaledWts)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[] time;
		double[] conc;
		String profile = "";
		if (numberOfSortVariable != 0)
			for (int k = 0; k < numberOfSortVariable; k++) {
				profile = profile
						+ (pkPdInst.sortVariables[k] + " = "
								+ dataSortVariables[ii][k] + ", ");

			}

		time = new double[subjectObsNos[ii]];
		conc = new double[subjectObsNos[ii]];
		for (int k = 0; k < subjectObsNos[ii]; k++) {
			time[k] = pkPdInst.X[k][0];
			conc[k] = pkPdInst.Y[k][0];

		}

		double[] weightedPredictedY = new double[subjectObsNos[ii]];
		double[] weightedResidualY = new double[subjectObsNos[ii]];

		for (int count = 0; count < subjectObsNos[ii]; count++) {
			weightedPredictedY[count] = dataStructList.calcYval[count]
					* scaledWts[count][count];

			weightedResidualY[count] = residual[count]
					* scaledWts[count][count];
		}

		generatePlotOutput(time, conc, dataStructList.calcYval, profile,
				weightedPredictedY, weightedResidualY);
		return time;
	}

	void writingRequiredIterationToOutput() throws RowsExceededException,
			WriteException, BiffException, IOException {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();

		pkpdInst.workSheetOutputInst.getTextoutput().add(
				"\r\n\r\n"
						+ StringUtils.rightPad("", 5)
						+ StringUtils
								.rightPad("Number of iterations taken", 52)

						+ StringUtils.rightPad(":" + pkpdInst.requiredIter, 10)
						+ "\r\n");

		String[] forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkpdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "Required Iteration";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkpdInst.requiredIter
				+ "";

		pkpdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);
	}

	void wrinitingSrsAndWRSSToTextArea(double[] parameter, double Combined_srs,
			double WRSS) throws RowsExceededException, WriteException,
			BiffException, IOException {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();

		if (pkpdInst.ifRoundingOff != 0) {
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					"Combined SRS = "
							+ pkpdInst.formatting(Combined_srs, false));
			pkpdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

		}

		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5)
						+ StringUtils.rightPad(
								"Sum of Weighted Squared Residuals", 43)

						+ StringUtils.rightPad(":"
								+ pkpdInst.convertToScientificNumber(WRSS), 10)
						+ "\r\n");

	}

	void writingSummaryDataToSummaryTable(double[] X_val, double[] Y_val,
			double[][] scaledWts, double error, double[] sdPred,
			double[] stdResidual,

			int idx, int r, String[] s1) throws RowsExceededException,
			WriteException, BiffException, IOException {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();

		if (pkPdInst.analysisType.equals("pk")
				|| pkPdInst.analysisType.equals("pd")
				|| pkPdInst.analysisType.equals("mm")) {
			s1[noOfSortVariables] = pkPdInst.formatting(X_val[r], true);
			s1[noOfSortVariables + 1] = pkPdInst.formatting(Y_val[r], false);
			s1[noOfSortVariables + 2] = pkpdInst.formatting(
					dataStructList.calcYval[r], false);
			s1[noOfSortVariables + 3] = pkpdInst.formatting(error, false);
			s1[noOfSortVariables + 4] = pkpdInst.formatting(scaledWts[r][r],
					false);
			s1[noOfSortVariables + 5] = pkpdInst.formatting(sdPred[r], false);
			s1[noOfSortVariables + 6] = pkpdInst.formatting(stdResidual[r],
					false);

		} else if (pkPdInst.analysisType.equals("irm")
				|| pkPdInst.analysisType.equals("pkpdlink")) {
			s1[noOfSortVariables] = pkPdInst.formatting(X_val[r], true);
			s1[noOfSortVariables + 1] = pkPdInst.formatting(Y_val[r], false);
			s1[noOfSortVariables + 2] = pkpdInst.formatting(
					dataStructList.concForLinkModel[r][0], false);
			s1[noOfSortVariables + 3] = pkpdInst.formatting(
					dataStructList.calcYval[r], false);
			s1[noOfSortVariables + 4] = pkpdInst.formatting(error, false);
			s1[noOfSortVariables + 5] = pkpdInst.formatting(scaledWts[r][r],
					false);
			s1[noOfSortVariables + 6] = pkpdInst.formatting(sdPred[r], false);
			s1[noOfSortVariables + 7] = pkpdInst.formatting(stdResidual[r],
					false);
		}

		pkpdInst.summaryTableAL.add(s1);
	}

	void writingSummaryDataToPredictedValTable(double[] X_val, double[] Y_val,
			double[][] scaledWts, double error, double[] sdPred,
			double[] stdResidual, int idx, int r, String[] s)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		s[noOfSortVariables] = 1 + "";
		s[noOfSortVariables + 1] = pkpdInst.formatting(X_val[r], true);
		s[noOfSortVariables + 2] = pkpdInst.formatting(Y_val[r], false);
		s[noOfSortVariables + 3] = pkpdInst.formatting(
				dataStructList.calcYval[r], false)
				+ "";
		s[noOfSortVariables + 4] = pkpdInst.formatting(error, false);
		s[noOfSortVariables + 5] = pkpdInst.formatting(scaledWts[r][r], false);
		pkpdInst.predictedValueAL.add(s);
	}

	void writingSummaryDataToTextArea(double[] X_val, double[] Y_val,
			double[][] scaledWts, double error, double[] sdPred,
			double[] stdResidual, int idx, int r) throws RowsExceededException,
			WriteException, BiffException, IOException {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5)
						+ StringUtils.rightPad(pkpdInst.formatting(X_val[r],
								true), 10)
						+ "\t"
						+ StringUtils.rightPad(pkpdInst.formatting(Y_val[r],
								false), 10)
						+ "\t"
						+ StringUtils.rightPad(pkpdInst.formatting(
								dataStructList.calcYval[r], false), 10)
						+ "\t"
						+ StringUtils.rightPad(pkpdInst
								.formatting(error, false), 10)
						+ "\t"
						+ StringUtils.rightPad(pkpdInst.formatting(
								scaledWts[r][r], false), 10)
						+ "\t"
						+ StringUtils.rightPad(pkpdInst.formatting(sdPred[r],
								false), 10)
						+ "\t"
						+ StringUtils.rightPad(pkpdInst.formatting(
								stdResidual[r], false), 10) + "\r\n");

	}

	void writeHeaderForSummaryTable() throws RowsExceededException,
			WriteException, BiffException, IOException {

		pkPdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 40)

				+ StringUtils.rightPad("SUMMARY OF NOLINEAR ESTIMATION", 20)
						+ "\r\n\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5) + StringUtils.rightPad("X", 10)
						+ "\t" + StringUtils.rightPad("Observed Y", 10) + "\t"
						+ StringUtils.rightPad("Predicted Y", 10) + "\t"
						+ StringUtils.rightPad("Residual", 10) + "\t"
						+ StringUtils.rightPad("Weight", 10) + "\t"
						+ StringUtils.rightPad("SE-Pred", 10) + "\t"
						+ StringUtils.rightPad("Stand Res", 10) + "\r\n");

		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5) + StringUtils.rightPad("", 10)
						+ "\t" + StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\r\n");
	}

	public void misOutpuAndSecondParamCal(double[] parameter, double[][] x,
			double[][] y, double[][] extraData, int[] row, double srs,
			double WRSS, double[] time, int profileNo, int numberOfSortVariable)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		GaussNewton gnInstance = new GaussNewton();
		double[][] A = gnInstance.CalculateA_mat(pkpdInst.parameter,
				dataStructList.infusionTime, dataStructList.dose,
				dataStructList.dosingTime, pkpdInst.X, pkpdInst.Y,
				pkpdInst.extraData, pkpdInst.row, profileNo);
		double condNumber = gnInstance.ConditionNumber(A);
		double[][] A_inv = gnInstance.inverseMatrix(A);

		int NObs = 0;
		for (int i = 0; i < 1; i++) {
			NObs = NObs + pkpdInst.row[i];
		}
		int df = NObs - noOfParam;
		double SD = Math.sqrt(WRSS / df);

		double[][] varienceCovarienceMatrix = computeVarCovarianceMatrix(A_inv,
				SD);

		double[] SE = new double[noOfParam];
		double[] CV = new double[noOfParam];

		writingParametersToTextAndHDT(pkpdInst.parameter,
				dataStructList.initial, A_inv, SD, profileNo, SE, CV);
		fillingUpVarienceCoVarienceMatrixTableAndTextOutput(
				varienceCovarienceMatrix, profileNo);

		fillingUpPartialDerivativeTable(time, numberOfSortVariable, profileNo);
		double[][] C = computeCorrelationMatrix(A_inv);
		fillingUpCorrelationMatrix(profileNo, C);

		pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("No. of Observations", 23) + "\t"
						+ StringUtils.rightPad(":" + NObs, 10) + "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("Degrees of Freedom", 23) + "\t"
						+ StringUtils.rightPad(":" + df, 10) + "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("Standard Deviation ", 23)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdInst.formatting(SD, false), 10) + "\r\n");

		double AIC = 1.0 * (NObs) * Math.log(WRSS) + 2.0 * noOfParam;
		double SC = 1.0 * (NObs) * Math.log(WRSS) + noOfParam
				* Math.log(1.0 * (NObs));

		pkpdInst.aicValue[pkpdInst.aicCount] = AIC;

		Matrix A_mat = new Matrix(A);
		int rank = A_mat.rank();
		double trace = A_mat.trace();
		EigenvalueDecomposition ED = A_mat.eig();

		double[] eigenValue = ED.getRealEigenvalues();
		Arrays.sort(eigenValue);

		fillingUpEigenValueDisplayTable(eigenValue);

		writingDiagnosticsToTextArea(condNumber, AIC, SC, rank, trace);

		secondaryParamCalInst = SecondaryParamComputer
				.createSecParamCalInstance();
		secondaryParamCalInst.calculateSecondaryParameter(pkpdInst.parameter,
				pkpdInst.modelNumber, dataStructList.dose[0], A_inv, SD);

		fillingUpDiagnosticsSpreedSheet(srs, WRSS, trace, AIC, SC, condNumber);

	}

	double[][] computeVarCovarianceMatrix(double[][] aInv, double SD) {

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

		int noOfSortVar = pkPdInst.numberOfSortVar;
		String[][] temp = inst
				.retrievingResultsFromAL(pkPdInst.minimizationProcessAL);

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

	double[][] computeCorrelationMatrix(double[][] aInv) {
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

	void writingDiagnosticsToTextArea(double condNumber, double AIC, double SC,
			int rank, double trace) throws RowsExceededException,
			WriteException, BiffException, IOException {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();

		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("Cond #", 23)
						+ StringUtils.rightPad("", 5)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdInst.formatting(condNumber, false), 10)
						+ "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("Rank", 23) + StringUtils.rightPad("", 5)
						+ "\t" + StringUtils.rightPad(":" + rank, 10) + "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("Trace", 23)
						+ StringUtils.rightPad("", 5)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdInst.formatting(trace, false) + "", 10)
						+ "\r\n");

		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("Corr(Obs, Pred)", 23)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdInst.formatting(
										dataStructList.corrObsAndPredY, false),
								10) + "\r\n");

		pkpdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("WtdCorr(Obs, Pred)", 23)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdInst.formatting(
										dataStructList.weightedCorrObsAndPredY,
										false), 10) + "\r\n");

		pkpdInst.workSheetOutputInst.getTextoutput()
				.add(
						StringUtils.rightPad("AIC", 23)
								+ StringUtils.rightPad("", 5)
								+ "\t"
								+ StringUtils.rightPad(":"
										+ pkpdInst.formatting(AIC, false), 10)
								+ "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("SC", 23)
						+ StringUtils.rightPad("", 5)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdInst.formatting(SC, false), 10) + "\r\n");

	}

	void fillingUpInitialParameterTable(double[] parameter,
			double[] lowerBound, double[] upperBound, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		int noOfSortVariables = pkPdInst.numberOfSortVar;
		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(
						"*************************************************"
								+ "**********************************", 80)
						+ "\r\n\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 40)

						+ StringUtils.rightPad(
								"INITIAL PARAMETER VALUE AND BOUNDS", 40)
						+ "\r\n\r\n");

		if (dataStructList.initialValEstimateMethod == 1)
			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 5)

									+ StringUtils
											.rightPad(
													"INITIAL PARAMETER CALCULATION METHOD : Curve Stripping",
													60) + "\r\n\r\n");

		else if (dataStructList.initialValEstimateMethod == 2)
			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 5)
									+ StringUtils
											.rightPad(
													"INITIAL PARAMETER CALCULATION METHOD : Genetic Algorithm",
													60) + "\r\n\r\n");
		else if (dataStructList.initialValEstimateMethod == 3)
			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 5)
									+ StringUtils
											.rightPad(
													"INITIAL PARAMETER CALCULATION METHOD : Grid Search",
													60) + "\r\n\r\n");

		else if (dataStructList.initialValEstimateMethod == 4)
			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 5)
									+ StringUtils
											.rightPad(
													"INITIAL PARAMETER CALCULATION METHOD : Previously Generated",
													60) + "\r\n\r\n");

		else
			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 5)
									+ StringUtils
											.rightPad(
													"INITIAL PARAMETER CALCULATION METHOD : User Defined",
													60) + "\r\n\r\n");

		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5)
						+ StringUtils.rightPad("Parameter", 10) + "\t"
						+ StringUtils.rightPad("Initial Value", 14) + "\t"
						+ StringUtils.rightPad("Lower Bound", 12) + "\t"
						+ StringUtils.rightPad("Upper Bound", 12) + "\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add(
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

			if (dataStructList.ifPropFinalEstimate == true
					&& pkPdInst.currentProfileNumber > 0) {
				s[noOfSortVariables + 1] = pkPdInst.formatting(
						dataStructList.propFinalEstimate[i], false);

				s[noOfSortVariables + 2] = pkPdInst.formatting(0, false);
				s[noOfSortVariables + 3] = pkPdInst.formatting(
						dataStructList.propFinalEstimate[i] * 10, false);
			} else {
				s[noOfSortVariables + 1] = pkPdInst.formatting(parameter[i],
						false);

				if (dataStructList.valueForParameterBoundary == 1) {
					s[noOfSortVariables + 2] = pkPdInst.formatting(
							lowerBound[i], false);
					s[noOfSortVariables + 3] = pkPdInst.formatting(
							upperBound[i], false);

				} else {
					s[noOfSortVariables + 2] = pkPdInst.formatting(0, false);

					s[noOfSortVariables + 3] = pkPdInst.formatting(
							parameter[i] * 10, false);
				}
			}

			pkPdInst.initialParameterAL.add(s);

			if (dataStructList.valueForParameterBoundary == 1) {
				pkPdInst.workSheetOutputInst.getTextoutput()
						.add(
								StringUtils.rightPad("", 5)
										+ StringUtils
												.rightPad(paramName[i], 10)
										+ "\t"
										+ StringUtils.rightPad(
												pkPdInst.formatting(
														parameter[i], false),
												14)
										+ "\t"
										+ StringUtils.rightPad(pkPdInst
												.formatting(lowerBound[i],
														false), 12)
										+ "\t"
										+ StringUtils.rightPad(pkPdInst
												.formatting(upperBound[i],
														false), 12)
										+ "\r\n\r\n");
			} else {
				pkPdInst.workSheetOutputInst.getTextoutput().add(
						StringUtils.rightPad("", 5)
								+ StringUtils.rightPad(paramName[i], 10)
								+ "\t"
								+ StringUtils.rightPad(pkPdInst.formatting(
										parameter[i], false), 14)
								+ "\t"
								+ StringUtils.rightPad(pkPdInst.formatting(0,
										false), 12)
								+ "\t"
								+ StringUtils.rightPad(pkPdInst.formatting(
										10 * parameter[i], false), 12)
								+ "\r\n\r\n");
			}

		}

	}

	void generatePlotOutput(double[] time, double[] conc, double[] calcYval2,
			String profile, double[] weightedPredictedY,
			double[] weightedResidualY) throws RowsExceededException,
			WriteException, BiffException, IOException {
		caGrapkGenerationInst = new CaGraphGenerator();

		caGrapkGenerationInst.plotGeneration(time, conc, calcYval2, profile,
				weightedPredictedY, weightedResidualY);

	}

	void fillingUpCorrelationMatrix(int ii, double[][] C)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		pkPdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						"\r\n\r\n"
								+ StringUtils
										.rightPad(
												"*************************************************"
														+ "*****************************************************************",
												80) + "\r\n\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 40)
						/* + "\t" */
						+ StringUtils.rightPad(
								"CORRELATION MATRIX OF THE ESTIMATES", 10)
						+ "\r\n\r\n");

		String temp = "";

		temp = StringUtils.rightPad("", 5)
				+ StringUtils.rightPad("Parameter", 10) + "\t";

		for (int i = 0; i < pkPdInst.parameter.length; i++) {

			temp = temp + StringUtils.rightPad(paramName[i], 10) + "\t";

		}

		pkPdInst.workSheetOutputInst.getTextoutput().add(temp);

		pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n");
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
							+ StringUtils.rightPad(pkPdInst.formatting(C[i][j],
									false), 10);

					s[noOfSortVariables + 1 + j] = pkPdInst.formatting(C[i][j],
							false);

				} else {

					temp = temp
							+ "\t"
							+ StringUtils.rightPad(pkPdInst.formatting(C[i][j],
									false), 10);

					s[noOfSortVariables + 1 + j] = pkPdInst.formatting(C[i][j],
							false);

				}

			}
			pkPdInst.workSheetOutputInst.getTextoutput().add(temp);
			pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n");
			pkPdInst.correlationMatrixAL.add(s);

		}

	}

	void fillingUpPartialDerivativeTable(double[] time,
			int numberOfSortVariable, int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		for (int f = 0; f < 1; f++) {

			String profile = "";
			if (numberOfSortVariable != 0)
				for (int k = 0; k < numberOfSortVariable; k++) {
					profile = profile
							+ (pkPdInst.sortVariables[k] + " = "
									+ dataSortVariables[profileNo][k] + ", ");
				}

			GaussNewton gnInstance = new GaussNewton();
			double[][] temp_J = gnInstance.Jacobian(pkPdInst.parameter,
					dataStructList.infusionTime, dataStructList.dose,
					dataStructList.dosingTime, pkPdInst.X, pkPdInst.Y,
					pkPdInst.extraData, pkPdInst.delta, f, pkPdInst.row,
					profileNo);
			for (int i = 0; i < pkPdInst.row[f]; i++) {

				String[] s = new String[noOfSortVariables + 1 + noOfParam];
				for (int k = 0; k < noOfSortVariables; k++) {
					s[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];

				}

				s[noOfSortVariables] = pkPdInst.formatting(time[i], false);

				for (int j = 0; j < noOfParam; j++) {

					s[noOfSortVariables + 1 + j] = pkPdInst.formatting(
							temp_J[i][j], false);
				}

				pkPdInst.partialDerivativeAL.add(s);

			}

			caGrapkGenerationInst = new CaGraphGenerator();

			caGrapkGenerationInst.partialDetPlotGeneration(time, temp_J,
					profile, noOfParam);
		}
	}

	void fillingUpVarienceCoVarienceMatrixTableAndTextOutput(
			double[][] varienceCovarienceMatrix, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		calculateSdOfPredConc(varienceCovarienceMatrix);

		pkPdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						"\r\n\r\n"
								+ StringUtils
										.rightPad(
												"*************************************************"
														+ "*****************************************************************",
												80) + "\r\n\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 40)
						/* + "\t" */
						+ StringUtils.rightPad(
								"VARIANCE CO-VARIANCE MATRIX OF THE ESTIMATES",
								10) + "\r\n\r\n");

		String temp = "";

		temp = temp + StringUtils.rightPad("", 5)
				+ StringUtils.rightPad("Parameter", 10) + "\t";

		for (int i = 0; i < pkPdInst.parameter.length; i++) {

			temp = temp + StringUtils.rightPad(paramName[i], 10) + "\t";

		}
		pkPdInst.workSheetOutputInst.getTextoutput().add(temp);

		pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n");
		for (int i = 0; i < noOfParam; i++) {
			String[] s = new String[noOfSortVariables + noOfParam + 1];
			for (int k = 0; k < noOfSortVariables; k++) {
				s[k] = dataSortVariables[ii][k];
			}
			temp = "";
			s[noOfSortVariables] = paramName[i];
			for (int j = 0; j <= i; j++) {
				if (j == 0) {

					s[noOfSortVariables + 1 + j] = pkPdInst
							.convertToScientificNumber(varienceCovarienceMatrix[i][j]);

					temp = temp
							+ StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(paramName[i], 10)
							+ "\t"

							+ StringUtils
									.rightPad(
											pkPdInst
													.convertToScientificNumber(varienceCovarienceMatrix[i][j]),
											10);

				} else {

					s[noOfSortVariables + 1 + j] = pkPdInst
							.convertToScientificNumber(varienceCovarienceMatrix[i][j]);

					temp = temp
							+ "\t"

							+ StringUtils
									.rightPad(
											pkPdInst
													.convertToScientificNumber(varienceCovarienceMatrix[i][j]),
											10);

				}

			}
			pkPdInst.workSheetOutputInst.getTextoutput().add(temp);
			pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n");
			pkPdInst.varienceCovarienceMatrixAL.add(s);

		}
	}

	private void calculateSdOfPredConc(double[][] varienceCovarienceMatrix) {

	}

	void writingParametersToTextAndHDT(double[] parameter, double[] initial,
			double[][] aInv, double sD, int ii, double[] CV, double[] SE)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		printParamValInst = new EstimatedParamValStorer();

		printParamValInst.parameterValuePrinting(pkPdInst.parameter, initial,
				aInv, sD);

	}

	void fillingUpDosingDisplayTable() throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] forDosingSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDosingSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
		}

		forDosingSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDosingSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
		}

		forDosingSpreedSheet[noOfSortVariables] = "Number of Doses";
		forDosingSpreedSheet[noOfSortVariables + 1] = dataStructList.dose.length
				+ "";
		pkPdInst.dosingValueAL.add(forDosingSpreedSheet);

		for (int i = 0; i < dataStructList.dose.length; i++) {
			forDosingSpreedSheet = new String[noOfSortVariables + 2];
			for (int k = 0; k < noOfSortVariables; k++) {
				forDosingSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
			}

			forDosingSpreedSheet[noOfSortVariables] = "Dose No: " + (i + 1);
			forDosingSpreedSheet[noOfSortVariables + 1] = pkPdInst.formatting(
					dataStructList.dose[i], false);
			pkPdInst.dosingValueAL.add(forDosingSpreedSheet);

			forDosingSpreedSheet = new String[noOfSortVariables + 2];
			for (int k = 0; k < noOfSortVariables; k++) {
				forDosingSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
			}

			forDosingSpreedSheet[noOfSortVariables] = "Dosing Time of Dose No: "
					+ (i + 1);
			forDosingSpreedSheet[noOfSortVariables + 1] = pkPdInst.formatting(
					dataStructList.dosingTime[i], false);
			pkPdInst.dosingValueAL.add(forDosingSpreedSheet);

			if (dataStructList.infusionTime[i] != 0) {
				forDosingSpreedSheet = new String[noOfSortVariables + 2];
				for (int k = 0; k < noOfSortVariables; k++) {
					forDosingSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
				}

				forDosingSpreedSheet[noOfSortVariables] = "End Time of Dose No: "
						+ (i + 1);
				forDosingSpreedSheet[noOfSortVariables + 1] = pkPdInst
						.formatting(dataStructList.infusionTime[i], false);
				pkPdInst.dosingValueAL.add(forDosingSpreedSheet);
			}

		}

	}

	void fillingUpUserSettingsTable() throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] forUserSettingsSpreedSheet = new String[1];

		forUserSettingsSpreedSheet[0] = "Model Number:"
				+ dataStructList.modelNumber;
		pkPdInst.userSettingsAL.add(forUserSettingsSpreedSheet);

		forUserSettingsSpreedSheet = new String[1];
		if (pkPdInst.methodNo == 1)
			forUserSettingsSpreedSheet[0] = "Method used: Gauss-Newton";
		else if (pkPdInst.methodNo == 2)
			forUserSettingsSpreedSheet[0] = "Method used: Nelder-Mead";
		else if (pkPdInst.methodNo == 3)
			forUserSettingsSpreedSheet[0] = "Method used: Levenberg- Hartley";

		pkPdInst.userSettingsAL.add(forUserSettingsSpreedSheet);

		forUserSettingsSpreedSheet = new String[1];
		forUserSettingsSpreedSheet[0] = "Convergence Criteria:"
				+ pkPdInst.convergence;
		pkPdInst.userSettingsAL.add(forUserSettingsSpreedSheet);

		forUserSettingsSpreedSheet = new String[1];
		forUserSettingsSpreedSheet[0] = "Iteration:" + pkPdInst.iter;
		pkPdInst.userSettingsAL.add(forUserSettingsSpreedSheet);

	}

	void writingHeaderToTextOutput(String stringTime)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		String[] s11 = new String[2];
		s11[0] = stringTime.substring(0, 10);
		s11[1] = stringTime.substring(11, 19);

		boolean ifSimulation = pkPdInst.ifSimulation;

		if (ifSimulation == true) {
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput()
					.add(
							StringUtils.rightPad(" ", 100) + "\t"
									+ StringUtils.rightPad("DATE", 5) + "\t"
									+ StringUtils.rightPad(s11[0], 30) + "\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput().add(
							StringUtils.rightPad(" ", 100) + "\t"
									+ StringUtils.rightPad("TIME", 5) + "\t"
									+ StringUtils.rightPad(s11[1], 30)
									+ "\r\n\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput()
					.add(
							StringUtils.rightPad(" ", 30)
									+ "\t"
									+ StringUtils
											.rightPad(
													"TATA CONSULTANCY SERVICES LTD",
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
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput().add(
							StringUtils.rightPad(" ", 40)
									+ "\t"
									+ StringUtils.rightPad(
											"VERSION 1.0 30SEP 2012", 25)
									+ "\r\n\r\n\r\n");

		} else {

			pkPdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad(" ", 30)
							+ "\t"
							+ StringUtils.rightPad(
									"TATA CONSULTANCY SERVICES LTD", 30)
							+ "\r\n");
			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad(" ", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													"DRUG DEVELOPMENT PLATFORM, COMPARTMENTAL ANALYSIS",
													55) + "\r\n");
			pkPdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad(" ", 40)
							+ "\t"
							+ StringUtils
									.rightPad("VERSION 1.0 30SEP 2012", 25)
							+ "\r\n\r\n\r\n");
		}

	}

	void writingInputToTextOutput() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ModelRelatedInformations mRI = new ModelRelatedInformations();
		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(" ", 5)
						+ StringUtils
								.rightPad("Listing of Input Commands ", 40)
						+ "\r\n\r\n");

		if (pkPdInst.analysisType.equals("pd")) {
			pkPdInst.workSheetOutputInst.getTextoutput().add(

					StringUtils.rightPad("MODEL TYPE", 20) + "\t"
							+ StringUtils.rightPad(":" + "PD MOdel", 50)
							+ "\r\n");
		} else {

			pkPdInst.workSheetOutputInst.getTextoutput().add(

					StringUtils.rightPad("MODEL TYPE", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Differential Equation Model", 50)
							+ "\r\n");

		}

		pkPdInst.workSheetOutputInst
				.getTextoutput()
				.add(

						StringUtils.rightPad("MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(dataStructList.modelNumber - 1),
												50) + "\r\n");

		pkPdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("NUMBER OF VARIABLES", 20) + "\t"
						+ StringUtils.rightPad(":" + 2, 50) + "\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("NUMBER OF PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad(":" + noOfParam, 50) + "\r\n");

		pkPdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("X COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + pkPdInst.xColumnIndex, 50)
						+ "\r\n");

		pkPdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("Y COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + pkPdInst.yColumnIndex, 50)
						+ "\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("CONVERGENCE CRITERIA", 20) + "\t"
						+ StringUtils.rightPad(":" + pkPdInst.convergence, 50)
						+ "\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("ITERATION NUMBER", 20) + "\t"
						+ StringUtils.rightPad(":" + pkPdInst.iter, 50)
						+ "\r\n");

		if (pkPdInst.methodNo == 1)
			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(

							StringUtils.rightPad("MINIMIZATION METHOD", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													":"
															+ "Gauss Newton Method (Levenberg-Marquardt Algorithm)",
													50) + "\r\n");

		else if (pkPdInst.methodNo == 2)
			pkPdInst.workSheetOutputInst.getTextoutput().add(

					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":" + "Nelder Mead Method",
									50) + "\r\n");

		else if (pkPdInst.methodNo == 3)
			pkPdInst.workSheetOutputInst.getTextoutput().add(

					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Gauss Newton Method (Hartley)", 50)
							+ "\r\n");

		if (pkPdInst.wtScheme == 0)
			pkPdInst.workSheetOutputInst.getTextoutput().add(

					StringUtils.rightPad("WEIGHTING", 20) + "\t"
							+ StringUtils.rightPad(":" + "Observed", 50)
							+ "\r\n");

		else
			pkPdInst.workSheetOutputInst.getTextoutput().add(

					StringUtils.rightPad("WEIGHTING", 20) + "\t"
							+ StringUtils.rightPad(":" + "Predicted", 50)
							+ "\r\n");
		double temp1 = ((-1) * pkPdInst.wtExp);
		pkPdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("WEIGHTING EXPONENT", 20) + "\t"
						+ StringUtils.rightPad(":" + temp1, 50) + "\r\n");

	}

	void fillingUpEigenValueDisplayTable(double[] eigenValue)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		for (int i = 0; i < eigenValue.length; i++) {
			String[] s = new String[noOfSortVariables + 2];
			for (int k = 0; k < noOfSortVariables; k++) {
				s[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
			}

			s[noOfSortVariables] = (i + 1) + "";
			s[noOfSortVariables + 1] = pkPdInst
					.formatting(eigenValue[i], false)
					+ "";
			pkPdInst.eigenValuesAL.add(s);

		}

	}

	void fillingUpHistoryDisplayTable(String string)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[] s = new String[7];
		s[0] = string.substring(0, 10);
		s[1] = string.substring(11, 19);
		s[2] = System.getProperty("user.name");
		s[3] = "";
		s[4] = "";
		s[5] = "CA Analysis completed successfully";
		s[6] = "";

		pkPdInst.historyAL.add(s);
	}

	void fillingUpDiagnosticsSpreedSheet(double srs, double WRSS, double trace,
			double AIC, double SC, double condNumber)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[] forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "SRS";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkPdInst.formatting(
				srs, false);

		pkPdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "WSRS";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkPdInst.formatting(
				WRSS, false);

		pkPdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "Trace";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkPdInst.formatting(
				trace, false);

		pkPdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "Corr(Obs, Pred)";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkPdInst.formatting(
				dataStructList.corrObsAndPredY, false);

		pkPdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "WtdCorr(Obs, Pred)";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkPdInst.formatting(
				dataStructList.weightedCorrObsAndPredY, false);

		pkPdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "AIC";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkPdInst.formatting(
				AIC, false);

		pkPdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "SC";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkPdInst.formatting(
				SC, false);

		pkPdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "Cond Number";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkPdInst.formatting(
				condNumber, false);

		pkPdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);
	}

}
