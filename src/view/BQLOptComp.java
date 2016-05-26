package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Common.JinternalFrameFunctions;

public class BQLOptComp {
	
	JRadioButton lloqRB;
	JRadioButton missingRB;
	JRadioButton zeroRB;

	public static BQLOptComp BQL_OPT = null;
	public static BQLOptComp createBQLOptINst(){
		if(BQL_OPT == null){
			BQL_OPT = new BQLOptComp("just object creation");
		}
		
		return BQL_OPT;
	}
	
	public BQLOptComp(String dummy){
		
	}
	JInternalFrame bqlOptionsIF;
	void bqlOptCreateUI(){
		bqlOptionsIF = new JInternalFrame("options", false,false,false,false);
		
		bqlOptionsIF
				.setLocation(
						BQLSetupAvailComp.createBqlSetAvailInst().setupAvailableComponentsInternalFrame
								.getX(),
						BQLSetupAvailComp.createBqlSetAvailInst().setupAvailableComponentsInternalFrame
								.getY()
								+ BQLSetupAvailComp.createBqlSetAvailInst().setupAvailableComponentsInternalFrame
										.getHeight()+20);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(bqlOptionsIF);
		bqlOptionsIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		bqlOptionsIF.setVisible(true);
		bqlOptionsIF.setSize(BQLMainScreen.createBQLMainScrInst().bqlMainScreenIF.getWidth()
				
				, BQLSetupAvailComp.createBqlSetAvailInst().setupAvailableComponentsInternalFrame.getHeight());
		DDViewLayer.createMTInstance().mainTabbedDesktopPane.add(bqlOptionsIF);
		bqlOptionsIF.moveToFront();
		bqlOptionsIF.setContentPane(new JDesktopPane());
		createOptionsForBQLRules();
	}

	private void createOptionsForBQLRules() {
		
		JInternalFrame rulesIF = new JInternalFrame("",false,false,false,false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(rulesIF);
		rulesIF.setLocation(0, 0);
		rulesIF.setSize(bqlOptionsIF.getWidth()/4, bqlOptionsIF.getHeight());
		rulesIF.setVisible(true);
		rulesIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		bqlOptionsIF.getContentPane().add(rulesIF);
		GridBagConstraints bqlRule1OptionsCon = new GridBagConstraints();
		GridBagConstraints bqlRule2OptionsCon = new GridBagConstraints();
		GridBagConstraints bqlRule3OptionsCon = new GridBagConstraints();
		
		JPanel rulespaJPanel = new JPanel();
		rulespaJPanel.setBorder(BorderFactory.createTitledBorder("Rules"));
		rulespaJPanel.setLayout(new GridBagLayout());
		lloqRB = new JRadioButton("LLOQ/2 ");
		lloqRB.setSelected(true);
		lloqRB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int columnSelected = BQLSetupDispComp.createBQLSetupDispInst().rulesTable.getSelectedColumn();
				int rowSelected = BQLSetupDispComp.createBQLSetupDispInst().rulesTable.getSelectedRow();
				if((rowSelected!=-1)&&
						(columnSelected != -1)){
					BQLSetupDispComp.createBQLSetupDispInst().rulesTable.setValueAt("LLOQ/2", rowSelected, columnSelected);
				} 
				
			}
		});
		
		bqlRule1OptionsCon.gridx = 0;
		bqlRule1OptionsCon.gridy = 0;
		rulespaJPanel.add(lloqRB,bqlRule1OptionsCon);
		missingRB = new JRadioButton("Missing ");
		missingRB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				int columnSelected = BQLSetupDispComp.createBQLSetupDispInst().rulesTable.getSelectedColumn();
				int rowSelected = BQLSetupDispComp.createBQLSetupDispInst().rulesTable.getSelectedRow();
				if((rowSelected!=-1)&&
						(columnSelected != -1)){
					BQLSetupDispComp.createBQLSetupDispInst().rulesTable.setValueAt("Missing", rowSelected, columnSelected);
				} 
				
			
				
			}
		});
		
		bqlRule2OptionsCon.gridx = 0;
		bqlRule2OptionsCon.gridy = 1;
		rulespaJPanel.add(missingRB, bqlRule2OptionsCon);
		
		
		zeroRB = new JRadioButton("Zero");
		zeroRB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				int columnSelected = BQLSetupDispComp.createBQLSetupDispInst().rulesTable.getSelectedColumn();
				int rowSelected = BQLSetupDispComp.createBQLSetupDispInst().rulesTable.getSelectedRow();
				if((rowSelected!=-1)&&
						(columnSelected != -1)){
					BQLSetupDispComp.createBQLSetupDispInst().rulesTable.setValueAt("0", rowSelected, columnSelected);
				} 
				
			
				
			}
		});
		bqlRule3OptionsCon.gridx = 0;
		bqlRule3OptionsCon.gridy = 2;
		rulespaJPanel.add(zeroRB,bqlRule3OptionsCon);
		
		rulesIF.getContentPane().add(rulespaJPanel);
		
		ButtonGroup rulesButtonGroup = new ButtonGroup();
		
		rulesButtonGroup.add(lloqRB);
		rulesButtonGroup.add(missingRB);
		rulesButtonGroup.add(zeroRB);
		
		
		
	
		
	}
}
