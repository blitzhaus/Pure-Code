package view;

import java.io.IOException;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.ThousandsSeperator;

public class CaOutoutTreeHandler {

	String tableName;
	DefaultMutableTreeNode node;
	String nodeName;
	String analysisType;
	
	
	public static CaOutoutTreeHandler INST = null;
	public static CaOutoutTreeHandler createOutputTreeHandler(){
		if(INST == null){
			INST = new CaOutoutTreeHandler(); 
		}
		return INST;
	}
	void includeAsInput() throws RowsExceededException, WriteException, BiffException, IOException{
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String plotName = CaResultAvailCompDisplayer.createPdResAvailCompInst().availableOutputsTree
				.getSelectionPath().getLastPathComponent().toString();
		String[] pathSplits =CaResultAvailCompDisplayer.createPdResAvailCompInst().availableOutputsTree
				.getSelectionPath().toString().split(",");
		String[] plotNameSplits = plotName.split(" ");
		String[][] sheeetData = null;
		if (CaResultAvailCompDisplayer.createPdResAvailCompInst().availableOutputsTree
				.getSelectionPath().getParentPath().toString().contains(
						"Sheets")) {
			int chosenOption = JOptionPane.showConfirmDialog(
					CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame,
					"Use as a input for a different analysis", "Conform",
					JOptionPane.YES_OPTION, 0);
			if (chosenOption == 0) {
				
				// get the selected node's corresponding table data
				sheeetData = getCorespondingSheetData(sheeetData, pathSplits);

				// add the data structure to array list of data structures in
				// ApplicationInfo
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().add(new WorkSheetsInfo());
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getDataStructuresArrayList().add(sheeetData);
				String sheetName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getSheetName();
				analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getAnalysisType();
				// set sheetName
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL().size() - 1)
						.setSheetName(
								sheetName + "_" + analysisType + "_"
										+ tableName);

				// set selected row as 1
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL().size() - 1)
						.setStartRow(1);

				// add a node to project explorer tree
				updateProjectExplorer();

				// set default column properties
				setDefaultColumnProperties();
			}
		} else {

		}
	}
	
	private void setDefaultColumnProperties() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL().size() - 1)
				.getColumnPropertiesArrayList().add(new ColumnProperties());
	}

	private void updateProjectExplorer() {
		boolean hasNode = checkIfNodePresent();
		performNodeCreationInPE(hasNode);

	}
	
	private boolean checkIfNodePresent() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String sheetName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getSheetName();
		Enumeration en = DDViewLayer.createPEInstance().workSpace
				.breadthFirstEnumeration();
		boolean hasNode = false;
		// iterate through the enumeration
		while (en.hasMoreElements()) {
			// get the node
			node = (DefaultMutableTreeNode) en.nextElement();

			// match the string with the user-object of the node
			nodeName = sheetName + "_" + analysisType + "_" + tableName;
			if ((node.getUserObject().toString()).equals(nodeName)) {
				hasNode = true;
				break;
			} else {
			}
		}

		return hasNode;
	}

	private void performNodeCreationInPE(boolean hasNode) {

		// the analysis node is present we just have to add another child to it.
		DefaultMutableTreeNode analysisNode = null;
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (hasNode == true) {
		} else if (hasNode == false) {
			String workBookSelected = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkBookName();
			Enumeration en = DDViewLayer.createPEInstance().workSpace.breadthFirstEnumeration();
			analysisNode = getAnalysisNode(workBookSelected, analysisNode, en);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
					nodeName);
			analysisNode.add(newNode);
			model.reload();
		}
	}
	
	private DefaultMutableTreeNode getAnalysisNode(String workBookSelected,
			DefaultMutableTreeNode analysisNode, Enumeration en) {
		while (en.hasMoreElements()) {
			DefaultMutableTreeNode next = (DefaultMutableTreeNode) en
					.nextElement();
			if (next.toString().equals("Data")) {
				for (int i = 0; i < next.getChildCount(); i++) {
					String child = next.getChildAt(i).toString();
					if (workBookSelected.equals(child)) {
						analysisNode = (DefaultMutableTreeNode) next
								.getChildAt(i);
						return analysisNode;
					}
				}
			}
		}
		return analysisNode;
	}
	
	private String[][] copySelectedSheetData(JTable table) {
		String[][] data = new String[table.getRowCount() + 1][table
				.getColumnCount()];
		TableColumnModel tm = table.getColumnModel();

		// include header as 1st row in data structure
		for (int j = 0; j < data[0].length; j++) {
			TableColumn tc = tm.getColumn(j);
			data[0][j] = tc.getHeaderValue().toString();
		}

		// copy the table data into the remaining rows of data.
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				try {
					data[i][j] = ThousandsSeperator.createThouSepInst()
							.removeComma(table.getValueAt(i - 1, j).toString());
				} catch (NullPointerException ne) {
					data[i][j] = "";
				}
			}
		}
		return data;
	}
	
	private String[][] getCorespondingSheetData(String[][] sheeetData,
			String[] pathSplits) throws RowsExceededException, WriteException, BiffException, IOException {
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Initial_Parameter]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.initialParameterDisplayTable);
					tableName = "Initial_Parameter";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Minimization_Process]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.minimizationProcessDisplayTable);
					tableName = "Minimization_Process";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Final_Parameters]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.finalparametersVerticalDisplayTable);
					tableName = "Final_Parameters";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Non_Transposed_Final_Parameters]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.finalParametersHorizontalDisplayTable);
					tableName = "Non_Transposed_Final_Parameters";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Dosing]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.dosingDisplayTable);
					tableName = "Dosing";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Correlation_Matrix]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.correlationMatrixDisplayTable);
					tableName = "Correlation_Matrix";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Eigen_Values]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.eigenValuesDisplayTable);
					tableName = "Eigen_Values";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Condition_Number]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.conditionNumberDisplayTable);
					tableName = "Condition_Number";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Predicted_Value]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.predictedValueDisplayTable);
					tableName = "Predicted_Value";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Variance_CoVariance_Matrix]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.varienceCovarienceMatrixDisplayTable);
					tableName = "Variance_CoVariance_Matrix";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Summary_Table]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.summaryTableDisplayTable);
					tableName = "Summary_Table";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Diagnostics]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.diagnosticsDisplayTable);
					tableName = "Diagnostics";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Partial_Derivative]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.partialDerivativeDisplayTable);
					tableName = "Partial_Derivative";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Secondary_Parameter]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.secondaryParameterDisplayTable);
					tableName = "Secondary_Parameter";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Non_Transposed_Secondary_Parameter]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.nonTransposedSecondaryParameterDisplayTable);
					tableName = "Non_Transposed_Secondary_Parameter";
			}
		if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" History]")) {
					sheeetData = copySelectedSheetData(CaResultDispCompCreator.createCaResDispCompInst()
							.historyDisplayTable);
					tableName = "History";
			}
		
		return sheeetData;
			}

	
}
