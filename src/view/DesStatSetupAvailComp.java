package view;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import Common.JinternalFrameFunctions;

public class DesStatSetupAvailComp {

	public static DesStatSetupAvailComp DES_STAT_AVAIL_COMP = null;

	public static DesStatSetupAvailComp createDesStatAvailComp() {
		if (DES_STAT_AVAIL_COMP == null) {
			DES_STAT_AVAIL_COMP = new DesStatSetupAvailComp();
		}
		return DES_STAT_AVAIL_COMP;
	}

	public DesStatSetupAvailComp() {

	}

	JInternalFrame setupAvailableComponentsInternalFrame;
	JTree availableComponentsTree;

	void createSetupAvailableComponentsinternalFrame(int selectedSheetIndex) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DDViewLayer.createMTInstance().mainTabbedFrame.setSize(DDViewLayer
				.createMTInstance().mainTabbedFrameOriginalWidth, DDViewLayer
				.createMTInstance().mainTabbedFrameoriginalHeight);
		setupAvailableComponentsInternalFrame = new JInternalFrame(
				"Available Options", false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						setupAvailableComponentsInternalFrame);
		setupAvailableComponentsInternalFrame.setLocation(0, 0);
		setupAvailableComponentsInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		int height = (int) (DescriptiveStatMainPage
				.createDescStatMainPageInst().desStatsMainIF.getHeight() -10);
		setupAvailableComponentsInternalFrame.setSize(DescriptiveStatMainPage
				.createDescStatMainPageInst().desStatsMainIF.getWidth() / 4,
				height);
		setupAvailableComponentsInternalFrame.setVisible(true);
		DSTabs.createDSTabInst().setupTabDesktopPane
				.add(setupAvailableComponentsInternalFrame);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.setSelectedSheetIndex(selectedSheetIndex);

		setupAvailableComponentsInternalFrame.moveToFront();
		appInfo = DisplayContents.createAppInfoInstance();
		DefaultMutableTreeNode availableComponents = new DefaultMutableTreeNode(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getSheetName());
		DefaultMutableTreeNode mainComponent = new DefaultMutableTreeNode(
				"Mapping");

		availableComponents.add(mainComponent);

		availableComponentsTree = new JTree(availableComponents);
		availableComponentsTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		availableComponentsTree.addTreeSelectionListener(DDViewLayer
				.createViewLayerInstance());
		JScrollPane availableComponentsScrollPane = new JScrollPane(
				availableComponentsTree);
		setupAvailableComponentsInternalFrame.getContentPane().add(
				availableComponentsScrollPane);

	}
}
