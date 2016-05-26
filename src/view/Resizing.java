package view;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;

import view.DisplayContents;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class Resizing {
	
	
	static Dimension impDSSize;
	static Point DDFrameLoc;  
	static Dimension DDFrameSize;
	static Point CAFrameLoc;
	static Dimension CAFrameSize;
	static  Point CPFrameloc;
	static Dimension CPFrameSize;

	public static void setOriginalPropertiesOfImportedDataSheet() {
		
		ImportedDataSheet.importedDataSheetFrame.setSize(impDSSize);
		ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame.setLocation(DDFrameLoc);
		ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame.setSize(DDFrameSize);
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.setLocation(CAFrameLoc);
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.setSize(CAFrameSize);
		ImportedDataSheet.createImportedDataSheetInstance().columnPropertiesInternalFrame.setSize(CPFrameSize);
		ImportedDataSheet.createImportedDataSheetInstance().columnPropertiesInternalFrame.setLocation(CPFrameloc);
	}

	public static void setTemporatyPropImportedDataSheet() throws RowsExceededException, WriteException, BiffException, IOException {
		ImportedDataSheet.importedDataSheetFrame.moveToFront();
		ImportedDataSheet.importedDataSheetFrame.setSize(PlotMavenCreateUI.createPlotMavenUIInst().PlotMavenMainInternalFrame.getWidth(),
				PlotMavenCreateUI.createPlotMavenUIInst().PlotMavenMainInternalFrame.getHeight()
				+PMOptions.createPMOptionsInst().plotMavenOptionsinernalFrame.getHeight());
		ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame.setLocation(0, 0);
		ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame.setSize(
				ImportedDataSheet.importedDataSheetFrame.getWidth(),
				ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame.getHeight());
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.setLocation(0, 
				ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame.getHeight());
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.setSize(
				ImportedDataSheet.importedDataSheetFrame.getWidth()/4, 
				ImportedDataSheet.importedDataSheetFrame.getHeight()/2);
		ImportedDataSheet.createImportedDataSheetInstance().columnPropertiesInternalFrame.setLocation(
				ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.getWidth(),
				ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.getX());
		ImportedDataSheet.createImportedDataSheetInstance().columnPropertiesInternalFrame.setSize(3*
				ImportedDataSheet.importedDataSheetFrame.getWidth()/4, 
				ImportedDataSheet.importedDataSheetFrame.getHeight()/2);
		DDViewLayer.createFeaturesPanelInstance().PreviewLable.setVisible(true);
		
	}

	public static void storeOriginalPropertiesOfImportedDataSheet(){
		
		impDSSize = ImportedDataSheet.importedDataSheetFrame.getSize();
		DDFrameLoc = ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame.getLocation();  
		 DDFrameSize = ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame.getSize();
		CAFrameLoc = ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.getLocation();
	 CAFrameSize = ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.getSize();
	 CPFrameSize = ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.getSize();
		 CPFrameloc  = ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame.getLocation();
		
		
		  
	}
}
