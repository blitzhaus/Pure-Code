package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import view.ProcessingInputsInfo;
import Common.TestException;

public class InVitroInitialParamValuePreparator {


	PkPdInfo pkpdInst;
	ListOfDataStructures dataStructInst;
	ProcessingInputsInfo procInputInst;

	void initParamValueInfo(int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		
		pkpdInst = PkPdInfo.createPKPDInstance();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		procInputInst = pkpdInst.copyProcessingInput();
		int numberOfSortVariable = procInputInst.getMappingDataObj().getSortVariablesListVector().size();
		
		int noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();
		pkpdInst.initSimplex = new double[noOfParam + 1][noOfParam];
		pkpdInst.lowerBound = new double[noOfParam];
		pkpdInst.upperBound = new double[noOfParam];
		dataStructInst.initial = new double[noOfParam];
		if (procInputInst.getModelInputTab2Obj().getParamBoundarySelection() == 1) {

			retrievingUserGivenBoundary(ii);

			if (procInputInst.getModelInputTab2Obj().getSourceOfIntParamValue() == 1)
				retrievingUserGivenInitValue(ii);
				
			else
				computeInitParamUsingGAOrGS(numberOfSortVariable, ii);

		} else {

			if (procInputInst.getModelInputTab2Obj().getSourceOfIntParamValue() == 1)
				retrievingUserGivenInitValue(ii);
				
			else
				computeInitParamUsingGAOrGS(numberOfSortVariable, ii);
			for (int parCount = 0; parCount < noOfParam; parCount++) {

				pkpdInst.lowerBound[parCount] = 0;
				pkpdInst.upperBound[parCount] = 10 * pkpdInst.parameter[parCount];

			}
		}

	}

	void computeInitParamUsingGAOrGS(int numberOfSortVariable, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		procInputInst = new ProcessingInputsInfo();
		procInputInst = pkpdInst.copyProcessingInput();

		pkpdInst.simplexDefined = 1;

		for (int parCount = 0; parCount < pkpdInst.noOfParam; parCount++)
			pkpdInst.initSimplex[pkpdInst.noOfParam][parCount] = pkpdInst.initSimplex[parCount][parCount];

		pkpdInst.nPop = 50;
		if (procInputInst.getModelInputTab2Obj().getSourceOfIntParamValue() == 0) {
			calculataeInitParamValue(numberOfSortVariable, ii);

		}
	}

	public void calculataeInitParamValue(int numberOfSortVariable, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		procInputInst = pkpdInst.copyProcessingInput();

		GeneticAlgorithm geneticAlgoInst = new GeneticAlgorithm();
		GridSearch gdInstance = new GridSearch();

		pkpdInst.parameter = new double[pkpdInst.noOfParam];
		procInputInst.getInitialParameterValueObj()
				.setCodeGenetratedInitialValue(
						new double[pkpdInst.parameter.length]);

		pkpdInst.setProcessingInput(procInputInst);

		if (procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 1) {

			for (int i = 0; i < pkpdInst.upperBound.length; i++) {
				if (pkpdInst.upperBound[i] <= 0 || pkpdInst.lowerBound[i] < 0) {
					pkpdInst.exceptionForCurrentProfile = 1;
				}

				if (pkpdInst.exceptionForCurrentProfile == 1) {

					TestException inst = new TestException();
					inst.throwCustomException(119);

					pkpdInst.workSheetOutputInst
							.getTextoutput()
							.add(
									"\r\n"
											+ StringUtils
													.rightPad(
															"*************************************************"
																	+ "**********************************",
															80) + "\r\n\r\n");
					pkpdInst
							.getWorkSheetOutputInst()
							.getTextoutput()
							.add(
									StringUtils
											.rightPad(
													"119: Insufficient informations for Genetic Algorithm. Parameters value can not be estimated for Profile Number :"
															+ pkpdInst.currentProfileNumber,
													120));
					pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
							"\r\n\r\n");

				}
			}

			if (pkpdInst.exceptionForCurrentProfile == 0) {

				gdInstance = new GridSearch();
				pkpdInst.parameter = gdInstance.GridSearch(pkpdInst.upperBound,
						pkpdInst.lowerBound, pkpdInst.X, pkpdInst.Y,
						pkpdInst.extraData, pkpdInst.row, pkpdInst.delta);

				for (int i = 0; i < pkpdInst.parameter.length; i++)
					pkpdInst.parameter[i] = pkpdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkpdInst.parameter.length]);
				pkpdInst.setProcessingInput(procInputInst);
			}

		} else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 1) {

			for (int i = 0; i < pkpdInst.upperBound.length; i++) {
				if (pkpdInst.upperBound[i] <= 0 || pkpdInst.lowerBound[i] < 0) {
					pkpdInst.exceptionForCurrentProfile = 1;
				}

				if (pkpdInst.exceptionForCurrentProfile == 1) {

					TestException inst = new TestException();
					inst.throwCustomException(119);

					pkpdInst.workSheetOutputInst
							.getTextoutput()
							.add(
									"\r\n"
											+ StringUtils
													.rightPad(
															"*************************************************"
																	+ "**********************************",
															80) + "\r\n\r\n");
					pkpdInst
							.getWorkSheetOutputInst()
							.getTextoutput()
							.add(
									StringUtils
											.rightPad(
													"119: Insufficient informations for Genetic Algorithm. Parameters value can not be estimated for Profile Number :"
															+ pkpdInst.currentProfileNumber,
													120));
					pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
							"\r\n\r\n");

				}
			}

			if (pkpdInst.exceptionForCurrentProfile == 0) {

				pkpdInst.parameter = geneticAlgoInst.GeneticAlgorithm(
						pkpdInst.upperBound, pkpdInst.lowerBound, pkpdInst.X,
						pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta);

				for (int i = 0; i < pkpdInst.parameter.length; i++)
					pkpdInst.parameter[i] = pkpdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkpdInst.parameter.length]);
				pkpdInst.setProcessingInput(procInputInst);
			}

		}

		String[] initParamVal = new String[pkpdInst.parameter.length];
		for (int i = 0; i < pkpdInst.parameter.length; i++) {
			initParamVal[i] = pkpdInst.formatting(pkpdInst.parameter[i], false);
		}

		String string = "";
		string = string + "Calculated Initial Parameter Value" + "\n";
		for (int i = 0; i < pkpdInst.parameter.length; i++)
			string = string
					+ procInputInst.getProfileAndParamInfoObj()
							.getParameterNameForCA()[i] + ": " + "  "
					+ pkpdInst.formatting(pkpdInst.parameter[i], false);

		for (int i = 0; i < pkpdInst.parameter.length; i++) {
			procInputInst.getInitialParameterValueObj()
					.setCodeGenetratedInitialValueAt(i, pkpdInst.parameter[i]);

			procInputInst.getInitialParameterValueObj()
					.setInitialParameterValueAt(ii * pkpdInst.noOfParam + i,
							numberOfSortVariable + 1, initParamVal[i] + "");
		}

		for (int i = 0; i < pkpdInst.parameter.length; i++) {
			dataStructInst.initial[i] = pkpdInst.parameter[i];
		}
	}

	void retrievingUserGivenBoundary(int ii) {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		procInputInst = pkpdInst.copyProcessingInput();

		int noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();

		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		for (int parCount = 0; parCount < noOfParam; parCount++) {
			try {
				String S1, S2;

				S1 = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(ii * noOfParam + parCount,
								noOfSortVariables + 2);

				S2 = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(ii * noOfParam + parCount,
								noOfSortVariables + 3);

				Double.parseDouble(S1);
				Double.parseDouble(S2);
				pkpdInst.lowerBound[parCount] = Double.parseDouble(S1);
				pkpdInst.upperBound[parCount] = Double.parseDouble(S2);
			} catch (Exception e) {
				pkpdInst.lowerBound[parCount] = 0;
				pkpdInst.upperBound[parCount] = 100;
			}
		}
	}

	void retrievingUserGivenInitValue(int ii) {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		procInputInst = pkpdInst.copyProcessingInput();

		int noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();
		
		pkpdInst.parameter = new double[noOfParam];

		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		for (int parCount = 0; parCount < noOfParam; parCount++) {
			try {
				String S = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(ii * noOfParam + parCount,
								noOfSortVariables + 3);

				Double.parseDouble(S);
				pkpdInst.parameter[parCount] = Double.parseDouble(S);
				dataStructInst.initial[parCount] = Double.parseDouble(S);

			} catch (Exception e) {

				pkpdInst.parameter[parCount] = -1;
				dataStructInst.initial[parCount] = -1;
			}

			if (pkpdInst.parameter[parCount] <= 0) {

				TestException inst = new TestException();
				inst.throwCustomException(116);
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
						StringUtils.rightPad("102: Invalid Parameters value:"
								+ (ii + 1), 100));
				pkpdInst.getWorkSheetOutputInst().getTextoutput().add(
						"\r\n\r\n");
				break;
			}
		}
	}

	void getDefaultParamBoundary(PkParamEstimator pkpdMain) {
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();

		int noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();

		for (int parCount = 0; parCount < noOfParam; parCount++) {

			pkpdMain.pkpdInst.initSimplex[parCount][parCount] = -1;
			pkpdMain.pkpdInst.lowerBound[parCount] = -1;
			pkpdMain.pkpdInst.upperBound[parCount] = -1;

		}
	}



}
