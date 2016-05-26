package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Arrays;

import view.ApplicationInfo;


public class lambdazData implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1092644303188968149L;


	@Override
	public String toString() {
		String consoleS = "\tPrinting Lambdaz information\n";
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String s =  "\nlambdaz for all profile\n";
		for(int i=0;i<lambdaZDetails.length;i++){
			
			s = s + "For profile "+(i+1)+"\n";
				s = s + "\t"+ lambdaZDetails[i]+"\n";
				s = s +"\n\n";
			
		}
			
		//p.append(s);
		return s;
	}
;

	 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		lambdazData other = (lambdazData) obj;
		if (!Arrays.deepEquals(lambdaZDetails, other.lambdaZDetails))
			return false;
		if (profileSelected != other.profileSelected)
			return false;
		return true;
	}
	LambdaZDetails[] lambdaZDetails;
	 int profileSelected;
	 
	
	public int getProfileSelected() {
		return profileSelected;
	}

	public void setProfileSelected(int profileSelected) {
		this.profileSelected = profileSelected;
	}

	public LambdaZDetails[] getLambdaZDetails() {
		return lambdaZDetails;
	}

	public LambdaZDetails getLambdaZDetailsOf(int index){
		return lambdaZDetails[index];
	}
	
	public void setLambdaZDetailsOf(int index, LambdaZDetails lzDet){
		lambdaZDetails[index] = lzDet;
	}
	public void setLambdaZDetails(int no_sub) {
		this.lambdaZDetails = new LambdaZDetails[no_sub] ;
		for(int i=0;i<this.lambdaZDetails.length;i++){
			this.lambdaZDetails[i] = new LambdaZDetails();
		}
		
	}
	
	@Override
	public Object clone(){
		lambdazData ld = null;
		try {
			ld = (lambdazData)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return ld;
		
		
	}

	public lambdazData(){

		// these arrays are being declared again in the respective classes with specific user intervention.
		//but to avoid null pointer exceptions when there is no specific user intervention I am defining with minimum memory
		lambdaZDetails = new LambdaZDetails[1];
		profileSelected = 0;
	}

}
	
