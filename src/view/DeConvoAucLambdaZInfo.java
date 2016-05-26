package view;

import java.io.Serializable;

public class DeConvoAucLambdaZInfo implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6982355185388395486L;
	private boolean ifPredAucInfUsed;
	private boolean ifObsAucInfUsed;
	private boolean ifGivenAucInfUsed;
	private String userGivenAucInf;
	private String userGivenLambdaZ;
	private String userGivenIntercept;
	private boolean ifGivenLamZIntUsed;
	private boolean ifGivenRange;
	
	private String userGivenStartTime;
	private String userGivenEndTime;
	
	
	public boolean isIfGivenRange() {
		return ifGivenRange;
	}
	public void setIfGivenRange(boolean ifGivenRange) {
		this.ifGivenRange = ifGivenRange;
	}
	public boolean isIfGivenLamZIntUsed() {
		return ifGivenLamZIntUsed;
	}
	public void setIfGivenLamZIntUsed(boolean ifGivenLamZIntUsed) {
		this.ifGivenLamZIntUsed = ifGivenLamZIntUsed;
	}
	public DeConvoAucLambdaZInfo() {
		
		ifPredAucInfUsed = true;
		ifObsAucInfUsed = false;
		ifGivenAucInfUsed = false;
		ifGivenLamZIntUsed = false; 
		ifGivenRange = false;
		userGivenAucInf = "";
		userGivenLambdaZ = "";
		userGivenIntercept = "";
		userGivenEndTime = "";
		userGivenStartTime = "";
	}
	public String getUserGivenStartTime() {
		return userGivenStartTime;
	}
	public void setUserGivenStartTime(String userGivenStartTime) {
		this.userGivenStartTime = userGivenStartTime;
	}
	public String getUserGivenEndTime() {
		return userGivenEndTime;
	}
	public void setUserGivenEndTime(String userGivenEndTime) {
		this.userGivenEndTime = userGivenEndTime;
	}
	public boolean isIfPredAucInfUsed() {
		return ifPredAucInfUsed;
	}
	public void setIfPredAucInfUsed(boolean ifPredAucInfUsed) {
		this.ifPredAucInfUsed = ifPredAucInfUsed;
	}
	public boolean isIfObsAucInfUsed() {
		return ifObsAucInfUsed;
	}
	public void setIfObsAucInfUsed(boolean ifObsAucInfUsed) {
		this.ifObsAucInfUsed = ifObsAucInfUsed;
	}
	public boolean isIfGivenAucInfUsed() {
		return ifGivenAucInfUsed;
	}
	public void setIfGivenAucInfUsed(boolean ifGivenAucInfUsed) {
		this.ifGivenAucInfUsed = ifGivenAucInfUsed;
	}
	public String getUserGivenAucInf() {
		return userGivenAucInf;
	}
	public void setUserGivenAucInf(String userGivenAucInf) {
		this.userGivenAucInf = userGivenAucInf;
	}
	public String getUserGivenLambdaZ() {
		return userGivenLambdaZ;
	}
	public void setUserGivenLambdaZ(String userGivenLambdaZ) {
		this.userGivenLambdaZ = userGivenLambdaZ;
	}
	public String getUserGivenIntercept() {
		return userGivenIntercept;
	}
	public void setUserGivenIntercept(String userGivenIntercept) {
		this.userGivenIntercept = userGivenIntercept;
	}
	
	

	
}
