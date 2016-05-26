package view;

import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CaWeightDoseActionHandler {

	
	CaWeightDoseDispGuiCreator weightDoseDispInst;
	CaDosingDispGuiCreator dosingDispInst;
	CaNoOfSubjectDeterminer determineNoOfSubInst;
	
	public void doseUnitButtonActionHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {
		weightDoseDispInst = CaWeightDoseDispGuiCreator
				.createCaWeightDoseInstance();

		weightDoseDispInst.doseUnitTextFieldForCA.setEditable(true);
		weightDoseDispInst.ifFromDoseUnitOfCA = true;
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().isFromPreferredUnitInternalFrame = false;
		CaLibModelDispGuiCreator.createCalibraryModelInstance().ifFromPkUnit = false;
		UnitBuilder.createUBInstance().unitBuilderFrame.setVisible(true);

	}

	public void doseUnitTfAtionHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		
		
		if (analysisType.equals(
						"pk")) {
			procInputInst
					.getModelInputTab1Obj()
					.setDoseUnit(
							CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().doseUnitTextFieldForCA
									.getText());
		} else if (analysisType.equals("pd")) {
			procInputInst
					.getModelInputTab1Obj()
					.setDoseUnit(
							CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().doseUnitTextFieldForCA
									.getText());
		} else if (analysisType.equals("mm")) {
			procInputInst
					.getModelInputTab1Obj()
					.setDoseUnit(
							CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().doseUnitTextFieldForCA
									.getText());
		} else if (analysisType.equals(
				"pkpdlink")) {
			procInputInst
					.getModelInputTab1Obj()
					.setDoseUnit(
							CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().doseUnitTextFieldForCA
									.getText());
		} else if (analysisType
				.equals("irm")) {
			procInputInst
					.getModelInputTab1Obj()
					.setDoseUnit(
							CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().doseUnitTextFieldForCA
									.getText());
		}
		
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}
	
	
	public void noOfDoseCbActionHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		actionListenerEventForNumberOfDoseComboBox();

	}

	
	public void actionListenerEventForNumberOfDoseComboBox() throws RowsExceededException, WriteException, BiffException, IOException {
		 determineNoOfSubInst = CaNoOfSubjectDeterminer.createDetermineNoOfSubInstance();
	
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		procInputInst.getModelInputTab1Obj()
				.setNumberOfDose(CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().numberOfDoseComboBox.getSelectedIndex() + 1);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
		
		
		weightDoseDispInst = CaWeightDoseDispGuiCreator.createCaWeightDoseInstance();
		weightDoseDispInst.previousNumberOfDoseForCA = CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().numberOfDoseForCA;
		weightDoseDispInst.numberOfDoseForCA = procInputInst.getModelInputTab1Obj().getNumberOfDose();
	
		dosingDispInst = CaDosingDispGuiCreator.createCaDosingDispGui();
		
		if (dosingDispInst.tableForDosing.isEditing())
			dosingDispInst.tableForDosing.getCellEditor()
					.stopCellEditing();
			
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int libModelNumber = 0;
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();		
		if(analysisType.equals("pk")|| analysisType.equals("pd")
			|| analysisType.equals("mm"))
		{
			libModelNumber = procInputInst.getModelInputTab1Obj().getModelNumberForCA();
		}else
		{
			libModelNumber = procInputInst.getModelInputTab1Obj().getModelNumberForLinkModel();
		}
		
		
		
		if (weightDoseDispInst.numberOfDoseComboBox.getSelectedIndex() >= 1)
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS.setEnabled(false);
		else {
			if (CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueComboBox.getSelectedIndex() == 0
					&& (libModelNumber != 14
							|| libModelNumber != 15 || libModelNumber != 16))
				CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS.setEnabled(true);
			else
				CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS.setEnabled(false);
	
		}
	
		int rowInsertPosition = 0;
		int rowRemovePosition = 0;
	
		
		procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		
		procInputInst
				.getDosingDataObj()	.setDosingDSForNCA(
						new String[determineNoOfSubInst.noOfSubject * CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().numberOfDoseForCA  
								][procInputInst.getDosingDataObj().getDosingDSForNCA()[0].length]);
	
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
		
		if (weightDoseDispInst.previousNumberOfDoseForCA < weightDoseDispInst.numberOfDoseForCA) {
			weightDoseDispInst.isFromRowRemoval = true;
				for (int i = 0; i < determineNoOfSubInst.noOfSubject; i++) {
				rowInsertPosition = rowInsertPosition
						+ weightDoseDispInst.previousNumberOfDoseForCA;
				String[] s = new String[CaDosingDispGuiCreator.createCaDosingDispGui().numberOfColumnForDosing];
				for (int k = 0; k < procInputInst.getMappingDataObj()
						.getSortVariablesListVector().size(); k++) {
					s[k] = determineNoOfSubInst.dataSortVariables[i][k];
				}
				for (int j = 0; j <weightDoseDispInst. numberOfDoseForCA
						- weightDoseDispInst.previousNumberOfDoseForCA; j++) {
					s[procInputInst.getMappingDataObj()
							.getSortVariablesListVector().size()] = (weightDoseDispInst.previousNumberOfDoseForCA
							+ j + 1)
							+ "";
	
					((DefaultTableModel) dosingDispInst.tableForDosing.getModel()).insertRow(
							rowInsertPosition, s);
					rowInsertPosition++;
				}
			}
		
				weightDoseDispInst.isFromRowRemoval = false;
		} else if (weightDoseDispInst.previousNumberOfDoseForCA > weightDoseDispInst.numberOfDoseForCA) {
			weightDoseDispInst.isFromRowRemoval = true;
	
			for (int i = 0; i < determineNoOfSubInst.noOfSubject; i++) {
				rowRemovePosition = rowRemovePosition + weightDoseDispInst.numberOfDoseForCA;
				for (int j = weightDoseDispInst.previousNumberOfDoseForCA
						- weightDoseDispInst.numberOfDoseForCA -1; j >=0 ; j--) {
					((DefaultTableModel) dosingDispInst.tableForDosing.getModel())
							.removeRow(rowRemovePosition + j);
	
				}
			}
			weightDoseDispInst.isFromRowRemoval = false;
		}
		
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
		dosingDispInst.DosingInternalFrame.validate();
		dosingDispInst.DosingInternalFrame.repaint();
	}


	public void normUnitCbActionhandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			
		actionListenerEventForNormalizationUnitComboBoxForCA();

	}
	
	public void actionListenerEventForNormalizationUnitComboBoxForCA() throws RowsExceededException, WriteException, BiffException, IOException {
	
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ParameterAndUnitListLoader paramAndUnitListInst = ParameterAndUnitListLoader.createParamAndUnitListInstance();
		
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		procInputInst.getModelInputTab1Obj()
				.setNormalizationIndex(
						CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().normalizationUnitComboBoxForCA.getSelectedIndex());
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
		paramAndUnitListInst.unitListForParameters();
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().paramUnitTableRebuilding(procInputInst);
		
		if (appInfo.isRetrievalFlag() == false)
			paramAndUnitListInst.unitListForParameters();
	
	}
	
	
	public void obsWeightRbActionHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		actionListenerEventForObservedWeightingRadioButton();

	}
	
	
	
	public void actionListenerEventForObservedWeightingRadioButton() throws RowsExceededException, WriteException, BiffException, IOException
	{
		weightDoseDispInst = CaWeightDoseDispGuiCreator
		.createCaWeightDoseInstance();		
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		
		if (weightDoseDispInst.observedWeightingRadioButton.isSelected() == true) {
			weightDoseDispInst.predictedWeightingRadioButton.setSelected(false);
			weightDoseDispInst.weightingOptionsComboBox.setEnabled(true);
			weightDoseDispInst.predictedWeightingOptionsComboBox.setEnabled(false);
			
			procInputInst.getModelInputTab1Obj()
					.setWeightingScheme(0);
		} else {
			weightDoseDispInst.weightingOptionsComboBox.setEnabled(false);
			procInputInst.getModelInputTab1Obj()
					.setWeightingScheme(-1);
		}
		
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}
	
	
	public void actionListenerEventForweightingOptionsComboBox(
			ApplicationInfo appInfo) {/*
		ProcessingInputsInfo procInputInst = CaMapingHandler.createPDMapHandInst().copyProcessingInput();

		procInputInst.getModelInputTab1Obj()
				.setWeightingIndex(caMainScreen.weightingOptionsComboBox.getSelectedIndex());
		CaMapingHandler.createPDMapHandInst().setProcessingInput(procInputInst);
		
		
	*/}
	
	
	public void actionListenerEventForWeightOptCB()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		CaWeightDoseDispGuiCreator weightDoseDispInst = CaWeightDoseDispGuiCreator
				.createCaWeightDoseInstance();
		
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.equals(
						"pk")) {
			procInputInst.getModelInputTab1Obj()
					.setWeightingIndex(
							weightDoseDispInst.weightingOptionsComboBox
									.getSelectedIndex());
		} else if (analysisType.equals("pd")) {
			procInputInst.getModelInputTab1Obj()
					.setWeightingIndex(
							weightDoseDispInst.weightingOptionsComboBox
									.getSelectedIndex());
		} else if (analysisType.equals("mm")) {
			procInputInst.getModelInputTab1Obj()
					.setWeightingIndex(
							weightDoseDispInst.weightingOptionsComboBox
									.getSelectedIndex());
		} else if (analysisType.equals("pkpdlink")) {
			procInputInst.getModelInputTab1Obj()
					.setWeightingIndex(
							weightDoseDispInst.weightingOptionsComboBox
									.getSelectedIndex());
		} else if (analysisType.equals("irm")) {
			procInputInst.getModelInputTab1Obj()
					.setWeightingIndex(
							weightDoseDispInst.weightingOptionsComboBox
									.getSelectedIndex());
		}
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	public void actionListenerEventForPrediWeightRB() throws RowsExceededException, WriteException, BiffException, IOException {
	
		CaWeightDoseDispGuiCreator weightDoseDispInst = CaWeightDoseDispGuiCreator
		.createCaWeightDoseInstance();		
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		
		
		if (weightDoseDispInst.predictedWeightingRadioButton.isSelected() == true) {
			weightDoseDispInst.observedWeightingRadioButton.setSelected(false);
			weightDoseDispInst.weightingOptionsComboBox.setEditable(false);
			weightDoseDispInst.predictedWeightingOptionsComboBox.setEnabled(true);
			procInputInst.getModelInputTab1Obj()
					.setWeightingScheme(1);
		} else {
			weightDoseDispInst.predictedWeightingOptionsComboBox.setEnabled(false);
			procInputInst.getModelInputTab1Obj()
					.setWeightingScheme(-1);
		}
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	public void actionListenerEventForPredWeighOptionsCB()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		CaWeightDoseDispGuiCreator weightDoseDispInst = CaWeightDoseDispGuiCreator
				.createCaWeightDoseInstance();
		ProcessingInputsInfo procinputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		
		if (analysisType.equals("pk")) {
			procinputInst
					.getModelInputTab1Obj()
					.setWeightingIndex(
							weightDoseDispInst.predictedWeightingOptionsComboBox
									.getSelectedIndex());

		} else if (analysisType.equals("pd")) {
			procinputInst
					.getModelInputTab1Obj()
					.setWeightingIndex(
							weightDoseDispInst.predictedWeightingOptionsComboBox
									.getSelectedIndex());

		} else if (analysisType.equals("mm")) {
			procinputInst
					.getModelInputTab1Obj()
					.setWeightingIndex(
							weightDoseDispInst.predictedWeightingOptionsComboBox
									.getSelectedIndex());
		} else if (analysisType.equals("pkpdlink")) {
			procinputInst
					.getModelInputTab1Obj()
					.setWeightingIndex(
							weightDoseDispInst.predictedWeightingOptionsComboBox
									.getSelectedIndex());
		} else if (analysisType.equals("irm")) {
			procinputInst
					.getModelInputTab1Obj()
					.setWeightingIndex(
							weightDoseDispInst.predictedWeightingOptionsComboBox
									.getSelectedIndex());
		}
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procinputInst);

	}

}
