package Model;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Time;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Vector;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.commons.lang.StringUtils;
import org.jfree.chart.JFreeChart;

import view.ApplicationInfo;
import view.DDViewLayer;
import jxl.Sheet;

// TODO: Auto-generated Javadoc
/**
 * NCA = Non Compartmental Analysis of Pharmacokinetic and Pharmacodynamic data.
 * <P>
 * NCA class provides various methods for the computation of NCA parameters.
 * Different methods for the calculation of Area Under the Curve (AUC) and
 * interpolation are provided and depending upon the type of data and
 * experiment, user selects the corresponding method of calculation.
 * 
 * @author 257737
 * @version 1.0
 */

public class NCA {

	/** The type of PK/PD data - (Plasma/Urine). */
	String dataType;
	/**
	 * The Data matrix as read using DataReader class in the form of 2
	 * dimensional array.
	 */
	String[][] Dat;
	/**
	 * The X matrix representing x-coordinates of data points (time in case of
	 * PK study).
	 */
	double[] X; // to be fitted
	/**
	 * The Y matrix representing y-coordinates of data points (concentration in
	 * case of PK study).
	 */
	double[] Y;
	/** The X matrix after pre-treatment. Xp is used for actual calculations. */
	double[] Yp;

	double[] Xp;
	/** The terminal x values used for the calculation of terminal slope. */
	double[] termX;
	String[] termXStr;
	/** The terminal y values used for the calculation of terminal slope. */
	double[] termY;
	/** The x coordinates of lower time points of partial areas to be computed. */
	double[] lowerX;
	/** The x coordinates of upper time points of partial areas to be computed. */
	double[] upperX;
	/**
	 * The partial areas calculated between lower lowerX) and upper (upperX)
	 * time points.
	 */
	double[] partialAUC;
	/**
	 * The calculated concentration values for the terminal portion (using
	 * regression).
	 */
	double[] Ycalc;
	String[] YcalcStr;
	/** Amount of the Dose administered. */
	double[] Dose;
	/** The time at which dose is administered. */
	double[] dosingTime;
	/** The time duration for infusion. */
	double[] TI;
	/** The initial concetration. */
	double C0;
	/** The maximum concentration. */
	double Cmax;
	/** The time at which concentration is maximum. */
	double Tmax;
	/** Maximum concentration divided by Dose administered. */
	double Cmax_D;
	/** The predicted maximum concentration. */
	double Cmax_pred;
	/** The last observed concentration. */
	double Clast;
	/** The last predicted concentration. */
	double Clast_pred;
	/**
	 * The last sampling time at which concentration is measured. Time
	 * correspinding to Clast
	 */
	double Tlast;
	/**
	 * Slope of the curve corresponding to selected terminal points. May not be
	 * terminal slope.
	 */
	double Slope;
	/**
	 * The Intercept corresponding to selected terminal points. May not be
	 * terminal intercept.
	 */
	double Intercept;
	/** The number of points on terminal used to calculate lambda Z. */
	int noPtsLambdaZ;
	/** The number of partial areas to be computed. */
	int noPartialAreas;
	/** The terminal slope (lambdaZ). */
	double lambdaZ;

	/** The terminal intercept. Corresponding to terminal slope lambdaZ. */
	double C0termIntercept;
	/**
	 * Upper limit on Time for values to be included in the calculation of
	 * lambdaZ.
	 */
	double lambdaZUpper;
	/**
	 * Lower limit on Time for values to be included in the calculation of
	 * lambdaZ.
	 */
	double lambdaZLower;
	/** The half life of drug calculated using terminal slope. */
	double termHalfLife;
	/**
	 * The Area under the curve from the time of dosing (Dosing_time) to the
	 * last measurable concentration.
	 */
	double AUClast;
	/**
	 * AArea under the curve from the time of dosing (Dosing_time) to the time
	 * of the last observation. If the last concentration is non-zero AUClast =
	 * AUCall. Otherwise, AUCall will be greater than AUClast as it includes the
	 * additional area from the last measurable concentration down to zero.
	 */
	double AUCall;
	/**
	 * AUC from the time of dosing (Dosing_time) extrapolated to infinity on
	 * observed concentrations.
	 */
	double AUCinfObs;
	/**
	 * AUC from the time of dosing (Dosing_time) extrapolated to infinity on
	 * predicted concentrations.
	 */
	double AUCinfPred;
	/** Observed AUCinf divided by Dose. */
	double AUCinf_DObs;
	/** Predicted AUCinf divided by Dose. */
	double AUCinf_DPred;
	/**
	 * Percentage of Observed AUCinf that is due to extrapolation from Tlast to
	 * infinity.
	 */
	double AUCExtrapObs;
	/**
	 * Percentage of Predicted AUCinf that is due to extrapolation from Tlast to
	 * infinity.
	 */
	double AUCExtrapPred;
	double AUMCall;
	/**
	 * Area under the moment curve from the time of dosing (Dosing_time) to the
	 * last measurable concentration.
	 */
	double AUMClast;
	/**
	 * Area under the first moment curve (AUMC) extrapolated to infinity on
	 * observed concentrations.
	 */
	double AUMCinfObs;
	/**
	 * Area under the first moment curve (AUMC) extrapolated to infinity on
	 * predicted concentrations.
	 */
	double AUMCinfPred;
	/**
	 * Percentage of Observed AUMCinf that is due to extrapolation from Tlast to
	 * infinity.
	 */
	double AUMCExtrapObs;
	/**
	 * Percentage of Predicted AUMCinf that is due to extrapolation from Tlast
	 * to infinity.
	 */
	double AUMCExtrapPred;
	/**
	 * Percentage of Observed AUCinf that is due to back extrapolation to dosing
	 * point in case of iv bolus dosing.
	 */
	double AUCBackExtrapObs;
	/**
	 * Percentage of Predicted AUCinf that is due to back extrapolation to
	 * dosing point in case of iv bolus dosing.
	 */
	double AUCBackExtrapPred;
	/**
	 * Mean residence time from the time of dosing (Dosing_time) to the time of
	 * the last measurable concentration. .
	 */
	double MRTlast;
	/** Observed Mean residence time (MRT) extrapolated to infinity. */
	double MRTinfObs;
	/** Predicted Mean residence time (MRT) extrapolated to infinity.. */
	double MRTinfPred;
	/** The Observed volume of distribution based on terminal phase. */
	double VzObs;
	/** The Observed Clearance based on terminal phase. */
	double ClObs;
	/** The Predicted volume of distribution based on terminal phase. */
	double VzPred;
	/** The Predicted Clearance based on terminal phase. */
	double ClPred;
	/** Steady state volume of distribution for observed concentration. */
	double VssObs;
	/** Steady state volume of distribution for predicted concentration. */
	double VssPred;
	/** Goodness of fit statistic for the terminal elimination phase. */
	double Rsq;
	/**
	 * Goodness of fit statistic for the terminal elimination phase, adjusted
	 * for the number of points used in the estimation of lambda Z.
	 */
	double adjRsq;
	/**
	 * Correlation between time (X) and log concentration (Y) for the points
	 * used in the estimation of lambda Z.
	 */
	double CorrTermXY;
	/** If partial areas to be computed (1 if yes, 0 if no). */
	public double ifPartialAreas;
	/** The method used for the calculation of AUC. */
	int method;

	/* additional parameters for sparse sampling */
	double SE_Cmax;
	double SE_AUClast;
	double SE_AUCall;

	String noPtsLambdaZStr;
	String lambdaZStr;
	String lambdaZUpperStr;
	String lambdaZLowerStr;
	String termHalfLifeStr;
	String AUCinfObsStr;
	String AUCinfPredStr;
	String AUCinf_DObsStr;
	String AUCinf_DPredStr;
	String AUCExtrapObsStr;
	String AUCExtrapPredStr;
	String AUMCinfObsStr;
	String AUMCinfPredStr;
	String AUMCExtrapObsStr;
	String AUMCExtrapPredStr;
	String AUCBackExtrapObsStr;
	String AUCBackExtrapPredStr;
	String MRTinfObsStr;
	String MRTinfPredStr;
	String VzObsStr;
	String ClObsStr;
	String VzPredStr;
	String ClPredStr;
	String VssObsStr;
	String VssPredStr;
	String RsqStr;
	String adjRsqStr;
	String CorrTermXYStr;
	String VzStr;
	String CLssStr;
	boolean isCurveStrippingDisable;
	boolean ifCalculateParam = false;

	/****************************************************/
	// change in the code for specifying unit of the parameters.

	String[] dose_unit;
	String time_unit;
	String[] dosingtime_unit;
	String volume_unit;
	String conc_mass_unit;
	String normalization_unit;

	/****************************************************/
	// change in the code for introducing multiple subjects and sparse sampling
	int no_subject;
	boolean ifserial_sampling;
	int ifserialsampling_multidose;
	boolean ifparse_sampling;
	int ifparsesampling_multidose;
	int no_distinct_obs_sparsesampling;
	double[] X_sparse;
	double[] Y_sparse;
	String[] subject_id;
	int[] subject_obs;
	int time;
	int conc;
	int sort_variable;
	int animal_id;
	int root_administration;
	int dose_col;
	int treatment;
	// for urine data
	int start_time_col;
	int end_time_col;
	int conc_col;
	int volume_col;
	double[] AUCValue;
	double[] AUMCValue;
	double[] unitConvertAmount;
	String[] defaultUnit;
	String[] preferredUnit;

	/**************************************/
	// additional parameters for oral dose
	double Tlag;

	// additional parameters for multiple dose
	double CLss;
	double Vz;
	double Tmin;
	double Cmin;
	double Cavg;
	double Fluctuation;
	double Accumulation_index;
	double AUC_tau;
	double AUMC_tau;

	// additional parameters for urine data
	double Tmax_Rate;
	double Max_Rate;
	double Mid_Pt_last;
	double Rate_last;
	double AURC_last;
	double Vol_UR;
	double Amount_Recovered;
	double Percent_Recovered;
	double AURC_all;
	double AURC_inf_obs;
	double AURC_Extrp_obs;
	double AURC_inf_Pred;
	double AURC_Extrp_Pred;
	double SE_Max_Rate;
	double SE_AURC_last;
	double SE_AURC_all;

	// variables for urine data processing
	double[] mid_point;
	double[] excretion_rate;
	double[] start_time;
	double[] end_time;
	double[] concentration;
	double[] volume;

	// variable for model selection
	int model_no;
	String singleDoseUnit;
	int numberOfColumns;
	int modelType;
	int rootOfAdminForCurrentProfile;
	int rootOfAdmin;
	int numberOfSortVariable;
	int noObs;
	int numberOfProfiles;
	ArrayList<String> parametersValue;
	ArrayList<double[]> stdError;

	/** The print stream variable. */

	int type1;
	int mode1;

	int weightingIndex;
	int normalizationUnitIndex;

	// variables for LambdaZ calculation
	int[] ifBestFit;
	double[] lambdaZStartTime;
	double[] lambdaZEndTime;
	double[] tau;

	boolean ifWithinNCAExecution = false;

	private TableColumn tc;
	double[] partialAreaValue;
	int partialAreaCount = 0;
	boolean[] ifProfileWithInsufficientData;

	String timeColumnName;
	String concColumnName;
	int timeColumnIndex;
	int concColumnIndex;
	double[] convert;
	int ifSteadyState;
	int roundTo;
	int sum;
	int sum1;
	int noOfPartialAreas;
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	WorkSheetOutputs wsOutputts;
	partialAreaCalculation subAreaCalInst;
	ArrayList<String> intermediateOutputForPA;
	ChooseParameterComputationClass instance;

	public static NCA NCA_OBJECT = null;

	public static NCA creaetNCAInst() {
		if (NCA_OBJECT == null) {
			NCA_OBJECT = new NCA("just object creation");
		}

		return NCA_OBJECT;
	}

	public NCA(String dummy) {

	}

	ApplicationInfo appInfo;

	public ApplicationInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(ApplicationInfo appInfo) {
		this.appInfo = appInfo;
	}

	
	public void performNCA(ApplicationInfo appInfo) throws Exception {

		setAppInfo(appInfo);
		subAreaCalInst = new partialAreaCalculation();

		try {

			// define few global variables

			initialization();
			intermediateOutputForPA = new ArrayList<String>();
			parametersValue = new ArrayList<String>();
			stdError = new ArrayList<double[]>();

			// dose and dose unit dosing time dosing time unit input and
			// conversion
			computeDoseTableRelatedInputs();

			// multiple the default units appropriately to convert to preferred
			// unit
			UnitConversion unitConversionInst = new UnitConversion();
			scaleFormDefaultToPreferredUnit(unitConversionInst);

			// start of the main loop
			sum = 0;
			int k = 0;
			sum1 = 0;

			wsOutputts = new WorkSheetOutputs();
			wsOutputts.getPlotOutputs().setLinearPlots(
					new ArrayList<JFreeChart>());
			wsOutputts.getPlotOutputs()
					.setLogplots(new ArrayList<JFreeChart>());

			boolean headerAlreadyPrinted = false;
			for (int currentProfileNumber = 0; currentProfileNumber < numberOfProfiles; currentProfileNumber++) {

				Time t1 = new Time(System.currentTimeMillis());
				ifWithinNCAExecution = true;

				// Populate data of the current profile i.e the index of this
				// for loop
				
				initializeParameterValueToZero();
				populatingCurrentProfileData(currentProfileNumber);

				int lambdaZCalcMethodNumber = 0;
				if ((appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getLambdazDataobj()
						.getLambdaZDetailsOf(currentProfileNumber).getMethodSelected()) == 0) {
					lambdaZCalcMethodNumber = 1;
				} else {
					lambdaZCalcMethodNumber = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
							.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getLambdazDataobj().getLambdaZDetailsOf(currentProfileNumber)
							.getMethodSelected() + 1;
				}
				if (rootOfAdmin == 3) {
					rootOfAdminForCurrentProfile = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getProfileInfoObj().getRouteOfAdminAt(currentProfileNumber);
				} else
					rootOfAdminForCurrentProfile = rootOfAdmin;

				// check whether the profile contins sufficient data for NCA
				DetectionOfProfilesWithInsufficientData detectionOfProfWithInsuffData = new DetectionOfProfilesWithInsufficientData();
				ifProfileWithInsufficientData[currentProfileNumber] = detectionOfProfWithInsuffData
						.detectProfileWithInsufficientDataInNCA(
								X,
								Y,
								lambdaZCalcMethodNumber,
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getLambdazDataobj()
										.getLambdaZDetails()[currentProfileNumber].getStartTime(),
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getLambdazDataobj()
										.getLambdaZDetails()[currentProfileNumber].getEndTime(),
								rootOfAdminForCurrentProfile, TI[currentProfileNumber],
								dosingTime[currentProfileNumber]);

				// ifProfileWithInsufficientData[j] = false;
				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.isExcluseProfiles() == true
						&& ifProfileWithInsufficientData[currentProfileNumber] == true) {
					wsOutputts
							.getTextoutput()
							.add(
									StringUtils
											.rightPad(
													" The Parameters of This Profile cannot be Estimated",
													100)
											+ "\r\n\r\n");

				} else {

					if (tau[currentProfileNumber] == 0) {
						ifSteadyState = 0;
					} else
						ifSteadyState = 1;

					// pre treatment in scenarios where the concentration is
					// zero for time = 0
					DataPreTreatment(dosingTime[currentProfileNumber], modelType,
							rootOfAdminForCurrentProfile, ifSteadyState, tau[currentProfileNumber]);

					// System.out.println("Program running.......");

					/*
					 * SerialIvPlasmaSingleDoseParametersCal inst = new
					 * SerialIvPlasmaSingleDoseParametersCal();
					 * inst.calculateParmeters(j);
					 */

					/*
					 * PlasmaPOSerialNoTauParamCal inst = new
					 * PlasmaPOSerialNoTauParamCal();
					 * inst.calculateParameters(j);
					 */
					instance = new ChooseParameterComputationClass();
					instance.chooseRequiredCombination(currentProfileNumber);
					noPtsLambdaZ = 0;

					partialAreaComputation(appInfo, currentProfileNumber);
					// LambdaZCalculationMethod.lambdaZCalculation(N.X, N.Y, 0,
					// 0,0);
					
					
						

				}

				addProfRelatedInfoToAppInfo();
			}
			wsOutputts.setParameterValuesAL(parametersValue);
			wsOutputts.setStdError(stdError);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.setWorkSheetOutputs(wsOutputts);
		}

		catch (NoSuchElementException e) {
			e.printStackTrace();
			
		}
	}

	private void initializeParameterValueToZero() {
	C0 = 0;
	Cmax = 0;
	Tmax = 0;
	Cmax_D = 0;
	Cmax_pred = 0;
	Clast  =0;
	Clast_pred = 0;
	Tlast = 0;
	Slope = 0;
	Intercept = 0;
	noPtsLambdaZ = 0;
	noPartialAreas = 0;
	lambdaZ = 0;
	C0termIntercept = 0;
	lambdaZUpper = 0;
	
	lambdaZLower = 0;
	termHalfLife= 0;
	AUClast= 0;
	
	AUCall= 0;
	AUCinfObs= 0;
	AUCinfPred= 0;
	AUCinf_DObs= 0;
	AUCinf_DPred= 0;
	
	AUCExtrapObs= 0;
	AUCExtrapPred= 0;
	AUMCall= 0;
	AUMClast= 0;
	AUMCinfObs= 0;
	AUMCinfPred= 0;
	AUMCExtrapObs = 0;
	AUMCExtrapPred = 0;
	
	AUCBackExtrapObs = 0;
	AUCBackExtrapPred = 0;
	MRTlast = 0; 
	MRTinfObs = 0;
	MRTinfPred = 0;
	VzObs = 0;
	ClObs = 0;
	VzPred = 0;
	ClPred = 0;
	VssObs = 0;
	VssPred = 0;
	Rsq = 0;
	adjRsq = 0;
	CorrTermXY = 0;
	SE_Cmax = 0;
	SE_AUClast = 0;
	SE_AUCall = 0;

	noPtsLambdaZStr = "";
	 lambdaZStr = "";
	 lambdaZUpperStr = "";
	 lambdaZLowerStr = "";
	 termHalfLifeStr = "";
	 AUCinfObsStr = "";
	 AUCinfPredStr = "";
	 AUCinf_DObsStr = "";
	 AUCinf_DPredStr = "";
	 AUCExtrapObsStr = "";
	 AUCExtrapPredStr = "";
	 AUMCinfObsStr = "";
	 AUMCinfPredStr = "";
	 AUMCExtrapObsStr = "";
	 AUMCExtrapPredStr = "";
	 AUCBackExtrapObsStr = "";
	 AUCBackExtrapPredStr = "";
	 MRTinfObsStr = "";
	 MRTinfPredStr = "";
	 VzObsStr = "";
	 ClObsStr = "";
	 VzPredStr = "";
	 ClPredStr = "";
	 VssObsStr = "";
	 VssPredStr = "";
	 RsqStr = "";
	 adjRsqStr = "";
	 CorrTermXYStr = "";
	 VzStr = "";
	 }

	private void addProfRelatedInfoToAppInfo() {
		ProfileRelatedOutputs profRelOut = new ProfileRelatedOutputs();
		profRelOut.setAucValue(AUCValue);
		profRelOut.setXp(Xp);
		profRelOut.setYp(Yp);
		profRelOut.setAumcValue(AUMCValue);
		wsOutputts.getProfileRelateroutput().add(profRelOut);

	}

	private void partialAreaComputation(ApplicationInfo appInfo, int j) {

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getNcaInfo()
				.getProcessingInputs()
				.getSubAreasDataObj()
				.getpartialValueAt(
						j * noOfPartialAreas,
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo().getProcessingInputs()
								.getMappingDataObj()
								.getSortVariablesListVector().size() + 1) != null
				&& appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getNcaInfo()
						.getProcessingInputs()
						.getSubAreasDataObj()
						.getpartialValueAt(
								j * noOfPartialAreas,
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size() + 2) != null) {

		}
		for (int iii = 0; iii < noOfPartialAreas; iii++) {
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getProcessingInputs()
					.getSubAreasDataObj()
					.getpartialValueAt(
							j * noOfPartialAreas + iii,
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() + 1) != null
					&& appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
							.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL()
							.get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo()
							.getProcessingInputs()
							.getSubAreasDataObj()
							.getpartialValueAt(
									j * noOfPartialAreas + iii,
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector()
											.size() + 2) != null) {

				try {
					String S1 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getSubAreasDataObj().getpartialValueAt(
									j * noOfPartialAreas + iii,
									numberOfSortVariable + 1);
					String S2 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getSubAreasDataObj().getpartialValueAt(
									j * noOfPartialAreas + iii,
									numberOfSortVariable + 2);
					Double.parseDouble(S1);
					Double.parseDouble(S2);
					double[] startTime = { Double.parseDouble(S1) };
					double[] endTime = { Double.parseDouble(S2) };

					subAreaCalInst.calculatePartialArea(Xp, Yp, startTime,
							endTime, 1);

					/*
					 * NCAMainScreen.completeTextOutputTextArea
					 * .append(StringUtils.rightPad(" ", 2) + "\t" +
					 * StringUtils.rightPad( startTime[0] + "", 10) + "\t" +
					 * StringUtils.rightPad( endTime[0] + "", 10) + "\t" +
					 * StringUtils.rightPad(
					 * round(partialAreaCalculation.partialArea[0],4) + "", 10)
					 * + "\r\n");
					 */

					partialAreaValue[partialAreaCount] = round(
							subAreaCalInst.partialArea[0], 4);
					partialAreaCount++;
				} catch (Exception e) {
					System.out.println(" within exception of partial area");
				}

			}
		}

	}

	private void populatingCurrentProfileData(int currentProfileNumber) {
		if (ifserial_sampling == true) {
			X = new double[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj().getSubject_obs()[currentProfileNumber]];
			Y = new double[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj().getSubject_obs()[currentProfileNumber]];

			for (int jj = 0; jj < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj().getSubject_obs()[currentProfileNumber]; jj++) {
				X[jj] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getTimeForAllProfile()[sum + jj];
				Y[jj] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getConcForAllProfile()[sum + jj];
			}
			sum = sum
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getProfileInfoObj().getSubject_obs()[currentProfileNumber];
		} else {
			X = new double[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getNo_distinct_obs_sparsesampling()[currentProfileNumber]];
			Y = new double[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getNo_distinct_obs_sparsesampling()[currentProfileNumber]];
			for (int jj = 0; jj < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getNo_distinct_obs_sparsesampling()[currentProfileNumber]; jj++) {
				X[jj] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getTimeForAllProfile()[sum + jj];
				Y[jj] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getConcForAllProfile()[sum + jj];
			}
			sum = sum
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getProfileInfoObj()
							.getNo_distinct_obs_sparsesampling()[currentProfileNumber];
			sum1 = sum1
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getProfileInfoObj().getSubject_obs()[currentProfileNumber];
			// DetermineNumberOfSubject.subject_obs[j]=DataPreparationForAllComponents.no_distinct_obs_sparsesampling[j];
		}
		System.out.println();

	}

	private void initialization() {

		method = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getCalculationMethod();
		roundTo = 5;
		modelType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getModelType();

		rootOfAdmin = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getRootOfAdmistration();
		ifSteadyState = 0;
		convert = new double[3];
		numberOfSortVariable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();

		noObs = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getProfileInfoObj()
				.getTimeForAllProfile().length;

		// modification for multiple dose and sort variables, mapping columns to
		// variables
		timeColumnName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName();
		concColumnName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName();

		timeColumnIndex = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getxColumnCorrespondinOriginalIndex();
		concColumnIndex = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getyColumnCorrespondinOriginalIndex();

		singleDoseUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getDoseUnits();
		numberOfProfiles = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject();
		

		Dose = new double[numberOfProfiles];
		TI = new double[numberOfProfiles];
		dosingTime = new double[numberOfProfiles];
		tau = new double[numberOfProfiles];
		dose_unit = new String[numberOfProfiles];
		dosingtime_unit = new String[numberOfProfiles];
		ifProfileWithInsufficientData = new boolean[numberOfProfiles];

		ifserial_sampling = true;
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getisSparseSelected() == true) {

			ifserial_sampling = false;
			ifparse_sampling = true;
		}

		if (modelType == 1) {
			String endTimeColumnName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getEndTimeColumnName();

			String volumeColumnName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getVolumeColumnName();

			end_time_col = numberOfColumns - 3;
			volume_col = numberOfColumns - 1;
			sort_variable = numberOfColumns - 5;
			start_time_col = numberOfColumns - 4;
			conc_col = numberOfColumns - 2;

		}
	}

	private void computeDoseTableRelatedInputs() {
		
		conc_mass_unit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj().getConcMassUnit();

		String doseUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								
								
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getDoseUnits();
		
		UnitConversion unitConversionInst = new UnitConversion();
		double doseUnitConversionAmount =1;
		try{
			doseUnitConversionAmount = unitConversionInst.unitConversion(doseUnit, conc_mass_unit);

		}catch (Exception e) {
			doseUnitConversionAmount = 1;
		}
		
		
		for (int j = 0; j < numberOfProfiles; j++) {

			dose_unit[j] = singleDoseUnit;

			if (rootOfAdmin == 3) {

				if (modelType != 1) {
					String C = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getDosingDataObj().getDosingValueAt(j,
									numberOfSortVariable + 2);
					try {
						Double.parseDouble(C);
						tau[j] = Double.parseDouble(C);
					} catch (Exception e) {
						// TODO: handle exception
						tau[j] = 0;
					}

					rootOfAdminForCurrentProfile = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getProfileInfoObj().getRouteOfAdminAt(j);

					if (rootOfAdminForCurrentProfile == 2)
						TI[j] = Double
								.parseDouble(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getDosingDataObj().getDosingValueAt(j,
												numberOfSortVariable + 3));
				}/* else
					TI[j] = Double.parseDouble(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
							.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getDosingDataObj().getDosingValueAt(j,
									numberOfSortVariable + 3));*/

			} else if (rootOfAdmin == 2) {
				if (modelType != 1) {
					String C = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getDosingDataObj().getDosingValueAt(j,
									numberOfSortVariable + 2);
					try {
						Double.parseDouble(C);
						tau[j] = Double.parseDouble(C);
					} catch (Exception e) {
						// TODO: handle exception
						tau[j] = 0;
					}
					TI[j] = Double.parseDouble(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
							.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getDosingDataObj().getDosingValueAt(j,
									numberOfSortVariable + 3));
				} else
					TI[j] = Double.parseDouble(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
							.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getDosingDataObj().getDosingValueAt(j,
									numberOfSortVariable + 2));

			} else {

				if (modelType != 1) {
					String C = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getDosingDataObj().getDosingValueAt(j,
									numberOfSortVariable + 2);
					try {
						Double.parseDouble(C);
						tau[j] = Double.parseDouble(C);
					} catch (Exception e) {
						// TODO: handle exception
						tau[j] = 0;
					}
				}
			}

			try {
				Dose[j] = Double.parseDouble(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getDosingDataObj()
						.getDosingValueAt(j, numberOfSortVariable + 0));
				
				Dose[j] = Dose[j] * doseUnitConversionAmount;

				dosingTime[j] = Double.parseDouble(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getDosingDataObj()
						.getDosingValueAt(j, numberOfSortVariable + 1));

				dosingtime_unit[j] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(1)
						.getUnitBuilderDataObj().getTimeUnits();
				
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(
						DDViewLayer.createMTInstance().mainTabbedFrame,
						"Please ensure Dosing Information is complete",
						"Conform", JOptionPane.YES_OPTION);
				break;
			}

		}

	}

	private void scaleFormDefaultToPreferredUnit(
			UnitConversion unitConversionInst) {

		if (modelType == 0) {
			defaultUnit = new String[16];
			preferredUnit = new String[16];
			unitConvertAmount = new double[16];
			for (int i = 0; i < 16; i++)
				unitConvertAmount[i] = 1;

			for (int i = 0; i < 16; i++) {
				defaultUnit[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getDefaultUnitValueAt(i);
				preferredUnit[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getPreferredUnitsArray()[i];
				try {
					unitConvertAmount[i] = unitConversionInst.unitConversion(
							defaultUnit[i], preferredUnit[i]);
				} catch (Exception e) {
					unitConvertAmount[i] = 1;
				}

			}
		}

		if (modelType == 1) {
			defaultUnit = new String[9];
			preferredUnit = new String[9];
			unitConvertAmount = new double[9];
			for (int i = 0; i < 9; i++)
				unitConvertAmount[i] = 1;

			for (int i = 0; i < 9; i++) {
				defaultUnit[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getDefaultUnitValueAt(i);
				preferredUnit[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getPreferredUnitsArray()[i];
				try {
					unitConvertAmount[i] = unitConversionInst.unitConversion(
							defaultUnit[i], preferredUnit[i]);
				} catch (Exception e) {
					unitConvertAmount[i] = 1;
				}
			}
		}

	}

	private ArrayList<String> fillListFromFile(String string) {

		ArrayList<String> tempList = new ArrayList<String>();
		try {
			FileInputStream fstream = new FileInputStream(string);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			try {
				while ((strLine = br.readLine()) != null) {
					// Print the content on the console
					tempList.add(strLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Close the input stream
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempList;
	}

	/**
	 * Data pre treatment.
	 * 
	 * @param Td
	 *            the td
	 * @param type
	 *            the type
	 * @param mode
	 *            the mode
	 * @param ifSteadyState
	 *            the if steady state
	 */
	public void DataPreTreatment(double Td, int type, int mode,
			int ifSteadyState, double tau) {
		int ifTdContained = findVal(X, Td);
		if (ifTdContained == -1) {
			Xp = new double[X.length + 1];
			Yp = new double[Y.length + 1];
			Xp[0] = Td;
			for (int i = 0; i < X.length; i++)
				Xp[i + 1] = X[i];

			if (modelType == 0) {
				if (mode == 1) {
					if (Y[0] == 0.0 || Y[1] == 0.0) {
						int id = firstNonZero(Y);
						C0 = Y[id];
					} else {
						double[] x = new double[2];
						double[] y = new double[2];
						// double[] y1 = new double[2];
						x[0] = X[0];
						x[1] = X[1];
						y[0] = Math.log(Y[0]);
						y[1] = Math.log(Y[1]);
						// y1[0] =Y[0]; y1[1] =Y[1];
						simpleLinRegression(x, y);
						lambdaZ = -1.0 * Slope;
						Intercept = Math.exp(Intercept);
						C0 = Slope * Td + Intercept;
						if (C0 < Cmax)
							C0 = Y[1];

					}
				} else

				{
					if (ifSteadyState != 0) {
						double minVal = minVal_tau(X, Y, Td, tau);
						C0 = minVal;
					} else {
						C0 = 0;
					}
				}

				Yp[0] = C0;
				for (int i = 0; i < Y.length; i++)
					Yp[i + 1] = Y[i];
			}
			if (modelType == 1) {
				Yp[0] = 0;
				for (int i = 0; i < Y.length; i++)
					Yp[i + 1] = Y[i];

			}
		} else {
			Xp = X;
			Yp = Y;
			C0 = Y[0];
		}

	}

	// for urine data processing

	public void urine_dataprocessing(double[] start_time, double[] end_time,
			double[] conc, double[] volume) {
		int no_obs = 0;
		no_obs = start_time.length;
		mid_point = new double[no_obs];
		excretion_rate = new double[no_obs];

		for (int i = 0; i < no_obs; i++) {
			mid_point[i] = (start_time[i] + end_time[i]) / 2;
			excretion_rate[i] = conc[i] * volume[i]
					/ (end_time[i] - start_time[i]);
		}
	}

	void urine_parameter(double[] conc1, double[] vol1, double dose1) {
		int no_obs1 = conc1.length;
		double sum = 0;
		double sum1 = 0;
		for (int i = 0; i < no_obs1; i++) {
			sum = sum + conc1[i] * vol1[i];
			sum1 = sum1 + vol1[i];

		}
		Vol_UR = sum1;
		Amount_Recovered = sum;
		Percent_Recovered = 100 * sum / dose1;

	}

	int findVal(double[] mat, double val) {

		for (int i = 0; i < mat.length; i++) {
			if (mat[i] == val)
				return i;
		}
		return -1;
	}

	int firstNonZero(double[] mat) {

		int index = 0;
		for (int i = 0; i < mat.length; i++) {
			if (mat[i] != 0.0) {
				index = i;
				break;
			}
		}
		return index;
	}

	int lastNonZero(double[] mat) {

		int index = 0;
		for (int i = mat.length - 1; i >= 0; i--) {
			if (mat[i] != 0.0) {
				index = i;
				break;
			}
		}
		return index;
	}

	public double minVal(double[] mat) {
		double min = mat[0];
		for (int i = 1; i < mat.length; i++) {
			if (mat[i] < min) {
				min = mat[i];
			}
		}
		return min;
	}

	public double minVal_tau(double[] X, double[] mat, double dt, double tau) {
		double min = mat[0];
		int i = 0;
		while (X[i] < dt)
			i++;
		while (X[i] <= tau + dt && i < mat.length) {
			if (mat[i] < min) {
				min = mat[i];
			}
			i++;
		}
		return min;
	}

	public double maxVal(double[] mat) {
		double max = mat[0];
		for (int i = 1; i < mat.length; i++) {
			if (mat[i] > max) {
				max = mat[i];
			}
		}
		return max;
	}

	public double maxVal_tau(double[] X, double[] mat, double dt, double tau) {
		double max = mat[0];
		int i = 0;
		while (X[i] < dt)
			i++;
		while (X[i] <= tau + dt && i < mat.length) {
			if (mat[i] > max) {
				max = mat[i];
			}
			i++;
		}
		return max;
	}

	public void simpleLinRegression(double[] x, double[] y) {
		int n = x.length;
		double sumX = 0;
		double sumY = 0;
		double sumXY = 0;
		double sumX2 = 0;
		double sumW = 0;
		double sumWXY = 0;
		double sumWX = 0;
		double sumWY = 0;
		double sumWX2 = 0;
		double sumWY2 = 0;
		double Sxx = 0;
		double Syy = 0;
		double Sxy = 0;

		for (int i = 0; i < n; i++) {
			sumX = sumX + x[i];
			sumY = sumY + y[i];
			// weightSum=weightSum+1/Math.pow(y[i],(weightingIndex-2));
			sumXY = sumXY + x[i] * y[i];
			sumX2 = sumX2 + x[i] * x[i];
			sumW = sumW + 1 / Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWX = sumWX + x[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWXY = sumWXY + x[i] * y[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWY = sumWY + y[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWX2 = sumWX2 + x[i] * x[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWY2 = sumWX2 + y[i] * y[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
		}

		// Slope = (n*sumXY - sumX * sumY)/ (n*sumX2 - sumX*sumX);
		Slope = (sumW * sumWXY - sumWX * sumWY)
				/ (sumW * sumWX2 - sumWX * sumWX);
		Intercept = (sumWX2 * sumWY - sumWX * sumWXY)
				/ (sumW * sumWX2 - sumWX * sumWX);

		// Intercept = (sumY - Slope*sumX)/n;
		Sxx = (sumWX2 - (sumWX * sumWX / sumW));
		Syy = (sumWY2 - (sumWY * sumWY / sumW));
		Sxy = (sumWXY - (sumWX * sumWY / sumW));
		// Rsq=((Sxy*Sxy)/(Sxx*Syy));

	}

	public int numberOfTerminalLambdaZPoints() {

		int maxCidx = findVal(Y, Cmax);
		int lastIdx = lastNonZero(Y);
		// int noPointsLambdaZ;
		// int pts = Yp.length - maxCidx - 1;
		int pts = lastIdx - maxCidx;

		double[] AdjRsq = new double[pts];
		double[] lambda = new double[pts];
		if (pts < 3) {
			noPtsLambdaZ = pts;
			return noPtsLambdaZ;
		} else {
			if (mode1 == 1)
				noPtsLambdaZ = pts + 1;
			else
				noPtsLambdaZ = pts;

			for (int i = 3; i <= pts; i++) {
				terminalRsq(i);
				calculateLambdaZ(i);
				AdjRsq[i - 3] = adjRsq;
				lambda[i - 3] = lambdaZ;
				System.out.println("$$$$$ adjusted Rsqure" + AdjRsq[i - 3]);

			}

			// double maxRsq = maxVal(AdjRsq);
			double maxRsq = AdjRsq[0];
			double adjRsqForNoOfPts = AdjRsq[0];
			int ii;
			for (ii = 3; ii < pts; ii++) {
				if ((AdjRsq[ii - 2] >= AdjRsq[ii - 3])
						|| (maxRsq - AdjRsq[ii - 2]) <= 0.0001) {
					System.out.println(" ****** In comparison loop ");
					if (maxRsq <= AdjRsq[ii - 2])
						maxRsq = AdjRsq[ii - 2];
					adjRsqForNoOfPts = AdjRsq[ii - 2];
					System.out.println(" maxRsq=" + maxRsq);
					System.out.println(" adjRsq=" + adjRsqForNoOfPts);
				} else
					break;

			}

			System.out.println("$$$$$ adjusted Rsqure for lambdaZ"
					+ adjRsqForNoOfPts);
			int idx = findVal(AdjRsq, adjRsqForNoOfPts);

			if (lambda[idx] >= 0.0)
				noPtsLambdaZ = idx + 3;

			// else
			// noPtsLambdaZ = 3;
			return noPtsLambdaZ;
		}

	}

	public void calculateLambdaZ(int noPts) {

		double[] logy = new double[noPts];

		for (int i = 0; i < noPts; i++)
			logy[i] = Math.log(termY[i]);
		CorrTermXY = Correlation(termX, logy);

		simpleLinRegression(termX, logy);
		lambdaZ = -1.0 * Slope;
		C0termIntercept = Math.exp(Intercept);

	}

	public void terminalRsq(int noPts) {
		int lastIdx = lastNonZero(Y);
		termX = new double[noPts];
		termY = new double[noPts];

		for (int i = noPts - 1; i >= 0; i--) {
			termX[noPts - 1 - i] = X[lastIdx - i];
			termY[noPts - 1 - i] = Y[lastIdx - i];

		}

		double[] logy = new double[noPts];
		for (int i = 0; i < noPts; i++)
			logy[i] = Math.log(termY[i]);
		simpleLinRegression(termX, logy);

		Ycalc = new double[noPts];

		for (int i = 0; i < noPts; i++) {
			Ycalc[i] = termX[i] * Slope + Intercept;

		}

		Clast_pred = Math.exp(Ycalc[noPts - 1]);

		double sumY = 0;
		double meanY = 0;
		double SST = 0;
		double SSR = 0;
		double SSE = 0;
		double[] error = new double[noPts];

		for (int i = 0; i < noPts; i++) {
			sumY = sumY + logy[i];
			error[i] = logy[i] - Ycalc[i];
			// SSE = SSE + Math.pow(error[i],2);
			SSE = SSE + Math.pow(error[i], 2);
		}
		meanY = sumY / noPts;

		for (int i = 0; i < noPts; i++) {
			SST = SST + Math.pow(logy[i] - meanY, 2);
			SSR = SSR + Math.pow(meanY - Ycalc[i], 2);
		}

		Rsq = 1.0 - (SSE / SST);

		adjRsq = 1.0 - ((1.0 - Rsq) * (noPts - 1)) / (noPts - 2);

	}

	public void calculateLabdaZUsingInterval(double[] X_value,
			double[] Y_value, double startTime, double endTime,
			int weightExponent)

	{
		double lambdaZUsingInterval = 0;

		int noObs = X_value.length;
		int noPoints = 0;
		if (X_value[noObs - 1] < endTime)
			lambdaZUsingInterval = 0;
		for (int i = 0; i < noObs; i++) {
			if (X_value[i] >= startTime && X_value[i] <= endTime)
				noPoints++;
		}
		if (noPoints < 2)
			lambdaZUsingInterval = 0;
		else {
			double[] newX = new double[noPoints];
			double[] newY = new double[noPoints];
			double[] newLogY = new double[noPoints];
			int count = 0;
			for (int i = 0; i < noObs; i++) {
				if (X_value[i] >= startTime && X_value[i] <= endTime) {
					newX[count] = X_value[i];
					newY[count] = Y_value[i];
					newLogY[count] = Math.log(Y_value[i]);
					count++;
				}

			}
			simpleLinRegression(newX, newLogY);
			lambdaZUsingInterval = (-1) * Slope;
			CorrTermXY = Correlation(newX, newLogY);

			Ycalc = new double[noPoints];

			for (int i = 0; i < noPoints; i++) {
				Ycalc[i] = newX[i] * Slope + Intercept;

			}

			double sumY = 0;
			double meanY = 0;
			double SST = 0;
			double SSR = 0;
			double SSE = 0;
			double[] error = new double[noPoints];

			for (int i = 0; i < noPoints; i++) {
				sumY = sumY + newLogY[i];
				error[i] = newLogY[i] - Ycalc[i];
				SSE = SSE + Math.pow(error[i], 2);
			}
			meanY = sumY / noPoints;

			for (int i = 0; i < noPoints; i++) {
				SST = SST + Math.pow(newLogY[i] - meanY, 2);
				SSR = SSR + Math.pow(meanY - Ycalc[i], 2);
			}

			Rsq = 1.0 - (SSE / SST);

			adjRsq = 1.0 - ((1.0 - Rsq) * (noPoints - 1)) / (noPoints - 2);

		}
		double AUC = 0;
		double AUMC = 0;

		lambdaZ = lambdaZUsingInterval;

	}

	public void se_Cmax(double t, double[] C, double C_max) {
		int n = C.length;
		double sum = 0;
		double sd;
		for (int i = 0; i < n; i++) {
			sum = sum + (C[i] - C_max) * (C[i] - C_max);
		}
		sd = sum / (n - 1);
		sd = Math.sqrt(sd);
		// SE_Cmax=sd;
		SE_Cmax = sd / Math.sqrt(n);

	}

	public double se_AUC(double[] t, double[] C, int profileNumber) {

		
		int[] noOfObsInEachProfile = new int[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject()];
	
		String[][] tempDataMatrix = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getDatForSparse(); 
		
		int missingValCount = 0;
		
		for (int i = 0; i < tempDataMatrix.length; i++) {
			if(Double.parseDouble(tempDataMatrix[i][tempDataMatrix[0].length-1]) == -3.14159265359)
				missingValCount++;
		}
		
		String[][] dataMatrix = new String[tempDataMatrix.length - missingValCount][tempDataMatrix[0].length];
		
		int count = 0;
		for (int i = 0; i < tempDataMatrix.length; i++) {
			if(Double.parseDouble(tempDataMatrix[i][tempDataMatrix[0].length-1]) != -3.14159265359)
			{
				dataMatrix[count] = tempDataMatrix[i];
				count++;
			}
				
		}
		
		int noOfSortVar = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getSortVariablesListVector().size();
		
		noOfObsInEachProfile = detectNoOfDataPtsInEachProfile(dataMatrix.length, noOfSortVar, dataMatrix);
		String[] animalId = new String[noOfObsInEachProfile[profileNumber]];
		X_sparse = new double[noOfObsInEachProfile[profileNumber]];
		Y_sparse = new double[noOfObsInEachProfile[profileNumber]];
	
		
		int countObs = 0;
		for (int i = 0; i < profileNumber; i++)
			countObs = countObs + noOfObsInEachProfile[i];
		
		int modelType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getModelType();

		if(modelType == 0)
		{
			
			for (int i = 0; i < noOfObsInEachProfile[profileNumber]; i++) {
				
				animalId[i] =  dataMatrix[i+ countObs][dataMatrix[0].length - 2];
				
				X_sparse[i] = Double.parseDouble(dataMatrix[i+ countObs][dataMatrix[0].length - 3]);
				
				Y_sparse[i] = Double.parseDouble(dataMatrix[i+ countObs][dataMatrix[0].length - 1]);
				
			}
		}else if(modelType == 1)
		{
			
			double[] conc = new double[X_sparse.length];
			double[] volume = new double[Y_sparse.length];
			double[] startTime = new double[Y_sparse.length];
			double[] endTime = new double[Y_sparse.length];
			for (int i = 0; i < noOfObsInEachProfile[profileNumber]; i++) {
				
				animalId[i] =  dataMatrix[i+ countObs][dataMatrix[0].length - 3];
				
				startTime[i] = Double.parseDouble(dataMatrix[i+ countObs][dataMatrix[0].length - 5]);
				endTime[i] = Double.parseDouble(dataMatrix[i+ countObs][dataMatrix[0].length - 4]);
				conc[i] = Double.parseDouble(dataMatrix[i+ countObs][dataMatrix[0].length - 2]);
				
				volume[i] = Double.parseDouble(dataMatrix[i+ countObs][dataMatrix[0].length - 1]);
				
				X_sparse[i] = (startTime[i] + endTime[i])/2;
				
				Y_sparse[i] = conc[i]* volume[i]/ (endTime[i] - startTime[i]);
				
			}
			
		}
		
		

	
		int no_sampleForCmax = 0;
		int a = 0;
		double tmax = t[findVal(C, Cmax)];
		for (a = 0; a < X_sparse.length; a++) {
			if (X_sparse[a] == tmax)
				no_sampleForCmax++;

		}
		double[] conc = new double[no_sampleForCmax];
		no_sampleForCmax = 0;
		for (a = 0; a < X_sparse.length; a++) {
			if (X_sparse[a] == tmax) {
				conc[no_sampleForCmax] = Y_sparse[a];
				no_sampleForCmax++;
			}

		}

		se_Cmax(tmax, conc, Cmax);
		

		double Var_AUC = 0;
		//int no_total_obs = X_sparse.length;
		int no_distinct_obs = t.length;
		int[] no_sample = new int[no_distinct_obs];
		double sum = 0;

		double[] sample_variance = new double[no_distinct_obs];
		double[][] sample_covariance = new double[no_distinct_obs][no_distinct_obs];
		double[] weight = new double[no_distinct_obs];
		int[][] Rij = new int[no_distinct_obs][no_distinct_obs];

		weight[0] = (t[1] - t[0]) / 2;
		for (int i = 1; i < t.length - 1; i++)
			weight[i] = (t[i + 1] - t[i]) / 2;

		weight[t.length - 1] = (t[t.length - 1] - t[t.length - 2]) / 2;
		
		//weight = calculateWeight(t, C);

		count = 0;
		no_sample[count] = 1;

		for (int i = 0; i < X_sparse.length - 1; i++) {
			if (X_sparse[i + 1] == X_sparse[i])
				no_sample[count] = no_sample[count] + 1;
			else {
				count++;
				no_sample[count] = no_sample[count] + 1;
			}
		}
		
		
		int lastNonZeroIndex = lastNonZero(C);
		
		double[] tForAucLast = new double[lastNonZeroIndex + 1];
		double[] cForAucLast = new double[lastNonZeroIndex + 1];
		
		
		for (int i = 0; i <= lastNonZeroIndex; i++) {
			tForAucLast[i] = t[i];
			cForAucLast[i] = C[i];
		}
		
		int totalNonZeroSample = 0;
		
		for (int i = 0; i <= lastNonZeroIndex; i++) {
			totalNonZeroSample = totalNonZeroSample + no_sample[i];
		}
		
		double[] xSparseForAucLast = new double[totalNonZeroSample];
		double[] ySparseForAucLast = new double[totalNonZeroSample];
		String[] idForAucLast = new String[totalNonZeroSample];
		
		for (int i = 0; i < totalNonZeroSample; i++) {
			xSparseForAucLast[i] = X_sparse[i];
			ySparseForAucLast[i] = Y_sparse[i];
			idForAucLast[i] = animalId[i];
		}
		
		
		standErrorOfAucLast(tForAucLast, cForAucLast, xSparseForAucLast, ySparseForAucLast, idForAucLast, profileNumber);

		
		double sum1 = 0;
		int sampleCount = 0;
		int sampleCountForRij = 0;
		int sampleCountForCovarience = 0;
		

		for (int i = 0; i < no_distinct_obs; i++) {
			
			for (int j = 0; j < no_distinct_obs; j++) {
				
				int noOfSampleUptoIth = 0;
				int noOfSampleUptoJth = 0;
				
				for (int k = 0; k < i; k++) {
					noOfSampleUptoIth = noOfSampleUptoIth + no_sample[k];
				}
				
				for (int k = 0; k < j; k++) {
					noOfSampleUptoJth = noOfSampleUptoJth + no_sample[k];
				}
				
				int tempCount =0;
				for (int j2 = 0; j2 < no_sample[i]; j2++) {
					
					for (int k = 0; k < no_sample[j]; k++) {
						
						if (animalId[noOfSampleUptoIth + j2 ]
										.equals(animalId[noOfSampleUptoJth + k])) {
									tempCount ++;

								}
						
					}
				}
				Rij[i][j] = tempCount;
				

			}
		}
		
		
		
		double cMean1;
		double cMean2;
		
		// for computing standard error corresponding to each time point
		
		if(t[0] !=0 )
		{
			double[] tempData = new double[2];
			tempData[0] = 0;
			tempData[1] = 0;
			
			stdError.add(tempData);
		}
		
		for (int i = 0; i < no_distinct_obs; i++) {
			
			 sum = 0;
			for (int k = 0; k < no_sample[i]; k++) {
				sum = sum + (Y_sparse[sampleCount + k] - C[i])
						* (Y_sparse[sampleCount + k] - C[i]);
				
			}
			sample_variance[i] = sum / (no_sample[i] -1);
			//sample_variance[i] = Math.sqrt(sample_variance[i]);
			sampleCount = sampleCount + no_sample[i];
			
			//for computing standard error corresponding to each time point
			double[] tempData = new double[2];
			tempData[0] = Math.sqrt(sample_variance[i]/no_sample[i]);
			tempData[1] = no_sample[i];
			stdError.add(tempData);
			
			
			for (int j = 0; j < no_distinct_obs; j++) {
				
				
				int noOfSampleUptoIth = 0;
				int noOfSampleUptoJth = 0;
				
				for (int k = 0; k < i; k++) {
					noOfSampleUptoIth = noOfSampleUptoIth + no_sample[k];
				}
				
				for (int k = 0; k < j; k++) {
					noOfSampleUptoJth = noOfSampleUptoJth + no_sample[k];
				}
				
				double[] C1 = new double[Rij[i][j]];
				double[] C2 = new double[Rij[i][j]];
				
				int tempCount =0;
				for (int j2 = 0; j2 < no_sample[i]; j2++) {
					
					for (int k = 0; k < no_sample[j]; k++) {
						
						if (animalId[noOfSampleUptoIth + j2 ]
										.equals(animalId[noOfSampleUptoJth + k])) {
									
							C1[tempCount] = Y_sparse[noOfSampleUptoIth + j2 ] ;
							C2[tempCount] = Y_sparse[noOfSampleUptoJth + k ] ;
							
							tempCount ++;

								}
						
					}
				}
			
				cMean1 = C[i];
				cMean2 = C[j];
				sample_covariance[i][j] = computeSampleCovar(C1, C2, cMean1, cMean2, Rij[i][j], no_sample[i], no_sample[j]);
				
			}
			
		}
		
		
		
		for (int i = 0; i < no_distinct_obs; i++) {
			Var_AUC = Var_AUC + (weight[i] * weight[i] * sample_variance[i])
					/ no_sample[i];
			for (int j = 0; j < i; j++) {
				Var_AUC = Var_AUC + ( (2*weight[i] * weight[j] * Rij[i][j]
						* sample_covariance[i][j]) / (no_sample[i] * no_sample[j]));

			}
		}
		
		SE_AUCall = Math.sqrt(Math.abs(Var_AUC));
			
		return Math.sqrt(Math.abs(Var_AUC));
		
		
	}

	
	public double[] calculateWeight(double[] t, double[] C)
	{
		double[] weight = new double[t.length];
		
		int maxConcIndex = findVal(Yp, Cmax);
		if (mode1 == 1)
			maxConcIndex = findVal(Yp, C[0]);
		
		if(method == 0)
		{
			
			for (int i = 0; i < t.length - 1; i++) {
				if (i + 1 <= maxConcIndex) {
					weight[i] = (t[i + 1] - t[i]) / 2.0;
					
				} else {
					if (C[i] == 0 || C[i + 1] == 0) {
						weight[i] = (t[i + 1] - t[i])/ 2.0;
						
					} else {
						weight[i] = (t[i + 1] - t[i]) 
								/ Math.log(C[i + 1] / C[i]);
						
					}

				}
			}
		}else if(method == 1)
		{
			
		}else if(method == 2)
		{
			
		}else if(method == 3)
		{
			
		}
		
		
		return weight;
	}
	
	void urineDataProcessing(double[] startTime, double[] endTime, double[] conc, double[] volume) {
		int no_obs = 0;
		no_obs = startTime.length;
		double[] midPoint = new double[no_obs];
		double[] excretionRate = new double[no_obs];

		for (int i = 0; i < no_obs; i++) {
			midPoint[i] = (startTime[i] + endTime[i]) / 2;
			excretionRate[i] = conc[i]
					* volume[i]
					/ (endTime[i] - startTime[i]);
		}
		
		System.out.println();
	}
	
	
	private void standErrorOfAucLast(double[] tForAucLast,
			double[] cForAucLast, double[] xSparseForAucLast,
			double[] ySparseForAucLast, String[] idForAucLast, int profileNumber) {

		// double AUC = 0; double AUMC = 0;

		System.out
				.println("&&&& in standard error calculation for sparse sampling");
		
		double[] t = tForAucLast;
		double[] C = cForAucLast;
		
	
		
		int[] noOfObsInEachProfile = new int[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject()];
	
		String[][] dataMatrix = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getNcaDat(); 
		
		int noOfSortVar = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getSortVariablesListVector().size();
		
		noOfObsInEachProfile = detectNoOfDataPtsInEachProfile(dataMatrix.length, noOfSortVar, dataMatrix);
		
		
		double[] X_sparse = xSparseForAucLast;
		double[] Y_sparse = ySparseForAucLast;
		
		
		String[] animalId = idForAucLast;
		
		
		
		
		
		int countObs = 0;
		for (int i = 0; i < profileNumber; i++)
			countObs = countObs + noOfObsInEachProfile[i];

			

		double Var_AUC = 0;
		int no_total_obs = X_sparse.length;
		int no_distinct_obs = t.length;
		int[] no_sample = new int[no_distinct_obs];
		double sum = 0;

		double[] sample_variance = new double[no_distinct_obs];
		double[][] sample_covariance = new double[no_distinct_obs][no_distinct_obs];
		double[] weight = new double[no_distinct_obs];
		int[][] Rij = new int[no_distinct_obs][no_distinct_obs];

		weight[0] = (t[1] - t[0]) / 2;
		for (int i = 1; i < t.length - 1; i++)
			weight[i] = (t[i + 1] - t[i -1]) / 2;

		weight[t.length - 1] = (t[t.length - 1] - t[t.length - 2]) / 2;

		int count = 0;
		no_sample[count] = 1;

		for (int i = 0; i < X_sparse.length - 1; i++) {
			if (X_sparse[i + 1] == X_sparse[i])
				no_sample[count] = no_sample[count] + 1;
			else {
				count++;
				no_sample[count] = no_sample[count] + 1;
			}
		}
		
		
		

		for (int i = 0; i < no_sample.length; i++)
			System.out.println(i + "th sample count" + no_sample[i]);

		double sum1 = 0;
		int sampleCount = 0;
		int sampleCountForRij = 0;
		int sampleCountForCovarience = 0;
		

		for (int i = 0; i < no_distinct_obs; i++) {
			
			for (int j = 0; j < no_distinct_obs; j++) {
				
				int noOfSampleUptoIth = 0;
				int noOfSampleUptoJth = 0;
				
				for (int k = 0; k < i; k++) {
					noOfSampleUptoIth = noOfSampleUptoIth + no_sample[k];
				}
				
				for (int k = 0; k < j; k++) {
					noOfSampleUptoJth = noOfSampleUptoJth + no_sample[k];
				}
				
				int tempCount =0;
				for (int j2 = 0; j2 < no_sample[i]; j2++) {
					
					for (int k = 0; k < no_sample[j]; k++) {
						
						if (animalId[noOfSampleUptoIth + j2 ]
										.equals(animalId[noOfSampleUptoJth + k])) {
									tempCount ++;

								}
						
					}
				}
				Rij[i][j] = tempCount;
				

			}
		}
		
		
		
		double cMean1;
		double cMean2;
		
		for (int i = 0; i < no_distinct_obs; i++) {
			sum = 0;
			
			for (int k = 0; k < no_sample[i]; k++) {
				sum = sum + (Y_sparse[sampleCount + k] - C[i])
						* (Y_sparse[sampleCount + k] - C[i]);
				System.out.println(" conc" + Y_sparse[sampleCount + k]);
			}
			sample_variance[i] = sum / (no_sample[i] - 1);
			//sample_variance[i] = Math.sqrt(sample_variance[i]);
			sampleCount = sampleCount + no_sample[i];
			for (int j = 0; j < no_distinct_obs; j++) {
				
				
				int noOfSampleUptoIth = 0;
				int noOfSampleUptoJth = 0;
				
				for (int k = 0; k < i; k++) {
					noOfSampleUptoIth = noOfSampleUptoIth + no_sample[k];
				}
				
				for (int k = 0; k < j; k++) {
					noOfSampleUptoJth = noOfSampleUptoJth + no_sample[k];
				}
				
				double[] C1 = new double[Rij[i][j]];
				double[] C2 = new double[Rij[i][j]];
				
				int tempCount =0;
				for (int j2 = 0; j2 < no_sample[i]; j2++) {
					
					for (int k = 0; k < no_sample[j]; k++) {
						
						if (animalId[noOfSampleUptoIth + j2 ]
										.equals(animalId[noOfSampleUptoJth + k])) {
									
							C1[tempCount] = Y_sparse[noOfSampleUptoIth + j2 ] ;
							C2[tempCount] = Y_sparse[noOfSampleUptoJth + k ] ;
							
							tempCount ++;

								}
						
					}
				}
			
				cMean1 = C[i];
				cMean2 = C[j];
				sample_covariance[i][j] = computeSampleCovar(C1, C2, cMean1, cMean2, Rij[i][j], no_sample[i], no_sample[j]);
				
			}
			
		}
		
		
		
		for (int i = 0; i < no_distinct_obs; i++) {
			Var_AUC = Var_AUC + weight[i] * weight[i] * sample_variance[i]
					/ no_sample[i];
			for (int j = 0; j < i; j++) {
				Var_AUC = Var_AUC + ( (2*weight[i] * weight[j] * Rij[i][j]
						* sample_covariance[i][j]) / (no_sample[i] * no_sample[j]));

			}
		}
		
		SE_AUClast = Math.sqrt(Math.abs(Var_AUC));
			
	
	}

	private double computeSampleCovar(double[] c1, double[] c2, double cMean1, 
			double cMean2, int i, int j, int k) {
		double coVar = 0;

		if (i == 1 && (j == 1 || k == 1)) {
			coVar = 0;
		} else {
			for (int l = 0; l < i; l++) {
				coVar = coVar + (c1[l] - cMean1) * (c2[l] - cMean2)
						/ ((i-1) + (1 - (i / j)) * (1 - (i / k)));
			}
		}

		return coVar;
	}

	private int[] detectNoOfDataPtsInEachProfile(int Nobs, int sortVariableCount, String[][] dataMatrix) {
		
		int[] noOfObsInEachProfile = new int[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject()] ;
		
		
		
		
		int count = 0;
		noOfObsInEachProfile[count] = 1;
		int numberOfSortLocal = sortVariableCount;
		for (int i = 0; i < Nobs - 1; i++) {
			
			
			while(numberOfSortLocal > 0){
			
				if(dataMatrix[i][numberOfSortLocal - 1].equals(dataMatrix[i+1][numberOfSortLocal - 1])){
					numberOfSortLocal--;
				} else {
					System.out.println("in the else loop");
					break;
				}
			}
			
			if(numberOfSortLocal == 0){
				noOfObsInEachProfile[count]++;
			} else {
				count++;
				noOfObsInEachProfile[count]++;
			}
			numberOfSortLocal = sortVariableCount;
			
		}
		// subject_obs[count]=subject_obs[count]+1;
	//	no_subject = count + 1;
		
		
		
		return noOfObsInEachProfile;
		
	}


	public void SimpsonsRule(double[] t, double[] C) {
		double AUC = 0;
		for (int i = 0; i < t.length - 2; i = i + 2) {
			double tempAUC = (t[i + 2] - t[i])
					* (C[i + 2] + 4 * C[i + 1] + C[i]) / 6.0;

			AUC = AUC + tempAUC;

		}
		AUCall = AUC;
	}

	public void linearTrapezoidal(double[] t, double[] C) {
		double AUC = 0;
		double AUMC = 0;
		int lastNonZeroIndex = lastNonZero(C);
		for (int i = 0; i < t.length - 1; i++) {
			double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
			double tempAUMC = (t[i + 1] - t[i])
					* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
			AUC = AUC + tempAUC;
			AUMC = AUMC + tempAUMC;
			AUCValue[i+1] = Math.abs(AUC);
			AUMCValue[i+1] = Math.abs(AUMC);

		}
		AUCall = Math.abs(AUC);
		AUMCall = Math.abs(AUMC);

		if (lastNonZeroIndex == t.length - 1) {
			AUClast = AUCall;
			AUMClast = AUMCall;
		} else {
			AUClast = AUCall - (t[lastNonZeroIndex + 1] - t[lastNonZeroIndex])
					* (C[lastNonZeroIndex + 1] + C[lastNonZeroIndex]) / 2.0;
			AUMClast = AUMCall
					- (t[lastNonZeroIndex + 1] - t[lastNonZeroIndex])
					* (t[lastNonZeroIndex + 1] * C[lastNonZeroIndex + 1] + t[lastNonZeroIndex]
							* C[lastNonZeroIndex]) / 2.0;
		}
	}

	public void linearTrapezoidalTau(double[] t, double[] C, double tau) {
		double AUC = 0;
		double AUMC = 0;
		int lastNonZeroIndex = lastNonZero(C);
		double C_tau;
		int i = 0;
		while (t[i + 1] <= tau && i + 1 < t.length) {
			double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
			double tempAUMC = (t[i + 1] - t[i])
					* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
			AUC = AUC + tempAUC;
			AUMC = AUMC + tempAUMC;
			i++;
		}
		if (t[i] < tau) {
			C_tau = linearInterpolation(t, C, tau);
			if (C[i] == 0 || C_tau == 0) {
				double tempAUC = (tau - t[i]) * (C_tau + C[i]) / 2.0;
				double tempAUMC = (tau - t[i]) * (tau * C_tau + t[i] * C[i])
						/ 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;

			} else {
				double tempAUC = (tau - t[i]) * (C_tau - C[i])
						/ Math.log(C_tau / C[i]);
				double tempAUMC = (tau - t[i]) * (tau * C_tau - t[i] * C[i])
						/ Math.log(C_tau / C[i]) - Math.pow((tau - t[i]), 2)
						* (C_tau - C[i]) / Math.pow(Math.log(C_tau / C[i]), 2);
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
			}
		}

		AUC_tau = Math.abs(AUC);
		AUMC_tau = Math.abs(AUMC);
	}

	public void logTrapezoidal(double[] t, double[] C) {
		double AUC = 0;
		double AUMC = 0;
		int lastNonZeroIndex = lastNonZero(C);
		for (int i = 0; i < t.length - 1; i++) {
			if (C[i] == 0 || C[i + 1] == 0) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
				AUCValue[i+1] = Math.abs(AUC);
				AUMCValue[i+1] = Math.abs(AUMC);

			} else {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
						/ Math.log(C[i + 1] / C[i]);
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] - t[i] * C[i])
						/ Math.log(C[i + 1] / C[i])
						- Math.pow((t[i + 1] - t[i]), 2) * (C[i + 1] - C[i])
						/ Math.pow(Math.log(C[i + 1] / C[i]), 2);
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
				AUCValue[i+1] = Math.abs(AUC);
				AUMCValue[i+1] = Math.abs(AUMC);
			}
		}

		AUCall = Math.abs(AUC);
		AUMCall = Math.abs(AUMC);

		if (lastNonZeroIndex == t.length - 1) {
			AUClast = AUCall;
			AUMClast = AUMCall;
		} else {
			AUClast = AUCall - (t[lastNonZeroIndex + 1] - t[lastNonZeroIndex])
					* (C[lastNonZeroIndex + 1] + C[lastNonZeroIndex]) / 2.0;
			AUMClast = AUMCall
					- (t[lastNonZeroIndex + 1] - t[lastNonZeroIndex])
					* (t[lastNonZeroIndex + 1] * C[lastNonZeroIndex + 1] + t[lastNonZeroIndex]
							* C[lastNonZeroIndex]) / 2.0;
		}

	}

	public void logTrapezoidalTau(double[] t, double[] C, double tau) {
		double AUC = 0;
		double AUMC = 0;
		int lastNonZeroIndex = lastNonZero(C);
		double C_tau;
		int i = 0;
		while (t[i + 1] <= tau && i + 1 < t.length) {
			if (C[i] == 0 || C[i + 1] == 0) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;

			} else {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
						/ Math.log(C[i + 1] / C[i]);
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] - t[i] * C[i])
						/ Math.log(C[i + 1] / C[i])
						- Math.pow((t[i + 1] - t[i]), 2) * (C[i + 1] - C[i])
						/ Math.pow(Math.log(C[i + 1] / C[i]), 2);
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
			}
			i++;
		}
		if (t[i] < tau) {
			C_tau = linearInterpolation(t, C, tau);
			if (C[i] == 0 || C_tau == 0) {
				double tempAUC = (tau - t[i]) * (C_tau + C[i]) / 2.0;
				double tempAUMC = (tau - t[i]) * (tau * C_tau + t[i] * C[i])
						/ 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;

			} else {
				double tempAUC = (tau - t[i]) * (C_tau - C[i])
						/ Math.log(C_tau / C[i]);
				double tempAUMC = (tau - t[i]) * (tau * C_tau - t[i] * C[i])
						/ Math.log(C_tau / C[i]) - Math.pow((tau - t[i]), 2)
						* (C_tau - C[i]) / Math.pow(Math.log(C_tau / C[i]), 2);
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
			}
		}

		AUC_tau = Math.abs(AUC);
		AUMC_tau = Math.abs(AUMC);

	}

	public void linearlogTrapezoidal(double[] t, double[] C) {
		double AUC = 0;
		double AUMC = 0;
		int maxConcIndex = findVal(Yp, Cmax);
		if (mode1 == 1)
			maxConcIndex = findVal(Yp, C[0]);

		int lastNonZeroIndex = lastNonZero(C);
		for (int i = 0; i < t.length - 1; i++) {
			if (i + 1 <= maxConcIndex) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
				AUCValue[i+1] = Math.abs(AUC);
				AUMCValue[i+1] = Math.abs(AUMC);
			} else {
				if (C[i] == 0 || C[i + 1] == 0) {
					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i])
							/ 2.0;
					double tempAUMC = (t[i + 1] - t[i])
							* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;
					AUCValue[i+1] = Math.abs(AUC);
					AUMCValue[i+1] = Math.abs(AUMC);
				} else {
					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
							/ Math.log(C[i + 1] / C[i]);
					double tempAUMC = (t[i + 1] - t[i])
							* (t[i + 1] * C[i + 1] - t[i] * C[i])
							/ Math.log(C[i + 1] / C[i])
							- Math.pow((t[i + 1] - t[i]), 2)
							* (C[i + 1] - C[i])
							/ Math.pow(Math.log(C[i + 1] / C[i]), 2);
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;
					AUCValue[i+1] = Math.abs(AUC);
					AUMCValue[i+1] = Math.abs(AUMC);
				}

			}
		}

		AUCall = Math.abs(AUC);
		AUMCall = Math.abs(AUMC);
		// int lastNonZeroIndex=lastNonZero(C);
		if (lastNonZeroIndex == t.length - 1) {

			AUClast = AUCall;
			AUMClast = AUMCall;
		} else {

			AUClast = AUCall - (t[lastNonZeroIndex + 1] - t[lastNonZeroIndex])
					* (C[lastNonZeroIndex + 1] + C[lastNonZeroIndex]) / 2.0;
			AUMClast = AUMCall
					- (t[lastNonZeroIndex + 1] - t[lastNonZeroIndex])
					* (t[lastNonZeroIndex + 1] * C[lastNonZeroIndex + 1] + t[lastNonZeroIndex]
							* C[lastNonZeroIndex]) / 2.0;
		}

	}

	public void linearlogTrapezoidalTau(double[] t, double[] C, double tau) {
		double AUC = 0;
		double AUMC = 0;
		int maxConcIndex = findVal(Yp, Cmax);
		int lastNonZeroIndex = lastNonZero(C);
		double C_tau;
		int i = 0;
		while (t[i + 1] <= tau && i + 1 < t.length) {
			if (i + 1 <= maxConcIndex) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
			} else {

				if (C[i] == 0 || C[i + 1] == 0) {
					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i])
							/ 2.0;
					double tempAUMC = (t[i + 1] - t[i])
							* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;

				} else {
					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
							/ Math.log(C[i + 1] / C[i]);
					double tempAUMC = (t[i + 1] - t[i])
							* (t[i + 1] * C[i + 1] - t[i] * C[i])
							/ Math.log(C[i + 1] / C[i])
							- Math.pow((t[i + 1] - t[i]), 2)
							* (C[i + 1] - C[i])
							/ Math.pow(Math.log(C[i + 1] / C[i]), 2);
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;
				}
			}
			i++;
		}

		if (t[i] < tau) {
			C_tau = linearInterpolation(t, C, tau);

			if (i + 1 < maxConcIndex) {
				double tempAUC = (tau - t[i]) * (C_tau + C[i]) / 2.0;
				double tempAUMC = (tau - t[i]) * (tau * C_tau + t[i] * C[i])
						/ 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
			} else {
				if (C[i] == 0 || C_tau == 0) {
					double tempAUC = (tau - t[i]) * (C_tau + C[i]) / 2.0;
					double tempAUMC = (tau - t[i])
							* (tau * C_tau + t[i] * C[i]) / 2.0;
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;

				} else {
					double tempAUC = (tau - t[i]) * (C_tau - C[i])
							/ Math.log(C_tau / C[i]);
					double tempAUMC = (tau - t[i])
							* (t[i + 1] * C_tau - t[i] * C[i])
							/ Math.log(C_tau / C[i])
							- Math.pow((tau - t[i]), 2) * (C_tau - C[i])
							/ Math.pow(Math.log(C_tau / C[i]), 2);
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;
				}
			}
		}

		AUC_tau = Math.abs(AUC);
		AUMC_tau = Math.abs(AUMC);
		
	}

	public void linearUplogDownTrapezoidal(double[] t, double[] C) {

		double AUC = 0;
		double AUMC = 0;
		int lastNonZeroIndex = lastNonZero(C);

		for (int i = 0; i < t.length - 1; i++) {
			if (C[i + 1] >= C[i]) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
				AUCValue[i+1] = Math.abs(AUC);
				AUMCValue[i+1] = Math.abs(AUMC);
			} else {
				if (C[i] == 0 || C[i + 1] == 0) {
					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i])
							/ 2.0;
					double tempAUMC = (t[i + 1] - t[i])
							* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;
					AUCValue[i+1] = Math.abs(AUC);
					AUMCValue[i+1] = Math.abs(AUMC);

				} else {

					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
							/ Math.log(C[i + 1] / C[i]);
					double tempAUMC = (t[i + 1] - t[i])
							* (t[i + 1] * C[i + 1] - t[i] * C[i])
							/ Math.log(C[i + 1] / C[i])
							- Math.pow((t[i + 1] - t[i]), 2)
							* (C[i + 1] - C[i])
							/ Math.pow(Math.log(C[i + 1] / C[i]), 2);
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;
					AUCValue[i+1] = Math.abs(AUC);
					AUMCValue[i+1] = Math.abs(AUMC);
				}
			}
		}

		AUCall = Math.abs(AUC);
		AUMCall = Math.abs(AUMC);

		if (lastNonZeroIndex == t.length - 1) {
			AUClast = AUCall;
			AUMClast = AUMCall;
		} else {
			AUClast = AUCall - (t[lastNonZeroIndex + 1] - t[lastNonZeroIndex])
					* (C[lastNonZeroIndex + 1] + C[lastNonZeroIndex]) / 2.0;
			AUMClast = AUMCall
					- (t[lastNonZeroIndex + 1] - t[lastNonZeroIndex])
					* (t[lastNonZeroIndex + 1] * C[lastNonZeroIndex + 1] + t[lastNonZeroIndex]
							* C[lastNonZeroIndex]) / 2.0;
		}

	}

	public void linearUplogDownTrapezoidalTau(double[] t, double[] C, double tau) {

		double AUC = 0;
		double AUMC = 0;
		double C_tau;
		int i = 0;
		while (t[i + 1] <= tau && (i + 1) < t.length) {
			if (C[i + 1] >= C[i]) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
			} else {
				if (C[i] == 0 || C[i + 1] == 0) {
					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i])
							/ 2.0;
					double tempAUMC = (t[i + 1] - t[i])
							* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;

				} else {

					double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
							/ Math.log(C[i + 1] / C[i]);
					double tempAUMC = (t[i + 1] - t[i])
							* (t[i + 1] * C[i + 1] - t[i] * C[i])
							/ Math.log(C[i + 1] / C[i])
							- Math.pow((t[i + 1] - t[i]), 2)
							* (C[i + 1] - C[i])
							/ Math.pow(Math.log(C[i + 1] / C[i]), 2);
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;
				}
			}
			i++;
		}

		if (t[i] < tau) {
			C_tau = linearInterpolation(t, C, tau);
			if (C_tau >= C[i]) {
				double tempAUC = (tau - t[i]) * (C_tau + C[i]) / 2.0;
				double tempAUMC = (tau - t[i]) * (tau * C_tau + t[i] * C[i])
						/ 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
			} else {
				if (C[i] == 0 || C_tau == 0) {
					double tempAUC = (tau - t[i]) * (C_tau + C[i]) / 2.0;
					double tempAUMC = (tau - t[i])
							* (tau * C_tau + t[i] * C[i]) / 2.0;
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;

				} else {
					double tempAUC = (tau - t[i]) * (C_tau - C[i])
							/ Math.log(C_tau / C[i]);
					double tempAUMC = (tau - t[i])
							* (tau * C_tau - t[i] * C[i])
							/ Math.log(C_tau / C[i])
							- Math.pow((tau - t[i]), 2) * (C_tau - C[i])
							/ Math.pow(Math.log(C_tau / C[i]), 2);
					AUC = AUC + tempAUC;
					AUMC = AUMC + tempAUMC;
				}
			}
		}

		AUC_tau = Math.abs(AUC);
		AUMC_tau = Math.abs(AUMC);
	}

	public void linearlogTrapezoidal_tau(double[] t, double[] C, double tau) {

		double AUC = 0;
		double AUMC = 0;
		double C_tau;
		int i = 0;
		while (t[i + 1] <= tau && i < t.length) {

			if (C[i + 1] >= C[i]) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
			} else {

				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
						/ Math.log(C[i + 1] / C[i]);
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] - t[i] * C[i])
						/ Math.log(C[i + 1] / C[i])
						- Math.pow((t[i + 1] - t[i]), 2) * (C[i + 1] - C[i])
						/ Math.pow(Math.log(C[i + 1] / C[i]), 2);
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;

			}
			i++;
		}
		if (t[i] < tau) {
			C_tau = linearInterpolation(t, C, tau);
			if (C[i + 1] >= C[i]) {
				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] + C[i]) / 2.0;
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] + t[i] * C[i]) / 2.0;
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;
			} else {

				double tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
						/ Math.log(C[i + 1] / C[i]);
				double tempAUMC = (t[i + 1] - t[i])
						* (t[i + 1] * C[i + 1] - t[i] * C[i])
						/ Math.log(C[i + 1] / C[i])
						- Math.pow((t[i + 1] - t[i]), 2) * (C[i + 1] - C[i])
						/ Math.pow(Math.log(C[i + 1] / C[i]), 2);
				AUC = AUC + tempAUC;
				AUMC = AUMC + tempAUMC;

			}
		}

		AUC_tau = Math.abs(AUC);
		AUMC_tau = Math.abs(AUMC);
	}

	public void logTrapezoidal_tau(double[] t, double[] C, double tau) {
		double AUC = 0;
		double AUMC = 0;
		double tempAUC = 0;
		double tempAUMC = 0;
		double C_tau;
		int i = 0;
		while (t[i + 1] <= tau && i < t.length) {
			tempAUC = (t[i + 1] - t[i]) * (C[i + 1] - C[i])
					/ Math.log(C[i + 1] / C[i]);
			tempAUMC = (t[i + 1] - t[i]) * (t[i + 1] * C[i + 1] - t[i] * C[i])
					/ Math.log(C[i + 1] / C[i])
					- Math.pow((t[i + 1] - t[i]), 2) * (C[i + 1] - C[i])
					/ Math.pow(Math.log(C[i + 1] / C[i]), 2);
			AUC = AUC + tempAUC;
			AUMC = AUMC + tempAUMC;
			i++;

		}
		if (t[i] < tau) {
			C_tau = linearInterpolation(t, C, tau);
			tempAUC = (tau - t[i]) * (C_tau - C[i]) / Math.log(C_tau / C[i]);
			tempAUMC = (tau - t[i]) * (tau * C_tau - t[i] * C[i])
					/ Math.log(C_tau / C[i]) - Math.pow((tau - t[i]), 2)
					* (C_tau - C[i]) / Math.pow(Math.log(C_tau / C[i]), 2);
			AUC = AUC + tempAUC;
			AUMC = AUMC + tempAUMC;
		}

		AUC_tau = Math.abs(AUC);
		AUMC_tau = Math.abs(AUMC);
	}

	double linearInterpolation(double[] t, double[] C, double tstar) {
		double Cstar = 0;
		int ifInterpReq = findVal(t, tstar);
		int interpIdx = 0;
		if (ifInterpReq == -1) {
			for (int i = 0; i < t.length - 1; i++) {
				if (tstar > t[i] && tstar < t[i + 1]) {
					interpIdx = i; 
					break;
				}
			}

			Cstar = C[interpIdx]
					+ Math.abs((tstar - t[interpIdx])
							/ (t[interpIdx + 1] - t[interpIdx]))
					* (C[interpIdx + 1] - C[interpIdx]);
		} else
			Cstar = C[ifInterpReq];

		return Cstar;
	}

	double logInterpolation(double[] t, double[] C, double tstar) {
		double Cstar = 0;
		int ifInterpReq = findVal(t, tstar);
		int interpIdx = 0;
		if (ifInterpReq == -1) {
			for (int i = 0; i < t.length - 1; i++) {
				if (tstar > t[i] && tstar < t[i + 1]) {
					interpIdx = i;
					break;
				}
			}
			double term1 = Math.log(C[interpIdx]);
			double term2 = Math.abs((tstar - t[interpIdx])
					/ (t[interpIdx + 1] - t[interpIdx]));
			double term3 = Math.log(C[interpIdx + 1]) - Math.log(C[interpIdx]);
			Cstar = Math.exp(term1 + term2 * term3);
		} else
			Cstar = C[ifInterpReq];

		return Cstar;
	}

	public double linearUplogDownInterpolation(double[] t, double[] C,
			double tstar) {
		int maxConcIndex;
		double maxConc = maxVal(C);
		maxConcIndex = findVal(C, maxConc);

		double Cstar = 0;
		int ifInterpReq = findVal(t, tstar);
		int interpIdx = 0;
		if (tstar > t[maxConcIndex]) {
			if (ifInterpReq == -1) {
				for (int i = 0; i < t.length - 1; i++) {
					if (tstar > t[i] && tstar < t[i + 1]) {
						interpIdx = i;
						break;
					}
				}
				double term1 = Math.log(C[interpIdx]);
				double term2 = Math.abs((tstar - t[interpIdx])
						/ (t[interpIdx + 1] - t[interpIdx]));
				double term3 = Math.log(C[interpIdx + 1])
						- Math.log(C[interpIdx]);
				Cstar = Math.exp(term1 + term2 * term3);
			} else
				Cstar = C[ifInterpReq];
		} else {
			if (ifInterpReq == -1) {
				for (int i = 0; i < t.length - 1; i++) {
					if (tstar > t[i] && tstar < t[i + 1]) {
						interpIdx = i;
						break;
					}
				}

				Cstar = C[interpIdx]
						+ Math.abs((tstar - t[interpIdx])
								/ (t[interpIdx + 1] - t[interpIdx]))
						* (C[interpIdx + 1] - C[interpIdx]);
			} else
				Cstar = C[ifInterpReq];
		}

		return Cstar;

	}

	public void partialAreas(double[] lowerT, double[] upperT) {
		int nPts = lowerT.length;

		for (int i = 0; i < noPartialAreas; i++) {
			if (upperT[i] > Yp[Yp.length - 1]) {

			}
		}

		/***/
		if (method == 1) {
			// findVal(X, lowerT[i]);
		}

	}

	double round(double value, int decimalPlace) {
		double power_of_ten = 1;
		while (decimalPlace-- > 0) {
			power_of_ten *= 10.0;
		}
		return Math.round(value * power_of_ten) / power_of_ten;
	}

	double Correlation(double Ax[], double Bx[]) {
		// int row = Ax.length;
		double Ra = 0;
		// System.out.println("########### In correlation coefficient calculation");
		int row = Ax.length;
		

		System.out.println("$$$$$$ points for correlation");
		for (int i = 0; i < Bx.length; i++)
			System.out.println(Ax[i] + "\t" + Bx[i]);

		for (int i = 0; i < Bx.length; i++)
			Bx[i] = Math.log(Bx[i]);

		double SumAx = 0;
		double SumBx = 0;
		double SumABx = 0;
		double SumA_Sq = 0;
		double SumB_Sq = 0;

		for (int i = 0; i < row; i++) {
			SumAx = SumAx + Ax[i];
			SumBx = SumBx + Bx[i];
			SumABx = SumABx + (Ax[i] * Bx[i]);
			SumA_Sq = SumA_Sq + (Ax[i] * Ax[i]);
			SumB_Sq = SumB_Sq + (Bx[i] * Bx[i]);
		}

		double t1 = row * SumA_Sq - Math.pow(SumAx, 2);
		t1 = Math.sqrt(t1);
		double t2 = row * SumB_Sq - Math.pow(SumBx, 2);
		t2 = Math.sqrt(t2);

		Ra = ((row * SumABx) - (SumAx * SumBx)) / (t1 * t2);
		// Ra = Math.abs(Ra);
		// System.out.println("########### correlation coefficient= "+Ra);
		return Ra;
	}

	public void decideSufficiencyOfProfile() {

		int noOfNonMissingPoint = 0;

		for (int i = 0; i < Xp.length; i++) {
			if (Double.isNaN(Yp[i]) == true) {
				Yp[i] = -3.14159265359;
			}

			if (Yp[i] == -3.14159265359) {

			} else {
				noOfNonMissingPoint++;
			}

		}

		if (noOfNonMissingPoint >= 2) {
			ifCalculateParam = true;
			

		} else {
			
			ifCalculateParam = false;
		}
		
		double[] tempX = new double[Xp.length];
		double[] tempY = new double[Yp.length];

		for (int i = 0; i < tempY.length; i++) {
			tempX[i] = Xp[i];
			tempY[i] = Yp[i];
		}

		Xp = new double[noOfNonMissingPoint];
		Yp = new double[noOfNonMissingPoint];

		int count = 0;
		for (int i = 0; i < tempX.length; i++) {

			if (tempY[i] == -3.14159265359) {

			} else {
				Xp[count] = tempX[i];
				Yp[count] = tempY[i];
				count++;
			}
		}
		
		
		
		// for original data
		
		noOfNonMissingPoint = 0;

		for (int i = 0; i < X.length; i++) {
			if (Double.isNaN(Y[i]) == true) {
				Y[i] = -3.14159265359;
			}

			if (Y[i] == -3.14159265359) {

			} else {
				noOfNonMissingPoint++;
			}

		}

		tempX = new double[X.length];
		tempY = new double[Y.length];

		for (int i = 0; i < tempY.length; i++) {
			tempX[i] = X[i];
			tempY[i] = Y[i];
		}

		X = new double[noOfNonMissingPoint];
		Y = new double[noOfNonMissingPoint];

		count = 0;
		for (int i = 0; i < tempX.length; i++) {

			if (tempY[i] == -3.14159265359) {

			} else {
				X[count] = tempX[i];
				Y[count] = tempY[i];
				count++;
			}
		}
		
		
		
		
	}

}
