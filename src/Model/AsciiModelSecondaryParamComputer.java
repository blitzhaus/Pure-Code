package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import Jama.Matrix;

import view.ApplicationInfo;
import view.DisplayContents;

public class AsciiModelSecondaryParamComputer {

	public double[] secondaryParameterValue;

	String[] defaultUnit;
	String[] preferredUnit;
	
	PkPdInfo pkpdInst;

	int noOfSecParam;

	int numberOfSortVariable;
	String[][] dataSortVariables;
	String[] secParamName;
	
	public void secondaryParamCalForAsciiModel(double[] param, int profileNo,
			double[][] aInverse, double sd) throws IOException, RowsExceededException, WriteException, BiffException {

		pkpdInst = PkPdInfo.createPKPDInstance();
		initialize();

		secondaryParameterValue = new double[noOfSecParam];
		defaultUnit = new String[noOfSecParam];
		preferredUnit = new String[noOfSecParam];

		
	
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				"\r\n\r\n"
						+ StringUtils.rightPad(
								"*************************************************"
										+ "**********************************",
								80) + "\r\n\r\n");
		pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils.rightPad("", 5)
								+ "\t"
								+ StringUtils
										.rightPad(
												"SUMMARY OF ESTIMATED SECONDARY PARAMETERS",
												45) + "\r\n\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5)
						+ StringUtils.rightPad("PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad("UNIT", 20) + "\t"
						+ StringUtils.rightPad("ESTIMATE", 20) + "\r\n");
		
		
		calculteSecondaryParameter(param, profileNo, aInverse, sd);
		
		
		printSecondaryParamForEstimation(defaultUnit, preferredUnit, aInverse, sd);
	

	}
	
	
	private void calculteSecondaryParameter(double[] param, int profileNo,
			double[][] aInverse, double sd) throws IOException {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();

		
//		AsciiParameterEstimator.createAsciiParamEstimationInst().evaluateCode(param, xf, fn_no, profileNo)
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int numSecParam = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getAsciiInfo().getProcessingInputs().getProfileAndParamInfoObj().getSecondaryParameterNameForCA().length;
		

		String[] secParam = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getAsciiInfo().getProcessingInputs().getProfileAndParamInfoObj().getSecondaryParameterNameForCA();
		
		AsciiParameterEstimator.createAsciiParamEstimationInst().isLastIteration = true;
		AsciiParameterEstimator.createAsciiParamEstimationInst().evaluateCode(param, 0, 0, profileNo, false);
		
		for (int i = 0; i < numSecParam; i++) {
			String str = "";
			if (AsciiParameterEstimator.createAsciiParamEstimationInst().hashTable.get(secParam[i]) != null) {
				/*str += String.format("%-20s%-20f\n", secParam[i], Double
				.valueOf(AsciiParameterEstimator.createAsciiParamEstimationInst().hashTable.get(secParam[i])));*/
			secondaryParameterValue[i] = Double.valueOf(AsciiParameterEstimator.createAsciiParamEstimationInst().hashTable.get(secParam[i]));
			//System.out.println(str);
			
	}
}
		System.out.println("number of Iterations  = "+ AsciiParameterEstimator.iterationCount);
		System.out.println("total time elapsed = "+AsciiParameterEstimator.time);;
		
	}

	private void initialize() {

		noOfSecParam = pkpdInst.noOfSecParam;
		numberOfSortVariable = pkpdInst.numberOfSortVar;
		dataSortVariables = pkpdInst.dataSortVariables;
		secParamName = pkpdInst.secParamName;
	}
	
	
	private void printSecondaryParamForEstimation(String[] defaultUnit,
			String[] preferredUnit, double[][] aInverse, double sd) throws RowsExceededException,
			WriteException, BiffException, IOException {

		DefaultToPreferredUnitConverter unitConversionInst = new DefaultToPreferredUnitConverter();
		

		PkPdInfo pkPdInst = PkPdInfo.createPKPDInstance();
		double[] stdError = calculateStdErrorofSP(aInverse, sd);
		double[] CV = new double[noOfSecParam];

		int count = 0;
		String[] strForHorizontalParam = new String[pkPdInst.numberOfSortVar
				+ 3 * pkPdInst.noOfSecParam];

		for (int k = 0; k < pkPdInst.numberOfSortVar; k++) {
			strForHorizontalParam[k] = pkPdInst.dataSortVariables[pkPdInst
					.getCurrentProfileNumber()][k];
			count++;
		}

		for (int i = 0; i < pkPdInst.noOfSecParam; i++) {
			double convertAmount;
			try {
				convertAmount = unitConversionInst.unitConversion(
						defaultUnit[i], preferredUnit[i]);

			} catch (Exception e) {
				convertAmount = 1;
			}
			CV[i] = Math.abs(stdError[i] / secondaryParameterValue[i]) * 100;

			String[] s = new String[pkPdInst.numberOfSortVar + 5];
			for (int k = 0; k < pkPdInst.numberOfSortVar; k++) {
				s[k] = pkPdInst.dataSortVariables[pkPdInst
						.getCurrentProfileNumber()][k];
			}
			s[pkPdInst.numberOfSortVar] = pkPdInst.secParamName[i];
			s[pkPdInst.numberOfSortVar + 1] = preferredUnit[i];
			s[pkPdInst.numberOfSortVar + 2] = pkPdInst.formatting(
					secondaryParameterValue[i] * convertAmount,
					false);

			s[pkPdInst.numberOfSortVar + 3] = pkPdInst.formatting(stdError[i],
					false);

			s[pkPdInst.numberOfSortVar + 4] = pkPdInst.formatting(CV[i],
					false);

			strForHorizontalParam[count++] = pkPdInst.formatting(
					secondaryParameterValue[i] * convertAmount,
					false);

			strForHorizontalParam[count++] = pkPdInst.formatting(stdError[i],
					false);

			strForHorizontalParam[count++] = pkPdInst.formatting(CV[i],
					false);

			pkPdInst.getNonTransposedSPAL().add(s);

			pkPdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(secParamName[i], 20)
							+ "\t"
							+ StringUtils.rightPad(preferredUnit[i], 20)
							+ "\t"
							+ StringUtils.rightPad(pkPdInst.formatting(
									secondaryParameterValue[i] * convertAmount,
									false), 20)
							+ "\t"
							+ StringUtils.rightPad(pkPdInst.formatting(
									stdError[i], false), 20)
							+ "\t"
							+ StringUtils.rightPad(pkPdInst.formatting(
									CV[i], false), 20) + "\r\n");

		}

		pkPdInst.secondaryParametersAL.add(strForHorizontalParam);
		System.out.println();
	}

	private double[] calculateStdErrorofSP(double[][] aInverse, double sd) throws RowsExceededException,
			WriteException, BiffException, IOException {
		PkPdInfo pkPdInst = PkPdInfo.createPKPDInstance();

		double[][] parDerivative = new double[pkPdInst.parameter.length][1];

		double dose = PkParamEstimator.createPkParamEstimateInstance().dose[0];
		double[] param = new double[pkPdInst.parameter.length];

		double[] stdError = new double[noOfSecParam];

		StandardErrorCalculationOfSp inst = new StandardErrorCalculationOfSp();

		for (int j = 0; j < pkPdInst.noOfSecParam; j++) {

			for (int i = 0; i < parDerivative.length; i++) {
				param = pkPdInst.parameter;

				double[] value = inst.chooseLibraryModel(param);

				param[i] = pkPdInst.parameter[i] + pkPdInst.getPdIncrement();

				double[] incValue = inst.chooseLibraryModel(param);

				parDerivative[i][0] = (incValue[j] - value[j])
						/ pkPdInst.getPdIncrement();

			}

			Matrix AInv = new Matrix(aInverse);
			Matrix G = new Matrix(parDerivative);
			Matrix GT = G.transpose();
			Matrix GTAinv = GT.times(AInv);
			Matrix GTaInvG = GTAinv.times(G);

			double[][] value = GTaInvG.getArray();

			double val = value[0][0] * sd * sd;

			val = Math.sqrt(val);
			stdError[j] = val;

		}
		System.out.println();
		return stdError;
	}
	
	

}
