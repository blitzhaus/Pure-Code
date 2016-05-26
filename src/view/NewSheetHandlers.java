package view;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.commons.collections.functors.WhileClosure;

import Common.PETreeFunctions;

public class NewSheetHandlers {

	public static NewSheetHandlers NEW_SHEET_HANDLERS = null;
	
	public static NewSheetHandlers createNewShetHandlersInst(){
		if(NEW_SHEET_HANDLERS == null){
			NEW_SHEET_HANDLERS = new NewSheetHandlers("just object creation");
		}
		return NEW_SHEET_HANDLERS;
	}
	
	NewSheetCreation newSheetCreatinst = null;
	ApplicationInfo appInfo = null;
	PETreeFunctions petreehandInst = null;
	public NewSheetHandlers(String dummy){
		newSheetCreatinst = NewSheetCreation.createNewSheetInst();
		petreehandInst = PETreeFunctions.createPETreeFuncInst();
		
	}
	
	void addRow(){
		String[] emptyRow = new String[NewSheetCreation.createNewSheetInst().table.getColumnCount()];
		for(int i=0;i<emptyRow.length;i++){
			emptyRow[i] = "";
		}
		((DefaultTableModel)NewSheetCreation.createNewSheetInst().table.getModel()).
		insertRow(NewSheetCreation.createNewSheetInst().table.getRowCount(), emptyRow);
		
		NewSheetCreation.createNewSheetInst().rowsCombo.setSelectedIndex(NewSheetCreation.createNewSheetInst().rowsCombo.getSelectedIndex() + 1);
	}

	public void addColumn() {
		((DefaultTableModel) NewSheetCreation.createNewSheetInst().table
				.getModel())
				.addColumn(NewSheetCreation.createNewSheetInst().table
						.getModel().getColumnCount());
		/*TableColumn tc = new TableColumn(NewSheetCreation.createNewSheetInst().table.getModel().getColumnCount()-1);
		NewSheetCreation.createNewSheetInst().table.addColumn(tc);*/
		char asciiValue = (char)(NewSheetCreation.createNewSheetInst().table.getColumnCount() - 1 + 65);
		NewSheetCreation.createNewSheetInst().table
				.getColumnModel()
				.getColumn(
						NewSheetCreation.createNewSheetInst().table
								.getColumnCount() - 1)
				.setHeaderValue(asciiValue);
		
		//ensuring all the cells in the new column created are empty strings
		for(int i=0;i<NewSheetCreation.createNewSheetInst().table.getRowCount();i++){
			NewSheetCreation.createNewSheetInst().table.setValueAt("", i, (NewSheetCreation.createNewSheetInst().table.getColumnCount() -1));
		}
		
		NewSheetCreation.createNewSheetInst().columnsCombo.setSelectedIndex(NewSheetCreation.createNewSheetInst().columnsCombo.getSelectedIndex() + 1);
		NewSheetCreation.createNewSheetInst().table.repaint();
		NewSheetCreation.createNewSheetInst().table.validate();
	}

	public void setNumberOfRows() {

		if (NewSheetCreation.createNewSheetInst().whileGuiCreation == true) {

		} else {
			int numberOfPresentRows = NewSheetCreation.createNewSheetInst().table
					.getRowCount();
			if (numberOfPresentRows > (NewSheetCreation.createNewSheetInst().rowsCombo
					.getSelectedIndex())) {

				int rowsSelected = NewSheetCreation.createNewSheetInst().rowsCombo
						.getSelectedIndex() + 1;

				while (rowsSelected != numberOfPresentRows--) {
					((DefaultTableModel) NewSheetCreation.createNewSheetInst().table
							.getModel()).removeRow(NewSheetCreation
							.createNewSheetInst().table.getRowCount() - 1);
				}

			} else if (numberOfPresentRows == (NewSheetCreation
					.createNewSheetInst().rowsCombo.getSelectedIndex() + 1)) {

			} else if (numberOfPresentRows < (NewSheetCreation
					.createNewSheetInst().rowsCombo.getSelectedIndex() + 1)) {

				int rowsSelected = NewSheetCreation.createNewSheetInst().rowsCombo
						.getSelectedIndex() + 1;
				while (rowsSelected != numberOfPresentRows++) {
					addRow();
				}
			}
		}

	}
	
	public void setNumberOfColumns() {
		if(NewSheetCreation.createNewSheetInst().whileGuiCreation == true){
			
		} else{
			int numberOfPresentColumns = NewSheetCreation.createNewSheetInst().table.getColumnCount();
			int numberOfColumnsSelected = NewSheetCreation.createNewSheetInst().columnsCombo.getSelectedIndex() + 1;
			if(numberOfPresentColumns < numberOfColumnsSelected){
				
				while(numberOfColumnsSelected != numberOfPresentColumns++){
					addColumn();
				}
			} else if(numberOfColumnsSelected == numberOfPresentColumns){
				
			} else if(numberOfPresentColumns > numberOfColumnsSelected){
				while(numberOfColumnsSelected != numberOfPresentColumns--){
					NewSheetCreation.createNewSheetInst().table
							.removeColumn(NewSheetCreation.createNewSheetInst().table
									.getColumnModel().getColumn(
											NewSheetCreation
													.createNewSheetInst().table
													.getColumnCount() - 1));
				}
			}
		}
		
		
		
	}
	

	private String[][] readTheCurrentTableContents() {
		
		int rows = NewSheetCreation.createNewSheetInst().table.getRowCount();
		int columns = NewSheetCreation.createNewSheetInst().table.getColumnCount();
		String[][] data =  new String[rows][columns];
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				data[i][j] = NewSheetCreation.createNewSheetInst().table.getValueAt(i, j).toString();
			}
		}
		
		return data;
	}

	void okButtonHandler() {
		reflectChangesInAppInfo();
		reflectChangesInPE();
		NewSheetCreation.NEW_SHEET.dispose();
		
	}

	private void reflectChangesInPE() {
		appInfo = DisplayContents.createAppInfoInstance();
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).dataFolderAlreadyAdded== false) {
			addDataNodeAndInternalNodes();
		} else {
			addInternalNodesToDataNode();
		}
	}

	private void addWorkBookToAppInfo() {

		appInfo = DisplayContents.createAppInfoInstance();

		// get the data node
		DefaultMutableTreeNode dataNode = (DefaultMutableTreeNode) appInfo.getpENodes().get(appInfo.getSelectedprojectPath()).getChildAt(0);
		
		// set the selected workbook to the new work book being imported
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.setSelectedWorkBookIndex(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList().size() - 1);

		// set the file name of the work book
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex()).setWorkBookName("Internal Work Book "+ (dataNode.getChildCount()+1));

	}
	
	
	//"Internal Work Book"+ (dataNode.getChildCount()+1)
	private void addInternalNodesToDataNode() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		// add work book to appInfo
		addWorkBookToAppInfo();

		// get the tree model
		DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
				.createPEInstance().tree1.getModel();

		// get the data node
		DefaultMutableTreeNode dataNode = (DefaultMutableTreeNode) appInfo.getpENodes().get(appInfo.getSelectedprojectPath()).getChildAt(0);
		
		// create and add the work book node to project explorer tree
		DefaultMutableTreeNode workBookNode = new DefaultMutableTreeNode("Internal Work Book "+ (dataNode.getChildCount()+1));

		model.insertNodeInto(workBookNode, dataNode,
				dataNode.getChildCount() - 1);

		appInfo.getpENodes().put(petreehandInst.convertObjToStr(workBookNode), workBookNode);
		
		
		// reload the tree model
		model.reload();

		//add the sheet node to the work book node
		DefaultMutableTreeNode sheetNode = new DefaultMutableTreeNode("sheet "+(workBookNode.getChildCount()+1));
		workBookNode.add(sheetNode);
		
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
		.setSheetName("sheet "+(workBookNode.getChildCount()));

		// reload the tree model
		model.reload();

	}

	private void isIntWBNodePreesnt() {/*
		
		//get the data node path
		TreePath dataNodePath = ViewLayer.createPEInstance().tree1.getPathForRow(1);
		
		//get the data node
		DefaultMutableTreeNode dataNode = (DefaultMutableTreeNode)dataNodePath.getLastPathComponent();
		
		for(int i=0;i<dataNode.getChildCount();i++){
			dataNode.getChildAt(i).
		}

	*/}

	
	//"Internal Work Book 1" sheet
	private void addDataNodeAndInternalNodes() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		// add the work book to appInfo
		addWorkBookToAppInfo();

		// get the tree model
		DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
				.createPEInstance().tree1.getModel();

		// create and add the data node to project explorer
		DefaultMutableTreeNode dataNode = new DefaultMutableTreeNode("Data");
		
		appInfo.getpENodes().get(appInfo.getSelectedprojectPath()).add(dataNode);
		appInfo.getpENodes().put(petreehandInst.convertObjToStr(dataNode), dataNode);
		
		model.insertNodeInto(dataNode, appInfo.getpENodes().get(appInfo.getSelectedprojectPath()),
				0);


		// create and add the work book node to project explorer tree
		DefaultMutableTreeNode workBookNode = new DefaultMutableTreeNode("Internal Work Book 1");
		if (dataNode.getChildCount() == 0) {
			model.insertNodeInto(workBookNode, dataNode, 0);

			appInfo.getpENodes().put(petreehandInst.convertObjToStr(workBookNode), workBookNode);
		} else {
			model.insertNodeInto(workBookNode, dataNode, dataNode
					.getChildCount() - 1);
			appInfo.getpENodes().put(petreehandInst.convertObjToStr(workBookNode), workBookNode);
		}

		//add the sheet node to the work book node
		DefaultMutableTreeNode sheetNode = new DefaultMutableTreeNode("sheet "+(workBookNode.getChildCount()+1));
		workBookNode.add(sheetNode);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
		.setSheetName("sheet "+(workBookNode.getChildCount()));
		
		
		// reload the tree model
		model.reload();

			model.reload();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).dataFolderAlreadyAdded = true;

	}

	private void reflectChangesInAppInfo() {
		
		//create a new work book in appInfo
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().add(new WorkBookInfo());
		
		//set the selected work book index
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).setSelectedWorkBookIndex(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().size() -1);
		
		//add the work sheet to the array list within the work book
		String[][] data = readTheCurrentTableContents();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).addElementToDS(data);
		
		//add the data to the original copy of data structures
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getCopyOfOriginalDataStructures().add(data);
		
		//add a new work sheet to the array list within the selected work book i.e the new work book
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().add(new WorkSheetsInfo());
		
		//set the selected sheet index
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).setSelectedSheetIndex(0);
		
		//add a new element to the column properties array list
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
		.getColumnPropertiesArrayList().add(new ColumnProperties());
		
		//set the user provided values the properties of the sheet
		
		//set the field which indicates whether column header is present or not
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getSelectedSheetIndex()).setHasHeader(NewSheetCreation.createNewSheetInst().hasHeaderRowCheck.isSelected());
		
		//set the starting row 
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getSelectedSheetIndex()).setStartRow(Integer.parseInt(NewSheetCreation.createNewSheetInst().startRowText.getText()));
		
		
		
	}

	void cancelButtonHandler() {
		NewSheetCreation.NEW_SHEET.dispose();
		
	}


}
