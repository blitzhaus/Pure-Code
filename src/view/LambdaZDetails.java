package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import Common.Comparator;
import Common.MyComparator;

public class LambdaZDetails implements Serializable{

	private static final long serialVersionUID = -5629794820630731261L;
	private boolean includeRecord;
	private double startTime;
	private double endTime;
	private int methodSelected;
	private double rSquare;
	private double adjRSquare;
	private double lambdaZ;
	private double[] predictedConc;
	int noPtsLambdaZ;
	double correlation;
	double lambdaZLower;
	double lambdaZUpper;
	double[] timeForPredVal;
	
	
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		Comparator comp = MyComparator.createMyCompInst();
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LambdaZDetails other = (LambdaZDetails) obj;
		if (InclusionExcusionPoints == null) {
			if (other.InclusionExcusionPoints != null)
				return false;
		} else if (!comp.compareIntBool(InclusionExcusionPoints, other.InclusionExcusionPoints))
			return false;
		if (Double.doubleToLongBits(adjRSquare) != Double
				.doubleToLongBits(other.adjRSquare))
			return false;
		if (Double.doubleToLongBits(correlation) != Double
				.doubleToLongBits(other.correlation))
			return false;
		if (Double.doubleToLongBits(endTime) != Double
				.doubleToLongBits(other.endTime))
			return false;
		if (includeRecord != other.includeRecord)
			return false;
		if (intermediateOutputForLambdaZ == null) {
			if (other.intermediateOutputForLambdaZ != null)
				return false;
		} else if (!Arrays.deepEquals(convert(intermediateOutputForLambdaZ), convert(other.intermediateOutputForLambdaZ)))
			return false;
		if (Double.doubleToLongBits(lambdaZ) != Double
				.doubleToLongBits(other.lambdaZ))
			return false;
		if (Double.doubleToLongBits(lambdaZLower) != Double
				.doubleToLongBits(other.lambdaZLower))
			return false;
		if (Double.doubleToLongBits(lambdaZUpper) != Double
				.doubleToLongBits(other.lambdaZUpper))
			return false;
		if (methodSelected != other.methodSelected)
			return false;
		if (noPtsLambdaZ != other.noPtsLambdaZ)
			return false;
		if (!Arrays.equals(predictedConc, other.predictedConc))
			return false;
		if (Double.doubleToLongBits(rSquare) != Double
				.doubleToLongBits(other.rSquare))
			return false;
		if (Double.doubleToLongBits(startTime) != Double
				.doubleToLongBits(other.startTime))
			return false;
		if (!Arrays.equals(timeForPredVal, other.timeForPredVal))
			return false;
		return true;
	}
	
	
	private String[] convert(ArrayList<String> intermediateOutputForLambdaZ2) {
		String[] al = new String[intermediateOutputForLambdaZ2.size()];
		for(int i=0;i<al.length;i++){
			al[i] = intermediateOutputForLambdaZ2.get(i);
		}
		return al;
	}
	ArrayList<String> intermediateOutputForLambdaZ;
 
	Hashtable<Integer, Boolean> InclusionExcusionPoints;
	public Hashtable<Integer, Boolean> getInclusionExcusionPoints() {
		return InclusionExcusionPoints;
	}
	public void setInclusionExcusionPoints(
			Hashtable<Integer, Boolean> inclusionExcusionPoints) {
		InclusionExcusionPoints = inclusionExcusionPoints;
	}
	public double[] getPredictedConc() {
		return predictedConc;
	}
	public void setPredictedConc(double[] predictedConc) {
		this.predictedConc = predictedConc;
	}
	public LambdaZDetails(){
		startTime = 0;
		endTime = 0;
		lambdaZ = 0;
		adjRSquare = 0;
		rSquare = 0;
		methodSelected = 0;
		includeRecord = true;
		predictedConc = new double[1];
		noPtsLambdaZ = 0;
		correlation = 0;
		lambdaZLower = 0;
		lambdaZUpper = 0;
		timeForPredVal = new double[1];
		intermediateOutputForLambdaZ = new ArrayList<String>();
		 InclusionExcusionPoints = new Hashtable<Integer, Boolean>();
	}
	public int getNoPtsLambdaZ() {
		return noPtsLambdaZ;
	}
	public void setNoPtsLambdaZ(int noPtsLambdaZ) {
		this.noPtsLambdaZ = noPtsLambdaZ;
	}
	public double getCorrelation() {
		return correlation;
	}
	public void setCorrelation(double correlation) {
		this.correlation = correlation;
	}
	public double getLambdaZLower() {
		return lambdaZLower;
	}
	public void setLambdaZLower(double lambdaZLower) {
		this.lambdaZLower = lambdaZLower;
	}
	public double getLambdaZUpper() {
		return lambdaZUpper;
	}
	public void setLambdaZUpper(double lambdaZUpper) {
		this.lambdaZUpper = lambdaZUpper;
	}
	public double[] getTimeForPredVal() {
		return timeForPredVal;
	}
	public void setTimeForPredVal(double[] timeForPredVal) {
		this.timeForPredVal = timeForPredVal;
	}
	public double getStartTime() {
		return startTime;
	}
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}
	public double getEndTime() {
		return endTime;
	}
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
	public int getMethodSelected() {
		return methodSelected;
	}
	public void setMethodSelected(int methodSelected) {
		this.methodSelected = methodSelected;
	}
	public double getrSquare() {
		return rSquare;
	}
	public void setrSquare(double rSquare) {
		this.rSquare = rSquare;
	}
	public double getAdjRSquare() {
		return adjRSquare;
	}
	public void setAdjRSquare(double adjRSquare) {
		this.adjRSquare = adjRSquare;
	}
	public double getLambdaZ() {
		return lambdaZ;
	}
	public void setLambdaZ(double lambdaZ) {
		this.lambdaZ = lambdaZ;
	}

	public boolean isIncludeRecord() {
		return includeRecord;
	}
	public void setIncludeRecord(boolean includeRecord) {
		this.includeRecord = includeRecord;
	}
	public double getxValue() {
		return startTime;
	}
	public void setxValue(double xValue) {
		this.startTime = xValue;
	}
	public double getyValue() {
		return endTime;
	}
	public void setyValue(double yValue) {
		this.endTime = yValue;
	}
	@Override
	public String toString() {
		String s = "\t\t LambdaZ = "+lambdaZ+"\n";
				s = s + "\t\t rSquare = "+rSquare +"\n";
				s = s + "\t\t adj Rsquare = "+adjRSquare + "\n";
				s = s + "\t\t method selectd = "+methodSelected +"\n";
				s = s + "\t\t end time = "+endTime+"\n";
				s = s + "\t\t start time = "+startTime +"\n";
				s = s + "\t\t Include record = "+includeRecord+"\n"; 
		
		
		return s;
	}
	
}


