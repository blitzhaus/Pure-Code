package Model;

import org.apache.commons.math.MathException;
import org.apache.commons.math.stat.StatUtils;
import org.apache.commons.math.distribution.ChiSquaredDistribution;
import org.apache.commons.math.distribution.ChiSquaredDistributionImpl;

public class DDUtils {
	
	public static int N(double[] ipVals)
	{
		int Nobs = NObs(ipVals);
		int Nmiss = NMiss(ipVals);
		
		return (Nobs - Nmiss);
	}
	
	public static int NMiss(double[] ipVals)
	{
		int missingValCounter = 0;
		for (int i = 0; i < ipVals.length; i++) {
			if (ipVals[i] == -99999.99999)
			{
				missingValCounter++;
			}
		}
		
		return missingValCounter;
	}
	
	public static int NObs(double[] ipVals)
	{
		return ipVals.length;
	}
	
	public static double mean(double[] ipVals)
	{
		return StatUtils.mean(ipVals);
	}
	
	public static double maximum(double[] ipVals)
	{
		return StatUtils.max(ipVals);
	}
	
	public static double minimum(double[] ipVals)
	{
		return StatUtils.min(ipVals);
	}
	
	public static double variance(double[] ipVals)
	{
		return StatUtils.variance(ipVals);
	}
	
	public static double stdDeviation(double[] ipVals)
	{
		return Math.sqrt(StatUtils.variance(ipVals));
	}
	
	public static double stdError(double[] ipVals)
	{
		double stdDeviation = stdDeviation(ipVals);
		int N = ipVals.length;
		double stdErr = stdDeviation / Math.sqrt(N);
		
		return stdErr;
	}
	
	public static double geomMean(double[] ipVals)
	{
		return StatUtils.geometricMean(ipVals);
	}
	
	public static double harmonicMean(double[] ipVals)
	{
		double[] reciprocals = new double[ipVals.length];
		
		for (int i = 0; i < ipVals.length; i++) {
			reciprocals[i] = Math.pow(ipVals[i],-1);
		}
		
		return mean(reciprocals);
	}
	
	public static double median(double[] ipVals)
	{
		return StatUtils.percentile(ipVals, 50);
	}
	
	public static double percentile(double[] ipVals, int whichPercentile)
	{
		return StatUtils.percentile(ipVals, whichPercentile);
	}
	
	public static double range(double[] ipVals)
	{
		return (StatUtils.max(ipVals) - StatUtils.min(ipVals));
	}
	
	public static double cvPercent(double[] ipVals)
	{
		return (stdDeviation(ipVals) / mean(ipVals) ) * 100.0;
	}
	
	public static double meanOfLogs(double[] ipVals)
	{
		double[] logVals = new double[ipVals.length];
		
		for (int i = 0; i < ipVals.length; i++) {
			logVals[i] = Math.log(ipVals[i]);
		}
		
		return mean(logVals);
	}
	
	public static double stdDevnOfLogs(double[] ipVals)
	{
		double[] logVals = new double[ipVals.length];
		
		for (int i = 0; i < ipVals.length; i++) {
			logVals[i] = Math.log(ipVals[i]);
		}
		
		return stdDeviation(logVals);
	}
	
	public static double coeffOfVariationGM(double[] ipVals)
	{
		double stdDevnOfLogs = stdDevnOfLogs(ipVals);
		
		double coeffOfVariationGM = 100 * Math.sqrt(Math.exp(Math.pow(stdDevnOfLogs,2))-1);
		
		return coeffOfVariationGM;
	}
	
	public static double lower1SD(double[] ipVals)
	{
		double mean = mean(ipVals);
		double stdDevn = stdDeviation(ipVals);
		return (mean-stdDevn);
	}
	
	public static double confIntLowerMean95PerCent(double[] ipVals)
	{
		double mean = mean(ipVals);
		double stdError = stdError(ipVals);
		double lowerBound = mean - (1.96 * stdError);
		
		return lowerBound;
		
	}
	
	public static double confIntUpperMean95PerCent(double[] ipVals)
	{
		double mean = mean(ipVals);
		double stdError = stdError(ipVals);
		double upperBound = mean + (1.96 * stdError);
		
		return upperBound;
		
	}
	
	public static double confIntUpperVariance95PerCent(double[] ipVals)
	{
		ChiSquaredDistribution chiSqDistbn = new ChiSquaredDistributionImpl(ipVals.length-1);
		double chiSq = 0.0;

		try {
			chiSq = chiSqDistbn.inverseCumulativeProbability((0.05 / 2.0));
			//chiSq = chiSqDistbn.inverseCumulativeProbability(0.05/2.0);
		} catch (MathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double sSq = variance(ipVals);
		double lowerBound =  ((ipVals.length-1) * sSq) / chiSq;
		return lowerBound;
		
	}
	
	public static double confIntLowerVariance95PerCent(double[] ipVals)
	{
		ChiSquaredDistribution chiSqDistbn = new ChiSquaredDistributionImpl(
				ipVals.length - 1);
		double chiSq = 0.0;

		try {
			chiSq = chiSqDistbn.inverseCumulativeProbability(1 - (0.05 / 2.0));
			//chiSq = chiSqDistbn.inverseCumulativeProbability(0.05/2.0);
		} catch (MathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double sSq = variance(ipVals);

		double upperBound = ((ipVals.length - 1) * sSq) / chiSq;
		return upperBound;
		
	}
	

}
