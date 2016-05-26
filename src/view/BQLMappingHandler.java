package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class BQLMappingHandler {

	public static BQLMappingHandler BQL_MAPPING_HANDLER = null;

	public static BQLMappingHandler createBqlMappingHandInst() {
		if (BQL_MAPPING_HANDLER == null) {
			BQL_MAPPING_HANDLER = new BQLMappingHandler("just object creation");
		}
		return BQL_MAPPING_HANDLER;
	}

	public BQLMappingHandler(String dummy) {

	}

	void handler() {

	}

	void availableColumnListSelectionEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		BQLSetupDispComp.createBQLSetupDispInst().SortAndAvailableButton
				.setIcon(BQLSetupDispComp.createBQLSetupDispInst().toLeft);
		BQLSetupDispComp.createBQLSetupDispInst().xAndAvailableButton
				.setIcon(BQLSetupDispComp.createBQLSetupDispInst().toLeft);
		BQLSetupDispComp.createBQLSetupDispInst().YAndAvailableButton
				.setIcon(BQLSetupDispComp.createBQLSetupDispInst().toLeft);

		BQLSetupDispComp.createBQLSetupDispInst().setAsStatusCode
				.setIcon(BQLSetupDispComp.createBQLSetupDispInst().toRight);
		BQLSetupDispComp.createBQLSetupDispInst().moveToCarryAlongButton
				.setIcon(BQLSetupDispComp.createBQLSetupDispInst().toRight);
		BQLSetupDispComp.createBQLSetupDispInst().isAvailableToSort = true;
		BQLSetupDispComp.createBQLSetupDispInst().isSortToAvailable = false;
		BQLSetupDispComp.createBQLSetupDispInst().isAvailableToX = true;
		BQLSetupDispComp.createBQLSetupDispInst().isXToAvailable = false;
		BQLSetupDispComp.createBQLSetupDispInst().isAvailableToY = true;
		BQLSetupDispComp.createBQLSetupDispInst().isYToAvailable = false;

		BQLSetupDispComp.createBQLSetupDispInst().isAvailableToCarryAlong = true;
		BQLSetupDispComp.createBQLSetupDispInst().isCarryAlongToAvailable = false;
		BQLSetupDispComp.createBQLSetupDispInst().isAvailToStatus = true;
		BQLSetupDispComp.createBQLSetupDispInst().isStatusToAvail = false;
	}

	void sortAndAvailableHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// storing the previous sort variables
		BQLSetupDispComp.createBQLSetupDispInst().previousSortVariables = new String[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size()];
		for (int i = 0; i < BQLSetupDispComp.createBQLSetupDispInst().previousSortVariables.length; i++) {
			BQLSetupDispComp.createBQLSetupDispInst().previousSortVariables[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i);
		}

		if (BQLSetupDispComp.createBQLSetupDispInst().isAvailableToSort == true) {

			int selectedIndex = BQLSetupDispComp.createBQLSetupDispInst().availableColumnsList
					.getSelectedIndex();
			
			String moving = (String) BQLSetupDispComp.createBQLSetupDispInst().availableColumnsList
					.getSelectedValue();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())

					.getBqlInfo()
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
									.getBqlInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size(),
							moving);
			BQLSetupDispComp.createBQLSetupDispInst().sortVariableListModel
					.add(
							BQLSetupDispComp.createBQLSetupDispInst().sortVariableListModel
									.getSize(), moving);
			BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
					.remove(selectedIndex);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedIndex);

		}

		if (BQLSetupDispComp.createBQLSetupDispInst().isSortToAvailable == true) {

			String moving = (String) BQLSetupDispComp.createBQLSetupDispInst().sortVariablesList
					.getSelectedValue();
			int selectedIndex = BQLSetupDispComp.createBQLSetupDispInst().sortVariablesList
					.getSelectedIndex();
			BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
					.add(
							BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
									.getSize(), moving);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())

					.getBqlInfo()
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

									.getBqlInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().remove(selectedIndex);
			BQLSetupDispComp.createBQLSetupDispInst().sortVariableListModel
					.remove(selectedIndex);

		}
	}

	private void storeThePreviousSort() {
		// TODO Auto-generated method stub

	}

	void sortColumnListHandler() {

		BQLSetupDispComp.createBQLSetupDispInst().SortAndAvailableButton
				.setIcon(BQLSetupDispComp.createBQLSetupDispInst().toRight);
		BQLSetupDispComp.createBQLSetupDispInst().isSortToAvailable = true;
		BQLSetupDispComp.createBQLSetupDispInst().isAvailableToSort = false;
		BQLSetupDispComp.createBQLSetupDispInst().isAvailableToY = true;
		BQLSetupDispComp.createBQLSetupDispInst().isYToAvailable = false;
		BQLSetupDispComp.createBQLSetupDispInst().isAvailableToX = true;
		BQLSetupDispComp.createBQLSetupDispInst().isXToAvailable = false;

	}

	void yAndAvailableButtonHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (BQLSetupDispComp.createBQLSetupDispInst().isAvailableToY == true) {

			availToYvarAction();
		}

		if (BQLSetupDispComp.createBQLSetupDispInst().isYToAvailable == true) {

			YvarToAvaialAction();
		}

		// default and preffered units preperation based on the model type

	}

	void yVarTextFieldHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {
		BQLSetupDispComp.createBQLSetupDispInst().YAndAvailableButton
				.setIcon(BQLSetupDispComp.createBQLSetupDispInst().toRight);
		BQLSetupDispComp.createBQLSetupDispInst().isYToAvailable = true;
		BQLSetupDispComp.createBQLSetupDispInst().isAvailableToY = false;

	}

	public void xVarTextFieldHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {
		// TODO Auto

		BQLSetupDispComp.createBQLSetupDispInst().xAndAvailableButton
				.setIcon(BQLSetupDispComp.createBQLSetupDispInst().toRight);
		BQLSetupDispComp.createBQLSetupDispInst().isXToAvailable = true;
		BQLSetupDispComp.createBQLSetupDispInst().isAvailableToX = false;

	}

	void statusCodeTextFieldHandler() {
		BQLSetupDispComp.createBQLSetupDispInst().setAsStatusCode
				.setIcon(BQLSetupDispComp.createBQLSetupDispInst().toLeft);
		BQLSetupDispComp.createBQLSetupDispInst().isStatusToAvail = true;
		BQLSetupDispComp.createBQLSetupDispInst().isAvailToStatus = false;
	}

	private void YvarToAvaialAction() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = BQLSetupDispComp.createBQLSetupDispInst().yVariableTextField
				.getText();
		BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
				.add(
						BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
								.size(), moving);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().add(moving);
		BQLSetupDispComp.createBQLSetupDispInst().yVariableTextField
				.setText("");
		BQLSetupDispComp.createBQLSetupDispInst().previousYColumnName = moving;

	}

	private void availToYvarAction() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = (String) BQLSetupDispComp.createBQLSetupDispInst().availableColumnsList
				.getSelectedValue();
		
		BQLSetupDispComp.createBQLSetupDispInst().previousYColumnName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName();

		int selectedIndex = BQLSetupDispComp.createBQLSetupDispInst().availableColumnsList
				.getSelectedIndex();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().remove(selectedIndex);
		BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
				.remove(selectedIndex);

		// if the y text field does not contain an empty string
		// then move it to the available variables
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName() != "") {
			BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
					.add(
							BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
									.size(),
							BQLSetupDispComp.createBQLSetupDispInst().yVariableTextField
									.getText());
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getBqlInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
							BQLSetupDispComp.createBQLSetupDispInst().yVariableTextField
									.getText());
			BQLSetupDispComp.createBQLSetupDispInst().previousYColumnName = BQLSetupDispComp
					.createBQLSetupDispInst().yVariableTextField.getText();
		}

		// set the y column name
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj().setYcolumnName(
						moving);

		// asign the new ycolumn name to the ytext field
		BQLSetupDispComp.createBQLSetupDispInst().yVariableTextField
				.setText(moving);

		// setting y column corresponding original index

		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
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
										.getSelectedSheetIndex()).getBqlInfo()
						.getProcessingInputs().getMappingDataObj()
						.setyColumnCorrespondinOriginalIndex(i);
			}
		}

	}

	void xAndAvailableButtonHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if ((BQLSetupDispComp.createBQLSetupDispInst().isAvailableToX == true)) {

			availToXVarAction();
		}

		if (BQLSetupDispComp.createBQLSetupDispInst().isXToAvailable) {

			xToAvailAction();
		}
	}

	private void xToAvailAction() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		BQLSetupDispComp.createBQLSetupDispInst().previousXVariable = BQLSetupDispComp
				.createBQLSetupDispInst().xVariableTextField.getText();
		;
		String moving = BQLSetupDispComp.createBQLSetupDispInst().xVariableTextField
				.getText();
		BQLSetupDispComp.createBQLSetupDispInst().xVariableTextField
				.setText(null);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getBqlInfo()
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
								.getBqlInfo().getProcessingInputs()
								.getMappingDataObj()
								.getAvailableColumnsVector().size(), moving);
		BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
				.add(
						BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
								.getSize(), moving);

	}

	private void availToXVarAction() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		BQLSetupDispComp.createBQLSetupDispInst().previousXVariable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName();
		String moving = (String) BQLSetupDispComp.createBQLSetupDispInst().availableColumnsList
				.getSelectedValue();
		int selectedindex = BQLSetupDispComp.createBQLSetupDispInst().availableColumnsList
				.getSelectedIndex();

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().remove(selectedindex);

		// add the previous time column to the available columns
		// if there is a time column already present
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName() != "") {
			BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
					.add(
							BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
									.size(),
							BQLSetupDispComp.createBQLSetupDispInst().xVariableTextField
									.getText());
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getBqlInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
							BQLSetupDispComp.createBQLSetupDispInst().xVariableTextField
									.getText());
		}

		BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
				.remove(selectedindex);
		BQLSetupDispComp.createBQLSetupDispInst().xVariableTextField
				.setText(moving);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj().setxColumnName(
						moving);

		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
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
										.getSelectedSheetIndex()).getBqlInfo()
						.getProcessingInputs().getMappingDataObj()
						.setxColumnCorrespondinOriginalIndex(i);
			}
		}
	}

	void availableToStatusCodeButtonHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (BQLSetupDispComp.createBQLSetupDispInst().isAvailToStatus == true) {

			BQLSetupDispComp.createBQLSetupDispInst().previousSubjectColumnName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSubjectColumnName();
			String moving = (String) BQLSetupDispComp.createBQLSetupDispInst().availableColumnsList
					.getSelectedValue();
			int selectedIndex = BQLSetupDispComp.createBQLSetupDispInst().availableColumnsList
					.getSelectedIndex();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedIndex);
			BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
					.remove(selectedIndex);

			// add the preious subject varialble to the available columns
			// if it is present
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSubjectColumnName() != "") {
				BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
						.add(
								BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
										.size(),
								BQLSetupDispComp.createBQLSetupDispInst().statusCodeTextField
										.getText());
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getBqlInfo()
						.getProcessingInputs()
						.getMappingDataObj()
						.getAvailableColumnsVector()
						.add(
								BQLSetupDispComp.createBQLSetupDispInst().statusCodeTextField
										.getText());
			}

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.setSubjectColumnName(moving);

			BQLSetupDispComp.createBQLSetupDispInst().statusCodeTextField
					.setText(moving);
			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
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
							.getBqlInfo().getProcessingInputs()
							.getMappingDataObj()
							.setSubjectColumnCorrespondinOriginalIndex(i);
				}
			}
		}

		if (BQLSetupDispComp.createBQLSetupDispInst().isStatusToAvail == true) {
			BQLSetupDispComp.createBQLSetupDispInst().previousSubjectColumnName = BQLSetupDispComp
					.createBQLSetupDispInst().statusCodeTextField.getText();
			String moving = BQLSetupDispComp.createBQLSetupDispInst().statusCodeTextField
					.getText();
			BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
					.add(
							BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
									.size(), moving);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().add(moving);
			BQLSetupDispComp.createBQLSetupDispInst().statusCodeTextField
					.setText(null);
		}

	}

	void carryAlongListSelectionHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		BQLSetupDispComp.createBQLSetupDispInst().isAvailableToCarryAlong = false;
		BQLSetupDispComp.createBQLSetupDispInst().moveToCarryAlongButton
				.setIcon(BQLSetupDispComp.createBQLSetupDispInst().toLeft);

	}

	void availableToCarryAlongButtonHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (BQLSetupDispComp.createBQLSetupDispInst().isAvailableToCarryAlong == true) {

			int selectedIndex = BQLSetupDispComp.createBQLSetupDispInst().availableColumnsList
					.getSelectedIndex();
			
			String moving = (String) BQLSetupDispComp.createBQLSetupDispInst().availableColumnsList
					.getSelectedValue();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getBqlInfo()
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
									.getBqlInfo().getProcessingInputs()
									.getMappingDataObj()
									.getCarryAlongVariablesListVector().size(),
							moving);
			BQLSetupDispComp.createBQLSetupDispInst().carryAlongModel.add(
					BQLSetupDispComp.createBQLSetupDispInst().carryAlongModel
							.getSize(), moving);
			BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
					.remove(selectedIndex);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedIndex);

		} else if (BQLSetupDispComp.createBQLSetupDispInst().isAvailableToCarryAlong == false) {
			String moving = (String) BQLSetupDispComp.createBQLSetupDispInst().carryAlongList
					.getSelectedValue();
			int selectedIndex = BQLSetupDispComp.createBQLSetupDispInst().carryAlongList
					.getSelectedIndex();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getBqlInfo()
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
									.getBqlInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);
			BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
					.add(
							BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
									.getSize(), moving);
			BQLSetupDispComp.createBQLSetupDispInst().carryAlongModel
					.remove(selectedIndex);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.getCarryAlongVariablesListVector().remove(selectedIndex);

		}

	}

}
