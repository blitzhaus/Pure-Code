package Model;

import java.io.IOException;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;


import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class DeModelsParamValStorer {
	String[] paramDefaultUnit;
	String[] paramPreferredUnit;
	ProcessingInputsInfo procInputInst;
	PkModelsParamValStorer printOfParamValInst;
	int noOfParam;
	
	
	public  void parameterValuePrinting(double[] parameter,double[] initial, double[][] AInv,double SD ) throws RowsExceededException, WriteException, BiffException, IOException
	{
		
		printOfParamValInst= PkModelsParamValStorer.createParamPrintInstance();
		printOfParamValInst.headerForEstimatedParameter();
		procInputInst = PkPdInfo.createPKPDInstance().copyProcessingInput();
	
		ApplicationInfo appInfo = PkParamEstimator.createPkParamEstimateInstance().appInfoinst;
		int modelNumber = procInputInst.getModelInputTab1Obj().getModelNumberForCA();
		
		paramDefaultUnit = procInputInst.getParameterUnitsDataObj()
				.getDefaultUnitArray();
		paramPreferredUnit = procInputInst.getParameterUnitsDataObj()
				.getPreferredUnitsArray();
		
		noOfParam = procInputInst.getProfileAndParamInfoObj().getNumberOfParameter();

		printOfParamValInst.noOfParam = noOfParam;
		printOfParamValInst.paramName = procInputInst.getProfileAndParamInfoObj().getParameterNameForCA();
		
		if(modelNumber == 1){
			
			printingOfLibraryModel1( parameter, initial, AInv,SD );
			
		}
		
		if(modelNumber == 2){
			printingOfLibraryModel2(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 3){
			printingOfLibraryModel3(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 4){
			printingOfLibraryModel4(parameter, initial, AInv, SD);
		}
		
		
		if(modelNumber == 5){
			printingOfLibraryModel5(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 6){
			printingOfLibraryModel6(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 7){
			printingOfLibraryModel7(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 8){
			
			printingOfLibraryModel8(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 9){
			printingOfLibraryModel9(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 10){
			printingOfLibraryModel10(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 11){
			printingOfLibraryModel11(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 12){
			printingOfLibraryModel12(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 13){
			printingOfLibraryModel13(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 14){
			printingOfLibraryModel14(parameter, initial, AInv, SD);
		}
		if(modelNumber == 15){
			printingOfLibraryModel15(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 16){
			printingOfLibraryModel16(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 17){
			printingOfLibraryModel17(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 18){
			printingOfLibraryModel18(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 19){
			printingOfLibraryModel19(parameter, initial, AInv, SD); 
		}
		if(modelNumber == 20){
			printingOfLibraryModel20(parameter, initial, AInv, SD); 
		}
		
		if(modelNumber == 21){
			printingOfLibraryModel21(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 22){
			printingOfLibraryModel22(parameter, initial, AInv, SD);
		}
		
		if(modelNumber == 23){
			printingOfLibraryModel23(parameter, initial, AInv, SD); 
		}
		if(modelNumber == 24){
			printingOfLibraryModel24(parameter, initial, AInv, SD); 
		}
	}
	
	
	private void printingOfLibraryModel24(double[] parameter, double[] initial,
			double[][] aInv, double sD) throws RowsExceededException, WriteException, BiffException, IOException {
			
			String[] defaultUnit = new String[noOfParam];
			String[] preferredUnit = new String[noOfParam];
			
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];
			
			defaultUnit[1] = paramDefaultUnit[3];
			preferredUnit[1] = paramPreferredUnit[3];

			defaultUnit[2] = paramDefaultUnit[3];
			preferredUnit[2] = paramPreferredUnit[3];
			
			defaultUnit[3] = paramDefaultUnit[2];
			preferredUnit[3] = paramPreferredUnit[2];

			
			printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, aInv, sD, defaultUnit, preferredUnit);
		}


	private void printingOfLibraryModel23(double[] parameter, double[] initial,
			double[][] aInv, double sD) throws RowsExceededException, WriteException, BiffException, IOException {String[] defaultUnit = new String[noOfParam];
			String[] preferredUnit = new String[noOfParam];
			
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];
			
			defaultUnit[1] = paramDefaultUnit[0];
			preferredUnit[1] = paramPreferredUnit[0];
			
			defaultUnit[0] = paramDefaultUnit[0];
			preferredUnit[0] = paramPreferredUnit[0];
			
			defaultUnit[1] = paramDefaultUnit[0];
			preferredUnit[1] = paramPreferredUnit[0];
			
			defaultUnit[1] = paramDefaultUnit[2];
			preferredUnit[1] = paramPreferredUnit[2];


			printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, aInv, sD, defaultUnit, preferredUnit);
		}


	private void printingOfLibraryModel22(double[] parameter, double[] initial,
			double[][] aInv, double sD) throws RowsExceededException, WriteException, BiffException, IOException {String[] defaultUnit = new String[noOfParam];
			String[] preferredUnit = new String[noOfParam];
			
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];
			
			defaultUnit[1] = paramDefaultUnit[0];
			preferredUnit[1] = paramPreferredUnit[0];

			printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, aInv, sD, defaultUnit, preferredUnit);
		}


	private void printingOfLibraryModel21(double[] parameter, double[] initial,
			double[][] aInv, double sD) throws RowsExceededException, WriteException, BiffException, IOException {String[] defaultUnit = new String[noOfParam];
			String[] preferredUnit = new String[noOfParam];
			
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];
			
			defaultUnit[1] = paramDefaultUnit[0];
			preferredUnit[1] = paramPreferredUnit[0];

			printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, aInv, sD, defaultUnit, preferredUnit);
		}


	private void printingOfLibraryModel1(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {
		
		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[2];
		preferredUnit[0] = paramPreferredUnit[2];
		
		defaultUnit[1] = paramDefaultUnit[0];
		preferredUnit[1] = paramPreferredUnit[0];

		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel2(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[2];
		preferredUnit[0] = paramPreferredUnit[2];
		
		defaultUnit[1] = paramDefaultUnit[3];
		preferredUnit[1] = paramPreferredUnit[3];
		
		
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel3(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[0];
		preferredUnit[0] = paramPreferredUnit[0];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		defaultUnit[2] = paramDefaultUnit[0];
		preferredUnit[2] = paramPreferredUnit[0];
		
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel4(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[3];
		preferredUnit[0] = paramPreferredUnit[3];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		defaultUnit[2] = paramDefaultUnit[0];
		preferredUnit[2] = paramPreferredUnit[0];
		
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel5(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[0];
		preferredUnit[0] = paramPreferredUnit[0];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		defaultUnit[2] = paramDefaultUnit[0];
		preferredUnit[2] = paramPreferredUnit[0];
		
		defaultUnit[3] = paramDefaultUnit[7];
		preferredUnit[3] = paramPreferredUnit[7];
			
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel6(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[3];
		preferredUnit[0] = paramPreferredUnit[3];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		defaultUnit[2] = paramDefaultUnit[0];
		preferredUnit[2] = paramPreferredUnit[0];
		
		defaultUnit[3] = paramDefaultUnit[7];
		preferredUnit[3] = paramPreferredUnit[7];
		
			
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel7(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[0];
		preferredUnit[0] = paramPreferredUnit[0];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
				
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel8(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[3];
		preferredUnit[0] = paramPreferredUnit[3];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel9(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[2];
		preferredUnit[0] = paramPreferredUnit[2];
		
		defaultUnit[1] = paramDefaultUnit[0];
		preferredUnit[1] = paramPreferredUnit[0];
		
		defaultUnit[2] = paramDefaultUnit[0];
		preferredUnit[2] = paramPreferredUnit[0];
		
		defaultUnit[3] = paramDefaultUnit[0];
		preferredUnit[3] = paramPreferredUnit[0];
		
				
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel10(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[3];
		preferredUnit[0] = paramPreferredUnit[3];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		defaultUnit[2] = paramDefaultUnit[3];
		preferredUnit[2] = paramPreferredUnit[3];
		
		defaultUnit[3] = paramDefaultUnit[2];
		preferredUnit[3] = paramPreferredUnit[2];
		
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel11(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[0];
		preferredUnit[0] = paramPreferredUnit[0];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		defaultUnit[2] = paramDefaultUnit[0];
		preferredUnit[2] = paramPreferredUnit[0];
		
		defaultUnit[3] = paramDefaultUnit[0];
		preferredUnit[3] = paramPreferredUnit[0];

		defaultUnit[4] = paramDefaultUnit[0];
		preferredUnit[4] = paramPreferredUnit[0];

		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel12(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[3];
		preferredUnit[0] = paramPreferredUnit[3];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		defaultUnit[2] = paramDefaultUnit[0];
		preferredUnit[2] = paramPreferredUnit[0];
				
		defaultUnit[3] = paramDefaultUnit[3];
		preferredUnit[3] = paramPreferredUnit[3];
		
		defaultUnit[4] = paramDefaultUnit[2];
		preferredUnit[4] = paramPreferredUnit[2];

	
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel13(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[0];
		preferredUnit[0] = paramPreferredUnit[0];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		defaultUnit[2] = paramDefaultUnit[0];
		preferredUnit[2] = paramPreferredUnit[0];
		
		defaultUnit[3] = paramDefaultUnit[0];
		preferredUnit[3] = paramPreferredUnit[0];

		defaultUnit[4] = paramDefaultUnit[0];
		preferredUnit[4] = paramPreferredUnit[0];
		
		defaultUnit[5] = paramDefaultUnit[7];
		preferredUnit[5] = paramPreferredUnit[7];
		
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
		
	}
	
	private void printingOfLibraryModel14(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[3];
		preferredUnit[0] = paramPreferredUnit[3];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		defaultUnit[2] = paramDefaultUnit[0];
		preferredUnit[2] = paramPreferredUnit[0];
				
		defaultUnit[3] = paramDefaultUnit[3];
		preferredUnit[3] = paramPreferredUnit[3];
		
		defaultUnit[4] = paramDefaultUnit[2];
		preferredUnit[4] = paramPreferredUnit[2];
		
		defaultUnit[5] = paramDefaultUnit[7];
		preferredUnit[5] = paramPreferredUnit[7];
		
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel15(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[0];
		preferredUnit[0] = paramPreferredUnit[0];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		defaultUnit[2] = paramDefaultUnit[0];
		preferredUnit[2] = paramPreferredUnit[0];
		
		defaultUnit[3] = paramDefaultUnit[0];
		preferredUnit[3] = paramPreferredUnit[0];
		
		
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
		
	}
	
	private void printingOfLibraryModel16(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[3];
		preferredUnit[0] = paramPreferredUnit[3];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		defaultUnit[2] = paramDefaultUnit[3];
		preferredUnit[2] = paramPreferredUnit[3];
		
		defaultUnit[3] = paramDefaultUnit[2];
		preferredUnit[3] = paramPreferredUnit[2];
		
		
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel17(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		for (int i = 0; i < parameter.length -1; i++) {
		
			defaultUnit[i] = paramDefaultUnit[0];
			preferredUnit[i] = paramPreferredUnit[0];
		}
		
			
		defaultUnit[5] = paramDefaultUnit[2];
		preferredUnit[5] = paramPreferredUnit[2];
		
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
	}
	
	private void printingOfLibraryModel18(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[3];
		preferredUnit[0] = paramPreferredUnit[3];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		defaultUnit[2] = paramDefaultUnit[3];
		preferredUnit[2] = paramPreferredUnit[3];
		
		defaultUnit[3] = paramDefaultUnit[2];
		preferredUnit[3] = paramPreferredUnit[2];
		
		defaultUnit[4] = paramDefaultUnit[3];
		preferredUnit[4] = paramPreferredUnit[3];
		
		defaultUnit[5] = paramDefaultUnit[2];
		preferredUnit[5] = paramPreferredUnit[2];
		
		
		
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
		
	}
	
	private void printingOfLibraryModel19(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		for (int i = 0; i < parameter.length -1; i++) {
			
			defaultUnit[i] = paramDefaultUnit[0];
			preferredUnit[i] = paramPreferredUnit[0];
		}
		
			
		defaultUnit[5] = paramDefaultUnit[2];
		preferredUnit[5] = paramPreferredUnit[2];
		
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
		
	}
	
	private void printingOfLibraryModel20(double[] parameter,
			double[] initial, double[][] AInv, double SD) throws RowsExceededException, WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];
		
		defaultUnit[0] = paramDefaultUnit[3];
		preferredUnit[0] = paramPreferredUnit[3];
		
		defaultUnit[1] = paramDefaultUnit[2];
		preferredUnit[1] = paramPreferredUnit[2];
		
		defaultUnit[2] = paramDefaultUnit[3];
		preferredUnit[2] = paramPreferredUnit[3];
		
		defaultUnit[3] = paramDefaultUnit[2];
		preferredUnit[3] = paramPreferredUnit[2];
		
		defaultUnit[4] = paramDefaultUnit[3];
		preferredUnit[4] = paramPreferredUnit[3];
		
		defaultUnit[5] = paramDefaultUnit[2];
		preferredUnit[5] = paramPreferredUnit[2];
		
				
		printOfParamValInst.writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD, defaultUnit, preferredUnit);
		
	}
}
