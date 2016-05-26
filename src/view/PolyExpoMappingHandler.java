package view;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PolyExpoMappingHandler {
	


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

	public static PolyExpoMappingHandler POLYEXPO_MAP_HAND = null;

	public static PolyExpoMappingHandler createPolyExpoMapHandInst() {
		if (POLYEXPO_MAP_HAND == null) {
			POLYEXPO_MAP_HAND = new PolyExpoMappingHandler();
		}
		return POLYEXPO_MAP_HAND;
	}

	PolyExpoMappingDispGuiCreator mapDispInst;

	public void availableVarAndXVarBtnEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		mapDispInst = PolyExpoMappingDispGuiCreator.createMappingInstance();

		if ((isAvailableToX == true)) {
			availableVarToXVarEvent();

		}

		if (isXToAvailable) {
			xVarToAvailableVarEvent();
			
		}
		
	}

	public void xVarToAvailableVarEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		mapDispInst = PolyExpoMappingDispGuiCreator.createMappingInstance();

		String moving = PolyExpoMappingDispGuiCreator.createMappingInstance().xVariableTextField
				.getText();
		PolyExpoMappingDispGuiCreator.createMappingInstance().xVariableTextField
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
		PolyExpoMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.add(
						PolyExpoMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
								.getSize(), moving);
	}

	public void availableVarToXVarEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		mapDispInst = PolyExpoMappingDispGuiCreator.createMappingInstance();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = (String) PolyExpoMappingDispGuiCreator
				.createMappingInstance().polyExpoAvailableColumnsList
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

			int selectedindex = PolyExpoMappingDispGuiCreator
					.createMappingInstance().polyExpoAvailableColumnsList
					.getSelectedIndex();
			procInputInst.getMappingDataObj().getAvailableColumnsVector()
					.remove(selectedindex);

			PolyExpoMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
					.remove(selectedindex);
			PolyExpoMappingDispGuiCreator.createMappingInstance().xVariableTextField
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

			
		} else {
			String message = "Please ensure that the column contains only numbers";
			showMessage(message);

		}

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	public void showMessage(String message) throws RowsExceededException,
			HeadlessException, WriteException, BiffException, IOException {

		JOptionPane.showMessageDialog(PolyExpoMainScreenCreator
				.createPolyExpoMainScreenInstance().polyExpoMainInternalFrame,
				message, "Conform", JOptionPane.YES_OPTION);

	}



	
	
	

	public void xVarTfEvent() throws RowsExceededException, WriteException,
			BiffException, IOException {

		PolyExpoMappingDispGuiCreator.createMappingInstance().xAndAvailableButton
				.setIcon(PolyExpoMappingDispGuiCreator.createMappingInstance().toLeft);
		isXToAvailable = true;
		isAvailableToX = false;
	}

	void yVarAndAvailableVarBtnEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {
		

		ProcessingInputsInfo processingInputs;
		if (isAvailableToY == true) {
			processingInputs = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();
			processingInputs = availableVarToYVarEvent(processingInputs);

			CaMapingHandler.createCaMapHandInst().setProcessingInput(
					processingInputs);

			processingInputs = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();
			

			processingInputs = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();
			
		}

		if (isYToAvailable == true) {
			processingInputs = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();
			yVarToAvailableVarEvent(processingInputs);
			
			processingInputs = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();
			
		}
		
	}

	private void yVarToAvailableVarEvent(ProcessingInputsInfo procInputInst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String moving = PolyExpoMappingDispGuiCreator.createMappingInstance().yVariableTextField
				.getText();
		PolyExpoMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.add(
						PolyExpoMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
								.size(), moving);
		procInputInst.getMappingDataObj().getAvailableColumnsVector().add(
				moving);
		procInputInst.getMappingDataObj().setyColumnCorrespondinOriginalIndex(
				-1);
		procInputInst.getMappingDataObj().setYcolumnName("");

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		PolyExpoMappingDispGuiCreator.createMappingInstance().yVariableTextField
				.setText(null);

	}

	private ProcessingInputsInfo availableVarToYVarEvent(
			ProcessingInputsInfo procInpuInst) throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = (String) PolyExpoMappingDispGuiCreator
				.createMappingInstance().polyExpoAvailableColumnsList
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

			int selectedIndex = PolyExpoMappingDispGuiCreator
					.createMappingInstance().polyExpoAvailableColumnsList
					.getSelectedIndex();
			procInpuInst.getMappingDataObj().getAvailableColumnsVector()
					.remove(selectedIndex);
			PolyExpoMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
					.remove(selectedIndex);
			procInpuInst.getMappingDataObj().setYcolumnName(moving);
			PolyExpoMappingDispGuiCreator.createMappingInstance().yVariableTextField
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
								.getSelectedSheetIndex()).getPolyExpoInfo()
				.getColumnPropertiesArrayList();

		return columnPropAl;

	}

	void yVarTfEvent() throws RowsExceededException, WriteException,
			BiffException, IOException {

		PolyExpoMappingDispGuiCreator.createMappingInstance().YAndAvailableButton
				.setIcon(PolyExpoMappingDispGuiCreator.createMappingInstance().toLeft);
		isYToAvailable = true;
		isAvailableToY = false;
	}

	void sortVarListEvent() throws RowsExceededException, WriteException,
			BiffException, IOException {

		PolyExpoMappingDispGuiCreator.createMappingInstance().SortAndAvailableButton
				.setIcon(PolyExpoMappingDispGuiCreator.createMappingInstance().toLeft);
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

		String moving = (String) PolyExpoMappingDispGuiCreator
				.createMappingInstance().polyExpoSortVariablesList
				.getSelectedValue();
		int selectedIndex = PolyExpoMappingDispGuiCreator
				.createMappingInstance().polyExpoSortVariablesList
				.getSelectedIndex();
		PolyExpoMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.add(
						PolyExpoMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
								.getSize(), moving);
		procInputInst.getMappingDataObj().getSortVariablesListVector().add(
				procInputInst.getMappingDataObj().getAvailableColumnsVector()
						.size(), moving);

		procInputInst.getMappingDataObj().getSortVariablesListVector().remove(
				selectedIndex);
		PolyExpoMappingDispGuiCreator.createMappingInstance().sortVariableListModel
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

		int selectedIndex = PolyExpoMappingDispGuiCreator
				.createMappingInstance().polyExpoAvailableColumnsList
				.getSelectedIndex();
		String moving = (String) PolyExpoMappingDispGuiCreator
				.createMappingInstance().polyExpoAvailableColumnsList
				.getSelectedValue();
		procInputInst.getMappingDataObj().getSortVariablesListVector().add(
				procInputInst.getMappingDataObj().getSortVariablesListVector()
						.size(), moving);
		PolyExpoMappingDispGuiCreator.createMappingInstance().sortVariableListModel
				.add(
						PolyExpoMappingDispGuiCreator.createMappingInstance().sortVariableListModel
								.getSize(), moving);
		PolyExpoMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.remove(selectedIndex);

		procInputInst.getMappingDataObj().getAvailableColumnsVector().remove(
				selectedIndex);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	void settingVarForAvailableColList() throws RowsExceededException,
			WriteException, BiffException, IOException {

		PolyExpoMappingDispGuiCreator.createMappingInstance().SortAndAvailableButton
				.setIcon(PolyExpoMappingDispGuiCreator.createMappingInstance().toRight);
		PolyExpoMappingDispGuiCreator.createMappingInstance().xAndAvailableButton
				.setIcon(PolyExpoMappingDispGuiCreator.createMappingInstance().toRight);
		PolyExpoMappingDispGuiCreator.createMappingInstance().YAndAvailableButton
				.setIcon(PolyExpoMappingDispGuiCreator.createMappingInstance().toRight);
		PolyExpoMappingDispGuiCreator.createMappingInstance().moveToCarryAlongButton
				.setIcon(PolyExpoMappingDispGuiCreator.createMappingInstance().toRight);

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
