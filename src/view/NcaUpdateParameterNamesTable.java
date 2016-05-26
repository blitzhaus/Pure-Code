package view;

import java.io.IOException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.DataReader2;

public class NcaUpdateParameterNamesTable {

	
	ParameterNamesData paramNamesDataInst;
	DataReader2 d;
	public NcaUpdateParameterNamesTable() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		paramNamesDataInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex()).getNcaInfo()
		.getProcessingInputs().getParameterNamesObj();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterNamesObj()
				.setPrefferedParameternames(new Vector<String>());

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterNamesObj()
				.setDefaultParameterNames(new Vector<String>());

		if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.getSelectedIndex() == 0) {

			if (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
					.getSelectedIndex() == 0) {

				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getisSparseSelected() == false) {

					String filePath = "serialOralParameterNames.txt";
					 d = new DataReader2(filePath, "String");
					String[][] dataFromFile = d.new_StringFileArray;

					// set
					addPreferredAndDefaultParameterNames(dataFromFile);

				} else if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getisSparseSelected() == true) {
					System.out
							.println("It is sparse oral data and the parameter preffered names are");
					String filePath = "sparseOralParameterNames.txt";

					DataReader2 d = new DataReader2(filePath, "String");
					String[][] dataFromFile = d.new_StringFileArray;
					addPreferredAndDefaultParameterNames(dataFromFile);
				}

			} else if (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
					.getSelectedIndex() == 1) {

				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getisSparseSelected() == false) {
					System.out
							.println("It is serial iv data and the parameter preffered names are");
					String filePath = "serialIVParameterNames.txt";
					System.out.println("The file path is " + filePath);
					DataReader2 d = new DataReader2(filePath, "String");
					System.out
							.println("The file has been read into a data structure");
					String[][] dataFromFile = d.new_StringFileArray;
					for (int i = 0; i < dataFromFile.length; i++) {
						for (int j = 0; j < dataFromFile[i].length; j++) {
							//System.out.print();
						}
						System.out.println();
					}
					addPreferredAndDefaultParameterNames(dataFromFile);
				} else if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getisSparseSelected() == true) {
					System.out
							.println("It is sparse iv data and the parameter preffered names are");
					String filePath = "sparseIVParameterNames.txt";
					DataReader2 d = new DataReader2(filePath, "String");
					String[][] dataFromFile = d.new_StringFileArray;
					addPreferredAndDefaultParameterNames(dataFromFile);
				}

			} else if (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
					.getSelectedIndex() == 2) {

				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getisSparseSelected() == false) {
					System.out
							.println("It is serial iv infusion data and the parameter preffered names are");
					String filePath = "serialInfusion.txt";
					DataReader2 d = new DataReader2(filePath, "String");
					String[][] dataFromFile = d.new_StringFileArray;
					addPreferredAndDefaultParameterNames(dataFromFile);

				} else if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getisSparseSelected() == true) {
					System.out
							.println("It is sparse iv infusion data and the parameter preffered names are");
					String filePath = "sparseIVInfusionParameternames.txt";
					DataReader2 d = new DataReader2(filePath, "String");
					String[][] dataFromFile = d.new_StringFileArray;
					addPreferredAndDefaultParameterNames(dataFromFile);
				}

			}
		} else if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.getSelectedIndex() == 1) {
			System.out
					.println("It is urine data and the parameter preffered names are");
			String filePath = "urineDataParameterNames.txt";
			DataReader2 d = new DataReader2(filePath, "String");

			// set the model type in appInfo
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj().setModelType(1);

			// set the model item in appInfo
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getProcessingInputs()
					.getModelInputsObj()
					.setModelItem(
							NcaOptionsGui.createNcaOptionsInstance().modelOptions
									.getSelectedItem().toString());
			String[][] dataFromFile = d.new_StringFileArray;
			addPreferredAndDefaultParameterNames(dataFromFile);
		}

		// now add the default and preffered names to the table by
		// adding rows to the table
		// first remove the previous rows
		for (int i = NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable
				.getRowCount() - 1; i >= 0; i--) {
			((DefaultTableModel) NcaParameterNamesDispGui
					.createParameterNamesInstance().parameterNamesTable
					.getModel()).removeRow(i);
		}

		// add the rows
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterNamesObj()
				.getDefaultParameterNames().size(); i++) {
			String[] s = new String[3];
			s[0] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getParameterNamesObj()
					.getPrefferedParameterNameAt(i);
			s[1] = s[0];
			s[2] = "yes";
			((DefaultTableModel) NcaParameterNamesDispGui
					.createParameterNamesInstance().parameterNamesTable
					.getModel()).insertRow(NcaParameterNamesDispGui
					.createParameterNamesInstance().parameterNamesTable
					.getRowCount(), s);
			
		}
		
	}

	private void addPreferredAndDefaultParameterNames(String[][] dataFromFile) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		for (int i = 0; i < dataFromFile.length; i++) {
			for (int j = 0; j < dataFromFile[i].length; j++) {

				// adding to the preferred parameter names arraylist
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterNamesObj()
						.getPrefferedParameternames().add(dataFromFile[i][j]);

				// adding to the default parameter names arraylist
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterNamesObj()
						.getDefaultParameterNames().add(dataFromFile[i][j]);

				// setting all the "adding to output" boolean values to true
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterNamesObj()
						.getIncludeInOutput().add(true);

				// printing
				
			}
		}

	}

}
