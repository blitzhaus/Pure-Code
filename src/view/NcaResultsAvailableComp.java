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

public class NcaResultsAvailableComp {

	
	public NcaResultsAvailableComp(String dummy){
		
	}
	
	public static NcaResultsAvailableComp NCA_RES_AVAIL_GUI = null;
	public static NcaResultsAvailableComp createNcaResAvailCompInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(NCA_RES_AVAIL_GUI == null){
			NCA_RES_AVAIL_GUI = new NcaResultsAvailableComp("just object creation");
		}
		return NCA_RES_AVAIL_GUI;
	}
	
	public void NcaResultsAvailableCompCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createResultsTabAvailableOutputsInternalFrame();
	}
	
	JInternalFrame ResultsTabAvailableoutputInternalFrame;
	DefaultMutableTreeNode availableOutputs;
	JTree availableOutputsTree;
	public boolean isModelReload;
	
	private void createResultsTabAvailableOutputsInternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		ResultsTabAvailableoutputInternalFrame = new JInternalFrame("Output",
				false,false,false,false);
		ResultsTabAvailableoutputInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(ResultsTabAvailableoutputInternalFrame);
		ResultsTabAvailableoutputInternalFrame.setLocation(0, 0);
		int height = (int) (DDViewLayer.createMTInstance().mainTabbedFrame.getHeight() / 2);
		ResultsTabAvailableoutputInternalFrame.setSize(NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame.getWidth(), 
				NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame.getHeight());
		ResultsTabAvailableoutputInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		ResultsTabAvailableoutputInternalFrame.setVisible(true);
		NcaTabbedPanes.createNcaTabbedPaneInstance().resultsTabDesktopPane.add(ResultsTabAvailableoutputInternalFrame);
		ResultsTabAvailableoutputInternalFrame.moveToFront();
		availableOutputs = new DefaultMutableTreeNode("Output");
		DefaultMutableTreeNode textOutput = new DefaultMutableTreeNode(
				"Text output");
		availableOutputs.add(textOutput);
		DefaultMutableTreeNode completeOutput = new DefaultMutableTreeNode(
				"Complete output");
		textOutput.add(completeOutput);

		DefaultMutableTreeNode Tables = new DefaultMutableTreeNode("Sheets");
		availableOutputs.add(Tables);
		DefaultMutableTreeNode finalParametersNode = new DefaultMutableTreeNode(
				"Final Parameters");
		Tables.add(finalParametersNode);

		DefaultMutableTreeNode transposedFinalParameters = new DefaultMutableTreeNode(
				"Transposed Final Parameters");
		Tables.add(transposedFinalParameters);
		
		DefaultMutableTreeNode dosingTable = new DefaultMutableTreeNode("Dosing Table");
		Tables.add(dosingTable);
		
		DefaultMutableTreeNode subAreasTable = new DefaultMutableTreeNode("Sub Areas Table");
		Tables.add(subAreasTable);
		
		DefaultMutableTreeNode summaryTable = new DefaultMutableTreeNode("Summary Table");
		Tables.add(summaryTable);
		
		availableOutputsTree = new JTree(availableOutputs);

		availableOutputsTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		availableOutputsTree.addTreeSelectionListener(DDViewLayer.createViewLayerInstance());
		availableOutputsTree.addMouseListener(DDViewLayer.createViewLayerInstance());
		JScrollPane availableComponentsScrollPane = new JScrollPane(
				availableOutputsTree);
		ResultsTabAvailableoutputInternalFrame.getContentPane().add(
				availableComponentsScrollPane);

	}
}
