package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class ScaSetupDispComp implements EventCodes {

	public static ScaSetupDispComp SCA_SETUP_DISP_COMP = null;

	public static ScaSetupDispComp createSetupDispConpInst() {

		if (SCA_SETUP_DISP_COMP == null) {
			SCA_SETUP_DISP_COMP = new ScaSetupDispComp("just object creation");
		}
		return SCA_SETUP_DISP_COMP;
	}

	public ScaSetupDispComp(String dummy) {

	}

	void createSetupDisplayComponentsInternalFrame() {
		createMappinginternalFrame();
	}

	JInternalFrame mappingInternalFrame;
	DefaultListModel sortVariableListModel;
	JList availableColumnsList;
	JList sortVariablesList;
	JButton SortAndAvailableButton;
	JButton xAndAvailableButton;
	JTextField timeVariableTextField;
	JButton timeAndAvailableButton;
	JButton moveToConcentrationButton;
	JButton moveToEffectButton;
	JTextField conbcentrationVariableTextField;
	JTextField effectVariableTextField;
	JInternalFrame midIF;
	JInternalFrame leftArrowsIF;
	JInternalFrame leftIF;
	DefaultListModel availableVariablesListmodel;
	JInternalFrame rightArrowsinternalFrameForSCA;
	JDesktopPane mappinginternalFrameDesktopPane;
	JInternalFrame rightInternalFrameForSCA;
	JButton moveToCarryAlongButton;
	JButton setAsSubjectButton;
	JButton YAndAvailableButton;
	boolean isSortToAvailable;
	boolean isAvailableToSort;
	boolean isXToAvailable;
	boolean isAvailableToX;
	boolean isYToAvailable;
	boolean isAvailableToEndTime;
	boolean isEndTimeToAvailable;
	boolean isAvailableToVolume;
	boolean isVolumeToAvailable;
	boolean isAvailableToCarryAlong;
	boolean isCarryAlongToAvailable;
	boolean isAvailableToSubject;
	boolean isSubjectToAvailable;
	String xColumnName;
	String ycolumnName;
	boolean isTimeToAvailable;
	boolean isAvailableToTime;
	boolean isAvailableToConcentration;
	boolean isConcentrationToAvailable;
	boolean isAvailableToEffect;
	boolean isEffectToAvailable;
	String concentrationColumnName;
	String effectColumnName;
	String timeColumnName;
	int concentrationColumnCorrespondinOriginalIndex;
	int effectColumnCorrespondinOriginalIndex;
	int timeColumnCorrespondinOriginalIndex;
	Icon toLeft = new ImageIcon("left.png");
	Icon toRight = new ImageIcon("right.png");

	private void createMappinginternalFrame() {

		createMappingFrame();
		
		
		leftIFCreation();
		leftArrowsInternalFrame();
		midIFCreation();
		rightArrowsInternalFrame();
		rightInternalFrame();

		mappinginternalFrameDesktopPane.add(midIF);
		midIF.moveToFront();
		mappinginternalFrameDesktopPane.add(leftArrowsIF);
		leftArrowsIF.moveToFront();

		mappinginternalFrameDesktopPane.add(leftIF);
		leftIF.moveToFront();
		mappinginternalFrameDesktopPane.add(rightArrowsinternalFrameForSCA);
		rightArrowsinternalFrameForSCA.moveToFront();
		mappinginternalFrameDesktopPane.add(rightInternalFrameForSCA);
		rightInternalFrameForSCA.moveToFront();

	}

	private void rightInternalFrame() {

		rightInternalFrameForSCA = new JInternalFrame("right", false, false,
				false, false);
		//rightInternalFrameForSCA.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(rightInternalFrameForSCA);
		rightInternalFrameForSCA.setLocation(rightArrowsinternalFrameForSCA
				.getX()
				+ rightArrowsinternalFrameForSCA.getWidth(),
				rightArrowsinternalFrameForSCA.getY());
		rightInternalFrameForSCA.setSize(leftIF.getWidth(),
				leftIF.getHeight());
		rightInternalFrameForSCA.setVisible(true);
		rightInternalFrameForSCA.setLayout(new GridBagLayout());
		rightInternalFrameForSCA.moveToFront();

		rightInternalFrameForSCA.setBorder(null);

	/*	JLabel conbcentrationLableForSCA = new JLabel("Concentration");
		GridBagConstraints conbcentrationLableForSCACon = new GridBagConstraints();
		conbcentrationLableForSCACon.gridx = 0;
		conbcentrationLableForSCACon.gridy = 0;

		conbcentrationLableForSCACon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrameForSCA.getContentPane().add(
				conbcentrationLableForSCA, conbcentrationLableForSCACon);*/
		
	

		conbcentrationVariableTextField = new JTextField();
		conbcentrationVariableTextField.setText("");
		//conbcentrationVariableTextField.setBackground(Color.white);
		conbcentrationVariableTextField.setEditable(false);
		conbcentrationVariableTextField.addFocusListener(DDViewLayer
				.createViewLayerInstance());
		conbcentrationVariableTextField.setColumns(12);
		conbcentrationVariableTextField.setVisible(true);
		conbcentrationVariableTextField.setBorder(BorderFactory.createTitledBorder("Concentration"));
		GridBagConstraints conbcentrationVariableTextFieldForSCACon = new GridBagConstraints();
		conbcentrationVariableTextFieldForSCACon.gridx = 0;
		conbcentrationVariableTextFieldForSCACon.gridy = 1;
		conbcentrationVariableTextFieldForSCACon.fill = GridBagConstraints.HORIZONTAL;
		conbcentrationVariableTextFieldForSCACon.weighty = 0.5;
		conbcentrationVariableTextFieldForSCACon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrameForSCA.getContentPane().add(
				conbcentrationVariableTextField,
				conbcentrationVariableTextFieldForSCACon);

		/*JLabel effectLableForSCA = new JLabel("Effect");
		GridBagConstraints effectLableForSCACon = new GridBagConstraints();
		effectLableForSCACon.gridx = 0;
		effectLableForSCACon.gridy = 2;
		effectLableForSCACon.weightx = 0.5;
		effectLableForSCACon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrameForSCA.getContentPane().add(effectLableForSCA,
				effectLableForSCACon);*/

		effectVariableTextField = new JTextField();
		effectVariableTextField.setText("");
		//effectVariableTextField.setBackground(Color.white);
		effectVariableTextField.setEditable(false);
		effectVariableTextField.addFocusListener(DDViewLayer
				.createViewLayerInstance());
		effectVariableTextField.setColumns(12);
		effectVariableTextField.setVisible(true);
		
		effectVariableTextField.setBorder(BorderFactory.createTitledBorder("Effect"));
		GridBagConstraints effectVariableTextFieldForSCACon = new GridBagConstraints();
		effectVariableTextFieldForSCACon.gridx = 0;
		effectVariableTextFieldForSCACon.gridy = 3;
		effectVariableTextFieldForSCACon.fill = GridBagConstraints.HORIZONTAL;
		effectVariableTextFieldForSCACon.weighty = 0.5;
		effectVariableTextFieldForSCACon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrameForSCA.getContentPane().add(effectVariableTextField,
				effectVariableTextFieldForSCACon);
	}

	private void rightArrowsInternalFrame() {

		rightArrowsinternalFrameForSCA = new JInternalFrame("right arrows",
				false, false, false, false);
		//rightArrowsinternalFrameForSCA.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(rightArrowsinternalFrameForSCA);
		rightArrowsinternalFrameForSCA.setLocation(midIF.getX()+midIF.getWidth(), midIF.getY());
		rightArrowsinternalFrameForSCA.setSize(leftArrowsIF
				.getWidth(), leftArrowsIF.getHeight());
		rightArrowsinternalFrameForSCA.setVisible(true);
		rightArrowsinternalFrameForSCA.setBorder(null);
		rightArrowsinternalFrameForSCA.setLayout(new GridBagLayout());

		moveToConcentrationButton = new JButton("", toRight);
		//moveToConcentrationButton.setPreferredSize(new Dimension(60, 15));
		moveToConcentrationButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		moveToConcentrationButton.setActionCommand(AVAILAANDCONC);
		moveToConcentrationButton.setBorder(null);
		moveToConcentrationButton.setBackground(null);
		GridBagConstraints moveToConcentrationButtonForSCACon = new GridBagConstraints();
		moveToConcentrationButtonForSCACon.gridx = 0;
		moveToConcentrationButtonForSCACon.gridy = 1;
		moveToConcentrationButtonForSCACon.weighty = 0.5;
		moveToConcentrationButtonForSCACon.anchor = GridBagConstraints.LINE_END;
		rightArrowsinternalFrameForSCA.getContentPane().add(
				moveToConcentrationButton, moveToConcentrationButtonForSCACon);

		moveToEffectButton = new JButton("", toRight);
		//moveToEffectButton.setPreferredSize(new Dimension(60, 15));
		moveToEffectButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		moveToEffectButton.setActionCommand(EFFECTANDAVAIL);
		GridBagConstraints moveToEffectButtonForSCACon = new GridBagConstraints();
		moveToEffectButtonForSCACon.gridx = 0;
		moveToEffectButtonForSCACon.gridy = 2;
		moveToEffectButtonForSCACon.weighty = 0.5;
		moveToEffectButton.setBorder(null);
		moveToEffectButton.setBackground(null);
		moveToEffectButtonForSCACon.anchor = GridBagConstraints.LINE_END;
		rightArrowsinternalFrameForSCA.getContentPane().add(moveToEffectButton,
				moveToEffectButtonForSCACon);

	}

	private void leftIFCreation() {
		
		leftIF = new JInternalFrame("middle", false, false,
				false, false);
		//middleInternalFrameForSCA.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(leftIF);
		leftIF.setLocation(0,0);
		leftIF.setSize(mappingInternalFrame.getWidth() / 5,
				mappingInternalFrame.getHeight());
		leftIF.setVisible(true);
		leftIF.setLayout(new GridBagLayout());
		leftIF.moveToFront();
		leftIF.setBorder(null);

		availableVariablesListmodel = new DefaultListModel();
		availableColumnsList = new JList(availableVariablesListmodel);
		fillAvailableColumnsList();
		availableColumnsList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		availableColumnsList.setVisible(true);
		availableColumnsList.setVisibleRowCount(5);
		
		JScrollPane columnsAvailableListScrollPaneForSCA = new JScrollPane(
				availableColumnsList);
		columnsAvailableListScrollPaneForSCA.setBorder(BorderFactory.createTitledBorder("Available"));	
		columnsAvailableListScrollPaneForSCA.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		columnsAvailableListScrollPaneForSCA.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		columnsAvailableListScrollPaneForSCA.setPreferredSize(new Dimension(
				100, 200));
		GridBagConstraints columnsAvailableListScrollPaneForSCACon = new GridBagConstraints();
		columnsAvailableListScrollPaneForSCACon.gridx = 0;
		columnsAvailableListScrollPaneForSCACon.gridy = 1;
		columnsAvailableListScrollPaneForSCACon.gridheight = 3;

		leftIF.getContentPane().add(
				columnsAvailableListScrollPaneForSCA,
				columnsAvailableListScrollPaneForSCACon);

	}

	private void fillAvailableColumnsList() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().setSize(0);
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().size(); i++) {
			availableVariablesListmodel.add(i, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName());

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getScaInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
							i,
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getColumnPropertiesArrayList().get(i)
									.getColumnName());

			

		}

	}

	private void leftArrowsInternalFrame() {
		leftArrowsIF = new JInternalFrame("left arrows",
				false, false, false, false);
		//leftarrowsInternalFrameForSCA.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(leftArrowsIF);
		leftArrowsIF.setLocation((leftIF.getX()+leftIF.getWidth()),leftIF.getY());
		leftArrowsIF.setSize(leftIF.getWidth(),leftIF.getHeight());
		leftArrowsIF.setVisible(true);
		leftArrowsIF.setLayout(new GridBagLayout());
		leftArrowsIF.setBorder(null);

		SortAndAvailableButton = new JButton("", toRight);
		SortAndAvailableButton.setToolTipText("Move to Sort List");
		SortAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		SortAndAvailableButton.setBorder(null);
		SortAndAvailableButton.setBackground(null);
		SortAndAvailableButton.setActionCommand(SORTANDAVAIL);
		//SortAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		SortAndAvailableButton.setVisible(true);
		GridBagConstraints moveToSortListButtonForSCACon = new GridBagConstraints();
		moveToSortListButtonForSCACon.gridx = 0;
		moveToSortListButtonForSCACon.gridy = 1;

		moveToSortListButtonForSCACon.weighty = 0.5;

		leftArrowsIF.getContentPane().add(
				SortAndAvailableButton, moveToSortListButtonForSCACon);

		timeAndAvailableButton = new JButton("", toRight);
		timeAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		timeAndAvailableButton.setActionCommand(TIMEANDAVAIL);
		timeAndAvailableButton.setBackground(null);
		timeAndAvailableButton.setBorder(null);
		//timeAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		GridBagConstraints setAsTimeVariableForSCACon = new GridBagConstraints();
		setAsTimeVariableForSCACon.gridx = 0;
		setAsTimeVariableForSCACon.gridy = 2;
		setAsTimeVariableForSCACon.weighty = 0.5;
		setAsTimeVariableForSCACon.anchor = GridBagConstraints.LINE_START;
		leftArrowsIF.getContentPane().add(
				timeAndAvailableButton, setAsTimeVariableForSCACon);

	}

	private void midIFCreation() {
		midIF = new JInternalFrame("left", false, false,
				false, false);
		//leftInternalFrameForSCA.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(midIF);
		midIF.setLocation(leftArrowsIF
				.getX()
				+ leftArrowsIF.getWidth(),
				leftArrowsIF.getY());
		midIF.setSize(mappingInternalFrame.getWidth() / 5,
				mappingInternalFrame.getHeight());
		midIF.setVisible(true);
		midIF.setLayout(new GridBagLayout());
		midIF.moveToFront();
		midIF.setBorder(null);

		sortVariableListModel = new DefaultListModel();
		sortVariablesList = new JList(sortVariableListModel);
		sortVariablesList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		sortVariablesList.setVisibleRowCount(3);
		sortVariablesList.setVisible(true);
		JScrollPane sortVarialbeListScrollPaneForSCA = new JScrollPane(
				sortVariablesList);
		sortVarialbeListScrollPaneForSCA.setBorder(BorderFactory.createTitledBorder("Sort Variables"));
		sortVarialbeListScrollPaneForSCA.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sortVarialbeListScrollPaneForSCA.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sortVarialbeListScrollPaneForSCA.setPreferredSize(new Dimension(100,
				150));
		GridBagConstraints sortVariableLIstScrollPaneForSCACon = new GridBagConstraints();

		sortVariableLIstScrollPaneForSCACon.gridx = 0;
		sortVariableLIstScrollPaneForSCACon.gridy = 1;
		sortVariableLIstScrollPaneForSCACon.weightx = 0.5;

		sortVariableLIstScrollPaneForSCACon.anchor = GridBagConstraints.LINE_START;

		midIF.getContentPane().add(
				sortVarialbeListScrollPaneForSCA,
				sortVariableLIstScrollPaneForSCACon);

		/*JLabel timeVariableLableForSCA = new JLabel("Time");
		GridBagConstraints timeVariableLableForSCACon = new GridBagConstraints();
		timeVariableLableForSCACon.gridx = 0;
		timeVariableLableForSCACon.gridy = 2;

		timeVariableLableForSCACon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrameForSCA.getContentPane().add(timeVariableLableForSCA,
				timeVariableLableForSCACon);*/

		timeVariableTextField = new JTextField();
		timeVariableTextField.setBorder(BorderFactory.createTitledBorder("Time"));
		timeVariableTextField.setText("");
		//timeVariableTextField.setBackground(Color.white);
		timeVariableTextField.setEditable(false);
		timeVariableTextField.addFocusListener(DDViewLayer
				.createViewLayerInstance());
		timeVariableTextField.setColumns(12);
		timeVariableTextField.setVisible(true);
		GridBagConstraints timeVariableTextFieldForSCACon = new GridBagConstraints();
		timeVariableTextFieldForSCACon.gridx = 0;
		timeVariableTextFieldForSCACon.gridy = 3;

		timeVariableTextFieldForSCACon.weighty = 0.5;
		timeVariableTextFieldForSCACon.anchor = GridBagConstraints.LINE_START;
		midIF.getContentPane().add(timeVariableTextField,
				timeVariableTextFieldForSCACon);

	}

	private void createMappingFrame() {
		mappingInternalFrame = new JInternalFrame("Mapping ", false, false,
				false, false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(mappingInternalFrame);
		mappingInternalFrame.setBackground(Color.white);
		mappingInternalFrame
				.setLocation(
						ScaSetupAvailComp.createSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ ScaSetupAvailComp.createSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						ScaSetupAvailComp.createSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		mappingInternalFrame
				.setSize(
						SCAMainPage.createScainstance().scaMainInternalFrame
								.getWidth()
								- ScaSetupAvailComp.createSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						ScaSetupAvailComp.createSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getHeight());
		mappingInternalFrame.setVisible(true);
		mappinginternalFrameDesktopPane = new JDesktopPane();
		mappingInternalFrame.setContentPane(mappinginternalFrameDesktopPane);
		GridBagLayout gridBagLayout = new GridBagLayout();

		mappingInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		ScaTabs.createScaTabsInst().setupTabDesktopPane
				.add(mappingInternalFrame);

	}

}
