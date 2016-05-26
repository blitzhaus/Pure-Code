package view;

import jxl.Sheet;

public class DataPreparationForDS {
	
	public static DataPreparationForDS DAT_PREP_DS = null;
	public static DataPreparationForDS createDatPrepForDS(){
		if(DAT_PREP_DS == null){
			DAT_PREP_DS = new DataPreparationForDS();
		}
		return DAT_PREP_DS;
	}
	String[][] Data;
	public String[][] prepareData(){
		Data = multipleLevelSorting.createMultipleSortInst().dataSorted;
		return Data;
	}

}
