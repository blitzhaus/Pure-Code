package view;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;

public class InVitroMainScreenCreator {

	public static InVitroMainScreenCreator INVITRO_MAIN_SCREEN = null;

	public static InVitroMainScreenCreator createInVitroMainScreenInstance()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (INVITRO_MAIN_SCREEN == null) {
			INVITRO_MAIN_SCREEN = new InVitroMainScreenCreator();
		}
		return INVITRO_MAIN_SCREEN;
	}

	JInternalFrame inVitroMainInternalFrame;
	JTextField doseTextField;
	JTextField timeOfDoseText;
	JTextField tauvalueText;
	JDesktopPane setupOptionsDesktopPane;

	JButton fitFitSlopesButton;
	JRadioButton unitradioButton;
	JTextField unitTextField;
	JInternalFrame modelSettingsInternalFrame;
	JInternalFrame doseOptionsInternalFrame;
	JInternalFrame fitSlopesInternalFrame;
	JDesktopPane ncaMainScreenDesktopPane;
	JInternalFrame ncaMainScreen;
	protected static UnitBuilder doseUnitBuilderFrame;

	JLabel infusionTimeLable;
	JTextField infusionTimeTextField;
	JCheckBox pageBreaksCheckBox;

	JCheckBox excludeProfilesCheckBox;

	// protected static JTextField numberOfPartialAreaTextField;

	AbstractTableModel tt;

	// dummy flags for table entry restriction
	boolean tblPartialArea;
	boolean tblDosing;
	boolean tblParNames;

	JScrollPane partialAreaScrollPane;
	JCheckBox useInternalWorkSheetcheck;
	// protected static JRadioButton partialAreaRadioButton;

	JInternalFrame preferredUnitSelectionInternalFrame;
	JButton preferredUnitBuilderButton;
	JButton resetUnitBuilderButton;
	JTable tableForPreferredUnit;
	int triedToChange;

	JTableHeader tableForDosingHeader;

	double[][] doingInputArray;

	int availableoutputsTreeSelectedRow;

	JButton doseUnitButton;

	JLabel startTimeForLambdaZLabel;
	JLabel endTimeForLambdaZLabel;
	JButton computeLambdaZButton;
	JDesktopPane lambdaZMethodSelectionDesktopPane;
	int numberOfPartialArea;
	JPanel modelSettingsPanel;

	JPanel partialAreasoptionsPanel;

	// DefaultTableModel model;
	JInternalFrame dummyDosingInternalFrame;
	int numberOfTimesDosingScreenChanged;

	String[] previousSortVariablesForPA;
	String[] previousSortVariablesForLZ;

	int previousSizeForPartialArea;
	int numberOfTimeDisableCurveStrippingCalled = 0;

	int previousSizeForLambdaZ;
	boolean ifMappingIsChangedInLambdaZ = false;

	TitledBorder titledBorder;

	JPanel startTimePanel;
	JPanel endTimePanel;

	int previousRootOfAdministration = 0;
	int numberOfTimeInsideTheLoop = 0;
	int columncount = 0;

	JCheckBox selectionCheckBoc;
	JCheckBox exclusionCheckBox;

	ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

	JInternalFrame displayDescriptiveStatsSheet;

	Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	Font componentOptionseFontForComboBox = new Font("Arial", Font.PLAIN, 11);
	Font componentLablesFont = new Font("Arial", Font.BOLD, 11);

	public void inVitroMainScreenCreation() throws RowsExceededException,
			WriteException, IOException, BiffException {

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
								.getSelectedSheetIndex()).setAnalysisType(
						"invitro");

		DDViewLayer.createMTInstance().mainTabbedFrame.setSize(DDViewLayer
				.createMTInstance().mainTabbedFrameOriginalWidth, DDViewLayer
				.createMTInstance().mainTabbedFrameoriginalHeight);

		inVitroMainInternalFrame = new JInternalFrame("InVitro Main Screen",
				false, false, false, false);

		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(inVitroMainInternalFrame);
		inVitroMainInternalFrame.setLocation(0, 0);
		inVitroMainInternalFrame
				.setSize(DDViewLayer.createMTInstance().mainTabbedFrame
						.getWidth(),
						(int) (DDViewLayer.createMTInstance().mainTabbedFrame
								.getHeight() / 2));

		InVitroMainScreenGUICreator.createInVitroMainScreenGui();
		InVitroMappingDispGuiCreator.createMappingInstance().mappingInternalFrame
				.moveToFront();

		inVitroMainInternalFrame.setVisible(true);
		inVitroMainInternalFrame
				.setBorder(DDViewLayer.createViewLayerInstance().b);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane
				.add(inVitroMainInternalFrame);
		inVitroMainInternalFrame.moveToFront();
	}

}
