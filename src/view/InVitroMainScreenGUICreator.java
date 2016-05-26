package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class InVitroMainScreenGUICreator {

	
	public static InVitroMainScreenGUICreator INVITRO_MAIN_SCREEN_GUI = null;
	public static InVitroMainScreenGUICreator createInVitroMainScreenGui() throws RowsExceededException, WriteException, BiffException, IOException{
		if(INVITRO_MAIN_SCREEN_GUI == null){
			INVITRO_MAIN_SCREEN_GUI = new InVitroMainScreenGUICreator();
		}
		return INVITRO_MAIN_SCREEN_GUI;
	}
	
	InVitroTabbedPanesCreator inVitroTabbedPaneInstance;
	InVitroSetupAvailableCompCreator inVitroSetupAvailComp;
	InVitroSetupDispCompGuiCreator inVitroSetupDispGui;
	InVitroOptionsGuiCreator inVitroOptionsGui;
	InVitroResultAvailCompDisplayer inVitroResultsAvailComp;
	InVitroResultDispCompCreator inVitroResultDispCom;
	
	
	public InVitroMainScreenGUICreator() throws RowsExceededException, WriteException, BiffException, IOException{
		
		inVitroTabbedPaneInstance = InVitroTabbedPanesCreator.createInVitroTabbedPaneInstance();
		inVitroTabbedPaneInstance.inVitroTabbedPanesCreation();
		inVitroSetupAvailComp = InVitroSetupAvailableCompCreator.createInVitroSetupAvailCompInst();
		inVitroSetupAvailComp.inVitroSetupAvailableCompCreation();
		inVitroSetupDispGui = InVitroSetupDispCompGuiCreator.createInVitroMainScrSetupDispGui();
		inVitroSetupDispGui.inVitroSetupDispCompGuiCreation();
			
		inVitroOptionsGui = InVitroOptionsGuiCreator.createInVitroOptionsInstance();
		inVitroOptionsGui.inVitroOptionsGuiCreation();
		
		
		inVitroResultsAvailComp = InVitroResultAvailCompDisplayer.createInVitroResAvailCompInst();
		inVitroResultsAvailComp.inVitroResultsAvailableCompCreation();
			
		inVitroResultDispCom = InVitroResultDispCompCreator.createInVitroResDispCompInst();
		inVitroResultDispCom.inVitroResultDispCompCreation();
		
	}


}
