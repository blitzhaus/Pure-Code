package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

import Common.Comparator;
import Common.MyComparator;


public class WorkSheetsInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2979125558638245554L;
	private String sheetName;
	private boolean hasHeader;
	private int startRow;
	private int missingValue;
	private  int columnNumberClicked;
	boolean isFromDescriptiveStatMainPage = false;
	String analysisType;

	boolean useThousandsSeperator;// this is a check box in the gui
	
	

	public String getAnalysisType() {
		return analysisType;
	}
	public void setAnalysisType(String analysisType) {
		this.analysisType = analysisType;
	}
	public boolean isFromDescriptiveStatMainPage() {
		return isFromDescriptiveStatMainPage;
	}
	public void setFromDescriptiveStatMainPage(boolean isFromDescriptiveStatMainPage) {
		this.isFromDescriptiveStatMainPage = isFromDescriptiveStatMainPage;
	}

	private ArrayList<ColumnProperties> tempCpObj;
	
	public ArrayList<ColumnProperties> getColumnPropertiesArrayList() {
		return tempCpObj;
	}
	public void setColumnPropertiesArrayList(ArrayList<ColumnProperties> tempCpObj) {
		this.tempCpObj = tempCpObj;
	}
	
	private BQLInfo bqlInfo;
	
	
	public BQLInfo getBqlInfo() {
		return bqlInfo;
	}
	public void setBqlInfo(BQLInfo bqlInfo) {
		this.bqlInfo = bqlInfo;
	}


	private TableMavenInfo tmInfo;

	public TableMavenInfo getTmInfo() {
		return tmInfo;
	}
	public void setTmInfo(TableMavenInfo tmInfo) {
		this.tmInfo = tmInfo;
	}

	private TableTransformationsInfo ttInfo;

	public TableTransformationsInfo getTtInfo() {
		return ttInfo;
	}
	public void setTtInfo(TableTransformationsInfo ttInfo) {
		this.ttInfo = ttInfo;
	}

	private NcaInfo ncaInfo;
	
	public NcaInfo getNcaInfo() {
		return ncaInfo;
	}
	public void setNcaInfo(NcaInfo ncaInfo) {
		this.ncaInfo = ncaInfo;
	}

	private DstatsInfo desStatsInfo;
	

	public DstatsInfo getDesStatsInfo() {
		return desStatsInfo;
	}


	public void setDesStatsInfo(DstatsInfo desStatsInfo) {
		this.desStatsInfo = desStatsInfo;
	}
	
	private NPSInfo npsInfo;
	
	private ScaInfo scaInfo;
	
	
	private PlotmavenInfo plotInfo;

	public PlotmavenInfo getPlotInfo() {
		return plotInfo;
	}
	public void setPlotInfo(PlotmavenInfo plotInfo) {
		this.plotInfo = plotInfo;
	}
	public NPSInfo getNpsInfo() {
		return npsInfo;
	}


	public void setNpsInfo(NPSInfo npsInfo) {
		this.npsInfo = npsInfo;
	}


	public ScaInfo getScaInfo() {
		return scaInfo;
	}


	public void setScaInfo(ScaInfo spcInfo) {
		this.scaInfo = spcInfo;
	}
	private PkAnalysisInfo pkInfo;
	
	public PkAnalysisInfo getPkInfo() {
		return pkInfo;
	}
	public void setCaInfo(PkAnalysisInfo caInfo) {
		this.pkInfo = caInfo;
	}
	
	

	@Override
	public String toString() {
		
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String consoleS = "Printing work sheet information \n";
		String s  = "\n\nNEW SHEET******************************\tsheet name = "+ sheetName+"\n";
		s = s + "\thas header = "+ hasHeader+"\n";
		s =s + "\tstart row = "+startRow+"\n";
		s =s + "\tmissing Value = "+missingValue+"\n";
		s =s + "\tcolumnNumberClicked = "+columnNumberClicked+"\n\n";
		s =s + "";
		
		s = s + "START OF NCA \n "+ncaInfo+"\n\n\n ..END OF NCA\n";
		s = s + "START OF DESCRIPTIVE STATS  \n"+desStatsInfo+"\n\n\n.. END OF DESCRIPTIVE STATS\n";
		s = s + "START OF NON PARAMETRIC SUPERPOSITION\n"+npsInfo+"END OF NON PARAMETRIC SUPERPOSITION\n";
		s = s + "START OF SEMI COMPARTMENTAL ANALYSIS\n"+scaInfo+"END OF SEMI COMPARTMENTAL ANALYSIS \n\n";
		s = s + "START OF PLOT MAVEN INFORMATION\n"+plotInfo+"END OF PLOT MAVEN INFORMATION\n";
		
		//p.append(s);
		return s;
	}


	public PDAnalysisInfo getPdInfo() {
		return pdInfo;
	}
	public void setPdInfo(PDAnalysisInfo pdInfo) {
		this.pdInfo = pdInfo;
	}
	public MichaelisMentenAnalysisInfo getMmInfo() {
		return mmInfo;
	}
	public void setMmInfo(MichaelisMentenAnalysisInfo mmInfo) {
		this.mmInfo = mmInfo;
	}
	public PkPdLinkModelAnalysisInfo getPkpdLinkInfo() {
		return pkpdLinkInfo;
	}
	public void setPkpdLinkInfo(PkPdLinkModelAnalysisInfo pkpdLinkInfo) {
		this.pkpdLinkInfo = pkpdLinkInfo;
	}
	public IndirectResponseModelAnalysisInfo getIrInfo() {
		return irInfo;
	}
	public void setIrInfo(IndirectResponseModelAnalysisInfo irInfo) {
		this.irInfo = irInfo;
	}



	private PDAnalysisInfo pdInfo;
	private MichaelisMentenAnalysisInfo mmInfo;
	private PkPdLinkModelAnalysisInfo pkpdLinkInfo;
	private IndirectResponseModelAnalysisInfo irInfo;
	private AsciiModelInfo asciiInfo;
	
	private InVitroModelInfo inVitroInfo;

	
	

	public InVitroModelInfo getInVitroInfo() {
		return inVitroInfo;
	}
	public void setInVitroInfo(InVitroModelInfo inVitroInfo) {
		this.inVitroInfo = inVitroInfo;
	}
	public AsciiModelInfo getAsciiInfo() {
		return asciiInfo;
	}
	public void setAsciiInfo(AsciiModelInfo asciiInfo) {
		this.asciiInfo = asciiInfo;
	}
	public WorkSheetsInfo(){
		
		sheetName = new String();
		hasHeader = true;
		startRow = 0;
	analysisType = "";
		columnNumberClicked = 0;
	ncaInfo = new NcaInfo();
	desStatsInfo = new DstatsInfo();
	npsInfo = new NPSInfo();
	scaInfo = new ScaInfo();
	tempCpObj = new ArrayList<ColumnProperties>();
	plotInfo = new PlotmavenInfo();
	tmInfo = new TableMavenInfo();
	pkInfo = new PkAnalysisInfo();
	bqlInfo = new BQLInfo();

	useThousandsSeperator = false;
	
	pdInfo = new PDAnalysisInfo();
	mmInfo = new MichaelisMentenAnalysisInfo();
	pkpdLinkInfo = new PkPdLinkModelAnalysisInfo();
	irInfo = new IndirectResponseModelAnalysisInfo();
	asciiInfo = new AsciiModelInfo();
	
	ttInfo = new TableTransformationsInfo();
	inVitroInfo = new InVitroModelInfo();
		
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		Comparator comp = MyComparator.createMyCompInst();
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkSheetsInfo other = (WorkSheetsInfo) obj;
		if (analysisType == null) {
			if (other.analysisType != null)
				return false;
		} else if (!analysisType.equals(other.analysisType))
			return false;
		if (bqlInfo == null) {
			if (other.bqlInfo != null)
				return false;
		} else if (!bqlInfo.equals(other.bqlInfo))
			return false;
		if (pkInfo == null) {
			if (other.pkInfo != null)
				return false;
		} else if (!pkInfo.equals(other.pkInfo))
			return false;
		if (columnNumberClicked != other.columnNumberClicked)
			return false;
		if (desStatsInfo == null) {
			if (other.desStatsInfo != null)
				return false;
		} else if (!desStatsInfo.equals(other.desStatsInfo))
			return false;
		if (hasHeader != other.hasHeader)
			return false;
		if (irInfo == null) {
			if (other.irInfo != null)
				return false;
		} else if (!irInfo.equals(other.irInfo))
			return false;
		if (isFromDescriptiveStatMainPage != other.isFromDescriptiveStatMainPage)
			return false;
		if (missingValue != other.missingValue)
			return false;
		if (mmInfo == null) {
			if (other.mmInfo != null)
				return false;
		} else if (!mmInfo.equals(other.mmInfo))
			return false;
		if (ncaInfo == null) {
			if (other.ncaInfo != null)
				return false;
		} else if (!ncaInfo.equals(other.ncaInfo))
			return false;
		if (npsInfo == null) {
			if (other.npsInfo != null)
				return false;
		} else if (!npsInfo.equals(other.npsInfo))
			return false;
		/*if (optionalDecimals != other.optionalDecimals)
			return false;*/
		if (pdInfo == null) {
			if (other.pdInfo != null)
				return false;
		} else if (!pdInfo.equals(other.pdInfo))
			return false;
		if (pkpdLinkInfo == null) {
			if (other.pkpdLinkInfo != null)
				return false;
		} else if (!pkpdLinkInfo.equals(other.pkpdLinkInfo))
			return false;
		if (plotInfo == null) {
			if (other.plotInfo != null)
				return false;
		} else if (!plotInfo.equals(other.plotInfo))
			return false;
/*		if (requiredDecimals == null) {
			if (other.requiredDecimals != null)
				return false;
		} else if (!requiredDecimals.equals(other.requiredDecimals))
			return false;*/
		if (scaInfo == null) {
			if (other.scaInfo != null)
				return false;
		} else if (!scaInfo.equals(other.scaInfo))
			return false;
		if (sheetName == null) {
			if (other.sheetName != null)
				return false;
		} else if (!sheetName.equals(other.sheetName))
			return false;
		if (startRow != other.startRow)
			return false;
		if (tempCpObj == null) {
			if (other.tempCpObj != null)
				return false;
		} else if (!comp.compare(tempCpObj,(other.tempCpObj)))
			return false;
		if (tmInfo == null) {
			if (other.tmInfo != null)
				return false;
		} else if (!tmInfo.equals(other.tmInfo))
			return false;
		if (ttInfo == null) {
			if (other.ttInfo != null)
				return false;
		} else if (!ttInfo.equals(other.ttInfo))
			return false;
		if (useThousandsSeperator != other.useThousandsSeperator)
			return false;
		return true;
	}
	public boolean isUseThousandSeperatorClicked() {
		return useThousandsSeperator;
	}
	public void setUseThousandSeperatorClicked(boolean useThresholdSeperatorClicked) {
		this.useThousandsSeperator = useThresholdSeperatorClicked;
	}
	public int getColumnNumberClicked() {
		return columnNumberClicked;
	}


	public void setColumnNumberClicked(int columnNumberClicked) {
		this.columnNumberClicked = columnNumberClicked;
	}
	 
	

	public String getSheetName() {
		return sheetName;
	}


	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}


	public boolean getHasHeader() {
		return hasHeader;
	}


	public void setHasHeader(boolean hasHeader) {
		this.hasHeader = hasHeader;
	}


	public int getStartRow() {
		return startRow;
	}


	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}


	public int getMissingValue() {
		return missingValue;
	}


	public void setMissingValue(int missingValue) {
		this.missingValue = missingValue;
	}
	
	
	

}
