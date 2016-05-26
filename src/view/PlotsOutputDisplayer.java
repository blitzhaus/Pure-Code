package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Common.JinternalFrameFunctions;

import view.ApplicationInfo;

import view.DisplayContents;

public class PlotsOutputDisplayer {

	ApplicationInfo appInfo;
	CaResultDispCompCreator caResDispCreatorInst;
	String analysisType;

	public void setPlotsToGUI() throws RowsExceededException, WriteException,
			BiffException, IOException {
		appInfo = DisplayContents.createAppInfoInstance();
		analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		

		if (analysisType.equals("pk")) {
			setLinearPlotsForPk();
			setLogPlotsForPk();
		} else if (analysisType.equals("pd")) {
			setLinearPlotsForPd();
			setLogPlotsForPd();
		} else if (analysisType.equals("mm")) {
			setLinearPlotsForMm();
			setLogPlotsForMm();
		} else if (analysisType.equals(
				"pkpdlink")) {
			setLinearPlotsForPkPdlink();
			setLogPlotsForPkPdLink();
		} else if (analysisType
				.equals("irm")) {
			setLinearPlotsForIrm();
			setLogPlotsForIrm();
		}else if (analysisType
				.equals("ascii")) {
			setLinearPlotsForAscii();
			setLogPlotsForAscii();
		}
		
		

	}

	
	private void setLogPlotsForAscii() throws RowsExceededException, WriteException, BiffException, IOException {


		caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst();
		
		caResDispCreatorInst.obsYVsWeightPredYLogPlotAL = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAsciiInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForObsevedYVsWeightedPredictedY());

		caResDispCreatorInst.partialDerivativeLogPlotAL = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAsciiInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForPartialDerivative());

		caResDispCreatorInst.weightPredYVsWeightResdYLogPlotAl = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAsciiInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForWeightedPredictedYVsWeightedResidualY());

		caResDispCreatorInst.xVsWeightedResidualYLogPlotAL = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAsciiInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForXVsWeightedResidualY());

		caResDispCreatorInst.xVsObsYAndPredYLinearPlotAL = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAsciiInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogplots());

		
	}


	private void setLinearPlotsForAscii() throws RowsExceededException, WriteException, BiffException, IOException {
		
		caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst(); 
		caResDispCreatorInst.obsYVsWeightPredYLinarPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY());

		caResDispCreatorInst.partialDerivativeLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative());

		caResDispCreatorInst.weightPredYVsWeightResdYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY());

		caResDispCreatorInst.xVsWeightedResidualYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY());

		caResDispCreatorInst.xVsObsYAndPredYLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlots());
	}


	public void setLogPlotsForPk() throws RowsExceededException, WriteException,
	BiffException, IOException {

		caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst();
		
		caResDispCreatorInst.obsYVsWeightPredYLogPlotAL = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForObsevedYVsWeightedPredictedY());

		caResDispCreatorInst.partialDerivativeLogPlotAL = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForPartialDerivative());

		caResDispCreatorInst.weightPredYVsWeightResdYLogPlotAl = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForWeightedPredictedYVsWeightedResidualY());

		caResDispCreatorInst.xVsWeightedResidualYLogPlotAL = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForXVsWeightedResidualY());

		caResDispCreatorInst.xVsObsYAndPredYLinearPlotAL = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogplots());
}

	

	private void setLogPlotsForPd() throws RowsExceededException,
			WriteException, BiffException, IOException {
		caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst();

		caResDispCreatorInst.obsYVsWeightPredYLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForObsevedYVsWeightedPredictedY());

		caResDispCreatorInst.partialDerivativeLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForPartialDerivative());

		caResDispCreatorInst.weightPredYVsWeightResdYLogPlotAl = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForWeightedPredictedYVsWeightedResidualY());

		caResDispCreatorInst.xVsWeightedResidualYLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForXVsWeightedResidualY());

		caResDispCreatorInst.xVsObsYAndPredYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogplots());
	}

	private void setLogPlotsForMm() throws RowsExceededException,
			WriteException, BiffException, IOException {

		caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst();
		caResDispCreatorInst.obsYVsWeightPredYLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForObsevedYVsWeightedPredictedY());

		caResDispCreatorInst.partialDerivativeLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForPartialDerivative());

		caResDispCreatorInst.weightPredYVsWeightResdYLogPlotAl = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForWeightedPredictedYVsWeightedResidualY());

		caResDispCreatorInst.xVsWeightedResidualYLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForXVsWeightedResidualY());

		caResDispCreatorInst.xVsObsYAndPredYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogplots());
	}

	private void setLogPlotsForPkPdLink() throws RowsExceededException,
			WriteException, BiffException, IOException {

		caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst();
		caResDispCreatorInst.obsYVsWeightPredYLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForObsevedYVsWeightedPredictedY());

		caResDispCreatorInst.partialDerivativeLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForPartialDerivative());

		caResDispCreatorInst.weightPredYVsWeightResdYLogPlotAl = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForWeightedPredictedYVsWeightedResidualY());

		caResDispCreatorInst.xVsWeightedResidualYLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForXVsWeightedResidualY());

		caResDispCreatorInst.xVsObsYAndPredYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogplots());
	}

	private void setLogPlotsForIrm() throws RowsExceededException,
			WriteException, BiffException, IOException {

		caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst();
		caResDispCreatorInst.obsYVsWeightPredYLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForObsevedYVsWeightedPredictedY());

		caResDispCreatorInst.partialDerivativeLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForPartialDerivative());

		caResDispCreatorInst.weightPredYVsWeightResdYLogPlotAl = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForWeightedPredictedYVsWeightedResidualY());

		caResDispCreatorInst.xVsWeightedResidualYLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForXVsWeightedResidualY());

		caResDispCreatorInst.xVsObsYAndPredYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLogplots());
	}

	private void setLinearPlotsForPd() throws RowsExceededException,
			WriteException, BiffException, IOException {
		caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst(); 
		caResDispCreatorInst.obsYVsWeightPredYLinarPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY());

		caResDispCreatorInst.partialDerivativeLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative());

		caResDispCreatorInst.weightPredYVsWeightResdYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY());

		caResDispCreatorInst.xVsWeightedResidualYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY());

		caResDispCreatorInst.xVsObsYAndPredYLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPdInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlots());
	}

	private void setLinearPlotsForMm() throws RowsExceededException,
			WriteException, BiffException, IOException {

		caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst();
		caResDispCreatorInst.obsYVsWeightPredYLinarPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY());

		caResDispCreatorInst.partialDerivativeLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative());

		caResDispCreatorInst.weightPredYVsWeightResdYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY());

		caResDispCreatorInst.xVsWeightedResidualYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY());

		caResDispCreatorInst.xVsObsYAndPredYLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getMmInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlots());
	}

	private void setLinearPlotsForPkPdlink() throws RowsExceededException,
			WriteException, BiffException, IOException {
		caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst();
		caResDispCreatorInst.obsYVsWeightPredYLinarPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY());

		caResDispCreatorInst.partialDerivativeLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative());

		caResDispCreatorInst.weightPredYVsWeightResdYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY());

		caResDispCreatorInst.xVsWeightedResidualYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY());

		caResDispCreatorInst.xVsObsYAndPredYLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPkpdLinkInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlots());
	}

	private void setLinearPlotsForIrm() throws RowsExceededException,
			WriteException, BiffException, IOException {
		
		caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst(); 
		caResDispCreatorInst.obsYVsWeightPredYLinarPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY());

		caResDispCreatorInst.partialDerivativeLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative());

		caResDispCreatorInst.weightPredYVsWeightResdYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY());

		caResDispCreatorInst.xVsWeightedResidualYLinearPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY());

		caResDispCreatorInst.xVsObsYAndPredYLogPlotAL = setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getIrInfo().getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlots());
	}

	public void setLinearPlotsForPk() throws RowsExceededException, WriteException,
	BiffException, IOException {

		caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst(); 
		caResDispCreatorInst.obsYVsWeightPredYLinarPlotAL = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotsForObsevedYVsWeightedPredictedY());

		caResDispCreatorInst.partialDerivativeLinearPlotAL = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative());

		caResDispCreatorInst.weightPredYVsWeightResdYLinearPlotAL = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getPkInfo()
				.getWorkSheetOutputs()
				.getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY());

		caResDispCreatorInst.xVsWeightedResidualYLinearPlotAL = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY());

		caResDispCreatorInst.xVsObsYAndPredYLogPlotAL = 
		setPlotsToIntFrameAL(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlots());
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

			f
					.setSize(
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getWidth(),
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getHeight());
			f.setContentPane(plotDispSP);
			f.setBorder(DDViewLayer.createViewLayerInstance().b);
			CaResultDispCompCreator.createCaResDispCompInst().displayResultsinternalFrameDesktopPane
					.add(f);

			displayInternalFrameAL.add(f);
		}

		return displayInternalFrameAL;

	}

}
