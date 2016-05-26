package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;

import org.apache.xmlbeans.impl.jam.mutable.MField;

import Common.Iconizing;

public class StepsPanelCreation{
	
	
	private final class MinimizeAAFrameButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			AvailableAnalysisFrame aaobj = DDViewLayer.createAAInstance();
				if(aaobj.availableAnalysisFrame.isVisible()){
					iconizingObj.iconizeAvailableAnalysisInternalFrame();
				}else{
					iconizingObj.deiconizeAvailableInternalFrame();
				}
				
				
			}
	}


	JPanel stepsPanel;

	JButton minimizeAvailableAnalysisInternalFrameButton;
	Iconizing iconizingObj = Iconizing.createIconizingInstance();
	JButton logFrameButton;
	JDesktopPane stepsframeDesktopPane;
	
	public void StepsPanelCreation(){
	stepsPanelCreation();
	}

	public StepsPanelCreation(String dummy){
		
	}
	
	private void stepsPanelCreation() {
		
		AvailableAnalysisFrame aaobj = DDViewLayer.createAAInstance();
		MenuBarCreation mbobj = DDViewLayer.createmenuBarInstance();
		FeaturesPnaleCreation fpobj = DDViewLayer.createFeaturesPanelInstance();
		MainTabbedIFCreation mtobj = DDViewLayer.createMTInstance();
		ComponentsPanelCreation cponj = DDViewLayer.createComponentsPaneInstance();
		DDViewLayer mlobj = DDViewLayer.createViewLayerInstance();

		stepsPanel = new JPanel();
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
		stepsPanel.setLayout(flowLayout);
		stepsPanel.setLocation(aaobj.availableAnalysisFrame.getX()+aaobj.availableAnalysisFrame.getWidth(), aaobj.availableAnalysisFrame.getY());
		stepsPanel.setSize((int)cponj.componentsPanel.getWidth(), (int)mlobj.screenSize.getHeight());
		stepsPanel.setVisible(true);
		
		minimizeAvailableAnalysisInternalFrameButton = new JButton("---");
		minimizeAvailableAnalysisInternalFrameButton.addActionListener(new MinimizeAAFrameButtonHandler());
		minimizeAvailableAnalysisInternalFrameButton.setVisible(true);
		minimizeAvailableAnalysisInternalFrameButton.setPreferredSize(new Dimension(40,20));
		stepsPanel.add(minimizeAvailableAnalysisInternalFrameButton);
		mlobj.desktop.add(stepsPanel);
		logFrameButton = new JButton("Log");
		logFrameButton.addMouseListener(DDViewLayer.createViewLayerInstance());
		logFrameButton.setPreferredSize(new Dimension(20,20));
		logFrameButton.setVisible(true);
		stepsPanel.add(logFrameButton);
		
		stepsframeDesktopPane = new JDesktopPane();
	    aaobj.availableAnalysisFrame.getContentPane().add(stepsframeDesktopPane);
		
	    mtobj.mainTabbedFrame.moveToFront();
	}
	
}
