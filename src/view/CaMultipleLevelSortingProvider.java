package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.poi.hssf.record.formula.atp.AnalysisToolPak;

import edu.umbc.cs.maple.utils.JamaUtils;

import Jama.Matrix;

import jxl.Cell;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CaMultipleLevelSortingProvider {

	Sheet selectedSheet;

	String[][] unsortedData;
	public String[][] dataSorted;
	private static ApplicationInfo appInfo;

	MultiLevelSort mlSort;
	static CaMultipleLevelSortingProvider mlpInstance = null;

	public static CaMultipleLevelSortingProvider createMultipleSortInstance() {
		if (mlpInstance == null) {
			mlpInstance = new CaMultipleLevelSortingProvider();
		}
		return mlpInstance;
	}

	public CaMultipleLevelSortingProvider() {
	}

	public static ApplicationInfo getAppInfo() {
		return appInfo;
	}

	public static void setAppInfo(ApplicationInfo appInfo) {
		CaMultipleLevelSortingProvider.appInfo = appInfo;
	}

	public void sortData(String a[]) throws RowsExceededException,
			WriteException, BiffException, IOException {
		appInfo = DisplayContents.createAppInfoInstance();
		sortMethod();
	}

	private int[] getIntColIndices(Vector colIndicesVec) {
		int[] resIndices = new int[colIndicesVec.size()];
		for (int i = 0; i < resIndices.length; i++) {
			String tmpIndexStr = (String) colIndicesVec.get(i);
			int tmpIndex = Integer.parseInt(tmpIndexStr);
			resIndices[i] = tmpIndex;
		}
		return resIndices;
	}

	private String[][] getColumnsData(String[][] data, int[] colIndices) {
		String[][] pickedColData = new String[data.length][colIndices.length];
		for (int i = 0; i < pickedColData.length; i++) {
			for (int j = 0; j < colIndices.length; j++) {
				int tempColIndex = colIndices[j];
				String tempCellVal = data[i][tempColIndex];
				pickedColData[i][j] = tempCellVal;
			}
		}
		return pickedColData;
	}

	@SuppressWarnings("unchecked")
	private void sortMethod() throws RowsExceededException, WriteException,
			BiffException, IOException {
		Hashtable htColNameIndices = new Hashtable();
		String[][] data;
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
								.getSelectedSheetIndex()).getHasHeader() == true) {

			data = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getDataStructuresArrayList()
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
			data = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getDataStructuresArrayList()
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
									.getSelectedSheetIndex());
		}
		int length = appInfo
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
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().size();

		for (int i = 0; i < length; i++) {
			String colName = appInfo
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
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName();
			htColNameIndices.put(colName, i + "");
		}
		Vector ordSortVarList = new Vector();
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

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		for (int i = 0; i < procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			String colNameFromSortList = (String) procInputInst
					.getMappingDataObj().getSortVariablesListVector().get(i);
			ordSortVarList.add((String) htColNameIndices
					.get(colNameFromSortList));
		}

		// adding the function column as the last sort variable in case of ascii
		if ((analysisType.equals("ascii"))
				&& (!CaMappingDispGuiCreator.createMappingInstance().caFuncTF
						.getText().toString().equals(""))) {
			String funcColName = (String) procInputInst.getMappingDataObj()
					.getFuncColName();
			ordSortVarList.add((String) htColNameIndices.get(funcColName));
			procInputInst.getMappingDataObj().getSortVariablesListVector().add(
					funcColName);
		}
		boolean ifSimlation = procInputInst.getModelInputTab1Obj()
				.isSimulation();
		if (ifSimlation == false) // Plasma
		{
			String timeColName = (String) procInputInst.getMappingDataObj()
					.getxColumnName();
			ordSortVarList.add((String) htColNameIndices.get(timeColName));
			String concColName = (String) procInputInst.getMappingDataObj()
					.getYcolumnName();
			ordSortVarList.add((String) htColNameIndices.get(concColName));
		} else if (ifSimlation == true) // Simulation
		{
			String timeColName = (String) procInputInst.getMappingDataObj()
					.getxColumnName();

			ordSortVarList.add((String) htColNameIndices.get(timeColName));
		} else if (false) // // Descriptive Stats Data
		{
		}
		mlSort = new MultiLevelSort();
		String[][] sortedData = mlSort.sortData(data, ordSortVarList);
		int[] colIndices = getIntColIndices(ordSortVarList);
		String[][] sortedColumnsData = getColumnsData(sortedData, colIndices);
		dataSorted = sortedColumnsData;
	}

}
