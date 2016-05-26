package view;

import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BQLTabbedPane {

	public static BQLTabbedPane BQL_TAB_PANE = null;
	public static BQLTabbedPane createBqlTabInst(){
		if(BQL_TAB_PANE == null){
			BQL_TAB_PANE = new BQLTabbedPane("just object creation");
		}
		return BQL_TAB_PANE;
	}
	
	public BQLTabbedPane(String dummy){
	}
	
	JTabbedPane bqlMainScreenexecutionTabbedPane;
	JDesktopPane setupTabDesktopPane;
	JDesktopPane resultsTabDesktopPane;
	JDesktopPane verificationTabDesktopPane;
	void bqlTabbedPaneCreation(){


		bqlMainScreenexecutionTabbedPane = new JTabbedPane();
		bqlMainScreenexecutionTabbedPane.setVisible(true);
		setupTabDesktopPane = new JDesktopPane();
		bqlMainScreenexecutionTabbedPane.addTab("Setup", setupTabDesktopPane);
		resultsTabDesktopPane = new JDesktopPane();
		bqlMainScreenexecutionTabbedPane.addTab("Results",
				resultsTabDesktopPane);
		verificationTabDesktopPane = new JDesktopPane();
		bqlMainScreenexecutionTabbedPane.addTab("Verification",
				verificationTabDesktopPane);
		bqlMainScreenexecutionTabbedPane.setSelectedIndex(0);
		BQLMainScreen.createBQLMainScrInst().bqlMainScreenIF.getContentPane().add(
				bqlMainScreenexecutionTabbedPane);
		bqlMainScreenexecutionTabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(bqlMainScreenexecutionTabbedPane.getSelectedIndex() == 0){
					BQLOptComp.createBQLOptINst().lloqRB.setEnabled(true);
					BQLOptComp.createBQLOptINst().missingRB.setEnabled(true);
					BQLOptComp.createBQLOptINst().zeroRB.setEnabled(true);
				} else if((bqlMainScreenexecutionTabbedPane.getSelectedIndex() == 1) ||(bqlMainScreenexecutionTabbedPane.getSelectedIndex() == 2)){
					BQLOptComp.createBQLOptINst().lloqRB.setEnabled(false);
					BQLOptComp.createBQLOptINst().missingRB.setEnabled(false);
					BQLOptComp.createBQLOptINst().zeroRB.setEnabled(false);
				}
				
			}
		});
		

	
	}
}
