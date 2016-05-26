package Test;

import java.util.ArrayList;
import java.util.Arrays;

import Common.MyComparator;

import Common.Comparator;

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

public class NcaRepositoryTest extends TestCase {

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
	
	public void testCase1() {
		ApplicationInfo[] appInfo =ncaRep.case1();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getMappingDataObj().equals(nca2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = true;
		}
	}
	
	 public void testncaMapCase1(){
		
		ApplicationInfo[] appInfo = ncaRep.case1();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getMappingDataObj().equals(nca2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
	}
	
	public void testncaDoseCase2(){
		ApplicationInfo[] appInfo = ncaRep.case2();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getDosingDataObj().equals(nca2.getProcessingInputs().getDosingDataObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaLambdaZCase3(){
		ApplicationInfo[] appInfo = ncaRep.case3();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getLambdazDataobj().equals(nca2.getProcessingInputs().getLambdazDataobj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaParameterUnitsCase4(){
		ApplicationInfo[] appInfo = ncaRep.case4();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getParameterUnitsDataObj().equals(nca2.getProcessingInputs().getParameterUnitsDataObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	
	public void testncaParameterNamesCase5(){
		ApplicationInfo[] appInfo = ncaRep.case5();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getParameterNamesObj().equals(nca2.getProcessingInputs().getParameterNamesObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaModelOptionsCase6(){
		ApplicationInfo[] appInfo = ncaRep.case6();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getModelInputsObj().equals(nca2.getProcessingInputs().getModelInputsObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaExportToPdfCase7(){
		ApplicationInfo[] appInfo = ncaRep.case7();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getExpToPdf().equals(nca2.getProcessingInputs().getExpToPdf())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	
	public void testncaDataCase8(){
		ApplicationInfo[] appInfo = ncaRep.case8();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(nca1.getProcessingInputs().getNcaDat(), nca2.getProcessingInputs().getNcaDat())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaProfileParamCase9(){
		ApplicationInfo[] appInfo = ncaRep.case9();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getProfileAndParamInfoObj().equals(nca2.getProcessingInputs().getProfileAndParamInfoObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaSubAreasCase10(){
		ApplicationInfo[] appInfo = ncaRep.case10();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getSubAreasDataObj().equals(nca2.getProcessingInputs().getSubAreasDataObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaProfileCase11(){
		ApplicationInfo[] appInfo = ncaRep.case11();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getProfileInfoObj().equals(nca2.getProcessingInputs().getProfileInfoObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	

	
	public void testncaColumnPropertiesCase12(){
		ApplicationInfo[] appInfo = ncaRep.case12();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(comp.compare(nca1.getColumnPropertiesArrayList(), nca2.getColumnPropertiesArrayList())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}

	public void testncaWSOutputCase13(){
		ApplicationInfo[] appInfo = ncaRep.case13();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(nca1.getWorkSheetOutputs().equals(nca2.getWorkSheetOutputs())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	
	public void testncaMapCase14(){
		
		ApplicationInfo appInfo = ncaRep.case14();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		
		boolean isMapDataEqual = true;
		if(nca1.getProcessingInputs().getMappingDataObj().equals(nca2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
	}
	
	public void testncaDoseCase15(){
		ApplicationInfo appInfo = ncaRep.case15();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		
		boolean isMapDataEqual = true;
		if(nca1.getProcessingInputs().getDosingDataObj().equals(nca2.getProcessingInputs().getDosingDataObj())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaLambdaZCase16(){
		ApplicationInfo appInfo = ncaRep.case16();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		
		boolean isMapDataEqual = true;
		if(nca1.getProcessingInputs().getLambdazDataobj().equals(nca2.getProcessingInputs().getLambdazDataobj())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaParameterUnitsCase17(){
		ApplicationInfo appInfo = ncaRep.case17();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		
		boolean isMapDataEqual = true;
		if(nca1.getProcessingInputs().getParameterUnitsDataObj().equals(nca2.getProcessingInputs().getParameterUnitsDataObj())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	
	public void testncaParameterNamesCase18(){
		ApplicationInfo appInfo = ncaRep.case18();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getParameterNamesObj().equals(nca2.getProcessingInputs().getParameterNamesObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaModelOptionsCase19(){
		ApplicationInfo appInfo = ncaRep.case19();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo() ;
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getModelInputsObj().equals(nca2.getProcessingInputs().getModelInputsObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaExportToPdfCase20(){
		ApplicationInfo appInfo = ncaRep.case20();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		
		boolean isMapDataEqual = true;
		if(nca1.getProcessingInputs().getExpToPdf().equals(nca2.getProcessingInputs().getExpToPdf())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	
	public void testncaDataCase21(){
		ApplicationInfo appInfo = ncaRep.case21();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(nca1.getProcessingInputs().getNcaDat(), nca2.getProcessingInputs().getNcaDat())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaProfileParamCase22(){
		ApplicationInfo appInfo = ncaRep.case22();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		
		boolean isMapDataEqual = false;
		if(nca1.getProcessingInputs().getProfileAndParamInfoObj().equals(nca2.getProcessingInputs().getProfileAndParamInfoObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaSubAreasCase23(){
		ApplicationInfo appInfo = ncaRep.case23();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		
		boolean isMapDataEqual = true;
		if(nca1.getProcessingInputs().getSubAreasDataObj().equals(nca2.getProcessingInputs().getSubAreasDataObj())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testncaProfileCase24(){
		ApplicationInfo appInfo = ncaRep.case24();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		
		boolean isMapDataEqual = true;
		if(nca1.getProcessingInputs().getProfileInfoObj().equals(nca2.getProcessingInputs().getProfileInfoObj())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	
	
	public void testncaColumnPropertiesCase25(){
		ApplicationInfo appInfo = ncaRep.case25();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		
		boolean isMapDataEqual = true;
		if(comp.compare(nca1.getColumnPropertiesArrayList(), nca2.getColumnPropertiesArrayList())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}

	public void testncaWSOutputCase26(){
		ApplicationInfo appInfo = ncaRep.case26();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		
		boolean isMapDataEqual = true;
		if(nca1.getWorkSheetOutputs().equals(nca2.getWorkSheetOutputs())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testnpsMappingCase27(){
		ApplicationInfo[] appInfo = ncaRep.case27();
		
		NPSInfo nps1 = getNPSInfo(appInfo[0]);
		NPSInfo nps2 = getNPSInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(nps1.getProcessingInputs().getMappingDataObj().equals(nps2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testnpsDosingCase28(){
		ApplicationInfo[] appInfo = ncaRep.case28();
		
		NPSInfo nps1 = getNPSInfo(appInfo[0]);
		NPSInfo nps2 = getNPSInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(nps1.getProcessingInputs().getDosingDataObj().equals(nps2.getProcessingInputs().getDosingDataObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testnpsModelOptionsCase29(){
		ApplicationInfo[] appInfo = ncaRep.case29();
		
		NPSInfo nps1 = getNPSInfo(appInfo[0]);
		NPSInfo nps2 = getNPSInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(nps1.getProcessingInputs().getModleInputForNPSObj().equals(nps2.getProcessingInputs().getModleInputForNPSObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testnpsExportToPdfCase30(){
		ApplicationInfo[] appInfo = ncaRep.case30();
		
		NPSInfo nps1 = getNPSInfo(appInfo[0]);
		NPSInfo nps2 = getNPSInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(nps1.getProcessingInputs().getExpToPdf().equals(nps2.getProcessingInputs().getExpToPdf())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testnpsOutputParamValuesCase31(){
		ApplicationInfo[] appInfo = ncaRep.case31();
		
		NPSInfo nps1 = getNPSInfo(appInfo[0]);
		NPSInfo nps2 = getNPSInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(convert(nps1.getWorkSheetOutputs().getParameterValuesAL()), convert(nps2.getWorkSheetOutputs().getParameterValuesAL()))){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	String[] convert(ArrayList<String> parValues){
		String[] par = new String[parValues.size()];
		for(int i=0;i<parValues.size();i++){
			par[i] = parValues.get(i);
		}
		return par;
		
	}
	
	
	public void testnpsOutputParamValuesCase32(){
		ApplicationInfo[] appInfo = ncaRep.case32();
		
		NPSInfo nps1 = getNPSInfo(appInfo[0]);
		NPSInfo nps2 = getNPSInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(nps1.getWorkSheetOutputs().equals(nps2.getWorkSheetOutputs())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testnpsColumnPropCase33(){
		ApplicationInfo[] appInfo = ncaRep.case33();
		
		NPSInfo nps1 = getNPSInfo(appInfo[0]);
		NPSInfo nps2 = getNPSInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(comp.compare(nps1.getColumnPropertiesArrayList(), nps2.getColumnPropertiesArrayList())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}

	public void testnpsVertParamCase34(){
		ApplicationInfo[] appInfo = ncaRep.case34();
		
		NPSInfo nps1 = getNPSInfo(appInfo[0]);
		NPSInfo nps2 = getNPSInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(nps1.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters(), nps2.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testisNpsMappingDefaultCase35(){
		ApplicationInfo appInfo = ncaRep.case35();
		
		NPSInfo nps1 = getNPSInfo(appInfo);
		NPSInfo nps2 = new NPSInfo();
		boolean isMapDataEqual = true;
		if(nps1.getProcessingInputs().getMappingDataObj().equals(nps2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testisNpsDosingDefaultCase36(){
		ApplicationInfo appInfo = ncaRep.case36();
		
		NPSInfo nps1 = getNPSInfo(appInfo);
		NPSInfo nps2 = new NPSInfo();
		boolean isMapDataEqual = false;
		if(nps1.getProcessingInputs().getDosingDataObj().equals(nps2.getProcessingInputs().getDosingDataObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testisNpsModelOptionsCase37(){
		ApplicationInfo appInfo = ncaRep.case37();
		
		NPSInfo nps1 = getNPSInfo(appInfo);
		NPSInfo nps2 = new NPSInfo();
		boolean isMapDataEqual = true;
		if(nps1.getProcessingInputs().getModleInputForNPSObj().equals(nps2.getProcessingInputs().getModleInputForNPSObj())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testisNpsExportToPdfDefaultCase38(){
		ApplicationInfo appInfo = ncaRep.case38();
		
		NPSInfo nps1 = getNPSInfo(appInfo);
		NPSInfo nps2 = new NPSInfo();
		boolean isMapDataEqual = true;
		if(nps1.getProcessingInputs().getExpToPdf().equals(nps2.getProcessingInputs().getExpToPdf())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	
	
	
	
	public void testisNpsColumnPropDefaultCase39(){
		ApplicationInfo appInfo = ncaRep.case39();
		
		NPSInfo nps1 = getNPSInfo(appInfo);
		NPSInfo nps2 = new NPSInfo();
		boolean isMapDataEqual = true;
		if(comp.compare(nps1.getColumnPropertiesArrayList(), nps2.getColumnPropertiesArrayList())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}

	public void testisNpsVertParamCase40(){
		ApplicationInfo appInfo = ncaRep.case40();
		
		NPSInfo nps1 = getNPSInfo(appInfo);
		NPSInfo nps2 = new NPSInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(nps1.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters(), nps2.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testttMappingCase41(){
		ApplicationInfo[] appInfo = ncaRep.case41();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo[0]);
		TableTransformationsInfo tt2 = getTTInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(tt1.getProcessingInputs().getMappingDataObj().equals(tt2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}

	public void testttCustomPointCase42(){
		ApplicationInfo[] appInfo = ncaRep.case42();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo[0]);
		TableTransformationsInfo tt2 = getTTInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(tt1.getProcessingInputs().getTtCostomPoint()==(tt2.getProcessingInputs().getTtCostomPoint())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testttNValueCase43(){
		ApplicationInfo[] appInfo = ncaRep.case43();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo[0]);
		TableTransformationsInfo tt2 = getTTInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(tt1.getProcessingInputs().getTtnValue()==(tt2.getProcessingInputs().getTtnValue())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testttBaseFuncCase44(){
		ApplicationInfo[] appInfo = ncaRep.case44();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo[0]);
		TableTransformationsInfo tt2 = getTTInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(tt1.getProcessingInputs().getTtSelectedBaseFuncIndex()==(tt2.getProcessingInputs().getTtSelectedBaseFuncIndex())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}

	public void testttFuncCase45(){
		ApplicationInfo[] appInfo = ncaRep.case45();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo[0]);
		TableTransformationsInfo tt2 = getTTInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(tt1.getProcessingInputs().getTtSelectedFuncIndex()==(tt2.getProcessingInputs().getTtSelectedFuncIndex())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testttFuncCase46(){
		ApplicationInfo[] appInfo = ncaRep.case46();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo[0]);
		TableTransformationsInfo tt2 = getTTInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(tt1.getProcessingInputs().getTtSlectedArithmeticFuncIndex()==(tt2.getProcessingInputs().getTtSlectedArithmeticFuncIndex())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testttFuncTypeCase47(){
		ApplicationInfo[] appInfo = ncaRep.case47();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo[0]);
		TableTransformationsInfo tt2 = getTTInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(tt1.getProcessingInputs().getTtTypeOfFunc()==(tt2.getProcessingInputs().getTtTypeOfFunc())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testttDataCase48(){
		ApplicationInfo[] appInfo = ncaRep.case48();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo[0]);
		TableTransformationsInfo tt2 = getTTInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(tt1.getProcessingInputs().getTtData(), tt2.getProcessingInputs().getTtData())){
			isMapDataEqual = true;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testisTTDataDefaultCase49(){
		ApplicationInfo appInfo = ncaRep.case49();
		TableTransformationsInfo tt1 = getTTInfo(appInfo);
		TableTransformationsInfo tt2 = new TableTransformationsInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(tt1.getProcessingInputs().getTtData(), tt2.getProcessingInputs().getTtData())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
	}

	public void testttOutVertParamCase50(){
		ApplicationInfo[] appInfo = ncaRep.case50();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo[0]);
		TableTransformationsInfo tt2 = getTTInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(tt1.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters(), tt2.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testttOutTransColumnCase51(){
		ApplicationInfo[] appInfo = ncaRep.case51();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo[0]);
		TableTransformationsInfo tt2 = getTTInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(tt1.getWorkSheetOutputs().getTtTransformedColumn(), tt2.getWorkSheetOutputs().getTtTransformedColumn())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisTtTransColumnCase52(){
		ApplicationInfo appInfo = ncaRep.case52();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo);
		TableTransformationsInfo tt2 = new TableTransformationsInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(tt1.getWorkSheetOutputs().getTtTransformedColumn(), tt2.getWorkSheetOutputs().getTtTransformedColumn())){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	
	
	
	public void testttOutTextCase53(){
		ApplicationInfo[] appInfo = ncaRep.case53();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo[0]);
		TableTransformationsInfo tt2 = getTTInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(convert(tt1.getWorkSheetOutputs().getTextoutput()), convert(tt2.getWorkSheetOutputs().getTextoutput()))){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisTtOutVertParamDefaultCase54(){
		ApplicationInfo appInfo = ncaRep.case54();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo);
		TableTransformationsInfo tt2 = new TableTransformationsInfo();
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(tt1.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters(), tt2.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisTtMappingDefaultCase55(){
		ApplicationInfo appInfo = ncaRep.case55();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo);
		TableTransformationsInfo tt2 = new TableTransformationsInfo();
		boolean isMapDataEqual = true;
		if(tt1.getProcessingInputs().getMappingDataObj().equals(tt2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = false;
		}
		
		assertTrue(isMapDataEqual);
		
	}
	
	public void testttColumnPropCase56(){
		ApplicationInfo[] appInfo = ncaRep.case56();
		
		TableTransformationsInfo tt1 = getTTInfo(appInfo[0]);
		TableTransformationsInfo tt2 = getTTInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(comp.compare(tt1.getColumnPropertiesArrayList(), tt2.getColumnPropertiesArrayList())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testbqlColumnPropCase57(){
		ApplicationInfo[] appInfo = ncaRep.case57();
		
		BQLInfo bql1 = getBqlInfo(appInfo[0]);
		BQLInfo bql2 = getBqlInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(comp.compare(bql1.getColumnPropertiesArrayList(), bql2.getColumnPropertiesArrayList())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testbqlMappingCase58(){
		ApplicationInfo[] appInfo = ncaRep.case58();
		
		BQLInfo bql1 = getBqlInfo(appInfo[0]);
		BQLInfo bql2 = getBqlInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(bql1.getProcessingInputs().getMappingDataObj().equals(bql2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisBqlMappingDefaultCase60(){
		ApplicationInfo appInfo = ncaRep.case60();
		
		BQLInfo bql1 = getBqlInfo(appInfo);
		BQLInfo bql2 = new BQLInfo();
		boolean isMapDataEqual = true;
		if(bql1.getProcessingInputs().getMappingDataObj().equals(bql2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testbqlRulesCase59(){
		ApplicationInfo[] appInfo = ncaRep.case59();
		
		BQLInfo bql1 = getBqlInfo(appInfo[0]);
		BQLInfo bql2 = getBqlInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(bql1.getProcessingInputs().getDosingDataObj().getBqlRulesTable(), bql2.getProcessingInputs().getDosingDataObj().getBqlRulesTable())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisBqlRulesDefaultCase61(){
		ApplicationInfo appInfo = ncaRep.case61();
		
		BQLInfo bql1 = getBqlInfo(appInfo);
		BQLInfo bql2 = new BQLInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(bql1.getProcessingInputs().getDosingDataObj().getBqlRulesTable(), bql2.getProcessingInputs().getDosingDataObj().getBqlRulesTable())){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testbqlSortedColumnsCase62(){
		ApplicationInfo[] appInfo = ncaRep.case62();
		
		BQLInfo bql1 = getBqlInfo(appInfo[0]);
		BQLInfo bql2 = getBqlInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(convert(bql1.getProcessingInputs().getBqlSortedColumnNames()), convert(bql2.getProcessingInputs().getBqlSortedColumnNames()))){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisBqlSortedColumnsCase63(){
		ApplicationInfo appInfo = ncaRep.case63();
		
		BQLInfo bql1 = getBqlInfo(appInfo);
		BQLInfo bql2 = new BQLInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(convert(bql1.getProcessingInputs().getBqlSortedColumnNames()), convert(bql2.getProcessingInputs().getBqlSortedColumnNames()))){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testbqlDataCase64(){
		ApplicationInfo[] appInfo = ncaRep.case64();
		
		BQLInfo bql1 = getBqlInfo(appInfo[0]);
		BQLInfo bql2 = getBqlInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals((bql1.getProcessingInputs().getBqlDat()), (bql2.getProcessingInputs().getBqlDat()))){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisBqlDataDefaultCase65(){
		ApplicationInfo appInfo = ncaRep.case65();
		
		BQLInfo bql1 = getBqlInfo(appInfo);
		BQLInfo bql2 = new BQLInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals((bql1.getProcessingInputs().getBqlDat()), (bql2.getProcessingInputs().getBqlDat()))){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testbqlOutConcCase66(){
		ApplicationInfo[] appInfo = ncaRep.case66();
		
		BQLInfo bql1 = getBqlInfo(appInfo[0]);
		BQLInfo bql2 = getBqlInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(convert(bql1.getWorkSheetOutputs().getBqlOriginalConcCopy()), convert(bql2.getWorkSheetOutputs().getBqlOriginalConcCopy()))){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}

	private Object[] convert(double[] bqlOriginalConcCopy) {
		
		String[] array = new String[bqlOriginalConcCopy.length];
		for(int i=0;i<array.length;i++){
			array[i] = bqlOriginalConcCopy[i]+"";
		}
		return array;
	}
	
	public void testisBqlOutConcDefaultCase67(){
		ApplicationInfo appInfo = ncaRep.case67();
		
		BQLInfo bql1 = getBqlInfo(appInfo);
		BQLInfo bql2 = new BQLInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(convert(bql1.getWorkSheetOutputs().getBqlOriginalConcCopy()), convert(bql2.getWorkSheetOutputs().getBqlOriginalConcCopy()))){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testbqlOutConcCase68(){
		ApplicationInfo[] appInfo = ncaRep.case68();
		
		BQLInfo bql1 = getBqlInfo(appInfo[0]);
		BQLInfo bql2 = getBqlInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(bql1.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters(), bql2.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}

	public void testisBqlOutConcCase69(){
		ApplicationInfo appInfo = ncaRep.case69();
		
		BQLInfo bql1 = getBqlInfo(appInfo);
		BQLInfo bql2 = new BQLInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(bql1.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters(), bql2.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters())){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testscaColumnPropCase70(){
		ApplicationInfo[] appInfo = ncaRep.case70();
		ScaInfo sca1 = getSCAInfo(appInfo[0]);
		ScaInfo sca2 = getSCAInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(comp.compare(sca1.getColumnPropertiesArrayList(),sca2.getColumnPropertiesArrayList())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testscaMappingCase71(){
		ApplicationInfo[] appInfo = ncaRep.case71();
		ScaInfo sca1 = getSCAInfo(appInfo[0]);
		ScaInfo sca2 = getSCAInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(sca1.getProcessingInputs().getMappingDataObj().equals(sca2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisScaMappingDefaultCase72(){
		ApplicationInfo appInfo = ncaRep.case72();
		ScaInfo sca1 = getSCAInfo(appInfo);
		ScaInfo sca2 = new ScaInfo();
		boolean isMapDataEqual = true;
		if(sca1.getProcessingInputs().getMappingDataObj().equals(sca2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testscaModelOptionsCase73(){
		ApplicationInfo[] appInfo = ncaRep.case73();
		ScaInfo sca1 = getSCAInfo(appInfo[0]);
		ScaInfo sca2 = getSCAInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(sca1.getProcessingInputs().getModelInputsObj().equals(sca2.getProcessingInputs().getModelInputsObj())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testscaOutVertParamCase74(){
		ApplicationInfo[] appInfo = ncaRep.case74();
		ScaInfo sca1 = getSCAInfo(appInfo[0]);
		ScaInfo sca2 = getSCAInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(sca1.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters(), sca2.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisScaOutVertParamDefaultCase75(){
		ApplicationInfo appInfo = ncaRep.case75();
		ScaInfo sca1 = getSCAInfo(appInfo);
		ScaInfo sca2 =new ScaInfo();
		boolean isMapDataEqual = true;
		if(sca1.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters().equals(sca2.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters())){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testdsMappingCase76(){
		ApplicationInfo[] appInfo = ncaRep.case76();
		DstatsInfo ds1 = getDSInfo(appInfo[0]);
		DstatsInfo ds2 = getDSInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(ds1.getProcessingInputs().getMappingDataObj().equals(ds2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisDsMappingDefaultCase77(){
		ApplicationInfo appInfo = ncaRep.case77();
		DstatsInfo ds1 = getDSInfo(appInfo);
		DstatsInfo ds2 = new DstatsInfo();
		
		boolean isMapDataEqual = true;
		if(ds1.getProcessingInputs().getMappingDataObj().equals(ds2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testdsOutVertParamCase78(){
		ApplicationInfo[] appInfo = ncaRep.case78();
		DstatsInfo ds1 = getDSInfo(appInfo[0]);
		DstatsInfo ds2 = getDSInfo(appInfo[1]);
		
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(ds1.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters(), ds2.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisDsOutVertParamDefaultCase79(){
		ApplicationInfo appInfo = ncaRep.case79();
		DstatsInfo ds1 = getDSInfo(appInfo);
		DstatsInfo ds2 = new DstatsInfo();
		
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(ds1.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters(), ds2.getWorkSheetOutputs().getSpreadSheetOutputs().getVerticalParameters())){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	

	private String[][][] convertToArray(
			ArrayList<String[][]> copyOfOriginalDataStructures) {

		String[][][] array = new String[copyOfOriginalDataStructures.size()][][];
		for (int i = 0; i < array.length; i++) {
			array[i] = copyOfOriginalDataStructures.get(i);
		}
		return array;
	}
	
/*	public void testwbOriginalDSCase80(){
		ApplicationInfo[] appInfo = ncaRep.case80();
		
		WorkBookInfo wb1 = getWBInfo(appInfo[0]);
		WorkBookInfo wb2 = getWBInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(convertToArray(wb1.getCopyOfOriginalDataStructures()), convertToArray(wb2.getCopyOfOriginalDataStructures()))){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}

	*/
	
	
	/*public void testisOriginalDSDefaultCase81(){
		ApplicationInfo appInfo = ncaRep.case81();
		
		WorkBookInfo wb1 = getWBInfo(appInfo);
		WorkBookInfo wb2 = new WorkBookInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(convertToArray(wb1.getCopyOfOriginalDataStructures()), convertToArray(wb2.getCopyOfOriginalDataStructures()))){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}*/
	

	private WorkBookInfo getWBInfo(ApplicationInfo appInfo) {
	
		
		return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex());
	}
	
	public void testwbCurrentDSCase82(){
		ApplicationInfo[] appInfo = ncaRep.case82();
		
		WorkBookInfo wb1 = getWBInfo(appInfo[0]);
		WorkBookInfo wb2 = getWBInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(convertToArray(wb1.getDataStructuresArrayList()), convertToArray(wb2.getDataStructuresArrayList()))){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisCurrentDSDefaultCase83(){
		ApplicationInfo appInfo = ncaRep.case83();
		
		WorkBookInfo wb1 = getWBInfo(appInfo);
		WorkBookInfo wb2 = new WorkBookInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(convertToArray(wb1.getDataStructuresArrayList()), convertToArray(wb2.getDataStructuresArrayList()))){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}

	public void testpmMappingCase84(){
		ApplicationInfo[] appInfo = ncaRep.case84();
		
		PlotmavenInfo pm1 = getPMInfo(appInfo[0]);
		PlotmavenInfo pm2 = getPMInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(pm1.getProcessingInputs().getMappingDataObj().equals(pm2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisPmMappingDefaultCase85(){
		ApplicationInfo appInfo = ncaRep.case85();
		
		PlotmavenInfo pm1 = getPMInfo(appInfo);
		PlotmavenInfo pm2 = new PlotmavenInfo();
		boolean isMapDataEqual = false;
		if(pm1.getProcessingInputs().getMappingDataObj().equals(pm2.getProcessingInputs().getMappingDataObj())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testncaExportToPdfVertParamCase86(){
		ApplicationInfo[] appInfo = ncaRep.case86();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(nca1.getProcessingInputs().getExpToPdf().getVerticalParameters(), nca2.getProcessingInputs().getExpToPdf().getVerticalParameters())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisNcaExportToPdfVertParamDefaultCase87(){
		ApplicationInfo appInfo = ncaRep.case87();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(nca1.getProcessingInputs().getExpToPdf().getVerticalParameters(), nca2.getProcessingInputs().getExpToPdf().getVerticalParameters())){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testncaExportToPdfHorzParamCase88(){
		ApplicationInfo[] appInfo = ncaRep.case88();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(nca1.getProcessingInputs().getExpToPdf().getHorzParameters(), nca2.getProcessingInputs().getExpToPdf().getHorzParameters())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisNcaExportToPdfHorzParamCase89(){
		ApplicationInfo appInfo = ncaRep.case89();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(nca1.getProcessingInputs().getExpToPdf().getHorzParameters(), nca2.getProcessingInputs().getExpToPdf().getHorzParameters())){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testncaExportToPdfSummaryParamCase90(){
		ApplicationInfo[] appInfo = ncaRep.case90();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(nca1.getProcessingInputs().getExpToPdf().getSummaryTable(), nca2.getProcessingInputs().getExpToPdf().getSummaryTable())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisncaExportToPdfSummaryDefaultParamCase91(){
		ApplicationInfo appInfo = ncaRep.case91();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(nca1.getProcessingInputs().getExpToPdf().getSummaryTable(), nca2.getProcessingInputs().getExpToPdf().getSummaryTable())){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testncaExportToPdfSubAreasParamCase92(){
		ApplicationInfo[] appInfo = ncaRep.case92();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(nca1.getProcessingInputs().getExpToPdf().getSubAreasTable(), nca2.getProcessingInputs().getExpToPdf().getSubAreasTable())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisncaExportToPdfSubAreasParamDefaultCase93(){
		ApplicationInfo appInfo = ncaRep.case93();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(nca1.getProcessingInputs().getExpToPdf().getSubAreasTable(), nca2.getProcessingInputs().getExpToPdf().getSubAreasTable())){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testncaExportToPdfDosingParamCase94(){
		ApplicationInfo[] appInfo = ncaRep.case94();
		
		NcaInfo nca1 = getNcaInfo(appInfo[0]);
		NcaInfo nca2 = getNcaInfo(appInfo[1]);
		boolean isMapDataEqual = false;
		if(Arrays.deepEquals(nca1.getProcessingInputs().getExpToPdf().getDosingTable(), nca2.getProcessingInputs().getExpToPdf().getDosingTable())){
			isMapDataEqual = true;
		}
		assertTrue(isMapDataEqual);
	}
	
	public void testisNcaExportToPdfDosingDefaultCase95(){
		ApplicationInfo appInfo = ncaRep.case95();
		
		NcaInfo nca1 = getNcaInfo(appInfo);
		NcaInfo nca2 = new NcaInfo();
		boolean isMapDataEqual = true;
		if(Arrays.deepEquals(nca1.getProcessingInputs().getExpToPdf().getDosingTable(), nca2.getProcessingInputs().getExpToPdf().getDosingTable())){
			isMapDataEqual = false;
		}
		assertTrue(isMapDataEqual);
	}
	
	
	

	


}
