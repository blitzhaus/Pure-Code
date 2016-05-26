package view;

import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;

import view.BioEquivalence.BEGuiCreation;

import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class SubOptionsOfNCA implements EventCodes{

	public static SubOptionsOfNCA SUBOPTNCA = null;
	public static SubOptionsOfNCA createSubOptNcaInst(){
		if(SUBOPTNCA == null){
			SUBOPTNCA = new SubOptionsOfNCA();
		}
		return SUBOPTNCA;
	}
	
	JDialog ncaOptionsDialog;
	public SubOptionsOfNCA(){
		ncaOptionsDialog = new JDialog(DDViewLayer.createViewLayerInstance(), "NCA Options");
		ncaOptionsDialog.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				SUBOPTNCA = null;
				
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				//SubOptionsOfNCA.createSubOptNcaInst().SUBOPTNCA = null;
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		
		});
		ncaOptionsDialog.setLocationRelativeTo(DDViewLayer.createViewLayerInstance());
		ncaOptionsDialog.setVisible(true);
		ncaOptionsDialog.setResizable(false);

		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Rectangle gebounds = ge.getMaximumWindowBounds();
		ncaOptionsDialog.setSize(gebounds.width/2, gebounds.height/2);
		ncaOptionsDialog.setContentPane(new JDesktopPane());
		createInternalFrame();
	}
	JInternalFrame ncaOptionsIF;
	private void createInternalFrame() {
		ncaOptionsIF = new JInternalFrame("", false,false,false,false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(ncaOptionsIF);
		ncaOptionsIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		ncaOptionsIF.setLocation(0,0);
		ncaOptionsIF.setSize(ncaOptionsDialog.getWidth(), ncaOptionsDialog.getHeight());
		ncaOptionsIF.setVisible(true);
		ncaOptionsIF.setLayout(new GridBagLayout());
		ncaOptionsDialog.getContentPane().add(ncaOptionsIF);
		createRadioButtons();
		createButton();
		
	}
	JButton continueButton;
	private void createButton() {
		// TODO Auto-generated method stub
		continueButton = new JButton("Continue");
	
		continueButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(SubOptionsOfNCA.createSubOptNcaInst().nca.isSelected() == true){
					new NCAButtonActionHandler();
				} else if(SubOptionsOfNCA.createSubOptNcaInst().nps.isSelected() == true){
					new NPSButtonHandler();
				} else if(SubOptionsOfNCA.createSubOptNcaInst().sca.isSelected() == true){
					new SCAbuttonHandler();
				} else if(SubOptionsOfNCA.createSubOptNcaInst().be.isSelected() == true){
					new BEGuiCreation();
				}
				ncaOptionsDialog.setVisible(false);
				ncaOptionsDialog.removeAll();
				ncaOptionsDialog = null;
				SUBOPTNCA = null;
				
			}
		});
		
		GridBagConstraints contButCon = new GridBagConstraints();
		contButCon.gridx = 1;
		contButCon.gridy = 4;
		contButCon.weighty = 0.3;
		contButCon.weightx = 0.5;
	//	contButCon.anchor = GridBagConstraints.LINE_START;
		ncaOptionsIF.getContentPane().add(continueButton, contButCon);
		
		
	}
	
	JRadioButton nca;
	JRadioButton sca;
	JRadioButton nps;
	JRadioButton be;
	private void createRadioButtons() {
		ButtonGroup bg = new ButtonGroup();
		
		nca = new JRadioButton("Non Compartmental Analysis");
		bg.add(nca);
		nca.setSelected(true);
		//nca.addItemListener(ViewLayer.createViewLayerInstance());
		GridBagConstraints ncaCon = new GridBagConstraints();
		ncaCon.gridx = 0;
		ncaCon.gridy = 0;
		ncaCon.weightx = 0.5;
		ncaCon.weighty = 0.5;
		ncaCon.anchor = GridBagConstraints.LINE_START;
		ncaOptionsIF.getContentPane().add(nca, ncaCon);
		
		
		sca = new JRadioButton("Semi Compartmental Analysis");
		bg.add(sca);
		//sca.addItemListener(ViewLayer.createViewLayerInstance());
		GridBagConstraints scaCon = new GridBagConstraints();
		scaCon.gridx = 0;
		scaCon.gridy = 1;
		scaCon.weightx = 0.5;
		scaCon.weighty = 0.5;
		scaCon.anchor = GridBagConstraints.LINE_START;
		ncaOptionsIF.getContentPane().add(sca, scaCon);
		
		nps = new JRadioButton("Non Parametric Superposition");
		bg.add(nps);
		//nps.addItemListener(ViewLayer.createViewLayerInstance());
		GridBagConstraints npsCon = new GridBagConstraints();
		npsCon.gridx = 0;
		npsCon.gridy = 2;
		npsCon.weightx = 0.5;
		npsCon.weighty = 0.5;
		npsCon.anchor = GridBagConstraints.LINE_START;
		ncaOptionsIF.getContentPane().add(nps, npsCon);
		
		
		be = new JRadioButton("Bio Equivalence");
		bg.add(be);
		//be.addItemListener(ViewLayer.createViewLayerInstance());
		GridBagConstraints beCon = new GridBagConstraints();
		beCon.gridx = 0;
		beCon.gridy = 3;
		beCon.weighty = 0.5;
		beCon.anchor = GridBagConstraints.LINE_START;
		ncaOptionsIF.getContentPane().add(be, beCon);
		
		
		
	}
}
