package view;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.codecompany.jeha.ExceptionHandler;
import org.codecompany.jeha.core.HandlerUtil;

import view.DisplayContents;
import Common.CursorToolkitTwo;
import Common.ExtensionFileFilter;
import Common.MyHandler;
import Common.TestException;


public class ImportFile {

	public static DisplayContents DISP_CONTENTS = null;
	public static DisplayContents createDispContentsInstance(){
		if(DISP_CONTENTS == null){
			DISP_CONTENTS = new DisplayContents();
			
		}
		return DISP_CONTENTS;
	}
	
	
	
	String path;
	int importedFileCount = 0;
	String extensionFile;
	
	public static ImportFile IMP_FILE = null;
	public static ImportFile createImportFileInstance(){
		if(IMP_FILE == null){
			IMP_FILE = new ImportFile();
		}
		
		return IMP_FILE;
	}
	public ImportFile(){
		
	}

	@ExceptionHandler(handler = MyHandler.class)
	public void fileImport(){
			importedFileCount++;
			try{
				
				path = importFile();
				System.out.println("path is "+path);
				File file = new File(path);
				if(!file.exists()){
					TestException test = new TestException();
					test.throwCustomException(1);
				}
				else{
					CursorToolkitTwo.startWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
					int dotPos = path.lastIndexOf(".");
					extensionFile = path.substring(dotPos);
					DDViewLayer.createViewLayerInstance().isFronClickOnProjectExplorer = false;
					
					DisplayContents d = createDispContentsInstance();
					
				
					d.createFrame();
					d.fillFilesFrame(path,importedFileCount);
					CursorToolkitTwo.startWaitCursor(d.f.getRootPane());
					d.getdata(extensionFile, path);
					CursorToolkitTwo.stopWaitCursor(d.f.getRootPane());
					CursorToolkitTwo.stopWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
				}
			}catch(Exception e){
				
				ImportFile.DISP_CONTENTS = null;
			}
		
		}
	
		@ExceptionHandler(handler = MyHandler.class)
		public String importFile(){
		
			File selectedPfile = null;
			
				JFileChooser importFile = new JFileChooser();
				importFile.setCurrentDirectory(new File("."));
				importFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
				FileFilter textFileFilter = new ExtensionFileFilter("Text Files", new String[] { "txt" });
				importFile.setFileFilter(textFileFilter);
				FileFilter  cSVFileFilter= new ExtensionFileFilter("CSV Files", new String[] { "csv"});
				importFile.setFileFilter(cSVFileFilter);
				FileFilter  docFileFilter= new ExtensionFileFilter("Doc Files", new String[] { "doc" });
				importFile.setFileFilter(docFileFilter);
				FileFilter  odtFileFilter= new ExtensionFileFilter("ods Files", new String[] { "ods" });
				importFile.setFileFilter(odtFileFilter);
				FileFilter  excelFileFilter= new ExtensionFileFilter("Excel Files", new String[] { "xls","xlsx" });
				importFile.setFileFilter(excelFileFilter);
				importFile.showOpenDialog(null);
				selectedPfile = importFile.getSelectedFile();
				String path = selectedPfile.getPath();
				return path;
		
	}
}
