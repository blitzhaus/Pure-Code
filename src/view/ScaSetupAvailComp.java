package view;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import Common.JinternalFrameFunctions;

public class ScaSetupAvailComp {

	public static ScaSetupAvailComp SCA_SETUP_AVAIL_COMP = null;

	public static ScaSetupAvailComp createSetupAvailCompInst() {
		if (SCA_SETUP_AVAIL_COMP == null) {
			SCA_SETUP_AVAIL_COMP = new ScaSetupAvailComp("just object creation");
		}
		return SCA_SETUP_AVAIL_COMP;
	}

	public ScaSetupAvailComp(String dummy) {

	}

	JInternalFrame setupAvailableComponentsInternalFrame;
	JTree availableComponentsTree;

	void createSetupAvailableComponentsinternalFrame(int selectedSheetIndex) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		setupAvailableComponentsInternalFrame = new JInternalFrame(
				"Available Options", false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						setupAvailableComponentsInternalFrame);
		setupAvailableComponentsInternalFrame.setLocation(0, 0);
		setupAvailableComponentsInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		int height = (int) (SCAMainPage.createScainstance().scaMainInternalFrame
				.getHeight());
		setupAvailableComponentsInternalFrame.setSize(SCAMainPage
				.createScainstance().scaMainInternalFrame.getWidth() / 4,
				height);
		setupAvailableComponentsInternalFrame.setVisible(true);
		ScaTabs.createScaTabsInst().setupTabDesktopPane
				.add(setupAvailableComponentsInternalFrame);
		setupAvailableComponentsInternalFrame.moveToFront();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.setSelectedSheetIndex(selectedSheetIndex);
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
