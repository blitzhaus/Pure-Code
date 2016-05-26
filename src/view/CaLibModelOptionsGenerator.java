package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CaLibModelOptionsGenerator {
	PkButtonHandler pkButtonHandlerInst;
	PdButtonHandler pdButtonHandlerInst;
	MmButtonHandler mmButtonHandlerInst;
	PkPdLinkButtonHandler pkpdLinkButtonHandlerInst;
	IrmButtonHandler irmButtonHandlerInst;
	
	InVitroButtonHandler inVitroButtonHandlerInst; 

	public void providingOptionsForAllCAModels() {
		final JFrame modelOptionsJFrame = new JFrame();

		modelOptionsJFrame.setSize(400, 500);
		modelOptionsJFrame.setLocationRelativeTo(null);

		JPanel parentPanel = new JPanel();

		JPanel modelOptionsPanel = new JPanel();

		modelOptionsPanel.setLayout(new GridLayout(15, 1));

		Border titBorderAvailVar = BorderFactory
				.createTitledBorder("Available Models");
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		titBorderAvailVar = BorderFactory.createCompoundBorder(
				titBorderAvailVar, raisedbevel);
		parentPanel.setBorder(titBorderAvailVar);

		JLabel dummyLabel1 = new JLabel("");
		dummyLabel1.setVisible(false);
		modelOptionsPanel.add(dummyLabel1);

		JRadioButton pkModelRb = new JRadioButton("Pharmacokinetic Model");
		pkModelRb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				appInfo
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
						.setAnalysisType("pk");

			}
		});
		modelOptionsPanel.add(pkModelRb);

		JLabel dummyLabel2 = new JLabel("");
		dummyLabel2.setVisible(false);
		modelOptionsPanel.add(dummyLabel2);

		JRadioButton pdModelRb = new JRadioButton("Pharmacodynamic Model");
		pdModelRb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				appInfo
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
						.setAnalysisType("pd");

			}
		});
		modelOptionsPanel.add(pdModelRb);

		JLabel dummyLabel3 = new JLabel("");
		dummyLabel3.setVisible(false);
		modelOptionsPanel.add(dummyLabel3);

		JRadioButton mmModelRb = new JRadioButton("Michaelis-Menten Model");
		mmModelRb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				appInfo
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
						.setAnalysisType("mm");

			}
		});
		modelOptionsPanel.add(mmModelRb);

		JLabel dummyLabel4 = new JLabel("");
		dummyLabel4.setVisible(false);
		modelOptionsPanel.add(dummyLabel4);

		JRadioButton pkpdLinkModelRb = new JRadioButton("PK/PD link Model");
		pkpdLinkModelRb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				appInfo
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
						.setAnalysisType("pkpdlink");

			}
		});
		modelOptionsPanel.add(pkpdLinkModelRb);

		JLabel dummyLabel5 = new JLabel("");
		dummyLabel5.setVisible(false);
		modelOptionsPanel.add(dummyLabel5);

		JRadioButton irmModelRb = new JRadioButton("Indirect response Model");
		irmModelRb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				appInfo
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
						.setAnalysisType("irm");

			}
		});
		modelOptionsPanel.add(irmModelRb);
		JLabel dummyLabel7 = new JLabel("");
		dummyLabel7.setVisible(false);
		modelOptionsPanel.add(dummyLabel7);

		JRadioButton asciiModelRb = new JRadioButton("Ascii Model");
		asciiModelRb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				appInfo
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
						.setAnalysisType("ascii");

			}
		});
		
		modelOptionsPanel.add(asciiModelRb);

		JLabel dummyLabel6 = new JLabel("");
		dummyLabel6.setVisible(false);
		modelOptionsPanel.add(dummyLabel6);

		ButtonGroup bg = new ButtonGroup();
		bg.add(pkModelRb);
		bg.add(pdModelRb);
		bg.add(mmModelRb);
		bg.add(pkpdLinkModelRb);
		bg.add(irmModelRb);
		bg.add(asciiModelRb);

		JPanel continueButtonpanel = new JPanel();

		JButton continueButton = new JButton("Continue");
		continueButton.setBounds(0, 450, 20, 10);
		continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				modelOptionsJFrame.setVisible(false);
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();

				try {
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
									.get(
											appInfo
													.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getAnalysisType();

					if (analysisType.equals("pk")) {
						pkButtonHandlerInst = new PkButtonHandler();
						
						//inVitroButtonHandlerInst = new InVitroButtonHandler();
					} else if (analysisType.equals("pd")) {
						pdButtonHandlerInst = new PdButtonHandler();
					} else if (analysisType.equals("mm")) {
						mmButtonHandlerInst = new MmButtonHandler();
					} else if (analysisType.equals("pkpdlink")) {
						pkpdLinkButtonHandlerInst = new PkPdLinkButtonHandler();
					} else if (analysisType.equals("irm")) {
						irmButtonHandlerInst = new IrmButtonHandler();
					}else if (analysisType.equals("ascii")) {
						new AsciiButtonHandler();
					}

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
		});

		continueButtonpanel.add(continueButton);
		parentPanel.setLayout(new GridLayout(2, 1));

		parentPanel.add(modelOptionsPanel);
		parentPanel.add(continueButtonpanel);

		JLabel dummyLabel8 = new JLabel("");
		dummyLabel8.setVisible(false);
		modelOptionsPanel.add(dummyLabel8);

		modelOptionsJFrame.getContentPane().add(parentPanel);
		modelOptionsJFrame.setVisible(true);

	}

}
