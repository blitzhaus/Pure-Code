package Model;

import java.io.IOException;
import java.sql.Time;

import org.apache.commons.lang.StringUtils;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class PolyExpoDeconvolution {

	static PolyExpoDeconvolution polyExpoDeConvoInst = null;

	public static PolyExpoDeconvolution createPolyExpoDeConvoInstance() {
		if (polyExpoDeConvoInst == null) {
			polyExpoDeConvoInst = new PolyExpoDeconvolution();
		}
		return polyExpoDeConvoInst;
	}

	DeConvolutionVariables deConvoVarInst;
	DeConvoModelInfoPreparator deConvoModelInfoInst;
	DeConvoOutputPreparator deConvoOutputInst;
	DeConvoResultStructStorer resultStructStorerInst;

	public void calculatePolyExpoDeConvolution(ApplicationInfo applicationInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		DeConvolutionVariables.deConvoVariablesInst = null;
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		deConvoVarInst.appInfo = applicationInfo;
		deConvoVarInst.analysisType = "polyexpo";
		deConvoModelInfoInst = new DeConvoModelInfoPreparator();
		deConvoOutputInst = new DeConvoOutputPreparator();
		resultStructStorerInst = new DeConvoResultStructStorer();

		String stringTime;

		deConvoModelInfoInst.copyProfileRelatedInfo();

		deConvoModelInfoInst.copyPolyExpoModelInfo();

		deConvoOutputInst.initializeResultsAL();
		stringTime = systemTime();

		deConvoOutputInst.writingHeaderToTextOutput(stringTime);

		mainParamComputationLoop();

	}

	private void mainParamComputationLoop() throws IOException,
			RowsExceededException, WriteException, BiffException {

		int sum = 0;

		for (int profileNo = 0; profileNo < deConvoVarInst.noOfProfiles; profileNo++) {

			deConvoVarInst.currentProfileNumber = profileNo;

			deConvoVarInst.workSheetOutputInst
					.getTextoutput()
					.add(
							"\r\n"
									+ StringUtils
											.rightPad(
													"*************************************************"
															+ "**********************************",
													80) + "\r\n\r\n");

			deConvoVarInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 25)
							+ StringUtils.rightPad(
									"Output of the Profile Number:", 37)
							+ StringUtils.rightPad(
									(deConvoVarInst.currentProfileNumber + 1)
											+ "", 5) + "\r\n\r\n");

			deConvoModelInfoInst.createSingleProfileData(sum, profileNo);
			sum = sum + deConvoVarInst.profileObsNos[profileNo];

			deConvoVarInst.removeMissingData(profileNo);

			deConvoVarInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad(
							"*************************************************"
									+ "**********************************", 80)
							+ "\r\n\r\n");

			deConvoVarInst.workSheetOutputInst.getTextoutput().add("\r\n");
			deConvoModelInfoInst.copyDoseForCurrentProfile(profileNo);
			deConvoModelInfoInst.copyExpValuesForCurrentProfile(profileNo);

			estimateFractionAbsorption();

			writeResultSummary(profileNo);
			writePredictedValue(profileNo);
			polyExpoPlotGeneration(profileNo);

			deConvoVarInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

			Time t2 = new Time(System.currentTimeMillis());

			deConvoVarInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad("Time at end is:", 20) + t2);
			deConvoVarInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

			deConvoVarInst.workSheetOutputInst
					.getTextoutput()
					.add(
							"\r\n\r\n"
									+ StringUtils
											.rightPad(
													"*************************************************"
															+ "**********************************",
													80) + "\r\n\r\n");
			deConvoVarInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 15)
							+ StringUtils.rightPad(
									"End of the Output of the Profile Number:",
									5)
							+ (deConvoVarInst.currentProfileNumber + 1));
			deConvoVarInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");
			deConvoVarInst.workSheetOutputInst
					.getTextoutput()
					.add(
							"\r\n\r\n"
									+ StringUtils
											.rightPad(
													"*************************************************"
															+ "**********************************",
													80) + "\r\n\r\n");
			deConvoVarInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");
			deConvoVarInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

			String string = systemTime();

			resultStructStorerInst.storeSpreedSheetResults();

		}

	}

	void writeResultSummary(int profileNo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		writeHeaderForSummaryTable();
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();

		for (int i = 0; i < deConvoVarInst.time.length; i++) {

			String[] str = new String[deConvoVarInst.noOfSortVar + 4];
			for (int j = 0; j < deConvoVarInst.noOfSortVar; j++) {
				str[j] = deConvoVarInst.sortVarData[profileNo][j];

			}
			str[deConvoVarInst.noOfSortVar] = deConvoVarInst.time[i] + "";
			str[deConvoVarInst.noOfSortVar + 1] = deConvoVarInst.conc[i] + "";
			str[deConvoVarInst.noOfSortVar + 2] = deConvoVarInst.absRate[i]
					+ "";
			str[deConvoVarInst.noOfSortVar + 3] = deConvoVarInst.fracAbsorption[i]
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
									10)
							+ "\t"

							+ StringUtils.rightPad(deConvoVarInst.formatting(
									deConvoVarInst.absRate[i], false), 10)
							+ "\t"

							+ "\r\n");

			deConvoVarInst.summaryTableAL.add(str);
		}
	}

	void writePredictedValue(int profileNo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();

		computePredictedConc(profileNo);

		for (int i = 0; i < deConvoVarInst.time.length; i++) {

			String[] str = new String[deConvoVarInst.noOfSortVar + 2];
			for (int j = 0; j < deConvoVarInst.noOfSortVar; j++) {
				str[j] = deConvoVarInst.sortVarData[profileNo][j];

			}
			str[deConvoVarInst.noOfSortVar] = deConvoVarInst.time[i] + "";
			str[deConvoVarInst.noOfSortVar + 1] = deConvoVarInst.predConc[i]
					+ "";

			deConvoVarInst.predictedValueAL.add(str);
		}
	}

	private void polyExpoPlotGeneration(int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		String profileStr = determineProfile(profileNo);

		GenerateLinGraphForDeConvoFracAbs linPlotInst = new GenerateLinGraphForDeConvoFracAbs(
				deConvoVarInst.time, deConvoVarInst.absRate, profileStr);
		GenerateLogGraphForDeConvoFracAbs logPlotInst = new GenerateLogGraphForDeConvoFracAbs(
				"Log Graph", deConvoVarInst.time, deConvoVarInst.absRate,
				profileStr);

		GenerateLinGraphForDeConvoCumuAbs linPlotInst1 = new GenerateLinGraphForDeConvoCumuAbs(
				deConvoVarInst.time, deConvoVarInst.fracAbsorption, profileStr);
		GenerateLogGraphForDeConvoCumuAbs logPlotInst1 = new GenerateLogGraphForDeConvoCumuAbs(
				"Log Graph", deConvoVarInst.time,
				deConvoVarInst.fracAbsorption, profileStr);

		GenerateLinGraphForDeConvoXVsY linPlotInst2 = new GenerateLinGraphForDeConvoXVsY(
				deConvoVarInst.time, deConvoVarInst.conc, deConvoVarInst.time,
				deConvoVarInst.predConc, profileStr);
		GenerateLogGraphForDeConvoXVsY logPlotInst2 = new GenerateLogGraphForDeConvoXVsY(
				"Log View", deConvoVarInst.time, deConvoVarInst.conc,
				deConvoVarInst.time, deConvoVarInst.predConc, profileStr);

	}

	private String determineProfile(int j) {
		String profile = "";
		ProcessingInputsInfo procInputInst = DeConvolutionVariables
				.createDeConvoVarInstance().copyProcessingInput();
		if (procInputInst.getMappingDataObj().getSortVariablesListVector()
				.size() != 0)
			for (int i = 0; i < procInputInst.getMappingDataObj()
					.getSortVariablesListVector().size(); i++) {
				profile = profile
						+ (procInputInst.getMappingDataObj()
								.getSortVariablesListVector().get(i)
								+ " = "
								+ procInputInst.getProfileAndParamInfoObj()
										.getDataSortVariables()[j][i] + ", ");

			}
		return profile;
	}

	private void computePredictedConc(int profileNo) {

		deConvoVarInst.predConc = new double[deConvoVarInst.time.length];
		for (int i = 0; i < deConvoVarInst.time.length; i++) {

			for (int j = 0; j < deConvoVarInst.expValues.length; j = j + 2) {
				deConvoVarInst.predConc[i] = deConvoVarInst.predConc[i]
						+ deConvoVarInst.expValues[j]
						* Math.exp((-1) * deConvoVarInst.expValues[j + 1]
								* deConvoVarInst.time[i]);
			}
		}

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

						+ StringUtils.rightPad(
								"SUMMARY OF POLYEXPONENTIAL DECONVOLUTION", 20)
						+ "\r\n\r\n");

		deConvoVarInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5) + StringUtils.rightPad("Time", 10)
						+ "\t" + StringUtils.rightPad("Conc", 10) + "\t"
						+ StringUtils.rightPad("Absorption Rate", 10) + "\t"
						+ StringUtils.rightPad("FracAbsorption", 10) + "\t");

		deConvoVarInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5) + StringUtils.rightPad("", 10)
						+ "\t" + StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\t"
						+ StringUtils.rightPad("", 10) + "\r\n");

	}

	private void estimateFractionAbsorption() {
		CalculatePolyExpoFracAbsorption polyExpoFracAbsCalInst = new CalculatePolyExpoFracAbsorption();
		polyExpoFracAbsCalInst.fracAbsorptionCalculation();
	}

	public String systemTime() {
		java.sql.Timestamp currentTime = new java.sql.Timestamp(
				new java.util.Date().getTime());
		String currenttime = String.valueOf(currentTime);
		String date = currenttime.substring(0, 10);
		String time = currenttime.substring(11, 19);
		// logArea.append("     " + date + "  " + time+"\n");
		return (date + "/" + time);
	}

}
