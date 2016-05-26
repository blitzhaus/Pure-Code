package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import Common.JinternalFrameFunctions;

public class TMOptions {

	public static TMOptions TM_OPT_UI = null;
	public static TMOptions createTMptionsInst() {
		if(TM_OPT_UI == null){
			TM_OPT_UI = new TMOptions("just object creation");
		}
		return TM_OPT_UI;
	}
	
	JInternalFrame tmOptionsIF;
	public TMOptions(String dummy){
		
	}
	
	private boolean restoreMode = false;
	
	public boolean isRestoreMode() {
		return restoreMode;
	}

	public void setRestoreMode(boolean restoreMode) {
		this.restoreMode = restoreMode;
	}

	void createOptionsUI() {
		
		tmOptionsIF = new JInternalFrame("options", false,false,false,false);
		tmOptionsIF.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(tmOptionsIF);
		tmOptionsIF.setLocation(0,TMSetupAvailComp.createTmSetupAvailCompInst().tmSetupAvailCompIF.getY()+
				TMSetupAvailComp.createTmSetupAvailCompInst().tmSetupAvailCompIF.getHeight());
		tmOptionsIF.setSize(TableMavenCreateUI
				.createTableMavenUIInst().tableMavenMainInternalFrame.getWidth(),TableMavenCreateUI
				.createTableMavenUIInst().tableMavenMainInternalFrame.getHeight());
		createOptionTabs();
		DDViewLayer.createMTInstance().mainTabbedFrame.getContentPane().add(tmOptionsIF);
		tmOptionsIF.moveToFront();
		tmOptionsIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		
		
				
		
	}

	private void createOptionTabs() {
		JTabbedPane optionsTabbedPane = new JTabbedPane();
		
		FontStylePanel fontStylePanel = TableMavenCreateUI.createFontStylePanel();
		TMStatsPanel tmStatsPanel = TableMavenCreateUI.createTMStatsPanel();
		tmStatsPanel.createTMStatsPanel();
		
		TMOptionsPanel tmOptionsPanel = TableMavenCreateUI.createTMOptionsPanel();
		
		if (DDViewLayer.createViewLayerInstance().isTMRestore == true)
		{
			tmStatsPanel.resetStatCompsWithAppInfo();
		}
		
		//optionsTabbedPane.addTab("Options", tmOptionsPanel);
		optionsTabbedPane.addTab("Statistics", tmStatsPanel);
		//optionsTabbedPane.addTab("Style", fontStylePanel);
		
		JScrollPane spOptionsTabbedPane = new JScrollPane(optionsTabbedPane,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tmOptionsIF.getContentPane().add(spOptionsTabbedPane);
		
	}

}
