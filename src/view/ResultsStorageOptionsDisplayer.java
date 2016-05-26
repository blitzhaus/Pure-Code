package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.apache.commons.lang.StringUtils;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;



import Common.ExtensionFileFilter;
import Common.MyHandler;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;



import orinoco.Alignment;
//import orinoco.Document;
import orinoco.Colour;
import orinoco.Column;
import orinoco.Heading;

import org.apache.commons.io.FileUtils;
import org.codecompany.jeha.ExceptionHandler;

public class ResultsStorageOptionsDisplayer {
	
	//orinoco.Document doc = null;

	JTree tree = null;
	JTextField tfFileName = null;

	int analysisNodesCount = 0;
	Object[] rootNodes = null;
	int nodeIndexer = 0;

	private CheckBoxNode[] createLeafNodeArray(String[] leafNodelLabels) {
		CheckBoxNode[] leafNodeArrays = new CheckBoxNode[leafNodelLabels.length];

		for (int i = 0; i < leafNodeArrays.length; i++) {
			leafNodeArrays[i] = new CheckBoxNode(leafNodelLabels[i], true);
		}

		return leafNodeArrays;
	}

	Vector linkLNsToParentNode(String parentLabel, CheckBoxNode[] leafNodeArrays)
	{
		Vector accessVector = new NamedVector(parentLabel,
				leafNodeArrays);

		return accessVector;
	}


	Vector linkParNodesToGrandPar(String grandParentLabel, Object[] parentNodes)
	{
		Vector gpVector = new NamedVector(grandParentLabel,
				parentNodes);

		return gpVector;
	}

	public void caResultoptionsTree(ApplicationInfo appInfo) throws IOException, DocumentException
	{
		DefaultMutableTreeNode rootNode = DDViewLayer.createPEInstance().workSpace;
		JTree peTree = new JTree(rootNode);
		/* true for nodeCount
		* false for tree creation.
		*/
		traverseProjectExplorerTree(peTree, true);
		rootNodes = new Object[analysisNodesCount];
		traverseProjectExplorerTree(peTree, false);
	}

	/*public Object[] caResultoptionsTree(ApplicationInfo appInfo)
	{
		ApplicationInfo appInfo1 = DisplayContents.createAppInfoInstance();
		int sheetsCount = appInfo1.getWorkSheetObjectsAL().size();
		//int sheetsCount = 1;
		//int sheetsCount = 4;

		Object[] nodePerSheet = new Object[sheetsCount];

		String[] sheetsNames = new String[sheetsCount];

		for (int i = 0; i < sheetsCount; i++) {
			sheetsNames[i] = appInfo1.getWorkSheetObjectsAL().get(i).getSheetName();
			//sheetsNames[i] = "sheet"+i;
			nodePerSheet[i] = createNodeStrPerSheet(sheetsNames[i]);
		}

		return nodePerSheet;
	}*/




	private CheckBoxNode[] createSheetLeafNode() {
		String[] textOutputLeafNodeTitles = new String[1];

		textOutputLeafNodeTitles[0] = "Data";

		CheckBoxNode[] sheetLeafNodes = createLeafNodeArray(textOutputLeafNodeTitles);

		return sheetLeafNodes;
	}

	Vector createSheetDataParentNode()
	{
		CheckBoxNode[] sheetLeafNodes = createSheetLeafNode();
		Vector sheetParentNode = linkLNsToParentNode("Data", sheetLeafNodes);

		return sheetParentNode;
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

	static void addImageToDoc(String outputPdfName, String jpegName) throws DocumentException, MalformedURLException, IOException, com.itextpdf.text.DocumentException {

	    try {
	      PdfReader pdfReader = new PdfReader("dummy2.pdf");

	      PdfStamper pdfStamper = new PdfStamper(pdfReader,
	            new FileOutputStream(outputPdfName));

	      com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(jpegName);

	      for(int i=1; i<= pdfReader.getNumberOfPages(); i++){

	          PdfContentByte content = pdfStamper.getUnderContent(i);

	          image.setAbsolutePosition(0f, 0f);

	          content.addImage(image);
	      }

	      pdfStamper.close();

	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	}

	private CheckBoxNode[] createSheetsLeafNodes()
	{
		String[] sheetSubNodeTitles = new String[16];

		sheetSubNodeTitles[0] = "Initial_Parameter";
		sheetSubNodeTitles[1] = "Minimization_Process";
		sheetSubNodeTitles[2] = "Final_Parameters";
		sheetSubNodeTitles[3] = "Non_Transposed_Final_Parameter";
		sheetSubNodeTitles[4] = "Dosing";
		sheetSubNodeTitles[5] = "Correlation_Matrix";
		sheetSubNodeTitles[6] = "Eigen_Values";
		sheetSubNodeTitles[7] = "Condition_Number";
		sheetSubNodeTitles[8] = "Predicted_Value";
		sheetSubNodeTitles[9] = "Variance_Covariance_Matrix";
		sheetSubNodeTitles[10] = "Summary_Table";
		sheetSubNodeTitles[11] = "Diagnostics";
		sheetSubNodeTitles[12] = "Partial_Derivative";
		sheetSubNodeTitles[13] = "Secondary_Parameters";
		sheetSubNodeTitles[14] = "Non_Transposed_Secondary_Parameter";
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

	private Vector createXVsObsPredYNode()
	{
		CheckBoxNode[] plotLeafNodes = createPlotLeafNodes();
		Vector xVsObsPredY = linkLNsToParentNode("X vs. Observed and Predicted Y", plotLeafNodes);

		return xVsObsPredY;
	}

	private Vector createObservedYVsWeightedPredictedY()
	{
		CheckBoxNode[] plotLeafNodes = createPlotLeafNodes();
		Vector obsYWtdPredY = linkLNsToParentNode("Observed Y Vs. Weighted Predicted Y", plotLeafNodes);

		return obsYWtdPredY;
	}


	private Vector createWeightedPredictedYVsWeightedResidualY()
	{
		CheckBoxNode[] plotLeafNodes = createPlotLeafNodes();
		Vector wtdPredYWtdResY = linkLNsToParentNode("Weighted Predicted Y Vs. Weighted Residual Y", plotLeafNodes);

		return wtdPredYWtdResY;
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

	//Vector createSheetDataParentNode()

	Vector createNodeStrPerSheet(String sheetName)
	{
		Vector sheetParentNode = createSheetDataParentNode();
		Vector resultsNode = createResultsNode();

		Object subNodes[] = { sheetParentNode, resultsNode };

		Vector rootVector = new NamedVector(
				sheetName, subNodes);

		return rootVector;
	}
	
	@ExceptionHandler(handler = MyHandler.class)
	public String saveFile(JFileChooser chooser) {

		File selectedPfile = null;

		
		FileFilter textFileFilter = new ExtensionFileFilter("Pdf files",
				new String[] { "*.pdf" });
		chooser.setFileFilter(textFileFilter);
		int retVal = chooser.showSaveDialog(null);
		String path = null;
		if (retVal==JFileChooser.APPROVE_OPTION)
		{
			selectedPfile = chooser.getSelectedFile();
			path = selectedPfile.getPath();
		}
		
		return path;
	}	


	void createResultsOptionsTree() throws IOException, DocumentException
	{
		previousSettingsCleanUp();		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		final JFrame frame = new JFrame("Analysis Results Storage Options");
		caResultoptionsTree(appInfo);
		Vector rootVector = new NamedVector("Analysis Results", rootNodes);
		tree = new JTree(rootVector);
		tree.setRootVisible(true);
		tree.setName("Analysis");

		CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
		tree.setCellRenderer(renderer);

		tree.setCellEditor(new CheckBoxNodeEditor(tree));
		tree.setEditable(true);

		JButton btnPrintJTree = new JButton("Export As PDF");
		JLabel lblFileName = new JLabel("Enter the File Name");

		tfFileName = new JTextField();
		tfFileName.setColumns(20);

		File f = new File(".\\linearplots");
		f.mkdir();
		
		final JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		btnPrintJTree.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				String fileName = saveFile(chooser);
				
				try {
					try {
						traverseTree(tree, fileName);// this tree is for exporting to pdf.
						frame.dispose();
				
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		JPanel pnlForPrintTreebtn = new JPanel();

		//pnlForPrintTreebtn.add(lblFileName);
		//pnlForPrintTreebtn.add(tfFileName);
		pnlForPrintTreebtn.add(btnPrintJTree);

		JScrollPane scrollPane = new JScrollPane(tree);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane().add(pnlForPrintTreebtn, BorderLayout.SOUTH);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		frame.setSize((int)(width * 0.7), (int)(height * 0.7));
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	private static ArrayList<String> convertStrArrayToArrayList(String[][] strArray)
	{
		ArrayList<String> retLines = new ArrayList<String>();
		for (int j = 0; j < strArray.length; j++) {
			String line = StringUtils.join(strArray[j],"\t\t\t");
			retLines.add(line);
		}

		return retLines;
	}

	public static void main(String args[]) {
	}
	
	void previousSettingsCleanUp()
	{
		 File directory = new File(".\\linearplots");

         /** Deletes a directory recursively. When deletion process is fail an
         * IOException is thrown and that's why we catch the exception.*/

		 try {
			 if (directory.exists())
			 {
				 FileUtils.deleteDirectory(directory);
			 }
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	static void traverseTree(JTree tree, String opFileName) throws IOException, DocumentException, com.itextpdf.text.DocumentException {
		TreeModel model = tree.getModel();
		Object root = model.getRoot();
		PdfExporter pdfExporter = new PdfExporter();
		//pdfExporter.initializePdfWriterSetup(opFileName, opFileName);

		walk(model, root, pdfExporter);

		//pdfExporter.closeTheDocument();

		CombineDynamicPdfContents cdpContents = new CombineDynamicPdfContents();

		cdpContents.convertJPegFilesToPdf(".\\linearplots", opFileName);
		cdpContents.consolidateAllPdfFiles(".\\linearplots", opFileName);

		 //File directory = new File(".\\linearplots");

         /** Deletes a directory recursively. When deletion process is fail an
         * IOException is thrown and that's why we catch the exception.*/

		 try {
			// FileUtils.deleteDirectory(directory);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	void traverseProjectExplorerTree(JTree tree, boolean forNodeCount) throws IOException, DocumentException, com.itextpdf.text.DocumentException {
		TreeModel model = tree.getModel();
		Object root = model.getRoot();
		walkProjectTree(model, root, forNodeCount);
	}



	public static void saveChartAsJPEG(File file,JFreeChart chart,int width,int height) throws IOException
    {
    	OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
    	ChartUtilities.writeChartAsJPEG(out, chart, width, height);
    	out.close();
    }

	void walkProjectTree(TreeModel model, Object o, boolean forNodeCount)
	{
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		int count = model.getChildCount(o);
		for (int i = 0; i < count; i++) {
			Object child = model.getChild(o, i);

			if (model.isLeaf(child))
			{
				System.out.println("Sivaram  trace.." + child.toString());
				String leafDetail = child.toString();

				TreeNode tNode = (TreeNode) child;

			if ((leafDetail.startsWith("PK-") == true))
				{
					System.out.println("Compartmental Analysis..Clicked" );
					if (forNodeCount == true)
					{
						analysisNodesCount++;
						//String projName = extractProject(tNode);
						String toCreateFilePath = extractDirectoryPath1(tNode);
						//String toCreateFilePath = "./linearplots/"+projName+"/"+leafDetail;
						File f = new File(toCreateFilePath);
						if (f.exists()!= true)
							f.mkdirs();
						
						System.out.println("directory structure created...");
					}
					else
					{
						// tree creation
						Vector subTreeNodes	= createNodeStrPerSheet(leafDetail);
						Object subNodes[] = { subTreeNodes };
						String projName = extractProject(tNode);
						Vector rootVector = new NamedVector(
								projName, subNodes);
						rootNodes[nodeIndexer] = rootVector;
						nodeIndexer++;
					}
				}
				else if ((leafDetail.startsWith("NCA-") == true))
				{
					System.out.println("NC Analysis..Clicked" );

					if (forNodeCount == true)
					{
						analysisNodesCount++;
						//String projName = extractProject(tNode);
						String toCreateFilePath = extractDirectoryPath1(tNode);
						//String toCreateFilePath = "./linearplots/"+projName+"/"+leafDetail;
						File f = new File(toCreateFilePath);
						if (f.exists()!= true)
							f.mkdirs();
						
						System.out.println("directory structure created...");
					}
					else
					{
						// tree creation

						NCAResOptionsTreeCreator ncaResOptionsTreeCreator = new NCAResOptionsTreeCreator();
						Vector subTreeNodes	= ncaResOptionsTreeCreator.createNodeStrPerSheet(leafDetail);
						Object subNodes[] = { subTreeNodes };
						String projName = extractProject(tNode);
						Vector rootVector = new NamedVector(
								projName, subNodes);
						rootNodes[nodeIndexer] = rootVector;
						nodeIndexer++;
					}
				}
				else if ((leafDetail.startsWith("DS-") == true))
				{
					System.out.println("Descriptive Stat..Clicked" );

					if (forNodeCount == true)
					{
						analysisNodesCount++;
						//String projName = extractProject(tNode);
						String toCreateFilePath = extractDirectoryPath1(tNode);
						//String toCreateFilePath = "./linearplots/"+projName+"/"+leafDetail;
						File f = new File(toCreateFilePath);
						if (f.exists()!= true)
							f.mkdirs();
						
						System.out.println("directory structure created...");
					}
					else
					{
						// tree creation
						DSResOptionsTreeCreator dsResOptionsTreeCreator = new DSResOptionsTreeCreator();
						//rootNodes[nodeIndexer] = 
							
						Vector subTreeNodes	= dsResOptionsTreeCreator.createNodeStrPerSheet(leafDetail);
						
						Object subNodes[] = { subTreeNodes };
						
						String projName = extractProject(tNode);

						Vector rootVector = new NamedVector(
								projName, subNodes);

						rootNodes[nodeIndexer] = rootVector;
						
						nodeIndexer++;
					}
				}
				else if ((leafDetail.startsWith("SCA-") == true))
				{
					System.out.println("Semi Compartmental Analysis..Clicked" );
					if (forNodeCount == true)
					{
						analysisNodesCount++;
						//String projName = extractProject(tNode);
						String toCreateFilePath = extractDirectoryPath1(tNode);
						//String toCreateFilePath = "./linearplots/"+projName+"/"+leafDetail;
						File f = new File(toCreateFilePath);
						if (f.exists()!= true)
							f.mkdirs();
						
						System.out.println("directory structure created...");
					}
					else
					{
						// tree creation

						NPSSCAResOptionsTreeCreator npsResOptionsTreeCreator = new NPSSCAResOptionsTreeCreator();
						Vector subTreeNodes	= npsResOptionsTreeCreator.createNodeStrPerSheet(leafDetail);
						
						Object subNodes[] = { subTreeNodes };
						
						String projName = extractProject(tNode);

						Vector rootVector = new NamedVector(
								projName, subNodes);
						rootNodes[nodeIndexer] = rootVector;
						nodeIndexer++;

					}
				}
				else if ((leafDetail.startsWith("NPS-") == true))
				{
					System.out.println("Non Parametric Superposition..Clicked" );
					if (forNodeCount == true)
					{
						analysisNodesCount++;
						//String projName = extractProject(tNode);
						String toCreateFilePath = extractDirectoryPath1(tNode);
						//String toCreateFilePath = "./linearplots/"+projName+"/"+leafDetail;
						File f = new File(toCreateFilePath);
						if (f.exists()!= true)
							f.mkdirs();
						
						System.out.println("directory structure created...");
					}
					else
					{
						// tree creation
						
						NPSSCAResOptionsTreeCreator npsResOptionsTreeCreator = new NPSSCAResOptionsTreeCreator();
						Vector subTreeNodes	= npsResOptionsTreeCreator.createNodeStrPerSheet(leafDetail);
						
						Object subNodes[] = { subTreeNodes };
						String projName = extractProject(tNode);
						Vector rootVector = new NamedVector(
								projName, subNodes);
						rootNodes[nodeIndexer] = rootVector;
						nodeIndexer++;
					}
				}
				else if ((leafDetail.startsWith("MM-") == true)
						|| (leafDetail.startsWith("IR-") == true)
						|| (leafDetail.startsWith("PKPDLINK-") == true))
				{
					System.out.println("Non Parametric Superposition..Clicked" );
					if (forNodeCount == true)
					{
						analysisNodesCount++;
						//String projName = extractProject(tNode);
						String toCreateFilePath = extractDirectoryPath1(tNode);
						//String toCreateFilePath = "./linearplots/"+projName+"/"+leafDetail;
						File f = new File(toCreateFilePath);
						if (f.exists()!= true)
							f.mkdirs();
						
						System.out.println("directory structure created...");
					}
					else
					{
						// tree creation
						MMPKPDLINKIRResOptionsTreeCreator npsResOptionsTreeCreator = new MMPKPDLINKIRResOptionsTreeCreator();
						Vector subTreeNodes	= npsResOptionsTreeCreator.createNodeStrPerSheet(leafDetail);
						
						Object subNodes[] = { subTreeNodes };
						String projName = extractProject(tNode);
						Vector rootVector = new NamedVector(
								projName, subNodes);
						rootNodes[nodeIndexer] = rootVector;
						nodeIndexer++;
					}
				}
				else if ((leafDetail.startsWith("PD-") == true))
				{

					System.out.println("PharmacoDynamic models..Clicked" );
					if (forNodeCount == true)
					{
						analysisNodesCount++;
						//String projName = extractProject(tNode);
						String toCreateFilePath = extractDirectoryPath1(tNode);
						//String toCreateFilePath = "./linearplots/"+projName+"/"+leafDetail;
						File f = new File(toCreateFilePath);
						if (f.exists()!= true)
							f.mkdirs();
						
						System.out.println("directory structure created...");
					}
					else
					{
						// tree creation
						CaResOptionsTreeCreator npsResOptionsTreeCreator = new CaResOptionsTreeCreator();
						Vector subTreeNodes	= npsResOptionsTreeCreator.createNodeStrPerSheet(leafDetail);
						
						Object subNodes[] = { subTreeNodes };
						String projName = extractProject(tNode);
						Vector rootVector = new NamedVector(
								projName, subNodes);
						rootNodes[nodeIndexer] = rootVector;
						nodeIndexer++;
					}
				
				}
				else if ((leafDetail.startsWith("TM-") == true))
				{

					System.out.println("Table Maven..Clicked" );
					if (forNodeCount == true)
					{
						analysisNodesCount++;
						//String projName = extractProject(tNode);
						String toCreateFilePath = extractDirectoryPath1(tNode);
						//String toCreateFilePath = "./linearplots/"+projName+"/"+leafDetail;
						File f = new File(toCreateFilePath);
						if (f.exists()!= true)
							f.mkdirs();
						
						System.out.println("directory structure created...");
					}
					else
					{
						// tree creation
						
						TMOptionsTreeCreator npsResOptionsTreeCreator = new TMOptionsTreeCreator();
						Vector subTreeNodes	= npsResOptionsTreeCreator.createNodeStrPerSheet(leafDetail);
						
						Object subNodes[] = { subTreeNodes };
						String projName = extractProject(tNode);
						Vector rootVector = new NamedVector(
								projName, subNodes);
						rootNodes[nodeIndexer] = rootVector;
						nodeIndexer++;
					}				
				}
				else if ((leafDetail.startsWith("BQL-") == true))
				{


					System.out.println("BQL Tab..Clicked" );
					if (forNodeCount == true)
					{
						analysisNodesCount++;
						//String projName = extractProject(tNode);
						String toCreateFilePath = extractDirectoryPath1(tNode);
						//String toCreateFilePath = "./linearplots/"+projName+"/"+leafDetail;
						File f = new File(toCreateFilePath);
						if (f.exists()!= true)
							f.mkdirs();
						
						System.out.println("directory structure created...");
					}
					else
					{
						// tree creation
						
						BQLOptionsTreeCreator npsResOptionsTreeCreator = new BQLOptionsTreeCreator();
						Vector subTreeNodes	= npsResOptionsTreeCreator.createNodeStrPerSheet(leafDetail);
						
						Object subNodes[] = { subTreeNodes };
						String projName = extractProject(tNode);
						Vector rootVector = new NamedVector(
								projName, subNodes);
						rootNodes[nodeIndexer] = rootVector;
						nodeIndexer++;
					}				
				}
				else if ((leafDetail.startsWith("Plot-") == true))
				{
					System.out.println("Plot Tab..Clicked" );
					if (forNodeCount == true)
					{
						analysisNodesCount++;
						//String projName = extractProject(tNode);
						String toCreateFilePath = extractDirectoryPath1(tNode);
						//String toCreateFilePath = "./linearplots/"+projName+"/"+leafDetail;
						File f = new File(toCreateFilePath);
						if (f.exists()!= true)
							f.mkdirs();
						
						System.out.println("directory structure created...");
					}
					else
					{
						// tree creation
						
						PlotOptionsTreeCreator npsResOptionsTreeCreator = new PlotOptionsTreeCreator();
						Vector subTreeNodes	= npsResOptionsTreeCreator.createNodeStrPerSheet(leafDetail);
						
						Object subNodes[] = { subTreeNodes };
						String projName = extractProject(tNode);
						Vector rootVector = new NamedVector(
								projName, subNodes);
						rootNodes[nodeIndexer] = rootVector;
						nodeIndexer++;
					}
				}
			}			
			else
			{
				walkProjectTree(model, child, forNodeCount);
			}
		}

	}
	
	private static String replaceDotWith(String str)
	{
		return str.replace('.','_');
	}
	
	private static String replaceSpaceWith(String str)
	{
		return str.replace("\\s","_");
	}
	
	private static String extractDirectoryPath1(TreeNode tNode) {


		TreePath treePath = TreeUtilities.getPath(tNode);
		Object[] pathComps = treePath.getPath();
		
		//Object[] relPathComps = new Object[pathComps.length];
		
		Object[] relPathComps = new Object[2];
		
		relPathComps[0] = pathComps[1].toString();
		relPathComps[1] = pathComps[pathComps.length-1].toString();
		
		String dirPath = "./linearplots";
		
		relPathComps[1] = replaceDotWith(relPathComps[1].toString());
		relPathComps[1] = replaceSpaceWith(relPathComps[1].toString());
		
		for (int j = 0; j < relPathComps.length; j++) {
			dirPath += "/"+relPathComps[j];
		}
		return dirPath;
	}
	
	private static String extractDirectoryPath2(TreeNode tNode) {


		TreePath treePath = TreeUtilities.getPath(tNode);
		Object[] pathComps = treePath.getPath();
		
		Object[] relPathComps = new Object[2];
		
		relPathComps[0] = pathComps[1].toString().substring(1,pathComps[1].toString().length()-1);
		relPathComps[1] = pathComps[2].toString().substring(1,pathComps[2].toString().length()-1);
		
		relPathComps[1] = replaceDotWith(relPathComps[1].toString());
		relPathComps[1] = replaceSpaceWith(relPathComps[1].toString());
		
		String dirPath = "./linearplots";
		
		for (int j = 0; j < relPathComps.length; j++) {
			dirPath += "/"+relPathComps[j];
		}
		
		
		return dirPath;
	}

	private String[][] getInitialParameters(String analysisType)
	{
		String[][] initParams = null;
		
		return initParams;
		
	}

	/**
	 * Visit every node in the tree and print the leaf nodes.
	 *
	 * @param model
	 *            the JTree's model
	 * @param o
	 *            Start at this branch in the tree.
	 * @throws IOException
	 * @throws DocumentException
	 * @throws com.itextpdf.text.DocumentException
	 */
	private static void walk(TreeModel model, Object o, PdfExporter pdfExporter) throws IOException, DocumentException, com.itextpdf.text.DocumentException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();


		int count = model.getChildCount(o);
		for (int i = 0; i < count; i++) {
			Object child = model.getChild(o, i);


			if (model.isLeaf(child))
			{
				System.out.println("Sivaram  trace.." + child.toString());
				String leafDetail = child.toString();

				TreeNode tNode = (TreeNode) child;

				int startIdx = leafDetail.indexOf("[");
				int endIdx = leafDetail.indexOf("/");
				String plotLeafDetail = leafDetail.substring(startIdx+1, endIdx);

				if ((leafDetail.contains("BQL_Transformed") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("BQL_Transformed..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("BQL_Transformed"+treePath.toString());
					
					
					String[][] bqlTransformedData = extractBQLTransformedData(appInfo, tNode);
					String specPath = extractPath(tNode);
					//	String dirPath = derivePdfsPath(tNode);
					
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "BQL_Transformed.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"bql transformed data", bqlTransformedData[0],
							bqlTransformedData);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Data") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Data..Clicked" );
					
					String project = extractProject(tNode);
					
					//String dirPath = derivePdfsPath(tNode);
					
					String dirPath = extractDirectoryPath2(tNode);
					
					String projName = extractProjectName(tNode);
					int projIdx = appInfo.getProjectIdByName(projName);

					String[][] data = appInfo.getProjectInfoAL().get(projIdx)
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex()).getDataStructuresArrayList().get(
					appInfo.getProjectInfoAL().get(projIdx)
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex()).getSelectedSheetIndex());
					
					ArrayList<String> dataLines = convertStrArrayToArrayList(data);
					//pdfExporter.writeLinesTpPdf("Data", dataLines);
					String specPath = extractPath(tNode);
					System.out.println(dirPath);
					
					//System.exit(1);

					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "data.pdf");

					pdfExporter1.printOrinocoTableForPdf(specPath+"Data", data[0],
							data);

					pdfExporter1.closeTheDocument();

				}
				else if ((leafDetail.contains("Complete_Output") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Complete_Output..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Complete_Output.Clicked"+treePath.toString());
					
					String analysis = extractAnalysis(tNode);
					
					ArrayList<String> completeDataLines = extractCompleteDataOutput(appInfo, tNode, analysis);
					//pdfExporter.writeLinesTpPdf("Complete_Output", completeDataLines);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					
					System.out.println("blahblah");
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Complete_Output.pdf");
					pdfExporter1.writeLinesTpPdf(specPath+"Complete_Output", completeDataLines);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Initial_Parameter") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Initial_Parameter..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Initial_Parameter.Clicked"+treePath.toString());
					
					String analysis = extractAnalysis(tNode);

					String[][] parameterList = extractInitialParameters(appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Initial_Parameter.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"parameterList", parameterList[0],
							parameterList);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Minimization_Process") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Minimization_Process..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Minimization_Process.Clicked"+treePath.toString());
					
					String analysis = extractAnalysis(tNode);

					String[][] minProcessForCA = extractMinProcessForCA(appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Minimization_Process.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"minProcessForCA", minProcessForCA[0],
							minProcessForCA);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Final_Parameters") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Final_Parameters..Clicked" );
					
					String analysis = extractAnalysis(tNode);

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Final_Parameters.Clicked"+treePath.toString());

					String[][] finalParameters = extractFinalParamsForCA(appInfo, tNode, analysis);
					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Final_Parameters.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"finalParameters", finalParameters[0],
							finalParameters);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Non_Transposed_Final_Parameter") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Non_Transposed_Final_Parameters..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Non_Transposed_Final_Parameters....Clicked"+treePath.toString());
					String analysis = extractAnalysis(tNode);
					String[][] horFinalParameters = extractNonTransFinalParams(appInfo, tNode, analysis);
					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Non_Transposed_Final_Parameter.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Non_Transposed_Final_Parameters", horFinalParameters[0],
							horFinalParameters);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.equals("Descriptive_Statistics") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Descriptive_Statistics..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Descriptive_Statistics....Clicked"+treePath.toString());

					String[][] dosingData = extractDescriptiveStatistics(appInfo, tNode);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Descriptive_Statistics.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Descriptive_Statistics", dosingData[0],
							dosingData);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Correlation_Matrix") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Correlation_Matrix..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Correlation_Matrix....Clicked"+treePath.toString());
					
					String analysis = extractAnalysis(tNode);

					String[][] corrMatrix = extractCorrMatrixForCA(appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Correlation_Matrix.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Correlation_Matrix", corrMatrix[0],
							corrMatrix);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Eigen_Values") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Eigen_Values..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Eigen_Values....Clicked"+treePath.toString());
					
					String analysis = extractAnalysis(tNode);

					String[][] eigenValues = extractEigenValuesForCA(appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Eigen_Values.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Eigen Values", eigenValues[0],
							eigenValues);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Condition_Number") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Condition_Number..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Condition_Number....Clicked"+treePath.toString());
					
					String analysis = extractAnalysis(tNode);

					String[][] condnNumber = extractConditionNumberForCA(appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Condition_Number.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Condition Number", condnNumber[0],
							condnNumber);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Predicted_Value") == true)
						&& (leafDetail.contains("true") == true))
				{
					

					TreePath treePath = TreeUtilities.getPath(tNode);
					String analysis = extractAnalysis(tNode);

					String[][] predictedValues = extractPredictedValsForCA(appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Predicted_Value.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Predicted_Value", predictedValues[0],
							predictedValues);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Variance_Covariance_Matrix") == true)
						&& (leafDetail.contains("true") == true))
				{


					System.out.println("Variance_Covariance_Matrix..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Variance_Covariance_Matrix....Clicked"+treePath.toString());
					
					String analysis = extractAnalysis(tNode);
					String[][] varCovarMatrix = extractVarCovarMatrixForCA(appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Variance_Covariance_Matrix.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Variance_Covariance_Matrix", varCovarMatrix[0],
							varCovarMatrix);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Summary_Table") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Summary_Table..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Summary_Table....Clicked"+treePath.toString());
					
					String analysis = extractAnalysis(tNode);

					String[][] summaryTable = extractSummaryTableContents(appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Summary_Table.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Summary_Table", summaryTable[0],
							summaryTable);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Vertical_Display_Table") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Vertical_Display_Table..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Vertical_Display_Table....Clicked"+treePath.toString());

					String[][] summaryTable = extractVerticalDisplayTableContents(appInfo, tNode);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Vertical_Display_Table.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Summary_Table", summaryTable[0],
							summaryTable);
					pdfExporter1.closeTheDocument();
				}				
				else if ((leafDetail.contains("Sub_Areas_Table") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Sub_Areas_Table..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Sub_Areas_Table....Clicked"+treePath.toString());

					String[][] subAreasTable = extractSubAreasTableContents(appInfo, tNode);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Sub_Areas_Table.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"SubAreas_Table", subAreasTable[0],
							subAreasTable);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("TableMaven_Summary") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("TableMaven_Summary..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("TableMaven_Summary....Clicked"+treePath.toString());

					String[][] tableMavenSummary = extractTableMavenSummary(appInfo, tNode);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "TableMaven_Summary.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"TableMaven_Summary", tableMavenSummary[0],
							tableMavenSummary);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Text_Output") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Text_Output..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Text_Output....Clicked"+treePath.toString());
					
					String analysis = extractAnalysis(tNode);

					String[][] outputContents = extractOutputContents(appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Text_Output.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Text_Output", outputContents[0],
							outputContents);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Dosing_Table") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Dosing_Table..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Dosing_Table....Clicked"+treePath.toString());

					String[][] dosingTable = extractDosingTableContents(appInfo, tNode);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Dosing_Table.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Dosing_Table", dosingTable[0],
							dosingTable);
					pdfExporter1.closeTheDocument();
				}
				
				else if ((leafDetail.contains("Diagnostics") == true)
						&& (leafDetail.contains("true") == true))
				{

					TreePath treePath = TreeUtilities.getPath(tNode);
					
					String analysis = extractAnalysis(tNode);
					String[][] diagnosticsValues = extractDiagsForCA(appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Diagnostics.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Diagnostics", diagnosticsValues[0],
							diagnosticsValues);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Partial_Derivative") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Partial_Derivative..Clicked");

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Partial_Derivative....Clicked"+treePath.toString());
					
					String analysis = extractAnalysis(tNode);

					String[][] partialDerivatives = extractPartialDerivativesForCA(appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Partial_Derivative.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Partial_Derivative", partialDerivatives[0],
							partialDerivatives);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Secondary_Parameters") == true)
						&& (leafDetail.contains("true") == true))
				{
					TreePath treePath = TreeUtilities.getPath(tNode);
					
					String analysis = extractAnalysis(tNode);
					String[][] secondaryParams = extractSecParamsForCA(appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Secondary_Parameters.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Secondary_Parameter", secondaryParams[0],
							secondaryParams);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Non_Transposed_Secondary_Parameter") == true)
						&& (leafDetail.contains("true") == true))
				{

					TreePath treePath = TreeUtilities.getPath(tNode);
					String analysis = extractAnalysis(tNode);
					String[][] secondaryParamsNT = extractNTSecParamForCA(
							appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					// String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath,
							"Non_Transposed_Secondary_Parameter.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath
							+ "Non_Transposed_Secondary_Parameter",
							secondaryParamsNT[0], secondaryParamsNT);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("User_Settings") == true)
						&& (leafDetail.contains("true") == true))
 {

					TreePath treePath = TreeUtilities.getPath(tNode);
					String analysis = extractAnalysis(tNode);
					String[][] userSettings = extractUserSettingsForCA(appInfo,
							tNode, analysis);

					String specPath = extractPath(tNode);
					// String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath,
							"User_Settings.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath
							+ "User_Settings", userSettings[0], userSettings);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("History") == true)
						&& (leafDetail.contains("true") == true))
				{
					

					TreePath treePath = TreeUtilities.getPath(tNode);
					
					String analysis = extractAnalysis(tNode);
					String[][] history = extractHistoryForCA(appInfo, tNode, analysis);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "History.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"History", history[0],
							history);
					pdfExporter1.closeTheDocument();
				}
				else if ((leafDetail.contains("Descriptive_Statistics") == true)
						&& (leafDetail.contains("true") == true))
				{
					System.out.println("Descriptive_Statistics..Clicked" );

					TreePath treePath = TreeUtilities.getPath(tNode);
					System.out.println("Descriptive_Statistics....Clicked"+treePath.toString());

					String[][] dosingData = extractDescriptiveStatistics(appInfo, tNode);

					String specPath = extractPath(tNode);
					//String dirPath = derivePdfsPath(tNode);
					String dirPath = extractDirectoryPath2(tNode);
					PdfExporter pdfExporter1 = new PdfExporter();
					pdfExporter1.initializePdfWriterSetup(dirPath, "Descriptive_Statistics.pdf");
					pdfExporter1.printOrinocoTableForPdf(specPath+"Dosing", dosingData[0],
							dosingData);
					pdfExporter1.closeTheDocument();
				}
				else if ((plotLeafDetail.equals("linear_plots") == true)
						&& (leafDetail.contains("true") == true))
				{
					
					String parentName = tNode.getParent().toString();
					
					TreePath treePath = TreeUtilities.getPath(tNode);
					

					if (parentName.contains("X vs. Observed and Predicted Y")==true)
					{
						
						String analysis = extractAnalysis(tNode);
						ArrayList<JFreeChart> freeCharts = extractLinPlotsforXVsObsPredY(appInfo, tNode, analysis);

						for (int j = 0; j < freeCharts.size(); j++) {
							JFreeChart freeChart = freeCharts.get(j);
							String specPath = extractPath(tNode);
							//String dirPath = derivePdfsPath(tNode);
							String dirPath = extractDirectoryPath2(tNode);
							String jpegFileName = dirPath+"/"+parentName+"XVsObsPredY" + j + ".jpeg";
							//String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"linear" + j + ".jpeg";
							File file = new File(jpegFileName);
							saveChartAsJPEG(file, freeChart, 400, 400);
						}

					}
					else if (parentName.contains("Observed Y Vs. Weighted Predicted Y")==true)
					{

						String analysis = extractAnalysis(tNode);
						ArrayList<JFreeChart> freeCharts = extractLinPlotsForObsYAndWtPredY(
								appInfo, tNode, analysis);

						for (int j = 0; j < freeCharts.size(); j++) {
							JFreeChart freeChart = freeCharts.get(j);
							
							String specPath = extractPath(tNode);
							
							//String dirPath = derivePdfsPath(tNode);
							String dirPath = extractDirectoryPath2(tNode);
							String jpegFileName = dirPath+"/"+parentName+"ObsYVsWtdPredY" + j + ".jpeg";
							//String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"linear" + j + ".jpeg";
							File file = new File(jpegFileName);
							saveChartAsJPEG(file, freeChart, 400, 400);
						}

					}
					else if (parentName.contains("Weighted Predicted Y Vs. Weighted Residual Y")==true)
					{
						String analysis = extractAnalysis(tNode);
						ArrayList<JFreeChart> freeCharts = extractLinPlotsWtPredYVsWtResY(appInfo, tNode, analysis);


						for (int j = 0; j < freeCharts.size(); j++) {
							JFreeChart freeChart = freeCharts.get(j);
							String specPath = extractPath(tNode);
							
							//String dirPath = derivePdfsPath(tNode);
							String dirPath = extractDirectoryPath2(tNode);
							String jpegFileName = dirPath+"/"+parentName+"WtdPredYVsWtdResY" + j + ".jpeg";
							//String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"linear" + j + ".jpeg";
							
							File file = new File(jpegFileName);
							saveChartAsJPEG(file, freeChart, 400, 400);

						}

					}
					else if (parentName.contains("X Vs. Weighted Residual Y")==true)
					{
						
						String analysis = extractAnalysis(tNode);
						ArrayList<JFreeChart> freeCharts = extractLinPlotsForXVsWtResY(appInfo, tNode, analysis);


						for (int j = 0; j < freeCharts.size(); j++) {
							JFreeChart freeChart = freeCharts.get(j);
							String specPath = extractPath(tNode);
							//String dirPath = derivePdfsPath(tNode);
							String dirPath = extractDirectoryPath2(tNode);
							String jpegFileName = dirPath+"/"+parentName+"XVsWtdResY" + j + ".jpeg";
							//String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"linear" + j + ".jpeg";
							File file = new File(jpegFileName);
							saveChartAsJPEG(file, freeChart, 400, 400);

						}

					}
					else if (parentName.contains("Partial Derivative Plot")==true)
					{
						
						String analysis = extractAnalysis(tNode);
						ArrayList<JFreeChart> freeCharts = extractLinearPartialDerPlots(appInfo, tNode, analysis);

						for (int j = 0; j < freeCharts.size(); j++) {
							JFreeChart freeChart = freeCharts.get(j);
							String specPath = extractPath(tNode);
							//String dirPath = derivePdfsPath(tNode);
							String dirPath = extractDirectoryPath2(tNode);
							String jpegFileName = dirPath+"/"+parentName+"PartialDerPlot" + j + ".jpeg";
							//String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"linear" + j + ".jpeg";
							File file = new File(jpegFileName);
							saveChartAsJPEG(file, freeChart, 400, 400);
						}

					}
					else if (parentName.contains("Linear and Loglinear Plots")==true)
					{
						ArrayList<JFreeChart> freeCharts = extractLinearPlots(appInfo, tNode);

						for (int j = 0; j < freeCharts.size(); j++) {
							JFreeChart freeChart = freeCharts.get(j);
							String specPath = extractPath(tNode);
							//String dirPath = derivePdfsPath(tNode);
							String dirPath = extractDirectoryPath2(tNode);
							String jpegFileName = dirPath+"/"+parentName+"linandloglinplots" + j + ".jpeg";
							File file = new File(jpegFileName);
							saveChartAsJPEG(file, freeChart, 400, 400);
						}

					}
					else if (parentName.contains("Plots")==true)
					{
						
						String analysis = extractAnalysis(tNode);
						ArrayList<JFreeChart> freeCharts = extractSCANPSLinearPlots(appInfo, tNode, analysis);

						for (int j = 0; j < freeCharts.size(); j++) {
							JFreeChart freeChart = freeCharts.get(j);
							String specPath = extractPath(tNode);
							//String dirPath = derivePdfsPath(tNode);
							String dirPath = extractDirectoryPath2(tNode);
							String jpegFileName = dirPath+"/"+parentName+"SCANPSLinearPlots" + j + ".jpeg";
							File file = new File(jpegFileName);
							saveChartAsJPEG(file, freeChart, 400, 400);
						}

					}
					
				}
				else if ((plotLeafDetail.equals("loglinear_plots") == true)
						&& (leafDetail.contains("true") == true))
				{
					//System.out.println("loglinear_plots..Clicked" );
					String parentName = tNode.getParent().toString();
					System.out.println("loglinear_plots..Clicked"+parentName);

					if (parentName.contains("X vs. Observed and Predicted Y")==true)
					{
						
						String analysis = extractAnalysis(tNode);
						ArrayList<JFreeChart> freeCharts = extractLogLinearXVsObsPredY(appInfo, tNode, analysis);


						for (int j = 0; j < freeCharts.size(); j++) {
							JFreeChart freeChart = freeCharts.get(j);
							String specPath = extractPath(tNode);

							//String dirPath = derivePdfsPath(tNode);
							String dirPath = extractDirectoryPath2(tNode);
							String jpegFileName = dirPath+"/"+parentName+"XvsObsYPredYLoglin" + j + ".jpeg";
							//String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"loglinear" + j + ".jpeg";
							File file = new File(jpegFileName);
							saveChartAsJPEG(file, freeChart, 400, 400);
						}

					}
					else if (parentName.contains("Observed Y Vs. Weighted Predicted Y")==true)
					{
						
						String analysis = extractAnalysis(tNode);
						ArrayList<JFreeChart> freeCharts = extractLogLinObsYVsWtdPredY(appInfo, tNode, analysis);

						for (int j = 0; j < freeCharts.size(); j++) {
							JFreeChart freeChart = freeCharts.get(j);
							String specPath = extractPath(tNode);
							
							//String dirPath = derivePdfsPath(tNode);
							String dirPath = extractDirectoryPath2(tNode);
							String jpegFileName = dirPath+"/"+parentName+"ObsYVsWtdPredYLogLinear" + j + ".jpeg";
							
							// String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"loglinear" + j + ".jpeg";
							File file = new File(jpegFileName);
							saveChartAsJPEG(file, freeChart, 400, 400);
						}

					}
					else if (parentName.contains("Weighted Predicted Y Vs. Weighted Residual Y")==true)
					{
					
						String analysis = extractAnalysis(tNode);
						ArrayList<JFreeChart> freeCharts = extractLogLinForWtPredyVsWtResY(appInfo, tNode, analysis);

						for (int j = 0; j < freeCharts.size(); j++) {
							JFreeChart freeChart = freeCharts.get(j);
							String specPath = extractPath(tNode);
							
							//String dirPath = derivePdfsPath(tNode);
							String dirPath = extractDirectoryPath2(tNode);
							String jpegFileName = dirPath+"/"+parentName+"WtdPredYVsWtdResYLoglin" + j + ".jpeg";
							
							//String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"loglinear" + j + ".jpeg";
							File file = new File(jpegFileName);
							saveChartAsJPEG(file, freeChart, 400, 400);
						}

					}
					else if (parentName.contains("X Vs. Weighted Residual Y")==true)
					{
						
						String analysis = extractAnalysis(tNode);
						ArrayList<JFreeChart> freeCharts = extractLogLinForXVsWtResY(appInfo, tNode, analysis);

						for (int j = 0; j < freeCharts.size(); j++) {
							JFreeChart freeChart = freeCharts.get(j);
							String specPath = extractPath(tNode);

							//String dirPath = derivePdfsPath(tNode);
							String dirPath = extractDirectoryPath2(tNode);
							String jpegFileName = dirPath+"/"+parentName+"XVsWtdResYLogLin" + j + ".jpeg";
							
							//String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"loglinear" + j + ".jpeg";
							File file = new File(jpegFileName);
							saveChartAsJPEG(file, freeChart, 400, 400);
						}
					}
					else if (parentName.contains("Partial Derivative Plot")==true)
					{
						String analysis = extractAnalysis(tNode);
						ArrayList<JFreeChart> freeCharts = extractLogLinForPartialDerivative(appInfo, tNode, analysis);

						for (int j = 0; j < freeCharts.size(); j++) {
							JFreeChart freeChart = freeCharts.get(j);
							String specPath = extractPath(tNode);
							
							//String dirPath = derivePdfsPath(tNode);
							String dirPath = extractDirectoryPath2(tNode);
							String jpegFileName = dirPath+"/"+parentName+"PartialDerPlotLogLin" + j + ".jpeg";
							
							//String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"loglinear" + j + ".jpeg";
							File file = new File(jpegFileName);
							saveChartAsJPEG(file, freeChart, 400, 400);

						}
					}
					else if (parentName.contains("Linear and Loglinear Plots")==true)
					{
						ArrayList<JFreeChart> freeCharts = extractLogLogLinearPlots(appInfo, tNode);

						for (int j = 0; j < freeCharts.size(); j++) {
							JFreeChart freeChart = freeCharts.get(j);
							String specPath = extractPath(tNode);
							
							//String dirPath = derivePdfsPath(tNode);
							String dirPath = extractDirectoryPath2(tNode);
							String jpegFileName = dirPath+"/"+parentName+"linearloglinear" + j + ".jpeg";
							
							//String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"loglinear" + j + ".jpeg";
							File file = new File(jpegFileName);
							saveChartAsJPEG(file, freeChart, 400, 400);

						}
					}
				}
				else if ((plotLeafDetail.equals("LinearX_LinearY") == true)
						&& (leafDetail.contains("true") == true))
				{

					ArrayList<JFreeChart> freeCharts = extractLinearXLinearYPlots(appInfo, tNode);
					String parentName = tNode.getParent().toString();
					System.out.println("loglinear_plots..Clicked"+parentName);
					
					for (int j = 0; j < freeCharts.size(); j++) {
						JFreeChart freeChart = freeCharts.get(j);
						String specPath = extractPath(tNode);
						
						//String dirPath = derivePdfsPath(tNode);
						String dirPath = extractDirectoryPath2(tNode);
						String jpegFileName = dirPath+"/"+parentName+"LinearX_LinearY" + j + ".jpeg";
						
						//String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"loglinear" + j + ".jpeg";
						File file = new File(jpegFileName);
						saveChartAsJPEG(file, freeChart, 400, 400);

					}
				
					
				}
				else if ((plotLeafDetail.equals("LinearX_LogY") == true)
						&& (leafDetail.contains("true") == true))
				{


					ArrayList<JFreeChart> freeCharts = extractLinearXLogYPlots(appInfo, tNode);
					String parentName = tNode.getParent().toString();
					System.out.println("loglinear_plots..Clicked"+parentName);

					for (int j = 0; j < freeCharts.size(); j++) {
						JFreeChart freeChart = freeCharts.get(j);
						String specPath = extractPath(tNode);
						
						//String dirPath = derivePdfsPath(tNode);
						String dirPath = extractDirectoryPath2(tNode);
						String jpegFileName = dirPath+"/"+parentName+"LinearX_LogY" + j + ".jpeg";
						
						//String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"loglinear" + j + ".jpeg";
						File file = new File(jpegFileName);
						saveChartAsJPEG(file, freeChart, 400, 400);

					}
				
					
				
					
				}
				else if ((plotLeafDetail.equals("LogX_LogY") == true)
						&& (leafDetail.contains("true") == true))
				{

					ArrayList<JFreeChart> freeCharts = extractLogXLogYPlots(appInfo, tNode);
					
					String parentName = tNode.getParent().toString();
					System.out.println("loglinear_plots..Clicked"+parentName);

					for (int j = 0; j < freeCharts.size(); j++) {
						JFreeChart freeChart = freeCharts.get(j);
						String specPath = extractPath(tNode);
						
						//String dirPath = derivePdfsPath(tNode);
						String dirPath = extractDirectoryPath2(tNode);
						String jpegFileName = dirPath+"/"+parentName+"LogX_LogY" + j + ".jpeg";
						
						//String jpegFileName = ".\\linearplots\\"+specPath+"\\"+parentName+"loglinear" + j + ".jpeg";
						File file = new File(jpegFileName);
						saveChartAsJPEG(file, freeChart, 400, 400);

					}
				}


			}
			else
				walk(model, child, pdfExporter);
		}
	}

	private static ArrayList<JFreeChart> extractLinPlotsforXVsObsPredY(
			ApplicationInfo appInfo,TreeNode tNode, String analysis) {
			ArrayList<JFreeChart> freeCharts = null;
			
			analysis = analysis.toLowerCase();
			
			if (analysis.equals("pk") == true)
 			{
			freeCharts = appInfo
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
									.getSelectedSheetIndex()).getPkInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLinearPlots();
			}
			else if (analysis.equals("pd") == true)
			{
					freeCharts = appInfo
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
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLinearPlots();
			}
			else if (analysis.equals("mm") == true)
			{
				
					freeCharts = appInfo
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
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLinearPlots();
			}
			else if (analysis.equals("pkpdlink") == true)
			{
			
					freeCharts = appInfo
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
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLinearPlots();
			
			}
			else
			{
				
					freeCharts = appInfo
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
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLinearPlots();
				}
	
		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLogXLogYPlots(
			ApplicationInfo appInfo, TreeNode tNode) {

		String projName = extractProjectName(tNode);
		int projIdx = appInfo.getProjectIdByName(projName);

		ArrayList<JFreeChart> freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().getExpToPdf().getLogPlots();
		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLinearXLogYPlots(
			ApplicationInfo appInfo, TreeNode tNode) {

		String projName = extractProjectName(tNode);
		int projIdx = appInfo.getProjectIdByName(projName);

		ArrayList<JFreeChart> freeCharts = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogplots();
		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLinearXLinearYPlots(
			ApplicationInfo appInfo, TreeNode tNode) {


		String projName = extractProjectName(tNode);
		int projIdx = appInfo.getProjectIdByName(projName);

		ArrayList<JFreeChart> freeCharts = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLinearPlots();
		return freeCharts;
	}

	private static String[][] extractBQLTransformedData(
			ApplicationInfo appInfo, TreeNode tNode) {


		String projName = extractProjectName(tNode);
		int projIdx = appInfo.getProjectIdByName(projName);

		String[][] bqlTransformedData = appInfo.getProjectInfoAL().get(projIdx)
				.getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo().getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters();
				
		return bqlTransformedData;
		}

	private static String[][] extractTableMavenSummary(ApplicationInfo appInfo,
			TreeNode tNode) {

		String[][] data = null;
		String projName = extractProjectName(tNode);
		int projIdx = appInfo.getProjectIdByName(projName);

		int templateType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getTemplateType();

		Hashtable<String, Hashtable<String, Double>> htStatMetricListing = null;

		if (templateType == 0) {
			T1ResultsLoader t1ResLoader = new T1ResultsLoader();
			t1ResLoader.template1Handling(htStatMetricListing);
			//String[] headers = t1ResLoader.getHeaders();
			data = t1ResLoader.getData();
			
		} else if (templateType == 1) {
			T2ResultsLoader t1ResLoader = new T2ResultsLoader();
			t1ResLoader.template2Handling(htStatMetricListing);
			data = t1ResLoader.getData();
			//t1ResLoader.getHeaders();
		} else if (templateType == 2) {
			T3ResultsLoader t1ResLoader = new T3ResultsLoader();
			t1ResLoader.template3Handling(htStatMetricListing);
			data = t1ResLoader.getData();
			//t1ResLoader.getHeaders();
		} else if (templateType == 3) {
			T6ResultsLoader t1ResLoader = new T6ResultsLoader();
			t1ResLoader.template6Handling(htStatMetricListing);
			data = t1ResLoader.getData();
			//t1ResLoader.getHeaders();
		}
		return data;
	}

	private static String extractAnalysis(TreeNode tNode) {

		TreePath treePath = TreeUtilities.getPath(tNode);

		Object[] pathComps = treePath.getPath();

		String specPath = pathComps[2].toString();
		specPath = specPath.substring(1,specPath.length()-1);
		
		StringTokenizer sTknzr = new StringTokenizer(specPath, "-");
		String analysis = sTknzr.nextToken();
				
		return analysis;
	}

	private static ArrayList<JFreeChart> extractSCANPSLinearPlots(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {
		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);

		analysis = analysis.toLowerCase();
		
		ArrayList<JFreeChart> freeCharts = null;
		
		if (analysis.equals("nps") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLinearPlots();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getNpsInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLinearPlots();
			
		} else if (analysis.equals("sca") == true) {
		
					/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLinearPlots();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getScaInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLinearPlots();
		}
		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLogLogLinearPlots(
			ApplicationInfo appInfo, TreeNode tNode) {

		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);

		/*ArrayList<JFreeChart> freeCharts = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogplots();*/
		
		WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
		.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
		
		ArrayList<JFreeChart> freeCharts = wsInfo.getNcaInfo().getWorkSheetOutputs().getPlotOutputs()
		.getLogplots();
		
		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLinearPlots(
			ApplicationInfo appInfo, TreeNode tNode) {

		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);

		/*ArrayList<JFreeChart> freeCharts = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlots();*/
		WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
		.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
		
		ArrayList<JFreeChart> freeCharts = wsInfo.getNcaInfo().getWorkSheetOutputs().getPlotOutputs()
		.getLinearPlots();
		
		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLogLinForPartialDerivative(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		ArrayList<JFreeChart> freeCharts = null;
		analysis = analysis.toLowerCase();
		
		if (analysis.equals("pk") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForPartialDerivative();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPkInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForPartialDerivative();
			
			
		} else if (analysis.equals("pd") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForPartialDerivative();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForPartialDerivative();
			
		} else if (analysis.equals("mm") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForPartialDerivative();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForPartialDerivative();
			
		} else if (analysis.equals("pkpdlink") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForPartialDerivative();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForPartialDerivative();
			
		} else {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForPartialDerivative();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForPartialDerivative();
		}

		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLogLinForXVsWtResY(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {
	
		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		
		ArrayList<JFreeChart> freeCharts = null;
		
		analysis = analysis.toLowerCase();

		if (analysis.equals("pk") == true) {
			/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getCaInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForXVsWeightedResidualY();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPkInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForXVsWeightedResidualY();
			
			
		} else if (analysis.equals("pd") == true) {
			/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPdInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForXVsWeightedResidualY();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForXVsWeightedResidualY();
			
			
		} else if (analysis.equals("mm") == true) {
			/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getMmInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForXVsWeightedResidualY();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForXVsWeightedResidualY();
			
		} else if (analysis.equals("pkpdlink") == true) {
			/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForXVsWeightedResidualY();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForXVsWeightedResidualY();
			
			
		} else {
			/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getIrInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForXVsWeightedResidualY();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForXVsWeightedResidualY();
		}

		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLogLinForWtPredyVsWtResY(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		
		ArrayList<JFreeChart> freeCharts = null;
		analysis = analysis.toLowerCase();
		
		if (analysis.equals("pk") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForWeightedPredictedYVsWeightedResidualY();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPkInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForWeightedPredictedYVsWeightedResidualY();
			
		} else if (analysis.equals("pd") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForWeightedPredictedYVsWeightedResidualY();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForWeightedPredictedYVsWeightedResidualY();
		} else if (analysis.equals("mm") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForWeightedPredictedYVsWeightedResidualY();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForWeightedPredictedYVsWeightedResidualY();			
			
		} else if (analysis.equals("pkpdlink") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForWeightedPredictedYVsWeightedResidualY();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForWeightedPredictedYVsWeightedResidualY();
			
		} else {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForWeightedPredictedYVsWeightedResidualY();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForWeightedPredictedYVsWeightedResidualY();
			
		}

		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLogLinObsYVsWtdPredY(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		
		ArrayList<JFreeChart> freeCharts = null;
		
		analysis = analysis.toLowerCase();

		if (analysis.equals("pk") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForObsevedYVsWeightedPredictedY();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPkInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForObsevedYVsWeightedPredictedY();
			
		} else if (analysis.equals("pd") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForObsevedYVsWeightedPredictedY();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForObsevedYVsWeightedPredictedY();
		} else if (analysis.equals("mm") == true) {
		/*	freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForObsevedYVsWeightedPredictedY();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForObsevedYVsWeightedPredictedY();
		} else if (analysis.equals("pkpdlink") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForObsevedYVsWeightedPredictedY();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForObsevedYVsWeightedPredictedY();
			
		} else {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getPlotOutputs()
					.getLogPlotForObsevedYVsWeightedPredictedY();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogPlotForObsevedYVsWeightedPredictedY();
		}

		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLogLinearXVsObsPredY(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		ArrayList<JFreeChart> freeCharts = null;
		analysis = analysis.toLowerCase();
		
		if (analysis.equals("pk") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLogplots();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPkInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogplots();
			
		} else if (analysis.equals("pd") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLogplots();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogplots();
			
		} else if (analysis.equals("mm") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLogplots();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogplots();			
			
		} else if (analysis.equals("pkpdlink") == true) {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLogplots();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogplots();
			
		} else {
			/*freeCharts = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getPlotOutputs().getLogplots();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			freeCharts = wsInfo.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
			.getLogplots();
		}

		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLinearPartialDerPlots(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {
			
		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		ArrayList<JFreeChart> freeCharts = null;
		
		analysis = analysis.toLowerCase();
			
			if (analysis.equals("pk") == true)
 			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getCaInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getPkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative();
			}
			else if (analysis.equals("pd") == true)
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPdInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative();
			}
			else if (analysis.equals("mm") == true)
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getMmInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative();
			}
			else if (analysis.equals("pkpdlink") == true)
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative();
			}
			else
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getIrInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative();
			}
	
		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLinPlotsForXVsWtResY(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {
		
		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		ArrayList<JFreeChart> freeCharts = null;
		
		analysis = analysis.toLowerCase();
			
			if (analysis.equals("pk") == true)
 			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getCaInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getPkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY();
				
			}
			else if (analysis.equals("pd") == true)
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPdInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY();*/
 				
 				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY();
			}
			else if (analysis.equals("mm") == true)
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getMmInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY();
			}
			else if (analysis.equals("pkpdlink") == true)
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY();
			}
			else
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getIrInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY();
			}
	
		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLinPlotsWtPredYVsWtResY(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {
		
		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		ArrayList<JFreeChart> freeCharts = null;
		
		analysis = analysis.toLowerCase();
			
			if (analysis.equals("pk") == true)
 			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getCaInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY();*/
 				
 				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getPkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY();
			}
			else if (analysis.equals("pd") == true)
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPdInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY();
			}
			else if (analysis.equals("mm") == true)
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getMmInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY();
			}
			else if (analysis.equals("pkpdlink") == true)
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY();
			}
			else
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getIrInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY();
			}
	
		return freeCharts;
	}

	private static ArrayList<JFreeChart> extractLinPlotsForObsYAndWtPredY(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		
		analysis = analysis.toLowerCase();
			ArrayList<JFreeChart> freeCharts = null;
			
			if (analysis.equals("pk") == true)
 			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getCaInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getPkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY();
			}
			else if (analysis.equals("pd") == true)
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPdInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY();
			}
			else if (analysis.equals("mm") == true)
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getMmInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY();
			}
			else if (analysis.equals("pkpdlink") == true)
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY();
			}
			else
			{
 				/*freeCharts = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getIrInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY();*/
				
				WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
				.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
				
				freeCharts = wsInfo.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY();
			}
	
		return freeCharts;
	}

	private static String[][] extractHistoryForCA(ApplicationInfo appInfo,
			TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		
		analysis = analysis.toLowerCase();

		String[][] history = null;

		if (analysis.equals("pk") == true) {
			/*history = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getHistoryForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			history = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHistoryForCA();	
			
			
		} else if (analysis.equals("pd") == true) {
			/*history = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getHistoryForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			history = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHistoryForCA();	
			
		} else if (analysis.equals("mm") == true) {
			/*history = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getHistoryForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			history = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHistoryForCA();	
		} else if (analysis.equals("pkpdlink") == true) {
			/*history = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getHistoryForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			history = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHistoryForCA();	
			
		} else {
			/*history = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getHistoryForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			history = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHistoryForCA();	
		}

		return history;
	}

	private static String[][] extractUserSettingsForCA(ApplicationInfo appInfo,
			TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		
		String[][] userSettings = null;

		if (analysis.equals("pk") == true) {
			/*userSettings = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getUserSettingsForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			userSettings = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getUserSettingsForCA();	
			
		} else if (analysis.equals("pd") == true) {
			/*userSettings = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getUserSettingsForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			userSettings = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getUserSettingsForCA();	
		} else if (analysis.equals("mm") == true) {
			/*userSettings = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getUserSettingsForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			userSettings = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getUserSettingsForCA();
		} else if (analysis.equals("pkpdlink") == true) {
			/*userSettings = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getUserSettingsForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			userSettings = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getUserSettingsForCA();
		} else {
			/*userSettings = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getUserSettingsForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			userSettings = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getUserSettingsForCA();
		}

		return userSettings;
	}

	private static String[][] extractNTSecParamForCA(ApplicationInfo appInfo,
			TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);

		String[][] secondaryParamsNT = null;
		
		analysis = analysis.toLowerCase();

		if (analysis.equals("pk") == true) {
			/*secondaryParamsNT = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getNonTransposedSPForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			secondaryParamsNT = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getNonTransposedSPForCA();
			
		} else if (analysis.equals("pd") == true) {
			/*secondaryParamsNT = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getNonTransposedSPForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			secondaryParamsNT = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getNonTransposedSPForCA();
			
			
		} else if (analysis.equals("mm") == true) {
			/*secondaryParamsNT = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getNonTransposedSPForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			secondaryParamsNT = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getNonTransposedSPForCA();			
			
		} else if (analysis.equals("pkpdlink") == true) {
			/*secondaryParamsNT = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getNonTransposedSPForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			secondaryParamsNT = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getNonTransposedSPForCA();	
		} else {
			/*secondaryParamsNT = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getNonTransposedSPForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			secondaryParamsNT = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getNonTransposedSPForCA();	
		}

		return secondaryParamsNT;
	}

	private static String[][] extractSecParamsForCA(ApplicationInfo appInfo,
			TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		
		String[][] secondaryParams = null;
		
		analysis = analysis.toLowerCase();

		if (analysis.equals("pk") == true) {
			/*secondaryParams = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getSecondaryParametersForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			secondaryParams = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getSecondaryParametersForCA();
			
			
		} else if (analysis.equals("pd") == true) {
			/*secondaryParams = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getSecondaryParametersForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			secondaryParams = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getSecondaryParametersForCA();
		} else if (analysis.equals("mm") == true) {
			/*secondaryParams = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getSecondaryParametersForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			secondaryParams = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getSecondaryParametersForCA();
					
					System.out.println("sec params");
		} else if (analysis.equals("pkpdlink") == true) {
			/*secondaryParams = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getSecondaryParametersForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			secondaryParams = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getSecondaryParametersForCA();
			
		} else {
			/*secondaryParams = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getSecondaryParametersForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			secondaryParams = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getSecondaryParametersForCA();
		}

		return secondaryParams;
	}

	private static String[][] extractPartialDerivativesForCA(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);

		String[][] partialDerivatives = null;
		
		analysis = analysis.toLowerCase();

		if (analysis.equals("pk") == true) {

			/*partialDerivatives = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getPartialDerivativeForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			partialDerivatives = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getPartialDerivativeForCA();

		} else if (analysis.equals("pd") == true) {

			/*partialDerivatives = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getPartialDerivativeForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			partialDerivatives = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getPartialDerivativeForCA();

		} else if (analysis.equals("mm") == true) {

			/*partialDerivatives = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getPartialDerivativeForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			partialDerivatives = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getPartialDerivativeForCA();

		} else if (analysis.equals("pkpdlink") == true) {

			/*partialDerivatives = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getPartialDerivativeForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			partialDerivatives = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getPartialDerivativeForCA();

		} else {

			/*partialDerivatives = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getPartialDerivativeForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			partialDerivatives = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getPartialDerivativeForCA();

		}

		return partialDerivatives;
	}

	private static String[][] extractDiagsForCA(ApplicationInfo appInfo,
			TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		String[][] diagnosticsValues = null;
		
		analysis = analysis.toLowerCase();

		if (analysis.equals("pk") == true) {/*
			diagnosticsValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getDiagnosticsForCA();
		*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			diagnosticsValues = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVarienceCovarienceMatrixForCA();
			
		} else if (analysis.equals("pd") == true) {/*
			diagnosticsValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getDiagnosticsForCA();
		*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			diagnosticsValues = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVarienceCovarienceMatrixForCA();
			
			
		} else if (analysis.equals("mm") == true) {/*
			diagnosticsValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getDiagnosticsForCA();
		*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			diagnosticsValues = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVarienceCovarienceMatrixForCA();	
		} else if (analysis.equals("pkpdlink") == true) {/*
			diagnosticsValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getDiagnosticsForCA();
		*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			diagnosticsValues = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVarienceCovarienceMatrixForCA();		
		
		} else {/*
			diagnosticsValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getDiagnosticsForCA();
		*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			diagnosticsValues = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVarienceCovarienceMatrixForCA();	
		
		}

		return diagnosticsValues;

	}

	private static String[][] extractVarCovarMatrixForCA(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);

		String[][] varCovarMatrix = null;
		
		analysis = analysis.toLowerCase();

		if (analysis.equals("pk") == true) {
			/*varCovarMatrix = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getVarienceCovarienceMatrixForCA();*/
		
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			varCovarMatrix = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVarienceCovarienceMatrixForCA();
			
		} else if (analysis.equals("pd") == true) {
			/*varCovarMatrix = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getVarienceCovarienceMatrixForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			varCovarMatrix = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVarienceCovarienceMatrixForCA();
			
		} else if (analysis.equals("mm") == true) {
			/*varCovarMatrix = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getVarienceCovarienceMatrixForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			varCovarMatrix = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVarienceCovarienceMatrixForCA();
			
		} else if (analysis.equals("pkpdlink") == true) {/*
			varCovarMatrix = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getVarienceCovarienceMatrixForCA();
		*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			varCovarMatrix = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVarienceCovarienceMatrixForCA();	
		} else {/*
			varCovarMatrix = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getVarienceCovarienceMatrixForCA();
		*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			varCovarMatrix = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVarienceCovarienceMatrixForCA();	
		}

		return varCovarMatrix;
	}

	private static String[][] extractPredictedValsForCA(ApplicationInfo appInfo, TreeNode tNode, String analysis) {
	
		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);

		String[][] predictedValues = null;
		
		analysis = analysis.toLowerCase();

		if (analysis.equals("pk") == true) {
			/*
			predictedValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getPredictedValueForCA();
		*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			predictedValues = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getPredictedValueForCA();
			
		} else if (analysis.equals("pd") == true) {
			/*predictedValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getPredictedValueForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			predictedValues = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getPredictedValueForCA();
			
		} else if (analysis.equals("mm") == true) {
			/*predictedValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getPredictedValueForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			predictedValues = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getPredictedValueForCA();
		} else if (analysis.equals("pkpdlink") == true) {
			/*predictedValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getPredictedValueForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			predictedValues = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getPredictedValueForCA();
		} else {
			/*predictedValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getPredictedValueForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			predictedValues = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getPredictedValueForCA();
		}

		return predictedValues;
	}

	private static String[][] extractConditionNumberForCA(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		String[][] condnNumber = null;
		
		analysis = analysis.toLowerCase();

		if (analysis.equals("pk") == true) {
			/*condnNumber = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getConditionNumberForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			condnNumber = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getConditionNumberForCA();
			
			
		} else if (analysis.equals("pd") == true) {
			/*condnNumber = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getConditionNumberForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			condnNumber = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getConditionNumberForCA();
		} else if (analysis.equals("mm") == true) {
			/*condnNumber = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getConditionNumberForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			condnNumber = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getConditionNumberForCA();
		} else if (analysis.equals("pkpdlink") == true) {
			/*condnNumber = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getConditionNumberForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			condnNumber = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getConditionNumberForCA();
		} else {
			/*condnNumber = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getConditionNumberForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			condnNumber = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getConditionNumberForCA();
		}

		return condnNumber;
	}

	private static String[][] extractEigenValuesForCA(ApplicationInfo appInfo,
			TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		String[][] eigenValues = null;
		
		analysis = analysis.toLowerCase();

		if (analysis.equals("pk") == true) {
			/*eigenValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getEigenValuesForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			eigenValues = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getEigenValuesForCA();
			
		} else if (analysis.equals("pd") == true) {
			/*eigenValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getEigenValuesForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			eigenValues = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getEigenValuesForCA();
			
		} else if (analysis.equals("mm") == true) {
			/*eigenValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getEigenValuesForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			eigenValues = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getEigenValuesForCA();
			
		} else if (analysis.equals("pkpdlink") == true) {
			/*eigenValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getEigenValuesForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			eigenValues = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getEigenValuesForCA();
			
		} else {
			/*eigenValues = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getEigenValuesForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			eigenValues = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getEigenValuesForCA();
		}

		return eigenValues;
	}

	private static String[][] extractCorrMatrixForCA(ApplicationInfo appInfo,
			TreeNode tNode, String analysis) {

		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		String[][] corrMatrix = null;
		
		analysis = analysis.toLowerCase();

		if (analysis.equals("pk") == true) {
			/*corrMatrix = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getCorrelationMatrixForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			corrMatrix = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getCorrelationMatrixForCA();
		} else if (analysis.equals("pd") == true) {
			/*corrMatrix = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getCorrelationMatrixForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			corrMatrix = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getCorrelationMatrixForCA();
		} else if (analysis.equals("mm") == true) {
			/*corrMatrix = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getCorrelationMatrixForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			corrMatrix = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getCorrelationMatrixForCA();
			
		} else if (analysis.equals("pkpdlink") == true) {
			/*corrMatrix = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getCorrelationMatrixForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			corrMatrix = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getCorrelationMatrixForCA();
		} else {
			/*corrMatrix = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getCorrelationMatrixForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			corrMatrix = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getCorrelationMatrixForCA();
		}

		return corrMatrix;
	}
	
	private static String[][] extractNonTransFinalParams(
		ApplicationInfo appInfo, TreeNode tNode, String analysis) {
		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		String[][] horFinalParameters = null;
		
		analysis = analysis.toLowerCase();

		if (analysis.equals("pk") == true) {
			/*horFinalParameters = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getVerticalParametersForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			horFinalParameters = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVerticalParametersForCA();
			
			
		} else if (analysis.equals("pd") == true) {
			/*horFinalParameters = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getVerticalParametersForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			horFinalParameters = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVerticalParametersForCA();
			
		} else if (analysis.equals("mm") == true) {
			/*horFinalParameters = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getVerticalParametersForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			horFinalParameters = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVerticalParametersForCA();
			
		} else if (analysis.equals("pkpdlink") == true) {
			/*horFinalParameters = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getVerticalParametersForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			horFinalParameters = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVerticalParametersForCA();
			
			
		} else {
			/*horFinalParameters = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getVerticalParametersForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			horFinalParameters = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getVerticalParametersForCA();
		}

		return horFinalParameters;
	}

	private static String[][] extractFinalParamsForCA(ApplicationInfo appInfo, TreeNode tNode, String analysis) {
		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		
		String[][] finalParameters = null;
		
		analysis = analysis.toLowerCase();

		if (analysis.equals("pk")== true)
		{
			/*finalParameters = appInfo.
			getProjectInfoAL().get(projIdx)
			.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(projIdx)
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getCaInfo()
			.getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHorizontalParametersForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			finalParameters = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHorizontalParametersForCA();
			
			
		}
		else if (analysis.equals("pd")== true)
		{
			/*finalParameters = appInfo.
			getProjectInfoAL().get(projIdx)
			.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(projIdx)
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPdInfo()
			.getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHorizontalParametersForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			finalParameters = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHorizontalParametersForCA();
		}
		else if (analysis.equals("mm")== true)
		{
			/*finalParameters = appInfo.
			getProjectInfoAL().get(projIdx)
			.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(projIdx)
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getMmInfo()
			.getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHorizontalParametersForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			finalParameters = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHorizontalParametersForCA();
		}
		else if (analysis.equals("pkpdlink")== true)
		{
			/*finalParameters = appInfo.
			getProjectInfoAL().get(projIdx)
			.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(projIdx)
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkpdLinkInfo()
			.getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHorizontalParametersForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			finalParameters = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHorizontalParametersForCA();
		}
		else
		{
			/*finalParameters = appInfo.
			getProjectInfoAL().get(projIdx)
			.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(projIdx)
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getIrInfo()
			.getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHorizontalParametersForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			finalParameters = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getHorizontalParametersForCA();
		}		
		
		return finalParameters;
	}

	private static String[][] extractMinProcessForCA(ApplicationInfo appInfo, TreeNode tNode, String analysis) {
		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		
		String[][] minProcessForCA = null;
		
		analysis = analysis.toLowerCase();
		
		if (analysis.equals("pk")== true)
		{
			/*minProcessForCA = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getMinimizationProcessForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			minProcessForCA = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getMinimizationProcessForCA();
		}
		else if (analysis.equals("pd")== true)
		{
			/*minProcessForCA = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getMinimizationProcessForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			minProcessForCA = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getMinimizationProcessForCA();
		}
		else if (analysis.equals("mm")== true)
		{
			/*minProcessForCA = appInfo
			.getProjectInfoAL()
			.get(projIdx)
			.getWorkBooksArrayList()
			.get(
					appInfo.getProjectInfoAL().get(projIdx)
							.getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL()
			.get(
					appInfo
							.getProjectInfoAL()
							.get(projIdx)
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											projIdx)
											.getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getMmInfo()
			.getWorkSheetOutputs().getSpreadSheetOutputs()
			.getMinimizationProcessForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			minProcessForCA = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getMinimizationProcessForCA();
		}
		else if (analysis.equals("pkpdlink")== true)
		{
			/*minProcessForCA = appInfo
			.getProjectInfoAL()
			.get(projIdx)
			.getWorkBooksArrayList()
			.get(
					appInfo.getProjectInfoAL().get(projIdx)
							.getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL()
			.get(
					appInfo
							.getProjectInfoAL()
							.get(projIdx)
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											projIdx)
											.getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getPkpdLinkInfo()
			.getWorkSheetOutputs().getSpreadSheetOutputs()
			.getMinimizationProcessForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			minProcessForCA = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getMinimizationProcessForCA();
		}
		else
		{
			/*minProcessForCA = appInfo
			.getProjectInfoAL()
			.get(projIdx)
			.getWorkBooksArrayList()
			.get(
					appInfo.getProjectInfoAL().get(projIdx)
							.getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL()
			.get(
					appInfo
							.getProjectInfoAL()
							.get(projIdx)
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											projIdx)
											.getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getIrInfo()
			.getWorkSheetOutputs().getSpreadSheetOutputs()
			.getMinimizationProcessForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			minProcessForCA = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getMinimizationProcessForCA();
		}		
		
		return minProcessForCA;
	}
	
	private static String[][] extractSummaryTableContents(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {
		String projName = extractProjectName(tNode);
		
		
		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		
		analysis = analysis.toLowerCase();
		
		String[][] summaryTable = null;
		if (analysis.equals("nca")== true)
		{
			/*summaryTable = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getExpToPdf().getSummaryTable();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			summaryTable = wsInfo.getNcaInfo().getProcessingInputs().getExpToPdf().getSummaryTable();

		}
		else if (analysis.equals("pk")== true)
		{
			/*summaryTable = appInfo.
			getProjectInfoAL().get(projIdx)
			.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(projIdx)
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getCaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getSummaryTableForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			summaryTable = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getSummaryTableForCA();
		}
		else if (analysis.equals("mm")== true)
		{
			/*summaryTable = appInfo.
			getProjectInfoAL().get(projIdx)
			.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(projIdx)
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getMmInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getSummaryTableForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			summaryTable = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getSummaryTableForCA();
		}
		else if (analysis.equals("pd")== true)
		{
			/*summaryTable = appInfo.
			getProjectInfoAL().get(projIdx)
			.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(projIdx)
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPdInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getSummaryTableForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			summaryTable = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getSummaryTableForCA();
		}
		else if (analysis.equals("pkpdlink")== true)
		{
			/*summaryTable = appInfo.
			getProjectInfoAL().get(projIdx)
			.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(projIdx)
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getSummaryTableForCA();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			summaryTable = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getSummaryTableForCA();
			
		}
		else
		{
			/*summaryTable = appInfo.
			getProjectInfoAL().get(projIdx)
			.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(projIdx)
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(projIdx).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getSummaryTableForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			summaryTable = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
			.getSummaryTableForCA();
		}
		
		
		return summaryTable;
	}

	private static String[][] extractDescriptiveStatistics(
			ApplicationInfo appInfo, TreeNode tNode) {

		String[][] descStats = null;
		String projName = extractProjectName(tNode);
		
		String[] nodeDetails = extractNodeDetails(tNode);		
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		int projIdx = appInfo.getProjectIdByName(projName);

		/*descStats = appInfo
				.getProjectInfoAL()
				.get(projIdx)
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								projIdx)
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(projIdx)
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getExpToPdf().getVerticalParameters();*/
		WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
		.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);

		descStats = wsInfo.getDesStatsInfo().getProcessingInputs().getExpToPdf().getVerticalParameters();

		return descStats;
	}

	private static String[][] extractOutputContents(ApplicationInfo appInfo,
			TreeNode tNode, String analysis) {

		String projName = extractProjectName(tNode);
		int projIdx = appInfo.getProjectIdByName(projName);
		
		String[] nodeDetails = extractNodeDetails(tNode);		
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);

		analysis = analysis.toLowerCase();

		String[][] outputContents = null;
		if (analysis.equals("sca") == true) {
			/*outputContents = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getExpToPdf()
					.getVerticalParameters();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);

			outputContents = wsInfo.getScaInfo().getProcessingInputs().getExpToPdf().getVerticalParameters();

		} else if (analysis.equals("nps") == true) {

			/*outputContents = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getExpToPdf()
					.getVerticalParameters();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);

			outputContents = wsInfo.getNpsInfo().getProcessingInputs().getExpToPdf().getVerticalParameters();
		}

		return outputContents;
	}

	private static String[][] extractDosingTableContents(
			ApplicationInfo appInfo, TreeNode tNode) {

		String[][] dosingTable = null;

		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);

		/*dosingTable = appInfo.getProjectInfoAL().get(
				projIdx).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(projIdx)
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getExpToPdf().getDosingTable();*/
		
		WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
		.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);

		dosingTable = wsInfo.getNcaInfo().getProcessingInputs().getExpToPdf().getDosingTable();

		return dosingTable;
	}

	private static String[][] extractSubAreasTableContents(
			ApplicationInfo appInfo, TreeNode tNode) {

		String[][] subAreasTable = null;

		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);

		/*subAreasTable = appInfo.getProjectInfoAL().get(projIdx)
				.getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getExpToPdf().getSubAreasTable();*/
		
		WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
		.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);

		subAreasTable = wsInfo.getNcaInfo().getProcessingInputs().getExpToPdf().getSubAreasTable();

		return subAreasTable;
	}

	private static String[][] extractVerticalDisplayTableContents(
			ApplicationInfo appInfo, TreeNode tNode) {

		String projName = extractProjectName(tNode);
		
		String[][] verticalDisplayTable = null;

		/*verticalDisplayTable = appInfo.getProjectInfoAL().get(projIdx)
				.getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getExpToPdf().getVerticalParameters();*/
		
		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		
		WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
		.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);

		verticalDisplayTable = wsInfo.getNcaInfo().getProcessingInputs().getExpToPdf().getVerticalParameters();

		return verticalDisplayTable;
	}

	

	private static String[][] extractInitialParameters(ApplicationInfo appInfo,
			TreeNode tNode, String analysis) {
		String projName = extractProjectName(tNode);
				
		String[][] initialParameters = null;
		
		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		
		analysis = analysis.toLowerCase();
		
		if (analysis.equals("pk")== true)
		{
			/*initialParameters = appInfo
			.getProjectInfoAL()
			.get(projIdx)
			.getWorkBooksArrayList()
			.get(
					appInfo.getProjectInfoAL().get(
							projIdx)
							.getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL()
			.get(
					appInfo
							.getProjectInfoAL()
							.get(projIdx)
							.getWorkBooksArrayList()
							.get(
									appInfo
											.getProjectInfoAL()
											.get(
													projIdx)
											.getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getCaInfo()
			.getWorkSheetOutputs().getSpreadSheetOutputs()
			.getInitialParameterForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			initialParameters = wsInfo.getPkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
				.getInitialParameterForCA();
		}
		else if (analysis.equals("pd")== true)
		{

			/*initialParameters = appInfo
			.getProjectInfoAL()
			.get(projIdx)
			.getWorkBooksArrayList()
			.get(
					appInfo.getProjectInfoAL().get(
							projIdx)
							.getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL()
			.get(
					appInfo
							.getProjectInfoAL()
							.get(projIdx)
							.getWorkBooksArrayList()
							.get(
									appInfo
											.getProjectInfoAL()
											.get(
													projIdx)
											.getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getPdInfo()
			.getWorkSheetOutputs().getSpreadSheetOutputs()
			.getInitialParameterForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			initialParameters = wsInfo.getPdInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
				.getInitialParameterForCA();
		}
		else if (analysis.equals("mm")== true)
		{

			/*initialParameters = appInfo
			.getProjectInfoAL()
			.get(projIdx)
			.getWorkBooksArrayList()
			.get(
					appInfo.getProjectInfoAL().get(
							projIdx)
							.getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL()
			.get(
					appInfo
							.getProjectInfoAL()
							.get(projIdx)
							.getWorkBooksArrayList()
							.get(
									appInfo
											.getProjectInfoAL()
											.get(
													projIdx)
											.getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getMmInfo()
			.getWorkSheetOutputs().getSpreadSheetOutputs()
			.getInitialParameterForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			initialParameters = wsInfo.getMmInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
				.getInitialParameterForCA();
		}
		else if (analysis.equals("pkpdlink")== true)
		{
			/*initialParameters = appInfo
			.getProjectInfoAL()
			.get(projIdx)
			.getWorkBooksArrayList()
			.get(
					appInfo.getProjectInfoAL().get(
							projIdx)
							.getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL()
			.get(
					appInfo
							.getProjectInfoAL()
							.get(projIdx)
							.getWorkBooksArrayList()
							.get(
									appInfo
											.getProjectInfoAL()
											.get(
													projIdx)
											.getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getPkpdLinkInfo()
			.getWorkSheetOutputs().getSpreadSheetOutputs()
			.getInitialParameterForCA();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			initialParameters = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
				.getInitialParameterForCA();
		
		}
		else
		{

			/*initialParameters = appInfo
			.getProjectInfoAL()
			.get(projIdx)
			.getWorkBooksArrayList()
			.get(
					appInfo.getProjectInfoAL().get(
							projIdx)
							.getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL()
			.get(
					appInfo
							.getProjectInfoAL()
							.get(projIdx)
							.getWorkBooksArrayList()
							.get(
									appInfo
											.getProjectInfoAL()
											.get(
													projIdx)
											.getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getIrInfo()
			.getWorkSheetOutputs().getSpreadSheetOutputs()
			.getInitialParameterForCA();		*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			initialParameters = wsInfo.getIrInfo().getWorkSheetOutputs().getSpreadSheetOutputs()
				.getInitialParameterForCA();
		}		
		
		return initialParameters;
	}

	private static ArrayList<String> extractCompleteDataOutput(
			ApplicationInfo appInfo, TreeNode tNode, String analysis) {
		ArrayList<String> completeDataLines = null;
		
		String[] nodeDetails = extractNodeDetails(tNode);		
		int projIdx = appInfo.getProjectIdByName(nodeDetails[0]);
		int wbIdx = appInfo.getWorkBookIdByName(nodeDetails[0], nodeDetails[1]);
		int sheetIdx = appInfo.getWorkSheetIndexByName(nodeDetails[0], nodeDetails[1], nodeDetails[2]);
		
		analysis = analysis.toLowerCase();	
		
		System.out.println("nlahblahii");
	
		if (analysis.equals("nca")==true)
		{
			/*completeDataLines = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getExpToPdf().getText();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
	completeDataLines = wsInfo.getNcaInfo().getProcessingInputs().getExpToPdf().getText();
		}
		else if (analysis.equals("pk")==true)
		{

			/*completeDataLines = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getCaInfo().getWorkSheetOutputs().getTextoutput();*/
						
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
					.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
			
			completeDataLines = wsInfo.getPkInfo().getWorkSheetOutputs().getTextoutput();
		}
		else if (analysis.equals("mm")==true)
		{
			/*completeDataLines = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMmInfo().getWorkSheetOutputs().getTextoutput();*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			completeDataLines = wsInfo.getMmInfo().getWorkSheetOutputs().getTextoutput();
		
		}
		else if (analysis.equals("pd")== true)
		{
			/*completeDataLines = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPdInfo().getWorkSheetOutputs().getTextoutput();
					*/
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			completeDataLines = wsInfo.getPdInfo().getWorkSheetOutputs().getTextoutput();
		
		
		}
		else if (analysis.equals("pkpdlink")== true)
 		{

			/*completeDataLines = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getWorkSheetOutputs().getTextoutput();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			completeDataLines = wsInfo.getPkpdLinkInfo().getWorkSheetOutputs().getTextoutput();

		}
		else
 		{
			/*completeDataLines = appInfo
					.getProjectInfoAL()
					.get(projIdx)
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(projIdx)
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(projIdx)
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													projIdx)
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getIrInfo()
					.getWorkSheetOutputs().getTextoutput();*/
			
			WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIdx).getWorkBooksArrayList()
			.get(wbIdx).getWorkSheetObjectsAL().get(sheetIdx);
	
			completeDataLines = wsInfo.getIrInfo().getWorkSheetOutputs().getTextoutput();

		}
		
		return completeDataLines;
	}
	
	private static String extractProject(TreeNode tNode) {
		TreePath treePath = TreeUtilities.getPath(tNode);

		Object[] pathComps = treePath.getPath();

		String specPath = pathComps[1].toString();
		//specPath = specPath.substring(1,specPath.length()-1);
		return specPath;
	}
	
	private static String[] extractNodeDetails(TreeNode tNode) {
		String[] nodeDetails = new String[4];
		TreePath treePath = TreeUtilities.getPath(tNode);

		Object[] pathComps = treePath.getPath();

		nodeDetails[0] = pathComps[1].toString().substring(1,pathComps[1].toString().length()-1);
		
		String analysis_wbName_sheetName = pathComps[2].toString();
		
		StringTokenizer sTokenizer = new StringTokenizer(analysis_wbName_sheetName, "-");
		nodeDetails[3] = sTokenizer.nextToken().substring(1);//analysis
		nodeDetails[1] = sTokenizer.nextToken();//wbName
		String sheetName = sTokenizer.nextToken();
		nodeDetails[2] = sheetName.substring(0, sheetName.length()-1);//sheetName
		
		return nodeDetails;
	}
	
	private static String extractPath1(TreeNode tNode) {
		TreePath treePath = TreeUtilities.getPath(tNode);

		Object[] pathComps = treePath.getPath();

		String specPath = pathComps[1].toString();
		specPath = specPath.substring(1,specPath.length()-1);
		return specPath;
	}
	
	private static String extractProjectName(TreeNode tNode) {
		TreePath treePath = TreeUtilities.getPath(tNode);

		Object[] pathComps = treePath.getPath();

		String projName = pathComps[1].toString();
		projName = projName.substring(1,projName.length()-1);
		
		return projName;
	}
	
	private static Object[] extractThePath(TreeNode tNode) {
		TreePath treePath = TreeUtilities.getPath(tNode);
		Object[] pathComps = treePath.getPath();
		
		Object[] relPathComps = new Object[2];
		
		relPathComps[0] = pathComps[1].toString().substring(1,pathComps[1].toString().length()-1);
		relPathComps[1] = pathComps[2].toString().substring(1,pathComps[2].toString().length()-1);
		
		return relPathComps;
	}
	
	private static String derivePdfsPath(TreeNode tNode) {
		Object[] pathComps = extractThePath(tNode);
		
		String dirPath = "./linearplots";
		
		for (int j = 0; j < pathComps.length; j++) {
			dirPath += "/"+pathComps[j];
		}
		
		return dirPath;
	}


	private static String extractPath(TreeNode tNode) {
		TreePath treePath = TreeUtilities.getPath(tNode);

		Object[] pathComps = treePath.getPath();
		String specPath = pathComps[1].toString()+"-"+pathComps[2].toString();
		
		/*for (int i = 0; i < pathComps.length; i++) {
			
			if (specPath == null)
			{
				String Path = pathComps[i].toString();
				
				System.out.println("Path["+i+"]"+Path);
				specPath = Path.substring(1,Path.length()-1);
			}
			else
			{
				String Path = pathComps[i].toString();
				System.out.println("Path["+i+"]"+Path);
				specPath += Path.substring(1,Path.length()-1);
			}
			
		}
*/
		//specPath = specPath.substring(1,specPath.length()-1);
		return specPath;
	}

}

class CheckBoxNodeRenderer implements TreeCellRenderer {
  private JCheckBox leafRenderer = new JCheckBox();

  private DefaultTreeCellRenderer nonLeafRenderer = new DefaultTreeCellRenderer();

  Color selectionBorderColor, selectionForeground, selectionBackground,
      textForeground, textBackground;

  protected JCheckBox getLeafRenderer() {
    return leafRenderer;
  }

  public CheckBoxNodeRenderer() {
    Font fontValue;
    fontValue = UIManager.getFont("Tree.font");
    if (fontValue != null) {
      leafRenderer.setFont(fontValue);
    }
    Boolean booleanValue = (Boolean) UIManager
        .get("Tree.drawsFocusBorderAroundIcon");
    leafRenderer.setFocusPainted((booleanValue != null)
        && (booleanValue.booleanValue()));

    selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
    selectionForeground = UIManager.getColor("Tree.selectionForeground");
    selectionBackground = UIManager.getColor("Tree.selectionBackground");
    textForeground = UIManager.getColor("Tree.textForeground");
    textBackground = UIManager.getColor("Tree.textBackground");
  }

  public Component getTreeCellRendererComponent(JTree tree, Object value,
      boolean selected, boolean expanded, boolean leaf, int row,
      boolean hasFocus) {

    Component returnValue;
    if (leaf) {

      String stringValue = tree.convertValueToText(value, selected,
          expanded, leaf, row, false);
      leafRenderer.setText(stringValue);
      leafRenderer.setSelected(false);

      leafRenderer.setEnabled(tree.isEnabled());

      if (selected) {
        leafRenderer.setForeground(selectionForeground);
        leafRenderer.setBackground(selectionBackground);
      } else {
        leafRenderer.setForeground(textForeground);
        leafRenderer.setBackground(textBackground);
      }

      if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
        Object userObject = ((DefaultMutableTreeNode) value)
            .getUserObject();
        if (userObject instanceof CheckBoxNode) {
          CheckBoxNode node = (CheckBoxNode) userObject;
          leafRenderer.setText(node.getText());
          leafRenderer.setSelected(node.isSelected());
        }
      }
      returnValue = leafRenderer;
    } else {
      returnValue = nonLeafRenderer.getTreeCellRendererComponent(tree,
          value, selected, expanded, leaf, row, hasFocus);
    }
    return returnValue;
  }
}

class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {

  CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();

  ChangeEvent changeEvent = null;

  JTree tree;

  public CheckBoxNodeEditor(JTree tree) {
    this.tree = tree;
    //traverseTree();
  }


  public Object getCellEditorValue() {
    JCheckBox checkbox = renderer.getLeafRenderer();
    CheckBoxNode checkBoxNode = new CheckBoxNode(checkbox.getText(),
        checkbox.isSelected());
    return checkBoxNode;
  }

  public boolean isCellEditable(EventObject event) {
    boolean returnValue = false;
    if (event instanceof MouseEvent) {
      MouseEvent mouseEvent = (MouseEvent) event;
      TreePath path = tree.getPathForLocation(mouseEvent.getX(),
          mouseEvent.getY());
      if (path != null) {
        Object node = path.getLastPathComponent();
        if ((node != null) && (node instanceof DefaultMutableTreeNode)) {
          DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
          Object userObject = treeNode.getUserObject();
          returnValue = ((treeNode.isLeaf()) && (userObject instanceof CheckBoxNode));
        }
      }
    }
    return returnValue;
  }

  public Component getTreeCellEditorComponent(JTree tree, Object value,
      boolean selected, boolean expanded, boolean leaf, int row) {

    Component editor = renderer.getTreeCellRendererComponent(tree, value,
        true, expanded, leaf, row, true);

    // editor always selected / focused
    ItemListener itemListener = new ItemListener() {
      public void itemStateChanged(ItemEvent itemEvent) {
        if (stopCellEditing()) {
          fireEditingStopped();
        }
      }
    };
    if (editor instanceof JCheckBox) {
      ((JCheckBox) editor).addItemListener(itemListener);
    }

    return editor;
  }
}

class CheckBoxNode {
  String text;

  boolean selected;

  public CheckBoxNode(String text, boolean selected) {
    this.text = text;
    this.selected = selected;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean newValue) {
    selected = newValue;
  }

  public String getText() {
    return text;
  }

  public void setText(String newValue) {
    text = newValue;
  }

  public String toString() {
    return getClass().getName() + "[" + text + "/" + selected + "]";
  }
}

class NamedVector extends Vector {
  String name;

  public NamedVector(String name) {
    this.name = name;
  }

  public NamedVector(String name, Object elements[]) {
    this.name = name;
    for (int i = 0, n = elements.length; i < n; i++) {
      add(elements[i]);
    }
  }

  public String toString() {
    return "[" + name + "]";
  }
}

