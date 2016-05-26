package view;

import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.text.View;
import javax.swing.tree.DefaultMutableTreeNode;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;

public class CaOptionsGuiCreator {
	
	JInternalFrame optionsInternalFrame;
	JTabbedPane moduleTabs;
	JDesktopPane libraryModelTabDesktopPane;
	JDesktopPane weightDoseTabDesktopPane;
	JDesktopPane engineSettingTabDesktopPane;
	JDesktopPane linkModelTabDesktopPane;
	
	public static CaOptionsGuiCreator PD_OPT_GUI = null;
	public static CaOptionsGuiCreator createPdOptionsInstance() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PD_OPT_GUI == null){
			PD_OPT_GUI = new CaOptionsGuiCreator();
		}
		return PD_OPT_GUI;
	}
	public void pdOptionsGuiCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createOptionsInternalFrame();
	}
	CaLibModelDispGuiCreator pdLibModelDispGui;
	CaWeightDoseDispGuiCreator pdWeightDoseDispGui;
	CaEngineSettingDispGuiCreator pdEngSettDispGui;
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
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		if(analysisType.equals("pk"))
		{
			yCoordinate = PkMainScreenCreator.createPkMainScreenInstance().pkMainInternalFrame.getY()+
			PkMainScreenCreator.createPkMainScreenInstance().pkMainInternalFrame.getHeight();
			width = PkMainScreenCreator.createPkMainScreenInstance().pkMainInternalFrame.getWidth();
			height = PkMainScreenCreator.createPkMainScreenInstance().pkMainInternalFrame.getHeight()+20;
		}
		else
		if(analysisType.equals("pd"))
		{
			yCoordinate = PdMainScreenCreator.createPdMainScreenInstance().pdMainInternalFrame.getY()+
			PdMainScreenCreator.createPdMainScreenInstance().pdMainInternalFrame.getHeight();
			width = PdMainScreenCreator.createPdMainScreenInstance().pdMainInternalFrame.getWidth();
			height = PdMainScreenCreator.createPdMainScreenInstance().pdMainInternalFrame.getHeight()+20;
		}
		else
			if(analysisType.equals("mm"))
			{
				yCoordinate = MmMainScreenCreator.createMmMainScreenInstance().mmMainInternalFrame.getY()+
				MmMainScreenCreator.createMmMainScreenInstance().mmMainInternalFrame.getHeight();
				width = MmMainScreenCreator.createMmMainScreenInstance().mmMainInternalFrame.getWidth();
				height = MmMainScreenCreator.createMmMainScreenInstance().mmMainInternalFrame.getHeight()+20;
			}
			else
				if(analysisType.equals("pkpdlink"))
				{
					yCoordinate = PkPdLinkMainScreenCreator.createPkPdLinkMainScreenInstance().pkpdLinkMainInternalFrame.getY()+
					PkPdLinkMainScreenCreator.createPkPdLinkMainScreenInstance().pkpdLinkMainInternalFrame.getHeight();
					width = PkPdLinkMainScreenCreator.createPkPdLinkMainScreenInstance().pkpdLinkMainInternalFrame.getWidth();
					height = PkPdLinkMainScreenCreator.createPkPdLinkMainScreenInstance().pkpdLinkMainInternalFrame.getHeight()+20;
				}
				else
					if(analysisType.equals("irm"))
					{
						yCoordinate = IrmMainScreenCreator.createIrmMainScreenInstance().irmMainInternalFrame.getY()+
						IrmMainScreenCreator.createIrmMainScreenInstance().irmMainInternalFrame.getHeight();
						
						width = IrmMainScreenCreator.createIrmMainScreenInstance().irmMainInternalFrame.getWidth();
						height = IrmMainScreenCreator.createIrmMainScreenInstance().irmMainInternalFrame.getHeight()+20;
					}
					else
						if(analysisType.equals("ascii"))
						{
							yCoordinate = AsciiMainScreen.createAsciiMainScreenInstance().asciiMainInternalFrame.getY()+
							AsciiMainScreen.createAsciiMainScreenInstance().asciiMainInternalFrame.getHeight();
							
							width = AsciiMainScreen.createAsciiMainScreenInstance().asciiMainInternalFrame.getWidth();
							height = AsciiMainScreen.createAsciiMainScreenInstance().asciiMainInternalFrame.getHeight()+20;
						}
			
		
		
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
		if(analysisType.equals("ascii"))
		{
		}else
		{
			libraryModelTabDesktopPane = new JDesktopPane();
			moduleTabs.addTab("Library Model", libraryModelTabDesktopPane);
		}
		if(analysisType.equals("pkpdlink")
				|| analysisType.equals("irm"))
		{
			linkModelTabDesktopPane = new JDesktopPane();
			moduleTabs.addTab("Link Model", linkModelTabDesktopPane);
		}
		weightDoseTabDesktopPane = new JDesktopPane();
		moduleTabs.addTab("Weight-Dose", weightDoseTabDesktopPane);
		engineSettingTabDesktopPane = new JDesktopPane();
		moduleTabs.addTab("Parameter Options-Engine Settings", engineSettingTabDesktopPane);
		optionsInternalFrame.getContentPane().add(moduleTabs);
		pdWeightDoseDispGui = pdWeightDoseDispGui.createCaWeightDoseInstance();
		pdWeightDoseDispGui.pdWeightDoseGuiCreation();
		pdEngSettDispGui = CaEngineSettingDispGuiCreator.createEngineSettingInstance();
		pdEngSettDispGui.pdEngSettingGuiCreation();
		if(analysisType.equals("ascii"))
		{
		}else
		{
			pdLibModelDispGui = CaLibModelDispGuiCreator.createCalibraryModelInstance();
			pdLibModelDispGui.caLibModelGuiCreation();
		}
		optionsInternalFrame.moveToFront();
	}
}