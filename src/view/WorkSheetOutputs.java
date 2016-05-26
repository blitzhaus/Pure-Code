package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import Model.GroupCVDetails;
import Model.PlotsOutputs;
import Model.ProfileRelatedOutputs;
import Model.SpreadSheetOutputs;
import Model.Template12Results;

import view.ApplicationInfo;



// only spread sheet output is stored in the AppInfo hierarchy. The text output is stored in a file as it may be too long for a string to handle
// and plots also have to be stored in hard disk.
public class WorkSheetOutputs implements Serializable{

	
	private static final long serialVersionUID = -764981966970643691L;
	//For NCA
	private SpreadSheetOutputs spreadSheetOutputs;
	private PlotsOutputs plotOutputs;
	private ArrayList<String> textoutput;
	private ArrayList<double[]> stdError;
	public ArrayList<double[]> getStdError() {
		return stdError;
	}

	public void setStdError(ArrayList<double[]> stdError) {
		this.stdError = stdError;
	}
	
	String[] convert(ArrayList<String> al){
		String[] str = new String[al.size()];
		for(int i=0;i<str.length;i++){
			str[i] = al.get(i);
		}
		return str;
	}
	
	String[] convert(double[] al){
		String[] str = new String[al.length];
		for(int i=0;i<str.length;i++){
			str[i] = al[i]+"";
		}
		return str;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkSheetOutputs other = (WorkSheetOutputs) obj;
		if (!Arrays.equals(convert(bqlOriginalConcCopy), convert(other.bqlOriginalConcCopy)))
			return false;
		if (parameterValuesAL == null) {
			if (other.parameterValuesAL != null)
				return false;
		} else if (!convert(parameterValuesAL).equals(convert(other.parameterValuesAL)))
			return false;
		if (plotOutputs == null) {
			if (other.plotOutputs != null)
				return false;
		} else if (!plotOutputs.equals(other.plotOutputs))
			return false;
		/*if (profileRelateroutput == null) {
			if (other.profileRelateroutput != null)
				return false;
		} else if (!profileRelateroutput.equals(other.profileRelateroutput))
			return false;*/
		if (spreadSheetOutputs == null) {
			if (other.spreadSheetOutputs != null)
				return false;
		} else if (!spreadSheetOutputs.equals(other.spreadSheetOutputs))
			return false;
		if (!Arrays.deepEquals(template1Results, other.template1Results))
			return false;
		if (!Arrays.deepEquals(template2Results, other.template2Results))
			return false;
		if (!Arrays.deepEquals(template3Results, other.template3Results))
			return false;
		/*if (template6Results == null) {
			if (other.template6Results != null)
				return false;
		} else if (!template6Results.equals(other.template6Results))
			return false;*/
		if (textoutput == null) {
			if (other.textoutput != null)
				return false;
		} else if (!Arrays.deepEquals(convert(textoutput), convert(other.textoutput)))
			return false;
		return true;
	}



	private ArrayList<String> parameterValuesAL;
	private double[] bqlOriginalConcCopy;

	Template12Results[] template1Results;
	Template12Results[] template2Results;
	Template12Results[] template3Results;

	public double[] getBqlOriginalConcCopy() {
		return bqlOriginalConcCopy;
	}

	public void setBqlOriginalConcCopy(double[] bqlOriginalConcCopy) {
		this.bqlOriginalConcCopy = bqlOriginalConcCopy;
	}

	Hashtable<GroupCVDetails, Template12Results[]> template6Results;

	public Hashtable<GroupCVDetails, Template12Results[]> getTemplate6Results() {
		return template6Results;
	}

	public void setTemplate6Results(
			Hashtable<GroupCVDetails, Template12Results[]> template6Results) {
		this.template6Results = template6Results;
	}

	public Template12Results[] getTemplate3Results() {
		return template3Results;
	}

	public void setTemplate3Results(Template12Results[] template3Results) {
		this.template3Results = template3Results;
	}

	public Template12Results[] getTemplate2Results() {
		return template2Results;
	}

	public void setTemplate2Results(Template12Results[] template2Results) {
		this.template2Results = template2Results;
	}

	public Template12Results[] getTemplate1Results() {
		return template1Results;
	}

	public void setTemplate1Results(Template12Results[] template1Results) {
		this.template1Results = template1Results;
	}

	
	private ArrayList<ProfileRelatedOutputs> profileRelateroutput;

	public ArrayList<ProfileRelatedOutputs> getProfileRelateroutput() {
		return profileRelateroutput;
	}

	public void setProfileRelateroutput(
			ArrayList<ProfileRelatedOutputs> profileRelateroutput) {
		this.profileRelateroutput = profileRelateroutput;
	}

	public ArrayList<String> getParameterValuesAL() {
		return parameterValuesAL;
	}

	public void setParameterValues(ArrayList<String> parameterValues) {
		this.parameterValuesAL = parameterValues;
	}

	public ArrayList<String> getTextoutput() {
		return textoutput;
	}

	public void setTextoutput(ArrayList<String> textoutput) {
		this.textoutput = textoutput;
	}


	

	public PlotsOutputs getPlotOutputs() {
		return plotOutputs;
	}

	public void setPlotOutputs(PlotsOutputs plotOutputs) {
		this.plotOutputs = plotOutputs;
	}

	@Override
	public String toString() {
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String consoleS = "\tprinting work sheet outputs\n";
		
		String s = "Sheet outputs \n";
		s = s + ""+spreadSheetOutputs;
		//p.append(s);
		return s;
			
	}

	public SpreadSheetOutputs getSpreadSheetOutputs() {
		return spreadSheetOutputs;
	}

	public void setSpreadSheetOutputs(SpreadSheetOutputs spreadSheetOutputs) {
		this.spreadSheetOutputs = spreadSheetOutputs;
	}
	
	
	public WorkSheetOutputs(){
		spreadSheetOutputs = new SpreadSheetOutputs();
		plotOutputs = new PlotsOutputs();
		textoutput = new ArrayList<String>();
		parameterValuesAL = new ArrayList<String>();
		profileRelateroutput = new ArrayList<ProfileRelatedOutputs>();
		bqlOriginalConcCopy = new double[1];

	}
}
