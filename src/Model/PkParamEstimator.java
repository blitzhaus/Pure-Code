package Model;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.apache.commons.lang.StringUtils;

import Common.TestException;

import view.ApplicationInfo; 
import view.ProcessingInputsInfo;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PkParamEstimator {

	double[] infusionTime;
	double[] dose;
	double[] secondDose;

	
	double[] dosingTime;

	int normalizationUnitIndex;
	int dataType;
	int rootOfAdministration;
	int ifParameterValueIsSupplied;
	int ifBoundaryIsUsed;
	double[] calcYval;

	int valueForParameterBoundary;


	boolean ifForInitialValue = false;

	boolean ifFromASCIIModel = false;

	double[] initial;
	
	double corrObsAndPredY;
	double weightedCorrObsAndPredY;
	double[] propFinalEstimate;
	String[] initParamVal;

	PkProcessingInputPreparator caProcIpPrep;
	PkProcessingOutputPreparator caProcOpPrep;
	
	static PkParamEstimator pkpdMainInst = null;
	GridSearch gdInstance;
	GeneticAlgorithm geneticAlgoInst;
	PkCurveStripper initValByCSInst;
	ParameterEstimator paramEstimateInst;

	HybridPSODriver psoInst;
	BeeTest beeTestInst;
	
	
	public static PkParamEstimator createPkParamEstimateInstance() {
		if (pkpdMainInst == null) {
			pkpdMainInst = new PkParamEstimator();
		}
		return pkpdMainInst;
	}

	public PkParamEstimator() {
		infusionTime = new double[1];
		dose = new double[1];
		secondDose = new double[1];
		dosingTime = new double[1];

		normalizationUnitIndex = 0;
		dataType = 0;
		rootOfAdministration = 0;
		ifParameterValueIsSupplied = 0;
		ifBoundaryIsUsed = 0;
		calcYval = new double[1];
		valueForParameterBoundary = 0;

		ifForInitialValue = false;
		ifFromASCIIModel = false;
		initial = new double[1];
		verificationOfInputs = new ArrayList<String[]>();
		corrObsAndPredY = 0;
		weightedCorrObsAndPredY = 0;

	}

	
	
	ApplicationInfo appInfoinst;

	PkPdInfo pkpdInst;

	int noOfParam;
	int[] subjectObsNos;
	int noOfSubjects;
	ListOfDataStructures dataStructInst;
	
	ArrayList<String[]> verificationOfInputs;
	ProcessingInputsInfo procInputInst;
	
	

	public void computePkParameter(ApplicationInfo appInfo) throws IOException,
			RowsExceededException, WriteException, BiffException {

		try {

			appInfoinst = appInfo;

			dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
			dataStructInst.appInfo = appInfo;
			
			PkPdInfo.pkpdInst = null;
			pkpdInst = PkPdInfo.createPKPDInstance();
			pkpdInst.analysisType = "pk";
			
			procInputInst = pkpdInst.copyProcessingInput();
			
			
			if(procInputInst.getModelInputTab1Obj().isSimulation() == true)
			{
				SimulationForLibraryModels simulationInst = SimulationForLibraryModels.createSimulationInst();
				simulationInst.performSimulation();
			}
			else {

				pkpdInst.modelNumber = procInputInst.getModelInputTab1Obj()
						.getModelNumberForCA();
				pkpdInst.ifAlgebraicModel = procInputInst
						.getModelInputTab1Obj().isAlgebraicModel();

				pkpdInst.ifClearanceParam = procInputInst
						.getModelInputTab1Obj().isIfClearanceParam();

				pkpdInst.nFun = procInputInst.getProfileAndParamInfoObj()
						.getNumberOfFunction();

				pkpdInst.nDer = procInputInst.getProfileAndParamInfoObj()
						.getNumberOfDeterminant();

				pkpdInst.odeSolverChoice = procInputInst.getModelInputTab2Obj()
						.getOdeSolverMethod() + 1;

				initializePkPdStruct(appInfo);
				pkpdInst.workSheetOutputInst = new WorkSheetOutputs();

				caProcIpPrep = new PkProcessingInputPreparator();
				caProcOpPrep = new PkProcessingOutputPreparator();
				
				
				ifParameterValueIsSupplied = procInputInst
				.getModelInputTab2Obj().getSourceOfIntParamValue();
				
				if(procInputInst.getModelInputTab2Obj().getInitialParamBYNCA() == 1)
					ifParameterValueIsSupplied = 1;
				
				
					

				
				noOfParam = procInputInst.getProfileAndParamInfoObj()
						.getNumberOfParameter();

				subjectObsNos = procInputInst.getProfileAndParamInfoObj()
						.getSubjectObsNo();

				noOfSubjects = procInputInst.getProfileAndParamInfoObj()
						.getNoOfSubject();

				caProcIpPrep.readingMiscellaneousInput(this, appInfo);

				

				pkpdInst.parameter = new double[noOfParam];
				pkpdInst.row = new int[noOfSubjects];
				pkpdInst.delta = new double[noOfParam];

				

				// start of the main loop for multiple profile

				int numberOfSortVariable = procInputInst.getMappingDataObj()
						.getSortVariablesListVector().size();
				
				
				caProcIpPrep.initializeResultsAL(this);
				caProcIpPrep.readingDoseUntAndConcMassUnit(this);
				double convertAmount = doseToConcMassUnitConvertAmount();

				

				//verifyInput();
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"FIRST EXECUTION (WEIGHTING SCHEME: OBSERVED, WEIGHTING EXPONENT: 0) "
												,
										80)
								+ "\r\n\r\n");
				
				
				
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				
				
						
					pkpdInst.wtScheme = 0;
					pkpdInst.wtExp = 0;
					
					String stringTime = systemTime();
					caProcOpPrep.writingHeaderToTextOutput(stringTime);
					
					caProcOpPrep.writingInputToTextOutput(this);
				
					caProcOpPrep.fillingUpUserSettingsTable(this);
					
					mainParamComputationLoop(convertAmount,
							numberOfSortVariable);
					
					
					if(pkpdInst.exceptionForCurrentProfile == 1)
						pkpdInst.aicValue[pkpdInst.aicCount] = 9999999999999.9;
					
					pkpdInst.aicCount ++;
					
			
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"SECOND EXECUTION (WEIGHTING SCHEME: OBSERVED, WEIGHTING EXPONENT: -1) "
												,
										80)
								+ "\r\n\r\n");
				
				
				
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				
				caProcIpPrep.readingMiscellaneousInput(this, appInfo);
				
			
				pkpdInst.exceptionForCurrentProfile = 0;
					pkpdInst.wtScheme = 0;
					pkpdInst.wtExp = 1;
					
					stringTime = systemTime();
					caProcOpPrep.writingHeaderToTextOutput(stringTime);
					
					caProcOpPrep.writingInputToTextOutput(this);
				
					caProcOpPrep.fillingUpUserSettingsTable(this);
					
					mainParamComputationLoop(convertAmount,
							numberOfSortVariable);
					
					if(pkpdInst.exceptionForCurrentProfile == 1)
						pkpdInst.aicValue[pkpdInst.aicCount] = 9999999999999.9;
					
					pkpdInst.aicCount ++;
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"THIRD EXECUTION (WEIGHTING SCHEME: OBSERVED, WEIGHTING EXPONENT: -2) "
												,
										80)
								+ "\r\n\r\n");
				
				
				
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				
				
				pkpdInst.exceptionForCurrentProfile = 0;
				caProcIpPrep.readingMiscellaneousInput(this, appInfo);
				
			
					pkpdInst.wtScheme = 0;
					pkpdInst.wtExp = 2;
					
					stringTime = systemTime();
					caProcOpPrep.writingHeaderToTextOutput(stringTime);
					
					caProcOpPrep.writingInputToTextOutput(this);
				
					caProcOpPrep.fillingUpUserSettingsTable(this);
					
					mainParamComputationLoop(convertAmount,
							numberOfSortVariable);
					
					if(pkpdInst.exceptionForCurrentProfile == 1)
						pkpdInst.aicValue[pkpdInst.aicCount] = 9999999999999.9;
					
					pkpdInst.aicCount ++;
			
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"FOURTH EXECUTION (WEIGHTING SCHEME: PREDICTED, WEIGHTING EXPONENT: -1) "
												,
										80)
								+ "\r\n\r\n");
				
				
				
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				
				pkpdInst.exceptionForCurrentProfile = 0;
				caProcIpPrep.readingMiscellaneousInput(this, appInfo);
								
					
					pkpdInst.wtScheme = 1;
					pkpdInst.wtExp = 1;
					
					stringTime = systemTime();
					caProcOpPrep.writingHeaderToTextOutput(stringTime);
					
					caProcOpPrep.writingInputToTextOutput(this);
					
					caProcOpPrep.fillingUpUserSettingsTable(this);
					
					mainParamComputationLoop(convertAmount,
							numberOfSortVariable);
					
					if(pkpdInst.exceptionForCurrentProfile == 1)
						pkpdInst.aicValue[pkpdInst.aicCount] = 9999999999999.9;
					
					pkpdInst.aicCount ++;
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"FIFTH EXECUTION (WEIGHTING SCHEME: PREDICTED, WEIGHTING EXPONENT: -2) "
												,
										80)
								+ "\r\n\r\n");
				
				
				
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				
				
				pkpdInst.exceptionForCurrentProfile = 0;
				caProcIpPrep.readingMiscellaneousInput(this, appInfo);
					
					pkpdInst.wtScheme = 1;
					pkpdInst.wtExp = 2;
					
					stringTime = systemTime();
					caProcOpPrep.writingHeaderToTextOutput(stringTime);
					
					caProcOpPrep.writingInputToTextOutput(this);
			
					caProcOpPrep.fillingUpUserSettingsTable(this);
					
					mainParamComputationLoop(convertAmount,
							numberOfSortVariable);
					
					if(pkpdInst.exceptionForCurrentProfile == 1)
						pkpdInst.aicValue[pkpdInst.aicCount] = 9999999999999.9;
					
					pkpdInst.aicCount ++;
				
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"FINAL EXECUTION (MINIMUM WEIGHTING SCHEME)"
												,
										80)
								+ "\r\n\r\n");
				
				
				
				
				pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils
								.rightPad(
										"*************************************************"
												+ "*****************************************************************",
										80)
								+ "\r\n\r\n");
				
				
				pkpdInst.aicCount = 0;
				caProcIpPrep.readingMiscellaneousInput(this, appInfo);
				
				
				determineOptimalWeighting();
				
				pkpdInst.exceptionForCurrentProfile = 0;
				// for final execution
				caProcIpPrep.initializeResultsAL(this);
				stringTime = systemTime();
				caProcOpPrep.writingHeaderToTextOutput(stringTime);
				
				caProcOpPrep.writingInputToTextOutput(this);
				
				caProcOpPrep.fillingUpUserSettingsTable(this);
				mainParamComputationLoop(convertAmount,
						numberOfSortVariable);

				
			}

		} catch (NoSuchElementException e) {
			System.out.println(" NUMBER ERROR ");
		}
	}

	private void determineOptimalWeighting() {
		
		double minAicValue = pkpdInst.aicValue[0];
		int minIndex = 0;
		
		for (int j = 0; j < pkpdInst.aicValue.length; j++) {
			
			if(pkpdInst.aicValue[j] < minAicValue)
			{
				minAicValue = pkpdInst.aicValue[j];
				minIndex = j;
			}
			
		}
		
		if(minIndex < 3)
		{
			pkpdInst.wtScheme = 0;
			
			if(minIndex == 0)
				pkpdInst.wtExp = 0;
			else if (minIndex == 1)
				pkpdInst.wtExp = 1;
			else
				pkpdInst.wtExp = 2;
		}else
		{
			pkpdInst.wtScheme = 1;
			
			if (minIndex == 3)
				pkpdInst.wtExp = 1;
			else
				pkpdInst.wtExp = 2;
		}
		
		
	}
	private void initializePkPdStruct(ApplicationInfo appInfo) {
		pkpdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();

		pkpdInst.numberOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		pkpdInst.dataSortVariables = procInputInst.getProfileAndParamInfoObj()
				.getDataSortVariables();

		pkpdInst.paramName = procInputInst.getProfileAndParamInfoObj()
				.getParameterNameForCA();

		pkpdInst.secParamName = procInputInst.getProfileAndParamInfoObj()
				.getSecondaryParameterNameForCA();

		pkpdInst.noOfSecParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfSecondaryParameters();

		pkpdInst.xColumnName = procInputInst.getMappingDataObj().getxColumnName();

		pkpdInst.yColumnName = procInputInst.getMappingDataObj().getYcolumnName();

		pkpdInst.timeUnit = procInputInst.getParameterUnitsDataObj().getTimeUnit();

		pkpdInst.concMassUnit = procInputInst.getParameterUnitsDataObj()
				.getConcMassUnit();
		pkpdInst.volumeUnit = procInputInst.getParameterUnitsDataObj()
				.getVolumeUnit();
		pkpdInst.numberOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();
		
		pkpdInst.noOfPredval = Integer.parseInt(procInputInst.getModelInputTab2Obj().getNumberOfPredictedValue());
	}


	void readingEngineSettingsInput() throws RowsExceededException,
			WriteException, BiffException, IOException {
		caProcIpPrep.readingEngineSettingsInput(this);
	}

	void readingWeightingInputs() {
		caProcIpPrep.readingWeightingInputs(this);
	}

	void readingDoseUntAndConcMassUnit() {
		caProcIpPrep.readingDoseUntAndConcMassUnit(this);
	}

	void readingMiscellaneousInput(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcIpPrep.readingMiscellaneousInput(this, appInfo);
	}

	void initializeResultsAL() {
		caProcIpPrep.initializeResultsAL(this);
	}

	void initParamValueInfo(ApplicationInfo appInfo, int numberOfSortVariable,
			int ii) throws RowsExceededException, WriteException,
			BiffException, IOException {
		caProcIpPrep
				.initParamValueInfo(this, appInfo, numberOfSortVariable, ii);
	}

	void retrievingUserGivenBoundary(ApplicationInfo appInfo, int ii) {
		caProcIpPrep.retrievingUserGivenBoundary(this, appInfo, ii);
	}

	void retrievingUserGivenInitValue(ApplicationInfo appInfo, int ii) {
		caProcIpPrep.retrievingUserGivenInitValue(this, appInfo, ii);
	}

	void getDefaultParamBoundary() {
		caProcIpPrep.getDefaultParamBoundary(this);
	}

	void retrievingParamBoundary(ApplicationInfo appInfo, int ii) {
		caProcIpPrep.retrievingParamBoundary(this, appInfo, ii);
	}

	void retrievingDoseRelatedInfo(PkParamEstimator pkpdMain,
			ApplicationInfo appInfo, double convertAmount, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcIpPrep
				.retrievingDoseRelatedInfo(this, appInfo, convertAmount, ii);
	}

	void retrievingInfusionDoseAndTimeForSimultaniousLm(
			ApplicationInfo appInfo, int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {
		caProcIpPrep.retrievingSecondDoseAndTimeForSimultaniousLm(this,
				appInfo, ii);
	}

	void retrievingInfusinTime(ApplicationInfo appInfo, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcIpPrep.retrievingInfusinTime(this, appInfo, ii);
	}

	void retrievingDoseAndDosingTime(ApplicationInfo appInfo,
			double convertAmount, int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {
		caProcIpPrep.retrievingDoseAndDosingTime(this, appInfo, convertAmount,
				ii);
	}

	void createSingleProfileData(PkParamEstimator pkpdMain, int sum,
			int ii) {
		caProcIpPrep.createSingleProfileData(this, sum, ii);
	}

	private double doseToConcMassUnitConvertAmount() {
		double convertAmount;
		DefaultToPreferredUnitConverter unitConversionInst = new DefaultToPreferredUnitConverter();

		try {
			convertAmount = unitConversionInst.unitConversion(
					pkpdInst.doseUnit, pkpdInst.concMassUnit);

		} catch (Exception e) {
			convertAmount = 1;
		}

		return convertAmount;
	}

	public void mainParamComputationLoop(
			double convertAmount, int numberOfSortVariable)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = appInfoinst;
		int sum = 0;

		for (int profileNo = 0; profileNo < noOfSubjects; profileNo++) {

			pkpdInst.currentProfileNumber = profileNo;
			int temp1 = pkpdInst.currentProfileNumber + 1;
			pkpdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							"\r\n"
									+ StringUtils
											.rightPad(
													"*************************************************"
															+ "**********************************",
													80) + "\r\n\r\n");

			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 25)
					/* + "\t" */
					+ StringUtils.rightPad("Output of the Profile Number:", 37)
							+ StringUtils.rightPad(temp1 + "", 5) + "\r\n\r\n");

			caProcIpPrep.createSingleProfileData(this, sum, profileNo);
			sum = sum + subjectObsNos[profileNo];

			removeMissingData(profileNo);
			
			DetectionOfProfilesWithInsufficientData insuffProfileDetectionInst = new DetectionOfProfilesWithInsufficientData();
			boolean ifProfileIsInsufficient = false;
			ifProfileIsInsufficient = insuffProfileDetectionInst
					.detectProfileWithInsufficientDataInCA(pkpdInst.X,
							pkpdInst.Y, noOfParam);

			if (ifProfileIsInsufficient == true) {
				pkpdInst.workSheetOutputInst.getTextoutput().add(
						StringUtils.rightPad(
								"*************************************************"
										+ "**********************************",
								80)
								+ "\r\n\r\n");

				pkpdInst.workSheetOutputInst
						.getTextoutput()
						.add(
								StringUtils.rightPad("", 15)

										+ StringUtils
												.rightPad(
														"Profile information is not sufficient to estimate the parmeters",
														70) + "\r\n\r\n");

			}

			else {

				pkpdInst.workSheetOutputInst.getTextoutput().add(
						StringUtils.rightPad(
								"*************************************************"
										+ "**********************************",
								80)
								+ "\r\n\r\n");

				pkpdInst.exceptionForCurrentProfile = 0;
				caProcIpPrep.retrievingDoseRelatedInfo(this, appInfo,
						convertAmount, profileNo);

				if (pkpdInst.exceptionForCurrentProfile == 0)
					caProcOpPrep.fillingUpDosingDisplayTable(this);

				pkpdInst.workSheetOutputInst.getTextoutput().add("\r\n");

				pkpdInst.row[0] = subjectObsNos[profileNo];
				if (pkpdInst.exceptionForCurrentProfile == 0) {
					if (procInputInst
							.getModelInputTab2Obj().isPropagateFinalEstimae() == true
							&& pkpdInst.currentProfileNumber > 0) {
						pkpdInst.parameter = propFinalEstimate;
					} else {
						caProcIpPrep.initParamValueInfo(this, appInfo,
								numberOfSortVariable, profileNo);
					}
				}

				if (pkpdInst.exceptionForCurrentProfile == 0)

					caProcOpPrep.fillingUpInitialParameterTable(this,
							pkpdInst.parameter, pkpdInst.lowerBound,
							pkpdInst.upperBound, profileNo);

				if (pkpdInst.exceptionForCurrentProfile == 0)
					estimateModelParameter(profileNo);

				if (pkpdInst.exceptionForCurrentProfile == 0) {
					caProcOpPrep.createAndPrintingOfSummaryAndSummaryTable(
							this, pkpdInst.parameter, pkpdInst.X, pkpdInst.Y,
							pkpdInst.extraData, pkpdInst.row, profileNo,
							numberOfSortVariable);

				}

				pkpdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

				Time t2 = new Time(System.currentTimeMillis());

				pkpdInst.workSheetOutputInst.getTextoutput().add(
						StringUtils.rightPad("", 5)
								+ StringUtils.rightPad("Time at end is:", 20)
								+ t2);
				pkpdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

				pkpdInst.workSheetOutputInst
						.getTextoutput()
						.add(
								"\r\n\r\n"
										+ StringUtils
												.rightPad(
														"*************************************************"
																+ "**********************************",
														80) + "\r\n\r\n");
				pkpdInst.workSheetOutputInst
						.getTextoutput()
						.add(
								StringUtils.rightPad("", 15)
										+ StringUtils
												.rightPad(
														"End of the Output of the Profile Number:",
														5) + temp1);
				pkpdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");
				pkpdInst.workSheetOutputInst
						.getTextoutput()
						.add(
								"\r\n\r\n"
										+ StringUtils
												.rightPad(
														"*************************************************"
																+ "**********************************",
														80) + "\r\n\r\n");
				pkpdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");
				pkpdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");
			}

			String string = systemTime();
			caProcOpPrep.fillingUpHistoryDisplayTable(this, string);

			ResultsStructureStorer sssrInstance = new ResultsStructureStorer();

			sssrInstance.storeSpreedSheetResults();

		}
	}

	public void removeMissingData(int profileNo) {
		

		
		int noOfNonMissingPoint = 0;
		
		for (int i = 0; i < pkpdInst.X.length; i++) {
			if(Double.isNaN(pkpdInst.Y[i][0]) == true || (pkpdInst.Y[i][0] == -3.14159))
			{
				pkpdInst.Y[i][0] =  -3.14159265359;
			}
			
			if(pkpdInst.Y[i][0] == -3.14159265359)
			{
				
			}else
			{
				noOfNonMissingPoint++;
			}
			
		}
		
			double[] tempX = new double[pkpdInst.X.length];
			double[] tempY = new double[pkpdInst.Y.length];
			
			for (int i = 0; i < tempY.length; i++) {
				tempX[i] = pkpdInst.X[i][0];
				tempY[i] = pkpdInst.Y[i][0];
			}
			
			pkpdInst.X = new double[noOfNonMissingPoint][1];
			pkpdInst.Y = new double[noOfNonMissingPoint][1];
			
			int count = 0;
			for (int i = 0; i < tempX.length; i++) {
				
				if(tempY[i] == -3.14159265359)
				{
					
				}else
				{
					pkpdInst.X[count][0] = tempX[i];
					pkpdInst.Y[count][0] = tempY[i];
					count++;
				}
			}
			
			pkpdInst.row[0] = noOfNonMissingPoint;
			subjectObsNos[profileNo] = noOfNonMissingPoint;
	
	}

	void computeInitParamUsingGAOrCS(ApplicationInfo appInfo,
			int numberOfSortVariable, int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {

		if (valueForParameterBoundary == 1) {

			caProcIpPrep.retrievingParamBoundary(this, appInfo, ii);

		} else if (valueForParameterBoundary == 0) {
			caProcIpPrep.getDefaultParamBoundary(this);
		}

		pkpdInst.simplexDefined = 1;

		for (int parCount = 0; parCount < noOfParam; parCount++)
			pkpdInst.initSimplex[noOfParam][parCount] = pkpdInst.initSimplex[parCount][parCount];

		pkpdInst.nPop = 50;
		if ((pkpdInst.methodNo == 1 || pkpdInst.methodNo == 2 || pkpdInst.methodNo == 3)
				&& procInputInst.getModelInputTab2Obj()
						.getSourceOfIntParamValue() == 0
				&& procInputInst.getModelInputTab2Obj()
						.getPreviouslyGenInitVal() == 0) {
			calculataeInitParamValue(numberOfSortVariable, ii);

		} else {

			if (procInputInst.getModelInputTab2Obj()
					.getPreviouslyGenInitVal() == 1)
				pkpdInst.parameter = procInputInst.getInitialParameterValueObj()
						.getCodeGenetratedInitialValue();
			else
				for (int i = 0; i < pkpdInst.parameter.length; i++)
					pkpdInst.parameter[i] = procInputInst
							.getInitialParameterValueObj()
							.getInitialParameterValueAt(i);

			for (int i = 0; i < pkpdInst.parameter.length; i++) {
				initial[i] = pkpdInst.parameter[i];
			}

		}
		pkpdInst.simplexDefined = 0;
	}
	

	public void calculataeInitParamValue(int numberOfSortVariable, int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {
	
		ApplicationInfo appInfo = appInfoinst;
		procInputInst = pkpdInst.copyProcessingInput();
		ifForInitialValue = true;
		geneticAlgoInst = new GeneticAlgorithm();
		psoInst = new HybridPSODriver();
		beeTestInst = new BeeTest();
		
		pkpdInst.parameter = new double[noOfParam];
		procInputInst.getInitialParameterValueObj()
				.setCodeGenetratedInitialValue(
						new double[pkpdInst.parameter.length]);

		pkpdInst.setProcessingInput(procInputInst);
		initValByCSInst = new PkCurveStripper();
		
		if (procInputInst.getModelInputTab2Obj()
				.getInitialParamByCS() == 1
				&&

				procInputInst.getModelInputTab1Obj()
						.getNumberOfDose() == 1) {

			pkpdInst.parameter = initValByCSInst.parameterByModel(
					pkpdInst.modelNumber, pkpdInst.X, pkpdInst.Y, noOfParam,
					dose[0], infusionTime[0]);
			
					

			if (pkpdInst.exceptionForCurrentProfile == 0) {
				for (int i = 0; i < pkpdInst.parameter.length; i++)
					pkpdInst.parameter[i] = pkpdInst.parameter[i];
				procInputInst = pkpdInst.copyProcessingInput();
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkpdInst.parameter.length]);
				pkpdInst.setProcessingInput(procInputInst);
			} else {
				for (int i = 0; i < pkpdInst.upperBound.length; i++) {
					if (pkpdInst.upperBound[i] <= 0
							|| pkpdInst.lowerBound[i] < 0) {
						TestException inst = new TestException();
						inst.throwCustomException(119);
						pkpdInst.exceptionForCurrentProfile = 1;
					}
				}

				if (pkpdInst.exceptionForCurrentProfile == 0) {
					TestException inst = new TestException();
					inst.throwCustomException(118);
					pkpdInst.workSheetOutputInst
							.getTextoutput()
							.add(
									"\r\n"
											+ StringUtils
													.rightPad(
															"*************************************************"
																	+ "**********************************",
															80) + "\r\n\r\n");
					pkpdInst
							.getWorkSheetOutputInst()
							.getTextoutput()
							.add(
									StringUtils
											.rightPad(
													"118: Problems occurred during Curve Stripping. Genetic Algorithm will be used for initial parameters estimation.",
													100));
					pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
							"\r\n\r\n");
				

					GridSearch gdInstance = new GridSearch();
					gdInstance.GridSearch(pkpdInst.upperBound,
							pkpdInst.lowerBound, pkpdInst.X, pkpdInst.Y,
							pkpdInst.extraData, pkpdInst.row, pkpdInst.delta);

				
					procInputInst
							.getInitialParameterValueObj()
							.setCodeGenetratedInitialValue(
									new double[pkpdInst.parameter.length]);
					pkpdInst.setProcessingInput(procInputInst);
				}

			}

		} else if(procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 1){

			for (int i = 0; i < pkpdInst.upperBound.length; i++) {
				if (pkpdInst.upperBound[i] <= 0 || pkpdInst.lowerBound[i] < 0) {
					pkpdInst.exceptionForCurrentProfile = 1;
				}

				if (pkpdInst.exceptionForCurrentProfile == 1) {

					TestException inst = new TestException();
					inst.throwCustomException(119);

					pkpdInst.workSheetOutputInst
							.getTextoutput()
							.add(
									"\r\n"
											+ StringUtils
													.rightPad(
															"*************************************************"
																	+ "**********************************",
															80) + "\r\n\r\n");
					pkpdInst
							.getWorkSheetOutputInst()
							.getTextoutput()
							.add(
									StringUtils
											.rightPad(
													"119: Insufficient informations for Genetic Algorithm. Parameters value can not be estimated for Profile Number :"
															+ pkpdInst.currentProfileNumber,
													120));
					pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
							"\r\n\r\n");

				}
			}

			if (pkpdInst.exceptionForCurrentProfile == 0) {
				

				gdInstance = new GridSearch();
				pkpdInst.parameter = gdInstance.GridSearch(pkpdInst.upperBound,
						pkpdInst.lowerBound, pkpdInst.X, pkpdInst.Y,
						pkpdInst.extraData, pkpdInst.row, pkpdInst.delta);

								
				for (int i = 0; i < pkpdInst.parameter.length; i++)
					pkpdInst.parameter[i] = pkpdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkpdInst.parameter.length]);
				pkpdInst.setProcessingInput(procInputInst);
			}
			

		}else if(procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 1)
		{
			
			


			for (int i = 0; i < pkpdInst.upperBound.length; i++) {
				if (pkpdInst.upperBound[i] <= 0 || pkpdInst.lowerBound[i] < 0) {
					pkpdInst.exceptionForCurrentProfile = 1;
				}

				if (pkpdInst.exceptionForCurrentProfile == 1) {

					TestException inst = new TestException();
					inst.throwCustomException(119);

					pkpdInst.workSheetOutputInst
							.getTextoutput()
							.add(
									"\r\n"
											+ StringUtils
													.rightPad(
															"*************************************************"
																	+ "**********************************",
															80) + "\r\n\r\n");
					pkpdInst
							.getWorkSheetOutputInst()
							.getTextoutput()
							.add(
									StringUtils
											.rightPad(
													"119: Insufficient informations for Genetic Algorithm. Parameters value can not be estimated for Profile Number :"
															+ pkpdInst.currentProfileNumber,
													120));
					pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
							"\r\n\r\n");

				}
			}

			if (pkpdInst.exceptionForCurrentProfile == 0) {
				
				 pkpdInst.parameter = geneticAlgoInst.GeneticAlgorithm(
						 pkpdInst.upperBound, pkpdInst.lowerBound, pkpdInst.X,
						 pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						 pkpdInst.delta);
				
							
				

				for (int i = 0; i < pkpdInst.parameter.length; i++)
					pkpdInst.parameter[i] = pkpdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkpdInst.parameter.length]);
				pkpdInst.setProcessingInput(procInputInst);
			}
			

		
			
			 
		}

		initParamVal = new String[pkpdInst.parameter.length];
		for (int i = 0; i < pkpdInst.parameter.length; i++) {
			initParamVal[i] = pkpdInst.formatting(
					pkpdInst.parameter[i], false);
		}
		
		
		ifForInitialValue = false;

		String string = "";
		string = string + "Calculated Initial Parameter Value" + "\n";
		for (int i = 0; i < pkpdInst.parameter.length; i++)
			string = string
					+ appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL()
							.get(
									appInfo
											.getProjectInfoAL()
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPkInfo().getProcessingInputs()
							.getProfileAndParamInfoObj()
							.getParameterNameForCA()[i]
					+ ": "
					+ "  "
					+ pkpdInst.formatting(pkpdInst.parameter[i],
							false);

		for (int i = 0; i < pkpdInst.parameter.length; i++) {
			appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getInitialParameterValueObj()
					.setCodeGenetratedInitialValueAt(i, pkpdInst.parameter[i]);

			appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getInitialParameterValueObj()
					.setInitialParameterValueAt(ii * noOfParam + i,
							numberOfSortVariable + 1,
							initParamVal[i] + "");
		}

		for (int i = 0; i < pkpdInst.parameter.length; i++) {
			initial[i] = pkpdInst.parameter[i];
		}
	}

	
	

	public void estimateModelParameter(int profileNo) throws RowsExceededException,
			WriteException, BiffException, IOException {

	
		pkpdInst.setIfCallingForParameterCalculation(true);

		paramEstimateInst = new ParameterEstimator();
		
		paramEstimateInst.estimateModelParameter(profileNo);
		
	
		if (appInfoinst
				.getProjectInfoAL()
				.get(appInfoinst.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfoinst.getProjectInfoAL().get(
								appInfoinst.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfoinst
								.getProjectInfoAL()
								.get(appInfoinst.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfoinst
												.getProjectInfoAL()
												.get(
														appInfoinst
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getModelInputTab2Obj()
				.isPropagateFinalEstimae() == true
				&& pkpdInst.currentProfileNumber == 0)
			propFinalEstimate = pkpdInst.parameter;

		pkpdInst.setIfCallingForParameterCalculation(false);
	}

	void createAndPrintingOfSummaryAndSummaryTable(double[] parameter,
			double[][] x, double[][] y, double[][] extraData, int[] row,
			int ii, int numberOfSortVariable) throws RowsExceededException,
			WriteException, BiffException, IOException {
		caProcOpPrep.createAndPrintingOfSummaryAndSummaryTable(this, parameter,
				x, y, extraData, row, ii, numberOfSortVariable);
	}

	double[] generateDataAndPlotWeightedPredYAndResidualY(int ii,
			int numberOfSortVariable, ApplicationInfo appInfo,
			double[] residual, double[][] scaledWts)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		return caProcOpPrep.generateDataAndPlotWeightedPredYAndResidualY(this,
				ii, numberOfSortVariable, appInfo, residual, scaledWts);
	}

	void writingRequiredIterationToOutput() throws RowsExceededException,
			WriteException, BiffException, IOException {
		caProcOpPrep.writingRequiredIterationToOutput(this);
	}

	void wrinitingSrsAndWRSSToTextArea(double[] parameter, double Combined_srs,
			double WRSS) throws RowsExceededException, WriteException,
			BiffException, IOException {
		caProcOpPrep.wrinitingSrsAndWRSSToTextArea(this, parameter,
				Combined_srs, WRSS);
	}

	void writingSummaryDataToSummaryTable(double[] X_val, double[] Y_val,
			double[][] scaledWts, double error, double[] sdPred,
			double[] stdRes, int idx, int r, String[] s1)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcOpPrep.writingSummaryDataToSummaryTable(this, X_val, Y_val,
				scaledWts, error, sdPred, stdRes, idx, r, s1);
	}

	void writingSummaryDataToPredictedValTable(double[] X_val, double[] Y_val,
			double[][] scaledWts, double error, double[] sdPred,
			double[] stdRes, int idx, int r, String[] s)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcOpPrep.writingSummaryDataToPredictedValTable(this, X_val, Y_val,
				scaledWts, error, sdPred, stdRes, idx, r, s);
	}

	void writingsummaryDataToTextArea(double[] X_val, double[] Y_val,
			double[][] scaledWts, double error, double[] sdPred,
			double[] stdRes, int idx, int r) throws RowsExceededException,
			WriteException, BiffException, IOException {
		caProcOpPrep.writingsummaryDataToTextArea(this, X_val, Y_val,
				scaledWts, error, sdPred, stdRes, idx, r);
	}

	void writeHeaderForSummaryTable() throws RowsExceededException,
			WriteException, BiffException, IOException {
		caProcOpPrep.writeHeaderForSummaryTable();
	}

	void misOutpuAndSecondParamCal(double[] parameter, double[][] x,
			double[][] y, double[][] extraData, int[] row, double srs,
			double WRSS, double[] time, int profileNo, int numberOfSortVariable)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcOpPrep.misOutpuAndSecondParamCal(this, parameter, x, y,
				extraData, row, srs, WRSS, time, profileNo, numberOfSortVariable);
	}

	double[][] computeVarCovarianceMatrix(double[][] aInv, double SD) {
		return caProcOpPrep.computeVarCovarianceMatrix(this, aInv, SD);

	}

	double[][] computeCorrelationMatrix(double[][] aInv) {
		return caProcOpPrep.computeCorrelationMatrix(this, aInv);
	}

	void writingDiagnosticsToTextArea(double condNumber, double AIC, double SC,
			int rank, double trace) throws RowsExceededException,
			WriteException, BiffException, IOException {
		caProcOpPrep.writingDiagnosticsToTextArea(this, condNumber, AIC, SC,
				rank, trace);
	}

	void fillingUpInitialParameterTable(double[] parameter,
			double[] lowerBound, double[] upperBound, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcOpPrep.fillingUpInitialParameterTable(this, parameter,
				lowerBound, upperBound, ii);
	}

	void generatePlotOutput(double[] time, double[] conc, double[] calcYval2,
			String profile, double[] weightedPredictedY,
			double[] weightedResidualY) throws RowsExceededException,
			WriteException, BiffException, IOException {
		caProcOpPrep.generatePlotOutput(this, time, conc, calcYval2, profile,
				weightedPredictedY, weightedResidualY);
	}

	void fillingUpCorrelationMatrix(int ii, double[][] C)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcOpPrep.fillingUpCorrelationMatrix(this, ii, C);
	}

	void fillingUpPartialDerivativeTable(double[] time,
			int numberOfSortVariable, int profileNo) throws RowsExceededException,
			WriteException, BiffException, IOException {
		caProcOpPrep.fillingUpPartialDerivativeTable(this, time,
				numberOfSortVariable, profileNo);
	}

	void fillingUpVarienceCoVarienceMatrixTableAndTextOutput(
			double[][] varienceCovarienceMatrix, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcOpPrep.fillingUpVarienceCoVarienceMatrixTableAndTextOutput(this,
				varienceCovarienceMatrix, ii);
	}

	void writingParametersToTextAndHDT(double[] parameter, double[] initial,
			double[][] aInv, double sD, int ii, double[] CV, double[] SE)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcOpPrep.writingParametersToTextAndHDT(this, parameter, initial,
				aInv, sD, ii, CV, SE);
	}

	void fillingUpDosingDisplayTable() throws RowsExceededException,
			WriteException, BiffException, IOException {
		caProcOpPrep.fillingUpDosingDisplayTable(this);
	}

	void fillingUpUserSettingsTable() throws RowsExceededException,
			WriteException, BiffException, IOException {
		caProcOpPrep.fillingUpUserSettingsTable(this);
	}

	void writingHeaderToTextOutput(String stringTime)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcOpPrep.writingHeaderToTextOutput(stringTime);
	}

	void writingInputToTextOutput() throws RowsExceededException,
			WriteException, BiffException, IOException {
		caProcOpPrep.writingInputToTextOutput(this);
	}

	void fillingUpEigenValueDisplayTable(double[] eigenValue)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcOpPrep.fillingUpEigenValueDisplayTable(this, eigenValue);
	}

	void fillingUpHistoryDisplayTable(String string)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcOpPrep.fillingUpHistoryDisplayTable(this, string);
	}

	void fillingUpDiagnosticsSpreedSheet(double srs, double WRSS, double trace,
			double AIC, double SC, double condNumber)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		caProcOpPrep.fillingUpDiagnosticsSpreedSheet(this, srs, WRSS, trace,
				AIC, SC, condNumber);
	}

	protected String systemTime() {
		java.sql.Timestamp currentTime = new java.sql.Timestamp(
				new java.util.Date().getTime());
		String currenttime = String.valueOf(currentTime);
		String date = currenttime.substring(0, 10);
		String time = currenttime.substring(11, 19);
		
		return (date + "/" + time);
	}

}
