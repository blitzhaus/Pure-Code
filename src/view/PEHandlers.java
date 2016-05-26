package view;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;


import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;
import Common.MenuToolBarEnableDisableFeatures;
import Common.PETreeFunctions;
import Common.SerializeTestCases;
import Common.WorkBookManipulation;

public class PEHandlers {

	public static PEHandlers PE_HANDLER = null;

	public static PEHandlers createPEHanderInst() {
		if (PE_HANDLER == null) {
			PE_HANDLER = new PEHandlers("just object creation");
		}
		return PE_HANDLER;

	}

	public PEHandlers(String dummy) {

	}
	
	public ImportedDataSheet impDataSheetInst = ImportedDataSheet.createImportedDataSheetInstance();

	public void projectExplorerSelctionHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		if (DDViewLayer.createPEInstance().tree1.getSelectionPath() != null) {

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			TreePath path = DDViewLayer.createPEInstance().tree1
					.getSelectionPath();
			
			String[] pathSplits = path.toString().split(",");
			
			String projName = PETreeFunctions.createPETreeFuncInst().getProjName(pathSplits);
			
			int projIndex = PETreeFunctions.createPETreeFuncInst().getProjIndex(projName);
			
			PETreeFunctions.createPETreeFuncInst().setProjIndexAndSelectedProjPath(projIndex, projName);
			
			if(pathSplits[pathSplits.length - 2].equals("[Work Space")){
				storePresentAnalysis();
				setProjectIndex(pathSplits);
				
			} else if (pathSplits[pathSplits.length - 2].equals(" NC Analysis")) {
				storePresentAnalysis();
				restoreNCAnalysis(pathSplits);
			} else if (pathSplits[pathSplits.length - 2]
					.equals(" Descriptive Stat") && (pathSplits[pathSplits.length - 3].equals(" Data") == false)) {
				storePresentAnalysis();
				restoreDesStats(pathSplits);
			} else if (pathSplits[pathSplits.length - 2]
					.equals(" NonParametric Superposition")&& (pathSplits[pathSplits.length - 3].equals(" Data") == false)) {
				storePresentAnalysis();
				restoreNPS(pathSplits);
			} else if (pathSplits[pathSplits.length - 2]
					.equals(" SemiCompartmental Analysis")&& (pathSplits[pathSplits.length - 3].equals(" Data") == false)) {
				storePresentAnalysis();
				restoreSCA(pathSplits);
			} else if (pathSplits[pathSplits.length - 2]
					.equals(" Plot Analysis")&& (pathSplits[pathSplits.length - 3].equals(" Data") == false)) {
				storePresentAnalysis();
				restorePlotMaven(pathSplits);
			} else if(pathSplits[pathSplits.length - 2]
			 					.equals(" Table Transformations")&& (pathSplits[pathSplits.length - 3].equals(" Data") == false)){
				
				storePresentAnalysis();
				restoreTT(pathSplits);
			} else if(pathSplits[pathSplits.length - 2]
			 					.equals(" BQL")&& (pathSplits[pathSplits.length - 3].equals(" Data") == false)){
				storePresentAnalysis();
				restoreBQL(pathSplits);
			}
			else if(pathSplits[pathSplits.length - 2]
			 					.equals(" TableMaven")&& (pathSplits[pathSplits.length - 3].equals(" Data") == false)){
				storePresentAnalysis();
				restoreTableMaven(pathSplits);
			}
			
			
			
			else if (pathSplits[pathSplits.length - 2]
					.equals(" Pharmacokinetic Analysis")&& (pathSplits[pathSplits.length - 3].equals(" Data") == false)) {
				storePresentAnalysis();
				restoreCAnalysis(pathSplits);
			} else if(pathSplits[pathSplits.length-2].equals(" PD Analysis") && (pathSplits[pathSplits.length - 3].equals(" Data") == false)){
				restorePdAnalysis(pathSplits);
			} else if(pathSplits[pathSplits.length-2].equals(" MM Analysis") && (pathSplits[pathSplits.length - 3].equals(" Data") == false)){
				restoreMmAnalysis(pathSplits);
			} else if(pathSplits[pathSplits.length-2].equals(" PKPDLINK Analysis") && (pathSplits[pathSplits.length - 3].equals(" Data") == false)){
				restorePkPdLinkAnalysis(pathSplits);
			}  else if(pathSplits[pathSplits.length-2].equals(" IR Analysis")&& (pathSplits[pathSplits.length - 3].equals(" Data") == false)){
				restoreIrmAnalysis(pathSplits);
			}  else if (pathSplits[pathSplits.length - 3].equals(" Data")) {

				// Now we will display the imported sheet along with options
				// and available analysis screen so it is like before NCA. Hence
				// we have to set the relative parameter. we will set it false
				// if
				// the user goes back to the previous analysis by double
				// clicking on
				// the nca node under the analysis parent node in the project
				// explorer.

				storePresentAnalysis();
				DDViewLayer.createViewLayerInstance().isDSRestore = false;
				DDViewLayer.createViewLayerInstance().isCARestore = false;
				DDViewLayer.createViewLayerInstance().isNcaRestore = false;
				DDViewLayer.createViewLayerInstance().isPlotMavenRestore = false;
				DDViewLayer.createViewLayerInstance().isNcaRestore = false;
				DDViewLayer.createViewLayerInstance().isTableMavenRestore = false;
				DDViewLayer.createViewLayerInstance().isNPSRestore = false;
				DDViewLayer.createViewLayerInstance().isSCARestore = false;

				DDViewLayer.createViewLayerInstance().isBeforeNCA = true;
				DDViewLayer.createViewLayerInstance().isBeforeCA = true;
				DDViewLayer.createViewLayerInstance().isFromCA = false;
				DDViewLayer.createViewLayerInstance().isNCAExecution = false;
				DDViewLayer.createViewLayerInstance().isCAExecution = false;
				DDViewLayer.createViewLayerInstance().isFromBQL = false;
				DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage = false;
				DDViewLayer.createViewLayerInstance().isFromPlotMavenMappingScreen = false;
				DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage = false;
				DDViewLayer.createViewLayerInstance().isPKPDLinkRestore = false;
				DDViewLayer.createViewLayerInstance().isFromTableMaven = false;
				DDViewLayer.createViewLayerInstance().isimportedDataTable = true;
				DDViewLayer.createViewLayerInstance().isTableTrans = false;
				
				DDViewLayer.createViewLayerInstance().isFronClickOnProjectExplorer = true;
				Object selectedSheetNode = path.getLastPathComponent();

				appInfo = DisplayContents
						.createAppInfoInstance();

				// set the selected work book
				setSelectedWorkBook(pathSplits);

				// setting the selected work sheet to application Info
				setSelectedSheetIndex(selectedSheetNode);

				setDSToOriginalData();

				ImportFile.createDispContentsInstance().finishAndOpenClicked = true;
				DDViewLayer.createFeaturesPanelInstance().plotsLable
						.setVisible(true);
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.removeAll();
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.dispose();
				DDViewLayer.createMTInstance().mainTabbedDesktopPane
						.remove(ImportedDataSheet.importedDataSheetFrame);
				ImportedDataSheet.importedDataSheetFrame = new ImportedDataSheet(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex());
				ImportedDataSheet.createImportedDataSheetInstance()
						.createImpDataSheetFrame();
				ImportedDataSheet.importedDataSheetFrame
						.setTitle("Imported Data main screen");
				// mainDisplayFrame.setTitle("Imported Data");
				ImportedDataSheet.importedDataSheetFrame.setLocation(0, 0);
				ImportedDataSheet.importedDataSheetFrame.setBorder(DDViewLayer
						.createViewLayerInstance().b);
				ImportedDataSheet.importedDataSheetFrame
						.setBackground(Color.white);
				JinternalFrameFunctions.createIFFunctionsInstance()
						.removeTitleBarForinternalFrame(
								ImportedDataSheet.importedDataSheetFrame);
				DDViewLayer.createMTInstance().mainTabbedFrame
						.setSize(
								DDViewLayer.createMTInstance().mainTabbedFrameOriginalWidth,
								DDViewLayer.createMTInstance().mainTabbedFrameoriginalHeight);
				ImportedDataSheet.importedDataSheetFrame
						.setSize(
								DDViewLayer.createMTInstance().mainTabbedFrameOriginalWidth,
								DDViewLayer.createMTInstance().mainTabbedFrameoriginalHeight);
				ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame
						.setSize(ImportedDataSheet.importedDataSheetFrame
								.getWidth(),
								ImportedDataSheet.importedDataSheetFrame
										.getHeight() / 2);
				ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame
						.setLocation(
								0,
								ImportedDataSheet
										.createImportedDataSheetInstance().dataDisplayFrame
										.getHeight());
				ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame
						.setSize(
								ImportedDataSheet
										.createImportedDataSheetInstance().dataDisplayFrame
										.getWidth() / 4,
								ImportedDataSheet
										.createImportedDataSheetInstance().dataDisplayFrame
										.getHeight());
				ImportedDataSheet.createImportedDataSheetInstance().columnPropertiesInternalFrame
						.setLocation(
								ImportedDataSheet
										.createImportedDataSheetInstance().columnsAvailableInternalFrame
										.getWidth()
										+ ImportedDataSheet
												.createImportedDataSheetInstance().columnsAvailableInternalFrame
												.getX(),
								ImportedDataSheet
										.createImportedDataSheetInstance().columnsAvailableInternalFrame
										.getY());
				ImportedDataSheet.createImportedDataSheetInstance().columnPropertiesInternalFrame
						.setSize(
								ImportedDataSheet
										.createImportedDataSheetInstance().dataDisplayFrame
										.getWidth()
										- ImportedDataSheet
												.createImportedDataSheetInstance().columnsAvailableInternalFrame
												.getWidth(),
								ImportedDataSheet
										.createImportedDataSheetInstance().columnsAvailableInternalFrame
										.getHeight());
				ImportedDataSheet.importedDataSheetFrame.setVisible(true);
				DDViewLayer.createLogFrameInstance().logFrame.setVisible(true);
				DDViewLayer.createAAInstance().availableAnalysisFrame
						.setVisible(true);
				DDViewLayer.createMTInstance().mainTabbedDesktopPane
						.add(ImportedDataSheet.importedDataSheetFrame);
				// fix(mainTabbedDesktopPane);
				ImportedDataSheet.importedDataSheetFrame.moveToFront();
				DDViewLayer.createMTInstance().mainTabbedFrame.setTitle(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getSheetName());

				ImportFile.createDispContentsInstance().finishAndOpenClicked = false;
				MenuToolBarEnableDisableFeatures.createMenuToolEnabDisabInst().eableMenuToolBarFeatures();
				
				//SerializeTestCases.createSerTestCasesInst().serializeAnalysis();
				

			}

		}

	}





	private void setProjectIndex(String[] pathSplits) {
		//set the selected project
		PETreeFunctions.createPETreeFuncInst().setProjIndexAndSelectedProjPath(
				PETreeFunctions.createPETreeFuncInst().getProjIndex(PETreeFunctions.createPETreeFuncInst().getProjName(pathSplits))
				, PETreeFunctions.createPETreeFuncInst().getProjName(pathSplits));
		
		
	}

	
	RestoreBQL resBql;
	private void restoreBQL(String[] pathSplits) {
		if (pathSplits[pathSplits.length - 1].startsWith(" BQL")) {
			DDViewLayer.createViewLayerInstance().isBqlRestore = true;
			resBql = new RestoreBQL();
			resBql.restoreAnalysis(pathSplits);
		}
		
	}
	
	RestoreTT resTT;
	private void restoreTT(String[] pathSplits) {


		if (pathSplits[pathSplits.length - 1].startsWith(" TT")) {
			DDViewLayer.createViewLayerInstance().isBqlRestore = true;
			resTT = new RestoreTT(); 
			resTT.restoreAnalysis(pathSplits);
		}
	}
	
	
	RestoreTableMaven resTM;
	private void restoreTableMaven(String[] pathSplits) {
		// TODO Auto-generated method stub
		
		if (pathSplits[pathSplits.length - 1].startsWith(" TM")) {
			DDViewLayer.createViewLayerInstance().isTMRestore = true;
			resTM = new RestoreTableMaven();
			resTM.restoreAnalysis(pathSplits);
		}
		
	}

	ApplicationInfo appInfo;
	void setSelectedWorkBook(String[] pathSplits) {
		 appInfo = DisplayContents.createAppInfoInstance();
		int selectedWorkBookIndex = 0;
		String parentNode = pathSplits[pathSplits.length - 2];
		parentNode = parentNode.substring(1, parentNode.length());
		
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().size(); i++) {

			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(i).getWorkBookName()
					.equals(parentNode)) {
				selectedWorkBookIndex = i;
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).setSelectedWorkBookIndex(selectedWorkBookIndex);
				break;
			}
		}

		

	}

	
	private void setSelectedSheetIndex(Object selectedSheetNode) {
		 appInfo = DisplayContents.createAppInfoInstance();

		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.size(); i++) {
			if (selectedSheetNode.toString().equals(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(i).getSheetName())) {
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.setSelectedSheetIndex(i);
				ImportedDataSheet.createImportedDataSheetInstance().sheetIndex = i;
				break;
			}

		}}

	private void setDSToOriginalData() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String[][] tempData = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getDataStructuresArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getDataStructuresArrayList().size() - 1);
		
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getCopyOfOriginalDataStructures().add(tempData);
		

	}
	
	RestoringAnalysis rs;

	private void restoreCAnalysis(String[] pathSplits) throws RowsExceededException, WriteException, BiffException {

		if (pathSplits[pathSplits.length - 1].startsWith(" PK")) {
			DDViewLayer.createViewLayerInstance().isCARestore = true;
			rs = new RestoringAnalysis();
			/*rs.restoreCaAnalysis(pathSplits, ViewLayer
					.createViewLayerInstance());*/
			
			rs.restorePkAnalysis(pathSplits);

		}


	}

	private void restorePdAnalysis(String[] pathSplits) {

		if(pathSplits[pathSplits.length - 1].startsWith(" PD")){
			DDViewLayer.createViewLayerInstance().isCARestore = true;
				RestoringAnalysis rs = new RestoringAnalysis();
				rs.restorePdAnalysis(pathSplits);
				
		}
		
	}
	
	private void restoreMmAnalysis(String[] pathSplits) {

		if(pathSplits[pathSplits.length - 1].startsWith(" MM")){
			DDViewLayer.createViewLayerInstance().isCARestore = true;
				RestoringAnalysis rs = new RestoringAnalysis();
				rs.restoreMmAnalysis(pathSplits);
				
		}
		
	}
	
	private void restorePkPdLinkAnalysis(String[] pathSplits) {

		if(pathSplits[pathSplits.length - 1].startsWith(" PKPDLINK")){
			DDViewLayer.createViewLayerInstance().isCARestore = true;
				RestoringAnalysis rs = new RestoringAnalysis();
				rs.restorePkPdLinkAnalysis(pathSplits);
				
		}
		
	}
	private void restoreIrmAnalysis(String[] pathSplits) {

		if(pathSplits[pathSplits.length - 1].startsWith(" IR")){
			DDViewLayer.createViewLayerInstance().isCARestore = true;
				RestoringAnalysis rs = new RestoringAnalysis();
				rs.restoreIrmAnalysis(pathSplits);
				
		}
		
	}
	
	private void restorePlotMaven(String[] pathSplits) {

		if (pathSplits[pathSplits.length - 1].startsWith(" Plot")) {
			DDViewLayer.createViewLayerInstance().isPlotMavenRestore = true;
			RestoringAnalysis rs = new RestoringAnalysis();
			rs.restorePlotAnalysis(pathSplits);
		}
	}

	private void restoreSCA(String[] pathSplits) throws RowsExceededException,
			WriteException, BiffException {

		if (pathSplits[pathSplits.length - 1].startsWith(" SCA")) {
			DDViewLayer.createViewLayerInstance().isSCARestore = true;
			RestoringAnalysis rs = new RestoringAnalysis();
			rs.restoreSCAAnalysis(pathSplits);
		}
	}

	private void restoreNPS(String[] pathSplits) throws RowsExceededException,
			WriteException, BiffException {
		if (pathSplits[pathSplits.length - 1].startsWith(" NPS")) {
			DDViewLayer.createViewLayerInstance().isNPSRestore = true;
			RestoringAnalysis rs = new RestoringAnalysis();
			rs.restoreNPSAnalysis(pathSplits);
		}
	}

	private void restoreDesStats(String[] pathSplits)
			throws RowsExceededException, WriteException, BiffException {

		if (pathSplits[pathSplits.length - 1].startsWith(" DS")) {
			DDViewLayer.createViewLayerInstance().isDSRestore = true;
			RestoringAnalysis rs = new RestoringAnalysis();
			rs.restoreDSAnalysis(pathSplits);
		}

	}

	private void restoreNCAnalysis(String[] pathSplits) {

		if (pathSplits[pathSplits.length - 1].startsWith(" NCA")) {
			DDViewLayer.createViewLayerInstance().isNcaRestore = true;
			RestoringAnalysis rs = new RestoringAnalysis();
			rs.restoreNcaAnalysis(pathSplits);

		}

	}

	PersistStructure persistStructureObject;
	void storePresentAnalysis() {

		// I think we need not store any thing manually as it is already done in
		// the respective action events

		try {
			// so directly we store the appInfo without calling the store
			// function
			persistStructureObject = new PersistStructure();
			persistStructureObject.writeMyObject(DisplayContents
					.createAppInfoInstance(), "sessions/store.ser", "sessions");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
