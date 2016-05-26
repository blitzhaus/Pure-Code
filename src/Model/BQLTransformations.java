package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.hssf.record.formula.functions.Replace;

import view.ApplicationInfo;
import view.DisplayContents;

public class BQLTransformations {

	public static BQLTransformations BQL_TRANSFORMATIONS = null;

	public static BQLTransformations createBqlTransInst() {
		if (BQL_TRANSFORMATIONS == null) {
			BQL_TRANSFORMATIONS = new BQLTransformations("just object creation");
		}
		return BQL_TRANSFORMATIONS;
	}

	WorkSheetOutputs wsoutputs;
	ApplicationInfo appInfo;

	public ApplicationInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(ApplicationInfo appInfo) {
		this.appInfo = appInfo;
	}

	public BQLTransformations(String dummy) {

	}

	void bqlTransformations(ApplicationInfo appInfo) {
		setAppInfo(appInfo);
		wsoutputs = new WorkSheetOutputs();
		performTransformations();
	}

	private void performTransformations() {
		int numberOfProf = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject();

		int numberOfRowsInRulesTable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getDosingDataObj().getBqlRulesTable().length;

		int numberOfColumnsInRulesTable = 6;

		copyTheOriginalConcToAppInfo();

		for (int i = 0; i < numberOfRowsInRulesTable; i++) {
			for (int j = 0; j < numberOfColumnsInRulesTable; j++) {
				for (int k = 0; k < numberOfProf; k++) {
					double[] timesForThisProfile = getTimesForThisProfile(k);
					double[] concForThisProfile = getConcForThisProfile(k);
					switch (j) {
					case 0:
						unConditionalSubstitution(timesForThisProfile,
								concForThisProfile, k, i, j);
						break;
					case 1:
						beforeTmax(timesForThisProfile, concForThisProfile, k,
								i, j);
						break;
					case 2:
						afterTmax(timesForThisProfile, concForThisProfile, k,
								i, j);
						break;
					case 3:
						firstConsecutiveAfterTmax(timesForThisProfile,
								concForThisProfile, k, i, j);
						break;
					case 4:
						afterFirstConsecutiveAfterTmax(timesForThisProfile,
								concForThisProfile, k, i, j);
						break;
					}
				}
			}
		}

	}

	private void useWhenLessThanLLOQ(double[] timesForThisProfile,
			double[] concForThisProfile, int profileNumber,
			int rulesTableRowNumber, int rulesTableColumnNumber) {

		
		double[] tempConc = new double[concForThisProfile.length];
		for (int i = 0; i < tempConc.length; i++) {
			tempConc[i] = concForThisProfile[i];
		}
		Arrays.sort(tempConc);
		

		int cmaxIndex = 0;
		for (int i = 0; i < concForThisProfile.length; i++) {
			if (concForThisProfile[i] == tempConc[tempConc.length - 1]) {
				cmaxIndex = i;
				break;
			}

		}

		double tmax = timesForThisProfile[cmaxIndex];

		double nextToNextToTmax = timesForThisProfile[cmaxIndex + 2];
		// rule chosen is
		String rule = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getDosingDataObj().getBqlRulesTable()[rulesTableRowNumber][5];
		String value = null;

		if (rule.equals("")) {

		} else {
			double[] originalConcForThisProfile = originalConcForThisProfile(profileNumber);
			for (int i = 0; i < timesForThisProfile.length; i++) {
				value = formTheKeyForThisrowInThisProfile(profileNumber, i,
						timesForThisProfile, originalConcForThisProfile);

				if ((timesForThisProfile[i] == nextToNextToTmax)
						&& (value
								.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getBqlInfo().getProcessingInputs()
										.getDosingDataObj().getBqlRulesTable()[rulesTableRowNumber][0]))) {
					double changedValue = 0;
					if (rule.equals("LLOQ/2")) {
						changedValue = perform(0, i, concForThisProfile);
					} else if (rule.equals("Missing")) {
						changedValue = perform(1, i, concForThisProfile);
					} else if (rule.equalsIgnoreCase("0")) {
						changedValue = perform(2, i, concForThisProfile);
					}
					concForThisProfile[i] = changedValue;
				}
			}

		}
		ReflectChangesInTheNewConCColumn(concForThisProfile, profileNumber);

	}

	private void afterFirstConsecutiveAfterTmax(double[] timesForThisProfile,
			double[] concForThisProfile, int profileNumber,
			int rulesTableRowNumber, int rulesTableColumnNumber) {

		System.out.println();
		double[] tempConc = new double[concForThisProfile.length];
		for (int i = 0; i < tempConc.length; i++) {
			tempConc[i] = concForThisProfile[i];
		}
		Arrays.sort(tempConc);
		System.out.println("max concentration is "
				+ tempConc[tempConc.length - 1]);

		int cmaxIndex = 0;
		for (int i = 0; i < concForThisProfile.length; i++) {
			if (concForThisProfile[i] == tempConc[tempConc.length - 1]) {
				cmaxIndex = i;
				break;
			}

		}

		double tmax = timesForThisProfile[cmaxIndex];

		double nextToNextToTmax = timesForThisProfile[cmaxIndex + 2];
		// rule chosen is
		String rule = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getDosingDataObj().getBqlRulesTable()[rulesTableRowNumber][5];
		String value = null;

		if (rule.equals("")) {

		} else {
			double[] originalConcForThisProfile = originalConcForThisProfile(profileNumber);
			for (int i = 0; i < timesForThisProfile.length; i++) {
				value = formTheKeyForThisrowInThisProfile(profileNumber, i,
						timesForThisProfile, originalConcForThisProfile);

				if (((timesForThisProfile[i] == nextToNextToTmax) && (value
						.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getBqlInfo().getProcessingInputs()
								.getDosingDataObj().getBqlRulesTable()[rulesTableRowNumber][0])))) {
					double changedValue = 0;
					if (rule.equals("LLOQ/2")) {
						changedValue = perform(0, i, concForThisProfile);
					} else if (rule.equals("Missing")) {
						changedValue = perform(1, i, concForThisProfile);
					} else if (rule.equalsIgnoreCase("0")) {
						changedValue = perform(2, i, concForThisProfile);
					}
					concForThisProfile[i] = changedValue;
				}
			}

		}
		ReflectChangesInTheNewConCColumn(concForThisProfile, profileNumber);
	}

	private boolean isCheckedAndConcLessThanLLOQ(int i,
			double[] concForThisProfile, int rulesTableRowNumber) {

		boolean ischecked = isChecked(rulesTableRowNumber);
		boolean isLessThanLloq = false;
		if (concForThisProfile[i] < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getLloqValue()) {
			isLessThanLloq = true;
		}

		if ((ischecked == true) && (isLessThanLloq == true)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isChecked(int rulesTableRowNumber) {
		return Boolean
				.getBoolean(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getBqlInfo()
						.getProcessingInputs().getDosingDataObj()
						.getBqlRulesTable()[rulesTableRowNumber][6]);

	}

	private void firstConsecutiveAfterTmax(double[] timesForThisProfile,
			double[] concForThisProfile, int profileNumber,
			int rulesTableRowNumber, int rulesTableColumnNumber) {

		System.out.println();
		double[] tempConc = new double[concForThisProfile.length];
		for (int i = 0; i < tempConc.length; i++) {
			tempConc[i] = concForThisProfile[i];
		}
		Arrays.sort(tempConc);
		System.out.println("max concentration is "
				+ tempConc[tempConc.length - 1]);

		int cmaxIndex = 0;
		for (int i = 0; i < concForThisProfile.length; i++) {
			if (concForThisProfile[i] == tempConc[tempConc.length - 1]) {
				cmaxIndex = i;
				break;
			}

		}

		double tmax = timesForThisProfile[cmaxIndex];

		double nextConcecutiveAfterTmax = timesForThisProfile[cmaxIndex + 1];
		// rule chosen is
		String rule = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getDosingDataObj().getBqlRulesTable()[rulesTableRowNumber][4];
		String value = null;

		if (rule.equals("")) {

		} else {
			double[] originalConcForThisProfile = originalConcForThisProfile(profileNumber);
			for (int i = 0; i < timesForThisProfile.length; i++) {
				value = formTheKeyForThisrowInThisProfile(profileNumber, i,
						timesForThisProfile, originalConcForThisProfile);

				if ((timesForThisProfile[i] == nextConcecutiveAfterTmax)
						&& (value
								.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getBqlInfo().getProcessingInputs()
										.getDosingDataObj().getBqlRulesTable()[rulesTableRowNumber][0]))) {
					double changedValue = 0;
					if (rule.equals("LLOQ/2")) {
						changedValue = perform(0, i, concForThisProfile);
					} else if (rule.equals("Missing")) {
						changedValue = perform(1, i, concForThisProfile);
					} else if (rule.equalsIgnoreCase("0")) {
						changedValue = perform(2, i, concForThisProfile);
					}
					concForThisProfile[i] = changedValue;
				}
			}

		}

		ReflectChangesInTheNewConCColumn(concForThisProfile, profileNumber);

	}

	private void afterTmax(double[] timesForThisProfile,
			double[] concForThisProfile, int profileNumber,
			int rulesTableRowNumber, int rulesTableColumnNumber) {

		System.out.println();
		double[] tempConc = new double[concForThisProfile.length];
		for (int i = 0; i < tempConc.length; i++) {
			tempConc[i] = concForThisProfile[i];
		}
		Arrays.sort(tempConc);
		System.out.println("max concentration is "
				+ tempConc[tempConc.length - 1]);

		int cmaxIndex = 0;
		for (int i = 0; i < concForThisProfile.length; i++) {
			if (concForThisProfile[i] == tempConc[tempConc.length - 1]) {
				cmaxIndex = i;
				break;
			}

		}

		double tmax = timesForThisProfile[cmaxIndex];

		// rule chosen is
		String rule = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getDosingDataObj().getBqlRulesTable()[rulesTableRowNumber][3];
		String value = null;

		if (rule.equals("")) {

		} else {
			if (tmax == timesForThisProfile[timesForThisProfile.length - 1]) {// it
																				// states
																				// that
																				// since
																				// tmax
																				// is
																				// the
																				// last
																				// time
																				// point
																				// in
																				// this
																				// profile
																				// after
																				// tmax
																				// condition
																				// does
																				// not
																				// arise
				// so we skip this profile and let the conc be the same and go
				// to next profile.

			} else {

				double[] originalConcForThisProfile = originalConcForThisProfile(profileNumber);
				for (int i = 0; i < timesForThisProfile.length; i++) {
					value = formTheKeyForThisrowInThisProfile(profileNumber, i,
							timesForThisProfile, originalConcForThisProfile);

					if ((timesForThisProfile[i] > tmax)
							&& (value
									.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getBqlInfo().getProcessingInputs()
											.getDosingDataObj()
											.getBqlRulesTable()[rulesTableRowNumber][0]))) {
						double changedValue = 0;
						if (rule.equals("LLOQ/2")) {
							changedValue = perform(0, i, concForThisProfile);
						} else if (rule.equals("Missing")) {
							changedValue = perform(1, i, concForThisProfile);
						} else if (rule.equalsIgnoreCase("0")) {
							changedValue = perform(2, i, concForThisProfile);
						}
						concForThisProfile[i] = changedValue;
					}
				}
			}
		}

		ReflectChangesInTheNewConCColumn(concForThisProfile, profileNumber);

	}

	private double[] originalConcForThisProfile(int profileNumber) {

		double[] allConc = wsoutputs.getBqlOriginalConcCopy();

		int index = 0;

		for (int i = 0; i < profileNumber; i++) {
			index = index
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getBqlInfo().getProcessingInputs()
							.getProfileInfoObj().getSubject_obs()[i];
		}
		System.out.println("k is :" + profileNumber);
		double[] concForThisProfile = new double[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getProfileInfoObj().getSubject_obs()[profileNumber]];

		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getProfileInfoObj().getSubject_obs()[profileNumber]; i++) {
			concForThisProfile[i] = allConc[index++];
		}

		return concForThisProfile;
	}

	private void copyTheOriginalConcToAppInfo() {
		double[] concAllProf = new double[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getProfileInfoObj()
				.getConcForAllProfile().length];

		for (int i = 0; i < concAllProf.length; i++) {
			concAllProf[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getConcForAllProfile()[i];
		}

		wsoutputs.setBqlOriginalConcCopy(concAllProf);

	}

	private void beforeTmax(double[] timesForThisProfile,
			double[] concForThisProfile, int profileNumber,
			int rulesTableRowNumber, int rulesTableColumnNumber) {
		System.out.println();
		double[] tempConc = new double[concForThisProfile.length];
		for (int i = 0; i < tempConc.length; i++) {
			tempConc[i] = concForThisProfile[i];
		}
		Arrays.sort(tempConc);
		System.out.println("max concentration is "
				+ tempConc[tempConc.length - 1]);

		int cmaxIndex = 0;
		for (int i = 0; i < concForThisProfile.length; i++) {
			if (concForThisProfile[i] == tempConc[tempConc.length - 1]) {
				cmaxIndex = i;
				break;
			}

		}

		double tmax = timesForThisProfile[cmaxIndex];

		// rule chosen is
		String rule = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getDosingDataObj().getBqlRulesTable()[rulesTableRowNumber][2];
		String value = null;

		if (rule.equals("")) {

		} else {
			if (tmax == timesForThisProfile[0]) {// it states that since tmax is
													// the 1st time point in
													// this profile before tmax
													// condition does not arise
				// so we skip this profile and let the conc be the same and go
				// to next profile.

			} else {
				double[] originalConcForThisProfile = originalConcForThisProfile(profileNumber);
				for (int i = 0; i < timesForThisProfile.length; i++) {
					value = formTheKeyForThisrowInThisProfile(profileNumber, i,
							timesForThisProfile, originalConcForThisProfile);

					if ((timesForThisProfile[i] < tmax)
							&& (value
									.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getBqlInfo().getProcessingInputs()
											.getDosingDataObj()
											.getBqlRulesTable()[rulesTableRowNumber][0]))) {
						double changedValue = 0;
						if (rule.equals("LLOQ/2")) {
							changedValue = perform(0, i, concForThisProfile);
						} else if (rule.equals("Missing")) {
							changedValue = perform(1, i, concForThisProfile);
						} else if (rule.equalsIgnoreCase("0")) {
							changedValue = perform(2, i, concForThisProfile);
						}
						concForThisProfile[i] = changedValue;
					}
				}
			}
		}
		ReflectChangesInTheNewConCColumn(concForThisProfile, profileNumber);

	}

	private double perform(int i, int indexWithinProfile,
			double[] concForThisProfile) {
		double changedValue = 0;
		switch (i) {
		case 0:
			changedValue = divideWith2(indexWithinProfile, concForThisProfile);
			break;
		case 1:
			changedValue = replaceWithMissing(indexWithinProfile,
					concForThisProfile);
			break;
		case 2:
			changedValue = replaceWithZero(indexWithinProfile,
					concForThisProfile);
			break;
		}

		concForThisProfile[indexWithinProfile] = changedValue;
		return concForThisProfile[indexWithinProfile];
	}

	private double replaceWithZero(int indexWithinProfile,
			double[] concForThisProfile) {
		concForThisProfile[indexWithinProfile] = 0;

		return concForThisProfile[indexWithinProfile];

	}

	private double replaceWithMissing(int indexWithinProfile,
			double[] concForThisProfile) {
		concForThisProfile[indexWithinProfile] = -1;

		return concForThisProfile[indexWithinProfile];

	}

	private double divideWith2(int indexWithinProfile,
			double[] concForThisProfile) {

		concForThisProfile[indexWithinProfile] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getLloqValue() / 2;

		return concForThisProfile[indexWithinProfile];

	}

	private void unConditionalSubstitution(double[] timesForThisProfile,
			double[] concForThisProfile, int profileNumber,
			int rulesTableRowNumber, int rulesTableColumnNumber) {
		String value = null;

		// rule chosen is
		String rule = appInfo
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
				.getProcessingInputs().getDosingDataObj().getBqlRulesTable()[rulesTableRowNumber][1];
		if (rule.equals("")) {

		} else {
			double[] originalConcForThisProfile = originalConcForThisProfile(profileNumber);
			for (int i = 0; i < timesForThisProfile.length; i++) {
				value = formTheKeyForThisrowInThisProfile(profileNumber, i,
						timesForThisProfile, originalConcForThisProfile);
				if (value
						.equals(appInfo
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
								.getBqlInfo().getProcessingInputs()
								.getDosingDataObj().getBqlRulesTable()[rulesTableRowNumber][0])) {
					double changedValue = 0;
					if (rule.equals("LLOQ/2")) {
						changedValue = perform(0, i, concForThisProfile);
					} else if (rule.equals("Missing")) {
						changedValue = perform(1, i, concForThisProfile);
					} else if (rule.equalsIgnoreCase("0")) {
						changedValue = perform(2, i, concForThisProfile);
					}
					concForThisProfile[i] = changedValue;
				}
			}
		}
		ReflectChangesInTheNewConCColumn(concForThisProfile, profileNumber);

	}

	private void ReflectChangesInTheNewConCColumn(double[] concForThisProfile,
			int profileNumber) {
		// reflect the rule applied concentrations in appInfo

		if (profileNumber == 0) {
			wsoutputs.setParameterValuesAL(new ArrayList<String>());
		}

		for (int i = 0; i < concForThisProfile.length; i++) {
			wsoutputs.getParameterValuesAL().add(concForThisProfile[i] + "");
		}

		if (profileNumber == appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject() - 1) {
			if (wsoutputs.getParameterValuesAL().size() != 0) {
				double[] tempConc = null;
				tempConc = new double[wsoutputs.getParameterValuesAL().size()];

				for (int i = 0; i < tempConc.length; i++) {
					tempConc[i] = Double.parseDouble(wsoutputs
							.getParameterValuesAL().get(i));
				}
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getBqlInfo()
						.getProcessingInputs().getProfileInfoObj()
						.setConcForAllProfile(tempConc);
			}

		}

	}

	private String formTheKeyForThisrowInThisProfile(int profileNumber,
			int rowNumber, double[] timesForThisProfile,
			double[] concForThisProfile) {

		String key = new String("");

		// sortvariables
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[] sortVar = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getProfileInfoObj()
				.getDataSortVariables()[profileNumber];
		for (int i = 0; i < sortVar.length; i++) {
			key = key + sortVar[i];
		}

		// time var
		key = key + timesForThisProfile[rowNumber];

		// conc var
		key = key + concForThisProfile[rowNumber];

		String value = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getProfileInfoObj().getStatusCodeHM()
				.get(key);

		return value;

	}

	private double[] getConcForThisProfile(int k) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		double[] allConc = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getProfileInfoObj()
				.getConcForAllProfile();
		int index = 0;

		for (int i = 0; i < k; i++) {
			index = index
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getBqlInfo().getProcessingInputs()
							.getProfileInfoObj().getSubject_obs()[i];
		}
		System.out.println("k is :" + k);
		double[] concForThisProfile = new double[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getProfileInfoObj().getSubject_obs()[k]];

		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getProfileInfoObj().getSubject_obs()[k]; i++) {
			concForThisProfile[i] = allConc[index++];
		}
		return concForThisProfile;

	}

	private double[] getTimesForThisProfile(int k) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		double[] alltimes = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getProfileInfoObj()
				.getTimeForAllProfile();
		int index = 0;
		for (int i = 0; i < k; i++) {
			index = index
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getBqlInfo().getProcessingInputs()
							.getProfileInfoObj().getSubject_obs()[i];
		}
		double[] timesForThisProfile = new double[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getProfileInfoObj().getSubject_obs()[k]];

		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getProfileInfoObj().getSubject_obs()[k]; i++) {
			timesForThisProfile[i] = alltimes[index++];
		}
		return timesForThisProfile;

	}
}
