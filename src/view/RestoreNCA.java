package view;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Common.JinternalFrameFunctions;
import Common.LogEntry;
import Common.PETreeFunctions;

import view.ApplicationInfo;
import view.DisplayContents;


import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class RestoreNCA {

	
	

	public RestoreNCA(String[] pathSplits){


		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[] split = pathSplits[pathSplits.length-1].split("-");
		String wbName = split[split.length-2];
		split = split[split.length-1].split("]");
		
		
		
		
		String sheetName = split[split.length-1];
		int selectedsheetIndex = 0;
		
		
		//if it is the same NCA that we are dealing with then do not create a new object
		//just do nothing which indicates that the present one is the nca screen or we may pop up a box
		//informing the same to the user.
		//I still have to implement this logic
		
		// the below lines are to create an new NCA main screen restoring all its capabilities
		//like event listeneres
		//except that the state of this already performed analysis is persisted 
		DDViewLayer.createViewLayerInstance().isFromNca = true;
		DDViewLayer.createViewLayerInstance().isPlotMavenExecution = false;
		DDViewLayer.createViewLayerInstance().isFromPlotMavenMappingScreen = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution = true;
		LogEntry.createLogEntryInstance().logEntry("Restored NCA");
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		DDViewLayer.createMTInstance().mainTabbedFrame.setTitle("Non Compartmental Analysis Main Screen");
		
		DDViewLayer.createViewLayerInstance().isNCAExecution = true;
		DDViewLayer.createViewLayerInstance().isNcaRestore = true;
		DDViewLayer.createViewLayerInstance().isDSRestore = false;
		DDViewLayer.createViewLayerInstance().isPlotMavenRestore = false;
		DDViewLayer.createViewLayerInstance().isTableMavenRestore = false;
		DDViewLayer.createViewLayerInstance().isNPSRestore = false;
		DDViewLayer.createViewLayerInstance().isSCARestore = false;
		DDViewLayer.createViewLayerInstance().isCARestore = false;
		DDViewLayer.createViewLayerInstance().istableTransRestore = false;
		
		
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage = false;
		//DesStatSetupDespComp.createDesStatSetupDispInst().carryThisToOtherComponents = false;
		DDViewLayer.createViewLayerInstance().isBeforeNCA = false;
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
			
			
			
			//getting the selected sheet index
			for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.size(); i++) {
				if (sheetName.equals(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(i).getSheetName())) {
					selectedsheetIndex = i;
					break;
				}
			}
	
			
			//set the sheet 
			readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).setSelectedSheetIndex(selectedsheetIndex);
			
			
			createImportedDataSheet(selectedsheetIndex,readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex() );
			
			NCAMainScreen.createNcaMainScreenInstance().isncaMainScreenCreation(selectedsheetIndex);
			
			
			DisplayContents.setM_appInfo(readPerst);
			
			//set the workbook to the stored workBook
			Workbook restoringWB = null;
			/*try {
				//restoringWB = Workbook.getWorkbook(new File("ajith.xls"));
			} catch (BiffException e) {
				e.printStackTrace();
			}*/
			appInfo = DisplayContents.createAppInfoInstance();
			//appInfo.setFile1Workbook(restoringWB);
			
			
			
			DDViewLayer.createViewLayerInstance().isNcaRestore = true;
			restoringPreviousState(appInfo, selectedsheetIndex);
			NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree.setSelectionRow(0);
			DDViewLayer.createFeaturesPanelInstance().PreviewLable.doClick();
			
		}catch(IOException e1){
			
		} /*catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/ catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DDViewLayer.createViewLayerInstance().isNcaRestore = false;
		
	
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

	private void createImportedDataSheet(int selectedSheetIndex, int selectedWorkBookIndex) {

		ImportFile.createDispContentsInstance().finishAndOpenClicked = true;

		DDViewLayer.createViewLayerInstance().isFronClickOnProjectExplorer = true;
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

		ImportFile.createDispContentsInstance().finishAndOpenClicked = false;

	}


	private void setAllTheGuiScreenObjectsToNull() {
		NCAMainScreen.NCA_MAIN_SCREEN = null;
		NCAMainScreenGUICreation.NCA_MAIN_SCREEN_GUI = null;
		NcaTabbedPanes.NCA_TABBLE_DISP = null;
		NcaMappingDispGui.NCA_MAP_DISP = null;
		NcaSetupAvailableComp.NCA_AVAIL_COMP = null;
		NcaSetupDispCompGui.SETUP_DISP_COMP_GUI = null;
		NcaResultsAvailableComp.NCA_RES_AVAIL_GUI = null;
		NcaResultDispComp.NCA_RES_DISP = null;
		NcaDosingDispGui.NCA_DOSE_GUI = null;
		NcaLambdaZDispGui.NCA_LANB_DISP = null;
		NcaParameterNamesDispGui.NCA_PAR_NAMES_DISP = null;
		NcaParameterUnitsDispGui.NCA_PAR_UNITS_DISP = null;
		NcaSubAreasDispGui.NCA_SUB_GUI = null;
		NcaOptionsGui.NCA_OPT_GUI = null;
		ImportedDataSheet.importedDataSheetFrame = null;
		
	}


	private void restoringPreviousState(ApplicationInfo readPerst,
			int selectedSheetIndex) throws RowsExceededException,
			WriteException, BiffException, IOException {

		NcaParameterNamesDispGui.createParameterNamesInstance().isRestoringParameterNames = true;
		NcaParameterUnitsDispGui.createNcaParUnitsDisInst().isRestoringParameterUnits = true;

		// restoring the mapping screen state
		restoreMappingPreviousState(readPerst);

		ApplicationInfo tempAppInfo = readPerst;
		

		String data[][] = new String[tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getDosingDataObj().getDosingDSForNCA().length][tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getDosingDataObj().getDosingDSForNCA()[0].length];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getDosingDataObj()
						.getDosingValueAt(i, j);
			}
		}

		//store the lambda z object details because they get wiped 
		//when going into "perform common functionality" code.
		lambdazData ld = storeTheLambdaZObjectDetails(tempAppInfo);
		
		String[] tempDefaultunits = storingDefaultUnitsToTempArray(tempAppInfo);
		String[] tempPreferredUnits = storePreferredUnits(tempAppInfo);
		
		// restoring the state of model options
		restoremodelOptionspreviousState(readPerst);

		// there was a problem of restoring the default values
		// they are getting replaced at various action events in
		// while restoring various other component values
		// so rather than placing conditional clauses at those points
		// its advisable to take the default values to an array here and
		// then assign these values while restoring them to the parameter units
		// table.
	

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// after mapping the mapping is changed
		tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.setIfMappingScreenIsChanged(true);

		NCAMainScreen.createNcaMainScreenInstance().appInfo = appInfo;
		// after mapping is done we have to click on any one of the
		// dosing/lambdaz/sub areas so that all of them get created
		// then it would be possible to fill the values
		// the numeric 2 indicates the row for dosing screen
		TreePath path = NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
				.getPathForRow(2);
		NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
				.setSelectionPath(path);

		
		
		// Restoring the dosing options state
		restoredosingoptionsPreviousState(data, readPerst);

		//restore LambdaZ previous state
		restoreLambdaZPreviousState(readPerst, ld);
		
		// restoring the specify sub areas State
		restoreSubAreaspreviousState(readPerst);

		// restoring the parameter names state
		restoreParameterNamesState(tempAppInfo);

		// restoring the parameter units
		restoreParameterUnitsState(tempAppInfo, tempDefaultunits, tempPreferredUnits);

		// restore and populate the results
		// this is where we have to write functions to restore the results
		NcaOutputGeneration.createNcaOutGeneration().NcaOutputNumberOfplotnodes = readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(
						tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLinearPlots().size();
		NcaOutputGeneration.createNcaOutGeneration().ncaOutputGeneration();
		
		
		

	}

	
	private String[] storePreferredUnits(ApplicationInfo tempAppInfo) {
	
		String[] Prefunits = tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj().getPreferredUnitsArray();
		
		return Prefunits;
	}


	private lambdazData storeTheLambdaZObjectDetails(ApplicationInfo tempAppInfo) {
		
		lambdazData ld = (lambdazData)tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().clone();
		
		
				
		return ld;
		
	}


	private void restoreLambdaZPreviousState(ApplicationInfo readPerst, lambdazData ld) {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().setLambdazDataobj(ld);
		
		
	}


	private void setLambdaZCalcMethodSelected(int i, ApplicationInfo readPerst) {
	
		
		
	}


	private void restoredosingoptionsPreviousState(String[][] data,
			ApplicationInfo readPerst) throws RowsExceededException,
			WriteException, BiffException, IOException {

		// Its enough if I restore the values other than the sort variables
		// as they will already be populated
		String[][] localDosingData = data;
		for (int i = 0; i < localDosingData.length; i++) {
			for (int j = readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
					readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size(); j < localDosingData[i].length; j++) {
				NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.setValueAt(localDosingData[i][j], i, j);

			}

		}

		readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getDosingDataObj().setDosingDSForNCA(
						data);

	}
	

	private void restoreSubAreaspreviousState(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		// Its enough if I restore the values other than the sort variables
		// as they will already be populated
		String[][] localSubAreasData = readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getSubAreasDataObj()
				.getPartialAreaDSForNCA();
		for (int i = 0; i < localSubAreasData.length; i++) {
			for (int j = readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
					readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size() + 1; j < localSubAreasData[i].length; j++) {
				NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().tableForPartialArea
						.setValueAt(localSubAreasData[i][j], i, j);
				
			}
			
		}

	}

	private void restoreParameterNamesState(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		DisplayContents.setM_appInfo(readPerst);
		// remove the default populated rows so that the retrieved values can be
		// inserted
		for (int i = NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable
				.getRowCount() - 1; i >= 0; i--) {
			((DefaultTableModel) NcaParameterNamesDispGui
					.createParameterNamesInstance().parameterNamesTable
					.getModel()).removeRow(i);
		}
		
		HashMap<String, String[]> map = readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterNamesObj().getParameterNamesMap();

		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterNamesObj()
				.getDefaultParameterNames().size(); i++) {
			String[] s = new String[3];

			// restore the default parameter names
			s[0] = readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
					readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getParameterNamesObj()
					.getDefaultameAt(i);

			// restore the preferred parameter names
			s[1] = map.get(s[0])[0];

			// restoring the "include in output" option
			s[2] = map.get(s[0])[1];

			((DefaultTableModel) NcaParameterNamesDispGui
					.createParameterNamesInstance().parameterNamesTable
					.getModel()).insertRow(NcaParameterNamesDispGui
					.createParameterNamesInstance().parameterNamesTable
					.getRowCount(), s);

		}

		NcaParameterNamesDispGui.createParameterNamesInstance().isRestoringParameterNames = true;
}



	
	private void restoreParameterUnitsState(ApplicationInfo readPerst,
			String[] tempDefaultunits, String[] prefUnits) throws RowsExceededException,
			WriteException, BiffException, IOException {

		DisplayContents.setM_appInfo(readPerst);

		readPerst = DisplayContents.createAppInfoInstance();
		// remove the default populated rows
		for (int i = NcaParameterUnitsDispGui.createNcaParUnitsDisInst().parameterUnitsTable
				.getRowCount() - 1; i >= 0; i--) {
			((DefaultTableModel) NcaParameterUnitsDispGui
					.createNcaParUnitsDisInst().parameterUnitsTable.getModel())
					.removeRow(i);
		}
		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj()
				.getDefaultUnitArray().length; i++) {

			String[] s = new String[3];
			s[0] = readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
					readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getParameterUnitsDataObj()
					.getParameterbyGroupValueAt(i);
			s[1] = tempDefaultunits[i];

			s[2] = prefUnits[i];

			((DefaultTableModel) NcaParameterUnitsDispGui
					.createNcaParUnitsDisInst().parameterUnitsTable.getModel())
					.insertRow(NcaParameterUnitsDispGui
							.createNcaParUnitsDisInst().parameterUnitsTable
							.getRowCount(), s);

		}
		
		readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj().setDefaultUnitArray(tempDefaultunits);
		
		readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj().setPreferredUnitsArray(prefUnits);
		NcaParameterUnitsDispGui.createNcaParUnitsDisInst().isRestoringParameterUnits = true;
}

	private void restoremodelOptionspreviousState(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.setSelectedIndex(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getModelType());
		NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
				.setSelectedIndex(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getRootOfAdmistration());
		
		NcaOptionsGui.createNcaOptionsInstance().weightingOptions
				.setSelectedIndex(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getWeightingOption());
		NcaOptionsGui.createNcaOptionsInstance().titlesTextArea
				.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj().getTitle());
		NcaOptionsGui.createNcaOptionsInstance().calculationMethodOptions
				.setSelectedIndex(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getCalculationMethod());
		NcaOptionsGui.createNcaOptionsInstance().sparseCheckBox
				.setSelected(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getisSparseSelected());
	/*	NcaOptionsGui.createNcaOptionsInstance().pageBreaksCheckBox
				.setSelected(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.isPageBreakSelected());*/
/*		NCAMainScreen.createNcaMainScreenInstance().excludeProfilesCheckBox.setSelected(readPerst
				.getWorkSheetObjectsAL().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getNcaInfo().getProcessingInputs().getModelInputsObj()
				.isExcluseProfiles());*/
		NcaOptionsGui.createNcaOptionsInstance().selectionCheckBoc
				.setSelected(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.isDisableCurveStripping());
		NcaOptionsGui.createNcaOptionsInstance().exclusionCheckBox
				.setSelected(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.isClearExclusion());
/*		NCAMainScreen.createNcaMainScreenInstance().unitTextField.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getDosingDisplayUnits());*/

	}




	private String[] storingDefaultUnitsToTempArray(ApplicationInfo tempAppInfo) {

		String[] localDefaultUnits = tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL().get(tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(tempAppInfo.getProjectInfoAL().get(tempAppInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj()
				.getDefaultUnitArray();
		return localDefaultUnits;
}




	private void restoreMappingPreviousState(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		// first we remove the available columns populated from the column names
		// of imported data....this is in normal
		// proceeding to NCA but not here ...... here the case is restoring the
		// values
		// removing the values from the list displayed
		int size = NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
				.size();
		for (int i = NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
				.size() - 1; i >= 0; i--) {
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.remove(i);
		}

		// setting the available columns
		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().size(); i++) {
			NcaMappingDispGui.createMappingInstance().availableVariablesListmodel
					.add(i, readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
							.getProcessingInputs().getMappingDataObj()
							.getAvailableColumnsVector().get(i));

		}

		// since sort variables are not populated while creating a new
		// ncaMainScreen
		// we need not remove anything, so
		// setting the sort variables
		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			NcaMappingDispGui.createMappingInstance().sortVariableListModel
					.add(i, readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
							.getProcessingInputs().getMappingDataObj()
							.getSortVariablesListVector().get(i));

		}

		// setting the time column

		NcaMappingDispGui.createMappingInstance().xVariableTextField
				.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getxColumnName());

		// setting the concentration column

		NcaMappingDispGui.createMappingInstance().yVariableTextField
				.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getYcolumnName());

		// setting the end time column
		// restore this value only if the previous analysis is of model type
		// urine
		if (readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getModelType() == 1) {
			NcaMappingDispGui.createMappingInstance().endTimeVariableTextField
					.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
							.getProcessingInputs().getMappingDataObj()
							.getEndTimeColumnName());

			NcaMappingDispGui.createMappingInstance().volumeVariableTextField
					.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
							.getProcessingInputs().getMappingDataObj()
							.getVolumeColumnName());
		}

		// setting the subject column name
		// restore this value only when if it was a sparse analysis
		if (readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getisSparseSelected() == true) {

			NcaMappingDispGui.createMappingInstance().subjectTextField
					.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
							.getProcessingInputs().getMappingDataObj()
							.getSubjectColumnName());
		}
}
	
}
