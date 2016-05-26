package view;

import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

public class HtmlPrint {
	
	void printAHtml(String fileName)
	{
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        DocFlavor flavor = DocFlavor.READER.TEXT_HTML;
        PrintService printService[] = 
          PrintServiceLookup.lookupPrintServices(flavor, pras);
        PrintService defaultService = 
          PrintServiceLookup.lookupDefaultPrintService();

        PrintService service = ServiceUI.printDialog(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration(), 200, 200,
          printService, defaultService, flavor, pras);
		if (service != null) {
			DocPrintJob job = service.createPrintJob();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(fileName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DocAttributeSet das = new HashDocAttributeSet();
			Doc document = new SimpleDoc(fis, flavor, das);
			try {
				job.print(document, pras);
			} catch (PrintException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		String fileName = "htmlreport.html";
		HtmlPrint htmlPrint = new HtmlPrint();
		htmlPrint.printAHtml(fileName);
	}

}
