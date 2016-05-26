package view;

import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;

public class ScaTabs {

	public static ScaTabs SCA_TABS = null;
	public static ScaTabs createScaTabsInst() {
		if(SCA_TABS == null){
			SCA_TABS = new ScaTabs("just object creation");
		}
		return SCA_TABS;
	}
	
	public ScaTabs(String dummy){
		
	}
	JTabbedPane ncaMainScreenexecutionTabbedPane;
	JDesktopPane setupTabDesktopPane;
	JDesktopPane resultsTabDesktopPane;
	JDesktopPane verificationTabDesktopPane;
	
	
	void cteateTabbedPanes() {
		// TODO Auto-generated method stub
		ncaMainScreenexecutionTabbedPane = new JTabbedPane();
		setupTabDesktopPane = new JDesktopPane();
		ncaMainScreenexecutionTabbedPane.addTab("Setup", setupTabDesktopPane);
		resultsTabDesktopPane = new JDesktopPane();
		ncaMainScreenexecutionTabbedPane.addTab("Results", resultsTabDesktopPane);
/*		verificationTabDesktopPane = new JDesktopPane();
		ncaMainScreenexecutionTabbedPane.addTab("Verification", verificationTabDesktopPane);*/
		SCAMainPage.createScainstance().scaMainInternalFrame.getContentPane().add(ncaMainScreenexecutionTabbedPane);
		
	}

}
