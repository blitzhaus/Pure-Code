package view;

import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;

public class NcaSetupAvailableComp {
	JInternalFrame setupAvailableComponentsInternalFrame;
	JTree availableComponentsTree;
	DefaultMutableTreeNode lambdaZSelectorNodeselector;

	public void NcaSetupAvailableCompCreation(int selectedSheetIndex)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		createSetupAvailableComponentsinternalFrame(selectedSheetIndex);
	}

	public NcaSetupAvailableComp(String dummy) throws RowsExceededException,
			WriteException, BiffException, IOException {
		setupAvailableComponentsInternalFrame = new JInternalFrame(
				"Available Options", false, false, false, false);
		setupAvailableComponentsInternalFrame.setLocation(0, 0);
		setupAvailableComponentsInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		int height = (int) (NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
				.getHeight() - 30);
		setupAvailableComponentsInternalFrame
				.setSize(
						NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
								.getWidth() / 4, height);

	}

	public static NcaSetupAvailableComp NCA_AVAIL_COMP = null;

	public static NcaSetupAvailableComp createNcaSetupAvailCompInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NCA_AVAIL_COMP == null) {
			NCA_AVAIL_COMP = new NcaSetupAvailableComp("just object creation");
		}
		return NCA_AVAIL_COMP;
	}

	private void createSetupAvailableComponentsinternalFrame(
			int selectedSheetInedx) throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						setupAvailableComponentsInternalFrame);

		setupAvailableComponentsInternalFrame.setVisible(true);
		NcaTabbedPanes.createNcaTabbedPaneInstance().setupTabDesktopPane
				.add(setupAvailableComponentsInternalFrame);
		setupAvailableComponentsInternalFrame.moveToFront();
		String sheetName = new String();

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.setSelectedSheetIndex(selectedSheetInedx);
		if ((appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
				&& (DesStatResDispComp.createDesStatResDispInst().carryThisToOtherComponents == true)) {
			sheetName = "Final Parameters";
		} else if (DDViewLayer.createViewLayerInstance().isFromPlotMavenMappingScreen == true) {
			sheetName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getSheetName();
		} else {
			sheetName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getSheetName();
		}

		DefaultMutableTreeNode availableComponents = new DefaultMutableTreeNode(
				sheetName);
		DefaultMutableTreeNode mainComponent = new DefaultMutableTreeNode(
				"Mapping");

		availableComponents.add(mainComponent);
		DefaultMutableTreeNode dosingComponent = new DefaultMutableTreeNode(
				"Dosing");
		availableComponents.add(dosingComponent);
		lambdaZSelectorNodeselector = new DefaultMutableTreeNode(
				"Lambda Z Selector");
		availableComponents.add(lambdaZSelectorNodeselector);
/*		DefaultMutableTreeNode slopes = new DefaultMutableTreeNode("Slopes");
		availableComponents.add(slopes);*/
		DefaultMutableTreeNode SpecifySubAreas = new DefaultMutableTreeNode(
				"Specify Sub Areas");
		availableComponents.add(SpecifySubAreas);

		DefaultMutableTreeNode parameterUnits = new DefaultMutableTreeNode(
				"Parameter Units");
		availableComponents.add(parameterUnits);
		DefaultMutableTreeNode parameterNames = new DefaultMutableTreeNode(
				"Parameter Names");
		availableComponents.add(parameterNames);
		availableComponentsTree = new JTree(availableComponents);
		availableComponentsTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		// availableComponentsTree.addMouseListener(ViewLayer.createViewLayerInstance());
		availableComponentsTree.addTreeSelectionListener(DDViewLayer
				.createViewLayerInstance());
		JScrollPane availableComponentsScrollPane = new JScrollPane(
				availableComponentsTree);
		setupAvailableComponentsInternalFrame.getContentPane().add(
				availableComponentsScrollPane);
		setupAvailableComponentsInternalFrame.moveToFront();

	}
}
