package Model;

import java.util.ArrayList;

import Common.WriteAppinfoForUnitTest;

import view.ApplicationInfo;

public class ResultsStructureStorer {

	PkPdInfo pkPdInst;

	public void storeSpreedSheetResults() {

		pkPdInst = PkPdInfo.createPKPDInstance();

		if (pkPdInst.analysisType.equals("invitro")) {
			storeSpreedSheetResultsForInVitro();
		} else {
			pkPdInst
					.getWorkSheetOutputInst()
					.getSpreadSheetOutputs()
					.setHorizontalParametersForCA(
							retrievingResultsFromAL(pkPdInst.horizontalParametersAL));

			pkPdInst
					.getWorkSheetOutputInst()
					.getSpreadSheetOutputs()
					.setVerticalParametersForCA(
							retrievingResultsFromAL(pkPdInst.verticalParametersAL));

			pkPdInst
					.getWorkSheetOutputInst()
					.getSpreadSheetOutputs()
					.setMinimizationProcessForCA(
							retrievingResultsFromAL(pkPdInst.minimizationProcessAL));

			if (pkPdInst.methodNo != 2)
				pkPdInst
						.getWorkSheetOutputInst()
						.getSpreadSheetOutputs()
						.setConditionNumberForCA(
								retrievingResultsFromAL(pkPdInst.conditionNumberAL));

			pkPdInst
					.getWorkSheetOutputInst()
					.getSpreadSheetOutputs()
					.setSecondaryParametersForCA(
							retrievingResultsFromAL(pkPdInst.secondaryParametersAL));

			pkPdInst
					.getWorkSheetOutputInst()
					.getSpreadSheetOutputs()
					.setNonTransposedSPForCA(
							retrievingResultsFromAL(pkPdInst.nonTransposedSPAL));

			pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
					.setDiagnosticsForCA(
							retrievingResultsFromAL(pkPdInst.diagnosticsAL));

			pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
					.setSummaryTableForCA(
							retrievingResultsFromAL(pkPdInst.summaryTableAL));

			pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
					.setPredictedValueForCA(
							retrievingResultsFromAL(pkPdInst.predictedValueAL));

			pkPdInst
					.getWorkSheetOutputInst()
					.getSpreadSheetOutputs()
					.setInitialParameterForCA(
							retrievingResultsFromAL(pkPdInst.initialParameterAL));

			pkPdInst
					.getWorkSheetOutputInst()
					.getSpreadSheetOutputs()
					.setCorrelationMatrixForCA(
							retrievingResultsFromAL(pkPdInst.correlationMatrixAL));

			pkPdInst
					.getWorkSheetOutputInst()
					.getSpreadSheetOutputs()
					.setPartialDerivativeForCA(
							retrievingResultsFromAL(pkPdInst.partialDerivativeAL));

			pkPdInst
					.getWorkSheetOutputInst()
					.getSpreadSheetOutputs()
					.setVarienceCovarienceMatrixForCA(
							retrievingResultsFromAL(pkPdInst.varienceCovarienceMatrixAL));

			if(pkPdInst.analysisType.equals("ascii"))
			{
				
			}else
			{
				pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
				.setDosingValueForCA(
						retrievingResultsFromAL(pkPdInst.dosingValueAL));
			}
			
			

			pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
					.setUserSettingsForCA(
							retrievingResultsFromAL(pkPdInst.userSettingsAL));

			pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
					.setEigenValuesForCA(
							retrievingResultsFromAL(pkPdInst.eigenValuesAL));

			pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
					.setHistoryForCA(
							retrievingResultsFromAL(pkPdInst.historyAL));

		}
	}

	private void storeSpreedSheetResultsForInVitro() {

		pkPdInst
				.getWorkSheetOutputInst()
				.getSpreadSheetOutputs()
				.setHorizontalParametersForCA(
						retrievingResultsFromAL(pkPdInst.horizontalParametersAL));

		pkPdInst
				.getWorkSheetOutputInst()
				.getSpreadSheetOutputs()
				.setVerticalParametersForCA(
						retrievingResultsFromAL(pkPdInst.verticalParametersAL));

		pkPdInst
				.getWorkSheetOutputInst()
				.getSpreadSheetOutputs()
				.setMinimizationProcessForCA(
						retrievingResultsFromAL(pkPdInst.minimizationProcessAL));

		if (pkPdInst.methodNo != 2)
			pkPdInst
					.getWorkSheetOutputInst()
					.getSpreadSheetOutputs()
					.setConditionNumberForCA(
							retrievingResultsFromAL(pkPdInst.conditionNumberAL));

	

		pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
				.setDiagnosticsForCA(
						retrievingResultsFromAL(pkPdInst.diagnosticsAL));

		pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
				.setSummaryTableForCA(
						retrievingResultsFromAL(pkPdInst.summaryTableAL));

		pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
				.setPredictedValueForCA(
						retrievingResultsFromAL(pkPdInst.predictedValueAL));

		pkPdInst
				.getWorkSheetOutputInst()
				.getSpreadSheetOutputs()
				.setInitialParameterForCA(
						retrievingResultsFromAL(pkPdInst.initialParameterAL));

		pkPdInst
				.getWorkSheetOutputInst()
				.getSpreadSheetOutputs()
				.setCorrelationMatrixForCA(
						retrievingResultsFromAL(pkPdInst.correlationMatrixAL));

		pkPdInst
				.getWorkSheetOutputInst()
				.getSpreadSheetOutputs()
				.setPartialDerivativeForCA(
						retrievingResultsFromAL(pkPdInst.partialDerivativeAL));

		pkPdInst
				.getWorkSheetOutputInst()
				.getSpreadSheetOutputs()
				.setVarienceCovarienceMatrixForCA(
						retrievingResultsFromAL(pkPdInst.varienceCovarienceMatrixAL));

		

		pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
				.setUserSettingsForCA(
						retrievingResultsFromAL(pkPdInst.userSettingsAL));

		pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
				.setEigenValuesForCA(
						retrievingResultsFromAL(pkPdInst.eigenValuesAL));

		pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
				.setHistoryForCA(
						retrievingResultsFromAL(pkPdInst.historyAL));

	
	}

	private void storeSpreedSheetResultsForSimulation() {

		SimulationForLibraryModels simulationInst = SimulationForLibraryModels
				.createSimulationInst();

		/*
		 * simulationInst.wsOutput.getSpreadSheetOutputs()
		 * .setHorizontalParametersForCA
		 * (retrievingResultsFromAL(simulationInst.horizontalParametersAL));
		 */

		simulationInst.wsOutput
				.getSpreadSheetOutputs()
				.setVerticalParametersForCA(
						retrievingResultsFromAL(simulationInst.verticalParametersAL));

		/*
		 * simulationInst.wsOutput.getSpreadSheetOutputs()
		 * .setMinimizationProcessForCA
		 * (retrievingResultsFromAL(simulationInst.minimizationProcessAL));
		 * 
		 * if(pkPdInst.methodNo != 2)
		 * simulationInst.wsOutput.getSpreadSheetOutputs()
		 * .setConditionNumberForCA
		 * (retrievingResultsFromAL(simulationInst.conditionNumberAL));
		 * 
		 * simulationInst.wsOutput.getSpreadSheetOutputs()
		 * .setSecondaryParametersForCA
		 * (retrievingResultsFromAL(simulationInst.secondaryParametersAL));
		 */

		simulationInst.wsOutput
				.getSpreadSheetOutputs()
				.setNonTransposedSPForCA(
						retrievingResultsFromAL(simulationInst.nonTransposedSPAL));

		/*
		 * simulationInst.wsOutput.getSpreadSheetOutputs()
		 * .setDiagnosticsForCA(retrievingResultsFromAL
		 * (simulationInst.diagnosticsAL));
		 * 
		 * simulationInst.wsOutput.getSpreadSheetOutputs()
		 * .setSummaryTableForCA(
		 * retrievingResultsFromAL(simulationInst.summaryTableAL));
		 * 
		 * 
		 * simulationInst.wsOutput.getSpreadSheetOutputs()
		 * .setPredictedValueForCA
		 * (retrievingResultsFromAL(simulationInst.predictedValueAL));
		 * 
		 * 
		 * simulationInst.wsOutput.getSpreadSheetOutputs()
		 * .setInitialParameterForCA
		 * (retrievingResultsFromAL(simulationInst.initialParameterAL));
		 * 
		 * 
		 * pkPdInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
		 * .setCorrelationMatrixForCA
		 * (retrievingResultsFromAL(pkPdInst.correlationMatrixAL));
		 */simulationInst.wsOutput
				.getSpreadSheetOutputs()
				.setPartialDerivativeForCA(
						retrievingResultsFromAL(simulationInst.partialDerivativeAL));

		/*
		 * simulationInst.wsOutput.getSpreadSheetOutputs()
		 * .setVarienceCovarienceMatrixForCA
		 * (retrievingResultsFromAL(simulationInst.varienceCovarienceMatrixAL));
		 */

		simulationInst.wsOutput.getSpreadSheetOutputs().setDosingValueForCA(
				retrievingResultsFromAL(simulationInst.dosingValueAL));

		/*
		 * simulationInst.wsOutput.getSpreadSheetOutputs()
		 * .setUserSettingsForCA(
		 * retrievingResultsFromAL(simulationInst.userSettingsAL));
		 */
		simulationInst.wsOutput.getSpreadSheetOutputs().setEigenValuesForCA(
				retrievingResultsFromAL(simulationInst.eigenValuesAL));

		/*
		 * simulationInst.wsOutput.getSpreadSheetOutputs()
		 * .setHistoryForCA(retrievingResultsFromAL(simulationInst.historyAL));
		 */

	}

	public String[][] retrievingResultsFromAL(ArrayList<String[]> arrayList) {
		String[][] results = new String[arrayList.size()][arrayList.get(0).length];

		for (int i = 0; i < arrayList.size(); i++) {

			results[i] = arrayList.get(i);

		}
		return results;
	}

}
