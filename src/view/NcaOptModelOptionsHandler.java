package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaOptModelOptionsHandler {

	public static NcaOptModelOptionsHandler NCA_MODEL_OPT_HANDLER = null;

	public static NcaOptModelOptionsHandler createNcaModelOptHandlerInst() {
		if (NCA_MODEL_OPT_HANDLER == null) {
			NCA_MODEL_OPT_HANDLER = new NcaOptModelOptionsHandler(
					"just object creation");
		}
		return NCA_MODEL_OPT_HANDLER;
	}

	public NcaOptModelOptionsHandler(String dummy) {

	}
	
	NcaOptionsGui ncaOptGuiInst;
	DefaultAndPrefferedUnit defAndPrefunitInst;
	NcaMappingDispGui ncaMappingDispGuiInst;
	ApplicationInfo appInfo ;
	NcaParameterUnitsDispGui ncaParamUnitsInst;
	NcaUpdateParameterNamesTable ncaUpdateParamNamesTabInst;
	ModelInputs modInputsInst;
	
	
	void actionPerformed() throws RowsExceededException, WriteException,
			BiffException, IOException {

		try {
			NcaParameterNamesDispGui.createParameterNamesInstance().isRestoringParameterNames = false;
			NcaParameterUnitsDispGui.createNcaParUnitsDisInst().isRestoringParameterUnits = false;
			// update The parameter Names Table
			new NcaUpdateParameterNamesTable();
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
		try {
			if (NcaParameterUnitsDispGui.createNcaParUnitsDisInst().isRestoringParameterUnits == true) {

			} else {

				 appInfo = DisplayContents
						.createAppInfoInstance();
				 defAndPrefunitInst = new DefaultAndPrefferedUnit();
				defAndPrefunitInst.preparationOfparameterByGroup();
				NcaOptionsGui.createNcaOptionsInstance().previousDataType = NcaOptionsGui
						.createNcaOptionsInstance().presentDataType;
				NcaOptionsGui.createNcaOptionsInstance().presentDataType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getModelType();
				NcaOptionsGui.createNcaOptionsInstance().ifModelOptionsIsSelected = true;
				NcaMappingDispGui.createMappingInstance().MappingInternalFrame
						.moveToFront();
				TreePath path = NcaSetupAvailableComp
						.createNcaSetupAvailCompInst().availableComponentsTree
						.getPathForRow(1);
				NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
						.setSelectionPath(path);

				if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
						.getSelectedIndex() == 1) {

					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
							.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL()
							.get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo()
							.getProcessingInputs()
							.getModelInputsObj()
							.setModelItem(
									(String) NcaOptionsGui
											.createNcaOptionsInstance().modelOptions
											.getSelectedItem());
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getModelInputsObj().setModelType(1);

					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
							.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL()
							.get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo()
							.getProcessingInputs()
							.getModelInputsObj()
							.setModelItem(
									(String) NcaOptionsGui
											.createNcaOptionsInstance().modelOptions
											.getSelectedItem());
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getParameterNamesObj().setPrefferedParameternames(
									new Vector<String>());
					for (int i = NcaParameterNamesDispGui
							.createParameterNamesInstance().parameterNamesTable
							.getRowCount(); i > 0; i--) {
						((DefaultTableModel) NcaParameterNamesDispGui
								.createParameterNamesInstance().parameterNamesTable
								.getModel()).removeRow(i - 1);
						
					}
					NcaMappingDispGui.createMappingInstance().endTimeVariableTextField
							.setVisible(true);
/*					NcaMappingDispGui.createMappingInstance().endTimeVariableLable
							.setVisible(true);*/
					NcaMappingDispGui.createMappingInstance().moveToEndTimeButton
							.setVisible(true);
					NcaMappingDispGui.createMappingInstance().moveToVolumeButton
							.setVisible(true);
					NcaMappingDispGui.createMappingInstance().volumeVariableTextField
							.setVisible(true);
/*					NcaMappingDispGui.createMappingInstance().volumeLable
							.setVisible(true);*/

					NcaParameterUnitsDispGui.createNcaParUnitsDisInst().parameterUnitsInternalFrame
							.revalidate();
					NcaParameterUnitsDispGui.createNcaParUnitsDisInst().parameterUnitsInternalFrame
							.repaint();
					NcaMappingDispGui.createMappingInstance().MappingInternalFrame
							.moveToFront();
					int rowCount = NcaParameterUnitsDispGui
							.createNcaParUnitsDisInst().parameterUnitsTable
							.getRowCount();
					for (int i = rowCount; i > 0; i--) {
						((DefaultTableModel) NcaParameterUnitsDispGui
								.createNcaParUnitsDisInst().parameterUnitsTable
								.getModel()).removeRow(i - 1);
					}
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getParameterUnitsDataObj().setPreferredUnitsArray(
									new String[9]);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getParameterUnitsDataObj().setDefaultUnitArray(
									new String[9]);

					for (int i = 0; i < 9; i++) {
						String[] s = new String[3];
						String c = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo().getProcessingInputs()
								.getParameterUnitsDataObj()
								.getParameterbyGroupValueAt(i);
						s[0] = c;
						String u = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo().getProcessingInputs()
								.getParameterUnitsDataObj()
								.getDefaultUnitValueAt(i);
						s[1] = u;
						s[2] = u;
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo().getProcessingInputs()
								.getParameterUnitsDataObj().setPreferredUnit(i,
										u);
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo().getProcessingInputs()
								.getParameterUnitsDataObj()
								.setDefaultUnitValueAt(i, u);

					}

					if (NcaOptionsGui.createNcaOptionsInstance().previousDataType != 1) {

						if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo().getProcessingInputs()
								.getModelInputsObj().getRootOfAdmistration() == 2) {
							NcaOptionsGui.createNcaOptionsInstance().columncount = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnCount();
							if (NcaOptionsGui.createNcaOptionsInstance().columncount < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() + 3) {
								for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size()
										+ 3
										- NcaOptionsGui
												.createNcaOptionsInstance().columncount; i++)
									((DefaultTableModel) NcaDosingDispGui
											.createNcaDosingGuiInst().tableForDosing
											.getModel()).addColumn("");
							}

							for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size(); i++) {
								TableColumnModel cmForFirstSortVariable = NcaDosingDispGui
										.createNcaDosingGuiInst().tableForDosing
										.getColumnModel();
								TableColumn tcForFirstSortVariable = cmForFirstSortVariable
										.getColumn(i);
								tcForFirstSortVariable
										.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getWorkSheetObjectsAL()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())
												.getNcaInfo()
												.getProcessingInputs()
												.getMappingDataObj()
												.getSortVariablesListVector()
												.get(i));
							}

							TableColumnModel cm1 = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnModel();
							TableColumn tc1 = cm1
									.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector()
											.size());
							tc1.setHeaderValue("Dose");
							TableColumnModel cm2 = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnModel();
							TableColumn tc2 = cm2
									.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector()
											.size() + 1);
							tc2.setHeaderValue("Time of Dose");

							TableColumnModel cm4 = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnModel();
							TableColumn tc4 = cm4
									.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector()
											.size() + 2);
							tc4.setHeaderValue("Length of Infusion");

							NcaOptionsGui.createNcaOptionsInstance().columncount = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnCount();
							for (int j = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() + 2; j < NcaOptionsGui
									.createNcaOptionsInstance().columncount; j++) {
								for (int i = 0; i < NcaDosingDispGui
										.createNcaDosingGuiInst().tableForDosing
										.getRowCount(); i++) {
									NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
											.setValueAt("", i, j);
								}
							}

							for (int i = 0; i < NcaOptionsGui
									.createNcaOptionsInstance().columncount
									- appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector()
											.size() - 3; i++) {
								int columnIndex = NcaDosingDispGui
										.createNcaDosingGuiInst().tableForDosing
										.getColumnCount() - 1;
								TableColumn tcol = NcaDosingDispGui
										.createNcaDosingGuiInst().tableForDosing
										.getColumnModel()
										.getColumn(columnIndex);
								NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
										.removeColumn(tcol);

							}

						} else {

							NcaOptionsGui.createNcaOptionsInstance().columncount = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnCount();
							if (NcaOptionsGui.createNcaOptionsInstance().columncount < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() + 2) {
								for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size()
										+ 2
										- NcaOptionsGui
												.createNcaOptionsInstance().columncount; i++)
									((DefaultTableModel) NcaDosingDispGui
											.createNcaDosingGuiInst().tableForDosing
											.getModel()).addColumn("");
							}

							for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size(); i++) {
								TableColumnModel cmForFirstSortVariable = NcaDosingDispGui
										.createNcaDosingGuiInst().tableForDosing
										.getColumnModel();
								TableColumn tcForFirstSortVariable = cmForFirstSortVariable
										.getColumn(i);
								tcForFirstSortVariable
										.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getWorkSheetObjectsAL()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())
												.getNcaInfo()
												.getProcessingInputs()
												.getMappingDataObj()
												.getSortVariablesListVector()
												.get(i));
							}

							TableColumnModel cm1 = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnModel();
							TableColumn tc1 = cm1
									.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector()
											.size());
							tc1.setHeaderValue("Dose");
							TableColumnModel cm2 = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnModel();
							TableColumn tc2 = cm2
									.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector()
											.size() + 1);
							tc2.setHeaderValue("Time of Dose");

							NcaOptionsGui.createNcaOptionsInstance().columncount = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnCount();
							for (int j = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() + 2; j < NcaOptionsGui
									.createNcaOptionsInstance().columncount; j++) {
								for (int i = 0; i < NcaDosingDispGui
										.createNcaDosingGuiInst().tableForDosing
										.getRowCount(); i++) {
									NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
											.setValueAt("", i, j);
								}
							}

							for (int i = 0; i < NcaOptionsGui
									.createNcaOptionsInstance().columncount
									- appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector()
											.size() - 2; i++) {
								int columnIndex = NcaDosingDispGui
										.createNcaDosingGuiInst().tableForDosing
										.getColumnCount() - 1;
								TableColumn tcol = NcaDosingDispGui
										.createNcaDosingGuiInst().tableForDosing
										.getColumnModel()
										.getColumn(columnIndex);
								NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
										.removeColumn(tcol);

							}

						}

					}

				}
			}
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

		if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.getSelectedIndex() == 0) {

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getProcessingInputs()
					.getModelInputsObj()
					.setModelItem(
							(String) NcaOptionsGui.createNcaOptionsInstance().modelOptions
									.getSelectedItem());
			try {
				if (NcaParameterUnitsDispGui.createNcaParUnitsDisInst().isRestoringParameterUnits == true) {

				} else {
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getModelInputsObj().setModelType(0);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
							.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL()
							.get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo()
							.getProcessingInputs()
							.getModelInputsObj()
							.setModelItem(
									(String) NcaOptionsGui
											.createNcaOptionsInstance().modelOptions
											.getSelectedItem());

					NcaMappingDispGui.createMappingInstance().endTimeVariableTextField
							.setVisible(false);
/*					NcaMappingDispGui.createMappingInstance().endTimeVariableLable
							.setVisible(false);*/
					NcaMappingDispGui.createMappingInstance().moveToEndTimeButton
							.setVisible(false);
					NcaMappingDispGui.createMappingInstance().moveToVolumeButton
							.setVisible(false);
					NcaMappingDispGui.createMappingInstance().volumeVariableTextField
							.setVisible(false);
/*					NcaMappingDispGui.createMappingInstance().volumeLable
							.setVisible(false);*/

					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getParameterUnitsDataObj().setPreferredUnitsArray(
									new String[16]);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getParameterUnitsDataObj().setDefaultUnitArray(
									new String[16]);
					NcaMappingDispGui.createMappingInstance().MappingInternalFrame
							.moveToFront();
					TreePath path = NcaSetupAvailableComp
							.createNcaSetupAvailCompInst().availableComponentsTree
							.getPathForRow(1);
					NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
							.setSelectionPath(path);
				}
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
		try {
			((DefaultTreeModel) NcaSetupAvailableComp
					.createNcaSetupAvailCompInst().availableComponentsTree
					.getModel()).reload();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			
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
}
