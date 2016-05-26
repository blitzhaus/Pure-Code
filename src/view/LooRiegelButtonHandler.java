package view;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.Iconizing;
import Common.PETreeFunctions;

public class LooRiegelButtonHandler {
	


	DefaultMutableTreeNode projectNode;
	DefaultMutableTreeNode analysisNode = null;
	boolean hasLooRiegelAnalysisNode = false;
	DefaultMutableTreeNode node = null;

	public LooRiegelButtonHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

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
								.getSelectedSheetIndex()).setAnalysisType(
						"looriegel");

		newColumnPropertiesArrayList();

		initializeVariables();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		procInputInst.setIfSheetIsSelected(true);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		nodeCreationInPE();
		setAllTheGuiScreenObjectsToNull();
		createLooRiegelman();

	}

	private void setAllTheGuiScreenObjectsToNull() {

		LooRiegelMainScreenCreator.LOORIEGEL_MAIN_SCREEN = null;
		LooRiegelMainScreenGUICreator.LOORIEGEL_MAIN_SCREEN_GUI = null;
		DeConvoTabbedPaneCreator.DECONVO_TABBLE_DISP = null;
		DeConvoSetupAvailableCompCreator.DECONVO_AVAIL_COMP = null;
		DeConvoSetupDispCompGuiCreator.SETUP_DISP_COMP_GUI = null;
		LooRiegelInitEstimateDispGuiCreator.LOORIEGEL_INIT_EST_INST = null;
		DeConvoMappingDispGuiCreator.DECONVO_MAP_DISP = null;
		DeConvoModelOptionsGui.DECONVO_OPT_GUI = null;
		DeConvoResultAvailCompDisplayer.DECONVO_RES_AVAIL_GUI = null;
		DeConvoResultDispCompCreator.DECONVO_RES_DISP = null;
		ImportedDataSheet.importedDataSheetFrame = null;
		CaMultipleLevelSortingProvider.mlpInstance = null;
		CaNoOfSubjectDeterminer.noOfSubjectInst = null;
		DataPreparationForAllComponentsForCA.datPrepForCAInst = null;
		
	}

	private void newColumnPropertiesArrayList() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

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
				.getLooRiegelInfo()
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

	private void createLooRiegelman() throws RowsExceededException, WriteException,
			BiffException, IOException {

		LooRiegelMainScreenCreator looRiegelMainScreen = LooRiegelMainScreenCreator
				.createLooRiegelMainScreenInstance();
		looRiegelMainScreen.looRiegelMainScreenCreation();

		TreePath path = DeConvoSetupAvailableCompCreator
				.createDeConvoSetupAvailCompInst().availableComponentsTree
				.getPathForRow(1);
		DeConvoSetupAvailableCompCreator.createDeConvoSetupAvailCompInst().availableComponentsTree
				.setSelectionPath(path);

		

	}

	private DefaultMutableTreeNode getProjNode() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Iterator<Entry<String, DefaultMutableTreeNode>> it = appInfo
				.getpENodes().entrySet().iterator();

		while (it.hasNext()) {

			Map.Entry pairs = (Map.Entry) it.next();
			if (pairs.getKey().equals(appInfo.getSelectedprojectPath())) {
				return (DefaultMutableTreeNode) pairs.getValue();
			}
		}

		return null;
	}

	private void getAnalysisNode() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String projName = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getProjectName();
		String key = "Work Space," + projName + "," + "Deconvo LooRiegel Analysis";

		analysisNode = appInfo.getpENodes().get(key);

	}

	private void nodeCreationInPE() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		projectNode = getProjNode();
		for (int i = 0; i < projectNode.getChildCount(); i++) {
			if (((DefaultMutableTreeNode) projectNode.getChildAt(i))
					.getUserObject().toString().equals("Deconvo LooRiegel Analysis")) {
				hasLooRiegelAnalysisNode = true;
				break;
			}
		}

		if (hasLooRiegelAnalysisNode == true) {
			getAnalysisNode();

			String newWBSheetName = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkBookName()
					+ "-"
					+ appInfo
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
							.getSheetName();
			for (int i = 0; i < analysisNode.getChildCount(); i++) {
				if (analysisNode.getChildAt(i).toString().contains(
						newWBSheetName)) {
					// the analysis is already existing and new node is not
					// created
				} else if (i == analysisNode.getChildCount() - 1) {

					int lastChiledOfNcaAnalysis = analysisNode.getChildCount();
					DefaultMutableTreeNode ncaNode = new DefaultMutableTreeNode(
							"LooRiegel-" + (newWBSheetName));
					analysisNode.add(ncaNode);
				}
			}

			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.reload();
		} else if (hasLooRiegelAnalysisNode == false) { // the analysis node is
			// not
			// present so we have to
			// create the analysis
			// node and then create a child of it.

			analysisNode = new DefaultMutableTreeNode("Deconvo LooRiegel Analysis");
			projectNode.add(analysisNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.insertNodeInto(analysisNode, projectNode, projectNode
					.getChildCount() - 1);

			// insert this node into the project nodes
			appInfo.getpENodes().put(
					PETreeFunctions.createPETreeFuncInst().convertObjToStr(
							analysisNode), analysisNode);

			DefaultMutableTreeNode ncaNode = new DefaultMutableTreeNode(
					"LooRiegel-"
							+ (appInfo
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
									.getWorkBookName()
									+ "-" + appInfo
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
									.getSheetName()));
			analysisNode.add(ncaNode);

			model.reload();
		}

	}

	private void initializeVariables() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

	
		DDViewLayer.createViewLayerInstance().isBeforeLooRiegel = false;

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
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		DDViewLayer.createViewLayerInstance().isCAExecution = false;
	
		DDViewLayer.createViewLayerInstance().isLooRiegelExecution = true;
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		DDViewLayer.createMTInstance().mainTabbedFrame
				.setTitle(" Loo-Riegelman DeConvolution");
		Iconizing.createIconizingInstance()
				.iconizeAvailableAnalysisInternalFrame();
		Iconizing.createIconizingInstance().iconizeLogInternalFrame();

	}




}
