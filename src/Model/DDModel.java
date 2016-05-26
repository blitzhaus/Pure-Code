package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import view.ApplicationInfo;

import view.ObserverInterface;

import view.TableWizardOpMetricOptions;

public class DDModel implements ModelInterface {

	ApplicationInfo appInfo = null;

	Template12Results[] template1Results;

	public Template12Results[] getTemplate1Results() {
		return template1Results;
	}

	public void setTemplate1Results(Template12Results[] template1Results) {
		this.template1Results = template1Results;
	}

	ArrayList<ObserverInterface> observers = new ArrayList<ObserverInterface>();
	WorkSheetOutputs wsOutputs;

	public WorkSheetOutputs getWsOutputs() {
		return wsOutputs;
	}

	public void setWsOutputs(WorkSheetOutputs wsOutputs) {
		this.wsOutputs = wsOutputs;
	}

	@Override
	public void registerObserver(ObserverInterface observer) {

		observers.add(observer);

	}

	public void doNca(ApplicationInfo appInfo) {

		try {
			NCA.creaetNCAInst().performNCA(appInfo);
			setResults(NCA.creaetNCAInst().wsOutputts);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void notifyObserver() throws RowsExceededException, WriteException,
			BiffException, IOException {

		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).updateView(this);
			
		}
	}

	@Override
	public void removeObserver(ObserverInterface observer) {
		observers.remove((ObserverInterface) observer);

	}

	@Override
	public void setResults(WorkSheetOutputs wsOutputs)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		this.wsOutputs = wsOutputs;
		notifyObserver();

	}

	public void doDesStat(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		desStatComputations.createDesStatCompInst()
				.descriptiveStatisticsCalculation(appInfo);
		setResults(desStatComputations.createDesStatCompInst().getWsOutputts());
	}

	public void doPlotMaven(ApplicationInfo appInfo)
			throws RowsExceededException, NumberFormatException,
			WriteException, BiffException, IOException {

		DataForPlot.createDataForPlotInst().dataProcessingForPlot(appInfo);
		setResults(DataForPlot.createDataForPlotInst().wsOutputs);

	}

	public void doSCA(ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		SemiCompartmentalModelling.creaetSCAInst().semiComparmentalComputation(
				appInfo);
		setResults(SemiCompartmentalModelling.creaetSCAInst().getWsOutputts());
	}

	public void doBQL(ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {
		BQLTransformations bqlTransformations = BQLTransformations
				.createBqlTransInst();
		bqlTransformations.bqlTransformations(appInfo);
		setResults(bqlTransformations.wsoutputs);
	}

	public void doNps(ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		NonParametricSuperposition npsInstance = NonParametricSuperposition
				.creaetNPSInst();
		try {
			npsInstance.Superposition_multidose(appInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setResults(npsInstance.wsOutputts);
	}

	public void doCA(ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {
		PkParamEstimator pkpdMainInst = PkParamEstimator.createPkParamEstimateInstance();
		pkpdMainInst.computePkParameter(appInfo);
		
		
		setResults(pkpdMainInst.pkpdInst.getWorkSheetOutputInst());
	
	}

	public void doPd(ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {
		PdParamEstimator pdParamCalInst = PdParamEstimator.createPdParamCalInstance();
		pdParamCalInst.calculatePdParameters(appInfo);
		
		setResults(pdParamCalInst.pkPdInst.getWorkSheetOutputInst());
	
	}

	public void doMm(ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {
		MmParamEstimator mmParamCalInst = MmParamEstimator.createMmParamCalInstance();
		mmParamCalInst.calculateMmParameters(appInfo);
		
		setResults(mmParamCalInst.pkPdInst.getWorkSheetOutputInst());
	
	}

	public void doPkPdLink(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		PkPdLinkParamEstimator pkpdLinkParamCalInst = PkPdLinkParamEstimator.createPkPdLinkmParamCalInstance();
		pkpdLinkParamCalInst.calculatePkPdLinkParameters(appInfo);
		
		setResults(pkpdLinkParamCalInst.pkPdInst.getWorkSheetOutputInst());
		
	}

	public void doIrm(ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {
			IrmParamEstimator irmParamCalInst = IrmParamEstimator.createIrmParamCalInstance();
		irmParamCalInst.calculateIrmParameters(appInfo);
		
		setResults(irmParamCalInst.pkPdInst.getWorkSheetOutputInst());
	

	}

	public void doTableMaven(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		

		this.appInfo = appInfo;

		String[][] data;
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getHasHeader() == true) {
			data = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getDataStructuresArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex());
			String[][] newData = new String[data.length - 1][data[0].length];
			for (int i = 0; i < newData.length; i++) {
				for (int j = 0; j < newData[i].length; j++) {
					newData[i][j] = data[i + 1][j];
				}
			}
			data = null;
			data = newData;

		} else {
			data = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getDataStructuresArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex());
		}

		int length = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().size();
		String[] colNames = new String[length];

		for (int i = 0; i < length; i++) {
			colNames[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName();
		}

		TableWizardCoordinator tblWizCoordinator = new TableWizardCoordinator(
				null, data, data.length, data[0].length, null, colNames);

		int tempType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getTemplateType();

		if (tempType == 0) {
			template1Handling(tblWizCoordinator);
		} else if (tempType == 1) {
			template2Handling(tblWizCoordinator);
		} else if (tempType == 2) {
			template3Handling(tblWizCoordinator);
		} else if (tempType == 3) {
			template6Handling(tblWizCoordinator);
		}

		notifyObserver();
	}

	private void template1Handling(TableWizardCoordinator tblWizCoordinator) {
		Template1Impl tmp1Impl = new Template1Impl(tblWizCoordinator);

		ArrayList<String> groupVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getGroupVariables();
		ArrayList<String> idVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getIdVariables();
		ArrayList<String> crossVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getCrossVariables();
		ArrayList<String> ordinaryVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getOrdinaryVariables();

		int templateType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getTemplateType();

		tmp1Impl.setTabWizIpOptions(groupVariables, idVariables,
				crossVariables, ordinaryVariables, templateType);

		TableWizardOpMetricOptions tblWizOpMetOptions = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getTmOpMetOptions();

		// tmp1Impl.setTabWizOpOptions(tblWizOpMetOptions);

		Template12Results[] template1Results = tmp1Impl.prepareTemplate();
		wsOutputs = new WorkSheetOutputs();
		wsOutputs.setTemplate1Results(template1Results);
	}

	private void template2Handling(TableWizardCoordinator tblWizCoordinator) {
		Template2Impl tmp2Impl = new Template2Impl(tblWizCoordinator);

		ArrayList<String> groupVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getGroupVariables();
		ArrayList<String> idVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getIdVariables();
		ArrayList<String> crossVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getCrossVariables();
		ArrayList<String> ordinaryVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getOrdinaryVariables();

		int templateType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getTemplateType();

		tmp2Impl.setTabWizIpOptions(groupVariables, idVariables,
				crossVariables, ordinaryVariables, templateType);

		TableWizardOpMetricOptions tblWizOpMetOptions = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getTmOpMetOptions();

		// tmp1Impl.setTabWizOpOptions(tblWizOpMetOptions);

		Template12Results[] template2Results = tmp2Impl.prepareTemplate();
		wsOutputs = new WorkSheetOutputs();
		wsOutputs.setTemplate2Results(template2Results);
	}

	private void template3Handling(TableWizardCoordinator tblWizCoordinator) {
		Template3Impl tmp2Impl = new Template3Impl(tblWizCoordinator);

		ArrayList<String> groupVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getGroupVariables();
		ArrayList<String> idVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getIdVariables();
		ArrayList<String> crossVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getCrossVariables();
		ArrayList<String> ordinaryVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getOrdinaryVariables();

		int templateType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getTemplateType();

		tmp2Impl.setTabWizIpOptions(groupVariables, idVariables,
				crossVariables, ordinaryVariables, templateType);

		TableWizardOpMetricOptions tblWizOpMetOptions = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getTmOpMetOptions();

		// tmp1Impl.setTabWizOpOptions(tblWizOpMetOptions);

		Template12Results[] template2Results = tmp2Impl.prepareTemplate();
		wsOutputs = new WorkSheetOutputs();
		wsOutputs.setTemplate3Results(template2Results);
	}

	private void template6Handling(TableWizardCoordinator tblWizCoordinator) {
		Template6Impl tmp6Impl = new Template6Impl(tblWizCoordinator);

		ArrayList<String> groupVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getGroupVariables();
		ArrayList<String> idVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getIdVariables();
		ArrayList<String> crossVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getCrossVariables();
		ArrayList<String> ordinaryVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getOrdinaryVariables();

		int templateType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getTemplateType();

		tmp6Impl.setTabWizIpOptions(groupVariables, idVariables,
				crossVariables, ordinaryVariables, templateType);

		TableWizardOpMetricOptions tblWizOpMetOptions = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getTmOpMetOptions();

		// tmp1Impl.setTabWizOpOptions(tblWizOpMetOptions);

		Template12Results[] template6Results = tmp6Impl.prepareTemplate();
		wsOutputs = new WorkSheetOutputs();
		wsOutputs.setTemplate6Results(template6Results);
	}

	public void doTT(ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		TableTransformationsComputations tt = new TableTransformationsComputations(
				appInfo);
		tt.performTransformations();
		setResults(tt.wsoutputs);

	}
	public void doAscii(ApplicationInfo appInfo) throws IOException, RowsExceededException, WriteException, BiffException {
		AsciiParameterEstimator asciiEval = AsciiParameterEstimator.createAsciiParamEstimationInst();
		asciiEval.evaluateAscii(appInfo);
		setResults(asciiEval.pkPdInst.getWorkSheetOutputInst());
	}

}
