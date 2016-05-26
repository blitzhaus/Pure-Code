package Test;

import view.ApplicationInfo;
import junit.framework.TestCase;

public class TCRepositoryTest extends TestCase {

	TCRepository tcRepo = null;
	protected void setUp() throws Exception {
		tcRepo = new TCRepository();
	}

	protected void tearDown() throws Exception {
		tcRepo = null;
	}

	public void testCase1() {
		ApplicationInfo[] appInfo = tcRepo.case1();
		boolean testCase1Flag = false;
		
		if (appInfo[0].equals(appInfo[1])== true)
			testCase1Flag = true;
			
		assertTrue("Test-Case 1", testCase1Flag);
	}

}
