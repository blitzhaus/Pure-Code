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

public class CaSetupAvailableCompCreator {
	JInternalFrame setupAvailableComponentsInternalFrame;
	JTree availableComponentsTree;
	String analysisType;
	
	public void pdSetupAvailableCompCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createSetupAvailableComponentsinternalFrame();
	}
	
	public CaSetupAvailableCompCreator() throws RowsExceededException, WriteException, BiffException, IOException{
		setupAvailableComponentsInternalFrame = new JInternalFrame(
				"Available Options", false, false, false, false);
		setupAvailableComponentsInternalFrame.setLocation(0, 0);
		setupAvailableComponentsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
		.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		if(analysisType.equals("pk"))
		{
			int height = (int) (PkMainScreenCreator.createPkMainScreenInstance().pkMainInternalFrame.getHeight()-20);
			setupAvailableComponentsInternalFrame.setSize(PkMainScreenCreator.createPkMainScreenInstance().pkMainInternalFrame
					.getWidth() / 4, height);
		}
		
		else
		
		if(analysisType.equals("pd"))
		{
			int height = (int) (PdMainScreenCreator.createPdMainScreenInstance().pdMainInternalFrame.getHeight()-20);
			setupAvailableComponentsInternalFrame.setSize(PdMainScreenCreator.createPdMainScreenInstance().pdMainInternalFrame
					.getWidth() / 4, height);
		}
		
		else
			if(analysisType.equals("mm"))
			{
				int height = (int) (MmMainScreenCreator.createMmMainScreenInstance().mmMainInternalFrame.getHeight()-20);
				setupAvailableComponentsInternalFrame.setSize(MmMainScreenCreator.createMmMainScreenInstance().mmMainInternalFrame
						.getWidth() / 4, height);	
			}
			else
				if(analysisType.equals("pkpdlink"))
				{
					int height = (int) (PkPdLinkMainScreenCreator.createPkPdLinkMainScreenInstance().pkpdLinkMainInternalFrame.getHeight()-20);
					setupAvailableComponentsInternalFrame.setSize(PkPdLinkMainScreenCreator.createPkPdLinkMainScreenInstance().pkpdLinkMainInternalFrame
							.getWidth() / 4, height);	
				}
				else
					if(analysisType.equals("irm"))
					{
						int height = (int) (IrmMainScreenCreator.createIrmMainScreenInstance().irmMainInternalFrame.getHeight()-20);
						setupAvailableComponentsInternalFrame.setSize(IrmMainScreenCreator.createIrmMainScreenInstance().irmMainInternalFrame
								.getWidth() / 4, height);	
					}else
						if(analysisType.equals("ascii"))
						{
							int height = (int) (AsciiMainScreen.createAsciiMainScreenInstance().asciiMainInternalFrame.getHeight()-20);
							setupAvailableComponentsInternalFrame.setSize(AsciiMainScreen.createAsciiMainScreenInstance().asciiMainInternalFrame
									.getWidth() / 4, height);	
						}
		
	}
	
	public static CaSetupAvailableCompCreator PD_AVAIL_COMP = null;
	public static CaSetupAvailableCompCreator createPdSetupAvailCompInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PD_AVAIL_COMP == null){
			PD_AVAIL_COMP = new CaSetupAvailableCompCreator();
		}
		return PD_AVAIL_COMP;
	}
	
	
	private void createSetupAvailableComponentsinternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(setupAvailableComponentsInternalFrame);

		setupAvailableComponentsInternalFrame.setVisible(true);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane.add(setupAvailableComponentsInternalFrame);
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
		if(analysisType.equals("pk")
				|| analysisType.equals("mm")
				|| analysisType.equals("pkpdlink")
				|| analysisType.equals("irm")
				||analysisType.equals("ascii"))
		{
			DefaultMutableTreeNode DosingComponent = new DefaultMutableTreeNode(
			"Dosing");
			availableComponents.add(DosingComponent);
		}

		
		
		
		DefaultMutableTreeNode initialEstimateComponent = new DefaultMutableTreeNode(
				"Initial Estimate");
		availableComponents.add(initialEstimateComponent);
		
		if(analysisType.equals("pkpdlink")
				|| analysisType.equals("irm"))
		{
			DefaultMutableTreeNode secondInitEstComponent = new DefaultMutableTreeNode(
			"Link Estimate");
			availableComponents.add(secondInitEstComponent);
		}
		
		
		
		
		DefaultMutableTreeNode parameterUnits = new DefaultMutableTreeNode(
				"Unit");
		availableComponents.add(parameterUnits);
		
		if(analysisType.equals("ascii")	)
		{
			createAsciiSubNodes(availableComponents);
		}
		
		availableComponentsTree = new JTree(availableComponents);
		availableComponentsTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		//availableComponentsTree.addMouseListener(ViewLayer.createViewLayerInstance());
		availableComponentsTree.addTreeSelectionListener(DDViewLayer.createViewLayerInstance());
		JScrollPane availableComponentsScrollPane = new JScrollPane(
				availableComponentsTree);
		availableComponentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		availableComponentsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setupAvailableComponentsInternalFrame.getContentPane().add(
				availableComponentsScrollPane);
		setupAvailableComponentsInternalFrame.moveToFront();

	}

	private void createAsciiSubNodes(DefaultMutableTreeNode availableComponents) {

		DefaultMutableTreeNode asciiTextComponent = new DefaultMutableTreeNode(
				"Ascii");
		availableComponents.add(asciiTextComponent);
		
		//ascii "model" node creation
		
		DefaultMutableTreeNode asciiModel = new DefaultMutableTreeNode(
		"Model");
		asciiTextComponent.add(asciiModel);
		
		// ascii commands/ selection node creation
		DefaultMutableTreeNode asciiCommands = new DefaultMutableTreeNode(
				"Selection");
		asciiModel.add(asciiCommands);

		// ascii primary and secondary parameter node creation
		DefaultMutableTreeNode asciiPriParameters = new DefaultMutableTreeNode(
				"Primary Parameters");
		asciiCommands.add(asciiPriParameters);

		DefaultMutableTreeNode asciiSecParameters = new DefaultMutableTreeNode(
		"Secondary Parameters");
		asciiCommands.add(asciiSecParameters);
		
		// ascii declaration/temporary node creation
		DefaultMutableTreeNode asciiTemp = new DefaultMutableTreeNode(
				"Declaration");
		asciiModel.add(asciiTemp);

		// ascii algebraic equations/functions node creation
		DefaultMutableTreeNode asciiFunc = new DefaultMutableTreeNode(
				"Algebraic_Equations");
		asciiModel.add(asciiFunc);

		// ascii starting/Initialization node creation
		DefaultMutableTreeNode asciiStart = new DefaultMutableTreeNode(
				"Initialization");
		asciiModel.add(asciiStart);

		// ascii differential equations node creation
		DefaultMutableTreeNode asciiDiff = new DefaultMutableTreeNode(
				"Differential_equations");
		asciiModel.add(asciiDiff);

		// ascii secondary/equations_sec_param node creation
		DefaultMutableTreeNode asciiSecondary = new DefaultMutableTreeNode(
				"Equations_sec_param");
		asciiModel.add(asciiSecondary);

	}
}
