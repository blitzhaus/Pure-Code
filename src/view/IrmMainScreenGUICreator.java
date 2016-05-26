package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.EventCodes;

public class IrmMainScreenGUICreator implements EventCodes{
		
		public static IrmMainScreenGUICreator IRM_MAIN_SCREEN_GUI = null;
		public static IrmMainScreenGUICreator createIrmMainScreenGui() throws RowsExceededException, WriteException, BiffException, IOException{
			if(IRM_MAIN_SCREEN_GUI == null){
				IRM_MAIN_SCREEN_GUI = new IrmMainScreenGUICreator();
			}
			return IRM_MAIN_SCREEN_GUI;
		}
		
		CaTabbedPanesCreator pdTabbedPaneInstance;
		CaSetupAvailableCompCreator pdSetupAvailComp;
		CaSetupDispCompGuiCreator pdSetupDispGui;
		CaOptionsGuiCreator pdOptionsGui;
		CaResultAvailCompDisplayer pdResultsAvailComp;
		CaResultDispCompCreator pdResultDispCom;
		
		
		public IrmMainScreenGUICreator() throws RowsExceededException, WriteException, BiffException, IOException{
			
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
