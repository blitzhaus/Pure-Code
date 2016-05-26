package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import org.jfree.chart.plot.Plot;

import Common.EventCodes;
import Common.Iconizing;
import Common.JinternalFrameFunctions;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PlotMavenCreateUI implements EventCodes{

	public static PlotMavenCreateUI PLOT_MAVEN_UI = null;
	public static PlotMavenCreateUI createPlotMavenUIInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PLOT_MAVEN_UI == null){
			PLOT_MAVEN_UI = new PlotMavenCreateUI("just object creation");
		}
		return PLOT_MAVEN_UI;
	}
	
	JTabbedPane plotMavenTabbedPane;
	JDesktopPane plotMavenSetupDesktopPane;
	JDesktopPane plotMavenResultDesktopPane;
	JDesktopPane plotMavenVerificationDesktopPane;
	JInternalFrame PlotMavenMainInternalFrame;
	PMSetupAvailComp pmSetupAvailComp;
	PMSetupDispComp pmSetupDispComp;
	PMOptions pmOptions;
	PMResultsAvailComp pmResultsAvailComp;
	PMResultsDispComp pmResultsDispComp;
	JDesktopPane logPlotsInternalFrameDesktopPane;
	JInternalFrame logPlotsInternalFrame;
	JInternalFrame resultsOptionsInternalFrame;
	JRadioButton logX;

	JRadioButton logy;
	JRadioButton linearX;
	JRadioButton lineary;

	
	public PlotMavenCreateUI(String dummy){
		
	}
	
	
	void plotMavenCreateUI(int selectedSheetIndex) throws RowsExceededException, WriteException, BiffException, IOException {

		
		//crating the tabbed pane i.e results, setup, verification.
		plotMavenTabbedPane = new JTabbedPane();
		PlotMavenMainInternalFrame = new JInternalFrame("Plot Maven Main Screen", false,false,false,false);
		PlotMavenMainInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		PlotMavenMainInternalFrame.setLocation(0, 0);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(PlotMavenMainInternalFrame);
		DDViewLayer.createMTInstance().mainTabbedFrame.setSize(DDViewLayer.createMTInstance().mainTabbedFrameOriginalWidth
				, DDViewLayer.createMTInstance().mainTabbedFrameoriginalHeight);
		PlotMavenMainInternalFrame.setSize(DDViewLayer.createMTInstance().mainTabbedFrame.getWidth(), DDViewLayer.createMTInstance()
				.mainTabbedFrame.getHeight());
		DDViewLayer.createMTInstance().mainTabbedDesktopPane.add(PlotMavenMainInternalFrame);
		plotMavenSetupDesktopPane = new JDesktopPane();
		plotMavenTabbedPane.addTab("Setup", plotMavenSetupDesktopPane);
		plotMavenResultDesktopPane = new JDesktopPane();
		plotMavenTabbedPane.addTab("Result", plotMavenResultDesktopPane);
	
		plotMavenVerificationDesktopPane = new  JDesktopPane();
		plotMavenTabbedPane.addTab("Verification", plotMavenVerificationDesktopPane);
		PlotMavenMainInternalFrame.getContentPane().add(plotMavenTabbedPane);
		PlotMavenMainInternalFrame.setVisible(true);
		PlotMavenMainInternalFrame.moveToFront();
		
		
		
		pmSetupAvailComp = PMSetupAvailComp.createPMAvailCompInst();
		pmSetupAvailComp.createPotMavenSetupAvailableComponentsFrame(selectedSheetIndex);
		pmSetupDispComp = PMSetupDispComp.createPMSetupDisCompInst();
		pmSetupDispComp.cratePlotMavenSetupDisplayFrame();
		pmOptions = PMOptions.createPMOptionsInst();
		pmOptions.createPlotMavenoptionsFrame();
		pmResultsAvailComp = PMResultsAvailComp.createPmSetupAvailCompInst();
		pmResultsAvailComp.createPlotMavenResultsAvailableInternalFrame();
		pmResultsDispComp = PMResultsDispComp.createPMDispInst();
		pmResultsDispComp.createPlotMavenResultsDisplayInternalFrame();
		createPlotMavenLogPlotsDisplayInternalFrame();
		createPlotMavenResultsOptionsInternalFrame();
	}
	
	private void createPlotMavenLogPlotsDisplayInternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		logPlotsInternalFrame = new JInternalFrame("Log View",false,false,false,false);
		
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(logPlotsInternalFrame);
		logPlotsInternalFrame.setLocation(PMResultsDispComp.createPMDispInst().ResultsDisplayInternalFrame.getX(),
				PMResultsAvailComp.createPmSetupAvailCompInst().ResultsAvailableInternalFrame.getY()+
				PMResultsAvailComp.createPmSetupAvailCompInst().ResultsAvailableInternalFrame.getHeight());
		logPlotsInternalFrame.setSize(PMResultsDispComp.createPMDispInst().ResultsDisplayInternalFrame.getWidth(), PlotMavenMainInternalFrame.getHeight()-
				PMResultsAvailComp.createPmSetupAvailCompInst().ResultsAvailableInternalFrame.getHeight()-25);
		logPlotsInternalFrame.setVisible(true);
		logPlotsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		plotMavenResultDesktopPane.add(logPlotsInternalFrame);
		logPlotsInternalFrame.setBackground(Color.white);
		logPlotsInternalFrameDesktopPane = new JDesktopPane();
		logPlotsInternalFrame.setContentPane(logPlotsInternalFrameDesktopPane);
		
	}

	private void createPlotMavenResultsOptionsInternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		resultsOptionsInternalFrame = new JInternalFrame("Options",false,false,false,false);
		//MainLayoutPage.removeTitleBarForinternalFrame(resultsOptionsInternalFrame);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(resultsOptionsInternalFrame);
		resultsOptionsInternalFrame.setBackground(Color.white);
		resultsOptionsInternalFrame.setLocation(
				PMResultsAvailComp.createPmSetupAvailCompInst().ResultsAvailableInternalFrame.getX(),
				PMResultsAvailComp.createPmSetupAvailCompInst().ResultsAvailableInternalFrame.getY()+
				PMResultsAvailComp.createPmSetupAvailCompInst().ResultsAvailableInternalFrame.getHeight());
		resultsOptionsInternalFrame.setSize(PMResultsAvailComp.createPmSetupAvailCompInst().ResultsAvailableInternalFrame.getWidth(), logPlotsInternalFrame.getHeight());
		resultsOptionsInternalFrame.setVisible(true);
		resultsOptionsInternalFrame.setLayout(new GridBagLayout());
		plotMavenResultDesktopPane.add(resultsOptionsInternalFrame);
		resultsOptionsInternalFrame.moveToFront();
		
		//creation of x scale panel
		JPanel xScalePanel = new JPanel();
		xScalePanel.setLayout(new GridBagLayout());
		xScalePanel.setBackground(Color.white);
		xScalePanel.setBorder(BorderFactory.createTitledBorder(DDViewLayer.createViewLayerInstance().b, "X Scale"));
		linearX = new JRadioButton("Linear");
		linearX.setSelected(true);
		linearX.addActionListener(DDViewLayer.createViewLayerInstance());
		linearX.setActionCommand(PMLINEARX);
		linearX.setBackground(Color.white);
		GridBagConstraints linearXCon = new GridBagConstraints();
		linearXCon.gridx = 0;
		linearXCon.gridy = 0;
		linearXCon.anchor = GridBagConstraints.LINE_START;
		xScalePanel.add(linearX,linearXCon);
		logX  = new JRadioButton("Logarithmic");
		logX.addActionListener(DDViewLayer.createViewLayerInstance());
		logX.setActionCommand(PMLOGX);
		logX.setBackground(Color.white);
		GridBagConstraints logXCon = new GridBagConstraints();
		logXCon.gridx = 0;
		logXCon.gridy = 1;
		logXCon.anchor = GridBagConstraints.LINE_START;
		xScalePanel.add(logX,logXCon);
		ButtonGroup xGroup = new ButtonGroup();
		xGroup.add(linearX);
		xGroup.add(logX);
		
		GridBagConstraints xScalePanelCon = new GridBagConstraints();
		xScalePanelCon.gridx = 0;
		xScalePanelCon.gridy = 0;
		xScalePanelCon.fill = GridBagConstraints.VERTICAL;
		resultsOptionsInternalFrame.getContentPane().add(xScalePanel,xScalePanelCon);
		
		
	}
	
	
}
