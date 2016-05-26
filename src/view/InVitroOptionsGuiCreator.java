package view;

import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;

public class InVitroOptionsGuiCreator {

	
	JInternalFrame optionsInternalFrame;
	JTabbedPane moduleTabs;
	
	JDesktopPane modelWeightTabDesktopPane;
	JDesktopPane engineSettingTabDesktopPane;
	
	
	public static InVitroOptionsGuiCreator INVITRO_OPT_GUI = null;
	public static InVitroOptionsGuiCreator createInVitroOptionsInstance() throws RowsExceededException, WriteException, BiffException, IOException{
		if(INVITRO_OPT_GUI == null){
			INVITRO_OPT_GUI = new InVitroOptionsGuiCreator();
		}
		return INVITRO_OPT_GUI;
	}
	public void inVitroOptionsGuiCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createOptionsInternalFrame();
	}

	
	InVitroEngSettingDispGuiCreator inVitroEngSettDispGui;
	
	InVitroModelWeightDispGuiCreator inVitroModelWeightDispGui;

	private void createOptionsInternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {

		optionsInternalFrame = new JInternalFrame("options ", false, false,
				false, false);
		optionsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(optionsInternalFrame);
		
		int yCoordinate = 0;
		int width = 0;
		int height = 0;
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		
		DDViewLayer.createMTInstance().mainTabbedFrame.setSize(DDViewLayer
				.createMTInstance().mainTabbedFrameOriginalWidth, DDViewLayer
				.createMTInstance().mainTabbedFrameoriginalHeight);
			
		
			yCoordinate = InVitroMainScreenCreator.createInVitroMainScreenInstance().inVitroMainInternalFrame.getY()+
			InVitroMainScreenCreator.createInVitroMainScreenInstance().inVitroMainInternalFrame.getHeight();
			
			width = InVitroMainScreenCreator.createInVitroMainScreenInstance().inVitroMainInternalFrame.getWidth();
			height = InVitroMainScreenCreator.createInVitroMainScreenInstance().inVitroMainInternalFrame.getHeight()+20;
		
			
		
		
		optionsInternalFrame.setLocation(0,yCoordinate);
		
		
		if (DDViewLayer.createViewLayerInstance().isToNCAFromPlotMaven == true) {
			optionsInternalFrame.setSize(PdMainScreenCreator.createPdMainScreenInstance().pdMainInternalFrame.getWidth(),
					PdMainScreenCreator.createPdMainScreenInstance().pdMainInternalFrame.getHeight()+20);
		} else {
			optionsInternalFrame.setSize(width,
					height);
		}

		optionsInternalFrame.setVisible(true);
		optionsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane.add(optionsInternalFrame);
		
		
		moduleTabs = new JTabbedPane();
		modelWeightTabDesktopPane = new JDesktopPane();
		moduleTabs.addTab("Library Model-Weighting", modelWeightTabDesktopPane);
		
		
		
		engineSettingTabDesktopPane = new JDesktopPane();
		moduleTabs.addTab("Parameter Options-Engine Settings", engineSettingTabDesktopPane);
		
		optionsInternalFrame.getContentPane().add(moduleTabs);
		
		inVitroModelWeightDispGui = InVitroModelWeightDispGuiCreator.createInVitroModelWeightInstance();
		inVitroModelWeightDispGui.invitroModelWeightGuiCreation();
		
		inVitroEngSettDispGui = InVitroEngSettingDispGuiCreator.createEngineSettingInstance();
		inVitroEngSettDispGui.inVitroEngSettingGuiCreation();
		
		
				
		optionsInternalFrame.moveToFront();

	}



}
