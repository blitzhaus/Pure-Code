package view;

import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;

public class DSTabs {

	public static DSTabs DS_TAB = null;
	public static DSTabs createDSTabInst() {
		if(DS_TAB ==null){
			DS_TAB = new DSTabs();
		}
		return DS_TAB;
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
		DescriptiveStatMainPage.createDescStatMainPageInst().desStatsMainIF.getContentPane().add(
				ncaMainScreenexecutionTabbedPane);

	}
}
