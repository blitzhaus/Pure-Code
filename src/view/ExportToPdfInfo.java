package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeriesCollection;

public class ExportToPdfInfo implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7804190514599846344L;


	String[] convert(ArrayList<String> al){
		String[] strAl = new String[al.size()];
		for(int i=0;i<strAl.length;i++){
			strAl[i] = al.get(i);
		}
		return strAl;
	}
	
	
	
	ArrayList<String> text;
	
	
	public ArrayList<String> getText() {
		return text;
	}

	public void setText(ArrayList<String> text) {
		this.text = text;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExportToPdfInfo other = (ExportToPdfInfo) obj;
		if (!Arrays.deepEquals(dosingTable, other.dosingTable))
			return false;
		if (!Arrays.deepEquals(horzParameters, other.horzParameters))
			return false;
		if (!Arrays.deepEquals(subAreasTable, other.subAreasTable))
			return false;
		if (!Arrays.deepEquals(summaryTable, other.summaryTable))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!Arrays.deepEquals(convert(text),convert(other.text)))
			return false;
		if (!Arrays.deepEquals(verticalParameters, other.verticalParameters))
			return false;
		return true;
	}

	public String[][] getVerticalParameters() {
		return verticalParameters;
	}

	public void setVerticalParameters(String[][] verticalParameters) {
		this.verticalParameters = verticalParameters;
	}

	public String[][] getHorzParameters() {
		return horzParameters;
	}

	public void setHorzParameters(String[][] horzParameters) {
		this.horzParameters = horzParameters;
	}

	public String[][] getSummaryTable() {
		return summaryTable;
	}

	public void setSummaryTable(String[][] summaryTable) {
		this.summaryTable = summaryTable;
	}

	public String[][] getDosingTable() {
		return dosingTable;
	}

	public void setDosingTable(String[][] dosingTable) {
		this.dosingTable = dosingTable;
	}

	public String[][] getSubAreasTable() {
		return subAreasTable;
	}

	public void setSubAreasTable(String[][] subAreasTable) {
		this.subAreasTable = subAreasTable;
	}

	public ArrayList<JFreeChart> getLinearPlots() {
		return linearPlots;
	}

	public void setLinearPlots(ArrayList<JFreeChart> linearPlots) {
		this.linearPlots = linearPlots;
	}

	public ArrayList<JFreeChart> getLogPlots() {
		return logPlots;
	}

	public void setLogPlots(ArrayList<JFreeChart> logPlots) {
		this.logPlots = logPlots;
	}

	String[][] verticalParameters;
	String[][] horzParameters;
	String[][] summaryTable;
	String[][] dosingTable;
	String[][] subAreasTable;
	ArrayList<JFreeChart> linearPlots;
	ArrayList<JFreeChart> logPlots;
	
	public ExportToPdfInfo(){
		text = new ArrayList<String>();
		verticalParameters = new String[1][1];
		horzParameters = new String[1][1];
		summaryTable = new String[1][1];
		dosingTable = new String[1][1];
		subAreasTable = new String[1][1];
		linearPlots = new ArrayList<JFreeChart>();
		logPlots = new ArrayList<JFreeChart>();
		
		
		
		
		
	}

}
