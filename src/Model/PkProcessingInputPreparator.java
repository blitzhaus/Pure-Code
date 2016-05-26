package Model;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;

import Common.TestException;

import view.ApplicationInfo;
import view.DisplayContents;
import view.ProcessingInputsInfo;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class PkProcessingInputPreparator {
	
	PkParamEstimator pkpdMainInst;

	void readingEngineSettingsInput(PkParamEstimator pkpdMain)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = pkpdMain.appInfoinst;
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		try {
			pkpdMain.pkpdInst.methodNo = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getModelInputTab2Obj()
					.getMinimizationMethod() + 1;
		} catch (Exception e) {
			pkpdMain.pkpdInst.methodNo = 1;
		}

		pkpdMain.pkpdInst.nPop = 50;
		pkpdMain.pkpdInst.crossOverProb = 0.8;
		pkpdMain.pkpdInst.mutationProb = 0.2;

		try {
			String S = (String) appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getModelInputTab2Obj()
					.getIncForPartialDerivative();
			Double.parseDouble(S);
			pkpdMain.pkpdInst.pdIncrement = Double.parseDouble(S);
			
			if(pkpdMain.pkpdInst.pdIncrement < 0 || pkpdMain.pkpdInst.pdIncrement > 0.1)
			{
				TestException inst = new TestException();
				inst.throwCustomException(110);
				pkpdMain.pkpdInst.pdIncrement = 0.0001;
			}
			
		} catch (Exception e) {
			TestException inst = new TestException();
			inst.throwCustomException(110);
			pkpdMain.pkpdInst.pdIncrement = 0.0001;

		}

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPkInfo().getProcessingInputs().getModelInputTab1Obj().getAlgebraicModel() == false) {
			try {
				pkpdMain.pkpdInst.odeSolverChoice = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
						.getProcessingInputs().getModelInputTab2Obj()
						.getOdeSolverMethod() + 1;

			} catch (Exception e) {

				pkpdMain.pkpdInst.odeSolverChoice = 1;
			}
		} else {
			pkpdMain.pkpdInst.odeSolverChoice = 0;

		}

		try {
			String S = (String) appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getModelInputTab2Obj()
					.getRoundingOffValue();
			Integer.parseInt(S);
			pkpdMain.pkpdInst.roundTo = Integer.parseInt(S);
			
			if(pkpdMain.pkpdInst.roundTo  < 0 || pkpdMain.pkpdInst.roundTo > 8)
			{
				TestException inst = new TestException();
				inst.throwCustomException(113);
				pkpdMain.pkpdInst.roundTo = 4;
			}

		} catch (Exception e) {
			TestException inst = new TestException();
			inst.throwCustomException(113);
			pkpdMain.pkpdInst.roundTo = 4;
		}

		try {
			String S = (String) appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getModelInputTab2Obj()
					.getConvergenceCriteria();
			System.out.println("convergence:" + S);
			pkpdMain.pkpdInst.convergence = Double.parseDouble(S);
			
			if(pkpdMain.pkpdInst.convergence  < 0 )
			{
				TestException inst = new TestException();
				inst.throwCustomException(111);
				pkpdMain.pkpdInst.convergence = 0.000001;
			}
			
			
		} catch (Exception e) {
			TestException inst = new TestException();
			inst.throwCustomException(111);
			pkpdMain.pkpdInst.convergence = 0.000001;
		}

		try {
			String S = (String) appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getModelInputTab2Obj()
					.getNumberOfIterations();
			Integer.parseInt(S);
			pkpdMain.pkpdInst.iter = Integer.parseInt(S);
			
			if(pkpdMain.pkpdInst.iter <= 0 )
			{
				TestException inst = new TestException();
				inst.throwCustomException(112);
				pkpdMain.pkpdInst.convergence = 100;
			}

		} catch (Exception e) {

			TestException inst = new TestException();
			inst.throwCustomException(112);
			pkpdMain.pkpdInst.convergence = 100;
		
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getLambdaValue();

			pkpdMain.pkpdInst.lambda = Double.parseDouble(S);
		} catch (Exception e) {
			pkpdMain.pkpdInst.lambda = 0.01;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj().getMuValue();

			pkpdMain.pkpdInst.mu = Double.parseDouble(S);

		} catch (Exception e) {
			pkpdMain.pkpdInst.mu = 0.2;
		}

	}

	void readingWeightingInputs(PkParamEstimator pkpdMain) {

		ApplicationInfo appInfo = pkpdMain.appInfoinst;
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		try {
			if (procInputInst.getModelInputTab1Obj()
					.getWeightingScheme() == 0) {
				pkpdMain.pkpdInst.wtScheme = 0;

			} else if (procInputInst.getModelInputTab1Obj()
					.getWeightingScheme() == 1) {

				pkpdMain.pkpdInst.wtScheme = 1;

			}
		} catch (Exception e) {
			pkpdMain.pkpdInst.wtScheme = 0;

		}
		pkpdMain.pkpdInst.wtExp = procInputInst.getModelInputTab1Obj()
				.getWeightingIndex();
		
	}


	void readingDoseUntAndConcMassUnit(PkParamEstimator pkpdMain) {
		ApplicationInfo appInfo = pkpdMain.appInfoinst;
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		pkpdMain.pkpdInst.doseUnit = procInputInst.getModelInputTab1Obj().getDoseUnit();

		pkpdMain.pkpdInst.concMassUnit = 
						procInputInst.getParameterUnitsDataObj().getConcMassUnit();
	
		if(pkpdMain.pkpdInst.doseUnit == null && pkpdMain.pkpdInst.concMassUnit != null)
		{
			String[] str = new String[3];
			str[0] = "101";
			str[1] = "Provide dose unit";
			pkpdMain.verificationOfInputs.add(str);
			
		}
		else
			if(pkpdMain.pkpdInst.concMassUnit == null && pkpdMain.pkpdInst.doseUnit != null)
			{
				String[] str = new String[3];
				str[0] = "101";
				str[1] = "Provide conccentration unit";
				pkpdMain.verificationOfInputs.add(str);
				
			}
			else
				if(pkpdMain.pkpdInst.doseUnit == null && pkpdMain.pkpdInst.concMassUnit == null)
				{
					String[] str = new String[3];
					str[0] = "101";
					str[1] = "Provide dose unit and concentration unit";
					pkpdMain.verificationOfInputs.add(str);
					
				}


	}

	void readingMiscellaneousInput(PkParamEstimator pkpdMain, ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		readingEngineSettingsInput(pkpdMain);
		readingWeightingInputs(pkpdMain);
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		try {
			pkpdMain.valueForParameterBoundary = procInputInst.getModelInputTab2Obj()
					.getParamBoundarySelection();

		} catch (Exception e) {
			pkpdMain.valueForParameterBoundary = 2;
		}
	}

	public void initializeResultsAL(PkParamEstimator pkpdMain) {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		pkpdMain.pkpdInst.verticalParametersAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.verticalParametersAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getVerticalParametersTH());

		pkpdMain.pkpdInst.horizontalParametersAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.horizontalParametersAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getHorizontalParametersTH());

		pkpdMain.pkpdInst.initialParameterAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.initialParameterAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getInitialParameterTH());

		pkpdMain.pkpdInst.minimizationProcessAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.minimizationProcessAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getMinimizationProcessTH());

		pkpdMain.pkpdInst.dosingValueAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.dosingValueAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getDosingValueTH());

		pkpdMain.pkpdInst.correlationMatrixAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.correlationMatrixAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getCorrelationMatrixTH());

		pkpdMain.pkpdInst.eigenValuesAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.eigenValuesAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getEigenValuesTH());

		pkpdMain.pkpdInst.conditionNumberAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.conditionNumberAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getConditionNumberTH());

		pkpdMain.pkpdInst.predictedValueAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.predictedValueAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getPredictedValueTH());

		pkpdMain.pkpdInst.varienceCovarienceMatrixAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.varienceCovarienceMatrixAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getVarienceCovarienceMatrixTH());

		pkpdMain.pkpdInst.summaryTableAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.summaryTableAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getSummaryTableTH());

		pkpdMain.pkpdInst.diagnosticsAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.diagnosticsAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getDiagnosticsTH());
		pkpdMain.pkpdInst.partialDerivativeAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.partialDerivativeAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getPartialDerivativeTH());

		pkpdMain.pkpdInst.secondaryParametersAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.secondaryParametersAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getSecondaryParametersTH());

		pkpdMain.pkpdInst.nonTransposedSPAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.nonTransposedSPAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getNonTransposedSPTH());

		pkpdMain.pkpdInst.userSettingsAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.userSettingsAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getUserSettingsTH());

		pkpdMain.pkpdInst.historyAL = new ArrayList<String[]>();
		pkpdMain.pkpdInst.historyAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getHistoryTH());
		
	}

	void initParamValueInfo(PkParamEstimator pkpdMain, ApplicationInfo appInfo,
			int numberOfSortVariable, int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		int noOfParam = procInputInst.getProfileAndParamInfoObj().getNumberOfParameter();
		pkpdMain.pkpdInst.initSimplex = new double[noOfParam + 1][noOfParam];
		pkpdMain.pkpdInst.lowerBound = new double[noOfParam];
		pkpdMain.pkpdInst.upperBound = new double[noOfParam];
		pkpdMain.initial = new double[noOfParam];
		if (pkpdMain.ifParameterValueIsSupplied == 0) {

			pkpdMain.computeInitParamUsingGAOrCS(appInfo, numberOfSortVariable,
					ii);

		} else {

			pkpdMain.caProcIpPrep.retrievingUserGivenInitValue(pkpdMain,
					appInfo, ii);
		}

		if (pkpdMain.valueForParameterBoundary == 1) {

			pkpdMain.caProcIpPrep.retrievingUserGivenBoundary(pkpdMain,
					appInfo, ii);

		} else if (pkpdMain.valueForParameterBoundary == 0) {

			for (int parCount = 0; parCount < noOfParam; parCount++) {

				pkpdMain.pkpdInst.lowerBound[parCount] = 0;
				pkpdMain.pkpdInst.upperBound[parCount] = 10 * pkpdMain.pkpdInst.parameter[parCount];

			}

		}
	}

	void retrievingUserGivenBoundary(PkParamEstimator pkpdMain,
			ApplicationInfo appInfo, int ii) {
		
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		int noOfParam = procInputInst.getProfileAndParamInfoObj().getNumberOfParameter();
		
		int noOfSortVariables = procInputInst.getMappingDataObj().getSortVariablesListVector().size();
		
		for (int parCount = 0; parCount < noOfParam; parCount++) {
			try {
				String S1, S2;

				if (pkpdMain.ifParameterValueIsSupplied == 0) {

					S1 = procInputInst
							.getInitialParameterValueObj()
							.getInitialParameterValueAt(
									ii
											* noOfParam	+ parCount,
											noOfSortVariables + 2);

					S2 = procInputInst
							.getInitialParameterValueObj()
							.getInitialParameterValueAt(
									ii	* noOfParam	+ parCount,
									noOfSortVariables + 3);

				} else {

					S1 = procInputInst
							.getInitialParameterValueObj()
							.getInitialParameterValueAt(
									ii * noOfParam+ parCount,
									noOfSortVariables + 2);

					S2 =procInputInst
							.getInitialParameterValueObj()
							.getInitialParameterValueAt(
									ii * noOfParam + parCount,
									noOfSortVariables + 3);

				}

				Double.parseDouble(S1);
				Double.parseDouble(S2);
				pkpdMain.pkpdInst.lowerBound[parCount] = Double.parseDouble(S1);
				pkpdMain.pkpdInst.upperBound[parCount] = Double.parseDouble(S2);
			} catch (Exception e) {
				pkpdMain.pkpdInst.lowerBound[parCount] = 0;
				pkpdMain.pkpdInst.upperBound[parCount] = 100;
			}
		}
	}

	void retrievingUserGivenInitValue(PkParamEstimator pkpdMain,
			ApplicationInfo appInfo, int ii) {
		
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		int noOfParam = procInputInst.getProfileAndParamInfoObj().getNumberOfParameter();
		
		int noOfSortVariables = procInputInst.getMappingDataObj().getSortVariablesListVector().size();
		
		for (int parCount = 0; parCount < noOfParam; parCount++) {
			try {
				String S = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii * noOfParam + parCount,
								noOfSortVariables + 1);

				Double.parseDouble(S);
				pkpdMain.pkpdInst.parameter[parCount] = Double.parseDouble(S);
				pkpdMain.initial[parCount] = Double.parseDouble(S);
			

			} catch (Exception e) {

				pkpdMain.pkpdInst.parameter[parCount] = -1;
				pkpdMain.initial[parCount] = -1;
			}
			
			if(pkpdMain.pkpdInst.parameter[parCount] <= 0)
			 {

								TestException inst = new TestException();
								inst.throwCustomException(116);
								pkpdMain.pkpdInst.exceptionForCurrentProfile = 1;
								pkpdMain.pkpdInst
										.getWorkSheetOutputInst()
										.getTextoutput()
										.add(
												"\r\n"
														+ StringUtils
																.rightPad(
																		"*************************************************"
																				+ "**********************************",
																		80) + "\r\n\r\n");
								pkpdMain.pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
										StringUtils.rightPad(
												"102: Invalid Parameters value:"
														+ (ii + 1), 100));
								pkpdMain.pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
										"\r\n\r\n");
								break;
			}
		}
	}

	void getDefaultParamBoundary(PkParamEstimator pkpdMain) {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		int noOfParam = procInputInst.getProfileAndParamInfoObj().getNumberOfParameter();
		
		for (int parCount = 0; parCount < noOfParam; parCount++) {

			pkpdMain.pkpdInst.initSimplex[parCount][parCount] = -1;
			pkpdMain.pkpdInst.lowerBound[parCount] = -1;
			pkpdMain.pkpdInst.upperBound[parCount] = -1;

		}
	}

	void retrievingParamBoundary(PkParamEstimator pkpdMain, ApplicationInfo appInfo,
			int ii) {
		
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		int noOfParam = procInputInst.getProfileAndParamInfoObj().getNumberOfParameter();
		
		int noOfSortVariables = procInputInst.getMappingDataObj().getSortVariablesListVector().size();
		
		for (int parCount = 0; parCount < noOfParam; parCount++) {
			try {
				String S1 = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii	* noOfParam + parCount,
								noOfSortVariables + 2);

				String S2 = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii	* noOfParam + parCount,
								noOfSortVariables + 3);

				Double.parseDouble(S1);
				Double.parseDouble(S2);
				pkpdMain.pkpdInst.initSimplex[parCount][parCount] = (Double
						.parseDouble(S1) + Double.parseDouble(S2)) / 2;
				pkpdMain.pkpdInst.lowerBound[parCount] = Double.parseDouble(S1);
				pkpdMain.pkpdInst.upperBound[parCount] = Double.parseDouble(S2);
			} catch (Exception e) {
				pkpdMain.pkpdInst.initSimplex[parCount][parCount] = -1;
				pkpdMain.pkpdInst.lowerBound[parCount] = -1;
				pkpdMain.pkpdInst.upperBound[parCount] = -1;
			}
		}
	}

	void retrievingDoseRelatedInfo(PkParamEstimator pkpdMain, ApplicationInfo appInfo,
			double convertAmount, int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {
		pkpdMainInst = PkParamEstimator.createPkParamEstimateInstance();
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		int numberOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();
		
		pkpdMainInst.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 40) 
				/*+ "\t"*/
				+ StringUtils.rightPad("DOSING INFORMATION", 20)
				+ "\r\n\r\n");
		
		
		pkpdMainInst.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5)
				+ StringUtils.rightPad("Number of Doses:", 15)
				/*+ "\t"*/
				+ StringUtils.rightPad(Double.toString(numberOfDose ), 5) + "\r\n\r\n");
		
		/*pkpdMainInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5)
				+ StringUtils.rightPad("Dose Number", 15)
				+ "\t" + StringUtils.rightPad("Dose", 10)
				+ "\t"
				+ StringUtils.rightPad("Time of Dose", 15)
				+ "\r\n\r\n");*/
		
		dosingInfoPrintingInText();
		
		pkpdMain.caProcIpPrep.retrievingDoseAndDosingTime(pkpdMain, appInfo,
				convertAmount, ii);
		pkpdMain.caProcIpPrep.retrievingInfusinTime(pkpdMain, appInfo, ii);
		pkpdMain.caProcIpPrep.retrievingSecondDoseAndTimeForSimultaniousLm(
				pkpdMain, appInfo, ii);
	}

	private void dosingInfoPrintingInText() {
		pkpdMainInst = PkParamEstimator.createPkParamEstimateInstance();
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		String temp = "";
		String tempHeader = "";
		int noOfSortVar = procInputInst.getMappingDataObj().getSortVariablesListVector().size();
		temp = StringUtils.rightPad("", 5);
		tempHeader = StringUtils.rightPad("", 5);
		String[][] dosingData = procInputInst.getDosingDataObj().getDosingDSForNCA();
		
		int noOfCol =  dosingData[0].length - noOfSortVar ;

		if(noOfCol == 4)
		{
			temp = temp + StringUtils.rightPad("Dose Number",15) +"\t"+
					StringUtils.rightPad("Dose",15)+"\t" + StringUtils.rightPad("Time of Dose",15) 
					+ "\t" + StringUtils.rightPad("Length of Infusion",15) ;
		
			//adding space between header and values
			tempHeader = tempHeader + StringUtils.rightPad("",15) +"\t"+
			StringUtils.rightPad("",15)+"\t" + StringUtils.rightPad("",15) 
			+ "\t" + StringUtils.rightPad("",15) ;
		
		}
		else
		if(noOfCol == 3)
		{
			temp = temp + StringUtils.rightPad("Dose Number",15) +"\t"+
					StringUtils.rightPad("Dose",15)+"\t" + 
					StringUtils.rightPad("Time of Dose",15)  ;
		
			//adding space between header and values
			tempHeader=tempHeader +StringUtils.rightPad("",15) +"\t"+
			StringUtils.rightPad("",15)+"\t" + 
			StringUtils.rightPad("",15)  ;

		}
		else
			if(noOfCol == 5)
			{
				temp = temp + StringUtils.rightPad("Dose Number",15) +"\t"+
						StringUtils.rightPad("Dose",15)+"\t" + StringUtils.rightPad("Time of Dose",15) 
						+ "\t" + StringUtils.rightPad("Infusion Dose",15)
						+ "\t" + StringUtils.rightPad("Length of Infusion",15) ;
			
			//adding space between header and values
				tempHeader=	tempHeader + StringUtils.rightPad("",15) +"\t"+
				StringUtils.rightPad("",15)+"\t" + StringUtils.rightPad("",15) 
				+ "\t" + StringUtils.rightPad("",15)
				+ "\t" + StringUtils.rightPad("",15) ;
			
			}
		
		temp = temp + "\r\n" ;
		tempHeader = tempHeader + "\r\n" ;
		pkpdMainInst.pkpdInst.workSheetOutputInst.getTextoutput().add(temp);
		pkpdMainInst.pkpdInst.workSheetOutputInst.getTextoutput().add(tempHeader);
		int noOfRow = procInputInst.getModelInputTab1Obj().getNumberOfDose();
		
		int startRow = pkpdMainInst.pkpdInst.currentProfileNumber * noOfRow;
		int endRow = startRow + noOfRow;
		
		for (int i = startRow; i < endRow; i++) {
			temp = StringUtils.rightPad("", 5);
			temp = temp + StringUtils.rightPad(dosingData[i][noOfSortVar], 25) +"\t";
			for (int j = noOfSortVar + 1; j < dosingData[0].length; j++) {
			
				temp = temp + StringUtils.rightPad(pkpdInst.formatting(Double.parseDouble(dosingData[i][j]),false), 15) +"\t";
			}
			temp = temp + "\r\n\r\n" ;
			pkpdMainInst.pkpdInst.workSheetOutputInst.getTextoutput().add(temp);
		}
		
		
	}

	void retrievingSecondDoseAndTimeForSimultaniousLm(PkParamEstimator pkpdMain,
			ApplicationInfo appInfo, int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
				
		int noOfSortVariables = procInputInst.getMappingDataObj().getSortVariablesListVector().size();
		
		
		int noOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();
		
		if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == true && (pkpdMain.pkpdInst.modelNumber == 15 || pkpdMain.pkpdInst.modelNumber == 16
				|| pkpdMain.pkpdInst.modelNumber == 17)) {
			
			pkpdMain.infusionTime = new double[noOfDose];
			pkpdMain.pkpdInst.infusionDose = new double[noOfDose];
			pkpdMain.secondDose = new double[noOfDose];
			for (int i = 0; i < noOfDose; i++) {
				try {
					String S1 = procInputInst
							.getDosingDataObj()
							.getDosingValueAt(
									ii
											* noOfDose + i,
									noOfSortVariables + 3);

					String S2 = procInputInst
							.getDosingDataObj()
							.getDosingValueAt(
									ii	* noOfDose + i,
									noOfSortVariables + 4);

					Double.parseDouble(S1);
					pkpdMain.pkpdInst.infusionDose[i] = Double.parseDouble(S1);

					Double.parseDouble(S2);
					pkpdMain.infusionTime[i] = Double.parseDouble(S2);

				} catch (Exception e) {
					pkpdMain.pkpdInst.infusionDose[i] = 0;
					pkpdMain.infusionTime[i] = 0;

				}
			}
		}
		
		else if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == false && (pkpdMain.pkpdInst.modelNumber == 21 || pkpdMain.pkpdInst.modelNumber == 22
		|| pkpdMain.pkpdInst.modelNumber == 23 || pkpdMain.pkpdInst.modelNumber == 24 ))
		{
			pkpdMain.infusionTime = new double[noOfDose];
			pkpdMain.pkpdInst.infusionDose = new double[noOfDose];
			pkpdMain.secondDose = new double[noOfDose];

			for (int i = 0; i < noOfDose; i++) {
				try {
					String S1 = procInputInst
							.getDosingDataObj()
							.getDosingValueAt(
									ii
											* noOfDose + i,
									noOfSortVariables + 3);

					String S2 = procInputInst
							.getDosingDataObj()
							.getDosingValueAt(
									ii	* noOfDose + i,
									noOfSortVariables + 4);

					Double.parseDouble(S1);
					pkpdMain.secondDose[i] = Double.parseDouble(S1);
					
					pkpdMain.pkpdInst.infusionDose[i] = pkpdMain.secondDose[i];

					Double.parseDouble(S2);
					pkpdMain.infusionTime[i] = Double.parseDouble(S2);

				} catch (Exception e) {
					pkpdMain.pkpdInst.infusionDose[i] = 0;
					pkpdMain.infusionTime[i] = 0;

				}
			}
		
		}
	}

	void retrievingInfusinTime(PkParamEstimator pkpdMain, ApplicationInfo appInfo,
			int ii) throws RowsExceededException, WriteException,
			BiffException, IOException {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
				
		int noOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();
		
		int noOfSortVariables = procInputInst.getMappingDataObj().getSortVariablesListVector().size();
		
		pkpdMain.infusionTime = new double[noOfDose];
		
		if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == true && (pkpdMain.pkpdInst.modelNumber == 2 || pkpdMain.pkpdInst.modelNumber == 9
				|| pkpdMain.pkpdInst.modelNumber == 10 || pkpdMain.pkpdInst.modelNumber == 19)) {
		
		for (int i = 0; i < noOfDose; i++) {
			try {
				String S = procInputInst.getDosingDataObj()
						.getDosingValueAt(
								ii * noOfDose + i,
								noOfSortVariables + 3);

				Double.parseDouble(S);
				pkpdMain.infusionTime[i] = Double.parseDouble(S);
			} catch (Exception e) {
				pkpdMain.infusionTime[i] = 0;
			}

			if(pkpdMain.infusionTime[i] <= 0)
 {

					TestException inst = new TestException();
					inst.throwCustomException(102);
					pkpdMain.pkpdInst.exceptionForCurrentProfile = 1;
					pkpdMain.pkpdInst
							.getWorkSheetOutputInst()
							.getTextoutput()
							.add(
									"\r\n"
											+ StringUtils
													.rightPad(
															"*************************************************"
																	+ "**********************************",
															80) + "\r\n\r\n");
					pkpdMain.pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
							StringUtils.rightPad(
									"102: Invalid Length 0f infusion. Profile Number:"
											+ (ii + 1), 100));
					pkpdMain.pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
							"\r\n\r\n");
					break;
				}
		
		}
		}
		
		else if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == false && (pkpdMain.pkpdInst.modelNumber == 7 || pkpdMain.pkpdInst.modelNumber == 8
				|| pkpdMain.pkpdInst.modelNumber == 15 || pkpdMain.pkpdInst.modelNumber == 16 || pkpdMain.pkpdInst.modelNumber == 19 || pkpdMain.pkpdInst.modelNumber == 20))
		{

			
			for (int i = 0; i < noOfDose; i++) {
				try {
					String S = procInputInst.getDosingDataObj()
							.getDosingValueAt(
									ii * noOfDose + i,
									noOfSortVariables + 3);

					Double.parseDouble(S);
					pkpdMain.infusionTime[i] = Double.parseDouble(S);
				} catch (Exception e) {
					pkpdMain.infusionTime[i] = 0;
				}

				
				if(pkpdMain.infusionTime[i] <= 0)
				 {

									TestException inst = new TestException();
									inst.throwCustomException(102);
									pkpdMain.pkpdInst.exceptionForCurrentProfile = 1;
									pkpdMain.pkpdInst
											.getWorkSheetOutputInst()
											.getTextoutput()
											.add(
													"\r\n"
															+ StringUtils
																	.rightPad(
																			"*************************************************"
																					+ "**********************************",
																			80) + "\r\n\r\n");
									pkpdMain.pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
											StringUtils.rightPad(
													"102: Invalid Length 0f infusion. Profile Number:"
															+ (ii + 1), 100));
									pkpdMain.pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
											"\r\n\r\n");
									break;
								}
				
			}
			
			
		}
		
		
	}

	void retrievingDoseAndDosingTime(PkParamEstimator pkpdMain,
			ApplicationInfo appInfo, double convertAmount, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		pkpdMainInst = PkParamEstimator.createPkParamEstimateInstance();
		int noOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();
		
		int noOfSortVariables = procInputInst.getMappingDataObj().getSortVariablesListVector().size();
		
		pkpdMain.dose = new double[noOfDose];
		pkpdMain.dosingTime = new double[noOfDose];
		for (int i = 0; i < noOfDose; i++) {
			try {
				String S = procInputInst.getDosingDataObj()
						.getDosingValueAt(
								ii * noOfDose + i,
								noOfSortVariables + 1);

				String S1 = procInputInst.getDosingDataObj()
						.getDosingValueAt(
								ii * noOfDose + i,
								noOfSortVariables + 2);

				Double.parseDouble(S1);
				pkpdMain.dosingTime[i] = Double.parseDouble(S1);

				Double.parseDouble(S);
				pkpdMain.dose[i] = Double.parseDouble(S);

				/*pkpdMainInst.workSheetOutputInst.getTextoutput().add(
						StringUtils.rightPad("", 5)
						+ "\t"
						+ StringUtils.rightPad((i + 1) + "", 15)
						+ "\t"
						+ StringUtils.rightPad(pkpdInst.formatting(pkpdMain.dose[i], pkpdInst.roundTo),
								10)
						+ "\t"
						+ StringUtils.rightPad(pkpdInst.formatting(pkpdMain.dosingTime[i],pkpdInst.roundTo)
								, 15) + "\r\n");*/
				
				if(pkpdMain.dose[i] <=0 || pkpdMain.dosingTime[i] < 0)
				{
					TestException inst = new TestException();
					inst.throwCustomException(101);
					pkpdMain.pkpdInst.exceptionForCurrentProfile = 1;
					pkpdMain.pkpdInst.getWorkSheetOutputInst().getTextoutput().add("\r\n"
							+ StringUtils.rightPad(
									"*************************************************"
											+ "**********************************", 80)
							+ "\r\n\r\n");
					pkpdMain.pkpdInst.getWorkSheetOutputInst().getTextoutput()
					.add(StringUtils.rightPad("101: Invalid Dose or Dosing Time. Profile Number:" + (ii+1), 100));
					pkpdMain.pkpdInst.getWorkSheetOutputInst().getTextoutput()
					.add("\r\n\r\n");	
					break;
				}
				
				
				pkpdMain.dose[i] = pkpdMain.dose[i] * convertAmount;
			} catch (Exception e) {
				e.printStackTrace();
				pkpdMain.dose[i] = 1;
				pkpdMain.dosingTime[i] = 0;
				TestException inst = new TestException();
				inst.throwCustomException(101);
				pkpdMain.pkpdInst.exceptionForCurrentProfile = 1;
				pkpdMain.pkpdInst.getWorkSheetOutputInst().getTextoutput().add("\r\n"
						+ StringUtils.rightPad(
								"*************************************************"
										+ "**********************************", 80)
						+ "\r\n\r\n");
				pkpdMain.pkpdInst.getWorkSheetOutputInst().getTextoutput()
				.add(StringUtils.rightPad("101: Invalid Dose or Dosing Time. Profile Number:" + (ii + 1), 100));
				pkpdMain.pkpdInst.getWorkSheetOutputInst().getTextoutput()
				.add("\r\n\r\n");	
				break;
		
				
				
			}

		}
		System.out.println();
	}

	void createSingleProfileData(PkParamEstimator pkpdMain, int sum, int ii) {
		
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		pkpdMain.pkpdInst.setX(new double[procInputInst.getProfileAndParamInfoObj().getSubjectObsNo()[ii]][1]);
		pkpdMain.pkpdInst.setY(new double[procInputInst.getProfileAndParamInfoObj().getSubjectObsNo()[ii]][1]);
		pkpdMain.pkpdInst.degreesOfFreedom = procInputInst.getProfileAndParamInfoObj().getSubjectObsNo()[ii]
				- procInputInst.getProfileAndParamInfoObj().getNumberOfParameter();
		
		for (int i = 0; i < procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]; i++) {
			pkpdMain.pkpdInst.X[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getTimeForAllProfile()[sum + i];
			pkpdMain.pkpdInst.Y[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getConcForAllProfile()[sum + i];

		}
		System.out.println();
	}

}
