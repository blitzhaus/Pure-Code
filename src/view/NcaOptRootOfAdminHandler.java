package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.TreePath;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaOptRootOfAdminHandler {

	public static NcaOptRootOfAdminHandler NCA_ROOT_OF_ADMIN_HANDLER = null;

	public static NcaOptRootOfAdminHandler createNcaOptRootHandler() {
		if (NCA_ROOT_OF_ADMIN_HANDLER == null) {
			NCA_ROOT_OF_ADMIN_HANDLER = new NcaOptRootOfAdminHandler(
					"just object creation");
		}
		return NCA_ROOT_OF_ADMIN_HANDLER;
	}

	public NcaOptRootOfAdminHandler(String dummy) {

	}

	NcaOptionsGui ncaOptGuiInst;
	NcaUpdateParameterNamesTable ncaUpdateParamnamesInst;
	NcaDosingDispGui ncaDosinginst;
	ModelInputs modelInputsInst;
	
	public void rootOfAdminHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		try {
			NcaParameterNamesDispGui.createParameterNamesInstance().isRestoringParameterNames = false;
			NcaParameterUnitsDispGui.createNcaParUnitsDisInst().isRestoringParameterUnits = false;

			// update the parameter names
			new NcaUpdateParameterNamesTable();
			NcaOptionsGui.createNcaOptionsInstance().previousRootOfAdministration = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.getRootOfAdmistration();

			if ((NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
					.getSelectedIndex() == 0)
					|| (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
							.getSelectedIndex() == 1)
					|| (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
							.getSelectedIndex() == 2)
					|| (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
							.getSelectedIndex() == 3)) {
				NcaMappingDispGui.createMappingInstance().MappingInternalFrame
						.moveToFront();
				for (int i = NcaParameterNamesDispGui
						.createParameterNamesInstance().parameterNamesTable
						.getRowCount(); i > 0; i--) {
					((DefaultTableModel) NcaParameterNamesDispGui
							.createParameterNamesInstance().parameterNamesTable
							.getModel()).removeRow(i - 1);
					
				}
				NcaSetupAvailableComp
						.createNcaSetupAvailCompInst().availableComponentsTree
						.setSelectionRow(1);
				
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
						.setRootOfAdmistration(
								NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
										.getSelectedIndex());
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
						.setRootAdministrationItem(
								(String) NcaOptionsGui
										.createNcaOptionsInstance().rootOfAdministration
										.getSelectedItem());
			}

			if (NcaOptionsGui.createNcaOptionsInstance().previousRootOfAdministration != 2
					&& NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
							.getSelectedIndex() == 2) {

				
				
				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getModelType() == 1) {
					NcaOptionsGui.createNcaOptionsInstance().columncount = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnCount();
					if (NcaOptionsGui.createNcaOptionsInstance().columncount < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 3) {
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
								- NcaOptionsGui.createNcaOptionsInstance().columncount; i++)
							((DefaultTableModel) NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getModel()).addColumn("");
					}

					for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size(); i++) {
						TableColumnModel cmForFirstSortVariable = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tcForFirstSortVariable = cmForFirstSortVariable
								.getColumn(i);
						tcForFirstSortVariable
								.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().get(i));
					}

					TableColumnModel cm1 = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnModel();
					TableColumn tc1 = cm1.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size());
					tc1.setHeaderValue("Dose");

					TableColumnModel cm2 = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnModel();
					TableColumn tc2 = cm2.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 1);
					tc2.setHeaderValue("Time of Dose");
					// tableForDosing.setTableHeader(tableForDosingHeader);

					TableColumnModel cm3 = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnModel();
					TableColumn tc3 = cm3.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 2);
					tc3.setHeaderValue("Length Of Infusion");
					// tableForDosing.setTableHeader(tableForDosingHeader);

					NcaOptionsGui.createNcaOptionsInstance().columncount = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnCount();
					for (int j = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 2; j < NcaOptionsGui
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
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() - 3; i++) {
						int columnIndex = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnCount() - 1;
						TableColumn tcol = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel().getColumn(columnIndex);
						NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
								.removeColumn(tcol);

					}

				} else {

					NcaOptionsGui.createNcaOptionsInstance().columncount = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnCount();
					if (NcaOptionsGui.createNcaOptionsInstance().columncount < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 4) {
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
								+ 4
								- NcaOptionsGui.createNcaOptionsInstance().columncount; i++)
							((DefaultTableModel) NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getModel()).addColumn("");
					}

					for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size(); i++) {
						TableColumnModel cmForFirstSortVariable = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tcForFirstSortVariable = cmForFirstSortVariable
								.getColumn(i);
						tcForFirstSortVariable
								.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().get(i));
					}

					TableColumnModel cm1 = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnModel();
					TableColumn tc1 = cm1.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size());
					tc1.setHeaderValue("Dose");
					// tableForDosing.setTableHeader(tableForDosingHeader);

					// TableColumnModel cm2 =
					// tableForDosingHeader.getColumnModel();
					TableColumnModel cm2 = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnModel();
					TableColumn tc2 = cm2.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 1);
					tc2.setHeaderValue("Time of Dose");
					// tableForDosing.setTableHeader(tableForDosingHeader);

					TableColumnModel cm3 = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnModel();
					TableColumn tc3 = cm3.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 2);
					tc3.setHeaderValue("tau");

					TableColumnModel cm4 = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnModel();
					TableColumn tc4 = cm4.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 3);
					tc4.setHeaderValue("Length Of Infusion");
					// tableForDosing.setTableHeader(tableForDosingHeader);

					NcaOptionsGui.createNcaOptionsInstance().columncount = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnCount();
					for (int j = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 2; j < NcaOptionsGui
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
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() - 4; i++) {
						int columnIndex = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnCount() - 1;
						TableColumn tcol = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel().getColumn(columnIndex);
						NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
								.removeColumn(tcol);

					}

				}

			} else if (NcaOptionsGui.createNcaOptionsInstance().previousRootOfAdministration == 2
					&& NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
							.getSelectedIndex() != 2
					&& NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
							.getSelectedIndex() != 3) {
				

				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getModelType() == 1) {
					NcaOptionsGui.createNcaOptionsInstance().columncount = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnCount();
					if (NcaOptionsGui.createNcaOptionsInstance().columncount < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 2) {
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
								- NcaOptionsGui.createNcaOptionsInstance().columncount; i++)
							((DefaultTableModel) NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getModel()).addColumn("");
					}

					for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size(); i++) {
						TableColumnModel cmForFirstSortVariable = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tcForFirstSortVariable = cmForFirstSortVariable
								.getColumn(i);
						tcForFirstSortVariable
								.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().get(i));
					}

					TableColumnModel cm1 = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnModel();
					TableColumn tc1 = cm1.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size());
					tc1.setHeaderValue("Dose");
					TableColumnModel cm2 = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnModel();
					TableColumn tc2 = cm2.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 1);
					tc2.setHeaderValue("Time of Dose");
					NcaOptionsGui.createNcaOptionsInstance().columncount = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnCount();
					for (int j = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 2; j < NcaOptionsGui
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
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() - 2; i++) {
						int columnIndex = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnCount() - 1;
						TableColumn tcol = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel().getColumn(columnIndex);
						NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
								.removeColumn(tcol);

					}

				} else {
					NcaOptionsGui.createNcaOptionsInstance().columncount = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnCount();
					if (NcaOptionsGui.createNcaOptionsInstance().columncount < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 3) {
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
								- NcaOptionsGui.createNcaOptionsInstance().columncount; i++)
							((DefaultTableModel) NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getModel()).addColumn("");
					}

					for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size(); i++) {
						TableColumnModel cmForFirstSortVariable = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tcForFirstSortVariable = cmForFirstSortVariable
								.getColumn(i);
						tcForFirstSortVariable
								.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().get(i));
					}
					TableColumnModel cm1 = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnModel();
					TableColumn tc1 = cm1.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size());
					tc1.setHeaderValue("Dose");
					TableColumnModel cm2 = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnModel();
					TableColumn tc2 = cm2.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 1);
					tc2.setHeaderValue("Time of Dose");
					// NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing.setTableHeader(tableForDosingHeader);

					TableColumnModel cm3 = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnModel();
					TableColumn tc3 = cm3.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 2);
					tc3.setHeaderValue("tau");
					NcaOptionsGui.createNcaOptionsInstance().columncount = NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing
							.getColumnCount();
					for (int j = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.size() + 2; j < NcaOptionsGui
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
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() - 3; i++) {
						int columnIndex = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnCount() - 1;
						TableColumn tcol = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel().getColumn(columnIndex);
						NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
								.removeColumn(tcol);
					}
				}
			} else if (NcaOptionsGui.createNcaOptionsInstance().previousRootOfAdministration != 3
					&& NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
							.getSelectedIndex() == 3) {/*

				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getNo_subject() != 0) {

					NcaDosingDispForDosingDefinedGui inst = new NcaDosingDispForDosingDefinedGui();
					inst.dosingTableCreationForDosingDefined();

					NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
							.setSelectionBackground(new Color(238, 238, 224));
					NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
							.setShowHorizontalLines(true); // Configure some of
					// JTable's
					// parameters
					NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
							.setRowSelectionAllowed(true);
					NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
							.setColumnSelectionAllowed(true);
					new ReorderableJList(NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing);
					new JvUndoableTable(NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing.getModel());
					// NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing.getSelectionModel().addListSelectionListener(new
					// DosingTabListSelecHandler());
					NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
							.getModel().addTableModelListener(
									new DosingTabModelSelecHandler());

					// int width = (int) ((int) getWidth() / 1.2);
					NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
							.setPreferredScrollableViewportSize(new Dimension(
									500, 400));
					NcaDosingDispGui.createNcaDosingGuiInst().tForDosing = (AbstractTableModel) NcaDosingDispGui
							.createNcaDosingGuiInst().tableForDosing.getModel();
					// tForDosing.fireTableDataChanged();
					NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
							.setFillsViewportHeight(true);

					NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
							.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

					GridBagConstraints tableForDosingTableScrollPaneCon = new GridBagConstraints();
					tableForDosingTableScrollPaneCon = new GridBagConstraints();
					tableForDosingTableScrollPaneCon.gridx = 0;
					tableForDosingTableScrollPaneCon.gridy = 0;
					tableForDosingTableScrollPaneCon.weighty = 0.5;
					tableForDosingTableScrollPaneCon.weightx = 0.5;
					tableForDosingTableScrollPaneCon.gridheight = 5;
					tableForDosingTableScrollPaneCon.gridwidth = 4;
					tableForDosingTableScrollPaneCon.fill = GridBagConstraints.BOTH;

					NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
							.setVisible(true);
					JScrollPane tableForDosingScrollPane = new JScrollPane(
							NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing);
					tableForDosingScrollPane.setBackground(Color.white);
					tableForDosingScrollPane.setVisible(true);
					NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
							.getContentPane().removeAll();
					NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
							.add(tableForDosingScrollPane,
									tableForDosingTableScrollPaneCon);
					NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
							.moveToFront();
					NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
							.setSize(NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
									.getSize());
				}

			*/} else if (NcaOptionsGui.createNcaOptionsInstance().previousRootOfAdministration == 3
					&& NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
							.getSelectedIndex() != 3) {

				if (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
						.getSelectedIndex() == 2) {

					if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getModelInputsObj().getModelType() == 1) {
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
									- NcaOptionsGui.createNcaOptionsInstance().columncount; i++)
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
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector()
											.get(i));
						}

						TableColumnModel cm1 = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tc1 = cm1
								.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size());
						tc1.setHeaderValue("Dose");

						TableColumnModel cm2 = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tc2 = cm2
								.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size() + 1);
						tc2.setHeaderValue("Time of Dose");
						// tableForDosing.setTableHeader(tableForDosingHeader);

						TableColumnModel cm3 = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tc3 = cm3
								.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size() + 2);
						tc3.setHeaderValue("Length Of Infusion");
						// tableForDosing.setTableHeader(tableForDosingHeader);

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
								- 3; i++) {
							int columnIndex = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnCount() - 1;
							TableColumn tcol = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnModel().getColumn(columnIndex);
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
								.getSortVariablesListVector().size() + 4) {
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
									+ 4
									- NcaOptionsGui.createNcaOptionsInstance().columncount; i++)
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
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector()
											.get(i));
						}

						TableColumnModel cm1 = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tc1 = cm1
								.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size());
						tc1.setHeaderValue("Dose");
						// tableForDosing.setTableHeader(tableForDosingHeader);

						// TableColumnModel cm2 =
						// tableForDosingHeader.getColumnModel();
						TableColumnModel cm2 = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tc2 = cm2
								.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size() + 1);
						tc2.setHeaderValue("Time of Dose");
						// tableForDosing.setTableHeader(tableForDosingHeader);

						TableColumnModel cm3 = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tc3 = cm3
								.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size() + 2);
						tc3.setHeaderValue("tau");

						TableColumnModel cm4 = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tc4 = cm4
								.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size() + 3);
						tc4.setHeaderValue("Length Of Infusion");
						// tableForDosing.setTableHeader(tableForDosingHeader);

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
								- 4; i++) {
							int columnIndex = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnCount() - 1;
							TableColumn tcol = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnModel().getColumn(columnIndex);
							NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
									.removeColumn(tcol);

						}

					}

				} else {

					

					if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getModelInputsObj().getModelType() == 1) {
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
									- NcaOptionsGui.createNcaOptionsInstance().columncount; i++)
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
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector()
											.get(i));
						}

						TableColumnModel cm1 = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tc1 = cm1
								.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size());
						tc1.setHeaderValue("Dose");
						TableColumnModel cm2 = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tc2 = cm2
								.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size() + 1);
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
								- 2; i++) {
							int columnIndex = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnCount() - 1;
							TableColumn tcol = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnModel().getColumn(columnIndex);
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
									- NcaOptionsGui.createNcaOptionsInstance().columncount; i++)
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
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector()
											.get(i));
						}
						TableColumnModel cm1 = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tc1 = cm1
								.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size());
						tc1.setHeaderValue("Dose");
						TableColumnModel cm2 = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tc2 = cm2
								.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size() + 1);
						tc2.setHeaderValue("Time of Dose");
						// NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing.setTableHeader(tableForDosingHeader);

						TableColumnModel cm3 = NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getColumnModel();
						TableColumn tc3 = cm3
								.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size() + 2);
						tc3.setHeaderValue("tau");
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
								- 3; i++) {
							int columnIndex = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnCount() - 1;
							TableColumn tcol = NcaDosingDispGui
									.createNcaDosingGuiInst().tableForDosing
									.getColumnModel().getColumn(columnIndex);
							NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
									.removeColumn(tcol);
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
	}
}