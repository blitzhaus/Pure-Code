package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class CaMappingDispGuiCreator implements EventCodes{
	
	
	JInternalFrame mappingInternalFrame;
	JList caavailableColumnsList;
	JList casortVariablesList;
	JList carryAlongList;
	JButton SortAndAvailableButton;
	JButton moveToCarryAlongButton;
	JButton setAsSubjectButton;
	JButton YAndAvailableButton;
	JButton xAndAvailableButton;
	DefaultListModel sortVariableListModel;
	DefaultListModel availableVariablesListmodel;
	DefaultListModel carryAlongVariableListmodel;
	JTextField xVariableTextField;
	JTextField yVariableTextField;
	JTextField subjectTextField;
	JTextField endTimeVariableTextField;
	JButton moveToEndTimeButton;
	Icon toLeft;
	Icon toRight;

	

	
	public static CaMappingDispGuiCreator PD_MAP_DISP = null;
	public static CaMappingDispGuiCreator createMappingInstance() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PD_MAP_DISP == null){
			PD_MAP_DISP = new CaMappingDispGuiCreator();
		}
		return PD_MAP_DISP;
	}

	public CaMappingDispGuiCreator() throws RowsExceededException, WriteException, BiffException, IOException{ 
		createMappinginternalFrame();
	}
	
	
	private void createMappinginternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {

		// TODO Auto-generated method stub
		mappingInternalFrame = new JInternalFrame("Mapping ", false, false,
				false, false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(mappingInternalFrame);
		mappingInternalFrame.setBackground(Color.white);
	
		
		DDViewLayer.createMTInstance().mainTabbedFrame.setSize(DDViewLayer
				.createMTInstance().mainTabbedFrameOriginalWidth, DDViewLayer
				.createMTInstance().mainTabbedFrameoriginalHeight);
		int width = 0;
		int height = 0;
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		
		if(analysisType.equals("pk"))
		{
			width = PkMainScreenCreator.createPkMainScreenInstance().pkMainInternalFrame.getWidth()
				- CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getWidth();
			height = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getHeight();	
		}
		else
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
			
					}else
						if(analysisType.equals("ascii"))
						{
							width = AsciiMainScreen.createAsciiMainScreenInstance().asciiMainInternalFrame.getWidth()
							- CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getWidth();
						height = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getHeight();	
				
						}
		
		
		
		mappingInternalFrame.setLocation(CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
				.getX()
				+ CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getWidth(),
				CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame.getY());
		mappingInternalFrame.setSize(width, height);
		mappingInternalFrame.setVisible(true);
		JDesktopPane mappinginternalFrameDesktopPane = new JDesktopPane();
		mappingInternalFrame.setContentPane(mappinginternalFrameDesktopPane);
		mappingInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		createMappingPanel();
		
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane.add(mappingInternalFrame);
	}
	
	String analysisType;
	public void createMappingPanel(){
	
		toLeft = new ImageIcon("left.png");
		toRight = new ImageIcon("right.png");
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAnalysisType();
		createAvailableColumnsIF();
		createTimeConButtonsIF();
		createTimeConIF();
		createSortCarryButtonsIF();
		createSortCarryIF();
		mappingInternalFrame.getContentPane().setBackground(caSortCarryIF.getBackground());
	}

	JInternalFrame caSortCarryIF;
	private void createSortCarryIF(){
		
		caSortCarryIF = new JInternalFrame("carry along and sort list internal frame", false,false,false,false);
		caSortCarryIF.setLocation(caSortCarryButIF.getX()+caSortCarryButIF.getWidth()
				, caSortCarryButIF.getY());
		caSortCarryIF.setSize(caSortCarryButIF.getWidth()*2,caSortCarryButIF.getHeight());
		caSortCarryIF.setVisible(true);
		caSortCarryIF.setBorder(null);
		caSortCarryIF.setLayout(new GridBagLayout());
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(caSortCarryIF);
		
		sortVariableListModel = new DefaultListModel();
		casortVariablesList = new JList(sortVariableListModel);
		casortVariablesList.setVisibleRowCount(5);
		casortVariablesList.addListSelectionListener(DDViewLayer.createViewLayerInstance()) ;
		
		JScrollPane scrlPane = new JScrollPane(casortVariablesList);
		scrlPane.setBorder(BorderFactory.createTitledBorder("Sort Columns"));
		scrlPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrlPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrlPane.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints sortLstCon = new GridBagConstraints();
		sortLstCon.gridx = 0;
		sortLstCon.gridy = 0;
		sortLstCon.weightx = 0.5;
		sortLstCon.anchor = GridBagConstraints.LINE_START;
		caSortCarryIF.getContentPane().add(scrlPane, sortLstCon);
		carryAlongVariableListmodel = new DefaultListModel();
		carryAlongList = new JList(carryAlongVariableListmodel);
		//carryAlongList.setVisibleRowCount(5);
		JScrollPane scrollPane = new JScrollPane(carryAlongList);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Carry Columns"));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints carryLstCon = new GridBagConstraints();
		carryLstCon.gridx = 0;
		carryLstCon.gridy = 1;
		carryLstCon.weightx = 0.5; 
		carryLstCon.anchor = GridBagConstraints.LINE_START;
		caSortCarryIF.getContentPane().add(scrollPane, carryLstCon);
		mappingInternalFrame.getContentPane().add(caSortCarryIF);
	}

	JInternalFrame caSortCarryButIF;
	private void createSortCarryButtonsIF(){
		
		caSortCarryButIF = new JInternalFrame("sort carry along internal frame", false,false,false,false);
		caSortCarryButIF.setLocation(caTimeConIF.getX()+caTimeConIF.getWidth(), caTimeConIF.getY());
		caSortCarryButIF.setSize(caTimeConIF.getWidth(), caTimeConIF.getHeight());
		caSortCarryButIF.setVisible(true);
		caSortCarryButIF.setBorder(null);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(caSortCarryButIF);
		caSortCarryButIF.setLayout(new GridBagLayout());
		
		SortAndAvailableButton = new JButton("", toRight);
		SortAndAvailableButton.addActionListener(DDViewLayer.createViewLayerInstance());
		SortAndAvailableButton.setActionCommand(AVAILAANDSORTCA);
		SortAndAvailableButton.setBackground(null);
		SortAndAvailableButton.setBorder(null);
		GridBagConstraints sortCon = new GridBagConstraints();
		sortCon.gridx = 0;
		sortCon.gridy = 0;
		sortCon.weighty = 0.5;
		sortCon.anchor = GridBagConstraints.LINE_END;
		caSortCarryButIF.getContentPane().add(SortAndAvailableButton, sortCon);
		
		moveToCarryAlongButton = new JButton("", toRight);
		moveToCarryAlongButton.setBorder(null);
		moveToCarryAlongButton.setBackground(null);
		GridBagConstraints carryCon = new GridBagConstraints();
		carryCon.gridx = 0;
		carryCon.gridy = 1;
		carryCon.weighty = 0.5;
		carryCon.anchor = GridBagConstraints.LINE_END;
		caSortCarryButIF.getContentPane().add(moveToCarryAlongButton, carryCon);
		mappingInternalFrame.getContentPane().add(caSortCarryButIF);
	}

	JInternalFrame caTimeConIF;
	JTextField caFuncTF;
	private void createTimeConIF() {
		
		String indepVar = "";
		String depVar = "";
		
		if(analysisType.equals("pk") || analysisType.equals("mm") ||analysisType.equals("pkpdlink") ||analysisType.equals("irm"))
		{
			depVar = "Time";
		} else if (analysisType.equals("pd"))
		{
			depVar = "Concentration";
		}
		
		if(analysisType.equals("pk") || analysisType.equals("mm"))
		{
			indepVar = "Concentration";
		} else if(analysisType.equals("pd") || analysisType.equals("pkpdlink")||analysisType.equals("irm"))
		{
			indepVar = "Response";
		} else if (analysisType.equals("ascii"))
		{
			indepVar = "X Variable";
			depVar = "Y Variable";
		}
		
		
		caTimeConIF = new JInternalFrame("Time conc internal frame", false,false,false,false);
		caTimeConIF.setLocation(caTimeConcButtonsIF.getX()+caTimeConcButtonsIF.getWidth()
				, caTimeConcButtonsIF.getY());
		caTimeConIF.setSize(caTimeConcButtonsIF.getWidth(), caTimeConcButtonsIF.getHeight());
		caTimeConIF.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(caTimeConIF);
		caTimeConIF.setBorder(null);
		caTimeConIF.setLayout(new GridBagLayout());
		
		xVariableTextField = new JTextField();
		xVariableTextField.setBorder(BorderFactory.createTitledBorder(indepVar));
		xVariableTextField.setEditable(false); 
		xVariableTextField.addFocusListener(DDViewLayer.createViewLayerInstance());
		xVariableTextField.setColumns(8);
		
		GridBagConstraints xVarCon = new GridBagConstraints();
		xVarCon.gridx = 0;
		xVarCon.gridy = 0;
		xVarCon.weighty = 0.5;
		xVarCon.anchor = GridBagConstraints.LINE_START;
		caTimeConIF.getContentPane().add(xVariableTextField, xVarCon);
		yVariableTextField = new JTextField();
		yVariableTextField.setBorder(BorderFactory.createTitledBorder(depVar));
		yVariableTextField.setColumns(8);
		yVariableTextField.setEditable(false);
		yVariableTextField.addFocusListener(DDViewLayer.createViewLayerInstance());
		GridBagConstraints yVarCon = new GridBagConstraints();
		yVarCon.gridx = 0;
		yVarCon.gridy = 1;
		yVarCon.weighty = 0.5;
		yVarCon.weightx = 0.5;
		yVarCon.anchor = GridBagConstraints.LINE_START;
		caTimeConIF.getContentPane().add(yVariableTextField, yVarCon);
		
		if(analysisType.equals("ascii")){
			caFuncTF = new JTextField();
			caFuncTF.setColumns(8);
			caFuncTF.setEditable(false);
			caFuncTF.addFocusListener(DDViewLayer.createViewLayerInstance());
			caFuncTF.setBorder(BorderFactory.createTitledBorder("Function"));
			GridBagConstraints funcCon = new GridBagConstraints();
			funcCon.gridx = 0;
			funcCon.gridy = 2;
			funcCon.weightx = 0.5;
			funcCon.weighty = 0.5;
			funcCon.anchor = GridBagConstraints.LINE_START;
			caTimeConIF.getContentPane().add(caFuncTF, funcCon);
		}
		
		mappingInternalFrame.getContentPane().add(caTimeConIF);
	}

	JInternalFrame caTimeConcButtonsIF;
	JButton caFuncButton;
	

	private void createTimeConButtonsIF(){
		caTimeConcButtonsIF = new JInternalFrame("time and conc buttons", false,false,false,false);
		caTimeConcButtonsIF.setLocation(caAvailColIF.getX()+caAvailColIF.getWidth(), caAvailColIF.getY());
		caTimeConcButtonsIF.setSize(caAvailColIF.getWidth()/2, caAvailColIF.getHeight());
		caTimeConcButtonsIF.setVisible(true);
		caTimeConcButtonsIF.setLayout(new GridBagLayout());
		caTimeConcButtonsIF.setBorder(null);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(caTimeConcButtonsIF);
		xAndAvailableButton = new JButton("", toRight);
		xAndAvailableButton.addActionListener(DDViewLayer.createViewLayerInstance());
		xAndAvailableButton.setActionCommand(AVAILANDXCA);
		xAndAvailableButton.setBackground(null);
		xAndAvailableButton.setBorder(null);
		GridBagConstraints caTimeConstraints = new GridBagConstraints();
		caTimeConstraints.gridx = 0;
		caTimeConstraints.gridy = 0;
		caTimeConstraints.weighty = 0.5;
		caTimeConstraints.anchor = GridBagConstraints.LINE_END;
		caTimeConcButtonsIF.getContentPane().add(xAndAvailableButton, caTimeConstraints);
		
		YAndAvailableButton = new JButton("", toRight);
		YAndAvailableButton.addActionListener(DDViewLayer.createViewLayerInstance());
		YAndAvailableButton.setActionCommand(AVAILANDYCA);
		YAndAvailableButton.setBackground(null);
		YAndAvailableButton.setBorder(null);
		GridBagConstraints caConcCon = new GridBagConstraints();
		caConcCon.gridx = 0;
		caConcCon.gridy = 1;
		caConcCon.weighty = 0.5;
		caConcCon.anchor = GridBagConstraints.LINE_END;
		caTimeConcButtonsIF.getContentPane().add(YAndAvailableButton, caConcCon);
		
		if(analysisType.equals("ascii")){
			caFuncButton = new JButton("", toRight);
			caFuncButton.setBackground(null);
			caFuncButton.setBorder(null);
			caFuncButton.addActionListener(DDViewLayer.createViewLayerInstance());
			caFuncButton.setActionCommand(AVAILANDFUNCCA);
			GridBagConstraints funcCon = new GridBagConstraints();
			funcCon.gridx = 0;
			funcCon.gridy = 2;
			funcCon.weighty = 0.5;
			funcCon.anchor = GridBagConstraints.LINE_END;
			caTimeConcButtonsIF.getContentPane().add(caFuncButton, funcCon);
		}
		mappingInternalFrame.getContentPane().add(caTimeConcButtonsIF);
		
	}
	
	JInternalFrame caAvailColIF;
	private void createAvailableColumnsIF() {
		
		
		caAvailColIF = new JInternalFrame("Available columns", false,false,false,false);
		caAvailColIF.setLocation(0, 0);
		caAvailColIF.setSize((mappingInternalFrame.getWidth()/7)*2
				, mappingInternalFrame.getHeight());
		caAvailColIF.setVisible(true);
		caAvailColIF.setBorder(null);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(caAvailColIF);
		availableVariablesListmodel = new DefaultListModel();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		procInputInst.getMappingDataObj()
				.getAvailableColumnsVector().setSize(0);
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getColumnPropertiesArrayList()
				.size(); i++) {
			availableVariablesListmodel.add(i, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName());
			procInputInst.getMappingDataObj()
					.getAvailableColumnsVector().add(
							i,
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
									.getColumnPropertiesArrayList().get(i)
									.getColumnName());
		}			
		caavailableColumnsList = new JList(availableVariablesListmodel);
		caavailableColumnsList.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Available Variables", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		caavailableColumnsList.addListSelectionListener(DDViewLayer.createViewLayerInstance());
		JScrollPane scrlPane = new JScrollPane(caavailableColumnsList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		caAvailColIF.getContentPane().add(scrlPane);
		mappingInternalFrame.getContentPane().add(caAvailColIF);
	}

}
