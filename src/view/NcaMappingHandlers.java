package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaMappingHandlers {

	public static NcaMappingHandlers NCA_MAP_HAND = null;

	public static NcaMappingHandlers createNCAMapHandInst() {
		if (NCA_MAP_HAND == null) {
			NCA_MAP_HAND = new NcaMappingHandlers();
		}
		return NCA_MAP_HAND;
	}

	public NcaMappingHandlers() {

	}
	
	NcaMappingDispGui ncamapDispGui = null;
	
	void availableColumnListSelectionEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {
		
		ncamapDispGui =  NcaMappingDispGui.createMappingInstance();
		
		NcaMappingDispGui.createMappingInstance().SortAndAvailableButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toLeft);
		NcaMappingDispGui.createMappingInstance().xAndAvailableButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toLeft);
		NcaMappingDispGui.createMappingInstance().YAndAvailableButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toLeft);
		NcaMappingDispGui.createMappingInstance().moveToEndTimeButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toLeft);
		NcaMappingDispGui.createMappingInstance().moveToVolumeButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toRight);
		NcaMappingDispGui.createMappingInstance().setAsSubjectButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toRight);
		NcaMappingDispGui.createMappingInstance().moveToCarryAlongButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toRight);
		NcaMappingDispGui.createMappingInstance().isAvailableToSort = true;
		NcaMappingDispGui.createMappingInstance().isSortToAvailable = false;
		NcaMappingDispGui.createMappingInstance().isAvailableToX = true;
		NcaMappingDispGui.createMappingInstance().isXToAvailable = false;
		NcaMappingDispGui.createMappingInstance().isAvailableToY = true;
		NcaMappingDispGui.createMappingInstance().isYToAvailable = false;
		NcaMappingDispGui.createMappingInstance().isAvailableToEndTime = true;
		NcaMappingDispGui.createMappingInstance().isEndTimeToAvailable = false;
		NcaMappingDispGui.createMappingInstance().isAvailableToVolume = true;
		NcaMappingDispGui.createMappingInstance().isVolumeToAvailable = false;
		NcaMappingDispGui.createMappingInstance().isAvailableToCarryAlong = true;
		NcaMappingDispGui.createMappingInstance().isCarryAlongToAvailable = false;
		NcaMappingDispGui.createMappingInstance().isAvailableToSubject = true;
		NcaMappingDispGui.createMappingInstance().isSubjectToAvailable = false;
	}

	ApplicationInfo appInfo;
	void sortAndAvailableButtonActnHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		appInfo = DisplayContents.createAppInfoInstance();
		if (NcaMappingDispGui.createMappingInstance().isAvailableToSort == true) {
			int selectedIndex = NcaMappingDispGui.createMappingInstance().availableColumnsList
					.getSelectedIndex();
			
			String moving = (String) NcaMappingDispGui.createMappingInstance().availableColumnsList
					.getSelectedValue();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getSortVariablesListVector()
					.add(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size(),
							moving);
			NcaMappingDispGui.createMappingInstance().sortVariableListModel
					.add(
							NcaMappingDispGui.createMappingInstance().sortVariableListModel
									.getSize(), moving);
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.remove(selectedIndex);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedIndex);
			
			for (int i = 0; i <appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().size(); i++) {
				

			}

		}

		if (NcaMappingDispGui.createMappingInstance().isSortToAvailable == true) {

			String moving = (String) NcaMappingDispGui.createMappingInstance().sortVariablesList
					.getSelectedValue();
			int selectedIndex = NcaMappingDispGui.createMappingInstance().sortVariablesList
					.getSelectedIndex();
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.add(
							NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
									.getSize(), moving);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().remove(selectedIndex);
			NcaMappingDispGui.createMappingInstance().sortVariableListModel
					.remove(selectedIndex);
			


		}

	}

	void sortCOlumnsListSelectionEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {
		NcaMappingDispGui.createMappingInstance().SortAndAvailableButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toRight);
		NcaMappingDispGui.createMappingInstance().isSortToAvailable = true;
		NcaMappingDispGui.createMappingInstance().isAvailableToSort = false;
		NcaMappingDispGui.createMappingInstance().isAvailableToY = true;
		NcaMappingDispGui.createMappingInstance().isYToAvailable = false;
		NcaMappingDispGui.createMappingInstance().isAvailableToX = true;
		NcaMappingDispGui.createMappingInstance().isXToAvailable = false;

	}
	
	DefaultAndPrefferedUnit defaultAndPrefUnitInst;

	void yAndAvailButtonActnHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (NcaMappingDispGui.createMappingInstance().isAvailableToY == true) {

			availToYvarAction();
		}

		if (NcaMappingDispGui.createMappingInstance().isYToAvailable == true) {

			YvarToAvaialAction();
		}

		// default and preferred units preparation based on the model type
		defaultAndPrefUnitInst = new DefaultAndPrefferedUnit();
		defaultAndPrefUnitInst.preparationOfparameterByGroup();
		buildDefaultUnitsX(appInfo);

	}

	private void buildDefaultUnitsX(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.getSelectedIndex() == 1) {

			for (int i = 0; i < 8; i++) {
				String[] s = new String[3];
				String c =appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getParameterbyGroupValueAt(i);
				s[0] = c;
				String u =appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getDefaultUnitValueAt(i);
				s[1] = u;
				s[2] = u;
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.setPreferredUnit(i, u);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.setDefaultUnitValueAt(i, u);
			}


		} else {

			for (int i = 0; i < 15; i++) {

				String[] s = new String[3];

				String c =appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getParameterbyGroupValueAt(i);
				s[0] = c;

				String u =appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getDefaultUnitValueAt(i);
				s[1] = u;
				s[2] = u;
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.setPreferredUnit(i, u);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.setDefaultUnitValueAt(i, u);
				// ((DefaultTableModel)parameterUnitsTable.getModel()).insertRow(parameterUnitsTable.getRowCount(),
				// s);

			}

		}
	}

	private void YvarToAvaialAction() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = NcaMappingDispGui.createMappingInstance().yVariableTextField
				.getText();
		NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
				.add(
						NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
								.size(), moving);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().add(moving);
		NcaMappingDispGui.createMappingInstance().yVariableTextField
				.setText("");
		NcaMappingDispGui.createMappingInstance().previousYColumnName = moving;

		

	}

	private void availToYvarAction() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = (String) NcaMappingDispGui.createMappingInstance().availableColumnsList
				.getSelectedValue();
		
		NcaMappingDispGui.createMappingInstance().previousYColumnName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName();

		int selectedIndex = NcaMappingDispGui.createMappingInstance().availableColumnsList
				.getSelectedIndex();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().remove(selectedIndex);
		NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
				.remove(selectedIndex);

		// if the y text field does not contain an empty string
		// then move it to the available variables
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName() != "") {
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.add(
							NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
									.size(), NcaMappingDispGui
									.createMappingInstance().yVariableTextField
									.getText());
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
							NcaMappingDispGui.createMappingInstance().yVariableTextField
									.getText());
			NcaMappingDispGui.createMappingInstance().previousYColumnName = NcaMappingDispGui
					.createMappingInstance().yVariableTextField.getText();
		}

		// set the y column name
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().setYcolumnName(
						moving);

		// asign the new ycolumn name to the ytext field
		NcaMappingDispGui.createMappingInstance().yVariableTextField
				.setText(moving);

		// setting y column corresponding original index

		for (int i = 0; i <appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().size(); i++) {
			if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName())) {
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.setyColumnCorrespondinOriginalIndex(i);
			}
		}
	}

	void yVarTextFieldHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {
		NcaMappingDispGui.createMappingInstance().YAndAvailableButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toRight);
		NcaMappingDispGui.createMappingInstance().isYToAvailable = true;
		NcaMappingDispGui.createMappingInstance().isAvailableToY = false;

	}

	public void xVarTextFieldHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {
		// TODO Auto

		NcaMappingDispGui.createMappingInstance().xAndAvailableButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toRight);
		NcaMappingDispGui.createMappingInstance().isXToAvailable = true;
		NcaMappingDispGui.createMappingInstance().isAvailableToX = false;

	}

	public void xAndAvailButtonActnHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if ((NcaMappingDispGui.createMappingInstance().isAvailableToX == true)) {

			availToXVarAction();
		}/*
		 * else if(xVariableTextField.getText() != ""){System.out.println(
		 * "Sorry boss please move and the existing value is  "
		 * +xVariableTextField.getText()); }
		 */

		if (NcaMappingDispGui.createMappingInstance().isXToAvailable) {

			xToAvailAction();
		}
		DefaultAndPrefferedUnit defAndPrefUnitInst = new DefaultAndPrefferedUnit();
		defAndPrefUnitInst.preparationOfparameterByGroup();
		buildDefaultUnitsY(appInfo);

	}

	private void buildDefaultUnitsY(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.getSelectedIndex() == 1) {

			for (int i = 0; i < 8; i++) {
				String[] s = new String[3];
				String c =appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getParameterbyGroupValueAt(i);
				s[0] = c;
				String u =appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getDefaultUnitValueAt(i);
				s[1] = u;
				s[2] = u;
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.setPreferredUnit(i, u);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.setDefaultUnitValueAt(i, u);
				// ((DefaultTableModel)parameterUnitsTable.getModel()).insertRow(parameterUnitsTable.getRowCount(),
				// s);
			}
			

		} else if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.getSelectedIndex() == 0) {

			for (int i = 0; i < 16; i++) {

				String[] s = new String[3];

				String c =appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getParameterbyGroupValueAt(i);
				s[0] = c;

				String u =appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getDefaultUnitValueAt(i);
				s[1] = u;
				s[2] = u;
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.setPreferredUnit(i, u);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.setDefaultUnitValueAt(i, u);
				// ((DefaultTableModel)parameterUnitsTable.getModel()).insertRow(parameterUnitsTable.getRowCount(),
				// s);

			}

		}
	}

	private void xToAvailAction() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NcaMappingDispGui.createMappingInstance().previousXVariable = NcaMappingDispGui
				.createMappingInstance().xVariableTextField.getText();
		;
		String moving = NcaMappingDispGui.createMappingInstance().xVariableTextField
				.getText();
		NcaMappingDispGui.createMappingInstance().xVariableTextField
				.setText(null);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getNcaInfo()
				.getProcessingInputs()
				.getMappingDataObj()
				.getAvailableColumnsVector()
				.add(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo().getProcessingInputs()
								.getMappingDataObj()
								.getAvailableColumnsVector().size(), moving);
		NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
				.add(
						NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
								.getSize(), moving);

	}

	private void availToXVarAction() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NcaMappingDispGui.createMappingInstance().previousXVariable =appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName();
		String moving = (String) NcaMappingDispGui.createMappingInstance().availableColumnsList
				.getSelectedValue();
		int selectedindex = NcaMappingDispGui.createMappingInstance().availableColumnsList
				.getSelectedIndex();
		
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().remove(selectedindex);

		// add the previous time column to the available columns
		// if there is a time column already present
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName() != "") {
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.add(
							NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
									.size(), NcaMappingDispGui
									.createMappingInstance().xVariableTextField
									.getText());
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
							NcaMappingDispGui.createMappingInstance().xVariableTextField
									.getText());
		}

		NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
				.remove(selectedindex);
		NcaMappingDispGui.createMappingInstance().xVariableTextField
				.setText(moving);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().setxColumnName(
						moving);

		for (int i = 0; i <appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().size(); i++) {
			if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName())) {
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.setxColumnCorrespondinOriginalIndex(i);
			}
		}
	}

	void endTimeTextFieldAction() throws RowsExceededException, WriteException,
			BiffException, IOException {

		NcaMappingDispGui.createMappingInstance().moveToEndTimeButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toRight);
		NcaMappingDispGui.createMappingInstance().isEndTimeToAvailable = true;
		NcaMappingDispGui.createMappingInstance().isAvailableToEndTime = false;

	}

	void availToCarryAlongButtonAction() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (NcaMappingDispGui.createMappingInstance().isAvailableToCarryAlong == true) {

			int selectedIndex = NcaMappingDispGui.createMappingInstance().availableColumnsList
					.getSelectedIndex();
			
			String moving = (String) NcaMappingDispGui.createMappingInstance().availableColumnsList
					.getSelectedValue();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getCarryAlongVariablesListVector()
					.add(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getCarryAlongVariablesListVector().size(),
							moving);
			NcaMappingDispGui.createMappingInstance().carryAlongModel.add(
					NcaMappingDispGui.createMappingInstance().carryAlongModel
							.getSize(), moving);
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.remove(selectedIndex);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedIndex);

		} else if (NcaMappingDispGui.createMappingInstance().isAvailableToCarryAlong == false) {
			String moving = (String) NcaMappingDispGui.createMappingInstance().carryAlongList
					.getSelectedValue();
			int selectedIndex = NcaMappingDispGui.createMappingInstance().carryAlongList
					.getSelectedIndex();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.add(
							NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
									.getSize(), moving);
			NcaMappingDispGui.createMappingInstance().carryAlongModel
					.remove(selectedIndex);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getCarryAlongVariablesListVector().remove(selectedIndex);

		}

	}

	void carryAlongListSelectionHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		NcaMappingDispGui.createMappingInstance().isAvailableToCarryAlong = false;
		NcaMappingDispGui.createMappingInstance().isCarryAlongToAvailable = true;
		NcaMappingDispGui.createMappingInstance().moveToCarryAlongButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toLeft);

	}

	
	MappIngData mappingDataObj;
	
	void availToSubjectButtonHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (NcaMappingDispGui.createMappingInstance().isAvailableToSubject == true) {

			NcaMappingDispGui.createMappingInstance().previousSubjectColumnName =appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSubjectColumnName();
			String moving = (String) NcaMappingDispGui.createMappingInstance().availableColumnsList
					.getSelectedValue();
			int selectedIndex = NcaMappingDispGui.createMappingInstance().availableColumnsList
					.getSelectedIndex();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedIndex);
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.remove(selectedIndex);

			// add the preious subject varialble to the available columns
			// if it is present
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSubjectColumnName() != "") {
				NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
						.add(
								NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
										.size(),
								NcaMappingDispGui.createMappingInstance().subjectTextField
										.getText());
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getNcaInfo()
						.getProcessingInputs()
						.getMappingDataObj()
						.getAvailableColumnsVector()
						.add(
								NcaMappingDispGui.createMappingInstance().subjectTextField
										.getText());
			}

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.setSubjectColumnName(moving);

			NcaMappingDispGui.createMappingInstance().subjectTextField
					.setText(moving);
			for (int i = 0; i <appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(i).getColumnName())) {
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj()
							.setSubjectColumnCorrespondinOriginalIndex(i);
				}
			}
		}

		if (NcaMappingDispGui.createMappingInstance().isSubjectToAvailable == true) {
			NcaMappingDispGui.createMappingInstance().previousSubjectColumnName = NcaMappingDispGui
					.createMappingInstance().subjectTextField.getText();
			String moving = NcaMappingDispGui.createMappingInstance().subjectTextField
					.getText();
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.add(
							NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
									.size(), moving);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().add(moving);
			NcaMappingDispGui.createMappingInstance().subjectTextField
					.setText(null);
		}

	}

	void availToVolumeButtonAction() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (NcaMappingDispGui.createMappingInstance().isAvailableToVolume == true) {

			NcaMappingDispGui.createMappingInstance().previousVolumeColumnName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getVolumeColumnName();
			String moving = (String) NcaMappingDispGui.createMappingInstance().availableColumnsList
					.getSelectedValue();
			
			int selectedIndex = NcaMappingDispGui.createMappingInstance().availableColumnsList
					.getSelectedIndex();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedIndex);
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.remove(selectedIndex);

			// add the previous volume column if present to the available list
			// of columns
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getVolumeColumnName() != "") {
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getNcaInfo()
						.getProcessingInputs()
						.getMappingDataObj()
						.getAvailableColumnsVector()
						.add(
								NcaMappingDispGui.createMappingInstance().volumeVariableTextField
										.getText());
				NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
						.add(
								NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
										.size(),
								NcaMappingDispGui.createMappingInstance().volumeVariableTextField
										.getText());
			}

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.setVolumeColumnName(moving);
			NcaMappingDispGui.createMappingInstance().volumeVariableTextField
					.setText(moving);
			for (int i = 0; i <appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(i).getColumnName())) {
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj()
							.setVolumeColumnCorrespondinOriginalIndex(i);
				}
			}
		}

		if (NcaMappingDispGui.createMappingInstance().isVolumeToAvailable == true) {
			NcaMappingDispGui.createMappingInstance().previousVolumeColumnName = NcaMappingDispGui
					.createMappingInstance().volumeVariableTextField.getText();
			String moving = NcaMappingDispGui.createMappingInstance().volumeVariableTextField
					.getText();
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.add(
							NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
									.size(), moving);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().add(moving);
			NcaMappingDispGui.createMappingInstance().volumeVariableTextField
					.setText(null);
		}

	}

	void availToEndTimeButtonAction() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (NcaMappingDispGui.createMappingInstance().isAvailableToEndTime == true) {

			NcaMappingDispGui.createMappingInstance().previousEndTime =appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getEndTimeColumnName();
			String moving = (String) NcaMappingDispGui.createMappingInstance().availableColumnsList
					.getSelectedValue();
			
			int selectedIndex = NcaMappingDispGui.createMappingInstance().availableColumnsList
					.getSelectedIndex();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedIndex);
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.remove(selectedIndex);

			// adding the previous end time to the available variables
			// if it is present.
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getEndTimeColumnName() != "") {
				NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
						.add(
								NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
										.size(),
								NcaMappingDispGui.createMappingInstance().endTimeVariableTextField
										.getText());
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getNcaInfo()
						.getProcessingInputs()
						.getMappingDataObj()
						.getAvailableColumnsVector()
						.add(
								NcaMappingDispGui.createMappingInstance().endTimeVariableTextField
										.getText());

			}
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.setEndTimeColumnName(moving);
			NcaMappingDispGui.createMappingInstance().endTimeVariableTextField
					.setText(moving);
			for (int i = 0; i <appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(i).getColumnName())) {
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj()
							.setEndTimeColumnCorrespondinOriginalIndex(i);
				}
			}
		}

		if (NcaMappingDispGui.createMappingInstance().isEndTimeToAvailable == true) {
			String moving = NcaMappingDispGui.createMappingInstance().endTimeVariableTextField
					.getText();
			NcaMappingDispGui.createMappingInstance().previousEndTime = moving;
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.add(
							NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
									.size(), moving);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().add(moving);
			NcaMappingDispGui.createMappingInstance().endTimeVariableTextField
					.setText(null);

		}

	}

	void subJectTextFieldAction() throws RowsExceededException, WriteException,
			BiffException, IOException {

		// TODO Auto

		NcaMappingDispGui.createMappingInstance().setAsSubjectButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toLeft);
		NcaMappingDispGui.createMappingInstance().isSubjectToAvailable = true;
		NcaMappingDispGui.createMappingInstance().isAvailableToSubject = false;

	}

	void volumeTextFieldAction() throws RowsExceededException, WriteException,
			BiffException, IOException {

		// TODO Auto

		NcaMappingDispGui.createMappingInstance().moveToVolumeButton
				.setIcon(NcaMappingDispGui.createMappingInstance().toLeft);
		NcaMappingDispGui.createMappingInstance().isVolumeToAvailable = true;
		NcaMappingDispGui.createMappingInstance().isAvailableToVolume = false;

	}

}
