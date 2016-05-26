package view;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Common.Iconizing;
import Common.JinternalFrameFunctions;
import Common.LogEntry;
import Common.PETreeFunctions;

import view.ApplicationInfo;
import view.DisplayContents;


import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class RestoreSCA {

	public RestoreSCA(String[] pathSplits) throws RowsExceededException, WriteException, BiffException {
		// TODO Auto-generated constructor stub
		


		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[] split = pathSplits[pathSplits.length-1].split("-");
		String wbName = split[split.length-2];
		split = split[split.length-1].split("]");
		String sheetName = split[split.length-1];
		
		
		
		
		DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage = true;
		DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage = false;
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution =false;
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		DDViewLayer.createViewLayerInstance().isNcaRestore = false;
		DDViewLayer.createViewLayerInstance().isDSRestore = false;
		DDViewLayer.createViewLayerInstance().isPlotMavenRestore = false;
		DDViewLayer.createViewLayerInstance().isTableMavenRestore = false;
		DDViewLayer.createViewLayerInstance().isNPSRestore = false;
		DDViewLayer.createViewLayerInstance().isSCARestore = true;
		DDViewLayer.createViewLayerInstance().isCARestore = false;
		DDViewLayer.createViewLayerInstance().istableTransRestore = false;
		DDViewLayer.createMTInstance().mainTabbedFrame.setTitle("SemiCompartmental Analysis");
		
		
	
		LogEntry.createLogEntryInstance().logEntry("Restored Semi Compartmental Analysis");
		
		try{

			PersistStructure persistStructureObject = new PersistStructure();
			ApplicationInfo readPerst = (ApplicationInfo)persistStructureObject.readEncryptedFile("outjar.enc");

			setAllTheGuiScreenObjectsToNull();
			
			
			
			
			
			
			//set the selected project
			PETreeFunctions.createPETreeFuncInst().setProjIndexAndSelectedProjPath(
					PETreeFunctions.createPETreeFuncInst().getProjIndex(PETreeFunctions.createPETreeFuncInst().getProjName(pathSplits))
					, PETreeFunctions.createPETreeFuncInst().getProjName(pathSplits));
			
			
			
			//set the selected work book
			setSelectedWorkBook(wbName, readPerst);
			

			int key = 0;
			for(int i=0;i<appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().size();i++){
				if(sheetName.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(i).getSheetName())){
					key = i;
					break;
				}
			}
			
			
			//set the selected work sheet
			readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).setSelectedSheetIndex(key);
			DisplayContents.setM_appInfo(readPerst);
			
			createInportedDataSheet(key, readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex());
			SCAMainPage.createScainstance().semiCompartmentalAnalysisMainPage(key);
			
			
			//set the workbook to the stored workBook
			Workbook restoringWB = null;
			
			appInfo = DisplayContents.createAppInfoInstance();
		
			restoringPreviousState(appInfo);
			ScaSetupAvailComp.createSetupAvailCompInst().availableComponentsTree.setSelectionRow(0);
			DDViewLayer.createFeaturesPanelInstance().PreviewLable.doClick();
			
		
		} catch(IOException e1){
			
		} /*catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */
		
	
	
	}
	
	private void setSelectedWorkBook(String wbName, ApplicationInfo readPerst) {
		
		int selectedWorkBookIndex = 0;
		
		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().size(); i++) {

			if (readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(i).getWorkBookName()
					.equals(wbName)) {
				selectedWorkBookIndex = i;
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).setSelectedWorkBookIndex(selectedWorkBookIndex);
				break;
			}
		}

		

	}
	
	
	private void createInportedDataSheet(int selectedSheetIndex, int selectedWorkBookIndex) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).setSelectedWorkBookIndex(selectedWorkBookIndex);
		
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.setSelectedSheetIndex(selectedSheetIndex);
		ImportedDataSheet.createImportedDataSheetInstance();
		ImportedDataSheet.createImportedDataSheetInstance()
				.createImpDataSheetFrame();
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setTitle("Imported Data main screen");
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setLocation(0, 0);
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setBorder(DDViewLayer.createViewLayerInstance().b);
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setBackground(Color.white);
		JinternalFrameFunctions
				.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame);
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setSize(DDViewLayer.createMTInstance().mainTabbedDesktopPane
						.getWidth(),
						DDViewLayer.createMTInstance().mainTabbedDesktopPane
								.getHeight());
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setVisible(true);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane
				.add(ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame);
		// fix(mainTabbedDesktopPane);

	}
	
private void setAllTheGuiScreenObjectsToNull() {
		
	SCAMainPage.SCA_MAIN_PAGE = null;
	ScaTabs.SCA_TABS = null;
	ScaSetupAvailComp.SCA_SETUP_AVAIL_COMP = null;
	ScaSetupDispComp.SCA_SETUP_DISP_COMP = null;
	ScaResultsAvailComp.SCA_RES_AVAIL_CIMP = null;
	ScaResultsDispComp.SCA_RES_DISP_COMP = null;
	ScaOptions.SCA_OPTIONS = null;
	ImportedDataSheet.importedDataSheetFrame = null;
	
	}

private void restoringPreviousState(ApplicationInfo readPerst) throws RowsExceededException, WriteException, BiffException, IOException {
		
		
		restoreMappingPreviousState(readPerst);
		restoreModelOptions(readPerst);
		restoreResults(readPerst);
		
		
		
	}

private void restoreResults(ApplicationInfo readPerst) throws RowsExceededException, WriteException, BiffException, IOException {

	ScaoutputGeneration.createScaoutInst().scaoutGeneration();
}


	private void restoreModelOptions(ApplicationInfo readPerst) {

		ScaOptions.createScaOptInst().Ke0TextField.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getModelInputsObj().getKe0Value());
		ScaOptions.createScaOptInst().computationMethodComboBox
				.setSelectedIndex(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getScaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getMethodNumberForSCA());
}

	private void restoreMappingPreviousState(ApplicationInfo readPerst) {

		// first we remove the available columns populated from the column names
		// of imported data....this is in normal
		// proceeding to NCA but not here ...... here the case is restoring the
		// values
		// removing the values from the list displayed
		int size = ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
				.size();
		for (int i = ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
				.size() - 1; i >= 0; i--) {
			ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
					.remove(i);
		}

		// setting the available columns
		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().size(); i++) {
			ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
					.add(
							i,
							readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getScaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().get(i));

		}

		ScaSetupDispComp.createSetupDispConpInst().timeVariableTextField
				.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getScaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getxColumnName());

		// setting the concentration column

		ScaSetupDispComp.createSetupDispConpInst().conbcentrationVariableTextField
				.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getScaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getYcolumnName());

		// since sort variables are not populated while creating a new
		// ncaMainScreen
		// we need not remove anything, so
		// setting the sort variables
		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			ScaSetupDispComp.createSetupDispConpInst().sortVariableListModel
					.add(
							i,
							readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getScaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().get(i));

		}

	}
}

