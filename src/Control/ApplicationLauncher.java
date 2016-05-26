package Control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.DDViewLayer;
import Model.DDModel;
import Model.ModelInterface;
import Model.ModelLayer;

public class ApplicationLauncher {

	
	static ModelInterface modelInst;
	public static void main(String[] args) throws FileNotFoundException{
		 try {
	            // Set cross-platform Java L&F (also called "Metal")
	        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());//getCrossPlatformLookAndFeelClassName()
	        
	        
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
		modelInst = new DDModel();
		DDControllerInterface contLayerInst = new DDController(modelInst);
		//System.setOut(new PrintStream(new File("Printing Statements")));
	}
}
