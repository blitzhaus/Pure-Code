package view;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Common.Iconizing;
import Common.MenuToolBarEnableDisableFeatures;
import Common.PETreeFunctions;

public class NPSButtonHandler {

	
	DefaultMutableTreeNode projectNode;
	
	public NPSButtonHandler() {

		newColumnPropertiesAL();
		setAllTheGuiScreenObjectsToNull();
		createNPS();
		initializeVar();
		nodeCreationInPE();
		MenuToolBarEnableDisableFeatures.createMenuToolEnabDisabInst().disableEnableMenuToolBarFeatures();
		

	}

	private void setAllTheGuiScreenObjectsToNull() {

		NPSMainPage.NPS_MAIN_PAGE = null;
		NPSTabs.NPS_TABS = null;
		NpsAdministeredDoseTableCreation.NPS_ADMIN_DOSE_GUI = null;
		NpsTermialPhaseTableCreation.NPS_TERMINAl_PHASE_GUI = null;
		NPSDosingTableCreation.NPS_DOSE_GUI = null;
		NPSSetupAvailComp.NPS_SETUP_AVAILCOMP = null;
		NPSSetupDispComp.NPS_SETUP_DISP_COMP = null;
		NPSResultsAvailComp.NPS_RES_AVAIL = null;
		NPSResultsDispComp.NPS_RES_DISP_COMP = null;
		NPSOpt.NPS_OPT = null;
		ImportedDataSheet.importedDataSheetFrame = null;

	}

	private DefaultMutableTreeNode getAnalysisNode() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String projName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getProjectName();
		String key = "Work Space,"+projName+","+"NonParametric Superposition,";
		
		DefaultMutableTreeNode analysisNode = appInfo.getpENodes().get(key);
		
		return analysisNode;
		
	}
	
	private void nodeCreationInPE() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DefaultMutableTreeNode analysisNode = null;
		Enumeration en = DDViewLayer.createPEInstance().workSpace
				.breadthFirstEnumeration();

		boolean hasNCAnalysisNode = false;
		DefaultMutableTreeNode node = null;
		
		projectNode = getProjNode();
		for(int i=0;i<projectNode.getChildCount();i++){
			if(((DefaultMutableTreeNode)projectNode.getChildAt(i)).getUserObject().toString().equals("NonParametric Superposition")){
				hasNCAnalysisNode = true;
				break;
			}
		}

		if (hasNCAnalysisNode == true) {
			analysisNode = getAnalysisNode();
			
			DefaultMutableTreeNode npsNode = new DefaultMutableTreeNode("NPS-"+appInfo
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
											.getSelectedSheetIndex()).getSheetName());
					
			analysisNode.add(npsNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.reload();
		} else if (hasNCAnalysisNode == false) {

			analysisNode = new DefaultMutableTreeNode(
					"NonParametric Superposition");
			projectNode.add(analysisNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.insertNodeInto(analysisNode,
					projectNode, projectNode.getChildCount() - 1);
			
			//insert this node into the project nodes
			appInfo.getpENodes().put(PETreeFunctions.createPETreeFuncInst().convertObjToStr(analysisNode), analysisNode);
	
			DefaultMutableTreeNode npsNode = new DefaultMutableTreeNode("NPS-"+appInfo
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
											.getSelectedSheetIndex()).getSheetName());
			analysisNode.add(npsNode);

			model.reload();
		}

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
	
	private void initializeVar() {
		// ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.setFocusable(false);
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage = true;
		DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage = false;
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		DDViewLayer.createViewLayerInstance().isTableTrans = false;
		DDViewLayer.createMTInstance().mainTabbedFrame
				.setTitle("NonParametric Superposition");
		/*
		 * Iconizing.createIconizingInstance()
		 * .iconizeAvailableAnalysisInternalFrame();
		 * Iconizing.createIconizingInstance().iconizeLogInternalFrame();
		 * ViewLayer.createPEInstance().projectExplorerFrame.setSize(ViewLayer.
		 * createPEInstance().projectExplorerFrame.getWidth(),
		 * ViewLayer.createPEInstance
		 * ().projectExplorerFrame.getHeight()+ViewLayer
		 * .createLogFrameInstance().logFrameOriginalHeight);
		 */
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().setIfSheetIsSelected(true);

	}

	private void createNPS() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NPSMainPage npsMainPage = NPSMainPage.createNPSMainPageInst();
		npsMainPage.nPSMainPage(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex());

	}

	private void newColumnPropertiesAL() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// setting the temporary column properties array list to the nca related
		// code.
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getNpsInfo()
				.setColumnPropertiesArrayList(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getColumnPropertiesArrayList());

	}
}
