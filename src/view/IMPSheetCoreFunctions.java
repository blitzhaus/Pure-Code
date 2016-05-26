package view;

import java.awt.Dimension;
import java.text.DecimalFormat;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jxl.Sheet;

public class IMPSheetCoreFunctions {

	public static IMPSheetCoreFunctions IMP_SHEET_CORE_FUNC = null;

	public static IMPSheetCoreFunctions createIMPSheetCoreFuncInstance() {
		if (IMP_SHEET_CORE_FUNC == null) {
			IMP_SHEET_CORE_FUNC = new IMPSheetCoreFunctions();
		}
		return IMP_SHEET_CORE_FUNC;
	}

	void printAllColumnProperties() {

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
						ImportedDataSheet.createImportedDataSheetInstance().sheetIndex)
				.getColumnPropertiesArrayList().size(); i++) {
			ColumnProperties obj = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							ImportedDataSheet.createImportedDataSheetInstance().sheetIndex)
					.getColumnPropertiesArrayList().get(i);
		}
	}

	void fillDefaultValuesForColumnProperties() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[][] data = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getElementFromDS(
				ImportedDataSheet.createImportedDataSheetInstance().sheetIndex);
		for (int j = 0; j < data[0].length; j++) {
			ImportedDataSheet.createImportedDataSheetInstance().dataTypeTextField
					.setText(appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL()
							.get(
									ImportedDataSheet
											.createImportedDataSheetInstance().sheetIndex)
							.getColumnPropertiesArrayList().get(j)
							.getDataType());
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
					.getColumnPropertiesArrayList().get(j)
					.getUnitBuilderDataObj().setUnitsJustForDisplay("");
		}
	}

	void updateDisplayedTable(int requiredDecimals) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[][] data = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getElementFromDS(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getSelectedSheetIndex());

		String decimalFormat = "0.";
		for (int i = 0; i < requiredDecimals; i++) {
			decimalFormat = decimalFormat + "0";
		}

		for (int i = 0; i < ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
				.getRowCount(); i++) {
			for (int j = 0; j < ImportedDataSheet
					.createImportedDataSheetInstance().importedDataTable
					.getColumnCount(); j++) {
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
						.getHasHeader() == true) {
					DecimalFormat df = new DecimalFormat(decimalFormat);
					ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
							.setValueAt(data[i + 1][j], i, j);
				} else {
					ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
							.setValueAt(data[i][j], i, j);
				}

			}
		}
		ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
				.repaint();
		ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
				.revalidate();

	}

	protected void modifyStoredData(String string) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (string.length() == 0) {

			appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.setElementInDS(
							ImportedDataSheet.createImportedDataSheetInstance().originalValues,
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
									.getSelectedSheetIndex());

		} else {

			int requiredDecimals = Integer.parseInt(string);
			int startFrom = 0;
			String[][] data = new String[appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getElementFromDS(
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
									.getSelectedSheetIndex()).length][appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getElementFromDS(
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
									.getSelectedSheetIndex())[0].length];
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					data[i][j] = appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getElementFromDS(
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
											.getSelectedSheetIndex())[i][j];
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
									.getSelectedSheetIndex()).getHasHeader() == true) {
				startFrom = appInfo
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
										.getSelectedSheetIndex()).getStartRow();
			} else {
				startFrom = appInfo
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
										.getSelectedSheetIndex()).getStartRow();
			}

			printMatrix("before truncating and storing", ImportedDataSheet
					.createImportedDataSheetInstance().originalValues);
			for (int i = startFrom; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
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
							.getColumnPropertiesArrayList().get(j)
							.getDataType() == "Numeric") {

						data[i][j] = round(Double.parseDouble(data[i][j]),
								requiredDecimals)
								+ "";

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
								.getDataStructuresArrayList()
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
												.getSelectedSheetIndex())[i][j] = data[i][j];
					}

				}
			}
			printMatrix("After truncating and storing", ImportedDataSheet
					.createImportedDataSheetInstance().originalValues);

		}

	}

	private double round(double value, int requiredDecimals) {
		double power_of_ten = 1;
		while (requiredDecimals-- > 0) {
			power_of_ten *= 10.0;
		}
		return Math.round(value * power_of_ten) / power_of_ten;

	}

	void displayColumns() {
		
		
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableListModel = new DefaultListModel();
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableList = new JList(
				ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableListModel);

		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableList
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {

				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();

				boolean adjust = listSelectionEvent.getValueIsAdjusting();
				
				if (!adjust) {
					JList list = (JList) listSelectionEvent.getSource();
					int selections[] = list.getSelectedIndices();
					Object selectionValues[] = list.getSelectedValues();
					for (int i = 0, n = selections.length; i < n; i++) {
						if (i == 0) {
							
						}
						
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
								.setColumnNumberClicked(selections[i]);
					}
					String dataType = appInfo
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
							ImportedDataSheet
									.createImportedDataSheetInstance().sheetIndex)
					.getColumnPropertiesArrayList()
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
									.getColumnNumberClicked())
					.getDataType();

					ImportedDataSheet.createImportedDataSheetInstance().dataTypeTextField
							.setText(dataType);
					ImportedDataSheet.createImportedDataSheetInstance().unitsTextField
							.setText(appInfo
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
											ImportedDataSheet
													.createImportedDataSheetInstance().sheetIndex)
									.getColumnPropertiesArrayList()
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
													.getColumnNumberClicked())
									.getUnitBuilderDataObj()
									.getUnitsJustForDisplay());
					
					//disable required & optional decimals
					if(dataType.equals("Text")){
						ImportedDataSheet.createImportedDataSheetInstance().requiredDecimalsCombo.setEnabled(false);
						ImportedDataSheet.createImportedDataSheetInstance().numericRadioButton.setEnabled(false);
						
						ImportedDataSheet.createImportedDataSheetInstance().optionaldecimalCombo.setEditable(false);
						ImportedDataSheet.createImportedDataSheetInstance().optionalDecimalsRadioButton.setEnabled(false);
					} else if( dataType.equals("Numeric")){
						if(ImportedDataSheet.createImportedDataSheetInstance().numericRadioButton.isSelected() == true){
							ImportedDataSheet.createImportedDataSheetInstance().requiredDecimalsCombo.setEnabled(true);
							
						} else {
							ImportedDataSheet.createImportedDataSheetInstance().optionaldecimalCombo.setEditable(true);
							
							
						}
						ImportedDataSheet.createImportedDataSheetInstance().numericRadioButton.setEnabled(true);
						ImportedDataSheet.createImportedDataSheetInstance().optionalDecimalsRadioButton.setEnabled(true);
						
						//set the required decimals for the selected column.

						int reqDecimals  = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getColumnNumberClicked()).getRequiredDecimals().length()-2;

						if(reqDecimals == -2){
							ImportedDataSheet.createImportedDataSheetInstance().requiredDecimalsCombo.setSelectedIndex(0);
						} else {
							ImportedDataSheet.createImportedDataSheetInstance().requiredDecimalsCombo.setSelectedIndex(reqDecimals);
						}
						
						
						
					
					}
					

				}
			}
		};
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableList
				.addMouseListener(DDViewLayer.createViewLayerInstance());
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableList
				.addListSelectionListener(listSelectionListener);
/*		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableList
				.setVisibleRowCount(10);*/
/*		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableList
				.setPreferredSize(new Dimension(
						ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame
								.getWidth(),
						ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame
								.getHeight()));*/
		ImportedDataSheet.createImportedDataSheetInstance()
				.fillColumnsAvailableList();
		JScrollPane columnsAvailableScrollPane = new JScrollPane(
				ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableList);
		columnsAvailableScrollPane
				.setPreferredSize(new Dimension(
						ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame
								.getWidth(),
						ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame
								.getHeight()));
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame
				.getContentPane().add(columnsAvailableScrollPane);
	}

	void reflectChangesInAppicationInfo(String newName) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int tempColumnClicked = appInfo
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
				.getColumnNumberClicked();
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
				.getColumnPropertiesArrayList().get(tempColumnClicked)
				.setColumnNames(newName);
		fillColumnsAvailableListWhileMoving();
		String[][] tempData = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getElementFromDS(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getSelectedSheetIndex());
		tempData[0][tempColumnClicked] = newName;
		appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.setElementInDS(
						tempData,
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
								.getSelectedSheetIndex());

		reflectInOriginalAppInfo(tempData);
	}

	public void reflectInOriginalAppInfo(String[][] tempData) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getCopyOfOriginalDataStructures()
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
								.getSelectedSheetIndex(), tempData);
	}

	void fillColumnsAvailableListWhileMoving() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo = DisplayContents.createAppInfoInstance();
		String[][] data = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getElementFromDS(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getSelectedSheetIndex());
		appInfo = DisplayContents.createAppInfoInstance();

		DefaultListModel tempDefaultListModel = ImportedDataSheet
				.createImportedDataSheetInstance().columnsAvailableListModel;
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableListModel
				.removeAllElements();
		for (int i = 0; i < data[0].length; i++) {
			String c = appInfo
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
					.getColumnPropertiesArrayList().get(i).getColumnName();

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
									.getSelectedSheetIndex()).getHasHeader() == true) {
				// String c = (String)tempDefaultListModel.get(i);

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
								ImportedDataSheet
										.createImportedDataSheetInstance().sheetIndex)
						.getColumnPropertiesArrayList().get(i)
						.setColumnNames(c);
			} else {
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
								ImportedDataSheet
										.createImportedDataSheetInstance().sheetIndex)
						.getColumnPropertiesArrayList().get(i)
						.setColumnNames(c);
			}

			ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableListModel
					.add(
							ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableListModel
									.getSize(),
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
											ImportedDataSheet
													.createImportedDataSheetInstance().sheetIndex)
									.getColumnPropertiesArrayList().get(i)
									.getColumnName());
		}

		fillColumnsAvailableJtableWhileMoving();

	}

	private void fillColumnsAvailableJtableWhileMoving() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo = DisplayContents.createAppInfoInstance();
		String[][] data = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getElementFromDS(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getSelectedSheetIndex());
		appInfo = DisplayContents.createAppInfoInstance();
		for (int i = 0; i < data[0].length; i++) {
			String c = appInfo
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
					.getColumnPropertiesArrayList().get(i).getColumnName();

			ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
					.getColumnModel().getColumn(i).setHeaderValue(c);

		}
		ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame
				.getContentPane().repaint();
		// ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.repaint();

	}

	public void printMatrix(String str, String[][] ipMat) {
		System.out.println(str);
		for (int i = 0; i < ipMat.length; i++) {
			for (int j = 0; j < ipMat[0].length; j++) {
				System.out.print("ipMat[" + i + "][" + j + "]" + ipMat[i][j]
						+ "\t");
			}
			System.out.println();
		}
	}

}
