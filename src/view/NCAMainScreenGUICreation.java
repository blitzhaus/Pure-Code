package view;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import Common.EventCodes;
import Common.JinternalFrameFunctions;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NCAMainScreenGUICreation implements EventCodes{
	
	public static NCAMainScreenGUICreation NCA_MAIN_SCREEN_GUI = null;
	public static NCAMainScreenGUICreation createNcaMainScreenGui(int selectedSheetIndex) throws RowsExceededException, WriteException, BiffException, IOException{
		if(NCA_MAIN_SCREEN_GUI == null){
			NCA_MAIN_SCREEN_GUI = new NCAMainScreenGUICreation(selectedSheetIndex);
		}
		return NCA_MAIN_SCREEN_GUI;
	}
	
	NcaTabbedPanes ncaTabbedPaneInstance;
	NcaSetupAvailableComp ncaSetupAvailComp;
	NcaSetupDispCompGui ncaSetupDispGui;
	NcaOptionsGui ncaOptionsGui;
	NcaResultsAvailableComp ncaResultsAvailComp;
	NcaResultDispComp ncaResultDispCom;
	
	
	public NCAMainScreenGUICreation(int selectedSheetIndex) throws RowsExceededException, WriteException, BiffException, IOException{
		
		
		ncaTabbedPaneInstance = NcaTabbedPanes.createNcaTabbedPaneInstance();
		ncaTabbedPaneInstance.NcaTabbedPanesCreation();
		ncaSetupAvailComp = NcaSetupAvailableComp.createNcaSetupAvailCompInst();
		ncaSetupAvailComp.NcaSetupAvailableCompCreation(selectedSheetIndex);
		ncaSetupDispGui = NcaSetupDispCompGui.createNcaMainScrSetupDispGui();
		ncaSetupDispGui.NcaSetupDispCompGuiCreation();
		ncaOptionsGui = NcaOptionsGui.createNcaOptionsInstance();
		ncaOptionsGui.NcaOptionsGuiCreation();
		ncaResultsAvailComp = NcaResultsAvailableComp.createNcaResAvailCompInst();
		ncaResultsAvailComp.NcaResultsAvailableCompCreation();
		ncaResultDispCom = NcaResultDispComp.createNcaResDispCompInst();
		ncaResultDispCom.NcaResultDispCompCreation();
		
	}
	


	

	


	
}
