package Model;


import java.io.IOException;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import pkpd_library.integration.DFunction;
import pkpd_library.integration.ODEsolver;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class AsciiParameterEstimator implements DFunction {
	static int j;
	public static Hashtable<String, String> hashTable = new Hashtable<String, String>();
	boolean gotoFlag;
	ArrayList<String> strArray = new ArrayList<String>();
	boolean condFlag = true;

	public static AsciiParameterEstimator ASCIIEVAL = null;

	public static AsciiParameterEstimator createAsciiParamEstimationInst() throws IOException {
		if (ASCIIEVAL == null) {
			ASCIIEVAL = new AsciiParameterEstimator();
		}
		return ASCIIEVAL;
	}

	ApplicationInfo appInfo;

	public ApplicationInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(ApplicationInfo appInfo) {
		this.appInfo = appInfo;
	}

	Model.WorkSheetOutputs wsOutputs;

	PkPdInfo pkPdInst;
	ListOfDataStructures dataStructInst;
	ProfileRelatedInfoPreparator profileRelatedInfoInst;
	ModelAndEngineSettingsInfo modelEnginInst;
	InitializationOfResultStructure initResStructInst;
	DosingRelatedInfoReader dosingInfoInst;
	WriteListOfInputCommand writeIpCommandInst;

	
	InitialParamInfoPreparator initParamInst;
	ProcessingInputsInfo procInputInst;
	ParameterEstimator paramEstimateInst;
	ResultsStructureStorer resStructStoreInstance;
	EstimatedParamValStorer paramValueStorerInst;
	AsciiOutputPreparator asciiOutputPrepInst;

	

	public void evaluateAscii(ApplicationInfo appInfo) throws IOException,
			RowsExceededException, WriteException, BiffException {
		

		pkPdInst = PkPdInfo.createPKPDInstance();
		
		pkPdInst.analysisType = "ascii";
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		dataStructInst.appInfo = appInfo;
		
		
		
		pkPdInst.workSheetOutputInst = new WorkSheetOutputs();
		profileRelatedInfoInst = new ProfileRelatedInfoPreparator();
		modelEnginInst = new ModelAndEngineSettingsInfo();
		initResStructInst = new InitializationOfResultStructure();

		writeIpCommandInst = new WriteListOfInputCommand();

		initParamInst = new InitialParamInfoPreparator();

		procInputInst = pkPdInst.copyProcessingInput();

		pkPdInst.defaultUnitForCA = procInputInst.getParameterUnitsDataObj()
				.getDefaultUnitArray();

		pkPdInst.preferredUnitForCA = procInputInst.getParameterUnitsDataObj()
				.getPreferredUnitsArray();

	

		if (procInputInst.getModelInputTab1Obj().isSimulation() == true) {
			SimulationForLibraryModels simulationInst = SimulationForLibraryModels
					.createSimulationInst();
			simulationInst.performSimulation();
		} else {
			String stringTime;

			profileRelatedInfoInst
					.copyProfileRelatedInfo(pkPdInst.analysisType);

			modelEnginInst
					.copyModelAndEngineSettingsInfo(pkPdInst.analysisType);

			
			initResStructInst.initializeResultAL(pkPdInst.analysisType);
			stringTime = profileRelatedInfoInst.systemTime();

			writeIpCommandInst.writingHeaderToTextOutput(stringTime);

			asciiOutputPrepInst = new AsciiOutputPreparator();
			//writeIpCommandInst.writingListOfInputCommand(pkPdInst.analysisType);
			mainParamComputationLoop();

		}

	

	}
	
	private void mainParamComputationLoop() throws RowsExceededException,
	WriteException, BiffException, IOException {
int sum = 0;

for (int profileNo = 0; profileNo < dataStructInst.noOfSubjects; profileNo++) {

	pkPdInst.currentProfileNumber = profileNo;
	int temp1 = pkPdInst.currentProfileNumber + 1;
	pkPdInst.workSheetOutputInst
			.getTextoutput()
			.add(
					"\r\n"
							+ StringUtils
									.rightPad(
											"*************************************************"
													+ "**********************************",
											80) + "\r\n\r\n");

	pkPdInst.workSheetOutputInst.getTextoutput().add(
			StringUtils.rightPad("", 25)
					+ StringUtils.rightPad(
							"Output of the Profile Number:", 37)
					+ StringUtils.rightPad(temp1 + "", 5) + "\r\n\r\n");

	profileRelatedInfoInst.createSingleProfileData(
			pkPdInst.analysisType, sum, profileNo);
	sum = sum + dataStructInst.subjectObsNos[profileNo];

	profileRelatedInfoInst.removeMissingData(profileNo);

	DetectionOfProfilesWithInsufficientData insuffProfileDetectionInst = new DetectionOfProfilesWithInsufficientData();
	boolean ifProfileIsInsufficient;
	ifProfileIsInsufficient = insuffProfileDetectionInst
			.detectProfileWithInsufficientDataInCA(pkPdInst.X,
					pkPdInst.Y, pkPdInst.noOfParam);

	

		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad(
						"*************************************************"
								+ "**********************************",
						80)
						+ "\r\n\r\n");

		pkPdInst.exceptionForCurrentProfile = 0;

		pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n");

		pkPdInst.row[0] = dataStructInst.subjectObsNos[profileNo];
		if (pkPdInst.exceptionForCurrentProfile == 0) {
			if (procInputInst.getModelInputTab2Obj()
					.isPropagateFinalEstimae() == true
					&& pkPdInst.currentProfileNumber > 0) {
				pkPdInst.parameter = dataStructInst.propFinalEstimate;
			} else {
				initParamInst.initParamValueInfo("ascii",profileNo);

			}
		}

		if (pkPdInst.exceptionForCurrentProfile == 0)

			asciiOutputPrepInst.fillingUpInitialParameterTable(profileNo);

		if (pkPdInst.exceptionForCurrentProfile == 0)
			estimateParameter(profileNo);

		if (pkPdInst.exceptionForCurrentProfile == 0) {
			asciiOutputPrepInst.createAndPrintingOfSummaryAndSummaryTable(
					pkPdInst.parameter, pkPdInst.X, pkPdInst.Y,
					pkPdInst.extraData, pkPdInst.row, profileNo,
					pkPdInst.numberOfSortVar);

		}

		pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

		Time t2 = new Time(System.currentTimeMillis());

		pkPdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5)
						+ StringUtils.rightPad("Time at end is:", 20)
						+ t2);
		pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

		pkPdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						"\r\n\r\n"
								+ StringUtils
										.rightPad(
												"*************************************************"
														+ "**********************************",
												80) + "\r\n\r\n");
		pkPdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils.rightPad("", 15)
								+ StringUtils
										.rightPad(
												"End of the Output of the Profile Number:",
												5) + temp1);
		pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");
		pkPdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						"\r\n\r\n"
								+ StringUtils
										.rightPad(
												"*************************************************"
														+ "**********************************",
												80) + "\r\n\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");
		pkPdInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");
	//}

	String string = profileRelatedInfoInst.systemTime();
	asciiOutputPrepInst.fillingUpHistoryDisplayTable(string);

	resStructStoreInstance = new ResultsStructureStorer();

	resStructStoreInstance.storeSpreedSheetResults();

}
}

	protected String systemTime() {
		java.sql.Timestamp currentTime = new java.sql.Timestamp(
				new java.util.Date().getTime());
		String currenttime = String.valueOf(currentTime);
		String date = currenttime.substring(0, 10);
		String time = currenttime.substring(11, 19);

		return (date + "/" + time);
	}

	public void initializeResultsAL() {}

	

	private void estimateParameter(int i) throws RowsExceededException,
			WriteException, BiffException, IOException {
		GaussNewton gnInstance = new GaussNewton();
		NelderMead nmInstance = new NelderMead();
		pkPdInst = PkPdInfo.createPKPDInstance();
		
		double[][] extraData = new double[1][1];
		double[] infusionTime = new double[1];
		double[] dose = new double[1];
		double[] dosingTime = new double[1];

		pkPdInst.setIfCallingForParameterCalculation(true);
		
		if(pkPdInst.methodNo == 1)
		pkPdInst.parameter = gnInstance.gaussNewtonLM(0, pkPdInst.parameter,
				pkPdInst.X, pkPdInst.Y, extraData, pkPdInst.row,
				pkPdInst.delta, infusionTime, dose, dosingTime,
				pkPdInst.numberOfSortVar, pkPdInst.dataSortVariables,
				pkPdInst.parameter.length, pkPdInst.pdIncrement, i);
		else if(pkPdInst.methodNo == 2)
			pkPdInst.parameter = gnInstance.GaussNewtonLH(0, pkPdInst.initial,
					pkPdInst.X, pkPdInst.Y, extraData, pkPdInst.row,
					pkPdInst.delta, infusionTime, dose, dosingTime,
					pkPdInst.numberOfSortVar, pkPdInst.dataSortVariables,
					pkPdInst.initial.length, pkPdInst.pdIncrement, i);
		else if(pkPdInst.methodNo == 3)
			pkPdInst.parameter = nmInstance
			.nMProcess(
					pkPdInst.parameter,
					pkPdInst.X,
					pkPdInst.Y,
					pkPdInst.extraData,
					pkPdInst.row,
					pkPdInst.delta,
					pkPdInst.initSimplex,
					PkParamEstimator
							.createPkParamEstimateInstance().infusionTime,
					PkParamEstimator
							.createPkParamEstimateInstance().dose,
					PkParamEstimator
							.createPkParamEstimateInstance().dosingTime, i);
		
		pkPdInst.setIfCallingForParameterCalculation(false);

			

	}

	private void copyInitParamValue(int i) {

		retrievingUserGivenInitValueForAscii(i);
	}

	void retrievingUserGivenInitValueForAscii(int i) {
		pkPdInst.parameter = new double[pkPdInst.noOfParam];
		dataStructInst.initial = new double[pkPdInst.noOfParam];

		for (int parCount = 0; parCount < pkPdInst.noOfParam; parCount++) {
			try {
				String S = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(
								i * pkPdInst.noOfParam + parCount,
								pkPdInst.numberOfSortVar + 1);

				Double.parseDouble(S);
				pkPdInst.parameter[parCount] = Double.parseDouble(S);
				dataStructInst.initial[parCount] = Double.parseDouble(S);

			} catch (Exception e) {
				System.out.println(" Exception in initial parameter value");
			}
			if (pkPdInst.parameter[parCount] <= 0) {

				break;
			}
		}
	}

	private void copyProfileRelatedInfoForAscii() {

		pkPdInst.numberOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		dataStructInst.subjectObsNos = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo();

		dataStructInst.noOfSubjects = procInputInst.getProfileAndParamInfoObj()
				.getNoOfSubject();

		pkPdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();
		pkPdInst.noOfDose = procInputInst.getModelInputTab1Obj()
				.getNumberOfDose();

		pkPdInst.sortVariables = new String[pkPdInst.numberOfSortVar];

		pkPdInst.row = dataStructInst.subjectObsNos;

		for (int i = 0; i < pkPdInst.numberOfSortVar; i++) {
			pkPdInst.sortVariables[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		pkPdInst.dataSortVariables = procInputInst.getProfileInfoObj()
				.getDataSortVariables();
	}

	private void createSingleProfileDataForAscii(int sum, int ii) {

		pkPdInst.setX(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);
		pkPdInst.setY(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);

		for (int i = 0; i < procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]; i++) {
			pkPdInst.X[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getTimeForAllProfile()[sum + i];
			pkPdInst.Y[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getConcForAllProfile()[sum + i];

		}

	}

	private void copyDataFromAppInfo(int sum, int i) {

		createSingleProfileDataForAscii(sum, i);
	}
	
	static long time = 0;
	static int iterationCount = 0;
	double evaluateCode(double[] parameter, double x, int funcNum, int ProfNum, boolean isFirstIteration)
			throws IOException {
		iterationCount++;
		long startTime = System.currentTimeMillis();
		hashTable.put("x", x+"");
		setDoseConstants(ProfNum);
		setPrimaryParamValues(parameter);
		getValue(isFirstIteration);
		long endTime = System.currentTimeMillis();
		long currentTimeElapsed = (endTime - startTime);
		time = time + currentTimeElapsed;
		return  Double.valueOf(hashTable.get("f"));
	}

	private void setPrimaryParamValues(double[] parameter) {
		
		ApplicationInfo appInfo = ListOfDataStructures.createListOfDataStructInstance().appInfo;
		String[] param = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().getProcessingInputs().getProfileAndParamInfoObj().getParameterNameForCA();
		for(int i=0;i<param.length;i++){
			hashTable.put(param[i], parameter[i]+"");
		}
	}

	private void setDoseConstants(int profNum) {
		ApplicationInfo appInfo = ListOfDataStructures.createListOfDataStructInstance().appInfo;
		
		HashMap<String, Double> hm = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().getProcessingInputs().getAsciiProfinfoAL().get(profNum).getConstantsHm();
		 for (Entry<String, Double> entry : hm.entrySet()) {
			 hashTable.put(entry.getKey(), entry.getValue()+"");
			 }
	}

	public AsciiParameterEstimator() {

	}

	String strLine;
	int size;
	boolean isLastIteration = false;
	ArrayList<String> diffEquations = new ArrayList<String>();
	public void getValue(boolean isFirstIteration) throws IOException {
		
		
		
		ApplicationInfo appInfo = ListOfDataStructures.createListOfDataStructInstance().appInfo;
	
		int nDer = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getAsciiInfo().getProcessingInputs().getAsciiCommands().getnDer();
		diffEquations = new ArrayList<String>();
		int i = 0;
		strLine = null;
		boolean isDifferentialBlock = false;

		//if it is first iteration then load the ascii code into an array list
		if(isFirstIteration == true){
			getAsciiCode();
		}
		j = 0;
		int size = strArray.size();
		while (j < size) {
			strLine = strArray.get(j);
			strLine = removeSpaces(strLine);
			j += 1;
			if (strLine.length() == 0) {
				continue;
			}
			if (isEquals(strLine, "COMMENT")) {
				while (!isEquals(strLine, "COMMENTEND") && j < strArray.size()) {
					strLine = strArray.get(j);
					strLine = removeSpaces(strLine);
					j += 1;
				}
			} else if (isEquals(strLine, "Selection")
					|| isEquals(strLine, "Declaration")
					|| isEquals(strLine, "Initialization")
					|| isEquals(strLine, "Secondaryparameterequations")
					|| isEquals(strLine, "Differentialequations")
					|| isEquals(strLine, "Initialization")) {
				if((isLastIteration == false) && (isEquals(strLine, "Secondaryparameterequations"))){
					break;
				} else {
					if(isEquals(strLine, "Differentialequations")){
						isDifferentialBlock = true;
					} else if(strLine.contains("Func")){
						if(isDifferentialBlock == true){
							double[] C0 = getC0();  
							double[] Yc = getYc();
							double t = Double.parseDouble(hashTable.get("x"));
							solvingODE(0.01, 0.0 , t, C0, Yc);
							isDifferentialBlock = false;
						}
					}
					strLine = strArray.get(j);
					strLine = removeSpaces(strLine);
					j += 1;
					while (((strLine.contains("=") || strLine.contains("<-") || strLine
							.length() == 0) && j < strArray.size())) {
						if (strLine.contains("<-")) {
							asgnVar(strLine);
						} else if (strLine.contains("=")) {
							if(strLine.startsWith("dz")){// if it is a differential equation dont evaluate it but just add it to an array list
								diffEquations.add(strLine);
							} else { // if its not a differential equation then evaluate the expression
								exprVar(strLine, false);
							}
						}
						if(((j+1)!=size) && (isEquals(strArray.get((j+1)), "Selection")
								|| isEquals(strArray.get((j+1)), "Declaration")
								|| isEquals(strArray.get((j+1)), "Initialization")
								|| isEquals(strArray.get((j+1)), "Secondaryparameterequations")
								|| isEquals(strArray.get((j+1)), "Differentialequations")
								|| isEquals(strArray.get((j+1)), "Initialization"))){
							break;
						} else {
							strLine = strArray.get(j);
							if(strLine.contains("Func") && (nDer > 0)){
								if(isDifferentialBlock == true){
									double[] C0 = getC0();  
									double[] Yc = new double[C0.length];
									double t = Double.parseDouble(hashTable.get("x"));
									solvingODE(0.01, 0.0 , t, C0, Yc);
									
									isDifferentialBlock = false;
								}
							}
							strLine = removeSpaces(strLine);
							j += 1;
						}
					}
					if (j == size && strArray.get(j - 1).contains("=")) {
						strLine = strArray.get(j - 1);
						strLine = removeSpaces(strLine);
						if(strLine.startsWith("dz")){
							diffEquations.add(strLine);
						} else {
							exprVar(strLine, false);
						}
					}
				}
				
			} else if (strLine.startsWith("for")) {
				String[] array = strLine.split("=");
				String idx = array[0].substring(3);
				String[] bounds = array[1].split("to");
				int initVal = Double.valueOf(bounds[0]).intValue();
					hashTable.put(idx, Integer.toString(initVal));
					int finalVal = Double.valueOf(hashTable.get(bounds[1]))
						.intValue();
				ArrayList<String> forBody = new ArrayList<String>();
				strLine = strArray.get(j);
				strLine = removeSpaces(strLine);
				j += 1;
				while (j < size && !strLine.equals("repeat")) {
					forBody.add(strLine);
					strLine = strArray.get(j);
					strLine = removeSpaces(strLine);
					j += 1;
				}
				for (int j = 0; j < forBody.size(); j++) {
				}
				for (int ite = initVal; ite <= finalVal; ite++) {
					forImplementation(forBody);
					if (gotoFlag == true) {
						gotoFlag = false;
						break;
					}
					hashTable.put(idx, Integer.toString(ite + 1));
				}

			} else if (strLine.startsWith("if")) {
				ArrayList<String> condBody = new ArrayList<String>();
				ArrayList<String> elseBody = new ArrayList<String>();
				condBody.add(strLine);
				strLine = strArray.get(j);
				while (!isEquals(strLine, "endif")
						&& !isEquals(strLine, "else")) {
					j += 1;
					condBody.add(strLine);
					strLine = strArray.get(j);
				}
				if (strArray.contains("else") && strLine.startsWith("else")) {
					j += 1;
					strLine = strArray.get(j);
					while (!isEquals(strLine, "endif")) {
						elseBody.add(strLine);
						j += 1;
						strLine = strArray.get(j);
					}
				}
				conditionImplementation(condBody);
				if (gotoFlag == true) {
					return;
				}
				if (condFlag == false) {
					condFlag = true;
					executeCondition(elseBody);
					if (gotoFlag == true) {
						return;
					}
				}
			} else if (strLine.contains("<-")) {
				asgnVar(strLine);
			} else if (strLine.contains("=")) {
				//System.out.println(strLine);
				exprVar(strLine, false);
			} else {
			}
		}
	}

	
	private void getAsciiCode() {
		ApplicationInfo appInfo = ListOfDataStructures.createListOfDataStructInstance().appInfo;
		String code =appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getAsciiInfo().getProcessingInputs().getAsciiNonEditText().getNonEditText();
		String[] localArray = code.split("\n");
		strArray = new ArrayList<String>();
		int i=0;
		while(i<localArray.length){
			strLine = localArray[i];
			strLine = removeSpaces(strLine);
			strArray.add(i++, strLine);
		}
		
	}

	private double[] getYc() {
ApplicationInfo appInfo = ListOfDataStructures.createListOfDataStructInstance().appInfo;
		
int numDiffEqn = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().getProcessingInputs().getAsciiCommands().getnDer();
		double[] temp = new double[numDiffEqn];
		for(int i=0;i<numDiffEqn;i++){
			String key = "dz"+(i+1)+"";
			temp[i] = Double.parseDouble(hashTable.get(key));
		}
		return temp;
	}

	private double[] getC0() {
		ApplicationInfo appInfo = ListOfDataStructures.createListOfDataStructInstance().appInfo;
		int numDiffEqn = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().getProcessingInputs().getAsciiCommands().getnDer();
		double[] temp = new double[numDiffEqn];
		for(int i=0;i<numDiffEqn;i++){
			String key = "z"+(i+1);
			temp[i] = Double.parseDouble(hashTable.get(key));
		}
		return temp;
	}

	private double[] solvingODE(double h, double t0, double t, double[] C0,
			double[] Yc) {
		//System.out.println( "time = "+ t);
		int odeSolverChoice = 1;
		if (odeSolverChoice == 1)
			Yc = ODEsolver.RKF(this, t0, C0, t, h, 1e-4, 0.1, 1e-5, 1e-5);
		if (odeSolverChoice == 2)
			Yc = ODEsolver.RKC(this, t0, C0, t, h, 1e-4, 0.1, 1e-5, 1e-5);
		if (odeSolverChoice == 3)
			Yc = ODEsolver.RK4(this, t0, C0, t, h);
		if (odeSolverChoice == 4)
			Yc = ODEsolver.RKG(this, t0, C0, t, h);
		if (odeSolverChoice == 5)
			Yc = ODEsolver.APC(this, t0, C0, t, h);
		if (odeSolverChoice == 6)
			Yc = ODEsolver.Gear4(this, t0, C0, t, h);
		if (odeSolverChoice == 7)
			Yc = ODEsolver.Gear5(this, t0, C0, t, h);
		if (odeSolverChoice == 8)
			Yc = ODEsolver.Gear6(this, t0, C0, t, h);
		if (odeSolverChoice == 9)
			Yc = ODEsolver.Implicit_trap(this, t0, C0, t, h);
		if (odeSolverChoice == 10)
			Yc = ODEsolver.Explicit_Euler(this, t0, C0, t, h);
		if (odeSolverChoice == 11)
			Yc = ODEsolver.Implicit_Euler(this, t0, C0, t, h);
		
		// replace all the start variables in the hash table
		for(int i=0;i< Yc.length;i++){
			String key = "z"+(i+1);
			hashTable.put(key, Yc[i]+"");
		}
		
		return Yc;
	}
	
	void forImplementation(ArrayList<String> forBody) {
		int size = forBody.size();
		String strLine;
		String strTag = null;
		for (int i = 0; i < size; i++) {
			strLine = forBody.get(i);
			if (strLine.startsWith("if")) {
				ArrayList<String> condBody = new ArrayList<String>();
				ArrayList<String> elseBody = new ArrayList<String>();
				condBody.add(strLine);
				i += 1;
				strLine = forBody.get(i);
				while (!isEquals(strLine, "endif")
						&& !isEquals(strLine, "else")) {
					i += 1;
					condBody.add(strLine);
					strLine = forBody.get(i);
				}
				if (forBody.contains("else")) {
					while (!isEquals(strLine, "endif")) {
						i += 1;
						elseBody.add(strLine);
						strLine = forBody.get(i);
					}
				}
				conditionImplementation(condBody);
				if (gotoFlag == true) {
					return;
				}
				if (condFlag == false) {
					condFlag = true;
					executeCondition(elseBody);
					if (gotoFlag == true) {
						return;
					}
				}
			} else if (strLine.contains("<-")) {
				asgnVar(strLine);
			} else if (strLine.contains("=")) {
				exprVar(strLine, false);
			}
		}
	}

	void conditionImplementation(ArrayList<String> condBody) {
		// TODO Auto-generated method stub
		String strLine;
		int size = condBody.size();
		if (size > 0) {
			strLine = condBody.get(0);
			if (strLine.contains("lte")) {
				String[] array = strLine.split("lte");
				double lhs = Double.valueOf(hashTable
						.get(array[0].substring(2)));
				double rhs = AsciiFunctionEval.createAsciiFuncEval().main(array[1]);
				if (lhs <= rhs) {
					executeCondition(condBody);
					if (gotoFlag == true) {
						return;
					}
				} else {
					condFlag = false;
				}
			} else if (strLine.contains("eq")) {
				String[] array = strLine.split("eq");
				double lhs = Double.valueOf(hashTable
						.get(array[0].substring(2)));
				double rhs = AsciiFunctionEval.createAsciiFuncEval().main(array[1]);
				if (lhs == rhs) {
					executeCondition(condBody);
					if (gotoFlag == true) {
						return;
					}
				} else {
					condFlag = false;
				}
			} else if (strLine.contains("gt")) {
				String[] array = strLine.split("gt");
				double lhs = Double.valueOf(hashTable
						.get(array[0].substring(2)));
				double rhs = AsciiFunctionEval.createAsciiFuncEval().main(array[1]);
				if (lhs > rhs) {
					executeCondition(condBody);
					if (gotoFlag == true) {
						return;
					}
				} else {
					condFlag = false;
				}
			}
		}
	}

	void executeCondition(ArrayList<String> condBody) {
		// TODO Auto-generated method stub
		int size = condBody.size();
		String strLine;
		for (int i = 0; i < size; i++) {
			strLine = condBody.get(i);
			if (isEquals(strLine, "COMMENT")) {
				i += 1;
				while (!isEquals(strLine, "COMMENTEND") && i < condBody.size()) {
					strLine = condBody.get(i);
					strLine = removeSpaces(strLine);
					i += 1;
				}
				i -= 1;
			} else if (strLine.contains("<-")) {
				asgnVar(strLine);
			} else if (strLine.contains("=")) {
				exprVar(strLine, false);
			} else if (strLine.length() >= 4
					&& isEquals(strLine.substring(0, 4), "GOTO")) {
				String label = strLine.substring(4) + ":";
				gotoFlag = true;
				j = strArray.indexOf(label);
				return;
			}
		}
	}

	double exprVar(String input, boolean isFromDiff) {
		String[] exprArray = input.split("=");
		double val;
		if (exprArray[1].contains("max") || exprArray[1].contains("min")
				|| exprArray[1].contains("const")
				|| exprArray[1].contains("seco")
				|| exprArray[1].contains("prim")) {
			String parenthesisedStr = AsciiParenthesise.createAsciiParenthesiseInst().main(exprArray[1]);
			val = AsciiFunctionEval.createAsciiFuncEval().main(parenthesisedStr);
		} else {
			String parenthesisedStr = AsciiParenthesise.createAsciiParenthesiseInst().main(exprArray[1]);
			val = AsciiFunctionEval.createAsciiFuncEval().main((parenthesisedStr));
		}
		
		if(isFromDiff == false){
			hashTable.put(exprArray[0], Double.toString(val));
			return 0.0;
		} else {
			return val;
		}
		
	}

	String removeSpaces(String input) {
		// TODO Auto-generated method stub
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ' ') {
				input = input.replace(" ", "");
			}
		}
		return input;
	}

	void asgnVar(String input) {
		// TODO Auto-generated method stub
		input = removeSpaces(input);
		String[] asgnArray = input.split("<-");
		if (hashTable.containsKey(asgnArray[1])) {
			hashTable.put(asgnArray[0], hashTable.get(asgnArray[1]));
		} else {
			hashTable.put(asgnArray[0], asgnArray[1]);
		}
		
	}

	boolean isEquals(String input, String match) {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile(match, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(input);
		if (m.matches()) {
			return true;
		}
		return false;
	}

	public void writeToConsole(/* String fName */) {
		try {
			int numSecParam = Double.valueOf(hashTable.get("nSecon")).intValue();
			String[] secParam = hashTable.get("SeconNames").split(",");
			for (int i = 0; i < numSecParam; i++) {
				String str = "";
				if (hashTable.get(secParam[i]) != null) {
				}
			}
			String str = String.format("%-20s%-20f\n", "Y", Double
					.valueOf(hashTable.get("f")));
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
		}
	}
	
	public void evalComment() throws IOException{
		while (!isEquals(strLine, "COMMENTEND") 
				&& j < strArray.size()) {
			strLine = strArray.get(j);
			strLine = removeSpaces(strLine);
			j += 1;
		}
	}

	public void evalTempInitStart() throws IOException {
		
		strLine = strArray.get(j);
		strLine = removeSpaces(strLine);
		j += 1;
		//if the line in block starts with for then evaluate For condition
		if(strLine.startsWith("for")){
			evalFor();
		} else if(strLine.startsWith("if")){ // if the line starts with if then evaluate If condition
			evalIf();
		} else { // if the line does not start with either If or For then enter this condition
			while (((strLine.contains("=") || strLine.contains("<-") || strLine
					.length() == 0) && j < strArray.size())) {
				if (strLine.contains("<-")) {
					asgnVar(strLine);
				} else if (strLine.contains("=")) {
					exprVar(strLine, false);
				}
				strLine = strArray.get(j);
				strLine = removeSpaces(strLine);
				j += 1;
			}
			if (j == size && strArray.get(j - 1).contains("=")) {
				strLine = strArray.get(j - 1);
				strLine = removeSpaces(strLine);
				exprVar(strLine, false);
			}
		}
	}
	public void evalFor() throws IOException {
		String[] array = strLine.split("=");
		String idx = array[0].substring(3);
		String[] bounds = array[1].split("to");
		int initVal = Double.valueOf(bounds[0]).intValue();
		hashTable.put(idx, Integer.toString(initVal));
		int finalVal = Double.valueOf(hashTable.get(bounds[1])).intValue();
		ArrayList<String> forBody = new ArrayList<String>();
		strLine = strArray.get(j);
		strLine = removeSpaces(strLine);
		j += 1;
		while (j < size && !strLine.equals("repeat")) {
			forBody.add(strLine);
			strLine = strArray.get(j);
			strLine = removeSpaces(strLine);
			j += 1;
		}
		for (int ite = initVal; ite <= finalVal; ite++) {
			forImplementation(forBody);
			if (gotoFlag == true) {
				gotoFlag = false;
				break;
			}
			hashTable.put(idx, Integer.toString(ite + 1));
		}
	}

	public void evalIf() throws IOException {
		ArrayList<String> condBody = new ArrayList<String>();
		ArrayList<String> elseBody = new ArrayList<String>();
		condBody.add(strLine);
		strLine = strArray.get(j);
		while (!isEquals(strLine, "endif")
				&& !isEquals(strLine, "else")) {
			j += 1;
			condBody.add(strLine);
			strLine = strArray.get(j);
		}
		if (strArray.contains("else") && strLine.startsWith("else")) {
			j += 1;
			strLine = strArray.get(j);
			while (!isEquals(strLine, "endif")) {
				elseBody.add(strLine);
				j += 1;
				strLine = strArray.get(j);
			}
		}
		conditionImplementation(condBody);
		if (gotoFlag == true) {
			return;
		}
		if (condFlag == false) {
			condFlag = true;
			executeCondition(elseBody);
			if (gotoFlag == true) {
				return;
			}
		}
	
		
	}

	@Override
	public double[] function(double x, double[] C0) {
		
		ApplicationInfo appInfo = ListOfDataStructures.createListOfDataStructInstance().appInfo;
		int numDiffEqn = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().getProcessingInputs().getAsciiCommands().getnDer();
		
		//replace the x value in the hash table
		hashTable.put("x", x+"");
		
		
		// replace all the start variables in the hash table
		for(int i=0;i< C0.length;i++){
			String key = "z"+(i+1);
			hashTable.put(key, C0[i]+"");
		}
		

		double[] values = new double[numDiffEqn];
		
		//evaluate the differential equations using expression evaluator
		for(int i=0;i<diffEquations.size();i++){
			values[i] = exprVar(diffEquations.get(i), true);
		}
		
		
		
		
/*		for(int i=0;i<numDiffEqn;i++){
			String key = "dz"+(i+1);
			values[i] = Double.parseDouble(hashTable.get(key));
			//System.out.println(x +"\t"+values[i]);
		}*/
		
		
		
		
		return values;
	}
	
	

}
