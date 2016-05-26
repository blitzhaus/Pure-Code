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
import Common.ReadFile2dStrArray;

public class CaResultAvailCompDisplayer {

	
	public CaResultAvailCompDisplayer(){
		
	}
	
	public static CaResultAvailCompDisplayer PD_RES_AVAIL_GUI = null;
	public static CaResultAvailCompDisplayer createPdResAvailCompInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PD_RES_AVAIL_GUI == null){
			PD_RES_AVAIL_GUI = new CaResultAvailCompDisplayer();
		}
		return PD_RES_AVAIL_GUI;
	}
	
	public void pdResultsAvailableCompCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createResultsTabAvailableOutputsInternalFrame();
	}
	
	JInternalFrame ResultsTabAvailableoutputInternalFrame;
	DefaultMutableTreeNode availableOutputs;
	JTree availableOutputsTree;
	
	
	
	private void createResultsTabAvailableOutputsInternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		createResAvailIntFrame();
		availableOutputs = new DefaultMutableTreeNode("output");
		DefaultMutableTreeNode textOutput = new DefaultMutableTreeNode(
				"Text output");
		availableOutputs.add(textOutput);
		DefaultMutableTreeNode completeOutput = new DefaultMutableTreeNode(
				"Complete output");
		textOutput.add(completeOutput);
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		
		
		String[][] spreadSheetNodeNames = new String[1][1];
		if (analysisType.equals("pk")) {
			spreadSheetNodeNames = readFile("SpreedSheetOutputName/PkOutputName.txt");
		}
		else
		if (analysisType.equals("pd")) {
			spreadSheetNodeNames = readFile("SpreedSheetOutputName/PdOutputName.txt");
		}
		else
			if (analysisType.equals("mm")) {
				spreadSheetNodeNames = readFile("SpreedSheetOutputName/MmOutputName.txt");
			}
			else
				if (analysisType.equals("pkpdlink")) {
					spreadSheetNodeNames = readFile("SpreedSheetOutputName/LinkOutputName.txt");
				}
				else
					if (analysisType.equals("irm")) {
						spreadSheetNodeNames = readFile("SpreedSheetOutputName/IrmOutputName.txt");
					}else
						if (analysisType.equals("ascii")) {
							spreadSheetNodeNames = readFile("SpreedSheetOutputName/AsciiOutputName.txt");
						}
					
				

		DefaultMutableTreeNode Tables = new DefaultMutableTreeNode("Sheets");
		availableOutputs.add(Tables);

		for (int i = 0; i < spreadSheetNodeNames.length; i++) {
			DefaultMutableTreeNode initialParameterNode = new DefaultMutableTreeNode(
					spreadSheetNodeNames[i][0]);
			Tables.add(initialParameterNode);
		}
		availableOutputsTree = new JTree(availableOutputs);
		availableOutputsTree.addTreeSelectionListener(DDViewLayer.createViewLayerInstance());
		availableOutputsTree.addMouseListener(DDViewLayer.createViewLayerInstance());

		availableOutputsTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
	
		JScrollPane availableComponentsScrollPane = new JScrollPane(
				availableOutputsTree);
		ResultsTabAvailableoutputInternalFrame.getContentPane().add(
				availableComponentsScrollPane);

	}
	
	void createResAvailIntFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		ResultsTabAvailableoutputInternalFrame = new JInternalFrame("Output",
				true, true, true, true);
		ResultsTabAvailableoutputInternalFrame.setBorder(viewLayerInst.b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(ResultsTabAvailableoutputInternalFrame);

		ResultsTabAvailableoutputInternalFrame.setLocation(0, 0);
		
		ResultsTabAvailableoutputInternalFrame.setSize(CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getWidth(), 
				CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getHeight());
		
		ResultsTabAvailableoutputInternalFrame.setBorder(viewLayerInst.b);
		ResultsTabAvailableoutputInternalFrame.setVisible(true);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().resultsTabDesktopPane.add(ResultsTabAvailableoutputInternalFrame);
		ResultsTabAvailableoutputInternalFrame.moveToFront();
	}
	
	public String[][] readFile(String fileLocation) {
		ReadFile2dStrArray RF = new ReadFile2dStrArray(fileLocation);
		int rowCount = RF.rowCount;
		int colCount = RF.colCount;

		System.out.println("rowCount.." + rowCount);
		System.out.println("colCount.." + colCount);

		String[][] inputMatrix = new String[rowCount][colCount];
		inputMatrix = RF.fileArray;
		return inputMatrix;
	}
	
}
