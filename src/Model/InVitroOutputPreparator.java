package Model;

import java.io.IOException;
import java.util.Arrays;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;
import Jama.CholeskyDecomposition;
import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class InVitroOutputPreparator {

	public InVitroOutputPreparator() {
		pkPdInst = PkPdInfo.createPKPDInstance();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();

		noOfParam = pkPdInst.noOfParam;

		noOfSortVariables = pkPdInst.numberOfSortVar;

		subjectObsNos = dataStructInst.subjectObsNos;

		dataSortVariables = pkPdInst.dataSortVariables;

		paramName = pkPdInst.paramName;
	}

	ProcessingInputsInfo procInputInst;
	PkPdInfo pkPdInst;
	ListOfDataStructures dataStructInst;
	EstimatedParamValStorer paramValueStorerInst;

	int noOfParam;
	int noOfSortVariables;
	ApplicationInfo appInfo;
	int[] subjectObsNos;
	int noOfFunction;
	String[][] dataSortVariables;
	String[] paramName;

	CaGraphGenerator caGrapkGenerationInst;
	

	void writingHeaderToTextOutput(String stringTime)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		String[] s11 = new String[2];
		s11[0] = stringTime.substring(0, 10);
		s11[1] = stringTime.substring(11, 19);

		pkPdInst = PkPdInfo.createPKPDInstance();
		procInputInst = pkPdInst.copyProcessingInput();

		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(" ", 100) + "\t"
						+ StringUtils.rightPad("DATE", 5) + "\t"
						+ StringUtils.rightPad(s11[0], 30) + "\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(" ", 100) + "\t"
						+ StringUtils.rightPad("TIME", 5) + "\t"
						+ StringUtils.rightPad(s11[1], 30) + "\r\n\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(" ", 30)
						+ "\t"
						+ StringUtils.rightPad("TATA CONSULTANCY SERVICES LTD",
								30) + "\r\n");
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
				StringUtils.rightPad(" ", 40) + "\t"
						+ StringUtils.rightPad("VERSION 1.0 30SEP 2012", 25)
						+ "\r\n\r\n\r\n");
	}

	
	void createAndPrintingOfSummaryAndSummaryTable(double[] parameter,
			double[][] x, double[][] y, double[][] extraData, int[] row,
			int profileNo, int numberOfSortVariable)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		GaussNewton gnInstance = new GaussNewton();

		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();

		noOfFunction = x[0].length;

		double[] srs = new double[noOfFunction];
		double Combined_srs = 0;

		double[] time = new double[subjectObsNos[profileNo]];
		double[] conc = new double[subjectObsNos[profileNo]];
		double[] obsYForCorr = new double[subjectObsNos[profileNo]];
		double[] predYForCorr = new double[subjectObsNos[profileNo]];

		double[] obsYForWtCorr = new double[subjectObsNos[profileNo]];
		double[] predYForWtCorr = new double[subjectObsNos[profileNo]];

		writeHeaderForSummaryTable();

		double[] residual = new double[subjectObsNos[profileNo]];
		double WRSS = gnInstance.Objective(parameter,
				dataStructInst.infusionTime, dataStructInst.dose,
				dataStructInst.dosingTime, pkPdInst.X, pkPdInst.Y,
				pkPdInst.extraData, pkPdInst.row, profileNo);

		for (int i = 0; i < x[0].length; i++) {

			double[] X_val = new double[subjectObsNos[profileNo]];
			double[] Y_val = new double[subjectObsNos[profileNo]];

			dataStructInst.calcYval = new double[subjectObsNos[profileNo]];
			double[][] scaledWts = new double[subjectObsNos[profileNo]][subjectObsNos[profileNo]];
			scaledWts = gnInstance.ScaledWeights(parameter,
					dataStructInst.infusionTime, dataStructInst.dose,
					dataStructInst.dosingTime, pkPdInst.X, pkPdInst.Y,
					pkPdInst.extraData, i, pkPdInst.row, profileNo);
			double[][] Error = new double[subjectObsNos[profileNo]][1];
			Error = gnInstance.error(parameter, pkPdInst.X, pkPdInst.Y,
					pkPdInst.extraData, i, pkPdInst.row,
					dataStructInst.infusionTime, dataStructInst.dose,
					dataStructInst.dosingTime, profileNo);
			double error = 0;

			for (int rr = 0; rr < subjectObsNos[profileNo]; rr++) {
				X_val[rr] = pkPdInst.X[rr][0];
				Y_val[rr] = pkPdInst.Y[rr][0];
				obsYForCorr[rr] = pkPdInst.Y[rr][0];
				obsYForWtCorr[rr] = pkPdInst.Y[rr][0];
			}
			int idx = 0;

			AsciiParameterEstimator asciiEvluationInst = AsciiParameterEstimator.createAsciiParamEstimationInst();

			for (int r = 0; r < pkPdInst.row[i]; r++) {
				double x1 = pkPdInst.X[r][i];
				dataStructInst.calcYval[r] = asciiEvluationInst.evaluateCode(
						parameter, x1, i, profileNo, true);

				predYForCorr[r] = dataStructInst.calcYval[r];
				predYForWtCorr[r] = dataStructInst.calcYval[r];
				X_val[r] = pkPdInst.X[r][i];
				Y_val[r] = pkPdInst.Y[r][i];

				if (Y_val[r] == 999999.9) {

				} else {
					error = Y_val[r] - dataStructInst.calcYval[r];

					double SqRes = Math.pow(Error[idx][0], 2);
					srs[i] = srs[i] + SqRes;
					idx = idx + 1;
				}

				residual[r] = error;

			}

			pkPdInst = PkPdInfo.createPKPDInstance();
			CalculateResidual calResInst = new CalculateResidual();
			calResInst.residualCalculation(pkPdInst.parameter,
					dataStructInst.infusionTime, dataStructInst.dose,
					dataStructInst.dosingTime, pkPdInst.X, pkPdInst.Y,
					pkPdInst.extraData, pkPdInst.delta, i, pkPdInst.row,
					profileNo);

			double[] stdResidual = pkPdInst.stdResidual;
			double[] sdPred = pkPdInst.sePredy;

			int df = X_val.length - noOfParam;
			double SD = Math.sqrt(WRSS / df);

			for (int r = 0; r < pkPdInst.row[i]; r++) {

				String[] s = new String[noOfSortVariables + 6];
				for (int k = 0; k < noOfSortVariables; k++) {
					s[k] = dataSortVariables[profileNo][k];
				}

				String[] s1 = new String[noOfSortVariables + 7];
				for (int k = 0; k < noOfSortVariables; k++) {
					s1[k] = dataSortVariables[profileNo][k];
				}
				writingSummaryDataToTextArea(X_val, Y_val, scaledWts,
						residual[r], sdPred, stdResidual, idx, r);

				writingSummaryDataToSummaryTable(X_val, Y_val, scaledWts,
						residual[r], sdPred, stdResidual, idx, r, s1);

			}

			writingSummaryDataToPredictedValTable(X_val, profileNo);
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

	public void misOutpuAndSecondParamCal(double[] parameter, double[][] x,
			double[][] y, double[][] extraData, int[] row, double srs,
			double WRSS, double[] time, int profileNo, int numberOfSortVariable)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		GaussNewton gnInstance = new GaussNewton();
		double[][] A = gnInstance.CalculateA_mat(pkpdInst.parameter,
				dataStructInst.infusionTime, dataStructInst.dose,
				dataStructInst.dosingTime, pkpdInst.X, pkpdInst.Y,
				pkpdInst.extraData, pkpdInst.row, profileNo);
		double condNumber = gnInstance.ConditionNumber(A);
		double[][] A_inv = gnInstance.inverseMatrix(A);

		int NObs = 0;
		for (int i = 0; i < 1; i++) {
			NObs = NObs + pkpdInst.row[i];
		}
		int df = NObs - noOfParam;
		pkpdInst.setDegreesOfFreedom(df);
		
		double SD = Math.sqrt(WRSS / df);

		double[][] varienceCovarienceMatrix = computeVarCovarianceMatrix(A_inv,
				SD);

		double[] SE = new double[noOfParam];
		double[] CV = new double[noOfParam];

		writingParametersToTextAndHDT(pkpdInst.parameter,
				dataStructInst.initial, A_inv, SD, profileNo, SE, CV);
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

		

		fillingUpDiagnosticsSpreedSheet(srs, WRSS, trace, AIC, SC, condNumber);

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
										dataStructInst.corrObsAndPredY, false),
								10) + "\r\n");

		pkpdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("WtdCorr(Obs, Pred)", 23)
						+ "\t"
						+ StringUtils.rightPad(":"
								+ pkpdInst.formatting(
										dataStructInst.weightedCorrObsAndPredY,
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
					dataStructInst.infusionTime, dataStructInst.dose,
					dataStructInst.dosingTime, pkPdInst.X, pkPdInst.Y,
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

		// calculateSdOfPredConc(varienceCovarienceMatrix);

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

	void writingParametersToTextAndHDT(double[] parameter, double[] initial,
			double[][] aInv, double sD, int ii, double[] CV, double[] SE)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		paramValueStorerInst = new EstimatedParamValStorer();

		paramValueStorerInst.parameterValuePrinting(pkPdInst.parameter,
				initial, aInv, sD);

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

	double[] generateDataAndPlotWeightedPredYAndResidualY(int profileNo,
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
								+ dataSortVariables[profileNo][k] + ", ");

			}

		time = new double[subjectObsNos[profileNo]];
		conc = new double[subjectObsNos[profileNo]];
		for (int k = 0; k < subjectObsNos[profileNo]; k++) {
			time[k] = pkPdInst.X[k][0];
			conc[k] = pkPdInst.Y[k][0];

		}

		double[] weightedPredictedY = new double[subjectObsNos[profileNo]];
		double[] weightedResidualY = new double[subjectObsNos[profileNo]];

		for (int count = 0; count < subjectObsNos[profileNo]; count++) {
			weightedPredictedY[count] = dataStructInst.calcYval[count]
					* scaledWts[count][count];

			weightedResidualY[count] = residual[count]
					* scaledWts[count][count];
		}

		generatePlotOutput(time, conc, dataStructInst.calcYval, profile,
				weightedPredictedY, weightedResidualY);
		return time;
	}

	void generatePlotOutput(double[] time, double[] conc, double[] calcYval2,
			String profile, double[] weightedPredictedY,
			double[] weightedResidualY) throws RowsExceededException,
			WriteException, BiffException, IOException {
		caGrapkGenerationInst = new CaGraphGenerator();

		caGrapkGenerationInst.plotGeneration(time, conc, calcYval2, profile,
				weightedPredictedY, weightedResidualY);

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

		dataStructInst.weightedCorrObsAndPredY = (sumW * sumWXY - sumWX * sumWY)
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

		dataStructInst.corrObsAndPredY = ((row * SumABx) - (SumAx * SumBx))
				/ (t1 * t2);

	}

	private void writingSummaryDataToPredictedValTable(double[] xVal, int ii) {
		pkPdInst = PkPdInfo.createPKPDInstance();

		double stepLength = (xVal[xVal.length - 1] - xVal[0])
				/ pkPdInst.noOfPredval;
		double predVal;
		double xValForPred = xVal[0];
		PdCurveStripper csInst = new PdCurveStripper();
		for (int i = 0; i < pkPdInst.noOfPredval; i++) {
			xValForPred = xValForPred + stepLength;
			predVal = csInst.linearInterpolation(xVal, dataStructInst.calcYval,
					xValForPred);

			String[] s = new String[noOfSortVariables + 6];
			for (int k = 0; k < noOfSortVariables; k++) {
				s[k] = dataSortVariables[ii][k];
			}

			s[noOfSortVariables] = 1 + "";
			s[noOfSortVariables + 1] = pkPdInst.formatting(xValForPred, true);
			s[noOfSortVariables + 2] = pkPdInst.formatting(predVal, false);

			pkPdInst.predictedValueAL.add(s);
		}

	}

	void writingSummaryDataToSummaryTable(double[] X_val, double[] Y_val,
			double[][] scaledWts, double error, double[] sdPred,
			double[] stdResidual,

			int idx, int r, String[] s1) throws RowsExceededException,
			WriteException, BiffException, IOException {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();

		s1[noOfSortVariables] = pkPdInst.formatting(X_val[r], true);
		s1[noOfSortVariables + 1] = pkPdInst.formatting(Y_val[r], false);
		s1[noOfSortVariables + 2] = pkpdInst.formatting(
				dataStructInst.calcYval[r], false);
		s1[noOfSortVariables + 3] = pkpdInst.formatting(error, false);
		s1[noOfSortVariables + 4] = pkpdInst.formatting(scaledWts[r][r], false);
		s1[noOfSortVariables + 5] = pkpdInst.formatting(sdPred[r], false);
		s1[noOfSortVariables + 6] = pkpdInst.formatting(stdResidual[r], false);

		pkpdInst.summaryTableAL.add(s1);
	}

	void writingSummaryDataToTextArea(double[] X_val, double[] Y_val,
			double[][] scaledWts, double error, double[] sdPred,
			double[] stdResidual, int idx, int r) throws RowsExceededException,
			WriteException, BiffException, IOException {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();

		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5)
						+ StringUtils.rightPad(pkpdInst.formatting(X_val[r],
								true), 10)
						+ "\t"
						+ StringUtils.rightPad(pkpdInst.formatting(Y_val[r],
								false), 10)
						+ "\t"
						+ StringUtils.rightPad(pkpdInst.formatting(
								dataStructInst.calcYval[r], false), 10)
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

	void fillingUpHistoryDisplayTable(String string)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		pkPdInst = PkPdInfo.createPKPDInstance();

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

		pkPdInst = PkPdInfo.createPKPDInstance();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();

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
				dataStructInst.corrObsAndPredY, false);

		pkPdInst.diagnosticsAL.add(forDiagnosticsSpreedSheet);

		forDiagnosticsSpreedSheet = new String[noOfSortVariables + 2];
		for (int k = 0; k < noOfSortVariables; k++) {
			forDiagnosticsSpreedSheet[k] = dataSortVariables[pkPdInst.currentProfileNumber][k];
		}

		forDiagnosticsSpreedSheet[noOfSortVariables] = "WtdCorr(Obs, Pred)";
		forDiagnosticsSpreedSheet[noOfSortVariables + 1] = pkPdInst.formatting(
				dataStructInst.weightedCorrObsAndPredY, false);

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

	void fillingUpInitialParameterTable(int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		
		pkPdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkPdInst.copyProcessingInput();
		
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

		if (procInputInst.getModelInputTab2Obj()
				.getInitialParamByCS() == 1)
			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 5)

									+ StringUtils
											.rightPad(
													"INITIAL PARAMETER CALCULATION METHOD : Curve Stripping",
													60) + "\r\n\r\n");

		else if (procInputInst.getModelInputTab2Obj()
				.getInitialParamBYGA() == 1)
			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 5)
									+ StringUtils
											.rightPad(
													"INITIAL PARAMETER CALCULATION METHOD : Genetic Algorithm",
													60) + "\r\n\r\n");
		
		else if (procInputInst.getModelInputTab2Obj()
				.getInitialParamBYGS() == 1)
			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 5)
									+ StringUtils
											.rightPad(
													"INITIAL PARAMETER CALCULATION METHOD : Grid Search",
													60) + "\r\n\r\n");
		else if (procInputInst.getModelInputTab2Obj()
				.getPreviouslyGenInitVal() == 1)
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
			
			if(procInputInst.getModelInputTab2Obj()
					.isPropagateFinalEstimae() == true && pkPdInst.currentProfileNumber >0 )
			{
				s[noOfSortVariables + 1] = pkPdInst.formatting(
						dataStructInst.propFinalEstimate[i], false);
				
				s[noOfSortVariables + 2] = pkPdInst.formatting(
						0, false);
				s[noOfSortVariables + 3] = pkPdInst.formatting(
						dataStructInst.propFinalEstimate[i] *10, false);
			}
			else
			{
				s[noOfSortVariables + 1] = pkPdInst.formatting(
						pkPdInst.parameter[i],false);
				
				if (procInputInst.getModelInputTab2Obj().getParamBoundarySelection() == 1) {
					s[noOfSortVariables + 2] = pkPdInst.formatting(
							pkPdInst.lowerBound[i], false);
					s[noOfSortVariables + 3] = pkPdInst.formatting(
							pkPdInst.upperBound[i], false);

				} else {
					s[noOfSortVariables + 2] = pkPdInst.formatting(0,
							false);
					
					s[noOfSortVariables + 3] = pkPdInst.formatting(
							pkPdInst.parameter[i] * 10, false);
				}
			}
			
			

			

			pkPdInst.initialParameterAL.add(s);

			pkPdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(paramName[i], 10)
							+ "\t"
							+ StringUtils.rightPad(pkPdInst
									.formatting(pkPdInst.parameter[i],
											false), 14)
							+ "\t"
							+ StringUtils.rightPad(pkPdInst
									.formatting(pkPdInst.lowerBound[i],
											false), 12)
							+ "\t"
							+ StringUtils.rightPad(pkPdInst
									.formatting(pkPdInst.upperBound[i],
											false), 12)
							+ "\r\n\r\n");

		}

	}

	
	
}
