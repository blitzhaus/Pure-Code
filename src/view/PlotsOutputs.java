package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import org.jfree.chart.JFreeChart;

public class PlotsOutputs implements Serializable{

	private static final long serialVersionUID = 1918110413008962414L;
	ArrayList<JFreeChart> linearPlots;
	ArrayList<JFreeChart> logplots;
	ArrayList<String> plotMavenProfileList;
	ArrayList<JFreeChart> linearPlotsForObsevedYVsWeightedPredictedY;
	ArrayList<JFreeChart> linearPlotForPartialDerivative;
	ArrayList<JFreeChart> linearPlotForWeightedPredictedYVsWeightedResidualY;
	ArrayList<JFreeChart> linearPlotForXVsWeightedResidualY;
	ArrayList<JFreeChart> logPlotForObsevedYVsWeightedPredictedY;
	ArrayList<JFreeChart> logPlotForPartialDerivative;
	ArrayList<JFreeChart> logPlotForWeightedPredictedYVsWeightedResidualY; 
	ArrayList<JFreeChart> logPlotForXVsWeightedResidualY;
	
	private int numberOfPlotNodes;
	private String profileForDisplay;
	
	
 	
	
	public String getProfileForDisplay() {
		return profileForDisplay;
	}

	public void setProfileForDisplay(String profileForDisplay) {
		this.profileForDisplay = profileForDisplay;
	}

	public int getNumberOfPlotNodes() {
		return numberOfPlotNodes;
	}

	public void setNumberOfPlotNodes(int numberOfPlotNodes) {
		this.numberOfPlotNodes = numberOfPlotNodes;
	}

	public PlotsOutputs(){
		linearPlots = new ArrayList<JFreeChart>();
		logplots = new ArrayList<JFreeChart>();
		plotMavenProfileList = new ArrayList<String>();
		linearPlotsForObsevedYVsWeightedPredictedY = new ArrayList<JFreeChart>();
		linearPlotForPartialDerivative = new ArrayList<JFreeChart>();
		linearPlotForWeightedPredictedYVsWeightedResidualY = new ArrayList<JFreeChart>();
		linearPlotForXVsWeightedResidualY = new ArrayList<JFreeChart>();
		
		logPlotForObsevedYVsWeightedPredictedY = new ArrayList<JFreeChart>();
		logPlotForPartialDerivative = new ArrayList<JFreeChart>();
		logPlotForWeightedPredictedYVsWeightedResidualY = new ArrayList<JFreeChart>(); 
		logPlotForXVsWeightedResidualY = new ArrayList<JFreeChart>();
		
		numberOfPlotNodes = 0;
		profileForDisplay = "";
	
	}

	
	String[] convert(ArrayList<String> al){
		String[] str = new String[al.size()];
		for(int i=0;i<str.length;i++){
			str[i] = al.get(i);
		}
		return str;
	}
	


	public void setLinearPlotsForObsevedYVsWeightedPredictedY(
			ArrayList<JFreeChart> linearPlotsForObsevedYVsWeightedPredictedY) {
		this.linearPlotsForObsevedYVsWeightedPredictedY = linearPlotsForObsevedYVsWeightedPredictedY;
	}

	public ArrayList<JFreeChart> getLinearPlotsForObsevedYVsWeightedPredictedY() {
		return linearPlotsForObsevedYVsWeightedPredictedY;
	}

	public ArrayList<JFreeChart> getLinearPlotForPartialDerivative() {
		return linearPlotForPartialDerivative;
	}


	public void setLinearPlotForPartialDerivative(
			ArrayList<JFreeChart> linearPlotForPartialDerivative) {
		this.linearPlotForPartialDerivative = linearPlotForPartialDerivative;
	}


	public ArrayList<JFreeChart> getLinearPlotForWeightedPredictedYVsWeightedResidualY() {
		return linearPlotForWeightedPredictedYVsWeightedResidualY;
	}


	public void setLinearPlotForWeightedPredictedYVsWeightedResidualY(
			ArrayList<JFreeChart> linearPlotForWeightedPredictedYVsWeightedResidualY) {
		this.linearPlotForWeightedPredictedYVsWeightedResidualY = linearPlotForWeightedPredictedYVsWeightedResidualY;
	}


	public ArrayList<JFreeChart> getLinearPlotForXVsWeightedResidualY() {
		return linearPlotForXVsWeightedResidualY;
	}


	public void setLinearPlotForXVsWeightedResidualY(
			ArrayList<JFreeChart> linearPlotForXVsWeightedResidualY) {
		this.linearPlotForXVsWeightedResidualY = linearPlotForXVsWeightedResidualY;
	}


	public ArrayList<JFreeChart> getLogPlotForObsevedYVsWeightedPredictedY() {
		return logPlotForObsevedYVsWeightedPredictedY;
	}


	public void setLogPlotForObsevedYVsWeightedPredictedY(
			ArrayList<JFreeChart> logPlotForObsevedYVsWeightedPredictedY) {
		this.logPlotForObsevedYVsWeightedPredictedY = logPlotForObsevedYVsWeightedPredictedY;
	}


	public ArrayList<JFreeChart> getLogPlotForPartialDerivative() {
		return logPlotForPartialDerivative;
	}


	public void setLogPlotForPartialDerivative(
			ArrayList<JFreeChart> logPlotForPartialDerivative) {
		this.logPlotForPartialDerivative = logPlotForPartialDerivative;
	}


	public ArrayList<JFreeChart> getLogPlotForWeightedPredictedYVsWeightedResidualY() {
		return logPlotForWeightedPredictedYVsWeightedResidualY;
	}


	public void setLogPlotForWeightedPredictedYVsWeightedResidualY(
			ArrayList<JFreeChart> logPlotForWeightedPredictedYVsWeightedResidualY) {
		this.logPlotForWeightedPredictedYVsWeightedResidualY = logPlotForWeightedPredictedYVsWeightedResidualY;
	}


	public ArrayList<JFreeChart> getLogPlotForXVsWeightedResidualY() {
		return logPlotForXVsWeightedResidualY;
	}


	public void setLogPlotForXVsWeightedResidualY(
			ArrayList<JFreeChart> logPlotForXVsWeightedResidualY) {
		this.logPlotForXVsWeightedResidualY = logPlotForXVsWeightedResidualY;
	}

	public ArrayList<String> getPlotMavenProfileList() {
		return plotMavenProfileList;
	}


	public void setPlotMavenProfileList(ArrayList<String> plotMavenProfileList) {
		this.plotMavenProfileList = plotMavenProfileList;
	}


	public ArrayList<JFreeChart> getLinearPlots() {
		return linearPlots;
	}


	public void setLinearPlots(ArrayList<JFreeChart> linearPlots) {
		this.linearPlots = linearPlots;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlotsOutputs other = (PlotsOutputs) obj;
		if (numberOfPlotNodes != other.numberOfPlotNodes)
			return false;
		if (plotMavenProfileList == null) {
			if (other.plotMavenProfileList != null)
				return false;
		} else if (!Arrays.deepEquals(convert(plotMavenProfileList),convert(other.plotMavenProfileList)))
			return false;
		if (profileForDisplay == null) {
			if (other.profileForDisplay != null)
				return false;
		} else if (!profileForDisplay.equals(other.profileForDisplay))
			return false;
		return true;
	}

	public ArrayList<JFreeChart> getLogplots() {
		return logplots;
	}


	public void setLogplots(ArrayList<JFreeChart> logplots) {
		this.logplots = logplots;
	}
	
	
}
