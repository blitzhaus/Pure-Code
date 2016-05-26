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

public class RestoreTableMaven  {

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
		
		TableMavenCreateUI.m_categoryPanel = null;
		TableMavenCreateUI.m_optionsPanel = null;
		TableMavenCreateUI.m_fontStylePanel = null;
		TableMavenCreateUI.m_tmStatsPanel = null;
	
		ImportedDataSheet.importedDataSheetFrame = null;
		
	}


	private void restoringPreviousState(ApplicationInfo readPerst,
			int selectedSheetIndex) throws RowsExceededException,
			WriteException, BiffException, IOException {
		
		TableMavenCreateUI tmCreateUI = TableMavenCreateUI.createTableMavenUIInst();
		
		tmCreateUI.setRestoreMode(true);
			//tmCreateUI.tableMavenCreateUI();
			tmCreateUI.rebuildResultsTab();
		tmCreateUI.setRestoreMode(false);
		
	}


	public void restoreAnalysis(String[] pathSplits) {


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
		
		/*// the below lines are to create an new NCA main screen restoring all its capabilities
		//like event listeneres
		//except that the state of this already performed analysis is persisted 
		ViewLayer.createViewLayerInstance().isFromNca = true;
		ViewLayer.createViewLayerInstance().isPlotMavenExecution = false;
		ViewLayer.createViewLayerInstance().isFromPlotMavenMappingScreen = false;
		ViewLayer.createViewLayerInstance().isNCAExecution = true;
		LogEntry.createLogEntryInstance().logEntry("Restored NCA");
		ViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		ViewLayer.createMTInstance().mainTabbedFrame.setTitle("Non Compartmental Analysis Main Screen");*/
		
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		DDViewLayer.createViewLayerInstance().isNcaRestore = false;
		DDViewLayer.createViewLayerInstance().isDSRestore = false;
		DDViewLayer.createViewLayerInstance().isPlotMavenRestore = false;
		DDViewLayer.createViewLayerInstance().isTableMavenRestore = true;
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
			
			DisplayContents.setM_appInfo(readPerst);
			
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
			
			//TableMavenLaunchScreen.createTableMavenLauncherInst().tableMavenLaunchScreen();
			
			TableMavenCreateUI.createTableMavenUIInst().tableMavenCreateUI();
			
			//set the workbook to the stored workBook
			Workbook restoringWB = null;
			/*try {
				//restoringWB = Workbook.getWorkbook(new File("ajith.xls"));
			} catch (BiffException e) {
				e.printStackTrace();
			}*/
			appInfo = DisplayContents.createAppInfoInstance();
			//appInfo.setFile1Workbook(restoringWB);
			
			restoringPreviousState(appInfo, selectedsheetIndex);
			
			
			
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


}
