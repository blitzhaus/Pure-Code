package Model;

import java.util.ArrayList;

public class DeConvoResultStructStorer {

	public void storeSpreedSheetResults() {
		
		DeConvolutionVariables deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();

		deConvoVarInst
				.getWorkSheetOutputInst()
				.getSpreadSheetOutputs()
				.setHorizontalParametersForCA(
						retrievingResultsFromAL(deConvoVarInst.horizontalParametersAL));

		deConvoVarInst
				.getWorkSheetOutputInst()
				.getSpreadSheetOutputs()
				.setVerticalParametersForCA(
						retrievingResultsFromAL(deConvoVarInst.verticalParametersAL));

		
		
		deConvoVarInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
				.setSummaryTableForCA(
						retrievingResultsFromAL(deConvoVarInst.summaryTableAL));

		deConvoVarInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
				.setPredictedValueForCA(
						retrievingResultsFromAL(deConvoVarInst.predictedValueAL));

		
		deConvoVarInst
				.getWorkSheetOutputInst()
				.getSpreadSheetOutputs()
				.setExclusionForDeConvo(
						retrievingResultsFromAL(deConvoVarInst.exclusionAL));

		
		deConvoVarInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
				.setDosingValueForCA(
						retrievingResultsFromAL(deConvoVarInst.dosingValueAL));

		deConvoVarInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
				.setUserSettingsForCA(
						retrievingResultsFromAL(deConvoVarInst.userSettingsAL));

		deConvoVarInst.getWorkSheetOutputInst().getSpreadSheetOutputs()
				.setHistoryForCA(
						retrievingResultsFromAL(deConvoVarInst.historyAL));

	
	}
	
	public String[][] retrievingResultsFromAL(ArrayList<String[]> arrayList) {
		String[][] results = new String[arrayList.size()][arrayList.get(0).length];

		for (int i = 0; i < arrayList.size(); i++) {

			results[i] = arrayList.get(i);

		}
		return results;
	}

}
