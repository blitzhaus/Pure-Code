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

public class InVitroSetupAvailableCompCreator {

	JInternalFrame setupAvailableComponentsInternalFrame;
	JTree availableComponentsTree;
	String analysisType;
	
	public void inVitroSetupAvailableCompCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createSetupAvailableComponentsinternalFrame();
	}
	
	public InVitroSetupAvailableCompCreator() throws RowsExceededException, WriteException, BiffException, IOException{
		setupAvailableComponentsInternalFrame = new JInternalFrame(
				"Available Options", false, false, false, false);
		setupAvailableComponentsInternalFrame.setLocation(0, 0);
		setupAvailableComponentsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		
		
			int height = (int) (InVitroMainScreenCreator.createInVitroMainScreenInstance().inVitroMainInternalFrame.getHeight()-20);
			setupAvailableComponentsInternalFrame.setSize(InVitroMainScreenCreator.createInVitroMainScreenInstance().inVitroMainInternalFrame
					.getWidth() / 4, height);
		
		
	}
	
	public static InVitroSetupAvailableCompCreator INVITRO_AVAIL_COMP = null;
	public static InVitroSetupAvailableCompCreator createInVitroSetupAvailCompInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(INVITRO_AVAIL_COMP == null){
			INVITRO_AVAIL_COMP = new InVitroSetupAvailableCompCreator();
		}
		return INVITRO_AVAIL_COMP;
	}
	
	
	private void createSetupAvailableComponentsinternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(setupAvailableComponentsInternalFrame);

		setupAvailableComponentsInternalFrame.setVisible(true);
		InVitroTabbedPanesCreator.createInVitroTabbedPaneInstance().setupTabDesktopPane.add(setupAvailableComponentsInternalFrame);
		setupAvailableComponentsInternalFrame.moveToFront();
		String sheetName = new String();
		
		if ((appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
				&& (DesStatResDispComp.createDesStatResDispInst().carryThisToOtherComponents == true)) {
			sheetName = "Final Parameters";
		} else if(DDViewLayer.createViewLayerInstance().isFromPlotMavenMappingScreen == true){
			sheetName = ImportedDataSheet.createImportedDataSheetInstance().workSheet.getName();
		} else {
			//sheetName = ImportedDataSheet.createImportedDataSheetInstance().workSheet.getName();
			sheetName=appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
			.getSheetName();
		}
		
		DefaultMutableTreeNode availableComponents = new DefaultMutableTreeNode(sheetName);
		DefaultMutableTreeNode mainComponent = new DefaultMutableTreeNode(
				"Mapping");

		availableComponents.add(mainComponent);
				
		
		
		DefaultMutableTreeNode initialEstimateComponent = new DefaultMutableTreeNode(
				"Initial Estimate");
		availableComponents.add(initialEstimateComponent);
		
		
		
		
		DefaultMutableTreeNode parameterUnits = new DefaultMutableTreeNode(
				"Unit");
		availableComponents.add(parameterUnits);
		
		
		
		availableComponentsTree = new JTree(availableComponents);
		availableComponentsTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		//availableComponentsTree.addMouseListener(ViewLayer.createViewLayerInstance());
		availableComponentsTree.addTreeSelectionListener(DDViewLayer.createViewLayerInstance());
		JScrollPane availableComponentsScrollPane = new JScrollPane(
				availableComponentsTree);
		setupAvailableComponentsInternalFrame.getContentPane().add(
				availableComponentsScrollPane);
		setupAvailableComponentsInternalFrame.moveToFront();

	}


}
