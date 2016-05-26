package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Common.JinternalFrameFunctions;

public class ScaOptions {

	private final class CompMethodComboActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getModelInputsObj()
					.setMethodNumberForSCA(
							computationMethodComboBox.getSelectedIndex());
		}
	}

	private final class Ke0TextFieldDocumentHandler implements DocumentListener {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppInfo();
		}

		private void updateAppInfo() {
			// TODO Auto-generated method stub
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getModelInputsObj().setKe0Value(
							Ke0TextField.getText());

		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppInfo();
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppInfo();
		}
	}

	public static ScaOptions SCA_OPTIONS = null;

	public static ScaOptions createScaOptInst() {
		if (SCA_OPTIONS == null) {
			SCA_OPTIONS = new ScaOptions("just object creation");
		}
		return SCA_OPTIONS;
	}

	JInternalFrame optionsInternalFrame;
	JTabbedPane moduleTabs;
	JDesktopPane optionsTabDesktopPane;
	JInternalFrame modelSelectionInternalFrame;
	JComboBox computationMethodComboBox;
	JLabel Ke0Label;
	JLabel viewLable;
	JTextField Ke0TextField;
	JComboBox viewsCombo;

	public ScaOptions(String dummy) {

	}

	void createOptionsInternalFrame() {
		// TODO Auto-generated method stub

		optionsInternalFrame = new JInternalFrame("options ", false, false,
				false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(optionsInternalFrame);
		optionsInternalFrame.setLocation(
				SCAMainPage.createScainstance().scaMainInternalFrame.getX(),
				SCAMainPage.createScainstance().scaMainInternalFrame.getY()
						+ SCAMainPage.createScainstance().scaMainInternalFrame
								.getHeight());

		optionsInternalFrame
				.setSize(SCAMainPage.createScainstance().scaMainInternalFrame
						.getWidth(),
						DDViewLayer.createMTInstance().mainTabbedFrame
								.getHeight() / 2);

		optionsInternalFrame.setVisible(true);
		optionsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane
				.add(optionsInternalFrame);
		moduleTabs = new JTabbedPane();
		optionsTabDesktopPane = new JDesktopPane();
		moduleTabs.addTab("Options", optionsTabDesktopPane);

		optionsInternalFrame.getContentPane().add(moduleTabs);
		// setFont(new Font("Serif", Font.PLAIN, 8));
		createModelSelectionInternalFrame();
		optionsInternalFrame.moveToFront();
	}

	private void createModelSelectionInternalFrame() {

		modelSelectionInternalFrame = new JInternalFrame("Model Selection",
				false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(modelSelectionInternalFrame);

		// need to modify
		modelSelectionInternalFrame.setLocation(0, 0);
		int width = (int) (optionsInternalFrame.getWidth() / 2.7);
		modelSelectionInternalFrame.setSize(optionsInternalFrame.getWidth(),
				optionsInternalFrame.getHeight()); // 50 is subtracted because
													// tabs are added and it is
													// to compensate the tabs
													// height

		modelSelectionInternalFrame.setVisible(true);
		modelSelectionInternalFrame.setLayout(new GridLayout(1, 1));
		modelSelectionInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);

		createInternalGuiModelSelection();
		optionsTabDesktopPane.add(modelSelectionInternalFrame);
		modelSelectionInternalFrame.moveToFront();

	}

	private void createInternalGuiModelSelection() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Font componentOptionseFont = new Font("Arial", Font.BOLD, 10);
		Font componentLablesFont = new Font("Arial", Font.BOLD, 10);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(null,
				"Options", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.CENTER, componentLablesFont, Color.black);
		JPanel modelTypePanel = new JPanel();
		modelTypePanel.setFont(componentOptionseFont);
		modelTypePanel.setLayout(new GridLayout(6, 2));
		modelTypePanel.setBorder(titledBorder);

		JLabel computationMethodLabel1 = new JLabel("");
		modelTypePanel.add(computationMethodLabel1);

		JLabel computationMethodLabel2 = new JLabel("");
		modelTypePanel.add(computationMethodLabel2);

		JLabel computationMethodLabel = new JLabel("Computation Method");
		computationMethodLabel.setVisible(true);

		modelTypePanel.add(computationMethodLabel);

		computationMethodComboBox = new JComboBox();
		computationMethodComboBox.addItem("Log-Linear");
		computationMethodComboBox.addItem("Linear");
		computationMethodComboBox.addItem("Log/Linear");
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getModelInputsObj()
				.setMethodNumberForSCA(0);

		computationMethodComboBox
				.addActionListener(new CompMethodComboActionHandler());
		modelTypePanel.add(computationMethodComboBox);

		JLabel computationMethodLabel3 = new JLabel("");
		modelTypePanel.add(computationMethodLabel3);

		JLabel computationMethodLabel4 = new JLabel("");
		modelTypePanel.add(computationMethodLabel4);
		JLabel computationMethodLabel5 = new JLabel("");
		modelTypePanel.add(computationMethodLabel5);

		JLabel computationMethodLabel6 = new JLabel("");
		modelTypePanel.add(computationMethodLabel6);

		Ke0Label = new JLabel("Ke0");
		modelTypePanel.add(Ke0Label);

		Ke0TextField = new JTextField(1);

		Ke0TextField.getDocument().addDocumentListener(
				new Ke0TextFieldDocumentHandler());

		modelTypePanel.add(Ke0TextField);

		JLabel computationMethodLabel7 = new JLabel("");
		modelTypePanel.add(computationMethodLabel7);

		JLabel computationMethodLabel8 = new JLabel("");
		modelTypePanel.add(computationMethodLabel8);

		modelSelectionInternalFrame.add(modelTypePanel);

		titledBorder = BorderFactory.createTitledBorder(null, "Plot Options",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.CENTER,
				componentLablesFont, Color.black);

		JPanel plotTypePanel = new JPanel();
		plotTypePanel.setFont(componentOptionseFont);
		plotTypePanel.setLayout(new GridLayout(5, 2));
		plotTypePanel.setBorder(titledBorder);

		JLabel computationMethodLabel9 = new JLabel("");
		plotTypePanel.add(computationMethodLabel9);

		JLabel computationMethodLabel10 = new JLabel("");
		plotTypePanel.add(computationMethodLabel10);

		JLabel computationMethodLabel11 = new JLabel("");
		plotTypePanel.add(computationMethodLabel11);

		JLabel computationMethodLabel12 = new JLabel("");
		plotTypePanel.add(computationMethodLabel12);

		viewLable = new JLabel(" Plot View");
		viewLable.setEnabled(false);
		plotTypePanel.add(viewLable);

		viewsCombo = new JComboBox();
		viewsCombo.setEnabled(false);
		viewsCombo.addActionListener(DDViewLayer.createViewLayerInstance());
		viewsCombo.addItem("Lin");
		viewsCombo.addItem("Log");
		plotTypePanel.add(viewsCombo);

		JLabel computationMethodLabel13 = new JLabel("");
		plotTypePanel.add(computationMethodLabel13);

		JLabel computationMethodLabel14 = new JLabel("");
		plotTypePanel.add(computationMethodLabel14);

		JLabel computationMethodLabel15 = new JLabel("");
		plotTypePanel.add(computationMethodLabel15);

		JLabel computationMethodLabel16 = new JLabel("");
		plotTypePanel.add(computationMethodLabel16);

		modelSelectionInternalFrame.add(plotTypePanel);

	}

}
