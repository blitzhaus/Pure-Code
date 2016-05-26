package Model;

import java.io.IOException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.StringUtils;

import view.ProcessingInputsInfo;

public class EstimatedParamValStorer {

	double[] tValue = { 12.706, 4.303, 3.182, 2.776, 2.571, 2.447, 2.365,
			2.306, 2.262, 2.228, 2.201, 2.179, 2.160, 2.145, 2.131, 2.120,
			2.110, 2.101, 2.093, 2.086, 2.080, 2.074, 2.069, 2.064, 2.060, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	String[] paramDefaultUnit;
	String[] paramPreferredUnit;
	int noOfParam;
	
	int numberOfSortVariable;
	String[][] dataSortVariables;
	String[] paramName;
	boolean clearancaParam;

	PkPdInfo pkPdInst;
	ProcessingInputsInfo procInputInst;
	
public static EstimatedParamValStorer printParamInst=null;
	
	public static EstimatedParamValStorer createParamPrintInstance() {
		if (printParamInst == null) {
			printParamInst = new EstimatedParamValStorer();
		}
		return printParamInst;
	}
	

	public EstimatedParamValStorer() {

		paramDefaultUnit = new String[1];
		paramPreferredUnit = new String[1];

	}

	PkModelsParamValStorer pkModelParamValStoreInst;
	PdModelsParamValStorer pdModelParamStoreInst;
	MmModelsParamValStorer mmModelParamStoreInst;
	IrmModelsParamValStorer irmModelParamStoreInst;
	PkPdLinkModelsParamValStorer pkpdLinkModelParamStoreInst;
	AsciiModelsParamValStorer asciiModelParamValStoreInst;
	InVitroModelParamValStorer inVitroModelParamValStoreInst;
	
	
	public void parameterValuePrinting(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		pkPdInst = PkPdInfo.createPKPDInstance();
		headerForEstimatedParameter();
		procInputInst = pkPdInst.copyProcessingInput();

		initializition();

		if (pkPdInst.analysisType.equals("pk")) {
			
			pkModelParamValStoreInst = PkModelsParamValStorer.createParamPrintInstance();
			pkModelParamValStoreInst.parameterValuePrinting(parameter, initial, AInv, SD);
			
			
		} else if (pkPdInst.analysisType.equals("pd")) {
			pdModelParamStoreInst = new PdModelsParamValStorer();
			pdModelParamStoreInst.printingOfPdModels(parameter, initial, AInv, SD);

		} else if (pkPdInst.analysisType.equals("mm")) {
			mmModelParamStoreInst = new MmModelsParamValStorer();
			mmModelParamStoreInst.printingOfMmModels(parameter, initial, AInv, SD);
		} else if (pkPdInst.analysisType.equals("irm")) {
			irmModelParamStoreInst = new IrmModelsParamValStorer();
			irmModelParamStoreInst.printingOfIrmModels(parameter, initial, AInv, SD);
		}else if (pkPdInst.analysisType.equals("pkpdlink")) {
			pkpdLinkModelParamStoreInst = new PkPdLinkModelsParamValStorer();
			pkpdLinkModelParamStoreInst.printingOfPkPdModels(parameter, initial, AInv, SD);
		}else if (pkPdInst.analysisType.equals("ascii")) {
			asciiModelParamValStoreInst = new AsciiModelsParamValStorer();
			asciiModelParamValStoreInst.parameterValuePrinting(parameter, initial, AInv, SD);
		}else if (pkPdInst.analysisType.equals("invitro")) {
			inVitroModelParamValStoreInst = new InVitroModelParamValStorer();
			inVitroModelParamValStoreInst.parameterValuePrinting(parameter, initial, AInv, SD);
		}

	}

	private void initializition() {
		noOfParam = pkPdInst.noOfParam;
		numberOfSortVariable = pkPdInst.numberOfSortVar;
		dataSortVariables = pkPdInst.dataSortVariables;
		paramName = pkPdInst.paramName;
	}

	public void headerForEstimatedParameter() throws RowsExceededException,
			WriteException, BiffException, IOException {
		
		PkPdInfo pkPdInst = PkPdInfo.createPKPDInstance();

		boolean ifSimulation = pkPdInst.ifSimulation;
		if (ifSimulation == true) {
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput().add(
							StringUtils.rightPad("", 40)
									
									+ StringUtils.rightPad(
											"ESTIMATED PARAMETERS VALUE", 30)
									+ "\r\n\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput().add(
							StringUtils.rightPad("", 5)
									+ StringUtils.rightPad("Parameter", 10)
									+ "\t" + StringUtils.rightPad("Unit", 15)
									+ "\t"
									+ StringUtils.rightPad("Estimate", 10)
									+ "\t" + StringUtils.rightPad("VIF", 10)
									+ "\t");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput().add(
							StringUtils.rightPad("", 5)
									+ StringUtils.rightPad("", 10) + "\t"
									+ StringUtils.rightPad("", 15) + "\t"
									+ StringUtils.rightPad("", 10) + "\t"
									+ StringUtils.rightPad("", 10) + "\t"
									+ "\r\n");

		} else {
			
			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");
			pkPdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 40)
					
					+ StringUtils.rightPad("ESTIMATED PARAMETERS VALUE", 30)
							+ "\r\n\r\n");
			pkPdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad("Parameter", 10) + "\t"
							+ StringUtils.rightPad("Unit", 15) + "\t"
							+ StringUtils.rightPad("Estimate", 10) + "\t"
							+ StringUtils.rightPad("SE", 10) + "\t"
							+ StringUtils.rightPad("CV%", 10) + "\t"
							+ StringUtils.rightPad("UNI_LOWER", 10) + "\t"
							+ StringUtils.rightPad("UNI_UPPER", 10) + "\r\n");
			pkPdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5) + StringUtils.rightPad("", 10)
							+ "\t" + StringUtils.rightPad("", 15) + "\t"
							+ StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\r\n");
		}

	}

	public void writingOutputInSpreedSheetAndTextArea(double[] parameter,
			double[] initial, double[][] AInv, double SD, String[] defaultUnit,
			String[] preferredUnit) throws RowsExceededException,
			WriteException, BiffException, IOException {

		
		procInputInst = PkPdInfo.createPKPDInstance().copyProcessingInput();

		boolean ifSimulation = procInputInst.getModelInputTab1Obj().isSimulation();

		if (ifSimulation == true) {
			writingOutputInSpreedSheetAndTextAreaForSimulation(parameter, AInv,
					defaultUnit, preferredUnit);
		} else {
			writingOutputInSpreedSheetAndTextAreaForEstimation(parameter,
					initial, AInv, SD, defaultUnit, preferredUnit);
		}

	}

	private void writingOutputInSpreedSheetAndTextAreaForEstimation(
			double[] parameter, double[] initial, double[][] AInv, double SD,
			String[] defaultUnit, String[] preferredUnit) {
		DefaultToPreferredUnitConverter unitConversionInst = new DefaultToPreferredUnitConverter();
		PkPdInfo pkpdInstance = PkPdInfo.createPKPDInstance();
		/*
		 * CaParametersCalculation pkpdMainInst = CaParametersCalculation
		 * .createPKPDMainInstance();
		 */
		double[] SE = new double[noOfParam];
		double[] CV = new double[noOfParam];
		int count = 0;
		String[] strForHorizontalParam = new String[numberOfSortVariable + 3
				* noOfParam];

		for (int k = 0; k < numberOfSortVariable; k++) {
			strForHorizontalParam[k] = dataSortVariables[pkpdInstance
					.getCurrentProfileNumber()][k];
			count++;
		}

		for (int i = 0; i < noOfParam; i++) {

			double convertAmount = unitConversionInst.unitConversion(
					defaultUnit[i], preferredUnit[i]);
			SE[i] = SD * Math.sqrt(AInv[i][i]);
			CV[i] = Math.abs(SE[i] / parameter[i]) * 100;

			String[] s = new String[numberOfSortVariable + 5];
			for (int k = 0; k < numberOfSortVariable; k++) {
				s[k] = dataSortVariables[pkpdInstance.getCurrentProfileNumber()][k];
			}

			s[numberOfSortVariable] = paramName[i];

			s[numberOfSortVariable + 1] = preferredUnit[i];
			s[numberOfSortVariable + 2] = pkpdInstance.formatting(parameter[i]
					* convertAmount, false)
					+ "";
			s[numberOfSortVariable + 3] = pkpdInstance.formatting(SE[i],
					false)
					+ "";
			s[numberOfSortVariable + 4] = pkpdInstance.formatting(CV[i],
					false)
					+ "";

			strForHorizontalParam[count++] = pkpdInstance.formatting(
					parameter[i] * convertAmount, false)
					+ "";

			strForHorizontalParam[count++] = pkpdInstance.formatting(SE[i],
					false)
					+ "";

			strForHorizontalParam[count++] = pkpdInstance.formatting(CV[i],
					false)
					+ "";

			pkpdInstance.verticalParametersAL.add(s);

			// printing for final parameter value
			pkpdInstance.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(paramName[i], 10)
							+ "\t"
							+ StringUtils.rightPad(preferredUnit[i], 15)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									parameter[i] * convertAmount,
									false), 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									SE[i], false), 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									CV[i], false), 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									parameter[i]
											* convertAmount
											- tValue[pkpdInstance
													.getDegreesOfFreedom() - 1]
											* SE[i], false), 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									parameter[i]
											* convertAmount
											+ tValue[pkpdInstance
													.getDegreesOfFreedom() - 1]
											* SE[i], false), 10)
							+ "\r\n");

		}

		pkpdInstance.horizontalParametersAL.add(strForHorizontalParam);

	}

	private void writingOutputInSpreedSheetAndTextAreaForSimulation(
			double[] parameter, double[][] aInv, String[] defaultUnit,
			String[] preferredUnit) {

		DefaultToPreferredUnitConverter unitConversionInst = new DefaultToPreferredUnitConverter();
		PkPdInfo pkpdInstance = PkPdInfo.createPKPDInstance();

		SimulationForLibraryModels simulationInst = SimulationForLibraryModels
				.createSimulationInst();

		for (int i = 0; i < noOfParam; i++) {

			double convertAmount = unitConversionInst.unitConversion(
					defaultUnit[i], preferredUnit[i]);

			// printing for final parameter value
			simulationInst.wsOutput.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(paramName[i], 10)
							+ "\t"
							+ StringUtils.rightPad(preferredUnit[i], 15)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									parameter[i] * convertAmount,
									false), 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									aInv[i][i],false), 10)

							+ "\r\n");
			
			
			
			String[] s = new String[numberOfSortVariable + 5];
			for (int k = 0; k < numberOfSortVariable; k++) {
				s[k] = simulationInst.pkpdInstance.dataSortVariables[simulationInst.pkpdInstance.getCurrentProfileNumber()][k];
			}

			s[numberOfSortVariable] = paramName[i];

			s[numberOfSortVariable + 1] = preferredUnit[i];
			s[numberOfSortVariable + 2] = pkpdInstance.formatting(parameter[i]
					* convertAmount, false)
					+ "";
						

			simulationInst.verticalParametersAL.add(s);

		}

	}

}
