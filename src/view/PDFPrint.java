package view;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.pdfbox.exceptions.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


public class PDFPrint
{
    public PDFPrint()
    {
        //static class
    }
    
    public void invokePrintJob(String pdfFile) throws IOException, CryptographyException, InvalidPasswordException, PrinterException
    {
        PDDocument document = null;
        try
        {
            document = PDDocument.load( pdfFile );
            PrinterJob printJob = PrinterJob.getPrinterJob();
            document.print( printJob );
        }
        finally
        {
            if( document != null )
            {
                document.close();
            }
        }
   }

    //public static void main( String pdfFilepath,String printerindx ) throws Exception
    public static void main( String[] args) throws Exception
    {
    	PDFPrint pdfPrnt =new PDFPrint();
    	String pdfFile = "htmlreport1.html";
    	pdfPrnt.invokePrintJob(pdfFile);
    }
}



