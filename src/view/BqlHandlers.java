package view;

import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Common.ThousandsSeperator;

public class BqlHandlers {

	public static BqlHandlers BQL_HANDLER = null;

	public static BqlHandlers createBqlHandlerInst() {
		if (BQL_HANDLER == null) {
			BQL_HANDLER = new BqlHandlers("just object creation");
		}
		return BQL_HANDLER;
	}

	String tableName;
	DefaultMutableTreeNode node;
	String nodeName;
	String analysisType;

	public BqlHandlers(String dummy) {
		// TODO Auto-generated constructor stub
	}

	void includeAsInput() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String plotName = BQLResAvailComp.createBQLResAvailInst().resultsAvailableTree
				.getSelectionPath().getLastPathComponent().toString();
		String[] pathSplits = BQLResAvailComp.createBQLResAvailInst().resultsAvailableTree
				.getSelectionPath().toString().split(",");

		String[] plotNameSplits = plotName.split(" ");
		String[][] sheeetData = null;

		if (BQLResAvailComp.createBQLResAvailInst().resultsAvailableTree
				.getSelectionPath().getParentPath().toString().contains(
						"Output")) {
			int chosenOption = JOptionPane.showConfirmDialog(BQLResDispComp
					.createBqlResDispInst().transformedSheetIF,
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

	private void performNodeCreationInPE(boolean hasNode) {
		// the analysis node is present we just have to add another child to it.
		DefaultMutableTreeNode analysisNode = null;
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (hasNode == true) {

		} else if (hasNode == false) {

			String workBookSelected = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkBookName();

			Enumeration en = DDViewLayer.createPEInstance().workSpace
					.breadthFirstEnumeration();

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

	private String[][] getCorespondingSheetData(String[][] sheeetData,
			String[] pathSplits) {

		if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Transformed Sheet]")) {
			sheeetData = copySelectedSheetData(BqlOutputGeneration
					.createBqloutGenInst().transformedTable);
			tableName = "Transformed Sheet";
		}
		return sheeetData;

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

}
