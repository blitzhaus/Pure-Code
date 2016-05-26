package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import Jama.CholeskyDecomposition;
import Jama.EigenvalueDecomposition;
import Jama.Matrix;

import view.ApplicationInfo;
import view.DisplayContents;
import view.ProcessingInputsInfo;

public class SimulationForLibraryModels {

	double[] dose;
	double[] dosingTime;
	double[] infusionTime;
	double[] infusionDose;
	double[] parameters;
	double[] X;
	double[] Y;
	int libraryModelNo;
	int numberOfSortVariables;

	PkPdInfo pkpdInstance;

	int noOfParam;
	int numberOfSortVariable;
	ApplicationInfo appInfo;
	int[] subjectObsNos;
	String[] paramName;
	double[] timeForAllProfile;
	int noOfSubjects;
	double[] eigenValue;
	WorkSheetOutputs wsOutput;
	double[] newDepenedentVar;
	ListOfDataStructures dataStructInst;
	String originalAnalysisType;
	double[] plasmaConc;
	double[] paramForLinkModel;

	ArrayList<String[]> verticalParametersAL;
	ArrayList<String[]> horizontalParametersAL;
	ArrayList<String[]> initialParameterAL;
	ArrayList<String[]> minimizationProcessAL;
	ArrayList<String[]> dosingValueAL;
	ArrayList<String[]> correlationMatrixAL;
	ArrayList<String[]> eigenValuesAL;
	ArrayList<String[]> conditionNumberAL;
	ArrayList<String[]> predictedValueAL;
	ArrayList<String[]> varienceCovarienceMatrixAL;
	ArrayList<String[]> summaryTableAL;
	ArrayList<String[]> diagnosticsAL;
	ArrayList<String[]> partialDerivativeAL;
	ArrayList<String[]> secondaryParametersAL;
	ArrayList<String[]> nonTransposedSPAL;
	ArrayList<String[]> userSettingsAL;
	ArrayList<String[]> historyAL;

	static SimulationForLibraryModels simulationInstance = null;

	public static SimulationForLibraryModels createSimulationInst() {
		if (simulationInstance == null) {
			simulationInstance = new SimulationForLibraryModels();
		}
		return simulationInstance;
	}

	public SimulationForLibraryModels() {

	}

	public void performSimulation() throws RowsExceededException,
			WriteException, BiffException, IOException {

		pkpdInstance = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInstance.copyProcessingInput();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();

		initialization(procInputInst);

		numberOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		int sum = 0;
		pkpdInstance.methodNo = procInputInst.getModelInputTab2Obj()
				.getMinimizationMethod();

		PkProcessingOutputPreparator caPropOutputInst = new PkProcessingOutputPreparator();
		String stringTime = PkParamEstimator.createPkParamEstimateInstance()
				.systemTime();

		DefaultToPreferredUnitConverter unitConversionInst = new DefaultToPreferredUnitConverter();
		double convertAmount = 1;

		try {
			convertAmount = unitConversionInst.unitConversion(procInputInst
					.getModelInputTab1Obj().getDoseUnit(), procInputInst
					.getParameterUnitsDataObj().getConcMassUnit());
		} catch (Exception e) {
			convertAmount = 1;
		}

		initializeResultStructure();

		pkpdInstance.ifSimulation = true;

		originalAnalysisType = pkpdInstance.analysisType;
		newDepenedentVar = new double[timeForAllProfile.length];
		int count = 0;
		if (pkpdInstance.analysisType.equals("pkpdlink")
				|| pkpdInstance.analysisType.equals("irm")) {
			originalAnalysisType = pkpdInstance.analysisType;
			libraryModelNo = procInputInst.getModelInputTab1Obj()
					.getModelNumberForLinkModel();
			pkpdInstance.modelNumber = libraryModelNo;
			pkpdInstance.ifAlgebraicModel = true;
			pkpdInstance.ifClearanceParam = procInputInst
					.getModelInputTab1Obj().isIfClearanceParam();
			noOfParam = procInputInst.getProfileAndParamInfoObj()
					.getNumberOfLinkParam();
			pkpdInstance.noOfParam = noOfParam;
			pkpdInstance.analysisType = "pk";
			for (int profileNo = 0; profileNo < noOfSubjects; profileNo++) {

				fillUpXVariables(profileNo);
				fillUpParametersValueForLinkModel(profileNo);
				fillUpInfusionTime(profileNo, libraryModelNo);
				fillUpDoseAndDosingTime(profileNo, convertAmount);
				double sd = 0;

				Y = new double[X.length];
				plasmaConc = calculateConcFromLibraryModel(X,
						paramForLinkModel, infusionTime, dose, dosingTime);

				sum = sum + subjectObsNos[profileNo];

				
			}

			

		}
		libraryModelNo = procInputInst.getModelInputTab1Obj()
				.getModelNumberForCA();
		caPropOutputInst.writingHeaderToTextOutput(stringTime);
		writingInputToTextOutput();

		EstimatedParamValStorer printInst = new EstimatedParamValStorer();

		sum = 0;

		for (int profileNo = 0; profileNo < noOfSubjects; profileNo++) {

			pkpdInstance.currentProfileNumber = profileNo;
			pkpdInstance.modelNumber = libraryModelNo;

			pkpdInstance.ifAlgebraicModel = procInputInst
					.getModelInputTab1Obj().isAlgebraicModel();
			pkpdInstance.ifClearanceParam = procInputInst
					.getModelInputTab1Obj().isIfClearanceParam();
			noOfParam = procInputInst.getProfileAndParamInfoObj()
					.getNumberOfParameter();
			pkpdInstance.noOfParam = noOfParam;

			pkpdInstance.analysisType = originalAnalysisType;

			fillUpXVariables(profileNo);
			fillUpParametersValue(profileNo);

			fillUpInfusionTime(profileNo, libraryModelNo);
			fillUpDoseAndDosingTime(profileNo, convertAmount);

			double sd = 0;

			Y = new double[X.length];
			Y = calculateConcFromLibraryModel(X, parameters, infusionTime,
					dose, dosingTime);

			double[][] aInv = calculateAinv(parameters, profileNo);
			printInst.parameterValuePrinting(parameters, parameters, aInv, sd);
			printingOfDosingInfo();
			fillingUpDosingDisplayTable();
			writingDoseRelatedInfoInTextoutput(1, profileNo);

			double[][] corr = computeCorrelationMatrix(aInv);

			printingOfEigenValues();

			printingOfSimulatedValue();

			printingOfPartialDerivative(profileNo);

			secondaryParamCalAndPrint();
			
			sum = sum + subjectObsNos[profileNo];

		}

		ResultsStructureStorer storingInst = new ResultsStructureStorer();
		//storingInst.storeSpreedSheetResultsForSimulation();

		pkpdInstance.setWorkSheetOutputInst(wsOutput);

	}

	
	
	public void initializeResultStructure() {
		
		ProcessingInputsInfo procInputInst = pkpdInstance.copyProcessingInput();
		
		verticalParametersAL = new ArrayList<String[]>();
		verticalParametersAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getVerticalParametersTH());

		horizontalParametersAL = new ArrayList<String[]>();
		horizontalParametersAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getHorizontalParametersTH());

		initialParameterAL = new ArrayList<String[]>();
		initialParameterAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getInitialParameterTH());

		minimizationProcessAL = new ArrayList<String[]>();
		minimizationProcessAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getMinimizationProcessTH());

		dosingValueAL = new ArrayList<String[]>();
		dosingValueAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getDosingValueTH());

		correlationMatrixAL = new ArrayList<String[]>();
		correlationMatrixAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getCorrelationMatrixTH());

		eigenValuesAL = new ArrayList<String[]>();
		eigenValuesAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getEigenValuesTH());

		conditionNumberAL = new ArrayList<String[]>();
		conditionNumberAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getConditionNumberTH());

		predictedValueAL = new ArrayList<String[]>();
		predictedValueAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getPredictedValueTH());

		varienceCovarienceMatrixAL = new ArrayList<String[]>();
		varienceCovarienceMatrixAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getVarienceCovarienceMatrixTH());

		summaryTableAL = new ArrayList<String[]>();
		summaryTableAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getSummaryTableTH());

		diagnosticsAL = new ArrayList<String[]>();
		diagnosticsAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getDiagnosticsTH());
		partialDerivativeAL = new ArrayList<String[]>();
		partialDerivativeAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getPartialDerivativeTH());

		secondaryParametersAL = new ArrayList<String[]>();
		secondaryParametersAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getSecondaryParametersTH());

		nonTransposedSPAL = new ArrayList<String[]>();
		nonTransposedSPAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getNonTransposedSPTH());

		userSettingsAL = new ArrayList<String[]>();
		userSettingsAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getUserSettingsTH());

		historyAL = new ArrayList<String[]>();
		historyAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getHistoryTH());
		
	}

	private void fillUpParametersValueForLinkModel(int ii) {

		// PK_PD pkpdInstance = PK_PD.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInstance.copyProcessingInput();

		int sum = 0;
		for (int i = 0; i < ii; i++) {

			sum = sum + noOfParam;
		}
		paramForLinkModel = new double[noOfParam];

		for (int i = 0; i < noOfParam; i++) {
			paramForLinkModel[i] = Double.parseDouble(procInputInst
					.getInitialParameterValueObj()
					.getInitialParameterValueForLinkModelAt(sum + i,
							numberOfSortVariables + 1));

		}

	}

	private void printingOfEigenValues() {
		wsOutput
				.getTextoutput()
				.add(
						"\r\n\r\n"
								+ StringUtils
										.rightPad(
												"*************************************************"
														+ "*****************************************************************",
												80) + "\r\n\r\n");
		wsOutput.getTextoutput()
				.add(
						StringUtils.rightPad("", 40)
								+ StringUtils.rightPad("Eigen Values", 10)
								+ "\r\n\r\n");

		wsOutput.getTextoutput()
				.add(
						StringUtils.rightPad("", 5)
								+ StringUtils.rightPad("Number", 10) + "\t"
								+ StringUtils.rightPad("Eigen Values", 10)
								+ "\r\n\r\n");

		for (int i = 0; i < eigenValue.length; i++) {
			wsOutput.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(i + "", 10) + "\t"
							+ StringUtils.rightPad(eigenValue[i] + "", 10)
							+ "\r\n\r\n");
			String[] s = new String[numberOfSortVariable + 2];

			for (int k = 0; k < numberOfSortVariable; k++) {
				s[k] = pkpdInstance.dataSortVariables[pkpdInstance
						.getCurrentProfileNumber()][k];
			}
			s[numberOfSortVariable] = i + "";
			s[numberOfSortVariable + 1] = pkpdInstance.formatting(
					eigenValue[i],false);

			eigenValuesAL.add(s);
		}

	}

	private void printingOfDosingInfo() {
		wsOutput
				.getTextoutput()
				.add(
						"\r\n\r\n"
								+ StringUtils
										.rightPad(
												"*************************************************"
														+ "*****************************************************************",
												80) + "\r\n\r\n");
		wsOutput.getTextoutput().add(StringUtils.rightPad("", 40)
		/* + "\t" */
		+ StringUtils.rightPad("Dosing Informations", 10) + "\r\n\r\n");

	}

	void fillingUpDosingDisplayTable() throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] forDosingSpreedSheet = new String[numberOfSortVariable + 2];
		for (int k = 0; k < numberOfSortVariable; k++) {
			forDosingSpreedSheet[k] = pkpdInstance.dataSortVariables[pkpdInstance.currentProfileNumber][k];
		}

		forDosingSpreedSheet = new String[numberOfSortVariable + 2];
		for (int k = 0; k < numberOfSortVariable; k++) {
			forDosingSpreedSheet[k] = pkpdInstance.dataSortVariables[pkpdInstance.currentProfileNumber][k];
		}

		forDosingSpreedSheet[numberOfSortVariable] = "Number of Doses";
		forDosingSpreedSheet[numberOfSortVariable + 1] = dose.length + "";
		dosingValueAL.add(forDosingSpreedSheet);

		for (int i = 0; i < dose.length; i++) {
			forDosingSpreedSheet = new String[numberOfSortVariable + 2];
			for (int k = 0; k < numberOfSortVariable; k++) {
				forDosingSpreedSheet[k] = pkpdInstance.dataSortVariables[pkpdInstance.currentProfileNumber][k];
			}

			forDosingSpreedSheet[numberOfSortVariable] = "Dose No: " + (i + 1);
			forDosingSpreedSheet[numberOfSortVariable + 1] = pkpdInstance
					.formatting(dose[i], false);
			dosingValueAL.add(forDosingSpreedSheet);

			forDosingSpreedSheet = new String[numberOfSortVariable + 2];
			for (int k = 0; k < numberOfSortVariable; k++) {
				forDosingSpreedSheet[k] = pkpdInstance.dataSortVariables[pkpdInstance.currentProfileNumber][k];
			}

			forDosingSpreedSheet[numberOfSortVariable] = "Dosing Time of Dose No: "
					+ (i + 1);
			forDosingSpreedSheet[numberOfSortVariable + 1] = pkpdInstance
					.formatting(dosingTime[i], false);
			dosingValueAL.add(forDosingSpreedSheet);

			if (infusionTime[i] != 0) {
				forDosingSpreedSheet = new String[numberOfSortVariable + 2];
				for (int k = 0; k < numberOfSortVariable; k++) {
					forDosingSpreedSheet[k] = pkpdInstance.dataSortVariables[pkpdInstance.currentProfileNumber][k];
				}

				forDosingSpreedSheet[numberOfSortVariable] = "End Time of Dose No: "
						+ (i + 1);
				forDosingSpreedSheet[numberOfSortVariable + 1] = pkpdInstance
						.formatting(infusionTime[i], false);
				dosingValueAL.add(forDosingSpreedSheet);
			}

		}

	}

	void writingDoseRelatedInfoInTextoutput(double convertAmount, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ProcessingInputsInfo procInputInst = pkpdInstance.copyProcessingInput();

		int numberOfDose = procInputInst.getModelInputTab1Obj()
				.getNumberOfDose();


		wsOutput.getTextoutput().add(
				StringUtils.rightPad("", 5)
						+ StringUtils.rightPad("Number of Doses:", 15)
						/* + "\t" */
						+ StringUtils
								.rightPad(Double.toString(numberOfDose), 5)
						+ "\r\n\r\n");

		dosingInfoPrintingInText();

	}

	private void dosingInfoPrintingInText() {

		ProcessingInputsInfo procInputInst = pkpdInstance.copyProcessingInput();
		String temp = "";
		String tempHeader = "";
		int noOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		temp = StringUtils.rightPad("", 5);
		tempHeader = StringUtils.rightPad("", 5);
		String[][] dosingData = procInputInst.getDosingDataObj()
				.getDosingDSForNCA();

		int noOfCol = dosingData[0].length - noOfSortVar;

		if (noOfCol == 4) {
			temp = temp + StringUtils.rightPad("Dose Number", 15) + "\t"
					+ StringUtils.rightPad("Dose", 15) + "\t"
					+ StringUtils.rightPad("Time of Dose", 15) + "\t"
					+ StringUtils.rightPad("Length of Infusion", 15);

			// adding space between header and values
			tempHeader = tempHeader + StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15);

		} else if (noOfCol == 3) {
			temp = temp + StringUtils.rightPad("Dose Number", 15) + "\t"
					+ StringUtils.rightPad("Dose", 15) + "\t"
					+ StringUtils.rightPad("Time of Dose", 15);

			// adding space between header and values
			tempHeader = tempHeader + StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15);

		} else if (noOfCol == 5) {
			temp = temp + StringUtils.rightPad("Dose Number", 15) + "\t"
					+ StringUtils.rightPad("Dose", 15) + "\t"
					+ StringUtils.rightPad("Time of Dose", 15) + "\t"
					+ StringUtils.rightPad("Infusion Dose", 15) + "\t"
					+ StringUtils.rightPad("Length of Infusion", 15);

			// adding space between header and values
			tempHeader = tempHeader + StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15);

		}

		temp = temp + "\r\n";
		tempHeader = tempHeader + "\r\n";
		wsOutput.getTextoutput().add(temp);
		wsOutput.getTextoutput().add(tempHeader);

		int noOfRow = procInputInst.getModelInputTab1Obj().getNumberOfDose();

		int startRow = pkpdInstance.currentProfileNumber * noOfRow;
		int endRow = startRow + noOfRow;

		for (int i = startRow; i < endRow; i++) {
			temp = StringUtils.rightPad("", 5);
			temp = temp + StringUtils.rightPad(dosingData[i][noOfSortVar], 25)
					+ "\t";
			for (int j = noOfSortVar + 1; j < dosingData[0].length; j++) {

				temp = temp
						+ StringUtils.rightPad(pkpdInstance.formatting(Double
								.parseDouble(dosingData[i][j]),
								false), 15) + "\t";
			}

			temp = temp + "\r\n\r\n";
			wsOutput.getTextoutput().add(temp);
		}

	}

	private void initialization(ProcessingInputsInfo procInputInst) {

		appInfo = dataStructInst.appInfo;

		noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();

		numberOfSortVariable = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		subjectObsNos = procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo();

		timeForAllProfile = procInputInst.getProfileAndParamInfoObj()
				.getTimeForAllProfile();

		paramName = procInputInst.getProfileAndParamInfoObj()
				.getParameterNameForCA();

		noOfSubjects = procInputInst.getProfileAndParamInfoObj()
				.getNoOfSubject();
		pkpdInstance.pdIncrement = Double.parseDouble(procInputInst
				.getModelInputTab2Obj().getIncForPartialDerivative());
		pkpdInstance.noOfParam = noOfParam;
		pkpdInstance.numberOfSortVar = numberOfSortVariable;
		pkpdInstance.dataSortVariables = procInputInst
				.getProfileAndParamInfoObj().getDataSortVariables();
		pkpdInstance.noOfSecParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfSecondaryParameters();
		pkpdInstance.paramName = procInputInst.getProfileAndParamInfoObj()
				.getParameterNameForCA();
		pkpdInstance.secParamName = procInputInst.getProfileAndParamInfoObj()
				.getSecondaryParameterNameForCA();
		wsOutput = new WorkSheetOutputs();
	}

	private void secondaryParamCalAndPrint() throws RowsExceededException,
			WriteException, BiffException, IOException {

		SecondaryParamComputer secondParamCalInst = new SecondaryParamComputer();
		double doseAmount = dose[0];
		double sd = 0;
		double[][] aInverse = null;

		secondParamCalInst.calculateSecondaryParameter(parameters,
				libraryModelNo, doseAmount, aInverse, sd);
	}

	private void printingOfPartialDerivative(int profileNo) throws RowsExceededException,
			WriteException, BiffException, IOException {
		GaussNewton gnInst = new GaussNewton();
		double[][] extraData = null;
		int[] row = { X.length };
		double[] delta = null;
		int fn_no = 0;
		double[][] X1 = new double[X.length][1];
		double[][] Y1 = new double[Y.length][1];

		for (int i = 0; i < Y1.length; i++) {
			X1[i][0] = X[i];
			Y1[i][0] = Y[i];

		}
		double[][] jaco = gnInst.Jacobian(parameters, infusionTime, dose,
				dosingTime, X1, Y1, extraData, delta, fn_no, row, profileNo);

		wsOutput
				.getTextoutput()
				.add(
						"\r\n\r\n"
								+ StringUtils
										.rightPad(
												"*************************************************"
														+ "*****************************************************************",
												80) + "\r\n\r\n");
		wsOutput.getTextoutput().add(
				StringUtils.rightPad("", 40)
						+ StringUtils.rightPad(
								"PARTIAL DERIVATIVE INFORMATION", 20)
						+ "\r\n\r\n");

		String temp = StringUtils.rightPad("", 5);

		if (pkpdInstance.analysisType.equals("pk")
				|| pkpdInstance.analysisType.equals("pkpdlink")
				|| pkpdInstance.analysisType.equals("mm")
				|| pkpdInstance.analysisType.equals("irm")) {
			temp = temp + "Time" + "\t";
		} else {
			temp = temp + "Conc" + "\t";
		}

		for (int j = 0; j < paramName.length; j++) {

			temp = temp + StringUtils.rightPad(paramName[j], 10) + "\t";
		}

		temp = temp + "\r\n";

		wsOutput.getTextoutput().add(temp);

		for (int i = 0; i < jaco.length; i++) {
			String[] s = new String[numberOfSortVariable + 1 + jaco[0].length];
			for (int k = 0; k < numberOfSortVariable; k++) {
				s[k] = pkpdInstance.dataSortVariables[pkpdInstance
						.getCurrentProfileNumber()][k];
			}

			s[numberOfSortVariable] = X1[i][0] + "";
			temp = StringUtils.rightPad("", 5);
			temp = temp + X1[i][0] + "\t";
			for (int j = 0; j < jaco[0].length; j++) {

				temp = temp
						+ StringUtils.rightPad(pkpdInstance.formatting(
								jaco[i][j], false), 10) + "\t";
				s[numberOfSortVariable + j + 1] = pkpdInstance.formatting(
						jaco[i][j], false);
			}

			temp = temp + "\r\n";

			partialDerivativeAL.add(s);
			wsOutput.getTextoutput().add(temp);

		}
	}

	private void printingOfSimulatedValue() {

		
		wsOutput
				.getTextoutput()
				.add(
						"\r\n\r\n"
								+ StringUtils
										.rightPad(
												"*************************************************"
														+ "*****************************************************************",
												80) + "\r\n\r\n");
		wsOutput.getTextoutput().add(
				StringUtils.rightPad("", 40)
						+ StringUtils.rightPad("Summary of Simulation", 20)
						+ "\r\n\r\n");

		wsOutput.getTextoutput().add(
				StringUtils.rightPad("", 5) + StringUtils.rightPad("Time", 10)
						+ "\t" + StringUtils.rightPad("Predicted Conc", 15)

						+ "\r\n");

		for (int i = 0; i < X.length; i++) {
			wsOutput.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(pkpdInstance.formatting(
									X[i], true), 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									Y[i], false), 15)

							+ "\r\n");

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

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();

		wsOutput
				.getTextoutput()
				.add(
						"\r\n\r\n"
								+ StringUtils
										.rightPad(
												"*************************************************"
														+ "*****************************************************************",
												80) + "\r\n\r\n");
		wsOutput.getTextoutput().add(
				StringUtils.rightPad("", 40)
						/* + "\t" */
						+ StringUtils.rightPad(
								"CORRELATION MATRIX OF THE ESTIMATES", 10)
						+ "\r\n\r\n");

		String temp = "";

		temp = StringUtils.rightPad("", 5)
				+ StringUtils.rightPad("Parameter", 10) + "\t";

		for (int i = 0; i < parameters.length; i++) {

			temp = temp + StringUtils.rightPad(paramName[i], 10) + "\t";

		}

		wsOutput.getTextoutput().add(temp);

		wsOutput.getTextoutput().add("\r\n");
		for (int i = 0; i < noOfParam; i++) {

			temp = "";
			for (int j = 0; j <= i; j++) {
				if (j == 0) {

					temp = temp
							+ StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(paramName[i], 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdInst.formatting(C[i][j],
									false), 10);

				} else {

					temp = temp
							+ "\t"
							+ StringUtils.rightPad(pkpdInst.formatting(C[i][j],
									false), 10);

				}

			}
			wsOutput.getTextoutput().add(temp);
			wsOutput.getTextoutput().add("\r\n");

		}

		return C;
	}

	private double[][] calculateAinv(double[] parameters2, int profileNo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		double[][] aInv;
		GaussNewton gnInst = new GaussNewton();
		double[][] extraData = null;
		int[] row = { X.length };
		double[][] X1 = new double[X.length][1];
		double[][] Y1 = new double[Y.length][1];

		for (int i = 0; i < Y1.length; i++) {
			X1[i][0] = X[i];
			Y1[i][0] = Y[i];

		}

		double[][] A = gnInst.CalculateA_mat(parameters, infusionTime, dose,
				dosingTime, X1, Y1, extraData, row, profileNo);
		double condNumber = gnInst.ConditionNumber(A);

		double[][] diag = new double[noOfParam][noOfParam];
		aInv = gnInst.inverseMatrix(A);

		Matrix A_mat = new Matrix(A);
		int rank = A_mat.rank();
		double trace = A_mat.trace();
		EigenvalueDecomposition ED = A_mat.eig();

		eigenValue = ED.getRealEigenvalues();
		Arrays.sort(eigenValue);

		return aInv;
	}

	private void printingOfOutput(double[] x2, double[] y2) {
		System.out.println(" Predicted conc value");
		for (int i = 0; i < y2.length; i++) {
			System.out.println("\t"
					+ pkpdInstance.formatting(x2[i], false)
					+ "\t"
					+ pkpdInstance.formatting(y2[i], false));
		}

	}

	private void fillUpInfusionTime(int ii, int modelNumber)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		// PK_PD pkpdInstance = PK_PD.createPKPDInstance();

		ProcessingInputsInfo procInputinst = pkpdInstance.copyProcessingInput();

		int noOfDose = procInputinst.getModelInputTab1Obj().getNumberOfDose();
		infusionTime = new double[noOfDose];
		for (int i = 0; i < noOfDose; i++) {
			try {
				String S = procInputinst.getDosingDataObj().getDosingValueAt(
						ii * noOfDose + i, numberOfSortVariable + 3);

				Double.parseDouble(S);
				infusionTime[i] = Double.parseDouble(S);
			} catch (Exception e) {
				infusionTime[i] = 0;
			}

		}

		if (modelNumber == 15 || modelNumber == 16 || modelNumber == 17) {
			for (int i = 0; i < noOfDose; i++) {
				try {
					String S1 = procInputinst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									numberOfSortVariable + 3);

					String S2 = procInputinst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									numberOfSortVariable + 4);

					Double.parseDouble(S1);
					infusionDose[0] = Double.parseDouble(S1);

					Double.parseDouble(S2);
					infusionTime[0] = Double.parseDouble(S2);

				} catch (Exception e) {
					infusionDose[0] = 0;
					infusionTime[0] = 0;

				}
			}
		}

	}

	private void fillUpDoseAndDosingTime(int ii, double convertAmount)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		// PK_PD pkpdInstance = PK_PD.createPKPDInstance();

		ProcessingInputsInfo procInputInst = pkpdInstance.copyProcessingInput();
		int noOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();

		dose = new double[noOfDose];
		dosingTime = new double[noOfDose];

		if (pkpdInstance.analysisType.equals("pd")) {

		} else {
			for (int i = 0; i < noOfDose; i++) {
				try {
					String S = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									numberOfSortVariable + 1);

					String S1 = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									numberOfSortVariable + 2);

					Double.parseDouble(S1);
					dosingTime[i] = Double.parseDouble(S1);
					Double.parseDouble(S);
					dose[i] = Double.parseDouble(S);

					dose[i] = dose[i] * convertAmount;
				} catch (Exception e) {
					e.printStackTrace();
					dose[i] = 1;
					dosingTime[i] = 0;
				}

			}

			for (int i = 0; i < noOfDose; i++) {
				try {
					String S = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									numberOfSortVariable + 2);

					Double.parseDouble(S);
					dosingTime[i] = Double.parseDouble(S);
				} catch (Exception e) {
					dosingTime[i] = 0;
				}

			}
		}

	}

	private void fillUpParametersValue(int ii) {

		PkPdInfo pkpdInstance = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInstance.copyProcessingInput();

		int sum = 0;
		for (int i = 0; i < ii; i++) {

			sum = sum + noOfParam;
		}
		parameters = new double[noOfParam];

		for (int i = 0; i < noOfParam; i++) {
			parameters[i] = Double.parseDouble(procInputInst
					.getInitialParameterValueObj().getInitialParameterValueAt(
							sum + i, numberOfSortVariables + 1));

		}

	}

	private void fillUpXVariables(int ii) {

		int sum = 0;
		for (int i = 0; i < ii; i++) {

			sum = sum + subjectObsNos[ii];
		}

		X = new double[subjectObsNos[ii]];

		for (int i = 0; i < subjectObsNos[ii]; i++) {
			X[i] = timeForAllProfile[sum + i];

		}

	}

	private double[] calculateConcFromLibraryModel(double[] X, double[] par,
			double[] infusionTime, double[] conDose, double[] conDosingTime)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		LibraryModelDefiner libModelInst = new LibraryModelDefiner();
		double[] conc = new double[X.length];
		int[] row = { X.length };
		double[][] Extra_DATA = new double[X.length][X.length];
		for (int i = 0; i < X.length; i++) {
			conc[i] = libModelInst.chooseLibraryModel(par, infusionTime,
					conDose, conDosingTime, X[i], 0, Extra_DATA, i, row);
		}
		return conc;

	}

	void writingInputToTextOutput() throws RowsExceededException,
			WriteException, BiffException, IOException {

		if (pkpdInstance.analysisType.equals("pk")) {
			writingListOfInputCommandForPk();

		} else if (pkpdInstance.analysisType.equals("pd")) {
			writingListOfInputCommandForPd();

		} else if (pkpdInstance.analysisType.equals("mm")) {
			writingListOfInputCommandForMm();
		} else if (pkpdInstance.analysisType.equals("irm")) {
			writingListOfInputCommandForIrm();
		} else if (pkpdInstance.analysisType.equals("pkpdlink")) {
			writingListOfInputCommandForLinkModel();
		}

	}

	private void writingListOfInputCommandForIrm() {

		ModelRelatedInformations mRI = new ModelRelatedInformations();
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();

		wsOutput.getTextoutput().add(
				StringUtils.rightPad(" ", 5)
						+ StringUtils
								.rightPad("Listing of Input Commands ", 40)
						+ "\r\n\r\n");

		wsOutput.getTextoutput().add(

				StringUtils.rightPad("MODEL TYPE", 20)
						+ "\t"
						+ StringUtils.rightPad(":" + "Indirect Response Model",
								50) + "\r\n");

		wsOutput
				.getTextoutput()
				.add(
						StringUtils.rightPad("PK MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(procInputInst
																		.getModelInputTab1Obj()
																		.getModelNumberForLinkModel() - 1),
												50) + "\r\n");

		wsOutput
				.getTextoutput()
				.add(
						StringUtils.rightPad("IDR MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(procInputInst
																		.getModelInputTab1Obj()
																		.getModelNumberForCA() - 1),
												50) + "\r\n");

		if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == false) {
			if (procInputInst.getModelInputTab2Obj().getOdeSolverMethod() == 1) {
				wsOutput.getTextoutput().add(

						StringUtils.rightPad("ODE SOLVER METHOD", 20)
								+ "\t"
								+ StringUtils.rightPad(":"
										+ "Runge Kutta Cashcarp", 50) + "\r\n");
			} else {

				wsOutput.getTextoutput().add(

						StringUtils.rightPad("ODE SOLVER METHOD", 20)
								+ "\t"
								+ StringUtils.rightPad(":"
										+ "Runge Kutta Fehlberg", 50) + "\r\n");

			}

		}

		wsOutput.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF VARIABLES", 20) + "\t"
						+ StringUtils.rightPad(":" + 1, 50) + "\r\n");
		wsOutput.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad(":" + noOfParam, 50) + "\r\n");

		int temp = procInputInst.getMappingDataObj()
				.getxColumnCorrespondinOriginalIndex() + 1;
		wsOutput.getTextoutput().add(
				StringUtils.rightPad("TIME COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");

		if (pkpdInst.methodNo == 1)
			wsOutput
					.getTextoutput()
					.add(
							StringUtils.rightPad("MINIMIZATION METHOD", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													":"
															+ "Gauss Newton Method (Levenberg-Marquardt Algorithm)",
													50) + "\r\n");

		else if (pkpdInst.methodNo == 2)
			wsOutput.getTextoutput().add(

					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":" + "Nelder Mead Method",
									50) + "\r\n");

		else if (pkpdInst.methodNo == 3)
			wsOutput.getTextoutput().add(
					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Gauss Newton Method (Hartley)", 50)
							+ "\r\n");

	}

	private void writingListOfInputCommandForMm() {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		ModelRelatedInformations mRI = new ModelRelatedInformations();
		wsOutput.getTextoutput().add(
				StringUtils.rightPad(" ", 5)
						+ StringUtils
								.rightPad("Listing of Input Commands ", 40)
						+ "\r\n\r\n");

		wsOutput.getTextoutput().add(

				StringUtils.rightPad("MODEL TYPE", 20)
						+ "\t"
						+ StringUtils.rightPad(":" + "Michaelis-Menten Model",
								50) + "\r\n");

		wsOutput
				.getTextoutput()
				.add(
						StringUtils.rightPad("MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(pkpdInst.modelNumber - 1),
												50) + "\r\n");

		if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == false) {
			if (procInputInst.getModelInputTab2Obj().getOdeSolverMethod() == 1) {
				wsOutput.getTextoutput().add(

						StringUtils.rightPad("ODE SOLVER METHOD", 20)
								+ "\t"
								+ StringUtils.rightPad(":"
										+ "Runge Kutta Cashcarp", 50) + "\r\n");
			} else {

				wsOutput.getTextoutput().add(

						StringUtils.rightPad("ODE SOLVER METHOD", 20)
								+ "\t"
								+ StringUtils.rightPad(":"
										+ "Runge Kutta Fehlberg", 50) + "\r\n");

			}

		}

		wsOutput.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF VARIABLES", 20) + "\t"
						+ StringUtils.rightPad(":" + 1, 50) + "\r\n");
		wsOutput.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad(":" + noOfParam, 50) + "\r\n");

		int temp = procInputInst.getMappingDataObj()
				.getxColumnCorrespondinOriginalIndex() + 1;
		wsOutput.getTextoutput().add(
				StringUtils.rightPad("TIME COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");

		if (pkpdInst.methodNo == 1)
			wsOutput
					.getTextoutput()
					.add(
							StringUtils.rightPad("MINIMIZATION METHOD", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													":"
															+ "Gauss Newton Method (Levenberg-Marquardt Algorithm)",
													50) + "\r\n");

		else if (pkpdInst.methodNo == 2)
			wsOutput.getTextoutput().add(

					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":" + "Nelder Mead Method",
									50) + "\r\n");

		else if (pkpdInst.methodNo == 3)
			wsOutput.getTextoutput().add(
					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Gauss Newton Method (Hartley)", 50)
							+ "\r\n");

	}

	private void writingListOfInputCommandForLinkModel() {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		ModelRelatedInformations mRI = new ModelRelatedInformations();
		wsOutput.getTextoutput().add(
				StringUtils.rightPad(" ", 5)
						+ StringUtils
								.rightPad("Listing of Input Commands ", 40)
						+ "\r\n\r\n");

		wsOutput.getTextoutput().add(

				StringUtils.rightPad("MODEL TYPE", 20) + "\t"
						+ StringUtils.rightPad(":" + "PK/PD link Model", 50)
						+ "\r\n");

		wsOutput
				.getTextoutput()
				.add(
						StringUtils.rightPad("PK MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(procInputInst
																		.getModelInputTab1Obj()
																		.getModelNumberForLinkModel() - 1),
												50) + "\r\n");

		wsOutput
				.getTextoutput()
				.add(
						StringUtils.rightPad("PD MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(procInputInst
																		.getModelInputTab1Obj()
																		.getModelNumberForCA() - 1),
												50) + "\r\n");

		wsOutput.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF VARIABLES", 20) + "\t"
						+ StringUtils.rightPad(":" + 1, 50) + "\r\n");
		wsOutput.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad(":" + noOfParam, 50) + "\r\n");

		int temp = procInputInst.getMappingDataObj()
				.getxColumnCorrespondinOriginalIndex() + 1;
		wsOutput.getTextoutput().add(
				StringUtils.rightPad("TIME COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");

		if (pkpdInst.methodNo == 1)
			wsOutput
					.getTextoutput()
					.add(
							StringUtils.rightPad("MINIMIZATION METHOD", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													":"
															+ "Gauss Newton Method (Levenberg-Marquardt Algorithm)",
													50) + "\r\n");

		else if (pkpdInst.methodNo == 2)
			wsOutput.getTextoutput().add(

					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":" + "Nelder Mead Method",
									50) + "\r\n");

		else if (pkpdInst.methodNo == 3)
			wsOutput.getTextoutput().add(
					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Gauss Newton Method (Hartley)", 50)
							+ "\r\n");

	}

	private void writingListOfInputCommandForPd() {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		ModelRelatedInformations mRI = new ModelRelatedInformations();
		wsOutput.getTextoutput().add(
				StringUtils.rightPad(" ", 5)
						+ StringUtils
								.rightPad("Listing of Input Commands ", 40)
						+ "\r\n\r\n");

		wsOutput.getTextoutput().add(

				StringUtils.rightPad("MODEL TYPE", 20) + "\t"
						+ StringUtils.rightPad(":" + "PD Model", 50) + "\r\n");

		wsOutput
				.getTextoutput()
				.add(
						StringUtils.rightPad("MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(pkpdInst.modelNumber - 1),
												50) + "\r\n");

		wsOutput.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF VARIABLES", 20) + "\t"
						+ StringUtils.rightPad(":" + 1, 50) + "\r\n");
		wsOutput.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad(":" + noOfParam, 50) + "\r\n");

		int temp = procInputInst.getMappingDataObj()
				.getxColumnCorrespondinOriginalIndex() + 1;
		wsOutput.getTextoutput().add(
				StringUtils.rightPad("CONCENTRATION COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");

		if (pkpdInst.methodNo == 1)
			wsOutput
					.getTextoutput()
					.add(
							StringUtils.rightPad("MINIMIZATION METHOD", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													":"
															+ "Gauss Newton Method (Levenberg-Marquardt Algorithm)",
													50) + "\r\n");

		else if (pkpdInst.methodNo == 2)
			wsOutput.getTextoutput().add(

					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":" + "Nelder Mead Method",
									50) + "\r\n");

		else if (pkpdInst.methodNo == 3)
			wsOutput.getTextoutput().add(
					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Gauss Newton Method (Hartley)", 50)
							+ "\r\n");

	}

	void writingListOfInputCommandForPk() throws RowsExceededException,
			WriteException, BiffException, IOException {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();

		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();

		ModelRelatedInformations mRI = new ModelRelatedInformations();
		wsOutput.getTextoutput().add(
				StringUtils.rightPad(" ", 5)
						+ StringUtils
								.rightPad("Listing of Input Commands ", 40)
						+ "\r\n\r\n");

		if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == true) {
			wsOutput.getTextoutput().add(

					StringUtils.rightPad("MODEL TYPE", 20) + "\t"
							+ StringUtils.rightPad(":" + "Algebraic Model", 50)
							+ "\r\n");
		} else {

			wsOutput.getTextoutput().add(
					StringUtils.rightPad("MODEL TYPE", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Differential Equation Model", 50)
							+ "\r\n");

		}

		wsOutput
				.getTextoutput()
				.add(

						StringUtils.rightPad("MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(libraryModelNo - 1),
												50) + "\r\n");

		if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == false) {
			if (procInputInst.getModelInputTab2Obj().getOdeSolverMethod() == 1) {
				wsOutput.getTextoutput().add(
						StringUtils.rightPad("ODE SOLVER METHOD", 20)
								+ "\t"
								+ StringUtils.rightPad(":"
										+ "Runge Kutta Cashcarp", 50) + "\r\n");
			} else {

				wsOutput.getTextoutput().add(
						StringUtils.rightPad("ODE SOLVER METHOD", 20)
								+ "\t"
								+ StringUtils.rightPad(":"
										+ "Runge Kutta Fehlberg", 50) + "\r\n");

			}

		}

		wsOutput.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF VARIABLES", 20) + "\t"
						+ StringUtils.rightPad(":" + 1, 50) + "\r\n");
		wsOutput.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad(":" + noOfParam, 50) + "\r\n");

		int temp = procInputInst.getMappingDataObj()
				.getxColumnCorrespondinOriginalIndex() + 1;
		wsOutput.getTextoutput().add(
				StringUtils.rightPad("TIME COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");

		if (pkpdInst.methodNo == 1)
			wsOutput
					.getTextoutput()
					.add(
							StringUtils.rightPad("MINIMIZATION METHOD", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													":"
															+ "Gauss Newton Method (Levenberg-Marquardt Algorithm)",
													50) + "\r\n");

		else if (pkpdInst.methodNo == 2)
			wsOutput.getTextoutput().add(
					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":" + "Nelder Mead Method",
									50) + "\r\n");

		else if (pkpdInst.methodNo == 3)
			wsOutput.getTextoutput().add(
					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Gauss Newton Method (Hartley)", 50)
							+ "\r\n");

	}

}
