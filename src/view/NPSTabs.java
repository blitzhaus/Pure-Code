package view;

import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;

public class NPSTabs {

	public static NPSTabs NPS_TABS = null;
	public static NPSTabs createNPSTabsInst() {
		
		if(NPS_TABS == null){
			NPS_TABS = new NPSTabs("just object creation");
		}
		return NPS_TABS;
	}

	public NPSTabs(String dummy){
		
	}
	
	JTabbedPane ncaMainScreenexecutionTabbedPane;
	JDesktopPane setupTabDesktopPane;
	JDesktopPane resultsTabDesktopPane;
	JDesktopPane verificationTabDesktopPane;
	
	void cteateTabbedPanes() {
		
		ncaMainScreenexecutionTabbedPane = new JTabbedPane();
		setupTabDesktopPane = new JDesktopPane();
		ncaMainScreenexecutionTabbedPane.addTab("Setup", setupTabDesktopPane);
		resultsTabDesktopPane = new JDesktopPane();
		ncaMainScreenexecutionTabbedPane.addTab("Results",
				resultsTabDesktopPane);
		/*verificationTabDesktopPane = new JDesktopPane();
		ncaMainScreenexecutionTabbedPane.addTab("Verification",
				verificationTabDesktopPane);*/
		NPSMainPage.createNPSMainPageInst().npsMainInternalFrame.getContentPane().add(
				ncaMainScreenexecutionTabbedPane);

	}

}
