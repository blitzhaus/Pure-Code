package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PolyExpoMainScreenGUICreator {


	
	public static PolyExpoMainScreenGUICreator POLYEXPO_MAIN_SCREEN_GUI = null;
	public static PolyExpoMainScreenGUICreator createPolyExpoMainScreenGui() throws RowsExceededException, WriteException, BiffException, IOException{
		if(POLYEXPO_MAIN_SCREEN_GUI == null){
			POLYEXPO_MAIN_SCREEN_GUI = new PolyExpoMainScreenGUICreator();
		}
		return POLYEXPO_MAIN_SCREEN_GUI;
	}
	
	PolyExpoTabbedPanesCreator polyExpoTabbedPaneInstance;
	PolyExpoSetupAvailableCompCreator polyExpoSetupAvailComp;
	PolyExpoSetupDispCompGuiCreator polyExpoSetupDispGui;
	PolyExpoOptionsGuiCreator polyExpoOptionsGui;
	PolyExpoResultAvailCompDisplayer polyExpoResultsAvailComp;
	PolyExpoResultDispCompCreator polyExpoResultDispCom;
	
	
	public PolyExpoMainScreenGUICreator() throws RowsExceededException, WriteException, BiffException, IOException{
		
		polyExpoTabbedPaneInstance = PolyExpoTabbedPanesCreator.createPolyExpoTabbedPaneInstance();
		polyExpoTabbedPaneInstance.polyExpoTabbedPanesCreation();
		polyExpoSetupAvailComp = PolyExpoSetupAvailableCompCreator.createPolyExpoSetupAvailCompInst();
		polyExpoSetupAvailComp.polyExpoSetupAvailableCompCreation();
		
		polyExpoSetupDispGui = PolyExpoSetupDispCompGuiCreator.createPolyExpoMainScrSetupDispGui();
		polyExpoSetupDispGui.polyExpoSetupDispCompGuiCreation();
			
		polyExpoOptionsGui = PolyExpoOptionsGuiCreator.createPolyExpoOptionsInstance();
		polyExpoOptionsGui.polyExpoOptionsGuiCreation();
		
		
		polyExpoResultsAvailComp = PolyExpoResultAvailCompDisplayer.createPolyExpoResAvailCompInst();
		polyExpoResultsAvailComp.polyExpoResultsAvailableCompCreation();
			
		polyExpoResultDispCom = PolyExpoResultDispCompCreator.createPolyExpoResDispCompInst();
		polyExpoResultDispCom.polyExpoResultDispCompCreation();
		
	}




}
