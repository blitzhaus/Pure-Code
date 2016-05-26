package Model;

import java.io.IOException;

import view.ProcessingInputsInfo;

public class DeConvoModelInfoPreparator {
	
	DeConvolutionVariables deConvoVarInst;
	ProcessingInputsInfo procInputInst;
	
	void copyProfileRelatedInfo() {
		
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		procInputInst = deConvoVarInst.copyProcessingInput();

		deConvoVarInst.noOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		deConvoVarInst.profileObsNos = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo();

		deConvoVarInst.noOfProfiles = procInputInst.getProfileAndParamInfoObj()
				.getNoOfSubject();
		
		deConvoVarInst.sortVarData = procInputInst.getProfileAndParamInfoObj().getDataSortVariables();
		
		deConvoVarInst.profileObsNos = procInputInst.getProfileAndParamInfoObj().getSubjectObsNo();
		
		deConvoVarInst.sortVariables = new String[deConvoVarInst.noOfSortVar];
		for (int i = 0; i < deConvoVarInst.noOfSortVar; i++) {
			deConvoVarInst.sortVariables[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}
	}
	
	
	

	void createSingleProfileData(int sum, int ii) throws IOException {
		
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		procInputInst = deConvoVarInst.copyProcessingInput();


		deConvoVarInst.time = new double[procInputInst.getProfileAndParamInfoObj()
		                 				.getSubjectObsNo()[ii]];
		deConvoVarInst.conc = new double[procInputInst.getProfileAndParamInfoObj()
			                 				.getSubjectObsNo()[ii]];
		
		
		
		for (int i = 0; i < procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]; i++) {
			deConvoVarInst.time[i] = procInputInst.getProfileAndParamInfoObj()
					.getTimeForAllProfile()[sum + i];
			deConvoVarInst.conc[i] = procInputInst.getProfileAndParamInfoObj()
					.getConcForAllProfile()[sum + i];

		}

	}
	
	void copyPolyExpoModelInfo()
	{
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		procInputInst = deConvoVarInst.copyProcessingInput();

		deConvoVarInst.noOfExpTerm = procInputInst.getPolyExpoOptionsInst().getNoOfExpTerm();
		
		
	}
	
	
	void copyDeConvoModelInfo(int profileNo)
	{
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		procInputInst = deConvoVarInst.copyProcessingInput();
		
		deConvoVarInst.ifAucInfProvided = procInputInst.getDeConvoAucAndLZInst().getDeConvoAucLZInfoInst()[profileNo].isIfGivenAucInfUsed();
		deConvoVarInst.ifPredAucInfIsUsed = procInputInst.getDeConvoAucAndLZInst().getDeConvoAucLZInfoInst()[profileNo].isIfPredAucInfUsed();
		deConvoVarInst.ifObsAucInfIsUsed = procInputInst.getDeConvoAucAndLZInst().getDeConvoAucLZInfoInst()[profileNo].isIfObsAucInfUsed();
		deConvoVarInst.ifLambdaZProvided = procInputInst.getDeConvoAucAndLZInst().getDeConvoAucLZInfoInst()[profileNo].isIfGivenLamZIntUsed();
		
		deConvoVarInst.adjRsq = procInputInst.getLambdazDataobj().getLambdaZDetailsOf(profileNo).getAdjRSquare();
		deConvoVarInst.rsq = procInputInst.getLambdazDataobj().getLambdaZDetailsOf(profileNo).getrSquare();
		deConvoVarInst.noOfPtsLambdaZ = procInputInst.getLambdazDataobj().getLambdaZDetailsOf(profileNo).getNoPtsLambdaZ();
		deConvoVarInst.lambdaZ = procInputInst.getLambdazDataobj().getLambdaZDetailsOf(profileNo).getLambdaZ();
		
		String str = "";
		
		if(deConvoVarInst.ifLambdaZProvided == true)
		{
			str = procInputInst.getDeConvoAucAndLZInst().getDeConvoAucLZInfoInst()[profileNo].getUserGivenLambdaZ();
			deConvoVarInst.lambdaZ = Double.parseDouble(str);
			
			str = procInputInst.getDeConvoAucAndLZInst().getDeConvoAucLZInfoInst()[profileNo].getUserGivenIntercept();
			deConvoVarInst.intercept = Double.parseDouble(str);
		}else
		{
			deConvoVarInst.lambdaZ =  procInputInst.getLambdazDataobj().getLambdaZDetailsOf(profileNo).getLambdaZ();
		}
		
		
		if(deConvoVarInst.ifAucInfProvided)
		{
			str = procInputInst.getDeConvoAucAndLZInst().getDeConvoAucLZInfoInst()[profileNo].getUserGivenAucInf();
			deConvoVarInst.aucInf = Double.parseDouble(str);
		}
		
		
		if(deConvoVarInst.analysisType.equals("looriegel"))
		{
			deConvoVarInst.parameter = new double[3];
			
			
			
			for (int i = 0; i < deConvoVarInst.parameter.length; i++) {
				str = procInputInst.getDeConvoSetupInfoInst().getParameterValueAt(profileNo*3 +i, deConvoVarInst.noOfSortVar+1);
				deConvoVarInst.parameter[i] = Double.parseDouble(str);
			}
		}
			
		
		
	}
	
	void copyExpValuesForCurrentProfile(int profileNo)
	{
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		procInputInst = deConvoVarInst.copyProcessingInput();
		
		deConvoVarInst.expValues = new double[2*deConvoVarInst.noOfExpTerm];
		
		for (int i = 0; i < deConvoVarInst.expValues.length; i++) {
			String temp = procInputInst.getDeConvoSetupInfoInst().getExpValues()[profileNo *2*deConvoVarInst.noOfExpTerm+i][deConvoVarInst.noOfSortVar+1];
			deConvoVarInst.expValues[i] = Double.parseDouble(temp);
		}
		
	}
	
	
	void copyDoseForCurrentProfile(int profileNo)
	{
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		procInputInst = deConvoVarInst.copyProcessingInput();
		
		deConvoVarInst.dose = new double[1];
		
		for (int i = 0; i < deConvoVarInst.dose.length; i++) {
			String temp = procInputInst.getDeConvoSetupInfoInst().getDose()[profileNo][deConvoVarInst.noOfSortVar];
			deConvoVarInst.dose[i] = Double.parseDouble(temp);
		}
	}
	
	void copyDosingInfoForWagNelson(int profileNo)
	{
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		procInputInst = deConvoVarInst.copyProcessingInput();
		
		deConvoVarInst.dosingTime = new double[1];
		
		for (int i = 0; i < deConvoVarInst.dosingTime.length; i++) {
			String temp = procInputInst.getDeConvoSetupInfoInst().getDose()[profileNo][deConvoVarInst.noOfSortVar];
			deConvoVarInst.dosingTime[i] = Double.parseDouble(temp);
		}
	}




	public void copyModelOptions() {
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		procInputInst = deConvoVarInst.copyProcessingInput();
		
		deConvoVarInst.aucCalMethodNo = procInputInst.getModelInputsObj().getCalculationMethod();
		
	}
	 
	

}
