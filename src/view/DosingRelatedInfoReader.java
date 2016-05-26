package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import Common.TestException;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class DosingRelatedInfoReader {

	ApplicationInfo appInfo;
	CaEngineSetActionHandler caActionHandInst;
	int modelNumber;

	public void readingDoseUntAndConcMassUnit(String analysisType)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		appInfo = DisplayContents.createAppInfoInstance();
		caActionHandInst = CaEngineSetActionHandler
				.createEngineSettingActHandlerInstance();

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

	public void readingDoseAndDosingTime(String analysisType,int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {

		appInfo = DisplayContents.createAppInfoInstance();
		
		caActionHandInst = CaEngineSetActionHandler
		.createEngineSettingActHandlerInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		readingDoseUntAndConcMassUnit(analysisType);

		double convertAmount = doseToConcMassUnitConvertAmount();
		
		if (analysisType.equals("pk")) {
			modelNumber = procInputInst.getModelInputTab1Obj()
					.getModelNumberForCA();
			retrievingDoseForPkModel(convertAmount, ii);
		} else if (analysisType.equals("pd")) {
			// readingUnitForPd();
		} else if (analysisType.equals("pkpdlink")) {
			modelNumber = procInputInst.getModelInputTab1Obj()
					.getModelNumberForLinkModel();
			retrievingDoseForPkModel(convertAmount, ii);
		} else if (analysisType.equals("mm")) {
			modelNumber = procInputInst.getModelInputTab1Obj()
					.getModelNumberForCA();
			retrievingDoseAndDosingTimeForMm(convertAmount, ii);
		} else if (analysisType.equals("irm")) {
			modelNumber = procInputInst.getModelInputTab1Obj()
					.getModelNumberForLinkModel();
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

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		int noOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();

		if (caActionHandInst.ifAlgebraicModel == true
				&& (modelNumber == 15 || modelNumber == 16 || modelNumber == 17)) {

			caActionHandInst.infusionTime = new double[noOfDose];
			caActionHandInst.infusionDose = new double[noOfDose];

			
			for (int i = 0; i < noOfDose; i++) {
				try {
					String S1 = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									noOfSortVariables + 3);

					String S2 = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									noOfSortVariables + 4);

					Double.parseDouble(S1);
					caActionHandInst.infusionDose[i] = Double.parseDouble(S1);
					
					Double.parseDouble(S2);
					caActionHandInst.infusionTime[i] = Double.parseDouble(S2);

				} catch (Exception e) {
					caActionHandInst.infusionDose[i] = 0;
					
					caActionHandInst.infusionTime[i] = 0;

				}
			}
		}

		else if (caActionHandInst.ifAlgebraicModel == false
				&& (modelNumber == 21 || modelNumber == 22 || modelNumber == 23 || modelNumber == 24)) {
			caActionHandInst.infusionTime = new double[noOfDose];
			caActionHandInst.infusionDose = new double[noOfDose];

			

			for (int i = 0; i < noOfDose; i++) {
				try {
					String S1 = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									noOfSortVariables + 3);

					String S2 = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									noOfSortVariables + 4);

					caActionHandInst.infusionDose[i] = Double.parseDouble(S1);
					
					Double.parseDouble(S2);
					caActionHandInst.infusionTime[i] = Double.parseDouble(S2);

				} catch (Exception e) {
					caActionHandInst.infusionDose[i] = 0;

					caActionHandInst.infusionTime[i] = 0;

				}
			}

		}
	}

	void retrievingInfusinTime(int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		int noOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();

		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		if (caActionHandInst.ifAlgebraicModel == true
				&& (modelNumber == 2 || modelNumber == 9 || modelNumber == 10 || modelNumber == 19)) {
			caActionHandInst.infusionTime = new double[noOfDose];

			for (int i = 0; i < noOfDose; i++) {
				try {
					String S = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									noOfSortVariables + 3);

					Double.parseDouble(S);
					caActionHandInst.infusionTime[i] = Double.parseDouble(S);
				} catch (Exception e) {
					caActionHandInst.infusionTime[i] = 0;
				}

				if (caActionHandInst.infusionTime[i] <= 0) {

					TestException inst = new TestException();
					inst.throwCustomException(102);

				}

			}
		}

		else if (caActionHandInst.ifAlgebraicModel == false
				&& (modelNumber == 7 || modelNumber == 8 || modelNumber == 15
						|| modelNumber == 16 || modelNumber == 19 || modelNumber == 20)) {

			for (int i = 0; i < noOfDose; i++) {
				try {
					String S = procInputInst.getDosingDataObj()
							.getDosingValueAt(ii * noOfDose + i,
									noOfSortVariables + 3);

					Double.parseDouble(S);
					caActionHandInst.infusionTime[i] = Double.parseDouble(S);
				} catch (Exception e) {
					caActionHandInst.infusionTime[i] = 0;
				}

				if (caActionHandInst.infusionTime[i] <= 0) {

					TestException inst = new TestException();
					inst.throwCustomException(102);

				}

			}

		}

	}

	void retrievingDoseAndDosingTime(double convertAmount, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		int noOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();

		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		caActionHandInst.conDose = new double[noOfDose];
		caActionHandInst.conDosingTime = new double[noOfDose];
		caActionHandInst.infusionTime = new double[noOfDose];
		caActionHandInst.infusionDose = new double[noOfDose];
		
		for (int i = 0; i < noOfDose; i++) {
			try {
				String S = procInputInst.getDosingDataObj().getDosingValueAt(
						ii * noOfDose + i, noOfSortVariables + 1);

				String S1 = procInputInst.getDosingDataObj().getDosingValueAt(
						ii * noOfDose + i, noOfSortVariables + 2);

				Double.parseDouble(S1);
				caActionHandInst.conDosingTime[i] = Double.parseDouble(S1);

				Double.parseDouble(S);
				caActionHandInst.conDose[i] = Double.parseDouble(S);

				if (caActionHandInst.conDose[i] <= 0
						|| caActionHandInst.conDosingTime[i] < 0) {
					TestException inst = new TestException();
					inst.throwCustomException(101);

				}

				caActionHandInst.conDose[i] = caActionHandInst.conDose[i]
						* convertAmount;
			} catch (Exception e) {
				e.printStackTrace();
				caActionHandInst.conDose[i] = 1;
				caActionHandInst.conDosingTime[i] = 0;
				TestException inst = new TestException();
				inst.throwCustomException(101);

			}

		}
		System.out.println();
	}

	void retrievingInfusinTimeForMm(int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		int noOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();
		caActionHandInst.infusionTime = new double[noOfDose];
		int numberOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		for (int i = 0; i < noOfDose; i++) {
			try {
				String S = procInputInst.getDosingDataObj().getDosingValueAt(
						ii * noOfDose + i, numberOfSortVar + 3);

				Double.parseDouble(S);
				caActionHandInst.infusionTime[i] = Double.parseDouble(S);
			} catch (Exception e) {
				caActionHandInst.infusionTime[i] = 0;
			}

			if (caActionHandInst.infusionTime[i] <= 0) {

				TestException inst = new TestException();
				inst.throwCustomException(102);

			}

		}

	}

	void retrievingDoseAndDosingTimeForMm(double convertAmount, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		int noOfDose = procInputInst.getModelInputTab1Obj().getNumberOfDose();
		int numberOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		caActionHandInst.conDose = new double[noOfDose];
		caActionHandInst.conDosingTime = new double[noOfDose];
		for (int i = 0; i < noOfDose; i++) {
			try {
				String S = procInputInst.getDosingDataObj().getDosingValueAt(
						ii * noOfDose + i, numberOfSortVar + 1);

				String S1 = procInputInst.getDosingDataObj().getDosingValueAt(
						ii * noOfDose + i, numberOfSortVar + 2);

				Double.parseDouble(S1);
				caActionHandInst.conDosingTime[i] = Double.parseDouble(S1);

				Double.parseDouble(S);
				caActionHandInst.conDose[i] = Double.parseDouble(S);

				if (caActionHandInst.conDose[i] <= 0
						|| caActionHandInst.conDosingTime[i] < 0) {
					TestException inst = new TestException();
					inst.throwCustomException(101);

				}

				caActionHandInst.conDose[i] = caActionHandInst.conDose[i]
						* convertAmount;
			} catch (Exception e) {
				e.printStackTrace();

				TestException inst = new TestException();
				inst.throwCustomException(101);

			}

		}

		if (modelNumber == 2 || modelNumber == 5)
			retrievingInfusinTimeForMm(ii);
	}

	String doseUnit;
	String concMassUnit;

	private void readingUnitForIrm() {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		doseUnit = procInputInst.getModelInputTab1Obj().getDoseUnit();

		concMassUnit = procInputInst.getParameterUnitsDataObj()
				.getConcMassUnit();

	}

	private void readingUnitForMm() {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		doseUnit = procInputInst.getModelInputTab1Obj().getDoseUnit();

		concMassUnit = procInputInst.getParameterUnitsDataObj()
				.getConcMassUnit();

	}

	private void readingUnitForLinkModel() {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		doseUnit = procInputInst.getModelInputTab1Obj().getDoseUnit();

		concMassUnit = procInputInst.getParameterUnitsDataObj()
				.getConcMassUnit();

	}

	private void readingUnitForPd() {

	}

	public double doseToConcMassUnitConvertAmount() {

		double convertAmount;
		DefaultToPreferredUnitConversion unitConversionInst = new DefaultToPreferredUnitConversion();

		try {
			convertAmount = unitConversionInst.unitConversion(doseUnit,
					concMassUnit);

		} catch (Exception e) {
			convertAmount = 1;
		}

		return convertAmount;

	}

}
