package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.PathIterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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


public class RestoreDS {

	public RestoreDS(String[] pathSplits) throws RowsExceededException,
			WriteException, BiffException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[] split = pathSplits[pathSplits.length - 1].split("-");
		String wbName = split[split.length-2];
		split = split[split.length - 1].split("]");
		String sheetName = split[split.length - 1];

	
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage = true;
		DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage = false;
		DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		DDViewLayer.createViewLayerInstance().isNcaRestore = false;
		DDViewLayer.createViewLayerInstance().isDSRestore = true;
		DDViewLayer.createViewLayerInstance().isPlotMavenRestore = false;
		DDViewLayer.createViewLayerInstance().isTableMavenRestore = false;
		DDViewLayer.createViewLayerInstance().isNPSRestore = false;
		DDViewLayer.createViewLayerInstance().isSCARestore = false;
		DDViewLayer.createViewLayerInstance().istableTransRestore = false;
		

		

		LogEntry.createLogEntryInstance().logEntry(
				"Restored Descriptive Statistics");

		//try {

			/*PersistStructure persistStructureObject = new PersistStructure();
			ApplicationInfo readPerst = (ApplicationInfo) persistStructureObject
					.readApplicationInfo("outjar.jar");*/
			
			PersistStructure persistStructureObject = new PersistStructure();
			//ApplicationInfo readPerst = (ApplicationInfo)persistStructureObject.readApplicationInfo("outjar.jar");
			ApplicationInfo readPerst = (ApplicationInfo)persistStructureObject.readEncryptedFile("outjar.enc");
			
			
/*			File f = new File("AppicationInfo.txt");
			if (f.exists()) {
				f.delete();
			}*/
			DescriptiveStatMainPage.DES_STAT_MAIN_PAGE = null;
			setAllTheGuiScreenObjectsToNull();
			
			//set the selected project
			PETreeFunctions.createPETreeFuncInst().setProjIndexAndSelectedProjPath(
					PETreeFunctions.createPETreeFuncInst().getProjIndex(PETreeFunctions.createPETreeFuncInst().getProjName(pathSplits))
					, PETreeFunctions.createPETreeFuncInst().getProjName(pathSplits));
			
			//set the selected work book
			setSelectedWorkBook(wbName, readPerst);
			
			int key = 0;
			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.size(); i++) {
				if (sheetName.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(i).getSheetName())) {
					key = i;
					break;
				}
			}
			
			
			// the selected index is set to the sheet which corresponds
			// to the analysis clicked
			readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.setSelectedSheetIndex(key);
			
			createImportedDataSheet(key, readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex());
			DescriptiveStatMainPage.createDescStatMainPageInst()
					.DescriptiveStatMainPage(key);


			DisplayContents.setM_appInfo(readPerst);

			// set the workbook to the stored workBook
			Workbook restoringWB = null;
			/*
			 * try { restoringWB = Workbook.getWorkbook(new File("ajith.xls"));
			 * } catch (BiffException e) { e.printStackTrace(); }
			 */
			appInfo = DisplayContents.createAppInfoInstance();
			// appInfo.setFile1Workbook(restoringWB);
			try {
				restoringPreviousState(appInfo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			DesStatSetupAvailComp.createDesStatAvailComp().availableComponentsTree
			.setSelectionRow(0);
			DDViewLayer.createFeaturesPanelInstance().PreviewLable.doClick();
			ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
			.moveToBack();
			DesStatSetupAvailComp.createDesStatAvailComp().availableComponentsTree
			.setSelectionRow(1);
			
			
	/*	} catch (IOException e1) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/

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
		DSTabs.DS_TAB = null;
		DesStatSetupAvailComp.DES_STAT_AVAIL_COMP = null;
		DesStatsUI.DES_STAT_UI = null;
		DesStatSetupDespComp.DES_STATSETUPDISP = null;
		DesStatResAvailComp.DES_STAT_RES_AVAIL = null;
		DesStatResDispComp.DES_STAT_RES_DISP = null;
		DesStatOptComp.DES_STAT_OPT = null;
		ImportedDataSheet.importedDataSheetFrame = null;

	}

	private void restoringPreviousState(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		restoreMappingScreen(readPerst);
		// restoreResults(readPerst);
		restoreResults();
	}

	private void restoreResults() throws RowsExceededException, WriteException,
			BiffException, IOException {
		DesStatsoutputGeneration.createDesStatOutInst().desStatOutGeneration();
	}

	private void restoreMappingScreen(ApplicationInfo readPerst) {

		// first we remove the available columns populated from the column names
		// of imported data....this is in normal
		// proceeding to NCA but not here ...... here the case is restoring the
		// values
		// removing the values from the list displayed
		int size = DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
				.size();
		for (int i = DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
				.size() - 1; i >= 0; i--) {
			DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
					.remove(i);
		}

		// setting the available columns
		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().size(); i++) {
			DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
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
									.getDesStatsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().get(i));

		}

		// since sort variables are not populated while creating a new
		// ncaMainScreen
		// we need not remove anything, so
		// setting the sort variables
		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			DesStatSetupDespComp.createDesStatSetupDispInst().sortVariableListModel
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
									.getDesStatsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().get(i));

		}

		// now setting the summary variables
		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getCarryAlongVariablesListVector().size(); i++) {
			DesStatSetupDespComp.createDesStatSetupDispInst().carryAlongModel
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
									.getDesStatsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getCarryAlongVariablesListVector().get(i));

		}

	}

}
