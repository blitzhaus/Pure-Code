package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.StringUtils;

import Common.ThousandsSeperator;

public class FillTablesWithRespectiveData extends NcaOutputGeneration {

	public static FillTablesWithRespectiveData FILL_TABLES = null;

	public static FillTablesWithRespectiveData createFillTabist() {
		if (FILL_TABLES == null) {
			FILL_TABLES = new FillTablesWithRespectiveData(
					"just object creation");
		}
		return FILL_TABLES;

	}

	String[] horizontalS;

	public FillTablesWithRespectiveData(String dummy) {

	}

	void fillHorizontalTable() throws RowsExceededException, WriteException,
			BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int numberOfProfiles = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject();


		for (int i = 0; i < numberOfProfiles; i++) {

			determineTheParNamesFileForThisProfile(i);
			ArrayList<String> parNames = fillListFromFile(NcaOutputGeneration
					.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable);
			ArrayList<String> thisProfileParValues = getThisProfValuesFromOutputInAppInfo(parNames);
			String s[] = appendFinalParametersToHorzTable(parNames, thisProfileParValues, i);
			int rowCount = NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
					.getRowCount();
			((DefaultTableModel) NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
					.getModel()).insertRow(rowCount, s);
		}

	}

	private void determineTheParNamesFileForThisProfile(int profileNumber)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int modelType = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getModelType();

		int sortVarSize = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();

		int rootOfAdmin = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getRootOfAdmistration();

		boolean isSparse = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getisSparseSelected();

		String tau = null;
		if (modelType == 0) {

			tau = appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getDosingDataObj().getDosingValueAt(
							profileNumber, (sortVarSize + 2));

		}

		if (modelType == 0) { // plasma
			if (rootOfAdmin == 0) {// po
				if (isSparse == true) {// sparse
					if (tau == null) {// no tau
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Sparse PO No tau.txt";
					} else {
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Sparse PO All have Tau.txt";
					}
				} else if (isSparse == false) {// serial
					if (tau == null) {// no tau
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma PO Serial No Tau.txt";
					} else {
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma PO Serial All have Tau.txt";
					}
				}
			} else if (rootOfAdmin == 1) {
				// po
				if (isSparse == true) {// sparse
					if (tau == null) {// no tau
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Sparse IV no tau.txt";
					} else {
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma IV Sparse All have TAU.txt";
					}
				} else if (isSparse == false) {// serial
					if (tau == null) {// no tau
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OPSerialIvPlasmaSingleDoseParameters.txt";
					} else {
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Serial IV all profiles has tau.txt";
					}
				}

			} else if (rootOfAdmin == 2) {

				// po
				if (isSparse == true) {// sparse
					if (tau == null) {// no tau
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Infusion Sparse No Tau.txt";
					} else {
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Infusion Sparse all have tau.txt";
					}
				} else if (isSparse == false) {// serial
					if (tau == null) {// no tau
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Infusion Serial No tau.txt";
					} else {
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Infusion Serial all have tau.txt";
					}
				}

			} else if (rootOfAdmin == 3) {
				if (appInfo
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
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getRouteOfAdminForDosingDefined()[profileNumber] == 0) {

					if (isSparse == true) {
						if (tau == null) {
							NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Sparse PO No tau.txt";
						} else {
							NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Sparse PO All have Tau.txt";
						}

					} else if (isSparse == false) {
						if (tau == null) {
							NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma PO Serial No Tau.txt";
						} else {
							NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma PO Serial All have Tau.txt";
						}

					}
				} else if (appInfo
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
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getRouteOfAdminForDosingDefined()[profileNumber] == 1) {

					if (isSparse == true) {
						if (tau == null) {
							NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Sparse IV no tau.txt";
						} else {
							NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma IV Sparse All have TAU.txt";
						}

					} else if (isSparse == false) {
						if (tau == null) {
							NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OPSerialIvPlasmaSingleDoseParameters.txt";
						} else {
							NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Serial IV all profiles has tau.txt";
						}

					}

				} else if (appInfo
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
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getRouteOfAdminForDosingDefined()[profileNumber] == 2) {
					if (isSparse == true) {
						if (tau == null) {
							NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Infusion Sparse No Tau.txt";
						} else {
							NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Infusion Sparse all have tau.txt";
						}

					} else if (isSparse == false) {
						if (tau == null) {
							NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Infusion Serial No tau.txt";
						} else {
							NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Infusion Serial all have tau.txt";
						}

					}

				}
			}
		} else { // urine
			if (isSparse == true) {// sparse
				NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Urine Sparse IVPOInfusion Parameters.txt";

			} else { // serial
				NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Urine Serial IVPOnfusion Parameters.txt";
			}
		}

	}

	WorkSheetOutputs wsoutputInst;
	private ArrayList<String> getThisProfValuesFromOutputInAppInfo(
			ArrayList<String> parNames) throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ArrayList<String> thisProfileParValues = new ArrayList<String>();

		if (appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getWorkSheetOutputs().getParameterValuesAL().size() > 0) {

			for (int i = 0; i < parNames.size(); i++) {
				thisProfileParValues
						.add(appInfo
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
								.getNcaInfo()
								.getWorkSheetOutputs()
								.getParameterValuesAL()
								.get(
										NcaOutputGeneration
												.createNcaOutGeneration().parameterValueIndex));
				NcaOutputGeneration.createNcaOutGeneration().parameterValueIndex++;
			}

		}

		return thisProfileParValues;
	}

	private String[] appendFinalParametersToHorzTable(ArrayList<String> parNames,
			ArrayList<String> thisProfileParValues, int profileNumber)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int sortVarSize = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();

		int carryAlongSize = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getCarryAlongVariablesListVector().size();
		String s[] = new String[NcaOutputGeneration.createNcaOutGeneration().headerParNamesList
				.size()
				+ sortVarSize + carryAlongSize];

		// fill the profile's sort variable values
		for (int i = 0; i < sortVarSize; i++) {

			s[i] = appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getDataSortVariables()[profileNumber][i];
		}

		// fill the profile's carry along values
		for (int i = 0; i < carryAlongSize; i++) {

			s[i + sortVarSize] = appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getCarryAlongData()[profileNumber][i];
		}

		int optDec = appInfo
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
								.getSelectedSheetIndex()).getColumnPropertiesArrayList()
								.get( appInfo
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
								.getSelectedSheetIndex()).getColumnNumberClicked()).getOptionalDecimals();
		String reqDec = appInfo
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
								.getSelectedSheetIndex()).getColumnPropertiesArrayList()
								.get( appInfo
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
								.getSelectedSheetIndex()).getColumnNumberClicked()).getRequiredDecimals();
		for (int i = 0; i < parNames.size(); i++) {
			String nameFromFile = parNames.get(i);
			for (int j = 0; j < NcaOutputGeneration.createNcaOutGeneration().headerParNamesList
					.size(); j++) {
				if (nameFromFile.equals(NcaOutputGeneration
						.createNcaOutGeneration().headerParNamesList.get(j))) {
					if ((thisProfileParValues.size() > 0)
							&& (thisProfileParValues.get(i).equals("Missing"))) {
						s[sortVarSize + carryAlongSize + j] = thisProfileParValues
								.get(i);
					} else if ((thisProfileParValues.size() > 0)
							&& (thisProfileParValues.get(i).equals("Missing") == false)) {
						s[sortVarSize + carryAlongSize + j] = ThousandsSeperator
								.createThouSepInst()
								.insertCommas(
										performFormatting(
												Double
														.parseDouble(thisProfileParValues
																.get(i)),
												optDec, reqDec));
					}

					break;
				}
			}
		}
		return s;
	}

	void fillVertTableAndTextWithRespectiveData(int j) throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Vector<String> textOutputVector = new Vector<String>();
		String tempS = new String();
		tempS = "";

		// for tables
		String[] s = new String[appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 3];
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			s[i] = appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getDataSortVariables()[j][i];
		}

		// compute sum
		int sum = 0;
		for (int k = 0; k < j; k++) {
			sum = sum
					+ appInfo
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
							.getNcaInfo().getProcessingInputs()
							.getProfileInfoObj().getSubject_obs()[k];
		}

		// append the tcs header for text output
		headerOfTextOutput();

		// for text output, appending the profile information
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
					.append(appInfo
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
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.get(i)
							+ " = "
							+ appInfo
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
									.getNcaInfo().getProcessingInputs()
									.getProfileInfoObj().getDataSortVariables()[j][i]
							+ ", ");
		}

		// appending the profile, date, time and settings to the text output
		printProfileInfo(textOutputVector, appInfo, j, sum, tempS);
		printDataTime(tempS, textOutputVector);
		printSettings(
				tempS,
				textOutputVector,
				appInfo,
				j,
				appInfo
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
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getTimeUnit(),
				NcaOptionsGui.createNcaOptionsInstance().normalizationUnit
						.getItemAt(
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
										.getNcaInfo().getProcessingInputs()
										.getModelInputsObj()
										.getNormalizationUnit()).toString(),
				appInfo
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
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getDoseUnits());

		for (int i = 0; i < textOutputVector.size(); i++) {
			NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
					.append(textOutputVector.get(i));
			NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
					.append("\r\n");
		}
		String[] ycalc = new String[appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetailsOf(
						j).getPredictedConc().length];
		String[] termX = new String[appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetailsOf(
						j).getPredictedConc().length];
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetailsOf(
						j).getPredictedConc().length; i++) {

			ycalc[i] = appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getLambdazDataobj()
					.getLambdaZDetailsOf(j).getPredictedConc()[i]
					+ "";

			termX[i] = appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getLambdazDataobj()
					.getLambdaZDetailsOf(j).getTimeForPredVal()[i]
					+ "";

		}

		String timeunit = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj().getTimeUnit();
		String concUnit = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj()
				.getConcMassUnit();
		String volumeUnit = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj()
				.getVolumeUnit();

		double[] aucValue = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getWorkSheetOutputs().getProfileRelateroutput().get(j)
				.getAucValue();
		double[] aumcValue = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getWorkSheetOutputs().getProfileRelateroutput().get(j)
				.getAumcValue();
		double[] xp = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getWorkSheetOutputs().getProfileRelateroutput().get(j).getXp();
		double[] yp = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getWorkSheetOutputs().getProfileRelateroutput().get(j).getYp();
		int weightingIndex = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getWeightingOption();

		// Appending summary table in the complete output.
		appendSummaryTableToTextAndTables(xp, yp, timeunit, concUnit,
				volumeUnit, ycalc, termX, aucValue, aumcValue, weightingIndex,
				j);
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append("\r\n");

		// sending final parameters to text output.
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append("Final Parameters");

		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append("\r\n\r\n");

		appendFinalParametersToTextAndVertTable(j, s, sum);
	}

	private void headerOfTextOutput() throws RowsExceededException,
			WriteException, BiffException, IOException {
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "\r\n");
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append(StringUtils.rightPad("", 30)
						+ "\t"
						+ StringUtils.center("TATA CONSULTANCY SERVICES LTD. ",
								50) + "\r\n");
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append(StringUtils.rightPad("", 30)
						+ "\t"
						+ StringUtils
								.rightPad(
										"DRUG DEVELOPMENT PLATFORM, NON-COMPARTMENTAL ANALYSIS ",
										50) + "\r\n");
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append(StringUtils.rightPad("", 30) + "\t"
						+ StringUtils.center("Version 1.0 30JUNE2012", 50)
						+ "\r\n");
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
						+ "\r\n");
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append("\r\n");
	}

	private void appendFinalParametersToTextAndVertTable(int profileNumber,

	String[] s, int sum) throws RowsExceededException, WriteException,
			BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ArrayList<String> parameterNamesList = new ArrayList<String>();
		determineTheParNamesFileForThisProfile(profileNumber);
		parameterNamesList = fillListFromFile(NcaOutputGeneration
				.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable);
		int sortVarListSize = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();

		int optDec = appInfo
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
								.getColumnPropertiesArrayList().get( appInfo
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
								.getSelectedSheetIndex()).getColumnNumberClicked()).getOptionalDecimals();
		String reqDec = appInfo
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
								.getColumnPropertiesArrayList().get( appInfo
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
								.getSelectedSheetIndex()).getColumnNumberClicked()).getRequiredDecimals();
		for (int i = 0; i < parameterNamesList.size(); i++) {

			if (parameterNamesList.get(i).equals("")) {
				horizontalS[sortVarListSize + i] = "";
				i++;

			} else {
				// s is for vertical table
				s[sortVarListSize] = parameterNamesList.get(i);
				s[sortVarListSize + 1] = NcaParameterFileDetermination
				.createNcaParDetInst().parameterUnitsHM.get(parameterNamesList.get(i))
						
						+ "";//getSimilarUnits(NcaParameterFileDetermination
				//.createNcaParDetInst().parameterUnitsHM, parameterNamesList.get(i))

				if (appInfo
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
										.getSelectedSheetIndex()).getNcaInfo()
						.getWorkSheetOutputs().getParameterValuesAL().get(
								parameterValueIndex).equals("Missing")) {
					s[sortVarListSize + 2] = appInfo
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
							.getNcaInfo().getWorkSheetOutputs()
							.getParameterValuesAL().get(parameterValueIndex);
				} else {
					s[sortVarListSize + 2] = ThousandsSeperator
							.createThouSepInst()
							.insertCommas(
									performFormatting(
											Double
													.parseDouble(appInfo
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
															.getNcaInfo()
															.getWorkSheetOutputs()
															.getParameterValuesAL()
															.get(
																	parameterValueIndex)),
											optDec, reqDec));
				}
				// value
				// from
				// the
				// appInfo
				// given
				// by
				// server.

				// now insert s as the next row in the vertical table
				int rowCount = NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable
						.getRowCount();
				((DefaultTableModel) NcaResultDispComp
						.createNcaResDispCompInst().finalparametersVerticalDisplayTable
						.getModel()).insertRow(rowCount, s);

				// now send the parameter and the corresponding units
				// to text output

				NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
						.append(StringUtils.rightPad(parameterNamesList.get(i),
								30)
								+ "\t"
								+ StringUtils
										.rightPad(
												NcaParameterFileDetermination
														.createNcaParDetInst().parameterUnitsHM
														.get(parameterNamesList
																.get(i)), 30)
								+ "\t"
								+ StringUtils.rightPad(
										(s[sortVarListSize + 2]), 25) + "\r\n");
				parameterValueIndex++;

			}

		}

	}

	private String getSimilarUnits(HashMap<String, String> parameterUnitsHM,
			String parameterName) {
		Iterator<Entry<String, String>> it = parameterUnitsHM.entrySet().iterator();
	
		while(it.hasNext()){
		 Map.Entry pairs = (Map.Entry)it.next();
		/* if(pairs.getKey().toString().startsWith(parameterName.substring(0, ))){
			 
		 }*/
		}
		
		
		
		
		return null;
	}

	public void textOutput() {
		// TODO Auto-generated method stub

	}
}
