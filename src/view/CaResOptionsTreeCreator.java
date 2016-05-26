package view;

import java.util.Vector;

public class CaResOptionsTreeCreator {
	
	Vector createNodeStrPerSheet(String sheetName)
	{
		Vector sheetParentNode = createSheetDataParentNode();
		Vector resultsNode = createResultsNode();

		Object subNodes[] = { sheetParentNode, resultsNode };

		Vector rootVector = new NamedVector(
				sheetName, subNodes);

		return rootVector;
	}
	
	private CheckBoxNode[] createSheetsLeafNodes()
	{
		String[] sheetSubNodeTitles = new String[16];

		sheetSubNodeTitles[0] = "Initial_Parameter";
		sheetSubNodeTitles[1] = "Minimization_Process";
		sheetSubNodeTitles[2] = "Final_Parameters";
		sheetSubNodeTitles[3] = "Non_Transposed_Final_Parameter";
		//sheetSubNodeTitles[4] = "Dosing";
		sheetSubNodeTitles[4] = "Correlation_Matrix";
		sheetSubNodeTitles[5] = "Eigen_Values";
		sheetSubNodeTitles[6] = "Condition_Number";
		sheetSubNodeTitles[7] = "Predicted_Value";
		sheetSubNodeTitles[8] = "Variance_Covariance_Matrix";
		sheetSubNodeTitles[9] = "Summary_Table";
		sheetSubNodeTitles[10] = "Diagnostics";
		sheetSubNodeTitles[11] = "Partial_Derivative";
		sheetSubNodeTitles[12] = "Secondary_Parameters";
		sheetSubNodeTitles[13] = "Non_Transposed_Secondary_Parameter";
		//sheetSubNodeTitles[15] = "User_Settings";
		sheetSubNodeTitles[15] = "History";

		CheckBoxNode[] sheetsLeafNodes = createLeafNodeArray(sheetSubNodeTitles);

		return sheetsLeafNodes;
	}
	
	Vector createSheetsParentNode()
	{
		CheckBoxNode[] sheetsLeafNodes = createSheetsLeafNodes();
		Vector sheetsParentNode = linkLNsToParentNode("Sheets", sheetsLeafNodes);
		return sheetsParentNode;
	}
	
	private CheckBoxNode[] createPlotLeafNodes()
	{
		String[] linPlotTitles = new String[2];

		linPlotTitles[0] = "linear_plots";
		linPlotTitles[1] = "loglinear_plots";

		CheckBoxNode[] plotLeafNodes = createLeafNodeArray(linPlotTitles);

		return plotLeafNodes;
	}
	
	private Vector createObservedYVsWeightedPredictedY()
	{
		CheckBoxNode[] plotLeafNodes = createPlotLeafNodes();
		Vector obsYWtdPredY = linkLNsToParentNode("Observed Y Vs. Weighted Predicted Y", plotLeafNodes);

		return obsYWtdPredY;
	}
	
	private Vector createPlotsNode()
	{
		Vector xVsObsYPredY = createXVsObsPredYNode();
		Vector obsYVsWtdPredY = createObservedYVsWeightedPredictedY();
		Vector wtdPredYVsWtdResY = createWeightedPredictedYVsWeightedResidualY();
		Vector xVsWeightedResidualY = createXVsWeightedResidualY();
		Vector partialDerPlot = createPartialDerivativePlot();

		Object subNodes[] = { xVsObsYPredY, obsYVsWtdPredY, wtdPredYVsWtdResY,
				xVsWeightedResidualY, partialDerPlot };

		Vector rootVector = new NamedVector(
				"Plots ", subNodes);

		return rootVector;
	}
	
	private Vector createXVsWeightedResidualY()
	{
		CheckBoxNode[] plotLeafNodes = createPlotLeafNodes();
		Vector xVsWtdResY = linkLNsToParentNode("X Vs. Weighted Residual Y", plotLeafNodes);

		return xVsWtdResY;
	}
	
	private Vector createPartialDerivativePlot()
	{
		CheckBoxNode[] plotLeafNodes = createPlotLeafNodes();
		Vector partDerPlotNode = linkLNsToParentNode("Partial Derivative Plot", plotLeafNodes);

		return partDerPlotNode;
	}
	
	private Vector createWeightedPredictedYVsWeightedResidualY()
	{
		CheckBoxNode[] plotLeafNodes = createPlotLeafNodes();
		Vector wtdPredYWtdResY = linkLNsToParentNode("Weighted Predicted Y Vs. Weighted Residual Y", plotLeafNodes);

		return wtdPredYWtdResY;
	}

	
	private Vector createXVsObsPredYNode()
	{
		CheckBoxNode[] plotLeafNodes = createPlotLeafNodes();
		Vector xVsObsPredY = linkLNsToParentNode("X vs. Observed and Predicted Y", plotLeafNodes);

		return xVsObsPredY;
	}
	
	private Vector createResultsNode()
	{
		Vector txtOpNode = createTxtOpParentNode();
		Vector sheetsNode = createSheetsParentNode();
		Vector plotsNode = createPlotsNode();

		Object subNodes[] = { txtOpNode, sheetsNode, plotsNode };

		Vector rootVector = new NamedVector(
				"Results", subNodes);

		return rootVector;
	}
	
	private CheckBoxNode[] createTextOutputLeafNodes() {
		String[] textOutputLeafNodeTitles = new String[1];

		textOutputLeafNodeTitles[0] = "Complete_Output";

		CheckBoxNode[] toLeafNodes = createLeafNodeArray(textOutputLeafNodeTitles);

		return toLeafNodes;
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
			leafNodeArrays[i] = new CheckBoxNode(leafNodelLabels[i], true);
		}

		return leafNodeArrays;
	}


}

