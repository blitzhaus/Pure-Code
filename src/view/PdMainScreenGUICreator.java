package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.EventCodes;

public class PdMainScreenGUICreator implements EventCodes{
	
	public static PdMainScreenGUICreator PD_MAIN_SCREEN_GUI = null;
	public static PdMainScreenGUICreator createPdMainScreenGui() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PD_MAIN_SCREEN_GUI == null){
			PD_MAIN_SCREEN_GUI = new PdMainScreenGUICreator();
		}
		return PD_MAIN_SCREEN_GUI;
	}
	
	CaTabbedPanesCreator pdTabbedPaneInstance;
	CaSetupAvailableCompCreator pdSetupAvailComp;
	CaSetupDispCompGuiCreator pdSetupDispGui;
	CaOptionsGuiCreator pdOptionsGui;
	CaResultAvailCompDisplayer pdResultsAvailComp;
	CaResultDispCompCreator pdResultDispCom;
	
	
	public PdMainScreenGUICreator() throws RowsExceededException, WriteException, BiffException, IOException{
		
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
