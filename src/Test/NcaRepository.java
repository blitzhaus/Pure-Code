package Test;

import java.io.IOException;

import view.ApplicationInfo;
import view.NcaInfo;
import view.PersistStructure;

public class NcaRepository {
	
	private ApplicationInfo loadAppInfoBasedonId(String fileName)
	{
		ApplicationInfo appInfo = null;
		String jarFileName = "TestCase\\"+fileName;
		
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
	

	ApplicationInfo[] case1(){
		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaMapCase1.ser");
		
		return appInfo;
	}


	public ApplicationInfo[] case2() {
		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaDoseCase2.ser");
		
		return appInfo;
	}


	public ApplicationInfo[] case3() {
		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncalambdazCase3.ser");
		
		return appInfo;
	}


	public ApplicationInfo[] case4() {
		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaParamUnitsCase4.ser");
		
		return appInfo;
	}


	public ApplicationInfo[] case5() {
		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaParamNamesCase5.ser");
		
		return appInfo;
	}


	public ApplicationInfo[] case6() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaModelOptionsCase6.ser");
		
		return appInfo;
	
}


	public ApplicationInfo[] case7() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaExportToPdfCase7.ser");
		
		return appInfo;
	
}


	public ApplicationInfo[] case8() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaDataCase8.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case9() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaProfParamCase9.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case10() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaSubAreasCase10.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case11() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaProfileCase11.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case12() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaColumnPropCase12.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case13() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaWSoutputCase13.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case14() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case15() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case16() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case17() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case18() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case19() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case20() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case21() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case22() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case23() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case24() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case25() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case26() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo[] case27() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("npsMappingCase27.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case28() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("npsDosingCase28.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case29() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("npsModelOptiosCase29.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case30() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("npsExportToPdfCase30.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case31() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("npsOutParamValuesCase31.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case32() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("npsWSOutputCase32.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case33() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("npsColumnPropCase33.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case34() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("npsVertParamCase34.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case35() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case36() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case37() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case38() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case39() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo case40() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo[] case41() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ttMappingCase41.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case42() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ttCustomPointCase42.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case43() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ttNValue43.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case44() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ttBaseFunc44.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case45() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ttFunc45.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case46() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ttArithmeticFunc46.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case47() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ttFuncType47.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case48() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ttData48.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case49() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");
		
		
		return appInfo;
	
	}


	public ApplicationInfo[] case50() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ttOutputVertParam50.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case51() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ttOutputTransColumn51.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case52() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case53() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ttOutputText53.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case54() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo case55() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case56() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ttColumnProp56.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case57() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("bqlColumnProp57.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case58() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("bqlMapping58.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case59() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("bqlRules59.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case60() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo case61() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case62() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("bqlSortedColumns62.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case63() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case64() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("bqlData64.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case65() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case66() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("bqlOutConc66.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case67() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case68() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("bqlOutVertParam68.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case69() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case70() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("scaColumnProp70.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case71() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("scaMapping71.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case72() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case73() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("scaModelOptions73.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo[] case74() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("scaOutVertParam74.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case75() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case76() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("dsMapping76.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case77() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case78() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("dsOutVertParam78.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case79() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case80() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("WBOriginalDS80.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case81() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case82() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("WBCurrentDS82.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case83() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case84() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("pmMapping84.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case85() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case86() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaExportPdfVertparam86.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case87() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case88() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaExportPdfHorzparam88.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case89() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case90() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaExportPdfSummaryparam90.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case91() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case92() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaExportPdfSubparam92.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case93() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}


	public ApplicationInfo[] case94() {

		ApplicationInfo[] appInfo = new ApplicationInfo[2];
		
		appInfo[0] = loadAppInfoBasedonId("store.ser");
		appInfo[1] = loadAppInfoBasedonId("ncaExportPdfDosing94.ser");
		
		return appInfo;
	
	}


	public ApplicationInfo case95() {

		ApplicationInfo appInfo = new ApplicationInfo();
		
		appInfo = loadAppInfoBasedonId("store.ser");

		
		return appInfo;
	
	}



	
	
	
	
		
	
	
}
