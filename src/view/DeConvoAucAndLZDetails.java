package view;

import java.io.Serializable;

public class DeConvoAucAndLZDetails implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7628218756875489109L;
	private DeConvoAucLambdaZInfo[] deConvoAucLZInfoInst;
	
	int profileSelected;

	public DeConvoAucLambdaZInfo[] getDeConvoAucLZInfoInst() {
		return deConvoAucLZInfoInst;
	}

	public void setDeConvoAucLZInfoInst(DeConvoAucLambdaZInfo[] deConvoAucLZInfoInst) {
		this.deConvoAucLZInfoInst = deConvoAucLZInfoInst;
	}

	public int getProfileSelected() {
		return profileSelected;
	}

	public void setProfileSelected(int profileSelected) {
		this.profileSelected = profileSelected;
	}

	public DeConvoAucAndLZDetails() {
		
		deConvoAucLZInfoInst = new DeConvoAucLambdaZInfo[1];
		profileSelected = 0;
	}

	public void setAucLamZDetails(int no_sub) {
		this.deConvoAucLZInfoInst = new DeConvoAucLambdaZInfo[no_sub] ;
		for(int i=0;i<this.deConvoAucLZInfoInst.length;i++){
			this.deConvoAucLZInfoInst[i] = new DeConvoAucLambdaZInfo();
		}
		
	}
	
}
