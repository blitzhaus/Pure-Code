package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.JinternalFrameFunctions;

public class NcaResultDispComp {

	
	public NcaResultDispComp(String dummy){
		
	}
	
	public static NcaResultDispComp NCA_RES_DISP = null;
	public static NcaResultDispComp createNcaResDispCompInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(NCA_RES_DISP == null){
			NCA_RES_DISP = new NcaResultDispComp("just object creation");
		}
		return NCA_RES_DISP;
	}
	

	JInternalFrame displayResultsInternalFrame;
	JDesktopPane displayResultsinternalFrameDesktopPane;
	JInternalFrame finalParametersinternalFrame;
	JTable finalparametersVerticalDisplayTable;
	JInternalFrame finalParametersHorizontalDisplayInternalFrame;
	JInternalFrame dosingTableIF;
	JInternalFrame SubAreasIF;
	JInternalFrame summaryTableIF;
	JInternalFrame plotsIF;
	JInternalFrame textCompleteOutputInternalFrame;
	JTextArea completeTextOutputTextArea;
	ArrayList<JInternalFrame> outputPlotsArraylist;
	ArrayList<JInternalFrame> outputPlotsLogViewsArrayList;
	int selectedImage;
	JTable finalParametersHorizontalDisplayTable;
	TableColumnModel finalParametersVerticalDisplayTableModel;
	JTableHeader finalParametersHorizontalDisplayTableHeader;
	TableColumnModel finalParametersHorizontalDisplayTableModel;
	JTable resultsSubAreasTable;
	JTable resultsSummaryTable;
	JTable resultsDosingTable;
	
	public void NcaResultDispCompCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		
		outputPlotsArraylist = new ArrayList<JInternalFrame>();
		outputPlotsLogViewsArrayList = new ArrayList<JInternalFrame>();
		createDisplayResultInternalFrame();
	}
	private void createDisplayResultInternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		displayResultsInternalFrame = new JInternalFrame("Display", false,false,false,false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(displayResultsInternalFrame);
		displayResultsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		displayResultsInternalFrame.setLocation(
				0 + NcaResultsAvailableComp.createNcaResAvailCompInst().ResultsTabAvailableoutputInternalFrame.getWidth(), 0);
		displayResultsInternalFrame.setSize(NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame.getWidth()
				- NcaResultsAvailableComp.createNcaResAvailCompInst().ResultsTabAvailableoutputInternalFrame.getWidth(),
				NcaResultsAvailableComp.createNcaResAvailCompInst().ResultsTabAvailableoutputInternalFrame.getHeight());
		displayResultsInternalFrame.setVisible(true);
		displayResultsinternalFrameDesktopPane = new JDesktopPane();
		displayResultsInternalFrame
				.setContentPane(displayResultsinternalFrameDesktopPane);
		displayResultsInternalFrame.moveToFront();
		displayResultsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		createFinalParametersInternalFrame();
		createSubAreasDosingSummaryIFs();
		createCompleteOutputInternalFrame();
		createplotsInternalFrame();
	}
	
	private void createplotsInternalFrame() {
		
		plotsIF = new JInternalFrame("plots",false,false,false,false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(plotsIF);
		
		plotsIF.setLocation(0, 0);
		plotsIF.setSize(displayResultsInternalFrame
				.getWidth(), displayResultsInternalFrame.getHeight());
		plotsIF.setVisible(true);
		plotsIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		displayResultsinternalFrameDesktopPane
		.add(plotsIF);
		
	}
	private void createFinalParametersInternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {

		NcaTabbedPanes.createNcaTabbedPaneInstance().resultsTabDesktopPane.add(displayResultsInternalFrame);
		finalParametersinternalFrame = new JInternalFrame("Final Parameters",
				false,false,false,false);
		finalParametersinternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(finalParametersinternalFrame);
		finalParametersinternalFrame.setLocation(0, 0);
		finalParametersinternalFrame.setSize(displayResultsInternalFrame
				.getWidth(), displayResultsInternalFrame.getHeight());
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
				displayResultsInternalFrame.getHeight());
		finalParametersHorizontalDisplayInternalFrame.setVisible(true);
		displayResultsinternalFrameDesktopPane
				.add(finalParametersHorizontalDisplayInternalFrame);

	}
	
	

	private void createSubAreasDosingSummaryIFs() {
		
		//dosing table
		dosingTableIF = new JInternalFrame("Dosing", false,false,false,false);
		dosingTableIF.setLocation(0, 0);
		dosingTableIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(dosingTableIF);
		dosingTableIF.setSize(displayResultsInternalFrame.getWidth(), displayResultsInternalFrame.getHeight());
		dosingTableIF.setVisible(true);
		displayResultsinternalFrameDesktopPane.add(dosingTableIF);
		
		//sub areas table
		SubAreasIF = new JInternalFrame("sub Areas", false,false,false,false);
		SubAreasIF.setLocation(0, 0);
		SubAreasIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(SubAreasIF);
		SubAreasIF.setSize(displayResultsInternalFrame.getWidth(), displayResultsInternalFrame.getHeight());
		SubAreasIF.setVisible(true);
		displayResultsinternalFrameDesktopPane.add(SubAreasIF);
		
		//summary table
		summaryTableIF = new JInternalFrame("Summary table", false,false,false,false);//false,false,false,false
		summaryTableIF.setLocation(0, 0);
		summaryTableIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(summaryTableIF);
		summaryTableIF.setSize(displayResultsInternalFrame.getWidth(), displayResultsInternalFrame.getHeight());
		summaryTableIF.setVisible(true);
		displayResultsinternalFrameDesktopPane.add(summaryTableIF);
	}
	
	private void createCompleteOutputInternalFrame() {
		textCompleteOutputInternalFrame = new JInternalFrame("Complete output",
				false,false,false,false);
		textCompleteOutputInternalFrame.setLocation(0, 0);
		textCompleteOutputInternalFrame.setSize(displayResultsInternalFrame
				.getWidth(), displayResultsInternalFrame.getHeight());
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(textCompleteOutputInternalFrame);
		textCompleteOutputInternalFrame.setVisible(true);
		textCompleteOutputInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		completeTextOutputTextArea = new JTextArea();
		JScrollPane completeTextAreaScrollPane = new JScrollPane(
				completeTextOutputTextArea);
		completeTextOutputTextArea.setEditable(false);
		textCompleteOutputInternalFrame.getContentPane().add(
				completeTextAreaScrollPane);

		displayResultsinternalFrameDesktopPane
				.add(textCompleteOutputInternalFrame);
		textCompleteOutputInternalFrame.moveToFront();

	}
	
}
