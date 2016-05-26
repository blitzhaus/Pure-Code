package Test;

import java.util.ArrayList;
import java.util.Arrays;

import Common.Comparator;
import Common.MyComparator;

import view.ApplicationInfo;
import view.BQLInfo;
import view.DstatsInfo;
import view.NcaInfo;
import view.NPSInfo;
import view.PlotmavenInfo;
import view.ScaInfo;
import view.TableMavenInfo;
import view.TableTransformationsInfo;
import view.WorkBookInfo;
import junit.framework.TestCase;

public class TestTestUnitIndividually extends TestCase {

	Comparator comp = null;
	NcaRepository ncaRep = null;


	
	
		
	protected void setUp() throws Exception {
		comp = MyComparator.createMyCompInst();	
		ncaRep = new NcaRepository();
	}

	protected void tearDown() throws Exception {
		ncaRep = null;
	}
	
	NcaInfo getNcaInfo(ApplicationInfo appInfo){
		
		
		return	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(0).getNcaInfo();
		}
		
		BQLInfo getBqlInfo(ApplicationInfo appInfo){
			
			return	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(4).getBqlInfo();
		}
		
		NPSInfo getNPSInfo(ApplicationInfo appInfo){
			
			return	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(1).getNpsInfo();
		}
		
		ScaInfo getSCAInfo(ApplicationInfo appInfo){
			
			return	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(2).getScaInfo();
		}
		
		
		DstatsInfo getDSInfo(ApplicationInfo appInfo){
			
			return	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(3).getDesStatsInfo();
		}
		
		TableTransformationsInfo getTTInfo(ApplicationInfo appInfo){
			
			return	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(5).getTtInfo();
		}
		
		TableMavenInfo getTMInfo(ApplicationInfo appInfo){
			
			return	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getTmInfo();
		}
		
		PlotmavenInfo getPMInfo(ApplicationInfo appInfo){
			
			return	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo();
		}
		
	
		private WorkBookInfo getWBInfo(ApplicationInfo appInfo) {
			
			
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex());
		}
		private String[][][] convertToArray(
				ArrayList<String[][]> copyOfOriginalDataStructures) {

			String[][][] array = new String[copyOfOriginalDataStructures.size()][][];
			for (int i = 0; i < array.length; i++) {
				array[i] = copyOfOriginalDataStructures.get(i);
			}
			return array;
		}
		
		public void testwbOriginalDSCase80(){
			ApplicationInfo[] appInfo = ncaRep.case80();
			
			WorkBookInfo wb1 = getWBInfo(appInfo[0]);
			WorkBookInfo wb2 = getWBInfo(appInfo[1]);
			boolean isMapDataEqual = false;
			if(Arrays.deepEquals(convertToArray(wb1.getCopyOfOriginalDataStructures()), convertToArray(wb2.getCopyOfOriginalDataStructures()))){
				isMapDataEqual = true;
			}
			assertTrue(isMapDataEqual);
		}
}
