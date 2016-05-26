package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;
import Jama.Matrix;

public class StandardErrorCalculationOfSp {
	


	double[] secondaryParameterValue;
	
	PkPdInfo pkpdInstance;

	int noOfSecParam;
	ApplicationInfo appInfo;
	
	
	double[] param;
	boolean clearancaParam;
	ProcessingInputsInfo procInputInst;

	
	public double[] chooseLibraryModel(
			double[] parameter) throws RowsExceededException, WriteException, BiffException, IOException{

		pkpdInstance = PkPdInfo.createPKPDInstance();
		PkParamEstimator pkpdMain = PkParamEstimator
				.createPkParamEstimateInstance();
		
		appInfo = PkParamEstimator.createPkParamEstimateInstance().appInfoinst;
		procInputInst = pkpdInstance.copyProcessingInput();

		noOfSecParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfSecondaryParameters();

		secondaryParameterValue = new double[noOfSecParam];
		param = parameter;
		clearancaParam = procInputInst.getModelInputTab1Obj()
				.isIfClearanceParam();
		
		int modelNumber = pkpdInstance.modelNumber;
		double dose = pkpdMain.dose[0];
		double lengthOfInfusion = pkpdMain.infusionTime[0]
		                                				- pkpdMain.dosingTime[0];
		
		
		if (clearancaParam == true) {
			ClearanceSecondaryParamComputer inst = new ClearanceSecondaryParamComputer();
			secondaryParameterValue = inst
					.chooseLibModel(dose, modelNumber,
							lengthOfInfusion, parameter);
		} else {

		if (modelNumber == 1) {
			calculateSPForModel1(dose);

		} else if (modelNumber == 2) {
			calculateSPForModel2(dose, lengthOfInfusion);
		} else if (modelNumber == 3) {
			calculateSPForModel3(dose);
		} else if (modelNumber == 4) {
			calculateSPForModel4(dose);
		} else if (modelNumber == 5) {
			calculateSPForModel5(dose);
		} else if (modelNumber == 6) {
			calculateSPForModel6(dose);
		} else if (modelNumber == 7) {
			calculateSPForModel7(dose);
		} else if (modelNumber == 8) {
			calculateSPForModel8(dose);
		} else if (modelNumber == 9) {
			calculateSPForModel9(dose, lengthOfInfusion);
		} else if (modelNumber == 10) {
			calculateSPForModel10(dose, lengthOfInfusion);
		} else if (modelNumber == 11) {
			calculateSPForModel11(dose);

		} else if (modelNumber == 12) {
			calculateSPForModel12(dose);
		} else if (modelNumber == 13) {
			calculateSPForModel13(dose);
		} else if (modelNumber == 14) {
			calculateSPForModel14(dose);
		} else if (modelNumber == 15) {
			calculateSPForModel15(dose);
		} else if (modelNumber == 16) {
			calculateSPForModel16(dose);
		} else if (modelNumber == 17) {
			calculateSPForModel17(dose);
		} else if (modelNumber == 18) {
			calculateSPForModel18(dose);
		} else if (modelNumber == 19) {
			calculateSPForModel19(dose, lengthOfInfusion);
		}
		}
		
		return secondaryParameterValue;

	}


	private void calculateSPForModel19(double dose, double lengthOfInfusion) {

		double ti = lengthOfInfusion;

		double k0 = dose / ti;
		double a1 = k0
				* (param[1] - param[3])
				* (param[2] - param[3]);
		a1 = a1
				/ (param[0]
						* param[3]
						* (param[5] - pkpdInstance
								.getParameter()[3]) * (pkpdInstance
						.getParameter()[4] - param[3]));
		double b1 = k0
				* (param[1] - param[4])
				* (param[2] - param[4]);
		b1 = b1
				/ (param[0]
						* param[4]
						* (param[5] - pkpdInstance
								.getParameter()[4]) * (pkpdInstance
						.getParameter()[3] - param[4]));
		double c1 = k0
				* (param[1] - param[5])
				* (param[2] - param[5]);
		c1 = c1
				/ (param[0]
						* param[5]
						* (param[3] - pkpdInstance
								.getParameter()[5]) * (pkpdInstance
						.getParameter()[4] - param[5]));

		secondaryParameterValue[0] = a1
				* (1.0 - Math.exp((-param[3]) * ti)) + b1
				* (1.0 - Math.exp((-param[4]) * ti)) + c1
				* (1.0 - Math.exp((-param[5]) * ti));

		secondaryParameterValue[1] = param[3]
				* param[4]
				* param[5]
				/ (param[1] * param[2]);

		double e1 = param[3]
				* param[4]
				+ param[3]
				* param[5]
				+ param[4]
				* param[5];
		double e2 = param[1]
				* (param[3]
						+ param[4] + pkpdInstance
						.getParameter()[5]);

		double e3 = e1 - e2 - secondaryParameterValue[1]
				* param[2]
				+ param[1]
				* param[1];

		secondaryParameterValue[2] = e3
				/ (param[2] - param[1]);

		secondaryParameterValue[3] = (param[3]
				+ param[4] + param[5])
				- (secondaryParameterValue[1] + secondaryParameterValue[2]
						+ param[1] + pkpdInstance
						.getParameter()[2]);

		secondaryParameterValue[4] = dose
				* (param[1] - param[3])
				* (param[3] - param[2])
				/ (param[0]
						* (param[5] - pkpdInstance
								.getParameter()[3]) * (pkpdInstance
						.getParameter()[3] - param[4]));
		secondaryParameterValue[5] = dose
				* (param[1] - param[4])
				* (param[2] - param[4])
				/ (param[0]
						* (param[5] - pkpdInstance
								.getParameter()[4]) * (pkpdInstance
						.getParameter()[3] - param[4]));
		secondaryParameterValue[6] = dose
				* (param[1] - param[5])
				* (param[2] - param[5])
				/ (param[0]
						* (param[5] - pkpdInstance
								.getParameter()[3]) * (pkpdInstance
						.getParameter()[5] - param[4]));

		secondaryParameterValue[7] = Math.log(2) / secondaryParameterValue[1];

		secondaryParameterValue[8] = Math.log(2)
				/ param[3];

		secondaryParameterValue[9] = Math.log(2)
				/ param[4];

		secondaryParameterValue[10] = Math.log(2)
				/ param[5];

		secondaryParameterValue[11] = (secondaryParameterValue[4] / pkpdInstance
				.getParameter()[3])
				+ (secondaryParameterValue[5] / param[4])
				+ (secondaryParameterValue[6] / param[5]);

		secondaryParameterValue[12] = dose / secondaryParameterValue[11];

		double aumcb = (secondaryParameterValue[4]
				/ (param[3] * param[3])
				+ secondaryParameterValue[5]
				/ (param[4] * param[4]) + secondaryParameterValue[6]
				/ (param[5] * param[5]));

		secondaryParameterValue[13] = aumcb + ti * secondaryParameterValue[11]
				/ 2.0;
		secondaryParameterValue[14] = (secondaryParameterValue[13] / secondaryParameterValue[11])
				- ti / 2;

		secondaryParameterValue[15] = dose
				* ((secondaryParameterValue[13] / secondaryParameterValue[11]) - ti / 2.0)
				/ secondaryParameterValue[11];

		secondaryParameterValue[16] = secondaryParameterValue[2]
				* param[0];

		secondaryParameterValue[17] = secondaryParameterValue[16]
				/ param[1];

		secondaryParameterValue[18] = secondaryParameterValue[3]
				* param[0];

		secondaryParameterValue[19] = secondaryParameterValue[18]
				/ param[2];

	}

	
	

	private void calculateSPForModel18(double dose) {
		secondaryParameterValue[0] = (param[0] + param[1] + param[2]);

		secondaryParameterValue[1] = dose / (param[0] + param[1] + param[2]);

		double e1 = 1;
		double e2 = (param[5] * param[1] + param[5] * param[0] + param[4]
				* param[2] + param[4] * param[0] + param[3] * param[2] + param[3]
				* param[1]);
		e2 = (-1) * e2 / secondaryParameterValue[0];
		double e3 = 1
				* (param[3] * param[5] * param[1] + param[5] * param[4]
						* param[0] + param[3] * param[4] * param[2])
				/ secondaryParameterValue[0];

		secondaryParameterValue[2] = 0.5 * ((-e2) + Math.sqrt(e2 * e2 - 4 * e3));

		secondaryParameterValue[3] = .5 * ((-e2) - Math.sqrt(e2 * e2 - 4 * e3));
		secondaryParameterValue[4] = param[3] * param[4] * param[5]
				/ (secondaryParameterValue[2] * secondaryParameterValue[3]);

		e1 = param[3] * param[4] + param[3] * param[5] + param[4] * param[5];
		e2 = secondaryParameterValue[2] * (param[3] + param[4] + param[5]);
		e3 = e1 - e2 - secondaryParameterValue[4] * secondaryParameterValue[3]
				+ secondaryParameterValue[2] * secondaryParameterValue[2];

		secondaryParameterValue[5] = e3
				/ (secondaryParameterValue[3] - secondaryParameterValue[2]);

		secondaryParameterValue[6] = (param[3] + param[4] + param[5])
				- (secondaryParameterValue[4] + secondaryParameterValue[5]
						+ secondaryParameterValue[2] + secondaryParameterValue[3]);

		secondaryParameterValue[7] = Math.log(2) / secondaryParameterValue[4];

		secondaryParameterValue[8] = Math.log(2) / param[3];

		secondaryParameterValue[9] = Math.log(2) / param[4];

		secondaryParameterValue[10] = Math.log(2) / param[5];

		secondaryParameterValue[11] = ((param[0] / param[3])
				+ (param[1] / param[4]) + (param[2] / param[5]));

		secondaryParameterValue[12] = dose / secondaryParameterValue[11];

		secondaryParameterValue[13] = (param[0] / (param[3] * param[3])
				+ param[1] / (param[4] * param[4]) + param[2]
				/ (param[5] * param[5]));
		secondaryParameterValue[14] = secondaryParameterValue[13]
				/ secondaryParameterValue[11];

		secondaryParameterValue[15] = secondaryParameterValue[12]
				* secondaryParameterValue[14];


		secondaryParameterValue[17] = secondaryParameterValue[5]
					* secondaryParameterValue[1];

			secondaryParameterValue[16] = secondaryParameterValue[17]
					/ secondaryParameterValue[2];

			secondaryParameterValue[19] = secondaryParameterValue[6]
					* secondaryParameterValue[1];

			secondaryParameterValue[18] = secondaryParameterValue[19]
					/ secondaryParameterValue[3];

	}

	
	private void calculateSPForModel17(double dose) {

		secondaryParameterValue[2] = (param[0]
				* param[3] + param[1]
				* param[2])
				/ (param[0] + param[1]);

		secondaryParameterValue[0] = param[2]
				* param[3] / secondaryParameterValue[2];

		secondaryParameterValue[1] = param[2]
				+ param[3] - secondaryParameterValue[2]
				- secondaryParameterValue[0];

		secondaryParameterValue[3] = Math.log(2) / secondaryParameterValue[0];
		secondaryParameterValue[4] = Math.log(2)
				/ param[2];

		secondaryParameterValue[5] = Math.log(2)
				/ param[3];

		secondaryParameterValue[6] = dose
				/ (param[0] + param[1]);

		secondaryParameterValue[7] = dose
				/ (param[0]
						/ param[2] + pkpdInstance
						.getParameter()[1]
						/ param[3]);

		secondaryParameterValue[8] = secondaryParameterValue[6]
				* secondaryParameterValue[1] / secondaryParameterValue[2];

		secondaryParameterValue[9] = secondaryParameterValue[6]
				* secondaryParameterValue[1];

	}

	
	private void calculateSPForModel16(double dose) {

		secondaryParameterValue[0] = Math.log(2)
				/ param[1];

		secondaryParameterValue[2] = (1 / 2.0)
				* (param[1]
						+ param[2]
						+ param[3] - Math.sqrt(Math.pow(
						(param[1]
								+ param[2] + pkpdInstance
								.getParameter()[3]), 2)
						- 4
						* param[3]
						* param[1]));

		secondaryParameterValue[1] = param[1]
				* param[3] / secondaryParameterValue[2];

		secondaryParameterValue[3] = Math.log(2) / secondaryParameterValue[1];
		secondaryParameterValue[4] = Math.log(2) / secondaryParameterValue[2];

		secondaryParameterValue[5] = (dose / param[0])
				* (secondaryParameterValue[1] - param[3])
				/ (secondaryParameterValue[1] - secondaryParameterValue[2]);

		secondaryParameterValue[6] = (dose / param[0])
				* (secondaryParameterValue[2] - param[3])
				/ (secondaryParameterValue[2] - secondaryParameterValue[1]);

		secondaryParameterValue[7] = param[0]
				* param[1];

		secondaryParameterValue[8] = param[0]
				* param[2]
				/ param[3];

		secondaryParameterValue[9] = param[2]
				* param[0];

	}

	
	private void calculateSPForModel15(double dose) {

		secondaryParameterValue[0] = param[0]
				* param[1];

		secondaryParameterValue[1] = Math.log(2)
				/ param[1];

	}

	
	private void calculateSPForModel14(double dose) {

		double temp = dose
				* (param[0]
						* (param[2] - pkpdInstance
								.getParameter()[3]) + pkpdInstance
						.getParameter()[1]
						* (param[2] - pkpdInstance
								.getParameter()[4]));

		secondaryParameterValue[2] = dose
				* (param[0]
						* param[4]
						* param[2]
						+ param[1]
						* param[3]
						* param[2] - (pkpdInstance
						.getParameter()[0] + param[1])
						* param[3]
						* param[4]) / temp;

		secondaryParameterValue[1] = dose
				* (param[0]
						* param[4]
						* param[2]
						+ param[1]
						* param[3]
						* param[2] - (pkpdInstance
						.getParameter()[0] + param[1])
						* param[3]
						* param[4]) / temp;

		secondaryParameterValue[0] = param[3]
				* param[4] / secondaryParameterValue[2];

		double v = param[2] * dose / temp;
		secondaryParameterValue[3] = dose / v / secondaryParameterValue[0];
		secondaryParameterValue[4] = Math.log(2)
				/ param[2];

		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[0];

		secondaryParameterValue[6] = Math.log(2)
				/ param[3];

		secondaryParameterValue[7] = Math.log(2)
				/ param[4];

		secondaryParameterValue[8] = v;

		secondaryParameterValue[9] = dose / secondaryParameterValue[3];

		secondaryParameterValue[11] = secondaryParameterValue[1] * v;

		secondaryParameterValue[10] = secondaryParameterValue[11]
				/ secondaryParameterValue[2];

	}

	
	private void calculateSPForModel1(double dose) {

		secondaryParameterValue[0] = dose
				/ (param[0] * param[1]);

		secondaryParameterValue[1] = Math.log(2)
				/ param[1];
		secondaryParameterValue[2] = dose / param[0];
		secondaryParameterValue[3] = param[0]
				* param[1];
		secondaryParameterValue[4] = dose
				/ (param[0]
						* param[1] * pkpdInstance
						.getParameter()[1]);
		secondaryParameterValue[5] = secondaryParameterValue[4]
				/ secondaryParameterValue[0];
		secondaryParameterValue[6] = secondaryParameterValue[5]
				* secondaryParameterValue[3];

	}

	
	
	
	private void calculateSPForModel2(double dose, double lengthOfInfusion) {

		secondaryParameterValue[0] = dose
				/ (param[0] * param[1]);

		secondaryParameterValue[1] = Math.log(2)
				/ param[1];
		// nedd to check the formula

		double coef = dose
				/ (lengthOfInfusion * param[0] * pkpdInstance
						.getParameter()[1]);
		secondaryParameterValue[2] = coef
				* (1 - Math.exp((-param[1])
						* lengthOfInfusion));

		secondaryParameterValue[3] = param[0]
				* param[1];

		double aumcIv = dose
				/ (param[0]
						* param[1] * pkpdInstance
						.getParameter()[1]);

		secondaryParameterValue[4] = aumcIv + lengthOfInfusion
				* secondaryParameterValue[0] / 2;
		secondaryParameterValue[5] = secondaryParameterValue[4]
				/ secondaryParameterValue[0] - lengthOfInfusion / 2;
		secondaryParameterValue[6] = secondaryParameterValue[5]
				* secondaryParameterValue[3];

	}


	private void calculateSPForModel3(double dose) {

		secondaryParameterValue[0] = dose
				/ (param[0] * param[2]);

		secondaryParameterValue[1] = Math.log(2)
				/ param[1];
		secondaryParameterValue[2] = Math.log(2)
				/ param[2];
		secondaryParameterValue[3] = param[0]
				* param[2];
		secondaryParameterValue[4] = Math.log(param[1]
				/ param[2])
				/ (param[1] - param[2]);

		secondaryParameterValue[5] = dose
				* Math
						.exp((-param[2] * secondaryParameterValue[4]))
				/ param[0];

	}

	
	private void calculateSPForModel4(double dose) {

		secondaryParameterValue[0] = dose
				/ (param[0] * param[2]);

		secondaryParameterValue[1] = Math.log(2)
				/ param[1];
		secondaryParameterValue[2] = Math.log(2)
				/ param[2];
		secondaryParameterValue[3] = param[0]
				* param[2];
		secondaryParameterValue[4] = Math.log(param[1]
				/ param[2])
				/ (param[1] - param[2])
				+ param[3];

		secondaryParameterValue[5] = dose
				* Math
						.exp((-param[2] * (secondaryParameterValue[4] - pkpdInstance
								.getParameter()[3])))
				/ param[0];

	}

	
	private void calculateSPForModel5(double dose) {

		secondaryParameterValue[0] = dose
				/ (param[0] * param[1]);

		secondaryParameterValue[1] = Math.log(2)
				/ param[1];
		secondaryParameterValue[2] = param[0]
				* param[1];
		secondaryParameterValue[3] = 1 / param[1];

		secondaryParameterValue[4] = Math.exp(-1)
				* (dose / param[0]);

	}

	
	private void calculateSPForModel6(double dose) {

		secondaryParameterValue[0] = dose
				/ (param[0] * param[1]);

		secondaryParameterValue[1] = Math.log(2)
				/ param[1];
		secondaryParameterValue[2] = param[0]
				* param[1];
		secondaryParameterValue[3] = 1 / param[1]
				+ param[2];

		secondaryParameterValue[4] = Math.exp(-1)
				* (dose / param[0]);

	}

	
	private void calculateSPForModel7(double dose) {

		secondaryParameterValue[1] = Math.log(2)
				/ param[1];

		double temp = Math.pow(
				(param[1]
						+ param[2] + pkpdInstance
						.getParameter()[3]), 2);
		double temp1 = Math.sqrt(temp - 4 * param[1]
				* param[3]);

		double temp2 = param[1]
				+ param[2]
				+ param[3] - temp1;

		secondaryParameterValue[3] = (1 / 2.0) * (temp2);
		secondaryParameterValue[2] = param[1]
				* param[3] / secondaryParameterValue[3];

		double a = (dose / param[0])
				* (secondaryParameterValue[2] - param[3])
				/ (secondaryParameterValue[2] - secondaryParameterValue[3]);

		double b = (-1) * (dose / param[0])
				* (secondaryParameterValue[3] - param[3])
				/ (secondaryParameterValue[2] - secondaryParameterValue[3]);

		secondaryParameterValue[0] = (a / secondaryParameterValue[2])
				+ (b / secondaryParameterValue[3]);

		secondaryParameterValue[4] = Math.log(2) / secondaryParameterValue[2];
		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		secondaryParameterValue[6] = a;
		secondaryParameterValue[7] = b;

		secondaryParameterValue[8] = dose / param[0];
		secondaryParameterValue[9] = dose / secondaryParameterValue[0];

		secondaryParameterValue[10] = secondaryParameterValue[6]
				/ Math.pow(secondaryParameterValue[2], 2)
				+ secondaryParameterValue[7]
				/ Math.pow(secondaryParameterValue[3], 2);
		secondaryParameterValue[11] = secondaryParameterValue[10]
				/ secondaryParameterValue[0];

		secondaryParameterValue[12] = secondaryParameterValue[9]
				* secondaryParameterValue[11];

		secondaryParameterValue[14] = param[0]
				* param[2];

		secondaryParameterValue[13] = secondaryParameterValue[14] / param[3];
		                                                  			

	}

	
	private void calculateSPForModel8(double dose) {

		secondaryParameterValue[0] = (param[0] / pkpdInstance
				.getParameter()[2])
				+ (param[1] / param[3]);

		secondaryParameterValue[2] = Math.log(2)
				/ param[2];

		secondaryParameterValue[3] = Math.log(2)
				/ param[3];

		secondaryParameterValue[6] = (param[0]
				* param[3] + param[1]
				* param[2])
				/ (param[0] + param[1]);

		secondaryParameterValue[4] = param[2]
				* param[3] / secondaryParameterValue[6];
		secondaryParameterValue[5] = param[2]
				+ param[3] - secondaryParameterValue[6]
				- secondaryParameterValue[4];

		secondaryParameterValue[1] = Math.log(2) / secondaryParameterValue[4];
		secondaryParameterValue[8] = dose
				/ (param[0] + param[1]);

		secondaryParameterValue[7] = dose / secondaryParameterValue[8];

		secondaryParameterValue[9] = dose / secondaryParameterValue[0];

		secondaryParameterValue[10] = param[0]
				/ Math.pow(param[2], 2)
				+ param[1]
				/ Math.pow(param[3], 2);

		secondaryParameterValue[11] = secondaryParameterValue[10]
				/ secondaryParameterValue[0];

		secondaryParameterValue[12] = secondaryParameterValue[9]
				* secondaryParameterValue[11];

		secondaryParameterValue[14] = secondaryParameterValue[8]
				* secondaryParameterValue[5];

		secondaryParameterValue[13] = secondaryParameterValue[14]
				/ secondaryParameterValue[6];

	}

	
	private void calculateSPForModel9(double dose, double lengthOfInfusion) {

		secondaryParameterValue[0] = dose
				/ (param[0] * param[1]);

		secondaryParameterValue[1] = Math.log(2)
				/ param[1];

		secondaryParameterValue[3] = (1 / 2.0)
				* (param[1]
						+ param[2]
						+ param[3] - Math.sqrt(Math.pow(
						(param[1]
								+ param[2] + pkpdInstance
								.getParameter()[3]), 2)

						- 4
						* param[1]
						* param[3]));

		secondaryParameterValue[2] = (param[1] * pkpdInstance
				.getParameter()[3])
				/ secondaryParameterValue[3];

		secondaryParameterValue[4] = Math.log(2) / secondaryParameterValue[2];

		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		double a1 = (dose / lengthOfInfusion / param[0])
				* (param[3] - secondaryParameterValue[2])
				/ (secondaryParameterValue[2] - secondaryParameterValue[3])
				/ secondaryParameterValue[2];

		double b1 = (-1)
				* (dose / lengthOfInfusion / param[0])
				* (param[3] - secondaryParameterValue[3])
				/ (secondaryParameterValue[2] - secondaryParameterValue[3])
				/ secondaryParameterValue[3];

		secondaryParameterValue[6] = -1.0 * a1 * lengthOfInfusion * secondaryParameterValue[2];

		secondaryParameterValue[7] = -1.0 * b1 * lengthOfInfusion * secondaryParameterValue[3];
		secondaryParameterValue[8] = a1
				* (Math.exp((-secondaryParameterValue[2]) * lengthOfInfusion) - 1.0)
				+ b1
				* (Math.exp((-secondaryParameterValue[3]) * lengthOfInfusion) - 1.0);

		secondaryParameterValue[9] = dose / secondaryParameterValue[0];

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

		secondaryParameterValue[14] = param[0]
				* param[2];

		secondaryParameterValue[13] = param[0]
				* (param[2] / param[3]);

	}

	
	private void calculateSPForModel10(double dose, double lengthOfInfusion) {

		secondaryParameterValue[0] = (param[2] * pkpdInstance
				.getParameter()[3])
				/ param[1];

		secondaryParameterValue[1] = param[2]
				+ param[3]
				- param[1] - secondaryParameterValue[0];

		secondaryParameterValue[2] = Math.log(2) / secondaryParameterValue[0];

		secondaryParameterValue[3] = dose
				/ (param[0] * secondaryParameterValue[0]);

		secondaryParameterValue[4] = Math.log(2)
				/ param[2];

		secondaryParameterValue[5] = Math.log(2)
				/ param[3];

		double a = dose
				* (param[2] - param[1])
				/ (param[0] * (pkpdInstance
						.getParameter()[2] - param[3]));
		double b = dose
				* (param[1] - param[3])
				/ (param[0] * (pkpdInstance
						.getParameter()[2] - param[3]));
		secondaryParameterValue[6] = a;
		secondaryParameterValue[7] = b;

		double k0 = dose / lengthOfInfusion;
		double aa = k0
				* (param[1] - param[2])
				/ (param[0]
						* param[2] * (pkpdInstance
						.getParameter()[3] - param[2]));
		double bb = k0
				* (param[1] - param[3])
				/ (param[0]
						* param[3] * (pkpdInstance
						.getParameter()[2] - param[3]));

		secondaryParameterValue[8] = aa
				* (1 - Math.exp((-param[2])
						* lengthOfInfusion))
				+ bb
				* (1 - Math.exp((-param[3])
						* lengthOfInfusion));
		secondaryParameterValue[9] = dose / secondaryParameterValue[3];

		double aumcb = (a
				/ (param[2] * param[2]) + b
				/ (param[3] * param[3]));
		secondaryParameterValue[10] = aumcb + lengthOfInfusion
				* secondaryParameterValue[3] / 2.0;

		secondaryParameterValue[11] = (secondaryParameterValue[10] / secondaryParameterValue[3])
				- lengthOfInfusion / 2.0;

		secondaryParameterValue[12] = dose
				* ((secondaryParameterValue[10] / secondaryParameterValue[3]) - lengthOfInfusion / 2)
				/ secondaryParameterValue[3];

		secondaryParameterValue[14] = secondaryParameterValue[1]
				* param[0];

		secondaryParameterValue[13] = secondaryParameterValue[14]
				/ param[1];

	}

	
	private void calculateSPForModel11(double dose) {

		secondaryParameterValue[0] = dose
				/ (param[0] * param[2]);

		secondaryParameterValue[1] = Math.log(2)
				/ param[1];

		secondaryParameterValue[2] = Math.log(2)
				/ param[2];

		secondaryParameterValue[4] = (1 / 2.0)
				* (param[2]
						+ param[3]
						+ param[4] - Math.sqrt(Math.pow(
						(param[2]
								+ param[3] + pkpdInstance
								.getParameter()[4]), 2)

						- 4
						* param[2]
						* param[4]));

		secondaryParameterValue[3] = param[2]
				* param[4] / secondaryParameterValue[4];

		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		secondaryParameterValue[6] = Math.log(2) / secondaryParameterValue[4];

		secondaryParameterValue[7] = (dose / param[0])
				* param[1]
				* (param[4] - secondaryParameterValue[3])
				/ (secondaryParameterValue[3] - secondaryParameterValue[4])
				/ (secondaryParameterValue[3] - param[1]);

		secondaryParameterValue[8] = (-1)
				* (dose / param[0])
				* param[1]
				* (param[4] - secondaryParameterValue[4])
				/ (secondaryParameterValue[3] - secondaryParameterValue[4])
				/ (secondaryParameterValue[4] - param[1]);

		secondaryParameterValue[9] = dose / secondaryParameterValue[0];

		secondaryParameterValue[11] = param[3]
				* param[0];

		secondaryParameterValue[10] = secondaryParameterValue[11]
				/ param[4];
	}

	
	private void calculateSPForModel12(double dose) {

		secondaryParameterValue[0] = dose
				/ (param[0] * param[2]);

		secondaryParameterValue[1] = Math.log(2)
				/ param[1];

		secondaryParameterValue[2] = Math.log(2)
				/ param[2];

		secondaryParameterValue[4] = (1 / 2.0)
				* (param[2]
						+ param[3]
						+ param[4] - Math.sqrt(Math.pow(
						(param[2]
								+ param[3] + pkpdInstance
								.getParameter()[4]), 2)

						- 4
						* param[2]
						* param[4]));

		secondaryParameterValue[3] = param[2]
				* param[4] / secondaryParameterValue[4];

		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		secondaryParameterValue[6] = Math.log(2) / secondaryParameterValue[4];

		secondaryParameterValue[7] = (dose / param[0])
				* param[1]
				* (param[4] - secondaryParameterValue[3])
				/ (secondaryParameterValue[3] - secondaryParameterValue[4])
				/ (secondaryParameterValue[3] - param[1]);
		secondaryParameterValue[8] = (-1)
				* (dose / param[0])
				* param[1]
				* (param[4] - secondaryParameterValue[4])
				/ (secondaryParameterValue[3] - secondaryParameterValue[4])
				/ (secondaryParameterValue[4] - param[1]);

		secondaryParameterValue[9] = dose / secondaryParameterValue[0];

		secondaryParameterValue[10] = param[3]
				* param[0]
				/ param[4];

		secondaryParameterValue[11] = param[3]
				* param[0];

	}

	
	private void calculateSPForModel13(double dose) {

		secondaryParameterValue[2] = (param[0]
				* param[4] + param[1]
				* param[3])
				/ (param[0] + param[1]);

		secondaryParameterValue[1] = param[3]
				+ param[4]
				- param[2] - secondaryParameterValue[2];

		secondaryParameterValue[0] = param[3]
				* param[4] / secondaryParameterValue[2];

		secondaryParameterValue[4] = Math.log(2)
				/ param[3];

		secondaryParameterValue[3] = param[2]
				* param[4] / secondaryParameterValue[0];

		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		secondaryParameterValue[6] = Math.log(2) / secondaryParameterValue[4];

		secondaryParameterValue[7] = (dose * (secondaryParameterValue[3] - pkpdInstance
				.getParameter()[4]))
				/ (param[0] * (secondaryParameterValue[3] - secondaryParameterValue[4]));

		secondaryParameterValue[8] = (dose * (secondaryParameterValue[4] - pkpdInstance
				.getParameter()[4]))
				/ (param[0] * (secondaryParameterValue[4] - secondaryParameterValue[3]));

		secondaryParameterValue[9] = dose / secondaryParameterValue[0];

		secondaryParameterValue[10] = param[3]
				* param[0]
				/ param[4];

		secondaryParameterValue[11] = param[3]
				* param[0];

	}

	
}
