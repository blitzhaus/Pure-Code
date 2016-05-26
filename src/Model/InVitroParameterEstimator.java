package Model;

import java.io.IOException;
import java.sql.Time;

import org.apache.commons.lang.StringUtils;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class InVitroParameterEstimator {

	static InVitroParameterEstimator inVitroParamCalInst = null;

	public static InVitroParameterEstimator createInVitroParamCalInstance() {
		if (inVitroParamCalInst == null) {
			inVitroParamCalInst = new InVitroParameterEstimator();
		}
		return inVitroParamCalInst;
	}

	ListOfDataStructures dataStructInst;
	PkPdInfo pkPdInst;
	ProfileRelatedInfoPreparator profileRelatedInfoInst;
	ModelAndEngineSettingsInfo modelEnginInst;
	InitializationOfResultStructure initResStructInst;
	WriteListOfInputCommand writeIpCommandInst;
	InVitroInitialParamValuePreparator initParamValInst;
	ProcessingInputsInfo procInputInst;
	ParameterEstimator paramEstimateInst;
	ResultsStructureStorer resStructStoreInstance;
	InVitroOutputPreparator outputInst;

	public void calculateInVitroParameters(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		pkPdInst = PkPdInfo.createPKPDInstance();
		pkPdInst.analysisType = "invitro";
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		dataStructInst.appInfo = appInfo;
		pkPdInst.workSheetOutputInst = new WorkSheetOutputs();
		profileRelatedInfoInst = new ProfileRelatedInfoPreparator();
		modelEnginInst = new ModelAndEngineSettingsInfo();
		initResStructInst = new InitializationOfResultStructure();

		writeIpCommandInst = new WriteListOfInputCommand();

		initParamValInst = new InVitroInitialParamValuePreparator();

		procInputInst = pkPdInst.copyProcessingInput();

		pkPdInst.defaultUnitForCA = procInputInst.getParameterUnitsDataObj()
				.getDefaultUnitArray();

		pkPdInst.preferredUnitForCA = procInputInst.getParameterUnitsDataObj()
				.getPreferredUnitsArray();

	

		if (procInputInst.getModelInputTab1Obj().isSimulation() == true) {
			SimulationForLibraryModels simulationInst = SimulationForLibraryModels
					.createSimulationInst();
			simulationInst.performSimulation();
		} else {
			String stringTime;

			profileRelatedInfoInst
					.copyProfileRelatedInfo(pkPdInst.analysisType);

			modelEnginInst
					.copyModelAndEngineSettingsInfo(pkPdInst.analysisType);

			
			initResStructInst.initializeResultAL(pkPdInst.analysisType);
			stringTime = profileRelatedInfoInst.systemTime();

			writeIpCommandInst.writingHeaderToTextOutput(stringTime);

			outputInst = new InVitroOutputPreparator();
			//writeIpCommandInst.writingListOfInputCommand(pkPdInst.analysisType);
			mainParamComputationLoop();

		}

	}

	private void determineOptimalWeighting() {

		double minAicValue = pkPdInst.aicValue[0];
		int minIndex = 0;

		for (int j = 0; j < pkPdInst.aicValue.length; j++) {

			if (pkPdInst.aicValue[j] < minAicValue) {
				minAicValue = pkPdInst.aicValue[j];
				minIndex = j;
			}

		}

		if (minIndex < 3) {
			pkPdInst.wtScheme = 0;

			if (minIndex == 0)
				pkPdInst.wtExp = 0;
			else if (minIndex == 1)
				pkPdInst.wtExp = 1;
			else
				pkPdInst.wtExp = 2;
		} else {
			pkPdInst.wtScheme = 1;

			if (minIndex == 3)
				pkPdInst.wtExp = 1;
			else
				pkPdInst.wtExp = 2;
		}

	}

	private void mainParamComputationLoop() throws RowsExceededException,
			WriteException, BiffException, IOException {
		int sum = 0;

		for (int profileNo = 0; profileNo < dataStructInst.noOfSubjects; profileNo++) {

			pkPdInst.currentProfileNumber = profileNo;
			int temp1 = pkPdInst.currentProfileNumber + 1;
			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							"\r\n"
									+ StringUtils
											.rightPad(
													"*************************************************"
															+ "**********************************",
													80) + "\r\n\r\n");

			pkPdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 25)
							+ StringUtils.rightPad(
									"Output of the Profile Number:", 37)
							+ StringUtils.rightPad(temp1 + "", 5) + "\r\n\r\n");

			profileRelatedInfoInst.createSingleProfileData(
					pkPdInst.analysisType, sum, profileNo);
			sum = sum + dataStructInst.subjectObsNos[profileNo];

			profileRelatedInfoInst.removeMissingData(profileNo);

			DetectionOfProfilesWithInsufficientData insuffProfileDetectionInst = new DetectionOfProfilesWithInsufficientData();
			boolean ifProfileIsInsufficient;
			ifProfileIsInsufficient = insuffProfileDetectionInst
					.detectProfileWithInsufficientDataInCA(pkPdInst.X,
							pkPdInst.Y, pkPdInst.noOfParam);

			/*if (ifProfileIsInsufficient == true) {
				pkPdInst.workSheetOutputInst.getTextoutput().add(
						StringUtils.rightPad(
								"*************************************************"
										+ "**********************************",
								80)
								+ "\r\n\r\n");

				pkPdInst.workSheetOutputInst
						.getTextoutput()
						.add(
								StringUtils.rightPad("", 15)

										+ StringUtils
												.rightPad(
														"Profile information is not sufficient to estimate the parmeters",
														70) + "\r\n\r\n");

			}

			else {*/

				pkPdInst.workSheetOutputInst.getTextoutput().add(
						StringUtils.rightPad(
								"*************************************************"
										+ "**********************************",
								80)
								+ "\r\n\r\n");

				pkPdInst.exceptionForCurrentProfile = 0;

				pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n");

				pkPdInst.row[0] = dataStructInst.subjectObsNos[profileNo];
				if (pkPdInst.exceptionForCurrentProfile == 0) {
					if (procInputInst.getModelInputTab2Obj()
							.isPropagateFinalEstimae() == true
							&& pkPdInst.currentProfileNumber > 0) {
						pkPdInst.parameter = dataStructInst.propFinalEstimate;
					} else {
						initParamValInst.initParamValueInfo(profileNo);

					}
				}

				if (pkPdInst.exceptionForCurrentProfile == 0)

					outputInst.fillingUpInitialParameterTable(profileNo);

				if (pkPdInst.exceptionForCurrentProfile == 0)
					estimateModelParameter(profileNo);

				if (pkPdInst.exceptionForCurrentProfile == 0) {
					outputInst.createAndPrintingOfSummaryAndSummaryTable(
							pkPdInst.parameter, pkPdInst.X, pkPdInst.Y,
							pkPdInst.extraData, pkPdInst.row, profileNo,
							pkPdInst.numberOfSortVar);

				}

				pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

				Time t2 = new Time(System.currentTimeMillis());

				pkPdInst.workSheetOutputInst.getTextoutput().add(
						StringUtils.rightPad("", 5)
								+ StringUtils.rightPad("Time at end is:", 20)
								+ t2);
				pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

				pkPdInst.workSheetOutputInst
						.getTextoutput()
						.add(
								"\r\n\r\n"
										+ StringUtils
												.rightPad(
														"*************************************************"
																+ "**********************************",
														80) + "\r\n\r\n");
				pkPdInst.workSheetOutputInst
						.getTextoutput()
						.add(
								StringUtils.rightPad("", 15)
										+ StringUtils
												.rightPad(
														"End of the Output of the Profile Number:",
														5) + temp1);
				pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");
				pkPdInst.workSheetOutputInst
						.getTextoutput()
						.add(
								"\r\n\r\n"
										+ StringUtils
												.rightPad(
														"*************************************************"
																+ "**********************************",
														80) + "\r\n\r\n");
				pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");
				pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");
			//}

			String string = profileRelatedInfoInst.systemTime();
			outputInst.fillingUpHistoryDisplayTable(string);

			resStructStoreInstance = new ResultsStructureStorer();

			resStructStoreInstance.storeSpreedSheetResults();

		}
	}

	private void estimateModelParameter(int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		pkPdInst.setIfCallingForParameterCalculation(true);

		paramEstimateInst = new ParameterEstimator();
		paramEstimateInst.estimateModelParameter(profileNo);

		if (procInputInst.getModelInputTab2Obj().isPropagateFinalEstimae() == true
				&& pkPdInst.currentProfileNumber == 0)
			dataStructInst.propFinalEstimate = pkPdInst.parameter;

		pkPdInst.setIfCallingForParameterCalculation(false);
	}

}
