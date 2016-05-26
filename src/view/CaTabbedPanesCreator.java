package view;

import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CaTabbedPanesCreator {

	JTabbedPane pdMainScreenexecutionTabbedPane;
	JDesktopPane setupTabDesktopPane;
	JDesktopPane resultsTabDesktopPane;
	JDesktopPane verificationTabDesktopPane;
	
	public CaTabbedPanesCreator(){
		
	}
	
	
	public static CaTabbedPanesCreator PD_TABBLE_DISP = null;
	public static CaTabbedPanesCreator createPdTabbedPaneInstance() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PD_TABBLE_DISP == null){
			PD_TABBLE_DISP = new CaTabbedPanesCreator();
		}
		return PD_TABBLE_DISP;
	}
	
	public void pdTabbedPanesCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createTabbedPanes();
	}
	
	private void createTabbedPanes() throws RowsExceededException, WriteException, BiffException, IOException {

		pdMainScreenexecutionTabbedPane = new JTabbedPane();
		pdMainScreenexecutionTabbedPane.setVisible(true);
		setupTabDesktopPane = new JDesktopPane();
		pdMainScreenexecutionTabbedPane.addTab("Setup", setupTabDesktopPane);
		resultsTabDesktopPane = new JDesktopPane();
		pdMainScreenexecutionTabbedPane.addTab("Results",
				resultsTabDesktopPane);
		/*verificationTabDesktopPane = new JDesktopPane();
		pdMainScreenexecutionTabbedPane.addTab("Verification",
				verificationTabDesktopPane);*/
		pdMainScreenexecutionTabbedPane.setSelectedIndex(0);
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		
		if(analysisType.equals("pk"))
		{
			PkMainScreenCreator.createPkMainScreenInstance().pkMainInternalFrame.getContentPane().add(
					pdMainScreenexecutionTabbedPane);	
		}
		else
		if(analysisType.equals("pd"))
		{
			PdMainScreenCreator.createPdMainScreenInstance().pdMainInternalFrame.getContentPane().add(
					pdMainScreenexecutionTabbedPane);	
		}
		else
			if(analysisType.equals("mm"))
			{
				MmMainScreenCreator.createMmMainScreenInstance().mmMainInternalFrame.getContentPane().add(
						pdMainScreenexecutionTabbedPane);	
			}
			
			else
				if(analysisType.equals("pkpdlink"))
				{
					PkPdLinkMainScreenCreator.createPkPdLinkMainScreenInstance().pkpdLinkMainInternalFrame.getContentPane().add(
							pdMainScreenexecutionTabbedPane);	
				}
				else
					if(analysisType.equals("irm"))
					{
						IrmMainScreenCreator.createIrmMainScreenInstance().irmMainInternalFrame.getContentPane().add(
								pdMainScreenexecutionTabbedPane);	
					}else
						if(analysisType.equals("ascii"))
						{
							AsciiMainScreen.createAsciiMainScreenInstance().asciiMainInternalFrame.getContentPane().add(
									pdMainScreenexecutionTabbedPane);	
						}
		
		
		

	}
}
