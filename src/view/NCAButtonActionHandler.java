package view;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import view.NCAMainScreen;
import view.DDViewLayer;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.Iconizing;
import Common.LogEntry;
import Common.MenuToolBarEnableDisableFeatures;
import Common.PETreeFunctions;

public class NCAButtonActionHandler {

	Enumeration en;
	boolean hasNCAnalysisNode;
	DefaultMutableTreeNode node;
	DefaultMutableTreeNode analysisNode;
	DefaultMutableTreeNode projectNode;

	public NCAButtonActionHandler() {
		// ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.setFocusable(false);
		setColumnPropertiesToNCA();
		setFlags();
		analysisNode = null;
		en = DDViewLayer.createPEInstance().workSpace.breadthFirstEnumeration();
		hasNCAnalysisNode = false;
		node = null;
		setColumnPropertiesToNCA();
		LogEntry.createLogEntryInstance().logEntry("Entered NCA");
		projectExplorerNodeCreation();
		setAllTheGuiScreenObjectsToNull();
		createNCAAnalysisScreen();
		
		MenuToolBarEnableDisableFeatures.createMenuToolEnabDisabInst().disableEnableMenuToolBarFeatures();
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

	}

	private void createNCAAnalysisScreen() {
		try {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			DDViewLayer.createViewLayerInstance().ncaAnalysisObject = NCAMainScreen
					.createNcaMainScreenInstance();
			int selectedSheetIndex = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getSelectedSheetIndex();
			NCAMainScreen.createNcaMainScreenInstance().isncaMainScreenCreation(
					selectedSheetIndex);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void projectExplorerNodeCreation() {
		checkIfNCAAnalysisNodePresent();
		performNodeCreationInPE();
	}

	private void performNodeCreationInPE() {
		// the analysis node is present we just have to add another child to it.
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String newWbSheetName = appInfo
		.getProjectInfoAL()
		.get(appInfo.getSelectedProjectIndex())
		.getWorkBooksArrayList()
		.get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getWorkBookName()+"-" + appInfo
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
								.getSelectedSheetIndex()).getSheetName();
		
		if (hasNCAnalysisNode == true) {
			getAnalysisNode();

			 	

			for (int i = 0; i < analysisNode.getChildCount(); i++) {
				if (analysisNode.getChildAt(i).toString()
						.contains(newWbSheetName)) {
					// the analysis is already existing and new node is not
					// created
				} else if (i == analysisNode.getChildCount() - 1) {

					int lastChiledOfNcaAnalysis = analysisNode.getChildCount();
					DefaultMutableTreeNode ncaNode = new DefaultMutableTreeNode(
							"NCA-"+(newWbSheetName));
					analysisNode.add(ncaNode);
				}
			}

			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.reload();
		} else if (hasNCAnalysisNode == false) { // the analysis node is not
													// present so we have to
													// create the analysis
			// node and then create a child of it.

			analysisNode = new DefaultMutableTreeNode("NC Analysis");
			projectNode.add(analysisNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.insertNodeInto(analysisNode,
					projectNode, projectNode.getChildCount()-1);
			
			//insert this node into the project nodes
			appInfo.getpENodes().put(PETreeFunctions.createPETreeFuncInst().convertObjToStr(analysisNode), analysisNode);
			
			DefaultMutableTreeNode ncaNode = new DefaultMutableTreeNode(
					"NCA-"+(newWbSheetName));
			analysisNode.add(ncaNode);

			model.reload();
		}

	}

	private void getAnalysisNode() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String projName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getProjectName();
		String key = "Work Space,"+projName+","+"NC Analysis,";
		
		analysisNode = appInfo.getpENodes().get(key);
		
	}

	private boolean checkIfNCAAnalysisNodePresent() {

		// iterate through the enumeration
/*		while (en.hasMoreElements()) {
			// get the node
			node = (DefaultMutableTreeNode) en.nextElement();

			// match the string with the user-object of the node
			if ((node.getUserObject().toString()).equals("NC Analysis")) {
				hasNCAnalysisNode = true;
				break;
			} else {

			}
		}*/
		
		projectNode = getProjNode();
		for(int i=0;i<projectNode.getChildCount();i++){
			if(((DefaultMutableTreeNode)projectNode.getChildAt(i)).getUserObject().toString().equals("NC Analysis")){
				hasNCAnalysisNode = true;
				break;
			}
		}
		
		
		
		

		return hasNCAnalysisNode;
	}

	private DefaultMutableTreeNode getProjNode() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Iterator<Entry<String, DefaultMutableTreeNode>> it = appInfo.getpENodes().entrySet().iterator();
		
		while(it.hasNext()){
			
			Map.Entry pairs = (Map.Entry)it.next();
			if(pairs.getKey().equals(appInfo.getSelectedprojectPath())){
				return (DefaultMutableTreeNode) pairs.getValue(); 
			}
		}
		
		return null;
	}

	void setColumnPropertiesToNCA() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// setting the temporary column properties array list to the nca related
		// code.
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
				.getNcaInfo()
				.setColumnPropertiesArrayList(
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
								.getColumnPropertiesArrayList());

	}

	void setFlags() {
		DDViewLayer.createViewLayerInstance().isFromNca = true;
		DDViewLayer.createViewLayerInstance().isPlotMavenExecution = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution = true;
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		DDViewLayer.createMTInstance().mainTabbedFrame
				.setTitle("Non Compartmental Analysis Main Screen");

		DDViewLayer.createViewLayerInstance().isBeforeNCA = false;
	}

	void enlargeTheScreen() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if ((appInfo
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
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
				|| (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == true)
				|| (DDViewLayer.createViewLayerInstance().isFromPlotMavenMappingScreen == true)
				|| (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == true)) {
			Iconizing.createIconizingInstance()
					.iconizeAvailableAnalysisInternalFrame();
		} else {
			Iconizing.createIconizingInstance()
					.iconizeAvailableAnalysisInternalFrame();
			Iconizing.createIconizingInstance().iconizeLogInternalFrame();
		}
	}
}
