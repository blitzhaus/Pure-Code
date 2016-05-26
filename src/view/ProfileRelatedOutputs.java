package view;

import java.io.Serializable;
import java.util.Arrays;

public class ProfileRelatedOutputs implements Serializable{

	private static final long serialVersionUID = -4540679419872690986L;

	public double[] getXp() {
		return xp;
	}

	public void setXp(double[] xp) {
		this.xp = xp;
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
		ProfileRelatedOutputs other = (ProfileRelatedOutputs) obj;
		if (!Arrays.equals(convert(aucValue), convert(other.aucValue)))
			return false;
		if (!Arrays.equals(convert(aumcValue), convert(other.aumcValue)))
			return false;
		if (!Arrays.equals(convert(xp), convert(other.xp)))
			return false;
		if (!Arrays.equals(convert(yp), convert(other.yp)))
			return false;
		return true;
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
