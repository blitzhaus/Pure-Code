package Common;

public interface EventCodes {

	/*
	 * All action events - Event Codes.
	 * 
	 */
	
	public final static String CUT   = "cut";
	public final static String COPY  = "copy";
	public final static String PASTE = "paste";
	public final static String UNDO   = "undo";
	public final static String REDO  = "redo";
	public final static String DELETE = "delete";
	public final static String FILLDOWN = "fill down ";
	public final static String NEW = "new";
	public final static String LOAD = "LOADPROJECT";
	public final static String OPEN = "open";
	public final static String SAVE = "save";
	public final static String SAVEAS = "save as";
	public final static String CLOSE = "close";
	public final static String SAVELOG = "save log";
	public final static String PRINT = "print";
	public final static String EXIT = "exit";
	public final static String EDIT = "edit";
	public final static String SELECTALL = "selectall";
	public final static String OPENPROJECT = "openproject";
	public final static String IMPORTPROJECT = "importproject";
	public final static String IMPORTDATA = "importdata";
	public final static String SORT = "sort";
	public final static String JOIN = "join";
	public final static String MERGE = "merge";
	public final static String STATUSCODES = "statuscodes";
	public final static String DESCSTATS = "descstats";
	public final static String NONPARAMSUPER = "nonparamsuper";
	public final static String SEMICOMPMODELING = "semicompmodeling";
	public final static String CHARTWIZARD = "chartwizard";
	public final static String EXECUTE = "execute the analysis on board";
	public final static String PREVIEW = "preview the on board analysis sheet";
	public final static String NEWPROJECT = "New project creation";
	public final static String NEWSHEET = "New sheet creation";
	public final static String TABTRANS = "table transformations";
	public final static String ADDROW = "new sheet add row to the table";
	public final static String ADDCOLUMN = "new sheet add column to the table";
	public final static String NUMBEROFROWS = "new sheet number of rows";
	public final static String NUMBEROFCOLUMNS = "new sheet number of columns";
	public final static String NEWSHEETOK = "new sheet ok button";
	public final static String NEWSHEETCANCEL = "new sheet cancel button";
	public final static String BASELINEFUNC = " Base line func ";
	public final static String TABTRANSAJITH = "Table transformations buy ajith";
	public final static String TTAVAILANDY = "table transfermations available to y button";
	public final static String TTAVAILANDX = "table transfermations available to x button";
	public final static String TTAVAILAANDSORT = "table transfermations available to sort button";

	
	
	// Display contents page event codes
	public final static String FINISHANDOPEN = "finishAndOpen";
	public final static String IMPCANCEL = "imported data sheet cancel button";
	public final static String HASHEADERROWCHECK = "check if header row is present";
	public final static String MISSINGVALUETEXT = "missing value text";

	//Imported data sheet page event codes
	public final static String IMPSHEETNUMERICRADIO = "column properties numeric radio button";
	public final static String IMPSHEETOPTDECIMALRADIO = "columns properties optional decimals radio button";
	public final static String DATATYPECHANGE = "column's data type change button";
	public final static String UNITBUILDERBUTTON = "unit builder button";
	public final static String OPTIONALDECRADIO = "optional decimals radio button imported data sheet";
	
	//unit builder frame event codes
	public final static String UBCANCEL = "unit builder cancel button";
	public final static String UBOK = "unit builder oj button";
	public final static String UBVOLADD = "unit builder volume add button";
	public final static String UBMASSADD = "unit builder mass add button";
	public final static String UBDIVOPERATOR = "unit builder division operator add button";
	public final static String UBMULOPERATOR = "unit builder multiplication operator button";
	public final static String UBEXPOPERATOR = "unit builder exponential operator button";
	public final static String UBTIMEADD = "unti builder time button add";
	public final static String UBCLEAR = "unit builder clear units button";
	public final static String UBDOSEUNIT = "unit builder dose  for nca";
	
	//available analysis frame event codes
	public final static String ENTERNCA = "enter nca";
	public final static String ENTERPK = "enter PK";
	public final static String ENTERPD = "enter PD";
	public final static String ENTERLME = "enter LME";
	public final static String ENTERNLME = "enter NLME";
	public final static String ENTERDESSTATS = "enter descriptive stats";
	public final static String ENTERTABLEMAVEN = "enter table maven";
	public final static String ENTERCA = "enter comparmental analysis";
	public final static String ENTERBQL = "enter bql";
	public final static String ENTERDISSOLUTION = "enter dissolution";
	public final static String EXECUTETABLEMAVEN = "enter execute maven";
	
	//feature panel codes
	public final static String ENTERPLOTMAVEN = "enter plot maven";
	
	// Nca Lambda Z Screen
	public final static String LINEARVIEW = "linear view radio button";
	public final static String LOGVIEW = "log view radio button";
	public final static String LAMBDAZMETHOD = "lambda z como box event";
	
	//Nca Mapping screen
	public final static String AVAILAANDSORT ="available and sort button ";
	public final static String AVAILANDY = "available and y variable button";
	public final static String AVAILANDX = "available and x variable button";
	public final static String AVAILANDENDTIME = "available and end time button";
	public final static String AVAILANDVOLUME = "available and volume button";
	public final static String AVAILANDSUBJECT = "Available and subject button";
	public final static String AVAILANDCARRYNCA = "available and carry along button";
	
	//Nca Model Options
	public final static String EXCLUDEPROFILE = "exclude profile";
	public final static String SELECTIONPROFILE = "selection profile";
	public final static String PAGEBREAK = "page break options";
	public final static String PARTIALAREACOUNT = "number of partial areas";
	public final static String ROOTOFADMIN = "root of administration";
	public final static String MODELTYPE = "model type";
	public final static String NCASPARSE = "nca Sparse";
	public final static String NCACALCMETHOD = "nca calculation method";
	public final static String NCAWEIGHT = "nca weighting options";
	
	//Plot maven mapping screen
	public final static String XYSCATTER = "xy scatter radio button";
	public final static String PMAVAILTOX = "available to x button";
	public final static String PMAVAILTOY = "available to y button";
	public final static String PMAVAILTOSORT = "available to sort button";
	public final static String PMAVAILTOGROUP = "available to group button";
	public final static String PMAVAILTOUP = "avail to up button";
	public final static String PMAVAILTODOWn = "available to down button";
	public final static String PMLINEARX = "linear view";
	public final static String PMLOGX = "log view";
	
	//descriptive statistics
	public  final static String DSSORTANDAVAIL = "sort to available button code";
	public final static String DSAVAILTOCARRY = "available to carry along button";
	public final static String DSWEIGHTTOAVAIL = "weight to available";
	
	//semicompartmental analysis
	public final static String ENTERSCA = "enter semi comparmental analysis";
	public final static String SORTANDAVAIL = "sort and available button handler";
	public final static String AVAILAANDCONC = "available and concentration button handler";
	public final static String EFFECTANDAVAIL = "available and effect button handler";
	public final static String TIMEANDAVAIL = "time and available button handler";
	
	
	//nonParametric superposition
	public final static String ENTERNPS = "entry into non parametric superposition";
	public final static String NPSSORTANDAVAIL = "nps sort and available button ";
	public final static String NPSAVAILTOCONC = "available to concentration text field";
	public final static String NPSAVAILTOTIME = "available to time button action";
	
	
	//ca Mapping screen
	public final static String AVAILAANDSORTCA ="available and sort button for Ca ";
	public final static String AVAILANDYCA = "available and y variable button for Ca";
	public final static String AVAILANDXCA = "available and x variable button for Ca";
	public final static String AVAILANDENDTIMECA = "available and end time button for Ca";
	public final static String AVAILANDVOLUMECA = "available and volume button for Ca";
	public final static String AVAILANDSUBJECTCA = "Available and subject button for Ca";
	public final static String AVAILANDFUNCCA = "Available and function button for ca";
	
	
	//compartmental analysis
	public final static String  ALMCB = "algebraic model combo box";
	public final static String ALMRB = "algebraic model radiobutton ";
	public final static String DEMCB = "differential equation model combobox";
	public final static String DEMRB = "differential equatio model radiobutton";
	public final static String  DTCB = "data type combobox";
	public final static String  OWRB = "observed weight radiobutton";
	public final static String  PWRB = "predicted weight radiobutton";
	public final static String OWCB = "observed weight combobox";
	public final static String  PWCB = "predicted weight combobox";
	public final static String DUB = "dose unit button";
	public final static String NUCB = "normalization unit combobox";
	public final static String  NDCB = "number of dose combobox";
	public final static String IPVCB = "initial parameter value combobox";
	public final static String PEBCSRB = "initial parameter estimate by curve stripping radiobutton";
	public final static String PEBGARB ="initial parameter estimate by genetic algorithm radiobutton";
	public final static String PEBGSRB ="initial parameter estimate by Grid Search radiobutton";
	public final static String PEBNCA = "initial parameter estimate using nca, curve sripping and equations";
		
	public final static String PBCB = "parameter boundary combobox";
	public final static String MMCB = "minimization method combobox";
	public final static String ODESCB = " ode solver combobox";
	public final static String AVTSB = " available variables to sort variable button";
	public final static String  AVTCB = "available variables to concentration button";
	public final static String AVTTB = " available variable to time button";
	public final static String NDTF = " number of dose text field";
	public final static String DUTF = " number of dose text field";
	public final static String CLER = " clearance parameterizition";
	public final static String SIMU = " library model simulation";
	public final static String PKUNIT = " pk unit";
	public final static String SIMUUNIT = " simulation unit";
	
	// For InVitro
	
	public final static String HILL = "Hill radiobutton";
	public final static String WEIBUL = "Weibul radiobutton";
	public final static String DOUBLEWEIBUL = "DoubleWeibul radiobutton";
	public final static String MAKOID = "Makoid radiobutton";
	public final static String INVITROWEIGHT = "InVitro weight radiobutton";
	public final static String AVAILAANDSORTINVITRO ="available and sort button for Invitro ";
	public final static String AVAILANDYINVITRO  = "available and y variable button for Invitro";
	public final static String AVAILANDXINVITRO  = "available and x variable button for Invitro";
	public final static String INVITROWEIGHTCB = "Invito weighting comboBox";
	
	
	
	//bql analysis
	public final static String BQLAVAILAANDSORT = "bql available to sort";
	public final static String BQLAVAILANDX = "bql available to x";
	public final static String BQLAVAILANDY = "bql available to Y";
	public final static String BQLMOVETOCARRY = "bql move to carry along list";
	public final static String BQLMOVETOSTATUS = "move to subject ";
	public final static String BQLLLOQCHECKBOX = "lloq check box";
	
	
	//globe
	public final static String SHOWAAFRAME = "show the available analysis frame";
	
	//find and replace codes
	String FINDREPLACE = "find and replace in the local work sheet";
	
	
	//for ca user initial parameters automatic computations
	String CAUSERIPCOMPUTE = "compute ca ascii initial parametes";
}
