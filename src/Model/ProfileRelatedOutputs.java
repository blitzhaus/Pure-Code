package Model;

import java.io.Serializable;

public class ProfileRelatedOutputs implements Serializable{

	public double[] getXp() {
		return xp;
	}

	public void setXp(double[] xp) {
		this.xp = xp;
	}

	public double[] getYp() {
		return yp;
	}

	public void setYp(double[] yp) {
		this.yp = yp;
	}



	public double[] getAucValue() {
		return aucValue;
	}

	public void setAucValue(double[] aucValue) {
		this.aucValue = aucValue;
	}

	public double[] getAumcValue() {
		return aumcValue;
	}

	public void setAumcValue(double[] aumcValue) {
		this.aumcValue = aumcValue;
	}

	private double[] xp;
	private double[] yp;

	private double[] aucValue;
	private double[] aumcValue;
	
	public ProfileRelatedOutputs(){
		
		xp = new double[1];
		yp = new double[1];
		aucValue = new double[1];
		aumcValue = new double[1];
	}
}
