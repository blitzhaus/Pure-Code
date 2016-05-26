package view;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.w3c.dom.css.ViewCSS;

import Common.Iconizing;
import Common.MenuToolBarEnableDisableFeatures;




public class DescStatsMenuBarSelectionHandler {

	public DescStatsMenuBarSelectionHandler() {
		// ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.setFocusable(false);

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
				.getDesStatsInfo()
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

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage = true;
		DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage = false;
		DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		DDViewLayer.createFeaturesPanelInstance().plotsLable.setVisible(false);
		DDViewLayer.createMTInstance().mainTabbedFrame
				.setTitle("Descriptive Stat");
		
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		DescriptiveStatMainPage desStatMainPage = DescriptiveStatMainPage
				.createDescStatMainPageInst();
		desStatMainPage.DescriptiveStatMainPage(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getSelectedSheetIndex());

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().setIfSheetIsSelected(true);

		checkAndAddDesStatsNode();
		DescriptiveStatMainPage.createDescStatMainPageInst();
		MenuToolBarEnableDisableFeatures.createMenuToolEnabDisabInst().disableEnableMenuToolBarFeatures();
	}

	private DefaultMutableTreeNode getAnalysisNode() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String projName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getProjectName();
		String key = "Work Space,"+projName+","+"Descriptive Stat,";
		
		DefaultMutableTreeNode analysisNode = appInfo.getpENodes().get(key);
		
		return analysisNode;
		
	}
	
	private void checkAndAddDesStatsNode() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DefaultMutableTreeNode analysisNode = null;

		boolean hasNCAnalysisNode = false;
		DefaultMutableTreeNode node = null;
		
		
		DefaultMutableTreeNode projectNode = getProjNode();
		for(int i=0;i<projectNode.getChildCount();i++){
			if(((DefaultMutableTreeNode)projectNode.getChildAt(i)).getUserObject().toString().equals("Descriptive Stat")){
				hasNCAnalysisNode = true;
				break;
			}
		}
		if (hasNCAnalysisNode == true) {
			analysisNode = getAnalysisNode();

			DefaultMutableTreeNode ncaNode = new DefaultMutableTreeNode("DS-"
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
			analysisNode.add(ncaNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.reload();
		} else if (hasNCAnalysisNode == false) {
			analysisNode = new DefaultMutableTreeNode("Descriptive Stat");
			projectNode.add(analysisNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.insertNodeInto(analysisNode,
					projectNode, projectNode.getChildCount() - 1);
			int lastChiledOfNcaAnalysis = analysisNode.getChildCount();
			DefaultMutableTreeNode ncaNode = new DefaultMutableTreeNode("DS-"
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
			analysisNode.add(ncaNode);
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
	
	
	
}
