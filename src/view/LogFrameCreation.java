package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import Common.SystemTime;

public class LogFrameCreation {

	
	 public JInternalFrame logFrame;
	int logFrameOriginalWidth;
	int logFrameOriginalHeight;
	public JTable logTable;
	AbstractTableModel logTableMOdel;
	TableColumnModel logTableColumnModel;
	public int previousLogNumber = 0;

	
	public static LogFrameCreation LOG_FRAME = null;
	public static LogFrameCreation createLogFrameInstance(){
		if(LOG_FRAME == null){
			LOG_FRAME = new LogFrameCreation("");
		}
		return LOG_FRAME;
	}
	
	
	
	public void LogFrameCreation(){
		logFrameCreation();	
	}
	
	public LogFrameCreation(String dummy){
		
	}
	
	 
	
	private void logFrameCreation() {
		
		MenuBarCreation mbobj = DDViewLayer.createmenuBarInstance();
		FeaturesPnaleCreation fpobj = DDViewLayer.createFeaturesPanelInstance();
		MainTabbedIFCreation mtobj = DDViewLayer.createMTInstance();
		ComponentsPanelCreation cponj = DDViewLayer.createComponentsPaneInstance();
		DDViewLayer mlobj = DDViewLayer.createViewLayerInstance();
		
		logFrame = new JInternalFrame("Log", false,false,false,false);
		int y = fpobj.featuresPanel.getHeight()+mbobj.menuBarPanel.getHeight()+ mtobj.mainTabbedFrame.getHeight();
		logFrame.setLocation(0+cponj.componentsPanel.getWidth(), y);
		logFrame.setBackground(Color.white);
		logFrame.setSize((int)mlobj.screenSize.getWidth()-2*cponj.componentsPanel.getWidth(),(int) mlobj.screenSize.getHeight() - y-80);
		logFrame.setBorder(mlobj.b);
		logFrame.setVisible(true);
		logFrameOriginalWidth = logFrame.getWidth();
		logFrameOriginalHeight = logFrame.getHeight();
		logTable = new JTable(0,4);
		logTableMOdel = (AbstractTableModel) logTable.getModel();
		
		logTable.setPreferredScrollableViewportSize(new Dimension(logFrame.getWidth(),logFrame.getHeight()));
		logTable.setFillsViewportHeight(true);
		
		logTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	
		logTable.setBackground(Color.white);
		JTableHeader logTableHeader = logTable.getTableHeader();
		logTableColumnModel = logTableHeader.getColumnModel();
		TableColumn tc = logTableColumnModel.getColumn(0);
		tc.setHeaderValue("S.No");
		tc.setPreferredWidth(50);
		tc = logTableColumnModel.getColumn(1);
		tc.setHeaderValue("Time");
		tc.setPreferredWidth((logFrameOriginalWidth-150)/2);
		tc = logTableColumnModel.getColumn(2);
		tc.setHeaderValue("Action");
		tc.setPreferredWidth((logFrameOriginalWidth-150)/2);
		tc = logTableColumnModel.getColumn(3);
		tc.setHeaderValue("User");
		tc.setPreferredWidth(100);
		
		JScrollPane logTableScrollPane = new JScrollPane(logTable);
		logTableScrollPane.setPreferredSize(new Dimension(logFrame.getWidth(),logFrame.getHeight()));
		logTableScrollPane.setBackground(Color.white);
		logFrame.getContentPane().setBackground(Color.white);
		logFrame.getContentPane().add(logTableScrollPane);
		/*String[] s = {"1","12:20","click","ajith"};;*/
		logTable.setEnabled(false);
		//((DefaultTableModel) logTable.getModel()).insertRow(0, s);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		logTableColumnModel.getColumn(0).setCellRenderer( centerRenderer );
		logTableColumnModel.getColumn(1).setCellRenderer( centerRenderer );
		logTableColumnModel.getColumn(2).setCellRenderer( centerRenderer );
		logTableColumnModel.getColumn(3).setCellRenderer( centerRenderer );
		SystemTime sysTimeObj = SystemTime.createSystemTimeInstance(); 
		String[] s = new String[4];
			s[0] = Integer.toString(previousLogNumber+1);
			s[1] = sysTimeObj.systemTime();
			s[2] = "Loggged In";
			s[3] = System.getProperty("user.name");
		((DefaultTableModel) logTable.getModel()).insertRow(previousLogNumber, s);
		previousLogNumber++;

		mlobj.desktop.add(logFrame);
	}
	
}
