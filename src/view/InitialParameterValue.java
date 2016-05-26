package view;

import java.io.Serializable;
import java.util.Arrays;

public class InitialParameterValue implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -7450905464747325061L;

	String[] convert(double[] array){
			String[] strArray = new String[array.length];
			for(int i=0;i<array.length;i++){
				strArray[i] = array[i]+"";
			}
			
			return strArray;
		}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InitialParameterValue other = (InitialParameterValue) obj;
		if (!Arrays.deepEquals(convert(codeGenetratedInitialValue),
				convert(other.codeGenetratedInitialValue)))
			return false;
		if (!Arrays.deepEquals(initialParameterValueForCA,
				other.initialParameterValueForCA))
			return false;
		if (!Arrays.deepEquals(initialParameterValueForLinkModel,
				other.initialParameterValueForLinkModel))
			return false;
		return true;
	}

	String[][] initialParameterValueForCA;

	double[] codeGenetratedInitialValue;
	
	String[][] initialParameterValueForLinkModel;

	public double[] getCodeGenetratedInitialValue() {
		return codeGenetratedInitialValue;
	}

	public void setCodeGenetratedInitialValue(
			double[] codeGenetratedInitialValue) {
		this.codeGenetratedInitialValue = codeGenetratedInitialValue;
	}

	public void setCodeGenetratedInitialValueAt(int xIndex, double value) {
		codeGenetratedInitialValue[xIndex] = value;
	}

	public double getInitialParameterValueAt(int xIndex) {
		return (codeGenetratedInitialValue[xIndex]);
	}

	public String[][] getInitialParameterValueForCA() {
		return initialParameterValueForCA;
	}

	public InitialParameterValue(String[][] initialParameterValueForCA,
			double[] codeGenetratedInitialValue) {
		super();
		this.initialParameterValueForCA = initialParameterValueForCA;
		this.codeGenetratedInitialValue = codeGenetratedInitialValue;
	}

	public InitialParameterValue() {

		initialParameterValueForCA = new String[1][1];
		codeGenetratedInitialValue = new double[1];
		initialParameterValueForLinkModel = new String[1][1];
	}

	
	public void setInitialParameterValueForLinkModelAt(int xIndex, int yIndex, String value) {
		initialParameterValueForLinkModel[xIndex][yIndex] = value;
	}
	
	
	public String[][] getInitialParameterValueForLinkModel() {
		return initialParameterValueForLinkModel;
	}
	
	public String getInitialParameterValueForLinkModelAt(int i, int j) {
		return initialParameterValueForLinkModel[i][j];
	}

	public void setInitialParameterValueForLinkModel(
			String[][] initialParameterValueForLinkModel) {
		this.initialParameterValueForLinkModel = initialParameterValueForLinkModel;
	}

	public void setInitialParameterValueForCA(
			String[][] initialParameterValueForCA) {
		this.initialParameterValueForCA = initialParameterValueForCA;
	}

	public void setInitialParameterValueAt(int xIndex, int yIndex, String value) {
		initialParameterValueForCA[xIndex][yIndex] = value;
	}

	public String getInitialParameterValueAt(int xIndex, int yIndex) {
		return (initialParameterValueForCA[xIndex][yIndex]);
	}

	@Override
	public String toString() {
		String S = "\n\t ";
		for (int i = 0; i < initialParameterValueForCA.length; i++) {
			for (int j = 0; j < initialParameterValueForCA[i].length; j++) {
				S = S + "\t\t" + initialParameterValueForCA[i][j];
			}
			S = S + "\n";
		}

		return S + "\n\n\n";
	}

}
