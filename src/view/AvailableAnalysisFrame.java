package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class AvailableAnalysisFrame implements EventCodes{

	
	public JInternalFrame availableAnalysisFrame;
	

	int availableStepsoriginalheight;
	int availableStrpsOriginalWidth;
	JButton ncaLable;
	JButton pkAnalysisLable;
	JButton pdAnalysisLable;
	JButton lmeLable;
	JButton nlmeLable;
	JButton ncaButton;
	JButton caButton;
	JButton admetButton;
	JButton ivIvButton;
	
	public void AvailableAnalysisFrameCration(){
		availableAnalysisFrame();
	}
	
	public AvailableAnalysisFrame(String dummy){
		
	}
	
	private void availableAnalysisFrame() {
		
		availableAnalysisFrame = new JInternalFrame("Analysis",false,false,false,false);
		availableAnalysisFrame.setTitle("Graphics");
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(availableAnalysisFrame);
		availableAnalysisFrame.setVisible(true);
		availableAnalysisFrame.moveToFront();
		availableAnalysisFrame.setBackground(Color.black);
		availableAnalysisFrame.setLocation(0, 0);
		availableAnalysisFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		availableAnalysisFrame.setSize(
				DDViewLayer.createMTInstance().mainTabbedFrame.getWidth(),
				DDViewLayer.createMTInstance().mainTabbedFrame.getHeight());
		
		availableAnalysisFrame.getContentPane().setBackground(Color.black);
		availableAnalysisFrame.getContentPane().setLayout(new GridBagLayout());
		
		Image ballImage = ImageLoader.getImage(Graphics.class, "ball.jpg");
		Icon ballIcon = new ImageIcon(ballImage);
		JLabel ballLable = new JLabel(ballIcon);
		GridBagConstraints balllableCon = new GridBagConstraints();
		balllableCon.gridx = 2;
		balllableCon.gridy = 2;

		balllableCon.fill = GridBagConstraints.BOTH;
		availableAnalysisFrame.getContentPane().add(ballLable,balllableCon);
		availableAnalysisFrame.getContentPane().setBackground(Color.white);
		
		admetButton  = new JButton("ADMET");
		admetButton.setFont(new Font("Arial", Font.BOLD, 32));
		admetButton.setBorder(null);
		admetButton.setEnabled(false);
		admetButton.setBackground(Color.white);
		GridBagConstraints middleLowerlableCon = new GridBagConstraints();
		middleLowerlableCon.gridx = 1;
		middleLowerlableCon.gridy = 2;
		middleLowerlableCon.anchor = GridBagConstraints.SOUTHWEST;
		middleLowerlableCon.fill = GridBagConstraints.BOTH;
		availableAnalysisFrame.getContentPane().add(admetButton,middleLowerlableCon);
		availableAnalysisFrame.getContentPane().setBackground(Color.white);
		
		ncaButton  = new JButton("NCA");
		ncaButton.addActionListener(DDViewLayer.createViewLayerInstance());
		ncaButton.setEnabled(false);
		ncaButton.setActionCommand(ENTERNCA);
		ncaButton.setFont(new Font("Arial", Font.BOLD, 32));
		ncaButton.setBorder(null);
		ncaButton.setBackground(Color.white);
		GridBagConstraints medium1lableCon = new GridBagConstraints();
		medium1lableCon.gridx = 2;
		medium1lableCon.gridy = 3;
		medium1lableCon.fill = GridBagConstraints.BOTH;
		availableAnalysisFrame.getContentPane().add(ncaButton,medium1lableCon);
		
		caButton  = new JButton("CA");
		caButton.setFont(new Font("Arial", Font.BOLD, 32));
		caButton.setBorder(null);
		caButton.setEnabled(false);
		caButton.setActionCommand(ENTERCA);
		caButton.addActionListener(DDViewLayer.createViewLayerInstance());
		caButton.setBackground(Color.white);
		GridBagConstraints mediumlableCon = new GridBagConstraints();
		mediumlableCon.gridx = 2;
		mediumlableCon.gridy = 1;
		mediumlableCon.fill = GridBagConstraints.BOTH;
		mediumlableCon.anchor = GridBagConstraints.SOUTHEAST;
		availableAnalysisFrame.getContentPane().add(caButton,mediumlableCon);
		
		ivIvButton  = new JButton("IVIVC");
		ivIvButton.setFont(new Font("Arial", Font.BOLD, 32));
		ivIvButton.setBorder(null);
		ivIvButton.setEnabled(false);
		ivIvButton.setBackground(Color.white);
		GridBagConstraints smallballlableCon = new GridBagConstraints();
		smallballlableCon.gridx = 3;
		smallballlableCon.gridy = 2;
		smallballlableCon.anchor = GridBagConstraints.NORTHWEST;
		smallballlableCon.fill = GridBagConstraints.BOTH;
		availableAnalysisFrame.getContentPane().add(ivIvButton,smallballlableCon);

		DDViewLayer.createMTInstance().mainTabbedDesktopPane.add(availableAnalysisFrame);
		availableAnalysisFrame.moveToFront();
		
	}
	
	void enableAvailableAnalysysInternalFrameFrame() {
		if(availableAnalysisFrame.isVisible() == false){
			availableAnalysisFrame.moveToFront();
			availableAnalysisFrame.setVisible(true);
			ncaButton.setEnabled(true);
			admetButton.setEnabled(true);
			ivIvButton.setEnabled(true);
			caButton.setEnabled(true);
		} else if(availableAnalysisFrame.isVisible() == true){
			availableAnalysisFrame.moveToBack();
			availableAnalysisFrame.setVisible(false);
			ncaButton.setEnabled(false);
			admetButton.setEnabled(false);
			ivIvButton.setEnabled(false);
			caButton.setEnabled(false);
		}
	}

	
}
