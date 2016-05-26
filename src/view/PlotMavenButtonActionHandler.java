package view;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.LogEntry;
import Common.MenuToolBarEnableDisableFeatures;

public class PlotMavenButtonActionHandler {
	
	DefaultMutableTreeNode projectNode;

	public PlotMavenButtonActionHandler() {

		try {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			PlotMavenMainScreen.createPlotMavenInstance().PlotMavenMainScreen(
					appInfo.getProjectInfoAL().get(
							appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getSelectedSheetIndex());
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
		LogEntry.createLogEntryInstance().logEntry("Entered Plot Maven");

		DDViewLayer.createAAInstance().availableAnalysisFrame.setVisible(false);
		DDViewLayer.createLogFrameInstance().logFrame.setVisible(false);

		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		DDViewLayer.createFeaturesPanelInstance().plotsLable.setVisible(false);
		DDViewLayer.createMTInstance().mainTabbedFrame.setTitle("Plot Maven");
		DDViewLayer.createViewLayerInstance().isPlotMavenExecution = true;
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage = false;
		DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage = false;
		DisplayContents
				.createAppInfoInstance()
				.getProjectInfoAL()
				.get(
						DisplayContents.createAppInfoInstance()
								.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						DisplayContents.createAppInfoInstance()
								.getProjectInfoAL().get(
										DisplayContents.createAppInfoInstance()
												.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						DisplayContents
								.createAppInfoInstance()
								.getProjectInfoAL()
								.get(
										DisplayContents.createAppInfoInstance()
												.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										DisplayContents
												.createAppInfoInstance()
												.getProjectInfoAL()
												.get(
														DisplayContents
																.createAppInfoInstance()
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage = false;
		checkAndCreatePlotAnalysisNode();
		DDViewLayer.createViewLayerInstance().isTableTrans = false;
		MenuToolBarEnableDisableFeatures.createMenuToolEnabDisabInst().disableEnableMenuToolBarFeatures();
		
	}

	private DefaultMutableTreeNode getAnalysisNode() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String projName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getProjectName();
		String key = "Work Space,"+projName+","+"Plot Analysis,";
		
		DefaultMutableTreeNode analysisNode = appInfo.getpENodes().get(key);
		
		return analysisNode;
		
	}
	
	
	
	private void checkAndCreatePlotAnalysisNode() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DefaultMutableTreeNode analysisNode = null;

		Enumeration en = DDViewLayer.createPEInstance().workSpace
				.breadthFirstEnumeration();

		boolean hasPlotnalysisNode = false;
		DefaultMutableTreeNode node = null;
		
		
		projectNode = getProjNode();
		for(int i=0;i<projectNode.getChildCount();i++){
			if(((DefaultMutableTreeNode)projectNode.getChildAt(i)).getUserObject().toString().equals("Plot Analysis")){
				hasPlotnalysisNode = true;
				break;
			}
		}

		if (hasPlotnalysisNode == true) {
			analysisNode = getAnalysisNode();
			int lastChiledOfNcaAnalysis = analysisNode.getChildCount();
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"Plot-"
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
			analysisNode.add(plotNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.reload();
		} else if (hasPlotnalysisNode == false) {

			analysisNode = new DefaultMutableTreeNode("Plot Analysis");
			projectNode.add(analysisNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.insertNodeInto(analysisNode,
					projectNode, projectNode.getChildCount() - 1);
			int lastChiledOfNcaAnalysis = analysisNode.getChildCount();
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"Plot-"
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
			analysisNode.add(plotNode);
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
