package view;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JTree;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import orinoco.Alignment;
import orinoco.Colour;
import orinoco.Column;
import orinoco.Heading;
import orinoco.Document;
import orinoco.Heading;
import orinoco.Column;
import orinoco.Table;
import orinoco.Alignment;
import orinoco.LayoutWriter;
import orinoco.TextWriter;
import orinoco.Paper;
import orinoco.PageNumber;
import orinoco.PostscriptWriter;
import orinoco.PDFWriter;
import orinoco.Colour;
import orinoco.OutputFormatWriter;

import orinoco.Font;


public class PdfExporter {
	
	orinoco.Document doc;
	
	String opFileName = null;
	
	
	
	//JTree tree;
	
	public String getOpFileName() {
		return opFileName;
	}

	public void setOpFileName(String opFileName) {
		this.opFileName = opFileName;
	}

	PdfExporter()
	{
		//this.tree = tree;
	}
	
	void persistResultsToPdf()
	{
		
	}
	
	void initializePdfWriterSetup(String opFileName, String sheetTitle) throws IOException {
		OutputFormatWriter ofw = null;
		this.opFileName = opFileName;
		ofw = new PDFWriter(new File(opFileName, sheetTitle));

		long start = System.currentTimeMillis();

		// Create the document
		doc = new orinoco.Document(Paper.LETTER, ofw);
		
		// Define a header
	  	orinoco.Font titleFont = new orinoco.Font(orinoco.Font.HELVETICA_BOLD, 14);
	  	LayoutWriter header = doc.getHeader();
	 
	  	Column[] headercols = new Column[4];
	  	headercols[0] = new  Column(2);
	  	headercols[1] = new Column(13, Alignment.CENTRE, titleFont);
	  	headercols[2] = new Column(2, Alignment.RIGHT);
	  	headercols[3] = new Column(2);

	  	Table ht = header.createTable(headercols);
	 	String[] titlerow = new String[]{"",
	 			"..........",
									   ""};
	  	ht.addCells(titlerow);
	  	TextWriter w = ht.getCellWriter(3);
	  	w.writeMacro(new PageNumber(" ", "", doc));
	  	ht.writeRow();
	  	ht.close();

	  	header.space(0.2);

	  	// Define a footer
	  	orinoco.Font footerFont = new orinoco.Font(orinoco.Font.TIMES, 8);
	  	LayoutWriter footer= doc.getFooter();
	  	footer.space(0.1);
	  	footer.drawLine();
	  	footer.space(0.1);
	  	footer.writeMacroLine(new PageNumber("page ", "", doc),
							footerFont, Alignment.RIGHT);
		doc.open();

	}
	
	

	void printOrinocoTableForPdf(String subTitle, String[] tblHdrs,
			String[][] tblData) throws IOException {
		orinoco.Font tableHeaderFont = new Font(Font.TIMES_BOLD, 9);
		doc.setFont(tableHeaderFont);
		doc.writeLine(subTitle);

		Column[] cols = new Column[tblHdrs.length];
		Heading[] hdrs = new Heading[tblHdrs.length];
		
		Table table;

		// orinoco table data loading starting point.

		Font f1 = new Font(Font.TIMES, 8);
		doc.setFont(f1);

		doc.newLine();
		
		for (int i = 0; i < tblHdrs.length; i++) {

			Column col1 = new Column(3, Alignment.CENTRE);
			cols[i] = col1;

			Heading hdr1 = new Heading(tblHdrs[i], Alignment.CENTRE,
					tableHeaderFont);
			hdrs[i] = hdr1;

		}

		table = doc.createTable(cols, hdrs);
		table.setHeaderBackground(Colour.GREY_80);
		table.setBorder(2);
		table.setHeaderBorder(2, 2);
		table.setColumnBorder(1);
		table.setRowBorder(1);
		
		

		for (int i = 1; i < tblData.length; i++) {
			table.addRow(tblData[i]);
			//table.add;
		}
		table.close();

		doc.newLine();

		// orinoco table data loading end point.

	}
	
	void printOrinocoTableForPdfForUserSettings(String subTitle, String[] tblHdrs,
			String[][] tblData) throws IOException {
		orinoco.Font tableHeaderFont = new Font(Font.TIMES_BOLD, 9);
		doc.setFont(tableHeaderFont);
		doc.writeLine(subTitle);

		Column[] cols = new Column[tblHdrs.length];
		Heading[] hdrs = new Heading[tblHdrs.length];
		
		Table table;

		// orinoco table data loading starting point.

		Font f1 = new Font(Font.TIMES, 8);
		doc.setFont(f1);

		doc.newLine();

		for (int i = 0; i < tblHdrs.length; i++) {

			Column col1 = new Column(3, Alignment.LEFT);
			cols[i] = col1;

			Heading hdr1 = new Heading(tblHdrs[i], Alignment.CENTRE,
					tableHeaderFont);
			hdrs[i] = hdr1;

		}

		table = doc.createTable(cols, hdrs);
		table.setHeaderBackground(Colour.GREY_80);
		table.setBorder(2);
		table.setHeaderBorder(2, 2);
		table.setColumnBorder(0);
		table.setRowBorder(0);
		
	
		for (int i = 1; i < tblData.length; i++) {
			table.addRow(tblData[i]);
			//table.add;
		}
		table.close();

		doc.newLine();

		// orinoco table data loading end point.

	}

	
	
	public double round(double value, int decimalPlace)
	{
	    double power_of_ten = 1;
	    while (decimalPlace-- > 0)
	    {
	      power_of_ten *= 10.0;
	    }
	    return Math.round(value * power_of_ten)/ power_of_ten;
	}
	
	void writeLinesTpPdf(String str, ArrayList<String> linestoOutput) throws IOException {
		orinoco.Font f1 = new orinoco.Font(Font.TIMES, 8);
		doc.setFont(f1);
		
		doc.writeLine(str);

		for (int i = 0; i < 6; i++) {
			String singleLine = linestoOutput.get(i);
			doc.writeLine(singleLine);
			 
			doc.newLine();	
		}

		//PrintingSAtarting text as a table format
		ArrayList<String> tempStartingTextForTable = new ArrayList<String>();
		for (int i = 6; i < 17; i++) {
			String StartingTextForTablesingleLine = linestoOutput.get(i);
			tempStartingTextForTable.add(StartingTextForTablesingleLine);	
		}
		makeTableforStartingText(tempStartingTextForTable);


				for (int i= 17; i < linestoOutput.size();i++) {
					String singleLine = linestoOutput.get(i);
		            
					if (singleLine.contains("\t"))
					 	{System.out.println("For loop i kavalue "+i);
						  i=makeTableforText(linestoOutput,i);
						    System.out.println("i ka value "+i);}				
					 else
					    	doc.writeLine(singleLine);
					 
					doc.newLine();
				}
			}
			public int makeTableforText(ArrayList<String> tempAL1,int index) throws IOException
			{
				    ArrayList<String[]> temp = new ArrayList<String[]>();
				    String singleLine = tempAL1.get(index);
				   int i=index;
				   String[] temp1    = singleLine.split("\t");
				   int columnCount=temp1.length;
				   int precolumnCount=columnCount;
				       for(i=index;i<tempAL1.size();i++){
				    	    singleLine = tempAL1.get(i);
				    	    if (singleLine.contains("\t"))
				    	      {temp1    = singleLine.split("\t");
				    	       columnCount=temp1.length;
				    	       System.out.println("precount= "+precolumnCount+ "columnCount= "+columnCount);
				    	       if(precolumnCount==columnCount)
				    	       {
				    	    	 temp.add(temp1);
				    	    	 precolumnCount=columnCount; 
				    	       }
				    	       else
				    	       {  printTextTableForPdf(precolumnCount,temp);
				    	    	 
				    	    	  break;
				    	       }
				    	      }
				    	    else{printTextTableForPdf(precolumnCount,temp);
				    	    	break;}
				        }
				       System.out.println("i inside value "+i);
				     return i-1; 
			}
			public void makeTableforStartingText(ArrayList<String> tempAL2) throws IOException
			{
				String singleLineStartingText = tempAL2.get(1);
			    
				   
				   String[] tempStartingText   = singleLineStartingText.split("\t");
				   int columnCountStartingText=tempStartingText.length;
			
				    ArrayList<String[]> tempStartingTextMain = new ArrayList<String[]>();
				    for (int i= 0; i < tempAL2.size();i++) {
						String singleLineStartingText2 = tempAL2.get(i);
				    
				   
				   String[] tempStartingText2    = singleLineStartingText2.split("\t");
				   
				   tempStartingTextMain.add(tempStartingText2);
				    	    	
				    	         
				    	    	 
				    	    	
			}printTextTableForStartingTextPdf(columnCountStartingText,tempStartingTextMain);
				    }
			
			
			public void printTextTableForStartingTextPdf(int tblHdrstartingText, ArrayList<String[]> tblDataStartingTextMain)
			throws IOException {
			orinoco.Font tableHeaderFont = new Font(Font.TIMES_BOLD, 9);
			doc.setFont(tableHeaderFont);

			Column[] cols = new Column[tblHdrstartingText];

			Table table;

			// orinoco table data loading starting point.

			Font f1 = new Font(Font.TIMES, 8);
			doc.setFont(f1);

			doc.newLine();

			for (int i = 0; i < tblHdrstartingText; i++) {

			Column col1 = new Column(9, Alignment.LEFT);
			cols[i] = col1;

			}

			table = doc.createTable(cols);
			table.setHeaderBackground(Colour.DEFAULT_BACKGROUND);
			table.setBorder(0);

			table.setColumnBorder(0);
			table.setRowBorder(0);

			for (int i = 0; i < tblDataStartingTextMain.size(); i++) {

			/*String temp[] = tblData.get(i);*/

			table.addRow(tblDataStartingTextMain.get(i));
			// table.add;
			}
			table.close();

			doc.newLine();

			// orinoco table data loading end point.

			}
			
			
			public void printTextTableForPdf(int tblHdrs, ArrayList<String[]> tblData)
			throws IOException {
			orinoco.Font tableHeaderFont = new Font(Font.TIMES_BOLD, 9);
			doc.setFont(tableHeaderFont);

			Column[] cols = new Column[tblHdrs];

			Table table;

			// orinoco table data loading starting point.

			Font f1 = new Font(Font.TIMES, 8);
			doc.setFont(f1);

			doc.newLine();

			for (int i = 0; i < tblHdrs; i++) {

			Column col1 = new Column(3, Alignment.LEFT);
			cols[i] = col1;

			}

			table = doc.createTable(cols);
			table.setHeaderBackground(Colour.DEFAULT_BACKGROUND);
			table.setBorder(0);

			table.setColumnBorder(0);
			table.setRowBorder(0);

			for (int i = 0; i < tblData.size(); i++) {

			/*String temp[] = tblData.get(i);*/

			table.addRow(tblData.get(i));
			// table.add;
			}
			table.close();

			doc.newLine();

			// orinoco table data loading end point.

			}
	
	public void saveChartAsJPEG(File file, JFreeChart chart, int width, int height) throws IOException 
    {
    	OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
    	ChartUtilities.writeChartAsJPEG(out, chart, width, height);
    	out.close();
    }
	
	public void savePlots(JFreeChart[] freeCharts, String[] titles) throws IOException
	{
		for (int i = 0; i < titles.length; i++) {
			File fileName1 = new File("./charts/"+titles[i]+".jpeg");
			saveChartAsJPEG(fileName1, freeCharts[i], 400, 300);
		}
	}
	
	void closeTheDocument() throws IOException
	{
		doc.close();
	}

}
