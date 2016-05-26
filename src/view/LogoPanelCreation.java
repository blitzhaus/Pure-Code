package view;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Common.ImageLoader;


public class LogoPanelCreation{

	
	 private JLabel logoLable;
	protected JPanel logoPanel;

	
	
	 public void LogoPanelCreationCreation(){
		 logoPanelCreation();
	 }
	 
	 public LogoPanelCreation(String dummy){
		 
	 }
	 
	private void logoPanelCreation() {
		DDViewLayer mlpobj = DDViewLayer.createViewLayerInstance();
		ImageLoader imgLoaderObj = ImageLoader.createImageLoaderInstance();
		// TODO Auto-generated method stub
		logoPanel = new JPanel();
		logoPanel.setLocation(0, 0);
		logoPanel.setSize(50, 45);
		
		Image TATAlogoImage = imgLoaderObj.getImage(DDViewLayer.class, "tataLogo.jpeg");
		Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
		logoLable = new JLabel(TATAlogoIcon);
		logoLable.addMouseListener(DDViewLayer.createViewLayerInstance());
		logoLable.setToolTipText("TATA Consultancy Services");
		logoPanel.add(logoLable);
		mlpobj.desktop.add(logoPanel);
	}
	
}
