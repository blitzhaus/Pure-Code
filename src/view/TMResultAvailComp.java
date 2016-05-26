package view;

public class TMResultAvailComp {

	public static TMResultAvailComp RES_AVAIL_COMP = null;
	public static TMResultAvailComp createResAvailCompInst() {
		if(RES_AVAIL_COMP == null){
			RES_AVAIL_COMP = new TMResultAvailComp("just object creation");
		}
		return RES_AVAIL_COMP;
	}

	public TMResultAvailComp(String dummy){
		
	}
	void resultsAvailCompUI(){
		
	}
}
