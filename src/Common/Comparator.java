package Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import com.itextpdf.text.pdf.parser.Vector;

import view.DosingData;
import view.ExportToPdfInfo;
import view.InitialParameterValue;
import view.LambdaZDetails;
import view.MappIngData;
import view.ModelInputTab1;
import view.ModelInputTab2;
import view.ModelInputs;
import view.ModleInputForNPS;
import view.ParameterNamesData;

import view.ParameterUnitsData;
import view.PlotsOutputs;
import view.ProfileAndParamInfo;
import view.ProfileInformation;
import view.ProgressBarInfo;
import view.ProjectInfo;
import view.ResultDisplayTablesHeader;
import view.SpreadSheetOutputs;

import view.TableMavenInfo;
import view.Template12Results;
import view.UnitBuilderData;
import view.WorkBookInfo;
import view.WorkSheetsInfo;
import view.ColumnProperties;
import view.WorkSheetOutputs;
import view.ProcessingInputsInfo;
import view.lambdazData;

public abstract class Comparator {

	public boolean compare(ArrayList<ColumnProperties> columnPropertiesArrayList, ArrayList<ColumnProperties> columnPropertiesArrayList2){
		if(columnPropertiesArrayList == columnPropertiesArrayList2)
			return true;
		else
			return false;
	}
	public boolean compare(WorkBookInfo wb1, WorkBookInfo wb2){
		if(wb1==wb2)
			return true;
		else
			return false;
	}
	public boolean compare(WorkSheetsInfo ws1, WorkSheetsInfo ws2){
		if(ws1 == ws2)
			return true;
		else
			return false;
		
	}
	public boolean compare(ProcessingInputsInfo p1, ProcessingInputsInfo p2){
		if(p1 == p2)
			return true;
		else
			return false;
	}
	public boolean compare(WorkSheetOutputs wsout1, WorkSheetOutputs wsout2){
		if(wsout1 == wsout2)
			return true;
		else
			return false;
	}
	public boolean compare(ColumnProperties cp1, ColumnProperties cp2){
		if(cp1 == cp2)
			return true;
		else 
			return false;
	}
	public boolean compare(MappIngData mp1, MappIngData mp2){
		if(mp1 == mp2)
			return true;
		else
			return false;
	}
	public boolean compare(DosingData dd1, DosingData dd2){
		if(dd1 == dd2)
			return true;
		else
			return false;
	}
	
	public boolean compare(lambdazData lamdZdata1, lambdazData lambdaZdata2){
		if(lamdZdata1 == lambdaZdata2)
			return true;
		else
			return false;
	}
	
	public boolean compare(LambdaZDetails lzdetails1, LambdaZDetails lzdetails2){
		return (lzdetails1 == lzdetails2)?true:false;
	}
	public boolean compare(ParameterNamesData pn1, ParameterNamesData pn2){
		return (pn1 == pn2)?true:false;
	}
	
	public boolean compare(ParameterUnitsData pu1, ParameterUnitsData pu2){
		return (pu1 == pu2) ? true: false;
	}
	public boolean compare(ModelInputs mi1, ModelInputs mi2){
		return (mi1 == mi2) ? true : false;
	}
	public boolean comapre(ModleInputForNPS miNps1, ModleInputForNPS miNps2){
		return (miNps1 == miNps2) ? true : false;
	}
	public boolean compare(ProfileInformation pi1, ProfileInformation pi2){
		return (pi1 == pi2) ? true : false;
	}
	public boolean compare(TableMavenInfo tm1, TableMavenInfo t2){
		return (tm1 == t2) ? true : false;
	}
	public boolean comapre(ModelInputTab1 tb1, ModelInputTab1 tb2){
		return (tb1 == tb2)?true:false;
	}
	public boolean compare(ModelInputTab2 tb1, ModelInputTab2 tb2){
		return (tb1 == tb2)?true:false;
	}
	public boolean comapre(InitialParameterValue ipv1, InitialParameterValue ipv2){
		return (ipv1 == ipv2)?true:false;
	}
	public boolean comapre(ProfileAndParamInfo pp1, ProfileAndParamInfo pp2){
		return (pp1 == pp2)?true:false;
	}
	public boolean comapre(ResultDisplayTablesHeader rd1, ResultDisplayTablesHeader rd2){
		return (rd1 == rd2)?true:false;
	}
	public boolean compare(ProgressBarInfo pb1, ProgressBarInfo pb2){
		return (pb1 == pb2)?true:false;
	}
	public boolean compare(ExportToPdfInfo expdf1, ExportToPdfInfo expdf2){
		return (expdf1 == expdf2)?true:false;
	}
	public boolean compare(SpreadSheetOutputs spout1, SpreadSheetOutputs spout2){
		return (spout1 == spout2)?true:false;
	}
	public boolean compare(PlotsOutputs plout1, PlotsOutputs plout2){
		return (plout1 == plout2)?true:false;
	}
	public boolean compare(Template12Results tmp12a, Template12Results tmp12b){
		return (tmp12a == tmp12b)?true:false;
	}
	public boolean compare(UnitBuilderData ub1, UnitBuilderData ub2){
		return (ub1 == ub2)?true:false;
	}
	
	public boolean compare(java.util.Vector<String> v1, java.util.Vector<String> v2){
		return (v1==v2)?true:false;
	}
	public boolean compareIntBool(Hashtable<Integer, Boolean> inclusionExcusionPoints,
			Hashtable<Integer, Boolean> inclusionExcusionPoints2) {
		return  (inclusionExcusionPoints == inclusionExcusionPoints2)?true:false;
		
	}

	
	
	public boolean compareStrBool(Hashtable<String, Boolean> inclusionExcusionPoints,
			Hashtable<String, Boolean> inclusionExcusionPoints2){
		return  (inclusionExcusionPoints == inclusionExcusionPoints2)?true:false;
	}
	
	public boolean compareStrStr(HashMap<String, String> statusCodeHM,
			HashMap<String, String> statusCodeHM2) {
		
		return (statusCodeHM == statusCodeHM2)?true:false;
		
	}
	
	public boolean compareStrHash(Hashtable<String, Hashtable<String, Double>> hash1, Hashtable<String, Hashtable<String, Double>> hash2){
		return(hash1 == hash2)?true:false;
	}
	public boolean compareWorkSheetInfo(ArrayList<WorkSheetsInfo> al1,
			ArrayList<WorkSheetsInfo> al2) {
		return (al1 == al2)?true:false;
		
		
	}
	
	public boolean compareProject(ArrayList<ProjectInfo>al1, ArrayList<ProjectInfo>al2){
		return (al1 == al2)?true:false;
	}
	
	
	public boolean compareWorkBooks(ArrayList<WorkBookInfo>al1, ArrayList<WorkBookInfo>al2){
		return (al1 == al2)?true:false;
	}
	
	public boolean compareasciiPrimaryPar(ArrayList<String> al1,
			ArrayList<String> al2) {
		return (al1 == al2)?true:false;
		
		
	}

	public boolean compareStrDouble(HashMap<String, Double> hash1,
			HashMap<String, Double> hash2) {
		return (hash1 == hash2)?true:false;
		
	}

}
