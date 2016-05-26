package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import view.CaInitEstimateDispGuiCreator.InitParamTableEventhandler;
import Common.JinternalFrameFunctions;

public class CaLinkParamInitEstimateGuiCreator {

	


	MyJTable tableForLinkInitialParameterValueForCA;
	JInternalFrame linkInitParamValueForCAIF;
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
	ParameterAndUnitListLoader paramAndUnitListInst;
	CaNoOfSubjectDeterminer determineNoOfSubInst;
	
	public static CaLinkParamInitEstimateGuiCreator CALink_INIT_EST_INST = null;
	
	public static CaLinkParamInitEstimateGuiCreator createLinkInitEstGui() throws RowsExceededException, WriteException, BiffException, IOException{
		if(CALink_INIT_EST_INST == null){
			CALink_INIT_EST_INST = new CaLinkParamInitEstimateGuiCreator();
		}
		return CALink_INIT_EST_INST;
	}
	
	
	public CaLinkParamInitEstimateGuiCreator() throws RowsExceededException, WriteException, BiffException, IOException
	{
		createLinkParamInitValueIF();
	}
	
	final class LinkInitParamTableEventhandler implements TableModelListener {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		public void tableChanged(TableModelEvent arg0) {

			int rowChanged = tableForLinkInitialParameterValueForCA
					.getSelectedRow();
			int colChanged = tableForLinkInitialParameterValueForCA
					.getSelectedColumn();

			
			ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();

			String tempStr;
			if(rowChanged >=0 && colChanged >=0)
			tempStr = (String) tableForLinkInitialParameterValueForCA
					.getValueAt(rowChanged, colChanged);
			else
				tempStr = "";
			
		
			
			try {
				if (tempStr.equals("") ) {

				} else
					Double.parseDouble(tempStr);

				for (int i = 0; i < tableForLinkInitialParameterValueForCA
						.getRowCount(); i++) {
					for (int j = 0; j < tableForLinkInitialParameterValueForCA
							.getColumnCount(); j++) {
						
												
						procInputInst
								.getInitialParameterValueObj()
								.setInitialParameterValueAt(
										i,
										j,
										(String) tableForLinkInitialParameterValueForCA
												.getValueAt(i, j)
												+ "");
						CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
					}
				}

			} catch (Exception e) {
				try {
					JOptionPane.showMessageDialog(
							PdMainScreenCreator.createPdMainScreenInstance().pdMainInternalFrame,
							"Please ensure initial value is a Number", "Confirm",
							JOptionPane.YES_OPTION);
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

				if(rowChanged >=0 && colChanged >=0)
				tableForLinkInitialParameterValueForCA.setValueAt(
						procInputInst
								.getInitialParameterValueObj()
								.getInitialParameterValueAt(rowChanged,
										colChanged), rowChanged, colChanged);
				
			}

		}
	}

	public void createLinkParamInitValueIF() throws RowsExceededException, WriteException, BiffException, IOException {
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		
		linkInitParamValueForCAIF = new JInternalFrame(
				"Intial Parameter Value", true, true, true, true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(linkInitParamValueForCAIF);
		

		linkInitParamValueForCAIF
				.setLayout(new GridLayout(1, 1));
		int width = 0;
		int height = 0;
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		if(analysisType.equals("pd"))
		{
			width = PdMainScreenCreator.createPdMainScreenInstance().pdMainInternalFrame.getWidth()
				- CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getWidth();
			height = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getHeight();	
		}
		else
			if(analysisType.equals("mm"))
			{
				width = MmMainScreenCreator.createMmMainScreenInstance().mmMainInternalFrame.getWidth()
				- CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getWidth();
			height = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getHeight();	
	
			}
		
			else
				if(analysisType.equals("pkpdlink"))
				{
					width = PkPdLinkMainScreenCreator.createPkPdLinkMainScreenInstance().pkpdLinkMainInternalFrame.getWidth()
					- CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getWidth();
				height = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getHeight();	
		
				}
				else
					if(analysisType.equals("irm"))
					{
						width = IrmMainScreenCreator.createIrmMainScreenInstance().irmMainInternalFrame.getWidth()
						- CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getWidth();
					height = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getHeight();	
			
					}
		
		
		
		linkInitParamValueForCAIF.setLocation(CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
				.getX()
				+ CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getWidth(),
				CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getY());
		linkInitParamValueForCAIF.setSize(width, height);
		
		
		linkInitParamValueForCAIF.setVisible(true);
		linkInitParamValueForCAIF
				.setBorder(viewLayerInst.b);
		
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane.add(linkInitParamValueForCAIF);
		//linkParameterInitialValueInternalFrameForCA.moveToFront();
		initialValueScrollPane = new JScrollPane();
		initialValueScrollPane.setBackground(Color.white);

		initialValueScrollPane.setVisible(false);
		linkInitParamValueForCAIF
				.add(initialValueScrollPane);

	}

	public void createInitialParameterValueTable() {

		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		rowAndColNoDetctionForIntParamTable(procInputInst );
		
		int noOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		defaultDataForIntParamTable( procInputInst, noOfSortVar);

		if (ifLibraryModelIsChanged == true
				|| procInputInst.getMappingDataObj()
						.getIfMappingScreenIsChanged() == true) {

			updatingIntParamTableForLMOrMappingChange(
					procInputInst, noOfSortVar);

		}

		else if (boundaryValueSelection == 1
				&& initialValueSlection == 0) {

			updatingIntParamTableCase1( procInputInst,
					noOfSortVar);

		} else if (boundaryValueSelection == 1
				&& initialValueSlection == 1) {

		updatingIntParamTableCase2(procInputInst, noOfSortVar);

		} else if (boundaryValueSelection != 1
				&& initialValueSlection == 0) {
			updatingIntParamTableCase3(procInputInst, noOfSortVar);

		} else if (boundaryValueSelection != 1
				&& initialValueSlection == 1) {

			updatingIntParamTableCase4(procInputInst, noOfSortVar);

		}

		createColNameForIntParamTable( noOfSortVar);

		int[] colIndices = makeNecessaryColsUneditable(noOfSortVar);

		initParamTableBuilding(colIndices);

		if (appInfo.isRetrievalFlag() == true) {
			for (int i = 0; i < tableForLinkInitialParameterValueForCA
					.getRowCount(); i++) {
				for (int j = 0; j < tableForLinkInitialParameterValueForCA
						.getColumnCount(); j++) {
					tableForLinkInitialParameterValueForCA.setValueAt(
							procInputInst
									.getInitialParameterValueObj()
									.getInitialParameterValueAt(i, j), i, j);

				}
			}
		}

		 procInputInst.getMappingDataObj()
				.setIfMappingScreenIsChanged(false);
		 CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	public void initParamTableBuilding(int[] colIndices) {
		initialParameterTableModel = new DefaultTableModel(
				dataForInitialParameter,
				columnNameForInitialParameter);
		tableForLinkInitialParameterValueForCA = new MyJTable(
				initialParameterTableModel, colIndices);
		new ReorderableJList(tableForLinkInitialParameterValueForCA);
		tableForLinkInitialParameterValueForCA.getTableHeader()
				.setReorderingAllowed(false);
		

		for (int i = 0; i < tableForLinkInitialParameterValueForCA
				.getRowCount(); i++)
			for (int j = 0; j < tableForLinkInitialParameterValueForCA
					.getColumnCount(); j++)
				tableForLinkInitialParameterValueForCA.isCellSelected(
						i, j);
		tableForLinkInitialParameterValueForCA.getModel()
				.addTableModelListener(new LinkInitParamTableEventhandler());

		initialValueScrollPane = new JScrollPane(
				tableForLinkInitialParameterValueForCA,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		initialValueScrollPane.setBackground(Color.white);

		initialValueScrollPane.setVisible(true);
		linkInitParamValueForCAIF.getContentPane()
				.removeAll();
		linkInitParamValueForCAIF
				.add(initialValueScrollPane);

		linkInitParamValueForCAIF
				.setSize(linkInitParamValueForCAIF
						.getSize());
	}

	private int[] makeNecessaryColsUneditable(int noOfSortVar) {
		int lengthOfUneditableColIndices;
		int[] colIndices;

		if (initialValueSlection > 0
				&& boundaryValueSelection == 1) {
			lengthOfUneditableColIndices = noOfSortVar + 1;
			colIndices = new int[lengthOfUneditableColIndices];
			for (int i = 0; i <= noOfSortVar; i++) {

				colIndices[i] = i;

			}

		} else if (initialValueSlection > 0
				&& boundaryValueSelection != 1) {
			lengthOfUneditableColIndices = noOfSortVar + 3;

			colIndices = new int[lengthOfUneditableColIndices];
			for (int i = 0; i <= noOfSortVar; i++) {

				colIndices[i] = i;

			}

			colIndices[lengthOfUneditableColIndices - 1] = noOfSortVar + 3;
			colIndices[lengthOfUneditableColIndices - 2] = noOfSortVar + 2;

		} else if (initialValueSlection == 0
				&& boundaryValueSelection == 1) {

			lengthOfUneditableColIndices = noOfSortVar + 2;
			colIndices = new int[lengthOfUneditableColIndices];

			for (int i = 0; i <= noOfSortVar; i++) {

				colIndices[i] = i;

			}

			colIndices[lengthOfUneditableColIndices - 1] = noOfSortVar + 1;

		} else if (initialValueSlection == 0
				&& boundaryValueSelection != 1) {

			lengthOfUneditableColIndices = noOfSortVar + 4;
			colIndices = new int[lengthOfUneditableColIndices];

			for (int i = 0; i < noOfSortVar + 4; i++) {

				colIndices[i] = i;

			}

		} else {
			lengthOfUneditableColIndices = noOfSortVar + 4;
			colIndices = new int[lengthOfUneditableColIndices];
		}

		return colIndices;
	}

	private void createColNameForIntParamTable(int noOfSortVar) {
		columnNameForInitialParameter[noOfSortVar] = "Parameter";
		columnNameForInitialParameter[noOfSortVar + 1] = "Initial Value";
		columnNameForInitialParameter[noOfSortVar + 2] = "Lower Bound";
		columnNameForInitialParameter[noOfSortVar + 3] = "Upper Bound";
	}

	private void updatingIntParamTableCase4(ProcessingInputsInfo procInputInst, int noOfSortVar) {
		System.out.println(" boundary !=1 and initial value=1");

		for (int i = 0; i < numberOfRowsForInitialValue; i++)

		{
			try {
				dataForInitialParameter[i][noOfSortVar + 1] = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(i, noOfSortVar + 1);
			} catch (Exception e) {
				e.printStackTrace();
				dataForInitialParameter[i][noOfSortVar + 1] = "";
			}
		}

		for (int i = 0; i < numberOfRowsForInitialValue; i++)

			dataForInitialParameter[i][noOfSortVar + 2] = "";
		for (int i = 0; i < numberOfRowsForInitialValue; i++)

			dataForInitialParameter[i][noOfSortVar + 3] = "";

		procInputInst
				.getInitialParameterValueObj().setInitialParameterValueForCA(
						dataForInitialParameter);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	private void updatingIntParamTableCase3(ProcessingInputsInfo procInputInst,int noOfSortVar) {
		System.out.println(" boundary !=1 and initial value=0");

		for (int i = 0; i < numberOfRowsForInitialValue; i++)

			dataForInitialParameter[i][noOfSortVar + 1] = "";

		for (int i = 0; i < numberOfRowsForInitialValue; i++)

			dataForInitialParameter[i][noOfSortVar + 2] = "";
		for (int i = 0; i < numberOfRowsForInitialValue; i++)

			dataForInitialParameter[i][noOfSortVar + 3] = "";

		procInputInst
				.getInitialParameterValueObj().setInitialParameterValueForCA(
						dataForInitialParameter);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	private void updatingIntParamTableCase2(ProcessingInputsInfo procInputInst, int noOfSortVar) {
		for (int i = 0; i < numberOfRowsForInitialValue; i++) {
			try {
				dataForInitialParameter[i][noOfSortVar + 1] = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(i, noOfSortVar + 1);
			} catch (Exception e) {
				e.printStackTrace();
				dataForInitialParameter[i][noOfSortVar + 1] = "";
			}

		}

		for (int i = 0; i < numberOfRowsForInitialValue; i++) {
			try {
				dataForInitialParameter[i][noOfSortVar + 2] = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(i, noOfSortVar + 2);
			} catch (Exception e) {
				e.printStackTrace();
				dataForInitialParameter[i][noOfSortVar + 2] = "";
			}
		}

		for (int i = 0; i < numberOfRowsForInitialValue; i++) {
			try {
				dataForInitialParameter[i][noOfSortVar + 3] = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(i, noOfSortVar + 3);
			} catch (Exception e) {
				e.printStackTrace();
				dataForInitialParameter[i][noOfSortVar + 3] = "";
			}

		}

		procInputInst.getInitialParameterValueObj().setInitialParameterValueForCA(
						dataForInitialParameter);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	private void updatingIntParamTableCase1(ProcessingInputsInfo procInputInst, int noOfSortVar) {
		for (int i = 0; i < numberOfRowsForInitialValue; i++)

			dataForInitialParameter[i][noOfSortVar + 1] = "";

		for (int i = 0; i < numberOfRowsForInitialValue; i++) {
			try {
				dataForInitialParameter[i][noOfSortVar + 2] = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(i, noOfSortVar + 2);
			} catch (Exception e) {
				e.printStackTrace();
				dataForInitialParameter[i][noOfSortVar + 2] = "";
			}
		}
		for (int i = 0; i < numberOfRowsForInitialValue; i++) {
			try {
				dataForInitialParameter[i][noOfSortVar + 3] = procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(i, noOfSortVar + 3);
			} catch (Exception e) {
				e.printStackTrace();
				dataForInitialParameter[i][noOfSortVar + 3] = "";
			}
		}

		procInputInst
				.getInitialParameterValueObj().setInitialParameterValueForCA(
						dataForInitialParameter);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	private void updatingIntParamTableForLMOrMappingChange(ProcessingInputsInfo procInputInst,int noOfSortVar) {
		for (int i = 0; i < dataForInitialParameter.length; i++)
			for (int j = noOfSortVar + 1; j < dataForInitialParameter[0].length; j++)
				dataForInitialParameter[i][j] = "";
		procInputInst
				.getInitialParameterValueObj().setInitialParameterValueForCA(
						dataForInitialParameter);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	public void defaultDataForIntParamTable(ProcessingInputsInfo procInputInst,int noOfSortVar) {

		determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();
		paramAndUnitListInst = ParameterAndUnitListLoader
				.createParamAndUnitListInstance();

		paramAndUnitListInst.createParameterAndCorrespondingUnitList();
		columnNameForInitialParameter = new String[numberOfColumnsForInitialValue];
		dataForInitialParameter = new String[numberOfRowsForInitialValue][numberOfColumnsForInitialValue];
		
		int noOfParam = procInputInst.getProfileAndParamInfoObj().getNumberOfParameter();
	
		for (int i = 0; i < noOfSortVar; i++) {
			columnNameForInitialParameter[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		for (int i = 0; i < determineNoOfSubInst.noOfSubject; i++) {
			for (int k = 0; k < noOfParam; k++) {
				for (int j = 0; j < noOfSortVar; j++) {
					dataForInitialParameter[noOfParam
							* i + k][j] = determineNoOfSubInst
							.dataSortVariables[i][j];
				}
				dataForInitialParameter[noOfParam
						* i + k][noOfSortVar] = procInputInst.getProfileAndParamInfoObj()
						.getParameterNameForCA()[k];
				
				dataForInitialParameter[noOfParam * i + k][noOfSortVar+1] ="";
				dataForInitialParameter[noOfParam * i + k][noOfSortVar+2] ="";
				dataForInitialParameter[noOfParam * i + k][noOfSortVar+3] ="";
			}
		}
		
		procInputInst.getInitialParameterValueObj().setInitialParameterValueForCA(
				dataForInitialParameter);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	public void rowAndColNoDetctionForIntParamTable(ProcessingInputsInfo procInputInst) {
		determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();
		paramAndUnitListInst = ParameterAndUnitListLoader
				.createParamAndUnitListInstance();

		numberOfRowsForInitialValue = determineNoOfSubInst
				.noOfSubject
				* procInputInst.getProfileAndParamInfoObj().getNumberOfParameter();
		numberOfColumnsForInitialValue = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size() + 4;
	}

	public void updateInitialParameterTable( WorkSheetOutputs  wsOutputInst, ProcessingInputsInfo procInputInst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		calculateInitialvalue = false;

		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (initialValueSlection == 0) {

			String[][] dataForInitialParameter = new String[wsOutputInst.getSpreadSheetOutputs()
					.getInitialParameterForCA().length - 1][wsOutputInst.getSpreadSheetOutputs()

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
				columnNameForInitialParameter[i] = procInputInst.getMappingDataObj()
						.getSortVariablesListVector().get(i);

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

			initialParameterTableModel =new DefaultTableModel(
							dataForInitialParameter,
							columnNameForInitialParameter);
			tableForLinkInitialParameterValueForCA = new MyJTable(
					initialParameterTableModel,
							colIndices);
			tableForLinkInitialParameterValueForCA
					.getTableHeader().setReorderingAllowed(false);

			for (int i = 0; i < tableForLinkInitialParameterValueForCA.getRowCount(); i++)
				for (int j = 0; j < tableForLinkInitialParameterValueForCA
						.getColumnCount(); j++)
					tableForLinkInitialParameterValueForCA
							.isCellSelected(i, j);

			JScrollPane initialValueScrollPane = new JScrollPane(
					tableForLinkInitialParameterValueForCA,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			initialValueScrollPane.setBackground(Color.white);

			initialValueScrollPane.setVisible(true);
			linkInitParamValueForCAIF
					.getContentPane().removeAll();
			linkInitParamValueForCAIF.add(
					initialValueScrollPane);

			linkInitParamValueForCAIF
					.setSize(
							linkInitParamValueForCAIF
									.getSize());
		}

	}

	/*@Override
	public void updateGuiView(String str) {
		createInitialParameterValueTable();
	}*/


}
