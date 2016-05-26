package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import view.ApplicationInfo;


public class ProcessingInputsInfo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7751975220735955301L;
	private MappIngData mappingDataObj;
	private DosingData dosingDataObj;
	private lambdazData lambdazDataobj;
	private ParameterUnitsData parameterUnitsDataObj;
	private SubAreasData subAreasDataObj;
	private ParameterNamesData parameterNamesObj;
	private ModelInputs modelInputsObj;
	private ModleInputForNPS npsModelInputObj;
	private TableMavenInfo tmProcessingInputs;
	private ProfileAndParamInfo profileAndParamInfoObj; 
	private InitialParameterValue initialParameterValueObj;
	private ModelInputTab1 modelInputTab1Obj;
	private ModelInputTab2 modelInputTab2Obj;
	private ResultDisplayTablesHeader resultDisplayTabllsHeaderObj;
	private double lloqValue;
	private String[][] bqlDat;
	private String[][] plotDat;
	private ProgressBarInfo progressBarInfoObj; 
	private int ttSelectedFuncIndex;
	private int ttSelectedBaseFuncIndex;
	private String[][] ttData;
	private double ttnValue;
	private boolean ttIsLowestInProfile;
	private String[][] datForSparse;
	private InVitroModelInput inVitroModelInputInst;
	
	public boolean isTtIsLowestInProfile() {
		return ttIsLowestInProfile;
	}
	
		public ProgressBarInfo getProgressBarInfoObj() {
		return progressBarInfoObj;
	}


	public void setProgressBarInfoObj(ProgressBarInfo progressBarInfoObj) {
		this.progressBarInfoObj = progressBarInfoObj;
	}



	public void setTtIsLowestInProfile(boolean ttIsLowestInProfile) {
		this.ttIsLowestInProfile = ttIsLowestInProfile;
	}


	public boolean isTtisCustom() {
		return ttisCustom;
	}


	public void setTtisCustom(boolean ttisCustom) {
		this.ttisCustom = ttisCustom;
	}


	public double getTtCostomPoint() {
		return ttCostomPoint;
	}


	public void setTtCostomPoint(double ttCostomPoint) {
		this.ttCostomPoint = ttCostomPoint;
	}


	private boolean ttisCustom;
	private double ttCostomPoint;
	
	public double getTtnValue() {
		return ttnValue;
	}


	public void setTtnValue(double ttnValue) {
		this.ttnValue = ttnValue;
	}


	public String[][] getTtData() {
		return ttData;
	}


	public void setTtData(String[][] ttData) {
		this.ttData = ttData;
	}


	public int getTtSelectedBaseFuncIndex() {
		return ttSelectedBaseFuncIndex;
	}


	public void setTtSelectedBaseFuncIndex(int ttSelectedBaseFuncIndex) {
		this.ttSelectedBaseFuncIndex = ttSelectedBaseFuncIndex;
	}


	private int ttSlectedArithmeticFuncIndex;
	private int ttTypeOfFunc;
	
	
	public int getTtTypeOfFunc() {
		return ttTypeOfFunc;
	}


	public void setTtTypeOfFunc(int ttTypeOfFunc) {
		this.ttTypeOfFunc = ttTypeOfFunc;
	}


	public int getTtSlectedArithmeticFuncIndex() {
		return ttSlectedArithmeticFuncIndex;
	}


	public void setTtSlectedArithmeticFuncIndex(int ttSlectedArithmeticFuncIndex) {
		this.ttSlectedArithmeticFuncIndex = ttSlectedArithmeticFuncIndex;
	}


	public int getTtSelectedFuncIndex() {
		return ttSelectedFuncIndex;
	}


	public void setTtSelectedFuncIndex(int ttSelectedFuncIndex) {
		this.ttSelectedFuncIndex = ttSelectedFuncIndex;
	}


	public String[][] getPlotDat() {
		return plotDat;
	}


	public void setPlotDat(String[][] plotDat) {
		this.plotDat = plotDat;
	}


	private String[][] ncaDat;
	private ArrayList<String> bqlSortedColumnNames;
	
	private ExportToPdfInfo expToPdf;
	
	


	public ExportToPdfInfo getExpToPdf() {
		return expToPdf;
	}


	public void setExpToPdf(ExportToPdfInfo expToPdf) {
		this.expToPdf = expToPdf;
	}


	public ArrayList<String> getBqlSortedColumnNames() {
		return bqlSortedColumnNames;
	}


	public void setBqlSortedColumnNames(ArrayList<String> bqlSortedColumnNames) {
		this.bqlSortedColumnNames = bqlSortedColumnNames;
	}


	public String[][] getBqlDat() {
		return bqlDat;
	}


	public void setBqlDat(String[][] bqlDat) {
		this.bqlDat = bqlDat;
	}


	public double getLloqValue() {
		return lloqValue;
	}


	public void setLloqValue(double lloqValue) {
		this.lloqValue = lloqValue;
	}


	public ModelInputTab2 getModelInputTab2Obj() {
		return modelInputTab2Obj;
	}


	public void setModelInputTab2Obj(ModelInputTab2 modelInputTab2Obj) {
		this.modelInputTab2Obj = modelInputTab2Obj;
	}
	
	public ModelInputTab1 getModelInputTab1Obj() {
		return modelInputTab1Obj;
	}


	public void setModelInputTab1Obj(ModelInputTab1 modelInputTab1Obj) {
		this.modelInputTab1Obj = modelInputTab1Obj;
	}
	
	
	public InitialParameterValue getInitialParameterValueObj() {
		return initialParameterValueObj;
	}


	public void setInitialParameterValueObj(
			InitialParameterValue initialParameterValueObj) {
		this.initialParameterValueObj = initialParameterValueObj;
	}

	
	
	public TableMavenInfo getTmProcessingInputs() {
		return tmProcessingInputs;
	}


	public void setTmProcessingInputs(TableMavenInfo tmProcessingInputs) {
		this.tmProcessingInputs = tmProcessingInputs;
	}


	public ProfileInformation getProfileInfoObj() {
		return profileInfoObj;
	}


	public void setProfileInfoObj(ProfileInformation profileInfoObj) {
		this.profileInfoObj = profileInfoObj;
	}


	private ProfileInformation profileInfoObj;

	
	
	
	public ModleInputForNPS getModleInputForNPSObj() {
		return npsModelInputObj;
	}


	public void setModelInputObj(ModleInputForNPS modelInputObj) {
		this.npsModelInputObj = modelInputObj;
	}

	public ProfileAndParamInfo getProfileAndParamInfoObj() {
		return profileAndParamInfoObj;
	}


	public void setProfileAndParamInfoObj(ProfileAndParamInfo profileAndParamInfoObj) {
		this.profileAndParamInfoObj = profileAndParamInfoObj;
	}

	
	public String[][] getDatForSparse() {
		return datForSparse;
	}

	public void setDatForSparse(String[][] datForSparse) {
		this.datForSparse = datForSparse;
	}

	@Override
	public String toString() {
		String consoleS = "Printing processing inputs\n";
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String s =  "ProcessingInputs \n" + dosingDataObj+
				"\n"+ lambdazDataobj +"\n"+
				"\n"+ mappingDataObj +"\n"+ modelInputsObj+
				"\n"+parameterNamesObj+
				"\n"+  parameterUnitsDataObj+
				"\n"+  subAreasDataObj ;
		
		//p.append(s);
		return s;
	}

	public ModleInputForNPS getNpsModelInputObj() {
		return npsModelInputObj;
	}

	public void setNpsModelInputObj(ModleInputForNPS npsModelInputObj) {
		this.npsModelInputObj = npsModelInputObj;
	}

	public AsciiSelectionInfo getAsciiCommands() {
		return asciiCommands;
	}

	public void setAsciiCommands(AsciiSelectionInfo asciiCommands) {
		this.asciiCommands = asciiCommands;
	}

	public AsciiParametersInfo getAsciiParameters() {
		return asciiParameters;
	}

	public void setAsciiParameters(AsciiParametersInfo asciiParameters) {
		this.asciiParameters = asciiParameters;
	}

	public AsciiDeclareVariablesInfo getAsciiTempVar() {
		return asciiTempVar;
	}

	public void setAsciiTempVar(AsciiDeclareVariablesInfo asciiTempVar) {
		this.asciiTempVar = asciiTempVar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	private AsciiSelectionInfo asciiCommands;
	private AsciiParametersInfo asciiParameters;
	private AsciiDeclareVariablesInfo asciiTempVar;
	private ArrayList<AsciiProfInfo> asciiProfinfoAL;
	private int asciiprofSelected;
	private AsciiNonEditTextInfo asciiNonEditText;
	private AsciiInitialEstimates asciiInitEstimates;

	

	public AsciiInitialEstimates getAsciiInitEstimates() {
		return asciiInitEstimates;
	}

	public void setAsciiInitEstimates(AsciiInitialEstimates asciiInitEstimates) {
		this.asciiInitEstimates = asciiInitEstimates;
	}

	public AsciiNonEditTextInfo getAsciiNonEditText() {
		return asciiNonEditText;
	}

	public void setAsciiNonEditText(AsciiNonEditTextInfo asciiNonEditText) {
		this.asciiNonEditText = asciiNonEditText;
	}

	public int getAsciiprofSelected() {
		return asciiprofSelected;
	}

	public void setAsciiprofSelected(int asciiprofSelected) {
		this.asciiprofSelected = asciiprofSelected;
	}

	public ProcessingInputsInfo(){
		mappingDataObj = new MappIngData();
		dosingDataObj = new DosingData();
		lambdazDataobj = new lambdazData();
		parameterNamesObj = new ParameterNamesData();
		parameterUnitsDataObj = new ParameterUnitsData();
		subAreasDataObj = new SubAreasData();
		modelInputsObj = new ModelInputs();
		npsModelInputObj = new ModleInputForNPS();
		profileInfoObj = new ProfileInformation();
		tmProcessingInputs = new TableMavenInfo();
		modelInputTab1Obj = new ModelInputTab1();
		modelInputTab2Obj = new ModelInputTab2();
		initialParameterValueObj = new InitialParameterValue();
		profileAndParamInfoObj = new ProfileAndParamInfo();
		resultDisplayTabllsHeaderObj = new ResultDisplayTablesHeader();
		progressBarInfoObj = new ProgressBarInfo();
		inVitroModelInputInst = new InVitroModelInput();
		lloqValue = 0;
		bqlDat = new String[1][1];
		ncaDat =  new String[1][1];
		plotDat = new String[1][1];
		bqlSortedColumnNames = new ArrayList<String>();
		expToPdf = new ExportToPdfInfo();
		ttSelectedFuncIndex = 0;
		ttSelectedBaseFuncIndex = 0;
		ttSlectedArithmeticFuncIndex = 0;
		ttTypeOfFunc = 1;//arithmetic is 1, base line is 2, function is 0
		ttData = new String[1][1];
		ttnValue = 0;
		ttIsLowestInProfile = true;
		ttisCustom = false;
		ttCostomPoint = 0;
		datForSparse = new String[1][1];
		
		//ascii related objects
		asciiCommands = new AsciiSelectionInfo();
		asciiParameters = new AsciiParametersInfo();
		asciiTempVar = new AsciiDeclareVariablesInfo();
		asciiProfinfoAL = new ArrayList<AsciiProfInfo>();
		asciiprofSelected = 0;
		asciiNonEditText = new AsciiNonEditTextInfo();
		asciiInitEstimates = new AsciiInitialEstimates();
	}

	public InVitroModelInput getInVitroModelInputInst() {
		return inVitroModelInputInst;
	}

	public void setInVitroModelInputInst(InVitroModelInput inVitroModelInputInst) {
		this.inVitroModelInputInst = inVitroModelInputInst;
	}

	String[] convert(ArrayList<String> al){
		String[] stral = new String[al.size()];
		for(int i=0;i<stral.length;i++){
			stral[i] = al.get(i);
		}
		return stral;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessingInputsInfo other = (ProcessingInputsInfo) obj;
		if (!Arrays.deepEquals(bqlDat, other.bqlDat))
			return false;
		if (bqlSortedColumnNames == null) {
			if (other.bqlSortedColumnNames != null)
				return false;
		} else if (!Arrays.deepEquals(convert(bqlSortedColumnNames), convert(other.bqlSortedColumnNames)))
			return false;
		if (dosingDataObj == null) {
			if (other.dosingDataObj != null)
				return false;
		} else if (!dosingDataObj.equals(other.dosingDataObj))
			return false;
		if (expToPdf == null) {
			if (other.expToPdf != null)
				return false;
		} else if (!expToPdf.equals(other.expToPdf))
			return false;
		if (ifSheetIsSelected != other.ifSheetIsSelected)
			return false;
		if (initialParameterValueObj == null) {
			if (other.initialParameterValueObj != null)
				return false;
		} else if (!initialParameterValueObj
				.equals(other.initialParameterValueObj))
			return false;
		if (lambdazDataobj == null) {
			if (other.lambdazDataobj != null)
				return false;
		} else if (!lambdazDataobj.equals(other.lambdazDataobj))
			return false;
		if (Double.doubleToLongBits(lloqValue) != Double
				.doubleToLongBits(other.lloqValue))
			return false;
		if (mappingDataObj == null) {
			if (other.mappingDataObj != null)
				return false;
		} else if (!mappingDataObj.equals(other.mappingDataObj))
			return false;
		if (modelInputTab1Obj == null) {
			if (other.modelInputTab1Obj != null)
				return false;
		} else if (!modelInputTab1Obj.equals(other.modelInputTab1Obj))
			return false;
		if (modelInputTab2Obj == null) {
			if (other.modelInputTab2Obj != null)
				return false;
		} else if (!modelInputTab2Obj.equals(other.modelInputTab2Obj))
			return false;
		if (modelInputsObj == null) {
			if (other.modelInputsObj != null)
				return false;
		} else if (!modelInputsObj.equals(other.modelInputsObj))
			return false;
		if (ncaDat == null) {
			if (other.ncaDat != null)
				return false;
		}
		else if (!Arrays.deepEquals(ncaDat, other.ncaDat))
			return false;
		if (npsModelInputObj == null) {
			if (other.npsModelInputObj != null)
				return false;
		} else if (!npsModelInputObj.equals(other.npsModelInputObj))
			return false;
		if (parameterNamesObj == null) {
			if (other.parameterNamesObj != null)
				return false;
		} else if (!parameterNamesObj.equals(other.parameterNamesObj))
			return false;
		if (parameterUnitsDataObj == null) {
			if (other.parameterUnitsDataObj != null)
				return false;
		} else if (!parameterUnitsDataObj.equals(other.parameterUnitsDataObj))
			return false;
		
		if (plotDat == null) {
			if (other.plotDat != null)
				return false;
		} else if (!Arrays.deepEquals(plotDat, other.plotDat))
			return false;
		if (profileAndParamInfoObj == null) {
			if (other.profileAndParamInfoObj != null)
				return false;
		} else if (!profileAndParamInfoObj.equals(other.profileAndParamInfoObj))
			return false;
		if (profileInfoObj == null) {
			if (other.profileInfoObj != null)
				return false;
		} else if (!profileInfoObj.equals(other.profileInfoObj))
			return false;
		if (progressBarInfoObj == null) {
			if (other.progressBarInfoObj != null)
				return false;
		} else if (!progressBarInfoObj.equals(other.progressBarInfoObj))
			return false;
		if (resultDisplayTabllsHeaderObj == null) {
			if (other.resultDisplayTabllsHeaderObj != null)
				return false;
		} else if (!resultDisplayTabllsHeaderObj
				.equals(other.resultDisplayTabllsHeaderObj))
			return false;
		if (subAreasDataObj == null) {
			if (other.subAreasDataObj != null)
				return false;
		} else if (!subAreasDataObj.equals(other.subAreasDataObj))
			return false;
		if (Double.doubleToLongBits(ttCostomPoint) != Double
				.doubleToLongBits(other.ttCostomPoint))
			return false;
		if (!Arrays.deepEquals(ttData, other.ttData))
			return false;
		if (ttIsLowestInProfile != other.ttIsLowestInProfile)
			return false;
		if (ttSelectedBaseFuncIndex != other.ttSelectedBaseFuncIndex)
			return false;
		if (ttSelectedFuncIndex != other.ttSelectedFuncIndex)
			return false;
		if (ttSlectedArithmeticFuncIndex != other.ttSlectedArithmeticFuncIndex)
			return false;
		if (ttTypeOfFunc != other.ttTypeOfFunc)
			return false;
		if (ttisCustom != other.ttisCustom)
			return false;
		if (Double.doubleToLongBits(ttnValue) != Double
				.doubleToLongBits(other.ttnValue))
			return false;
		return true;
	}

	public String[][] getNcaDat() {
		return ncaDat;
	}


	public void setNcaDat(String[][] ncaDat) {
		this.ncaDat = ncaDat;
	}


	public ResultDisplayTablesHeader getResultDisplayTabllsHeaderObj() {
		return resultDisplayTabllsHeaderObj;
	}


	public void setResultDisplayTabllsHeaderObj(
			ResultDisplayTablesHeader resultDisplayTabllsHeaderObj) {
		this.resultDisplayTabllsHeaderObj = resultDisplayTabllsHeaderObj;
	}
	
boolean ifSheetIsSelected = false;
	

	
	public boolean isIfSheetIsSelected() {
		return ifSheetIsSelected;
	}


	public void setIfSheetIsSelected(boolean ifSheetIsSelected) {
		this.ifSheetIsSelected = ifSheetIsSelected;
	}







	public MappIngData getMappingDataObj() {
		return mappingDataObj;
	}


	public void setMappingDataObj(MappIngData mappingDataObj) {
		this.mappingDataObj = mappingDataObj;
	}


	public DosingData getDosingDataObj() {
		return dosingDataObj;
	}


	public void setDosingDataObj(DosingData dosingDataObj) {
		this.dosingDataObj = dosingDataObj;
	}


	public lambdazData getLambdazDataobj() {
		return lambdazDataobj;
	}


	public void setLambdazDataobj(lambdazData lambdazDataobj) {
		this.lambdazDataobj = lambdazDataobj;
	}


	public ParameterUnitsData getParameterUnitsDataObj() {
		return parameterUnitsDataObj;
	}


	public void setParameterUnitsDataObj(ParameterUnitsData parameterUnitsDataObj) {
		this.parameterUnitsDataObj = parameterUnitsDataObj;
	}


	public SubAreasData getSubAreasDataObj() {
		return subAreasDataObj;
	}


	public void setSubAreasDataObj(SubAreasData subAreasDataObj) {
		this.subAreasDataObj = subAreasDataObj;
	}


	public ParameterNamesData getParameterNamesObj() {
		return parameterNamesObj;
	}


	public void setParameterNamesObj(ParameterNamesData parameterNamesObj) {
		this.parameterNamesObj = parameterNamesObj;
	}


	public ModelInputs getModelInputsObj() {
		return modelInputsObj;
	}


	public void setModelInputsObj(ModelInputs modelInputsObj) {
		this.modelInputsObj = modelInputsObj;
	}


	public ArrayList<AsciiProfInfo> getAsciiProfinfoAL() {
		return asciiProfinfoAL;
	}

	public void setAsciiProfinfoAL(ArrayList<AsciiProfInfo> asciiProfinfoAL) {
		this.asciiProfinfoAL = asciiProfinfoAL;
	}


}
