package view;

import javax.swing.JInternalFrame;

import Common.JinternalFrameFunctions;

public class BQLResDispComp {

	JInternalFrame transformedSheetIF;
	public static BQLResDispComp BQL_RES_DISP = null;
	public static BQLResDispComp createBqlResDispInst(){
		if(BQL_RES_DISP == null){
			BQL_RES_DISP = new BQLResDispComp("just object creation");
		}
		return BQL_RES_DISP;
	}
	
	public BQLResDispComp(String dummy){
		
	}
	
	void bqlResDispUICreation(){
		transformedSheetIF = new JInternalFrame("",false,false,false,false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(transformedSheetIF);
		transformedSheetIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		transformedSheetIF
				.setLocation(
						BQLResAvailComp.createBQLResAvailInst().resultsAvailableComponentIF
								.getX()
								+ BQLResAvailComp.createBQLResAvailInst().resultsAvailableComponentIF
										.getWidth(),
						BQLResAvailComp.createBQLResAvailInst().resultsAvailableComponentIF
								.getY());
		transformedSheetIF.setSize(BQLMainScreen.createBQLMainScrInst().bqlMainScreenIF.getWidth()-
				BQLResAvailComp.createBQLResAvailInst().resultsAvailableComponentIF.getWidth(), 
				BQLResAvailComp.createBQLResAvailInst().resultsAvailableComponentIF.getHeight());
		
		BQLTabbedPane.createBqlTabInst().resultsTabDesktopPane.add(transformedSheetIF);
		transformedSheetIF.moveToFront();
		transformedSheetIF.setVisible(true);
		
	}
		
}
