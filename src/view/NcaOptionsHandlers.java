package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaOptionsHandlers {

	public static NcaOptionsHandlers NCA_OPT_HAND = null;

	public static NcaOptionsHandlers createNcaOptHandlers() {
		if (NCA_OPT_HAND == null) {
			NCA_OPT_HAND = new NcaOptionsHandlers();
		}
		return NCA_OPT_HAND;
	}

	void excludeProfileHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (NcaOptionsGui.createNcaOptionsInstance().exclusionCheckBox
				.isSelected() == true) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.setClearExclusion(true);
		} else if (NcaOptionsGui.createNcaOptionsInstance().exclusionCheckBox
				.isSelected() == false) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.setClearExclusion(false);
		}

	}

	NcaOptionsGui ncaOptGuiInst;
	void selectionCheckHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if (NcaOptionsGui.createNcaOptionsInstance().selectionCheckBoc
				.isSelected() == true) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.setClearSelection(true);
		} else if (NcaOptionsGui.createNcaOptionsInstance().selectionCheckBoc
				.isSelected() == false) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.setClearSelection(false);
		}

	}

	void pageBreakHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {/*

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (NcaOptionsGui.createNcaOptionsInstance().pageBreaksCheckBox
				.isSelected() == false) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.setPageBreakSelected(false);
		} else if (NcaOptionsGui.createNcaOptionsInstance().pageBreaksCheckBox
				.isSelected() == true) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.setPageBreakSelected(true);
		}

	*/}

	void partialAreasComboHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().ifFromNumberOfPartialAreasCombo = true;
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().numberOfColumn = 3 + appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().ifPartialAreaScreenIsCalledFirstTime = false;
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst()
				.createSubAreasInternalFrame();
		for (int i = 0; i < NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().tableForPartialArea
				.getRowCount(); i++) {
			NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaData[i][0] = (String) NcaSubAreasDispGui
					.createNcaSubAreasDispGuiInst().tableForPartialArea
					.getValueAt(i, NcaSubAreasDispGui
							.createNcaSubAreasDispGuiInst().numberOfColumn - 2);
			NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaData[i][1] = (String) NcaSubAreasDispGui
					.createNcaSubAreasDispGuiInst().tableForPartialArea
					.getValueAt(i, NcaSubAreasDispGui
							.createNcaSubAreasDispGuiInst().numberOfColumn - 1);

		}
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaInternalFrame
				.moveToFront();
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().tableForPartialArea
				.setVisible(true);

	}
}
