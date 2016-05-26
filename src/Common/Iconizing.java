package Common;

import org.apache.xmlbeans.impl.jam.mutable.MField;

import view.AvailableAnalysisFrame;
import view.ComponentsPanelCreation;
import view.DisplayContents;
import view.ImportedDataSheet;
import view.LogFrameCreation;
import view.MainTabbedIFCreation;
import view.ProjectExplorerCreation;
import view.DDViewLayer;


public class Iconizing {
	
	public static Iconizing ICONIZING_OBJ = null;
	
	//singleton design pattern implementation for iconizing object 
	public static Iconizing createIconizingInstance(){
		if(ICONIZING_OBJ == null){
			ICONIZING_OBJ = new Iconizing();
		}
		return ICONIZING_OBJ;
	}
	
	
	

	
	 @SuppressWarnings("deprecation")
	public void iconizeAvailableAnalysisInternalFrame() {

		DDViewLayer.createAAInstance().availableAnalysisFrame.setVisible(false);
		DDViewLayer.createMTInstance().mainTabbedFrame.resize(DDViewLayer
				.createMTInstance().mainTabbedFrame.getWidth()
				+ DDViewLayer.createAAInstance().availableAnalysisFrame
						.getWidth(),
				DDViewLayer.createMTInstance().mainTabbedFrame.getHeight());
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.resize(DDViewLayer.createMTInstance().mainTabbedDesktopPane
						.getWidth(),
						DDViewLayer.createMTInstance().mainTabbedDesktopPane
								.getHeight());
		ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame
				.resize(
						DDViewLayer.createMTInstance().mainTabbedFrame.getWidth(),
						DDViewLayer.createMTInstance().mainTabbedFrame
								.getHeight() / 2 - 20);
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame
				.resize(
						ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame
								.getWidth() / 4,
						ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame
								.getHeight() + 20);
		ImportedDataSheet.createImportedDataSheetInstance().columnPropertiesInternalFrame
				.setLocation(
						ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame
								.getWidth()
								+ ImportedDataSheet
										.createImportedDataSheetInstance().columnsAvailableInternalFrame
										.getX(),
						ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame
								.getY());
		ImportedDataSheet.createImportedDataSheetInstance().columnPropertiesInternalFrame
				.resize(
						ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame
								.getWidth()
								- ImportedDataSheet
										.createImportedDataSheetInstance().columnsAvailableInternalFrame
										.getWidth(),
						ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame
								.getHeight());

	}
	 
	public void deiconizeLogInternalFrame(){
			DDViewLayer.createLogFrameInstance().logFrame.setVisible(true);
		}
		
	 
	 
	 @SuppressWarnings("deprecation")
	public void deiconizeAvailableInternalFrame() {
	 	
		 DDViewLayer.createMTInstance().mainTabbedFrame.resize(DDViewLayer.createMTInstance().mainTabbedFrame.getWidth()-DDViewLayer.createAAInstance().availableAnalysisFrame.getWidth(), DDViewLayer.createMTInstance().mainTabbedFrame.getHeight());
	 	DDViewLayer.createAAInstance().availableAnalysisFrame.setVisible(true);
	 }

		 @SuppressWarnings("deprecation")
		public
		void iconizeLogInternalFrame() {

			 DDViewLayer.createLogFrameInstance().logFrame.setVisible(false);
			 DDViewLayer.createMTInstance().mainTabbedFrame.resize(DDViewLayer.createMTInstance().mainTabbedFrame.getWidth(), DDViewLayer.createMTInstance().mainTabbedFrame.getHeight()+DDViewLayer.createLogFrameInstance().logFrame.getHeight());//
			//projectExplorerFrame.setSize((int)(getWidth()/6.5),(int)(getHeight()/1));
			ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame.resize(DDViewLayer.createMTInstance().mainTabbedDesktopPane.getWidth(), DDViewLayer.createMTInstance().mainTabbedDesktopPane.getHeight());
			ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame.resize(DDViewLayer.createMTInstance().mainTabbedFrame.getWidth(),DDViewLayer.createMTInstance().mainTabbedFrame.getHeight()/2 -20);
			ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.setLocation(0, 
					ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame.getHeight());
			ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame
			.resize(ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame.getWidth()/4,
					ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame.getHeight()+20);
			ImportedDataSheet.createImportedDataSheetInstance().columnPropertiesInternalFrame
			.setLocation(ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.getWidth()
					+ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.getX(), 
					ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.getY());
			ImportedDataSheet.createImportedDataSheetInstance().columnPropertiesInternalFrame
			.resize(ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame.getWidth()
					-ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.getWidth(), 
					ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.getHeight());
			
		}
	 
	 
}
