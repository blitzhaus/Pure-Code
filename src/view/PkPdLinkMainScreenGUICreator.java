package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.EventCodes;

public class PkPdLinkMainScreenGUICreator implements EventCodes{
	
	public static PkPdLinkMainScreenGUICreator PKPDLINK_MAIN_SCREEN_GUI = null;
	public static PkPdLinkMainScreenGUICreator createPkPdLinkMainScreenGui() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PKPDLINK_MAIN_SCREEN_GUI == null){
			PKPDLINK_MAIN_SCREEN_GUI = new PkPdLinkMainScreenGUICreator();
		}
		return PKPDLINK_MAIN_SCREEN_GUI;
	}
	
	CaTabbedPanesCreator pdTabbedPaneInstance;
	CaSetupAvailableCompCreator pdSetupAvailComp;
	CaSetupDispCompGuiCreator pdSetupDispGui;
	CaOptionsGuiCreator pdOptionsGui;
	CaResultAvailCompDisplayer pdResultsAvailComp;
	CaResultDispCompCreator pdResultDispCom;
	
	
	public PkPdLinkMainScreenGUICreator() throws RowsExceededException, WriteException, BiffException, IOException{
		
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
