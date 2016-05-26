package view;

import java.io.IOException;
import java.util.ArrayList;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DataPreparationForAllComponents {

	double[] startTimeForOneProfile;
	double[] endTimeForOneProfile;
	double[] volumeForOneProfile;
	double[] concentrationForOneProfile;
	double[] TimeForOneProfile;
	double[] ConcForOneProfile;
	double[] TimeForAllProfile;
	double[] ConcForAllProfile;
	double[] TimeForAllSparseSampling;
	double[] ConcForAllSparseSampling;
	double[] midPointForOneProfile;
	double[] excretionRateForOneProfile;
	double[] volumeForAllUrineProfile;
	double[] concForAllUrineProfile;
	double[][] summaryVariablesForDS;
	int[] no_distinct_obs_sparsesampling;
	int sum;
	int sumForSparse;
	int nonMissingSampleCount;

	double[] X_sparse;
	double[] Y_sparse;

	ArrayList<StdErrorAndObsCount> SE;
	DetermineNumberOfSubject detNoSub;
	String[] independetTimeForDS;

	public static DataPreparationForAllComponents DATA_PREP_ALL_COMP = null;

	public static DataPreparationForAllComponents createDataPrepAllCompInst() {
		if (DATA_PREP_ALL_COMP == null) {
			DATA_PREP_ALL_COMP = new DataPreparationForAllComponents(
					"just object creation");
		}
		return DATA_PREP_ALL_COMP;
	}

	public DataPreparationForAllComponents(String dummy) {

	}

	void dataPreparationForAll(String[][] dataMatrix)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		detNoSub = DetermineNumberOfSubject.createDetNoSubInst();
		TimeForAllProfile = new double[dataMatrix.length];
		ConcForAllProfile = new double[dataMatrix.length];
		independetTimeForDS = new String[dataMatrix.length];

		if (NcaOptionsGui.createNcaOptionsInstance().sparseCheckBox
				.isSelected() == true) {
			TimeForAllSparseSampling = new double[dataMatrix.length];
			ConcForAllSparseSampling = new double[dataMatrix.length];
		}
		no_distinct_obs_sparsesampling = new int[detNoSub.no_subject];

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		sum = 0;

		if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {

			summaryVariablesForDS = new double[dataMatrix.length][DesStatSetupDespComp
					.createDesStatSetupDispInst().carryAlongModel.size()];
			profilePreparationDesStat(dataMatrix);

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == true)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)) {

			profilePreparationSca(dataMatrix);
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == true)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {

			profilePreparationNps(dataMatrix);
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == true)) {

			profilePreparationPlot(dataMatrix);
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == true)) {
			profilePreperationForBQL(dataMatrix);
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == false)
				&& (DDViewLayer.createViewLayerInstance().isFromTableMaven == false)
				&& (DDViewLayer.createViewLayerInstance().isTableTrans = true)) {
			profilePrepForT(dataMatrix);
		}

		else {
			sumForSparse = 0;
			for (int j = 0; j < detNoSub.no_subject; j++) {

				if ((DDViewLayer.createViewLayerInstance().isNCAExecution == true)
						&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)) {

					profilePreparationNca(dataMatrix, j);

				}
			}
		}
		setProfileInfoToAppIfo();

	}

	private void profilePrepForT(String[][] data) {

		TimeForAllProfile = new double[data.length];
		ConcForAllProfile = new double[data.length];

		for (int i = 0; i < data.length; i++) {
			TimeForAllProfile[i] = Double
					.parseDouble(data[i][data[0].length - 2]);

			ConcForAllProfile[i] = Double
					.parseDouble(data[i][data[0].length - 1]);
		}

	}

	private void profilePreperationForBQL(String[][] data) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		TimeForAllProfile = new double[data.length];
		ConcForAllProfile = new double[data.length];

		for (int i = 0; i < data.length; i++) {
			TimeForAllProfile[i] = Double
					.parseDouble(data[i][data[0].length - 3]);
			ConcForAllProfile[i] = Double
					.parseDouble(data[i][data[0].length - 2]);
		}
	}

	private void setProfileInfoToAppIfo() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {

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
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setConcForAllProfile(ConcForAllProfile);

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
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setConcForAllSparseSampling(ConcForAllSparseSampling);

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
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setNo_distinct_obs_sparsesampling(
							no_distinct_obs_sparsesampling);

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
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getProfileInfoObj().setSE(SE);

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
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setTimeForAllProfile(TimeForAllProfile);
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
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setTimeForAllSparseSampling(TimeForAllSparseSampling);

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == true)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)) {

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
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setConcForAllProfile(ConcForAllProfile);

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
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setTimeForAllProfile(TimeForAllProfile);

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == true)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {

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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setConcForAllProfile(ConcForAllProfile);

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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setConcForAllSparseSampling(ConcForAllSparseSampling);

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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setNo_distinct_obs_sparsesampling(
							no_distinct_obs_sparsesampling);

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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getProfileInfoObj().setSE(SE);

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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setTimeForAllProfile(TimeForAllProfile);
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
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setTimeForAllSparseSampling(TimeForAllSparseSampling);

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == true)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)) {

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
					.getProcessingInputs().getProfileInfoObj()
					.setConcForAllProfile(ConcForAllProfile);

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
					.getProcessingInputs().getProfileInfoObj()
					.setConcForAllSparseSampling(ConcForAllSparseSampling);

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
					.getProcessingInputs().getProfileInfoObj()
					.setNo_distinct_obs_sparsesampling(
							no_distinct_obs_sparsesampling);

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
					.getProcessingInputs().getProfileInfoObj().setSE(SE);

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
					.getProcessingInputs().getProfileInfoObj()
					.setTimeForAllProfile(TimeForAllProfile);
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
					.getProcessingInputs().getProfileInfoObj()
					.setTimeForAllSparseSampling(TimeForAllSparseSampling);

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == true)) {

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
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setTimeForAllProfile(TimeForAllProfile);
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
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setConcForAllProfile(ConcForAllProfile);
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == true)) {

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
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setConcForAllProfile(ConcForAllProfile);

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
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setTimeForAllProfile(TimeForAllProfile);

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == false)
				&& (DDViewLayer.createViewLayerInstance().isFromTableMaven == false)
				&& (DDViewLayer.createViewLayerInstance().isTableTrans = true)) {

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
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setConcForAllProfile(ConcForAllProfile);

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
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setTimeForAllProfile(TimeForAllProfile);
		}

	}

	private void profilePreparationPlot(String[][] data) {
		TimeForAllProfile = new double[data.length];
		ConcForAllProfile = new double[data.length];

		for (int i = 0; i < data.length; i++) {
			TimeForAllProfile[i] = Double
					.parseDouble(data[i][data[0].length - 2]);

			ConcForAllProfile[i] = Double
					.parseDouble(data[i][data[0].length - 1]);
		}

	}

	private void profilePreparationNca(String[][] dataMatrix, int j)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		if (NcaMappingDispGui.createMappingInstance().sortVariableListModel
				.size() != 0) {
			String[][] totalSortVariables = new String[1][detNoSub.numberOfSortVariable];
			for (int ii = 0; ii < detNoSub.numberOfSortVariable; ii++) {
				totalSortVariables[0][ii] = dataMatrix[sum][ii];
				System.out.print("\t " + totalSortVariables[0][ii]);
			}
		}

		/*
		 * X_sparse = new double[detNoSub.subject_obs[j]]; Y_sparse = new
		 * double[detNoSub.subject_obs[j]];
		 */
		// sumForSparse = 0;

		// modification for urine data
		if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.getSelectedIndex() == 1) {

			nonMissingSampleCount = 0;

			for (int i = 0; i < detNoSub.subject_obs[j]; i++) {
				// N.subject_id[i]=N.Dat[i+sum][N.sort_variable];

				if (Double
						.parseDouble(dataMatrix[i + sum][detNoSub.numberOfSortVariable + 2]) != -3.14159265359) {
					nonMissingSampleCount++;
				}

			}

			startTimeForOneProfile = new double[nonMissingSampleCount];
			endTimeForOneProfile = new double[nonMissingSampleCount];
			concentrationForOneProfile = new double[nonMissingSampleCount];
			volumeForOneProfile = new double[nonMissingSampleCount];

			int count = 0;
			for (int i = 0; i < detNoSub.subject_obs[j]; i++) {

				if (Double
						.parseDouble(dataMatrix[i + sum][detNoSub.numberOfSortVariable + 2]) != -3.14159265359) {
					startTimeForOneProfile[count] = Double
							.parseDouble(dataMatrix[i + sum][detNoSub.numberOfSortVariable]);
					endTimeForOneProfile[count] = Double
							.parseDouble(dataMatrix[i + sum][detNoSub.numberOfSortVariable + 1]);
					concentrationForOneProfile[count] = Double
							.parseDouble(dataMatrix[i + sum][detNoSub.numberOfSortVariable + 2]);
					volumeForOneProfile[count] = Double
							.parseDouble(dataMatrix[i + sum][detNoSub.numberOfSortVariable + 3]);
					count++;

				}

			}

			sum = sum + nonMissingSampleCount;

			urineDataProcessing();

			for (int i = 0; i < startTimeForOneProfile.length; i++)
				System.out.println("mid time " + midPointForOneProfile[i]
						+ "conc " + excretionRateForOneProfile[i]);

			if (NcaOptionsGui.createNcaOptionsInstance().sparseCheckBox
					.isSelected() == false) {
				serialUrineDataPopulation(j);
			}

			// modification for sparse sampling

			// sumForSparse = 0;
			if (NcaOptionsGui.createNcaOptionsInstance().sparseCheckBox
					.isSelected() == true) {
				sparseUrineDataPopulation(j, dataMatrix);
			}
		}

		// modification for multiple subject and serial sampling
		if (NcaOptionsGui.createNcaOptionsInstance().sparseCheckBox
				.isSelected() == false
				&& NcaOptionsGui.createNcaOptionsInstance().modelOptions
						.getSelectedIndex() == 0) {
			sum = serialPlasma(dataMatrix, j, sum);

		}

		// modification for sparse sampling
		if (NcaOptionsGui.createNcaOptionsInstance().sparseCheckBox
				.isSelected() == true
				&& NcaOptionsGui.createNcaOptionsInstance().modelOptions
						.getSelectedIndex() == 0) {
			sum = sparsePlasma(sum, dataMatrix, j);
		}

		if (NcaOptionsGui.createNcaOptionsInstance().sparseCheckBox
				.isSelected() == false)
			for (int i = 0; i < detNoSub.subject_obs[j]; i++) {
				TimeForAllProfile[sum - detNoSub.subject_obs[j] + i] = TimeForOneProfile[i];
				ConcForAllProfile[sum - detNoSub.subject_obs[j] + i] = ConcForOneProfile[i];
			}
		else {
			for (int i = 0; i < nonMissingSampleCount; i++) {
				TimeForAllSparseSampling[sum - nonMissingSampleCount + i] = X_sparse[i];
				ConcForAllSparseSampling[sum - nonMissingSampleCount + i] = Y_sparse[i];
			}
			for (int i = 0; i < no_distinct_obs_sparsesampling[j]; i++) {
				TimeForAllProfile[sumForSparse
						- no_distinct_obs_sparsesampling[j] + i] = TimeForOneProfile[i];
				ConcForAllProfile[sumForSparse
						- no_distinct_obs_sparsesampling[j] + i] = ConcForOneProfile[i];
			}

		}

	}

	private void sparseUrineDataPopulation(int j, String[][] dataMatrix) {

		X_sparse = new double[nonMissingSampleCount];
		Y_sparse = new double[nonMissingSampleCount];

		System.out.println("No. of observation is:" + detNoSub.subject_obs[j]);
		X_sparse = midPointForOneProfile;
		Y_sparse = excretionRateForOneProfile;
		int k = 0;
		no_distinct_obs_sparsesampling[j] = 0;
		while (k < nonMissingSampleCount - 1) {

			while ((k < nonMissingSampleCount - 1)
					&& (X_sparse[k] == X_sparse[k + 1])) {
				k++;
			}
			no_distinct_obs_sparsesampling[j] = no_distinct_obs_sparsesampling[j] + 1;
			k++;
		}
		if ((k < nonMissingSampleCount) && (X_sparse[k - 1] != X_sparse[k])) {
			no_distinct_obs_sparsesampling[j] = no_distinct_obs_sparsesampling[j] + 1;
		}

		
		double[][] test = new double[no_distinct_obs_sparsesampling[j]][2];
		test = Parse_Sampling.SparseSampling(X_sparse, Y_sparse,
				nonMissingSampleCount);
		SE = Parse_Sampling.calculateStandardError(X_sparse, Y_sparse,
				nonMissingSampleCount - 1);
		TimeForOneProfile = new double[no_distinct_obs_sparsesampling[j]];
		ConcForOneProfile = new double[no_distinct_obs_sparsesampling[j]];
		for (int i = 0; i < no_distinct_obs_sparsesampling[j]; i++) {
			System.out.println(test[i][0] + "\t" + test[i][1]);
			TimeForOneProfile[i] = test[i][0];
			ConcForOneProfile[i] = test[i][1];

		}
		sumForSparse = sumForSparse + no_distinct_obs_sparsesampling[j];

	}

	private void serialUrineDataPopulation(int j) {
		TimeForOneProfile = new double[detNoSub.subject_obs[j]];
		ConcForOneProfile = new double[detNoSub.subject_obs[j]];
		TimeForOneProfile = midPointForOneProfile;
		ConcForOneProfile = excretionRateForOneProfile;

	}

	private int sparsePlasma(int sum, String[][] dataMatrix, int j) {

		/*
		 * X_sparse = new double[detNoSub.subject_obs[j]]; Y_sparse = new
		 * double[detNoSub.subject_obs[j]];
		 */
		nonMissingSampleCount = 0;

		for (int i = 0; i < detNoSub.subject_obs[j]; i++) {
			// N.subject_id[i]=N.Dat[i+sum][N.sort_variable];

			if (Double
					.parseDouble(dataMatrix[i + sum][detNoSub.numberOfSortVariable + 1]) != -3.14159265359) {
				nonMissingSampleCount++;
			}

		}

		X_sparse = new double[nonMissingSampleCount];
		Y_sparse = new double[nonMissingSampleCount];

		int count = 0;

		for (int i = 0; i < detNoSub.subject_obs[j]; i++) {
			// N.subject_id[i]=N.Dat[i+sum][N.sort_variable];

			if (Double
					.parseDouble(dataMatrix[i + sum][detNoSub.numberOfSortVariable + 1]) != -3.14159265359) {
				X_sparse[count] = Double
						.parseDouble(dataMatrix[i + sum][detNoSub.numberOfSortVariable]);
				Y_sparse[count] = Double
						.parseDouble(dataMatrix[i + sum][detNoSub.numberOfSortVariable + 1]);
				count++;
			}

		}
		sum = sum + detNoSub.subject_obs[j];
		System.out.println("No. of observation is:" + detNoSub.subject_obs[j]);
		int k = 0;
		no_distinct_obs_sparsesampling[j] = 0;
		while (k < nonMissingSampleCount - 1) {

			while ((k < nonMissingSampleCount - 1)
					&& (X_sparse[k] == X_sparse[k + 1])) {

				k++;
				// count++;

			}

			no_distinct_obs_sparsesampling[j] = no_distinct_obs_sparsesampling[j] + 1;
			k++;
		}
		if ((k < nonMissingSampleCount) && (X_sparse[k - 1] != X_sparse[k])) {
			no_distinct_obs_sparsesampling[j] = no_distinct_obs_sparsesampling[j] + 1;
		}

		double[][] test = new double[no_distinct_obs_sparsesampling[j]][2];
		test = Parse_Sampling.SparseSampling(X_sparse, Y_sparse,
				nonMissingSampleCount);
		SE = Parse_Sampling.calculateStandardError(X_sparse, Y_sparse,
				nonMissingSampleCount - 1);
		int[] nuObservationPerTimePoint = Parse_Sampling.numberOfObservations(
				X_sparse, Y_sparse, nonMissingSampleCount - 1);
		TimeForOneProfile = new double[no_distinct_obs_sparsesampling[j]];
		ConcForOneProfile = new double[no_distinct_obs_sparsesampling[j]];
		for (int i = 0; i < no_distinct_obs_sparsesampling[j]; i++) {
			System.out.println(test[i][0] + "\t" + test[i][1]);
			TimeForOneProfile[i] = test[i][0];
			ConcForOneProfile[i] = test[i][1];

		}
		// subject_obs[j]=N.no_distinct_obs_sparsesampling;
		sumForSparse = sumForSparse + no_distinct_obs_sparsesampling[j];
		System.out.println("No. of distinct observation"
				+ no_distinct_obs_sparsesampling);
		return sum;

	}

	private int serialPlasma(String[][] dataMatrix, int j, int sum) {

		TimeForOneProfile = new double[detNoSub.subject_obs[j]];
		ConcForOneProfile = new double[detNoSub.subject_obs[j]];
		for (int i = 0; i < detNoSub.subject_obs[j]; i++) {
			TimeForOneProfile[i] = Double
					.parseDouble(dataMatrix[i + sum][detNoSub.numberOfSortVariable]);
			ConcForOneProfile[i] = Double
					.parseDouble(dataMatrix[i + sum][detNoSub.numberOfSortVariable + 1]);
		}
		sum = sum + detNoSub.subject_obs[j];
		return sum;
	}

	private void profilePreparationNps(String[][] data) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		TimeForAllProfile = new double[data.length];
		ConcForAllProfile = new double[data.length];

		for (int i = 0; i < data.length; i++) {
			TimeForAllProfile[i] = Double
					.parseDouble(data[i][data[0].length - 2]);
			ConcForAllProfile[i] = Double
					.parseDouble(data[i][data[0].length - 1]);

			System.out.println(i + "");

		}

		System.out.println("I am out of for loop");

	}

	private void profilePreparationSca(String[][] data) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		TimeForAllProfile = new double[data.length];
		ConcForAllProfile = new double[data.length];

		for (int i = 0; i < data.length; i++) {
			TimeForAllProfile[i] = Double
					.parseDouble(data[i][data[0].length - 2]);
			ConcForAllProfile[i] = Double
					.parseDouble(data[i][data[0].length - 1]);

		}

	}

	private void profilePreparationDesStat(String[][] dataMatrix) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		for (int i = 0; i < dataMatrix.length; i++) {
			independetTimeForDS[i] = (dataMatrix[i][detNoSub.numberOfSortVariable - 1]);

			for (int k = 0; k < DesStatSetupDespComp
					.createDesStatSetupDispInst().carryAlongModel.size(); k++) {
				summaryVariablesForDS[i][k] = Double
						.parseDouble(dataMatrix[i][detNoSub.numberOfSortVariable
								+ k]);
			}

		}

		// ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
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
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getProfileInfoObj()
				.setIndependentVarForDS(independetTimeForDS);

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
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getProfileInfoObj()
				.setSummaryVariablesForDS(summaryVariablesForDS);
	}

	void urineDataProcessing() {
		int no_obs = 0;

		no_obs = startTimeForOneProfile.length;
		midPointForOneProfile = new double[no_obs];
		excretionRateForOneProfile = new double[no_obs];

		for (int i = 0; i < no_obs; i++) {
			midPointForOneProfile[i] = (startTimeForOneProfile[i] + endTimeForOneProfile[i]) / 2;
			excretionRateForOneProfile[i] = concentrationForOneProfile[i]
					* volumeForOneProfile[i]
					/ (endTimeForOneProfile[i] - startTimeForOneProfile[i]);
		}
	}
}
