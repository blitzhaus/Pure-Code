package Model;

import java.io.IOException;

import view.ApplicationInfo;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class UrineSerialIVPOnfusionParametersCal {
	NCA ncaInst;
	ApplicationInfo appInfo;
	String missingParamDisp;

	NCAoutputPlots ncaPlotOutputInst ;
	void calculateParameters(int currentProfileNumber) throws RowsExceededException,
			WriteException, BiffException, IOException {
		ncaInst = NCA.creaetNCAInst();
		appInfo = ncaInst.getAppInfo();
		missingParamDisp = "Missing";

		ncaInst.decideSufficiencyOfProfile();

		if (ncaInst.ifCalculateParam == false) {
			storMissingeParamValueInResultStructure(currentProfileNumber);

		} else {

			concRelatedParamCal();
			computeAucAndAumc();

			ncaInst.lambdaZ = appInfo
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
					.getLambdaZDetails()[currentProfileNumber].getLambdaZ();

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
					.getProcessingInputs().getModelInputsObj()
					.isDisableCurveStripping() == true
					|| ncaInst.lambdaZ == 0) {
				setLambdaZRelatedParamToMissing();
			} else {
				setLambdaZRelatedParamValFromAppinfo(currentProfileNumber);
			}

			copyConcAndVolume(currentProfileNumber);

			ncaInst.urine_parameter(ncaInst.concentration, ncaInst.volume,
					ncaInst.Dose[currentProfileNumber]);
			ncaInst.Vol_UR = ncaInst.round(ncaInst.Vol_UR
					* ncaInst.unitConvertAmount[7], ncaInst.roundTo);
			ncaInst.Amount_Recovered = ncaInst.round(ncaInst.Amount_Recovered
					* ncaInst.unitConvertAmount[3], ncaInst.roundTo);
			ncaInst.Percent_Recovered = ncaInst.round(
					ncaInst.Percent_Recovered, ncaInst.roundTo);

			if (ncaInst.rootOfAdminForCurrentProfile == 0) {
				try {
					ncaInst.Tlag = ncaInst.Xp[ncaInst.firstNonZero(ncaInst.Yp) - 1];

				} catch (Exception e) {

				}

			}

			calculateAurcLambdaZRelatedParam(currentProfileNumber);
			storeParamValueInAppInfo();

		}
		String profile = determineProfileName(currentProfileNumber);

		ncaPlotOutputInst = new NCAoutputPlots();

		ncaPlotOutputInst.GenerateplottsForNCAOutput(ncaInst.Xp, ncaInst.Yp,
				ncaInst.termX, ncaInst.Ycalc, ncaInst.noPtsLambdaZ, profile);
	}

	private void copyConcAndVolume(int currentProfileNumber) {
		ncaInst.concentration = new double[appInfo
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
				.getProcessingInputs().getProfileInfoObj().getSubject_obs()[currentProfileNumber]];
		ncaInst.volume = new double[appInfo
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
				.getProcessingInputs().getProfileInfoObj().getSubject_obs()[currentProfileNumber]];

		int noOfCol = appInfo
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
				.getProcessingInputs().getNcaDat()[0].length;
		for (int ii = 0; ii < appInfo
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
				.getProcessingInputs().getProfileInfoObj().getSubject_obs()[currentProfileNumber]; ii++) {
			ncaInst.concentration[ii] = Double
					.parseDouble(appInfo
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
							.getNcaInfo().getProcessingInputs().getNcaDat()[ncaInst.sum
							- appInfo
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
									.getProfileInfoObj().getSubject_obs()[currentProfileNumber]
							+ ii][noOfCol - 2]);

			ncaInst.volume[ii] = Double
					.parseDouble(appInfo
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
							.getNcaInfo().getProcessingInputs().getNcaDat()[ncaInst.sum
							- appInfo
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
									.getProfileInfoObj().getSubject_obs()[currentProfileNumber]
							+ ii][noOfCol - 1]);

		}

	}

	private void storMissingeParamValueInResultStructure(int currentProfileNumber) {

		if (ncaInst.X.length > 0) {
			ncaInst.Cmax = ncaInst.maxVal(ncaInst.Y);
			int maxCidx = ncaInst.findVal(ncaInst.Y, ncaInst.Cmax);
			ncaInst.Tmax = ncaInst.X[maxCidx];
			int ClastIdx = ncaInst.lastNonZero(ncaInst.Yp);
			ncaInst.Clast = ncaInst.Yp[ClastIdx];
			ncaInst.Tlast = ncaInst.Xp[ClastIdx];

			ncaInst.parametersValue.add(ncaInst.round(ncaInst.Cmax
					* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
					+ "");
			ncaInst.parametersValue.add(ncaInst.round(ncaInst.Tmax
					* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
					+ "");
		} else {
			ncaInst.parametersValue.add(missingParamDisp);
			ncaInst.parametersValue.add(missingParamDisp);

		}

		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);

		if (ncaInst.X.length > 0) {
			if (ncaInst.rootOfAdminForCurrentProfile == 0) {
				try {
					ncaInst.Tlag = ncaInst.Xp[ncaInst.firstNonZero(ncaInst.Yp) - 1];

				} catch (Exception e) {

				}

			}

			ncaInst.parametersValue.add(ncaInst.round(ncaInst.Tlag
					* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
					+ "");
			ncaInst.parametersValue.add(ncaInst.round(ncaInst.Clast
					* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
					+ "");
			ncaInst.parametersValue.add(ncaInst.round(ncaInst.Tlast
					* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
					+ "");
		} else {
			ncaInst.parametersValue.add(missingParamDisp);
			ncaInst.parametersValue.add(missingParamDisp);
			ncaInst.parametersValue.add(missingParamDisp);

		}

		if (ncaInst.X.length > 0) {
			copyConcAndVolume(currentProfileNumber);

			ncaInst.urine_parameter(ncaInst.concentration, ncaInst.volume,
					ncaInst.Dose[currentProfileNumber]);
			ncaInst.parametersValue.add(ncaInst.Vol_UR + "");
			ncaInst.parametersValue.add(ncaInst.Amount_Recovered + "");
			ncaInst.parametersValue.add(ncaInst.Percent_Recovered + "");
		} else {
			ncaInst.parametersValue.add(missingParamDisp);
			ncaInst.parametersValue.add(missingParamDisp);
			ncaInst.parametersValue.add(missingParamDisp);

		}

		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);

	}

	private void computeAucAndAumc() {
		ncaInst.AUCValue = new double[ncaInst.Xp.length];
		ncaInst.AUMCValue = new double[ncaInst.Xp.length];
		if (ncaInst.method == 0)
			ncaInst.linearlogTrapezoidal(ncaInst.Xp, ncaInst.Yp);
		else if (ncaInst.method == 1)
			ncaInst.linearUplogDownTrapezoidal(ncaInst.Xp, ncaInst.Yp);
		else if (ncaInst.method == 2)
			ncaInst.linearTrapezoidal(ncaInst.Xp, ncaInst.Yp);
		else if (ncaInst.method == 3)
			ncaInst.linearTrapezoidal(ncaInst.Xp, ncaInst.Yp);
	}

	private void concRelatedParamCal() {
		ncaInst.Cmax = ncaInst.maxVal(ncaInst.Y);
		int maxCidx = ncaInst.findVal(ncaInst.Y, ncaInst.Cmax);
		ncaInst.Tmax = ncaInst.X[maxCidx];
		int ClastIdx = ncaInst.lastNonZero(ncaInst.Yp);
		ncaInst.Clast = ncaInst.Yp[ClastIdx];
		ncaInst.Tlast = ncaInst.Xp[ClastIdx];
	}

	private String determineProfileName(int currentProfileNumber) {
		String profile = "";

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
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() != 0)
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
				profile = profile
						+ (appInfo
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
								.getMappingDataObj()
								.getSortVariablesListVector().get(i)
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
										.getProfileInfoObj()
										.getDataSortVariables()[currentProfileNumber][i] + ", ");

			}
		return profile;
	}

	private void storeParamValueInAppInfo() {
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.Cmax
				* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.Tmax
				* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.AUClast
				* ncaInst.unitConvertAmount[6], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.AUCall
				* ncaInst.unitConvertAmount[6], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.AUCinfObsStr);
		ncaInst.parametersValue.add(ncaInst.AUCinfPredStr);
		ncaInst.parametersValue.add(ncaInst.AUCExtrapObsStr);
		ncaInst.parametersValue.add(ncaInst.AUCExtrapPredStr);
		ncaInst.parametersValue.add(ncaInst.termHalfLifeStr);
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.Tlag
				* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.Clast
				* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.Tlast
				* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.Vol_UR + "");
		ncaInst.parametersValue.add(ncaInst.Amount_Recovered + "");
		ncaInst.parametersValue.add(ncaInst.Percent_Recovered + "");

		
		ncaInst.parametersValue.add(ncaInst.RsqStr);
		ncaInst.parametersValue.add(ncaInst.adjRsqStr);
		ncaInst.parametersValue.add(ncaInst.CorrTermXYStr);
		ncaInst.parametersValue.add(ncaInst.noPtsLambdaZStr);
		ncaInst.parametersValue.add(ncaInst.lambdaZStr);
		ncaInst.parametersValue.add(ncaInst.lambdaZUpperStr);
		ncaInst.parametersValue.add(ncaInst.lambdaZLowerStr);

	}

	private void calculateAurcLambdaZRelatedParam(int j) {
		DefaultToPreferredUnitConversion unitConversionInst = new DefaultToPreferredUnitConversion();

		double convertedDose;

		try {
			ncaInst.convert[0] = unitConversionInst.unitConversion(
					ncaInst.dose_unit[j], ncaInst.conc_mass_unit);
		} catch (Exception e) {
			ncaInst.convert[0] = 1.0;
		}

		convertedDose = ncaInst.Dose[j] * ncaInst.convert[0];

		if (ncaInst.noPtsLambdaZ == 0) {
			ncaInst.AUCinfObsStr = missingParamDisp;
			ncaInst.AUCinfPredStr = missingParamDisp;
			ncaInst.AUCExtrapObsStr = missingParamDisp;
			ncaInst.AUCExtrapPredStr = missingParamDisp;
		} else {
			ncaInst.AUCinfObs = ncaInst.AUClast + ncaInst.Clast
					/ ncaInst.lambdaZ;
			ncaInst.AUCinfObsStr = ncaInst.round(ncaInst.AUCinfObs
					* ncaInst.unitConvertAmount[6], ncaInst.roundTo)
					+ "";

			ncaInst.AUCinfPred = ncaInst.AUClast + ncaInst.Clast_pred
					/ ncaInst.lambdaZ;
			ncaInst.AUCinfPredStr = ncaInst.round(ncaInst.AUCinfPred
					* ncaInst.unitConvertAmount[6], ncaInst.roundTo)
					+ "";

			ncaInst.AUCExtrapObs = ((ncaInst.AUCinfObs - ncaInst.AUClast) / ncaInst.AUCinfObs) * 100;
			ncaInst.AUCExtrapObsStr = ncaInst.round(ncaInst.AUCExtrapObs,
					ncaInst.roundTo)
					+ "";

			ncaInst.AUCExtrapPred = ((ncaInst.AUCinfPred - ncaInst.AUClast) / ncaInst.AUCinfPred) * 100;
			ncaInst.AUCExtrapPredStr = ncaInst.round(ncaInst.AUCExtrapPred,
					ncaInst.roundTo)
					+ "";
		}

	}

	private void setLambdaZRelatedParamValFromAppinfo(int j) {
		ncaInst.lambdaZ = appInfo
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
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[j]
				.getLambdaZ();
		ncaInst.Rsq = appInfo
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
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[j]
				.getrSquare();
		ncaInst.adjRsq = appInfo
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
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[j]
				.getAdjRSquare();

		ncaInst.noPtsLambdaZ = appInfo
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
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[j]
				.getNoPtsLambdaZ();

		ncaInst.CorrTermXY = appInfo
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
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[j]
				.getCorrelation();

		ncaInst.lambdaZLower = appInfo
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
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[j]
				.getLambdaZLower();
		ncaInst.lambdaZUpper = appInfo
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
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[j]
				.getLambdaZUpper();

		ncaInst.termX = new double[ncaInst.noPtsLambdaZ];
		ncaInst.Ycalc = new double[ncaInst.noPtsLambdaZ];

		ncaInst.termX = appInfo
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
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[j]
				.getTimeForPredVal();
		ncaInst.Ycalc = appInfo
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
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[j]
				.getPredictedConc();

		ncaInst.termXStr = new String[ncaInst.noPtsLambdaZ];
		ncaInst.YcalcStr = new String[ncaInst.noPtsLambdaZ];

		ncaInst.noPtsLambdaZStr = ncaInst.noPtsLambdaZ + "";
		ncaInst.lambdaZStr = ncaInst.lambdaZ + "";
		ncaInst.RsqStr = ncaInst.Rsq + "";
		ncaInst.adjRsqStr = ncaInst.adjRsq + "";
		ncaInst.lambdaZLowerStr = ncaInst.round(ncaInst.lambdaZLower
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "";
		ncaInst.lambdaZUpperStr = ncaInst.round(ncaInst.lambdaZUpper
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "";
		ncaInst.CorrTermXYStr = ncaInst.CorrTermXY + "";

		ncaInst.termHalfLife = Math.log(2) / ncaInst.lambdaZ;
		ncaInst.termHalfLifeStr = ncaInst.round(ncaInst.termHalfLife
				* ncaInst.unitConvertAmount[5], ncaInst.roundTo)
				+ "";

	}

	private void setLambdaZRelatedParamToMissing() {
		ncaInst.noPtsLambdaZStr = missingParamDisp;
		ncaInst.lambdaZStr = missingParamDisp;
		ncaInst.RsqStr = missingParamDisp;
		ncaInst.adjRsqStr = missingParamDisp;
		ncaInst.lambdaZLowerStr = missingParamDisp;
		ncaInst.lambdaZUpperStr = missingParamDisp;
		ncaInst.CorrTermXYStr = missingParamDisp;
		ncaInst.termHalfLifeStr = missingParamDisp;
		ncaInst.termXStr = new String[ncaInst.Xp.length];
		ncaInst.YcalcStr = new String[ncaInst.Yp.length];

		for (int i = 0; i < ncaInst.Xp.length; i++) {
			ncaInst.termXStr[i] = "";
			ncaInst.YcalcStr[i] = "";
		}
	}
}
