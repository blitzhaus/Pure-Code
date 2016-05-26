package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.EventCodes;

public class MmMainScreenGUICreator implements EventCodes{
	
	public static MmMainScreenGUICreator MM_MAIN_SCREEN_GUI = null;
	public static MmMainScreenGUICreator createMmMainScreenGui() throws RowsExceededException, WriteException, BiffException, IOException{
		if(MM_MAIN_SCREEN_GUI == null){
			MM_MAIN_SCREEN_GUI = new MmMainScreenGUICreator();
		}
		return MM_MAIN_SCREEN_GUI;
	}
	
	CaTabbedPanesCreator pdTabbedPaneInstance;
	CaSetupAvailableCompCreator pdSetupAvailComp;
	CaSetupDispCompGuiCreator pdSetupDispGui;
	CaOptionsGuiCreator pdOptionsGui;
	CaResultAvailCompDisplayer pdResultsAvailComp;
	CaResultDispCompCreator pdResultDispCom;
	
	
	public MmMainScreenGUICreator() throws RowsExceededException, WriteException, BiffException, IOException{
		
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
