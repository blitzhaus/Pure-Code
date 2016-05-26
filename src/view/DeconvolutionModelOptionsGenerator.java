package view;

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

public class DeconvolutionModelOptionsGenerator {
	

	PolyExpoButtonHandler polyExpoButtonHandlerInst;
	WagNelsonButtonHandler wagNelsonButtonHandlerInst;
	LooRiegelButtonHandler looRiegelButtonHandlerInst;
	

	public void providingOptionsForAllCAModels() {
		final JFrame modelOptionsJFrame = new JFrame();

		modelOptionsJFrame.setSize(300, 300);
		modelOptionsJFrame.setLocationRelativeTo(null);

		JPanel parentPanel = new JPanel();

		JPanel modelOptionsPanel = new JPanel();

		modelOptionsPanel.setLayout(new GridLayout(8, 1));

		Border titBorderAvailVar = BorderFactory
				.createTitledBorder("Available Models");
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		titBorderAvailVar = BorderFactory.createCompoundBorder(
				titBorderAvailVar, raisedbevel);
		parentPanel.setBorder(titBorderAvailVar);

		JLabel dummyLabel1 = new JLabel("");
		dummyLabel1.setVisible(false);
		modelOptionsPanel.add(dummyLabel1);

		JRadioButton pkModelRb = new JRadioButton("PolyExponential Model");
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
						.setAnalysisType("polyexpo");

			}
		});
		modelOptionsPanel.add(pkModelRb);

		JLabel dummyLabel2 = new JLabel("");
		dummyLabel2.setVisible(false);
		modelOptionsPanel.add(dummyLabel2);

		JRadioButton pdModelRb = new JRadioButton("Wagner Nelson Model");
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
						.setAnalysisType("wagnelson");

			}
		});
		modelOptionsPanel.add(pdModelRb);

		JLabel dummyLabel3 = new JLabel("");
		dummyLabel3.setVisible(false);
		modelOptionsPanel.add(dummyLabel3);

		JRadioButton mmModelRb = new JRadioButton("Loo-Riegelman Model");
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
						.setAnalysisType("looriegel");

			}
		});
		modelOptionsPanel.add(mmModelRb);

		

		JLabel dummyLabel6 = new JLabel("");
		dummyLabel6.setVisible(false);
		modelOptionsPanel.add(dummyLabel6);

		ButtonGroup bg = new ButtonGroup();
		bg.add(pkModelRb);
		bg.add(pdModelRb);
		bg.add(mmModelRb);
		

		JPanel continueButtonpanel = new JPanel();

		JButton continueButton = new JButton("Continue");
		continueButton.setBounds(0, 450, 20, 10);
		continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				modelOptionsJFrame.setVisible(false);
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();

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

				if (analysisType.equals("polyexpo")) {
					try {
						polyExpoButtonHandlerInst = new PolyExpoButtonHandler();
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
					
					
				} else if (analysisType.equals("wagnelson")) {
					try {
						wagNelsonButtonHandlerInst = new WagNelsonButtonHandler();
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
				} else if (analysisType.equals("looriegel")) {
					try {
						looRiegelButtonHandlerInst = new LooRiegelButtonHandler();
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
			}
		});

		continueButtonpanel.add(continueButton);
		parentPanel.setLayout(new GridLayout(2, 1));

		parentPanel.add(modelOptionsPanel);
		parentPanel.add(continueButtonpanel);

	
		modelOptionsJFrame.getContentPane().add(parentPanel);
		modelOptionsJFrame.setVisible(true);

	}



}
