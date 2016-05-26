package Model;

import java.io.IOException;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ClearanceSecondaryParamComputer {
	PkPdInfo pkpdInstance;
	PkModelSecondaryParamComputer pkModelSecondaryParamCalInst;
	ApplicationInfo appInfo;
	int noOfSecParam;
	double[] secondaryParameterValue;
	ProcessingInputsInfo procInputInst;

	public ClearanceSecondaryParamComputer() {

	}

	public void secondaryParamCalForClearanceParam(double dose,
			int modelNumber, double lengthOfInfusion, double[] param)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		pkpdInstance = PkPdInfo.createPKPDInstance();
		pkModelSecondaryParamCalInst = PkModelSecondaryParamComputer
				.createPkSecParamCalInstance();
		appInfo = PkParamEstimator.createPkParamEstimateInstance().appInfoinst;
		procInputInst = pkpdInstance.copyProcessingInput();
		
		pkModelSecondaryParamCalInst.allDefaultUnit = procInputInst.getParameterUnitsDataObj()
				.getDefaultUnitArray();
		pkModelSecondaryParamCalInst.allPreferredUnit = procInputInst.getParameterUnitsDataObj()
				.getPreferredUnitsArray();
		noOfSecParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfSecondaryParameters();

		pkModelSecondaryParamCalInst.defaultUnit = new String[noOfSecParam];
		pkModelSecondaryParamCalInst.preferredUnit = new String[noOfSecParam];

		secondaryParameterValue = new double[pkModelSecondaryParamCalInst.noOfSecParam];

		if (modelNumber == 1) {
			spCalculationForModel1(dose, param);

		} else if (modelNumber == 2) {
			spCalculationForModel2(dose, lengthOfInfusion, param);
		} else if (modelNumber == 3) {
			spCalculationForModel3(dose, param);
		} else if (modelNumber == 4) {
			spCalculationForModel4(dose, param);
		} else if (modelNumber == 5) {
			spCalculationForModel5(dose, param);
		} else if (modelNumber == 6) {
			spCalculationForModel6(dose, param);
		} else if (modelNumber == 7) {
			spCalculationForModel7(dose, param);
		} else if (modelNumber == 9) {
			spCalculationForModel9(dose, lengthOfInfusion, param);
		} else if (modelNumber == 11) {
			spCalculationForModel11(dose, param);

		} else if (modelNumber == 12) {
			spCalculationForModel12(dose, param);
		} else if (modelNumber == 15) {
			spCalculationForModel15(dose, param);
		} else if (modelNumber == 16) {
			spCalculationForModel16(dose, param);
		}

		
	}
	
	
	public double[] chooseLibModel(double dose,
			int modelNumber, double lengthOfInfusion, double[] param)
	throws RowsExceededException, WriteException, BiffException,
	IOException 
	{
		pkpdInstance = PkPdInfo.createPKPDInstance();
		pkModelSecondaryParamCalInst = PkModelSecondaryParamComputer.createPkSecParamCalInstance();
		secondaryParameterValue = new double[pkModelSecondaryParamCalInst.noOfSecParam];
		if (modelNumber == 1) {
			calculateSPForModel1(dose, param);

		} else if (modelNumber == 2) {
			calculateSPForModel2(dose, lengthOfInfusion, param);
		} else if (modelNumber == 3) {
			calculateSPForModel3(dose, param);
		} else if (modelNumber == 4) {
			calculateSPForModel4(dose, param);
		} else if (modelNumber == 5) {
			calculateSPForModel5(dose, param);
		} else if (modelNumber == 6) {
			calculateSPForModel6(dose, param);
		} else if (modelNumber == 7) {
			calculateSPForModel7(dose, param);
		} else if (modelNumber == 9) {
			calculateSPForModel9(dose, lengthOfInfusion, param);
		} else if (modelNumber == 11) {
			calculateSPForModel11(dose, param);

		} else if (modelNumber == 12) {
			calculateSPForModel12(dose, param);
		} else if (modelNumber == 15) {
			calculateSPForModel15(dose, param);
		} else if (modelNumber == 16) {
			calculateSPForModel16(dose, param);
		}

		return secondaryParameterValue;
	}
	

	private void spCalculationForModel16(double dose, double[] param)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel16();
		calculateSPForModel16(dose, param);
		pkModelSecondaryParamCalInst.writingOutputInSpreedSheetAndTextArea(
				pkModelSecondaryParamCalInst.defaultUnit,
				pkModelSecondaryParamCalInst.preferredUnit);

	}

	private void calculateSPForModel16(double dose, double[] param) {

		secondaryParameterValue[7] = param[1] / param[0];

		secondaryParameterValue[8] = param[3] / param[0];

		secondaryParameterValue[9] = param[0] * secondaryParameterValue[8] / param[2];
		
		secondaryParameterValue[0] = Math.log(2) / secondaryParameterValue[7];

		secondaryParameterValue[2] = (1 / 2.0)
				* (secondaryParameterValue[7] + secondaryParameterValue[8] 
				 + secondaryParameterValue[9] - Math.sqrt(Math.pow(
				(secondaryParameterValue[7] + secondaryParameterValue[8] 
				+ secondaryParameterValue[9]), 2)
				- 4 * secondaryParameterValue[9] * secondaryParameterValue[7]));

		secondaryParameterValue[1] = secondaryParameterValue[7] * secondaryParameterValue[9]
				/ secondaryParameterValue[2];

		secondaryParameterValue[3] = Math.log(2) / secondaryParameterValue[1];
		secondaryParameterValue[4] = Math.log(2) / secondaryParameterValue[2];

		secondaryParameterValue[5] = (dose / param[0])
				* (secondaryParameterValue[1] - secondaryParameterValue[9])
				/ (secondaryParameterValue[1] - secondaryParameterValue[2]);

		secondaryParameterValue[6] = (dose / param[0])
				* (secondaryParameterValue[2] - secondaryParameterValue[9])
				/ (secondaryParameterValue[2] - secondaryParameterValue[1]);

		

	}

	private void copyDefaultAndPreferredUnitForModel16() {

		pkModelSecondaryParamCalInst.defaultUnit[0] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[0] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[1] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[1] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[2] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[2] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[3] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[3] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[4] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[4] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[5] = pkModelSecondaryParamCalInst.allDefaultUnit[1];
		pkModelSecondaryParamCalInst.preferredUnit[5] = pkModelSecondaryParamCalInst.allPreferredUnit[1];

		pkModelSecondaryParamCalInst.defaultUnit[6] = pkModelSecondaryParamCalInst.allDefaultUnit[1];
		pkModelSecondaryParamCalInst.preferredUnit[6] = pkModelSecondaryParamCalInst.allPreferredUnit[1];

		pkModelSecondaryParamCalInst.defaultUnit[7] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[7] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[8] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[8] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[9] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[9] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

	}

	private void spCalculationForModel15(double dose, double[] param)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel15();
		calculateSPForModel15(dose, param);
		pkModelSecondaryParamCalInst.writingOutputInSpreedSheetAndTextArea(
				pkModelSecondaryParamCalInst.defaultUnit,
				pkModelSecondaryParamCalInst.preferredUnit);

	}

	private void calculateSPForModel15(double dose, double[] param) {

		secondaryParameterValue[0] = param[1] / param[0];

		secondaryParameterValue[1] = Math.log(2) / secondaryParameterValue[0];

	}

	private void copyDefaultAndPreferredUnitForModel15() {

		pkModelSecondaryParamCalInst.defaultUnit[0] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[0] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[1] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[1] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

	}

	private void spCalculationForModel1(double dose, double[] param)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel1();
		calculateSPForModel1(dose, param);
		pkModelSecondaryParamCalInst.secondaryParameterValue = secondaryParameterValue;
		pkModelSecondaryParamCalInst.writingOutputInSpreedSheetAndTextArea(
				pkModelSecondaryParamCalInst.defaultUnit,
				pkModelSecondaryParamCalInst.preferredUnit);
	}

	private void calculateSPForModel1(double dose, double[] param) {

		secondaryParameterValue[3] = param[1] /  param[0];
		secondaryParameterValue[0] = dose / (param[0] * secondaryParameterValue[3] );

		secondaryParameterValue[1] = Math.log(2) / secondaryParameterValue[3] ;
		secondaryParameterValue[2] = dose / param[0];
		
		secondaryParameterValue[4] = dose
				/ (param[0] * secondaryParameterValue[3] * secondaryParameterValue[3] );
		secondaryParameterValue[5] = secondaryParameterValue[4]
				/ secondaryParameterValue[0];
		secondaryParameterValue[6] = secondaryParameterValue[5]
				* secondaryParameterValue[3];

	}

	private void copyDefaultAndPreferredUnitForModel1() {

		pkModelSecondaryParamCalInst.defaultUnit[0] = pkModelSecondaryParamCalInst.allDefaultUnit[4];
		pkModelSecondaryParamCalInst.preferredUnit[0] = pkModelSecondaryParamCalInst.allPreferredUnit[4];

		pkModelSecondaryParamCalInst.defaultUnit[1] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[1] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[2] = pkModelSecondaryParamCalInst.allDefaultUnit[8];
		pkModelSecondaryParamCalInst.preferredUnit[2] = pkModelSecondaryParamCalInst.allPreferredUnit[8];

		pkModelSecondaryParamCalInst.defaultUnit[3] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[3] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[4] = pkModelSecondaryParamCalInst.allDefaultUnit[5];
		pkModelSecondaryParamCalInst.preferredUnit[4] = pkModelSecondaryParamCalInst.allPreferredUnit[5];

		pkModelSecondaryParamCalInst.defaultUnit[5] = pkModelSecondaryParamCalInst.allDefaultUnit[9];
		pkModelSecondaryParamCalInst.preferredUnit[5] = pkModelSecondaryParamCalInst.allPreferredUnit[9];

		pkModelSecondaryParamCalInst.defaultUnit[6] = pkModelSecondaryParamCalInst.allDefaultUnit[2];
		pkModelSecondaryParamCalInst.preferredUnit[6] = pkModelSecondaryParamCalInst.allPreferredUnit[2];

	}

	private void spCalculationForModel2(double dose, double lengthOfInfusion,
			double[] param) throws RowsExceededException, WriteException,
			BiffException, IOException {
		copyDefaultAndPreferredUnitForModel2();
		calculateSPForModel2(dose, lengthOfInfusion, param);
		pkModelSecondaryParamCalInst.secondaryParameterValue = secondaryParameterValue;
		pkModelSecondaryParamCalInst.writingOutputInSpreedSheetAndTextArea(
				pkModelSecondaryParamCalInst.defaultUnit,
				pkModelSecondaryParamCalInst.preferredUnit);

	}

	private void calculateSPForModel2(double dose, double lengthOfInfusion,
			double[] param) {

		secondaryParameterValue[3] =  param[1]/ param[0];
	
		secondaryParameterValue[0] = dose / (param[0] * secondaryParameterValue[3]);

		secondaryParameterValue[1] = Math.log(2) / secondaryParameterValue[3];
		
		double coef = dose
				/ (lengthOfInfusion * param[0] * secondaryParameterValue[3]);
		secondaryParameterValue[2] = coef
				* (1 - Math.exp((-secondaryParameterValue[3]) * lengthOfInfusion));

		

		double aumcIv = dose
				/ (param[0] * secondaryParameterValue[3] * secondaryParameterValue[3]);

		secondaryParameterValue[4] = aumcIv + lengthOfInfusion
				* secondaryParameterValue[0] / 2;
		secondaryParameterValue[5] = secondaryParameterValue[4]
				/ secondaryParameterValue[0] - lengthOfInfusion / 2;
		secondaryParameterValue[6] = secondaryParameterValue[5]
				* param[1];

	}

	private void copyDefaultAndPreferredUnitForModel2() {

		pkModelSecondaryParamCalInst.defaultUnit[0] = pkModelSecondaryParamCalInst.allDefaultUnit[4];
		pkModelSecondaryParamCalInst.preferredUnit[0] = pkModelSecondaryParamCalInst.allPreferredUnit[4];

		pkModelSecondaryParamCalInst.defaultUnit[1] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[1] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[2] = pkModelSecondaryParamCalInst.allDefaultUnit[8];
		pkModelSecondaryParamCalInst.preferredUnit[2] = pkModelSecondaryParamCalInst.allPreferredUnit[8];

		pkModelSecondaryParamCalInst.defaultUnit[3] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[3] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[4] = pkModelSecondaryParamCalInst.allDefaultUnit[5];
		pkModelSecondaryParamCalInst.preferredUnit[4] = pkModelSecondaryParamCalInst.allPreferredUnit[5];

		pkModelSecondaryParamCalInst.defaultUnit[5] = pkModelSecondaryParamCalInst.allDefaultUnit[9];
		pkModelSecondaryParamCalInst.preferredUnit[5] = pkModelSecondaryParamCalInst.allPreferredUnit[9];

		pkModelSecondaryParamCalInst.defaultUnit[6] = pkModelSecondaryParamCalInst.allDefaultUnit[2];
		pkModelSecondaryParamCalInst.preferredUnit[6] = pkModelSecondaryParamCalInst.allPreferredUnit[2];
	}

	private void spCalculationForModel3(double dose, double[] param)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel3();
		calculateSPForModel3(dose, param);
		pkModelSecondaryParamCalInst.secondaryParameterValue = secondaryParameterValue;
		pkModelSecondaryParamCalInst.writingOutputInSpreedSheetAndTextArea(
				pkModelSecondaryParamCalInst.defaultUnit,
				pkModelSecondaryParamCalInst.preferredUnit);

	}

	private void calculateSPForModel3(double dose, double[] param) {

		secondaryParameterValue[3] = param[2]/ param[0];
		secondaryParameterValue[0] = dose / (param[0] * secondaryParameterValue[3]);

		secondaryParameterValue[1] = Math.log(2) / param[1];
		secondaryParameterValue[2] = Math.log(2) / secondaryParameterValue[3];

		secondaryParameterValue[4] = Math.log(param[1] / secondaryParameterValue[3])
				/ (param[1] - secondaryParameterValue[3]);

		secondaryParameterValue[5] = dose
				* Math.exp((-secondaryParameterValue[3] * secondaryParameterValue[4])) / param[0];

	}

	private void copyDefaultAndPreferredUnitForModel3() {

		pkModelSecondaryParamCalInst.defaultUnit[0] = pkModelSecondaryParamCalInst.allDefaultUnit[4];
		pkModelSecondaryParamCalInst.preferredUnit[0] = pkModelSecondaryParamCalInst.allPreferredUnit[4];

		pkModelSecondaryParamCalInst.defaultUnit[1] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[1] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[2] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[2] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[3] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[3] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[4] = pkModelSecondaryParamCalInst.allDefaultUnit[7];
		pkModelSecondaryParamCalInst.preferredUnit[4] = pkModelSecondaryParamCalInst.allPreferredUnit[7];

		pkModelSecondaryParamCalInst.defaultUnit[5] = pkModelSecondaryParamCalInst.allDefaultUnit[8];
		pkModelSecondaryParamCalInst.preferredUnit[5] = pkModelSecondaryParamCalInst.allPreferredUnit[8];

	}

	private void spCalculationForModel4(double dose, double[] param)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel4();
		calculateSPForModel4(dose, param);
		pkModelSecondaryParamCalInst.secondaryParameterValue = secondaryParameterValue;
		pkModelSecondaryParamCalInst.writingOutputInSpreedSheetAndTextArea(
				pkModelSecondaryParamCalInst.defaultUnit,
				pkModelSecondaryParamCalInst.preferredUnit);

	}

	private void calculateSPForModel4(double dose, double[] param) {

		secondaryParameterValue[3] = param[2] / param[0];
		secondaryParameterValue[0] = dose / (param[0] * secondaryParameterValue[3]);

		secondaryParameterValue[1] = Math.log(2) / param[1];
		secondaryParameterValue[2] = Math.log(2) / secondaryParameterValue[3];
		
		secondaryParameterValue[4] = Math.log(param[1] / secondaryParameterValue[3])
				/ (param[1] - secondaryParameterValue[3]) + param[3];

		secondaryParameterValue[5] = dose
				* Math
						.exp((-secondaryParameterValue[3] * (secondaryParameterValue[4] - param[3]))) / param[0];

	}

	private void copyDefaultAndPreferredUnitForModel4() {

		pkModelSecondaryParamCalInst.defaultUnit[0] = pkModelSecondaryParamCalInst.allDefaultUnit[4];
		pkModelSecondaryParamCalInst.preferredUnit[0] = pkModelSecondaryParamCalInst.allPreferredUnit[4];

		pkModelSecondaryParamCalInst.defaultUnit[1] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[1] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[2] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[2] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[3] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[3] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[4] = pkModelSecondaryParamCalInst.allDefaultUnit[7];
		pkModelSecondaryParamCalInst.preferredUnit[4] = pkModelSecondaryParamCalInst.allPreferredUnit[7];

		pkModelSecondaryParamCalInst.defaultUnit[5] = pkModelSecondaryParamCalInst.allDefaultUnit[8];
		pkModelSecondaryParamCalInst.preferredUnit[5] = pkModelSecondaryParamCalInst.allPreferredUnit[8];

	}

	private void spCalculationForModel5(double dose, double[] param)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel5();
		calculateSPForModel5(dose, param);
		pkModelSecondaryParamCalInst.secondaryParameterValue = secondaryParameterValue;
		pkModelSecondaryParamCalInst.writingOutputInSpreedSheetAndTextArea(
				pkModelSecondaryParamCalInst.defaultUnit,
				pkModelSecondaryParamCalInst.preferredUnit);

	}

	private void calculateSPForModel5(double dose, double[] param) {

		secondaryParameterValue[2] = param[1] / param[0];
		secondaryParameterValue[0] = dose / (param[0] * secondaryParameterValue[2]);
		secondaryParameterValue[1] = Math.log(2) / secondaryParameterValue[2];
		
		secondaryParameterValue[3] = 1 / secondaryParameterValue[2];
		secondaryParameterValue[4] = Math.exp(-1) * (dose / param[0]);

	}

	private void copyDefaultAndPreferredUnitForModel5() {

		pkModelSecondaryParamCalInst.defaultUnit[0] = pkModelSecondaryParamCalInst.allDefaultUnit[4];
		pkModelSecondaryParamCalInst.preferredUnit[0] = pkModelSecondaryParamCalInst.allPreferredUnit[4];

		pkModelSecondaryParamCalInst.defaultUnit[1] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[1] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[2] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[2] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[3] = pkModelSecondaryParamCalInst.allDefaultUnit[7];
		pkModelSecondaryParamCalInst.preferredUnit[3] = pkModelSecondaryParamCalInst.allPreferredUnit[7];

		pkModelSecondaryParamCalInst.defaultUnit[4] = pkModelSecondaryParamCalInst.allDefaultUnit[8];
		pkModelSecondaryParamCalInst.preferredUnit[4] = pkModelSecondaryParamCalInst.allPreferredUnit[8];

	}

	private void spCalculationForModel6(double dose, double[] param)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel6();
		calculateSPForModel6(dose, param);
		pkModelSecondaryParamCalInst.secondaryParameterValue = secondaryParameterValue;
		pkModelSecondaryParamCalInst.writingOutputInSpreedSheetAndTextArea(
				pkModelSecondaryParamCalInst.defaultUnit,
				pkModelSecondaryParamCalInst.preferredUnit);

	}

	private void calculateSPForModel6(double dose, double[] param) {

		secondaryParameterValue[2] = param[1] / param[0];
		secondaryParameterValue[0] = dose / (param[0] * secondaryParameterValue[2]);
		secondaryParameterValue[1] = Math.log(2) / secondaryParameterValue[2];
		secondaryParameterValue[3] = 1 / secondaryParameterValue[2] + param[2];
		secondaryParameterValue[4] = Math.exp(-1) * (dose / param[0]);

	}

	private void copyDefaultAndPreferredUnitForModel6() {

		pkModelSecondaryParamCalInst.defaultUnit[0] = pkModelSecondaryParamCalInst.allDefaultUnit[4];
		pkModelSecondaryParamCalInst.preferredUnit[0] = pkModelSecondaryParamCalInst.allPreferredUnit[4];

		pkModelSecondaryParamCalInst.defaultUnit[1] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[1] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[2] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[2] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[3] = pkModelSecondaryParamCalInst.allDefaultUnit[7];
		pkModelSecondaryParamCalInst.preferredUnit[3] = pkModelSecondaryParamCalInst.allPreferredUnit[7];

		pkModelSecondaryParamCalInst.defaultUnit[4] = pkModelSecondaryParamCalInst.allDefaultUnit[8];
		pkModelSecondaryParamCalInst.preferredUnit[4] = pkModelSecondaryParamCalInst.allPreferredUnit[8];

	}

	private void spCalculationForModel7(double dose, double[] param)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel7();
		calculateSPForModel7(dose, param);
		pkModelSecondaryParamCalInst.secondaryParameterValue = secondaryParameterValue;
		pkModelSecondaryParamCalInst.writingOutputInSpreedSheetAndTextArea(
				pkModelSecondaryParamCalInst.defaultUnit,
				pkModelSecondaryParamCalInst.preferredUnit);

	}

	private void calculateSPForModel7(double dose, double[] param) {

		secondaryParameterValue[9] = param[1] / param[0];
		secondaryParameterValue[13] = param[3] / param[0];
		secondaryParameterValue[14] = param[0] * secondaryParameterValue[13]/param[2];
		
		
		secondaryParameterValue[1] = Math.log(2) / secondaryParameterValue[9];

		double temp = Math.pow((secondaryParameterValue[9] + secondaryParameterValue[13]
		                             + secondaryParameterValue[14]), 2);
		double temp1 = Math.sqrt(Math.abs(temp - 4 * secondaryParameterValue[9] * secondaryParameterValue[14]));

		double temp2 = secondaryParameterValue[9] + secondaryParameterValue[13] 
		                    + secondaryParameterValue[14] - temp1;

		secondaryParameterValue[3] = (1 / 2.0) * (temp2);
		secondaryParameterValue[2] = secondaryParameterValue[9] * secondaryParameterValue[14]
				/ secondaryParameterValue[3];

		double a = (dose / param[0]) * (secondaryParameterValue[2] - secondaryParameterValue[14])
				/ (secondaryParameterValue[2] - secondaryParameterValue[3]);

		double b = (-1) * (dose / param[0])
				* (secondaryParameterValue[3] - secondaryParameterValue[14])
				/ (secondaryParameterValue[2] - secondaryParameterValue[3]);

		secondaryParameterValue[0] = (a / secondaryParameterValue[2])
				+ (b / secondaryParameterValue[3]);

		secondaryParameterValue[4] = Math.log(2) / secondaryParameterValue[2];
		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		secondaryParameterValue[6] = a;
		secondaryParameterValue[7] = b;

		secondaryParameterValue[8] = dose / param[0];
		

		secondaryParameterValue[10] = secondaryParameterValue[6]
				/ Math.pow(secondaryParameterValue[2], 2)
				+ secondaryParameterValue[7]
				/ Math.pow(secondaryParameterValue[3], 2);
		secondaryParameterValue[11] = secondaryParameterValue[10]
				/ secondaryParameterValue[0];

		secondaryParameterValue[12] = secondaryParameterValue[9]
				* secondaryParameterValue[11];

		

	}

	private void copyDefaultAndPreferredUnitForModel7() {

		pkModelSecondaryParamCalInst.defaultUnit[0] = pkModelSecondaryParamCalInst.allDefaultUnit[4];
		pkModelSecondaryParamCalInst.preferredUnit[0] = pkModelSecondaryParamCalInst.allPreferredUnit[4];

		pkModelSecondaryParamCalInst.defaultUnit[1] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[1] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[2] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[2] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[3] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[3] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[4] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[4] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[5] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[5] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[6] = pkModelSecondaryParamCalInst.allDefaultUnit[1];
		pkModelSecondaryParamCalInst.preferredUnit[6] = pkModelSecondaryParamCalInst.allPreferredUnit[1];

		pkModelSecondaryParamCalInst.defaultUnit[7] = pkModelSecondaryParamCalInst.allDefaultUnit[1];
		pkModelSecondaryParamCalInst.preferredUnit[7] = pkModelSecondaryParamCalInst.allPreferredUnit[1];

		pkModelSecondaryParamCalInst.defaultUnit[8] = pkModelSecondaryParamCalInst.allDefaultUnit[8];
		pkModelSecondaryParamCalInst.preferredUnit[8] = pkModelSecondaryParamCalInst.allPreferredUnit[8];

		pkModelSecondaryParamCalInst.defaultUnit[9] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[9] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[10] = pkModelSecondaryParamCalInst.allDefaultUnit[5];
		pkModelSecondaryParamCalInst.preferredUnit[10] = pkModelSecondaryParamCalInst.allPreferredUnit[5];

		pkModelSecondaryParamCalInst.defaultUnit[11] = pkModelSecondaryParamCalInst.allDefaultUnit[9];
		pkModelSecondaryParamCalInst.preferredUnit[11] = pkModelSecondaryParamCalInst.allPreferredUnit[9];

		pkModelSecondaryParamCalInst.defaultUnit[12] = pkModelSecondaryParamCalInst.allDefaultUnit[2];
		pkModelSecondaryParamCalInst.preferredUnit[12] = pkModelSecondaryParamCalInst.allPreferredUnit[2];

		pkModelSecondaryParamCalInst.defaultUnit[13] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[13] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[14] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[14] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

	}

	private void spCalculationForModel9(double dose, double lengthOfInfusion,
			double[] param) throws RowsExceededException, WriteException,
			BiffException, IOException {
		copyDefaultAndPreferredUnitForModel9();
		calculateSPForModel9(dose, lengthOfInfusion, param);
		pkModelSecondaryParamCalInst.secondaryParameterValue = secondaryParameterValue;
		pkModelSecondaryParamCalInst.writingOutputInSpreedSheetAndTextArea(
				pkModelSecondaryParamCalInst.defaultUnit,
				pkModelSecondaryParamCalInst.preferredUnit);

	}

	private void calculateSPForModel9(double dose, double lengthOfInfusion,
			double[] param) {

		
		secondaryParameterValue[9] = param[1] / param[0];
		secondaryParameterValue[13] = param[3] / param[0];
		secondaryParameterValue[14] = param[0] * secondaryParameterValue[13] / param[2];

		
		
		secondaryParameterValue[0] = dose / (param[0] * secondaryParameterValue[9]);

		secondaryParameterValue[1] = Math.log(2) / secondaryParameterValue[9];

		secondaryParameterValue[3] = (1 / 2.0)
				* (secondaryParameterValue[9] + secondaryParameterValue[13] 
				 + secondaryParameterValue[14] - Math.sqrt(Math.pow(
				(secondaryParameterValue[9] + secondaryParameterValue[13] 
				 + secondaryParameterValue[14]),2)

						- 4 * secondaryParameterValue[9] * secondaryParameterValue[14]));

		secondaryParameterValue[2] = (secondaryParameterValue[9] * secondaryParameterValue[14])
				/ secondaryParameterValue[3];

		secondaryParameterValue[4] = Math.log(2) / secondaryParameterValue[2];

		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		double a1 = (dose / lengthOfInfusion / param[0])
				* (secondaryParameterValue[14] - secondaryParameterValue[2])
				/ (secondaryParameterValue[2] - secondaryParameterValue[3])
				/ secondaryParameterValue[2];

		double b1 = (-1) * (dose / lengthOfInfusion / param[0])
				* (secondaryParameterValue[14] - secondaryParameterValue[3])
				/ (secondaryParameterValue[2] - secondaryParameterValue[3])
				/ secondaryParameterValue[3];

		secondaryParameterValue[6] = -1.0 * a1 * lengthOfInfusion * secondaryParameterValue[2];

		secondaryParameterValue[7] = -1.0 * b1 * lengthOfInfusion * secondaryParameterValue[3];
		secondaryParameterValue[8] = a1
				* (Math.exp((-secondaryParameterValue[2]) * lengthOfInfusion) - 1.0)
				+ b1
				* (Math.exp((-secondaryParameterValue[3]) * lengthOfInfusion) - 1.0);

		

		double a = (-1.0) * a1 * lengthOfInfusion * secondaryParameterValue[2];

		double b = (-1.0) * b1 * lengthOfInfusion * secondaryParameterValue[3];

		double aumcb = (a
				/ (secondaryParameterValue[2] * secondaryParameterValue[2]) + b
				/ (secondaryParameterValue[3] * secondaryParameterValue[3]));

		secondaryParameterValue[10] = aumcb + lengthOfInfusion
				* secondaryParameterValue[0] / 2.0;

		secondaryParameterValue[11] = secondaryParameterValue[10]
				/ secondaryParameterValue[0] - lengthOfInfusion / 2;

		secondaryParameterValue[12] = dose
				* ((secondaryParameterValue[10] / secondaryParameterValue[0]) - lengthOfInfusion / 2)
				/ secondaryParameterValue[0];

	}

	private void copyDefaultAndPreferredUnitForModel9() {

		pkModelSecondaryParamCalInst.defaultUnit[0] = pkModelSecondaryParamCalInst.allDefaultUnit[4];
		pkModelSecondaryParamCalInst.preferredUnit[0] = pkModelSecondaryParamCalInst.allPreferredUnit[4];

		pkModelSecondaryParamCalInst.defaultUnit[1] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[1] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[2] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[2] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[3] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[3] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[4] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[4] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[5] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[5] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[6] = pkModelSecondaryParamCalInst.allDefaultUnit[1];
		pkModelSecondaryParamCalInst.preferredUnit[6] = pkModelSecondaryParamCalInst.allPreferredUnit[1];

		pkModelSecondaryParamCalInst.defaultUnit[7] = pkModelSecondaryParamCalInst.allDefaultUnit[1];
		pkModelSecondaryParamCalInst.preferredUnit[7] = pkModelSecondaryParamCalInst.allPreferredUnit[1];

		pkModelSecondaryParamCalInst.defaultUnit[8] = pkModelSecondaryParamCalInst.allDefaultUnit[8];
		pkModelSecondaryParamCalInst.preferredUnit[8] = pkModelSecondaryParamCalInst.allPreferredUnit[8];

		pkModelSecondaryParamCalInst.defaultUnit[9] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[9] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[10] = pkModelSecondaryParamCalInst.allDefaultUnit[5];
		pkModelSecondaryParamCalInst.preferredUnit[10] = pkModelSecondaryParamCalInst.allPreferredUnit[5];

		pkModelSecondaryParamCalInst.defaultUnit[11] = pkModelSecondaryParamCalInst.allDefaultUnit[9];
		pkModelSecondaryParamCalInst.preferredUnit[11] = pkModelSecondaryParamCalInst.allPreferredUnit[9];

		pkModelSecondaryParamCalInst.defaultUnit[12] = pkModelSecondaryParamCalInst.allDefaultUnit[2];
		pkModelSecondaryParamCalInst.preferredUnit[12] = pkModelSecondaryParamCalInst.allPreferredUnit[2];

		pkModelSecondaryParamCalInst.defaultUnit[13] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[13] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[14] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[14] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

	}

	private void spCalculationForModel11(double dose, double[] param)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel11();
		calculateSPForModel11(dose, param);
		pkModelSecondaryParamCalInst.secondaryParameterValue = secondaryParameterValue;
		pkModelSecondaryParamCalInst.writingOutputInSpreedSheetAndTextArea(
				pkModelSecondaryParamCalInst.defaultUnit,
				pkModelSecondaryParamCalInst.preferredUnit);

	}

	private void calculateSPForModel11(double dose, double[] param) {

		
		secondaryParameterValue[9] = param[2] / param[0];
		secondaryParameterValue[10] = param[4] / param[0];
		secondaryParameterValue[11] = param[0] * secondaryParameterValue[10] / param[3];

				
		secondaryParameterValue[0] = dose / (param[0] * secondaryParameterValue[9]);

		secondaryParameterValue[1] = Math.log(2) / param[1];

		secondaryParameterValue[2] = Math.log(2) / secondaryParameterValue[9];

		secondaryParameterValue[4] = (1 / 2.0)
				* (secondaryParameterValue[9] + secondaryParameterValue[10] 
				 + secondaryParameterValue[11] - Math.sqrt(Math.pow(
				(secondaryParameterValue[9] + secondaryParameterValue[10]
				 + secondaryParameterValue[11]), 2)

				- 4 * secondaryParameterValue[9] * secondaryParameterValue[11]));

		secondaryParameterValue[3] = secondaryParameterValue[9] * 
		secondaryParameterValue[11]	/ secondaryParameterValue[4];

		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		secondaryParameterValue[6] = Math.log(2) / secondaryParameterValue[4];

		secondaryParameterValue[7] = (dose / param[0]) * param[1]
				* (secondaryParameterValue[11] - secondaryParameterValue[3])
				/ (secondaryParameterValue[3] - secondaryParameterValue[4])
				/ (secondaryParameterValue[3] - param[1]);

		secondaryParameterValue[8] = (-1) * (dose / param[0]) * param[1]
				* (secondaryParameterValue[11] - secondaryParameterValue[4])
				/ (secondaryParameterValue[3] - secondaryParameterValue[4])
				/ (secondaryParameterValue[4] - param[1]);

		
	}

	private void copyDefaultAndPreferredUnitForModel11() {

		pkModelSecondaryParamCalInst.defaultUnit[0] = pkModelSecondaryParamCalInst.allDefaultUnit[4];
		pkModelSecondaryParamCalInst.preferredUnit[0] = pkModelSecondaryParamCalInst.allPreferredUnit[4];

		pkModelSecondaryParamCalInst.defaultUnit[1] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[1] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[2] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[2] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[3] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[3] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[4] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[4] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[5] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[5] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[6] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[6] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[7] = pkModelSecondaryParamCalInst.allDefaultUnit[1];
		pkModelSecondaryParamCalInst.preferredUnit[7] = pkModelSecondaryParamCalInst.allPreferredUnit[1];

		pkModelSecondaryParamCalInst.defaultUnit[8] = pkModelSecondaryParamCalInst.allDefaultUnit[1];
		pkModelSecondaryParamCalInst.preferredUnit[8] = pkModelSecondaryParamCalInst.allPreferredUnit[1];

		pkModelSecondaryParamCalInst.defaultUnit[9] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[9] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[10] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[10] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[11] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[11] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[12] = pkModelSecondaryParamCalInst.allDefaultUnit[7];
		pkModelSecondaryParamCalInst.preferredUnit[12] = pkModelSecondaryParamCalInst.allPreferredUnit[7];

		pkModelSecondaryParamCalInst.defaultUnit[13] = pkModelSecondaryParamCalInst.allDefaultUnit[8];
		pkModelSecondaryParamCalInst.preferredUnit[13] = pkModelSecondaryParamCalInst.allPreferredUnit[8];
	}

	private void spCalculationForModel12(double dose, double[] param)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel12();
		calculateSPForModel12(dose, param);
		pkModelSecondaryParamCalInst.secondaryParameterValue = secondaryParameterValue;
		pkModelSecondaryParamCalInst.writingOutputInSpreedSheetAndTextArea(
				pkModelSecondaryParamCalInst.defaultUnit,
				pkModelSecondaryParamCalInst.preferredUnit);

	}

	private void calculateSPForModel12(double dose, double[] param) {

		
		secondaryParameterValue[9] = param[2] / param[0];
		secondaryParameterValue[10] = param[4] / param[0];
		secondaryParameterValue[11] = param[0] * secondaryParameterValue[10] / param[3];

		secondaryParameterValue[0] = dose / (param[0] * secondaryParameterValue[9]);

		secondaryParameterValue[1] = Math.log(2) / param[1];

		secondaryParameterValue[2] = Math.log(2) / secondaryParameterValue[9];

		secondaryParameterValue[4] = (1 / 2.0)
				* (secondaryParameterValue[9] + secondaryParameterValue[10] 
				 + secondaryParameterValue[11] - Math.sqrt(Math.pow(
				(secondaryParameterValue[9] + secondaryParameterValue[10] 
				 + secondaryParameterValue[11]), 2)
				- 4 * secondaryParameterValue[9] * secondaryParameterValue[11]));

		secondaryParameterValue[3] = secondaryParameterValue[9] * secondaryParameterValue[11]
				/ secondaryParameterValue[4];

		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		secondaryParameterValue[6] = Math.log(2) / secondaryParameterValue[4];

		secondaryParameterValue[7] = (dose / param[0]) * param[1]
				* (secondaryParameterValue[11] - secondaryParameterValue[3])
				/ (secondaryParameterValue[3] - secondaryParameterValue[4])
				/ (secondaryParameterValue[3] - param[1]);
		secondaryParameterValue[8] = (-1) * (dose / param[0]) * param[1]
				* (secondaryParameterValue[11] - secondaryParameterValue[4])
				/ (secondaryParameterValue[3] - secondaryParameterValue[4])
				/ (secondaryParameterValue[4] - param[1]);

	}

	private void copyDefaultAndPreferredUnitForModel12() {

		pkModelSecondaryParamCalInst.defaultUnit[0] = pkModelSecondaryParamCalInst.allDefaultUnit[4];
		pkModelSecondaryParamCalInst.preferredUnit[0] = pkModelSecondaryParamCalInst.allPreferredUnit[4];

		pkModelSecondaryParamCalInst.defaultUnit[1] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[1] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[2] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[2] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[3] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[3] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[4] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[4] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[5] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[5] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[6] = pkModelSecondaryParamCalInst.allDefaultUnit[6];
		pkModelSecondaryParamCalInst.preferredUnit[6] = pkModelSecondaryParamCalInst.allPreferredUnit[6];

		pkModelSecondaryParamCalInst.defaultUnit[7] = pkModelSecondaryParamCalInst.allDefaultUnit[1];
		pkModelSecondaryParamCalInst.preferredUnit[7] = pkModelSecondaryParamCalInst.allPreferredUnit[1];

		pkModelSecondaryParamCalInst.defaultUnit[8] = pkModelSecondaryParamCalInst.allDefaultUnit[1];
		pkModelSecondaryParamCalInst.preferredUnit[8] = pkModelSecondaryParamCalInst.allPreferredUnit[1];

		pkModelSecondaryParamCalInst.defaultUnit[9] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[9] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[10] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[10] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[11] = pkModelSecondaryParamCalInst.allDefaultUnit[0];
		pkModelSecondaryParamCalInst.preferredUnit[11] = pkModelSecondaryParamCalInst.allPreferredUnit[0];

		pkModelSecondaryParamCalInst.defaultUnit[12] = pkModelSecondaryParamCalInst.allDefaultUnit[7];
		pkModelSecondaryParamCalInst.preferredUnit[12] = pkModelSecondaryParamCalInst.allPreferredUnit[7];

		pkModelSecondaryParamCalInst.defaultUnit[13] = pkModelSecondaryParamCalInst.allDefaultUnit[8];
		pkModelSecondaryParamCalInst.preferredUnit[13] = pkModelSecondaryParamCalInst.allPreferredUnit[8];

	}

}
