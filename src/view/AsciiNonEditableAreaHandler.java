package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AsciiNonEditableAreaHandler {
	
	public static AsciiNonEditableAreaHandler ASCIINONEDITINST = null;
	public static AsciiNonEditableAreaHandler createAsciiNonEditHandnst(){
		if(ASCIINONEDITINST == null){
			ASCIINONEDITINST = new AsciiNonEditableAreaHandler();
		}
		return ASCIINONEDITINST;
	}
	//private AsciiModelInfo asciiModInfoInst;
	public AsciiNonEditableAreaHandler(){
		
	}
	
	void updateNonEditArea() throws RowsExceededException, WriteException, BiffException, IOException{
		clearNonEditArea();
		updateCommands();
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("\n");
		updatePriParam();
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("\n");
		updateSecParam();
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("\n");
		updateTemp();
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("\n");
		updateStart();
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("\n");
		updateDifferential();
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("\n");
		updateFunc();
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("\n");
		
		updateSecondary();
		storeInAppInfo();
	}

	private void storeInAppInfo() throws RowsExceededException, WriteException, BiffException, IOException {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiNonEditText().setNonEditText(
				AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.getText().toString());
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).setAsciiInfo(asciiModInfoInst);*/
	}

	private void clearNonEditArea() throws RowsExceededException, WriteException, BiffException, IOException {
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.setText("");
	}

	private void updateSecondary() throws RowsExceededException, WriteException, BiffException, IOException {
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append(" Secondary parameter equations\n");
		String[] local =AsciiEqnSecParamDispGui.createAsciiSecInst().asciiEqnSecParamTA.getText().toString().split("\n");
		for(int i=0;i<local.length;i++){
			AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("  "+local[i]+"\n");
		}
	}

	private void updateDifferential() throws RowsExceededException, WriteException, BiffException, IOException {
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append(" Differential equations\n");
		String[] local = AsciiDiffRqnDispGui.createAsciiDiffInst().asciiDiffEqnTA.getText().toString().split("\n");
		for(int i=0;i<local.length;i++){
			AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("  "+local[i]+"\n");
		}
	}

	private void updateStart() throws RowsExceededException, WriteException, BiffException, IOException {
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append(" Initialization\n");
		String[] local = AsciiInitializationDispGui.createAsciiStartInst().asciiInitializationTA.getText().toString().split("\n");
		for(int i=0;i<local.length;i++){
			AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("  "+local[i]+"\n");
		}
	}

	private void updateFunc() throws RowsExceededException, WriteException, BiffException, IOException {
		
		String[] local = AsciiAlgEqcDispGui.createAsciiFuncInst().asciiAlgEqnTA.getText().toString().split("\n");
		for(int i=0;i<local.length;i++){
			AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("  "+local[i]+"\n");
		}
	}

	private void updateTemp() throws RowsExceededException, WriteException, BiffException, IOException {
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append(" Declaration\n");
		String[] local = AsciiDeclarationDispGui.createAsciiTempInst().asciiDeclareTA.getText().toString().split("\n");
		for(int i=0;i<local.length;i++){
			AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("  "+local[i]+"\n");
		}
	}

	private void updateSecParam() throws RowsExceededException, WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
	AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("  secParam <- ");
		
		HashMap<Integer, String> hm = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters()
		.getSecParameters();
		
		for(int i: hm.keySet()){
			AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append(hm.get(i)+", ");
		}
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("\n");
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).setAsciiInfo(asciiModInfoInst);*/
	}

	private void updatePriParam() throws RowsExceededException, WriteException, BiffException, IOException {
		
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("  priParam <- ");
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		HashMap<Integer, String> hm = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters()
		.getPriParameters();
		
		for(int i: hm.keySet()){
			AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append(hm.get(i)+", ");
		}
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("\n");
/*		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).setAsciiInfo(asciiModInfoInst);*/
	}

	private void updateCommands() throws RowsExceededException, WriteException, BiffException, IOException {
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append(" Selection\n");
		
		String nPri;
		if(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nParamTF.getText().toString().equals("")){
			nPri = "0";
		} else {
			nPri = AsciiSelectionDispGUI.createAsciiCommandGuiInst().nParamTF.getText().toString();
		}
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("  nPriParam <- "+
				nPri+"\n");
		
		String nSec;
		if(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nSecParamTF.getText().toString().equals("")){
			nSec = "0";
		} else {
			nSec = AsciiSelectionDispGUI.createAsciiCommandGuiInst().nSecParamTF.getText().toString();
		}
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("  nSecParam <- "+
				nSec+"\n");
		String nFunc;
		if(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nFuncTF.getText().toString().equals("")){
			nFunc = "0";
		} else {
			nFunc = AsciiSelectionDispGUI.createAsciiCommandGuiInst().nFuncTF.getText().toString();
		}
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("  nAlgEqn <- "+
				nFunc+"\n");
		String nDer;
		if(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nDerTF.getText().toString().equals("")){
			nDer = "0";
		} else {
			nDer =  AsciiSelectionDispGUI.createAsciiCommandGuiInst().nDerTF.getText().toString();
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiCommands().setnDer(Integer.parseInt(nDer));
		}
		AsciiNonEditableArea.createAsciiNonEditInst().asciiSummaryTA.append("  nDiffEqn <- "+
				nDer+"\n");
	}
}
