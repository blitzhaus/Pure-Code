package view;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.JinternalFrameFunctions;

public class PMResultsDispComp {

	public static PMResultsDispComp PM_RES_DISP_COMP = null;
	public static PMResultsDispComp createPMDispInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PM_RES_DISP_COMP == null){
			PM_RES_DISP_COMP = new PMResultsDispComp("just object creation");
		}
		return PM_RES_DISP_COMP;
	}
	
	JInternalFrame ResultsDisplayInternalFrame;
	JDesktopPane plotMavenResultsDisplayDesktopPane;
	 ArrayList<JInternalFrame> internalFramesArrayList;
		ArrayList<JInternalFrame> logInternalFramesArrayList;
	
	public PMResultsDispComp(String dummy) throws RowsExceededException, WriteException, BiffException, IOException{
		
	}
	
	void createPlotMavenResultsDisplayInternalFrame()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ResultsDisplayInternalFrame = new JInternalFrame("Display", false,
				false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(ResultsDisplayInternalFrame);
		ResultsDisplayInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		ResultsDisplayInternalFrame.setLocation(0 + PMResultsAvailComp
				.createPmSetupAvailCompInst().ResultsAvailableInternalFrame
				.getWidth(), 0);
		ResultsDisplayInternalFrame
				.setSize(
						PlotMavenCreateUI.createPlotMavenUIInst().PlotMavenMainInternalFrame
								.getWidth()
								- PMResultsAvailComp
										.createPmSetupAvailCompInst().ResultsAvailableInternalFrame
										.getWidth(),
						PMResultsAvailComp.createPmSetupAvailCompInst().ResultsAvailableInternalFrame
								.getHeight());
		ResultsDisplayInternalFrame.setVisible(true);
		PlotMavenCreateUI.createPlotMavenUIInst().plotMavenResultDesktopPane
				.add(ResultsDisplayInternalFrame);
		ResultsDisplayInternalFrame.moveToFront();
		ResultsDisplayInternalFrame.setBackground(Color.white);
		plotMavenResultsDisplayDesktopPane = new JDesktopPane();
		ResultsDisplayInternalFrame
				.setContentPane(plotMavenResultsDisplayDesktopPane);
	}
}
