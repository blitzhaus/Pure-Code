package view;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class QcmainScreen extends JInternalFrame{

	public static QcmainScreen QCMAINSCRINST = null;
	public static QcmainScreen createQcMainScrInst(){
		if(QCMAINSCRINST == null){
			QCMAINSCRINST = new QcmainScreen();
		}
		return QCMAINSCRINST;
	}
	
	public QcmainScreen() {
		
		createSetupDisplay();
		createOptions();
		
	}

	private void createOptions() {
		// TODO Auto-generated method stub
		
	}

	private void createSetupDisplay() {
		
		JInternalFrame displayFrame = new JInternalFrame("", true,true,true,true);
		displayFrame.setContentPane(new JDesktopPane());
		displayFrame.setSize(DDViewLayer.createMTInstance().mainTabbedFrameOriginalWidth, DDViewLayer.createMTInstance().mainTabbedFrameoriginalHeight);
		displayFrame.setLocation(0, 0);
		displayFrame.setVisible(true);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane.add(displayFrame);
		displayFrame.moveToFront();
		createConditionsTable();
		
	}

	JTable qcTable;
	private void createConditionsTable() {
		
		qcTable = new JTable(1,3);
		TableColumnModel tcm  = qcTable.getColumnModel();
		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue("IF");
		
		tc = tcm.getColumn(1);
		tc.setHeaderValue("Condition");
		
		tc = tcm.getColumn(2);
		tc.setHeaderValue("Replacement");
		qcTable.addMouseListener(DDViewLayer.createViewLayerInstance());
	}
	
	
	
}
