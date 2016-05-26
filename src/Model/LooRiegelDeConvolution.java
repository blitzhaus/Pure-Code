package Model;

import java.io.IOException;
import java.sql.Time;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import view.ApplicationInfo;

public class LooRiegelDeConvolution {
	

	static LooRiegelDeConvolution looRiegelDeConvoInst = null;

	public static LooRiegelDeConvolution createLooRiegelDeConvoInstance() {
		if (looRiegelDeConvoInst == null) {
			looRiegelDeConvoInst = new LooRiegelDeConvolution();
		}
		return looRiegelDeConvoInst;
	}

	DeConvolutionVariables deConvoVarInst;
	DeConvoModelInfoPreparator deConvoModelInfoInst;
	DeConvoOutputPreparator deConvoOutputInst;
	DeConvoResultStructStorer resultStructStorerInst;

	public void calculateLooRiegelDeConvolution(ApplicationInfo applicationInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		DeConvolutionVariables.deConvoVariablesInst = null;
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		deConvoVarInst.appInfo = applicationInfo;
		deConvoVarInst.analysisType = "looriegel";
		deConvoModelInfoInst = new DeConvoModelInfoPreparator();
		deConvoOutputInst = new DeConvoOutputPreparator();
		resultStructStorerInst = new DeConvoResultStructStorer();

		String stringTime;

		deConvoModelInfoInst.copyProfileRelatedInfo();

		deConvoModelInfoInst.copyModelOptions();

		deConvoOutputInst.initializeResultsAL();
		stringTime = systemTime();

		deConvoOutputInst.writingHeaderToTextOutput(stringTime);

		mainParamComputationLoop();

	}

	private void mainParamComputationLoop() throws IOException, RowsExceededException, WriteException, BiffException {

		int sum = 0;

		for (int profileNo = 0; profileNo < deConvoVarInst.noOfProfiles; profileNo++) {

			deConvoVarInst.currentProfileNumber = profileNo;

			deConvoVarInst.workSheetOutputInst
					.getTextoutput()
					.add(
							"\r\n"
									+ StringUtils
											.rightPad(
													"*************************************************"
															+ "**********************************",
													80) + "\r\n\r\n");

			deConvoVarInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 25)
							+ StringUtils.rightPad(
									"Output of the Profile Number:", 37)
							+ StringUtils.rightPad(
									(deConvoVarInst.currentProfileNumber + 1)
											+ "", 5) + "\r\n\r\n");

			deConvoModelInfoInst.createSingleProfileData(sum, profileNo);
			sum = sum + deConvoVarInst.profileObsNos[profileNo];

			deConvoVarInst.removeMissingData(profileNo);

			deConvoVarInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad(
							"*************************************************"
									+ "**********************************", 80)
							+ "\r\n\r\n");

			deConvoVarInst.workSheetOutputInst.getTextoutput().add("\r\n");
			deConvoModelInfoInst.copyDosingInfoForWagNelson(profileNo);
			deConvoModelInfoInst.copyDeConvoModelInfo(profileNo);

			estimateFractionAbsorption();

			deConvoOutputInst.writeSpreadSheetOutputs(profileNo);

			deConvoVarInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

			Time t2 = new Time(System.currentTimeMillis());

			deConvoVarInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad("Time at end is:", 20) + t2);
			deConvoVarInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

			deConvoVarInst.workSheetOutputInst
					.getTextoutput()
					.add(
							"\r\n\r\n"
									+ StringUtils
											.rightPad(
													"*************************************************"
															+ "**********************************",
													80) + "\r\n\r\n");
			deConvoVarInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 15)
							+ StringUtils.rightPad(
									"End of the Output of the Profile Number:",
									5)
							+ (deConvoVarInst.currentProfileNumber + 1));
			deConvoVarInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");
			deConvoVarInst.workSheetOutputInst
					.getTextoutput()
					.add(
							"\r\n\r\n"
									+ StringUtils
											.rightPad(
													"*************************************************"
															+ "**********************************",
													80) + "\r\n\r\n");
			deConvoVarInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");
			deConvoVarInst.workSheetOutputInst.getTextoutput().add("\r\n\r\n");

			String string = systemTime();

			resultStructStorerInst.storeSpreedSheetResults();

		}

	}

	private void estimateFractionAbsorption() {
		CalculateLooRiegelmanFracAbsorption looRiegelFracAbsCalInst = new CalculateLooRiegelmanFracAbsorption();
		looRiegelFracAbsCalInst.fracAbsorptionCalculation();
	}

	public String systemTime() {
		java.sql.Timestamp currentTime = new java.sql.Timestamp(
				new java.util.Date().getTime());
		String currenttime = String.valueOf(currentTime);
		String date = currenttime.substring(0, 10);
		String time = currenttime.substring(11, 19);
		// logArea.append("     " + date + "  " + time+"\n");
		return (date + "/" + time);
	}





}
