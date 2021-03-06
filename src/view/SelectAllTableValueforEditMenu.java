package view;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JTable;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;
import Common.LogEntry;




public class SelectAllTableValueforEditMenu {

	public static SelectAllTableValueforEditMenu SelectAllTableValue = null;
	public static SelectAllTableValueforEditMenu createSelectAllTableValueInstance(){
		if(SelectAllTableValue == null){
			SelectAllTableValue = new SelectAllTableValueforEditMenu();
		}
		return SelectAllTableValue;
	}
	
	void SelectAllTableValueForEdit() throws RowsExceededException, WriteException, BiffException, IOException{
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if((DDViewLayer.createViewLayerInstance().isNCAExecution == false)&&
				(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)&&
				(DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)&&
				(DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage== false)&&
				(DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage ==false)){
			if(DDViewLayer.createFeaturesPanelInstance().PreviewLable.isVisible() == true){
				performSelectAllTableValueImportDataTable();
			}	
		}else
		
		if((DDViewLayer.createViewLayerInstance().isNCAExecution == false)&&
				(DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage== true)&&
				(DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage== false)&&
				(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)){
			if(DDViewLayer.createFeaturesPanelInstance().PreviewLable.isVisible() == true){
				performSelectAllTableValueImportDataTable();
			}	
		}else			
		
		
		if((DDViewLayer.createViewLayerInstance().isNCAExecution == false)&&
				(DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == true)&&
				(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)&&
				(DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage ==false)){
			if(DDViewLayer.createFeaturesPanelInstance().PreviewLable.isVisible() == true){
				performSelectAllTableValueImportDataTable();
			}
		}

		//to execute the nca code but not the plot related code
		if((DDViewLayer.createViewLayerInstance().isNCAExecution == true)&& 
				(DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)){
			
			if(DDViewLayer.createFeaturesPanelInstance().PreviewLable.isVisible() == true){
				performSelectAllTableValueImportDataTable();
			}
			else{
			performSelectAllTableValueNCATable();}
			
		}else if((DDViewLayer.createViewLayerInstance().isNCAExecution == false)&&
				(DDViewLayer.createViewLayerInstance().isPlotMavenExecution == true)){
			if(DDViewLayer.createFeaturesPanelInstance().PreviewLable.isVisible() == true){
				performSelectAllTableValueImportDataTable();
			}	
		}
		else if((DDViewLayer.createViewLayerInstance().isNCAExecution == false)&&
				(DDViewLayer.createViewLayerInstance().isCAExecution == true)){
			if(DDViewLayer.createFeaturesPanelInstance().PreviewLable.isVisible() == true){
				performSelectAllTableValueImportDataTable();
			}
			else{
			performSelectAllTableValueCATable();}
		} else if((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == true)){
			
			if(DDViewLayer.createFeaturesPanelInstance().PreviewLable.isVisible() == true){
				performSelectAllTableValueImportDataTable();
			}
			
			
		} else if((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == false)
				&&(DDViewLayer.createViewLayerInstance().isFromTableMaven == true)){
			if(DDViewLayer.createFeaturesPanelInstance().PreviewLable.isVisible() == true){
				performSelectAllTableValueImportDataTable();
			}
			
		}
		
		
		else if(DDViewLayer.createViewLayerInstance().isDisplayContentsTable() == true){
			//performSelectAllTableValueDisplayDataTable();
			//ImportFile.createDispContentsInstance().dataTable.getSelectedColumn();
		}
		else if(ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getSelectedColumn()>0 && ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.isFocusable()){
			performSelectAllTableValueImportDataTable();
			//ImportFile.createDispContentsInstance().dataTable.getSelectedColumn();
		}
	}
	
	private void performSelectAllTableValueImportDataTable() {
		 JTable tab = new JTable();
			
			
			//if (ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getSelectedColumn()>0 && ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.isFocusable())
				tab=ImportedDataSheet.createImportedDataSheetInstance().importedDataTable;
			
			
			ReorderableJList inst = new ReorderableJList(tab);
			inst.SelectAllValues();
	}
	
	private void performSelectAllTableValueDisplayDataTable() throws RowsExceededException, WriteException, BiffException, IOException {
		JTable tab = new JTable();
		
		
		if (ImportFile.createDispContentsInstance().dataTable.getSelectedColumn()>0 && ImportFile.createDispContentsInstance().dataTable.isFocusable())
			tab=ImportFile.createDispContentsInstance().dataTable;
		
		
		ReorderableJList inst = new ReorderableJList(tab);
		inst.SelectAllValues();
		}
	
	
	private void performSelectAllTableValueCATable() throws RowsExceededException, WriteException, BiffException, IOException {
	JTable tab = new JTable();
   
	
	
	if (CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing.getSelectedColumnCount()>0 && CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing.isFocusable())
		tab = CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing;
	else if (CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA.getSelectedColumnCount()>0&& CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA.isFocusable())
		tab = CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA;
	
	else if(CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().tableForLinkInitialParameterValueForCA.getSelectedColumnCount() > 0 && 
			CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().tableForLinkInitialParameterValueForCA.isFocusable())
		tab = CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().tableForLinkInitialParameterValueForCA;
	
	ReorderableJList inst = new ReorderableJList(tab);
	inst.SelectAllValues();
	}
	
	private void performSelectAllTableValueNCATable() throws RowsExceededException, WriteException, BiffException, IOException {
		JTable tab = new JTable();
		NcaDosingDispGui NcaDosingDispGuiInst =NcaDosingDispGui.createNcaDosingGuiInst();
		NcaSubAreasDispGui	NcaSubAreasDispGuiInst= NcaSubAreasDispGui.createNcaSubAreasDispGuiInst();
		NcaParameterNamesDispGui	NcaParameterNamesDispGuiInst= NcaParameterNamesDispGui.createParameterNamesInstance();
		
		if (NcaDosingDispGuiInst.tableForDosing.getSelectedColumnCount()>0 && NcaDosingDispGuiInst.tableForDosing.isFocusable())
			tab=NcaDosingDispGuiInst.tableForDosing;
		else if (NcaSubAreasDispGuiInst.tableForPartialArea.getSelectedColumnCount()>0&& NcaSubAreasDispGuiInst.tableForPartialArea.isFocusable())
			tab=NcaSubAreasDispGuiInst.tableForPartialArea;
		else if (NcaParameterNamesDispGuiInst.parameterNamesTable.getSelectedColumnCount()>0&& NcaParameterNamesDispGuiInst.parameterNamesTable.isFocusable())
			tab=NcaParameterNamesDispGuiInst.parameterNamesTable;
		
		ReorderableJList inst = new ReorderableJList(tab);
		inst.SelectAllValues();
		}
}
