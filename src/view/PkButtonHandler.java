package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import Common.Iconizing;
import Common.PETreeFunctions;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PkButtonHandler {

	DefaultMutableTreeNode projectNode;
	DefaultMutableTreeNode analysisNode = null;
	boolean hasCAnalysisNode = false;
	DefaultMutableTreeNode node = null;

	public PkButtonHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

		newColumnPropertiesArrayList();

		initializeVariables();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		procInputInst.setIfSheetIsSelected(true);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		nodeCreationInPE();
		setAllTheGuiScreenObjectsToNull();
		createPk();

	}

	public void setAllTheGuiScreenObjectsToNull() {
		

		PkMainScreenCreator.PK_MAIN_SCREEN = null;
		PkMainScreenGUICreator.PK_MAIN_SCREEN_GUI = null;
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
				.getPkInfo()
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

	public void createPk() throws RowsExceededException, WriteException,
			BiffException, IOException {
		PkMainScreenCreator pkMainScreen = PkMainScreenCreator.createPkMainScreenInstance();
		pkMainScreen.pkMainScreenCreation();

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
		String key = "Work Space," + projName + ","
				+ "Pharmacokinetic Analysis,";

		analysisNode = appInfo.getpENodes().get(key);

	}

	public void nodeCreationInPE() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		projectNode = getProjNode();
		for (int i = 0; i < projectNode.getChildCount(); i++) {
			if (((DefaultMutableTreeNode) projectNode.getChildAt(i))
					.getUserObject().toString().equals(
							"Pharmacokinetic Analysis")) {
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
							"PK-" + (newWBSheetName));
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

			analysisNode = new DefaultMutableTreeNode(
					"Pharmacokinetic Analysis");
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
					"PK-"
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
		DDViewLayer.createMTInstance().mainTabbedFrame
				.setTitle(" Pharmacokinetic Analysis");
		Iconizing.createIconizingInstance()
				.iconizeAvailableAnalysisInternalFrame();
		Iconizing.createIconizingInstance().iconizeLogInternalFrame();

	}

}
