package Model;

import view.ApplicationInfo;

public class partialAreaCalculation {

	double[] partialArea;
	NCA ncaInst;
	ApplicationInfo appInfo;

	void calculatePartialArea(double[] time, double[] conc, double[] startTime,
			double[] endTime, int noOfPartialArea) {

		ncaInst = NCA.creaetNCAInst();
		appInfo = ncaInst.getAppInfo();

		intermediateOutputHeader();

		int i;
		partialArea = new double[startTime.length];
		double totalNoObs = time.length;
		int noPoints;
		for (int j = 0; j < startTime.length; j++) {
			
			noPoints = 0;
			for (i = 0; i < totalNoObs; i++) {
				if (time[i] >= startTime[j] && time[i] <= endTime[j])
					noPoints++;
			}
			double newStartTimeConc = 0;
			double newEndTimeConc = 0;
			int findStartTimeIndex;
			int findEndTimeIndex;
			findStartTimeIndex = findVal(time, startTime[j]);
			findEndTimeIndex = findVal(time, endTime[j]);
			

			if (findStartTimeIndex == -1 && findEndTimeIndex == -1) {

				partialAreaCalForCase1(time, conc, startTime, endTime,
						totalNoObs, noPoints, j);
			}

			if (findStartTimeIndex == -1 && findEndTimeIndex > -1) {

				partialAreaCalForCase2(time, conc, startTime, endTime,
						totalNoObs, noPoints, j);

			}

			if (findStartTimeIndex > -1 && findEndTimeIndex == -1) {

				partialAreaCalForCase3(time, conc, startTime, endTime,
						totalNoObs, noPoints, j);

			}
			if (findStartTimeIndex > -1 && findEndTimeIndex > -1) {
				i = partialAreaCalForCase4(time, conc, startTime, endTime,
						totalNoObs, noPoints, j);
			}

			

		}

		
		// return partialArea;
	}

	private int partialAreaCalForCase4(double[] time, double[] conc,
			double[] startTime, double[] endTime, double totalNoObs,
			int noPoints, int j) {
		int i;
		double[] newTime = new double[noPoints];
		double[] newConc = new double[noPoints];
		int count = 0;
		for (i = 0; i < totalNoObs; i++) {
			if (time[i] >= startTime[j] && time[i] <= endTime[j]) {
				newTime[count] = time[i];
				newConc[count] = conc[i];
				count++;
			}
		}
		calculateAuc(j, newTime, newConc);
		

		// partialArea[j]=linearTrapezoidal(newTime,newConc);
		return i;
	}

	private void partialAreaCalForCase3(double[] time, double[] conc,
			double[] startTime, double[] endTime, double totalNoObs,
			int noPoints, int j) {
		int i;
		double newEndTimeConc;
		if (endTime[j] <= time[time.length - 1]) {

			double[] newTime = new double[noPoints + 1];
			double[] newConc = new double[noPoints + 1];

			i = 0;
			while (endTime[j] >= time[i])
				i++;
			if (conc[i - 1] < conc[i - 2] && i - 2 >= 0)
				newEndTimeConc = logInterpolation(time, conc, startTime[j]);
			else
				newEndTimeConc = linearInterpolation(time, conc, startTime[j]);

			newTime[noPoints] = endTime[j];

			newConc[noPoints] = newEndTimeConc;
			int count = 0;
			for (i = 0; i < totalNoObs; i++) {
				if (time[i] >= startTime[j] && time[i] <= endTime[j]) {
					newTime[count + 1] = time[i];
					newConc[count + 1] = conc[i];
					count++;
				}

			}

			calculateAuc(j, newTime, newConc);
			

		} else if ((ncaInst.lambdaZ != 0 && endTime[j] > time[time.length - 1])) {

			double partialArea1 = 0, partialArea2 = 0;

			newEndTimeConc = ncaInst.Clast_pred
					* Math.exp((-1) * ncaInst.lambdaZ
							* (endTime[j] - ncaInst.Tlast));

			int count1 = 0;

			for (i = 0; i < totalNoObs; i++) {
				if (time[i] >= startTime[j] && time[i] <= endTime[j])
					count1++;
			}

			double[] newTime1 = new double[count1];
			double[] newConc1 = new double[count1];

			int count = 0;
			for (i = 0; i < totalNoObs; i++) {
				if (time[i] >= startTime[j] && time[i] <= endTime[j]) {

					newTime1[count + 1] = time[i];
					newConc1[count + 1] = conc[i];
					count++;
				}
			}

			if (ncaInst.method == 0)
				partialArea1 = linearlogTrapezoidal(newTime1, newConc1);
			if (ncaInst.method == 1)
				partialArea1 = linearUplogDownTrapezoidal(newTime1, newConc1);
			if (ncaInst.method == 2)
				partialArea1 = linearTrapezoidal(newTime1, newConc1);
			if (ncaInst.method == 3)
				partialArea1 = linearTrapezoidal(newTime1, newConc1);

			double[] newTime = new double[2];
			double[] newConc = new double[2];
			newTime[0] = time[time.length - 1];
			newTime[1] = endTime[j];
			newConc[0] = conc[time.length - 1];
			newConc[1] = newEndTimeConc;

			partialArea2 = logTrapezoidal(newTime, newConc);

			partialArea[j] = partialArea1 + partialArea2;
			

		} else if ((ncaInst.lambdaZ == 0 && endTime[j] > time[time.length - 1])) {

			
		}
	}

	private void partialAreaCalForCase2(double[] time, double[] conc,
			double[] startTime, double[] endTime, double totalNoObs,
			int noPoints, int j) {
		int i;
		double newStartTimeConc;
		double[] newTime = new double[noPoints + 1];
		double[] newConc = new double[noPoints + 1];

		i = 0;
		while (startTime[j] >= time[i])
			i++;
		if (conc[i - 1] < conc[i - 2] && i - 2 >= 0)
			newStartTimeConc = logInterpolation(time, conc, startTime[j]);
		else
			newStartTimeConc = linearInterpolation(time, conc, startTime[j]);

		newTime[0] = startTime[j];
		newConc[0] = newStartTimeConc;
		int count = 0;
		for (i = 0; i < totalNoObs; i++) {
			if (time[i] >= startTime[j] && time[i] <= endTime[j]) {
				newTime[count + 1] = time[i];
				newConc[count + 1] = conc[i];
				count++;
			}
		}

		calculateAuc(j, newTime, newConc);
	}

	private void partialAreaCalForCase1(double[] time, double[] conc,
			double[] startTime, double[] endTime, double totalNoObs,
			int noPoints, int j) {
		int i;
		double newStartTimeConc;
		double newEndTimeConc;
		if (time[0] <= startTime[j] && endTime[j] <= time[time.length - 1]) {

			double[] newTime = new double[noPoints + 2];
			double[] newConc = new double[noPoints + 2];

			i = 0;
			while (startTime[j] >= time[i])
				i++;

			if (conc[i - 1] < conc[i - 2] && i - 2 >= 0)
				newStartTimeConc = logInterpolation(time, conc, startTime[j]);
			else
				newStartTimeConc = linearInterpolation(time, conc, startTime[j]);

			i = 0;
			while (endTime[j] >= time[i])
				i++;

			if (conc[i - 1] < conc[i - 2] && i - 2 >= 0)
				newEndTimeConc = logInterpolation(time, conc, endTime[j]);
			else
				newEndTimeConc = linearInterpolation(time, conc, endTime[j]);

			newTime[0] = startTime[j];
			newTime[noPoints + 1] = endTime[j];
			newConc[0] = newStartTimeConc;
			newConc[noPoints + 1] = newEndTimeConc;
			int count = 0;
			for (i = 0; i < totalNoObs; i++) {
				if (time[i] >= startTime[j] && time[i] <= endTime[j]) {
					newTime[count + 1] = time[i];
					newConc[count + 1] = conc[i];
					count++;
				}

			}

			calculateAuc(j, newTime, newConc);

		} else if ((ncaInst.lambdaZ != 0
				&& time[time.length - 1] < startTime[j] && endTime[j] > time[time.length - 1])
				|| (ncaInst.lambdaZ != 0
						&& time[time.length - 1] < startTime[j] && endTime[j] > time[time.length - 1])) {
			if (time[time.length - 1] < startTime[j]
					&& endTime[j] > time[time.length - 1]) {

				double[] newTime = new double[noPoints + 2];
				double[] newConc = new double[noPoints + 2];
				newStartTimeConc = ncaInst.Clast_pred
						* Math.exp((-1) * ncaInst.lambdaZ
								* (startTime[j] - ncaInst.Tlast));
				newEndTimeConc = ncaInst.Clast_pred
						* Math.exp((-1) * ncaInst.lambdaZ
								* (endTime[j] - ncaInst.Tlast));

				newTime[0] = startTime[j];
				newTime[noPoints + 1] = endTime[j];
				newConc[0] = newStartTimeConc;
				newConc[noPoints + 1] = newEndTimeConc;
				int count = 0;
				for (i = 0; i < totalNoObs; i++) {
					if (time[i] >= startTime[j] && time[i] <= endTime[j]) {
						newTime[count + 1] = time[i];
						newConc[count + 1] = conc[i];
						count++;
					}

				}

				partialArea[j] = logTrapezoidal(newTime, newConc);

			} else if (time[time.length - 1] > startTime[j]
					&& endTime[j] > time[time.length - 1]) {
				double partialArea1 = 0, partialArea2 = 0;

				i = 0;
				while (startTime[j] >= time[i])
					i++;

				if (conc[i - 1] < conc[i - 2] && i - 2 >= 0)
					newStartTimeConc = logInterpolation(time, conc,
							startTime[j]);
				else
					newStartTimeConc = linearInterpolation(time, conc,
							startTime[j]);

				newEndTimeConc = ncaInst.Clast_pred
						* Math.exp((-1) * ncaInst.lambdaZ
								* (endTime[j] - ncaInst.Tlast));

				int count1 = 0;

				for (i = 0; i < totalNoObs; i++) {
					if (time[i] >= startTime[j] && time[i] <= endTime[j])
						count1++;
				}

				double[] newTime1 = new double[count1 + 1];
				double[] newConc1 = new double[count1 + 1];

				newTime1[0] = startTime[j];
				newConc1[0] = endTime[j];

				int count = 0;
				for (i = 0; i < totalNoObs; i++) {
					if (time[i] >= startTime[j] && time[i] <= endTime[j]) {
						count++;
						newTime1[count + 1] = time[i];
						newConc1[count + 1] = conc[i];

					}
				}

				if (ncaInst.method == 0)
					partialArea1 = linearlogTrapezoidal(newTime1, newConc1);
				if (ncaInst.method == 1)
					partialArea1 = linearUplogDownTrapezoidal(newTime1,
							newConc1);
				if (ncaInst.method == 2)
					partialArea1 = linearTrapezoidal(newTime1, newConc1);
				if (ncaInst.method == 3)
					partialArea1 = linearTrapezoidal(newTime1, newConc1);

				double[] newTime = new double[2];
				double[] newConc = new double[2];
				newTime[0] = time[time.length - 1];
				newTime[1] = endTime[j];
				newConc[0] = conc[time.length - 1];
				newConc[1] = newEndTimeConc;

				partialArea2 = logTrapezoidal(newTime, newConc);

				partialArea[j] = partialArea1 + partialArea2;

			}
		} else if ((ncaInst.lambdaZ == 0
				&& time[time.length - 1] < startTime[j] && endTime[j] > time[time.length - 1])) {

			
		}

	}

	private void intermediateOutputHeader() {
		if (ncaInst.ifWithinNCAExecution == true
				&& appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).
				getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).
						getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.isIntermediateoutputSelected() == true) {
			ncaInst.intermediateOutputForPA.add("\r\n\r\n");
			ncaInst.intermediateOutputForPA
					.add("Intermediate Output for Partial Area" + "\t");
			ncaInst.intermediateOutputForPA.add("Lower Time" + "\t");
			ncaInst.intermediateOutputForPA.add("Upper Time" + "\t");
			ncaInst.intermediateOutputForPA.add("AUC" + "\t");
			ncaInst.intermediateOutputForPA.add("\r\n\r\n");
		}
	}

	private void calculateAuc(int j, double[] newTime, double[] newConc) {
		if (ncaInst.method == 0)
			partialArea[j] = linearlogTrapezoidal(newTime, newConc);
		if (ncaInst.method == 1)
			partialArea[j] = linearUplogDownTrapezoidal(newTime, newConc);
		if (ncaInst.method == 2)
			partialArea[j] = linearTrapezoidal(newTime, newConc);
		if (ncaInst.method == 3)
			partialArea[j] = linearTrapezoidal(newTime, newConc);
	}

	double linearTrapezoidal(double[] t, double[] C) {
		double AUC = 0;

		for (int i = 0; i < t.length - 1; i++) {
			double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;

			AUC = AUC + tempAUC;
			storeInIntermediateOutput(t, i, tempAUC);

		}
		AUC = Math.abs(AUC);
		return AUC;

	}

	private void storeInIntermediateOutput(double[] t, int i, double tempAUC) {
		if (ncaInst.ifWithinNCAExecution == true
				&& appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).
				getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).
						getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.isIntermediateoutputSelected() == true) {
			ncaInst.intermediateOutputForPA.add(t[i] + "\t");
			ncaInst.intermediateOutputForPA.add(t[i + 1] + "\t");
			ncaInst.intermediateOutputForPA.add(tempAUC + "\t");
			ncaInst.intermediateOutputForPA.add("\r\n\r\n");
		}
	}

	double logTrapezoidal(double[] t, double[] C) {
		double AUC = 0;

		for (int i = 0; i < t.length - 1; i++) {
			if (C[i] == 0 || C[i + 1] == 0) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;

				AUC = AUC + tempAUC;

				storeInIntermediateOutput(t, i, tempAUC);

			} else {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
						/ Math.log(C[i + 1] / C[i]);

				AUC = AUC + tempAUC;

				storeInIntermediateOutput(t, i, tempAUC);

			}
		}

		AUC = Math.abs(AUC);
		return AUC;
	}

	double linearlogTrapezoidal(double[] t, double[] C) {
		double AUC = 0;
		double maxValue = maxVal(C);
		int maxConcIndex = findVal(C, maxValue);

		for (int i = 0; i < t.length - 1; i++) {
			if (i + 1 <= maxConcIndex) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;

				AUC = AUC + tempAUC;

				storeInIntermediateOutput(t, i, tempAUC);

			} else {
				if (C[i] == 0 || C[i + 1] == 0) {
					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i])
							/ 2.0;
					AUC = AUC + tempAUC;
					storeInIntermediateOutput(t, i, tempAUC);
				} else {
					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
							/ Math.log(C[i + 1] / C[i]);

					AUC = AUC + tempAUC;
					storeInIntermediateOutput(t, i, tempAUC);

				}

			}
		}

		AUC = Math.abs(AUC);
		return AUC;
	}

	double linearUplogDownTrapezoidal(double[] t, double[] C) {

		double AUC = 0;

		for (int i = 0; i < t.length - 1; i++) {
			if (C[i + 1] >= C[i]) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
				AUC = AUC + tempAUC;
				storeInIntermediateOutput(t, i, tempAUC);
			} else {
				if (C[i] == 0 || C[i + 1] == 0) {
					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i])
							/ 2.0;
					AUC = AUC + tempAUC;
					storeInIntermediateOutput(t, i, tempAUC);

				} else {

					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
							/ Math.log(C[i + 1] / C[i]);

					AUC = AUC + tempAUC;
					storeInIntermediateOutput(t, i, tempAUC);

				}
			}
		}

		AUC = Math.abs(AUC);

		return AUC;

	}

	double linearInterpolation(double[] t, double[] C, double tstar) {
		double Cstar = 0;
		int ifInterpReq = findVal(t, tstar);
		int interpIdx = 0;
		if (ifInterpReq == -1) {
			if (tstar >= t[0])
				for (int i = 0; i < t.length - 1; i++) {
					if (tstar > t[i] && tstar < t[i + 1]) {
						interpIdx = i;
						break;
					}
				}

			Cstar = C[interpIdx]
					+ Math.abs((tstar - t[interpIdx])
							/ (t[interpIdx + 1] - t[interpIdx]))
					* (C[interpIdx + 1] - C[interpIdx]);
		} else
			Cstar = C[ifInterpReq];

		return Cstar;
	}

	/**
	 * Log interpolation.
	 * 
	 * @param t
	 *            the t
	 * @param C
	 *            the c
	 * @param tstar
	 *            the tstar
	 * @return the double
	 */
	double logInterpolation(double[] t, double[] C, double tstar) {
		double Cstar = 0;
		int ifInterpReq = findVal(t, tstar);
		int interpIdx = 0;
		if (ifInterpReq == -1) {
			for (int i = 0; i < t.length - 1; i++) {
				if (tstar > t[i] && tstar < t[i + 1]) {
					interpIdx = i;
					break;
				}
			}
			double term1 = Math.log(C[interpIdx]);
			double term2 = Math.abs((tstar - t[interpIdx])
					/ (t[interpIdx + 1] - t[interpIdx]));
			double term3 = Math.log(C[interpIdx + 1]) - Math.log(C[interpIdx]);
			Cstar = Math.exp(term1 + term2 * term3);
		} else
			Cstar = C[ifInterpReq];

		return Cstar;
	}

	double maxVal(double[] mat) {
		double max = mat[0];
		for (int i = 1; i < mat.length; i++) {
			if (mat[i] > max) {
				max = mat[i];
			}
		}
		return max;
	}

	int findVal(double[] mat, double val) {

		for (int i = 0; i < mat.length; i++) {
			if (mat[i] == val)
				return i;
		}
		return -1;
	}

}
