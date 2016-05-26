package view;

import java.util.Vector;

public class PlotOptionsTreeCreator {
	
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
	
		Vector plotsNode = createPlotsParentNode();
		
		Object subNodes[] = { plotsNode };

		Vector rootVector = new NamedVector(
				"Results", subNodes);
		
		return rootVector;
	}
	
	private Vector createPlotsNode()
	{
		CheckBoxNode[] linearLinearLeafNodes = createLinearLinearLeafNodes();
		CheckBoxNode[] linearLogLeafNodes = createLinearLogLeafNodes();
		CheckBoxNode[] logLogLeafNodes = createLogLogLeafNodes();

		Vector rootVector1 = linkLNsToParentNode("Plots", linearLinearLeafNodes);
		Vector rootVector2 = linkLNsToParentNode("Plots", linearLogLeafNodes);
		Vector rootVector3 = linkLNsToParentNode("Plots", logLogLeafNodes);
		
		Vector rootVector = new Vector();
		
		rootVector.add(rootVector1);
		rootVector.add(rootVector2);
		rootVector.add(rootVector3);
		
		return rootVector;
	}
	
	private CheckBoxNode[] createLinearLinearLeafNodes()
	{
		String[] linPlotTitles = new String[1];
		
		linPlotTitles[0] = "LinearX_LinearY";
		//linPlotTitles[1] = "loglinear_plots";
		
		CheckBoxNode[] plotLeafNodes = createLeafNodeArray(linPlotTitles);
		
		return plotLeafNodes;
	}
	
	private CheckBoxNode[] createLinearLogLeafNodes()
	{
		String[] linPlotTitles = new String[1];
		
		linPlotTitles[0] = "LinearX_LogY";
		//linPlotTitles[1] = "loglinear_plots";
		
		CheckBoxNode[] plotLeafNodes = createLeafNodeArray(linPlotTitles);
		
		return plotLeafNodes;
	}
	
	private CheckBoxNode[] createLogLogLeafNodes()
	{
		String[] linPlotTitles = new String[1];
		
		linPlotTitles[0] = "LogX_LogY";
			
		CheckBoxNode[] plotLeafNodes = createLeafNodeArray(linPlotTitles);
		
		return plotLeafNodes;
	}
	
	Vector createTxtOpParentNode()
	{
		CheckBoxNode[] txtOPLeafNodes = createTextOutputLeafNodes();
		Vector txtOpParentNode = linkLNsToParentNode("Text_Output", txtOPLeafNodes);
	
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
	
	Vector createPlotsParentNode()
	{
		CheckBoxNode[] sheetsLeafNodes = createPlotLeafNodes();
		Vector sheetsParentNode = linkLNsToParentNode("Plots", sheetsLeafNodes);
		return sheetsParentNode;
	}
	
	private CheckBoxNode[] createSheetsLeafNodes()
	{
		String[] sheetSubNodeTitles = new String[1];
		
		sheetSubNodeTitles[0] = "Text_Output";
	
		CheckBoxNode[] sheetsLeafNodes = createLeafNodeArray(sheetSubNodeTitles);
		
		return sheetsLeafNodes;
	}
	
	private CheckBoxNode[] createPlotLeafNodes()
	{
		String[] sheetSubNodeTitles = new String[3];
		
		sheetSubNodeTitles[0] = "LinearX_LinearY";
		sheetSubNodeTitles[1] = "LinearX_LogY";
		sheetSubNodeTitles[2] = "LogX_LogY";
					
		CheckBoxNode[] sheetsLeafNodes = createLeafNodeArray(sheetSubNodeTitles);
		
		return sheetsLeafNodes;
	}
	
	private CheckBoxNode[] createTextOutputLeafNodes() {
		String[] textOutputLeafNodeTitles = new String[1];

		textOutputLeafNodeTitles[0] = "Text_Output";

		CheckBoxNode[] toLeafNodes = createLeafNodeArray(textOutputLeafNodeTitles);

		return toLeafNodes;
	}



}
