package view;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;

import Common.DataManipulationFunctions;
import Common.JinternalFrameFunctions;
import Common.LogEntry;
import Common.ThousandsSeperator;

public class ImportedDataSheetHandlers {
	
	public static ImportedDataSheetHandlers IMPSHEET_HAND = null;
	public static ImportedDataSheetHandlers createImpSheetHandInstance(){
		if(IMPSHEET_HAND == null){
			IMPSHEET_HAND = new ImportedDataSheetHandlers();
		}
		return IMPSHEET_HAND;
	}
	
	boolean reqDecimalHandling;
	boolean optDecimalHandling;
	boolean isReflectingChangesToGUI;
	
	public void dataTypeChangeHand() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		int columnClicked = 
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
			.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL()
			.get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex())
			.getColumnNumberClicked();
		
		if (ImportedDataSheet.createImportedDataSheetInstance().dataTypeTextField
				.getText().equals("Numeric")) {

			ImportedDataSheet.createImportedDataSheetInstance().dataTypeTextField
					.setText("Text");
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							ImportedDataSheet.createImportedDataSheetInstance().sheetIndex)
					.getColumnPropertiesArrayList()
					.get(columnClicked).setDataType(
							"Text");
			LogEntry
					.createLogEntryInstance()
					.logEntry(
							"Data type of "
									+ ImportedDataSheet
											.createImportedDataSheetInstance().TableColumnModel
											.getColumn(columnClicked)
											.getHeaderValue() + "Changed to "
									+ "Text");
			
			
			
		} else {
			ImportedDataSheet.createImportedDataSheetInstance().dataTypeTextField
					.setText("Numeric");
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							ImportedDataSheet.createImportedDataSheetInstance().sheetIndex)
					.getColumnPropertiesArrayList()
					.get(columnClicked).setDataType(
							"Numeric");
			LogEntry
					.createLogEntryInstance()
					.logEntry(
							"Data type of "
									+ ImportedDataSheet
											.createImportedDataSheetInstance().TableColumnModel
											.getColumn(columnClicked)
											.getHeaderValue() + "Changed to "
									+ "Numeric");
			
			String[][] changedOriginalDS = setChnagesToOriginalDS(columnClicked);
			setChnagesToGUI(changedOriginalDS);
		}

	}
	
	private void setChnagesToGUI(String[][] data) {
		
		isReflectingChangesToGUI = true;
		
		if(isSheetWithHeader() == true){
			for(int i=0;i<ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getRowCount();i++){
				for(int j=0;j< ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getColumnCount();j++){
					if(data[i+1][j].equals("-3.14159265359")){
						ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.setValueAt("", i, j);
					} else {
						ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.setValueAt(data[i+1][j], i, j);
					}
					
				}
				
			}
			
		} else {
			

			for(int i=0;i<ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getRowCount();i++){
				for(int j=0;j< ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getColumnCount();j++){
					if(data[i][j].equals("-3.14159265359")){
						ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.setValueAt("", i, j);
					} else {
						ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.setValueAt(data[i][j], i, j);
					}
					
				}
				
			}
			
		
		}
		
		isReflectingChangesToGUI = false;
		
	}

	private String[][] setChnagesToOriginalDS(int columnClicked) {
		
		int selectedSheetIndex = getSelectedSheetIndex();
		String[][] data = getSheetData();
		if(isSheetWithHeader() == true){
			
			for(int i=1;i<data.length;i++){
				if(isElementOfColumnDataType(data[i][columnClicked]) == true){
					
				} else {
					data[i][columnClicked] = "-3.14159265359";
				}
				 
			}
		} else {

			for(int i=0;i<data.length;i++){
				if(isElementOfColumnDataType(data[i][columnClicked]) == true){
					
				} else {
					data[i][columnClicked] = "-3.14159265359";
				}
				 
			}
		
		}
		
		setIntoAppInfo(data, selectedSheetIndex);
		return data;
	}
	
	private void setIntoAppInfo(String[][] data, int selectedSheetIndex) {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo
		.getProjectInfoAL()
		.get(appInfo.getSelectedProjectIndex())
		.getWorkBooksArrayList()
		.get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).setElementInDS(data, selectedSheetIndex);
		
	}

	boolean isElementOfColumnDataType(String changedValue) {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int rowIndex = ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
		.getSelectedRow();
		int columnIndex = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
		.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL()
		.get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex())
		.getColumnNumberClicked();
		
		String columnDataType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(	appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
		.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(	appInfo.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getColumnPropertiesArrayList().get(columnIndex).getDataType();
		
		//String changedValue = ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getValueAt(rowIndex, columnIndex).toString();
		
		String changedValueDataType = ImpDataTableValueChangeHandler.createImpTableHandlerInst().getDataTypeOf(changedValue);
		
		if(changedValueDataType.equals(columnDataType)){
			return true;
		} else{
			return false;
		}
		
		
	}
	

	/*private String[][] getOriginalDS(int selectedSheetIndex) {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		return appInfo
		.getProjectInfoAL()
		.get(appInfo.getSelectedProjectIndex())
		.getWorkBooksArrayList()
		.get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getCopyOfOriginalDataStructures().get(selectedSheetIndex);
		
		
	}
*/
	private int getSelectedSheetIndex() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		return appInfo
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
		.getSelectedSheetIndex();
	}

	private boolean isSheetWithHeader() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		return appInfo
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
						.getSelectedSheetIndex()).getHasHeader();
	}

	public void renameColumn() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.setColumnNumberClicked(
						ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableList
								.getSelectedIndex());
		String newName = JOptionPane
				.showInputDialog(
						ImportedDataSheet.createImportedDataSheetInstance().columnPropertiesInternalFrame,
						"Please enter the Column Name ", "Rename",
						JOptionPane.YES_OPTION);
		
		if(newName.equals("")){
			
		} else{
			IMPSheetCoreFunctions.createIMPSheetCoreFuncInstance()
			.reflectChangesInAppicationInfo(newName);
		}

		
	}

	public void unitBuilder() {
	UnitBuilder ub = UnitBuilder.createUBInstance();
	
	if(ub.isVisible() == true){
		
	} else{
		ub.setVisible(true);
	}
	}

	public void optionalDecFunc() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ImportedDataSheet.createImportedDataSheetInstance().requiredDecimalsCombo.setEnabled(false);
		ImportedDataSheet.createImportedDataSheetInstance().requiredDecimalsCombo.setSelectedIndex(0);
		ImportedDataSheet.createImportedDataSheetInstance().optionaldecimalCombo.setEnabled(true);
		ImportedDataSheet.createImportedDataSheetInstance().optionaldecimalCombo.setEditable(false);
		String[][] data;
		
		data = new String[ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getRowCount()]
		                  [ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getColumnCount()];
		int optDecimals = 0;
		data = copyOriginalDataIntoData(data);
		int selectedColumn  = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnNumberClicked();
		applyChangedValuesToJTable(data, selectedColumn);
		applyChangesToAppInfo(data,optDecimals);
		setReqDecOfAllColsToZero();
		
	}

	private void setReqDecOfAllColsToZero() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		int numberOfCol = 	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex()).getColumnPropertiesArrayList().size();
		
		for(int i=0;i<numberOfCol;i++){

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getColumnPropertiesArrayList().get(i)
							.setRequiredDecimals("");

			
		}
		
	
	}

	public void reqDecimalsFunc() {

		ImportedDataSheet.createImportedDataSheetInstance().requiredDecimalsCombo.setEnabled(true);
		ImportedDataSheet.createImportedDataSheetInstance().requiredDecimalsCombo.setEditable(false);
		ImportedDataSheet.createImportedDataSheetInstance().optionaldecimalCombo.setEnabled(false);
		ImportedDataSheet.createImportedDataSheetInstance().optionaldecimalCombo.setSelectedIndex(0);
		
		String[][] data;
		
		data = new String[ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getRowCount()]
		                  [ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getColumnCount()];
		int optDecimals = 0;
		data = copyOriginalDataIntoData(data);
		
		applyChangesToAppInfo(data,optDecimals);
		
		applyChangedValuesToAllColumnsOfJTable(data);
		
		setoptDecOfAllColToZero();
	
	}

	private void setoptDecOfAllColToZero() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		int numberOfCol = 	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex()).getColumnPropertiesArrayList().size();
		
		for(int i=0;i<numberOfCol;i++){

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getColumnPropertiesArrayList().get(i)
							.setOptionalDecimals(0);

			
		}
		
	}

	private void applyChangedValuesToAllColumnsOfJTable(String[][] data) {
		

		
		String[][] dat = new String[data.length][data[0].length];
		
		for(int i=0;i<dat.length;i++){
			for(int j=0;j<dat[i].length;j++){
				dat[i][j] = data[i][j];
			}
		}
		
		
		for (int i = 0; i < ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
				.getRowCount(); i++) {
			for(int j=0;j< ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
			.getColumnCount();j++){

				if(dat[i+1][j].equals("-3.14159265359")){
					ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
					.setValueAt("", i, j);
				} else{
					ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
					.setValueAt(dat[i+1][j], i, j);//data[i+1][j]
				}
				
			
			
			}
		}

	
	}

	private String[][] getSheetData() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
		.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getSelectedWorkBookIndex()).getDataStructuresArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
		.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getSelectedWorkBookIndex()).getSelectedSheetIndex());
		
	}



	private void applyChangesToAppInfo(String[][] data, int optDecimals) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getHasHeader() == true) {
			
			//
			for (int i = 1; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).length; i++) {

				int columnSelected = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getColumnNumberClicked();
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getElementFromDS(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())[i][columnSelected] = data[i - 1][columnSelected];
			

			}
		} else {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).setElementInDS(
					data,
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex());
			
			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).length; i++) {

				int columnSelected = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getColumnNumberClicked();
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getElementFromDS(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())[i][columnSelected] = data[i - 1][columnSelected];
			

			}
			
		}
		
		/*//set optional decilams in appInfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnNumberClicked())
								.setOptionalDecimals(
						optDecimals);
		
		//set required decimals in appInfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL().get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex()).getColumnNumberClicked())
								.setRequiredDecimals(
						"");
*/
	}

	private void applyChangedValuesToJTable(String[][] data, int selectedColumn) {
		
		String[][] dat = new String[data.length][data[0].length];
		
		for(int i=0;i<dat.length;i++){
			for(int j=0;j<dat[i].length;j++){
				dat[i][j] = data[i][j];
			}
		}
		
		
		for (int i = 0; i < ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
				.getRowCount(); i++) {
			
			int j = selectedColumn;
			if(dat[i+1][j].equals("-3.14159265359")){
				ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
				.setValueAt("", i, j);
			} else{
				ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
				.setValueAt(dat[i+1][j], i, j);//data[i+1][j]
			}
			
		
		}

	}

	private String[][] copyOriginalDataIntoData(String[][] data) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getHasHeader() == true) {

			int rows = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getCopyOfOriginalDataStructures().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).length;
			int columns = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getCopyOfOriginalDataStructures().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())[0].length;
			data = new String[rows][columns];
			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getCopyOfOriginalDataStructures().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).length; i++) {
				for (int j = 0; j < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getCopyOfOriginalDataStructures().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())[i].length; j++) {
					data[i][j] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getCopyOfOriginalDataStructures().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())[i][j];
				}
			}

		} else {
			int rows = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getCopyOfOriginalDataStructures().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).length;
			int columns = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getCopyOfOriginalDataStructures().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())[0].length;
			data = new String[rows][columns];
			data = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getCopyOfOriginalDataStructures().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex());

		}
		return data;

	}
	
	public void implementOptionalHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String[][] data;

		data = new String[ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
				.getRowCount()][ImportedDataSheet
				.createImportedDataSheetInstance().importedDataTable
				.getColumnCount()];
		int optDecimals = 0;
		int selectedColumn  = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnNumberClicked();
		if (ImportedDataSheet.createImportedDataSheetInstance().optionaldecimalCombo
				.getSelectedIndex() == 0) {
			data = copyOriginalDataIntoData(data);
		} else {

			data = copyOriginalDataIntoData(data);
			optDecimals = ImportedDataSheet.createImportedDataSheetInstance().optionaldecimalCombo
					.getSelectedIndex();

			for (int i = 0; i < data.length; i++) {


				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(selectedColumn)
						.getDataType().equals("Numeric")) {
					

					try{
						double value = Double.parseDouble(data[i][selectedColumn]);
						if(value == -3.14159265359){
							data[i][selectedColumn] = value+"";
						} else{
							data[i][selectedColumn] = DataManipulationFunctions
							.createDataManFuncInst().truncateDouble(value,
									optDecimals)+"";
						}
						
					}catch(NumberFormatException e){
						data[i][selectedColumn] = data[i][selectedColumn];
					}
				}

			
			}
		}

		applyChangedValuesToJTable(data, selectedColumn);
		applyChangesToAppInfo(optDecimals);
		

	}
	

	private void applyChangesToAppInfo(int optDecimals) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getColumnPropertiesArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getColumnNumberClicked()).setRequiredDecimals("");
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
		.getColumnPropertiesArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
		.getColumnNumberClicked()).setOptionalDecimals(optDecimals);
		
	
		
	}

	void implementRequiredDecimals() {
		

	ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		
		String[][] data;
		
		data = new String[ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getRowCount()]
		                  [ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getColumnCount()];
		int reqDecimals;
		String s = "0.";
		int selectedColumn  = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnNumberClicked();

		if(ImportedDataSheet.createImportedDataSheetInstance().requiredDecimalsCombo.getSelectedIndex() == 0
				){
			data = copyOriginalDataIntoData(data);
		} else {
			data = getSheetData();
			reqDecimals = ImportedDataSheet.createImportedDataSheetInstance().requiredDecimalsCombo
					.getSelectedIndex();

			for (int i = 0; i < reqDecimals; i++) {
				s = s + 0;
			}
			DecimalFormat dec = new DecimalFormat(s);
			dec.setRoundingMode(RoundingMode.DOWN);
			
			for (int i = 0; i < data.length; i++) {
				
				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(selectedColumn)
						.getDataType().equals("Numeric")) {
					try{
						double value = Double.parseDouble(data[i][selectedColumn]);
						if(value == -3.14159265359){
							data[i][selectedColumn] = value+"";
						} else{
							data[i][selectedColumn] = dec.format(value);
						}
						
					}catch(NumberFormatException e){
						data[i][selectedColumn] = data[i][selectedColumn];
					}
					
				}

			
				
			}

		}
		applyChangedValuesToJTable(data, selectedColumn);
		applyReqDecChnagesToAppInfo(s);
		
		
	}

	private void applyReqDecChnagesToAppInfo(String s) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getColumnPropertiesArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getColumnNumberClicked()).setRequiredDecimals(s);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
		.getColumnPropertiesArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
		.getColumnNumberClicked()).setOptionalDecimals(0);
		
	}

	void thousandsSepHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (ImportedDataSheet.createImportedDataSheetInstance().useThousandsSeperatorCheck
				.isSelected() == true) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.setUseThousandSeperatorClicked(true);
			String[][] data = getDataAndAddSeperator();
			setItBackToTable(data);
		} else if (ImportedDataSheet.createImportedDataSheetInstance().useThousandsSeperatorCheck
				.isSelected() == false) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.setUseThousandSeperatorClicked(false);
			String[][] data = getDataAndRemoveSeperator();
			setItBackToTable(data);
		}

	}

	private String[][] getDataAndRemoveSeperator() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[][] data = new String[ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getRowCount()]
		                         [ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getColumnCount()];
		String str = "ajith";
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[i].length;j++){
				data[i][j] = ImportedDataSheet
				.createImportedDataSheetInstance().importedDataTable
				.getValueAt(i, j).toString();
				data[i][j] = ThousandsSeperator.createThouSepInst().removeComma(data[i][j]);
				System.out.println();
			}
		}
		return data;
	}

	private void setItBackToTable(String[][] data) {
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[i].length;j++){
				ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.setValueAt(data[i][j], i, j);
			}
		}
		
	}

	private String[][] getDataAndAddSeperator() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[][] data = new String[ImportedDataSheet
				.createImportedDataSheetInstance().importedDataTable
				.getRowCount()][ImportedDataSheet
				.createImportedDataSheetInstance().importedDataTable
				.getColumnCount()];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {

				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(j).getDataType()
						.equals("Numeric")) {

					data[i][j] = ThousandsSeperator
							.createThouSepInst()
							.insertCommas(
									ImportedDataSheet
											.createImportedDataSheetInstance().importedDataTable
											.getValueAt(i, j).toString());

				} else {
					data[i][j] = ImportedDataSheet
							.createImportedDataSheetInstance().importedDataTable
							.getValueAt(i, j).toString();
				}

			}
		}
		return data;
	}
}
