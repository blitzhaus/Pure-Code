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

public class NpsAdministeredDoseTableCreation {

	MyJTable administeredDoseForNPS;
	JInternalFrame administeredDoseIFForNPS;
	AbstractTableModel tForDosing;
	public static NpsAdministeredDoseTableCreation NPS_ADMIN_DOSE_GUI = null;

	public static NpsAdministeredDoseTableCreation createNpsAdministeredDoseGuiInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NPS_ADMIN_DOSE_GUI == null) {
			NPS_ADMIN_DOSE_GUI = new NpsAdministeredDoseTableCreation();
		}
		return NPS_ADMIN_DOSE_GUI;
	}

	public NpsAdministeredDoseTableCreation() {
		createAdminsteredDoseInternalFrame();
	}

	int numberOfRow;
	int numberOfCol;
	
	
	final class TableForAdministeredDoseEventHandler implements TableModelListener {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		public void tableChanged(TableModelEvent arg0) {

			ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst().copyProcessingInput();
			int rowChanged = administeredDoseForNPS.getSelectedRow();
			int colChanged = administeredDoseForNPS.getSelectedColumn();
			
			if(rowChanged >=0 && colChanged >=0)

			for (int i = 0; i < administeredDoseForNPS.getRowCount(); i++) {
				for (int j = 0; j < administeredDoseForNPS.getColumnCount(); j++) {
					procInputInst.getModleInputForNPSObj().setAdministeredDosingValueAt(rowChanged, colChanged, (String) administeredDoseForNPS.getValueAt(rowChanged, colChanged));
				}
				
			}


		}
	}

	
	
	private void createAdminsteredDoseInternalFrame() {
		administeredDoseIFForNPS = new JInternalFrame("Dosing", false, false, false,
				false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(administeredDoseIFForNPS);
		GridBagLayout gridBagLayout = new GridBagLayout();
		administeredDoseIFForNPS.setLayout(gridBagLayout);
		administeredDoseIFForNPS
				.setLocation(
						NPSSetupAvailComp.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ NPSSetupAvailComp
										.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
										NPSSetupAvailComp.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());//
		administeredDoseIFForNPS
				.setSize(
						NPSMainPage.createNPSMainPageInst().npsMainInternalFrame
								.getWidth()
								- NPSSetupAvailComp
										.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
										NPSSetupAvailComp.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getHeight());
		administeredDoseIFForNPS.setVisible(true);
		administeredDoseIFForNPS.setBorder(DDViewLayer.createViewLayerInstance().b);
		NPSTabs.createNPSTabsInst().setupTabDesktopPane
		.add(administeredDoseIFForNPS);
	}
	
	public void createInternalGUIForAdministeredDosing() throws RowsExceededException, WriteException, BiffException, IOException {

		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();

		administeredDoseIFForNPS.getContentPane().removeAll();
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
		numberOfCol = procInputInst.getMappingDataObj().getSortVariablesListVector().size() +1;
		int[] colIndices = new int[procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size() ];

		for (int i = 0; i < colIndices.length; i++) {
			colIndices[i] = i;
		}

		administeredDoseForNPS = new MyJTable(numberOfRow,
				numberOfCol, colIndices);

		for (int i = 0; i < administeredDoseForNPS.getRowCount(); i++) {

			
			
			for (int j = 0; j < administeredDoseForNPS.getColumnCount(); j++) {

				if(j < numberOfCol-1)
				{
					administeredDoseForNPS.setValueAt(procInputInst.getProfileInfoObj().getDataSortVariables()[i][j], i, j);
				}else
				{
					administeredDoseForNPS.setValueAt("", i, j);
				}
				

			}

		}
		new ReorderableJList((JTable) administeredDoseForNPS);
		administeredDoseForNPS.getTableHeader().setReorderingAllowed(false);
		// caMainScreen.tableForDosing.setFont(caMainScreen.componentOptionseFont);

		settingColumnsNameToDosingTable();

		administeredDoseForNPS.setBackground(Color.white);

		String[][] data = new String[administeredDoseForNPS.getRowCount()][administeredDoseForNPS
				.getColumnCount()];

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = (String) administeredDoseForNPS.getValueAt(i, j);
			}

		}

		procInputInst.getModleInputForNPSObj().setAdministeredDose(data);

		NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);

		administeredDoseForNPS.getModel().addTableModelListener(
				new TableForAdministeredDoseEventHandler());

		for (int i = 0; i < administeredDoseForNPS.getRowCount(); i++)
			for (int j = 0; j < administeredDoseForNPS.getColumnCount(); j++)
				administeredDoseForNPS.isCellEditable(i, j);

		tForDosing = (AbstractTableModel) administeredDoseForNPS.getModel();
		administeredDoseForNPS.setFillsViewportHeight(true);

		administeredDoseForNPS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		administeredDoseForNPS.setVisible(true);

		GridBagConstraints tableForDosingTableScrollPaneCon = new GridBagConstraints();
		tableForDosingTableScrollPaneCon = new GridBagConstraints();
		tableForDosingTableScrollPaneCon.gridx = 0;
		tableForDosingTableScrollPaneCon.gridy = 0;
		tableForDosingTableScrollPaneCon.weighty = 0.5;
		tableForDosingTableScrollPaneCon.weightx = 0.5;
		tableForDosingTableScrollPaneCon.gridheight = 5;
		tableForDosingTableScrollPaneCon.gridwidth = 4;
		tableForDosingTableScrollPaneCon.fill = GridBagConstraints.BOTH;

	
		
		JScrollPane tableForDosingScrollPane = new JScrollPane(administeredDoseForNPS,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tableForDosingScrollPane.setBackground(Color.white);
		tableForDosingScrollPane.setVisible(true);

		administeredDoseForNPS.validate();
		administeredDoseIFForNPS.add(tableForDosingScrollPane, tableForDosingTableScrollPaneCon);
		administeredDoseIFForNPS.validate();

		administeredDoseIFForNPS.setSize(administeredDoseIFForNPS.getSize());
		
		
		

	}

	void settingColumnsNameToDosingTable() {
		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();
		int noSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		for (int i = 0; i < noSortVar; i++) {
			TableColumnModel cmForFirstSortVariable = administeredDoseForNPS
					.getColumnModel();
			TableColumn tcForFirstSortVariable = cmForFirstSortVariable
					.getColumn(i);
			tcForFirstSortVariable.setHeaderValue(procInputInst
					.getMappingDataObj().getSortVariablesListVector().get(i));

		}

		TableColumnModel cmForFirstSortVariable = administeredDoseForNPS
		.getColumnModel();
		TableColumn tcForFirstSortVariable = cmForFirstSortVariable
		.getColumn(noSortVar);
		tcForFirstSortVariable.setHeaderValue("Administered Dose");
	}

}
