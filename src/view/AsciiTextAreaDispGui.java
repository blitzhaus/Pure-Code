package view;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.io.IOException;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;

public class AsciiTextAreaDispGui {

	JInternalFrame asciiTAInternalFrame;

	public AsciiTextAreaDispGui() {

	}

	public static AsciiTextAreaDispGui ASCII_TA_DISP = null;

	public static AsciiTextAreaDispGui createAsciiTextAreaInstance()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (ASCII_TA_DISP == null) {
			ASCII_TA_DISP = new AsciiTextAreaDispGui();
		}
		return ASCII_TA_DISP;
	}

	public void asciiTaDispGuiCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {
		createAsciiTaInternalFrame();
	}

	
	JTextArea asciiTextArea;
	private void createAsciiTaInternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {

		asciiTAInternalFrame = new JInternalFrame("Mapping ", false, false,
				false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(asciiTAInternalFrame);
		asciiTAInternalFrame.setBackground(Color.white);
		DDViewLayer.createMTInstance().mainTabbedFrame.setSize(DDViewLayer
				.createMTInstance().mainTabbedFrameOriginalWidth, DDViewLayer
				.createMTInstance().mainTabbedFrameoriginalHeight);
		int width = 0;
		int height = 0;
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
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
		if (analysisType.equals("ascii")) {
			width = AsciiMainScreen.createAsciiMainScreenInstance().asciiMainInternalFrame
					.getWidth()
					- CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
							.getWidth();
			height = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
					.getHeight();
		}
		asciiTAInternalFrame
				.setLocation(CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ CaSetupAvailableCompCreator
										.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		asciiTAInternalFrame.setSize(width, height);
		asciiTAInternalFrame.setVisible(true);
		asciiTextArea = new JTextArea();
		JScrollPane textAreaSp = new JScrollPane(asciiTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiTAInternalFrame.getContentPane().add(textAreaSp);
		

		asciiTAInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
				.add(asciiTAInternalFrame);
	}

}
