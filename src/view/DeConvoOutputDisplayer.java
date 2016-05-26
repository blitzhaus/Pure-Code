package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.omg.CORBA.portable.ApplicationException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;
import Common.WriteAppinfoForUnitTest;

public class DeConvoOutputDisplayer {

	public static DeConvoOutputDisplayer DECONVO_OUT_GEN = null;

	public static DeConvoOutputDisplayer createDeConvoOutInst() {
		if (DECONVO_OUT_GEN == null) {
			DECONVO_OUT_GEN = new DeConvoOutputDisplayer();
		}
		return DECONVO_OUT_GEN;
	}

	String analysisType;
	DeConvoResultDispCompCreator deConvoResDispCreatorInst;
	PlotsOutputDisplayer plotRetrievingInst;
	TablesResultDisplayer dispResultInstance;

	public void deConvoOutputGeneration() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		analysisType = appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAnalysisType();

		
		
		if(analysisType.equals("wagnelson") || analysisType.equals("looriegel"))
		{
			deConvoResDispCreatorInst = DeConvoResultDispCompCreator
			.createDeConvoResDispCompInst();
			deConvoResDispCreatorInst.initializationOfPlotArrays();
			deConvoResDispCreatorInst.removePreviousResults();
			deConvoResDispCreatorInst.removingExistingRowsOfOutputTables();
			deConvoResDispCreatorInst.populateAvailableResultsWithPlots();
			setPlotsToGUI();
		}
		
				

		dispResultInstance = new TablesResultDisplayer();

		if (analysisType.equals("wagnelson")) {

			dispResultInstance.displayingResultsInTablesForWagNelson();

			for (int i = 0; i < appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getWagNelsonInfo().getWorkSheetOutputs().getTextoutput()
					.size(); i++) {
				deConvoResDispCreatorInst.completeTextOutputTextArea
						.append(appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getWagNelsonInfo().getWorkSheetOutputs()
								.getTextoutput().get(i));
			}
			deConvoResDispCreatorInst.completeTextOutputTextArea
					.setCaretPosition(0);
			makeResultsUneditable();
		}

		else if (analysisType.equals("looriegel")) {
			deConvoResDispCreatorInst.initializationOfPlotArrays();
			deConvoResDispCreatorInst.removePreviousResults();
			deConvoResDispCreatorInst.removingExistingRowsOfOutputTables();
			deConvoResDispCreatorInst.populateAvailableResultsWithPlots();

			dispResultInstance.displayingResultsInTablesForLooRiegel();

			for (int i = 0; i < appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getLooRiegelInfo().getWorkSheetOutputs().getTextoutput()
					.size(); i++) {
				deConvoResDispCreatorInst.completeTextOutputTextArea
						.append(appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getLooRiegelInfo().getWorkSheetOutputs()
								.getTextoutput().get(i));
			}
			deConvoResDispCreatorInst.completeTextOutputTextArea
					.setCaretPosition(0);
			makeResultsUneditable();
		} else if (analysisType.equals("polyexpo")) {

			PolyExpoResultDispCompCreator.createPolyExpoResDispCompInst()
					.initializationOfPlotArrays();
			PolyExpoResultDispCompCreator.createPolyExpoResDispCompInst()
					.removePreviousResults();
			PolyExpoResultDispCompCreator.createPolyExpoResDispCompInst()
					.removingExistingRowsOfOutputTables();
			PolyExpoResultDispCompCreator.createPolyExpoResDispCompInst()
					.populateAvailableResultsWithPlots();

			setPolyExpoPlotsToGUI();
			
			dispResultInstance.displayingResultsInTablesForPolyExpo();

			for (int i = 0; i < appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPolyExpoInfo()
					.getWorkSheetOutputs().getTextoutput().size(); i++) {
				PolyExpoResultDispCompCreator.createPolyExpoResDispCompInst().completeTextOutputTextArea
						.append(appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPolyExpoInfo().getWorkSheetOutputs()
								.getTextoutput().get(i));
			}

			PolyExpoResultDispCompCreator.createPolyExpoResDispCompInst().completeTextOutputTextArea
					.setCaretPosition(0);
		}

	}

	private void setPolyExpoPlotsToGUI() throws RowsExceededException, WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		PolyExpoResultDispCompCreator peResDispCreatorInst = PolyExpoResultDispCompCreator
				.createPolyExpoResDispCompInst();

		peResDispCreatorInst.xVsObsYAndPredYLinearPlotAL = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPolyExpoInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLinearPlots());
		peResDispCreatorInst.xVsObsYAndPredYLogPlotAL = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPolyExpoInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogplots());
		
		
		peResDispCreatorInst.cumuAbsLinearPlotAL = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPolyExpoInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLinearPlotsForObsevedYVsWeightedPredictedY());
		peResDispCreatorInst.cumuAbsLogPlotAl = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPolyExpoInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogPlotForObsevedYVsWeightedPredictedY());
		
		
		
		peResDispCreatorInst.fracAbsLinarPlotAL = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPolyExpoInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLinearPlotForXVsWeightedResidualY());
		peResDispCreatorInst.fracAbsLogPlotAL = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPolyExpoInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogPlotForXVsWeightedResidualY());
		
		

	
		
	}

	public void makeResultsUneditable() throws RowsExceededException,
			WriteException, BiffException, IOException {

		deConvoResDispCreatorInst = DeConvoResultDispCompCreator
				.createDeConvoResDispCompInst();

		deConvoResDispCreatorInst.completeTextOutputTextArea.setEditable(false);
		deConvoResDispCreatorInst.finalparametersVerticalDisplayTable
				.setEnabled(false);
		deConvoResDispCreatorInst.finalParametersHorizontalDisplayTable
				.setEnabled(false);

		deConvoResDispCreatorInst.dosingDisplayTable.setEnabled(false);

		deConvoResDispCreatorInst.predictedValueDisplayTable.setEnabled(false);

		deConvoResDispCreatorInst.summaryTableDisplayTable.setEnabled(false);
		deConvoResDispCreatorInst.dosingDisplayTable.setEnabled(false);
		deConvoResDispCreatorInst.exclusionDisplayTable.setEnabled(false);

		deConvoResDispCreatorInst.historyDisplayTable.setEnabled(false);

	}

	public void setPlotsToGUI() throws RowsExceededException, WriteException,
			BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		analysisType = appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.endsWith("wagnelson"))
			setPlotsForWagNelson();
		else if (analysisType.equals("looriegel"))
			setPlotsForlooRiegel();

	}

	private void setPlotsForWagNelson() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DeConvoResultDispCompCreator dcResDispCreatorInst = DeConvoResultDispCompCreator
				.createDeConvoResDispCompInst();

		dcResDispCreatorInst.outputPlotsArraylist = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLinearPlots());
		dcResDispCreatorInst.outputPlotsLogViewsArrayList = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogplots());
		
		
		dcResDispCreatorInst.fracAbsPlotsArraylist = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLinearPlotForXVsWeightedResidualY());
		dcResDispCreatorInst.fracAbsPlotsLogViewsArrayList = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogPlotForXVsWeightedResidualY());

	}

	private void setPlotsForlooRiegel() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DeConvoResultDispCompCreator dcResDispCreatorInst = DeConvoResultDispCompCreator
				.createDeConvoResDispCompInst();

		dcResDispCreatorInst.outputPlotsArraylist = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getLooRiegelInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLinearPlots());
		dcResDispCreatorInst.outputPlotsLogViewsArrayList = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getLooRiegelInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogplots());
		
		dcResDispCreatorInst.fracAbsPlotsArraylist = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getLooRiegelInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLinearPlotForXVsWeightedResidualY());
		dcResDispCreatorInst.fracAbsPlotsLogViewsArrayList = setPlotsToIntFrameAL(appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getLooRiegelInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogPlotForXVsWeightedResidualY());

	}

	private ArrayList<JInternalFrame> setPlotsToIntFrameAL(
			ArrayList<JFreeChart> linearPlotsAL) throws RowsExceededException,
			WriteException, BiffException, IOException {
		JinternalFrameFunctions jIFfunctionsInst = JinternalFrameFunctions
				.createIFFunctionsInstance();

		ArrayList<JInternalFrame> displayInternalFrameAL = new ArrayList<JInternalFrame>();

		ArrayList<JFreeChart> chartArraylist = linearPlotsAL;
		for (int i = 0; i < chartArraylist.size(); i++) {
			JFreeChart chart = chartArraylist.get(i);
			ChartPanel chartPanel = new ChartPanel(chart);

			JScrollPane plotDispSP = new JScrollPane(chartPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JInternalFrame f = new JInternalFrame();
			// f.setContentPane(chartPanel);
			jIFfunctionsInst.removeTitleBarForinternalFrame(f);
			f.setVisible(true);

			if(analysisType.equals("wagNelson") || analysisType.equals("looriegel"))
			{
				f.setSize(DeConvoResultDispCompCreator
						.createDeConvoResDispCompInst().displayResultsInternalFrame
						.getWidth(), DeConvoResultDispCompCreator
						.createDeConvoResDispCompInst().displayResultsInternalFrame
						.getHeight());
				f.setContentPane(plotDispSP);
				f.setBorder(DDViewLayer.createViewLayerInstance().b);
				DeConvoResultDispCompCreator.createDeConvoResDispCompInst().displayResultsinternalFrameDesktopPane
						.add(f);
			}else if(analysisType.equals("polyexpo"))
			{
				f.setSize(PolyExpoResultDispCompCreator
						.createPolyExpoResDispCompInst().displayResultsInternalFrame
						.getWidth(), PolyExpoResultDispCompCreator
						.createPolyExpoResDispCompInst().displayResultsInternalFrame
						.getHeight());
				f.setContentPane(plotDispSP);
				f.setBorder(DDViewLayer.createViewLayerInstance().b);
				PolyExpoResultDispCompCreator.createPolyExpoResDispCompInst().displayResultsinternalFrameDesktopPane
						.add(f);
			}
			
			

			
			
			displayInternalFrameAL.add(f);
		}

		return displayInternalFrameAL;

	}

}
