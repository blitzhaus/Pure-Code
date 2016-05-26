package view;

import java.util.Vector;

public class NPSSCAResOptionsTreeCreator {
	
	Vector createNodeStrPerSheet(String sheetName)
	{
		Vector sheetParentNode = createSheetDataParentNode();
		Vector resultsNode = createResultsNode();
		
		Object subNodes[] = { sheetParentNode, resultsNode };

		Vector rootVector = new NamedVector(
				sheetName, subNodes);
		
		return rootVector;
	}
	
	Vector createSheetDataParentNode()
	{
		CheckBoxNode[] sheetLeafNodes = createSheetLeafNode();
		Vector sheetParentNode = linkLNsToParentNode("Data", sheetLeafNodes);
	
		return sheetParentNode;
	}
	
	private CheckBoxNode[] createSheetLeafNode() {
		String[] textOutputLeafNodeTitles = new String[1];

		textOutputLeafNodeTitles[0] = "Data";

		CheckBoxNode[] sheetLeafNodes = createLeafNodeArray(textOutputLeafNodeTitles);

		return sheetLeafNodes;
	}
	
	private CheckBoxNode[] createLeafNodeArray(String[] leafNodelLabels) {
		CheckBoxNode[] leafNodeArrays = new CheckBoxNode[leafNodelLabels.length];

		for (int i = 0; i < leafNodeArrays.length; i++) {
			leafNodeArrays[i] = new CheckBoxNode(leafNodelLabels[i], false);
		}

		return leafNodeArrays;
	}
	
	private Vector createResultsNode()
	{
		//Vector txtOpNode = createTxtOpParentNode();
		Vector sheetsNode = createSheetsParentNode();
		Vector plotsNode = createPlotsNode();
		
		//Object subNodes[] = { txtOpNode, sheetsNode, plotsNode };
		Object subNodes[] = { sheetsNode, plotsNode };

		Vector rootVector = new NamedVector(
				"Results", subNodes);
		
		return rootVector;
	}
	
	private Vector createPlotsNode()
	{
		CheckBoxNode[] plotLeafNodes = createPlotLeafNodes();

		Vector rootVector = linkLNsToParentNode("Plots", plotLeafNodes);
		
		return rootVector;
	}
	
	private CheckBoxNode[] createPlotLeafNodes()
	{
		String[] linPlotTitles = new String[1];
		
		linPlotTitles[0] = "linear_plots";
		//linPlotTitles[1] = "loglinear_plots";
		
		CheckBoxNode[] plotLeafNodes = createLeafNodeArray(linPlotTitles);
		
		return plotLeafNodes;
	}
	
	Vector createTxtOpParentNode()
	{
		CheckBoxNode[] txtOPLeafNodes = createTextOutputLeafNodes();
		Vector txtOpParentNode = linkLNsToParentNode("Text Output", txtOPLeafNodes);
	
		return txtOpParentNode;
	}
	
	Vector linkLNsToParentNode(String parentLabel, CheckBoxNode[] leafNodeArrays)
	{
		Vector accessVector = new NamedVector(parentLabel,
				leafNodeArrays);

		return accessVector;
	}
	
	Vector createSheetsParentNode()
	{
		CheckBoxNode[] sheetsLeafNodes = createSheetsLeafNodes();
		Vector sheetsParentNode = linkLNsToParentNode("Sheets", sheetsLeafNodes);
		return sheetsParentNode;
	}
	
	private CheckBoxNode[] createSheetsLeafNodes()
	{
		String[] sheetSubNodeTitles = new String[1];
		
		sheetSubNodeTitles[0] = "Output";
		/*sheetSubNodeTitles[1] = "Summary_Table";
		sheetSubNodeTitles[2] = "Sub_Areas_Table";
		sheetSubNodeTitles[3] = "Dosing_Table";*/
			
		CheckBoxNode[] sheetsLeafNodes = createLeafNodeArray(sheetSubNodeTitles);
		
		return sheetsLeafNodes;
	}
	
	private CheckBoxNode[] createTextOutputLeafNodes() {
		String[] textOutputLeafNodeTitles = new String[1];

		textOutputLeafNodeTitles[0] = "Complete_Output";

		CheckBoxNode[] toLeafNodes = createLeafNodeArray(textOutputLeafNodeTitles);

		return toLeafNodes;
	}

}

