package view;

import view.ApplicationInfo;
import view.DisplayContents;
import jxl.Cell;
import jxl.Sheet;

public class DataPreparationForPlot {
	public static String[][] Dat = null;
	
	public static String[][] prepareData(String fileExtension, Sheet importedWorkSheet, String DataInputFile){
				ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
				int numberOfRows = 0;
				int numberOfColumns = 0 ;
				if((DDViewLayer.createViewLayerInstance().isNCAExecution == false)&&(DDViewLayer.createViewLayerInstance().isPlotMavenExecution == true)){
					Dat = multipleLevelSorting.createMultipleSortInst().dataSorted;
				}else{
						
						Dat = multipleLevelSorting.createMultipleSortInst().dataSorted;
						for (int i = 0; i < Dat.length; i++) {
							for (int j = 0; j < Dat[0].length; j++) {
								System.out.print("\t"+Dat[i][j]);
							}
							System.out.println();
						}
						System.out.println();
						System.out.println();
					}
								
				System.out.println("The data taken into the DAT of nca is ");
				for(int i=0;i<numberOfRows;i++){
					for(int j=0;j<numberOfColumns;j++){
						System.out.print(Dat[i][j]+" ");
					}
					System.out.println();
				}
			
		return Dat;
	}

}
