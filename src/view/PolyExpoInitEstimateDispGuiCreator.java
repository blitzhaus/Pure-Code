package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.JinternalFrameFunctions;

public class PolyExpoInitEstimateDispGuiCreator {

	JTable initialParamTableForPolyExpo;
	JInternalFrame paramInitValIFForPolyExpo;
	boolean ifNoOfExpIsChanged;
	int numberOfRowsForInitialValue;
	int boundaryValueSelection;
	int initialValueSlection;
	int numberOfColumnsForInitialValue;

	String[][] dataForInitialParameter;
	String[] columnNameForInitialParameter;
	DefaultTableModel initialParameterTableModel;
	JScrollPane initialValueScrollPane;
	boolean calculateInitialvalue;

	InVitroParamAndUnitListLoader paramListInst;

	public static PolyExpoInitEstimateDispGuiCreator POLYEXPO_INIT_EST_INST = null;

	public static PolyExpoInitEstimateDispGuiCreator createPolyExpoInitEstGui()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (POLYEXPO_INIT_EST_INST == null) {
			POLYEXPO_INIT_EST_INST = new PolyExpoInitEstimateDispGuiCreator();
		}
		return POLYEXPO_INIT_EST_INST;
	}

	public PolyExpoInitEstimateDispGuiCreator() {
		try {
			createParameterInitialValueInternalFrame();
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

	final class InitParamTableEventhandler implements TableModelListener {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		public void tableChanged(TableModelEvent arg0) {

			int rowChanged = initialParamTableForPolyExpo.getSelectedRow();
			int colChanged = initialParamTableForPolyExpo.getSelectedColumn();
			
			
			int rowCount = initialParamTableForPolyExpo.getRowCount();
			int columnCount = initialParamTableForPolyExpo.getColumnCount();

			ProcessingInputsInfo procInputInst = CaMapingHandler
					.createCaMapHandInst().copyProcessingInput();

			String tempStr;
			if (rowChanged >= 0 && colChanged >= 0 && rowChanged < rowCount && colChanged < columnCount)
				tempStr = (String) initialParamTableForPolyExpo.getValueAt(
						rowChanged, colChanged);
			else
				tempStr = "";

			try {
				if (tempStr.equals("")) {

				} else
					Double.parseDouble(tempStr);

				for (int i = 0; i < initialParamTableForPolyExpo.getRowCount(); i++) {
					for (int j = 0; j < initialParamTableForPolyExpo
							.getColumnCount(); j++) {

						procInputInst.getDeConvoSetupInfoInst().setExpValueAt(
								i,
								j,
								(String) initialParamTableForPolyExpo
										.getValueAt(i, j)
										+ "");
						CaMapingHandler.createCaMapHandInst()
								.setProcessingInput(procInputInst);

					}
				}

			} catch (Exception e) {
				
				procInputInst = CaMapingHandler.createCaMapHandInst()
						.copyProcessingInput();

				if (rowChanged >= 0 && colChanged >= 0 && rowChanged < rowCount && colChanged < columnCount)
					initialParamTableForPolyExpo.setValueAt(procInputInst
							.getDeConvoSetupInfoInst().getExpValueAt(
									rowChanged, colChanged), rowChanged,
							colChanged);

			}

		}
	}

	void createParameterInitialValueInternalFrame()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		paramInitValIFForPolyExpo = new JInternalFrame(
				"Intial Parameter Value", true, true, true, true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(paramInitValIFForPolyExpo);

		paramInitValIFForPolyExpo.setLayout(new GridLayout(1, 1));
		int width = 0;
		int height = 0;

		width = PolyExpoMainScreenCreator.createPolyExpoMainScreenInstance().polyExpoMainInternalFrame
				.getWidth()
				- PolyExpoSetupAvailableCompCreator
						.createPolyExpoSetupAvailCompInst().setupAvailableComponentsInternalFrame
						.getWidth();
		height = PolyExpoSetupAvailableCompCreator
				.createPolyExpoSetupAvailCompInst().setupAvailableComponentsInternalFrame
				.getHeight();

		paramInitValIFForPolyExpo
				.setLocation(
						PolyExpoSetupAvailableCompCreator
								.createPolyExpoSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ PolyExpoSetupAvailableCompCreator
										.createPolyExpoSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						PolyExpoSetupAvailableCompCreator
								.createPolyExpoSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		paramInitValIFForPolyExpo.setSize(width, height);

		paramInitValIFForPolyExpo.setVisible(true);
		paramInitValIFForPolyExpo.setBorder(viewLayerInst.b);

		PolyExpoTabbedPanesCreator.createPolyExpoTabbedPaneInstance().setupTabDesktopPane
				.add(paramInitValIFForPolyExpo);

		initialValueScrollPane = new JScrollPane();
		initialValueScrollPane.setBackground(Color.white);

		initialValueScrollPane.setVisible(false);
		paramInitValIFForPolyExpo.add(initialValueScrollPane);

	}

	CaMultipleLevelSortingProvider multiplelevelSortInst;
	CaNoOfSubjectDeterminer determineNoOfSubInst;

	public void createInitialParameterValueTable()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		if (procInputInst.getMappingDataObj().getSortVariablesListVector()
				.size() > 0) {

			multiplelevelSortInst = CaMultipleLevelSortingProvider
					.createMultipleSortInstance();

			multiplelevelSortInst.setAppInfo(appInfo);
			multiplelevelSortInst.sortData(null);
			determineNoOfSubInst = CaNoOfSubjectDeterminer
					.createDetermineNoOfSubInstance();

			determineNoOfSubInst
					.determineNumberOfSubject(multiplelevelSortInst.dataSorted);
		}

		rowAndColNoDetctionForIntParamTable(procInputInst);

		int noOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		defaultDataForIntParamTable(procInputInst, noOfSortVar);

		/*
		 * if (ifNoOfExpIsChanged == true || procInputInst.getMappingDataObj()
		 * .getIfMappingScreenIsChanged() == true) {
		 * 
		 * updatingIntParamTableForLMOrMappingChange(procInputInst,
		 * noOfSortVar);
		 * 
		 * }
		 */

		createColNameForIntParamTable(noOfSortVar);

		int[] colIndices = makeNecessaryColsUneditable(noOfSortVar);

		initParamTableBuilding(colIndices);
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

		if (appInfo.isRetrievalFlag() == true) {
			for (int i = 0; i < initialParamTableForPolyExpo.getRowCount(); i++) {
				for (int j = 0; j < initialParamTableForPolyExpo
						.getColumnCount(); j++) {

					initialParamTableForPolyExpo
							.setValueAt(
									procInputInst
											.getInitialParameterValueObj()
											.getInitialParameterValueForLinkModel()[i][j],
									i, j);

				}
			}
		}

		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(false);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	void initParamTableBuilding(int[] colIndices) {

		int numberOfSortVar = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance().numberOfSortVariable;

		UIDefaults ui = UIManager.getLookAndFeel().getDefaults();
		UIManager.put("RadioButton.focus", ui.getColor("control"));

		DefaultTableModel dm = new DefaultTableModel();

		Object[][] tableObj = new Object[dataForInitialParameter.length][columnNameForInitialParameter.length];

		for (int i = 0; i < tableObj.length; i++) {
			for (int j = 0; j < tableObj[0].length; j++) {
				tableObj[i][j] = dataForInitialParameter[i][j];

			}

		}

		initialParameterTableModel = new DefaultTableModel();
		initialParameterTableModel.setDataVector(tableObj,
				columnNameForInitialParameter);

		initialParamTableForPolyExpo = new JTable(initialParameterTableModel);

		new ReorderableJList(initialParamTableForPolyExpo);
		initialParamTableForPolyExpo.getTableHeader().setReorderingAllowed(
				false);

		for (int i = 0; i < initialParamTableForPolyExpo.getRowCount(); i++)
			for (int j = 0; j < initialParamTableForPolyExpo.getColumnCount(); j++)
				initialParamTableForPolyExpo.isCellSelected(i, j);
		initialParamTableForPolyExpo.getModel().addTableModelListener(
				new InitParamTableEventhandler());

		initialValueScrollPane = new JScrollPane(initialParamTableForPolyExpo,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		initialValueScrollPane.setBackground(Color.white);

		initialValueScrollPane.setVisible(true);
		paramInitValIFForPolyExpo.getContentPane().removeAll();
		paramInitValIFForPolyExpo.add(initialValueScrollPane);

		paramInitValIFForPolyExpo.setSize(paramInitValIFForPolyExpo.getSize());
	}

	private int[] makeNecessaryColsUneditable(int noOfSortVar) {

		int[] colIndices = new int[noOfSortVar];
		for (int i = 0; i < colIndices.length; i++) {
			colIndices[i] = i;
		}

		return colIndices;
	}

	private void createColNameForIntParamTable(int noOfSortVar) {
		columnNameForInitialParameter[noOfSortVar] = "Parameter";
		columnNameForInitialParameter[noOfSortVar + 1] = "Value";

	}

	private void updatingIntParamTableCase4(ProcessingInputsInfo procInputInst,
			int noOfSortVar) {
	}

	private void updatingIntParamTableCase3(ProcessingInputsInfo procInputInst,
			int noOfSortVar) {
	}

	void defaultDataForIntParamTable(ProcessingInputsInfo procInputInst,
			int noOfSortVar) {
		CaNoOfSubjectDeterminer determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();

		int noOfParam = procInputInst.getPolyExpoOptionsInst().noOfExpTerm * 2;

		columnNameForInitialParameter = new String[numberOfColumnsForInitialValue];
		dataForInitialParameter = new String[numberOfRowsForInitialValue][numberOfColumnsForInitialValue];

		for (int i = 0; i < noOfSortVar; i++) {
			columnNameForInitialParameter[i] = procInputInst
					.getMappingDataObj().getSortVariablesListVector().get(i);

		}

		for (int i = 0; i < determineNoOfSubInst.noOfSubject; i++) {
			for (int k = 0; k < noOfParam; k++) {
				for (int j = 0; j < noOfSortVar; j++) {
					dataForInitialParameter[noOfParam * i + k][j] = determineNoOfSubInst.dataSortVariables[i][j];
				}

				if (k % 2 == 0) {
					dataForInitialParameter[noOfParam * i + k][noOfSortVar] = "A"
							+ (k / 2 + 1);
				} else {
					dataForInitialParameter[noOfParam * i + k][noOfSortVar] = "Alpha"
							+ (k / 2 + 1);
				}

				dataForInitialParameter[noOfParam * i + k][noOfSortVar + 1] = "";

			}

		}

		procInputInst.getDeConvoSetupInfoInst().setExpValues(
				dataForInitialParameter);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	void rowAndColNoDetctionForIntParamTable(ProcessingInputsInfo procInputInst) {
		CaNoOfSubjectDeterminer determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();

		int noOfExpTerm = procInputInst.getPolyExpoOptionsInst()
				.getNoOfExpTerm();

		numberOfRowsForInitialValue = determineNoOfSubInst.noOfSubject * 2
				* noOfExpTerm;
		numberOfColumnsForInitialValue = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size() + 2;

	}

	void updateInitialParameterTable(WorkSheetOutputs wsOutputInst,
			ProcessingInputsInfo procInputInst) throws RowsExceededException,
			WriteException, BiffException, IOException {

		calculateInitialvalue = false;

		if (initialValueSlection == 0) {

			String[][] dataForInitialParameter = new String[wsOutputInst
					.getSpreadSheetOutputs().getInitialParameterForCA().length - 1][wsOutputInst
					.getSpreadSheetOutputs()

					.getInitialParameterForCA()[0].length];
			for (int i = 0; i < dataForInitialParameter.length; i++) {
				for (int j = 0; j < dataForInitialParameter[0].length; j++) {

					dataForInitialParameter[i][j] = wsOutputInst
							.getSpreadSheetOutputs().getInitialParameterForCA()[i + 1][j];
				}
			}

			int numberOfSortVariable = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().size();
			String[] columnNameForInitialParameter = new String[numberOfSortVariable + 4];

			for (int i = 0; i < numberOfSortVariable; i++) {
				columnNameForInitialParameter[i] = procInputInst
						.getMappingDataObj().getSortVariablesListVector()
						.get(i);

			}

			columnNameForInitialParameter[numberOfSortVariable] = "Parameter";
			columnNameForInitialParameter[numberOfSortVariable + 1] = "Initial Value";
			columnNameForInitialParameter[numberOfSortVariable + 2] = "Lower Bound";
			columnNameForInitialParameter[numberOfSortVariable] = "Upper Bound";

			int[] colIndices;
			if (boundaryValueSelection == 1) {
				colIndices = new int[numberOfSortVariable + 2];
				for (int i = 0; i <= numberOfSortVariable; i++) {
					colIndices[i] = i;
				}
				colIndices[colIndices.length - 1] = numberOfSortVariable + 1;
			} else {
				colIndices = new int[numberOfSortVariable + 4];

				for (int i = 0; i <= numberOfSortVariable; i++) {
					colIndices[i] = i;
				}

				colIndices[colIndices.length - 1] = numberOfSortVariable + 3;
				colIndices[colIndices.length - 2] = numberOfSortVariable + 2;
				colIndices[colIndices.length - 3] = numberOfSortVariable + 1;
			}

			initialParameterTableModel = new DefaultTableModel(
					dataForInitialParameter, columnNameForInitialParameter);
			initialParamTableForPolyExpo = new MyJTable(
					initialParameterTableModel, colIndices);
			initialParamTableForPolyExpo.getTableHeader().setReorderingAllowed(
					false);

			for (int i = 0; i < initialParamTableForPolyExpo.getRowCount(); i++)
				for (int j = 0; j < initialParamTableForPolyExpo
						.getColumnCount(); j++)
					initialParamTableForPolyExpo.isCellSelected(i, j);

			JScrollPane initialValueScrollPane = new JScrollPane(
					initialParamTableForPolyExpo,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			initialValueScrollPane.setBackground(Color.white);

			initialValueScrollPane.setVisible(true);
			paramInitValIFForPolyExpo.getContentPane().removeAll();
			paramInitValIFForPolyExpo.add(initialValueScrollPane);

			paramInitValIFForPolyExpo.setSize(paramInitValIFForPolyExpo
					.getSize());
		}

	}

}
