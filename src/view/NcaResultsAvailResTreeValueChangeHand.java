package view;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Common.ThousandsSeperator;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaResultsAvailResTreeValueChangeHand {

	public static NcaResultsAvailResTreeValueChangeHand NCA_RES_TREE = null;

	public static NcaResultsAvailResTreeValueChangeHand createNcaResTreeInst() {
		if (NCA_RES_TREE == null) {
			NCA_RES_TREE = new NcaResultsAvailResTreeValueChangeHand();
		}
		return NCA_RES_TREE;
	}

	String tableName;
	DefaultMutableTreeNode node;
	String nodeName;
	String analysisType;

	void availableResults() throws RowsExceededException, WriteException,
			BiffException, IOException {

		
		if(NcaResultsAvailableComp.createNcaResAvailCompInst().isModelReload == true){
			
		} else{
			String plotName = NcaResultsAvailableComp.createNcaResAvailCompInst().availableOutputsTree
			.getSelectionPath().getLastPathComponent().toString();

	
	if(plotName != null){
		String[] pathSplits = NcaResultsAvailableComp
		.createNcaResAvailCompInst().availableOutputsTree
		.getSelectionPath().toString().split(",");

String[] plotNameSplits = plotName.split(" ");

if ((pathSplits.length >= 2)//08026554014
		&& (pathSplits[pathSplits.length - 2]
				.equalsIgnoreCase(" plots"))) {

	DDViewLayer.createmenuBarInstance().exportImage.setEnabled(true);
	DDViewLayer.createmenuBarInstance().exportTextoutput
			.setEnabled(false);
	DDViewLayer.createmenuBarInstance().exportTable.setEnabled(false);
	int plotNumber = Integer
			.parseInt(plotNameSplits[plotNameSplits.length - 1]);
	
	
	
	createLinearPlotAndShow(plotNumber, "log");

}

if (pathSplits[pathSplits.length - 1]
		.equalsIgnoreCase(" Final Parameters]")) {
	DDViewLayer.createmenuBarInstance().exportTextoutput
			.setEnabled(false);
	DDViewLayer.createmenuBarInstance().exportImage.setEnabled(false);
	DDViewLayer.createmenuBarInstance().exportTable.setEnabled(true);
	System.out.println("Viewing final parameters");
	NcaResultDispComp.createNcaResDispCompInst().finalParametersinternalFrame
			.moveToFront();
}

if (pathSplits[pathSplits.length - 1]
		.equals(" Transposed Final Parameters]")) {
	DDViewLayer.createmenuBarInstance().exportTextoutput
			.setEnabled(false);
	DDViewLayer.createmenuBarInstance().exportImage.setEnabled(false);
	DDViewLayer.createmenuBarInstance().exportTable.setEnabled(true);
	NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayInternalFrame
			.moveToFront();
}

if (pathSplits[pathSplits.length - 1].equals(" Dosing Table]")) {
	NcaResultDispComp.createNcaResDispCompInst().dosingTableIF
			.moveToFront();
	DDViewLayer.createmenuBarInstance().exportTextoutput
			.setEnabled(false);
	DDViewLayer.createmenuBarInstance().exportImage.setEnabled(false);
	DDViewLayer.createmenuBarInstance().exportTable.setEnabled(true);
	// dosingTableIF.moveToFront();
}

if (pathSplits[pathSplits.length - 1].equals(" Sub Areas Table]")) {
	DDViewLayer.createmenuBarInstance().exportTextoutput
			.setEnabled(false);
	DDViewLayer.createmenuBarInstance().exportImage.setEnabled(false);
	DDViewLayer.createmenuBarInstance().exportTable.setEnabled(true);
	NcaResultDispComp.createNcaResDispCompInst().SubAreasIF
			.moveToFront();
}

if (pathSplits[pathSplits.length - 1]
		.equalsIgnoreCase(" Summary Table]")) {
	DDViewLayer.createmenuBarInstance().exportTextoutput
			.setEnabled(false);
	DDViewLayer.createmenuBarInstance().exportImage.setEnabled(false);
	DDViewLayer.createmenuBarInstance().exportTable.setEnabled(true);
	NcaResultDispComp.createNcaResDispCompInst().summaryTableIF
			.moveToFront();
}

if (pathSplits[pathSplits.length - 1]
		.equalsIgnoreCase(" Complete output]")) {
	DDViewLayer.createmenuBarInstance().exportTextoutput.setEnabled(true);
	DDViewLayer.createmenuBarInstance().exportImage.setEnabled(false);
	DDViewLayer.createmenuBarInstance().exportTable.setEnabled(false);
	NcaResultDispComp.createNcaResDispCompInst().textCompleteOutputInternalFrame
			.moveToFront();
}

	}
		}

	}

	private void createLinearPlotAndShow(int plotNumber, String axis)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		// first remove all the contents of the plots internal frame.
		NcaResultDispComp.createNcaResDispCompInst().plotsIF.getContentPane()
				.removeAll();

		// create the chart panel and add it to the plot internal frame
		JFreeChart chart = null;
		if (axis.equals("linear")) {
			chart = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLinearPlots()
					.get(plotNumber - 1);
		} else if (axis.equals("log")) {
			chart = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLogplots().get(
							plotNumber - 1);
		}

		ChartPanel chartPlnel = new ChartPanel(chart);
		JScrollPane chartSP = new JScrollPane(chartPlnel);
		chartSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		chartSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);;
		chartPlnel.setVisible(true);
		chartPlnel.setSize(NcaResultDispComp.createNcaResDispCompInst().plotsIF
				.getWidth(),
				NcaResultDispComp.createNcaResDispCompInst().plotsIF
						.getHeight());
		NcaResultDispComp.createNcaResDispCompInst().plotsIF.getContentPane()
				.add(chartSP);
		NcaResultDispComp.createNcaResDispCompInst().plotsIF.moveToFront();
		NcaResultDispComp.createNcaResDispCompInst().plotsIF.validate();
	}

	void includeAsInput() throws RowsExceededException, HeadlessException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String plotName = NcaResultsAvailableComp.createNcaResAvailCompInst().availableOutputsTree
				.getSelectionPath().getLastPathComponent().toString();
		String[] pathSplits = NcaResultsAvailableComp
				.createNcaResAvailCompInst().availableOutputsTree
				.getSelectionPath().toString().split(",");

		String[] plotNameSplits = plotName.split(" ");
		String[][] sheeetData = null;

		if (NcaResultsAvailableComp.createNcaResAvailCompInst().availableOutputsTree
				.getSelectionPath().getParentPath().toString().contains(
						"Sheets")) {
			int chosenOption = JOptionPane.showConfirmDialog(NcaResultDispComp
					.createNcaResDispCompInst().displayResultsInternalFrame,
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

	private String[][] mergeSheetDataFixedData(String[][] sheeetData,
			String[][] fixedData) {
		
		String[][] data = new String[sheeetData.length][(sheeetData[0].length + fixedData[0].length)];
		for(int i=0;i<fixedData.length;i++){
			for(int j=0;j<fixedData[i].length;j++){
				data[i][j] = fixedData[i][j];
			}
		}
		
		for(int i=0;i<sheeetData.length;i++){
			for(int j=0;j<sheeetData[0].length;j++){
				data[i][fixedData[0].length+j] = sheeetData[i][j];
			}
		
		}
		
		return data;
		
	}
	
	private String[][] getCorespondingSheetData(String[][] sheeetData,
			String[] pathSplits) throws RowsExceededException, WriteException,
			BiffException, IOException {
		if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Final Parameters]")) {
			sheeetData = copySelectedSheetData(NcaResultDispComp
					.createNcaResDispCompInst().finalparametersVerticalDisplayTable);
			tableName = "Final Parameters";
		}

		if (pathSplits[pathSplits.length - 1]
				.equals(" Transposed Final Parameters]")) {
			
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			
			JTable fixedTable = FixedColumnTable.createFixedColumnTableInst().getFixedTable();
			String[][] fixedData = copySelectedSheetData(fixedTable);
			sheeetData = copySelectedSheetData(NcaResultDispComp
					.createNcaResDispCompInst().finalParametersHorizontalDisplayTable);
			tableName = "Transposed Final Parameters";
			sheeetData = mergeSheetDataFixedData(sheeetData, fixedData);
		}

		if (pathSplits[pathSplits.length - 1].equals(" Dosing Table]")) {
			sheeetData = copySelectedSheetData(NcaResultDispComp
					.createNcaResDispCompInst().resultsDosingTable);
			tableName = "Dosing";
		}

		if (pathSplits[pathSplits.length - 1].equals(" Sub Areas Table]")) {
			sheeetData = copySelectedSheetData(NcaResultDispComp
					.createNcaResDispCompInst().resultsSubAreasTable);
			tableName = "Sub areas";
		}

		if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Summary Table]")) {
			sheeetData = copySelectedSheetData(NcaResultDispComp
					.createNcaResDispCompInst().resultsSummaryTable);
			tableName = "Summary";
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
