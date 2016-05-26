package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Common.LogEntry;

import jxl.BooleanCell;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;

public class DCCoreFunctions {

	DisplayContents dispContentsInst;
	private ArrayList<Integer> nonEmptySheets;
	public static DCCoreFunctions DC_COREFUNC = null;

	public static DCCoreFunctions createDCCoreFuncInstance() {
		if (DC_COREFUNC == null) {
			DC_COREFUNC = new DCCoreFunctions();
		}
		return DC_COREFUNC;
	}

	public DCCoreFunctions() {

	}

	void setWorkBookContentsToApplicationInfo(Workbook w,
			DisplayContents dispContentsInst) {

		this.dispContentsInst = dispContentsInst;
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.setFile1Workbook(w);

		ArrayList<WorkSheetsInfo> workSheetsInfoAL = new ArrayList<WorkSheetsInfo>();

		//determine non empty sheets
		nonEmptySheets = new ArrayList<Integer>();
		determineNonEmptySheetIndices();
		
		// adding the objects to the work sheets arraylist in the
		// applicationinfo class
		for (int i = 0; i < nonEmptySheets.size(); i++) {
			WorkSheetsInfo workSheet = new WorkSheetsInfo();
			workSheetsInfoAL.add(workSheet);
		}
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.setWorkSheetObjectsAL(workSheetsInfoAL);
		
		
		createDataStructuresToStoreWorkSheets();

		// the default option is true for has headers i.e all the sheets have
		// headers
		for (int i = 0; i < nonEmptySheets.size(); i++) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(i).setHasHeader(true);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(i).setStartRow(1);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(i).setMissingValue(0);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(i).setSheetName(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getFile1Workbook().getSheet(nonEmptySheets.get(i)).getName());
		}

		dispContentsInst.sheetLablesListModel = new DefaultListModel();
		dispContentsInst.sheetLablesList = new JList(
				dispContentsInst.sheetLablesListModel);
		dispContentsInst.sheetLablesList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		JScrollPane sheetsSP = new JScrollPane(dispContentsInst.sheetLablesList);

		// To display all the work sheets available
		for (int i = 0; i < appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.size(); i++) {
			dispContentsInst.sheetLablesListModel.add(i, appInfo
					.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(i).getSheetName());

			if (appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getFile1Workbook()
					.getSheet(i).getRows() > dispContentsInst.maxRows) {
				dispContentsInst.maxRows = appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex()).getFile1Workbook()
						.getSheet(nonEmptySheets.get(i)).getRows();
			}

			if (appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getFile1Workbook()
					.getSheet(i).getColumns() > dispContentsInst.maxColumns) {
				dispContentsInst.maxColumns = appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex()).getFile1Workbook()
						.getSheet(nonEmptySheets.get(i)).getColumns();
			}
			dispContentsInst.sheetsFrame.getContentPane().add(sheetsSP);
		}

	}

	private boolean determineNonEmptySheetIndices() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		for(int i = 0; i < appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getFile1Workbook()
				.getNumberOfSheets(); i++) {

			Sheet sheet = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getFile1Workbook().getSheet(i);
			boolean isEmpty = determineIsSheetEmpty(sheet);
			
			if(isEmpty == true){
				
			} else{
				nonEmptySheets.add(i);
			}
		}
		return true;
	}

	

	private boolean determineIsSheetEmpty(Sheet sheet) {
		for(int j=0;j<sheet.getRows();j++){
			for(int k=0;k<sheet.getColumns();k++){
				if(sheet.getCell(j, k).getContents().equals("")){
					
				} else {
					
					return false;
				}
			}
		}
		
		return true;
	}

	public void createDataStructuresToStoreWorkSheets() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.setDataStructuresArrayList(new ArrayList<String[][]>());
		for (int i = 0; i < appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.size(); i++) {
			String[][] data = new String[appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getFile1Workbook()
					.getSheet(nonEmptySheets.get(i)).getRows()][appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getFile1Workbook()
					.getSheet(nonEmptySheets.get(i)).getColumns()];
			Sheet workSheet = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getFile1Workbook()
					.getSheet(nonEmptySheets.get(i));
			for (int rowIndex = 0; rowIndex < workSheet.getRows(); rowIndex++) {
				for (int columnIndex = 0; columnIndex < workSheet.getColumns(); columnIndex++) {
					Cell c = workSheet.getCell(columnIndex, rowIndex);
					CellType type = c.getType();
					if (type == CellType.NUMBER) {
						NumberCell nc = (NumberCell) c;
						data[rowIndex][columnIndex] = nc.getValue() + "";
					} else if (type == CellType.DATE) {
						DateCell dc = (DateCell) c;
						data[rowIndex][columnIndex] = dc.getDate() + "";
					} else if (type == CellType.BOOLEAN) {
						BooleanCell bc = (BooleanCell) c;
						data[rowIndex][columnIndex] = bc.getValue() + "";
					} else {
						String contents = c.getContents();
						
						data[rowIndex][columnIndex] = contents;
					}

				}

			}

			data = deleteEmptyRowsColumns(data);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.addElementToDS(data);
		}
	}

	private String[][] deleteEmptyRowsColumns(String[][] data) {

		try {
			HashSet<Integer> emptyRowIndices = new HashSet<Integer>();
			HashSet<Integer> emptyColumnIndices = new HashSet<Integer>();

			emptyRowIndices = checkEmptyRows(emptyRowIndices, data);
			emptyColumnIndices = checkEmptyColumns(emptyColumnIndices, data);

			if ((emptyRowIndices.size() > 0) || (emptyColumnIndices.size() > 0)) {
				JOptionPane
						.showMessageDialog(
								ImportFile.createDispContentsInstance().dataFrame,
								"Please note that the empty rows(columns)in the input sheets will be deleted",
								"Conform", JOptionPane.YES_OPTION);

				String[][] tempData = new String[(data.length - emptyRowIndices
						.size())][data[0].length];
				tempData = removeEmptyRows(tempData, emptyRowIndices, data);
				String[][] removeColumnsData = new String[tempData.length][tempData[0].length];
				removeColumnsData = tempData;

				tempData = new String[removeColumnsData.length][removeColumnsData[0].length
						- emptyColumnIndices.size()];
				tempData = removeEmptyColumns(tempData, emptyColumnIndices,
						removeColumnsData);
				return tempData;

			} else {
				return data;
			}

		} catch (Exception e) {

		}

		return data;

	}

	private String[][] removeEmptyColumns(String[][] tempData,
			HashSet<Integer> emptyColumnIndices, String[][] data) {
		try {
			for (int i = 0; i < tempData.length; i++) {

				for (int j = 0; j < tempData[0].length; j++) {
					if (emptyColumnIndices.contains(j)) {

					} else {
						tempData[i][j] = data[i][j];
					}
				}
			}

		} catch (Exception e) {

		}

		return tempData;
	}

	private HashSet<Integer> checkEmptyColumns(
			HashSet<Integer> emptyColumnIndices, String[][] data) {

		try {
			for (int i = 0; i < data[0].length; i++) {
				boolean emptyColumn = true;
				for (int j = 0; j < data.length; j++) {
					if (data[j][i].equals("")) {

					} else {
						emptyColumn = false;
					}
				}

				if (emptyColumn == true) {
					emptyColumnIndices.add(i);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			emptyColumnIndices.add(2);
		}
		return emptyColumnIndices;
	}

	private String[][] removeEmptyRows(String[][] tempData,
			HashSet<Integer> emptyRowIndices, String[][] data) {
		int index = 0;
		for (int i = 0; i < data.length; i++) {
			if (emptyRowIndices.contains(i)) {

			} else {
				tempData[index++] = data[i];
			}
		}
		return tempData;
	}

	private HashSet<Integer> checkEmptyRows(HashSet<Integer> emptyRowIndices,
			String[][] data) {
		for (int i = 0; i < data.length; i++) {
			boolean emptyRow = true;
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j].equals("")) {

				} else {
					emptyRow = false;
				}
			}

			if (emptyRow == true) {
				emptyRowIndices.add(i);
			}

		}
		return emptyRowIndices;
	}

}
