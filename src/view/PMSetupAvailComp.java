package view;

import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.JinternalFrameFunctions;

public class PMSetupAvailComp {

	public static PMSetupAvailComp PM_AVAIL_COMP = null;

	public static PMSetupAvailComp createPMAvailCompInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (PM_AVAIL_COMP == null) {
			PM_AVAIL_COMP = new PMSetupAvailComp("dummy");
		}
		return PM_AVAIL_COMP;
	}

	public PMSetupAvailComp(String dummy) {

	}

	JInternalFrame setupAvailableComponentsInternalFrame;
	JTree plotMavenAvailableComponentsTree;

	void createPotMavenSetupAvailableComponentsFrame(int selectedSheetIndex)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		setupAvailableComponentsInternalFrame = new JInternalFrame(
				"Available Components", false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						setupAvailableComponentsInternalFrame);
		setupAvailableComponentsInternalFrame
				.setLocation(
						PlotMavenCreateUI.createPlotMavenUIInst().PlotMavenMainInternalFrame
								.getX(),
						PlotMavenCreateUI.createPlotMavenUIInst().PlotMavenMainInternalFrame
								.getY());
		setupAvailableComponentsInternalFrame
				.setSize(DDViewLayer.createMTInstance().mainTabbedFrame
						.getWidth() / 4, PlotMavenCreateUI
						.createPlotMavenUIInst().PlotMavenMainInternalFrame
						.getHeight() / 2);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.setSelectedSheetIndex(selectedSheetIndex);

		setupAvailableComponentsInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		setupAvailableComponentsInternalFrame.setVisible(true);
		PlotMavenCreateUI.createPlotMavenUIInst().plotMavenSetupDesktopPane
				.add(setupAvailableComponentsInternalFrame);
		setupAvailableComponentsInternalFrame.moveToFront();

		String sheetname = new String();

		if ((appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
				&& (DesStatResDispComp.createDesStatResDispInst().carryThisToOtherComponents == true)) {
			sheetname = "Final Parameters";

		} else {
			sheetname = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getSheetName();

		}

		DefaultMutableTreeNode plotmavenAvailableComponents = new DefaultMutableTreeNode(
				sheetname);
		plotMavenAvailableComponentsTree = new JTree(
				plotmavenAvailableComponents);
		plotMavenAvailableComponentsTree.addTreeSelectionListener(DDViewLayer
				.createViewLayerInstance());
		JScrollPane plotMavenAvailableComponentsScrollPane = new JScrollPane(
				plotMavenAvailableComponentsTree);
		setupAvailableComponentsInternalFrame.getContentPane().add(
				plotMavenAvailableComponentsScrollPane);

	}
}
