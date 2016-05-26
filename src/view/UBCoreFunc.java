package view;

import java.io.IOException;

import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.LogEntry;

public class UBCoreFunc {

	public static UBCoreFunc UB_FUNC = null;
	
	CaParamUnitsDispGuiCreator caParamUnitDispInst;
	ParameterAndUnitListLoader paramAndUnitListInst;
	

	public static UBCoreFunc createUBCoreFuncInstance() {
		if (UB_FUNC == null) {
			UB_FUNC = new UBCoreFunc();
		}
		return UB_FUNC;
	}

	void cancelButtonHandler() {

		UnitBuilder.createUBInstance().unitBuilderFrame.setVisible(false);

	}

	void massMeasureAddHandler() {

		int massprefixSeleectedIndex = UnitBuilder.createUBInstance().massPrefixCombo
				.getSelectedIndex();

		UnitBuilder.createUBInstance().newUnitsText
				.setText(UnitBuilder.createUBInstance().newUnitsText.getText()
						+ UnitBuilder.createUBInstance().prefixes[massprefixSeleectedIndex]
						+ UnitBuilder.createUBInstance().massunits[UnitBuilder
								.createUBInstance().massUnitCombo
								.getSelectedIndex()]);

	}

	public void divisionOperatorHandler() {
		UnitBuilder.createUBInstance().newUnitsText.setText(UnitBuilder
				.createUBInstance().newUnitsText.getText()
				+ "/");

	}

	public void mulOperatorHandler() {
		UnitBuilder.createUBInstance().newUnitsText.setText(UnitBuilder
				.createUBInstance().newUnitsText.getText()
				+ "*");

	}

	public void expOperatorHandler() {
		UnitBuilder.createUBInstance().newUnitsText.setText(UnitBuilder
				.createUBInstance().newUnitsText.getText()
				+ "^");

	}

	public void volumeAddButtonHandler() {

		int prefixSelectdIndex = UnitBuilder.createUBInstance().volumePrefixCombo
				.getSelectedIndex();
		int unitSelectedIndex = UnitBuilder.createUBInstance().volumeUnitCombo
				.getSelectedIndex();
		UnitBuilder.createUBInstance().newUnitsText
				.setText(UnitBuilder.createUBInstance().newUnitsText.getText()
						+ UnitBuilder.createUBInstance().prefixes[prefixSelectdIndex]
						+ UnitBuilder.createUBInstance().volumeUnits[unitSelectedIndex]);

	}

	public void timeAddHandler() {
		int timeSelectedIndex = UnitBuilder.createUBInstance().timeunitsCombo
				.getSelectedIndex();
		UnitBuilder.createUBInstance().newUnitsText.setText(UnitBuilder
				.createUBInstance().newUnitsText.getText()
				+ UnitBuilder.createUBInstance().timeUnits[timeSelectedIndex]);
	}

	public void clearUnitsHandler() {

		UnitBuilder.createUBInstance().newUnitsText.setText("");
		UnitBuilder.createUBInstance().volumePrefixCombo.setSelectedIndex(0);
		UnitBuilder.createUBInstance().massPrefixCombo.setSelectedIndex(0);
		UnitBuilder.createUBInstance().timeunitsCombo.setSelectedIndex(0);
		UnitBuilder.createUBInstance().volumeUnitCombo.setSelectedIndex(0);
		UnitBuilder.createUBInstance().massUnitCombo.setSelectedIndex(0);

	}

	public void okButtonHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

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
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		if ((DDViewLayer.createViewLayerInstance().isBeforeNCA == true)
				&& (DDViewLayer.createViewLayerInstance().isBeforeCA == true)&&
				DDViewLayer.createViewLayerInstance().isBeforeInVitro == true) {
			ImportedDataSheet.createImportedDataSheetInstance().unitsTextField
					.setText(UnitBuilder.createUBInstance().newUnitsText
							.getText());
			ColumnProperties obj = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getColumnNumberClicked());
			obj.setDataType("Numeric");
			int timeIndex = UnitBuilder.createUBInstance().timeunitsCombo
					.getSelectedIndex();
			obj.getUnitBuilderDataObj().setTimeUnits(
					UnitBuilder.createUBInstance().timeUnits[timeIndex]);
			int massPrefixIndex = UnitBuilder.createUBInstance().massPrefixCombo
					.getSelectedIndex();
			obj.getUnitBuilderDataObj().setMasPrefixIndex(
					UnitBuilder.createUBInstance().prefixes[massPrefixIndex]);
			int massUnitIndex = UnitBuilder.createUBInstance().massUnitCombo
					.getSelectedIndex();
			obj.getUnitBuilderDataObj().setMassunitIndex(
					UnitBuilder.createUBInstance().massunits[massUnitIndex]);
			int volumePrefixIndex = UnitBuilder.createUBInstance().volumePrefixCombo
					.getSelectedIndex();
			obj.getUnitBuilderDataObj().setVolumePrefixIndex(
					UnitBuilder.createUBInstance().prefixes[volumePrefixIndex]);
			int volumeUnitsIndex = UnitBuilder.createUBInstance().volumeUnitCombo
					.getSelectedIndex();
			obj
					.getUnitBuilderDataObj()
					.setVolumeUnitindex(
							UnitBuilder.createUBInstance().volumeUnits[volumeUnitsIndex]);
			obj.getUnitBuilderDataObj().setUnitsJustForDisplay(
					UnitBuilder.createUBInstance().newUnitsText.getText());

			UnitBuilder.createUBInstance().TableColumnModel = ImportedDataSheet
					.createImportedDataSheetInstance().importedDataTableHeader
					.getColumnModel();
			TableColumn tc = UnitBuilder.createUBInstance().TableColumnModel
					.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getColumnNumberClicked());

			String headerName = tc.getHeaderValue().toString();
			String[] headerSplits = headerName.split("\\(");
			String ColumnName = headerSplits[0];
			
			String newHeaderName = headerSplits[0]
					+ "("
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
							.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL()
							.get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getColumnPropertiesArrayList()
							.get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getColumnNumberClicked())
							.getUnitBuilderDataObj().getUnitsJustForDisplay()
					+ ")";

			tc.setHeaderValue(newHeaderName);

			if (timeIndex != 0) {
				LogEntry
						.createLogEntryInstance()
						.logEntry(
								tc.getHeaderValue().toString()
										+ ": Time units Changed to "
										+ UnitBuilder.createUBInstance().timeUnits[timeIndex]);
			}
			if (massPrefixIndex != 0) {
				LogEntry
						.createLogEntryInstance()
						.logEntry(
								tc.getHeaderValue().toString()
										+ ": Mass Prefix Changed to "
										+ UnitBuilder.createUBInstance().prefixes[massPrefixIndex]);
			}
			if (massUnitIndex != 0) {
				LogEntry
						.createLogEntryInstance()
						.logEntry(
								tc.getHeaderValue().toString()
										+ ": Mass units Changed to "
										+ UnitBuilder.createUBInstance().massunits[massUnitIndex]);
			}
			if (volumePrefixIndex != 0) {
				LogEntry
						.createLogEntryInstance()
						.logEntry(
								tc.getHeaderValue().toString()
										+ ": Volume Prefix Changed to "
										+ UnitBuilder.createUBInstance().prefixes[volumePrefixIndex]);
			}
			ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame
					.repaint();

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == true)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)) {

			if (NcaOptionsGui.createNcaOptionsInstance().isFromDoseUnitBuilding == true) {
				int selectedPrefixIndex = NcaOptionsGui.createNcaOptionsInstance().doseUnitBuilderFrame.massPrefixCombo
						.getSelectedIndex();
				int massUnitSelectedIndex = NcaOptionsGui.createNcaOptionsInstance().doseUnitBuilderFrame.massUnitCombo
						.getSelectedIndex();

				int voumePrefixIndex =  NcaOptionsGui.createNcaOptionsInstance().doseUnitBuilderFrame.volumePrefixCombo.getSelectedIndex();
				int volumeIndex  = NcaOptionsGui.createNcaOptionsInstance().doseUnitBuilderFrame.volumeUnitCombo.getSelectedIndex();
				
				NcaOptionsGui.createNcaOptionsInstance().unitTextField
						.setText(UnitBuilder.createUBInstance().prefixes[selectedPrefixIndex]
								+ UnitBuilder.createUBInstance().massunits[massUnitSelectedIndex]);
				
				
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo().getProcessingInputs().getModelInputsObj()
										.setDoseUnits(NcaOptionsGui.createNcaOptionsInstance().unitTextField.getText());
				
				
				((DefaultTreeModel) NcaSetupAvailableComp
						.createNcaSetupAvailCompInst().availableComponentsTree
						.getModel()).reload();
				TreePath path = NcaSetupAvailableComp
						.createNcaSetupAvailCompInst().availableComponentsTree
						.getPathForRow(1);
				NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
						.setSelectionPath(path);

				NcaParameterUnitsDispGui.createNcaParUnitsDisInst().isRestoringParameterUnits = false;

			} else if (NcaOptionsGui.createNcaOptionsInstance().isFromPreferredUnitInternalFrame == true) {
				
				NcaParameterUnitsDispGui.createNcaParUnitsDisInst().parameterUnitsTable
						.setValueAt(UnitBuilder.createUBInstance().newUnitsText
								.getText(), NcaParameterUnitsDispGui
								.createNcaParUnitsDisInst().parameterUnitsTable
								.getSelectedRow(), NcaParameterUnitsDispGui
								.createNcaParUnitsDisInst().parameterUnitsTable
								.getSelectedColumn());
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getPreferredUnitsArray()[NcaParameterUnitsDispGui
						.createNcaParUnitsDisInst().parameterUnitsTable
						.getSelectedRow()] = UnitBuilder.createUBInstance().newUnitsText
						.getText();
				

			}
			
			else {
				
				int selectedColumn = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnNumberClicked();
				
				UnitBuilderData obj = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList()
						.get(selectedColumn)
						.getUnitBuilderDataObj();
				
				int timeIndex = UnitBuilder.createUBInstance().timeunitsCombo
						.getSelectedIndex();
				obj
						.setTimeUnits(UnitBuilder.createUBInstance().timeUnits[timeIndex]);
				int massPrefixIndex = UnitBuilder.createUBInstance().massPrefixCombo
						.getSelectedIndex();
				obj
						.setMasPrefixIndex(UnitBuilder.createUBInstance().prefixes[massPrefixIndex]);
				int massUnitIndex = UnitBuilder.createUBInstance().massUnitCombo
						.getSelectedIndex();
				obj
						.setMassunitIndex(UnitBuilder.createUBInstance().massunits[massUnitIndex]);
				int volumePrefixIndex = UnitBuilder.createUBInstance().volumePrefixCombo
						.getSelectedIndex();
				obj
						.setVolumePrefixIndex(UnitBuilder.createUBInstance().prefixes[volumePrefixIndex]);
				int volumeUnitsIndex = UnitBuilder.createUBInstance().volumeUnitCombo
						.getSelectedIndex();
				obj
						.setVolumeUnitindex(UnitBuilder.createUBInstance().volumeUnits[volumeUnitsIndex]);
				ImportedDataSheet.createImportedDataSheetInstance().unitsTextField
				.setText(UnitBuilder.createUBInstance().newUnitsText
						.getText());
				obj
						.setUnitsJustForDisplay(UnitBuilder.createUBInstance().newUnitsText
								.getText());
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getNcaInfo()
						.getColumnPropertiesArrayList()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getColumnNumberClicked())
						.setUnitBuilderDataObj(obj);

	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.setDataChanged(true);

				ImportedDataSheet.createImportedDataSheetInstance().unitsTextField
						.setText(obj.getUnitsJustForDisplay());

			}
			
			

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isCAExecution == true)
				&& (DDViewLayer.createViewLayerInstance().isBeforeCA == false)) {
			
			caParamUnitDispInst = CaParamUnitsDispGuiCreator.createCaParUnitsDisInst();
			
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
									.getSelectedSheetIndex()).getAnalysisType();

			procInputInst = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();

			 if ((analysisType.equals("pk") || analysisType.equals("pkpdlink")
					|| analysisType.equals("mm") || analysisType.equals("irm"))
					&& (CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().ifFromDoseUnitOfCA == true)
					&& caParamUnitDispInst.isFromPreferredUnitInternalFrame == false
					&& CaLibModelDispGuiCreator.createCalibraryModelInstance().ifFromPkUnit == false) {
				CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().doseUnitTextFieldForCA
						.setText(UnitBuilder.createUBInstance().newUnitsText
								.getText());
				procInputInst.getModelInputTab1Obj().setDoseUnit(
						UnitBuilder.createUBInstance().newUnitsText.getText());
				CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().ifFromDoseUnitOfCA = false;

			}else if ((analysisType.equals("pk")|| analysisType.equals("pd") || analysisType.equals("pkpdlink")
					|| analysisType.equals("mm") || analysisType.equals("irm"))
					&& (CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().ifFromDoseUnitOfCA == false)
					&& caParamUnitDispInst.isFromPreferredUnitInternalFrame == true
					&& CaLibModelDispGuiCreator.createCalibraryModelInstance().ifFromPkUnit == false) {
				String ss = UnitBuilder.createUBInstance().newUnitsText
						.getText();

				procInputInst.getParameterUnitsDataObj()
						.setFromPreferredUnitScreen(true);
				procInputInst
						.getParameterUnitsDataObj()
						.setPreferredUnit(
								caParamUnitDispInst.rowNoForPreferredUnit,
								ss);

				caParamUnitDispInst.parameterUnitsTable
						.setValueAt(
								ss,
								CaParamUnitsDispGuiCreator
										.createCaParUnitsDisInst().rowNoForPreferredUnit,
								2);
				caParamUnitDispInst.isFromPreferredUnitInternalFrame = false;

			} else if ((analysisType.equals("pkpdlink") || analysisType
					.equals("irm"))
					&& (CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().ifFromDoseUnitOfCA == false)
					&& caParamUnitDispInst.isFromPreferredUnitInternalFrame == false
					&& CaLibModelDispGuiCreator.createCalibraryModelInstance().ifFromPkUnit == true
					&& CaLibModelDispGuiCreator.createCalibraryModelInstance().ifFromSimulationUnit == false ) {
				
			
				
				String pkUnit = UnitBuilder.createUBInstance().newUnitsText
				.getText(); 
				
				if(pkUnit.contains("/"))
				{
					CaLibModelDispGuiCreator.createCalibraryModelInstance().pkUnitTf
					.setText(UnitBuilder.createUBInstance().newUnitsText
							.getText());
					procInputInst.getModelInputTab1Obj().setPkUnit(
					UnitBuilder.createUBInstance().newUnitsText.getText());
			
					String[] split = pkUnit.split("/");
					
					procInputInst.getParameterUnitsDataObj()
					.setConcMassUnit(split[0]);
					procInputInst.getParameterUnitsDataObj()
					.setVolumeUnit(split[1]);
					caParamUnitDispInst.paramUnitTableRebuilding(procInputInst);
				}
				else
				{
					/*String message = "Please choose a correct concentration unit";
					PDMapingHandler.createPDMapHandInst().showMessage(message);*/
					

					CaLibModelDispGuiCreator.createCalibraryModelInstance().pkUnitTf
					.setText(UnitBuilder.createUBInstance().newUnitsText
							.getText());
					procInputInst.getModelInputTab1Obj().setPkUnit(
					UnitBuilder.createUBInstance().newUnitsText.getText());
			
				//	String[] split = pkUnit.split("/");
					
					procInputInst.getParameterUnitsDataObj()
					.setConcMassUnit(pkUnit);
					/*procInputInst.getParameterUnitsDataObj()
					.setVolumeUnit(split[1]);*/
					caParamUnitDispInst.paramUnitTableRebuilding(procInputInst);
				
					
					
				}
				CaLibModelDispGuiCreator.createCalibraryModelInstance().ifFromPkUnit = false;
				
			}else if(CaLibModelDispGuiCreator.createCalibraryModelInstance().ifFromSimulationUnit == true 
					&& (CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().ifFromDoseUnitOfCA == false)
					&& caParamUnitDispInst.isFromPreferredUnitInternalFrame == false
					&& CaLibModelDispGuiCreator.createCalibraryModelInstance().ifFromPkUnit == false)
			{
				
				
				String simulationUnit = UnitBuilder.createUBInstance().newUnitsText
				.getText(); 
				CaLibModelDispGuiCreator.createCalibraryModelInstance().simulationUnitTf
				.setText(UnitBuilder.createUBInstance().newUnitsText
						.getText());
				
				procInputInst.getModelInputTab1Obj().setSimulationUnit(
						UnitBuilder.createUBInstance().newUnitsText.getText());
				
				if(simulationUnit.contains("/"))
				{	
			
					String[] split = simulationUnit.split("/");
					
					procInputInst.getParameterUnitsDataObj()
					.setConcMassUnit(split[0]);
					procInputInst.getParameterUnitsDataObj()
					.setVolumeUnit(split[1]);
					
					paramAndUnitListInst = ParameterAndUnitListLoader.createParamAndUnitListInstance();
					paramAndUnitListInst.unitListForParameters();
					caParamUnitDispInst.paramUnitTableRebuilding(procInputInst);
				}
				else
				{
					if(analysisType.equals("pk") || analysisType.equals("mm"))
					{
						procInputInst.getParameterUnitsDataObj()
						.setConcMassUnit(simulationUnit);
						
					}
				
					paramAndUnitListInst = ParameterAndUnitListLoader.createParamAndUnitListInstance();
					paramAndUnitListInst.unitListForParameters();
					caParamUnitDispInst.paramUnitTableRebuilding(procInputInst);
							
				}
				
				CaLibModelDispGuiCreator.createCalibraryModelInstance().ifFromSimulationUnit = false;
			}
		}else if((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isInVitroExecution == true))
		{

			String ss = UnitBuilder.createUBInstance().newUnitsText
					.getText();

			 
			
			procInputInst.getParameterUnitsDataObj()
					.setFromPreferredUnitScreen(true);
			procInputInst
					.getParameterUnitsDataObj()
					.setPreferredUnit(
							InVitroParamUnitDispGuiCreator.createInVitroParUnitsDisInst().rowNoForPreferredUnit,
							ss);

			InVitroParamUnitDispGuiCreator.createInVitroParUnitsDisInst().parameterUnitsTable
					.setValueAt(
							ss,
							InVitroParamUnitDispGuiCreator.createInVitroParUnitsDisInst().rowNoForPreferredUnit,
							2);
			InVitroParamUnitDispGuiCreator.createInVitroParUnitsDisInst().isFromPreferredUnitInternalFrame = false;

		
		}
		
			CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
			UnitBuilder.createUBInstance().unitBuilderFrame.setVisible(false);
		}

		

	}

