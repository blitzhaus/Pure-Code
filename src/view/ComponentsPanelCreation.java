package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComponentsPanelCreation {

	 JPanel componentsPanel;
	private JLabel operationsLable;
	


	
	public void ComponentsPanelCreation(){
		componentsPanelCreation();
	}
	
	public ComponentsPanelCreation(String dummy){
		
	}
	
	private void componentsPanelCreation() {
		
		LogoPanelCreation lpobj = DDViewLayer.createLogoPanelInstance();
		DDViewLayer mlobj = DDViewLayer.createViewLayerInstance();

		//buttons panel for displaying the other components
		    componentsPanel = new JPanel();
		    componentsPanel.setLocation(0, lpobj.logoPanel.getHeight());
		    componentsPanel.setSize(50, (int) mlobj.screenSize.getHeight());
		    componentsPanel.setVisible(true);
		    operationsLable = new JLabel("op");
		    mlobj.desktop.add(componentsPanel);
		  
	}
}
