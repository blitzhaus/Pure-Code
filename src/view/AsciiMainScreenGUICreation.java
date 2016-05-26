package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.EventCodes;

public class AsciiMainScreenGUICreation implements EventCodes{
	
	public static AsciiMainScreenGUICreation ASCII_MAIN_SCREEN_GUI = null;
	public static AsciiMainScreenGUICreation createAsciiMainScreenGui() throws RowsExceededException, WriteException, BiffException, IOException{
		if(ASCII_MAIN_SCREEN_GUI == null){
			ASCII_MAIN_SCREEN_GUI = new AsciiMainScreenGUICreation();
		}
		return ASCII_MAIN_SCREEN_GUI;
	}
	
	CaTabbedPanesCreator pdTabbedPaneInstance;
	CaSetupAvailableCompCreator pdSetupAvailComp;
	CaSetupDispCompGuiCreator pdSetupDispGui;
	CaOptionsGuiCreator pdOptionsGui;
	CaResultAvailCompDisplayer pdResultsAvailComp;
	CaResultDispCompCreator pdResultDispCom;
	
	
	public AsciiMainScreenGUICreation() throws RowsExceededException, WriteException, BiffException, IOException{
		
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
