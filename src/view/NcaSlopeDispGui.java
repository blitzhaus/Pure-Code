package view;

public class NcaSlopeDispGui {
	
	public NcaSlopeDispGui(String dummy){
		
	}

	public static NcaSlopeDispGui NCA_SLOPE_DISP = null;
	public static NcaSlopeDispGui createNcaSlopeDispInstance(){
		if(NCA_SLOPE_DISP == null){
			NCA_SLOPE_DISP = new NcaSlopeDispGui("just object creation");
		}
		return NCA_SLOPE_DISP;
	}
	
	
	public void NcaSlopeDispGuiCreation(){
		createSlopesInternalFrame();
	}
	
	
	private void createSlopesInternalFrame() {

	}
}
