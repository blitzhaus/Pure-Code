package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;

public class NcaSubAreasDispGui {

	private final class PartialAreasTableListSelectionHandler implements
			ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			int rowSelected = tableForPartialArea.getSelectedRow();
			int columnSelected = tableForPartialArea.getSelectedColumn();
			isSubAreasGraph = true;
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			try {
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getLambdazDataobj().setProfileSelected(computeNoOfPartialAreas(rowSelected));
			} catch (RowsExceededException e1) {
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
			
			
			try {
				NcaLambdaZDispGui.createLambDispGuiInstance().observedX.clear();
				NcaLambdaZDispGui.createLambDispGuiInstance().observedY.clear();
				if (rowSelected == 0) {
					setObservedXYZerothProfile();
				} else {
					setObservedXYOtherProfiles();
				}
				
				
				NCAoutputPlots
						.createNcaoutputPlotsInst()
						.generateSubAreasPlotForTheProfileClicked(
								NcaLambdaZDispGui.createLambDispGuiInstance().observedX,
								NcaLambdaZDispGui.createLambDispGuiInstance().observedY,
								NcaLambdaZDispGui.createLambDispGuiInstance().profileNamesUsedForProfileNumberDetermination
										.get(computeNoOfPartialAreas(rowSelected)));
				isSubAreasGraph = false;
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private int computeNoOfPartialAreas(int rowSelected) throws RowsExceededException, WriteException, BiffException, IOException {
			
			if(NcaOptionsGui.createNcaOptionsInstance().numberOfPartialAreasCombo.getSelectedIndex()!=0){
				double x = rowSelected/(NcaOptionsGui.createNcaOptionsInstance().numberOfPartialAreasCombo.getSelectedIndex()+1);
				if(x <1){
					return 0;	
				} else{
					return (int) Math.ceil(x);
				}
			}

			return 0;
		}
	}

	void setObservedXYOtherProfiles() throws RowsExceededException,
			WriteException, BiffException, IOException {

		int numberOfObservationsToSkip = 0;
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getisSparseSelected() == false) {// serial
			// data
			// the for loop determines the number of
			// observations to skip to reach the required
			// profile
			
			int selectedProfileNumber = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getLambdazDataobj().getProfileSelected();
			for (int j = 0; j < selectedProfileNumber; j++) {
				numberOfObservationsToSkip = numberOfObservationsToSkip
						+ DetermineNumberOfSubject.createDetNoSubInst().subject_obs[j];
			}

			// now we determine and take the profile
			for (int k = numberOfObservationsToSkip; k < numberOfObservationsToSkip
					+ DetermineNumberOfSubject.createDetNoSubInst().subject_obs[selectedProfileNumber]; k++) {
				NcaLambdaZDispGui.createLambDispGuiInstance().observedX
						.add(DataPreparationForAllComponents
								.createDataPrepAllCompInst().TimeForAllProfile[k]);
				if(DataPreparationForAllComponents
								.createDataPrepAllCompInst().ConcForAllProfile[k] >= 0){
					NcaLambdaZDispGui.createLambDispGuiInstance().observedY
					.add(DataPreparationForAllComponents
							.createDataPrepAllCompInst().ConcForAllProfile[k]);
				} else{
					NcaLambdaZDispGui.createLambDispGuiInstance().observedY
					.add(0.0);
				}
				

			}
		} else if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getisSparseSelected() == true) {// sparse
			// data
			// serial data
			// the for loop determines the number of
			// observations to skip to reach the required
			// profile
			int selectedProfileNumber = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getLambdazDataobj().getProfileSelected();
			
			
			for (int j = 0; j < selectedProfileNumber; j++) {
				numberOfObservationsToSkip = numberOfObservationsToSkip
						+ DataPreparationForAllComponents
								.createDataPrepAllCompInst().no_distinct_obs_sparsesampling[j];
			}

			// now we determine and take the profile
			for (int k = numberOfObservationsToSkip; k < numberOfObservationsToSkip
					+ DataPreparationForAllComponents
							.createDataPrepAllCompInst().no_distinct_obs_sparsesampling[selectedProfileNumber]; k++) {
				NcaLambdaZDispGui.createLambDispGuiInstance().observedX
						.add(DataPreparationForAllComponents
								.createDataPrepAllCompInst().TimeForAllProfile[k]);
				if(DataPreparationForAllComponents
								.createDataPrepAllCompInst().ConcForAllProfile[k] >= 0){
					NcaLambdaZDispGui.createLambDispGuiInstance().observedY
					.add(DataPreparationForAllComponents
							.createDataPrepAllCompInst().ConcForAllProfile[k]);

				} else {
					NcaLambdaZDispGui.createLambDispGuiInstance().observedY
					.add(0.0);

				}
				
				
			}

		}

	}

	void setObservedXYZerothProfile() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		int selectedProfileNumber = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getProfileSelected();
		
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getisSparseSelected() == false) {// serial
			// data
			// in this case there are no observations to
			// skip
			for (int k = 0; k < DetermineNumberOfSubject.createDetNoSubInst().subject_obs[selectedProfileNumber]; k++) {
				NcaLambdaZDispGui.createLambDispGuiInstance().observedX
						.add(DataPreparationForAllComponents
								.createDataPrepAllCompInst().TimeForAllProfile[k]);
				if(DataPreparationForAllComponents
								.createDataPrepAllCompInst().ConcForAllProfile[k] >=0)
						NcaLambdaZDispGui.createLambDispGuiInstance().observedY
							.add(DataPreparationForAllComponents
									.createDataPrepAllCompInst().ConcForAllProfile[k]);
				else
					NcaLambdaZDispGui.createLambDispGuiInstance().observedY
					.add(0.0);
			}
		} else if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getisSparseSelected() == true) {// sparse
			// data

			// in this case there are no observations to
			// skip
			for (int k = 0; k < DataPreparationForAllComponents
					.createDataPrepAllCompInst().no_distinct_obs_sparsesampling[selectedProfileNumber]; k++) {
				NcaLambdaZDispGui.createLambDispGuiInstance().observedX
						.add(DataPreparationForAllComponents
								.createDataPrepAllCompInst().TimeForAllProfile[k]);
				
				if(DataPreparationForAllComponents
								.createDataPrepAllCompInst().ConcForAllProfile[k] >= 0){
					NcaLambdaZDispGui.createLambDispGuiInstance().observedY
					.add(DataPreparationForAllComponents
							.createDataPrepAllCompInst().ConcForAllProfile[k]);
				} else {
					NcaLambdaZDispGui.createLambDispGuiInstance().observedY
					.add(0.0);
				}
				
			}
		}

	}

	private final class PartialAreasTableLIstenerHandler implements
			TableModelListener {
		@Override
		public void tableChanged(TableModelEvent arg0) {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			int columnChanged = tableForPartialArea.getSelectedColumn();
			int rowChanged = tableForPartialArea.getSelectedRow();
			

			if ((rowChanged >= 0) || (columnChanged >= 0))
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getSubAreasDataObj()
						.setPartialValueAt(
								rowChanged,
								columnChanged,
								tableForPartialArea.getValueAt(rowChanged,
										columnChanged)
										+ "");

		}
	}

	public NcaSubAreasDispGui(String dummy) {

	}

	JInternalFrame DummyPartialAreaInternalFrame;
	JInternalFrame partialAreaInternalFrame;
	JInternalFrame partialAreaGraphInternalFrame;
	JDesktopPane partialAreaGraphDesktopPane;
	int presentNumberOfPartialAreas;
	int previousNumberOfPartialAreas;
	boolean ifFromNumberOfPartialAreasCombo;
	boolean ifMappingIsChangedInPartialArea;
	JTable tableForPartialArea;
	String[][] partialAreaData = new String[127][2];
	int numberOfColumn;
	int numberOfRow;
	boolean isSubAreasGraph;
	
	AbstractTableModel t;
	boolean ifPartialAreaScreenIsCalledFirstTime = true;
	boolean fromAreaScreen;
	int numberOfTimePartialAreaScreenCalled = 0;
	boolean fromPositiveNumberOfProfileInPartialArea;
	AbstractAction tableForSubAreasCellChangedaction;

	public static NcaSubAreasDispGui NCA_SUB_GUI = null;

	public static NcaSubAreasDispGui createNcaSubAreasDispGuiInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NCA_SUB_GUI == null) {
			NCA_SUB_GUI = new NcaSubAreasDispGui("just object creation");
		}
		return NCA_SUB_GUI;
	}

	public void NcaSubAreasDispGuiCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {
		createDummySubAreasInternalFrame();
	}

	private void createDummySubAreasInternalFrame()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		DummyPartialAreaInternalFrame = new JInternalFrame("Partial Area",
				true, true, true, true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(DummyPartialAreaInternalFrame);
		GridBagLayout gridBagLayout1 = new GridBagLayout();
		DummyPartialAreaInternalFrame.setLayout(gridBagLayout1);
		DummyPartialAreaInternalFrame
				.setLocation(
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ NcaSetupAvailableComp
										.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		DummyPartialAreaInternalFrame
				.setSize(
						NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
								.getWidth()
								- NcaSetupAvailableComp
										.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getHeight());
		DummyPartialAreaInternalFrame.setVisible(true);
		DummyPartialAreaInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		NcaTabbedPanes.createNcaTabbedPaneInstance().setupTabDesktopPane
				.add(DummyPartialAreaInternalFrame);
		DummyPartialAreaInternalFrame.moveToFront();
	}

	SubAreasData subAreasDatainst;
	
	
	void createSubAreasInternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		subAreasDatainst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
		.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL()
		.get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex())
		.getNcaInfo()
		.getProcessingInputs()
		.getSubAreasDataObj();
		
		partialAreaInternalFrame = new JInternalFrame("Partial Area", false,
				false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(partialAreaInternalFrame);
		GridBagLayout gridBagLayout = new GridBagLayout();
		partialAreaInternalFrame.setLayout(gridBagLayout);
		partialAreaInternalFrame
				.setLocation(
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ NcaSetupAvailableComp
										.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		partialAreaInternalFrame
				.setSize(
						(NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
								.getWidth() - NcaSetupAvailableComp
								.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getWidth()) / 2,
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getHeight());
		partialAreaInternalFrame.setVisible(true);
		partialAreaInternalFrame
				.setBorder(DDViewLayer.createViewLayerInstance().b);
		NcaTabbedPanes.createNcaTabbedPaneInstance().setupTabDesktopPane
				.add(partialAreaInternalFrame);
		partialAreaInternalFrame.moveToFront();

		partialAreaGraphInternalFrame = new JInternalFrame("Profile Graph",
				false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(partialAreaGraphInternalFrame);
		partialAreaGraphInternalFrame.setLocation(partialAreaInternalFrame
				.getX()
				+ partialAreaInternalFrame.getWidth(), partialAreaInternalFrame
				.getY());
		partialAreaGraphInternalFrame.setSize(partialAreaInternalFrame
				.getWidth(), partialAreaInternalFrame.getHeight());
		partialAreaGraphInternalFrame.setVisible(true);
		partialAreaGraphInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		NcaTabbedPanes.createNcaTabbedPaneInstance().setupTabDesktopPane
				.add(partialAreaGraphInternalFrame);
		partialAreaGraphInternalFrame.moveToFront();
		partialAreaGraphDesktopPane = new JDesktopPane();
		partialAreaGraphInternalFrame
				.setContentPane(partialAreaGraphDesktopPane);

		previousNumberOfPartialAreas = presentNumberOfPartialAreas;
		presentNumberOfPartialAreas = NcaOptionsGui.createNcaOptionsInstance().numberOfPartialAreasCombo
				.getSelectedIndex() + 1;
		
		if (ifFromNumberOfPartialAreasCombo == true) {
			ifMappingIsChangedInPartialArea = false;
			for (int i = 0; i < tableForPartialArea.getRowCount(); i++) {

				partialAreaData[i][0] = (String) tableForPartialArea
						.getValueAt(i, numberOfColumn - 2);
				partialAreaData[i][1] = (String) tableForPartialArea
						.getValueAt(i, numberOfColumn - 1);
				System.out.println("data in partial area "
						+ partialAreaData[i][0] + " " + partialAreaData[i][1]);

			}
		} else

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getIfMappingScreenIsChanged() == true) {
			// ifFromNumberOfPartialAreasCombo=false;
			for (int i = 0; i < tableForPartialArea.getRowCount(); i++) {

				partialAreaData[i][0] = "";
				partialAreaData[i][1] = "";
			}

		}

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() == 0) {
			numberOfRow = presentNumberOfPartialAreas;
			numberOfColumn = 3;
		} else {
			numberOfRow = presentNumberOfPartialAreas
					* DetermineNumberOfSubject.createDetNoSubInst().no_subject;
			numberOfColumn = 3 + appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();
		}

		tableForPartialArea = new JTable(numberOfRow, numberOfColumn);
		tableForPartialArea.setBackground(Color.white);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getNcaInfo()
				.getProcessingInputs()
				.getSubAreasDataObj()
				.setPartialAreaDSForNCA(
						new String[tableForPartialArea.getRowCount()][tableForPartialArea
								.getColumnCount()]);
		tableForPartialArea.setSelectionBackground(new Color(238, 238, 224));
		tableForPartialArea.setShowHorizontalLines(true); // Configure some of
		// JTable's
		// parameters
		tableForPartialArea.setRowSelectionAllowed(true);
		tableForPartialArea.setColumnSelectionAllowed(true);
		new ReorderableJList(tableForPartialArea);
		tableForSubAreasCellChangedaction = new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				tableForDosingCellChangeAction(e);

			}

			private void tableForDosingCellChangeAction(ActionEvent e) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				TableCellListener tcl = (TableCellListener) e.getSource();
				int noSortVar = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size();

				if ((tcl.getColumn() == (noSortVar + 1))
						|| (tcl.getColumn() == (noSortVar + 2))) {
					if (tcl.getNewValue().toString().matches("[^0-9]*")) {// if
						// contains
						// character
						JOptionPane.showMessageDialog(partialAreaInternalFrame,
								"Please enter Number", "Conform",
								JOptionPane.YES_OPTION);
						tableForPartialArea.setValueAt("", tcl.getRow(), tcl
								.getColumn());

					}
				}
			}

		};

		new TableCellListener(tableForPartialArea,
				tableForSubAreasCellChangedaction);
		tableForPartialArea.getModel().addTableModelListener(
				new PartialAreasTableLIstenerHandler());

		tableForPartialArea.getSelectionModel().addListSelectionListener(
				new PartialAreasTableListSelectionHandler());

		// int width = (int) ((int) getWidth() / 1.2);
		tableForPartialArea.setPreferredScrollableViewportSize(new Dimension(
				300, 200));
		t = (AbstractTableModel) tableForPartialArea.getModel();
		t.fireTableDataChanged();
		tableForPartialArea.setFillsViewportHeight(true);

		tableForPartialArea.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		GridBagConstraints partialAreaTableScrollPaneCon = new GridBagConstraints();
		partialAreaTableScrollPaneCon = new GridBagConstraints();
		partialAreaTableScrollPaneCon.gridx = 0;
		partialAreaTableScrollPaneCon.gridy = 0;
		partialAreaTableScrollPaneCon.weighty = 0.5;
		partialAreaTableScrollPaneCon.weightx = 0.5;
		partialAreaTableScrollPaneCon.gridheight = 5;
		partialAreaTableScrollPaneCon.gridwidth = 4;
		partialAreaTableScrollPaneCon.fill = GridBagConstraints.BOTH;

		// to fill the header of the sub areas table
		fillHeaderPartialAreasTable();
		TableColumnModel cm1 = tableForPartialArea.getColumnModel();
		TableColumn tc1 = cm1.getColumn(numberOfColumn - 3);
		tc1.setHeaderValue("Partial Area # ");
		// tableForDosing.setTableHeader(tableForDosingHeader);

		// TableColumnModel cm2 = tableForDosingHeader.getColumnModel();
		TableColumnModel cm2 = tableForPartialArea.getColumnModel();
		TableColumn tc2 = cm2.getColumn(numberOfColumn - 2);
		tc2.setHeaderValue("Start Time");
		// tableForDosing.setTableHeader(tableForDosingHeader);

		TableColumnModel cm3 = tableForPartialArea.getColumnModel();
		TableColumn tc3 = cm3.getColumn(numberOfColumn - 1);
		tc3.setHeaderValue("End Time");
		if (ifPartialAreaScreenIsCalledFirstTime == true) {
			partialAreaScreenCalled1stTime();

		} else if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getIfMappingScreenIsChanged() == true) {
			reflectMappingChanges();
		} else {

			int positionOfRowInsertion = previousNumberOfPartialAreas;
			if (ifFromNumberOfPartialAreasCombo == true) {
				fromNumberOfPartialAreas();
			}
		}

		fromTableToDS();
		JScrollPane partialAreaScrollPane = new JScrollPane(tableForPartialArea);
		partialAreaScrollPane.setBackground(Color.white);
		partialAreaScrollPane.setVisible(true);
		partialAreaInternalFrame.add(partialAreaScrollPane,
				partialAreaTableScrollPaneCon);
		partialAreaInternalFrame.setSize(partialAreaInternalFrame.getSize());
	}

	private void fromTableToDS() {
		// copying the content till the sort variables + partial area index
		// into the data structure
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		for (int i = 0; i < tableForPartialArea.getRowCount(); i++) {
			for (int j = 0; j < tableForPartialArea.getColumnCount() - 2; j++) {
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getSubAreasDataObj()
						.setPartialValueAt(i, j,
								tableForPartialArea.getValueAt(i, j) + "");
			}
		}

	}

	private void fromNumberOfPartialAreas() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		presentNumberOfPartialAreas = NcaOptionsGui.createNcaOptionsInstance().numberOfPartialAreasCombo
				.getSelectedIndex() + 1;
		System.out.println(" ###### in partial area combo.."
				+ presentNumberOfPartialAreas);
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() == 0)
			DetermineNumberOfSubject.createDetNoSubInst().no_subject = 1;
		for (int k = 0; k < DetermineNumberOfSubject.createDetNoSubInst().no_subject; k++) {

			for (int i = 0; i < presentNumberOfPartialAreas; i++) {

				for (int j = 0; j < numberOfColumn - 3; j++) {
					String c = DetermineNumberOfSubject.createDetNoSubInst().dataSortVariables[k][j];
					tableForPartialArea.setValueAt(c, k
							* presentNumberOfPartialAreas + i, j);

				}
				tableForPartialArea.setValueAt(i + 1, k
						* presentNumberOfPartialAreas + i, numberOfColumn - 3);
			}
		}

		int count = 0;

		
		int minVal = previousNumberOfPartialAreas;
		if (presentNumberOfPartialAreas < minVal)
			minVal = presentNumberOfPartialAreas;

		for (int k = 0; k < DetermineNumberOfSubject.createDetNoSubInst().no_subject; k++) {

			for (int i = 0; i < minVal; i++) {

				tableForPartialArea.setValueAt(partialAreaData[k
						* previousNumberOfPartialAreas + i][0], k
						* presentNumberOfPartialAreas + i, numberOfColumn - 2);
				tableForPartialArea.setValueAt(partialAreaData[k
						* previousNumberOfPartialAreas + i][1], k
						* presentNumberOfPartialAreas + i, numberOfColumn - 1);

			}

		}

	}

	private void reflectMappingChanges() {

		for (int k = 0; k < DetermineNumberOfSubject.createDetNoSubInst().no_subject; k++) {

			for (int i = 0; i < presentNumberOfPartialAreas; i++) {

				for (int j = 0; j < numberOfColumn - 3; j++) {
					String c = DetermineNumberOfSubject.createDetNoSubInst().dataSortVariables[k][j];
					tableForPartialArea.setValueAt(c, k
							* presentNumberOfPartialAreas + i, j);

				}
				tableForPartialArea.setValueAt(i + 1, k
						* presentNumberOfPartialAreas + i, numberOfColumn - 3);
			}
		}
		presentNumberOfPartialAreas = 1;

	}

	private void partialAreaScreenCalled1stTime() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getIfMappingScreenIsChanged() == false) {
			tableForPartialArea.setValueAt(1, 0, 0);

		} else {
			// int repeat=1;
			for (int k = 0; k < DetermineNumberOfSubject.createDetNoSubInst().no_subject; k++) {

				for (int i = 0; i < presentNumberOfPartialAreas; i++) {

					for (int j = 0; j < numberOfColumn - 3; j++) {
						String c = DetermineNumberOfSubject
								.createDetNoSubInst().dataSortVariables[k][j];
						tableForPartialArea.setValueAt(c, k
								* presentNumberOfPartialAreas + i, j);

					}
					tableForPartialArea.setValueAt(i + 1, k
							* presentNumberOfPartialAreas + i,
							numberOfColumn - 3);
				}
			}
			presentNumberOfPartialAreas = 1;
		}

	}

	private void fillHeaderPartialAreasTable() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int index = 0;
		while (index < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size()) {

			TableColumnModel cmForFirstSortVariable = tableForPartialArea
					.getColumnModel();
			TableColumn tcForFirstSortVariable = cmForFirstSortVariable
					.getColumn(index);
			tcForFirstSortVariable.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(index));
			index++;
		}
	}

}
