package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Common.JinternalFrameFunctions;


import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NPSDosingTableCreation {

	MyJTable dosingTableForNPS;
	JInternalFrame dosingInternalFrameForNPS;
	AbstractTableModel tForDosing;
	public static NPSDosingTableCreation NPS_DOSE_GUI = null;

	public static NPSDosingTableCreation createNpsDosingGuiInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NPS_DOSE_GUI == null) {
			NPS_DOSE_GUI = new NPSDosingTableCreation();
		}
		return NPS_DOSE_GUI;
	}

	public NPSDosingTableCreation() {
		createTheInternalFrame();

	}

	public void createDosingTable() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		int noOfProfiles = 0;
		int noOfDose = 0;
		int noOfRow = noOfDose * noOfProfiles;
		int noOfCol = appInfo
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
						.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();
		String[][] data;

	}

	void createDosingInternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {

		createTheInternalFrame();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst().copyProcessingInput();
		
		int numberOfRow;
		int noOfSortVar = procInputInst.getMappingDataObj()
		.getSortVariablesListVector().size();
		
		int noOfDose ;
		try{
			 noOfDose = Integer.parseInt(procInputInst.getModleInputForNPSObj().getNumberOfDoseBeforeDisplay())-1;

		}catch (Exception e) {
			noOfDose = 0;
		}
	
		
		if(noOfDose >0)
		{
			if (noOfSortVar == 0)
				numberOfRow = (noOfDose);
			else
				numberOfRow = (noOfDose) * DetermineNumberOfSubject
						.createDetNoSubInst().no_subject;

			int numberOfColumn = procInputInst.getMappingDataObj()
			.getSortVariablesListVector().size() + 2;
			
			String[][] data = new String[numberOfRow][numberOfColumn];
			
			for (int i = 0; i < DetermineNumberOfSubject
			.createDetNoSubInst().no_subject; i++) {
				for(int k = 0; k < noOfDose ; k++)
				{
				
				for (int j = 0; j <noOfSortVar;  j++) {
					
					data[i * noOfDose +k][j] =   DetermineNumberOfSubject
					.createDetNoSubInst().dataSortVariables[i][j];
				}
				

				for (int j = noOfSortVar; j < data[0].length;  j++) {
					
					data[i * noOfDose +k ][j] =   "";
				}
			}
				
				
				
			}
			
			procInputInst.getModleInputForNPSObj().setDosingDataForNPS(data);
			
			NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);
			
			procInputInst = NPSHandlers.createNPSHandlersInst().copyProcessingInput();
			
			
			String[] headers = new String[numberOfColumn];
			int[] cIdx = new int[numberOfColumn -2];
			
			for (int i = 0; i < noOfSortVar; i++) {
				headers[i] = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().get(i);
				cIdx[i] = i;
			}
			
			headers[noOfSortVar] = "Time of Dose";
			headers[noOfSortVar+1] = "Dose";
			
			
			dosingTableForNPS = new MyJTable(data, headers, cIdx);

			// tableForDosing.setBackground(new Color(238-238-224));
			dosingTableForNPS.setSelectionBackground(new Color(238, 238, 224));
			dosingTableForNPS.setShowHorizontalLines(true); // Configure some of
															// JTable's parameters
			dosingTableForNPS.setRowSelectionAllowed(true);
			dosingTableForNPS.setColumnSelectionAllowed(true);
			new ReorderableJList(dosingTableForNPS);
			new JvUndoableTable(dosingTableForNPS.getModel());
			/*dosingTableForNPS.getSelectionModel().addListSelectionListener(
					new DosingTabListSelecHandler());*/
			dosingTableForNPS.getModel().addTableModelListener(
					new NPSDosingTabModelSelecHandler());

			// int width = (int) ((int) getWidth() / 1.2);
			dosingTableForNPS.setPreferredScrollableViewportSize(new Dimension(500,
					400));
			tForDosing = (AbstractTableModel) dosingTableForNPS.getModel();
			// tForDosing.fireTableDataChanged();
			dosingTableForNPS.setFillsViewportHeight(true);

			dosingTableForNPS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			GridBagConstraints dosingTableForNPSTableScrollPaneCon = new GridBagConstraints();
			dosingTableForNPSTableScrollPaneCon = new GridBagConstraints();
			dosingTableForNPSTableScrollPaneCon.gridx = 0;
			dosingTableForNPSTableScrollPaneCon.gridy = 0;
			dosingTableForNPSTableScrollPaneCon.weighty = 0.5;
			dosingTableForNPSTableScrollPaneCon.weightx = 0.5;
			dosingTableForNPSTableScrollPaneCon.gridheight = 5;
			dosingTableForNPSTableScrollPaneCon.gridwidth = 4;
			dosingTableForNPSTableScrollPaneCon.fill = GridBagConstraints.BOTH;

			
			dosingTableForNPS.setVisible(true);
			JScrollPane dosingTableForNPSScrollPane = new JScrollPane(dosingTableForNPS);
			dosingTableForNPSScrollPane.setBackground(Color.white);
			dosingTableForNPSScrollPane.setVisible(true);
			dosingInternalFrameForNPS.add(dosingTableForNPSScrollPane,
					dosingTableForNPSTableScrollPaneCon);
			//dosingInternalFrameForNPS.moveToFront();
			dosingInternalFrameForNPS.setSize(dosingInternalFrameForNPS.getSize());

			procInputInst.getModleInputForNPSObj().setDosingDataForNPS(
							new String[dosingTableForNPS.getRowCount()][dosingTableForNPS
									.getColumnCount()]);

			// setting the sort variables into the dosing ds of appInfo
			if (procInputInst.getMappingDataObj()
					.getSortVariablesListVector().size() == 0) {

			} else {
				String[][] dataSortVar = procInputInst.getProfileInfoObj()
						.getDataSortVariables();
				for (int i = 0; i < dataSortVar.length; i++) {
					for (int j = 0; j < dataSortVar[i].length; j++) {
						procInputInst.getModleInputForNPSObj()
								.setDosingValueAt(i, j, dataSortVar[i][j]);
					}
				}
			}
			
			NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);
		}
	
		

	}

	private void createTheInternalFrame() {
		dosingInternalFrameForNPS = new JInternalFrame("Dosing", false, false, false,
				false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(dosingInternalFrameForNPS);
		GridBagLayout gridBagLayout = new GridBagLayout();
		dosingInternalFrameForNPS.setLayout(gridBagLayout);
		dosingInternalFrameForNPS
				.setLocation(
						NPSSetupAvailComp.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ NPSSetupAvailComp
										.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
										NPSSetupAvailComp.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());//
		dosingInternalFrameForNPS
				.setSize(
						NPSMainPage.createNPSMainPageInst().npsMainInternalFrame
								.getWidth()
								- NPSSetupAvailComp
										.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
										NPSSetupAvailComp.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getHeight());
		dosingInternalFrameForNPS.setVisible(true);
		dosingInternalFrameForNPS.setBorder(DDViewLayer.createViewLayerInstance().b);
		NPSTabs.createNPSTabsInst().setupTabDesktopPane
		.add(dosingInternalFrameForNPS);
	}

}
