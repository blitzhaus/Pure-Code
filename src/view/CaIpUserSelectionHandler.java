package view;

public class CaIpUserSelectionHandler {

	public CaIpUserSelectionHandler(){
		
	}
	
	public void setupTreeSelectionHandler(){
		if(CaIpUserSelectionGui.createCAIpUserSelecInst().availableComponentsTree
		.getSelectionPath() != null){
			String[] pathSplits = CaIpUserSelectionGui.createCAIpUserSelecInst().availableComponentsTree
			.getSelectionPath().toString().split(",");
			
			if(pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Matrix]")){
				CaIpUserSelectionGui.createCAIpUserSelecInst().matrixIF.moveToFront();
			} else if(pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Administration Route]")){
				CaIpUserSelectionGui.createCAIpUserSelecInst().routeIF.moveToFront();
			} else if(pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Comparment]")){
				CaIpUserSelectionGui.createCAIpUserSelecInst().compartmentsIF.moveToFront();
			}
		}
		
		
	}
}
