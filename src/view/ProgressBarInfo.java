package view;

import java.io.Serializable;

public class ProgressBarInfo implements Serializable{

	private static final long serialVersionUID = 6986717694712693054L;
	private int minVal;
	private int maxVal;
	private int currentIterNo;
	
	
	public ProgressBarInfo() {
		
		minVal = 0;
		maxVal = 0;
		currentIterNo = 0;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgressBarInfo other = (ProgressBarInfo) obj;
		if (currentIterNo != other.currentIterNo)
			return false;
		if (maxVal != other.maxVal)
			return false;
		if (minVal != other.minVal)
			return false;
		return true;
	}
	public int getMinVal() {
		return minVal;
	}
	public void setMinVal(int minVal) {
		this.minVal = minVal;
	}
	public int getMaxVal() {
		return maxVal;
	}
	public void setMaxVal(int maxVal) {
		this.maxVal = maxVal;
	}
	public int getCurrentIterNo() {
		return currentIterNo;
	}
	public void setCurrentIterNo(int currentIterNo) {
		this.currentIterNo = currentIterNo;
	}
	

}
