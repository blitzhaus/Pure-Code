package view;

public class DataPreparationForTT {

	String[][] Data;
	public static DataPreparationForTT DAT_PREP_NPS = null;

	public static DataPreparationForTT createDatPrepForTT() {
		if (DAT_PREP_NPS == null) {
			DAT_PREP_NPS = new DataPreparationForTT();
		}
		return DAT_PREP_NPS;
	}

	public String[][] prepareData() {
		Data = multipleLevelSorting.createMultipleSortInst().dataSorted;
		return Data;
	}

}
