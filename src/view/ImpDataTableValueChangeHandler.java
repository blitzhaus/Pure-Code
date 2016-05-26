package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ImpDataTableValueChangeHandler {

	public static ImpDataTableValueChangeHandler IMPTAB_HANDLER = null;

	public static ImpDataTableValueChangeHandler createImpTableHandlerInst() {
		if (IMPTAB_HANDLER == null) {
			IMPTAB_HANDLER = new ImpDataTableValueChangeHandler(
					"just object creation");
		}

		return IMPTAB_HANDLER;
	}

	public ImpDataTableValueChangeHandler(String dummy) {

	}

	void valueChangeHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if ((ImportFile.createDispContentsInstance().finishAndOpenClicked == true)
				|| (DDViewLayer.createViewLayerInstance().isSCARestore == true)
				|| (DDViewLayer.createViewLayerInstance().isNPSRestore == true)
				|| (DDViewLayer.createViewLayerInstance().isDSRestore == true)
				|| (DDViewLayer.createViewLayerInstance().isNcaRestore == true)
				|| (DDViewLayer.createViewLayerInstance().isCARestore == true)
				|| (DDViewLayer.createViewLayerInstance().isPlotMavenRestore == true)
				|| (DDViewLayer.createViewLayerInstance().istableTransRestore == true)
				|| (DDViewLayer.createViewLayerInstance().isBqlRestore == true)) {

		} else {
			
			if(( ImportedDataSheetHandlers.createImpSheetHandInstance().isReflectingChangesToGUI == false) && (isChangeOfSameDataType() == true)){
				reflectChangesInAppInfo();

				if ((ImportedDataSheetHandlers.createImpSheetHandInstance().reqDecimalHandling == true)
						|| (ImportedDataSheetHandlers.createImpSheetHandInstance().optDecimalHandling == true)) {

					// reflect changes in appInfo so that the analysis takes data
					// from here
				} else if ((ImportedDataSheetHandlers.createImpSheetHandInstance().reqDecimalHandling == false)
						|| (ImportedDataSheetHandlers.createImpSheetHandInstance().optDecimalHandling == false)) {
					// which means it is a edit cell event i.e copy/paste or fill
					// down etc...
					reflectChangesInOriginalCopyOfDataStructures();
				}

				if ((DDViewLayer.createViewLayerInstance().isNCAExecution == true)
						&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)) {

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
							.setDataChanged(true);
				} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
						&& (appInfo
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
												.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
						&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
						&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
						&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {
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
							.getDesStatsInfo().setDataChanged(true);
				} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
						&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == true)
						&& (appInfo
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
												.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
						&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {
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
							.setDataChanged(true);
				} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
						&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == true)
						&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
						&& (appInfo
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
												.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)) {
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
											.getSelectedSheetIndex()).getScaInfo()
							.setDataChanged(true);
				} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
						&& (appInfo
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
												.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
						&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
						&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
						&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
						&& (DDViewLayer.createViewLayerInstance().isFromBQL == true)) {
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
											.getSelectedSheetIndex()).getBqlInfo()
							.setDataChanged(true);
				} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
						&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == true)) {
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
											.getSelectedSheetIndex()).getPlotInfo()
							.setDataChanged(true);
				} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
						&& (appInfo
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
												.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
						&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
						&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
						&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
						&& (DDViewLayer.createViewLayerInstance().isFromBQL == false)
						&& (DDViewLayer.createViewLayerInstance().isFromTableMaven == true)) {
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
											.getSelectedSheetIndex()).getTmInfo()
							.setDataChanged(true);
				}
			} else{
				if(ImportedDataSheetHandlers.createImpSheetHandInstance().isReflectingChangesToGUI == false){
					JOptionPane.showMessageDialog(ImportedDataSheet.createImportedDataSheetInstance(),
							"Data Type mis-match", "Conform", JOptionPane.YES_OPTION);
					loadOriginalDataIntoTable();
				} else {
					
				}
				
			}
			

		}

	}

	private void loadOriginalDataIntoTable() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int selectedSheetIndex = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex();
		
		String[][] data =  appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getElementFromDS(selectedSheetIndex);
		int rowIndex = ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
		.getSelectedRow();
		int columnIndex = ImportedDataSheet
		.createImportedDataSheetInstance().importedDataTable
		.getSelectedColumn();
		if(appInfo
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
								.getSelectedSheetIndex()).getHasHeader() == true){
			ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.setValueAt(data[rowIndex+1][columnIndex], rowIndex, columnIndex);
		} else{
			ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.setValueAt(data[rowIndex][columnIndex], rowIndex, columnIndex);
		}
		
		
	}

	boolean isChangeOfSameDataType() {
		if((ImportedDataSheetHandlers.createImpSheetHandInstance().reqDecimalHandling == true)
						|| (ImportedDataSheetHandlers.createImpSheetHandInstance().optDecimalHandling == true)){
			
		} else {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			int rowIndex = ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
			.getSelectedRow();
			int columnIndex = ImportedDataSheet
			.createImportedDataSheetInstance().importedDataTable
			.getSelectedColumn();
			if(columnIndex == -1){
				columnIndex = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnNumberClicked();
			}
			
			String columnDataType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(	appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
			.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(	appInfo.getProjectInfoAL()
											.get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getColumnPropertiesArrayList().get(columnIndex).getDataType();
			
			String changedValue = ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getValueAt(rowIndex, columnIndex).toString();
			
			String changedValueDataType = getDataTypeOf(changedValue);
			
			if(changedValueDataType.equals(columnDataType)){
				return true;
			} else{
				return false;
			}
		}
		
		return true;
		
		
	}

	String getDataTypeOf(String changedValue) {
		
		try{
			Double.parseDouble(changedValue);
			return "Numeric"; 
		} catch(Exception e){
			try{
				Integer.parseInt(changedValue);
				return "Numeric";
			}catch(Exception ee){
				return "Text";
			}
			
		} 
		
	}

	private void reflectChangesInAppInfo() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// copy the entire table contents and reflect them in appInfo
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

			String[][] data = appInfo
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
									.getSelectedSheetIndex());
			int rows = data.length - 1;
			int columns = data[0].length;

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					String value = "";
					if((ImportedDataSheetHandlers.createImpSheetHandInstance().reqDecimalHandling == true)
						|| (ImportedDataSheetHandlers.createImpSheetHandInstance().optDecimalHandling == true)){
						if(ImportedDataSheet
							.createImportedDataSheetInstance().importedDataTable
							.getValueAt(i, j).toString().equals("")){
							value = "-3.14159265359";
						} else {
							value = ImportedDataSheet
							.createImportedDataSheetInstance().importedDataTable
							.getValueAt(i, j).toString();
						}
					}  else {
						value = ImportedDataSheet
						.createImportedDataSheetInstance().importedDataTable
						.getValueAt(i, j).toString();
					}
					
					appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
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
											.getSelectedSheetIndex())[i + 1][j] = value;
				}
			}

		} else if (appInfo
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
								.getSelectedSheetIndex()).getHasHeader() == false) {

			String[][] data = appInfo
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
									.getSelectedSheetIndex());
			int rows = data.length;
			int columns = data[0].length;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					String value = "";
					if((ImportedDataSheetHandlers.createImpSheetHandInstance().reqDecimalHandling == true)
						|| (ImportedDataSheetHandlers.createImpSheetHandInstance().optDecimalHandling == true)){
						if(ImportedDataSheet
							.createImportedDataSheetInstance().importedDataTable
							.getValueAt(i, j).toString().equals("")){
							value = "-3.14159265359";
						} else {
							value = ImportedDataSheet
							.createImportedDataSheetInstance().importedDataTable
							.getValueAt(i, j).toString();
						}
					}  else {
						value = ImportedDataSheet
						.createImportedDataSheetInstance().importedDataTable
						.getValueAt(i, j).toString();
					}
					
					appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
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
											.getSelectedSheetIndex())[i][j] =value;
				}
			}
			System.out.println();

		}

	}

	private void reflectChangesInOriginalCopyOfDataStructures() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// copy the entire table contents and reflect them in appInfo
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

			String[][] data = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getCopyOfOriginalDataStructures().get(
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
			int rows = data.length - 1;
			int columns = data[0].length;

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getCopyOfOriginalDataStructures().get(
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
											.getSelectedSheetIndex())[i + 1][j] = ImportedDataSheet
							.createImportedDataSheetInstance().importedDataTable
							.getValueAt(i, j).toString();
				}
			}

		} else if (appInfo
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
								.getSelectedSheetIndex()).getHasHeader() == false) {

			String[][] data = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getCopyOfOriginalDataStructures().get(
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
			int rows = data.length;
			int columns = data[0].length;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getCopyOfOriginalDataStructures()
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
											.getSelectedSheetIndex())[i][j] = ImportedDataSheet
							.createImportedDataSheetInstance().importedDataTable
							.getValueAt(i, j).toString();
				}
			}
			System.out.println();

		}

	}

}
