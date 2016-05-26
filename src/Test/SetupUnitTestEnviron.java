package Test;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import view.HtmlExporter;

public class SetupUnitTestEnviron {
	
	void createTestFolderInProject()
	{
		File f = new File(".\\testcases");
		f.mkdir();
	}
	
	public static void copyAppInfoObjsTo(String tcFileName)
	{
		try {
			File aFile = new File("store.ser");
			File bFile = new File("testcases/"+tcFileName);
			
			FileUtils.copyFile(aFile, bFile);

			/*if (afile.renameTo(bFile)) {
				System.out.println("File is moved successfully!");
			} else {
				System.out.println("File is failed to move!");
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void listFilesInDir(String dirPath)
	{
		File folder = new File(dirPath);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		    }
		}
	}

	
	public static void main(String[] args) {
		SetupUnitTestEnviron setupEnv = new SetupUnitTestEnviron();
		setupEnv.createTestFolderInProject();
		setupEnv.copyAppInfoObjsTo("TestCase4.ser");
		/*String dirPath = ".\\testcases";		
		setupEnv.listFilesInDir(dirPath);*/
	}

}
