package Model;

import java.io.IOException;

import view.ApplicationInfo;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PlasmaInfusionSparseNoTau {

	NCA ncaInst;
	ApplicationInfo appInfo;
	String missingParamDisp;

	NCAoutputPlots ncaOutputPlotInst ;
	void calculateParameters(int j) throws RowsExceededException,
			WriteException, BiffException, IOException {

		ncaInst = NCA.creaetNCAInst();
		appInfo = ncaInst.getAppInfo();
		missingParamDisp = "Missing";

		ncaInst.decideSufficiencyOfProfile();

		if (ncaInst.ifCalculateParam == false) {
			storMissingeParamValueInResultStructure(j);

		} else {
			concRelatedParamCal(j);
			sparseRelatedParamCal(j);
			calculateAucAndAumc();

			ncaInst.MRTlast = (ncaInst.AUMClast / ncaInst.AUClast)
					- ncaInst.TI[j] / 2.0;

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
					.getLambdaZDetails()[j].getLambdaZ();

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

				ncaInst.termXStr = new String[ncaInst.Xp.length];
				ncaInst.YcalcStr = new String[ncaInst.Yp.length];

				for (int i = 0; i < ncaInst.Xp.length; i++) {
					ncaInst.termXStr[i] = "";
					ncaInst.YcalcStr[i] = "";
				}

			} else {

				calculateLambdaZRelatedParam(j);

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
						.getProcessingInputs().getLambdazDataobj()
						.getLambdaZDetails()[j].getTimeForPredVal();
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
						.getProcessingInputs().getLambdazDataobj()
						.getLambdaZDetails()[j].getPredictedConc();

				ncaInst.termXStr = new String[ncaInst.noPtsLambdaZ];
				ncaInst.YcalcStr = new String[ncaInst.noPtsLambdaZ];

			}

			if (ncaInst.noPtsLambdaZ == 0)
				ncaInst.Clast_pred = 0;
			else {

				int clast_PredIndex = ncaInst
						.lastNonZero(appInfo
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
								.getLambdazDataobj().getLambdaZDetailsOf(j)
								.getPredictedConc());
				ncaInst.Clast_pred = appInfo
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
						.getLambdaZDetailsOf(j).getPredictedConc()[clast_PredIndex];

			}

			DefaultToPreferredUnitConversion unitConversionInst = new DefaultToPreferredUnitConversion();

			double convertedDose;

			try {
				ncaInst.convert[0] = unitConversionInst.unitConversion(
						ncaInst.dose_unit[j], ncaInst.conc_mass_unit);
			} catch (Exception e) {
				ncaInst.convert[0] = 1.0;
			}
			// N.dose_unit[j]=N.conc_mass_unit;
			convertedDose = ncaInst.Dose[j] * ncaInst.convert[0];

			ncaInst.AUCall = ncaInst.round(ncaInst.AUCall, ncaInst.roundTo);
			ncaInst.AUClast = ncaInst.round(ncaInst.AUClast, ncaInst.roundTo);

			if (ncaInst.noPtsLambdaZ == 0) {

				setLambdaZAndAucRelatedParamToMissing();
			} else {

				calculateLambdaZAndAucRelatedParam(j, convertedDose);
			}
			storeParamValueInResultStructure();

		}

		String profile = determineProfile(j);

		ncaOutputPlotInst = new NCAoutputPlots();

		ncaOutputPlotInst.GenerateplottsForNCAOutput(ncaInst.Xp, ncaInst.Yp,
				ncaInst.termX, ncaInst.Ycalc, ncaInst.noPtsLambdaZ, profile);

	}

	private void storMissingeParamValueInResultStructure(int j) {

		if (ncaInst.X.length > 0) {
			ncaInst.Cmax = ncaInst.maxVal(ncaInst.Y);
			ncaInst.Cmax_D = ncaInst.Cmax / ncaInst.Dose[j];
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
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);

		if (ncaInst.X.length > 0) {

			ncaInst.parametersValue.add(ncaInst.round(ncaInst.Clast
					* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
					+ "");
			ncaInst.parametersValue.add(ncaInst.round(ncaInst.Tlast
					* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
					+ "");
			ncaInst.parametersValue.add(ncaInst.round(ncaInst.Cmax_D
					* ncaInst.unitConvertAmount[3], ncaInst.roundTo)
					+ "");
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
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);

		if (ncaInst.X.length > 0) {
			ncaInst.se_AUC(ncaInst.X, ncaInst.Y, j);
			ncaInst.parametersValue.add(ncaInst.round(ncaInst.SE_Cmax,
					ncaInst.roundTo)
					+ "");
		} else {
			ncaInst.parametersValue.add(missingParamDisp);
		}
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);

	}

	private void storeParamValueInResultStructure() {

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
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.MRTlast
				* ncaInst.unitConvertAmount[11], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.MRTinfObsStr);
		ncaInst.parametersValue.add(ncaInst.MRTinfPredStr);
		ncaInst.parametersValue.add(ncaInst.ClObsStr);
		ncaInst.parametersValue.add(ncaInst.ClPredStr);
		ncaInst.parametersValue.add(ncaInst.VzObsStr);
		ncaInst.parametersValue.add(ncaInst.VzPredStr);
		ncaInst.parametersValue.add(ncaInst.VssObsStr);
		ncaInst.parametersValue.add(ncaInst.VssPredStr);

		ncaInst.parametersValue.add(ncaInst.round(ncaInst.AUMClast
				* ncaInst.unitConvertAmount[10], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.AUMCinfObsStr);
		ncaInst.parametersValue.add(ncaInst.AUMCExtrapObsStr);
		ncaInst.parametersValue.add(ncaInst.AUMCinfPredStr);
		ncaInst.parametersValue.add(ncaInst.AUMCExtrapPredStr);
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.Clast
				* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.Tlast
				* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.Cmax_D
				* ncaInst.unitConvertAmount[3], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.AUCinf_DObsStr);
		ncaInst.parametersValue.add(ncaInst.AUCinf_DPredStr);
		ncaInst.parametersValue.add(ncaInst.RsqStr);
		ncaInst.parametersValue.add(ncaInst.adjRsqStr);
		ncaInst.parametersValue.add(ncaInst.CorrTermXYStr);
		ncaInst.parametersValue.add(ncaInst.noPtsLambdaZStr);
		ncaInst.parametersValue.add(ncaInst.lambdaZStr);
		ncaInst.parametersValue.add(ncaInst.lambdaZUpperStr);
		ncaInst.parametersValue.add(ncaInst.lambdaZLowerStr);

		ncaInst.parametersValue.add(ncaInst.round(ncaInst.SE_Cmax,
				ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.SE_AUClast,
				ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.SE_AUCall,
				ncaInst.roundTo)
				+ "");

	}

	private String determineProfile(int j) {
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
										.getDataSortVariables()[j][i] + ", ");
				System.out.println(profile);
			}
		return profile;
	}

	private void calculateLambdaZAndAucRelatedParam(int j, double convertedDose) {
		ncaInst.AUCinfObs = ncaInst.AUClast + ncaInst.Clast / ncaInst.lambdaZ;
		ncaInst.AUCinfObsStr = ncaInst.round(ncaInst.AUCinfObs
				* ncaInst.unitConvertAmount[6], ncaInst.roundTo)
				+ "";

		ncaInst.AUCinfPred = ncaInst.AUClast + ncaInst.Clast_pred
				/ ncaInst.lambdaZ;
		ncaInst.AUCinfPredStr = ncaInst.round(ncaInst.AUCinfPred
				* ncaInst.unitConvertAmount[6], ncaInst.roundTo)
				+ "";

		ncaInst.AUCinf_DObs = ncaInst.AUCinfObs / ncaInst.Dose[j];
		ncaInst.AUCinf_DObsStr = ncaInst.round(ncaInst.AUCinf_DObs
				* ncaInst.unitConvertAmount[7], ncaInst.roundTo)
				+ "";

		ncaInst.AUCinf_DPred = ncaInst.AUCinfPred / ncaInst.Dose[j];
		ncaInst.AUCinf_DPredStr = ncaInst.round(ncaInst.AUCinf_DPred
				* ncaInst.unitConvertAmount[7], ncaInst.roundTo)
				+ "";

		ncaInst.AUMCinfObs = ncaInst.AUMClast + ncaInst.Tlast * ncaInst.Clast
				/ ncaInst.lambdaZ + ncaInst.Clast
				/ Math.pow(ncaInst.lambdaZ, 2);
		ncaInst.AUMCinfObsStr = ncaInst.round(ncaInst.AUMCinfObs
				* ncaInst.unitConvertAmount[10], ncaInst.roundTo)
				+ "";

		ncaInst.AUMCinfPred = ncaInst.AUMClast + ncaInst.Tlast
				* ncaInst.Clast_pred / ncaInst.lambdaZ + ncaInst.Clast_pred
				/ Math.pow(ncaInst.lambdaZ, 2);
		ncaInst.AUMCinfPredStr = ncaInst.round(ncaInst.AUMCinfPred
				* ncaInst.unitConvertAmount[10], ncaInst.roundTo)
				+ "";

		ncaInst.AUCExtrapObs = ((ncaInst.AUCinfObs - ncaInst.AUClast) / ncaInst.AUCinfObs) * 100;
		ncaInst.AUCExtrapObsStr = ncaInst.round(ncaInst.AUCExtrapObs,
				ncaInst.roundTo)
				+ "";

		ncaInst.AUCExtrapPred = ((ncaInst.AUCinfPred - ncaInst.AUClast) / ncaInst.AUCinfPred) * 100;
		ncaInst.AUCExtrapPredStr = ncaInst.round(ncaInst.AUCExtrapPred,
				ncaInst.roundTo)
				+ "";

		ncaInst.AUMCExtrapObs = ((ncaInst.AUMCinfObs - ncaInst.AUMClast) / ncaInst.AUMCinfObs) * 100;
		ncaInst.AUMCExtrapObsStr = ncaInst.round(ncaInst.AUMCExtrapObs,
				ncaInst.roundTo)
				+ "";

		ncaInst.AUMCExtrapPred = ((ncaInst.AUMCinfPred - ncaInst.AUMClast) / ncaInst.AUMCinfPred) * 100;
		ncaInst.AUMCExtrapPredStr = ncaInst.round(ncaInst.AUMCExtrapPred,
				ncaInst.roundTo)
				+ "";

		ncaInst.MRTinfObs = (ncaInst.AUMCinfObs / ncaInst.AUCinfObs)
				- ncaInst.TI[j] / 2.0;
		ncaInst.MRTinfPred = (ncaInst.AUMCinfPred / ncaInst.AUCinfPred)
				- ncaInst.TI[j] / 2.0;

		ncaInst.MRTinfObsStr = ncaInst.round(ncaInst.MRTinfObs
				* ncaInst.unitConvertAmount[11], ncaInst.roundTo)
				+ "";
		ncaInst.MRTinfPredStr = ncaInst.round(ncaInst.MRTinfPred
				* ncaInst.unitConvertAmount[11], ncaInst.roundTo)
				+ "";

		ncaInst.VzObs = convertedDose / (ncaInst.lambdaZ * ncaInst.AUCinfObs);
		ncaInst.VzObsStr = ncaInst.round(ncaInst.VzObs
				* ncaInst.unitConvertAmount[8], ncaInst.roundTo)
				+ "";

		ncaInst.ClObs = convertedDose / ncaInst.AUCinfObs;
		ncaInst.ClObsStr = ncaInst.round(ncaInst.ClObs
				* ncaInst.unitConvertAmount[9], ncaInst.roundTo)
				+ "";

		ncaInst.VzPred = convertedDose / (ncaInst.lambdaZ * ncaInst.AUCinfPred);
		ncaInst.VzPredStr = ncaInst.round(ncaInst.VzPred
				* ncaInst.unitConvertAmount[8], ncaInst.roundTo)
				+ "";

		ncaInst.ClPred = convertedDose / ncaInst.AUCinfPred;
		ncaInst.ClPredStr = ncaInst.round(ncaInst.ClPred
				* ncaInst.unitConvertAmount[9], ncaInst.roundTo)
				+ "";

		ncaInst.VssObs = ncaInst.MRTinfObs * ncaInst.ClObs;
		ncaInst.VssObsStr = ncaInst.round(ncaInst.VssObs
				* ncaInst.unitConvertAmount[8], ncaInst.roundTo)
				+ "";

		ncaInst.VssPred = ncaInst.MRTinfPred * ncaInst.ClPred;
		ncaInst.VssPredStr = ncaInst.round(ncaInst.VssPred
				* ncaInst.unitConvertAmount[8], ncaInst.roundTo)
				+ "";
	}

	private void setLambdaZAndAucRelatedParamToMissing() {
		ncaInst.AUCinfObsStr = missingParamDisp;
		ncaInst.AUCinfPredStr = missingParamDisp;
		ncaInst.AUCinf_DObsStr = missingParamDisp;
		ncaInst.AUCinf_DPredStr = missingParamDisp;
		ncaInst.AUMCinfObsStr = missingParamDisp;
		ncaInst.AUMCinfPredStr = missingParamDisp;
		ncaInst.AUCExtrapObsStr = missingParamDisp;
		ncaInst.AUCExtrapPredStr = missingParamDisp;
		ncaInst.AUMCExtrapObsStr = missingParamDisp;
		ncaInst.AUMCExtrapPredStr = missingParamDisp;
		ncaInst.MRTinfObsStr = missingParamDisp;
		ncaInst.MRTinfPredStr = missingParamDisp;
		ncaInst.VzObsStr = missingParamDisp;
		ncaInst.ClObsStr = missingParamDisp;
		ncaInst.VzPredStr = missingParamDisp;
		ncaInst.ClPredStr = missingParamDisp;
		ncaInst.VssObsStr = missingParamDisp;
		ncaInst.VssPredStr = missingParamDisp;
	}

	private void calculateLambdaZRelatedParam(int j) {
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
				.getLambdaZUpper();

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
				.getLambdaZLower();

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
	}

	private void calculateAucAndAumc() {
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

	private void sparseRelatedParamCal(int j) {
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
								.getSelectedSheetIndex())
				.isFromDescriptiveStatMainPage() == false) {

			ncaInst.se_AUC(ncaInst.X, ncaInst.Y, j);
		}
	}

	private void concRelatedParamCal(int j) {
		ncaInst.Cmax = ncaInst.maxVal(ncaInst.Y);
		ncaInst.Cmax_D = ncaInst.Cmax / ncaInst.Dose[j];
		int maxCidx = ncaInst.findVal(ncaInst.Y, ncaInst.Cmax);
		ncaInst.Tmax = ncaInst.X[maxCidx];
		int ClastIdx = ncaInst.lastNonZero(ncaInst.Yp);
		ncaInst.Clast = ncaInst.Yp[ClastIdx];
		ncaInst.Tlast = ncaInst.Xp[ClastIdx];
	}

}
