package Model;

import java.util.ArrayList;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class InitializationOfResultStructure {

	ListOfDataStructures dataStructInst;
	ApplicationInfo appInfo;
	PkPdInfo pkPdInst;

	public void initializeResultAL(String analysisType) {
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		appInfo = dataStructInst.getAppInfo();
		pkPdInst = PkPdInfo.createPKPDInstance();

		if (analysisType.equals("pd")) {
			initializeResultsALForPd();
		} else if (analysisType.equals("pkpdlink")) {
			initializeResultsALForLinkModel();
		} else if (analysisType.equals("mm")) {
			initializeResultsALForMm();
		} else if (analysisType.equals("irm")) {
			initializeResultsALForIrm();
		} else if (analysisType.equals("invitro")) {
			initializeResultsALForInVitro();
		}else if (analysisType.equals("ascii")) {
			initializeResultsALForAscii();
		}
	}

	private void initializeResultsALForAscii() {


		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();

		pkpdInst.verticalParametersAL = new ArrayList<String[]>();
		pkpdInst.verticalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getVerticalParametersTH());

		pkpdInst.horizontalParametersAL = new ArrayList<String[]>();
		pkpdInst.horizontalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getHorizontalParametersTH());

		pkpdInst.initialParameterAL = new ArrayList<String[]>();
		pkpdInst.initialParameterAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getInitialParameterTH());

		pkpdInst.minimizationProcessAL = new ArrayList<String[]>();
		pkpdInst.minimizationProcessAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getMinimizationProcessTH());

		pkpdInst.dosingValueAL = new ArrayList<String[]>();
		pkpdInst.dosingValueAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getDosingValueTH());

		pkpdInst.correlationMatrixAL = new ArrayList<String[]>();
		pkpdInst.correlationMatrixAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getCorrelationMatrixTH());

		pkpdInst.eigenValuesAL = new ArrayList<String[]>();
		pkpdInst.eigenValuesAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getEigenValuesTH());

		pkpdInst.conditionNumberAL = new ArrayList<String[]>();
		pkpdInst.conditionNumberAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getConditionNumberTH());

		pkpdInst.predictedValueAL = new ArrayList<String[]>();
		pkpdInst.predictedValueAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPredictedValueTH());

		pkpdInst.varienceCovarienceMatrixAL = new ArrayList<String[]>();
		pkpdInst.varienceCovarienceMatrixAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj()
				.getVarienceCovarienceMatrixTH());

		pkpdInst.summaryTableAL = new ArrayList<String[]>();
		pkpdInst.summaryTableAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getSummaryTableTH());

		pkpdInst.diagnosticsAL = new ArrayList<String[]>();
		pkpdInst.diagnosticsAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getDiagnosticsTH());
		pkpdInst.partialDerivativeAL = new ArrayList<String[]>();
		pkpdInst.partialDerivativeAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPartialDerivativeTH());

		pkpdInst.secondaryParametersAL = new ArrayList<String[]>();
		pkpdInst.secondaryParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getSecondaryParametersTH());

		pkpdInst.nonTransposedSPAL = new ArrayList<String[]>();
		pkpdInst.nonTransposedSPAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getNonTransposedSPTH());

		pkpdInst.userSettingsAL = new ArrayList<String[]>();
		pkpdInst.userSettingsAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getUserSettingsTH());

		pkpdInst.historyAL = new ArrayList<String[]>();
		pkpdInst.historyAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getHistoryTH());

	

	}

	private void initializeResultsALForInVitro() {

		ProcessingInputsInfo procInputInst = pkPdInst.copyProcessingInput();

		pkPdInst.verticalParametersAL = new ArrayList<String[]>();
		pkPdInst.verticalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getVerticalParametersTH());

		pkPdInst.horizontalParametersAL = new ArrayList<String[]>();
		pkPdInst.horizontalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getHorizontalParametersTH());

		pkPdInst.initialParameterAL = new ArrayList<String[]>();
		pkPdInst.initialParameterAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getInitialParameterTH());

		pkPdInst.minimizationProcessAL = new ArrayList<String[]>();
		pkPdInst.minimizationProcessAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getMinimizationProcessTH());

	
		pkPdInst.correlationMatrixAL = new ArrayList<String[]>();
		pkPdInst.correlationMatrixAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getCorrelationMatrixTH());

		pkPdInst.eigenValuesAL = new ArrayList<String[]>();
		pkPdInst.eigenValuesAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getEigenValuesTH());

		pkPdInst.conditionNumberAL = new ArrayList<String[]>();
		pkPdInst.conditionNumberAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getConditionNumberTH());

		pkPdInst.predictedValueAL = new ArrayList<String[]>();
		pkPdInst.predictedValueAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPredictedValueTH());

		pkPdInst.varienceCovarienceMatrixAL = new ArrayList<String[]>();
		pkPdInst.varienceCovarienceMatrixAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj()
				.getVarienceCovarienceMatrixTH());

		pkPdInst.summaryTableAL = new ArrayList<String[]>();
		pkPdInst.summaryTableAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getSummaryTableTH());

		pkPdInst.diagnosticsAL = new ArrayList<String[]>();
		pkPdInst.diagnosticsAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getDiagnosticsTH());
		pkPdInst.partialDerivativeAL = new ArrayList<String[]>();
		pkPdInst.partialDerivativeAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPartialDerivativeTH());

	
		pkPdInst.userSettingsAL = new ArrayList<String[]>();
		pkPdInst.userSettingsAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getUserSettingsTH());

		pkPdInst.historyAL = new ArrayList<String[]>();
		pkPdInst.historyAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getHistoryTH());

	}

	private void initializeResultsALForIrm() {

		ProcessingInputsInfo procInputInst = pkPdInst.copyProcessingInput();

		pkPdInst.verticalParametersAL = new ArrayList<String[]>();
		pkPdInst.verticalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getVerticalParametersTH());

		pkPdInst.horizontalParametersAL = new ArrayList<String[]>();
		pkPdInst.horizontalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getHorizontalParametersTH());

		pkPdInst.initialParameterAL = new ArrayList<String[]>();
		pkPdInst.initialParameterAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getInitialParameterTH());

		pkPdInst.minimizationProcessAL = new ArrayList<String[]>();
		pkPdInst.minimizationProcessAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getMinimizationProcessTH());

		pkPdInst.dosingValueAL = new ArrayList<String[]>();
		pkPdInst.dosingValueAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getDosingValueTH());

		pkPdInst.correlationMatrixAL = new ArrayList<String[]>();
		pkPdInst.correlationMatrixAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getCorrelationMatrixTH());

		pkPdInst.eigenValuesAL = new ArrayList<String[]>();
		pkPdInst.eigenValuesAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getEigenValuesTH());

		pkPdInst.conditionNumberAL = new ArrayList<String[]>();
		pkPdInst.conditionNumberAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getConditionNumberTH());

		pkPdInst.predictedValueAL = new ArrayList<String[]>();
		pkPdInst.predictedValueAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPredictedValueTH());

		pkPdInst.varienceCovarienceMatrixAL = new ArrayList<String[]>();
		pkPdInst.varienceCovarienceMatrixAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj()
				.getVarienceCovarienceMatrixTH());

		pkPdInst.summaryTableAL = new ArrayList<String[]>();
		pkPdInst.summaryTableAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getSummaryTableTH());

		pkPdInst.diagnosticsAL = new ArrayList<String[]>();
		pkPdInst.diagnosticsAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getDiagnosticsTH());
		
		pkPdInst.partialDerivativeAL = new ArrayList<String[]>();
		pkPdInst.partialDerivativeAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPartialDerivativeTH());

		pkPdInst.secondaryParametersAL = new ArrayList<String[]>();
		pkPdInst.secondaryParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getSecondaryParametersTH());

		pkPdInst.nonTransposedSPAL = new ArrayList<String[]>();
		pkPdInst.nonTransposedSPAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getNonTransposedSPTH());

		pkPdInst.userSettingsAL = new ArrayList<String[]>();
		pkPdInst.userSettingsAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getUserSettingsTH());

		pkPdInst.historyAL = new ArrayList<String[]>();
		pkPdInst.historyAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getHistoryTH());
	}

	private void initializeResultsALForMm() {
		ProcessingInputsInfo procInputInst = pkPdInst.copyProcessingInput();
		pkPdInst.verticalParametersAL = new ArrayList<String[]>();
		pkPdInst.verticalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getVerticalParametersTH());

		pkPdInst.horizontalParametersAL = new ArrayList<String[]>();
		pkPdInst.horizontalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getHorizontalParametersTH());

		pkPdInst.initialParameterAL = new ArrayList<String[]>();
		pkPdInst.initialParameterAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getInitialParameterTH());

		pkPdInst.minimizationProcessAL = new ArrayList<String[]>();
		pkPdInst.minimizationProcessAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getMinimizationProcessTH());

		pkPdInst.dosingValueAL = new ArrayList<String[]>();
		pkPdInst.dosingValueAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getDosingValueTH());

		pkPdInst.correlationMatrixAL = new ArrayList<String[]>();
		pkPdInst.correlationMatrixAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getCorrelationMatrixTH());

		pkPdInst.eigenValuesAL = new ArrayList<String[]>();
		pkPdInst.eigenValuesAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getEigenValuesTH());

		pkPdInst.conditionNumberAL = new ArrayList<String[]>();
		pkPdInst.conditionNumberAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getConditionNumberTH());

		pkPdInst.predictedValueAL = new ArrayList<String[]>();
		pkPdInst.predictedValueAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPredictedValueTH());

		pkPdInst.varienceCovarienceMatrixAL = new ArrayList<String[]>();
		pkPdInst.varienceCovarienceMatrixAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj()
				.getVarienceCovarienceMatrixTH());

		pkPdInst.summaryTableAL = new ArrayList<String[]>();
		pkPdInst.summaryTableAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getSummaryTableTH());

		pkPdInst.diagnosticsAL = new ArrayList<String[]>();
		pkPdInst.diagnosticsAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getDiagnosticsTH());
		pkPdInst.partialDerivativeAL = new ArrayList<String[]>();
		pkPdInst.partialDerivativeAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPartialDerivativeTH());

		pkPdInst.secondaryParametersAL = new ArrayList<String[]>();
		pkPdInst.secondaryParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getSecondaryParametersTH());

		pkPdInst.nonTransposedSPAL = new ArrayList<String[]>();
		pkPdInst.nonTransposedSPAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getNonTransposedSPTH());

		pkPdInst.userSettingsAL = new ArrayList<String[]>();
		pkPdInst.userSettingsAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getUserSettingsTH());

		pkPdInst.historyAL = new ArrayList<String[]>();
		pkPdInst.historyAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getHistoryTH());

	}

	private void initializeResultsALForLinkModel() {
		ProcessingInputsInfo procInputInst = pkPdInst.copyProcessingInput();
		pkPdInst.verticalParametersAL = new ArrayList<String[]>();
		pkPdInst.verticalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getVerticalParametersTH());

		pkPdInst.horizontalParametersAL = new ArrayList<String[]>();
		pkPdInst.horizontalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getHorizontalParametersTH());

		pkPdInst.initialParameterAL = new ArrayList<String[]>();
		pkPdInst.initialParameterAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getInitialParameterTH());

		pkPdInst.minimizationProcessAL = new ArrayList<String[]>();
		pkPdInst.minimizationProcessAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getMinimizationProcessTH());

		pkPdInst.dosingValueAL = new ArrayList<String[]>();
		pkPdInst.dosingValueAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getDosingValueTH());

		pkPdInst.correlationMatrixAL = new ArrayList<String[]>();
		pkPdInst.correlationMatrixAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getCorrelationMatrixTH());

		pkPdInst.eigenValuesAL = new ArrayList<String[]>();
		pkPdInst.eigenValuesAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getEigenValuesTH());

		pkPdInst.conditionNumberAL = new ArrayList<String[]>();
		pkPdInst.conditionNumberAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getConditionNumberTH());

		pkPdInst.predictedValueAL = new ArrayList<String[]>();
		pkPdInst.predictedValueAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPredictedValueTH());

		pkPdInst.varienceCovarienceMatrixAL = new ArrayList<String[]>();
		pkPdInst.varienceCovarienceMatrixAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj()
				.getVarienceCovarienceMatrixTH());

		pkPdInst.summaryTableAL = new ArrayList<String[]>();
		pkPdInst.summaryTableAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getSummaryTableTH());

		pkPdInst.diagnosticsAL = new ArrayList<String[]>();
		pkPdInst.diagnosticsAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getDiagnosticsTH());
		pkPdInst.partialDerivativeAL = new ArrayList<String[]>();
		pkPdInst.partialDerivativeAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPartialDerivativeTH());

		pkPdInst.secondaryParametersAL = new ArrayList<String[]>();
		pkPdInst.secondaryParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getSecondaryParametersTH());

		pkPdInst.nonTransposedSPAL = new ArrayList<String[]>();
		pkPdInst.nonTransposedSPAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getNonTransposedSPTH());

		pkPdInst.userSettingsAL = new ArrayList<String[]>();
		pkPdInst.userSettingsAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getUserSettingsTH());

		pkPdInst.historyAL = new ArrayList<String[]>();
		pkPdInst.historyAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getHistoryTH());

	}

	private void initializeResultsALForPd() {
		ProcessingInputsInfo procInputInst = pkPdInst.copyProcessingInput();

		pkPdInst.verticalParametersAL = new ArrayList<String[]>();
		pkPdInst.verticalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getVerticalParametersTH());

		pkPdInst.horizontalParametersAL = new ArrayList<String[]>();
		pkPdInst.horizontalParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getHorizontalParametersTH());

		pkPdInst.initialParameterAL = new ArrayList<String[]>();
		pkPdInst.initialParameterAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getInitialParameterTH());

		pkPdInst.minimizationProcessAL = new ArrayList<String[]>();
		pkPdInst.minimizationProcessAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getMinimizationProcessTH());

		pkPdInst.dosingValueAL = new ArrayList<String[]>();
		pkPdInst.dosingValueAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getDosingValueTH());

		pkPdInst.correlationMatrixAL = new ArrayList<String[]>();
		pkPdInst.correlationMatrixAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getCorrelationMatrixTH());

		pkPdInst.eigenValuesAL = new ArrayList<String[]>();
		pkPdInst.eigenValuesAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getEigenValuesTH());

		pkPdInst.conditionNumberAL = new ArrayList<String[]>();
		pkPdInst.conditionNumberAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getConditionNumberTH());

		pkPdInst.predictedValueAL = new ArrayList<String[]>();
		pkPdInst.predictedValueAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPredictedValueTH());

		pkPdInst.varienceCovarienceMatrixAL = new ArrayList<String[]>();
		pkPdInst.varienceCovarienceMatrixAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj()
				.getVarienceCovarienceMatrixTH());

		pkPdInst.summaryTableAL = new ArrayList<String[]>();
		pkPdInst.summaryTableAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getSummaryTableTH());

		pkPdInst.diagnosticsAL = new ArrayList<String[]>();
		pkPdInst.diagnosticsAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getDiagnosticsTH());
		pkPdInst.partialDerivativeAL = new ArrayList<String[]>();
		pkPdInst.partialDerivativeAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getPartialDerivativeTH());

		pkPdInst.secondaryParametersAL = new ArrayList<String[]>();
		pkPdInst.secondaryParametersAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getSecondaryParametersTH());

		pkPdInst.nonTransposedSPAL = new ArrayList<String[]>();
		pkPdInst.nonTransposedSPAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getNonTransposedSPTH());

		pkPdInst.userSettingsAL = new ArrayList<String[]>();
		pkPdInst.userSettingsAL.add(procInputInst
				.getResultDisplayTabllsHeaderObj().getUserSettingsTH());

		pkPdInst.historyAL = new ArrayList<String[]>();
		pkPdInst.historyAL.add(procInputInst.getResultDisplayTabllsHeaderObj()
				.getHistoryTH());

	}

}
