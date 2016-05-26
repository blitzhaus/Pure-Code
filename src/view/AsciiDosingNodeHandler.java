package view;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AsciiDosingNodeHandler extends AsciiDosingDispGui {

	public static AsciiDosingNodeHandler ASCIIDOSENODEHAND = null;
	
	public static AsciiDosingNodeHandler createAsciiDoseNodeHand() throws RowsExceededException, WriteException, BiffException, IOException {
		if(ASCIIDOSENODEHAND == null){
			ASCIIDOSENODEHAND = new AsciiDosingNodeHandler();
		}
		return ASCIIDOSENODEHAND;
	}
	//private ProcessingInputsInfo processingInputs;
	private CaNoOfSubjectDeterminer determineNoOfSubInst;
	public AsciiDosingNodeHandler()throws RowsExceededException, WriteException, BiffException, IOException{
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getAsciiInfo().getProcessingInputs();
		determineNoOfSubInst = CaNoOfSubjectDeterminer
		.createDetermineNoOfSubInstance();
	}
	public void doseNodeClickHandler() throws RowsExceededException, WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getAsciiInfo().getProcessingInputs().getMappingDataObj().getIfMappingScreenIsChanged() == true){
			setAppInfo();
			createDoseTable();
			fillDoseTable();
			
			fillProfList();
			asciiDoseTable.repaint();
			asciiDoseTable.validate();
			asciiDoseTable.revalidate();
			asciiDoseSP.validate();
			asciiDoseSP.repaint();
		} else {
		}
		asciiDoseIF.moveToFront();
		asciiDoseIF.validate();
		asciiDoseIF.repaint();
		asciiDoseIF.revalidate();
		asciiProfIF.moveToFront();
		
		/*appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().setProcessingInputs(processingInputs);*/
		
	}

	void asciiDoseTableHandler(){
		
		int rowChanged = asciiDoseTable.getSelectedRow();
		int columnChnaged = asciiDoseTable.getSelectedColumn();
		int numColumns = asciiDoseTable.getColumnCount();
		
		
		if(columnChnaged == (numColumns-1)){
			try{
				ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
								.getAsciiInfo().getProcessingInputs().getAsciiProfinfoAL().get(getProfSelected(rowChanged, columnChnaged)).getConstantsHm()
				.put(asciiDoseTable.getValueAt(rowChanged, (columnChnaged -1)).toString()
						, Double.parseDouble(asciiDoseTable.getValueAt(rowChanged, (columnChnaged)).toString()));
			} catch(NumberFormatException numFormatExep){
				JOptionPane.showConfirmDialog(asciiDoseIF, "Please enter a numberic value");
			}
			
		}
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().setProcessingInputs(processingInputs);*/
	}
	private int getProfSelected(int rowChanged, int columnChanged) {
		int i = 0;
		String prof = "";
		while(i < columnChanged ){
			prof = prof + " "+asciiDoseTable.getValueAt(rowChanged, i).toString();
			i++;
		}
		i=0;
		int selectedProf = 0;
		for(i = 0;i< doseProfilesList.getModel().getSize();i++){
			if(prof.equals(doseProfListModel.get(i))){
				selectedProf = i;
			}
		}
		
		return selectedProf;
	}
	private void setAppInfo() {
		int numProf = determineNoOfSubInst.noOfSubject;
		for(int i = 0;i< numProf;i++){
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
							appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
							.getAsciiInfo().getProcessingInputs().getAsciiProfinfoAL().add(new AsciiProfInfo());
		}
			
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().setProcessingInputs(processingInputs);*/
	}
	private void fillProfList() {
		
		String[][] local = determineNoOfSubInst.dataSortVariables;
		if(local.length == 1){
			
			doseProfListModel.add(0, "Profile");
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
							appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
							.getAsciiInfo().getProcessingInputs().getAsciiProfinfoAL().get(0).setnDose(1);
			
		} else {
			
			int index=0;
			for(int i=0;i<local.length;i++){
				String prof = "";
				for(int j=0;j<local[i].length;j++){
						prof = prof +"  "+ local[i][j];
				}
				doseProfListModel.add(index++, prof);
			}
		}
		doseProfilesList.setSelectedIndex(4);
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().setProcessingInputs(processingInputs);*/
	}
	
	private void fillDoseTable() {
		removeAllRows();
		String[][] local = determineNoOfSubInst.dataSortVariables; 
		for(int i=0;i< local.length;i++){	
			Object[] obj = new Object[local[i].length + 1];
			for(int j=0;j<local[i].length;j++){
				obj[j] = (local[i][j]);
			}
			if(local.length == 1){
				obj[0] = "const(1)";
				obj[1] = "";
			} else {
				obj[local[i].length] = "const(1)";
			}
			insertRowsForProfile(i,obj);
		}
	}

	private void removeAllRows() {
		int rowsPresent = asciiDoseTable.getRowCount()-1;
		while(rowsPresent >=0){
			((DefaultTableModel)asciiDoseTable.getModel()).removeRow(rowsPresent);
			rowsPresent--;
		}
	}
	private void insertRowsForProfile(int profNumber, Object[] obj) {
		
		((DefaultTableModel)asciiDoseTable.getModel()).addRow(obj);
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getAsciiInfo().getProcessingInputs().getAsciiProfinfoAL().get(profNumber).getConstantsHm().put("const(1)", null);
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().setProcessingInputs(processingInputs);*/
	}
	private int createDoseTable() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int numSort = determineNoOfSubInst.numberOfSortVariable;
		int numConstantsColumn = 1;
		int numValuesColumn = 1;
		int columns = numSort + numConstantsColumn + numValuesColumn;
		int rows = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().getProcessingInputs().getAsciiCommands().getnCon();
		
		if (rows == 0) {
			rows = DEFAULTROWS;
		}
		
		asciiDoseIF.getContentPane().removeAll();
		asciiDoseTable = null;
		createDoseTable(rows, columns);
		TableColumnModel tcm = asciiDoseTable.getColumnModel();

		// set other columns header
		TableColumn tc = tcm.getColumn(numSort);
		tc.setHeaderValue("Constant");
		tc = tcm.getColumn(numSort + 1);
		tc.setHeaderValue("Value");

		// set profile header
		for (int i = 0; i < numSort; i++) {
			tc = tcm.getColumn(i);
			
			String sortVarName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
					.getAsciiInfo().getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i);
			tc.setHeaderValue(sortVarName);
		}
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().setProcessingInputs(processingInputs);*/
		return rows;
		
	}
	public void nDoseTFHandler() throws RowsExceededException, NumberFormatException, WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int selectedProf = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getAsciiInfo().getProcessingInputs().getAsciiprofSelected();
		int nDose = Integer.parseInt(nConTF.getText());
		int previousnDose = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getAsciiInfo().getProcessingInputs().getAsciiProfinfoAL().get(selectedProf).getnDose();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().getProcessingInputs().getAsciiProfinfoAL().get(selectedProf).setnDose(nDose);
		insertRowsForProf(selectedProf, nDose, previousnDose);
		validateTable();
		
		/*appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().setProcessingInputs(processingInputs);*/
	}
	private void validateTable() {
		asciiDoseSP.validate();
		asciiDoseSP.repaint();
		
		asciiDoseTable.validate();
		asciiDoseTable.repaint();
		
		asciiDoseIF.getContentPane().validate();
		asciiDoseIF.getContentPane().repaint();
	}
	private void insertRowsForProf(int selectedProf, int nDose, int prevnDose) {
	
		
			int rowsToAdd = (nDose) - (prevnDose);
			int insertAt = getInsertIndex(selectedProf, prevnDose);
			Object[] rowData;
			int i=1;
			while(rowsToAdd-- > 0){
				rowData = getRowData(selectedProf, (prevnDose + i++));
				((DefaultTableModel)asciiDoseTable.getModel()).insertRow(insertAt++, rowData);
			}
	}
	
	private Object[] getRowData(int selectedProf, int conNumber) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[][] local = determineNoOfSubInst.dataSortVariables;
		String[] temp;
		if(local.length == 1){
			temp = new String[2];
			temp[0] = "const("+conNumber+")";
			temp[1] = "";
			//insert the constant into the hash map
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
					.getAsciiInfo().getProcessingInputs().getAsciiProfinfoAL().get(selectedProf).getConstantsHm().put(temp[0], null);
		} else {
			temp = new String[(local[selectedProf].length)+1];
			for(int i=0;i < local[selectedProf].length; i++){
				temp[i] = local[selectedProf][i];
			}
			temp[local[selectedProf].length] = "const("+conNumber+")";
			//insert the constant into the hash map
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
					.getAsciiInfo().getProcessingInputs().getAsciiProfinfoAL().get(selectedProf).getConstantsHm().put(temp[local[selectedProf].length], null);
		}
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().setProcessingInputs(processingInputs);*/
	return temp;

	}

	private int getInsertIndex(int selectedProf, int prevnDose) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int index = 0;
		for (int i = 0; i <= selectedProf; i++) {
			int nDose;
			if(i == selectedProf){
				nDose = prevnDose;
			} else {
				nDose = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
								.getAsciiInfo().getProcessingInputs().getAsciiProfinfoAL().get(i)
				.getnDose();
			}
			 
			index = index + (nDose);
		}
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().setProcessingInputs(processingInputs);*/
		return index;
	}

	public void asciiProfListHandler() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int selectedProf = doseProfilesList.getSelectedIndex();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().getProcessingInputs().setAsciiprofSelected(selectedProf);
		try {
			AsciiDosingNodeHandler.createAsciiDoseNodeHand().nConTF.setText(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
							.getAsciiInfo().getProcessingInputs().getAsciiProfinfoAL().get(selectedProf).getnDose()+"");
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
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().setProcessingInputs(processingInputs);*/
		
	}
}