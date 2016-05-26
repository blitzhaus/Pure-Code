package view;

import java.io.Serializable;

public class InVitroModelInput implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3257132510383107916L;
	int ifHillModel;
	public int getIfHillModel() {
		return ifHillModel;
	}
	public void setIfHillModel(int ifHillModel) {
		this.ifHillModel = ifHillModel;
	}
	public int getIfWeibulModel() {
		return ifWeibulModel;
	}
	public void setIfWeibulModel(int ifWeibulModel) {
		this.ifWeibulModel = ifWeibulModel;
	}
	public int getIfDoubleWeibulModel() {
		return ifDoubleWeibulModel;
	}
	public void setIfDoubleWeibulModel(int ifDoubleWeibulModel) {
		this.ifDoubleWeibulModel = ifDoubleWeibulModel;
	}
	public int getIfMakoidBanakarModel() {
		return ifMakoidBanakarModel;
	}
	public void setIfMakoidBanakarModel(int ifMakoidBanakarModel) {
		this.ifMakoidBanakarModel = ifMakoidBanakarModel;
	}
	int ifWeibulModel;
	int ifDoubleWeibulModel;
	int ifMakoidBanakarModel;
	int weightingIndex;
	public int getWeightingIndex() {
		return weightingIndex;
	}
	public void setWeightingIndex(int weightingIndex) {
		this.weightingIndex = weightingIndex;
	}
	public InVitroModelInput() {
		
		ifHillModel = 0;
		ifWeibulModel = 0;
		ifDoubleWeibulModel = 0;
		ifMakoidBanakarModel = 0;
		weightingIndex = 0;
	}
	

}
