package view;


import jxl.Sheet;

public class DataPreparationForNPS {

	String[][] Data;
	public static DataPreparationForNPS DAT_PREP_NPS = null;
	public static DataPreparationForNPS createDatPrepForNPS(){
		if(DAT_PREP_NPS == null){
			DAT_PREP_NPS = new DataPreparationForNPS();
		}
		return DAT_PREP_NPS;
	}
	
	public String[][] prepareData(){
		Data = multipleLevelSorting.createMultipleSortInst().dataSorted;
		return Data;
	}

}
