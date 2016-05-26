package view;

import java.awt.Color;
import java.io.IOException;

import jxl.Workbook;
import Common.JinternalFrameFunctions;
import Common.LogEntry;
import Common.PETreeFunctions;

public class RestoreBQL {
	
	public void restoreAnalysis(String[] pathSplits) {
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
		DDViewLayer.createViewLayerInstance().isSCARestore = false;
		DDViewLayer.createViewLayerInstance().isCARestore = false;
		DDViewLayer.createViewLayerInstance().istableTransRestore = true;
		DDViewLayer.createMTInstance().mainTabbedFrame.setTitle("Table Transformations");
		
		
	
		LogEntry.createLogEntryInstance().logEntry("Restored Table Transformations");
		
		//try{

			PersistStructure persistStructureObject = new PersistStructure();
			//ApplicationInfo readPerst = (ApplicationInfo)persistStructureObject.readApplicationInfo("outjar.jar");
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
			
			BQLMainScreen.createBQLMainScrInst().createUI();
			
			
			//set the workbook to the stored workBook
			Workbook restoringWB = null;
			
			appInfo = DisplayContents.createAppInfoInstance();
		
			restoringPreviousState(appInfo);
		
		/*} catch(IOException e1){
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */
	}
private void restoringPreviousState(ApplicationInfo readPerst) {
		
		restoreMappingScreen(readPerst);
		BQLSetupAvailComp.createBqlSetAvailInst().availableComponentsTree.setSelectionRow(2);
		restoreBqlRulesTable(readPerst);
		restoreModelOptions(readPerst);
		restoreResults(readPerst);
		
	}
	private void restoreBqlRulesTable(ApplicationInfo readPerst) {
	
		String[][] data = readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getDosingDataObj().getBqlRulesTable();
		
		
		for(int i=0;i< BQLSetupDispComp.createBQLSetupDispInst().rulesTable.getRowCount();i++){
			for(int j=0;j< BQLSetupDispComp.createBQLSetupDispInst().rulesTable.getColumnCount();j++){
				BQLSetupDispComp.createBQLSetupDispInst().rulesTable.setValueAt(data[i][j], i, j);
			}
		}
		
		
		
		
		BQLSetupDispComp.createBQLSetupDispInst().lloqText.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getLloqValue()+"");
		
		if(BQLSetupDispComp.createBQLSetupDispInst().lloqText.getText().equals("")){
			
		} else{
			BQLSetupDispComp.createBQLSetupDispInst().lloqCheckBox.setSelected(true);
			BQLSetupDispComp.createBQLSetupDispInst().lloqText.setEnabled(true);
		}
		
	
}
	private void restoreResults(ApplicationInfo readPerst) {
		
		BqlOutputGeneration.createBqloutGenInst().generateOutput();
		
	}
	private void restoreModelOptions(ApplicationInfo readPerst) {
		
		
		
	}
	private void baseLineFunc(ApplicationInfo readPerst) {
		TableTransformations.createTableTrasGUIInst().baseLineRB.setSelected(true);
		TableTransformations.createTableTrasGUIInst().baseLineCombo.setSelectedIndex(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getTtInfo().getProcessingInputs().getTtSelectedBaseFuncIndex());
		TableTransformations.createTableTrasGUIInst().customTimePointTF.setText(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getTtInfo().getProcessingInputs().getTtCostomPoint()+"");
		
		
	}

	private void restoreMappingScreen(ApplicationInfo readPerst) {


		int size = BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
				.size();
		for (int i = BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
				.size() - 1; i >= 0; i--) {
			BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
					.remove(i);
		}

		// setting the available columns
		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().size(); i++) {
			BQLSetupDispComp.createBQLSetupDispInst().availableVariablesListmodel
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
									.getBqlInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().get(i));

		}

		BQLSetupDispComp.createBQLSetupDispInst().xVariableTextField
				.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getBqlInfo()
						.getProcessingInputs().getMappingDataObj()
						.getxColumnName());

		// setting the concentration column

		BQLSetupDispComp.createBQLSetupDispInst().yVariableTextField
				.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getBqlInfo()
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
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			BQLSetupDispComp.createBQLSetupDispInst().sortVariableListModel
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
									.getBqlInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().get(i));

		}
		
		BQLSetupDispComp.createBQLSetupDispInst().statusCodeTextField.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj().getSubjectColumnName());
		

	
		
		
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

		BQLMainScreen.BAL_MAIN_PAGE = null;
		
		ImportedDataSheet.importedDataSheetFrame = null;

	
		
	}

	
	

}
