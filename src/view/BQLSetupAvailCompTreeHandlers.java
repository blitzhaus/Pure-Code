package view;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class BQLSetupAvailCompTreeHandlers {

	public static BQLSetupAvailCompTreeHandlers BQL_SETUP_AVAIL_TREE_HANDLER = null;

	public static BQLSetupAvailCompTreeHandlers createBQLSetupAvailhandlerInst() {
		if (BQL_SETUP_AVAIL_TREE_HANDLER == null) {
			BQL_SETUP_AVAIL_TREE_HANDLER = new BQLSetupAvailCompTreeHandlers(
					"just object creation");
		}
		return BQL_SETUP_AVAIL_TREE_HANDLER;
	}

	HashSet<String> distincStatusCode;

	public BQLSetupAvailCompTreeHandlers(String dummy) {

	}

	void availCompHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String plotName = BQLSetupAvailComp.createBqlSetAvailInst().availableComponentsTree
				.getSelectionPath().getLastPathComponent().toString();

		String[] pathSplits = BQLSetupAvailComp.createBqlSetAvailInst().availableComponentsTree
				.getSelectionPath().toString().split(",");

		String[] plotNameSplits = plotName.split(" ");
		if (BQLSetupAvailComp.createBqlSetAvailInst().availableComponentsTree
				.getMinSelectionRow() == 0) {
			PreviewSelectedSheet.createpreviewShtInstance().showPreview();
		} else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Mapping]")) {
			BQLSetupDispComp.createBQLSetupDispInst().bqlMappingIF
					.moveToFront();
		} else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" BQL Rules]")) {
			String s = mappingChangeDetection();
			
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.getIfMappingScreenIsChanged() == true) {
				boolean isValidInput = checkForValidInput();

				if (isValidInput == true) {
					JOptionPane.showMessageDialog(BQLSetupDispComp
							.createBQLSetupDispInst().bqlMappingIF, s,
							"Conform", JOptionPane.YES_OPTION);
					removePreviousRows();
					createRows();

					// determine number of profiles and enter fill the profile
					// object in appInfo
					// under BQL object
					determineprofiles();

					// just print the sorted data
					// printSortedData();
					BQLSetupDispComp.createBQLSetupDispInst().bqlRulesIF
							.moveToFront();

				} 
				
				else {
					JOptionPane.showMessageDialog(BQLSetupDispComp
							.createBQLSetupDispInst().bqlMappingIF,
							"Please ensure Mapping of columns is correct and complete",
							"Conform", JOptionPane.YES_OPTION);

					BQLSetupAvailComp.createBqlSetAvailInst().availableComponentsTree
							.setSelectionRow(1);
				}
				
				

			} else{
				
				boolean isValidInput = checkForValidInput();
				if(isValidInput == true){
					BQLSetupDispComp.createBQLSetupDispInst().bqlRulesIF
					.moveToFront();
				} else {
					JOptionPane.showMessageDialog(BQLSetupDispComp
							.createBQLSetupDispInst().bqlMappingIF,
							"Please ensure Mapping of columns is correct and complete",
							"Conform", JOptionPane.YES_OPTION);

					BQLSetupAvailComp.createBqlSetAvailInst().availableComponentsTree
							.setSelectionRow(1);
				}
				
				

			}

		}
	}

	private boolean checkForValidInput() {

		boolean checkTimeConcValid = checkTimeConcValid();
		boolean statusCodeValid = checkstatusCodeValid();

		if ((checkTimeConcValid == true) && (statusCodeValid == true)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean checkstatusCodeValid() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSubjectColumnName().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkTimeConcValid() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		boolean isTimeColumnEmpty = false;
		boolean isConcColumnEmpty = false;
		boolean isTimeNumeric = false;
		boolean isConcNumeric = false;

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName()
				.equals("")) {
			isTimeColumnEmpty = true;

		} else {
			// determine data type of time column
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getBqlInfo()
					.getColumnPropertiesArrayList()
					.get(
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
									.getxColumnCorrespondinOriginalIndex())
					.getDataType().equals("Numeric")) {
				isTimeNumeric = true;
			} else {

			}
		}

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName()
				.equals("")) {
			isConcColumnEmpty = true;
		} else {
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getBqlInfo()
					.getColumnPropertiesArrayList()
					.get(
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
									.getyColumnCorrespondinOriginalIndex())
					.getDataType().equals("Numeric")) {
				isConcNumeric = true;
			} else {

			}
		}

		if ((isTimeColumnEmpty == false) && (isConcColumnEmpty == false)
				&& (isTimeNumeric == true) && (isConcNumeric == true)) {
			return true;
		} else {
			JOptionPane.showMessageDialog(BQLSetupDispComp
					.createBQLSetupDispInst().bqlMappingIF,
					"Please ensure Time and Concentrations are valid",
					"Conform", JOptionPane.YES_OPTION);
			return false;
		}

	}

	private void printSortedData() {
		DataPreparationForBQL dataPrepForBql = new DataPreparationForBQL();
		String[][] data = dataPrepForBql.Dat;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				
			}
			
		}
	}

	private void determineprofiles() throws RowsExceededException,
			WriteException, BiffException, IOException {

		multipleLevelSorting m = multipleLevelSorting.createMultipleSortInst();// new
		// multipleLevelSorting(appInfo);
		m.main(null);

		DataPreparationForBQL dataPrepForBql = new DataPreparationForBQL();
		dataPrepForBql.prepareData();

		DetermineNumberOfSubject.createDetNoSubInst().determineNumberOfSubject(
				dataPrepForBql.Dat);

		DataPreparationForAllComponents.createDataPrepAllCompInst()
				.dataPreparationForAll(dataPrepForBql.Dat);

		setTheStatusCodeInAppinfo(dataPrepForBql.Dat);
		System.out.println();
	}

	private void setTheStatusCodeInAppinfo(String[][] dat) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		for (int i = 0; i < dat.length; i++) { // for each row in dat
			String key = new String("");
			for (int j = 0; j < dat[i].length; j++) {
				if (j == dat[i].length - 1) {

				} else {
					key = key + dat[i][j];
				}

			}
			String value = dat[i][dat[i].length - 1];

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getStatusCodeHM().put(key, value);

		}
	}

	private void removePreviousRows() {
		for (int i = BQLSetupDispComp.createBQLSetupDispInst().rulesTable
				.getRowCount() - 1; i >= 0; i--) {
			((DefaultTableModel) BQLSetupDispComp.createBQLSetupDispInst().rulesTable
					.getModel()).removeRow(i);
		}

	}

	private String mappingChangeDetection() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int a = 1;
		String s = new String("");
		
		if (BQLSetupDispComp.createBQLSetupDispInst().previousSize != appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size()) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.setIfMappingScreenIsChanged(true);
			s = s + "Sort variables changed";

		} else {

			for (int i = 0; i < BQLSetupDispComp.createBQLSetupDispInst().previousSize; i++) {
				for (int j = 0; j < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getBqlInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size(); j++) {
					String String1 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getBqlInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.get(i);
					String String2 = BQLSetupDispComp.createBQLSetupDispInst().previousSortVariables[i];
					if (String1.equals(String2) == true)
						a = a * 0;
					else
						a = a * 1;

				}

				if (a == 1) {
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getBqlInfo().getProcessingInputs()
							.getMappingDataObj().setIfMappingScreenIsChanged(
									true);
					s = s + "sort variables changed";
					break;

				} else {
					a = 1;
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getBqlInfo().getProcessingInputs()
							.getMappingDataObj().setIfMappingScreenIsChanged(
									false);
				}

			}

		}

		// setting the new sort variables to the previousSortVariables array
		BQLSetupDispComp.createBQLSetupDispInst().previousSortVariables = new String[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size()];
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			String String2 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i);
			BQLSetupDispComp.createBQLSetupDispInst().previousSortVariables[i] = String2;
		}

		BQLSetupDispComp.createBQLSetupDispInst().previousSize = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();
		s = checkIfXColumnChanged(s);

		BQLSetupDispComp.createBQLSetupDispInst().previousXVariable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName();

		// checking if y column is changed
		s = checkIfYColumnChanged(s);

		BQLSetupDispComp.createBQLSetupDispInst().previousYColumnName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName();

		// checking if subject column is changed

		s = checkIfStatusColumnChanged(s);
		
		return s;

	}

	private String checkIfStatusColumnChanged(String s) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (BQLSetupDispComp.createBQLSetupDispInst().previousSubjectColumnName
				.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getBqlInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSubjectColumnName())) {

		} else {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.setIfMappingScreenIsChanged(true);
			s = s + "	: Status code column changed";
			

		}
		BQLSetupDispComp.createBQLSetupDispInst().previousSubjectColumnName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSubjectColumnName();

		return s;

	}

	private String checkIfYColumnChanged(String s) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (BQLSetupDispComp.createBQLSetupDispInst().previousYColumnName
				.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getBqlInfo()
						.getProcessingInputs().getMappingDataObj()
						.getYcolumnName())) {

		} else {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.setIfMappingScreenIsChanged(true);
			s = s + "	: Concentration column changed";
			
		}
		return s;
	}

	private String checkIfXColumnChanged(String s) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// checking if the x column is changed
		if (BQLSetupDispComp.createBQLSetupDispInst().previousXVariable
				.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getBqlInfo()
						.getProcessingInputs().getMappingDataObj()
						.getxColumnName())) {

		} else {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.setIfMappingScreenIsChanged(true);
			s = s + "	: Time column changed";
		}
		return s;
	}

	private void createRows() {
		determineDistinctStatusCodes();
		setInAppInfo();
		insertRows();

	}

	private void setInAppInfo() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getDosingDataObj().setBqlRulesTable(
						new String[distincStatusCode.size()][6]);

	}

	private void insertRows() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Iterator<String> it = distincStatusCode.iterator();
		for (int i = 0; i < distincStatusCode.size(); i++) {
			String[] s = new String[BQLSetupDispComp.createBQLSetupDispInst().rulesTable
					.getColumnCount()];
			for (int j = 0; j < BQLSetupDispComp.createBQLSetupDispInst().rulesTable
					.getColumnCount(); j++) {
				if (j == 0) {
					s[j] = it.next();
				} else {
					s[j] = "";
				}
			}
			int rowCount = BQLSetupDispComp.createBQLSetupDispInst().rulesTable
					.getRowCount();
			((DefaultTableModel) BQLSetupDispComp.createBQLSetupDispInst().rulesTable
					.getModel()).insertRow(rowCount, s);

			// just to invoke the table value changed listener for the distinct
			// status codes cells
			// we have to re insert the cell value so taht the value changed
			// listener is called and it reflects in appInfo

			for (int k = 0; k < BQLSetupDispComp.createBQLSetupDispInst().rulesTable
					.getColumnCount(); k++) {
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getBqlInfo()
						.getProcessingInputs().getDosingDataObj()
						.getBqlRulesTable()[rowCount][k] = s[k];
			}
		}

	}

	private void determineDistinctStatusCodes() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String[][] data = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex());
		String[] StatusCodeColData = null;
		int statusCodeColoriginalIndex = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSubjectColumnCorrespondinOriginalIndex();
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getHasHeader() == true) {

			StatusCodeColData = new String[data.length - 1];
			for (int i = 1; i < data.length; i++) {
				StatusCodeColData[i - 1] = data[i][statusCodeColoriginalIndex];
			}
		} else if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getHasHeader() == false) {
			StatusCodeColData = new String[data.length];
			for (int i = 0; i < data.length; i++) {
				StatusCodeColData[i] = data[i][statusCodeColoriginalIndex];
			}
		}

		distincStatusCode = new HashSet<String>();
		for (int i = 0; i < StatusCodeColData.length; i++) {
			if (StatusCodeColData[i] != "") {
				distincStatusCode.add(StatusCodeColData[i]);
			}

		}

	}

}
