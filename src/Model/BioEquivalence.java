package Model;

import org.apache.commons.math3.distribution.FDistribution;

import org.apache.commons.math3.distribution.TDistribution;

import view.ApplicationInfo;
import view.DisplayContents;
import view.multipleLevelSorting;

import Common.DataReader2;

import java.lang.Math;
import java.util.ArrayList;


public class BioEquivalence {

	double alpha;
	double UL, LL;
	double[] y11;
	double[] y21;
	double[] y12;
	double[] y22;
	int n;
	DataReader2 data;
	double[][] subjects;
	WorkSheetOutputs wsoutputs;
	public BioEquivalence(ApplicationInfo appInfo) {

		wsoutputs = new WorkSheetOutputs();
		setVariables(appInfo);
	
		String[][] tempDat = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBeInfo().getProcessingInputs().getBeDat();
		
		int numDepntVar = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBeInfo().getProcessingInputs().getMappingDataObj()
								.getCarryAlongVariablesListVector().size();
		
		for(int i=0;i<tempDat.length;i++){
			for(int j=0;j<tempDat[i].length;j++){
				//subjects[i][j] = (tempDat[i][j]);
				System.out.print(tempDat[i][j]+"	");
			}
			System.out.println();
		}

		//As this implementation is for 2*2 cross over, all the implementations are so constrained..
		ArrayList<String[][]> al = getFourSets(tempDat);
		
		for(int i=0;i<numDepntVar;i++){
			performBioEquivalance(al, i);
		}
		
		setResults();
		
	}

	private void setResults() {
		// TODO Auto-generated method stub
		String[][] beOutput = getBEoutput();
		//String[][] anaovaOutput = getAnovaOutput();
	wsoutputs.	getSpreadSheetOutputs().setVerticalParameters(beOutput);
	wsoutputs.setParameterValuesAL(outAL);
		
	}

	/*private String[][] getAnovaOutput() {
		String[][] out = new String[][]
	
	   return null;
	}*/

	private String[][] getBEoutput() {
		String[][] out = new String[11][2];
		
		out[0][0] = "LsMean_Ref";
		out[0][1] = LsMean_Ref+"";
		out[1][0] = "LsMean_test"; 
		out[1][1] = LsMean_test+"";
		out[2][0] = "Diff_Lse";
		out[2][1] = Diff_Lse + "";
		out[3][0] = "SE_Diff_Lse";
		out[3][1] = SE_Diff_Lse+"";
		out[4][0] = "SE_LsMean_test";
		out[4][1] = SE_LsMean_test+"";
		out[5][0] = "SE_LsMean_Ref";
		out[5][1] = SE_LsMean_Ref+"";
		out[6][0] = "Cl_Lower";
		out[6][1] = Cl_Lower+"";
		out[7][0] = "Cl_Upper";
		out[7][1] = Cl_Upper+"";
		out[8][0] = "GeoMean";
		out[8][1] = GeoMean + "";
		out[9][0] = "Antiln_Cl_Lower";
		out[9][1] = Antiln_Cl_Lower+"";
		out[10][0] = "Antiln_Cl_Upper";
		out[10][1] = Antiln_Cl_Upper+"";
		return out;
	}

	int selectedIndex;
	private void setVariables(ApplicationInfo appInfo) {

		
		selectedIndex = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBeInfo().getProcessingInputs().getBeDepVarTransComboIndex();
		if((selectedIndex == 0) || selectedIndex == 2){			
			UL = Math.log(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBeInfo().getProcessingInputs().getBeUpperLimit());
			
			LL = Math.log(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBeInfo().getProcessingInputs().getBeLowerLimit());
		} else if((selectedIndex == 1) || selectedIndex == 3){
			
			
			UL = Math.log10(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBeInfo().getProcessingInputs().getBeUpperLimit());
			
			LL = Math.log10(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBeInfo().getProcessingInputs().getBeLowerLimit());
		
		}
		
		
		alpha = 1-(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBeInfo().getProcessingInputs().getBeConfInterval() / 100);
	}

	private void performBioEquivalance(ArrayList<String[][]> al, int depntVarNumber) {
		// p=cross over number , 2x2 design p*q=2*2=4
		//subjects = data.fileArray;;
		subjects = getSubjects(al, depntVarNumber);
		
		subjects = getLnTransforamtion(subjects);
	//	subjects = new double[2][];

		n = (int)((0.5) * subjects[0].length);
		
		tDistribInst = new TDistribution(2 * n - 2);

		y11 = new double[n];
		y21 = new double[n];
		y12 = new double[n];
		y22 = new double[n];

		for (int i = 0; i < n; i++) {
			y11[i] = subjects[0][i];
			y21[i] = subjects[0][i + n];
			y12[i] = subjects[1][i];
			y22[i] = subjects[1][i + n];

		}
		
		getConfidenceInterval();
		getAnova();

		
		getTest();
		getCv();
		getEquatilyofvariance(); //Pitman_Morgan  test
	}

	private double[][] getSubjects(ArrayList<String[][]> al, int depntVarNumber) {
		
		double[] oneOne = getColumn(al.get(2), depntVarNumber);
		double[] twoTwo = getColumn(al.get(3), depntVarNumber);
		double[] oneTwo = getColumn(al.get(1), depntVarNumber);
		double[] twoOne = getColumn(al.get(0), depntVarNumber);
		
		double[] firstRow = getRow(oneOne, oneTwo);
		double[] secondRow = getRow(twoOne, twoTwo);
		
		double[][] subjects = new double[2][firstRow.length];
		for(int i=0;i<subjects[0].length;i++){
			subjects[0][i] = firstRow[i];
			subjects[1][i] = secondRow[i];
		}
		
		return subjects;
	}

	private double[] getRow(double[] oneOne, double[] oneTwo) {
		// TODO Auto-generated method stub
		double[] firstRow = new double[oneOne.length+oneTwo.length];
		int enOfFirst = 0;
		for(int i=0;i<oneOne.length;i++){
			firstRow[i] = oneOne[i];
			enOfFirst = i;
		} 
		
		int index = 0;
		for(int i=(enOfFirst+1);i<firstRow.length;i++){
			firstRow[i] = oneTwo[index++];
		}
		
		return firstRow;
	}

	private double[] getColumn(String[][] set, int depntVarNumber) {
		
		double[] smallSet = new double[set.length];
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int originalColumnIndex = 0;
		int length = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().size();
		for (int i = 0; i < length; i++) {
			String colName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName();
			
			String ourColName =  appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBeInfo().getProcessingInputs()
									.getMappingDataObj().getCarryAlongVariablesListVector().get(depntVarNumber);
			if(colName.equals(ourColName)){
				originalColumnIndex = i;
			}
			
			
		}
		for(int i=0;i<set.length;i++){
			
			smallSet[i] = Double.parseDouble(set[i][originalColumnIndex]);
		}
		
		return smallSet;
	}

	private ArrayList<String[][]> getFourSets(String[][] tempDat) {
		// TODO Auto-generated method stub
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int[] sub_obs =  appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBeInfo().getProcessingInputs().getProfileInfoObj().getSubject_obs();
		int rowIndex = 0;
		
		ArrayList<String[][]> sets = new ArrayList<String[][]>();
		for(int i=0;i<sub_obs.length;i++){
			if(sub_obs[i] != 0){
				rowIndex = getRowIndex(sub_obs, i);
				String[][] set = getSet(rowIndex, tempDat, sub_obs, i);
				sets.add(set);
			}
			
		}
		
		return sets;
	}



	private String[][] getSet(int rowIndex, String[][] tempDa, int[] subObst, int profNumber) {
		int endIndex = rowIndex + subObst[profNumber];
		String[][] set = new String[subObst[profNumber]][tempDa[0].length];
		int index = 0;
		for(int i=rowIndex;i<endIndex;i++){
			
			for(int j=0;j<tempDa[i].length;j++){
				set[index][j] = tempDa[i][j];
			}
			index++;
		}
		return set;
	}

	private int getRowIndex(int[] subObs, int profNumber) {
		int index = 0;
		for(int i=0;i<profNumber;i++){
			index = index + subObs[i];
		}
		
		
		return index;
	}

	private double[][] getLnTransforamtion(double[][] subjects) {
		
		
		if(selectedIndex == 0){
			for(int i=0;i<subjects.length;i++){
				for(int j=0;j< subjects[i].length;j++){
					subjects[i][j] = Math.log(subjects[i][j]);
				}
			}
		} else if(selectedIndex == 1){
			for(int i=0;i<subjects.length;i++){
				for(int j=0;j< subjects[i].length;j++){
					subjects[i][j] = Math.log10(subjects[i][j]);
				}
			}
		} else if((selectedIndex == 2) || (selectedIndex == 3)){
			for(int i=0;i<subjects.length;i++){
				for(int j=0;j< subjects[i].length;j++){
					subjects[i][j] = (subjects[i][j]);
				}
			}
		}
		
		
		return subjects;
	}

	TDistribution tDistribInst;

	double LsMean_Ref, LsMean_test, Diff_Lse;
	double sd, SE_Diff_Lse, SE_LsMean_test, SE_LsMean_Ref;
	double Cl_Lower, Cl_Upper, GeoMean;
	double Antiln_Cl_Lower, Antiln_Cl_Upper;
	double Antilog_Cl_Lower, Antilog_Cl_Upper, Log_GeoMean;
	double mean_y11;
	double mean_y12;
	double mean_y21;
	double mean_y22;

	private void getConfidenceInterval() {

		double t = tDistribInst.inverseCumulativeProbability(1 - (alpha / 2));
		DescriptiveStatistics obj = new DescriptiveStatistics();

		mean_y11 = obj.getMean(y11);
		mean_y12 = obj.getMean(y12);
		mean_y21 = obj.getMean(y21);
		mean_y22 = obj.getMean(y22);
		LsMean_Ref = (0.5) * (mean_y11 + mean_y22);
		LsMean_test = (0.5) * (mean_y12 + mean_y21);
		// CI
		double[] d1 = new double[n];
		double[] d2 = new double[n];

		for (int i = 0; i < n; i++) {
			d1[i] = (0.5) * (y21[i] - y11[i]);
			d2[i] = (0.5) * (y22[i] - y12[i]);

		}
		double d1_mean = obj.getMean(d1);
		double d2_mean = obj.getMean(d2);
		double sd1 = 0;
		double sd2 = 0;
		for (int i = 0; i < n; i++) {
			sd1 = sd1 + (d1[i] - d1_mean) * (d1[i] - d1_mean);
			sd2 = sd2 + (d2[i] - d2_mean) * (d2[i] - d2_mean);
		}

		sd = (sd1 + sd2) * ((double)2/(double)(n*((2*n) - 2)));
		Diff_Lse = LsMean_test - LsMean_Ref;

		Cl_Lower = Diff_Lse - t * Math.pow(sd, 0.5);
		Cl_Upper = Diff_Lse + t * Math.pow(sd, 0.5);

		Antiln_Cl_Lower = Math.exp(Cl_Lower);
		Antiln_Cl_Upper = Math.exp(Cl_Upper);
		GeoMean = Math.exp(Diff_Lse);
		Antilog_Cl_Lower = Math.pow(10, Cl_Lower);
		Antilog_Cl_Upper = Math.pow(10, Cl_Upper);
		Log_GeoMean = Math.pow(10, Diff_Lse);

		SE_Diff_Lse = Math.pow(sd, 0.5);
		double s1 = 0, s2 = 0, sf;
		for (int i = 0; i < n; i++) {
			s1 = s1 + (y11[i] - mean_y11) * (y11[i] - mean_y11);
			s2 = s2 + (y12[i] - mean_y12) * (y12[i] - mean_y12);

		}
		sf = ((double)1 / (double)(2 * n - 2)) * (s1 + s2);

		SE_LsMean_test = Math.pow(((double)2 /(double) n) * sf, 0.5);
		SE_LsMean_Ref = Math.pow(((double)2 / (double)n) * sf, 0.5);
        System.out.print(t);
		System.out.println("LsMean_Ref=" + LsMean_Ref + " LsMean_test="
				+ LsMean_test + " Diff_Lse=" + Diff_Lse + "SE_Diff_Lse="
				+ SE_Diff_Lse + "SE_LsMean_test=" + SE_LsMean_test
				+ "SE_LsMean_Ref=" + SE_LsMean_Ref);
		System.out.println("Cl_Lower=" +100* Antiln_Cl_Lower + "Cl_Upper=" +100* Antiln_Cl_Upper
				+ "GeoMean=" + GeoMean);

	}

	double sssequence, ssdrug, ssperiod, ssintra, ssinter;
	double mscarry, msdrug, msperiod, msintra, msinter;
	double Fc, Fv, Fp, Fd;
	double pc, pv, pp, pd;
	
	
ArrayList<String> outAL = new ArrayList<String>();
	private void getAnova() {

		double sst = 0, sswithin;
		double[] U1 = new double[n];
		double[] U2 = new double[n];
		for (int i = 0; i < n; i++) {
			U1[i] = (0.5) * (y11[i] + y21[i]);
			U2[i] = (0.5) * (y12[i] + y22[i]);

		}

		double s1 = 0, s2 = 0, s3 = 0, s4 = 0;
		for (int i = 0; i < n; i++) {
			s1 = s1 + (y11[i] - U1[i]) * (y11[i] - U1[i]);
			s2 = s2 + (y21[i] - U1[i]) * (y21[i] - U1[i]);
			s3 = s3 + (y12[i] - U2[i]) * (y12[i] - U2[i]);
			s4 = s4 + (y22[i] - U2[i]) * (y22[i] - U2[i]);
		}
		sssequence = n * Math.pow(0.5*(mean_y12 + mean_y22 - mean_y11 - mean_y21), 2);
		outAL.add(sssequence+"");
		sswithin = s1 + s2 + s3 + s4;
		ssdrug = n
				* Math.pow((0.5)
						* (mean_y21 - mean_y11 - mean_y22 + mean_y12), 2);
		outAL.add(ssdrug+"");
		ssperiod = n
				* Math.pow((0.5)
						* (mean_y21 - mean_y11 + mean_y22 - mean_y12), 2);
		outAL.add(ssperiod+"");
		ssintra = sswithin - ssdrug - ssperiod;
		outAL.add(ssintra+"");
		msintra = ssintra / (2 * n - 2);
		outAL.add(msintra+"");
		double Mean = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < subjects[0].length; j++) {
				Mean = Mean + subjects[i][j];
			}

		}
		Mean = Mean / (2 * 2 * n);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < subjects[0].length; j++) {
				sst = sst + (subjects[i][j] - Mean) * (subjects[i][j] - Mean);

			}

		}

		ssinter = sst - sssequence - sswithin;
	outAL.add(ssinter+"");
		msinter = ssinter / (2 * n - 2);
		outAL.add(msinter+"");
		FDistribution F1dist = new FDistribution(1, 2 * n - 2);
		FDistribution F2dist = new FDistribution(2 * n - 2, 2 * n - 2);
		
		Fc = (sssequence / ssinter) * (2 * n - 2);
		outAL.add(Fc+"");
		Fv = ssinter / ssintra;
		outAL.add(Fv+"");
		Fp = (ssperiod / ssintra) * (2 * n - 2);
		outAL.add(Fp+"");
		Fd = (ssdrug / ssintra) * (2 * n - 2);
		outAL.add(Fd+"");
		pc = 1 - F1dist.cumulativeProbability(Fc);
		outAL.add(pc+"");
		pv = 1 - F2dist.cumulativeProbability(Fv);
		outAL.add(pv+"");
		pd = 1 - F1dist.cumulativeProbability(Fd);
		outAL.add(pd+"");
		pp = 1 - F1dist.cumulativeProbability(Fp);
		outAL.add(pp+"");

		System.out.println("ssseq=" + sssequence + "ssinter=" + ssinter
				+ "ssperiod=" + ssperiod + "ssdrug=" + ssdrug + "ssintra="
				+ ssintra);
		System.out.println("msinter=" + msinter + "msintra=" + msintra);
		System.out.println("Fc=" + Fc + "Fv=" + Fv + "Fp=" + Fp + "Fd=" + Fd);
		System.out.println("pc" + pc + "pv" + pv + "pp" + pp + "pd" + pd);

	}

	double IntraSubject_Cv, InterSubject_Cv;
	double Antiln_IntraSubject_Cv, Antiln_InterSubject_Cv;

	private void getCv() {
		IntraSubject_Cv = 100 * Math.sqrt(msintra) / LsMean_Ref;
		outAL.add(IntraSubject_Cv+"");
		Antiln_IntraSubject_Cv = 100 * Math.sqrt(Math
				.abs((Math.exp(msintra)) - 1));
		outAL.add(Antiln_IntraSubject_Cv+"");
		InterSubject_Cv = 100 * Math.sqrt((msinter - msintra) / 2) / LsMean_Ref;
		outAL.add(InterSubject_Cv+"");
		Antiln_InterSubject_Cv = 100 * Math.sqrt(Math.abs(Math
				.exp((msinter - msintra) / 2) - 1));
		outAL.add(Antiln_InterSubject_Cv+"");
		System.out.println("Antiln_IntraSubject_Cv" + Antiln_IntraSubject_Cv
				+ "Antiln_InterSubject_Cv" + Antiln_InterSubject_Cv);
	}

	double TL, TU, p1, p2;
	double TAH, delta, p3;

	private void getTest() {
		// tost

		TU = (Diff_Lse - UL) / Math.pow(sd, 0.5); // TU<-t(1-(alpha/2))
		outAL.add(TU+"");
		p1 = tDistribInst.cumulativeProbability(TU);
		outAL.add(p1+"");
		TL = (Diff_Lse - LL) / Math.pow(sd, 0.5); // TL>t(1-(alpha/2))
		outAL.add(TL+"");
		p2 = 1 - tDistribInst.cumulativeProbability(TL);
		outAL.add(p2+"");

		// AH test

		TAH = (Diff_Lse - ((UL + LL) / 2)) / Math.pow(sd, 0.5);
		outAL.add(TAH+"");
		delta = (UL - LL) / (2 * Math.pow(sd, 0.5));
		outAL.add(delta+"");
		p3 = tDistribInst.cumulativeProbability(Math.abs(TAH) - delta)
				- tDistribInst.cumulativeProbability(-Math.abs(TAH) - delta);
		outAL.add(p3+"");
		System.out.println("TU = " + TU + "  TL = " + TL + "  p1(upper) = " + p1 + "  p2(lower) = " + p2);
		System.out.println("TAH = " + TAH + "  delta = " + delta + "  p3(A-H) = " + p3);

	}
	
	double Fpm,pvalue_Fpm, stt;
	private void getEquatilyofvariance()
	{ double stt=0,srr=0,srt=0,myt=0,myr=0;
	double Ftr, Rtr;
	for(int i=0;i<n;i++)
	{
	myt=myt+(y21[i]+y12[i]);
	myr=myr+(y11[i]+y22[i]);
	}
	myt=myt/(2*n);
	myr=myr/(2*n);
	for(int i=0;i<n;i++){
		stt=stt+(y21[i]-myt)*(y21[i]-myt)+(y12[i]-myt)*(y12[i]-myt);
		srr=srr+(y11[i]-myr)*(y11[i]-myr)+(y22[i]-myr)*(y22[i]-myr);
		srt=srt+(y21[i]-myt)*(y11[i]-myr) + (y12[i]-myt)*(y22[i]-myr);
		
	}
	stt=stt/(2*n-1);
	srr=srr/(2*n-1);
	srt=srt/(2*n-1);
	
	Ftr=stt/srr;
	Rtr=srt/ (Math.pow(stt*srr,0.5));
	Fpm=(2*n-2)*(Ftr-1)*(Ftr-1)/(4*Ftr*(1-Rtr*Rtr));
	outAL.add(Fpm+"");
	FDistribution F3dist = new FDistribution(1, 2 * n - 2);
	pvalue_Fpm=1-F3dist.cumulativeProbability(Fpm);
	outAL.add(pvalue_Fpm+"");
	System.out.println("stt"+stt+"srr"+srr+"srt"+srt);
	System.out.println("test" +Fpm);
	System.out.println("pvalue" +pvalue_Fpm);
	}

	public static void main(String[] args) {
		//new BioEquivalence();
	}
}

class DescriptiveStatistics {

	double getMean(double[] array) {
		double mean = 0;
		for (int i = 0; i < array.length; i++) {
			mean = mean + array[i];
		}
		mean = mean / array.length;

		return mean;
	}

}
