package Common;

import java.io.IOException;

import view.DisplayContents;
import view.PersistStructure;

public class WriteAppinfoForUnitTest {
	
	
	public void storeAppInfoForUnitTesting(String fileName, String folderName) {

		

		try {
			
			PersistStructure persistStructureObject = new PersistStructure();
			persistStructureObject.writeMyObject(DisplayContents
					.createAppInfoInstance(), folderName+"/"+fileName, folderName);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
