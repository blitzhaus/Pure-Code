package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import view.ApplicationInfo;


import Common.DataReader2;

public class ParameterNamesData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5155025866760000809L;

	private String[] convert(Vector<String> carryAlongVariablesListVector2) {
		
		String[] objAr = new String[carryAlongVariablesListVector2.size()];
		for(int i=0;i<carryAlongVariablesListVector2.size();i++){
			objAr[i] = carryAlongVariablesListVector2.get(i);
		}
		return objAr;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParameterNamesData other = (ParameterNamesData) obj;
		if (defaultParameterNames == null) {
			if (other.defaultParameterNames != null)
				return false;
		} else if (!Arrays.deepEquals(convert(defaultParameterNames), convert(other.defaultParameterNames)))
			return false;
		if (includeInOutput == null) {
			if (other.includeInOutput != null)
				return false;
		} else if (!includeInOutput.equals(other.includeInOutput))
			return false;
		if (prefferedParameternames == null) {
			if (other.prefferedParameternames != null)
				return false;
		} else if (!Arrays.deepEquals(convert(prefferedParameternames), convert(other.prefferedParameternames)))
			return false;
		return true;
	}

	private Vector<String> prefferedParameternames;
	private Vector<String> defaultParameterNames;
	private Vector<Boolean> includeInOutput;
	private HashMap<String, String[]> parameterNamesMap;

	public HashMap<String, String[]> getParameterNamesMap() {
		return parameterNamesMap;
	}



	public void setParameterNamesMap(HashMap<String, String[]> parameterNamesMap) {
		this.parameterNamesMap = parameterNamesMap;
	}



	@Override
	public String toString() {
		String consoleS = "Printing Parameter Names";
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String s  = "";
		
		s = s + "\n\t//ParameterNamesData \n";
		s = s + "\t\t Preffered parameter names = ";
		for(int i=0;i<prefferedParameternames.size();i++){
			s = s +prefferedParameternames.get(i)+ " ,"; 
		}
		
		s =s + "\n" + "\t\tdefaultParameterNames = ";
		for(int i=0;i<defaultParameterNames.size();i++){
			s = s+defaultParameterNames.get(i)+" ,";
		}
		s = s +"\n"+ "\t\tinclude in output array = ";
		for(int i=0;i< includeInOutput.size();i++){
			s =s + includeInOutput.get(i)+ " ,";
		}
		//p.append(s);
		return s;
	}

	
	public Vector<String> getDefaultParameterNames() {
		return defaultParameterNames;
	}

	public String getDefaultameAt(int index){
		return defaultParameterNames.get(index);
	}
	public void setDefaultNameAt(int index, String value){
		defaultParameterNames.set(index, value);
	}
	
	public void setDefaultParameterNames(Vector<String> defaultParameterNames) {
		this.defaultParameterNames = defaultParameterNames;
	}

	public Vector<Boolean> getIncludeInOutput() {
		return includeInOutput;
	}

	public void setIncludeInOutput(Vector<Boolean> includeInOutput) {
		this.includeInOutput = includeInOutput;
	}

	public Vector<String> getPrefferedParameternames() {
		return prefferedParameternames;
	}

	public void setPrefferedParameternames(Vector<String> prefferedParameternames) {
		this.prefferedParameternames = prefferedParameternames;
	}
	
	public String getPrefferedParameterNameAt(int index){
		return prefferedParameternames.get(index);
	}
	
	public void setPrefferedNameAt(int index, String value){
		prefferedParameternames.set(index, value);
	}
	
	public ParameterNamesData(){
		prefferedParameternames = new Vector<String>();
		defaultParameterNames = new Vector<String>();
		includeInOutput = new Vector<Boolean>();
		parameterNamesMap = new HashMap<String, String[]>();
		
		//the default values will be those of the serial and oral combination
		String filePath = "serialOralParameterNames.txt";
		DataReader2 d = new DataReader2(filePath,"String");
		String[][] dataFromFile = d.new_StringFileArray;
		
		for (int i = 0; i < dataFromFile.length; i++) {
			for (int j = 0; j < dataFromFile[i].length; j++) {

				// adding to the preferred parameter names arraylist
				prefferedParameternames
						.add(dataFromFile[i][j]);

				// adding to the default parameter names arraylist
				defaultParameterNames.add(
								dataFromFile[i][j]);
				
				includeInOutput.add(true);
			}
		}
		
		
		
		
	}
}
