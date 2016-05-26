package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import Common.JinternalFrameFunctions;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NpsTermialPhaseTableCreation {

	MyJTable terminalPhaseTable;
	JInternalFrame terminalPhaseIFForNPS;
	AbstractTableModel tableModel;
	public static NpsTermialPhaseTableCreation NPS_TERMINAl_PHASE_GUI = null;

	public static NpsTermialPhaseTableCreation createNpsTerminalPhaseGuiInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NPS_TERMINAl_PHASE_GUI == null) {
			NPS_TERMINAl_PHASE_GUI = new NpsTermialPhaseTableCreation();
		}
		return NPS_TERMINAl_PHASE_GUI;
	}

	public NpsTermialPhaseTableCreation() {
		createTerminalPhaseInternalFrame();
	}

	int numberOfRow;
	int numberOfCol;
	
	
	final class TableForTerminalPhaseEventHandler implements TableModelListener {
	

		public void tableChanged(TableModelEvent arg0) {

			ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst().copyProcessingInput();
			int rowChanged = terminalPhaseTable.getSelectedRow();
			int colChanged = terminalPhaseTable.getSelectedColumn();

			if(rowChanged >=0 && colChanged >=0)
			for (int i = 0; i < terminalPhaseTable.getRowCount(); i++) {
				for (int j = 0; j < terminalPhaseTable.getColumnCount(); j++) {
					procInputInst.getModleInputForNPSObj().setTerminatPhaseValueAt(rowChanged, colChanged, (String) terminalPhaseTable.getValueAt(rowChanged, colChanged));
				}
				
			}

			NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);
			
		}
	}

	
	
	private void createTerminalPhaseInternalFrame() {
		terminalPhaseIFForNPS = new JInternalFrame("Dosing", false, false, false,
				false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(terminalPhaseIFForNPS);
		GridBagLayout gridBagLayout = new GridBagLayout();
		terminalPhaseIFForNPS.setLayout(gridBagLayout);
		terminalPhaseIFForNPS
				.setLocation(
						NPSSetupAvailComp.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ NPSSetupAvailComp
										.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
										NPSSetupAvailComp.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());//
		terminalPhaseIFForNPS
				.setSize(
						NPSMainPage.createNPSMainPageInst().npsMainInternalFrame
								.getWidth()
								- NPSSetupAvailComp
										.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
										NPSSetupAvailComp.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getHeight());
		terminalPhaseIFForNPS.setVisible(true);
		terminalPhaseIFForNPS.setBorder(DDViewLayer.createViewLayerInstance().b);
		NPSTabs.createNPSTabsInst().setupTabDesktopPane
		.add(terminalPhaseIFForNPS);
	}
	
	public void createInternalGUIForTerminatPhase() throws RowsExceededException, WriteException, BiffException, IOException {

		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();

		terminalPhaseIFForNPS.getContentPane().removeAll();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if (procInputInst.getMappingDataObj().getSortVariablesListVector()
				.size() > 0) {

			
			multipleLevelSorting m = multipleLevelSorting
					.createMultipleSortInst();

			//m.setAppInfo(appInfo);
			m.main(null);
			DetermineNumberOfSubject determineNoOfSubInst = DetermineNumberOfSubject
					.createDetNoSubInst();

			determineNoOfSubInst.determineNumberOfSubject(m.dataSorted);
		}

		numberOfRow = DetermineNumberOfSubject.createDetNoSubInst().no_subject;
		numberOfCol = procInputInst.getMappingDataObj().getSortVariablesListVector().size() +2;
		int[] colIndices = new int[procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size()];

		for (int i = 0; i < colIndices.length; i++) {
			colIndices[i] = i;
		}

		terminalPhaseTable = new MyJTable(numberOfRow,
				numberOfCol, colIndices);

		for (int i = 0; i < terminalPhaseTable.getRowCount(); i++) {

			
			
			for (int j = 0; j < terminalPhaseTable.getColumnCount(); j++) {

				if(j < numberOfCol-2)
				{
					terminalPhaseTable.setValueAt(procInputInst.getProfileInfoObj().getDataSortVariables()[i][j], i, j);
				}else
				{
					terminalPhaseTable.setValueAt("", i, j);
				}
				

			}

		}
		new ReorderableJList((JTable) terminalPhaseTable);
		terminalPhaseTable.getTableHeader().setReorderingAllowed(false);
		// caMainScreen.tableForDosing.setFont(caMainScreen.componentOptionseFont);

		settingColumnsNameToDosingTable();

		terminalPhaseTable.setBackground(Color.white);

		String[][] data = new String[terminalPhaseTable.getRowCount()][terminalPhaseTable
				.getColumnCount()];

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = (String) terminalPhaseTable.getValueAt(i, j);
			}

		}

		procInputInst.getModleInputForNPSObj().setTerminatPhaseData(data);

		NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);

		terminalPhaseTable.getModel().addTableModelListener(
				new TableForTerminalPhaseEventHandler());

		for (int i = 0; i < terminalPhaseTable.getRowCount(); i++)
			for (int j = 0; j < terminalPhaseTable.getColumnCount(); j++)
				terminalPhaseTable.isCellEditable(i, j);

		tableModel = (AbstractTableModel) terminalPhaseTable.getModel();
		terminalPhaseTable.setFillsViewportHeight(true);

		terminalPhaseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		terminalPhaseTable.setVisible(true);
		
		GridBagConstraints tableForDosingTableScrollPaneCon = new GridBagConstraints();
		tableForDosingTableScrollPaneCon = new GridBagConstraints();
		tableForDosingTableScrollPaneCon.gridx = 0;
		tableForDosingTableScrollPaneCon.gridy = 0;
		tableForDosingTableScrollPaneCon.weighty = 0.5;
		tableForDosingTableScrollPaneCon.weightx = 0.5;
		tableForDosingTableScrollPaneCon.gridheight = 5;
		tableForDosingTableScrollPaneCon.gridwidth = 4;
		tableForDosingTableScrollPaneCon.fill = GridBagConstraints.BOTH;

		JScrollPane tableForDosingScrollPane = new JScrollPane(terminalPhaseTable,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tableForDosingScrollPane.setBackground(Color.white);
		tableForDosingScrollPane.setVisible(true);

		terminalPhaseTable.validate();
		terminalPhaseIFForNPS.add(tableForDosingScrollPane, tableForDosingTableScrollPaneCon);
		terminalPhaseIFForNPS.validate();

		terminalPhaseIFForNPS.setSize(terminalPhaseIFForNPS.getSize());
		
		
		

	}

	void settingColumnsNameToDosingTable() {
		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();
		int noSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		for (int i = 0; i < noSortVar; i++) {
			TableColumnModel cmForFirstSortVariable = terminalPhaseTable
					.getColumnModel();
			TableColumn tcForFirstSortVariable = cmForFirstSortVariable
					.getColumn(i);
			tcForFirstSortVariable.setHeaderValue(procInputInst
					.getMappingDataObj().getSortVariablesListVector().get(i));

		}

		TableColumnModel cmForFirstSortVariable = terminalPhaseTable
		.getColumnModel();
		TableColumn tcForFirstSortVariable = cmForFirstSortVariable
		.getColumn(noSortVar);
		tcForFirstSortVariable.setHeaderValue("Start Time");
		
		cmForFirstSortVariable = terminalPhaseTable
		.getColumnModel();
		tcForFirstSortVariable = cmForFirstSortVariable
		.getColumn(noSortVar+1);
		tcForFirstSortVariable.setHeaderValue("End Time");
	}

}
