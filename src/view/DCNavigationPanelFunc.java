package view;

import java.awt.Color;

import javax.print.attribute.standard.Finishings;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import Common.MenuToolBarEnableDisableFeatures;
import Common.PETreeFunctions;

public class DCNavigationPanelFunc {

	public DisplayContents dispContentsInst = null;
	public static DCNavigationPanelFunc DCNAV_FUNC = null;

	public static DCNavigationPanelFunc createDCNavigationHandlerInst() {
		if (DCNAV_FUNC == null) {
			DCNAV_FUNC = new DCNavigationPanelFunc(ImportFile
					.createDispContentsInstance());
		}
		return DCNAV_FUNC;
	}

	public DCNavigationPanelFunc(DisplayContents dispContentsObj) {

		this.dispContentsInst = dispContentsObj;
	}

	void finishAndOpenFunc(DisplayContents dispContentsInst) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		PEHandlers.createPEHanderInst().storePresentAnalysis();
		DDViewLayer.createFeaturesPanelInstance().tableLabel.setVisible(true);
		dispContentsInst.finishAndOpenClicked = true;
		DDViewLayer.createViewLayerInstance().setDisplayContentsTable(false);
		dispContentsInst.f.setVisible(false);
		String startRowData = dispContentsInst.startRowText.getText();
		boolean isNumber = true;
		char[] a = startRowData.toCharArray();
		for (int i = 0; i < a.length; i++) {
			if (Character.isDigit(a[i])) {

			} else {
				isNumber = false;
			}
		}

		if (isNumber == false) {
			dispContentsInst.startRowValue = 0;

		} else {
			dispContentsInst.startRowValue = Integer.parseInt(startRowData);

		}

		ImportedDataSheet.importedDataSheetFrame = null;
		ImportedDataSheet.createImportedDataSheetInstance();
		ImportedDataSheet.createImportedDataSheetInstance()
				.createImpDataSheetFrame();
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setTitle("Imported Data main screen");
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setLocation(0, 0);
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setBorder(DDViewLayer.createViewLayerInstance().b);
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setBackground(Color.white);
		dispContentsInst.removeTitleBarForinternalFrame(ImportedDataSheet
				.createImportedDataSheetInstance().importedDataSheetFrame);
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setSize(DDViewLayer.createMTInstance().mainTabbedDesktopPane
						.getWidth(),
						DDViewLayer.createMTInstance().mainTabbedDesktopPane
								.getHeight());
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setVisible(true);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane
				.add(ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame);
		// fix(mainTabbedDesktopPane);
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.moveToFront();
		DDViewLayer.createMTInstance().mainTabbedFrame
				.setTitle(appInfo
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
						.getSheetName());

		// filling the project explorer with the data work book/text file
		// imported
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).dataFolderAlreadyAdded == false) {
			addDataNodeAndAllInternalNodes();
		} else {
			addInterNalNodesToDataNode();
		}

		// make a copy of the original data imported (all work sheets). THis is
		// useful in the case of optional and required decimals functionalities.
		makeCopyOfOriginalImportedData();

		//select the first column
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableList.setSelectedIndex(0);
		

		// setting the analysis label visible.
		DDViewLayer.createFeaturesPanelInstance().avaiAnalButton.setVisible(true);
		MenuToolBarEnableDisableFeatures.createMenuToolEnabDisabInst().eableMenuToolBarFeatures();
		DDViewLayer.createAAInstance().availableAnalysisFrame.setVisible(false);
		
	}

	void addInterNalNodesToDataNode() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		// add work book to appInfo
		addWorkBookToAppInfo();

		// get the tree model
		DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
				.createPEInstance().tree1.getModel();

		// create and add the work book node to project explorer tree
		DefaultMutableTreeNode workBookNode = new DefaultMutableTreeNode(
				ImportFile.createDispContentsInstance().fileName);
		


		// get the data node
		DefaultMutableTreeNode dataNode = (DefaultMutableTreeNode) appInfo.getpENodes().get(appInfo.getSelectedprojectPath()).getChildAt(0);

		model.insertNodeInto(workBookNode, dataNode,
				dataNode.getChildCount() - 1);

		appInfo.getpENodes().put(PETreeFunctions.createPETreeFuncInst().convertObjToStr(workBookNode), workBookNode);
		
		
		// reload the tree model
		model.reload();

		// add the sheets in the work book to project explorer
		for (int i = 0; i < appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.size(); i++) {

			DefaultMutableTreeNode sheetNode = new DefaultMutableTreeNode(
					appInfo.getProjectInfoAL().get(
							appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(i).getSheetName());

			workBookNode.add(sheetNode);
			appInfo.getpENodes().put(PETreeFunctions.createPETreeFuncInst().convertObjToStr(sheetNode), sheetNode);
		}

		// reload the tree model
		model.reload();

	}

	void addDataNodeAndAllInternalNodes() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		// add the work book to appInfo
		addWorkBookToAppInfo();

		// get the tree model
		DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
				.createPEInstance().tree1.getModel();

		// create and add the data node to project explorer
		DefaultMutableTreeNode dataNode = new DefaultMutableTreeNode("Data");
		
		appInfo.getpENodes().get(appInfo.getSelectedprojectPath()).add(dataNode);
		appInfo.getpENodes().put(PETreeFunctions.createPETreeFuncInst().convertObjToStr(dataNode), dataNode);
		
		model.insertNodeInto(dataNode, appInfo.getpENodes().get(appInfo.getSelectedprojectPath()),
				0);


		// create and add the work book node to project explorer tree
		DefaultMutableTreeNode workBookNode = new DefaultMutableTreeNode(
				ImportFile.createDispContentsInstance().fileName);
		if (dataNode.getChildCount() == 0) {
			model.insertNodeInto(workBookNode, dataNode, 0);

			appInfo.getpENodes().put(PETreeFunctions.createPETreeFuncInst().convertObjToStr(workBookNode), workBookNode);
		} else {
			model.insertNodeInto(workBookNode, dataNode, dataNode
					.getChildCount() - 1);
			appInfo.getpENodes().put(PETreeFunctions.createPETreeFuncInst().convertObjToStr(workBookNode), workBookNode);
		}

		// reload the tree model
		model.reload();

		// add the sheets in the work book to project explorer
		for (int i = 0; i < appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.size(); i++) {

			DefaultMutableTreeNode sheetNode = new DefaultMutableTreeNode(
					appInfo.getProjectInfoAL().get(
							appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(i).getSheetName());

			workBookNode.add(sheetNode);
			appInfo.getpENodes().put(PETreeFunctions.createPETreeFuncInst().convertObjToStr(sheetNode), sheetNode);
		}

		// reload the tree model
		model.reload();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).dataFolderAlreadyAdded = true;

	}

	private void addWorkBookToAppInfo() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		/*
		 * // create a new work book & add it to array list of work books
		 * appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).
		 * getWorkBooksArrayList
		 * ().add(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex
		 * ()).getWorkBooksArrayList().size() - 1);
		 */
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
								.getSelectedWorkBookIndex()).setWorkBookName(
						ImportFile.createDispContentsInstance().fileName);

	}

	private void makeCopyOfOriginalImportedData() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		for (int i = 0; i < appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.size(); i++) {
			String[][] data = new String[appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getElementFromDS(i).length][appInfo.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getElementFromDS(i)[0].length];
			for (int j = 0; j < data.length; j++) {
				for (int k = 0; k < data[j].length; k++) {
					data[j][k] = appInfo.getProjectInfoAL().get(
							appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getElementFromDS(i)[j][k];
				}
			}
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getCopyOfOriginalDataStructures().add(data);
		}

	}

	void cancelButtonFunc() {

		DDViewLayer mlObj = DDViewLayer.createViewLayerInstance();
		dispContentsInst.f.setVisible(false);
		if (mlObj.getNumberOFclicksOnPlotLable() == 1) {
			mlObj.setNumberOFclicksOnPlotLable(0);
			DDViewLayer.createFeaturesPanelInstance().plotsLable.setEnabled(true);

		}
		mlObj.setDisplayContentsTable(false);
	}

}
