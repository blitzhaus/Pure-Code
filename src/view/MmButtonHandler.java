package view;

import java.io.IOException;
import java.util.Enumeration;
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

public class MmButtonHandler {

	DefaultMutableTreeNode projectNode;
	DefaultMutableTreeNode analysisNode = null;
	boolean hasCAnalysisNode = false;
	DefaultMutableTreeNode node = null;

	public MmButtonHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

		newColumnPropertiesArrayList();
		analysisNode = null;
		hasCAnalysisNode = false;
		node = null;

		initializeVariables();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		procInputInst.setIfSheetIsSelected(true);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		nodeCreationInPE();
		setAllTheGuiScreenObjectsToNull();
		createMM();

	}

	public void setAllTheGuiScreenObjectsToNull()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		MmMainScreenCreator.MM_MAIN_SCREEN = null;
		MmMainScreenGUICreator.MM_MAIN_SCREEN_GUI = null;
		CaTabbedPanesCreator.PD_TABBLE_DISP = null;
		CaSetupAvailableCompCreator.PD_AVAIL_COMP = null;
		CaSetupDispCompGuiCreator.SETUP_DISP_COMP_GUI = null;
		CaMappingDispGuiCreator.PD_MAP_DISP = null;
		CaInitEstimateDispGuiCreator.PD_INIT_EST_INST = null;
		CaDosingDispGuiCreator.PD_DOSE_INST = null;
		CaParamUnitsDispGuiCreator.CA_PAR_UNITS_DISP = null;
		CaOptionsGuiCreator.PD_OPT_GUI = null;
		CaLibModelDispGuiCreator.CA_LIB_MOD_GUI = null;
		CaWeightDoseDispGuiCreator.PD_W_D_GUI = null;
		CaEngineSettingDispGuiCreator.PD_ENG_SETGUI = null;
		CaResultAvailCompDisplayer.PD_RES_AVAIL_GUI = null;
		CaResultDispCompCreator.CA_RES_DISP = null;
		CaMultipleLevelSortingProvider.mlpInstance = null;
		CaNoOfSubjectDeterminer.noOfSubjectInst = null;
		DataPreparationForAllComponentsForCA.datPrepForCAInst = null;
		ParameterAndUnitListLoader.paramAndUnitListInst = null;

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
				.getMmInfo()
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

	public void createMM() throws RowsExceededException, WriteException,
			BiffException, IOException {
		MmMainScreenCreator mmMainScreen = MmMainScreenCreator.createMmMainScreenInstance();
		mmMainScreen.MmMainScreenCreation();

		TreePath path = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().availableComponentsTree
				.getPathForRow(1);
		CaSetupAvailableCompCreator.createPdSetupAvailCompInst().availableComponentsTree
				.setSelectionPath(path);

		ParameterAndUnitListLoader.createParamAndUnitListInstance()
				.unitListForParameters();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst()
				.paramUnitTableRebuilding(procInputInst);
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
		String key = "Work Space," + projName + "," + "MM Analysis,";

		analysisNode = appInfo.getpENodes().get(key);

	}

	public void nodeCreationInPE() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		projectNode = getProjNode();
		for (int i = 0; i < projectNode.getChildCount(); i++) {
			if (((DefaultMutableTreeNode) projectNode.getChildAt(i))
					.getUserObject().toString().equals("MM Analysis")) {
				hasCAnalysisNode = true;
				break;
			}
		}

		if (hasCAnalysisNode == true) {
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
							"MM-" + (newWBSheetName));
					analysisNode.add(ncaNode);
				}
			}

			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.reload();
		} else if (hasCAnalysisNode == false) { // the analysis node is not
			// present so we have to
			// create the analysis
			// node and then create a child of it.

			analysisNode = new DefaultMutableTreeNode("MM Analysis");
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
					"MM-"
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
		DDViewLayer.createViewLayerInstance().isFromCA = true;
		DDViewLayer.createViewLayerInstance().isBeforeCA = false;
		DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage = false;
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
		DDViewLayer.createViewLayerInstance().isCAExecution = true;
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		DDViewLayer.createMTInstance().mainTabbedFrame.setTitle(" MM Analysis");
		Iconizing.createIconizingInstance()
				.iconizeAvailableAnalysisInternalFrame();
		Iconizing.createIconizingInstance().iconizeLogInternalFrame();

	}

}
