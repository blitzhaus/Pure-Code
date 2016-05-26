package view;

import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Common.ThousandsSeperator;

public class NPSHandlers {

	public static NPSHandlers NPS_HANDLERS = null;

	public static NPSHandlers createNPSHandlersInst() {
		if (NPS_HANDLERS == null) {
			NPS_HANDLERS = new NPSHandlers("just object creation");
		}

		return NPS_HANDLERS;
	}

	String tableName;
	DefaultMutableTreeNode node;
	String nodeName;
	String analysisType;

	public NPSHandlers(String dummy) {

	}

	ProcessingInputsInfo copyProcessingInput() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = null;
		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.equals("nps")) {
			procInputInst = appInfo
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
					.getProcessingInputs();
		} else if (analysisType.equals("pd")) {
			procInputInst = appInfo
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
									.getSelectedSheetIndex()).getPdInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("mm")) {
			procInputInst = appInfo
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
									.getSelectedSheetIndex()).getMmInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("pkpdlink")) {
			procInputInst = appInfo
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
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("irm")) {
			procInputInst = appInfo
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
									.getSelectedSheetIndex()).getMmInfo()
					.getProcessingInputs();
		}
		return procInputInst;
	}

	void setProcessingInput(ProcessingInputsInfo procInputInst) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.equals("nps")) {
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
									.getSelectedSheetIndex()).getNpsInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("pd")) {
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
									.getSelectedSheetIndex()).getPdInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("mm")) {
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
									.getSelectedSheetIndex()).getMmInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("pkpdlink")) {
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
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("irm")) {
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
									.getSelectedSheetIndex()).getIrInfo()
					.setProcessingInputs(procInputInst);
		}

	}

	public void tauInNpsTextFocusHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
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
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getModleInputForNPSObj()
				.setTauValueInNPS(
						NPSOpt.createNPSOptInst().tauInNPSTextField.getText());

	}

	void availaSetupCompTreeHandler() {

		if (NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree
				.getSelectionPath() != null) {
			String plotName = NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree
					.getSelectionPath().getLastPathComponent().toString();
			String[] pathSplits = NPSSetupAvailComp
					.createNPSSetupAvailCompInst().availableComponentsTree
					.getSelectionPath().toString().split(",");
			String[] plotNameSplits = plotName.split(" ");

			if (NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree
					.getMinSelectionRow() == 0) {
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToFront();
				DDViewLayer.createFeaturesPanelInstance().PreviewLable
						.setVisible(true);
			}

			if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Mapping]")) {
				NPSSetupDispComp.createNpsSetDispInst().MappingInternalFrame
						.moveToFront();
				NPSOpt.createNPSOptInst().optionsInternalFrame.moveToFront();

			}

		}

	}

	void availableColumnsSelectionHandler() {

		NPSSetupDispComp.createNpsSetDispInst().SortAndAvailableButton
				.setIcon(NPSSetupDispComp.createNpsSetDispInst().toLeft);
		NPSSetupDispComp.createNpsSetDispInst().timeAndAvailableButton
				.setIcon(NPSSetupDispComp.createNpsSetDispInst().toLeft);
		NPSSetupDispComp.createNpsSetDispInst().moveToConcentrationButton
				.setIcon(NPSSetupDispComp.createNpsSetDispInst().toRight);
		NPSSetupDispComp.createNpsSetDispInst().isAvailableToSort = true;
		NPSSetupDispComp.createNpsSetDispInst().isSortToAvailable = false;
		NPSSetupDispComp.createNpsSetDispInst().isAvailableToTime = true;
		NPSSetupDispComp.createNpsSetDispInst().isTimeToAvailable = false;
		NPSSetupDispComp.createNpsSetDispInst().isAvailableToConcentration = true;
		NPSSetupDispComp.createNpsSetDispInst().isConcentrationToAvailable = false;
	}

	void sortAndAvailButtonHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (NPSSetupDispComp.createNpsSetDispInst().isAvailableToSort == true) {
			int selectedIndex = NPSSetupDispComp.createNpsSetDispInst().availableColumnsList
					.getSelectedIndex();

			String moving = (String) NPSSetupDispComp.createNpsSetDispInst().availableColumnsList
					.getSelectedValue();

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
									.getSelectedSheetIndex())
					.getNpsInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getSortVariablesListVector()
					.add(
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
									.getNpsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size(),
							moving);

			NPSSetupDispComp.createNpsSetDispInst().sortVariableListModel
					.add(
							NPSSetupDispComp.createNpsSetDispInst().sortVariableListModel
									.getSize(), moving);
			NPSSetupDispComp.createNpsSetDispInst().availableVariablesListmodel
					.remove(selectedIndex);
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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedIndex);

		}
		if (NPSSetupDispComp.createNpsSetDispInst().isSortToAvailable == true) {

			String moving = (String) NPSSetupDispComp.createNpsSetDispInst().sortVariablesList
					.getSelectedValue();
			int selectedIndex = NPSSetupDispComp.createNpsSetDispInst().sortVariablesList
					.getSelectedIndex();
			NPSSetupDispComp.createNpsSetDispInst().availableVariablesListmodel
					.add(
							NPSSetupDispComp.createNpsSetDispInst().availableVariablesListmodel
									.getSize(), moving);

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
									.getSelectedSheetIndex())
					.getNpsInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
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
									.getNpsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);

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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().remove(selectedIndex);
			NPSSetupDispComp.createNpsSetDispInst().sortVariableListModel
					.remove(selectedIndex);

		}

	}

	void sortVarialbesListSelectionHandler() {
		NPSSetupDispComp.createNpsSetDispInst().SortAndAvailableButton
				.setIcon(NPSSetupDispComp.createNpsSetDispInst().toRight);
		NPSSetupDispComp.createNpsSetDispInst().isSortToAvailable = true;
		NPSSetupDispComp.createNpsSetDispInst().isAvailableToSort = false;
	}

	void concVarTextFocusHandler() {
		NPSSetupDispComp.createNpsSetDispInst().moveToConcentrationButton
				.setIcon(NPSSetupDispComp.createNpsSetDispInst().toLeft);
		NPSSetupDispComp.createNpsSetDispInst().isConcentrationToAvailable = true;
		NPSSetupDispComp.createNpsSetDispInst().isAvailableToConcentration = false;
	}

	void availToConcButtonHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if ((NPSSetupDispComp.createNpsSetDispInst().isAvailableToConcentration == true)) {
			String moving = (String) NPSSetupDispComp.createNpsSetDispInst().availableColumnsList
					.getSelectedValue();

			// select the column focused in the available columns list
			int selectedindex = NPSSetupDispComp.createNpsSetDispInst().availableColumnsList
					.getSelectedIndex();

			// remove the column fromt the avaiclable columns vector in appInfo
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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedindex);

			// remove the column from the available columns list
			NPSSetupDispComp.createNpsSetDispInst().availableVariablesListmodel
					.remove(selectedindex);

			// add the column to the conc text field
			NPSSetupDispComp.createNpsSetDispInst().conbcentrationVariableTextField
					.setText(moving);

			// set the conc mass unit to appInfo from the column properties
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
									.getSelectedSheetIndex())
					.getNpsInfo()
					.getProcessingInputs()
					.getParameterUnitsDataObj()
					.setConcMassUnit(
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
									.getNpsInfo()
									.getColumnPropertiesArrayList().get(
											selectedindex)
									.getUnitBuilderDataObj()
									.getMasPrefixIndex()
									+ appInfo
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
											.getNpsInfo()
											.getColumnPropertiesArrayList()
											.get(selectedindex)
											.getUnitBuilderDataObj()
											.getMassunitIndex());

			// set the volume unit to appInfo from column properties
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
									.getSelectedSheetIndex())
					.getNpsInfo()
					.getProcessingInputs()
					.getParameterUnitsDataObj()
					.setVolumeUnit(
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
									.getNpsInfo()
									.getColumnPropertiesArrayList().get(
											selectedindex)
									.getUnitBuilderDataObj()
									.getVolumePrefixIndex()
									+ appInfo
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
											.getNpsInfo()
											.getColumnPropertiesArrayList()
											.get(selectedindex)
											.getUnitBuilderDataObj()
											.getVolumeUnitindex());

			// set the conc column name
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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getMappingDataObj().setYcolumnName(
							moving);

			// set the concentration's columns original index into appInfo
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
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving
						.equals(appInfo
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
								.getColumnPropertiesArrayList().get(i)
								.getColumnName())) {
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
							.getNpsInfo().getProcessingInputs()
							.getMappingDataObj()
							.setyColumnCorrespondinOriginalIndex(i);
				}
			}
		}

		if (NPSSetupDispComp.createNpsSetDispInst().isConcentrationToAvailable == true) {
			String moving = NPSSetupDispComp.createNpsSetDispInst().conbcentrationVariableTextField
					.getText();

			// clear the conc text field
			NPSSetupDispComp.createNpsSetDispInst().conbcentrationVariableTextField
					.setText(null);

			// add the column to the available columns vector in appInfo
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
									.getSelectedSheetIndex())
					.getNpsInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
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
									.getNpsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);

			// add the conc column back to the available list of columns
			NPSSetupDispComp.createNpsSetDispInst().availableVariablesListmodel
					.add(
							NPSSetupDispComp.createNpsSetDispInst().availableVariablesListmodel
									.getSize(), moving);

			// clear the conc mass unit of the conc column
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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getParameterUnitsDataObj()
					.setConcMassUnit(new String(""));

			// clear the volume unit
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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getParameterUnitsDataObj()
					.setVolumeUnit(new String(""));

		}

	}

	void timeVarTextFocusHandler() {

		NPSSetupDispComp.createNpsSetDispInst().timeAndAvailableButton
				.setIcon(NPSSetupDispComp.createNpsSetDispInst().toRight);
		NPSSetupDispComp.createNpsSetDispInst().isTimeToAvailable = true;
		NPSSetupDispComp.createNpsSetDispInst().isAvailableToTime = false;

	}

	void timeAndAvailButtonHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		// moving the column from available list to the time text field
		if ((NPSSetupDispComp.createNpsSetDispInst().isAvailableToTime == true)) {

			String moving = (String) NPSSetupDispComp.createNpsSetDispInst().availableColumnsList
					.getSelectedValue();
			int selectedindex = NPSSetupDispComp.createNpsSetDispInst().availableColumnsList
					.getSelectedIndex();

			// remove the column selected from the available columns vector in
			// appInfo
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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedindex);

			// remove the column selected from the available columns list
			NPSSetupDispComp.createNpsSetDispInst().availableVariablesListmodel
					.remove(selectedindex);

			// set the column name to the time text field
			NPSSetupDispComp.createNpsSetDispInst().timeVariableTextField
					.setText(moving);

			// set the time column name in appInfo
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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getMappingDataObj().setxColumnName(
							moving);
			NPSSetupDispComp.createNpsSetDispInst().timeColumnName = moving;

			// set the time units to appinfo
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
									.getSelectedSheetIndex())
					.getNpsInfo()
					.getProcessingInputs()
					.getParameterUnitsDataObj()
					.setTimeUnit(
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
									.getNpsInfo()
									.getColumnPropertiesArrayList().get(
											selectedindex)
									.getUnitBuilderDataObj().getTimeUnits());

			// set the original time column index to appInfo
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
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving
						.equals(appInfo
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
								.getColumnPropertiesArrayList().get(i)
								.getColumnName())) {
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
							.getNpsInfo().getProcessingInputs()
							.getMappingDataObj()
							.setxColumnCorrespondinOriginalIndex(i);
				}
			}
		}

		// moving from the time text field to the available columns list
		if (NPSSetupDispComp.createNpsSetDispInst().isTimeToAvailable == true) {
			String moving = NPSSetupDispComp.createNpsSetDispInst().timeVariableTextField
					.getText();
			NPSSetupDispComp.createNpsSetDispInst().timeVariableTextField
					.setText(null);
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
									.getSelectedSheetIndex())
					.getNpsInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
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
									.getNpsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);

			// clear the time units in appInfo
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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getParameterUnitsDataObj()
					.setTimeUnit(new String(""));

			NPSSetupDispComp.createNpsSetDispInst().availableVariablesListmodel
					.add(
							NPSSetupDispComp.createNpsSetDispInst().availableVariablesListmodel
									.getSize(), moving);
		}

	}

	void availResultsTreeSelctionHandler() {

		if (NPSResultsAvailComp.createNPSResAvailInst().availableOutputsTree
				.getSelectionPath() != null) {
			String plotName = NPSResultsAvailComp.createNPSResAvailInst().availableOutputsTree
					.getSelectionPath().getLastPathComponent().toString();
			String[] pathSplits = NPSResultsAvailComp.createNPSResAvailInst().availableOutputsTree
					.getSelectionPath().toString().split(",");
			String[] plotNameSplits = plotName.split(" ");

			if (pathSplits[pathSplits.length - 2]
					.equalsIgnoreCase(" Time vs Conc")) {

				DDViewLayer.createmenuBarInstance().exportImage.setEnabled(true);
				DDViewLayer.createmenuBarInstance().exportTextoutput
						.setEnabled(false);
				DDViewLayer.createmenuBarInstance().exportTable.setEnabled(false);
				int plotNumber = Integer
						.parseInt(plotNameSplits[plotNameSplits.length - 1]);
				NPSResultsAvailComp.createNPSResAvailInst().selectedImage = plotNumber - 1;
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				if (NPSOpt.createNPSOptInst().viewsCombo.getSelectedIndex() == 0) {

					showPlot("linear", plotNumber);
				}

				else if (NPSOpt.createNPSOptInst().viewsCombo
						.getSelectedIndex() == 1) {
					showPlot("log", plotNumber);
				}
			}

			if (pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Final Output]")) {
				DDViewLayer.createmenuBarInstance().exportTextoutput
						.setEnabled(false);
				DDViewLayer.createmenuBarInstance().exportImage.setEnabled(false);
				DDViewLayer.createmenuBarInstance().exportTable.setEnabled(true);
				NPSResultsDispComp.createNPSResDispCompInst().finalParametersinternalFrame
						.moveToFront();
			}

			if (pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Complete output]")) {
				DDViewLayer.createmenuBarInstance().exportTextoutput
						.setEnabled(true);
				DDViewLayer.createmenuBarInstance().exportImage.setEnabled(false);
				DDViewLayer.createmenuBarInstance().exportTable.setEnabled(false);
				NPSResultsDispComp.createNPSResDispCompInst().textCompleteOutputInternalFrame
						.moveToFront();
			}

		}

	}

	private void showPlot(String axis, int plotNumber) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		JFreeChart chart = null;
		if (axis.equals("linear")) {
			chart = appInfo
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
					.getWorkSheetOutputs().getPlotOutputs().getLinearPlots()
					.get(plotNumber - 1);
		} else if (axis.equals("log")) {
			chart = appInfo
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
					.getWorkSheetOutputs().getPlotOutputs().getLogplots().get(
							plotNumber - 1);
		}

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setSize(
				NPSResultsDispComp.createNPSResDispCompInst().plotsFrame
						.getWidth(), NPSResultsDispComp
						.createNPSResDispCompInst().plotsFrame.getHeight());
		NPSResultsDispComp.createNPSResDispCompInst().plotsFrame
				.setContentPane(chartPanel);
		NPSResultsDispComp.createNPSResDispCompInst().plotsFrame.moveToFront();

	}

	boolean checkValidInputs() {
		boolean isValidTimeConc = checkValidTimeConc();
		boolean isValidModelOptions = checkValidModelOpt();
		if ((isValidTimeConc == true) && (isValidModelOptions == true)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean checkValidModelOpt() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst().copyProcessingInput();
		
		String loadDose = null;
		String maintainDose = null;
		String tau = null;
		String noDataOutputPoints = null;
		if (NPSOpt.createNPSOptInst().displayAtNthdoseRadioButton.isSelected() == true) {
			noDataOutputPoints = procInputInst.getModleInputForNPSObj()
					.getNoOfOutputPoints();
		}

		int noOfSortVar = procInputInst.getMappingDataObj().getSortVariablesListVector().size();
		
		if(NPSOpt.createNPSOptInst().dosingTypeComboBox.getSelectedIndex() == 0)
		{
		
			tau = procInputInst.getModleInputForNPSObj()
				.getTauValueInNPS();

			try{
			for (int i = 0; i < procInputInst.getModleInputForNPSObj().getAdministeredDose().length; i++) {
				
				loadDose = procInputInst.getModleInputForNPSObj().getAdministeredDose()[i][noOfSortVar];
			}
			}catch (Exception e) {
				loadDose = procInputInst.getModleInputForNPSObj()
				.getLoadingDose();
			}
		
			

			maintainDose = procInputInst.getModleInputForNPSObj()
				.getMaintenanceDose();
		}else
		{
			try{
				for (int i = 0; i < procInputInst.getModleInputForNPSObj().getAdministeredDose().length; i++) {
					
					loadDose = procInputInst.getModleInputForNPSObj().getAdministeredDose()[i][noOfSortVar];
				}
				}catch (Exception e) {
					loadDose = "";
				}
				
				try{
					for (int i = 0; i < procInputInst.getModleInputForNPSObj().getDosingDataForNPS().length; i++) {
						
						maintainDose = procInputInst.getModleInputForNPSObj().getDosingDataForNPS()[i][noOfSortVar+1];
					}
					}catch (Exception e) {
						maintainDose = "";
					}
				
					try{
						for (int i = 0; i < procInputInst.getModleInputForNPSObj().getDosingDataForNPS().length; i++) {
							
							tau = procInputInst.getModleInputForNPSObj().getDosingDataForNPS()[i][noOfSortVar];
						}
						}catch (Exception e) {
							tau = "";
						}
		}

		try {
			int noDataOutPts = 0;
			if (NPSOpt.createNPSOptInst().displayAtNthdoseRadioButton
					.isSelected() == true) {
				noDataOutPts = Integer.parseInt(noDataOutputPoints);
			}

			double ldDose = Double.parseDouble(loadDose);
			double mainDose = Double.parseDouble(maintainDose);
			double tauu = Double.parseDouble(tau);

			if (NPSOpt.createNPSOptInst().displayAtNthdoseRadioButton
					.isSelected() == true) {
				if ((noDataOutPts <= 0) || (ldDose <= 0) || (mainDose <= 0)
						|| (tauu <= 0)) {
					return false;
				} else {
					return true;
				}
			} else {
				if ((ldDose <= 0) || (mainDose <= 0) || (tauu <= 0)) {
					return false;
				} else {
					return true;
				}
			}

		} catch (Exception e) {
			return false;
		}

	}

	private boolean checkValidTimeConc() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		boolean isTimeColumnEmpty = false;
		boolean isConcColumnEmpty = false;
		boolean isTimeNumeric = false;
		boolean isConcNumeric = false;

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
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName()
				.equals("")) {
			isTimeColumnEmpty = true;

		} else {
			// determine data type of time column
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
									.getSelectedSheetIndex())
					.getNpsInfo()
					.getColumnPropertiesArrayList()
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
									.getNpsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getxColumnCorrespondinOriginalIndex())
					.getDataType().equals("Numeric")) {
				isTimeNumeric = true;
			} else {

			}
		}

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
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName()
				.equals("")) {
			isConcColumnEmpty = true;
		} else {
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
									.getSelectedSheetIndex())
					.getNpsInfo()
					.getColumnPropertiesArrayList()
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
									.getNpsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getyColumnCorrespondinOriginalIndex())
					.getDataType().equals("Numeric")) {
				isConcNumeric = true;
			} else {

			}
		}

		if ((isTimeColumnEmpty == false) && (isConcColumnEmpty == false)
				&& (isTimeNumeric == true) && (isConcNumeric == true)) {
			return true;
		} else {
			return false;
		}

	}

	void includeAsInput() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String plotName = NPSResultsAvailComp.createNPSResAvailInst().availableOutputsTree
				.getSelectionPath().getLastPathComponent().toString();
		String[] pathSplits = NPSResultsAvailComp.createNPSResAvailInst().availableOutputsTree
				.getSelectionPath().toString().split(",");

		String[] plotNameSplits = plotName.split(" ");
		String[][] sheeetData = null;

		if (NPSResultsAvailComp.createNPSResAvailInst().availableOutputsTree
				.getSelectionPath().getParentPath().toString().contains(
						"Sheets")) {
			int chosenOption = JOptionPane.showConfirmDialog(NPSResultsDispComp
					.createNPSResDispCompInst().displayResultsInternalFrame,
					"Use as a input for a different analysis", "Conform",
					JOptionPane.YES_OPTION, 0);

			if (chosenOption == 0) {

				// get the selected node's corresponding table data
				sheeetData = getCorespondingSheetData(sheeetData, pathSplits);

				// add the data structure to array list of data structures in
				// ApplicationInfo
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().add(new WorkSheetsInfo());
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getDataStructuresArrayList().add(sheeetData);

				String sheetName = appInfo
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
										.getSelectedSheetIndex())
						.getSheetName();

				analysisType = appInfo
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
										.getSelectedSheetIndex())
						.getAnalysisType();
				// set sheetName
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
										.getWorkSheetObjectsAL().size() - 1)
						.setSheetName(
								sheetName + "_" + analysisType + "_"
										+ tableName);

				// set selected row as 1
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

			String workBookSelected = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkBookName();

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
		String sheetName = appInfo
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
				.equalsIgnoreCase(" Final Output]")) {
			sheeetData = copySelectedSheetData(NPSResultsDispComp
					.createNPSResDispCompInst().finalparametersVerticalDisplayTable);
			tableName = "Final Output";
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
