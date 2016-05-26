package view;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import Common.JinternalFrameFunctions;
import Common.RightClick;


public class MainTabbedIFCreation {

	public void MainTabbedIFCreation(){
		mainTabbedFrame();
	}
	
	public MainTabbedIFCreation(String dummy){
		
	}
	
	public JInternalFrame mainTabbedFrame;

	 int mainTabbedFrameOriginalWidth;
	 int mainTabbedFrameoriginalHeight;
	 public JDesktopPane mainTabbedDesktopPane;
	 JInternalFrame ppMainDisplayinternalFrame;
	 JDesktopPane ppmainDisplayDesktopPane;

	 
	 
	 private void mainTabbedFrame() {
		 ProjectExplorerCreation peobj = DDViewLayer.createPEInstance();
		 DDViewLayer mlobj  = DDViewLayer.createViewLayerInstance();
			
			mainTabbedFrame = new JInternalFrame("", false,false,false,false);
				mainTabbedFrame.addPropertyChangeListener(DDViewLayer.createViewLayerInstance());
				mainTabbedFrame.setLocation(peobj.projectExplorerFrame.getX()+peobj.projectExplorerFrame.getWidth(),peobj. projectExplorerFrame.getY());
				mainTabbedFrame.setBackground(Color.white);
				int width = mainTabbedFrameOriginalWidth =  (int)(mlobj.screenSize.getWidth()/1.25);
				int height = mainTabbedFrameoriginalHeight = (int)(mlobj.screenSize.getHeight()/1.15);
				mainTabbedFrame.setSize(width, height);
				mainTabbedDesktopPane = new JDesktopPane();
				mainTabbedFrame.setBorder(mlobj.b);
				mainTabbedFrame.setContentPane(mainTabbedDesktopPane);
				mainTabbedFrame.setVisible(true);
				displayinternalFrame();
				mlobj.desktop.add(mainTabbedFrame);
		}
	 
	 
	 
	 private void displayinternalFrame() {
		
		 DDViewLayer mlobj  =  DDViewLayer.createViewLayerInstance();
			
			//Display internal frame
				ppMainDisplayinternalFrame  = new JInternalFrame("Display", true,false,true,false);
				ppMainDisplayinternalFrame.addMouseListener(new RightClick());
				JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(ppMainDisplayinternalFrame);
				ppMainDisplayinternalFrame.setLocation(0,0);
				ppMainDisplayinternalFrame.setBackground(Color.white);
			    int width = (int)(mlobj.screenSize.getWidth()/1.6);
			    int height = (int)(mlobj.screenSize.getHeight()/1.6);
			    ppMainDisplayinternalFrame.setSize(width, height);
			    ppmainDisplayDesktopPane =  new JDesktopPane();
			    ppMainDisplayinternalFrame.setBorder(mlobj.b);
			    ppMainDisplayinternalFrame.getContentPane().add(ppmainDisplayDesktopPane);
			    ppMainDisplayinternalFrame.setVisible(true);
			
		}

	
}
