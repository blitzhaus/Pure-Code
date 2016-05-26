package view;

import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PolyExpoTabbedPanesCreator {
	

	


	JTabbedPane polyExpoMainScreenTabbedPane;
	JDesktopPane setupTabDesktopPane;
	JDesktopPane resultsTabDesktopPane;
	JDesktopPane verificationTabDesktopPane;
	
	public PolyExpoTabbedPanesCreator(){
		
	}
	
	
	public static PolyExpoTabbedPanesCreator POLYEXPO_TABBLE_DISP = null;
	public static PolyExpoTabbedPanesCreator createPolyExpoTabbedPaneInstance() throws RowsExceededException, WriteException, BiffException, IOException{
		if(POLYEXPO_TABBLE_DISP == null){
			POLYEXPO_TABBLE_DISP = new PolyExpoTabbedPanesCreator();
		}
		return POLYEXPO_TABBLE_DISP;
	}
	
	public void polyExpoTabbedPanesCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createTabbedPanes();
	}
	
	private void createTabbedPanes() throws RowsExceededException, WriteException, BiffException, IOException {

		polyExpoMainScreenTabbedPane = new JTabbedPane();
		polyExpoMainScreenTabbedPane.setVisible(true);
		setupTabDesktopPane = new JDesktopPane();
		polyExpoMainScreenTabbedPane.addTab("Setup", setupTabDesktopPane);
		resultsTabDesktopPane = new JDesktopPane();
		polyExpoMainScreenTabbedPane.addTab("Results",
				resultsTabDesktopPane);
		
		polyExpoMainScreenTabbedPane.setSelectedIndex(0);
		
	
			PolyExpoMainScreenCreator.createPolyExpoMainScreenInstance().polyExpoMainInternalFrame.getContentPane().add(
					polyExpoMainScreenTabbedPane);	
		
		
		

	}




}
