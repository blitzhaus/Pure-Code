package view;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import Common.JinternalFrameFunctions;

public class NPSResultsAvailComp {

	public static NPSResultsAvailComp NPS_RES_AVAIL = null;
	public static NPSResultsAvailComp createNPSResAvailInst(){
		if(NPS_RES_AVAIL == null){
			NPS_RES_AVAIL = new NPSResultsAvailComp("Just object creation");
		}
		return NPS_RES_AVAIL;
	}
	
	public NPSResultsAvailComp(String dummy){
	}
	
	JInternalFrame ResultsTabAvailableoutputInternalFrame;
	 DefaultMutableTreeNode availableOutputs;
	 JTree availableOutputsTree;
	 int selectedImage;
	
	void createResultsTabAvailableOutputsInternalFrame() {
		ResultsTabAvailableoutputInternalFrame = new JInternalFrame("Output",
				true, true, true, true);
		ResultsTabAvailableoutputInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(ResultsTabAvailableoutputInternalFrame);
		ResultsTabAvailableoutputInternalFrame.setLocation(0, 0);
		int height = (int) (DDViewLayer.createMTInstance().mainTabbedFrame.getHeight() / 2);
		ResultsTabAvailableoutputInternalFrame.setSize(
				DDViewLayer.createMTInstance().mainTabbedFrame.getWidth() / 4, height);
		ResultsTabAvailableoutputInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		ResultsTabAvailableoutputInternalFrame.setVisible(true);
		NPSTabs.createNPSTabsInst().resultsTabDesktopPane.add(ResultsTabAvailableoutputInternalFrame);
		ResultsTabAvailableoutputInternalFrame.moveToFront();
		availableOutputs = new DefaultMutableTreeNode("output");
/*		DefaultMutableTreeNode textOutput = new DefaultMutableTreeNode(
				"Text output");
		availableOutputs.add(textOutput);*/
		/*DefaultMutableTreeNode completeOutput = new DefaultMutableTreeNode(
				"Complete output");
		textOutput.add(completeOutput);*/

		DefaultMutableTreeNode Tables = new DefaultMutableTreeNode("Sheets");
		availableOutputs.add(Tables);
		DefaultMutableTreeNode finalParametersNode = new DefaultMutableTreeNode(
				"Final Output");
		Tables.add(finalParametersNode);

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
