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

public class DeConvoSetupAvailableCompCreator {
	



	JInternalFrame setupAvailableComponentsInternalFrame;
	JTree availableComponentsTree;
	String analysisType;
	DefaultMutableTreeNode lambdaZSelectorNodeselector;
	public void deConvoSetupAvailableCompCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createSetupAvailableComponentsinternalFrame();
	}
	
	public DeConvoSetupAvailableCompCreator() throws RowsExceededException, WriteException, BiffException, IOException{
		setupAvailableComponentsInternalFrame = new JInternalFrame(
				"Available Options", false, false, false, false);
		setupAvailableComponentsInternalFrame.setLocation(0, 0);
		setupAvailableComponentsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();
		
		if(analysisType.equals("wagnelson"))
		{
			int height = (int) (WagNelsonMainScreenCreator.createWagNelsonMainScreenInstance().wagNelsonMainInternalFrame.getHeight()-20);
			setupAvailableComponentsInternalFrame.setSize(WagNelsonMainScreenCreator.createWagNelsonMainScreenInstance().wagNelsonMainInternalFrame
					.getWidth() / 4, height);
		}else if(analysisType.equals("looriegel"))
		{
			int height = (int) (LooRiegelMainScreenCreator.createLooRiegelMainScreenInstance().looRiegelMainInternalFrame.getHeight()-20);
			setupAvailableComponentsInternalFrame.setSize(LooRiegelMainScreenCreator.createLooRiegelMainScreenInstance().looRiegelMainInternalFrame
					.getWidth() / 4, height);
		}
		
			
		
		
	}
	
	public static DeConvoSetupAvailableCompCreator DECONVO_AVAIL_COMP = null;
	public static DeConvoSetupAvailableCompCreator createDeConvoSetupAvailCompInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(DECONVO_AVAIL_COMP == null){
			DECONVO_AVAIL_COMP = new DeConvoSetupAvailableCompCreator();
		}
		return DECONVO_AVAIL_COMP;
	}
	
	
	private void createSetupAvailableComponentsinternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(setupAvailableComponentsInternalFrame);

		setupAvailableComponentsInternalFrame.setVisible(true);
		DeConvoTabbedPaneCreator.createDeConvoTabbedPaneInstance().setupTabDesktopPane.add(setupAvailableComponentsInternalFrame);
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
				"Dosing");
		availableComponents.add(initialEstimateComponent);
		
		
		
		lambdaZSelectorNodeselector = new DefaultMutableTreeNode(
				"LambdaZ");
		availableComponents.add(lambdaZSelectorNodeselector);
		
		
		if(analysisType.equals("looriegel"))
		{
			DefaultMutableTreeNode parameter = new DefaultMutableTreeNode(
			"Parameters");
			availableComponents.add(parameter);
		}
		
		
		
		availableComponentsTree = new JTree(availableComponents);
		availableComponentsTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
	
		availableComponentsTree.addTreeSelectionListener(DDViewLayer.createViewLayerInstance());
		JScrollPane availableComponentsScrollPane = new JScrollPane(
				availableComponentsTree);
		setupAvailableComponentsInternalFrame.getContentPane().add(
				availableComponentsScrollPane);
		setupAvailableComponentsInternalFrame.moveToFront();

	}



}
