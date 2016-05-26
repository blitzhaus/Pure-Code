package Test;

import java.io.File;
import java.io.IOException;

import view.ApplicationInfo;
import view.PersistStructure;

public class TCRepository {
	
	private ApplicationInfo loadAppInfoBasedonId(String fileName)
	{
		ApplicationInfo appInfo = null;
		String jarFileName = "testcases/"+fileName;
		
		PersistStructure perstStr = new PersistStructure();
		try {
			appInfo = (ApplicationInfo) perstStr.readMyObject(jarFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appInfo;
	}
	
	ApplicationInfo[] case1()
	{
		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store2.ser");
		appInfo[1] = loadAppInfoBasedonId("store.ser");
		
		return appInfo;
	}

}
