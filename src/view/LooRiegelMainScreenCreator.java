package view;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;

public class LooRiegelMainScreenCreator {
	

	public static LooRiegelMainScreenCreator LOORIEGEL_MAIN_SCREEN = null;

	public static LooRiegelMainScreenCreator createLooRiegelMainScreenInstance()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (LOORIEGEL_MAIN_SCREEN == null) {
			LOORIEGEL_MAIN_SCREEN = new LooRiegelMainScreenCreator();
		}
		return LOORIEGEL_MAIN_SCREEN;
	}

	JInternalFrame looRiegelMainInternalFrame;
	JDesktopPane setupOptionsDesktopPane;

	ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

	Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	Font componentOptionseFontForComboBox = new Font("Arial", Font.PLAIN, 11);
	Font componentLablesFont = new Font("Arial", Font.BOLD, 11);

	public void looRiegelMainScreenCreation() throws RowsExceededException,
			WriteException, IOException, BiffException {

		DDViewLayer.createMTInstance().mainTabbedFrame.setSize(DDViewLayer
				.createMTInstance().mainTabbedFrameOriginalWidth, DDViewLayer
				.createMTInstance().mainTabbedFrameoriginalHeight);

		looRiegelMainInternalFrame = new JInternalFrame("PolyExpo Main Screen",
				false, false, false, false);

		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(looRiegelMainInternalFrame);
		looRiegelMainInternalFrame.setLocation(0, 0);
		looRiegelMainInternalFrame
				.setSize(DDViewLayer.createMTInstance().mainTabbedFrame
						.getWidth(),
						(int) (DDViewLayer.createMTInstance().mainTabbedFrame
								.getHeight() / 2));

		LooRiegelMainScreenGUICreator.createLooRiegelMainScreenGui();
		DeConvoMappingDispGuiCreator.createMappingInstance().mappingInternalFrame
				.moveToFront();

		looRiegelMainInternalFrame.setVisible(true);
		looRiegelMainInternalFrame
				.setBorder(DDViewLayer.createViewLayerInstance().b);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane
				.add(looRiegelMainInternalFrame);
		looRiegelMainInternalFrame.moveToFront();
	}





}
