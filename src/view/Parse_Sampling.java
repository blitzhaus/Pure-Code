package view;

import java.io.Serializable;
import java.util.ArrayList;

import view.ApplicationInfo;
import view.DisplayContents;

public class Parse_Sampling {
	public static double[][] SparseSampling(double[] X, double[] Y, int no_obs) {
		double[][] sparse_sample = new double[no_obs][2];
		
	
		
		double sum = 0;
		int count = 0;
		int count1 = 0;
		int i = 0;
		while (i < no_obs - 1) {
			sum = Y[i];
			count = 1;
			while ((i < no_obs - 1) && (X[i] == X[i + 1])) {
				sum = sum + Y[i + 1];
				i++;
				count++;

			}
			sparse_sample[count1][0] = X[i];
			sparse_sample[count1][1] = sum / count;
			count1 = count1 + 1;
			i++;
		}
		
		if((i == no_obs - 1) && (X[i-1] != X[i ]))
		{
			sparse_sample[count1][0] = X[i];
			sparse_sample[count1][1] = Y[i];
		}
		
		
		return sparse_sample;

	}

	public static ArrayList<StdErrorAndObsCount> calculateStandardError(
			double[] X, double[] Y, int no_obs) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ArrayList<StdErrorAndObsCount> list = new ArrayList<StdErrorAndObsCount>();

		double[] se = new double[no_obs];
		double sum = 0;
		int count = 0;
		int count1 = 0;
		int i = 0;
		while (i < no_obs - 1) {
			sum = Y[i];
			count = 1;
			while ((i < no_obs) && (X[i] == X[i + 1])) {
				sum = sum + Y[i + 1];
				i++;
				count++;

			}

			double weight = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.getWeightingOption();
			weight = Math.pow(X[i], (-1) * (weight));
			double mean = (sum / count);
			double numerator = 0;
			for (int m = 0; m < X.length; m++) {
				numerator = numerator + weight * (Math.pow(X[m] - mean, 2));

			}

			double sd = Math.sqrt(numerator / count - 1); // this is sample
															// standard
															// deviation hence
															// we have the count
															// -1

			se[count1] = sd / Math.sqrt(count);
			StdErrorAndObsCount obj = new StdErrorAndObsCount();
			obj.setSe(se[count1]);
			obj.setObsCount(count);
			list.add(obj);

			count1 = count1 + 1;
			i++;
		}
		return list;
	}

	public static int[] numberOfObservations(double[] xSparse,
			double[] ySparse, int i) {

		return null;
	}

}

class StdErrorAndObsCount implements Serializable{
	
	private static final long serialVersionUID = -4591816487881068591L;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StdErrorAndObsCount other = (StdErrorAndObsCount) obj;
		if (obsCount != other.obsCount)
			return false;
		if (Double.doubleToLongBits(se) != Double.doubleToLongBits(other.se))
			return false;
		return true;
	}

	private double se;

	public double getSe() {
		return se;
	}

	public void setSe(double se) {
		this.se = se;
	}

	public int getObsCount() {
		return obsCount;
	}

	public void setObsCount(int obsCount) {
		this.obsCount = obsCount;
	}

	private int obsCount;
}
