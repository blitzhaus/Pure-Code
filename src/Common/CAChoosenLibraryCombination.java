package Common;

import org.apache.poi.hssf.record.formula.atp.AnalysisToolPak;

import view.ApplicationInfo;
import view.DisplayContents;
import view.ProcessingInputsInfo;

public class CAChoosenLibraryCombination {

	public CAChoosenLibraryCombination(){
		
	}
	
	public String getCombination(String analysisType) {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if(getIsAlgebraicModel(analysisType)){
			return getAlgebraicCombName(analysisType);
		} else {
			return getDiffEqnCombName(analysisType);
		}
		
		
	}

	public boolean getIsAlgebraicModel(String analysisType) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (analysisType.equals("pk")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
							.getProcessingInputs().getModelInputTab1Obj().getAlgebraicModel();
		} else if (analysisType.equals("pd")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPdInfo()
							.getProcessingInputs().getModelInputTab1Obj().getAlgebraicModel();
		} else if (analysisType.equals("mm")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getMmInfo()
							.getProcessingInputs().getModelInputTab1Obj().getAlgebraicModel();
		} else if (analysisType.equals("pkpdlink")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkpdLinkInfo()
							.getProcessingInputs().getModelInputTab1Obj().getAlgebraicModel();
		} else if (analysisType.equals("irm")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getIrInfo()
							.getProcessingInputs().getModelInputTab1Obj().getAlgebraicModel();
		}
		 
		return false;
	}

	private String getDiffEqnCombName(String analysisType) {

		int number = getLibraryNumber(analysisType);
		String combination = null;
		switch(number){
		case 1: combination = "compartment 1_plasma_iv bolus";
				break;
		case 2: combination = "compartment 1_plasma_iv bolus";
				break;
		case 3: combination = "compartment 1_plasma_extravascular";
				break;
		case 4: combination = "compartment 1_plasma_extravascular";
				break;
		case 5: combination = "compartment 1_plasma_extravascular";
				break;
		case 6: combination = "compartment 1_plasma_extravascular";
				break;
		case 7: combination = "compartment 1_plasma_iv infusion";
				break;
		case 8: combination = "compartment 1_plasma_iv infusion";
				break;
		case 9: combination = "compartment 2_plasma_iv bolus";
				break;
		case 10: combination = "compartment 2_plasma_iv bolus";
				break;
		case 11: combination = "compartment 2_plasma_extravascular";
				break;
		case 12: combination = "compartment 2_plasma_extravascular";
				break;
		case 13: combination = "compartment 2_plasma_extravascular";
				break;
		case 14: combination = "compartment 2_plasma_extravascular";
				break;
		case 15: combination = "compartment 2_plasma_iv infusion";
				break;
		case 16: combination = "compartment 2_plasma_iv infusion";
				break;
		case 17: combination = "compartment 3_plasma_iv bolus";
				break;
		case 18: combination = "compartment 3_plasma_iv bolus";
				break;
		case 19: combination = "compartment 3_plasma_iv infusion";
				break;
		case 20: combination = "compartment 3_plasma_iv infusion";
				break;
		case 21: combination = "compartment 1_plasma_iv bolus";
				break;
		case 22: combination = "compartment 1_plasma_iv bolus";
				break;
		case 23: combination = "compartment 2_plasma_iv bolus";
				break;
		case 24: combination = "compartment 2_plasma_iv bolus";
				break;
		case 25: combination = "";
				break;
		case 26: combination = "";
				break;
		case 27: combination = "";
				break;
		case 28: combination = "";
				break;
				
		}
		
		return combination;
	
	}

	private String getAlgebraicCombName(String analysisType) {
		int number = getLibraryNumber(analysisType);
		String combination = null;
		switch(number){
		case 1: combination = "compartment 1_plasma_iv bolus";
				break;
		case 2: combination = "compartment 1_plasma_iv infusion";
				break;
		case 3: combination = "compartment 1_plasma_extravascular";
				break;
		case 4: combination = "compartment 1_plasma_extravascular";
				break;
		case 5: combination = "compartment 1_plasma_extravascular";
				break;
		case 6: combination = "compartment 1_plasma_extravascular";
				break;
		case 7: combination = "compartment 2_plasma_iv bolus";
				break;
		case 8: combination = "compartment 2_plasma_iv bolus";
				break;
		case 9: combination = "compartment 2_plasma_iv infusion";
				break;
		case 10: combination = "compartment 2_plasma_iv infusion";
				break;
		case 11: combination = "compartment 2_plasma_extravascular";
				break;
		case 12: combination = "compartment 2_plasma_extravascular";
				break;
		case 13: combination = "compartment 2_plasma_extravascular";
				break;
		case 14: combination = "compartment 2_plasma_extravascular";
				break;
		case 15: combination = "compartment 1_plasma_iv bolus";
				break;
		case 16: combination = "compartment 2_plasma_iv bolus";
				break;
		case 17: combination = "compartment 2_plasma_iv bolus";
				break;
		case 18: combination = "compartment 3_plasma_iv bolus";
				break;
		case 19: combination = "compartment 3_plasma_iv infusion";
				break;
		
				
		}
		
		return combination;
	}
	
	private int getLibraryNumber(String analysisType) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (analysisType.equals("pk")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
							.getProcessingInputs().getModelInputTab1Obj().getModelNumberForCA();
		} else if (analysisType.equals("pd")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPdInfo()
							.getProcessingInputs().getModelInputTab1Obj().getModelNumberForCA();
		} else if (analysisType.equals("mm")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getMmInfo()
							.getProcessingInputs().getModelInputTab1Obj().getModelNumberForCA();
		} else if (analysisType.equals("pkpdlink")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkpdLinkInfo()
					.getProcessingInputs().getModelInputTab1Obj().getModelNumberForCA();
		} else if (analysisType.equals("irm")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getIrInfo()
							.getProcessingInputs().getModelInputTab1Obj().getModelNumberForCA();
		}
		 
		return 0;
	}
	
	public int getNumberOfSortVar(String analysisType){
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (analysisType.equals("pk")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
							.getProcessingInputs().getMappingDataObj().getSortVariablesListVector().size();
		} else if (analysisType.equals("pd")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPdInfo()
							.getProcessingInputs().getMappingDataObj().getSortVariablesListVector().size();
		} else if (analysisType.equals("mm")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getMmInfo()
							.getProcessingInputs().getMappingDataObj().getSortVariablesListVector().size();
		} else if (analysisType.equals("pkpdlink")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkpdLinkInfo()
							.getProcessingInputs().getMappingDataObj().getSortVariablesListVector().size();
		} else if (analysisType.equals("irm")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getIrInfo()
							.getProcessingInputs().getMappingDataObj().getSortVariablesListVector().size();
		}
		 
		return 0;
	}
	public int getNumberOfProfiles(String analysisType) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (analysisType.equals("pk")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
							.getProcessingInputs().getProfileAndParamInfoObj().getNoOfSubject();
		} else if (analysisType.equals("pd")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPdInfo()
							.getProcessingInputs().getProfileAndParamInfoObj().getNoOfSubject();
		} else if (analysisType.equals("mm")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getMmInfo()
							.getProcessingInputs().getProfileAndParamInfoObj().getNoOfSubject();
		} else if (analysisType.equals("pkpdlink")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkpdLinkInfo()
							.getProcessingInputs().getProfileAndParamInfoObj().getNoOfSubject();
		} else if (analysisType.equals("irm")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getIrInfo()
							.getProcessingInputs().getProfileAndParamInfoObj().getNoOfSubject();
		}
		 
		return 0;
	}

	public ProcessingInputsInfo getprocessingInputs(String analysisType) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (analysisType.equals("pk")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
							.getProcessingInputs();
		} else if (analysisType.equals("pd")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPdInfo()
							.getProcessingInputs();
		} else if (analysisType.equals("mm")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getMmInfo()
							.getProcessingInputs();
		} else if (analysisType.equals("pkpdlink")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkpdLinkInfo()
							.getProcessingInputs();
		} else if (analysisType.equals("irm")) {
			return appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getIrInfo()
							.getProcessingInputs();
		}
		 
		return null;
	}

	public void setProcessingInputs(ProcessingInputsInfo processingInputs, String analysisType) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (analysisType.equals("pk")) {
			 appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
							.setProcessingInputs(processingInputs);
		} else if (analysisType.equals("pd")) {
			 appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPdInfo()
							.setProcessingInputs(processingInputs);
		} else if (analysisType.equals("mm")) {
			 appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getMmInfo()
							.setProcessingInputs(processingInputs);
		} else if (analysisType.equals("pkpdlink")) {
			 appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkpdLinkInfo()
							.setProcessingInputs(processingInputs);
		} else if (analysisType.equals("irm")) {
			 appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getIrInfo()
							.setProcessingInputs(processingInputs);
		}
		 
		
	}
	
}
