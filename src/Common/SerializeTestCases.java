package Common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import view.ApplicationInfo;
import view.DisplayContents;

public class SerializeTestCases {

	public static SerializeTestCases SER_TEST_CASES = null;
	
	public static SerializeTestCases createSerTestCasesInst() {
		if(SER_TEST_CASES == null){
			SER_TEST_CASES = new SerializeTestCases("Just object creation");
		}
		return SER_TEST_CASES;
	}

	
	public SerializeTestCases(String string) {
		// TODO Auto-generated constructor stub
	}

	
	void storeTestCase(String fileName, ApplicationInfo appInfo){

		try {
			
			FileOutputStream fos = new FileOutputStream("TestCase\\"+fileName); 
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(appInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	public void serializeAnalysis() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		storeTestCase("npsMappingCase27.ser", appInfo);
		storeTestCase("npsDosingCase28.ser", appInfo);
		storeTestCase("npsModelOptiosCase29.ser", appInfo);
		storeTestCase("npsExportToPdfCase30.ser", appInfo);
		storeTestCase("npsOutParamValuesCase31.ser", appInfo);
		storeTestCase("npsWSOutputCase32.ser", appInfo);
		storeTestCase("npsColumnPropCase33.ser", appInfo);
		storeTestCase("npsVertParamCase34.ser", appInfo);
		storeTestCase("ttMappingCase41.ser", appInfo);
		storeTestCase("ttCustomPointCase42.ser", appInfo);
		storeTestCase("ttNValue43.ser", appInfo);
		storeTestCase("ttBaseFunc44.ser", appInfo);
		storeTestCase("ttFunc45.ser", appInfo);
		storeTestCase("ttArithmeticFunc46.ser", appInfo);
		storeTestCase("ttFuncType47.ser", appInfo);
		storeTestCase("ttData48.ser", appInfo);
		storeTestCase("ttOutputVertParam50.ser", appInfo);
		storeTestCase("ttOutputTransColumn51.ser", appInfo);
		storeTestCase("ttOutputText53.ser", appInfo);
		storeTestCase("ttColumnProp56.ser", appInfo);
		storeTestCase("bqlColumnProp57.ser", appInfo);
		storeTestCase("bqlMapping58.ser", appInfo);
		storeTestCase("bqlRules59.ser", appInfo);
		storeTestCase("bqlSortedColumns62.ser", appInfo);
		storeTestCase("bqlData64.ser", appInfo);
		storeTestCase("bqlOutConc66.ser", appInfo);
		storeTestCase("bqlOutVertParam68.ser", appInfo);
		storeTestCase("scaColumnProp70.ser", appInfo);
		storeTestCase("scaMapping71.ser", appInfo);
		storeTestCase("scaModelOptions73.ser", appInfo);
		storeTestCase("scaOutVertParam74.ser", appInfo);
		storeTestCase("dsMapping76.ser", appInfo);
		storeTestCase("dsOutVertParam78.ser", appInfo);
		storeTestCase("WBOriginalDS80.ser", appInfo);
		storeTestCase("WBCurrentDS82.ser", appInfo);
		storeTestCase("pmMapping84.ser", appInfo);
		
		
	}
	public void serializeNcaObjects(){
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		storeTestCase("ncaMapCase1.ser",appInfo);
		storeTestCase("ncaDoseCase2.ser",appInfo);
		storeTestCase("ncalambdazCase3.ser",appInfo);
		storeTestCase("ncaParamUnitsCase4.ser",appInfo);
		storeTestCase("ncaParamNamesCase5.ser",appInfo);
		storeTestCase("ncaModelOptionsCase6.ser",appInfo);
		storeTestCase("ncaExportToPdfCase7.ser", appInfo);
		storeTestCase("ncaDataCase8.ser", appInfo);
		storeTestCase("ncaProfParamCase9.ser", appInfo);
		storeTestCase("ncaSubAreasCase10.ser", appInfo);
		storeTestCase("ncaProfileCase11.ser", appInfo);
		storeTestCase("ncaColumnPropCase12.ser", appInfo);
		storeTestCase("ncaWSoutputCase13.ser", appInfo);
		storeTestCase("ncaExportPdfVertparam86.ser", appInfo);
		storeTestCase("ncaExportPdfHorzparam88.ser", appInfo);
		storeTestCase("ncaExportPdfSummaryparam90.ser", appInfo);
		storeTestCase("ncaExportPdfSubparam92.ser", appInfo);
		storeTestCase("ncaExportPdfDosing94.ser", appInfo);
	}
	
	
}
