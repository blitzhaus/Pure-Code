package view;

public class DesStatsUI {

	public static DesStatsUI DES_STAT_UI = null;
	public static DesStatsUI createDesStatsUI(){
		if(DES_STAT_UI == null){
			DES_STAT_UI = new DesStatsUI("just to create object");
		}
		return DES_STAT_UI;
	}
	
	
	
	public DesStatsUI(String dummy){
		
	}

	DSTabs descStatsTabs;
	DesStatSetupAvailComp desStatAvailComp;
	DesStatSetupDespComp desStatSetupDespComp;
	DesStatOptComp desStatOptComp;
	DesStatResAvailComp desStatResAvailComp;
	DesStatResDispComp desStatResDispComp;

	void createUI(int selectedSheetIndex) {

		descStatsTabs = DSTabs.createDSTabInst();
		descStatsTabs.cteateTabbedPanes();
		desStatAvailComp = DesStatSetupAvailComp.createDesStatAvailComp();
		desStatAvailComp.createSetupAvailableComponentsinternalFrame(selectedSheetIndex);
		desStatSetupDespComp = DesStatSetupDespComp
				.createDesStatSetupDispInst();
		desStatSetupDespComp.createSetupDisplayComponentsInternalFrame();
		desStatOptComp = DesStatOptComp.createDesStatOptInst();
		desStatOptComp.createOptionsInternalFrame();
		desStatSetupDespComp.MappingInternalFrame.moveToFront();

		desStatResAvailComp = DesStatResAvailComp.createDesStatResAvailInst();
		desStatResAvailComp.createResultsTabAvailableOutputsInternalFrame();
		desStatResDispComp = DesStatResDispComp.createDesStatResDispInst();
		desStatResDispComp.createDisplayResultInternalFrame();

	}
}
