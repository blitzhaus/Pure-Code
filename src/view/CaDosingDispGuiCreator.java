package view;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.JinternalFrameFunctions;



public class CaDosingDispGuiCreator  {

	MyJTable tableForDosing;

	JInternalFrame DosingInternalFrame;

	int numberOfRowForDosing;
	int numberOfColumnForDosing;

	int numberOfDoseForCA;
	AbstractTableModel tForDosing;
	boolean isFromRowRemoval = false;
	CaMultipleLevelSortingProvider multiplelevelSortInst;
	CaNoOfSubjectDeterminer determineNoOfSubInst;
	
	public static CaDosingDispGuiCreator PD_DOSE_INST = null;

	public static CaDosingDispGuiCreator createCaDosingDispGui()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (PD_DOSE_INST == null) {
			PD_DOSE_INST = new CaDosingDispGuiCreator();
		}
		return PD_DOSE_INST;
	}

	public CaDosingDispGuiCreator() throws RowsExceededException, WriteException,
			BiffException, IOException {
		createDosingInternalFrame();
	}

	final class TableForDosingEventHandler implements TableModelListener {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		public void tableChanged(TableModelEvent arg0) {

			int rowChanged = tableForDosing.getSelectedRow();
			int colChanged = tableForDosing.getSelectedColumn();

			String tempStr = "";
			ProcessingInputsInfo procInputInst = CaMapingHandler
					.createCaMapHandInst().copyProcessingInput();

			if ((rowChanged >= 0 && colChanged >= 0)
					&& (isFromRowRemoval == false))
				tempStr = (String) tableForDosing.getValueAt(rowChanged,
						colChanged);

			try {

				if (tempStr.equals("")) {

				} else
					Double.parseDouble(tempStr);

				if (appInfo.isRetrievalFlag() == false)
					for (int i = 0; i < tableForDosing.getRowCount(); i++) {
						for (int j = 0; j < tableForDosing.getColumnCount(); j++) {

							procInputInst.getDosingDataObj().setDosingValueAt(
									i, j, tableForDosing.getValueAt(i, j) + "");
							CaMapingHandler.createCaMapHandInst()
									.setProcessingInput(procInputInst);
						}
					}

			} catch (Exception e) {

				if ((rowChanged >= 0 && colChanged >= 0)
						&& isFromRowRemoval == false) {

					String message = "Please insert a Number";
					try {
						CaMapingHandler.createCaMapHandInst().showMessage(
								message);
					} catch (RowsExceededException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (WriteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BiffException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}

		}
	}

	void createDosingInternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {
		DosingInternalFrame = new JInternalFrame("Dosing", false, false, false,
				false);
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(DosingInternalFrame);

		int width = 0;
		int height = 0;

		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		if (analysisType.equals("pk")) {
			width = PkMainScreenCreator.createPkMainScreenInstance().pkMainInternalFrame
					.getWidth()
					- CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
							.getWidth();
			height = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
					.getHeight();
		} else
		if (analysisType.equals("pd")) {
			width = PdMainScreenCreator.createPdMainScreenInstance().pdMainInternalFrame
					.getWidth()
					- CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
							.getWidth();
			height = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
					.getHeight();
		} else if (analysisType.equals("mm")) {
			width = MmMainScreenCreator.createMmMainScreenInstance().mmMainInternalFrame
					.getWidth()
					- CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
							.getWidth();
			height = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
					.getHeight();

		}

		else if (analysisType.equals("pkpdlink")) {
			width = PkPdLinkMainScreenCreator.createPkPdLinkMainScreenInstance().pkpdLinkMainInternalFrame
					.getWidth()
					- CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
							.getWidth();
			height = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
					.getHeight();

		} else if (analysisType.equals("irm")) {
			width = IrmMainScreenCreator.createIrmMainScreenInstance().irmMainInternalFrame
					.getWidth()
					- CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
							.getWidth();
			height = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
					.getHeight();

		}

		DosingInternalFrame
				.setLocation(
						CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ CaSetupAvailableCompCreator
										.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		DosingInternalFrame.setSize(width, height);

		DosingInternalFrame.setVisible(true);
		DosingInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);

		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
				.add(DosingInternalFrame);

	}

	public void createInternalGUIForDosing() throws RowsExceededException, WriteException, BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		DosingInternalFrame.getContentPane().removeAll();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if (procInputInst.getMappingDataObj().getSortVariablesListVector()
				.size() > 0) {

		
			 multiplelevelSortInst = CaMultipleLevelSortingProvider
					.createMultipleSortInstance();

			multiplelevelSortInst.setAppInfo(appInfo);
			multiplelevelSortInst.sortData(null);
			determineNoOfSubInst = CaNoOfSubjectDeterminer
					.createDetermineNoOfSubInstance();

			determineNoOfSubInst.determineNumberOfSubject(multiplelevelSortInst.dataSorted);
		}

		calculateNumberOfRowForDosing();
		calculateNumberOfColumnForDosing();

		int[] colIndices = new int[procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size() + 1];

		for (int i = 0; i < colIndices.length; i++) {
			colIndices[i] = i;
		}

		tableForDosing = new MyJTable(numberOfRowForDosing,
				numberOfColumnForDosing, colIndices);

		for (int i = 0; i < tableForDosing.getRowCount(); i++) {

			for (int j = 0; j < tableForDosing.getColumnCount(); j++) {

				tableForDosing.setValueAt("", i, j);

			}

		}
		new ReorderableJList((JTable) tableForDosing);
		tableForDosing.getTableHeader().setReorderingAllowed(false);
		// caMainScreen.tableForDosing.setFont(caMainScreen.componentOptionseFont);

		settingColumnsNameToDosingTable();

		tableForDosing.setBackground(Color.white);

		String[][] data = new String[tableForDosing.getRowCount()][tableForDosing
				.getColumnCount()];

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = "";
			}

		}

		procInputInst.getDosingDataObj().setDosingDSForNCA(data);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		tableForDosing.getModel().addTableModelListener(
				new TableForDosingEventHandler());

		for (int i = 0; i < tableForDosing.getRowCount(); i++)
			for (int j = 0; j < tableForDosing.getColumnCount(); j++)
				tableForDosing.isCellEditable(i, j);

		tForDosing = (AbstractTableModel) tableForDosing.getModel();
		tableForDosing.setFillsViewportHeight(true);

		tableForDosing.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableForDosing.setVisible(true);

		JScrollPane tableForDosingScrollPane = new JScrollPane(tableForDosing,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tableForDosingScrollPane.setBackground(Color.white);
		tableForDosingScrollPane.setVisible(true);

		tableForDosing.validate();
		DosingInternalFrame.add(tableForDosingScrollPane);
		DosingInternalFrame.validate();

		DosingInternalFrame.setSize(DosingInternalFrame.getSize());
		
		
		procInputInst = CaMapingHandler.createCaMapHandInst()
		.copyProcessingInput();
		String[] colHeader = new String[tableForDosing.getColumnCount()];
		
		for (int i = 0; i < colHeader.length; i++) {
			colHeader[i] = (String)tableForDosing.getColumnModel().getColumn(i).getHeaderValue();
		}
		
		procInputInst.getDosingDataObj().setDosingDSHeader(colHeader);

	}

	void settingColumnsNameToDosingTable() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		int modelNumber = 0;
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();

		
		if (analysisType.equals("pkpdlink")
				|| analysisType.equals("irm")) {
			modelNumber = procInputInst.getModelInputTab1Obj()
					.getModelNumberForLinkModel();
		} else if (analysisType.equals("pk")
				|| analysisType.equals("mm")) {
			modelNumber = procInputInst.getModelInputTab1Obj()
					.getModelNumberForCA();
		}

		// modelNumber = libraeyModelSelected + 1;

		if (analysisType.equals("pk")
				) {
			
			if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == true) {
				if (modelNumber == 1 || modelNumber == 3 || modelNumber == 4
						|| modelNumber == 5 || modelNumber == 6
						|| modelNumber == 7 || modelNumber == 8
						|| modelNumber == 11 || modelNumber == 12
						|| modelNumber == 13 || modelNumber == 14
						|| modelNumber == 18) {
					settingColumnsNameTo1stSetOfAlgebraicModel();

				} else if (modelNumber == 15 || modelNumber == 16
						|| modelNumber == 17) {
					settingColumnsNameTo2ndSetOfAlgebraicModel();
				}

				else {
					settingColumnsNameTo3rdSetOfAlgebraicModel();
				}
			} else {

				if (modelNumber == 1 || modelNumber == 2 || modelNumber == 3
						|| modelNumber == 4 || modelNumber == 5
						|| modelNumber == 6 || modelNumber == 9
						|| modelNumber == 10 || modelNumber == 11
						|| modelNumber == 12 || modelNumber == 13
						|| modelNumber == 14 || modelNumber == 17
						|| modelNumber == 18 || modelNumber == 21) {
					settingColumnsNameTo1stSetOfAlgebraicModel();

				} else if (modelNumber == 23 || modelNumber == 24) {
					settingColumnsNameTo2ndSetOfAlgebraicModel();
				}

				else {
					settingColumnsNameTo3rdSetOfAlgebraicModel();
				}

			}

		} else 
			if (analysisType.equals("mm")) {
				if (modelNumber == 1 || modelNumber == 3 || modelNumber == 4)
					settingColumnsNameTo1stSetOfAlgebraicModel();
				else {
					settingColumnsNameTo3rdSetOfAlgebraicModel();
				}
			}
		
		else
		if ( (analysisType.equals("pkpdlink")
				|| analysisType.equals("irm"))) {
			if (modelNumber == 1 || modelNumber == 3 || modelNumber == 4
					|| modelNumber == 5 || modelNumber == 6 || modelNumber == 7
					|| modelNumber == 8 || modelNumber == 11
					|| modelNumber == 12 || modelNumber == 13
					|| modelNumber == 14 || modelNumber == 18) {

				settingColumnsNameTo1stSetOfAlgebraicModel();
			} else if (modelNumber == 15 || modelNumber == 16
					|| modelNumber == 17) {

				settingColumnsNameTo2ndSetOfAlgebraicModel();
			}

			else {
				settingColumnsNameTo3rdSetOfAlgebraicModel();
			}
		}
		System.out.println();
	}

	private void settingColumnsNameTo3rdSetOfAlgebraicModel() {
		CaNoOfSubjectDeterminer determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		int noSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		for (int i = 0; i < noSortVar; i++) {
			TableColumnModel cmForFirstSortVariable = tableForDosing
					.getColumnModel();
			TableColumn tcForFirstSortVariable = cmForFirstSortVariable
					.getColumn(i);
			tcForFirstSortVariable.setHeaderValue(procInputInst
					.getMappingDataObj().getSortVariablesListVector().get(i));
		}
		numberOfDoseForCA = procInputInst.getModelInputTab1Obj()
				.getNumberOfDose();
		
		String[][] colName = readFile("DosingTableColName/case3.txt");
		for (int i = 0; i < 4; i++) {
			TableColumnModel cm0 = tableForDosing.getColumnModel();
			TableColumn tc0 = cm0.getColumn(noSortVar + i);
			tc0.setHeaderValue(colName[i][0]);
		}

		for (int j = 0; j < determineNoOfSubInst.noOfSubject; j++)
			for (int i = 0; i < numberOfDoseForCA; i++) {
				for (int k = 0; k < noSortVar; k++) {
					String c = determineNoOfSubInst.dataSortVariables[j][k];
					tableForDosing.setValueAt(c, j * numberOfDoseForCA + i, k);
				}
				tableForDosing.setValueAt(i + 1, j * numberOfDoseForCA + i,
						noSortVar);
			}
	}

	private void settingColumnsNameTo2ndSetOfAlgebraicModel() {
		CaNoOfSubjectDeterminer determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		int noSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		for (int i = 0; i < noSortVar; i++) {
			TableColumnModel cmForFirstSortVariable = tableForDosing
					.getColumnModel();
			TableColumn tcForFirstSortVariable = cmForFirstSortVariable
					.getColumn(i);
			tcForFirstSortVariable.setHeaderValue(procInputInst
					.getMappingDataObj().getSortVariablesListVector().get(i));
		}

		numberOfDoseForCA = procInputInst.getModelInputTab1Obj()
				.getNumberOfDose();
		
		String[][] colName = readFile("DosingTableColName/case2.txt");
		for (int i = 0; i < 5; i++) {
			TableColumnModel cm0 = tableForDosing.getColumnModel();
			TableColumn tc0 = cm0.getColumn(noSortVar + i);
			tc0.setHeaderValue(colName[i][0]);
		}

		for (int j = 0; j < determineNoOfSubInst.noOfSubject; j++)
			for (int i = 0; i < numberOfDoseForCA; i++) {
				for (int k = 0; k < noSortVar; k++) {
					String c = determineNoOfSubInst.dataSortVariables[j][k];
					tableForDosing.setValueAt(c, j * numberOfDoseForCA + i, k);
				}
				tableForDosing.setValueAt(i + 1, j * numberOfDoseForCA + i,
						noSortVar);
			}
	}

	private void settingColumnsNameTo1stSetOfAlgebraicModel() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		CaNoOfSubjectDeterminer determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		int noSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		for (int i = 0; i < noSortVar; i++) {
			TableColumnModel cmForFirstSortVariable = tableForDosing
					.getColumnModel();
			TableColumn tcForFirstSortVariable = cmForFirstSortVariable
					.getColumn(i);
			tcForFirstSortVariable.setHeaderValue(procInputInst
					.getMappingDataObj().getSortVariablesListVector().get(i));

		}

		numberOfDoseForCA = procInputInst.getModelInputTab1Obj()
				.getNumberOfDose();
		
		String[][] colName = readFile("DosingTableColName/case1.txt");
		for (int i = 0; i < 3; i++) {
			TableColumnModel cm0 = tableForDosing.getColumnModel();
			TableColumn tc0 = cm0.getColumn(noSortVar + i);
			tc0.setHeaderValue(colName[i][0]);
		}

		for (int j = 0; j < determineNoOfSubInst.noOfSubject; j++)
			for (int i = 0; i < numberOfDoseForCA; i++) {
				for (int k = 0; k < noSortVar; k++) {
					String c = determineNoOfSubInst.dataSortVariables[j][k];
					tableForDosing.setValueAt(c, j * numberOfDoseForCA + i, k);
				}
				tableForDosing.setValueAt(i + 1, j * numberOfDoseForCA + i,
						noSortVar);
			}
	}
	
	private String[][] readFile(String fileLocation) {
		ReadFile2dStrArray RF = new ReadFile2dStrArray(fileLocation);
		int rowCount = RF.rowCount;
		int colCount = RF.colCount;

		System.out.println("rowCount.." + rowCount);
		System.out.println("colCount.." + colCount);

		String[][] inputMatrix = new String[rowCount][colCount];
		String[] stringArray = new String[rowCount];
		inputMatrix = RF.fileArray;
		stringArray = RF.stringArray;

		return inputMatrix;
	}
	

	void calculateNumberOfColumnForDosing() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		int noSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int modelNumber;
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.equals("pkpdlink")
				|| analysisType.equals("irm")) {
			modelNumber = procInputInst.getModelInputTab1Obj()
					.getModelNumberForLinkModel();
		} else {
			modelNumber = procInputInst.getModelInputTab1Obj()
					.getModelNumberForCA();

		}

		if (analysisType.equals("pk")) {
			
			if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == true) {
				if (modelNumber == 1 || modelNumber == 3 || modelNumber == 4
						|| modelNumber == 5 || modelNumber == 6
						|| modelNumber == 7 || modelNumber == 8
						|| modelNumber == 11 || modelNumber == 12
						|| modelNumber == 13 || modelNumber == 14
						|| modelNumber == 18) {
					numberOfColumnForDosing = 3 + noSortVar;

				} else if (modelNumber == 15 || modelNumber == 16
						|| modelNumber == 17) {
					numberOfColumnForDosing = 5 + noSortVar;
				}

				else {
					numberOfColumnForDosing = 4 + noSortVar;
				}
			} else {

				if (modelNumber == 1 || modelNumber == 2 || modelNumber == 3
						|| modelNumber == 4 || modelNumber == 5
						|| modelNumber == 6 || modelNumber == 9
						|| modelNumber == 10 || modelNumber == 11
						|| modelNumber == 12 || modelNumber == 13
						|| modelNumber == 14 || modelNumber == 17
						|| modelNumber == 18 || modelNumber == 21) {
					numberOfColumnForDosing = 3 + noSortVar;

				} else if (modelNumber == 23 || modelNumber == 24) {
					numberOfColumnForDosing = 5 + noSortVar;
				}

				else {
					numberOfColumnForDosing = 4 + noSortVar;
				}

			}

		} else 
			if (analysisType.equals("mm")) {
				if (modelNumber == 1 || modelNumber == 3 || modelNumber == 4)
					numberOfColumnForDosing = 3 + noSortVar;
				else {
					numberOfColumnForDosing = 4 + noSortVar;
				}
			
		}else if( analysisType.equals("pkpdlink")
				|| analysisType.equals("irm"))
		{
			if (modelNumber == 1 || modelNumber == 3 || modelNumber == 4
					|| modelNumber == 5 || modelNumber == 6
					|| modelNumber == 7 || modelNumber == 8
					|| modelNumber == 11 || modelNumber == 12
					|| modelNumber == 13 || modelNumber == 14
					|| modelNumber == 18) {
				numberOfColumnForDosing = 3 + noSortVar;

			} else if (modelNumber == 15 || modelNumber == 16
					|| modelNumber == 17) {
				numberOfColumnForDosing = 5 + noSortVar;
			}

			else {
				numberOfColumnForDosing = 4 + noSortVar;
			}
		}
	

	}

	void calculateNumberOfRowForDosing() {

		CaNoOfSubjectDeterminer determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		if (procInputInst.getMappingDataObj().getSortVariablesListVector()
				.size() == 0)
			numberOfRowForDosing = procInputInst.getModelInputTab1Obj()
					.getNumberOfDose();
		else
			numberOfRowForDosing = determineNoOfSubInst.noOfSubject
					* procInputInst.getModelInputTab1Obj().getNumberOfDose();

	}
}
