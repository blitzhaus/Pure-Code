package Model;

import java.io.IOException;

import view.ApplicationInfo;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PlasmaSerialIVAllProfilesHasTtauParamCal {
	NCA ncaInst;
	ApplicationInfo appInfo;
	String missingParamDisp;

	NCAoutputPlots ncaPlotOutputInst;

	void calculateParameters(int j) throws RowsExceededException,
			WriteException, BiffException, IOException {

		ncaInst = NCA.creaetNCAInst();
		appInfo = ncaInst.getAppInfo();
		missingParamDisp = "Missing";

		ncaInst.decideSufficiencyOfProfile();

		if (ncaInst.ifCalculateParam == false) {
			storMissingeParamValueInResultStructure(j);

		} else {

			ncaInst.Cmax = ncaInst.maxVal(ncaInst.Y);
			ncaInst.Cmax_D = ncaInst.Cmax / ncaInst.Dose[j];

			ncaInst.Cmin = ncaInst.minVal_tau(ncaInst.X, ncaInst.Y,
					ncaInst.dosingTime[j], ncaInst.tau[j]);
			ncaInst.Tmin = ncaInst.X[ncaInst.findVal(ncaInst.Y, ncaInst.Cmin)];
			ncaInst.Cmax = ncaInst.maxVal_tau(ncaInst.X, ncaInst.Y,
					ncaInst.dosingTime[j], ncaInst.tau[j]);
			ncaInst.Tmax = ncaInst.X[ncaInst.findVal(ncaInst.Y, ncaInst.Cmax)];

			int ClastIdx = ncaInst.lastNonZero(ncaInst.Yp);
			ncaInst.Clast = ncaInst.Yp[ClastIdx];
			ncaInst.Tlast = ncaInst.Xp[ClastIdx];

			calculateAucAndAumc();

			if (ncaInst.method == 0)
				ncaInst.linearlogTrapezoidalTau(ncaInst.Xp, ncaInst.Yp,
						ncaInst.tau[j]);
			else if (ncaInst.method == 1)
				ncaInst.linearUplogDownTrapezoidalTau(ncaInst.Xp, ncaInst.Yp,
						ncaInst.tau[j]);
			else if (ncaInst.method == 2)
				ncaInst.linearTrapezoidalTau(ncaInst.Xp, ncaInst.Yp,
						ncaInst.tau[j]);
			else if (ncaInst.method == 3)
				ncaInst.linearTrapezoidalTau(ncaInst.Xp, ncaInst.Yp,
						ncaInst.tau[j]);
			// AUC from o to tau.for Cavg we require AUC from dt to Tau

			ncaInst.MRTlast = ncaInst.AUMClast / ncaInst.AUClast;

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

				setLambdaZRelatedParmValFromAppInfo(j);

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

			ncaInst.Cavg = ncaInst.AUC_tau
					/ (ncaInst.dosingTime[j] + ncaInst.tau[j]);
			ncaInst.Fluctuation = 100 * (ncaInst.Cmax - ncaInst.Cmin)
					/ ncaInst.Cavg;
			ncaInst.Accumulation_index = 1 / Math.abs((1 - Math.exp((-1)
					* ncaInst.lambdaZ * ncaInst.tau[j])));
			ncaInst.CLss = convertedDose / ncaInst.AUC_tau;
			ncaInst.CLssStr = ncaInst.round(ncaInst.CLss
					* ncaInst.unitConvertAmount[9], ncaInst.roundTo)
					+ "";

			if (ncaInst.noPtsLambdaZ == 0) {
				ncaInst.VzStr = missingParamDisp;
				ncaInst.MRTinfObsStr = missingParamDisp;
				ncaInst.MRTinfPredStr = missingParamDisp;
				ncaInst.VssObsStr = missingParamDisp;
				ncaInst.VssPredStr = missingParamDisp;
			} else {
				ncaInst.Vz = convertedDose
						/ (ncaInst.lambdaZ * ncaInst.AUC_tau);
				ncaInst.VzStr = ncaInst.round(ncaInst.Vz
						* ncaInst.unitConvertAmount[8], ncaInst.roundTo)
						+ "";

				ncaInst.MRTinfObs = (ncaInst.AUMC_tau + ncaInst.tau[j]
						* (ncaInst.AUCinfObs - ncaInst.AUC_tau))
						/ (ncaInst.AUC_tau);
				ncaInst.MRTinfPred = (ncaInst.AUMC_tau + ncaInst.tau[j]
						* (ncaInst.AUCinfPred - ncaInst.AUC_tau))
						/ (ncaInst.AUC_tau);
				ncaInst.MRTinfObsStr = ncaInst.round(ncaInst.MRTinfObs
						* ncaInst.unitConvertAmount[11], ncaInst.roundTo)
						+ "";
				ncaInst.MRTinfPredStr = ncaInst.round(ncaInst.MRTinfPred
						* ncaInst.unitConvertAmount[11], ncaInst.roundTo)
						+ "";

				ncaInst.VssObs = ncaInst.MRTinfObs * ncaInst.CLss;
				ncaInst.VssPred = ncaInst.MRTinfPred * ncaInst.CLss;
				ncaInst.VssObsStr = ncaInst.round(ncaInst.VssObs
						* ncaInst.unitConvertAmount[8], ncaInst.roundTo)
						+ "";
				ncaInst.VssPredStr = ncaInst.round(ncaInst.VssPred
						* ncaInst.unitConvertAmount[8], ncaInst.roundTo)
						+ "";

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

			aucRelatedParamCal(j, convertedDose);
			storeParamValueInAppInfo();
		}
		String profile = determineProfileName(j);

		ncaPlotOutputInst = new NCAoutputPlots();

		ncaPlotOutputInst.GenerateplottsForNCAOutput(ncaInst.Xp, ncaInst.Yp,
				ncaInst.termX, ncaInst.Ycalc, ncaInst.noPtsLambdaZ, profile);
		/*************************************************/

	}

	private void storMissingeParamValueInResultStructure(int j) {

		if (ncaInst.X.length > 0) {
			ncaInst.Cmax = ncaInst.maxVal(ncaInst.Y);
			ncaInst.Cmax_D = ncaInst.Cmax / ncaInst.Dose[j];

			ncaInst.Cmax = ncaInst.maxVal_tau(ncaInst.X, ncaInst.Y,
					ncaInst.dosingTime[j], ncaInst.tau[j]);
			ncaInst.Tmax = ncaInst.X[ncaInst.findVal(ncaInst.Y, ncaInst.Cmax)];
			ncaInst.Cmin = ncaInst.minVal_tau(ncaInst.X, ncaInst.Y,
					ncaInst.dosingTime[j], ncaInst.tau[j]);
			ncaInst.Tmin = ncaInst.X[ncaInst.findVal(ncaInst.Y, ncaInst.Cmin)];

			ncaInst.parametersValue.add(ncaInst.round(ncaInst.C0
					* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
					+ "");
			ncaInst.parametersValue.add(ncaInst.round(ncaInst.Cmax
					* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
					+ "");
			ncaInst.parametersValue.add(ncaInst.round(ncaInst.Tmax
					* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
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
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);

		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);

		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);

		if (ncaInst.X.length > 0) {
			int ClastIdx = ncaInst.lastNonZero(ncaInst.Yp);
			ncaInst.Clast = ncaInst.Yp[ClastIdx];
			ncaInst.Tlast = ncaInst.Xp[ClastIdx];
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
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);

		if (ncaInst.X.length > 0) {
			ncaInst.parametersValue.add(ncaInst.round(ncaInst.Cmin
					* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
					+ "");
			ncaInst.parametersValue.add(ncaInst.round(ncaInst.Tmin
					* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
					+ "");
		} else {
			ncaInst.parametersValue.add(missingParamDisp);
			ncaInst.parametersValue.add(missingParamDisp);
		}

		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);
		ncaInst.parametersValue.add(missingParamDisp);

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

	private void setLambdaZRelatedParmValFromAppInfo(int j) {
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
		ncaInst.lambdaZLowerStr = ncaInst.round(ncaInst.lambdaZLower
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "";
		ncaInst.lambdaZUpperStr = ncaInst.round(ncaInst.lambdaZUpper
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "";

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

	private String determineProfileName(int j) {
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

	private void storeParamValueInAppInfo() {

		ncaInst.parametersValue.add(ncaInst.round(ncaInst.C0
				* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
				+ "");
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
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.AUC_tau
				* ncaInst.unitConvertAmount[6], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.AUMC_tau
				* ncaInst.unitConvertAmount[10], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.AUCExtrapObsStr);
		ncaInst.parametersValue.add(ncaInst.AUCExtrapPredStr);
		ncaInst.parametersValue.add(ncaInst.termHalfLifeStr);
		ncaInst.parametersValue.add(ncaInst.MRTinfObsStr);
		ncaInst.parametersValue.add(ncaInst.MRTinfPredStr);

		ncaInst.parametersValue.add(ncaInst.CLssStr);
		ncaInst.parametersValue.add(ncaInst.VzStr);

		ncaInst.parametersValue.add(ncaInst.VssObsStr);
		ncaInst.parametersValue.add(ncaInst.VssPredStr);

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
		ncaInst.parametersValue.add(ncaInst.AUCBackExtrapObsStr);
		ncaInst.parametersValue.add(ncaInst.AUCBackExtrapPredStr);
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.Cmin
				* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.Tmin
				* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.Cavg
				* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.Fluctuation,
				ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(ncaInst.Accumulation_index,
				ncaInst.roundTo)
				+ "");

	}

	private void aucRelatedParamCal(int j, double convertedDose) {

		ncaInst.AUCall = ncaInst.round(ncaInst.AUCall, ncaInst.roundTo);
		ncaInst.AUClast = ncaInst.round(ncaInst.AUClast, ncaInst.roundTo);

		if (ncaInst.noPtsLambdaZ == 0) {
			setLambdaZRelatedParamToMissing();
		} else {
			calculateAucLambdaZRelatedParam(j, convertedDose);
		}

		double[] XFirstPiece = new double[2];
		double[] YFirstPiece = new double[2];
		double AUCFirstPiece = 0;
		XFirstPiece[0] = ncaInst.Xp[0];
		XFirstPiece[1] = ncaInst.Xp[1];
		YFirstPiece[0] = ncaInst.Yp[0];
		YFirstPiece[1] = ncaInst.Yp[1];

		if (ncaInst.method == 0)
			AUCFirstPiece = (XFirstPiece[1] - XFirstPiece[0])
					* (YFirstPiece[1] - YFirstPiece[0])
					/ Math.log(YFirstPiece[1] / (YFirstPiece[0]));
		if (ncaInst.method == 1) {
			if (YFirstPiece[1] >= YFirstPiece[0]) {
				AUCFirstPiece = (XFirstPiece[1] - XFirstPiece[0])
						* (YFirstPiece[1] + YFirstPiece[0]) / 2.0;

			} else {

				AUCFirstPiece = (XFirstPiece[1] - XFirstPiece[0])
						* (YFirstPiece[1] - YFirstPiece[0])
						/ Math.log(YFirstPiece[1] / (YFirstPiece[0]));

			}

		}

		if (ncaInst.method == 2)
			AUCFirstPiece = (XFirstPiece[1] - XFirstPiece[0])
					* (YFirstPiece[1] + YFirstPiece[0]) / 2.0;
		if (ncaInst.method == 3)
			AUCFirstPiece = (XFirstPiece[1] - XFirstPiece[0])
					* (YFirstPiece[1] + YFirstPiece[0]) / 2.0;
		ncaInst.AUCBackExtrapObs = 100 * AUCFirstPiece / ncaInst.AUCinfObs;
		ncaInst.AUCBackExtrapPred = 100 * AUCFirstPiece / ncaInst.AUCinfPred;

		if (ncaInst.noPtsLambdaZ == 0) {
			ncaInst.AUCBackExtrapPredStr = missingParamDisp;
			ncaInst.AUCBackExtrapObsStr = missingParamDisp;
		} else {
			ncaInst.AUCBackExtrapPredStr = ncaInst.AUCBackExtrapPred + "";
			ncaInst.AUCBackExtrapObsStr = ncaInst.AUCBackExtrapObs + "";
		}
	}

	private void calculateAucLambdaZRelatedParam(int j, double convertedDose) {

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

		ncaInst.MRTinfObs = ncaInst.AUMCinfObs / ncaInst.AUCinfObs;
		ncaInst.MRTinfPred = ncaInst.AUMCinfPred / ncaInst.AUCinfPred;

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

}
