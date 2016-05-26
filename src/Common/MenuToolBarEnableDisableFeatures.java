package Common;

import view.DDViewLayer;

public class MenuToolBarEnableDisableFeatures {

	public static MenuToolBarEnableDisableFeatures MENU_TOOL_ENABLE_DISABLE_INST;
	public static MenuToolBarEnableDisableFeatures createMenuToolEnabDisabInst(){
		if(MENU_TOOL_ENABLE_DISABLE_INST == null){
			MENU_TOOL_ENABLE_DISABLE_INST = new MenuToolBarEnableDisableFeatures();
		}
		
		return MENU_TOOL_ENABLE_DISABLE_INST;
	}
	
	
	public void disableEnableMenuToolBarFeatures() {
		
		DDViewLayer.createFeaturesPanelInstance().avaiAnalButton.setVisible(false);
		DDViewLayer.createmenuBarInstance().utilities.setEnabled(false);
		DDViewLayer.createFeaturesPanelInstance().plotsLable.setVisible(false);
		DDViewLayer.createmenuBarInstance().sort.setEnabled(false);
		DDViewLayer.createmenuBarInstance().tableTransItem.setEnabled(false);
		DDViewLayer.createFeaturesPanelInstance().tableLabel.setVisible(false);
		DDViewLayer.createmenuBarInstance().missingData.setEnabled(false);
		
	}
	
	
	public void eableMenuToolBarFeatures() {
		
		DDViewLayer.createFeaturesPanelInstance().avaiAnalButton.setVisible(true);
		DDViewLayer.createmenuBarInstance().utilities.setEnabled(true);
		DDViewLayer.createFeaturesPanelInstance().plotsLable.setVisible(true);
		DDViewLayer.createmenuBarInstance().sort.setEnabled(true);
		DDViewLayer.createmenuBarInstance().tableTransItem.setEnabled(true);
		DDViewLayer.createFeaturesPanelInstance().tableLabel.setVisible(true);
		DDViewLayer.createmenuBarInstance().missingData.setEnabled(true);
		
	}
	
	
}
