package view;

import java.io.IOException;

import Common.WriteAppinfoForUnitTest;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CaOutputDisplayer {

	public static CaOutputDisplayer CA_OUT_GEN = null;

	public static CaOutputDisplayer createCaOutInst() {
		if (CA_OUT_GEN == null) {
			CA_OUT_GEN = new CaOutputDisplayer();
		}
		return CA_OUT_GEN;
	}

	

	String analysisType;
	CaResultDispCompCreator caResDispCreatorInst;
	PlotsOutputDisplayer plotRetrievingInst;
	TablesResultDisplayer dispResultInstance;
	
	
	public void caOutputGeneration() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		analysisType = appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAnalysisType();

		
			
			caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst();

			
			caResDispCreatorInst.initializationOfPlotArrays();
			caResDispCreatorInst.remoVePreviousResults();

			caResDispCreatorInst.removingExistingRowsOfOutputTables();

			caResDispCreatorInst.populateAvailableResultsWithPlots();
			
			plotRetrievingInst = new PlotsOutputDisplayer();
			plotRetrievingInst.setPlotsToGUI();

			dispResultInstance = new TablesResultDisplayer();

			if (analysisType.equals("pk")) {
				dispResultInstance.displayingResultsInTablesForPk();

				for (int i = 0; i < appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPkInfo()
						.getWorkSheetOutputs().getTextoutput().size(); i++) {
					caResDispCreatorInst.completeTextOutputTextArea
							.append(appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getWorkBooksArrayList()
													.get(
															appInfo
																	.getProjectInfoAL()
																	.get(
																			appInfo
																					.getSelectedProjectIndex())
																	.getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getPkInfo().getWorkSheetOutputs()
									.getTextoutput().get(i));
				}
				caResDispCreatorInst.completeTextOutputTextArea
						.setCaretPosition(0);
			}

		 else if (analysisType.equals("pd")) {
				dispResultInstance.displayingResultsInTablesForPd();

				for (int i = 0; i < appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPdInfo()
						.getWorkSheetOutputs().getTextoutput().size(); i++) {
					caResDispCreatorInst.completeTextOutputTextArea
							.append(appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getWorkBooksArrayList()
													.get(
															appInfo
																	.getProjectInfoAL()
																	.get(
																			appInfo
																					.getSelectedProjectIndex())
																	.getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getPdInfo().getWorkSheetOutputs()
									.getTextoutput().get(i));
				}
				caResDispCreatorInst.completeTextOutputTextArea
						.setCaretPosition(0);
			} else if (analysisType.equals("mm")) {
				dispResultInstance.displayingResultsInTablesForMm();

				for (int i = 0; i < appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getMmInfo()
						.getWorkSheetOutputs().getTextoutput().size(); i++) {
					caResDispCreatorInst.completeTextOutputTextArea
							.append(appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getWorkBooksArrayList()
													.get(
															appInfo
																	.getProjectInfoAL()
																	.get(
																			appInfo
																					.getSelectedProjectIndex())
																	.getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getMmInfo().getWorkSheetOutputs()
									.getTextoutput().get(i));
				}

				caResDispCreatorInst.completeTextOutputTextArea
						.setCaretPosition(0);
			} else if (analysisType.equals("pkpdlink")) {
				dispResultInstance.displayingResultsInTablesForPkPdLink();

				for (int i = 0; i < appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getPkpdLinkInfo().getWorkSheetOutputs()
						.getTextoutput().size(); i++) {
					caResDispCreatorInst.completeTextOutputTextArea
							.append(appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getWorkBooksArrayList()
													.get(
															appInfo
																	.getProjectInfoAL()
																	.get(
																			appInfo
																					.getSelectedProjectIndex())
																	.getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getPkpdLinkInfo().getWorkSheetOutputs()
									.getTextoutput().get(i));
				}
				caResDispCreatorInst.completeTextOutputTextArea
						.setCaretPosition(0);
			} else if (analysisType.equals("irm")) {
				dispResultInstance.displayingResultsInTablesForIrm();

				for (int i = 0; i < appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getIrInfo()
						.getWorkSheetOutputs().getTextoutput().size(); i++) {
					caResDispCreatorInst.completeTextOutputTextArea
							.append(appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getWorkBooksArrayList()
													.get(
															appInfo
																	.getProjectInfoAL()
																	.get(
																			appInfo
																					.getSelectedProjectIndex())
																	.getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getIrInfo().getWorkSheetOutputs()
									.getTextoutput().get(i));
				}
				caResDispCreatorInst.completeTextOutputTextArea
						.setCaretPosition(0);
			}
			else if (analysisType.equals("ascii")) {
				dispResultInstance.displayingResultsInTablesForAscii();

				for (int i = 0; i < appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getAsciiInfo()
						.getWorkSheetOutputs().getTextoutput().size(); i++) {
					caResDispCreatorInst.completeTextOutputTextArea
							.append(appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getWorkBooksArrayList()
													.get(
															appInfo
																	.getProjectInfoAL()
																	.get(
																			appInfo
																					.getSelectedProjectIndex())
																	.getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getAsciiInfo().getWorkSheetOutputs()
									.getTextoutput().get(i));
				}
				caResDispCreatorInst.completeTextOutputTextArea
						.setCaretPosition(0);
			}


		

		StoreDosingTableHeader storeDosingTableHeaderInst = new StoreDosingTableHeader();

		storeDosingTableHeaderInst.storingTextOutput();

		if (analysisType.equals("pd") || analysisType.equals("ascii")) {

		} else {
			storeDosingTableHeaderInst.storeDosingTableHeader();
		}

		makeResultsUneditable();
		//StoreAppinfo();
	}
	
	
	private void StoreAppinfo()
	{
		WriteAppinfoForUnitTest inst = new WriteAppinfoForUnitTest();		
		if(analysisType.equals("pk"))
		{

			inst.storeAppInfoForUnitTesting("PkHorizontalParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkVerticalParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkMinProcess.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkCondNumber.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkSecondParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkNonTSecondParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkDiagnostic.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkSummary.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPredVal.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkInitParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkCorrMatrix.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkParDerivative.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkVarCovarMatrix.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkDosing.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkUseSettings.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkEigenVal.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkHistory.ser", "testcases");
			
			inst.storeAppInfoForUnitTesting("PkTextOutput.ser", "testcases");
		}else if(analysisType.equals("pd"))
		{
			
			inst.storeAppInfoForUnitTesting("PdHorizontalParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdVerticalParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdMinProcess.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdCondNumber.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdSecondParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdNonTSecondParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdDiagnostic.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdSummary.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdPredVal.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdInitParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdCorrMatrix.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdParDerivative.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdVarCovarMatrix.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdDosing.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdUseSettings.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdEigenVal.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PdHistory.ser", "testcases");
			
			inst.storeAppInfoForUnitTesting("PdTextOutput.ser", "testcases");
		
		}else if(analysisType.equals("mm"))
		{

			inst.storeAppInfoForUnitTesting("MmHorizontalParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmVerticalParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmMinProcess.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmCondNumber.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmSecondParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmNonTSecondParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmDiagnostic.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmSummary.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmPredVal.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmInitParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmCorrMatrix.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmParDerivative.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmVarCovarMatrix.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmDosing.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmUseSettings.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmEigenVal.ser", "testcases");
			inst.storeAppInfoForUnitTesting("MmHistory.ser", "testcases");
			
			inst.storeAppInfoForUnitTesting("MmTextOutput.ser", "testcases");
		
		}else if(analysisType.equals("pkpdlink"))
		{

			inst.storeAppInfoForUnitTesting("PkPdLinkHorizontalParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkVerticalParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkMinProcess.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkCondNumber.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkSecondParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkNonTSecondParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkDiagnostic.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkSummary.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkPredVal.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkInitParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkCorrMatrix.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkParDerivative.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkVarCovarMatrix.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkDosing.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkUseSettings.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkEigenVal.ser", "testcases");
			inst.storeAppInfoForUnitTesting("PkPdLinkHistory.ser", "testcases");
			
			inst.storeAppInfoForUnitTesting("PkPdLinkTextOutput.ser", "testcases");
		
			
		}else if(analysisType.equals("irm"))
		{
			inst.storeAppInfoForUnitTesting("IrmHorizontalParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmVerticalParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmMinProcess.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmCondNumber.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmSecondParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmNonTSecondParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmDiagnostic.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmSummary.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmPredVal.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmInitParam.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmCorrMatrix.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmParDerivative.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmVarCovarMatrix.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmDosing.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmUseSettings.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmEigenVal.ser", "testcases");
			inst.storeAppInfoForUnitTesting("IrmHistory.ser", "testcases");
			
			inst.storeAppInfoForUnitTesting("IrmTextOutput.ser", "testcases");
		}
	}
	
	

	
	public void makeResultsUneditable() throws RowsExceededException,
			WriteException, BiffException, IOException {

		caResDispCreatorInst = CaResultDispCompCreator.createCaResDispCompInst();
		
		caResDispCreatorInst.completeTextOutputTextArea
				.setEditable(false);
		caResDispCreatorInst.finalparametersVerticalDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.finalParametersHorizontalDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.initialParameterDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.minimizationProcessDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.dosingDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.correlationMatrixDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.eigenValuesDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.conditionNumberDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.predictedValueDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.varienceCovarienceMatrixDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.summaryTableDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.diagnosticsDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.partialDerivativeDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.secondaryParameterDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.nonTransposedSecondaryParameterDisplayTable
				.setEnabled(false);
		caResDispCreatorInst.historyDisplayTable
				.setEnabled(false);

	}
}
