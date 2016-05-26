package view;

public class TMResultDispComp {

	public static TMResultDispComp TM_RES_DISP_COMP = null;
	public static TMResultDispComp createResDispInst() {
		
		if(TM_RES_DISP_COMP == null){
			TM_RES_DISP_COMP = new TMResultDispComp("just opbject creation");
			
		}
		return TM_RES_DISP_COMP;
	}

	public TMResultDispComp(String dummy){
		
	}
	public void resultsDispCompUI() {
		// TODO Auto-generated method stub
		
	}

}
