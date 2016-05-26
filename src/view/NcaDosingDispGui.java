package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
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
import Common.LogEntry;

public class NcaDosingDispGui {

	int rowChanged;
	int[] routeOfAdminFOrDosingD;
	AbstractAction tableForDosingCellChangedaction;

	private final class DosingTableCellEditionHandler implements Action {
		@Override
		public void actionPerformed(ActionEvent e) {
			TableCellListener tcl = (TableCellListener) e.getSource();

		}

		@Override
		public void setEnabled(boolean b) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removePropertyChangeListener(PropertyChangeListener listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public void putValue(String key, Object value) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object getValue(String key) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void addPropertyChangeListener(PropertyChangeListener listener) {
			// TODO Auto-generated method stub

		}
	}

	private final class DosingTabModelSelecHandler implements
			TableModelListener {
		@Override
		public void tableChanged(TableModelEvent arg0) {

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

			int columnChanged = tableForDosing.getSelectedColumn();
			rowChanged = tableForDosing.getSelectedRow();

			if ( (appInfo
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
											.getSelectedSheetIndex()).getNcaInfo()
							.getProcessingInputs().getModelInputsObj().getRootOfAdmistration() != 3)) {
				if ((columnChanged >= 0) && (rowChanged >= 0))
					for (int i = 0; i < tableForDosing.getRowCount(); i++) {
						for (int j = 0; j < tableForDosing.getColumnCount(); j++) {
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
									.getWorkSheetObjectsAL()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getWorkBooksArrayList()
													.get(
															appInfo
																	.getProjectInfoAL()
																	.get(
																			appInfo
																					.getSelectedProjectIndex())
																	.getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getDosingDataObj().setDosingValueAt(
											i,
											j,
											(String) tableForDosing.getValueAt(
													i, j));
						}
					}
			} else {
				
				if(columnChanged < appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size() + 4)
				{

					if ((columnChanged >= 0) && (rowChanged >= 0))
						for (int i = 0; i < tableForDosing.getRowCount(); i++) {
							for (int j = 0; j < tableForDosing.getColumnCount() - 3; j++) {
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
										.getWorkSheetObjectsAL()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getWorkBooksArrayList()
														.get(
																appInfo
																		.getProjectInfoAL()
																		.get(
																				appInfo
																						.getSelectedProjectIndex())
																		.getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getDosingDataObj().setDosingValueAt(
												i,
												j,
												(String) tableForDosing.getValueAt(
														i, j));
							}
						}
				
				}else
				{
					NcaDosingDispForDosingDefinedGui inst = new NcaDosingDispForDosingDefinedGui();

					inst.actionEventForDosingDefinedRouteAdmin(rowChanged,
							columnChanged);
				}
				
				
				tableForDosing.validate();
				DosingInternalFrame.moveToFront();
				DosingInternalFrame.validate();
				DosingInternalFrame.setSize(DosingInternalFrame.getSize());

			}

		}
	}

	private final class DosingTabListSelecHandler implements
			ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			System.out.println("The selection is "
					+ tableForDosing.getSelectedColumn() + " and "
					+ tableForDosing.getSelectedRow());

		}
	}

	JInternalFrame DosingInternalFrame;
	JTable tableForDosing;
	int numberOfRowForDosing;
	int numberOfColumnForDosing;
	AbstractTableModel tForDosing;
	int numberOfTimeDosingScreenCalled;
	boolean fromPositiveNumberOfProfile;

	public NcaDosingDispGui(String dummy) {

	}

	public static NcaDosingDispGui NCA_DOSE_GUI = null;

	public static NcaDosingDispGui createNcaDosingGuiInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NCA_DOSE_GUI == null) {
			NCA_DOSE_GUI = new NcaDosingDispGui("just object creation");
		}
		return NCA_DOSE_GUI;
	}

	public void NcaDosingDispGuiCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {
		createDummyDosingScreen();
	}

	private void createDummyDosingScreen() throws RowsExceededException,
			WriteException, BiffException, IOException {

		DosingInternalFrame = new JInternalFrame("Dosing", false, false, false,
				false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(DosingInternalFrame);
		GridBagLayout gridBagLayout = new GridBagLayout();
		DosingInternalFrame.setLayout(gridBagLayout);
		DosingInternalFrame
				.setLocation(
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ NcaSetupAvailableComp
										.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());//
		DosingInternalFrame
				.setSize(
						NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
								.getWidth()
								- NcaSetupAvailableComp
										.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getHeight());
		DosingInternalFrame.setVisible(true);
		DosingInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		NcaTabbedPanes.createNcaTabbedPaneInstance().setupTabDesktopPane
				.add(DosingInternalFrame);
	}
	
	ApplicationInfo appInfo= DisplayContents.createAppInfoInstance();
	DosingData dosingData = appInfo
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
	.getWorkSheetObjectsAL()
	.get(
			appInfo
					.getProjectInfoAL()
					.get(
							appInfo
									.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(
											appInfo
													.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getSelectedSheetIndex())
	.getNcaInfo().getProcessingInputs()
	.getDosingDataObj();
	

	void createDosingInternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {

		createTheInternalFrame();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() == 0)
			numberOfRowForDosing = 1;
		else
			numberOfRowForDosing = DetermineNumberOfSubject
					.createDetNoSubInst().no_subject;

		if (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
				.getSelectedIndex() == 3) {
			NcaDosingDispForDosingDefinedGui inst = new NcaDosingDispForDosingDefinedGui();
			inst.dosingTableCreationForDosingDefined();
		}

		else

		if (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
				.getSelectedIndex() == 2) {

			ivProfileDosingTableSizeDet();
		} else {
			nonIVProfileDosingTableSizeDet();
		}

		// tableForDosing.setBackground(new Color(238-238-224));
		tableForDosing.setSelectionBackground(new Color(238, 238, 224));
		tableForDosing.setShowHorizontalLines(true); // Configure some of
		// JTable's parameters
		tableForDosing.setRowSelectionAllowed(true);
		tableForDosing.setColumnSelectionAllowed(true);
		tableForDosingCellChangedaction = new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				tableForDosingCellChangeAction(e);

			}

			private void tableForDosingCellChangeAction(ActionEvent e) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				TableCellListener tcl = (TableCellListener) e.getSource();
				int noSortVar = appInfo
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
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size();

				if ((tcl.getColumn() == noSortVar + 1)
						|| (tcl.getColumn() == noSortVar)) {
					if (tcl.getNewValue().toString().matches("[^0-9]*")) {// if
						// contains
						// character
						JOptionPane.showMessageDialog(DosingInternalFrame,
								"Please enter Number", "Conform",
								JOptionPane.YES_OPTION);
						tableForDosing.setValueAt("", tcl.getRow(), tcl
								.getColumn());

					}
				} else if ((tcl.getColumn() == noSortVar + 2)) {
					System.out.println();
					if (tcl.getNewValue().toString().matches("[^0-9]*")) {// if
						// contains
						// character
						JOptionPane.showMessageDialog(DosingInternalFrame,
								"Please enter Number", "Conform",
								JOptionPane.YES_OPTION);
						tableForDosing.setValueAt("", tcl.getRow(), tcl
								.getColumn());

					} else if (tcl.getNewValue().toString().contains(".")) {
						try {
							int tauValue = Integer.parseInt(tcl.getNewValue()
									.toString());
							if (tauValue == 0) {
								JOptionPane.showMessageDialog(
										DosingInternalFrame,
										"Please note: tau cannot be zero",
										"Conform", JOptionPane.YES_OPTION);
							}

						} catch (Exception cannotConvertToInteger) {
							JOptionPane.showMessageDialog(DosingInternalFrame,
									"Please ensure tau is a whole Number",
									"Conform", JOptionPane.YES_OPTION);
						}

						tableForDosing.setValueAt("", tcl.getRow(), tcl
								.getColumn());
					}

				}
			}

		};

		new TableCellListener(tableForDosing, tableForDosingCellChangedaction);

		new ReorderableJList((JTable) tableForDosing);
		tableForDosing.getSelectionModel().addListSelectionListener(
				new DosingTabListSelecHandler());
		tableForDosing.getModel().addTableModelListener(
				new DosingTabModelSelecHandler());

		// int width = (int) ((int) getWidth() / 1.2);
		tableForDosing.setPreferredScrollableViewportSize(new Dimension(500,
				400));
		tForDosing = (AbstractTableModel) tableForDosing.getModel();
		// tForDosing.fireTableDataChanged();
		tableForDosing.setFillsViewportHeight(true);

		tableForDosing.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		GridBagConstraints tableForDosingTableScrollPaneCon = new GridBagConstraints();
		tableForDosingTableScrollPaneCon = new GridBagConstraints();
		tableForDosingTableScrollPaneCon.gridx = 0;
		tableForDosingTableScrollPaneCon.gridy = 0;
		tableForDosingTableScrollPaneCon.weighty = 0.5;
		tableForDosingTableScrollPaneCon.weightx = 0.5;
		tableForDosingTableScrollPaneCon.gridheight = 5;
		tableForDosingTableScrollPaneCon.gridwidth = 4;
		tableForDosingTableScrollPaneCon.fill = GridBagConstraints.BOTH;

		if (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
				.getSelectedIndex() == 2) {
			pupulateDosingTableIVProfileI();

		} else {

			populateDosingTableNonIvProfile();
		}
		tableForDosing.setVisible(true);
		JScrollPane tableForDosingScrollPane = new JScrollPane(tableForDosing);
		tableForDosingScrollPane.setBackground(Color.white);
		tableForDosingScrollPane.setVisible(true);
		DosingInternalFrame.add(tableForDosingScrollPane,
				tableForDosingTableScrollPaneCon);
		DosingInternalFrame.moveToFront();
		DosingInternalFrame.setSize(DosingInternalFrame.getSize());

		appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getDosingDataObj().setDosingDSForNCA(
						new String[tableForDosing.getRowCount()][tableForDosing
								.getColumnCount()]);

		// setting the sort variables into the dosing ds of appInfo
		if (appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() == 0) {

		} else {
			String[][] dataSortVar = appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getDataSortVariables();
			for (int i = 0; i < dataSortVar.length; i++) {
				for (int j = 0; j < dataSortVar[i].length; j++) {
					appInfo
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
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getDosingDataObj().setDosingValueAt(i, j,
									dataSortVar[i][j]);
				}
			}
		}

	}

	private void populateDosingTableNonIvProfile()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		for (int i = 0; i < appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			TableColumnModel cmForFirstSortVariable = tableForDosing
					.getColumnModel();
			TableColumn tcForFirstSortVariable = cmForFirstSortVariable
					.getColumn(i);
			tcForFirstSortVariable
					.setHeaderValue(appInfo
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
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.get(i));
		}

		TableColumnModel cm1 = tableForDosing.getColumnModel();
		TableColumn tc1 = cm1
				.getColumn(appInfo
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
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size());
		tc1.setHeaderValue("Dose");
		TableColumnModel cm2 = tableForDosing.getColumnModel();
		TableColumn tc2 = cm2
				.getColumn(appInfo
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
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() + 1);
		tc2.setHeaderValue("Time of Dose");
		if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.getSelectedIndex() != 1) {
			TableColumnModel cm3 = tableForDosing.getColumnModel();
			TableColumn tc3 = cm3
					.getColumn(appInfo
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
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 2);
			tc3.setHeaderValue("Tau");
		}
		for (int j = 0; j < DetermineNumberOfSubject.createDetNoSubInst().no_subject; j++)
			for (int k = 0; k < appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size(); k++) {
				String c = null;
				try {
					c = DetermineNumberOfSubject.createDetNoSubInst().dataSortVariables[j][k];
				} catch (Exception arrayIndexOutOfBoundException) {
					String[] error = new String[2];
					error[0] = "Selection";
					error[1] = "Please select the Model type and Route of Administration";

					break;
				}

				tableForDosing.setValueAt(c, j, k);
			}

	}

	private void pupulateDosingTableIVProfileI() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		for (int i = 0; i < appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			TableColumnModel cmForFirstSortVariable = tableForDosing
					.getColumnModel();
			TableColumn tcForFirstSortVariable = cmForFirstSortVariable
					.getColumn(i);
			tcForFirstSortVariable
					.setHeaderValue(appInfo
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
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.get(i));
		}

		TableColumnModel cm1 = tableForDosing.getColumnModel();
		TableColumn tc1 = cm1
				.getColumn(appInfo
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
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size());
		tc1.setHeaderValue("Dose");
		TableColumnModel cm2 = tableForDosing.getColumnModel();
		TableColumn tc2 = cm2
				.getColumn(appInfo
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
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() + 1);
		tc2.setHeaderValue("Time of Dose");
		if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.getSelectedIndex() != 1) {
			TableColumnModel cm4 = tableForDosing.getColumnModel();
			TableColumn tc4 = cm4
					.getColumn(appInfo
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
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 2);
			tc4.setHeaderValue("Tau");

			TableColumnModel cm3 = tableForDosing.getColumnModel();
			TableColumn tc3 = cm3
					.getColumn(appInfo
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
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 3);
			tc3.setHeaderValue("length of Infusion");
		} else {
			TableColumnModel cm3 = tableForDosing.getColumnModel();
			TableColumn tc3 = cm3
					.getColumn(appInfo
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
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 2);
			tc3.setHeaderValue("length of Infusion");
		}
		for (int j = 0; j < DetermineNumberOfSubject.createDetNoSubInst().no_subject; j++)
			for (int k = 0; k < appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size(); k++) {
				String c = DetermineNumberOfSubject.createDetNoSubInst().dataSortVariables[j][k];
				tableForDosing.setValueAt(c, j, k);

			}
	}

	private void nonIVProfileDosingTableSizeDet() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.getSelectedIndex() == 1) {
			numberOfColumnForDosing = 2 + appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();
			tableForDosing = new JTable(numberOfRowForDosing,
					numberOfColumnForDosing);
		} else {
			numberOfColumnForDosing = 3 + appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();
			tableForDosing = new JTable(numberOfRowForDosing,
					numberOfColumnForDosing);

		}

	}

	private void ivProfileDosingTableSizeDet() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.getSelectedIndex() == 1) {
			numberOfColumnForDosing = 3 + appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();
			tableForDosing = new JTable(numberOfRowForDosing,
					numberOfColumnForDosing);
		} else {
			numberOfColumnForDosing = 4 + appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();
			tableForDosing = new JTable(numberOfRowForDosing,
					numberOfColumnForDosing);
		}

	}

	private void createTheInternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {
		DosingInternalFrame = new JInternalFrame("Dosing", false, false, false,
				false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(DosingInternalFrame);
		GridBagLayout gridBagLayout = new GridBagLayout();
		DosingInternalFrame.setLayout(gridBagLayout);
		DosingInternalFrame
				.setLocation(
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ NcaSetupAvailableComp
										.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());//
		DosingInternalFrame
				.setSize(
						NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
								.getWidth()
								- NcaSetupAvailableComp
										.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getHeight());
		DosingInternalFrame.setVisible(true);
		DosingInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		NcaTabbedPanes.createNcaTabbedPaneInstance().setupTabDesktopPane
				.add(DosingInternalFrame);

	}

}
