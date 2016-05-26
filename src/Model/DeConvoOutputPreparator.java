package Model;

import java.io.IOException;
import java.util.ArrayList;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class DeConvoOutputPreparator {

	DeConvolutionVariables deConvoVarInst;

	void initializeResultsAL() {

		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		ProcessingInputsInfo procInputInst = deConvoVarInst
				.copyProcessingInput();

		deConvoVarInst.verticalParametersAL = new ArrayList<String[]>();
		deConvoVarInst.verticalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getVerticalParametersTH());

		deConvoVarInst.horizontalParametersAL = new ArrayList<String[]>();
		deConvoVarInst.horizontalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getHorizontalParametersTH());

		deConvoVarInst.dosingValueAL = new ArrayList<String[]>();
		deConvoVarInst.dosingValueAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getDosingValueTH());

		deConvoVarInst.predictedValueAL = new ArrayList<String[]>();
		deConvoVarInst.predictedValueAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPredictedValueTH());

		deConvoVarInst.summaryTableAL = new ArrayList<String[]>();
		deConvoVarInst.summaryTableAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getSummaryTableTH());

		deConvoVarInst.exclusionAL = new ArrayList<String[]>();
		deConvoVarInst.exclusionAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPartialDerivativeTH());

		deConvoVarInst.userSettingsAL = new ArrayList<String[]>();
		deConvoVarInst.userSettingsAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getUserSettingsTH());

		deConvoVarInst.historyAL = new ArrayList<String[]>();
		deConvoVarInst.historyAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getHistoryTH());

	}

	public void writingHeaderToTextOutput(String stringTime) {

		String[] s11 = new String[2];
		s11[0] = stringTime.substring(0, 10);
		s11[1] = stringTime.substring(11, 19);

		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();

		deConvoVarInst.workSheetOutputInst
				.setTextoutput(new ArrayList<String>());

		deConvoVarInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(" ", 100) + "\t"
						+ StringUtils.rightPad("DATE", 5) + "\t"
						+ StringUtils.rightPad(s11[0], 30) + "\r\n");
		deConvoVarInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(" ", 100) + "\t"
						+ StringUtils.rightPad("TIME", 5) + "\t"
						+ StringUtils.rightPad(s11[1], 30) + "\r\n\r\n");
		deConvoVarInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(" ", 30)
						+ "\t"
						+ StringUtils.rightPad("TATA CONSULTANCY SERVICES LTD",
								30) + "\r\n");
		deConvoVarInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils.rightPad(" ", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												"DRUG DEVELOPMENT PLATFORM, COMPARTMENTAL ANALYSIS",
												55) + "\r\n");
		deConvoVarInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(" ", 40) + "\t"
						+ StringUtils.rightPad("VERSION 1.0 30SEP 2012", 25)
						+ "\r\n\r\n\r\n");

	}

	/*void writeResultSummary(int profileNo) {
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();

		for (int i = 0; i < deConvoVarInst.time.length; i++) {

			String[] str = new String[deConvoVarInst.noOfSortVar + 5];
			for (int j = 0; j < deConvoVarInst.noOfSortVar; j++) {
				str[j] = deConvoVarInst.sortVarData[profileNo][j];

			}
			str[deConvoVarInst.noOfSortVar] = deConvoVarInst.time[i] + "";
			str[deConvoVarInst.noOfSortVar + 1] = deConvoVarInst.conc[i] + "";
			str[deConvoVarInst.noOfSortVar + 2] = "";
			str[deConvoVarInst.noOfSortVar + 3] = "";
			str[deConvoVarInst.noOfSortVar + 4] = deConvoVarInst.fracAbsorption[i]
					+ "";

			deConvoVarInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(deConvoVarInst.formatting(
									deConvoVarInst.time[i], true), 10)
							+ "\t"
							+ StringUtils.rightPad(deConvoVarInst.formatting(
									deConvoVarInst.conc[i], false), 10)
							+ "\t"

							+ StringUtils.rightPad(deConvoVarInst.formatting(
									deConvoVarInst.fracAbsorption[i], false),
									10) + "\t"

							+ "\r\n");

			deConvoVarInst.summaryTableAL.add(str);
		}
	}
*/
	void writeSpreadSheetOutputs(int profileNo) throws RowsExceededException,
			WriteException, BiffException, IOException {
		writeVerticalFinalParameters(profileNo);
		writeHorizontalFinalParameters(profileNo);

		writeWagNelsonAndLooRiegelResult(profileNo);
		writeSummaryResult(profileNo);

	}

	private void writeVerticalFinalParameters(int profileNo) {

		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();

		String[] paramName = { "Rsq", "Rsq_Adjusted", "LambdaZ",
				"No_Points_LambdaZ" };

		for (int i = 0; i < paramName.length; i++) {

			String[] str = new String[deConvoVarInst.noOfSortVar + 2];
			for (int j = 0; j < deConvoVarInst.noOfSortVar; j++) {
				str[j] = deConvoVarInst.sortVarData[profileNo][j];

			}
			str[deConvoVarInst.noOfSortVar] = paramName[i];

			if (i == 0)
				str[deConvoVarInst.noOfSortVar + 1] = deConvoVarInst
						.formatting(deConvoVarInst.rsq, false);
			else if (i == 1)
				str[deConvoVarInst.noOfSortVar + 1] = deConvoVarInst
						.formatting(deConvoVarInst.adjRsq, false);
			else if (i == 2)
				str[deConvoVarInst.noOfSortVar + 1] = deConvoVarInst
						.formatting(deConvoVarInst.lambdaZ, false);
			else if (i == 3)
				str[deConvoVarInst.noOfSortVar + 1] = deConvoVarInst
						.formatting(deConvoVarInst.noOfPtsLambdaZ, false);

			deConvoVarInst.verticalParametersAL.add(str);
		}
	}

	private void writeHorizontalFinalParameters(int profileNo) {

		String[] str = new String[deConvoVarInst.noOfSortVar + 4];
		for (int j = 0; j < deConvoVarInst.noOfSortVar; j++) {
			str[j] = deConvoVarInst.sortVarData[profileNo][j];

		}

		str[deConvoVarInst.noOfSortVar] = deConvoVarInst.formatting(
				deConvoVarInst.rsq, false);
		str[deConvoVarInst.noOfSortVar + 1] = deConvoVarInst.formatting(
				deConvoVarInst.adjRsq, false);
		str[deConvoVarInst.noOfSortVar + 2] = deConvoVarInst.formatting(
				deConvoVarInst.lambdaZ, false);
		str[deConvoVarInst.noOfSortVar + 3] = deConvoVarInst.formatting(
				deConvoVarInst.noOfPtsLambdaZ, false);

		deConvoVarInst.horizontalParametersAL.add(str);

	}

	private void writeWagNelsonAndLooRiegelResult(int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		writeHeaderForWagNelsonAndLooRiegel();

		if (deConvoVarInst.analysisType.equals("looriegel")) {
			for (int i = 0; i < deConvoVarInst.time.length; i++) {

				String[] str = new String[deConvoVarInst.noOfSortVar + 6];
				for (int j = 0; j < deConvoVarInst.noOfSortVar; j++) {
					str[j] = deConvoVarInst.sortVarData[profileNo][j];

				}
				str[deConvoVarInst.noOfSortVar] = deConvoVarInst.time[i] + "";
				str[deConvoVarInst.noOfSortVar + 1] = deConvoVarInst.conc[i]
						+ "";
				str[deConvoVarInst.noOfSortVar + 2] = deConvoVarInst.auc[i]
						+ "";
				str[deConvoVarInst.noOfSortVar + 3] = deConvoVarInst.amtPeripheralV[i]
						+ "";
				str[deConvoVarInst.noOfSortVar + 4] = deConvoVarInst.cumulAmtAbsV[i]
						+ "";
				str[deConvoVarInst.noOfSortVar + 5] = deConvoVarInst.fracAbsorption[i]
						+ "";

				deConvoVarInst.workSheetOutputInst
						.getTextoutput()
						.add(
								StringUtils.rightPad("", 5)
										+ StringUtils.rightPad(deConvoVarInst
												.formatting(
														deConvoVarInst.time[i],
														true), 10)
										+ "\t"
										+ StringUtils.rightPad(deConvoVarInst
												.formatting(
														deConvoVarInst.conc[i],
														false), 10)
										+ "\t"
										+ StringUtils.rightPad(deConvoVarInst
												.formatting(
														deConvoVarInst.auc[i],
														false), 10)
										+ "\t"
										+ StringUtils
												.rightPad(
														deConvoVarInst
																.formatting(
																		deConvoVarInst.amtPeripheralV[i],
																		false),
														10)
										+ "\t"
										+ StringUtils
												.rightPad(
														deConvoVarInst
																.formatting(
																		deConvoVarInst.cumulAmtAbsV[i],
																		false),
														10)
										+ "\t"

										+ StringUtils
												.rightPad(
														deConvoVarInst
																.formatting(
																		deConvoVarInst.fracAbsorption[i],
																		false),
														10) + "\t"

										+ "\r\n");

				deConvoVarInst.predictedValueAL.add(str);
			}
		} else {
			for (int i = 0; i < deConvoVarInst.time.length; i++) {

				String[] str = new String[deConvoVarInst.noOfSortVar + 5];
				for (int j = 0; j < deConvoVarInst.noOfSortVar; j++) {
					str[j] = deConvoVarInst.sortVarData[profileNo][j];

				}
				str[deConvoVarInst.noOfSortVar] = deConvoVarInst.time[i] + "";
				str[deConvoVarInst.noOfSortVar + 1] = deConvoVarInst.conc[i]
						+ "";
				str[deConvoVarInst.noOfSortVar + 2] = deConvoVarInst.auc[i]
						+ "";
				str[deConvoVarInst.noOfSortVar + 3] = deConvoVarInst.cumulAmtAbsV[i]
						+ "";
				str[deConvoVarInst.noOfSortVar + 4] = deConvoVarInst.fracAbsorption[i]
						+ "";

				deConvoVarInst.workSheetOutputInst
						.getTextoutput()
						.add(
								StringUtils.rightPad("", 5)
										+ StringUtils.rightPad(deConvoVarInst
												.formatting(
														deConvoVarInst.time[i],
														true), 10)
										+ "\t"
										+ StringUtils.rightPad(deConvoVarInst
												.formatting(
														deConvoVarInst.conc[i],
														false), 10)
										+ "\t"
										+ StringUtils.rightPad(deConvoVarInst
												.formatting(
														deConvoVarInst.auc[i],
														false), 10)
										+ "\t"
										+ StringUtils
												.rightPad(
														deConvoVarInst
																.formatting(
																		deConvoVarInst.cumulAmtAbsV[i],
																		false),
														10)
										+ "\t"

										+ StringUtils
												.rightPad(
														deConvoVarInst
																.formatting(
																		deConvoVarInst.fracAbsorption[i],
																		false),
														10) + "\t"

										+ "\r\n");

				deConvoVarInst.predictedValueAL.add(str);
			}
		}

	}

	void writeHeaderForWagNelsonAndLooRiegel() throws RowsExceededException,
			WriteException, BiffException, IOException {

		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();

		deConvoVarInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
		deConvoVarInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 40)

				+ StringUtils.rightPad("SUMMARY OF DECONVOLUTON", 20)
						+ "\r\n\r\n");

		if (deConvoVarInst.analysisType.equals("wagnelson")) {
			deConvoVarInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad("Time", 10) + "\t"
							+ StringUtils.rightPad("Conc", 10) + "\t"
							+ StringUtils.rightPad("Auc", 10) + "\t"
							+ StringUtils.rightPad("Cumul_Amt_Abs_V", 10)
							+ "\t"
							+ StringUtils.rightPad("Rel_Fraction_Abs", 10)
							+ "\t");

			deConvoVarInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5) + StringUtils.rightPad("", 10)
							+ "\t" + StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\t");
		} else {
			deConvoVarInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad("Time", 10) + "\t"
							+ StringUtils.rightPad("Conc", 10) + "\t"
							+ StringUtils.rightPad("Auc", 10) + "\t"
							+ StringUtils.rightPad("Amt_Peripheral_V", 10)
							+ "\t"
							+ StringUtils.rightPad("Cumul_Amt_Abs_V", 10)
							+ "\t"
							+ StringUtils.rightPad("Rel_Fraction_Abs", 10)
							+ "\t");

			deConvoVarInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5) + StringUtils.rightPad("", 10)
							+ "\t" + StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\t");
		}

	}

	private void writeSummaryResult(int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		writeHeaderForSummaryTable();

		double predVal = 0;
		String predValStr = "";
		double residual = 0;
		String residualStr = "";
		int weighting = 0;

		ProcessingInputsInfo procInputInst = deConvoVarInst
				.copyProcessingInput();

		for (int i = 0; i < deConvoVarInst.time.length; i++) {

			String[] str = new String[deConvoVarInst.noOfSortVar + 5];
			for (int j = 0; j < deConvoVarInst.noOfSortVar; j++) {
				str[j] = deConvoVarInst.sortVarData[profileNo][j];

			}
			str[deConvoVarInst.noOfSortVar] = deConvoVarInst.time[i] + "";
			str[deConvoVarInst.noOfSortVar + 1] = deConvoVarInst.conc[i] + "";

			// predVal = deConvoVarInst.time[i]* (-1)*deConvoVarInst.lambdaZ
			// +deConvoVarInst.intercept;
			predVal = procInputInst.getLambdazDataobj().getLambdaZDetails()[profileNo]
					.getPredictedConc()[i];

			residual = deConvoVarInst.conc[i] - predVal;

			if (predVal == 0) {
				predValStr = "";
				residualStr = "";
				weighting = 0;

				str[deConvoVarInst.noOfSortVar + 2] = predValStr;
				str[deConvoVarInst.noOfSortVar + 3] = residualStr;
				str[deConvoVarInst.noOfSortVar + 4] = weighting + "";

				deConvoVarInst.workSheetOutputInst.getTextoutput().add(
						StringUtils.rightPad("", 5)
								+ StringUtils.rightPad(deConvoVarInst
										.formatting(deConvoVarInst.time[i],
												true), 10)
								+ "\t"
								+ StringUtils.rightPad(deConvoVarInst
										.formatting(deConvoVarInst.conc[i],
												false), 10) + "\t"
								+ StringUtils.rightPad("", 10) + "\t"
								+ StringUtils.rightPad("", 10) + "\t"
								+ StringUtils.rightPad(weighting + "", 10)
								+ "\t"

								+ "\r\n");

				deConvoVarInst.summaryTableAL.add(str);
			} else {
				residual = deConvoVarInst.conc[i] - predVal;
				predValStr = predVal + "";
				residualStr = residual + "";
				weighting = 1;

				str[deConvoVarInst.noOfSortVar + 2] = predValStr;
				str[deConvoVarInst.noOfSortVar + 3] = residualStr;
				str[deConvoVarInst.noOfSortVar + 4] = weighting + "";

				deConvoVarInst.workSheetOutputInst.getTextoutput().add(
						StringUtils.rightPad("", 5)
								+ StringUtils.rightPad(deConvoVarInst
										.formatting(deConvoVarInst.time[i],
												true), 10)
								+ "\t"
								+ StringUtils.rightPad(deConvoVarInst
										.formatting(deConvoVarInst.conc[i],
												false), 10)
								+ "\t"
								+ StringUtils.rightPad(deConvoVarInst
										.formatting(predVal, false), 10)
								+ "\t"
								+ StringUtils.rightPad(deConvoVarInst
										.formatting(residual, false), 10)
								+ "\t"
								+ StringUtils.rightPad(weighting + "", 10)
								+ "\t"

								+ "\r\n");

				deConvoVarInst.summaryTableAL.add(str);
			}

		}

		double[] termX = procInputInst.getLambdazDataobj().getLambdaZDetails()[profileNo]
				.getTimeForPredVal();
		double[] yCalc = procInputInst.getLambdazDataobj().getLambdaZDetails()[profileNo]
				.getPredictedConc();

		String profileStr = determineProfile(profileNo);
		GenerateLogGraphForDeConvo logPlotInst = new GenerateLogGraphForDeConvo(
				"Log Graph", deConvoVarInst.time, deConvoVarInst.conc, termX,
				yCalc, deConvoVarInst.lambdaZ, profileStr);
		
		GenerateNormalGraphForDeConvo linearPlotInst = new GenerateNormalGraphForDeConvo(deConvoVarInst.time, deConvoVarInst.conc, termX,
				yCalc, profileStr);

		
		GenerateLinGraphForDeConvoFracAbs linPlotInst = new GenerateLinGraphForDeConvoFracAbs(deConvoVarInst.time, deConvoVarInst.fracAbsorption, profileStr);
		GenerateLogGraphForDeConvoFracAbs logplotInst = new GenerateLogGraphForDeConvoFracAbs("Log Graph",deConvoVarInst.time, deConvoVarInst.fracAbsorption, profileStr);
	}

	
	
	private String determineProfile(int j) {
		String profile = "";
		ProcessingInputsInfo procInputInst = DeConvolutionVariables.createDeConvoVarInstance()
				.copyProcessingInput();
		if (procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size() != 0)
			for (int i = 0; i < procInputInst.getMappingDataObj()
					.getSortVariablesListVector().size(); i++) {
				profile = profile
						+ (procInputInst
								.getMappingDataObj()
								.getSortVariablesListVector().get(i)
								+ " = "
								+ procInputInst
										.getProfileAndParamInfoObj()
										.getDataSortVariables()[j][i] + ", ");
				
			}
		return profile;
	}
	void writeHeaderForSummaryTable() throws RowsExceededException,
			WriteException, BiffException, IOException {

		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();

		deConvoVarInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
		deConvoVarInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 40)

				+ StringUtils.rightPad("SUMMARY OF SLOPE CALCULATION", 20)
						+ "\r\n\r\n");

		deConvoVarInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5) + StringUtils.rightPad("Time", 10)
						+ "\t" + StringUtils.rightPad("Conc", 10) + "\t"
						+ StringUtils.rightPad("Predicted", 10) + "\t"
						+ StringUtils.rightPad("Residual", 10) + "\t"
						+ StringUtils.rightPad("Weight", 10) + "\t");

		deConvoVarInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5) + StringUtils.rightPad("", 10)
						+ "\t" + StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\r\n");

	}

}
