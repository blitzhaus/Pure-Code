package view;

import java.io.File;
import java.io.IOException;

import Common.MenuToolBarEnableDisableFeatures;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class RestoringAnalysis {

	private RestoreNCA restoreNca;
	private RestoreDS restoreDs;
	private RestoreNPS restoreNps;
	private RestoreSCA restoreSca;
	private RestoreplotAnalysis restoreplotAnalysis;
	
	private RestorePD restorePd;
	private RestoreMM restoreMm;
	private RestoreIRM restoreIrm;
	private RestorePkPdLink restorePkPdLink;
	private RestorePk restorePk;
	
	

	public RestoreplotAnalysis getRestoreplotAnalysis() {
		return restoreplotAnalysis;
	}


	public void setRestoreplotAnalysis(RestoreplotAnalysis restoreplotAnalysis) {
		this.restoreplotAnalysis = restoreplotAnalysis;
	}


	private boolean isRestoringParameterNames;
	private static boolean isRestoringParameterUnits;
	
	
	public RestoreNCA getRestoreNca() {
		return restoreNca;
	}


	public void setRestoreNca(RestoreNCA restoreNca) {
		this.restoreNca = restoreNca;
	}


	public RestoreDS getRestoreDs() {
		return restoreDs;
	}


	public void setRestoreDs(RestoreDS restoreDs) {
		this.restoreDs = restoreDs;
	}


	public RestoreNPS getRestoreNps() {
		return restoreNps;
	}


	public void setRestoreNps(RestoreNPS restoreNps) {
		this.restoreNps = restoreNps;
	}


	public RestoreSCA getRestoreSca() {
		return restoreSca;
	}


	public void setRestoreSca(RestoreSCA restoreSca) {
		this.restoreSca = restoreSca;
	}


	public RestoringAnalysis(){
		
		menuToolBarInst = 	MenuToolBarEnableDisableFeatures.createMenuToolEnabDisabInst();
	}
	
	

	MenuToolBarEnableDisableFeatures menuToolBarInst = null;
	public void restoreNcaAnalysis(String[] pathSplits) {
		restoreNca = new RestoreNCA(pathSplits);
		menuToolBarInst.disableEnableMenuToolBarFeatures();
		
	}
	
	public void restoreCaAnalysis(String[] pathSplits, DDViewLayer vl) {/*
		restoreCa = new RestoreCA(pathSplits, vl);
		menuToolBarInst.disableEnableMenuToolBarFeatures();
	*/}	
		public void restorePkAnalysis(String[] pathSplits) throws RowsExceededException, WriteException, BiffException {
			menuToolBarInst.disableEnableMenuToolBarFeatures();
		restorePk = new RestorePk(pathSplits);
	}	
	
	public void restorePdAnalysis(String[] pathSplits) {
		restorePd = new RestorePD(pathSplits);
		menuToolBarInst.disableEnableMenuToolBarFeatures();
	}
	
	public void restoreMmAnalysis(String[] pathSplits) {
		restoreMm = new RestoreMM(pathSplits);
		menuToolBarInst.disableEnableMenuToolBarFeatures();
		
	}
	public void restoreIrmAnalysis(String[] pathSplits) {
		restoreIrm = new RestoreIRM(pathSplits);
		menuToolBarInst.disableEnableMenuToolBarFeatures();
	}
	public void restorePkPdLinkAnalysis(String[] pathSplits) {
		restorePkPdLink = new RestorePkPdLink(pathSplits);
		menuToolBarInst.disableEnableMenuToolBarFeatures();
	}



	public void restoreDSAnalysis(String[] pathSplits) throws RowsExceededException, WriteException, BiffException {
		// TODO Auto-generated method stub
		restoreDs = new RestoreDS(pathSplits);
		menuToolBarInst.disableEnableMenuToolBarFeatures();
	}


	public void restoreNPSAnalysis(String[] pathSplits) throws RowsExceededException, WriteException, BiffException {
		// TODO Auto-generated method stub
		restoreNps = new RestoreNPS(pathSplits);
		menuToolBarInst.disableEnableMenuToolBarFeatures();
	}


	public void restoreSCAAnalysis(String[] pathSplits) throws RowsExceededException, WriteException, BiffException {
		// TODO Auto-generated method stub
		restoreSca = new RestoreSCA(pathSplits);
		menuToolBarInst.disableEnableMenuToolBarFeatures();
	}


	public void restorePlotAnalysis(String[] pathSplits) {
		// TODO Auto-generated method stub
		restoreplotAnalysis = new RestoreplotAnalysis(pathSplits);
		menuToolBarInst.disableEnableMenuToolBarFeatures();
	}	
	
	
}
