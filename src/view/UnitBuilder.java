package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import view.ApplicationInfo;
import view.DisplayContents;

import Common.EventCodes;
import Common.JinternalFrameFunctions;
import Common.LogEntry;



public class UnitBuilder extends JFrame implements EventCodes{


	
	JDesktopPane unitBuilderFrameDesktopPane;
	JInternalFrame leftInternalFrame;
	JInternalFrame rightInternalFrame;
	JInternalFrame TimeinternalFrame;
	JInternalFrame MassInternalFrame;
	JInternalFrame VolumeinternalFrame;
	JInternalFrame NavigationinternalFrame;
	JButton okbutton;
	JButton cancelButton;
	JButton timeUnitAddButton;
	JButton massmeasureAddButton;
	JButton volumeunitAddButton;
	JComboBox massPrefixCombo;
	JComboBox massUnitCombo;
	JComboBox volumePrefixCombo;
	JComboBox volumeUnitCombo;
	JTextField newUnitsText;
	JTextField molecularWeightText;
	JButton multiplicationoperatorButton;
	JButton exponentialOperatorButton;
	JButton divisionOperatorButton;
	JComboBox timeunitsCombo;
	JButton clearUnitsButton;
	Font f = new Font("Arial", Font.BOLD, 10);
	String[] timeUnits = {"","hr","h","hour","min","m","sec","s","seconds","day"};
	String[] prefixes = {"","a","f","p","n","u","m","c","d","dk","k"};
	String[] massunits = {"","g","mol","lb","IU"};
	String[] volumeUnits = {"L"};
	GridBagLayout gridBagLayout = new GridBagLayout();
	protected TableColumnModel TableColumnModel;
	private Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	private Font componentLablesFont = new Font("Arial", Font.BOLD, 11);
    private TitledBorder titledBorder;
    final Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    JInternalFrame unitsFormatIF;
	

	public static UnitBuilder unitBuilderFrame = null;
	public static UnitBuilder createUBInstance(){
		if(unitBuilderFrame == null){
			unitBuilderFrame = new UnitBuilder();
		}
		return unitBuilderFrame;
	}
	
	public UnitBuilder() {

		createUnitBuilderFrame();

	}

	public void createUnitBuilderFrame(){
		new JFrame("Unit Builder");
		setFont(componentOptionseFont);
		setTitle("Unit Builder");
		int width = (int)(DDViewLayer.createViewLayerInstance().screenSize.getWidth()/3);
		int height = (int)(DDViewLayer.createViewLayerInstance().screenSize.getHeight()/2.5);
		setSize(width,height);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	//unitBuilderFrame.setBackground(Color.white);
		unitBuilderFrameDesktopPane = new JDesktopPane();
		getContentPane().add(unitBuilderFrameDesktopPane);
		
		createleftInternalFrame();
		createTimeinternalFrame();
		
		createUnitsFormatIF();
		createMassinternalFrame();
		
		createVolumeinternalFrame();
		//createOperatorsIF();
		
		createNavigationFrame();
		unitBuilderFrameDesktopPane.setBackground(NavigationinternalFrame.getBackground());
	}

	private void createOperatorsIF() {
		JInternalFrame operatorsIF = new JInternalFrame("", false,false,false,false);
		operatorsIF.setLocation(unitsFormatIF.getX() + unitsFormatIF.getWidth(), unitsFormatIF.getY());
		operatorsIF.setSize(unitsFormatIF.getWidth(), unitsFormatIF.getHeight());
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(operatorsIF);
		operatorsIF.setBorder(BorderFactory.createRaisedBevelBorder());
		operatorsIF.moveToFront();
		operatorsIF.setVisible(true);
		operatorsIF.setLayout(new GridBagLayout());
		unitBuilderFrameDesktopPane.add(operatorsIF);
		/*JPanel addOperatorPanel = new JPanel();
		titledBorder = BorderFactory
				.createTitledBorder(null, "Add Operator",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		addOperatorPanel.setBorder(titledBorder);
		GridBagConstraints addOperatorPanelCon = new GridBagConstraints();
		addOperatorPanelCon.gridx = 0;
		addOperatorPanelCon.gridy = 4;
		addOperatorPanelCon.weighty = 0.5;
		
		addOperatorPanelCon.gridheight = 2;
*/
		multiplicationoperatorButton = new JButton("*");
		multiplicationoperatorButton.setFont(componentOptionseFont);
		multiplicationoperatorButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		multiplicationoperatorButton.setActionCommand(UBMULOPERATOR);
		multiplicationoperatorButton.setPreferredSize(new Dimension(40, 20));
		GridBagConstraints multiplicationOperatorButtonCon = new GridBagConstraints();
		multiplicationOperatorButtonCon.gridx = 0;
		multiplicationOperatorButtonCon.gridy = 5;
		operatorsIF.getContentPane().add(multiplicationoperatorButton,
				multiplicationOperatorButtonCon);

		exponentialOperatorButton = new JButton("^");
		exponentialOperatorButton.setFont(componentOptionseFont);
		exponentialOperatorButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		exponentialOperatorButton.setActionCommand(UBEXPOPERATOR);
		exponentialOperatorButton.setPreferredSize(new Dimension(40, 20));
		GridBagConstraints exponentialOperatorButtonCon = new GridBagConstraints();
		exponentialOperatorButtonCon.gridx = 1;
		exponentialOperatorButtonCon.gridy = 5;
		operatorsIF.getContentPane().add(exponentialOperatorButton,
				exponentialOperatorButtonCon);

		divisionOperatorButton = new JButton("/");
		divisionOperatorButton.setFont(componentOptionseFont);
		divisionOperatorButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		divisionOperatorButton.setActionCommand(UBDIVOPERATOR);
		divisionOperatorButton.setPreferredSize(new Dimension(40, 20));
		GridBagConstraints divisionOpoeratorButtonCon = new GridBagConstraints();
		divisionOpoeratorButtonCon.gridx = 2;
		divisionOpoeratorButtonCon.gridy = 5;
		operatorsIF.getContentPane()
				.add(divisionOperatorButton, divisionOpoeratorButtonCon);

		
	}

	private void createUnitsFormatIF() {
		unitsFormatIF = new JInternalFrame("",false,false,false,false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(unitsFormatIF);
		unitsFormatIF.setLocation(leftInternalFrame.getX() + leftInternalFrame.getWidth(), leftInternalFrame.getY());
		unitsFormatIF.setSize(leftInternalFrame.getWidth(), leftInternalFrame.getHeight());
		unitsFormatIF.setVisible(true);
		unitBuilderFrameDesktopPane.add(unitsFormatIF);
		unitsFormatIF.setLayout(new GridBagLayout());
		unitsFormatIF.moveToFront();
		unitsFormatIF.setBorder(BorderFactory.createRaisedBevelBorder());
		
		ButtonGroup specifyOrConvertUnits = new ButtonGroup();
		JRadioButton specifyUnitsRadioButton = new JRadioButton("Specify Units");
		specifyUnitsRadioButton.setFont(componentOptionseFont);
		specifyUnitsRadioButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		specifyUnitsRadioButton.doClick();
		specifyOrConvertUnits.add(specifyUnitsRadioButton);
		GridBagConstraints specifyUnitsRadioButonCon = new GridBagConstraints();
		specifyUnitsRadioButonCon.gridx = 0;
		specifyUnitsRadioButonCon.gridy = 0;
		/*specifyUnitsRadioButonCon.weighty = 0.5;*/
		specifyUnitsRadioButonCon.anchor = GridBagConstraints.LINE_START;
		unitsFormatIF.getContentPane().add(specifyUnitsRadioButton,
				specifyUnitsRadioButonCon);

		JRadioButton convertUnitsRadioButton = new JRadioButton("Convert Units");
		convertUnitsRadioButton.setFont(componentOptionseFont);
		specifyOrConvertUnits.add(convertUnitsRadioButton);
		GridBagConstraints convertUnitsRadioButtonCon = new GridBagConstraints();
		convertUnitsRadioButtonCon.gridx = 0;
		convertUnitsRadioButtonCon.gridy = 1;
		convertUnitsRadioButtonCon.anchor = GridBagConstraints.LINE_START;
		unitsFormatIF.getContentPane().add(convertUnitsRadioButton,
				convertUnitsRadioButtonCon);

		clearUnitsButton = new JButton("Clear Units");
		clearUnitsButton.setFont(componentOptionseFont);
		clearUnitsButton.addActionListener(DDViewLayer.createViewLayerInstance());
		clearUnitsButton.setActionCommand(UBCLEAR);
		GridBagConstraints clearUnitsButtonCon = new GridBagConstraints();
		
		clearUnitsButton.setPreferredSize(new Dimension(100, 20));

		clearUnitsButton.setFont(f);
		clearUnitsButtonCon.gridx = 0;
		clearUnitsButtonCon.gridy = 2;
		clearUnitsButtonCon.fill = GridBagConstraints.HORIZONTAL;
		clearUnitsButtonCon.anchor = GridBagConstraints.LINE_START;
		unitsFormatIF.getContentPane().add(clearUnitsButton,
				clearUnitsButtonCon);
		
		
		
	}

	private void createNavigationFrame() {
		
		NavigationinternalFrame = new JInternalFrame("navigation frame", false,
				false, false, false);
		NavigationinternalFrame.setLocation(MassInternalFrame.getX() + MassInternalFrame.getWidth()/2, MassInternalFrame.getY() + MassInternalFrame.getHeight());
		NavigationinternalFrame.setSize(VolumeinternalFrame.getWidth(), getHeight()- (leftInternalFrame.getHeight()+ VolumeinternalFrame.getHeight()+MassInternalFrame.getHeight()));
		NavigationinternalFrame.setVisible(true);
		NavigationinternalFrame.setBorder(null);
		NavigationinternalFrame.setLayout(gridBagLayout);
		removeTitleBarForinternalFrame(NavigationinternalFrame);
		unitBuilderFrameDesktopPane.add(NavigationinternalFrame);

		NavigationinternalFrame.setFont(componentOptionseFont);
		okbutton = new JButton("Ok");
		okbutton.setFont(componentOptionseFont);
		okbutton.addActionListener(DDViewLayer.createViewLayerInstance());
		okbutton.setActionCommand(UBOK);
		// okbutton.setPreferredSize(new Dimension(50,20));
		GridBagConstraints okButtonCon = new GridBagConstraints();
		okButtonCon.gridx = 0;
		okButtonCon.gridy = 0;
		//okButtonCon.weightx = 0.5;
		okButtonCon.anchor = GridBagConstraints.LINE_END;
		NavigationinternalFrame.getContentPane().add(okbutton, okButtonCon);

		cancelButton = new JButton("Cancel");
		cancelButton.setFont(componentOptionseFont);
		cancelButton.addActionListener(DDViewLayer.createViewLayerInstance());
		cancelButton.setActionCommand(UBCANCEL);
		GridBagConstraints cancelButtonCon = new GridBagConstraints();
		cancelButtonCon.gridx = 1;
		cancelButtonCon.gridy = 0;
		//cancelButtonCon.weightx = 0.5;
		cancelButtonCon.anchor = GridBagConstraints.LINE_END;
		NavigationinternalFrame.getContentPane().add(cancelButton,
				cancelButtonCon);

	}

	private void createVolumeinternalFrame() {
		
		VolumeinternalFrame = new JInternalFrame("volome", false, false, false,
				false);
		VolumeinternalFrame.setLocation(unitsFormatIF.getX(), unitsFormatIF.getY() + unitsFormatIF.getHeight());
		VolumeinternalFrame.setSize(MassInternalFrame.getWidth()/2,
				(int)(getHeight() / 3.4));
		VolumeinternalFrame.setVisible(true);
		VolumeinternalFrame.setLayout(gridBagLayout);
		VolumeinternalFrame.setBorder(BorderFactory.createRaisedBevelBorder());
		removeTitleBarForinternalFrame(VolumeinternalFrame);
		unitBuilderFrameDesktopPane.add(VolumeinternalFrame);

		JLabel volumePrefixlable = new JLabel("Volume Prefix");
		volumePrefixlable.setFont(componentLablesFont);
		GridBagConstraints volumeprefixLableCon = new GridBagConstraints();
		volumeprefixLableCon.gridx = 0;
		volumeprefixLableCon.gridy = 0;
		volumeprefixLableCon.weightx = 0.5;
		volumeprefixLableCon.anchor = GridBagConstraints.LINE_START;
		VolumeinternalFrame.getContentPane().add(volumePrefixlable,
				volumeprefixLableCon);

		volumePrefixCombo = new JComboBox();
		volumePrefixCombo.setFont(componentOptionseFont);
		volumePrefixCombo.addItem("");
		volumePrefixCombo.addItem("atto(a)");
		volumePrefixCombo.addItem("femto(f)");
		volumePrefixCombo.addItem("pico(p)");
		volumePrefixCombo.addItem("nano(n)");
		volumePrefixCombo.addItem("micro(u)");
		volumePrefixCombo.addItem("milli(m)");
		volumePrefixCombo.addItem("centi(c)");
		volumePrefixCombo.addItem("deci(d)");
		volumePrefixCombo.addItem("deca(dk)");
		volumePrefixCombo.addItem("kilo(k)");
		volumePrefixCombo.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints volumePrefixComboCon = new GridBagConstraints();
		volumePrefixComboCon.gridx = 1;
		volumePrefixComboCon.gridy = 0;
		volumePrefixComboCon.weightx = 0.5;
		volumePrefixComboCon.anchor = GridBagConstraints.LINE_START;
		VolumeinternalFrame.getContentPane().add(volumePrefixCombo,
				volumePrefixComboCon);

		JLabel volumeUnitLable = new JLabel("Volume Unit");
		volumeUnitLable.setFont(componentLablesFont);
		GridBagConstraints volumeunitLableCon = new GridBagConstraints();
		volumeunitLableCon.gridx = 1;
		volumeunitLableCon.gridy = 0;
		volumeunitLableCon.anchor = GridBagConstraints.LINE_START;
		VolumeinternalFrame.getContentPane().add(volumeUnitLable,volumeunitLableCon);

		volumeUnitCombo = new JComboBox();
		volumeUnitCombo.setFont(componentOptionseFont);
		volumeUnitCombo.addItem("Litre(L)");
		volumeUnitCombo.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints volumeUnitComboCon = new GridBagConstraints();
		volumeUnitComboCon.gridx = 1;
		volumeUnitComboCon.gridy = 1;
		volumeUnitComboCon.anchor = GridBagConstraints.LINE_START;
		VolumeinternalFrame.getContentPane().add(volumeUnitCombo,
				volumeUnitComboCon);

		volumeunitAddButton = new JButton("Add");
		volumeunitAddButton.setFont(componentOptionseFont);
		volumeunitAddButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		volumeunitAddButton.setActionCommand(UBVOLADD);
		volumeunitAddButton.setPreferredSize(new Dimension(60, 20));
		volumeunitAddButton.setFont(new Font("Arial", Font.BOLD, 10));

		GridBagConstraints volumeUnitAddButtonCon = new GridBagConstraints();
		volumeUnitAddButtonCon.gridx = 1;
		volumeUnitAddButtonCon.gridy = 2;
		volumeUnitAddButtonCon.anchor = GridBagConstraints.LINE_START;
		VolumeinternalFrame.getContentPane().add(volumeunitAddButton,
				volumeUnitAddButtonCon);
	}

	private void createMassinternalFrame() {
		// TODO Auto-generated method stub
		MassInternalFrame = new JInternalFrame("mass measueres", false, false,
				false, false);
		MassInternalFrame.setLocation(TimeinternalFrame.getX(), TimeinternalFrame.getY() + TimeinternalFrame.getHeight());
		MassInternalFrame
				.setSize(TimeinternalFrame.getWidth() * 2, (int)(getHeight() /4.8));
		MassInternalFrame.setVisible(true);
		MassInternalFrame.setLayout(gridBagLayout);
		removeTitleBarForinternalFrame(MassInternalFrame);
		MassInternalFrame.setBorder(BorderFactory.createRaisedBevelBorder());
		unitBuilderFrameDesktopPane.add(MassInternalFrame);

		JLabel massPrefixLable = new JLabel("Mass");
		massPrefixLable.setFont(componentLablesFont);
		GridBagConstraints massPrefixLableCon = new GridBagConstraints();
		massPrefixLableCon.gridx = 0;
		massPrefixLableCon.gridy = 0;
		massPrefixLableCon.weightx = 0.3;
		massPrefixLableCon.anchor = GridBagConstraints.LINE_START;
		MassInternalFrame.getContentPane().add(massPrefixLable,
				massPrefixLableCon);
		
		JLabel PrefixLable = new JLabel("Prefix");
		PrefixLable.setFont(componentLablesFont);
		GridBagConstraints PrefixLableCon = new GridBagConstraints();
		PrefixLableCon.gridx = 0;
		PrefixLableCon.gridy = 1;
		
		PrefixLableCon.anchor = GridBagConstraints.LINE_START;
		MassInternalFrame.getContentPane().add(PrefixLable,
				PrefixLableCon);
		
		
		

		massPrefixCombo = new JComboBox();
		massPrefixCombo.setFont(componentOptionseFont);
		massPrefixCombo.addItem("");
		massPrefixCombo.addItem("atto(a)");
		massPrefixCombo.addItem("femto(f)");
		massPrefixCombo.addItem("pico(p)");
		massPrefixCombo.addItem("nano(n)");
		massPrefixCombo.addItem("micro(u)");
		massPrefixCombo.addItem("milli(m)");
		massPrefixCombo.addItem("centi(c)");
		massPrefixCombo.addItem("deci(d)");
		massPrefixCombo.addItem("deca(dk)");
		massPrefixCombo.addItem("kilo(k)");
		massPrefixCombo.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints massPrefixComboCon = new GridBagConstraints();
		massPrefixComboCon.gridx = 1;
		massPrefixComboCon.gridy = 1;
		massPrefixComboCon.weightx = 1;
		massPrefixComboCon.anchor = GridBagConstraints.LINE_START;
		MassInternalFrame.getContentPane().add(massPrefixCombo,
				massPrefixComboCon);

		JLabel massUnitLable = new JLabel("Mass Unit");
		massUnitLable.setFont(componentLablesFont);
		GridBagConstraints massUnitlableCon = new GridBagConstraints();
		massUnitlableCon.gridx = 3;
		massUnitlableCon.gridy = 0;
		massUnitlableCon.weightx = 0.5;
		massUnitlableCon.anchor = GridBagConstraints.LINE_END;
		MassInternalFrame.getContentPane().add(massUnitLable, massUnitlableCon);

		massUnitCombo = new JComboBox();
		massUnitCombo.setFont(componentOptionseFont);
		massUnitCombo.addItem("");
		massUnitCombo.addItem("gram(g)");
		massUnitCombo.addItem("mol(mol)");
		massUnitCombo.addItem("pound(lb)");
		massUnitCombo.addItem("IU(IU)");
		massUnitCombo.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints massUnitComboCon = new GridBagConstraints();
		massUnitComboCon.gridx = 4;
		massUnitComboCon.gridy = 0;
		massUnitComboCon.weightx = 0.5;
		massUnitComboCon.anchor = GridBagConstraints.LINE_END;
		MassInternalFrame.getContentPane().add(massUnitCombo, massUnitComboCon);

		massmeasureAddButton = new JButton("Add");
		massmeasureAddButton.setFont(componentOptionseFont);
		massmeasureAddButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		massmeasureAddButton.setActionCommand(UBMASSADD);
		massmeasureAddButton.setPreferredSize(new Dimension(60, 20));
		massmeasureAddButton.setFont(new Font("Arial", Font.BOLD, 10));
		GridBagConstraints massMeasureAddButtonCon = new GridBagConstraints();
		massMeasureAddButtonCon.gridx = 4;
		massMeasureAddButtonCon.gridy = 1;
		massMeasureAddButtonCon.anchor = GridBagConstraints.LINE_END;
		MassInternalFrame.getContentPane().add(massmeasureAddButton,
				massMeasureAddButtonCon);

	}

	private void createTimeinternalFrame() {
		
		TimeinternalFrame = new JInternalFrame("time measures", false, false,
				false, false);
		TimeinternalFrame.setLocation(leftInternalFrame.getX(),
				leftInternalFrame.getY() + leftInternalFrame.getHeight());
		TimeinternalFrame.setSize(getWidth() / 2, (int)(getHeight() /3.4));
		TimeinternalFrame.setVisible(true);
		TimeinternalFrame.setLayout(gridBagLayout);
		TimeinternalFrame.setBorder(BorderFactory.createRaisedBevelBorder());
		unitBuilderFrameDesktopPane.add(TimeinternalFrame);
		removeTitleBarForinternalFrame(TimeinternalFrame);

		/*JPanel unitMeasurespanel = new JPanel();
		titledBorder = BorderFactory
				.createTitledBorder(null, "Time Measure",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		unitMeasurespanel.setBorder(titledBorder);
		GridBagConstraints unitMeasuresPanelCon = new GridBagConstraints();
		unitMeasuresPanelCon.gridx = 0;
		unitMeasuresPanelCon.gridy = 0;
		
		unitMeasuresPanelCon.fill = GridBagConstraints.HORIZONTAL;*/

		JLabel timeLable = new JLabel("Time");
		timeLable.setFont(componentLablesFont);
		GridBagConstraints timeLableCon = new GridBagConstraints();
		timeLableCon.gridx = 0;
		timeLableCon.gridy = 0;
		timeLableCon.weightx = 0.5;
		timeLableCon.anchor = GridBagConstraints.LINE_START;
		TimeinternalFrame.getContentPane().add(timeLable,timeLableCon);
		
		timeunitsCombo = new JComboBox();
		timeunitsCombo.setFont(componentOptionseFont);
		timeunitsCombo.addItem("");
		timeunitsCombo.addItem("hour(hr)");
		timeunitsCombo.addItem("hour(h)");
		timeunitsCombo.addItem("hour(hour)");
		timeunitsCombo.addItem("minute(min)");
		timeunitsCombo.addItem("minutes(m)");
		timeunitsCombo.addItem("seconds(sec)");
		timeunitsCombo.addItem("seconds(s)");
		timeunitsCombo.addItem("seconds(seconds)");
		timeunitsCombo.addItem("day(day)");
		timeunitsCombo.setPreferredSize(new Dimension(100, 20));
		

		GridBagConstraints timeUnitsComboCon = new GridBagConstraints();
		timeUnitsComboCon.gridx = 1;
		timeUnitsComboCon.gridy = 0;
		timeUnitsComboCon.weightx = 0.9;
		timeUnitsComboCon.anchor = GridBagConstraints.LINE_START;
		TimeinternalFrame.getContentPane().add(timeunitsCombo, timeUnitsComboCon);

		timeUnitAddButton = new JButton("Add");
		timeUnitAddButton.setFont(componentOptionseFont);
		timeUnitAddButton
				.addActionListener(DDViewLayer.createViewLayerInstance());
		timeUnitAddButton.setActionCommand(UBTIMEADD);
		timeUnitAddButton.setFont(new Font("Arial", Font.BOLD, 10));
		timeUnitAddButton.setPreferredSize(new Dimension(60, 20));
		GridBagConstraints timeUnitAddButonCon = new GridBagConstraints();
		timeUnitAddButonCon.gridx = 1;
		timeUnitAddButonCon.gridy = 1;
		timeUnitAddButonCon.anchor = GridBagConstraints.LINE_END;
		TimeinternalFrame.getContentPane().add(timeUnitAddButton, timeUnitAddButonCon);

/*		TimeinternalFrame.getContentPane().add(unitMeasurespanel,
				unitMeasuresPanelCon);*/
	}

	private void createleftInternalFrame() {
		
		leftInternalFrame = new JInternalFrame("left Frame", false, false,
				false, false);
		leftInternalFrame.setLocation(0,0);
		leftInternalFrame.setSize(getWidth() / 2, (int)(getHeight()/4));
		leftInternalFrame.setVisible(true);
		leftInternalFrame.setBorder(BorderFactory.createRaisedBevelBorder());
		removeTitleBarForinternalFrame(leftInternalFrame);
		GridBagLayout gridBagLayout = new GridBagLayout();
		leftInternalFrame.getContentPane().setLayout(gridBagLayout);
		unitBuilderFrameDesktopPane.add(leftInternalFrame);

		JLabel newUnitsLable = new JLabel("New units");
		newUnitsLable.setFont(componentLablesFont);
		GridBagConstraints newUnitsCon = new GridBagConstraints();
		
		newUnitsCon.gridx = 0;
		newUnitsCon.gridy = 1;
		newUnitsCon.weightx = 0.1;
		newUnitsCon.anchor = GridBagConstraints.LINE_START;
		newUnitsLable.setVisible(true);
		leftInternalFrame.getContentPane().add(newUnitsLable, newUnitsCon);

		newUnitsText = new JTextField();
		newUnitsText.setFont(componentOptionseFont);
		GridBagConstraints newUnitsTextCon = new GridBagConstraints();
		newUnitsTextCon.gridx = 1;
		newUnitsTextCon.gridy = 1;
		newUnitsTextCon.weightx = 0.5;
		newUnitsTextCon.anchor = GridBagConstraints.LINE_START;
		newUnitsText.setVisible(true);
		newUnitsText.setColumns(5);
		leftInternalFrame.getContentPane().add(newUnitsText, newUnitsTextCon);

		multiplicationoperatorButton = new JButton("*");
		multiplicationoperatorButton.setFont(componentOptionseFont);
		multiplicationoperatorButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		multiplicationoperatorButton.setActionCommand(UBMULOPERATOR);
		multiplicationoperatorButton.setPreferredSize(new Dimension(40, 20));
		GridBagConstraints multiplicationOperatorButtonCon = new GridBagConstraints();
		multiplicationOperatorButtonCon.gridx = 2;
		multiplicationOperatorButtonCon.gridy = 0;
		multiplicationOperatorButtonCon.weightx = 0.5;
		leftInternalFrame.getContentPane().add(multiplicationoperatorButton,
				multiplicationOperatorButtonCon);

		exponentialOperatorButton = new JButton("^");
		exponentialOperatorButton.setFont(componentOptionseFont);
		exponentialOperatorButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		exponentialOperatorButton.setActionCommand(UBEXPOPERATOR);
		exponentialOperatorButton.setPreferredSize(new Dimension(40, 20));
		GridBagConstraints exponentialOperatorButtonCon = new GridBagConstraints();
		exponentialOperatorButtonCon.gridx = 2;
		exponentialOperatorButtonCon.gridy = 1;
		leftInternalFrame.getContentPane().add(exponentialOperatorButton,
				exponentialOperatorButtonCon);

		divisionOperatorButton = new JButton("/");
		divisionOperatorButton.setFont(componentOptionseFont);
		divisionOperatorButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		divisionOperatorButton.setActionCommand(UBDIVOPERATOR);
		divisionOperatorButton.setPreferredSize(new Dimension(40, 20));
		GridBagConstraints divisionOpoeratorButtonCon = new GridBagConstraints();
		divisionOpoeratorButtonCon.gridx = 2;
		divisionOpoeratorButtonCon.gridy = 2;
		leftInternalFrame.getContentPane()
				.add(divisionOperatorButton, divisionOpoeratorButtonCon);
	}
	
	protected void removeTitleBarForinternalFrame(JInternalFrame j) {
		javax.swing.plaf.InternalFrameUI ifu = j.getUI();
		((javax.swing.plaf.basic.BasicInternalFrameUI) ifu).setNorthPane(null);
	}
	
}
