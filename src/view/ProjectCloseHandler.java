package view;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.codecompany.jeha.ExceptionHandler;

import Common.ExtensionFileFilter;
import Common.MyHandler;

public class ProjectCloseHandler {
	
	public static ProjectCloseHandler PROJECT_CLOSE = null;
	public static ProjectCloseHandler createProjectCloseHandlerInst(){
		if(PROJECT_CLOSE == null){
			PROJECT_CLOSE = new ProjectCloseHandler();
		}		
		return PROJECT_CLOSE;
	}
	public ProjectCloseHandler(){		
	}
	
	@ExceptionHandler(handler = MyHandler.class)
	public void closeProject() {
		System.out.println("I am in close project");
		String message = "Do you want to save changes to the project:New Project?";
		String title = "Save Project";
		int optionSelected = JOptionPane.showConfirmDialog(DDViewLayer.createViewLayerInstance(),
				message, title, JOptionPane.YES_NO_CANCEL_OPTION);
		
		System.out.println("Selected option is...."+optionSelected);
		
		if (optionSelected == 0)
		{
			System.out.println("Yes Button Clicked..");
			String fileName = saveFile();
			System.out.println("fileName.."+fileName);
		}
		else if (optionSelected == 1)
		{
			System.out.println("No Button Clicked..");
			message = "<html>If you do not save all changes will be lost.<br>Do you want to continue without saving?</html>";
			title = "Project Close Warning";
			int yesNoOptionSelected = JOptionPane.showConfirmDialog(DDViewLayer.createViewLayerInstance(),
					message, title, JOptionPane.YES_NO_OPTION);
			
			if (yesNoOptionSelected == 0)
			{
				System.out.println("yesNoOptionSelected details: Yes Button Clicked..");
			}
			else if (yesNoOptionSelected == 1)
			{
				System.out.println("yesNoOptionSelected details: No Button Clicked..");
				String fileName = importFile();
				System.out.println("fileName.."+fileName);
			}
		}
		else if (optionSelected == 2)
		{
			System.out.println("Cancel Button Clicked..");
		}
		
	}
	
	@ExceptionHandler(handler = MyHandler.class)
	public String saveFile() {

		File selectedPfile = null;

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter textFileFilter = new ExtensionFileFilter("enc Files",
				new String[] { "ddproj" });
		chooser.setFileFilter(textFileFilter);
		chooser.showSaveDialog(null);
		selectedPfile = chooser.getSelectedFile();
		String path = selectedPfile.getPath();
		return path;

	}
	
	@ExceptionHandler(handler = MyHandler.class)
	public String importFile() {

		File selectedPfile = null;

		JFileChooser importFile = new JFileChooser();
		importFile.setCurrentDirectory(new File("."));
		importFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter textFileFilter = new ExtensionFileFilter("enc Files",
				new String[] { "ddproj" });
		importFile.setFileFilter(textFileFilter);
		
		int returnVal = importFile.showOpenDialog(null);
		
		
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("You chose to open this file: " +
		    		   importFile.getSelectedFile().getName());
		    }
		//selectedPfile = importFile.getSelectedFile();
		//String path = selectedPfile.getPath();
		String path = importFile.getSelectedFile().getPath();
		return path;

	}

}
