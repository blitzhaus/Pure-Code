package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import Common.TestException;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class DosingRelatedInfoReader {

	ListOfDataStructures dataStructInst;
	ApplicationInfo appInfo;
	PkPdInfo pkPdInst;

	public void readingDoseUntAndConcMassUnit(String analysisType) {
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		appInfo = dataStructInst.getAppInfo();
		pkPdInst = PkPdInfo.createPKPDInstance();

		if (analysisType.equals("pd")) {
			readingUnitForPd();
		} else if (analysisType.equals("pkpdlink")) {
			readingUnitForLinkModel();
		} else if (analysisType.equals("mm")) {
			readingUnitForMm();
		} else if (analysisType.equals("irm")) {
			readingUnitForIrm();
		}

	}

	public void readingDoseAndDosingTime(String analysisType,
			double convertAmount, int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		appInfo = dataStructInst.getAppInfo();
		pkPdInst = PkPdInfo.createPKPDInstance();

		if (analysisType.equals("pk")) {
			retrievingDoseForPkModel(convertAmount, ii);
		} else if (analysisType.equals("pd")) {
			// readingUnitForPd();
		} else if (analysisType.equals("pkpdlink")) {
			retrievingDoseForPkModel(convertAmount, ii);
		} else if (analysisType.equals("mm")) {
			retrievingDoseAndDosingTimeForMm(convertAmount, ii);
		} else if (analysisType.equals("irm")) {
			retrievingDoseForPkModel(convertAmount, ii);
		}

	}

	private void retrievingDoseForPkModel(double convertAmount, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		retrievingDoseAndDosingTime(convertAmount, ii);
		retrievingSecondDoseAndTimeForSimultaniousLm(ii);
		retrievingInfusinTime(ii);

	}

	void retrievingSecondDoseAndTimeForSimultaniousLm(int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();

		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		int noOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();

		if (pkpdInst.ifAlgebraicModel == true
				&& (pkpdInst.modelNumber == 15 || pkpdInst.modelNumber == 16 || pkpdInst.modelNumber == 17)) {

			dataStructInst.infusionTime = new double[noOfDose];
			dataStructInst.infusionDose = new double[noOfDose];
			dataStructInst.secondDose = new double[noOfDose];
			for (int i = 0; i < noOfDose; i++) {
				try {
					String S1 = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									noOfSortVariables + 3);

					String S2 = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									noOfSortVariables + 4);

					Double.parseDouble(S1);
					dataStructInst.infusionDose[i] = Double.parseDouble(S1);

					Double.parseDouble(S2);
					dataStructInst.infusionTime[i] = Double.parseDouble(S2);

				} catch (Exception e) {
					dataStructInst.infusionDose[i] = 0;
					dataStructInst.infusionTime[i] = 0;

				}
			}
		}

		else if (pkpdInst.ifAlgebraicModel == false
				&& (pkpdInst.modelNumber == 21 || pkpdInst.modelNumber == 22
						|| pkpdInst.modelNumber == 23 || pkpdInst.modelNumber == 24)) {
			dataStructInst.infusionTime = new double[noOfDose];
			dataStructInst.infusionDose = new double[noOfDose];
			dataStructInst.secondDose = new double[noOfDose];

			for (int i = 0; i < noOfDose; i++) {
				try {
					String S1 = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									noOfSortVariables + 3);

					String S2 = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									noOfSortVariables + 4);

					Double.parseDouble(S1);
					dataStructInst.secondDose[i] = Double.parseDouble(S1);

					dataStructInst.infusionDose[i] = dataStructInst.secondDose[i];

					Double.parseDouble(S2);
					dataStructInst.infusionTime[i] = Double.parseDouble(S2);

				} catch (Exception e) {
					dataStructInst.infusionDose[i] = 0;
					dataStructInst.infusionTime[i] = 0;

				}
			}

		}
	}

	void retrievingInfusinTime(int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();

		int noOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();

		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		dataStructInst.infusionTime = new double[noOfDose];

		if (pkpdInst.ifAlgebraicModel == true
				&& (pkpdInst.modelNumber == 2 || pkpdInst.modelNumber == 9
						|| pkpdInst.modelNumber == 10 || pkpdInst.modelNumber == 19)) {

			for (int i = 0; i < noOfDose; i++) {
				try {
					String S = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									noOfSortVariables + 3);

					Double.parseDouble(S);
					dataStructInst.infusionTime[i] = Double.parseDouble(S);
				} catch (Exception e) {
					dataStructInst.infusionTime[i] = 0;
				}

				if (dataStructInst.infusionTime[i] <= 0) {

					TestException inst = new TestException();
					inst.throwCustomException(102);
					pkpdInst.exceptionForCurrentProfile = 1;
					pkpdInst
							.getWorkSheetOutputInst()
							.getTextoutput()
							.add(
									"\r\n"
											+ StringUtils
													.rightPad(
															"*************************************************"
																	+ "**********************************",
															80) + "\r\n\r\n");
					pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
							StringUtils.rightPad(
									"102: Invalid Length 0f infusion. Profile Number:"
											+ (ii + 1), 100));
					pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
							"\r\n\r\n");
					break;
				}

			}
		}

		else if (pkpdInst.ifAlgebraicModel == false
				&& (pkpdInst.modelNumber == 7 || pkpdInst.modelNumber == 8
						|| pkpdInst.modelNumber == 15
						|| pkpdInst.modelNumber == 16
						|| pkpdInst.modelNumber == 19 || pkpdInst.modelNumber == 20)) {

			for (int i = 0; i < noOfDose; i++) {
				try {
					String S = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									noOfSortVariables + 3);

					Double.parseDouble(S);
					dataStructInst.infusionTime[i] = Double.parseDouble(S);
				} catch (Exception e) {
					dataStructInst.infusionTime[i] = 0;
				}

				if (dataStructInst.infusionTime[i] <= 0) {

					TestException inst = new TestException();
					inst.throwCustomException(102);
					pkpdInst.exceptionForCurrentProfile = 1;
					pkpdInst
							.getWorkSheetOutputInst()
							.getTextoutput()
							.add(
									"\r\n"
											+ StringUtils
													.rightPad(
															"*************************************************"
																	+ "**********************************",
															80) + "\r\n\r\n");
					pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
							StringUtils.rightPad(
									"102: Invalid Length 0f infusion. Profile Number:"
											+ (ii + 1), 100));
					pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
							"\r\n\r\n");
					break;
				}

			}

		}

	}

	void retrievingDoseAndDosingTime(double convertAmount, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		int noOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();

		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		dataStructInst.dose = new double[noOfDose];
		dataStructInst.dosingTime = new double[noOfDose];
		for (int i = 0; i < noOfDose; i++) {
			try {
				String S = procInputInst.getDosingDataObj().getDosingValueAt(
						ii * noOfDose + i, noOfSortVariables + 1);

				String S1 = procInputInst.getDosingDataObj().getDosingValueAt(
						ii * noOfDose + i, noOfSortVariables + 2);

				Double.parseDouble(S1);
				dataStructInst.dosingTime[i] = Double.parseDouble(S1);

				Double.parseDouble(S);
				dataStructInst.dose[i] = Double.parseDouble(S);

				/*
				 * pkpdMainInst.workSheetOutputInst.getTextoutput().add(
				 * StringUtils.rightPad("", 5) + "\t" + StringUtils.rightPad((i
				 * + 1) + "", 15) + "\t" +
				 * StringUtils.rightPad(pkpdInst.formatting(pkpdMain.dose[i],
				 * pkpdInst.roundTo), 10) + "\t" +
				 * StringUtils.rightPad(pkpdInst.
				 * formatting(pkpdMain.dosingTime[i],pkpdInst.roundTo) , 15) +
				 * "\r\n");
				 */

				if (dataStructInst.dose[i] <= 0
						|| dataStructInst.dosingTime[i] < 0) {
					TestException inst = new TestException();
					inst.throwCustomException(101);
					pkpdInst.exceptionForCurrentProfile = 1;
					pkpdInst
							.getWorkSheetOutputInst()
							.getTextoutput()
							.add(
									"\r\n"
											+ StringUtils
													.rightPad(
															"*************************************************"
																	+ "**********************************",
															80) + "\r\n\r\n");
					pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
							StringUtils.rightPad(
									"101: Invalid Dose or Dosing Time. Profile Number:"
											+ (ii + 1), 100));
					pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
							"\r\n\r\n");
					break;
				}

				dataStructInst.dose[i] = dataStructInst.dose[i] * convertAmount;
			} catch (Exception e) {
				e.printStackTrace();
				dataStructInst.dose[i] = 1;
				dataStructInst.dosingTime[i] = 0;
				TestException inst = new TestException();
				inst.throwCustomException(101);
				pkpdInst.exceptionForCurrentProfile = 1;
				pkpdInst
						.getWorkSheetOutputInst()
						.getTextoutput()
						.add(
								"\r\n"
										+ StringUtils
												.rightPad(
														"*************************************************"
																+ "**********************************",
														80) + "\r\n\r\n");
				pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
						StringUtils.rightPad(
								"101: Invalid Dose or Dosing Time. Profile Number:"
										+ (ii + 1), 100));
				pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
						"\r\n\r\n");
				break;

			}

		}
		System.out.println();
	}

	void retrievingDoseRelatedInfo(double convertAmount, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {/*
						 * 
						 * 
						 * int noOfDose =
						 * appInfo.getWorkSheetObjectsAL().get(appInfo
						 * .getSelectedSheetIndex())
						 * .getCaInfo().getProcessingInputs
						 * ().getModelInputTab1Obj().getNumberOfDose();
						 * 
						 * pkPdInst.workSheetOutputInst.getTextoutput().add(
						 * StringUtils.rightPad("", 40) + "\t" +
						 * StringUtils.rightPad("DOSING INFORMATION", 20) +
						 * "\r\n\r\n");
						 * 
						 * 
						 * pkPdInst.workSheetOutputInst.getTextoutput().add(
						 * StringUtils.rightPad("", 5) +
						 * StringUtils.rightPad("Number of Doses:", 15) + "\t" +
						 * StringUtils.rightPad(Double.toString(noOfDose ), 5) +
						 * "\r\n\r\n");
						 * 
						 * 
						 * dosingInfoPrintingInText();
						 * 
						 * pkpdMain.caProcIpPrep.retrievingDoseAndDosingTime(
						 * pkpdMain, appInfo, convertAmount, ii);
						 * pkpdMain.caProcIpPrep.retrievingInfusinTime(pkpdMain,
						 * appInfo, ii);pkpdMain.caProcIpPrep.
						 * retrievingSecondDoseAndTimeForSimultaniousLm(
						 * pkpdMain, appInfo, ii);
						 */
	}

	void retrievingInfusinTimeForMm(int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();

		dataStructInst.infusionTime = new double[pkPdInst.noOfDose];

		for (int i = 0; i < pkPdInst.noOfDose; i++) {
			try {
				String S = procInputInst.getDosingDataObj().getDosingValueAt(
						ii * pkPdInst.noOfDose + i,
						pkPdInst.numberOfSortVar + 3);

				Double.parseDouble(S);
				dataStructInst.infusionTime[i] = Double.parseDouble(S);
			} catch (Exception e) {
				dataStructInst.infusionTime[i] = 0;
			}

			if (dataStructInst.infusionTime[i] <= 0) {

				TestException inst = new TestException();
				inst.throwCustomException(102);
				pkPdInst.exceptionForCurrentProfile = 1;
				pkPdInst
						.getWorkSheetOutputInst()
						.getTextoutput()
						.add(
								"\r\n"
										+ StringUtils
												.rightPad(
														"*************************************************"
																+ "**********************************",
														80) + "\r\n\r\n");
				pkPdInst.getWorkSheetOutputInst().getTextoutput().add(
						StringUtils.rightPad(
								"102: Invalid Length 0f infusion. Profile Number:"
										+ (ii + 1), 100));
				pkPdInst.getWorkSheetOutputInst().getTextoutput().add(
						"\r\n\r\n");
				break;
			}

		}

	}

	void retrievingDoseAndDosingTimeForMm(double convertAmount, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();

		dataStructInst.dose = new double[pkPdInst.noOfDose];
		dataStructInst.dosingTime = new double[pkPdInst.noOfDose];
		for (int i = 0; i < pkPdInst.noOfDose; i++) {
			try {
				String S = procInputInst.getDosingDataObj().getDosingValueAt(
						ii * pkPdInst.noOfDose + i,
						pkPdInst.numberOfSortVar + 1);

				String S1 = procInputInst.getDosingDataObj().getDosingValueAt(
						ii * pkPdInst.noOfDose + i,
						pkPdInst.numberOfSortVar + 2);

				Double.parseDouble(S1);
				dataStructInst.dosingTime[i] = Double.parseDouble(S1);

				Double.parseDouble(S);
				dataStructInst.dose[i] = Double.parseDouble(S);

				if (dataStructInst.dose[i] <= 0
						|| dataStructInst.dosingTime[i] < 0) {
					TestException inst = new TestException();
					inst.throwCustomException(101);
					pkPdInst.exceptionForCurrentProfile = 1;
					pkPdInst
							.getWorkSheetOutputInst()
							.getTextoutput()
							.add(
									"\r\n"
											+ StringUtils
													.rightPad(
															"*************************************************"
																	+ "**********************************",
															80) + "\r\n\r\n");
					pkPdInst.getWorkSheetOutputInst().getTextoutput().add(
							StringUtils.rightPad(
									"101: Invalid Dose or Dosing Time. Profile Number:"
											+ (ii + 1), 100));
					pkPdInst.getWorkSheetOutputInst().getTextoutput().add(
							"\r\n\r\n");
					break;
				}

				dataStructInst.dose[i] = dataStructInst.dose[i] * convertAmount;
			} catch (Exception e) {
				e.printStackTrace();

				TestException inst = new TestException();
				inst.throwCustomException(101);
				pkPdInst.exceptionForCurrentProfile = 1;
				pkPdInst
						.getWorkSheetOutputInst()
						.getTextoutput()
						.add(
								"\r\n"
										+ StringUtils
												.rightPad(
														"*************************************************"
																+ "**********************************",
														80) + "\r\n\r\n");
				pkPdInst.getWorkSheetOutputInst().getTextoutput().add(
						StringUtils.rightPad(
								"101: Invalid Dose or Dosing Time. Profile Number:"
										+ (ii + 1), 100));
				pkPdInst.getWorkSheetOutputInst().getTextoutput().add(
						"\r\n\r\n");
				break;

			}

		}

		if (pkpdInst.modelNumber == 2 || pkpdInst.modelNumber == 5)
			retrievingInfusinTimeForMm(ii);
	}

	private void readingUnitForIrm() {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();

		pkPdInst.doseUnit = procInputInst.getModelInputTab1Obj().getDoseUnit();

		pkPdInst.concMassUnit = procInputInst.getParameterUnitsDataObj()
				.getConcMassUnit();

		if (pkPdInst.doseUnit == null && pkPdInst.concMassUnit != null) {
			String[] str = new String[3];
			str[0] = "101";
			str[1] = "Provide dose unit";
			dataStructInst.verificationOfInputs.add(str);

		} else if (pkPdInst.concMassUnit == null && pkPdInst.doseUnit != null) {
			String[] str = new String[3];
			str[0] = "101";
			str[1] = "Provide conccentration unit";
			dataStructInst.verificationOfInputs.add(str);

		} else if (pkPdInst.doseUnit == null && pkPdInst.concMassUnit == null) {
			String[] str = new String[3];
			str[0] = "101";
			str[1] = "Provide dose unit and concentration unit";
			dataStructInst.verificationOfInputs.add(str);

		}

	}

	private void readingUnitForMm() {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();

		pkPdInst.doseUnit = procInputInst.getModelInputTab1Obj().getDoseUnit();

		pkPdInst.concMassUnit = procInputInst.getParameterUnitsDataObj()
				.getConcMassUnit();

		if (pkPdInst.doseUnit == null && pkPdInst.concMassUnit != null) {
			String[] str = new String[3];
			str[0] = "101";
			str[1] = "Provide dose unit";
			dataStructInst.verificationOfInputs.add(str);

		} else if (pkPdInst.concMassUnit == null && pkPdInst.doseUnit != null) {
			String[] str = new String[3];
			str[0] = "101";
			str[1] = "Provide conccentration unit";
			dataStructInst.verificationOfInputs.add(str);

		} else if (pkPdInst.doseUnit == null && pkPdInst.concMassUnit == null) {
			String[] str = new String[3];
			str[0] = "101";
			str[1] = "Provide dose unit and concentration unit";
			dataStructInst.verificationOfInputs.add(str);

		}

	}

	private void readingUnitForLinkModel() {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();

		pkPdInst.doseUnit = procInputInst.getModelInputTab1Obj().getDoseUnit();

		pkPdInst.concMassUnit = procInputInst.getParameterUnitsDataObj()
				.getConcMassUnit();

		if (pkPdInst.doseUnit == null && pkPdInst.concMassUnit != null) {
			String[] str = new String[3];
			str[0] = "101";
			str[1] = "Provide dose unit";
			dataStructInst.verificationOfInputs.add(str);

		} else if (pkPdInst.concMassUnit == null && pkPdInst.doseUnit != null) {
			String[] str = new String[3];
			str[0] = "101";
			str[1] = "Provide conccentration unit";
			dataStructInst.verificationOfInputs.add(str);

		} else if (pkPdInst.doseUnit == null && pkPdInst.concMassUnit == null) {
			String[] str = new String[3];
			str[0] = "101";
			str[1] = "Provide dose unit and concentration unit";
			dataStructInst.verificationOfInputs.add(str);

		}

	}

	void writingDoseRelatedInfoInTextoutput(double convertAmount, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ProcessingInputsInfo procInputInst = pkPdInst.copyProcessingInput();

		int numberOfDose = procInputInst.getModelInputTab1Obj()
				.getNumberOfDose();
		
	/*	pkPdInst.workSheetOutputInst
		.getTextoutput()
		.add(
				StringUtils
						.rightPad(
								"*************************************************"
										+ "*****************************************************************",
								80)
						+ "\r\n\r\n");*/
		
		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 40)

				+ StringUtils.rightPad("DOSING INFORMATION", 20)
						+ "\r\n\r\n");

		pkPdInst.getWorkSheetOutputInst().getTextoutput().add(
				StringUtils.rightPad("", 5)
						+ StringUtils.rightPad("Number of Doses:", 15)
						/* + "\t" */
						+ StringUtils
								.rightPad(Double.toString(numberOfDose), 5)
						+ "\r\n\r\n");

		dosingInfoPrintingInText();

	}

	private void dosingInfoPrintingInText() {

		ProcessingInputsInfo procInputInst = pkPdInst.copyProcessingInput();
		String temp = "";
		String tempHeader = "";
		int noOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		temp = StringUtils.rightPad("", 5);
		tempHeader = StringUtils.rightPad("", 5);
		String[][] dosingData = procInputInst.getDosingDataObj()
				.getDosingDSForNCA();

		int noOfCol = dosingData[0].length - noOfSortVar;

		if (noOfCol == 4) {
			temp = temp + StringUtils.rightPad("Dose Number", 15) + "\t"
					+ StringUtils.rightPad("Dose", 15) + "\t"
					+ StringUtils.rightPad("Time of Dose", 15) + "\t"
					+ StringUtils.rightPad("Length of Infusion", 15);

			// adding space between header and values
			tempHeader = tempHeader + StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15);

		} else if (noOfCol == 3) {
			temp = temp + StringUtils.rightPad("Dose Number", 15) + "\t"
					+ StringUtils.rightPad("Dose", 15) + "\t"
					+ StringUtils.rightPad("Time of Dose", 15);

			// adding space between header and values
			tempHeader = tempHeader + StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15);

		} else if (noOfCol == 5) {
			temp = temp + StringUtils.rightPad("Dose Number", 15) + "\t"
					+ StringUtils.rightPad("Dose", 15) + "\t"
					+ StringUtils.rightPad("Time of Dose", 15) + "\t"
					+ StringUtils.rightPad("Infusion Dose", 15) + "\t"
					+ StringUtils.rightPad("Length of Infusion", 15);

			// adding space between header and values
			tempHeader = tempHeader + StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15) + "\t"
					+ StringUtils.rightPad("", 15);

		}

		temp = temp + "\r\n";
		tempHeader = tempHeader + "\r\n";
		pkPdInst.getWorkSheetOutputInst().getTextoutput().add(temp);
		pkPdInst.getWorkSheetOutputInst().getTextoutput().add(tempHeader);

		int noOfRow = procInputInst.getModelInputTab1Obj().getNumberOfDose();

		int startRow = pkPdInst.currentProfileNumber * noOfRow;
		int endRow = startRow + noOfRow;

		for (int i = startRow; i < endRow; i++) {
			temp = StringUtils.rightPad("", 5);
			temp = temp + StringUtils.rightPad(dosingData[i][noOfSortVar], 25)
					+ "\t";
			for (int j = noOfSortVar + 1; j < dosingData[0].length; j++) {

				temp = temp
						+ StringUtils.rightPad(pkPdInst.formatting(Double
								.parseDouble(dosingData[i][j]),
								false), 15) + "\t";
			}

			temp = temp + "\r\n\r\n";
			pkPdInst.getWorkSheetOutputInst().getTextoutput().add(temp);
		}

	}

	private void readingUnitForPd() {

	}

	public double doseToConcMassUnitConvertAmount() {

		double convertAmount;
		DefaultToPreferredUnitConverter unitConversionInst = new DefaultToPreferredUnitConverter();

		try {
			convertAmount = unitConversionInst.unitConversion(
					pkPdInst.doseUnit, pkPdInst.concMassUnit);

		} catch (Exception e) {
			convertAmount = 1;
		}

		return convertAmount;

	}

}
