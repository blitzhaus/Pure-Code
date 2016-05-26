package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.EventCodes;

public class PkMainScreenGUICreator implements EventCodes{
	
	public static PkMainScreenGUICreator PK_MAIN_SCREEN_GUI = null;
	public static PkMainScreenGUICreator createPkMainScreenGui() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PK_MAIN_SCREEN_GUI == null){
			PK_MAIN_SCREEN_GUI = new PkMainScreenGUICreator();
		}
		return PK_MAIN_SCREEN_GUI;
	}
	
	CaTabbedPanesCreator pdTabbedPaneInstance;
	CaSetupAvailableCompCreator pdSetupAvailComp;
	CaSetupDispCompGuiCreator pdSetupDispGui;
	CaOptionsGuiCreator pdOptionsGui;
	CaResultAvailCompDisplayer pdResultsAvailComp;
	CaResultDispCompCreator pdResultDispCom;
	
	
	public PkMainScreenGUICreator() throws RowsExceededException, WriteException, BiffException, IOException{
		
		pdTabbedPaneInstance = CaTabbedPanesCreator.createPdTabbedPaneInstance();
		pdTabbedPaneInstance.pdTabbedPanesCreation();
		pdSetupAvailComp = CaSetupAvailableCompCreator.createPdSetupAvailCompInst();
		pdSetupAvailComp.pdSetupAvailableCompCreation();
		pdSetupDispGui = CaSetupDispCompGuiCreator.createPdMainScrSetupDispGui();
		pdSetupDispGui.pdSetupDispCompGuiCreation();
			
		pdOptionsGui = CaOptionsGuiCreator.createPdOptionsInstance();
		pdOptionsGui.pdOptionsGuiCreation();
		
		
		pdResultsAvailComp = CaResultAvailCompDisplayer.createPdResAvailCompInst();
		pdResultsAvailComp.pdResultsAvailableCompCreation();
			
		pdResultDispCom = CaResultDispCompCreator.createCaResDispCompInst();
		pdResultDispCom.pdResultDispCompCreation();
		
	}
}
