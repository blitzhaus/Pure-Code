package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfCopyFields;
import com.itextpdf.text.pdf.PdfReader;
//import com.lowagie.text.pdf.PdfCopyFields;
//import com.lowagie.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class CombineDynamicPdfContents
{
	public String[] listFilesInDirectory(String directoryName)
	{
		String[] files = null;
		
		File dir = new File(directoryName);

		files = dir.list();
		
		return files;		
	}
	
	public String[] listFilteredFilesInDirectory(String directoryName, final String fileFilter)
	{
		String[] children = null;
		
		File dir = new File(directoryName);
		
		// It is also possible to filter the list of returned files.
		// This example does not return any files that has `.pdf'.
		FilenameFilter filter = new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return !name.contains(fileFilter);
		    }
		};
		children = dir.list(filter);

		return children;		
	}
	
	void convertJPegsToPdf(String directoryName) throws DocumentException, MalformedURLException, IOException
	{
		String[] fileNamesInDir = listFilteredFilesInDirectory(directoryName, ".pdf");
		
	    
		for (int i = 0; i < fileNamesInDir.length; i++) {
			
			String fileNameJPeg = fileNamesInDir[i];
			int idx = fileNameJPeg.indexOf(".jpeg");
			
			String fileNameTruncd = fileNameJPeg.substring(0, idx);

			// Create Document Object
			Document convertJpgToPdf = new Document();
			
			System.out.println(fileNameTruncd);
			// Create PdfWriter for Document to hold physical file
			PdfWriter.getInstance(convertJpgToPdf, new FileOutputStream(
					directoryName+"\\"+fileNameTruncd+".pdf"));
			convertJpgToPdf.open();
			// Get the input image to Convert to PDF
			Image convertJpg = Image.getInstance(directoryName+"\\"+fileNamesInDir[i]);
			// Add image to Document
			convertJpgToPdf.add(convertJpg);
			// Close Document
			convertJpgToPdf.close();
		}
	    
	   
	    System.out.println("Successfully Converted JPG to PDF in iText");
	
	}
	
	void convertJPegFilesToPdf(String directoryName) throws DocumentException, MalformedURLException, IOException
	{
		List<File> fileNamesInDir = retrieveFilesUnderDir(directoryName);
		
	    
		for (int i = 0; i < fileNamesInDir.size(); i++) {
			
			File fileNameJPeg = (File) fileNamesInDir.get(i);
			String fileName = fileNameJPeg.getName();
			
			int indexOfJPeg = fileNameJPeg.toString().indexOf(".jpeg");
			
			if (indexOfJPeg != -1) {

				String fileNameTruncd = fileNameJPeg.toString().substring(0, indexOfJPeg);

				// Create Document Object
				Document convertJpgToPdf = new Document();

				System.out.println(fileNameTruncd);
				// Create PdfWriter for Document to hold physical file
				PdfWriter.getInstance(convertJpgToPdf, new FileOutputStream(
						fileNameTruncd + ".pdf"));
				convertJpgToPdf.open();
				// Get the input image to Convert to PDF
				Image convertJpg = Image.getInstance(fileNameTruncd + ".jpeg");
				// Add image to Document
				convertJpgToPdf.add(convertJpg);
				// Close Document
				convertJpgToPdf.close();
			}
		}
	    
	   
	    System.out.println("Successfully Converted JPG to PDF in iText");
	
	}
	
	void convertJPegFilesToPdf(String directoryName, String opFileName) throws DocumentException, MalformedURLException, IOException
	{
		List<File> fileNamesInDir = retrieveFilesUnderDir(directoryName);
		
	    
		for (int i = 0; i < fileNamesInDir.size(); i++) {
			
			File fileNameJPeg = (File) fileNamesInDir.get(i);
			String fileName = fileNameJPeg.getName();
			
			int indexOfJPeg = fileNameJPeg.toString().indexOf(".jpeg");
			
			if (indexOfJPeg != -1) {

				String fileNameTruncd = fileNameJPeg.toString().substring(0, indexOfJPeg);

				// Create Document Object
				Document convertJpgToPdf = new Document();

				System.out.println(fileNameTruncd);
				// Create PdfWriter for Document to hold physical file
				PdfWriter.getInstance(convertJpgToPdf, new FileOutputStream(
						fileNameTruncd + ".pdf"));
				convertJpgToPdf.open();
				// Get the input image to Convert to PDF
				Image convertJpg = Image.getInstance(fileNameTruncd + ".jpeg");
				// Add image to Document
				convertJpgToPdf.add(convertJpg);
				// Close Document
				convertJpgToPdf.close();
			}
		}
	    
	   
	    System.out.println("Successfully Converted JPG to PDF in iText");
	
	}
	
	private List<File> retrieveFilesUnderDir(String dirPath)
	{
		List<File> list = new ArrayList<File>();
		
		File folder = new File("./linearplots");
       
        getFiles(folder, list);
        System.out.println("list.size = " + list.size());
        
        for (int i = 0; i < list.size(); i++) {
        	System.out.println("list element "+i+"="+list.get(i));
		}
		return list;
	}
	
	private void getFiles(File folder, List<File> list) {
        folder.setReadOnly();
        File[] files = folder.listFiles();
        for(int j = 0; j < files.length; j++) {
            list.add(files[j]);
            if(files[j].isDirectory())
                getFiles(files[j], list);
        }
    }
	
	

	
	void consolidatePdfFiles(String directoryName) throws DocumentException, IOException
	{
		String[] fileNamesInDir = listFilteredFilesInDirectory(directoryName, ".jpeg");
		
		FileOutputStream fos = new FileOutputStream( "Pdf-Combined.pdf" );
        PdfCopyFields copy = new PdfCopyFields( fos );
        
        for (int i = 0; i < fileNamesInDir.length; i++) {
        	FileInputStream fis1 = new FileInputStream( ".\\linearplots\\"+fileNamesInDir[i] );
        	PdfReader file1 = new PdfReader( fis1 );
        	copy.addDocument( file1 );
        	fis1.close();
        	file1.close();
		}
        
        copy.close();
	}
	
	void consolidateAllPdfFiles(String directoryName) throws DocumentException, IOException
	{
		List<File> fileNamesInDir = retrieveFilesUnderDir(directoryName);
		
		FileOutputStream fos = new FileOutputStream( "Pdf-Combined.pdf" );
        PdfCopyFields copy = new PdfCopyFields( fos );
        
        for (int i = 0; i < fileNamesInDir.size(); i++) {
        	
        	File fileNameJPeg = (File) fileNamesInDir.get(i);
        	
        	int indexOfJPeg = fileNameJPeg.toString().indexOf(".pdf");
        	
        	if (indexOfJPeg != -1)
        	{
        		FileInputStream fis1 = new FileInputStream( fileNameJPeg.toString() );
            	PdfReader file1 = new PdfReader( fis1 );
            	copy.addDocument( file1 );
            	fis1.close();
            	file1.close();
        	}
        	
        	
		}
        
        copy.close();
	}
	
	void consolidateAllPdfFiles(String directoryName, String opFileName) throws DocumentException, IOException
	{
		List<File> fileNamesInDir = retrieveFilesUnderDir(directoryName);
		
		FileOutputStream fos = new FileOutputStream( opFileName , true);
        PdfCopyFields copy = new PdfCopyFields( fos );
        
        for (int i = 0; i < fileNamesInDir.size(); i++) {
        	
        	File fileNameJPeg = (File) fileNamesInDir.get(i);
        	System.out.println("fileNamesInDir["+i+"].."+fileNameJPeg);
        	
        	int indexOfJPeg = fileNameJPeg.toString().indexOf(".pdf");
        	
        	if (indexOfJPeg != -1)
        	{
        		FileInputStream fis1 = new FileInputStream( fileNameJPeg.toString() );
            	PdfReader file1 = new PdfReader( fis1 );
            	copy.addDocument( file1 );
            	fis1.close();
            	file1.close();
        	}
        	
        	
		}
        
        copy.close();
	}


    // throws FileNotFoundException, IOException, DocumentException
    public static void main( String ... a ) throws Exception
    {
       // String fileHome = System.getProperty( "user.home" ) + "/Desktop/";

        System.out.println( "Start combine PDF files" );
       // FileInputStream fis1 = new FileInputStream( fileHome + "pdf-file-1.pdf" );
        //FileInputStream fis2 = new FileInputStream( fileHome + "pdf-file-2.pdf" );
        
      FileInputStream fis1 = new FileInputStream( "result1.pdf" );
      FileInputStream fis2 = new FileInputStream( "result2.pdf" );

        // now create pdfreaders using inputstreams of pdf contents
        PdfReader file1 = new PdfReader( fis1 );
        PdfReader file2 = new PdfReader( fis2 );

        FileOutputStream fos = new FileOutputStream( "Pdf-Combined.pdf" );
        PdfCopyFields copy = new PdfCopyFields( fos );

        copy.addDocument( file1 );
        copy.addDocument( file2 );
        copy.close();

        System.out.println( "Done ..." );
    } // psvm( .. )
} // class CombineDynamicPdfContents

