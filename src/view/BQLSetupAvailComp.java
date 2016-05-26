package view;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import Common.JinternalFrameFunctions;

public class BQLSetupAvailComp {

	public static BQLSetupAvailComp BQL_SETUP_AVAIL = null;

	public static BQLSetupAvailComp createBqlSetAvailInst() {
		if (BQL_SETUP_AVAIL == null) {
			BQL_SETUP_AVAIL = new BQLSetupAvailComp("just object creation");
		}
		return BQL_SETUP_AVAIL;
	}

	public BQLSetupAvailComp(String dummy) {

	}

	JInternalFrame setupAvailableComponentsInternalFrame;
	JTree availableComponentsTree;

	void bqlSetupAvailCompCreation() {

		setupAvailableComponentsInternalFrame = new JInternalFrame(
				"Available Options", false, false, false, false);
		setupAvailableComponentsInternalFrame.setLocation(0, 0);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						setupAvailableComponentsInternalFrame);
		setupAvailableComponentsInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		int height = (int) (BQLMainScreen.createBQLMainScrInst().bqlMainScreenIF
				.getHeight() - 20);
		setupAvailableComponentsInternalFrame.setSize(BQLMainScreen
				.createBQLMainScrInst().bqlMainScreenIF.getWidth() / 4, height);
		BQLTabbedPane.createBqlTabInst().setupTabDesktopPane
				.add(setupAvailableComponentsInternalFrame);
		setupAvailableComponentsInternalFrame.moveToFront();
		setupAvailableComponentsInternalFrame.setVisible(true);

		availableCompTreeCreation();
	}

	private void availableCompTreeCreation() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String sheetName = new String();
		if (DDViewLayer.createViewLayerInstance().isFromPlotMavenMappingScreen == true) {
			sheetName = ImportedDataSheet.createImportedDataSheetInstance().workSheet
					.getName();
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
		DefaultMutableTreeNode mappingComponent = new DefaultMutableTreeNode(
				"Mapping");

		availableComponents.add(mappingComponent);

		DefaultMutableTreeNode tableComponent = new DefaultMutableTreeNode(
				"BQL Rules");
		availableComponents.add(mappingComponent);
		availableComponents.add(tableComponent);

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
