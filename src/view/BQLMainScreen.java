package view;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JInternalFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Common.Iconizing;
import Common.JinternalFrameFunctions;
import Common.MenuToolBarEnableDisableFeatures;
import Common.PETreeFunctions;

public class BQLMainScreen {

	public static BQLMainScreen BAL_MAIN_PAGE = null;

	public static BQLMainScreen createBQLMainScrInst() {
		if (BAL_MAIN_PAGE == null) {
			BAL_MAIN_PAGE = new BQLMainScreen("just object creation");
		}
		return BAL_MAIN_PAGE;
	}

	public BQLMainScreen(String dummy) {

	}

	BQLTabbedPane bqlTabbedPane;
	BQLSetupAvailComp bqlsetupAvailComp;
	BQLSetupDispComp bqlsetupDispComp;
	BQLOptComp bqlOptComp;
	BQLResAvailComp bqlResAvailComp;
	BQLResDispComp bqlResDispComp;
	JInternalFrame bqlMainScreenIF;
	DefaultMutableTreeNode projectNode;
	DefaultMutableTreeNode analysisNode;

	void createUI() {

		setColumnProperties();
		createBqlMainScreenUI();

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.setAnalysisType("bql");

		// bql tabbed pane creation
		bqlTabbedPane = BQLTabbedPane.createBqlTabInst();
		bqlTabbedPane.bqlTabbedPaneCreation();

		// bql setup available component creation
		bqlsetupAvailComp = BQLSetupAvailComp.createBqlSetAvailInst();
		bqlsetupAvailComp.bqlSetupAvailCompCreation();

		// bql setup display component creation
		bqlsetupDispComp = BQLSetupDispComp.createBQLSetupDispInst();
		bqlsetupDispComp.bqlSetupDispCompCreation();

		// bql options component creation
		bqlOptComp = BQLOptComp.createBQLOptINst();
		bqlOptComp.bqlOptCreateUI();

		// bql results available component creation
		bqlResAvailComp = BQLResAvailComp.createBQLResAvailInst();
		bqlResAvailComp.bqlResAvailUICreation();

		// bql results display component creation
		bqlResDispComp = BQLResDispComp.createBqlResDispInst();
		bqlResDispComp.bqlResDispUICreation();

		createPENode();
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
		String key = "Work Space,"+projName+","+"BQL,";
		
		analysisNode = appInfo.getpENodes().get(key);
		
		return analysisNode;
	}
	
	
	private void createPENode() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		
		boolean hasTTnalysisNode = false;
		DefaultMutableTreeNode node = null;

		projectNode = getProjNode();
		for(int i=0;i<projectNode.getChildCount();i++){
			if(((DefaultMutableTreeNode)projectNode.getChildAt(i)).getUserObject().toString().equals("BQL")){
				hasTTnalysisNode = true;
				break;
			}
		}
		

		if (hasTTnalysisNode == true) {
			analysisNode = getAnalysisNode();
			int lastChiledOfNcaAnalysis = analysisNode.getChildCount();
			DefaultMutableTreeNode ncaNode = new DefaultMutableTreeNode("BQL-"
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
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.reload();
		} else if (hasTTnalysisNode == false) {

			analysisNode = new DefaultMutableTreeNode(
					"BQL");
			projectNode.add(analysisNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.insertNodeInto(analysisNode,
					projectNode, projectNode.getChildCount() - 1);
			
			//insert this node into the project nodes
			appInfo.getpENodes().put(PETreeFunctions.createPETreeFuncInst().convertObjToStr(analysisNode), analysisNode);
	
			
			
			DefaultMutableTreeNode ncaNode = new DefaultMutableTreeNode("BQL-"
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

	private void setColumnProperties() {
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
				.getBqlInfo()
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

	private void createBqlMainScreenUI() {

		initializeVariables();
		/*
		 * if (ViewLayer.createLogFrameInstance().logFrame.isVisible() == true)
		 * { Iconizing.createIconizingInstance().iconizeLogInternalFrame();
		 * ViewLayer.createPEInstance().projectExplorerFrame .setSize(
		 * ViewLayer.createPEInstance().projectExplorerOriginalWidth,
		 * ViewLayer.createPEInstance().projectExplorerOriginalHeight +
		 * ViewLayer.createLogFrameInstance().logFrameOriginalHeight); }
		 * 
		 * if (ViewLayer.createAAInstance().availableAnalysisFrame.isVisible()
		 * == true) { Iconizing.createIconizingInstance()
		 * .iconizeAvailableAnalysisInternalFrame(); }
		 */
		bqlMainScreenIF = new JInternalFrame("bql", false, false, false, false);
		bqlMainScreenIF.setLocation(0, 0);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(bqlMainScreenIF);
		bqlMainScreenIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		bqlMainScreenIF.setSize(DDViewLayer.createMTInstance().mainTabbedFrame
				.getWidth(), DDViewLayer.createMTInstance().mainTabbedFrame
				.getHeight() / 2);
		bqlMainScreenIF.setVisible(true);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane.add(bqlMainScreenIF);
		bqlMainScreenIF.moveToFront();
	}

	private void initializeVariables() {
		// ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.setFocusable(false);
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		DDViewLayer.createFeaturesPanelInstance().executeLable.setEnabled(true);
		DDViewLayer.createViewLayerInstance().isFronClickOnProjectExplorer = false;

		DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage = false;
		DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage = false;
		DDViewLayer.createViewLayerInstance().isFromPlotMavenMappingScreen = false;
		DDViewLayer.createViewLayerInstance().isFromNca = false;
		DDViewLayer.createViewLayerInstance().isPlotMavenExecution = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		DDViewLayer.createViewLayerInstance().isToNCAFromPlotMaven = false;
		DDViewLayer.createViewLayerInstance().isFromCA = false;
		DDViewLayer.createViewLayerInstance().isCAExecution = false;
		DDViewLayer.createViewLayerInstance().isFromBQL = true;
		DDViewLayer.createViewLayerInstance().isTableTrans = false;

	}

}
