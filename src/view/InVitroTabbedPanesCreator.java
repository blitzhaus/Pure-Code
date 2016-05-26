package view;

import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class InVitroTabbedPanesCreator {
	


	JTabbedPane inVitroMainScreenTabbedPane;
	JDesktopPane setupTabDesktopPane;
	JDesktopPane resultsTabDesktopPane;
	JDesktopPane verificationTabDesktopPane;
	
	public InVitroTabbedPanesCreator(){
		
	}
	
	
	public static InVitroTabbedPanesCreator INVITRO_TABBLE_DISP = null;
	public static InVitroTabbedPanesCreator createInVitroTabbedPaneInstance() throws RowsExceededException, WriteException, BiffException, IOException{
		if(INVITRO_TABBLE_DISP == null){
			INVITRO_TABBLE_DISP = new InVitroTabbedPanesCreator();
		}
		return INVITRO_TABBLE_DISP;
	}
	
	public void inVitroTabbedPanesCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createTabbedPanes();
	}
	
	private void createTabbedPanes() throws RowsExceededException, WriteException, BiffException, IOException {

		inVitroMainScreenTabbedPane = new JTabbedPane();
		inVitroMainScreenTabbedPane.setVisible(true);
		setupTabDesktopPane = new JDesktopPane();
		inVitroMainScreenTabbedPane.addTab("Setup", setupTabDesktopPane);
		resultsTabDesktopPane = new JDesktopPane();
		inVitroMainScreenTabbedPane.addTab("Results",
				resultsTabDesktopPane);
		
		inVitroMainScreenTabbedPane.setSelectedIndex(0);
		
	
			InVitroMainScreenCreator.createInVitroMainScreenInstance().inVitroMainInternalFrame.getContentPane().add(
					inVitroMainScreenTabbedPane);	
		
		
		

	}


}
