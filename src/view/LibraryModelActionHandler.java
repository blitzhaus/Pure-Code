package view;

import java.io.IOException;

import com.itextpdf.text.log.SysoLogger;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class LibraryModelActionHandler {

	public static LibraryModelActionHandler LIB_MOD_ACT_HAND = null;

	public static LibraryModelActionHandler createLibModelActionHandInst() {
		if (LIB_MOD_ACT_HAND == null) {
			LIB_MOD_ACT_HAND = new LibraryModelActionHandler();
		}
		return LIB_MOD_ACT_HAND;
	}

	public LibraryModelActionHandler() {

		count = 0;
	}

	int count = 0;

	public void ActionEventForClearance() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		if (CaLibModelDispGuiCreator.createCalibraryModelInstance().clearanceCb
				.isSelected() == true) {
			procInputInst.getModelInputTab1Obj().setIfClearanceParam(true);
		} else
			procInputInst.getModelInputTab1Obj().setIfClearanceParam(false);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	public void actionHandlerForPKUnitBtn() throws RowsExceededException,
			WriteException, BiffException, IOException {
		CaLibModelDispGuiCreator pdLibModDispInst = CaLibModelDispGuiCreator
				.createCalibraryModelInstance();

		pdLibModDispInst.pkUnitTf.setEditable(true);

		pdLibModDispInst.ifFromPkUnit = true;
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().isFromPreferredUnitInternalFrame = false;
		CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().ifFromDoseUnitOfCA = false;
		pdLibModDispInst.ifFromSimulationUnit = false;

		UnitBuilder.createUBInstance().unitBuilderFrame.setVisible(true);

	}

	public void actionHandlerForSimulationUnitBtn()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		CaLibModelDispGuiCreator pdLibModDispInst = CaLibModelDispGuiCreator
				.createCalibraryModelInstance();

		pdLibModDispInst.ifFromPkUnit = false;
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().isFromPreferredUnitInternalFrame = false;
		CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().ifFromDoseUnitOfCA = false;
		pdLibModDispInst.ifFromSimulationUnit = true;

		UnitBuilder.createUBInstance().unitBuilderFrame.setVisible(true);

	}

	public void actionEventForSimulation() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		if (CaLibModelDispGuiCreator.createCalibraryModelInstance().simulationCb
				.isSelected() == true) {
			procInputInst.getModelInputTab1Obj().setSimulation(true);
			CaLibModelDispGuiCreator.createCalibraryModelInstance().simulationUnitButton
					.setVisible(true);
			CaLibModelDispGuiCreator.createCalibraryModelInstance().simulationUnitTf
					.setVisible(true);
			changeStateOfInitParamOptions(false);
		} else {
			procInputInst.getModelInputTab1Obj().setSimulation(false);
			CaLibModelDispGuiCreator.createCalibraryModelInstance().simulationUnitButton
					.setVisible(false);
			CaLibModelDispGuiCreator.createCalibraryModelInstance().simulationUnitTf
					.setVisible(false);
			changeStateOfInitParamOptions(true);
		}

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}


	private void changeStateOfInitParamOptions(boolean state) throws RowsExceededException,
			WriteException, BiffException, IOException {
		
		if(state == false)
		{
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueComboBox.setSelectedIndex(1);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().parameterBoundariesComboBox.setSelectedIndex(1);
			
		}else
		{
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueComboBox.setSelectedIndex(0);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().parameterBoundariesComboBox.setSelectedIndex(0);
			
		}
		
		
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueComboBox
				.setEnabled(state);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
				.setEnabled(state);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
				.setEnabled(state);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
				.setEnabled(state);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().previouslyGenInitilParam
				.setEnabled(state);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().parameterBoundariesComboBox
				.setEnabled(state);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().propFinalEstimateCb
				.setEnabled(state);

	}
}
