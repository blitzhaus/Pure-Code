package view;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PlotMavenoutputGeneration {

	public static PlotMavenoutputGeneration PM_OUT_GEN = null;

	public static PlotMavenoutputGeneration createPMoutGen() {
		if (PM_OUT_GEN == null) {
			PM_OUT_GEN = new PlotMavenoutputGeneration("just object creation");
		}
		return PM_OUT_GEN;
	}

	public PlotMavenoutputGeneration(String dummy) {

	}

	public void getnetateOutput() throws RowsExceededException, WriteException,
			BiffException, IOException {

		updateAvailableOutputsTree();
		setTheDeafaultLinearAndLogplots();
		saveLogxLogyPlotsExportToPdf();

	}

	private void saveLogxLogyPlotsExportToPdf() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		ArrayList<JFreeChart> al = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogplots();
		
		ArrayList<JFreeChart> logxlogyal = new ArrayList<JFreeChart>();
		for(int i=0;i<al.size();i++){
			
			JFreeChart chart = al.get(i);
			String xlable = chart.getXYPlot().getDomainAxis().getLabel();
			NumberAxis linearXAxis = new NumberAxis(xlable);
			chart.getXYPlot().setDomainAxis(linearXAxis);
			logxlogyal.add(chart);
		}
		
		
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo().getProcessingInputs().getExpToPdf().setLogPlots(logxlogyal);
	}

	void setTheDeafaultLinearAndLogplots()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		showlinearPlot(0);
		showLogPlot(0);
	}

	void showLogPlot(int i) throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		JFreeChart linearChart = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogplots().get(i);

		ChartPanel chartpanel = new ChartPanel(linearChart);
		chartpanel.setPreferredSize(new Dimension(PlotMavenCreateUI
				.createPlotMavenUIInst().logPlotsInternalFrame.getWidth(),
				PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame
						.getHeight()));
		PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame
				.getContentPane().removeAll();
		PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame
				.setContentPane(chartpanel);

		PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame
				.moveToFront();
		PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame.repaint();
		PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame.validate();
	}

	void showlinearPlot(int i) throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		JFreeChart linearChart = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLinearPlots().get(i);

		ChartPanel chartpanel = new ChartPanel(linearChart);
		chartpanel
				.setPreferredSize(new Dimension(
						PMResultsDispComp.createPMDispInst().ResultsDisplayInternalFrame
								.getWidth(),
						PMResultsDispComp.createPMDispInst().ResultsDisplayInternalFrame
								.getHeight()));
		PMResultsDispComp.createPMDispInst().ResultsDisplayInternalFrame
				.getContentPane().removeAll();
		PMResultsDispComp.createPMDispInst().ResultsDisplayInternalFrame
				.setContentPane(chartpanel);

		PMResultsDispComp.createPMDispInst().ResultsDisplayInternalFrame
				.moveToFront();
		PMResultsDispComp.createPMDispInst().ResultsDisplayInternalFrame.repaint();
		PMResultsDispComp.createPMDispInst().ResultsDisplayInternalFrame.validate();
		
	}

	private void updateAvailableOutputsTree() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		DefaultMutableTreeNode plotMavenPlotsAvailable = null;

		plotMavenPlotsAvailable = new DefaultMutableTreeNode("plots");
		PMResultsAvailComp.createPmSetupAvailCompInst().OutputMainNode
				.removeAllChildren();
		PMResultsAvailComp.createPmSetupAvailCompInst().OutputMainNode
				.add(plotMavenPlotsAvailable);
		((DefaultTreeModel) PMResultsAvailComp.createPmSetupAvailCompInst().resultsAvailableTree
				.getModel()).reload();

		int noNodes = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
		.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex()).getPlotInfo()
		.getWorkSheetOutputs().getPlotOutputs().getPlotMavenProfileList().size();

		for (int i = 0; i < noNodes; i++) {
			String nodeName = (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getWorkSheetOutputs().getPlotOutputs().getPlotMavenProfileList().get(i));

			DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeName);
			plotMavenPlotsAvailable.add(node);

		}

	}

}
