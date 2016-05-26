package view;

public class NcaSubAreasHandlers {

	public static NcaSubAreasHandlers NCA_SUB_HAND = null;
	public static NcaSubAreasHandlers createNcaSybAreaHandInst(){
		if(NCA_SUB_HAND == null){
			NCA_SUB_HAND = new NcaSubAreasHandlers();
		}
		return NCA_SUB_HAND;
	}
	
	
}
