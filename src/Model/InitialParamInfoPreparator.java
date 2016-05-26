package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import Common.TestException;
import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class InitialParamInfoPreparator {

	ListOfDataStructures dataStructInst;
	ApplicationInfo appInfo;
	PkPdInfo pkPdInst;
	ProcessingInputsInfo procInputInst;
	String[] initParamVal;

	public void retrievingInitialParamValInfo(String analysisType, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		appInfo = dataStructInst.getAppInfo();
		pkPdInst = PkPdInfo.createPKPDInstance();
		procInputInst = pkPdInst.copyProcessingInput();
		if (procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 1)
			dataStructInst.initialValEstimateMethod = 1;
		else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 1)
			dataStructInst.initialValEstimateMethod = 2;
		else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 1)
			dataStructInst.initialValEstimateMethod = 3;
		else if (procInputInst.getModelInputTab2Obj().getPreviouslyGenInitVal() == 1)
			dataStructInst.initialValEstimateMethod = 4;
		dataStructInst.valueForParameterBoundary = procInputInst
				.getModelInputTab2Obj().getParamBoundarySelection();
		initParamValueInfo(analysisType, ii);

	}
	
	void retrieveInitialValueForLinkParam(String analysisType, int ii)
	{
		if(analysisType.equals("pkpdlink") || analysisType.equals("irm"))
			retrieveInitialValueForLinkParameter(ii);

	}

	void initParamValueInfo(String analysisType, int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		pkPdInst = PkPdInfo.createPKPDInstance();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();

		pkPdInst.initSimplex = new double[pkPdInst.noOfParam + 1][pkPdInst.noOfParam];
		pkPdInst.lowerBound = new double[pkPdInst.noOfParam];
		pkPdInst.upperBound = new double[pkPdInst.noOfParam];
		initParamVal = new String[pkPdInst.noOfParam];
		dataStructInst.initial = new double[pkPdInst.noOfParam];

		if (dataStructInst.valueForParameterBoundary == 1) {

			retrievingUserGivenBoundary(ii, analysisType);

		}

		if (dataStructInst.ifParameterValueIsSupplied == 0) {

			computeInitParam(ii, analysisType);

		} else {

			retrievingUserGivenInitValue(ii, analysisType);
		}
		
		if(analysisType.equals("pkpdlink") || analysisType.equals("irm"))
			retrieveInitialValueForLinkParameter(ii);
		
		
	}

	private void retrieveInitialValueForLinkParameter(int ii) {
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		appInfo = dataStructInst.getAppInfo();
		pkPdInst = PkPdInfo.createPKPDInstance();
		procInputInst = pkPdInst.copyProcessingInput();

		dataStructInst.paramForLinkModel = new double[procInputInst
				.getInitialParameterValueObj()
				.getInitialParameterValueForLinkModel().length];

		for (int parCount = 0; parCount < dataStructInst.paramForLinkModel.length; parCount++) {
			try {
				String S = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueForLinkModel()[ii
						* pkPdInst.noOfParam + parCount][pkPdInst.numberOfSortVar + 1];

				Double.parseDouble(S);
				dataStructInst.paramForLinkModel[parCount] = Double
						.parseDouble(S);
				// dataStructInst.initial[parCount] = Double.parseDouble(S);

			} catch (Exception e) {
				System.out.println(" Exception in initial parameter value");
			}
			if (dataStructInst.paramForLinkModel[parCount] <= 0) {

				throwingException(ii);
				break;
			}
		}
	}

	public void computeInitParam(int ii, String analysisType)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		if (analysisType.equals("pd")) {
			computeInitParamForPd(ii);
		} else if (analysisType.equals("pkpdlink")) {
			computeInitParamForLinkModel(ii);
		} else if (analysisType.equals("mm")) {
			computeInitParamForMm(ii);
		} else if (analysisType.equals("irm")) {
			computeInitParamForIrm(ii);
		}else if (analysisType.equals("ascii")) {
			computeInitParamForAscii(ii);
		}
	}

	private void computeInitParamForAscii(int ii) throws RowsExceededException, WriteException, BiffException, IOException {
		pkPdInst = PkPdInfo.createPKPDInstance();
		procInputInst = pkPdInst.copyProcessingInput();

		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++)
			pkPdInst.initSimplex[pkPdInst.noOfParam][parCount] = pkPdInst.initSimplex[parCount][parCount];

		pkPdInst.nPop = 50;
		if (procInputInst.getModelInputTab2Obj().getSourceOfIntParamValue() == 0
				&& procInputInst.getModelInputTab2Obj()
						.getPreviouslyGenInitVal() == 0) {
			calculataeInitParamValueForAscii(ii);

		} else {

			if (procInputInst.getModelInputTab2Obj().getPreviouslyGenInitVal() == 1)
				pkPdInst.parameter = procInputInst
						.getInitialParameterValueObj()
						.getCodeGenetratedInitialValue();
			else
				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = procInputInst
							.getInitialParameterValueObj()
							.getInitialParameterValueAt(i);

			for (int i = 0; i < pkPdInst.parameter.length; i++) {
				dataStructInst.initial[i] = pkPdInst.parameter[i];
			}

		}
		pkPdInst.simplexDefined = 0;
	
		
	}

	private void calculataeInitParamValueForAscii(int ii) throws RowsExceededException, WriteException, BiffException, IOException {
		dataStructInst.ifForInitialValue = true;

		pkPdInst.parameter = new double[pkPdInst.noOfParam];
		procInputInst.getInitialParameterValueObj()
				.setCodeGenetratedInitialValue(
						new double[pkPdInst.parameter.length]);

		if (procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 1) {

			initialparamValEstimateByCS();

			if (pkPdInst.exceptionForCurrentProfile == 0) {
				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];

				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			} else {

				if (pkPdInst.exceptionForCurrentProfile == 1) {
					useOfGAWhenCSFail();

					for (int i = 0; i < pkPdInst.parameter.length; i++)
						pkPdInst.parameter[i] = pkPdInst.parameter[i];
					procInputInst.getInitialParameterValueObj()
							.setCodeGenetratedInitialValue(
									new double[pkPdInst.parameter.length]);
				}

			}

		} else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 1) {

			checkingBoundRelatedExceptions();

			if (pkPdInst.exceptionForCurrentProfile == 0) {

				initialParamValEstimateByGA();

				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			}
		} else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 1) {
			checkingBoundRelatedExceptions();

			if (pkPdInst.exceptionForCurrentProfile == 0) {

				initialParamValEstimateByGS();

				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			}

		}

		dataStructInst.ifForInitialValue = false;

		String string = "";
		string = string + "Calculated Initial Parameter Value" + "\n";
		for (int i = 0; i < pkPdInst.parameter.length; i++)
			string = string
					+ procInputInst.getProfileAndParamInfoObj()
							.getParameterNameForCA()[i]
					+ ": "
					+ "  "
					+ pkPdInst.formatting(pkPdInst.parameter[i],
							false);

		for (int i = 0; i < pkPdInst.parameter.length; i++) {
			procInputInst.getInitialParameterValueObj()
					.setCodeGenetratedInitialValueAt(i, pkPdInst.parameter[i]);

			procInputInst.getInitialParameterValueObj()
					.setInitialParameterValueAt(ii * pkPdInst.noOfParam + i,
							pkPdInst.numberOfSortVar + 1,
							pkPdInst.initial[i] + "");
		}

		for (int i = 0; i < pkPdInst.parameter.length; i++) {
			dataStructInst.initial[i] = pkPdInst.parameter[i];
		}
		pkPdInst.setProcessingInput(procInputInst);
	}

	private void retrievingUserGivenInitValue(int ii, String analysisType) {

		if (analysisType.equals("pd")) {
			retrievingUserGivenInitValueForPd(ii);
		} else if (analysisType.equals("pkpdlink")) {
			retrievingUserGivenInitValueForLinkModel(ii);
		} else if (analysisType.equals("mm")) {
			retrievingUserGivenInitValueForMm(ii);
		} else if (analysisType.equals("irm")) {
			retrievingUserGivenInitValueForIrm(ii);
		}else if (analysisType.equals("ascii")) {
			retrievingUserGivenInitValueForPd(ii);
		}
	}

	private void retrievingUserGivenBoundary(int ii, String analysisType) {
		if (analysisType.equals("pd")) {
			retrievingUserGivenBoundaryForPD(ii);
		} else if (analysisType.equals("pkpdlink")) {
			retrievingUserGivenBoundaryForLinkModel(ii);
		} else if (analysisType.equals("mm")) {
			retrievingUserGivenBoundaryForMM(ii);
		} else if (analysisType.equals("irm")) {
			retrievingUserGivenBoundaryForIrm(ii);
		}else if (analysisType.equals("ascii")) {
			retrievingUserGivenBoundaryForPD(ii);
		}
	}

	void retrievingUserGivenBoundaryForPD(int ii) {

		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++) {
			try {
				String S1, S2;

				S1 = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii * pkPdInst.noOfParam + parCount,
								pkPdInst.numberOfSortVar + 2);

				S2 = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii * pkPdInst.noOfParam + parCount,
								pkPdInst.numberOfSortVar + 3);

				Double.parseDouble(S1);
				Double.parseDouble(S2);
				pkPdInst.lowerBound[parCount] = Double.parseDouble(S1);
				pkPdInst.upperBound[parCount] = Double.parseDouble(S2);
			} catch (Exception e) {
				System.out.println(" parameter boundary related exceptions");
			}
		}
	}

	void retrievingUserGivenBoundaryForMM(int ii) {

		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++) {
			try {
				String S1, S2;

				S1 = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii * pkPdInst.noOfParam + parCount,
								pkPdInst.numberOfSortVar + 2);

				S2 = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii * pkPdInst.noOfParam + parCount,
								pkPdInst.numberOfSortVar + 3);

				Double.parseDouble(S1);
				Double.parseDouble(S2);
				pkPdInst.lowerBound[parCount] = Double.parseDouble(S1);
				pkPdInst.upperBound[parCount] = Double.parseDouble(S2);
			} catch (Exception e) {
				System.out.println(" parameter boundary related exceptions");
			}
		}
	}

	void retrievingUserGivenBoundaryForLinkModel(int ii) {

		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++) {
			try {
				String S1, S2;

				S1 = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii * pkPdInst.noOfParam + parCount,
								pkPdInst.numberOfSortVar + 2);

				S2 = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii * pkPdInst.noOfParam + parCount,
								pkPdInst.numberOfSortVar + 3);

				Double.parseDouble(S1);
				Double.parseDouble(S2);
				pkPdInst.lowerBound[parCount] = Double.parseDouble(S1);
				pkPdInst.upperBound[parCount] = Double.parseDouble(S2);
			} catch (Exception e) {
				System.out.println(" parameter boundary related exceptions");
			}
		}
	}

	void retrievingUserGivenBoundaryForIrm(int ii) {

		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++) {
			try {
				String S1, S2;

				S1 = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii * pkPdInst.noOfParam + parCount,
								pkPdInst.numberOfSortVar + 2);

				S2 = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii * pkPdInst.noOfParam + parCount,
								pkPdInst.numberOfSortVar + 3);

				Double.parseDouble(S1);
				Double.parseDouble(S2);
				pkPdInst.lowerBound[parCount] = Double.parseDouble(S1);
				pkPdInst.upperBound[parCount] = Double.parseDouble(S2);
			} catch (Exception e) {
				System.out.println(" parameter boundary related exceptions");
			}
		}
	}

	void retrievingUserGivenInitValueForPd(int ii) {
		procInputInst = PkPdInfo.createPKPDInstance().copyProcessingInput();

		pkPdInst.parameter = new double[pkPdInst.noOfParam];
		dataStructInst.initial = new double[pkPdInst.noOfParam];
		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++) {
			try {
				String S = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii * pkPdInst.noOfParam + parCount,
								pkPdInst.numberOfSortVar + 1);

				Double.parseDouble(S);
				pkPdInst.parameter[parCount] = Double.parseDouble(S);
				dataStructInst.initial[parCount] = Double.parseDouble(S);

			} catch (Exception e) {
				System.out.println(" Exception in initial parameter value");
			}

			if (pkPdInst.parameter[parCount] <= 0) {

				throwingException(ii);
				break;
			}
		}
	}

	private void throwingException(int ii) {

		TestException inst = new TestException();
		inst.throwCustomException(116);
		pkPdInst.exceptionForCurrentProfile = 1;
		pkPdInst.workSheetOutputInst.getTextoutput().add(
				"\r\n"
						+ StringUtils.rightPad(
								"*************************************************"
										+ "**********************************",
								80) + "\r\n\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("102: Invalid Parameters value:"
						+ (ii + 1), 100));
		pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

	}

	void retrievingUserGivenInitValueForMm(int ii) {
		pkPdInst.parameter = new double[pkPdInst.noOfParam];
		dataStructInst.initial = new double[pkPdInst.noOfParam];
		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++) {
			try {
				String S = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii * pkPdInst.noOfParam + parCount,
								pkPdInst.numberOfSortVar + 1);

				Double.parseDouble(S);
				pkPdInst.parameter[parCount] = Double.parseDouble(S);
				dataStructInst.initial[parCount] = Double.parseDouble(S);

			} catch (Exception e) {
				System.out.println(" Exception in initial parameter value");
			}
			if (pkPdInst.parameter[parCount] <= 0) {

				throwingException(ii);
				break;
			}
		}
	}

	void retrievingUserGivenInitValueForLinkModel(int ii) {
		pkPdInst.parameter = new double[pkPdInst.noOfParam];
		dataStructInst.initial = new double[pkPdInst.noOfParam];

		dataStructInst.paramForLinkModel = new double[procInputInst
				.getInitialParameterValueObj()
				.getInitialParameterValueForLinkModel().length];

		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++) {
			try {
				String S = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii * pkPdInst.noOfParam + parCount,
								pkPdInst.numberOfSortVar + 1);

				Double.parseDouble(S);
				pkPdInst.parameter[parCount] = Double.parseDouble(S);
				dataStructInst.initial[parCount] = Double.parseDouble(S);

			} catch (Exception e) {
				System.out.println(" Exception in initial parameter value");
			}
			if (pkPdInst.parameter[parCount] <= 0) {

				throwingException(ii);
				break;
			}
		}
	}

	void retrievingUserGivenInitValueForIrm(int ii) {
		pkPdInst.parameter = new double[pkPdInst.noOfParam];
		dataStructInst.initial = new double[pkPdInst.noOfParam];


		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++) {
			try {
				String S = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								ii * pkPdInst.noOfParam + parCount,
								pkPdInst.numberOfSortVar + 1);
				Double.parseDouble(S);
				pkPdInst.parameter[parCount] = Double.parseDouble(S);
				dataStructInst.initial[parCount] = Double.parseDouble(S);

			} catch (Exception e) {
				System.out.println(" Exception in initial parameter value");
			}
			if (pkPdInst.parameter[parCount] <= 0) {

				throwingException(ii);
				break;
			}
		}
		System.out.println();
	}

	void computeInitParamForPd(int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {

		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++)
			pkPdInst.initSimplex[pkPdInst.noOfParam][parCount] = pkPdInst.initSimplex[parCount][parCount];

		pkPdInst.nPop = 50;
		if (procInputInst.getModelInputTab2Obj().getSourceOfIntParamValue() == 0
				&& procInputInst.getModelInputTab2Obj()
						.getPreviouslyGenInitVal() == 0) {
			calculataeInitParamValueForPd(ii);

		} else {

			if (procInputInst.getModelInputTab2Obj().getPreviouslyGenInitVal() == 1)
				pkPdInst.parameter = procInputInst
						.getInitialParameterValueObj()
						.getCodeGenetratedInitialValue();
			else
				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = procInputInst
							.getInitialParameterValueObj()
							.getInitialParameterValueAt(i);

			for (int i = 0; i < pkPdInst.parameter.length; i++) {
				dataStructInst.initial[i] = pkPdInst.parameter[i];
			}

		}
		pkPdInst.simplexDefined = 0;
	}

	void computeInitParamForMm(int ii) throws RowsExceededException,
			WriteException, BiffException, IOException {

		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++)
			pkPdInst.initSimplex[pkPdInst.noOfParam][parCount] = pkPdInst.initSimplex[parCount][parCount];

		pkPdInst.nPop = 50;
		if (procInputInst.getModelInputTab2Obj().getSourceOfIntParamValue() == 0
				&& procInputInst.getModelInputTab2Obj()
						.getPreviouslyGenInitVal() == 0) {
			calculataeInitParamValueForMm(ii);

		} else {

			if (procInputInst.getModelInputTab2Obj().getPreviouslyGenInitVal() == 1)
				pkPdInst.parameter = procInputInst
						.getInitialParameterValueObj()
						.getCodeGenetratedInitialValue();
			else
				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = procInputInst
							.getInitialParameterValueObj()
							.getInitialParameterValueAt(i);

			for (int i = 0; i < pkPdInst.parameter.length; i++) {
				dataStructInst.initial[i] = pkPdInst.parameter[i];
			}

		}
		pkPdInst.simplexDefined = 0;
	}

	void computeInitParamForLinkModel(int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++)
			pkPdInst.initSimplex[pkPdInst.noOfParam][parCount] = pkPdInst.initSimplex[parCount][parCount];

		pkPdInst.nPop = 50;
		if (procInputInst.getModelInputTab2Obj().getSourceOfIntParamValue() == 0
				&& procInputInst.getModelInputTab2Obj()
						.getPreviouslyGenInitVal() == 0) {
			calculataeInitParamValueForLinkModel(ii);

		} else {

			if (procInputInst.getModelInputTab2Obj().getPreviouslyGenInitVal() == 1)
				pkPdInst.parameter = procInputInst
						.getInitialParameterValueObj()
						.getCodeGenetratedInitialValue();
			else
				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = procInputInst
							.getInitialParameterValueObj()
							.getInitialParameterValueAt(i);

			for (int i = 0; i < pkPdInst.parameter.length; i++) {
				dataStructInst.initial[i] = pkPdInst.parameter[i];
			}

		}
		pkPdInst.simplexDefined = 0;
	}

	void computeInitParamForIrm(int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++)
			pkPdInst.initSimplex[pkPdInst.noOfParam][parCount] = pkPdInst.initSimplex[parCount][parCount];

		pkPdInst.nPop = 50;
		if (procInputInst.getModelInputTab2Obj().getSourceOfIntParamValue() == 0
				&& procInputInst.getModelInputTab2Obj()
						.getPreviouslyGenInitVal() == 0) {
			calculataeInitParamValueForLinkIrm(ii);

		} else {

			if (procInputInst.getModelInputTab2Obj().getPreviouslyGenInitVal() == 1)
				pkPdInst.parameter = procInputInst
						.getInitialParameterValueObj()
						.getCodeGenetratedInitialValue();
			else
				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = procInputInst
							.getInitialParameterValueObj()
							.getInitialParameterValueAt(i);

			for (int i = 0; i < pkPdInst.parameter.length; i++) {
				dataStructInst.initial[i] = pkPdInst.parameter[i];
			}

		}
		pkPdInst.simplexDefined = 0;
	}

	private void calculataeInitParamValueForPd(int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		dataStructInst.ifForInitialValue = true;

		pkPdInst.parameter = new double[pkPdInst.noOfParam];

		procInputInst.getInitialParameterValueObj()
				.setCodeGenetratedInitialValue(
						new double[pkPdInst.parameter.length]);

		if (procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 1) {

			initialparamValEstimateByCS();

			if (pkPdInst.exceptionForCurrentProfile == 0) {
				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];

				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			} else {

				if (pkPdInst.exceptionForCurrentProfile == 1) {
					useOfGAWhenCSFail();

					for (int i = 0; i < pkPdInst.parameter.length; i++)
						pkPdInst.parameter[i] = pkPdInst.parameter[i];
					procInputInst.getInitialParameterValueObj()
							.setCodeGenetratedInitialValue(
									new double[pkPdInst.parameter.length]);
				}

			}

		} else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 1) {

			checkingBoundRelatedExceptions();

			if (pkPdInst.exceptionForCurrentProfile == 0) {

				initialParamValEstimateByGA();

				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			}
		} else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 1) {
			checkingBoundRelatedExceptions();

			if (pkPdInst.exceptionForCurrentProfile == 0) {

				initialParamValEstimateByGS();

				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			}

		}

		dataStructInst.ifForInitialValue = false;

		String string = "";
		string = string + "Calculated Initial Parameter Value" + "\n";
		for (int i = 0; i < pkPdInst.parameter.length; i++)
			string = string
					+ procInputInst.getProfileAndParamInfoObj()
							.getParameterNameForCA()[i]
					+ ": "
					+ "  "
					+ pkPdInst.formatting(pkPdInst.parameter[i],
							false);

		for (int i = 0; i < pkPdInst.parameter.length; i++) {
			procInputInst.getInitialParameterValueObj()
					.setCodeGenetratedInitialValueAt(i, pkPdInst.parameter[i]);

			procInputInst.getInitialParameterValueObj()
					.setInitialParameterValueAt(ii * pkPdInst.noOfParam + i,
							pkPdInst.numberOfSortVar + 1,
							initParamVal[i]);
		}

		for (int i = 0; i < pkPdInst.parameter.length; i++) {
			dataStructInst.initial[i] = pkPdInst.parameter[i];
		}

		pkPdInst.setProcessingInput(procInputInst);
	}

	private void calculataeInitParamValueForMm(int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		dataStructInst.ifForInitialValue = true;

		pkPdInst.parameter = new double[pkPdInst.noOfParam];
		procInputInst.getInitialParameterValueObj()
				.setCodeGenetratedInitialValue(
						new double[pkPdInst.parameter.length]);

		if (procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 1) {

			initialparamValEstimateByCS();

			if (pkPdInst.exceptionForCurrentProfile == 0) {
				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];

				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			} else {

				if (pkPdInst.exceptionForCurrentProfile == 1) {
					useOfGAWhenCSFail();

					for (int i = 0; i < pkPdInst.parameter.length; i++)
						pkPdInst.parameter[i] = pkPdInst.parameter[i];
					procInputInst.getInitialParameterValueObj()
							.setCodeGenetratedInitialValue(
									new double[pkPdInst.parameter.length]);
				}

			}

		} else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 1) {

			checkingBoundRelatedExceptions();

			if (pkPdInst.exceptionForCurrentProfile == 0) {

				initialParamValEstimateByGA();

				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			}
		} else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 1) {
			checkingBoundRelatedExceptions();

			if (pkPdInst.exceptionForCurrentProfile == 0) {

				initialParamValEstimateByGS();

				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			}

		}

		dataStructInst.ifForInitialValue = false;

		String string = "";
		string = string + "Calculated Initial Parameter Value" + "\n";
		for (int i = 0; i < pkPdInst.parameter.length; i++)
			string = string
					+ procInputInst.getProfileAndParamInfoObj()
							.getParameterNameForCA()[i]
					+ ": "
					+ "  "
					+ pkPdInst.formatting(pkPdInst.parameter[i],
							false);

		for (int i = 0; i < pkPdInst.parameter.length; i++) {
			procInputInst.getInitialParameterValueObj()
					.setCodeGenetratedInitialValueAt(i, pkPdInst.parameter[i]);

			procInputInst.getInitialParameterValueObj()
					.setInitialParameterValueAt(ii * pkPdInst.noOfParam + i,
							pkPdInst.numberOfSortVar + 1,
							initParamVal[i] + "");
		}

		for (int i = 0; i < pkPdInst.parameter.length; i++) {
			dataStructInst.initial[i] = pkPdInst.parameter[i];
		}
		pkPdInst.setProcessingInput(procInputInst);
	}

	private void calculataeInitParamValueForLinkModel(int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		dataStructInst.ifForInitialValue = true;

		pkPdInst.parameter = new double[pkPdInst.noOfParam];
		procInputInst.getInitialParameterValueObj()
				.setCodeGenetratedInitialValue(
						new double[pkPdInst.parameter.length]);

		if (procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 1) {

			initialparamValEstimateByCS();

			if (pkPdInst.exceptionForCurrentProfile == 0) {
				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];

				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			} else {

				if (pkPdInst.exceptionForCurrentProfile == 1) {
					useOfGAWhenCSFail();

					for (int i = 0; i < pkPdInst.parameter.length; i++)
						pkPdInst.parameter[i] = pkPdInst.parameter[i];
					procInputInst.getInitialParameterValueObj()
							.setCodeGenetratedInitialValue(
									new double[pkPdInst.parameter.length]);
				}

			}

		} else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 1) {

			checkingBoundRelatedExceptions();

			if (pkPdInst.exceptionForCurrentProfile == 0) {

				initialParamValEstimateByGA();

				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			}
		} else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 1) {
			checkingBoundRelatedExceptions();

			if (pkPdInst.exceptionForCurrentProfile == 0) {

				initialParamValEstimateByGS();

				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			}

		}

		dataStructInst.ifForInitialValue = false;

		String string = "";
		string = string + "Calculated Initial Parameter Value" + "\n";
		for (int i = 0; i < pkPdInst.parameter.length; i++)
			string = string
					+ procInputInst.getProfileAndParamInfoObj()
							.getParameterNameForCA()[i]
					+ ": "
					+ "  "
					+ pkPdInst.formatting(pkPdInst.parameter[i],
							false);

		for (int i = 0; i < pkPdInst.parameter.length; i++) {
			procInputInst.getInitialParameterValueObj()
					.setCodeGenetratedInitialValueAt(i, pkPdInst.parameter[i]);

			procInputInst.getInitialParameterValueObj()
					.setInitialParameterValueAt(ii * pkPdInst.noOfParam + i,
							pkPdInst.numberOfSortVar + 1,
							initParamVal[i] + "");
		}

		for (int i = 0; i < pkPdInst.parameter.length; i++) {
			dataStructInst.initial[i] = pkPdInst.parameter[i];
		}
		pkPdInst.setProcessingInput(procInputInst);
	}

	private void calculataeInitParamValueForLinkIrm(int ii)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		dataStructInst.ifForInitialValue = true;

		pkPdInst.parameter = new double[pkPdInst.noOfParam];
		procInputInst.getInitialParameterValueObj()
				.setCodeGenetratedInitialValue(
						new double[pkPdInst.parameter.length]);

		if (procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 1) {

			initialparamValEstimateByCS();

			if (pkPdInst.exceptionForCurrentProfile == 0) {
				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];

				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			} else {

				if (pkPdInst.exceptionForCurrentProfile == 1) {
					useOfGAWhenCSFail();

					for (int i = 0; i < pkPdInst.parameter.length; i++)
						pkPdInst.parameter[i] = pkPdInst.parameter[i];
					procInputInst.getInitialParameterValueObj()
							.setCodeGenetratedInitialValue(
									new double[pkPdInst.parameter.length]);
				}

			}

		} else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 1) {

			checkingBoundRelatedExceptions();

			if (pkPdInst.exceptionForCurrentProfile == 0) {

				initialParamValEstimateByGA();

				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			}
		} else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 1) {
			checkingBoundRelatedExceptions();

			if (pkPdInst.exceptionForCurrentProfile == 0) {

				initialParamValEstimateByGS();

				for (int i = 0; i < pkPdInst.parameter.length; i++)
					pkPdInst.parameter[i] = pkPdInst.parameter[i];
				procInputInst.getInitialParameterValueObj()
						.setCodeGenetratedInitialValue(
								new double[pkPdInst.parameter.length]);
			}

		}

		dataStructInst.ifForInitialValue = false;

		String string = "";
		string = string + "Calculated Initial Parameter Value" + "\n";
		for (int i = 0; i < pkPdInst.parameter.length; i++)
			string = string
					+ procInputInst.getProfileAndParamInfoObj()
							.getParameterNameForCA()[i]
					+ ": "
					+ "  "
					+ pkPdInst.formatting(pkPdInst.parameter[i],
							false);

		for (int i = 0; i < pkPdInst.parameter.length; i++) {
			procInputInst.getInitialParameterValueObj()
					.setCodeGenetratedInitialValueAt(i, pkPdInst.parameter[i]);

			procInputInst.getInitialParameterValueObj()
					.setInitialParameterValueAt(ii * pkPdInst.noOfParam + i,
							pkPdInst.numberOfSortVar + 1,
							initParamVal[i] + "");
		}

		for (int i = 0; i < pkPdInst.parameter.length; i++) {
			dataStructInst.initial[i] = pkPdInst.parameter[i];
		}
		pkPdInst.setProcessingInput(procInputInst);
	}

	private void checkingBoundRelatedExceptions() {
		for (int i = 0; i < pkPdInst.upperBound.length; i++) {
			if (pkPdInst.upperBound[i] <= 0 || pkPdInst.lowerBound[i] < 0) {
				pkPdInst.exceptionForCurrentProfile = 1;
			}

			if (pkPdInst.exceptionForCurrentProfile == 1) {

				TestException inst = new TestException();
				inst.throwCustomException(119);

				pkPdInst.workSheetOutputInst
						.getTextoutput()
						.add(
								"\r\n"
										+ StringUtils
												.rightPad(
														"*************************************************"
																+ "**********************************",
														80) + "\r\n\r\n");
				pkPdInst
						.getWorkSheetOutputInst()
						.getTextoutput()
						.add(
								StringUtils
										.rightPad(
												"119: Insufficient informations for Genetic Algorithm. Parameters value can not be estimated for Profile Number :"
														+ pkPdInst.currentProfileNumber,
												120));
				pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

			}
		}
	}

	private void useOfGAWhenCSFail() throws RowsExceededException,
			WriteException, BiffException, IOException {
		for (int i = 0; i < pkPdInst.upperBound.length; i++) {
			if (pkPdInst.upperBound[i] <= 0 || pkPdInst.lowerBound[i] < 0) {
				TestException inst = new TestException();
				inst.throwCustomException(119);
				pkPdInst.exceptionForCurrentProfile = 1;
			}
		}

		if (pkPdInst.exceptionForCurrentProfile == 1) {
			TestException inst = new TestException();
			inst.throwCustomException(118);
			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							"\r\n"
									+ StringUtils
											.rightPad(
													"*************************************************"
															+ "**********************************",
													80) + "\r\n\r\n");
			pkPdInst
					.getWorkSheetOutputInst()
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"118: Problems occurred during Curve Stripping. Genetic Algorithm will be used for initial parameters estimation.",
											100));
			pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

			initialParamValEstimateByGA();

		}

	}

	PkCurveStripper initValByCSInst;
	PdCurveStripper pdCurveStripInst;
	MmCurveStripper mmCurveStripInst;
	IdrCurveStripper idrCurveStripInst;
	PkPdLinkCurveStripper pkpdlinkCurveStripInst;
	GeneticAlgorithm geneticAlgoInst;
	GridSearch gdInstance;
	
	private void initialparamValEstimateByCS() {
		
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		if(pkPdInst.analysisType.equals("pk"))
		{
			initValByCSInst = new PkCurveStripper();
			pkPdInst.parameter = initValByCSInst.parameterByModel(
					dataStructInst.modelNumber, pkPdInst.X, pkPdInst.Y,
					pkPdInst.noOfParam, dataStructInst.dose[0],
					dataStructInst.infusionTime[0]);
		}else if(pkPdInst.analysisType.equals("pd"))
		{
			pdCurveStripInst = new PdCurveStripper();
			pkPdInst.parameter = pdCurveStripInst.calculateInitialValue(pkPdInst.X, pkPdInst.Y);
		}else if(pkPdInst.analysisType.equals("mm"))
		{
			mmCurveStripInst = new MmCurveStripper();
			try {
				pkPdInst.parameter = mmCurveStripInst.calculateInitialValue(pkPdInst.X, pkPdInst.Y,dataStructInst.dose[0]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(pkPdInst.analysisType.equals("irm"))
		{
			idrCurveStripInst = new IdrCurveStripper();
			pkPdInst.parameter = idrCurveStripInst.calculateInitialValue(pkPdInst.X, dataStructInst.concForLinkModel, pkPdInst.Y);
		}else if(pkPdInst.analysisType.equals("pkpdlink"))
		{
			pkpdlinkCurveStripInst = new PkPdLinkCurveStripper();
			pkPdInst.parameter = pkpdlinkCurveStripInst.calculateInitialValue(pkPdInst.X, dataStructInst.concForLinkModel, pkPdInst.Y);
		}
		convertCalculatedInitParamToRequiredDecimal();

	}

	private void initialParamValEstimateByGA() throws RowsExceededException,
			WriteException, BiffException, IOException {
		geneticAlgoInst = new GeneticAlgorithm();
		pkPdInst.parameter = geneticAlgoInst.GeneticAlgorithm(
				pkPdInst.upperBound, pkPdInst.lowerBound, pkPdInst.X,
				pkPdInst.Y, pkPdInst.extraData, pkPdInst.row, pkPdInst.delta);
		convertCalculatedInitParamToRequiredDecimal();

	}

	private void initialParamValEstimateByGS() throws RowsExceededException,
			WriteException, BiffException, IOException {
		gdInstance = new GridSearch();
		pkPdInst.parameter = gdInstance.GridSearch(pkPdInst.upperBound,
				pkPdInst.lowerBound, pkPdInst.X, pkPdInst.Y,
				pkPdInst.extraData, pkPdInst.row, pkPdInst.delta);
		
		convertCalculatedInitParamToRequiredDecimal();

	}

	private void convertCalculatedInitParamToRequiredDecimal()
	{
		initParamVal = new String[pkPdInst.parameter.length];
		for (int i = 0; i < pkPdInst.parameter.length; i++) {
			initParamVal[i] = pkPdInst.formatting(
					pkPdInst.parameter[i], false);
		}
	}
	
}
