package view;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JTree;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import Common.SystemTime;


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


public class HtmlExporter {
	
	String opFileName = null;
	PrintWriter ofw = null;
	
	public String getOpFileName() {
		return opFileName;
	}

	public void setOpFileName(String opFileName) {
		this.opFileName = opFileName;
	}

	HtmlExporter()
	{
		
	}
	
	void persistResultsToPdf()
	{
		
	}
	
	void initializePdfWriterSetup(String opFileName, String sheetTitle) throws IOException {
		
		this.opFileName = opFileName;
		
		URL location = HtmlExporter.class.getProtectionDomain().getCodeSource().getLocation();
		
		String path = location.getPath();
		path = path.replaceAll("%20", " ");
		ofw = new PrintWriter(new File(opFileName));
		
		ofw.println("<html>");
		ofw.println("<STYLE TYPE=\"text/css\">");
		ofw.println("<!--");
		ofw.println("TD{font-family: Arial; font-size: 8pt;}");
		ofw.println("--->");
		ofw.println("</STYLE>");
		ofw.println("<body>");
		ofw.println("<h4 align=\"center\">"+"TATA CONSULTANCY SERVICES LTD"+"</h3>");
		ofw.println("<h4 align=\"center\">"+"DRUG DEVELOPMENT PLATFORM, Version 1.0"+"</h3>");
		ofw.println("<h4 align=\"right\">"+SystemTime.createSystemTimeInstance().systemTime()+"</h4>");
		
	}
	
	

	void printTableForHtml(String subTitle, String[] tblHdrs,
			String[][] tblData) throws IOException {
		ofw.println("<h3>"+subTitle+"</h3>");
		ofw.println("<TABLE BORDER=\"1\" BORDERCOLOR=\"black\">");
		ofw.println("<tbody>");
		
		ofw.println("<tr>");
		
		for (int i = 0; i < tblHdrs.length; i++) {
			ofw.println("<th bgcolor=\"white\">"+tblHdrs[i]+"</th>");
		}
		
		ofw.println("</tr>");
		
		for (int i = 1; i < tblData.length; i++) {
			ofw.println("<tr>");
			for (int j = 0; j < tblData[i].length; j++) {
				ofw.println("<td>"+tblData[i][j]+"</td>");
			}
			ofw.println("</tr>");	
		}
		
		ofw.println("</tbody>");
		
		ofw.println("</TABLE>");

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
	
	void writeLinesTpHtmlFile(String title, ArrayList<String> linestoOutput) throws IOException {
		//ofw.println("<h1>"+title+"</h1>");
		
		
		for (int i = 0; i < 6; i++) {
			String singleLine = linestoOutput.get(i);
			ofw.println("<p>"+singleLine+"</p>");
			}
		
		//PrintingSAtarting text as a table format
		ArrayList<String> tempStartingTextForTable = new ArrayList<String>();
		for (int i = 6; i < 17; i++) {
			String StartingTextForTablesingleLine = linestoOutput.get(i);
			tempStartingTextForTable.add(StartingTextForTablesingleLine);	
		}
		makeTableforStartingTextHtml(tempStartingTextForTable);
		
		for (int i= 17; i < linestoOutput.size();i++) {
			String singleLine = linestoOutput.get(i);
            
			if (singleLine.contains("\t"))
			 	{System.out.println("For loop i kavalue "+i);
				  i=makeTableforTextHtml(linestoOutput,i);
				    System.out.println("i ka value "+i);}				
			 else
				 ofw.println("<p>"+singleLine+"</p>");
			 
			
		}
		
		
		
		
		/*for (int i = 0; i < linestoOutput.size(); i++) {
			String singleLine = linestoOutput.get(i);
			ofw.println("<p>"+singleLine+"</p>");
		}*/
	}
	
	private int makeTableforTextHtml(ArrayList<String> tempAL1,int index) {

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
	    	       {  printTextTableForHtml(precolumnCount,temp);
	    	    	 
	    	    	  break;
	    	       }
	    	      }
	    	    else{printTextTableForHtml(precolumnCount,temp);
	    	    	break;}
	        }
	       System.out.println("i inside value "+i);
	     return i-1; 

	}

	private void printTextTableForHtml(int precolumnCount,
			ArrayList<String[]> temp) {
		//ofw.println("<h2>"+subTitle+"</h2>");
		ofw.println("<TABLE BORDER=\"0\" BORDERCOLOR=\"white\">");
		ofw.println("<tbody>");
		
		ofw.println("<tr>");
		
		/*for (int i = 0; i < columnCountStartingText; i++) {
			ofw.println("<th bgcolor=\"white\">"+tblHdrs[i]+"</th>");
		}*/
		
		ofw.println("</tr>");
		
		for (int i = 0; i < temp.size(); i++) {
			ofw.println("<tr>");
			String[] cells = temp.get(i);
			for (int j = 0; j < cells.length; j++) {
				ofw.println("<td>"+cells[j]+"</td>");
			}
			ofw.println("</tr>");	
		}
		
		ofw.println("</tbody>");
		
		ofw.println("</TABLE>");

		
	
		
		
	}

	private void makeTableforStartingTextHtml(
			ArrayList<String> tempAL2) {

		String singleLineStartingText = tempAL2.get(1);

		String[] tempStartingText = singleLineStartingText.split("\t");
		int columnCountStartingText = tempStartingText.length;

		ArrayList<String[]> tempStartingTextMain = new ArrayList<String[]>();
		for (int i = 0; i < tempAL2.size(); i++) {
			String singleLineStartingText2 = tempAL2.get(i);

			String[] tempStartingText2 = singleLineStartingText2.split("\t");

			tempStartingTextMain.add(tempStartingText2);

		}
		printTextTableForStartingTextHtml(columnCountStartingText,
				tempStartingTextMain);
	
		
	}

	private void printTextTableForStartingTextHtml(int columnCountStartingText,
			ArrayList<String[]> tempStartingTextMainHtml) {
		//ofw.println("<h2>"+subTitle+"</h2>");
		ofw.println("<TABLE BORDER=\"0\" BORDERCOLOR=\"white\">");
		ofw.println("<tbody>");
		
		ofw.println("<tr>");
		
		/*for (int i = 0; i < columnCountStartingText; i++) {
			ofw.println("<th bgcolor=\"white\">"+tblHdrs[i]+"</th>");
		}*/
		
		ofw.println("</tr>");
		
		for (int i = 0; i < tempStartingTextMainHtml.size(); i++) {
			ofw.println("<tr>");
			String[] cells = tempStartingTextMainHtml.get(i);
			for (int j = 0; j < cells.length; j++) {
				ofw.println("<td>"+cells[j]+"</td>");
			}
			ofw.println("</tr>");	
		}
		
		ofw.println("</tbody>");
		
		ofw.println("</TABLE>");

		
	}

	
	
	
	
	public void saveChartAsJPEG(File file, JFreeChart chart, int width, int height) throws IOException 
    {
    	OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
    	ChartUtilities.writeChartAsJPEG(out, chart, width, height);
    	String fileName = file.getAbsolutePath();
    	
    	ImageManipulation imgManipulator = new ImageManipulation();
    	//String imgBase64 = imgManipulator.convertImgToBase64(fileName);
    	//byte[] imageByteArray = imgManipulator.decodeImage(imgBase64);
    	//String base64Img = new String(imageByteArray);
    	String encoded = Base64.encodeFromFile(fileName);
    	
    	ofw.println("<img src=data:image/jpg;base64,"+encoded+" alt="+fileName+" width=\"20\" height=\"20\" >");
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
		
		ofw.println("</body>");
		ofw.println("</html>");
		ofw.close();
	}

}
