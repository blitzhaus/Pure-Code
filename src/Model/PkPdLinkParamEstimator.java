package Model;

import java.io.IOException;
import java.sql.Time;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import view.ApplicationInfo;

import view.ProcessingInputsInfo;

public class PkPdLinkParamEstimator {

	static PkPdLinkParamEstimator pkpdlinkParamCalInst = null;

	public static PkPdLinkParamEstimator createPkPdLinkmParamCalInstance() {
		if (pkpdlinkParamCalInst == null) {
			pkpdlinkParamCalInst = new PkPdLinkParamEstimator();
		}
		return pkpdlinkParamCalInst;
	}

	ListOfDataStructures dataStructInst;
	PkPdInfo pkPdInst;
	ProfileRelatedInfoPreparator profileRelatedInfoInst;
	ModelAndEngineSettingsInfo modelEnginInst;
	InitializationOfResultStructure initResStructInst;
	DosingRelatedInfoReader dosingInfoInst;
	WriteListOfInputCommand writeIpCommandInst;
	ApplicationInfo appInfo;
	CaOutputPreparator outputInst;
	InitialParamInfoPreparator initParamInst;
	ProcessingInputsInfo procInputInst;
	ParameterEstimator paramEstimateInst;

	public void calculatePkPdLinkParameters(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		pkPdInst = PkPdInfo.createPKPDInstance();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		dataStructInst.setAppInfo(appInfo);

		pkPdInst.workSheetOutputInst = new WorkSheetOutputs();
		profileRelatedInfoInst = new ProfileRelatedInfoPreparator();
		modelEnginInst = new ModelAndEngineSettingsInfo();
		initResStructInst = new InitializationOfResultStructure();
		dosingInfoInst = new DosingRelatedInfoReader();
		writeIpCommandInst = new WriteListOfInputCommand();

		initParamInst = new InitialParamInfoPreparator();

		pkPdInst.analysisType = "pkpdlink";
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

			profileRelatedInfoInst
					.copyProfileRelatedInfo(pkPdInst.analysisType);

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"FIRST EXECUTION (WEIGHTING SCHEME: OBSERVED, WEIGHTING EXPONENT: 0) ",
											80)
									+ "\r\n\r\n");

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");

			modelEnginInst
					.copyModelAndEngineSettingsInfo(pkPdInst.analysisType);

			pkPdInst.wtScheme = 0;
			pkPdInst.wtExp = 0;

			initResStructInst.initializeResultAL(pkPdInst.analysisType);
			String stringTime = profileRelatedInfoInst.systemTime();
			dosingInfoInst.readingDoseUntAndConcMassUnit(pkPdInst.analysisType);
			double convertAmount = dosingInfoInst
					.doseToConcMassUnitConvertAmount();

			writeIpCommandInst.writingHeaderToTextOutput(stringTime);
			writeIpCommandInst.writingListOfInputCommand(pkPdInst.analysisType);
			outputInst = new CaOutputPreparator();
			mainParamComputationLoop(convertAmount);

			if (pkPdInst.exceptionForCurrentProfile == 1)
				pkPdInst.aicValue[pkPdInst.aicCount] = 9999999999999.9;

			pkPdInst.aicCount++;

			pkPdInst.exceptionForCurrentProfile = 0;
			convertAmount = 1;

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"SECOND EXECUTION (WEIGHTING SCHEME: OBSERVED, WEIGHTING EXPONENT: -1) ",
											80)
									+ "\r\n\r\n");

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");

			modelEnginInst
					.copyModelAndEngineSettingsInfo(pkPdInst.analysisType);

			pkPdInst.wtScheme = 0;
			pkPdInst.wtExp = 1;

			initResStructInst.initializeResultAL(pkPdInst.analysisType);
			stringTime = profileRelatedInfoInst.systemTime();
			dosingInfoInst.readingDoseUntAndConcMassUnit(pkPdInst.analysisType);

			writeIpCommandInst.writingHeaderToTextOutput(stringTime);
			writeIpCommandInst.writingListOfInputCommand(pkPdInst.analysisType);
			outputInst = new CaOutputPreparator();
			mainParamComputationLoop(convertAmount);

			if (pkPdInst.exceptionForCurrentProfile == 1)
				pkPdInst.aicValue[pkPdInst.aicCount] = 9999999999999.9;

			pkPdInst.aicCount++;

			pkPdInst.exceptionForCurrentProfile = 0;
			convertAmount = 1;

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"THIRD EXECUTION (WEIGHTING SCHEME: OBSERVED, WEIGHTING EXPONENT: -2) ",
											80)
									+ "\r\n\r\n");

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");

			modelEnginInst
					.copyModelAndEngineSettingsInfo(pkPdInst.analysisType);

			pkPdInst.wtScheme = 0;
			pkPdInst.wtExp = 2;

			initResStructInst.initializeResultAL(pkPdInst.analysisType);
			stringTime = profileRelatedInfoInst.systemTime();
			dosingInfoInst.readingDoseUntAndConcMassUnit(pkPdInst.analysisType);

			writeIpCommandInst.writingHeaderToTextOutput(stringTime);
			writeIpCommandInst.writingListOfInputCommand(pkPdInst.analysisType);
			outputInst = new CaOutputPreparator();
			mainParamComputationLoop(convertAmount);

			if (pkPdInst.exceptionForCurrentProfile == 1)
				pkPdInst.aicValue[pkPdInst.aicCount] = 9999999999999.9;

			pkPdInst.aicCount++;

			pkPdInst.exceptionForCurrentProfile = 0;
			convertAmount = 1;

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"FOURTH EXECUTION (WEIGHTING SCHEME: PREDICTED, WEIGHTING EXPONENT: -1) ",
											80)
									+ "\r\n\r\n");

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");

			modelEnginInst
					.copyModelAndEngineSettingsInfo(pkPdInst.analysisType);

			pkPdInst.wtScheme = 1;
			pkPdInst.wtExp = 1;

			initResStructInst.initializeResultAL(pkPdInst.analysisType);
			stringTime = profileRelatedInfoInst.systemTime();
			dosingInfoInst.readingDoseUntAndConcMassUnit(pkPdInst.analysisType);

			writeIpCommandInst.writingHeaderToTextOutput(stringTime);
			writeIpCommandInst.writingListOfInputCommand(pkPdInst.analysisType);
			outputInst = new CaOutputPreparator();
			mainParamComputationLoop(convertAmount);

			if (pkPdInst.exceptionForCurrentProfile == 1)
				pkPdInst.aicValue[pkPdInst.aicCount] = 9999999999999.9;

			pkPdInst.aicCount++;

			pkPdInst.exceptionForCurrentProfile = 0;
			convertAmount = 1;

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"FIFTH EXECUTION (WEIGHTING SCHEME: PREDICTED, WEIGHTING EXPONENT: -2) ",
											80)
									+ "\r\n\r\n");

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");

			modelEnginInst
					.copyModelAndEngineSettingsInfo(pkPdInst.analysisType);

			pkPdInst.wtScheme = 1;
			pkPdInst.wtExp = 2;

			initResStructInst.initializeResultAL(pkPdInst.analysisType);
			stringTime = profileRelatedInfoInst.systemTime();
			dosingInfoInst.readingDoseUntAndConcMassUnit(pkPdInst.analysisType);

			writeIpCommandInst.writingHeaderToTextOutput(stringTime);
			writeIpCommandInst.writingListOfInputCommand(pkPdInst.analysisType);
			outputInst = new CaOutputPreparator();
			mainParamComputationLoop(convertAmount);

			if (pkPdInst.exceptionForCurrentProfile == 1)
				pkPdInst.aicValue[pkPdInst.aicCount] = 9999999999999.9;

			pkPdInst.exceptionForCurrentProfile = 0;
			convertAmount = 1;

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
					StringUtils.rightPad(
							"FINAL EXECUTION (OPTIMUM WEIGHTING SCHEME) ", 80)
							+ "\r\n\r\n");

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");
			pkPdInst.aicCount = 0;

			modelEnginInst
					.copyModelAndEngineSettingsInfo(pkPdInst.analysisType);

			determineOptimalWeighting();

			initResStructInst.initializeResultAL(pkPdInst.analysisType);
			stringTime = profileRelatedInfoInst.systemTime();
			dosingInfoInst.readingDoseUntAndConcMassUnit(pkPdInst.analysisType);

			writeIpCommandInst.writingHeaderToTextOutput(stringTime);
			writeIpCommandInst.writingListOfInputCommand(pkPdInst.analysisType);
			outputInst = new CaOutputPreparator();
			mainParamComputationLoop(convertAmount);

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

	private void mainParamComputationLoop(double convertAmount)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int sum = 0;

		for (int profileNo = 0; profileNo < dataStructInst.noOfSubjects; profileNo++) {
			pkPdInst.analysisType = "pkpdlink";
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

			if (ifProfileIsInsufficient == true) {
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

			else {

				pkPdInst.workSheetOutputInst.getTextoutput().add(
						StringUtils.rightPad(
								"*************************************************"
										+ "**********************************",
								80)
								+ "\r\n\r\n");

				pkPdInst.exceptionForCurrentProfile = 0;

				pkPdInst.analysisType = "pk";
				pkPdInst.modelNumber = procInputInst.getModelInputTab1Obj()
						.getModelNumberForLinkModel();
				pkPdInst.ifAlgebraicModel = true;

				if (pkPdInst.exceptionForCurrentProfile == 0)

					dosingInfoInst.readingDoseAndDosingTime(
							pkPdInst.analysisType, convertAmount, profileNo);
				pkPdInst.analysisType = "pkpdlink";
				outputInst.fillingUpDosingDisplayTable();

				initParamInst.retrieveInitialValueForLinkParam(
						pkPdInst.analysisType, profileNo);

				pkPdInst.modelNumber = procInputInst.getModelInputTab1Obj()
						.getModelNumberForLinkModel();
				pkPdInst.ifAlgebraicModel = true;

				pkPdInst.analysisType = "pk";
				LibraryModelDefiner libModelInst = new LibraryModelDefiner();
				dataStructInst.concForLinkModel = new double[dataStructInst.subjectObsNos[profileNo]][1];

				for (int i = 0; i < pkPdInst.X.length; i++) {
					dataStructInst.concForLinkModel[i][0] = libModelInst
							.chooseLibraryModel(
									dataStructInst.paramForLinkModel,
									dataStructInst.infusionTime,
									dataStructInst.dose,
									dataStructInst.dosingTime,
									pkPdInst.X[i][0], 0, pkPdInst.extraData, i,
									pkPdInst.row);
				}

				pkPdInst.analysisType = "pkpdlink";
				pkPdInst.ifAlgebraicModel = false;
				pkPdInst.modelNumber = procInputInst.getModelInputTab1Obj()
						.getModelNumberForCA();

				pkPdInst.row[0] = dataStructInst.subjectObsNos[profileNo];

				dosingInfoInst.writingDoseRelatedInfoInTextoutput(
						convertAmount, profileNo);
				if (pkPdInst.exceptionForCurrentProfile == 0) {
					if (procInputInst.getModelInputTab2Obj()
							.isPropagateFinalEstimae() == true
							&& pkPdInst.currentProfileNumber > 0) {
						pkPdInst.parameter = dataStructInst.propFinalEstimate;
					} else {
						initParamInst.retrievingInitialParamValInfo(
								pkPdInst.analysisType, profileNo);

					}
				}

				

				if (pkPdInst.exceptionForCurrentProfile == 0)

					outputInst.fillingUpInitialParameterTable(
							pkPdInst.parameter, pkPdInst.lowerBound,
							pkPdInst.upperBound, profileNo);

				
				
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
			}

			String string = profileRelatedInfoInst.systemTime();
			outputInst.fillingUpHistoryDisplayTable(string);

			ResultsStructureStorer sssrInstance = new ResultsStructureStorer();

			sssrInstance.storeSpreedSheetResults();

		}
	}

	private void estimateModelParameter(int profileNo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		pkPdInst.setIfCallingForParameterCalculation(true);

		paramEstimateInst = new ParameterEstimator();
		paramEstimateInst.estimateModelParameter(profileNo);

		
		if (procInputInst.getModelInputTab2Obj().isPropagateFinalEstimae() == true
				&& pkPdInst.currentProfileNumber == 0)
			dataStructInst.propFinalEstimate = pkPdInst.parameter;
		System.out.println();
		pkPdInst.setIfCallingForParameterCalculation(false);
	}

}
