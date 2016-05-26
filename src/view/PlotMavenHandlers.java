package view;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;

import Common.JinternalFrameFunctions;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PlotMavenHandlers {

	public static PlotMavenHandlers PM_HANDLER = null;

	public static PlotMavenHandlers createPlotMavenHandlerInst() {
		if (PM_HANDLER == null) {
			PM_HANDLER = new PlotMavenHandlers();
		}
		return PM_HANDLER;
	}

	private Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	private Font componentLablesFont = new Font("Arial", Font.BOLD, 11);
	private TitledBorder titledBorder;
	int numberOftimesPlacedInXVariable = 0;
	int numberOFTimesPlacedInYVariable = 0;
	int numberOfTimesPlacedInUpVariable = 0;
	int numberOfTimesPlacedInDownVariable = 0;
	int numberOftimesPlacedInGroupVariable = 0;

	public PlotMavenHandlers() {

	}

	void availToDownButtonHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		numberOfTimesPlacedInDownVariable++;
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String moving = (String) PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsList
				.getSelectedValue();
		if (PMSetupDispComp.createPMSetupDisCompInst().isFromAvailableToDown == true) {

			int correspondingOriginalIndex = 0;
			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getColumnPropertiesArrayList().size(); i++) {
				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getColumnPropertiesArrayList().get(i).getColumnName()
						.equals(moving)) {
					correspondingOriginalIndex = i;
					break;
				}
			}

			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getColumnPropertiesArrayList().get(
							correspondingOriginalIndex).getDataType().equals(
							"Numeric")) {
				if (PMSetupDispComp.createPMSetupDisCompInst().downVariablesTextField
						.getText().equals("Down Variable")) {
					moving = (String) PMSetupDispComp
							.createPMSetupDisCompInst().plotMavenavailableColumnsList
							.getSelectedValue();
					PMSetupDispComp.createPMSetupDisCompInst().downVariablesTextField
							.setText(moving);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().setDownVariable(moving);
					PMSetupDispComp.createPMSetupDisCompInst().downVariable = moving;

				} else {
					moving = (String) PMSetupDispComp
							.createPMSetupDisCompInst().plotMavenavailableColumnsList
							.getSelectedValue();
					String previousUpVariable = PMSetupDispComp
							.createPMSetupDisCompInst().downVariablesTextField
							.getText();
					boolean alreadyPresent = false;
					for (int i = 0; i < PMSetupDispComp
							.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
							.size(); i++) {
						if (PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
								.getElementAt(i).equals(previousUpVariable)) {

							alreadyPresent = true;
						}
					}
					if (alreadyPresent == false)
						PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
								.addElement(previousUpVariable);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().getAvailableColumnsVector()
							.add(previousUpVariable);
					PMSetupDispComp.createPMSetupDisCompInst().downVariablesTextField
							.setText(moving);
					PMSetupDispComp.createPMSetupDisCompInst().downVariablesTextField
							.setVisible(true);
					PMSetupDispComp.createPMSetupDisCompInst().downVariable = moving;
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().setDownVariable(moving);

				}
			} else {

				JOptionPane.showMessageDialog(NPSMainPage
						.createNPSMainPageInst().ncaMainScreen,
						"Please ensure that the column contains only numbers",
						"Conform", JOptionPane.YES_OPTION);

			}
		} else if (PMSetupDispComp.createPMSetupDisCompInst().isFromAvailableToDown == false) {
			if (PMSetupDispComp.createPMSetupDisCompInst().downVariablesTextField
					.getText().equals("Down Variable")) {

			} else {
				String previousUpVariable = PMSetupDispComp
						.createPMSetupDisCompInst().downVariablesTextField
						.getText();
				boolean alreadyPresent = false;
				for (int i = 0; i < PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
						.size(); i++) {
					if (PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
							.getElementAt(i).equals(previousUpVariable)) {
						alreadyPresent = true;
					}
				}
				if (alreadyPresent == false)
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
							.addElement(previousUpVariable);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getAvailableColumnsVector().add(previousUpVariable);
				PMSetupDispComp.createPMSetupDisCompInst().downVariablesTextField
						.setText("Down Variable");
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.setDownVariable("Down Variable");

			}
		}

	}

	void availToUpButtonHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = (String) PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsList
				.getSelectedValue();
		if (PMSetupDispComp.createPMSetupDisCompInst().isFromAvailableToDown == true) {

			int correspondingOriginalIndex = 0;
			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getColumnPropertiesArrayList().size(); i++) {
				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getColumnPropertiesArrayList().get(i).getColumnName()
						.equals(moving)) {
					correspondingOriginalIndex = i;
					break;
				}
			}

			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getColumnPropertiesArrayList().get(
							correspondingOriginalIndex).getDataType().equals(
							"Numeric")) {

				if (PMSetupDispComp.createPMSetupDisCompInst().isFromAvailableToUp == true) {
					if (PMSetupDispComp.createPMSetupDisCompInst().upVariableTextField
							.getText().equals("Up Variable")) {
						int selectedIndex = PMSetupDispComp
								.createPMSetupDisCompInst().plotMavenavailableColumnsList
								.getSelectedIndex();
						System.out.println("The selected index is "
								+ selectedIndex);
						if (selectedIndex == -1) {
							String[] error = new String[2];
							error[0] = "No Selection";
							error[1] = "Please make a selection";
							// AboutDialog.main(error);
						} else {
							moving = (String) PMSetupDispComp
									.createPMSetupDisCompInst().plotMavenavailableColumnsList
									.getSelectedValue();
							PMSetupDispComp.createPMSetupDisCompInst().upVariableTextField
									.setText(moving);
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getPlotInfo().getProcessingInputs()
									.getMappingDataObj().setUpVariable(moving);
							PMSetupDispComp.createPMSetupDisCompInst().upVariable = moving;
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getPlotInfo().getProcessingInputs()
									.getMappingDataObj().setUpVariable(moving);
						}

					} else {
						moving = (String) PMSetupDispComp
								.createPMSetupDisCompInst().plotMavenavailableColumnsList
								.getSelectedValue();
						String previousUpVariable = PMSetupDispComp
								.createPMSetupDisCompInst().upVariableTextField
								.getText();
						boolean alreadyPresent = false;
						for (int i = 0; i < PMSetupDispComp
								.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
								.size(); i++) {
							if (PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
									.getElementAt(i).equals(previousUpVariable)) {
								alreadyPresent = true;
							}
						}
						if (alreadyPresent == false)
							PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
									.addElement(previousUpVariable);
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPlotInfo().getProcessingInputs()
								.getMappingDataObj()
								.getAvailableColumnsVector().add(
										previousUpVariable);
						PMSetupDispComp.createPMSetupDisCompInst().upVariableTextField
								.setText(moving);
						PMSetupDispComp.createPMSetupDisCompInst().upVariable = moving;
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPlotInfo().getProcessingInputs()
								.getMappingDataObj().setUpVariable(moving);
					}

				} else if (PMSetupDispComp.createPMSetupDisCompInst().isFromAvailableToUp == false) {
					if (PMSetupDispComp.createPMSetupDisCompInst().upVariableTextField
							.getText().equals("Up Variable")) {

					} else {

						String previousUpVariable = PMSetupDispComp
								.createPMSetupDisCompInst().upVariableTextField
								.getText();
						boolean alreadyPresent = false;
						for (int i = 0; i < PMSetupDispComp
								.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
								.size(); i++) {
							if (PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
									.getElementAt(i).equals(previousUpVariable)) {
								alreadyPresent = true;
							}
						}
						if (alreadyPresent == false)
							PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
									.addElement(previousUpVariable);
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPlotInfo().getProcessingInputs()
								.getMappingDataObj()
								.getAvailableColumnsVector().add(
										previousUpVariable);
						PMSetupDispComp.createPMSetupDisCompInst().upVariableTextField
								.setText("Up Variable");
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPlotInfo().getProcessingInputs()
								.getMappingDataObj().setUpVariable(
										"Up Variable");
					}
				}
			} else {

				JOptionPane.showMessageDialog(NPSMainPage
						.createNPSMainPageInst().ncaMainScreen,
						"Please ensure that the column contains only numbers",
						"Conform", JOptionPane.YES_OPTION);

			}

		}
	}

	void availToGroupButtonHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if (PMSetupDispComp.createPMSetupDisCompInst().isAvailableToGroup == true) {

			numberOftimesPlacedInGroupVariable++;
			System.out.println("The number of times placed in group count is  "
					+ numberOftimesPlacedInGroupVariable);
			if (PMSetupDispComp.createPMSetupDisCompInst().plotMavenGroupVariableTextField
					.getText().equals("Insert Group Variable")) {
				int selectedIndex = PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsList
						.getSelectedIndex();
				System.out.println("The selected index is " + selectedIndex);
				if (selectedIndex == -1) {
					String[] error = new String[2];
					error[0] = "No Selection";
					error[1] = "Please make a selection";
					// AboutDialog.main(error);
				} else {
					String moving = (String) PMSetupDispComp
							.createPMSetupDisCompInst().plotMavenavailableColumnsList
							.getSelectedValue();
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
							.removeElement(moving);
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenGroupVariableTextField
							.setText(moving);

					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().setGroupVariable(moving);
					int correspondingOriginalIndex = 0;
					for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getColumnPropertiesArrayList()
							.size(); i++) {
						if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPlotInfo().getColumnPropertiesArrayList()
								.get(i).getColumnName().equals(moving)) {
							correspondingOriginalIndex = i;
							break;
						}
					}

					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj()
							.setGroupVariableCorrespondingoriginalIndex(
									correspondingOriginalIndex);
					PMSetupDispComp.createPMSetupDisCompInst().groupVariable = moving;
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().setGroupVariable(moving);
				}

			} else {

				int selectedIndex = PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsList
						.getSelectedIndex();
				if (selectedIndex == -1) {
					String[] error = new String[2];
					error[0] = "No Selection";
					error[1] = "Please make a selection";
					// AboutDialog.main(error);
				} else {
					String moving = (String) PMSetupDispComp
							.createPMSetupDisCompInst().plotMavenavailableColumnsList
							.getSelectedValue();
					String previousGroupVariable = PMSetupDispComp
							.createPMSetupDisCompInst().plotMavenGroupVariableTextField
							.getText();
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
							.removeElement(moving);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().setGroupVariable(moving);
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
							.addElement(previousGroupVariable);
					int correspondingOriginalIndex = 0;
					for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getColumnPropertiesArrayList()
							.size(); i++) {
						if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPlotInfo().getColumnPropertiesArrayList()
								.get(i).getColumnName().equals(moving)) {
							correspondingOriginalIndex = i;
							break;
						}
					}

					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj()
							.setGroupVariableCorrespondingoriginalIndex(
									correspondingOriginalIndex);

					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().getAvailableColumnsVector()
							.add(previousGroupVariable);
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenGroupVariableTextField
							.setText(moving);
					PMSetupDispComp.createPMSetupDisCompInst().groupVariable = moving;
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().setGroupVariable(moving);
				}

			}

		} else if (PMSetupDispComp.createPMSetupDisCompInst().isAvailableToGroup == false) {
			if (PMSetupDispComp.createPMSetupDisCompInst().plotMavenGroupVariableTextField
					.getText().equals("Insert Group Variable")) {
				String[] error = new String[2];
				error[0] = "Sorry! Invalid Operation";
				error[1] = "No Group variable present";
				// .AboutDialog.main(error);
			} else {
				String moving = PMSetupDispComp.createPMSetupDisCompInst().plotMavenGroupVariableTextField
						.getText();
				PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
						.addElement(moving);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getAvailableColumnsVector().add(moving);

				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.setGroupVariableCorrespondingoriginalIndex(-1);
				PMSetupDispComp.createPMSetupDisCompInst().plotMavenGroupVariableTextField
						.setText("Insert Group Variable");
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.setGroupVariable("Insert Group Variable");

			}

		}

	}

	void availToSortButtonHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// going from available variables to sort list
		if (PMSetupDispComp.createPMSetupDisCompInst().isAvailableToSort == true) {
			System.out
					.println("value is "
							+ PMSetupDispComp.createPMSetupDisCompInst().isAvailableToSort);
			String moving = (String) PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsList
					.getSelectedValue();

			PMSetupDispComp pmSetupDispComp = PMSetupDispComp
					.createPMSetupDisCompInst();

			int selectedIndex = PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsList
					.getSelectedIndex();
			if (selectedIndex != -1) {
				pmSetupDispComp.plotMavenavailableVariablesListmodel
						.removeElement(moving);

				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getAvailableColumnsVector().remove(selectedIndex);

				pmSetupDispComp.plotMavenSortVariablesListModel
						.addElement(moving);
				pmSetupDispComp.plotMavenSortVariablesList.validate();
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().add(moving);
				pmSetupDispComp.plotMavenSortVariablesListVector.add(moving);
				System.out
						.println("The plot maven sort variables list vector size is "
								+ PMSetupDispComp.createPMSetupDisCompInst().plotMavenSortVariablesListVector
										.size());
			} else {
				System.out.println("yes the selected sort index is -1");
				String[] error = new String[2];
				error[0] = "No Selection";
				error[1] = "Please make a selection";
				// AboutDialog.main(error);
			}

		} else if (PMSetupDispComp.createPMSetupDisCompInst().isAvailableToSort == false) {

			String moving = (String) PMSetupDispComp.createPMSetupDisCompInst().plotMavenSortVariablesList
					.getSelectedValue();
			int selectedIndex = PMSetupDispComp.createPMSetupDisCompInst().plotMavenSortVariablesList
					.getSelectedIndex();
			if (selectedIndex != -1) {
				System.out.println("The selected index in the sort box is "
						+ selectedIndex);
				// plotMavenSortVariablesListModel.removeElement(moving);
				PMSetupDispComp.createPMSetupDisCompInst().plotMavenSortVariablesListModel
						.remove(selectedIndex);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().remove(selectedIndex);
				PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
						.addElement(moving);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getAvailableColumnsVector().add(moving);
				PMSetupDispComp.createPMSetupDisCompInst().plotMavenSortVariablesListVector
						.remove(moving);
				System.out
						.println("The plot maven sort variables list vector size is "
								+ PMSetupDispComp.createPMSetupDisCompInst().plotMavenSortVariablesListVector
										.size());
			} else {
				System.out.println("yes the selected sort index is -1");
				String[] error = new String[2];
				error[0] = "No Selection";
				error[1] = "Please make a selection";
				// AboutDialog.main(error);
			}

		}

	}

	void sortVariablesListSelectionHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		PMSetupDispComp.createPMSetupDisCompInst().isAvailableToSort = false;
		PMSetupDispComp.createPMSetupDisCompInst().availableToSortButton
				.setIcon(PMSetupDispComp.createPMSetupDisCompInst().moveToLeftButtonImage);

	}

	void xyScatterRadioHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

		titledBorder = BorderFactory
				.createTitledBorder(null, "XYScatter ",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		PMOptions.createPMOptionsInst().scatterJPanel.setBorder(titledBorder);

	}

	void availToYButtonHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		numberOFTimesPlacedInYVariable++;
		String moving = (String) PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsList
				.getSelectedValue();
		int selectedIndex = PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsList
				.getSelectedIndex();
		if (selectedIndex == -1) {
			String[] error = new String[2];
			error[0] = "No Selection";
			error[1] = "Please make a selection";
			// AboutDialog.main(error);
		} else {
			if (PMSetupDispComp.createPMSetupDisCompInst().yvariableTextField
					.getText().equals("Y Variable")) {

				int correspondingOriginalIndex = 0;
				for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getColumnPropertiesArrayList().size(); i++) {
					if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getColumnPropertiesArrayList()
							.get(i).getColumnName().equals(moving)) {
						correspondingOriginalIndex = i;
						break;
					}
				}

				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getColumnPropertiesArrayList().get(
								correspondingOriginalIndex).getDataType()
						.equals("Numeric")) {
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsVector
							.remove(selectedIndex);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().getAvailableColumnsVector()
							.remove(selectedIndex);
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
							.remove(selectedIndex);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().setYcolumnName(moving);
					PMSetupDispComp.createPMSetupDisCompInst().yvariableTextField
							.setText(moving);
					PMSetupDispComp.createPMSetupDisCompInst().ycolumnSelected = moving;

					for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getColumnPropertiesArrayList().size(); i++) {
						if (moving
								.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getColumnPropertiesArrayList().get(i)
										.getColumnName())) {
							PMSetupDispComp.createPMSetupDisCompInst().plotMavenyColumnCorrespondinOriginalIndex = i;
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getPlotInfo().getProcessingInputs()
									.getMappingDataObj()
									.setyColumnCorrespondinOriginalIndex(i);
						}
					}
				} else {
					JOptionPane
							.showMessageDialog(
									NPSMainPage.createNPSMainPageInst().ncaMainScreen,
									"Please ensure that the column contains only numbers",
									"Conform", JOptionPane.YES_OPTION);
				}

			} else {

				int correspondingOriginalIndex = 0;
				for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getColumnPropertiesArrayList().size(); i++) {
					if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getColumnPropertiesArrayList()
							.get(i).getColumnName().equals(moving)) {
						correspondingOriginalIndex = i;
						break;
					}
				}

				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getColumnPropertiesArrayList().get(
								correspondingOriginalIndex).getDataType()
						.equals("Numeric")) {
					String previousYVariable = PMSetupDispComp
							.createPMSetupDisCompInst().yvariableTextField
							.getText();
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsVector
							.remove(selectedIndex);
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
							.remove(selectedIndex);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().getAvailableColumnsVector()
							.remove(selectedIndex);

					PMSetupDispComp.createPMSetupDisCompInst().yvariableTextField
							.setText(moving);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().setYcolumnName(moving);
					PMSetupDispComp.createPMSetupDisCompInst().ycolumnSelected = moving;
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsVector
							.add(previousYVariable);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().getAvailableColumnsVector()
							.add(previousYVariable);

					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
							.add(
									PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
											.getSize(), previousYVariable);
					for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getColumnPropertiesArrayList().size(); i++) {
						if (moving
								.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getColumnPropertiesArrayList().get(i)
										.getColumnName())) {
							PMSetupDispComp.createPMSetupDisCompInst().plotMavenyColumnCorrespondinOriginalIndex = i;
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getPlotInfo().getProcessingInputs()
									.getMappingDataObj()
									.setyColumnCorrespondinOriginalIndex(i);
						}
					}
				} else {

					JOptionPane
							.showMessageDialog(
									NPSMainPage.createNPSMainPageInst().ncaMainScreen,
									"Please ensure that the column contains only numbers",
									"Conform", JOptionPane.YES_OPTION);

				}
			}
		}

	}

	void avialToXButtonHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		numberOftimesPlacedInXVariable++;
		String moving = (String) PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsList
				.getSelectedValue();
		int selectedIndex = PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsList
				.getSelectedIndex();
		if (selectedIndex == -1) {
			String[] error = new String[2];
			error[0] = "No Selection";
			error[1] = "Please make a selection";
			// AboutDialog.main(error);
		} else {
			if (PMSetupDispComp.createPMSetupDisCompInst().plotMavenxvariableTextField
					.getText().equals("x Variable")) {
				int correspondingOriginalIndex = 0;
				for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getColumnPropertiesArrayList().size(); i++) {
					if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getColumnPropertiesArrayList()
							.get(i).getColumnName().equals(moving)) {
						correspondingOriginalIndex = i;
						break;
					}
				}

				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getColumnPropertiesArrayList().get(
								correspondingOriginalIndex).getDataType()
						.equals("Numeric")) {
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsVector
							.remove(selectedIndex);
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
							.remove(selectedIndex);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().getAvailableColumnsVector()
							.remove(selectedIndex);
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenxvariableTextField
							.setText(moving);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().setxColumnName(moving);
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenXColumnName = moving;
					for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getColumnPropertiesArrayList().size(); i++) {
						if (moving
								.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getColumnPropertiesArrayList().get(i)
										.getColumnName())) {
							PMSetupDispComp.createPMSetupDisCompInst().plotMavenxColumnCorrespondinOriginalIndex = i;
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getPlotInfo().getProcessingInputs()
									.getMappingDataObj()
									.setxColumnCorrespondinOriginalIndex(i);
						}
					}
				} else {

					JOptionPane
							.showMessageDialog(
									NPSMainPage.createNPSMainPageInst().ncaMainScreen,
									"Please ensure that the column contains only numbers",
									"Conform", JOptionPane.YES_OPTION);

				}

			} else {

				int correspondingOriginalIndex = 0;
				for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getColumnPropertiesArrayList().size(); i++) {
					if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getColumnPropertiesArrayList()
							.get(i).getColumnName().equals(moving)) {
						correspondingOriginalIndex = i;
						break;
					}
				}

				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getColumnPropertiesArrayList().get(
								correspondingOriginalIndex).getDataType()
						.equals("Numeric")) {
					String previousXvariable = PMSetupDispComp
							.createPMSetupDisCompInst().plotMavenxvariableTextField
							.getText();

					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsVector
							.remove(selectedIndex);
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
							.remove(selectedIndex);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().getAvailableColumnsVector()
							.remove(selectedIndex);
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenxvariableTextField
							.setText(moving);
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenXColumnName = moving;
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().setYcolumnName(moving);
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableColumnsVector
							.add(previousXvariable);
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().getAvailableColumnsVector()
							.add(previousXvariable);
					PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
							.add(
									PMSetupDispComp.createPMSetupDisCompInst().plotMavenavailableVariablesListmodel
											.getSize(), previousXvariable);
					for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getColumnPropertiesArrayList().size(); i++) {
						if (moving
								.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getColumnPropertiesArrayList().get(i)
										.getColumnName())) {
							PMSetupDispComp.createPMSetupDisCompInst().plotMavenxColumnCorrespondinOriginalIndex = i;
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getPlotInfo().getProcessingInputs()
									.getMappingDataObj()
									.setxColumnCorrespondinOriginalIndex(i);
						}
					}
				} else {
					JOptionPane
							.showMessageDialog(
									NPSMainPage.createNPSMainPageInst().ncaMainScreen,
									"Please ensure that the column contains only numbers",
									"Conform", JOptionPane.YES_OPTION);
				}
			}
		}
	}

	void availableColumnsListSelctionHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		PMSetupDispComp.createPMSetupDisCompInst().availableToSortButton
				.setIcon(PMSetupDispComp.createPMSetupDisCompInst().moveToRightButtonIcon);
		PMSetupDispComp.createPMSetupDisCompInst().availableToGroupButton
				.setIcon(PMSetupDispComp.createPMSetupDisCompInst().moveToRightButtonIcon);
		
		PMSetupDispComp.createPMSetupDisCompInst().availableToDownVariableButton
				.setIcon(PMSetupDispComp.createPMSetupDisCompInst().moveToRightButtonIcon);
		PMSetupDispComp.createPMSetupDisCompInst().availableToUpVariablebutton
				.setIcon(PMSetupDispComp.createPMSetupDisCompInst().moveToRightButtonIcon);
		PMSetupDispComp.createPMSetupDisCompInst().isAvailableToSort = true;
		PMSetupDispComp.createPMSetupDisCompInst().isAvailableToGroup = true;
		PMSetupDispComp.createPMSetupDisCompInst().isFromAvailableToDown = true;
		PMSetupDispComp.createPMSetupDisCompInst().isFromAvailableToUp = true;
		
		PMSetupDispComp.createPMSetupDisCompInst().availableToyButton
		.setIcon(PMSetupDispComp.createPMSetupDisCompInst().moveToRightButtonIcon);
		
		PMSetupDispComp.createPMSetupDisCompInst().availableToUpVariablebutton
		.setIcon(PMSetupDispComp.createPMSetupDisCompInst().moveToRightButtonIcon);

	}

	void resultsCompTreeHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

			PlotMavenCreateUI.createPlotMavenUIInst().linearX.setSelected(true);
			
			int selecetdRow = PMResultsAvailComp.createPmSetupAvailCompInst().resultsAvailableTree
			.getMinSelectionRow();
			if(selecetdRow < 2){
				
			} else {
				
				PlotMavenoutputGeneration.createPMoutGen().showlinearPlot(selecetdRow -2);
				PlotMavenoutputGeneration.createPMoutGen().showLogPlot(selecetdRow - 2);

			}



	}

	void linearX() throws RowsExceededException, WriteException, BiffException,
			IOException {

		ChartPanel c = (ChartPanel) PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame.getContentPane();
		String xlable = c.getChart().getXYPlot().getDomainAxis().getLabel();
		NumberAxis linearXAxis = new NumberAxis(xlable);
		((ChartPanel) PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame
				.getContentPane()).getChart().getXYPlot().setDomainAxis(
				linearXAxis);
		PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame.validate();
	}

	void lox() throws RowsExceededException, WriteException, BiffException,
			IOException {

		ChartPanel c = (ChartPanel)PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame.getContentPane(); 
				
		JFreeChart chart = c.getChart();
		XYPlot plot = chart.getXYPlot();
		ValueAxis xaxis = plot.getDomainAxis();
		String xlable = c.getChart().getXYPlot().getDomainAxis().getLabel();
		String yLable = c.getChart().getXYPlot().getRangeAxis().getLabel();
		final NumberAxis logxaxis = new LogarithmicAxis(xlable);// xaxis.getLabel()
		final NumberAxis logYAxis = new LogarithmicAxis(yLable);// plot.getRangeAxis().getLabel()
		plot.setDomainAxis(logxaxis);

		plot.setRangeAxis(logYAxis);
		chart = new JFreeChart(plot);
		c.setChart(chart);

		// NumberAxis linearYaxis = new
		// NumberAxis(plot.getRangeAxis().getLabel());
		PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame.getContentPane().removeAll();
		PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame.setContentPane(c);
		PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame.validate();
		

	}

	void availableCompTreeHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (PMSetupAvailComp.createPMAvailCompInst().plotMavenAvailableComponentsTree
				.getSelectionPath() != null) {

			if (PMSetupAvailComp.createPMAvailCompInst().plotMavenAvailableComponentsTree
					.getMinSelectionRow() == 0) {

				if ((appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
						&& (DesStatResDispComp.createDesStatResDispInst().carryThisToOtherComponents == true)) {
					// the final parameters table is taken into Plot maven so we
					// have to display that sheet
					if (NCAMainScreen.createNcaMainScreenInstance().displayDescriptiveStatsSheet == null) {
						NCAMainScreen.createNcaMainScreenInstance().displayDescriptiveStatsSheet = new JInternalFrame(
								"Descriptive statistics", false, false, false,
								false);
						NCAMainScreen.createNcaMainScreenInstance().displayDescriptiveStatsSheet
								.setBorder(DDViewLayer.createViewLayerInstance().b);
						JinternalFrameFunctions
								.createIFFunctionsInstance()
								.removeTitleBarForinternalFrame(
										NCAMainScreen
												.createNcaMainScreenInstance().displayDescriptiveStatsSheet);
						NCAMainScreen.createNcaMainScreenInstance().displayDescriptiveStatsSheet
								.setLocation(0, 0);
						NCAMainScreen.createNcaMainScreenInstance().displayDescriptiveStatsSheet
								.setSize(
										DDViewLayer.createMTInstance().mainTabbedDesktopPane
												.getWidth(),
										DDViewLayer.createMTInstance().mainTabbedDesktopPane
												.getHeight());
						NCAMainScreen.createNcaMainScreenInstance().displayDescriptiveStatsSheet
								.setVisible(true);
						NCAMainScreen.createNcaMainScreenInstance().displayDescriptiveStatsSheet
								.getContentPane()
								.add(
										DesStatResDispComp
												.createDesStatResDispInst().finalParametersinternalFrame);
						DDViewLayer.createMTInstance().mainTabbedDesktopPane
								.add(NCAMainScreen
										.createNcaMainScreenInstance().displayDescriptiveStatsSheet);
					}
					NCAMainScreen.createNcaMainScreenInstance().displayDescriptiveStatsSheet
							.moveToFront();
					DDViewLayer.createFeaturesPanelInstance().PreviewLable
							.setVisible(true);
				} else {
					Resizing.storeOriginalPropertiesOfImportedDataSheet();
					Resizing.setTemporatyPropImportedDataSheet();
				}

			}
		}

	}

	public void groupVarTextField() throws RowsExceededException,
			WriteException, BiffException, IOException {
		PMSetupDispComp.createPMSetupDisCompInst().availableToGroupButton
				.setIcon(PMSetupDispComp.createPMSetupDisCompInst().moveToLeftButtonImage);
		PMSetupDispComp.createPMSetupDisCompInst().isAvailableToGroup = false;

	}

	boolean validateInput() {
		boolean isTimeConcValid = checkTimeConcValidity();
		return isTimeConcValid;
	}

	private boolean checkTimeConcValidity() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		boolean isTimeColumnEmpty = false;
		boolean isConcColumnEmpty = false;
		boolean isTimeNumeric = false;
		boolean isConcNumeric = false;

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName()
				.equals("")) {
			isTimeColumnEmpty = true;

		} else {
			// determine data type of time column
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getPlotInfo()
					.getColumnPropertiesArrayList()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getPlotInfo().getProcessingInputs()
									.getMappingDataObj()
									.getxColumnCorrespondinOriginalIndex())
					.getDataType().equals("Numeric")) {
				isTimeNumeric = true;
			} else {

			}
		}

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName()
				.equals("")) {
			isConcColumnEmpty = true;
		} else {
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getPlotInfo()
					.getColumnPropertiesArrayList()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getPlotInfo().getProcessingInputs()
									.getMappingDataObj()
									.getyColumnCorrespondinOriginalIndex())
					.getDataType().equals("Numeric")) {
				isConcNumeric = true;
			} else {

			}
		}

		if ((isTimeColumnEmpty == false) && (isConcColumnEmpty == false)
				&& (isTimeNumeric == true) && (isConcNumeric == true)) {
			return true;
		} else {
			return false;
		}

	}
}
