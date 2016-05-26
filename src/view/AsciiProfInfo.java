package view;

import java.io.Serializable;
import java.util.HashMap;

public class AsciiProfInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nDose;
	private HashMap<String, Double> constantsHm;
	public HashMap<String, Double> getConstantsHm() {
		return constantsHm;
	}
	public void setConstantsHm(HashMap<String, Double> constantsHm) {
		this.constantsHm = constantsHm;
	}
	public int getnDose() {
		return nDose;
	}
	public void setnDose(int nDose) {
		this.nDose = nDose;
	}
	public AsciiProfInfo(){
		nDose = 1;
		constantsHm = new HashMap<String, Double>();
		
	}
}
