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
import view.CaInitEstimateDispGuiCreator.InitParamTableEventhandler;
import Common.JinternalFrameFunctions;

public class InVitroInitEstimateDispGuiCreator {

	JTable initialParamTableForInVitro;
	JInternalFrame paramInitValIFForInVitro;
	boolean ifLibraryModelIsChanged;
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

	public static InVitroInitEstimateDispGuiCreator INVITRO_INIT_EST_INST = null;

	public static InVitroInitEstimateDispGuiCreator createInVitroInitEstGui()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (INVITRO_INIT_EST_INST == null) {
			INVITRO_INIT_EST_INST = new InVitroInitEstimateDispGuiCreator();
		}
		return INVITRO_INIT_EST_INST;
	}

	public InVitroInitEstimateDispGuiCreator() {
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

			int rowChanged = initialParamTableForInVitro.getSelectedRow();
			int colChanged = initialParamTableForInVitro.getSelectedColumn();

			ProcessingInputsInfo procInputInst = CaMapingHandler
					.createCaMapHandInst().copyProcessingInput();

			String tempStr;
			int numberOfSortVar = procInputInst.getMappingDataObj().getSortVariablesListVector().size();
			if((colChanged == numberOfSortVar+1) || (colChanged == numberOfSortVar+2))
			{
				
				procInputInst.getInitialParameterValueObj()
				.setInitialParameterValueAt(
						rowChanged,
						colChanged,
						1
								+ "");
				CaMapingHandler.createCaMapHandInst()
				.setProcessingInput(procInputInst);
				
			}else
			{

				if (rowChanged >= 0 && colChanged >= 0)
					tempStr = (String) initialParamTableForInVitro.getValueAt(
							rowChanged, colChanged);
				else
					tempStr = "";

				try {
					if (tempStr.equals("")) {

					} else
						Double.parseDouble(tempStr);



					procInputInst.getInitialParameterValueObj()
							.setInitialParameterValueAt(
									rowChanged,
									colChanged,
									(String) initialParamTableForInVitro
											.getValueAt(rowChanged, colChanged)
											+ "");
					CaMapingHandler.createCaMapHandInst()
							.setProcessingInput(procInputInst);

				

				} catch (Exception e) {
					try {

						String message = "Please ensure initial value is a Number";

						CaMapingHandler.createCaMapHandInst().showMessage(message);
					} catch (RowsExceededException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (WriteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BiffException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					procInputInst = CaMapingHandler.createCaMapHandInst()
							.copyProcessingInput();

					if (rowChanged >= 0 && colChanged >= 0) {
						
						for (int i = 0; i < initialParamTableForInVitro.getRowCount(); i++) {
							for (int j = procInputInst.getMappingDataObj().getSortVariablesListVector().size()
									+3; j < initialParamTableForInVitro.getColumnCount(); j++) {
								initialParamTableForInVitro
								.setValueAt(procInputInst
										.getInitialParameterValueObj()
										.getInitialParameterValueAt(i,
												j), i, j);
							}
						}
						
					}

			}
			
			
			
			}

		}
	}

	void createParameterInitialValueInternalFrame()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		paramInitValIFForInVitro = new JInternalFrame("Intial Parameter Value",
				true, true, true, true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(paramInitValIFForInVitro);

		paramInitValIFForInVitro.setLayout(new GridLayout(1, 1));
		int width = 0;
		int height = 0;

		width = InVitroMainScreenCreator.createInVitroMainScreenInstance().inVitroMainInternalFrame
				.getWidth()
				- InVitroSetupAvailableCompCreator
						.createInVitroSetupAvailCompInst().setupAvailableComponentsInternalFrame
						.getWidth();
		height = InVitroSetupAvailableCompCreator
				.createInVitroSetupAvailCompInst().setupAvailableComponentsInternalFrame
				.getHeight();

		paramInitValIFForInVitro
				.setLocation(
						InVitroSetupAvailableCompCreator
								.createInVitroSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ InVitroSetupAvailableCompCreator
										.createInVitroSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						InVitroSetupAvailableCompCreator
								.createInVitroSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		paramInitValIFForInVitro.setSize(width, height);

		paramInitValIFForInVitro.setVisible(true);
		paramInitValIFForInVitro.setBorder(viewLayerInst.b);

		InVitroTabbedPanesCreator.createInVitroTabbedPaneInstance().setupTabDesktopPane
				.add(paramInitValIFForInVitro);

		initialValueScrollPane = new JScrollPane();
		initialValueScrollPane.setBackground(Color.white);

		initialValueScrollPane.setVisible(false);
		paramInitValIFForInVitro.add(initialValueScrollPane);

	}

	public void createInitialParameterValueTable() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		rowAndColNoDetctionForIntParamTable(procInputInst);

		int noOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		defaultDataForIntParamTable(procInputInst, noOfSortVar);

		if (ifLibraryModelIsChanged == true
				|| procInputInst.getMappingDataObj()
						.getIfMappingScreenIsChanged() == true) {

			updatingIntParamTableForLMOrMappingChange(procInputInst,
					noOfSortVar);

		}

		else if (boundaryValueSelection == 1 && initialValueSlection == 0) {

			updatingIntParamTableCase1(procInputInst, noOfSortVar);

		} else if (boundaryValueSelection == 1 && initialValueSlection == 1) {

			updatingIntParamTableCase2(procInputInst, noOfSortVar);

		} else if (boundaryValueSelection != 1 && initialValueSlection == 0) {
			updatingIntParamTableCase3(procInputInst, noOfSortVar);

		} else if (boundaryValueSelection != 1 && initialValueSlection == 1) {

			updatingIntParamTableCase4(procInputInst, noOfSortVar);

		}

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
			for (int i = 0; i < initialParamTableForInVitro.getRowCount(); i++) {
				for (int j = 0; j < initialParamTableForInVitro
						.getColumnCount(); j++) {

					if (analysisType.equals("pkpdlink")
							|| analysisType.equals("irm")) {

						initialParamTableForInVitro.setValueAt(procInputInst
								.getInitialParameterValueObj()
								.getInitialParameterValueForLinkModel()[i][j],
								i, j);

					} else {
						initialParamTableForInVitro.setValueAt(procInputInst
								.getInitialParameterValueObj()
								.getInitialParameterValueAt(i, j), i, j);
					}

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
			for (int j = 0; j <= numberOfSortVar; j++) {
				tableObj[i][j] = dataForInitialParameter[i][j];

			}

			tableObj[i][numberOfSortVar + 1] = new JRadioButton();
			tableObj[i][numberOfSortVar + 2] = new JRadioButton();

			ButtonGroup group1 = new ButtonGroup();
			group1.add((JRadioButton) tableObj[i][numberOfSortVar + 1]);
			group1.add((JRadioButton) tableObj[i][numberOfSortVar + 2]);

			tableObj[i][numberOfSortVar + 3] = dataForInitialParameter[i][numberOfSortVar + 3];
			tableObj[i][numberOfSortVar + 4] = dataForInitialParameter[i][numberOfSortVar + 4];
			tableObj[i][numberOfSortVar + 5] = dataForInitialParameter[i][numberOfSortVar + 5];

		}

		initialParameterTableModel = new DefaultTableModel();
		initialParameterTableModel.setDataVector(tableObj,
				columnNameForInitialParameter);

		initialParamTableForInVitro = new JTable(initialParameterTableModel);

		initialParamTableForInVitro.getColumn("Fixed").setCellRenderer(
				new RadioButtonRenderer());

		initialParamTableForInVitro.getColumn("Estimated").setCellRenderer(
				new RadioButtonRenderer());

		// this is how the selection of combo box is possible
		initialParamTableForInVitro.getColumn("Fixed").setCellEditor(
				new RadioButtonEditor(new JCheckBox()));
		initialParamTableForInVitro.getColumn("Estimated").setCellEditor(
				new RadioButtonEditor(new JCheckBox()));

		/*
		 * initialParamTableForInVitro = new MyJTable(
		 * initialParameterTableModel, colIndices);
		 */

		new ReorderableJList(initialParamTableForInVitro);
		initialParamTableForInVitro.getTableHeader()
				.setReorderingAllowed(false);

		for (int i = 0; i < initialParamTableForInVitro.getRowCount(); i++)
			for (int j = 0; j < initialParamTableForInVitro.getColumnCount(); j++)
				initialParamTableForInVitro.isCellSelected(i, j);
		initialParamTableForInVitro.getModel().addTableModelListener(
				new InitParamTableEventhandler());

		initialValueScrollPane = new JScrollPane(initialParamTableForInVitro,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		initialValueScrollPane.setBackground(Color.white);

		initialValueScrollPane.setVisible(true);
		paramInitValIFForInVitro.getContentPane().removeAll();
		paramInitValIFForInVitro.add(initialValueScrollPane);

		paramInitValIFForInVitro.setSize(paramInitValIFForInVitro.getSize());
	}

	private int[] makeNecessaryColsUneditable(int noOfSortVar) {
		int lengthOfUneditableColIndices;
		int[] colIndices = null;

		if (initialValueSlection > 0 && boundaryValueSelection == 1) {
			lengthOfUneditableColIndices = noOfSortVar + 1;
			colIndices = new int[lengthOfUneditableColIndices];
			for (int i = 0; i <= noOfSortVar; i++) {

				colIndices[i] = i;

			}

		} else if (initialValueSlection > 0 && boundaryValueSelection != 1) {
			lengthOfUneditableColIndices = noOfSortVar + 3;

			colIndices = new int[lengthOfUneditableColIndices];
			for (int i = 0; i <= noOfSortVar; i++) {

				colIndices[i] = i;

			}

			colIndices[lengthOfUneditableColIndices - 1] = noOfSortVar + 5;
			colIndices[lengthOfUneditableColIndices - 2] = noOfSortVar + 4;

		} else if (initialValueSlection == 0 && boundaryValueSelection == 1) {

			lengthOfUneditableColIndices = noOfSortVar + 2;
			colIndices = new int[lengthOfUneditableColIndices];

			for (int i = 0; i <= noOfSortVar; i++) {

				colIndices[i] = i;

			}

			colIndices[lengthOfUneditableColIndices - 1] = noOfSortVar + 3;

		} else if (initialValueSlection == 0 && boundaryValueSelection != 1) {

			lengthOfUneditableColIndices = noOfSortVar + 4;
			colIndices = new int[lengthOfUneditableColIndices];

			for (int i = 0; i <= noOfSortVar; i++) {

				colIndices[i] = i;

			}

			colIndices[lengthOfUneditableColIndices - 1] = noOfSortVar + 5;
			colIndices[lengthOfUneditableColIndices - 2] = noOfSortVar + 4;

			colIndices[lengthOfUneditableColIndices - 3] = noOfSortVar + 3;

		} else {
			lengthOfUneditableColIndices = noOfSortVar + 4;
			colIndices = new int[lengthOfUneditableColIndices];
		}

		return colIndices;
	}

	private void createColNameForIntParamTable(int noOfSortVar) {
		columnNameForInitialParameter[noOfSortVar] = "Parameter";
		columnNameForInitialParameter[noOfSortVar + 1] = "Fixed";
		columnNameForInitialParameter[noOfSortVar + 2] = "Estimated";
		columnNameForInitialParameter[noOfSortVar + 3] = "Initial Value";
		columnNameForInitialParameter[noOfSortVar + 4] = "Lower Bound";
		columnNameForInitialParameter[noOfSortVar + 5] = "Upper Bound";
	}

	private void updatingIntParamTableCase4(ProcessingInputsInfo procInputInst,
			int noOfSortVar) {

		for (int i = 0; i < numberOfRowsForInitialValue; i++)

		{
			try {
				dataForInitialParameter[i][noOfSortVar + 3] = procInputInst
						.getInitialParameterValueObj()
						.getInitialParameterValueAt(i, noOfSortVar + 3);
			} catch (Exception e) {
				e.printStackTrace();
				dataForInitialParameter[i][noOfSortVar + 3] = "";
			}
		}

		for (int i = 0; i < numberOfRowsForInitialValue; i++)

			dataForInitialParameter[i][noOfSortVar + 4] = "";
		for (int i = 0; i < numberOfRowsForInitialValue; i++)

			dataForInitialParameter[i][noOfSortVar + 5] = "";

		procInputInst.getInitialParameterValueObj()
				.setInitialParameterValueForCA(dataForInitialParameter);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	private void updatingIntParamTableCase3(ProcessingInputsInfo procInputInst,
			int noOfSortVar) {

		for (int i = 0; i < numberOfRowsForInitialValue; i++)

			dataForInitialParameter[i][noOfSortVar + 3] = "";

		for (int i = 0; i < numberOfRowsForInitialValue; i++)

			dataForInitialParameter[i][noOfSortVar + 4] = "";
		for (int i = 0; i < numberOfRowsForInitialValue; i++)

			dataForInitialParameter[i][noOfSortVar + 5] = "";

		procInputInst.getInitialParameterValueObj()
				.setInitialParameterValueForCA(dataForInitialParameter);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	private void updatingIntParamTableCase2(ProcessingInputsInfo procInputInst,
			int noOfSortVar) {

		for (int i = 0; i < numberOfRowsForInitialValue; i++) {
			try {
				dataForInitialParameter[i][noOfSortVar + 3] = procInputInst
						.getInitialParameterValueObj()
						.getInitialParameterValueAt(i, noOfSortVar + 3);
			} catch (Exception e) {
				e.printStackTrace();
				dataForInitialParameter[i][noOfSortVar + 3] = "";
			}

		}

		for (int i = 0; i < numberOfRowsForInitialValue; i++) {
			try {
				dataForInitialParameter[i][noOfSortVar + 4] = procInputInst
						.getInitialParameterValueObj()
						.getInitialParameterValueAt(i, noOfSortVar + 4);
			} catch (Exception e) {
				e.printStackTrace();
				dataForInitialParameter[i][noOfSortVar + 4] = "";
			}
		}

		for (int i = 0; i < numberOfRowsForInitialValue; i++) {
			try {
				dataForInitialParameter[i][noOfSortVar + 5] = procInputInst
						.getInitialParameterValueObj()
						.getInitialParameterValueAt(i, noOfSortVar + 5);
			} catch (Exception e) {
				e.printStackTrace();
				dataForInitialParameter[i][noOfSortVar + 5] = "";
			}

		}
		procInputInst.getInitialParameterValueObj()
				.setInitialParameterValueForCA(dataForInitialParameter);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	private void updatingIntParamTableCase1(ProcessingInputsInfo procInputInst,
			int noOfSortVar) {

		for (int i = 0; i < numberOfRowsForInitialValue; i++)

			dataForInitialParameter[i][noOfSortVar + 3] = "";

		for (int i = 0; i < numberOfRowsForInitialValue; i++) {
			try {
				dataForInitialParameter[i][noOfSortVar + 4] = procInputInst
						.getInitialParameterValueObj()
						.getInitialParameterValueAt(i, noOfSortVar + 4);
			} catch (Exception e) {
				e.printStackTrace();
				dataForInitialParameter[i][noOfSortVar + 4] = "";
			}
		}
		for (int i = 0; i < numberOfRowsForInitialValue; i++) {
			try {
				dataForInitialParameter[i][noOfSortVar + 5] = procInputInst
						.getInitialParameterValueObj()
						.getInitialParameterValueAt(i, noOfSortVar + 5);
			} catch (Exception e) {
				e.printStackTrace();
				dataForInitialParameter[i][noOfSortVar + 5] = "";
			}
		}
		procInputInst.getInitialParameterValueObj()
				.setInitialParameterValueForCA(dataForInitialParameter);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	private void updatingIntParamTableForLMOrMappingChange(
			ProcessingInputsInfo procInputInst, int noOfSortVar) {

		for (int i = 0; i < dataForInitialParameter.length; i++)
			for (int j = noOfSortVar + 1; j < dataForInitialParameter[0].length; j++)
				dataForInitialParameter[i][j] = "";

		procInputInst.getInitialParameterValueObj()
				.setInitialParameterValueForCA(dataForInitialParameter);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	void defaultDataForIntParamTable(ProcessingInputsInfo procInputInst,
			int noOfSortVar) {
		CaNoOfSubjectDeterminer determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();

		paramListInst = InVitroParamAndUnitListLoader.createParamListInstance();

		paramListInst.createParameterList();

		int noOfParam;

		noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();

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

				dataForInitialParameter[noOfParam * i + k][noOfSortVar] = procInputInst
						.getProfileAndParamInfoObj().getParameterNameForCA()[k];

				dataForInitialParameter[noOfParam * i + k][noOfSortVar + 3] = "";
				dataForInitialParameter[noOfParam * i + k][noOfSortVar + 4] = "";
				dataForInitialParameter[noOfParam * i + k][noOfSortVar + 5] = "";
			}

		}

		procInputInst.getInitialParameterValueObj()
				.setInitialParameterValueForCA(dataForInitialParameter);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	void rowAndColNoDetctionForIntParamTable(ProcessingInputsInfo procInputInst) {
		CaNoOfSubjectDeterminer determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();

		paramListInst = InVitroParamAndUnitListLoader.createParamListInstance();

		paramListInst.createParameterList();

		numberOfRowsForInitialValue = determineNoOfSubInst.noOfSubject
				* procInputInst.getProfileAndParamInfoObj()
						.getNumberOfParameter();
		numberOfColumnsForInitialValue = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size() + 6;

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
			initialParamTableForInVitro = new MyJTable(
					initialParameterTableModel, colIndices);
			initialParamTableForInVitro.getTableHeader().setReorderingAllowed(
					false);

			for (int i = 0; i < initialParamTableForInVitro.getRowCount(); i++)
				for (int j = 0; j < initialParamTableForInVitro
						.getColumnCount(); j++)
					initialParamTableForInVitro.isCellSelected(i, j);

			JScrollPane initialValueScrollPane = new JScrollPane(
					initialParamTableForInVitro,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			initialValueScrollPane.setBackground(Color.white);

			initialValueScrollPane.setVisible(true);
			paramInitValIFForInVitro.getContentPane().removeAll();
			paramInitValIFForInVitro.add(initialValueScrollPane);

			paramInitValIFForInVitro
					.setSize(paramInitValIFForInVitro.getSize());
		}

	}

}
