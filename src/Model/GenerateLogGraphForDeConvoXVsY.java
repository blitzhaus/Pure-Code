package Model;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JInternalFrame;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import view.ProcessingInputsInfo;




public class GenerateLogGraphForDeConvoXVsY extends JInternalFrame {

	DeConvolutionVariables deConvoVarInst;
	ProcessingInputsInfo procInputInst;
	public GenerateLogGraphForDeConvoXVsY(final String title, double[] xplot,
			double[] yPlot, double[] termX, double[] yCalc, String profile) throws RowsExceededException, WriteException, BiffException, IOException {

		//super(title);
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		procInputInst = deConvoVarInst.copyProcessingInput();
	
		
		final XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries observedData = new XYSeries("Observed Data");

		for (int i = 0; i < xplot.length; i++) {
			if (yPlot[i] > 1)
				observedData.add(xplot[i], yPlot[i]);
			else
				continue;

		}
		dataset.addSeries(observedData);

		XYSeries predictedData = new XYSeries("Predicted Data");

		for (int i = 0; i < yCalc.length; i++) {

			if (yCalc[i] > 1 && termX[i] > 1)
				predictedData.add(termX[i], yCalc[i]);
			else
				continue;

		}
		dataset.addSeries(predictedData);

		String timeUnit = "";
		
		String concMassUnit = "";
		String volumeUnit = "";
		
		final JFreeChart chart = ChartFactory.createXYLineChart(profile,
				deConvoVarInst.xColumnName
						+ " (" + timeUnit + ")", deConvoVarInst.yColumnName
						+ " ("
						+ concMassUnit
						+ "/"
						+ volumeUnit + ")", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		
		chart.getTitle().setFont(new Font("Arial", Font.PLAIN, 12));
		final XYPlot plot = chart.getXYPlot();

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, true);
		renderer.setSeriesFillPaint(1, Color.blue);
		renderer.setSeriesShapesVisible(1, true);
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinesVisible(false);
		plot.setDomainGridlinesVisible(false);
		
		final NumberAxis domainAxis = new NumberAxis(deConvoVarInst.xColumnName
				+ " (" + timeUnit + ")");
		final NumberAxis rangeAxis = new LogarithmicAxis(deConvoVarInst.yColumnName
				+ " ("
				+ concMassUnit
				+ "/"
				+ volumeUnit
				+ ")");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(rangeAxis);
		chart.setBackgroundPaint(Color.white);
		plot.setOutlinePaint(Color.black);
		
		 deConvoVarInst.getWorkSheetOutputInst().getPlotOutputs().getLogplots().add(chart);
		
		
	}


	protected static void removeTitleBarForinternalFrame(JInternalFrame j) {
		javax.swing.plaf.InternalFrameUI ifu = j.getUI();
		((javax.swing.plaf.basic.BasicInternalFrameUI) ifu).setNorthPane(null);
	}

}
