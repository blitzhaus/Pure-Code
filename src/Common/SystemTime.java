package Common;

public class SystemTime {

	
	public static SystemTime SYSTIME = null;
	
	
	//singleton design pattern implementation for system time
	public static SystemTime createSystemTimeInstance(){ 
		if(SYSTIME == null){
			SYSTIME = new SystemTime();
		}
		
		return SYSTIME;
	}
	
	public String systemTime() {
		java.sql.Timestamp currentTime = new java.sql.Timestamp(new java.util.Date().getTime());
		String currenttime = String.valueOf(currentTime);
		String date = currenttime.substring(0, 10);
		String time = currenttime.substring(11, 19);
		//logArea.append("     " + date + "  " + time+"\n");
		return (date+"/"+time);
	}
}
