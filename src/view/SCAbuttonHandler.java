package view;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Common.Iconizing;
import Common.MenuToolBarEnableDisableFeatures;


public class SCAbuttonHandler {

	
	DefaultMutableTreeNode projectNode;
	
	public SCAbuttonHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		newColumnPropertiesArrayList();
		createSCA();
		initializeVariables();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().setIfSheetIsSelected(true);
		nodeCreationInPE();
		
		MenuToolBarEnableDisableFeatures.createMenuToolEnabDisabInst().disableEnableMenuToolBarFeatures();

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

	
	
	private DefaultMutableTreeNode getAnalysisNode() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String projName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getProjectName();
		String key = "Work Space,"+projName+","+"SemiCompartmental Analysis,";
		
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
			if(((DefaultMutableTreeNode)projectNode.getChildAt(i)).getUserObject().toString().equals("SemiCompartmental Analysis")){
				hasNCAnalysisNode = true;
				break;
			}
		}
		

		if (hasNCAnalysisNode == true) {
			analysisNode = getAnalysisNode();
			
			DefaultMutableTreeNode scaNode = new DefaultMutableTreeNode("SCA-"
					+ appInfo
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
			analysisNode.add(scaNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.reload();
		} else if (hasNCAnalysisNode == false) {

			analysisNode = new DefaultMutableTreeNode(
					"SemiCompartmental Analysis");
			projectNode.add(analysisNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.insertNodeInto(analysisNode,
					projectNode, projectNode.getChildCount() - 1);
			
			DefaultMutableTreeNode ncaNode = new DefaultMutableTreeNode("SCA-"
					+ (appInfo
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
													.getSelectedSheetIndex()).getSheetName()));
			analysisNode.add(ncaNode);

			model.reload();
		}
	}

	private void initializeVariables() {
		// ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.setFocusable(false);
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage = true;
		DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage = false;
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		DDViewLayer.createMTInstance().mainTabbedFrame
				.setTitle("SemiCompartmental Analysis");
		DDViewLayer.createViewLayerInstance().isTableTrans = false;
		/*
		 * Iconizing.createIconizingInstance().iconizeAvailableAnalysisInternalFrame
		 * (); Iconizing.createIconizingInstance().iconizeLogInternalFrame();
		 */
	}

	private void createSCA() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		SCAMainPage sca = SCAMainPage.createScainstance();
		sca.semiCompartmentalAnalysisMainPage(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getSelectedSheetIndex());

	}

	private void newColumnPropertiesArrayList() {
		
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
				.getScaInfo()
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
