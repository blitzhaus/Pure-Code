package Control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import view.ApplicationInfo;

import view.DDViewLayer;

import Model.DDModel;
import Model.ModelInterface;

import Model.ModelLayer;

public class DDController implements DDControllerInterface {

	DDModel modelInst;
	DDViewLayer viewLayerInst;

	public DDController(ModelLayer modelLayerInst) {

	}

	public DDController(ModelInterface modelInst) throws FileNotFoundException {
		this.modelInst = (DDModel) modelInst;
		viewLayerInst = DDViewLayer.createViewLayerInstance();
		viewLayerInst.setModel(modelInst);
		viewLayerInst.setController(this);
		viewLayerInst.instantiateViewRef();
	//	System.setOut(new PrintStream(new File("Printing Statements")));

	}

	@Override
	public void initiateAnalysis(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
			
			String analysisType = appInfo
		.getProjectInfoAL()
		.get(appInfo.getSelectedProjectIndex())
		.getWorkBooksArrayList()
		.get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL()
		.get(
				appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(
												appInfo
														.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getSelectedSheetIndex()).getAnalysisType();

			

		if (appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("nca")) {
			performNCA(appInfo);
		} else if (analysisType.equals("pk")) {
			performCA(appInfo);
		}else if (analysisType.equals("pd"))
		{
			performPD(appInfo);
		}else if (analysisType.equals("mm"))
		{
			performMM(appInfo);
		}else if (analysisType.equals("pkpdlink"))
		{
			performPkPdLink(appInfo);
		}else if (analysisType.equals("irm"))
		{
			performIRM(appInfo);
		}
		
		
		 else if (appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("nps")) {
			performNPS(appInfo);
		} else if (appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("sca")) {
			performSCA(appInfo);
		} else if (appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("plotmaven")) {
			performPlotMaven(appInfo);
		} else if (appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("tablemaven")) {
			performTableMaven(appInfo);
		} else if (appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("desstats")) {
			performDS(appInfo);
		} else if (appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("tt")) {
			performTT(appInfo);
		}

	}

	public void performPD(ApplicationInfo appInfo) {
		try {
			modelInst.doPd(appInfo);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void performMM(ApplicationInfo appInfo) {
		try {
			modelInst.doMm(appInfo);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
public void performCA(ApplicationInfo appInfo) {
		try {
			modelInst.doCA(appInfo);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void performDS(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		modelInst.doDesStat(appInfo);

	}

	@Override
	public void performNCA(ApplicationInfo appInfo) {

		modelInst.doNca(appInfo);
	}

	@Override
	public void performNPS(ApplicationInfo appInfo) {
		try {
			modelInst.doNps(appInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void performPlotMaven(ApplicationInfo appInfo)
			throws RowsExceededException, NumberFormatException,
			WriteException, BiffException, IOException {
		modelInst.doPlotMaven(appInfo);

	}

	@Override
	public void performSCA(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		modelInst.doSCA(appInfo);

	}

	@Override
	public void performTableMaven(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		modelInst.doTableMaven(appInfo);
	}

	@Override
	public void performBQL(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		modelInst.doBQL(appInfo);
	}

	@Override
	public void performTT(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		modelInst.doTT(appInfo);

	}
	
	@Override
	public void performAscii(ApplicationInfo appInfo) throws IOException, RowsExceededException, WriteException, BiffException{
		modelInst.doAscii(appInfo);
	}
	
	public void performIRM(ApplicationInfo appInfo) {
		try {
			modelInst.doIrm(appInfo);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void performPkPdLink(ApplicationInfo appInfo) {
		try {
			modelInst.doPkPdLink(appInfo);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void performInVitro(ApplicationInfo appInfo) {
		// TODO Auto-generated method stub
		
	}
}
