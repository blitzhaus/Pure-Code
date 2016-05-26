package Common;

import javax.swing.table.DefaultTableModel;

import view.LogFrameCreation;
import view.DDViewLayer;


public class LogEntry {


	LogFrameCreation lfObj = DDViewLayer.createLogFrameInstance();
	public static LogEntry LOG_ENTRY = null;
	public static LogEntry createLogEntryInstance(){
		if(LOG_ENTRY == null){
			LOG_ENTRY = new LogEntry();
			
		}
		return LOG_ENTRY;
	}
	
	public void logEntry(String action){
		String[] s = new String[4];
		s[0] = Integer.toString(lfObj.previousLogNumber+1);
		s[1] = Common.SystemTime.createSystemTimeInstance().systemTime();
		s[2] = action;
		s[3] = System.getProperty("user.name");
	((DefaultTableModel) lfObj.logTable.getModel()).insertRow(lfObj.previousLogNumber, s);
	lfObj.previousLogNumber++;

	}
}
