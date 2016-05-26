package view;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.record.formula.atp.AnalysisToolPak;

import Model.WorkSheetOutputs;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class InVitroMapingHandler {

	boolean isSortToAvailable;
	boolean isAvailableToSort;
	boolean isAvailableToFunc;
	boolean isXToAvailable;
	boolean isAvailableToX;
	boolean isYToAvailable;
	boolean isAvailableToEndTime;
	boolean isEndTimeToAvailable;
	boolean isAvailableToVolume;
	boolean isVolumeToAvailable;
	boolean isAvailableToCarryAlong;
	boolean isCarryAlongToAvailable;
	boolean isAvailableToSubject;
	boolean isSubjectToAvailable;
	boolean isAvailableToY;

	public static InVitroMapingHandler INVITRO_MAP_HAND = null;

	public static InVitroMapingHandler createInVitroMapHandInst() {
		if (INVITRO_MAP_HAND == null) {
			INVITRO_MAP_HAND = new InVitroMapingHandler();
		}
		return INVITRO_MAP_HAND;
	}

	InVitroMappingDispGuiCreator mapDispInst;

	public void availableVarAndXVarBtnEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		mapDispInst = InVitroMappingDispGuiCreator.createMappingInstance();

		if ((isAvailableToX == true)) {
			availableVarToXVarEvent();

		}

		if (isXToAvailable) {
			xVarToAvailableVarEvent();
			removeTimeUnit();
		}
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		/*
		 * ParameterAndUnitListLoader paramAndUnitListInst =
		 * ParameterAndUnitListLoader .createParamAndUnitListInstance();
		 * paramAndUnitListInst.unitListForParameters();
		 * CaParamUnitsDispGuiCreator.createCaParUnitsDisInst()
		 * .paramUnitTableRebuilding(procInputInst);
		 */

	}

	public void xVarToAvailableVarEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		mapDispInst = InVitroMappingDispGuiCreator.createMappingInstance();

		String moving = InVitroMappingDispGuiCreator.createMappingInstance().xVariableTextField
				.getText();
		InVitroMappingDispGuiCreator.createMappingInstance().xVariableTextField
				.setText(null);

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		procInputInst.getMappingDataObj().getAvailableColumnsVector().add(
				procInputInst.getMappingDataObj().getAvailableColumnsVector()
						.size(), moving);
		procInputInst.getMappingDataObj().setxColumnCorrespondinOriginalIndex(
				-1);
		procInputInst.getMappingDataObj().setxColumnName("");

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
		InVitroMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.add(
						InVitroMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
								.getSize(), moving);
	}

	public void availableVarToXVarEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		mapDispInst = InVitroMappingDispGuiCreator.createMappingInstance();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = (String) InVitroMappingDispGuiCreator
				.createMappingInstance().inVitroAvailableColumnsList
				.getSelectedValue();

		ProcessingInputsInfo procInputInst;

		procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		int index = 0;

		for (int i = 0; i < appInfo
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
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().size(); i++) {
			if (moving
					.equals(appInfo
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
							.getColumnPropertiesArrayList().get(i)
							.getColumnName())) {
				index = i;
			}
		}

		ArrayList<ColumnProperties> columnPropAl = copyColumnPropAl();

		if (columnPropAl.get(index).getDataType().equals("Numeric")) {

			int selectedindex = InVitroMappingDispGuiCreator
					.createMappingInstance().inVitroAvailableColumnsList
					.getSelectedIndex();
			procInputInst.getMappingDataObj().getAvailableColumnsVector()
					.remove(selectedindex);

			InVitroMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
					.remove(selectedindex);
			InVitroMappingDispGuiCreator.createMappingInstance().xVariableTextField
					.setText(moving);

			procInputInst.getMappingDataObj().setxColumnName(moving);

			for (int i = 0; i < appInfo
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
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving
						.equals(appInfo
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
								.getColumnPropertiesArrayList().get(i)
								.getColumnName())) {
					procInputInst.getMappingDataObj()
							.setxColumnCorrespondinOriginalIndex(i);
				}
			}

			detectTimeUnit(procInputInst);
		} else {
			String message = "Please ensure that the column contains only numbers";
			showMessage(message);

		}

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	public void showMessage(String message) throws RowsExceededException,
			HeadlessException, WriteException, BiffException, IOException {

		JOptionPane.showMessageDialog(InVitroMainScreenCreator
				.createInVitroMainScreenInstance().inVitroMainInternalFrame,
				message, "Conform", JOptionPane.YES_OPTION);

	}

	private void removeTimeUnit() {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		String timeUnit = "";

		procInputInst.getParameterUnitsDataObj().setTimeUnit(timeUnit);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	private void detectTimeUnit(ProcessingInputsInfo procInputInst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String timeUnit = appInfo
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
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().get(
						procInputInst.getMappingDataObj()
								.getxColumnCorrespondinOriginalIndex())
				.getUnitBuilderDataObj().getTimeUnits();

		procInputInst.getParameterUnitsDataObj().setTimeUnit(timeUnit);

		InVitroParamUnitDispGuiCreator.createInVitroParUnitsDisInst()
				.paramUnitTableRebuilding(procInputInst);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	private void removeConcUnit() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		String concMassUnit = "";
		procInputInst.getParameterUnitsDataObj().setConcMassUnit(concMassUnit);

		InVitroParamUnitDispGuiCreator.createInVitroParUnitsDisInst()
				.paramUnitTableRebuilding(procInputInst);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	private void concUnitDetection(ProcessingInputsInfo procInputInst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String concMassUnit = appInfo
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
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().get(
						procInputInst.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex())
				.getUnitBuilderDataObj().getMasPrefixIndex()
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
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(
								procInputInst.getMappingDataObj()
										.getyColumnCorrespondinOriginalIndex())
						.getUnitBuilderDataObj().getMassunitIndex();
		procInputInst.getParameterUnitsDataObj().setConcMassUnit(concMassUnit);

		InVitroParamUnitDispGuiCreator.createInVitroParUnitsDisInst()
				.paramUnitTableRebuilding(procInputInst);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	public void xVarTfEvent() throws RowsExceededException, WriteException,
			BiffException, IOException {

		InVitroMappingDispGuiCreator.createMappingInstance().xAndAvailableButton
				.setIcon(InVitroMappingDispGuiCreator.createMappingInstance().toLeft);
		isXToAvailable = true;
		isAvailableToX = false;
	}

	void yVarAndAvailableVarBtnEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {
		/*
		 * ParameterAndUnitListLoader paramAndUnitListInst =
		 * ParameterAndUnitListLoader .createParamAndUnitListInstance();
		 */

		ProcessingInputsInfo processingInputs;
		if (isAvailableToY == true) {
			processingInputs = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();
			processingInputs = availableVarToYVarEvent(processingInputs);

			CaMapingHandler.createCaMapHandInst().setProcessingInput(
					processingInputs);

			processingInputs = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();
			concUnitDetection(processingInputs);

			processingInputs = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();
			// paramAndUnitListInst.unitListForParameters();
			/*
			 * CaParamUnitsDispGuiCreator.createCaParUnitsDisInst()
			 * .paramUnitTableRebuilding(processingInputs);
			 */
		}

		if (isYToAvailable == true) {
			processingInputs = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();
			yVarToAvailableVarEvent(processingInputs);
			removeConcUnit();
			processingInputs = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();
			/*
			 * paramAndUnitListInst.unitListForParameters();
			 * CaParamUnitsDispGuiCreator.createCaParUnitsDisInst()
			 * .paramUnitTableRebuilding(processingInputs);
			 */
		}
		// paramAndUnitListInst.unitListForParameters();
	}

	private void yVarToAvailableVarEvent(ProcessingInputsInfo procInputInst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String moving = InVitroMappingDispGuiCreator.createMappingInstance().yVariableTextField
				.getText();
		InVitroMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.add(
						InVitroMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
								.size(), moving);
		procInputInst.getMappingDataObj().getAvailableColumnsVector().add(
				moving);
		procInputInst.getMappingDataObj().setyColumnCorrespondinOriginalIndex(
				-1);
		procInputInst.getMappingDataObj().setYcolumnName("");

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		InVitroMappingDispGuiCreator.createMappingInstance().yVariableTextField
				.setText(null);

	}

	private ProcessingInputsInfo availableVarToYVarEvent(
			ProcessingInputsInfo procInpuInst) throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = (String) InVitroMappingDispGuiCreator
				.createMappingInstance().inVitroAvailableColumnsList
				.getSelectedValue();

		int index = 0;

		for (int i = 0; i < appInfo
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
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().size(); i++) {
			if (moving
					.equals(appInfo
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
							.getColumnPropertiesArrayList().get(i)
							.getColumnName())) {
				index = i;
			}
		}

		ArrayList<ColumnProperties> columnPropAl = copyColumnPropAl();

		if (columnPropAl.get(index).getDataType().equals("Numeric")) {

			int selectedIndex = InVitroMappingDispGuiCreator
					.createMappingInstance().inVitroAvailableColumnsList
					.getSelectedIndex();
			procInpuInst.getMappingDataObj().getAvailableColumnsVector()
					.remove(selectedIndex);
			InVitroMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
					.remove(selectedIndex);
			procInpuInst.getMappingDataObj().setYcolumnName(moving);
			InVitroMappingDispGuiCreator.createMappingInstance().yVariableTextField
					.setText(moving);

			for (int i = 0; i < appInfo
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
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving
						.equals(appInfo
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
								.getColumnPropertiesArrayList().get(i)
								.getColumnName())) {
					procInpuInst.getMappingDataObj()
							.setyColumnCorrespondinOriginalIndex(i);
				}
			}

		} else {
			String message = "Please ensure that the column contains only numbers";
			showMessage(message);

		}
		return procInpuInst;
	}

	private ArrayList<ColumnProperties> copyColumnPropAl() {
		ArrayList<ColumnProperties> columnPropAl = new ArrayList<ColumnProperties>();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		columnPropAl = appInfo
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
								.getSelectedSheetIndex()).getInVitroInfo()
				.getColumnPropertiesArrayList();

		return columnPropAl;

	}

	void yVarTfEvent() throws RowsExceededException, WriteException,
			BiffException, IOException {

		InVitroMappingDispGuiCreator.createMappingInstance().YAndAvailableButton
				.setIcon(InVitroMappingDispGuiCreator.createMappingInstance().toLeft);
		isYToAvailable = true;
		isAvailableToY = false;
	}

	void sortVarListEvent() throws RowsExceededException, WriteException,
			BiffException, IOException {

		InVitroMappingDispGuiCreator.createMappingInstance().SortAndAvailableButton
				.setIcon(InVitroMappingDispGuiCreator.createMappingInstance().toLeft);
		isSortToAvailable = true;
		isAvailableToSort = false;
	}

	void availableVarToSortVarBtnEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		if (isAvailableToSort == true) {

			availableVarToSortVarEvent();

		}

		if (isSortToAvailable == true) {

			sortVarToAvailbleVarEvent();

		}
	}

	private void sortVarToAvailbleVarEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		String moving = (String) InVitroMappingDispGuiCreator
				.createMappingInstance().inVitroSortVariablesList
				.getSelectedValue();
		int selectedIndex = InVitroMappingDispGuiCreator
				.createMappingInstance().inVitroSortVariablesList
				.getSelectedIndex();
		InVitroMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.add(
						InVitroMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
								.getSize(), moving);
		procInputInst.getMappingDataObj().getSortVariablesListVector().add(
				procInputInst.getMappingDataObj().getAvailableColumnsVector()
						.size(), moving);

		procInputInst.getMappingDataObj().getSortVariablesListVector().remove(
				selectedIndex);
		InVitroMappingDispGuiCreator.createMappingInstance().sortVariableListModel
				.remove(selectedIndex);

		procInputInst.getMappingDataObj().getSortVariablesListVector().remove(
				selectedIndex);

		procInputInst.getMappingDataObj().getAvailableColumnsVector().add(
				moving);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	private void availableVarToSortVarEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		int selectedIndex = InVitroMappingDispGuiCreator
				.createMappingInstance().inVitroAvailableColumnsList
				.getSelectedIndex();
		String moving = (String) InVitroMappingDispGuiCreator
				.createMappingInstance().inVitroAvailableColumnsList
				.getSelectedValue();
		procInputInst.getMappingDataObj().getSortVariablesListVector().add(
				procInputInst.getMappingDataObj().getSortVariablesListVector()
						.size(), moving);
		InVitroMappingDispGuiCreator.createMappingInstance().sortVariableListModel
				.add(
						InVitroMappingDispGuiCreator.createMappingInstance().sortVariableListModel
								.getSize(), moving);
		InVitroMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.remove(selectedIndex);

		procInputInst.getMappingDataObj().getAvailableColumnsVector().remove(
				selectedIndex);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	void settingVarForAvailableColList() throws RowsExceededException,
			WriteException, BiffException, IOException {

		InVitroMappingDispGuiCreator.createMappingInstance().SortAndAvailableButton
				.setIcon(InVitroMappingDispGuiCreator.createMappingInstance().toRight);
		InVitroMappingDispGuiCreator.createMappingInstance().xAndAvailableButton
				.setIcon(InVitroMappingDispGuiCreator.createMappingInstance().toRight);
		InVitroMappingDispGuiCreator.createMappingInstance().YAndAvailableButton
				.setIcon(InVitroMappingDispGuiCreator.createMappingInstance().toRight);
		InVitroMappingDispGuiCreator.createMappingInstance().moveToCarryAlongButton
				.setIcon(InVitroMappingDispGuiCreator.createMappingInstance().toRight);

		isAvailableToSort = true;
		isSortToAvailable = false;
		isAvailableToX = true;
		isXToAvailable = false;
		isAvailableToY = true;
		isYToAvailable = false;
		isAvailableToEndTime = true;
		isEndTimeToAvailable = false;
		isAvailableToVolume = true;
		isVolumeToAvailable = false;
		isAvailableToCarryAlong = true;
		isCarryAlongToAvailable = false;
		isAvailableToSubject = true;
		isSubjectToAvailable = false;
		isAvailableToFunc = true;
	}

}
