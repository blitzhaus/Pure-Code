package view;

public class NcaTherapeuticDispGui {

	
	public void NcaTherapeuticDispGuiCreation(){
		createTherapeuticResponseinternalFrame();
	}
	
	public NcaTherapeuticDispGui(String dummy){
		
	}
	
	public static NcaTherapeuticDispGui NCA_THERA_GUI = null;
	public static NcaTherapeuticDispGui createNcaTherapeuticGuiInstance(){
		if(NCA_THERA_GUI == null){
			NCA_THERA_GUI = new NcaTherapeuticDispGui("just object creation");
		}
		return NCA_THERA_GUI;
	}
	
	private void createTherapeuticResponseinternalFrame() {

	}
	
}
