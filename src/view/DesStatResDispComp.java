package view;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableColumnModel;

import Common.JinternalFrameFunctions;


public class DesStatResDispComp {

	public static DesStatResDispComp DES_STAT_RES_DISP = null;
	public static DesStatResDispComp createDesStatResDispInst(){
		if(DES_STAT_RES_DISP == null){
			DES_STAT_RES_DISP = new DesStatResDispComp();
		}
		return DES_STAT_RES_DISP;
	}
	
	
	public DesStatResDispComp(){
		
	}
	JInternalFrame displayResultsInternalFrame;
	JDesktopPane displayResultsinternalFrameDesktopPane;
	JInternalFrame finalParametersinternalFrame;
	JInternalFrame finalParametersHorizontalDisplayInternalFrame;
	JInternalFrame textCompleteOutputInternalFrame;
	//JTextArea completeTextOutputTextArea;
	boolean carryThisToOtherComponents;
	JTable finalparametersVerticalDisplayTable;
	TableColumnModel finalParametersVerticalDisplayTableModel;
	
	 void createDisplayResultInternalFrame() {
		displayResultsInternalFrame = new JInternalFrame("Display", false,false,false,false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(displayResultsInternalFrame);
		displayResultsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		displayResultsInternalFrame.setLocation(
				0 + DesStatResAvailComp.createDesStatResAvailInst().ResultsTabAvailableoutputInternalFrame.getWidth(), 0);
		displayResultsInternalFrame.setSize(DescriptiveStatMainPage.createDescStatMainPageInst().desStatsMainIF.getWidth()
				- DesStatResAvailComp.createDesStatResAvailInst().ResultsTabAvailableoutputInternalFrame.getWidth(),
				DescriptiveStatMainPage.createDescStatMainPageInst().desStatsMainIF.getHeight());
		displayResultsInternalFrame.setVisible(true);
		displayResultsinternalFrameDesktopPane = new JDesktopPane();
		displayResultsInternalFrame
				.setContentPane(displayResultsinternalFrameDesktopPane);
		displayResultsInternalFrame.moveToFront();
		displayResultsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		createFinalParametersInternalFrame();
		//createCompleteOutputInternalFrame();
	}
	
/*	private void createCompleteOutputInternalFrame() {
		textCompleteOutputInternalFrame = new JInternalFrame("Complete output",
				true, true, true, true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(textCompleteOutputInternalFrame);
		textCompleteOutputInternalFrame.setLocation(0, 0);
		textCompleteOutputInternalFrame.setSize(
				displayResultsInternalFrame.getWidth(),
				displayResultsInternalFrame.getHeight());
		textCompleteOutputInternalFrame.setVisible(true);
		textCompleteOutputInternalFrame.setBorder(ViewLayer.createViewLayerInstance().b);
		completeTextOutputTextArea = new JTextArea();
		JScrollPane completeTextAreaScrollPane = new JScrollPane(
				completeTextOutputTextArea);
		textCompleteOutputInternalFrame.getContentPane().add(
				completeTextAreaScrollPane);

		displayResultsinternalFrameDesktopPane
				.add(textCompleteOutputInternalFrame);
		textCompleteOutputInternalFrame.moveToFront();

	}*/
	private void createFinalParametersInternalFrame() {

		DSTabs.createDSTabInst().resultsTabDesktopPane.add(displayResultsInternalFrame);
		finalParametersinternalFrame = new JInternalFrame("Final Parameters",
				false,false,false,false);
		finalParametersinternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(finalParametersinternalFrame);
		finalParametersinternalFrame.setLocation(0, 0);
		finalParametersinternalFrame.setSize(
				displayResultsInternalFrame.getWidth(),
				displayResultsInternalFrame.getHeight() - 20);
		finalParametersinternalFrame.setVisible(true);
		displayResultsinternalFrameDesktopPane
				.add(finalParametersinternalFrame);

		finalParametersHorizontalDisplayInternalFrame = new JInternalFrame(
				"Transposed Final Parameters", true, true, true, true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(finalParametersHorizontalDisplayInternalFrame);
		finalParametersHorizontalDisplayInternalFrame
				.setBorder(DDViewLayer.createViewLayerInstance().b);
		finalParametersHorizontalDisplayInternalFrame.setLocation(0, 0);
		finalParametersHorizontalDisplayInternalFrame.setSize(
				displayResultsInternalFrame.getWidth(),
				displayResultsInternalFrame.getHeight() - 20);
		finalParametersHorizontalDisplayInternalFrame.setVisible(true);
		displayResultsinternalFrameDesktopPane
				.add(finalParametersHorizontalDisplayInternalFrame);

	}
}
