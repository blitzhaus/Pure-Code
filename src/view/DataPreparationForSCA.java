package view;


import jxl.Sheet;

public class DataPreparationForSCA {
	
	public static DataPreparationForSCA DAT_PREP_SCA = null;
	public static DataPreparationForSCA createDatPrepForSCA(){
		if(DAT_PREP_SCA == null){
			DAT_PREP_SCA = new DataPreparationForSCA();
		}
		return DAT_PREP_SCA;
	}
	String[][] Data;
	
	String[][] prepareData(){
		Data = multipleLevelSorting.createMultipleSortInst().dataSorted;
		return Data;
		
	}

}
