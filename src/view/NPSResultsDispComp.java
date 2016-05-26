package view;

import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import Common.JinternalFrameFunctions;

public class NPSResultsDispComp {

	public static NPSResultsDispComp NPS_RES_DISP_COMP = null;
	public static NPSResultsDispComp createNPSResDispCompInst(){
		if(NPS_RES_DISP_COMP == null){
			NPS_RES_DISP_COMP = new NPSResultsDispComp("just object creation");
		}
		return NPS_RES_DISP_COMP;
	}
	
	public NPSResultsDispComp(String dummy){
	}
	
	 JInternalFrame displayResultsInternalFrame;
	 JDesktopPane displayResultsinternalFrameDesktopPane;
	 JInternalFrame finalParametersinternalFrame;
	 JInternalFrame finalParametersHorizontalDisplayInternalFrame;
	 JInternalFrame textCompleteOutputInternalFrame;
	// JTextArea completeTextOutputTextArea;
	 ArrayList<JInternalFrame> outputPlotsArraylist;
	 ArrayList<JInternalFrame> outputPlotsLogViewsArrayList;
	 JTable finalparametersVerticalDisplayTable;
	 JInternalFrame plotsFrame;
	
	void createDisplayResultInternalFrame() {
		displayResultsInternalFrame = new JInternalFrame("Display", true, true,
				true, true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(displayResultsInternalFrame);
		displayResultsInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		displayResultsInternalFrame.setLocation(0 + NPSResultsAvailComp
				.createNPSResAvailInst().ResultsTabAvailableoutputInternalFrame
				.getWidth(), 0);
		displayResultsInternalFrame
				.setSize(
						NPSMainPage.createNPSMainPageInst().npsMainInternalFrame
								.getWidth()
								- NPSResultsAvailComp.createNPSResAvailInst().ResultsTabAvailableoutputInternalFrame
										.getWidth(), NPSMainPage
								.createNPSMainPageInst().npsMainInternalFrame
								.getHeight()-10);
		displayResultsInternalFrame.setVisible(true);
		displayResultsinternalFrameDesktopPane = new JDesktopPane();
		displayResultsInternalFrame
				.setContentPane(displayResultsinternalFrameDesktopPane);
		displayResultsInternalFrame.moveToFront();
		displayResultsInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		createFinalParametersInternalFrame();
		createCompleteOutputInternalFrame();
		createPlotsFrame();
	}
	
	private void createPlotsFrame() {
		plotsFrame = new JInternalFrame("plot",false,false,false,false);
		plotsFrame.setLocation(0, 0);
		plotsFrame.setSize(displayResultsInternalFrame
				.getWidth(), displayResultsInternalFrame.getHeight() - 20);
		plotsFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(plotsFrame);
		plotsFrame.setVisible(true);
		displayResultsinternalFrameDesktopPane
		.add(plotsFrame);
	}

	private void createFinalParametersInternalFrame() {

		NPSTabs.createNPSTabsInst().resultsTabDesktopPane
				.add(displayResultsInternalFrame);
		finalParametersinternalFrame = new JInternalFrame("Final Parameters",
				true, true, true, true);
		finalParametersinternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(finalParametersinternalFrame);
		finalParametersinternalFrame.setLocation(0, 0);
		finalParametersinternalFrame.setSize(displayResultsInternalFrame
				.getWidth(), displayResultsInternalFrame.getHeight() - 20);
		finalParametersinternalFrame.setVisible(true);
		displayResultsinternalFrameDesktopPane
				.add(finalParametersinternalFrame);

		finalParametersHorizontalDisplayInternalFrame = new JInternalFrame(
				"Transposed Final Parameters", true, true, true, true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						finalParametersHorizontalDisplayInternalFrame);
		finalParametersHorizontalDisplayInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		finalParametersHorizontalDisplayInternalFrame.setLocation(0, 0);
		finalParametersHorizontalDisplayInternalFrame.setSize(
				displayResultsInternalFrame.getWidth(),
				displayResultsInternalFrame.getHeight() - 20);
		finalParametersHorizontalDisplayInternalFrame.setVisible(true);
		displayResultsinternalFrameDesktopPane
				.add(finalParametersHorizontalDisplayInternalFrame);

	}
	
	private void createCompleteOutputInternalFrame() {/*
		textCompleteOutputInternalFrame = new JInternalFrame("Complete output",
				true, true, true, true);
		JinternalFrameFunctions
				.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(textCompleteOutputInternalFrame);
		textCompleteOutputInternalFrame.setLocation(0, 0);
		textCompleteOutputInternalFrame.setSize(displayResultsInternalFrame
				.getWidth(), displayResultsInternalFrame.getHeight());
		textCompleteOutputInternalFrame.setVisible(true);
		textCompleteOutputInternalFrame.setBorder(ViewLayer
				.createViewLayerInstance().b);
		completeTextOutputTextArea = new JTextArea();
		JScrollPane completeTextAreaScrollPane = new JScrollPane(
				completeTextOutputTextArea);
		textCompleteOutputInternalFrame.getContentPane().add(
				completeTextAreaScrollPane);

		displayResultsinternalFrameDesktopPane
				.add(textCompleteOutputInternalFrame);
		textCompleteOutputInternalFrame.moveToFront();

	*/}
}
