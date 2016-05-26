package Model;

import org.apache.commons.lang.StringUtils;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class WriteListOfInputCommand {

	ListOfDataStructures dataStructInst;
	ApplicationInfo appInfo;
	PkPdInfo pkpdInst;
	ProcessingInputsInfo procInputInst;

	public void writingListOfInputCommand(String analysisType) {
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		appInfo = dataStructInst.getAppInfo();
		pkpdInst = PkPdInfo.createPKPDInstance();
		procInputInst = pkpdInst.copyProcessingInput();

		if (analysisType.equals("pd")) {
			writingListOfInputCommandForPd();
		} else if (analysisType.equals("pkpdlink")) {
			writingListOfInputCommandForLinkModel();
		} else if (analysisType.equals("mm")) {
			writingListOfInputCommandForMm();
		} else if (analysisType.equals("irm")) {
			writingListOfInputCommandForIrm();
		}

	}

	public void writingHeaderToTextOutput(String stringTime) {

		String[] s11 = new String[2];
		s11[0] = stringTime.substring(0, 10);
		s11[1] = stringTime.substring(11, 19);
				
		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		boolean ifSimulation = pkpdInst.ifSimulation;

		if (ifSimulation == true) {
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput()
					.add(
							StringUtils.rightPad(" ", 100) + "\t"
									+ StringUtils.rightPad("DATE", 5) + "\t"
									+ StringUtils.rightPad(s11[0], 30) + "\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput().add(
							StringUtils.rightPad(" ", 100) + "\t"
									+ StringUtils.rightPad("TIME", 5) + "\t"
									+ StringUtils.rightPad(s11[1], 30)
									+ "\r\n\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput()
					.add(
							StringUtils.rightPad(" ", 30)
									+ "\t"
									+ StringUtils
											.rightPad(
													"TATA CONSULTANCY SERVICES LTD",
													30) + "\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput()
					.add(
							StringUtils.rightPad(" ", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													"DRUG DEVELOPMENT PLATFORM, COMPARTMENTAL ANALYSIS",
													55) + "\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput().add(
							StringUtils.rightPad(" ", 40)
									+ "\t"
									+ StringUtils.rightPad(
											"VERSION 1.0 30SEP 2012", 25)
									+ "\r\n\r\n\r\n");

		} else {
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad(" ", 100) + "\t"
							+ StringUtils.rightPad("DATE", 5) + "\t"
							+ StringUtils.rightPad(s11[0], 30) + "\r\n");
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad(" ", 100) + "\t"
							+ StringUtils.rightPad("TIME", 5) + "\t"
							+ StringUtils.rightPad(s11[1], 30) + "\r\n\r\n");
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad(" ", 30)
							+ "\t"
							+ StringUtils.rightPad(
									"TATA CONSULTANCY SERVICES LTD", 30)
							+ "\r\n");
			pkpdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad(" ", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													"DRUG DEVELOPMENT PLATFORM, COMPARTMENTAL ANALYSIS",
													55) + "\r\n");
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad(" ", 40)
							+ "\t"
							+ StringUtils
									.rightPad("VERSION 1.0 30SEP 2012", 25)
							+ "\r\n\r\n\r\n");
		}

	}

	private void writingListOfInputCommandForIrm() {

		ModelRelatedInformations mRI = new ModelRelatedInformations();
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(" ", 5)
						+ StringUtils
								.rightPad("Listing of Input Commands ", 40)
						+ "\r\n\r\n");

		pkpdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("MODEL TYPE", 20)
						+ "\t"
						+ StringUtils.rightPad(":" + "Indirect Response Model",
								50) + "\r\n");

		pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils.rightPad("PK MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(procInputInst
																		.getModelInputTab1Obj()
																		.getModelNumberForLinkModel() - 1),
												50) + "\r\n");

		pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils.rightPad("IDR MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(procInputInst
																		.getModelInputTab1Obj()
																		.getModelNumberForCA() - 1),
												50) + "\r\n");

		if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == false) {
			if (procInputInst.getModelInputTab2Obj().getOdeSolverMethod() == 1) {
				pkpdInst.workSheetOutputInst.getTextoutput().add(

						StringUtils.rightPad("ODE SOLVER METHOD", 20)
								+ "\t"
								+ StringUtils.rightPad(":"
										+ "Runge Kutta Cashcarp", 50) + "\r\n");
			} else {

				pkpdInst.workSheetOutputInst.getTextoutput().add(

						StringUtils.rightPad("ODE SOLVER METHOD", 20)
								+ "\t"
								+ StringUtils.rightPad(":"
										+ "Runge Kutta Fehlberg", 50) + "\r\n");

			}

		}

		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF VARIABLES", 20) + "\t"
						+ StringUtils.rightPad(":" + 2, 50) + "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad(":" + pkpdInst.noOfParam, 50)
						+ "\r\n");

		int temp = procInputInst.getMappingDataObj()
				.getxColumnCorrespondinOriginalIndex() + 1;
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("TIME COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");

		temp = procInputInst.getMappingDataObj()
				.getyColumnCorrespondinOriginalIndex() + 1;

		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("RESPONSE COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("CONVERGENCE CRITERIA", 20) + "\t"
						+ StringUtils.rightPad(":" + pkpdInst.convergence, 50)
						+ "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("ITERATION NUMBER", 20) + "\t"
						+ StringUtils.rightPad(":" + pkpdInst.iter, 50)
						+ "\r\n");

		if (pkpdInst.methodNo == 1)
			pkpdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("MINIMIZATION METHOD", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													":"
															+ "Gauss Newton Method (Levenberg-Marquardt Algorithm)",
													50) + "\r\n");

		else if (pkpdInst.methodNo == 2)
			pkpdInst.workSheetOutputInst.getTextoutput().add(

					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":" + "Nelder Mead Method",
									50) + "\r\n");

		else if (pkpdInst.methodNo == 3)
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Gauss Newton Method (Hartley)", 50)
							+ "\r\n");

		if (pkpdInst.wtScheme == 0)
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("WEIGHTING", 20) + "\t"
							+ StringUtils.rightPad(":" + "Observed", 50)
							+ "\r\n");

		else
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("WEIGHTING", 20) + "\t"
							+ StringUtils.rightPad(":" + "Predicted", 50)
							+ "\r\n");
		double temp1 = ((-1) * pkpdInst.wtExp);
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("WEIGHTING EXPONENT", 20) + "\t"
						+ StringUtils.rightPad(":" + temp1, 50) + "\r\n");

	}

	private void writingListOfInputCommandForMm() {

		ModelRelatedInformations mRI = new ModelRelatedInformations();
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(" ", 5)
						+ StringUtils
								.rightPad("Listing of Input Commands ", 40)
						+ "\r\n\r\n");

		pkpdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("MODEL TYPE", 20)
						+ "\t"
						+ StringUtils.rightPad(":" + "Michaelis-Menten Model",
								50) + "\r\n");

		pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils.rightPad("MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(pkpdInst.modelNumber - 1),
												50) + "\r\n");

		if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == false) {
			if (procInputInst.getModelInputTab2Obj().getOdeSolverMethod() == 1) {
				pkpdInst.workSheetOutputInst.getTextoutput().add(

						StringUtils.rightPad("ODE SOLVER METHOD", 20)
								+ "\t"
								+ StringUtils.rightPad(":"
										+ "Runge Kutta Cashcarp", 50) + "\r\n");
			} else {

				pkpdInst.workSheetOutputInst.getTextoutput().add(

						StringUtils.rightPad("ODE SOLVER METHOD", 20)
								+ "\t"
								+ StringUtils.rightPad(":"
										+ "Runge Kutta Fehlberg", 50) + "\r\n");

			}

		}

		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF VARIABLES", 20) + "\t"
						+ StringUtils.rightPad(":" + 2, 50) + "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad(":" + pkpdInst.noOfParam, 50)
						+ "\r\n");

		int temp = procInputInst.getMappingDataObj()
				.getxColumnCorrespondinOriginalIndex() + 1;
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("TIME COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");

		temp = procInputInst.getMappingDataObj()
				.getyColumnCorrespondinOriginalIndex() + 1;

		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("CONCENTRATION COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("CONVERGENCE CRITERIA", 20) + "\t"
						+ StringUtils.rightPad(":" + pkpdInst.convergence, 50)
						+ "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("ITERATION NUMBER", 20) + "\t"
						+ StringUtils.rightPad(":" + pkpdInst.iter, 50)
						+ "\r\n");

		if (pkpdInst.methodNo == 1)
			pkpdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("MINIMIZATION METHOD", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													":"
															+ "Gauss Newton Method (Levenberg-Marquardt Algorithm)",
													50) + "\r\n");

		else if (pkpdInst.methodNo == 2)
			pkpdInst.workSheetOutputInst.getTextoutput().add(

					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":" + "Nelder Mead Method",
									50) + "\r\n");

		else if (pkpdInst.methodNo == 3)
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Gauss Newton Method (Hartley)", 50)
							+ "\r\n");

		if (pkpdInst.wtScheme == 0)
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("WEIGHTING", 20) + "\t"
							+ StringUtils.rightPad(":" + "Observed", 50)
							+ "\r\n");

		else
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("WEIGHTING", 20) + "\t"
							+ StringUtils.rightPad(":" + "Predicted", 50)
							+ "\r\n");
		double temp1 = ((-1) * pkpdInst.wtExp);
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("WEIGHTING EXPONENT", 20) + "\t"
						+ StringUtils.rightPad(":" + temp1, 50) + "\r\n");

	}

	private void writingListOfInputCommandForLinkModel() {

		ModelRelatedInformations mRI = new ModelRelatedInformations();
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(" ", 5)
						+ StringUtils
								.rightPad("Listing of Input Commands ", 40)
						+ "\r\n\r\n");

		pkpdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("MODEL TYPE", 20) + "\t"
						+ StringUtils.rightPad(":" + "PK/PD link Model", 50)
						+ "\r\n");

		pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils.rightPad("PK MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(procInputInst
																		.getModelInputTab1Obj()
																		.getModelNumberForLinkModel() - 1),
												50) + "\r\n");

		pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils.rightPad("PD MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(procInputInst
																		.getModelInputTab1Obj()
																		.getModelNumberForCA() - 1),
												50) + "\r\n");

		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF VARIABLES", 20) + "\t"
						+ StringUtils.rightPad(":" + 2, 50) + "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad(":" + pkpdInst.noOfParam, 50)
						+ "\r\n");

		int temp = procInputInst.getMappingDataObj()
				.getxColumnCorrespondinOriginalIndex() + 1;
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("TIME COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");

		temp = procInputInst.getMappingDataObj()
				.getyColumnCorrespondinOriginalIndex() + 1;

		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("RESPONSE COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("CONVERGENCE CRITERIA", 20) + "\t"
						+ StringUtils.rightPad(":" + pkpdInst.convergence, 50)
						+ "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("ITERATION NUMBER", 20) + "\t"
						+ StringUtils.rightPad(":" + pkpdInst.iter, 50)
						+ "\r\n");

		if (pkpdInst.methodNo == 1)
			pkpdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("MINIMIZATION METHOD", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													":"
															+ "Gauss Newton Method (Levenberg-Marquardt Algorithm)",
													50) + "\r\n");

		else if (pkpdInst.methodNo == 2)
			pkpdInst.workSheetOutputInst.getTextoutput().add(

					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":" + "Nelder Mead Method",
									50) + "\r\n");

		else if (pkpdInst.methodNo == 3)
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Gauss Newton Method (Hartley)", 50)
							+ "\r\n");

		if (pkpdInst.wtScheme == 0)
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("WEIGHTING", 20) + "\t"
							+ StringUtils.rightPad(":" + "Observed", 50)
							+ "\r\n");

		else
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("WEIGHTING", 20) + "\t"
							+ StringUtils.rightPad(":" + "Predicted", 50)
							+ "\r\n");
		double temp1 = ((-1) * pkpdInst.wtExp);
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("WEIGHTING EXPONENT", 20) + "\t"
						+ StringUtils.rightPad(":" + temp1, 50) + "\r\n");

	}

	private void writingListOfInputCommandForPd() {

		ModelRelatedInformations mRI = new ModelRelatedInformations();
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(" ", 5)
						+ StringUtils
								.rightPad("Listing of Input Commands ", 40)
						+ "\r\n\r\n");

		pkpdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("MODEL TYPE", 20) + "\t"
						+ StringUtils.rightPad(":" + "PD Model", 50) + "\r\n");

		pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils.rightPad("MODEL NUMBER", 20)
								+ "\t"
								+ StringUtils
										.rightPad(
												":"
														+ mRI
																.getModelinformationForModelNo(pkpdInst.modelNumber - 1),
												50) + "\r\n");

		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF VARIABLES", 20) + "\t"
						+ StringUtils.rightPad(":" + 2, 50) + "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("NUMBER OF PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad(":" + pkpdInst.noOfParam, 50)
						+ "\r\n");

		int temp = procInputInst.getMappingDataObj()
				.getxColumnCorrespondinOriginalIndex() + 1;
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("CONCENTRATION COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");

		temp = procInputInst.getMappingDataObj()
				.getyColumnCorrespondinOriginalIndex() + 1;

		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("RESPONSE COLUMN", 20) + "\t"
						+ StringUtils.rightPad(":" + temp, 50) + "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("CONVERGENCE CRITERIA", 20) + "\t"
						+ StringUtils.rightPad(":" + pkpdInst.convergence, 50)
						+ "\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(

				StringUtils.rightPad("ITERATION NUMBER", 20) + "\t"
						+ StringUtils.rightPad(":" + pkpdInst.iter, 50)
						+ "\r\n");

		if (pkpdInst.methodNo == 1)
			pkpdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("MINIMIZATION METHOD", 20)
									+ "\t"
									+ StringUtils
											.rightPad(
													":"
															+ "Gauss Newton Method (Levenberg-Marquardt Algorithm)",
													50) + "\r\n");

		else if (pkpdInst.methodNo == 2)
			pkpdInst.workSheetOutputInst.getTextoutput().add(

					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":" + "Nelder Mead Method",
									50) + "\r\n");

		else if (pkpdInst.methodNo == 3)
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("MINIMIZATION METHOD", 20)
							+ "\t"
							+ StringUtils.rightPad(":"
									+ "Gauss Newton Method (Hartley)", 50)
							+ "\r\n");

		if (pkpdInst.wtScheme == 0)
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("WEIGHTING", 20) + "\t"
							+ StringUtils.rightPad(":" + "Observed", 50)
							+ "\r\n");

		else
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("WEIGHTING", 20) + "\t"
							+ StringUtils.rightPad(":" + "Predicted", 50)
							+ "\r\n");
		double temp1 = ((-1) * pkpdInst.wtExp);
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("WEIGHTING EXPONENT", 20) + "\t"
						+ StringUtils.rightPad(":" + temp1, 50) + "\r\n");

	}

}
