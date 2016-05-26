package Model;

import Common.TestException;
import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class ModelAndEngineSettingsInfo {

	ListOfDataStructures dataStructInst;
	ApplicationInfo appInfo;
	PkPdInfo pkpdInst;
	ProcessingInputsInfo procInputInst;

	public void copyModelAndEngineSettingsInfo(String analysisType) {
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		appInfo = dataStructInst.getAppInfo();
		pkpdInst = PkPdInfo.createPKPDInstance();
		procInputInst = pkpdInst.copyProcessingInput();

	//	if (analysisType.equals("pd")) {
			copyModelRelatedInfoForPd();
			copyEngineSettingsInfoForPd();
			initializePkPdStructForPd();
	/*	} else if (analysisType.equals("pkpdlink")) {
			copyModelRelatedInfoForLink();
			copyEngineSettingsInfoForLink();
			initializePkPdStructForLink();
		} else if (analysisType.equals("mm")) {
			copyModelRelatedInfoForMm();
			copyEngineSettingsInfoForMm();
			initializePkPdStructForMm();
		} else if (analysisType.equals("irm")) {
			copyModelRelatedInfoForIrm();
			copyEngineSettingsInfoForIrm();
			initializePkPdStructForIrm();
		}
		else if (analysisType.equals("invitro")) {
			copyModelRelatedInfoForInVitro();
			copyEngineSettingsInfoForInVitro();
			initializePkPdStructForInVitro();
		}
		*/
		copyWeightingInfo();

	}

	/*private void initializePkPdStructForInVitro() {
		// TODO Auto-generated method stub
		
	}

	private void copyEngineSettingsInfoForInVitro() {
		// TODO Auto-generated method stub
		
	}*/

	private void copyModelRelatedInfoForInVitro() {
		dataStructInst.ifParameterValueIsSupplied = procInputInst.getModelInputTab2Obj()
		.getSourceOfIntParamValue();

	pkpdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
			.getNumberOfParameter();
	
	dataStructInst.noOfFunc = procInputInst.getProfileAndParamInfoObj()
			.getNumberOfFunction();}

	private void copyWeightingInfo() {
		
		pkpdInst.wtScheme = procInputInst.getModelInputTab1Obj().getWeightingScheme();
		pkpdInst.wtExp = procInputInst.getModelInputTab1Obj().getWeightingIndex();
	}

	
	
	private void initializePkPdStructForIrm() {
		pkpdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();

		pkpdInst.numberOfSortVar =procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		pkpdInst.dataSortVariables = procInputInst.getProfileAndParamInfoObj()
				.getDataSortVariables();

		pkpdInst.paramName = procInputInst.getProfileAndParamInfoObj()
				.getParameterNameForCA();

		pkpdInst.secParamName = procInputInst.getProfileAndParamInfoObj()
				.getSecondaryParameterNameForCA();

		pkpdInst.noOfSecParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfSecondaryParameters();
	}

	private void initializePkPdStructForMm() {
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
	}

	private void initializePkPdStructForLink() {
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
	}

	private void initializePkPdStructForPd() {

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
	}

	private void copyEngineSettingsInfoForIrm() {

		try {
			pkpdInst.methodNo = procInputInst.getModelInputTab2Obj()
					.getMinimizationMethod() + 1;
		} catch (Exception e) {
			pkpdInst.methodNo = 1;
		}

		pkpdInst.nPop = 50;
		pkpdInst.crossOverProb = 0.8;
		pkpdInst.mutationProb = 0.2;

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getIncForPartialDerivative();
			Double.parseDouble(S);
			pkpdInst.pdIncrement = Double.parseDouble(S);

			if (pkpdInst.pdIncrement < 0 || pkpdInst.pdIncrement > 0.1) {
				TestException inst = new TestException();
				inst.throwCustomException(110);
				pkpdInst.pdIncrement = 0.0001;
			}

		} catch (Exception e) {
			TestException inst = new TestException();
			inst.throwCustomException(110);
			pkpdInst.pdIncrement = 0.0001;

		}

		try {
			pkpdInst.odeSolverChoice = procInputInst.getModelInputTab2Obj()
					.getOdeSolverMethod() + 1;

		} catch (Exception e) {

			pkpdInst.odeSolverChoice = 1;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getConvergenceCriteria();
			System.out.println("convergence:" + S);
			pkpdInst.convergence = Double.parseDouble(S);

			if (pkpdInst.convergence < 0) {
				TestException inst = new TestException();
				inst.throwCustomException(111);
				pkpdInst.convergence = 0.000001;
			}

		} catch (Exception e) {
			TestException inst = new TestException();
			inst.throwCustomException(111);
			pkpdInst.convergence = 0.000001;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getNumberOfIterations();
			Integer.parseInt(S);
			pkpdInst.iter = Integer.parseInt(S);

			if (pkpdInst.iter <= 0) {
				TestException inst = new TestException();
				inst.throwCustomException(112);
				pkpdInst.convergence = 100;
			}

		} catch (Exception e) {

			TestException inst = new TestException();
			inst.throwCustomException(112);
			pkpdInst.convergence = 100;

		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getLambdaValue();

			pkpdInst.lambda = Double.parseDouble(S);
		} catch (Exception e) {
			pkpdInst.lambda = 0.01;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj().getMuValue();

			pkpdInst.mu = Double.parseDouble(S);

		} catch (Exception e) {
			pkpdInst.mu = 0.2;
		}
		
		try {
			String S = (String) procInputInst.getModelInputTab2Obj().getNumberOfPredictedValue();

			pkpdInst.noOfPredval = Integer.parseInt(S);

		} catch (Exception e) {
			pkpdInst.noOfPredval = 100;
		}

	}

	private void copyModelRelatedInfoForIrm() {
		dataStructInst.ifParameterValueIsSupplied = procInputInst.getModelInputTab2Obj()
				.getSourceOfIntParamValue();

		pkpdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();
		
		dataStructInst.noOfFunc = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfFunction();

	}

	private void copyEngineSettingsInfoForMm() {

		try {
			pkpdInst.methodNo = procInputInst.getModelInputTab2Obj()
					.getMinimizationMethod() + 1;
		} catch (Exception e) {
			pkpdInst.methodNo = 1;
		}

		pkpdInst.nPop = 50;
		pkpdInst.crossOverProb = 0.8;
		pkpdInst.mutationProb = 0.2;

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getIncForPartialDerivative();
			Double.parseDouble(S);
			pkpdInst.pdIncrement = Double.parseDouble(S);

			if (pkpdInst.pdIncrement < 0 || pkpdInst.pdIncrement > 0.1) {
				TestException inst = new TestException();
				inst.throwCustomException(110);
				pkpdInst.pdIncrement = 0.0001;
			}

		} catch (Exception e) {
			TestException inst = new TestException();
			inst.throwCustomException(110);
			pkpdInst.pdIncrement = 0.0001;

		}

		try {
			pkpdInst.odeSolverChoice = procInputInst.getModelInputTab2Obj()
					.getOdeSolverMethod() + 1;

		} catch (Exception e) {

			pkpdInst.odeSolverChoice = 1;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getConvergenceCriteria();

			pkpdInst.convergence = Double.parseDouble(S);

			if (pkpdInst.convergence < 0) {
				TestException inst = new TestException();
				inst.throwCustomException(111);
				pkpdInst.convergence = 0.000001;
			}

		} catch (Exception e) {
			TestException inst = new TestException();
			inst.throwCustomException(111);
			pkpdInst.convergence = 0.000001;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getNumberOfIterations();
			Integer.parseInt(S);
			pkpdInst.iter = Integer.parseInt(S);

			if (pkpdInst.iter <= 0) {
				TestException inst = new TestException();
				inst.throwCustomException(112);
				pkpdInst.convergence = 100;
			}

		} catch (Exception e) {

			TestException inst = new TestException();
			inst.throwCustomException(112);
			pkpdInst.convergence = 100;

		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getLambdaValue();

			pkpdInst.lambda = Double.parseDouble(S);
		} catch (Exception e) {
			pkpdInst.lambda = 0.01;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj().getMuValue();

			pkpdInst.mu = Double.parseDouble(S);

		} catch (Exception e) {
			pkpdInst.mu = 0.2;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj().getNumberOfPredictedValue();

			pkpdInst.noOfPredval = Integer.parseInt(S);

		} catch (Exception e) {
			pkpdInst.noOfPredval = 100;
		}
	}

	private void copyModelRelatedInfoForMm() {
		dataStructInst.ifParameterValueIsSupplied = procInputInst.getModelInputTab2Obj()
				.getSourceOfIntParamValue();

		pkpdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();
		
		dataStructInst.noOfFunc = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfFunction();


	}

	private void copyModelRelatedInfoForPd() {

		dataStructInst.ifParameterValueIsSupplied = procInputInst.getModelInputTab2Obj()
				.getSourceOfIntParamValue();

		pkpdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();
		
		dataStructInst.noOfFunc = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfFunction();

	}

	private void copyEngineSettingsInfoForLink() {

		try {
			pkpdInst.methodNo = procInputInst.getModelInputTab2Obj()
					.getMinimizationMethod() + 1;
		} catch (Exception e) {
			pkpdInst.methodNo = 1;
		}

		pkpdInst.nPop = 50;
		pkpdInst.crossOverProb = 0.8;
		pkpdInst.mutationProb = 0.2;

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getIncForPartialDerivative();
			Double.parseDouble(S);
			pkpdInst.pdIncrement = Double.parseDouble(S);

			if (pkpdInst.pdIncrement < 0 || pkpdInst.pdIncrement > 0.1) {
				TestException inst = new TestException();
				inst.throwCustomException(110);
				pkpdInst.pdIncrement = 0.0001;
			}

		} catch (Exception e) {
			TestException inst = new TestException();
			inst.throwCustomException(110);
			pkpdInst.pdIncrement = 0.0001;

		}

		try {
			pkpdInst.odeSolverChoice = procInputInst.getModelInputTab2Obj()
					.getOdeSolverMethod() + 1;

		} catch (Exception e) {

			pkpdInst.odeSolverChoice = 1;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getConvergenceCriteria();

			pkpdInst.convergence = Double.parseDouble(S);

			if (pkpdInst.convergence < 0) {
				TestException inst = new TestException();
				inst.throwCustomException(111);
				pkpdInst.convergence = 0.000001;
			}

		} catch (Exception e) {
			TestException inst = new TestException();
			inst.throwCustomException(111);
			pkpdInst.convergence = 0.000001;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getNumberOfIterations();
			Integer.parseInt(S);
			pkpdInst.iter = Integer.parseInt(S);

			if (pkpdInst.iter <= 0) {
				TestException inst = new TestException();
				inst.throwCustomException(112);
				pkpdInst.convergence = 100;
			}

		} catch (Exception e) {

			TestException inst = new TestException();
			inst.throwCustomException(112);
			pkpdInst.convergence = 100;

		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getLambdaValue();

			pkpdInst.lambda = Double.parseDouble(S);
		} catch (Exception e) {
			pkpdInst.lambda = 0.01;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj().getMuValue();

			pkpdInst.mu = Double.parseDouble(S);

		} catch (Exception e) {
			pkpdInst.mu = 0.2;
		}
		
		try {
			String S = (String) procInputInst.getModelInputTab2Obj().getNumberOfPredictedValue();

			pkpdInst.noOfPredval = Integer.parseInt(S);

		} catch (Exception e) {
			pkpdInst.noOfPredval = 100;
		}

	}

	private void copyModelRelatedInfoForLink() {
		dataStructInst.ifParameterValueIsSupplied = procInputInst.getModelInputTab2Obj()
				.getSourceOfIntParamValue();

		pkpdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();
		
		dataStructInst.noOfFunc = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfFunction();

	}

	private void copyEngineSettingsInfoForPd() {
		
		pkpdInst = PkPdInfo.createPKPDInstance();

		try {
			pkpdInst.methodNo = procInputInst.getModelInputTab2Obj()
					.getMinimizationMethod() + 1;
		} catch (Exception e) {
			pkpdInst.methodNo = 1;
		}

		pkpdInst.nPop = 50;
		pkpdInst.crossOverProb = 0.8;
		pkpdInst.mutationProb = 0.2;

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getIncForPartialDerivative();
			Double.parseDouble(S);
			pkpdInst.pdIncrement = Double.parseDouble(S);

			if (pkpdInst.pdIncrement < 0 || pkpdInst.pdIncrement > 0.1) {
				TestException inst = new TestException();
				inst.throwCustomException(110);
				pkpdInst.pdIncrement = 0.0001;
			}

		} catch (Exception e) {
			TestException inst = new TestException();
			inst.throwCustomException(110);
			pkpdInst.pdIncrement = 0.0001;

		}

		try {
			pkpdInst.odeSolverChoice = procInputInst.getModelInputTab2Obj()
					.getOdeSolverMethod() + 1;

		} catch (Exception e) {

			pkpdInst.odeSolverChoice = 1;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getConvergenceCriteria();

			pkpdInst.convergence = Double.parseDouble(S);

			if (pkpdInst.convergence < 0) {
				TestException inst = new TestException();
				inst.throwCustomException(111);
				pkpdInst.convergence = 0.000001;
			}

		} catch (Exception e) {
			TestException inst = new TestException();
			inst.throwCustomException(111);
			pkpdInst.convergence = 0.000001;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getNumberOfIterations();
			Integer.parseInt(S);
			pkpdInst.iter = Integer.parseInt(S);

			if (pkpdInst.iter <= 0) {
				TestException inst = new TestException();
				inst.throwCustomException(112);
				pkpdInst.convergence = 100;
			}

		} catch (Exception e) {

			TestException inst = new TestException();
			inst.throwCustomException(112);
			pkpdInst.convergence = 100;

		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj()
					.getLambdaValue();

			pkpdInst.lambda = Double.parseDouble(S);
		} catch (Exception e) {
			pkpdInst.lambda = 0.01;
		}

		try {
			String S = (String) procInputInst.getModelInputTab2Obj().getMuValue();

			pkpdInst.mu = Double.parseDouble(S);

		} catch (Exception e) {
			pkpdInst.mu = 0.2;
		}
		
		try {
			String S = (String) procInputInst.getModelInputTab2Obj().getNumberOfPredictedValue();

			pkpdInst.noOfPredval = Integer.parseInt(S);

		} catch (Exception e) {
			pkpdInst.noOfPredval = 100;
		}

	}

}
