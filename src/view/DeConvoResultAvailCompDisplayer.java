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

public class DeConvoResultAvailCompDisplayer {
	

	
	public DeConvoResultAvailCompDisplayer(){
		
	}
	
	public static DeConvoResultAvailCompDisplayer DECONVO_RES_AVAIL_GUI = null;
	public static DeConvoResultAvailCompDisplayer createDeConvoResAvailCompInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(DECONVO_RES_AVAIL_GUI == null){
			DECONVO_RES_AVAIL_GUI = new DeConvoResultAvailCompDisplayer();
		}
		return DECONVO_RES_AVAIL_GUI;
	}
	
	public void deConvoResultsAvailableCompCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createResultsTabAvailableOutputsInternalFrame();
	}
	
	JInternalFrame resultsTabAvailableoutputInternalFrame;
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
		if (analysisType.equals("wagnelson")) {
			spreadSheetNodeNames = readFile("SpreedSheetOutputName/WagNelsonOutputName.txt");
		}else
			if (analysisType.equals("looriegel")) {
				spreadSheetNodeNames = readFile("SpreedSheetOutputName/LooRiegelOutputName.txt");
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
		resultsTabAvailableoutputInternalFrame.getContentPane().add(
				availableComponentsScrollPane);

	}
	
	void createResAvailIntFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		resultsTabAvailableoutputInternalFrame = new JInternalFrame("Output",
				true, true, true, true);
		resultsTabAvailableoutputInternalFrame.setBorder(viewLayerInst.b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(resultsTabAvailableoutputInternalFrame);

		resultsTabAvailableoutputInternalFrame.setLocation(0, 0);
		
		resultsTabAvailableoutputInternalFrame.setSize(DeConvoSetupAvailableCompCreator.createDeConvoSetupAvailCompInst().setupAvailableComponentsInternalFrame.getWidth(), 
				DeConvoSetupAvailableCompCreator.createDeConvoSetupAvailCompInst().setupAvailableComponentsInternalFrame.getHeight());
		
		resultsTabAvailableoutputInternalFrame.setBorder(viewLayerInst.b);
		resultsTabAvailableoutputInternalFrame.setVisible(true);
		DeConvoTabbedPaneCreator.createDeConvoTabbedPaneInstance().resultsTabDesktopPane.add(resultsTabAvailableoutputInternalFrame);
		resultsTabAvailableoutputInternalFrame.moveToFront();
	}
	
	public String[][] readFile(String fileLocation) {
		ReadFile2dStrArray RF = new ReadFile2dStrArray(fileLocation);
		int rowCount = RF.rowCount;
		int colCount = RF.colCount;

		String[][] inputMatrix = new String[rowCount][colCount];
		inputMatrix = RF.fileArray;
		return inputMatrix;
	}
	

}
