package view;

import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaTabbedPanes {

	JTabbedPane ncaMainScreenexecutionTabbedPane;
	JDesktopPane setupTabDesktopPane;
	JDesktopPane resultsTabDesktopPane;
	JDesktopPane verificationTabDesktopPane;
	
	public NcaTabbedPanes(String dummy){
		
	}
	
	
	public static NcaTabbedPanes NCA_TABBLE_DISP = null;
	public static NcaTabbedPanes createNcaTabbedPaneInstance() throws RowsExceededException, WriteException, BiffException, IOException{
		if(NCA_TABBLE_DISP == null){
			NCA_TABBLE_DISP = new NcaTabbedPanes("just object creation");
		}
		return NCA_TABBLE_DISP;
	}
	
	public void NcaTabbedPanesCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createTabbedPanes();
	}
	
	private void createTabbedPanes() throws RowsExceededException, WriteException, BiffException, IOException {

		ncaMainScreenexecutionTabbedPane = new JTabbedPane();
		ncaMainScreenexecutionTabbedPane.setVisible(true);
		setupTabDesktopPane = new JDesktopPane();
		ncaMainScreenexecutionTabbedPane.addTab("Setup", setupTabDesktopPane);
		resultsTabDesktopPane = new JDesktopPane();
		ncaMainScreenexecutionTabbedPane.addTab("Results",
				resultsTabDesktopPane);
		/*verificationTabDesktopPane = new JDesktopPane();
		ncaMainScreenexecutionTabbedPane.addTab("Verification",
				verificationTabDesktopPane);*/
		ncaMainScreenexecutionTabbedPane.setSelectedIndex(0);
		NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame.getContentPane().add(
				ncaMainScreenexecutionTabbedPane);
		

	}
}
