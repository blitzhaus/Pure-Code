package view;



public class NcaOptWeightOptionsHandler extends NcaOptionsGui {

	public static NcaOptWeightOptionsHandler NCA_WEIGHT_OPT_HAND = null;
	public static NcaOptWeightOptionsHandler createNcaWeightOptHandler(){
		if(NCA_WEIGHT_OPT_HAND == null){
			NCA_WEIGHT_OPT_HAND = new NcaOptWeightOptionsHandler();
		}
		return NCA_WEIGHT_OPT_HAND;
	}
	public NcaOptWeightOptionsHandler()  {
		super("just object creation");
		// TODO Auto-generated constructor stub

	}

	public void handler(){

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// NCA.weightingIndex = weightingOptions.getSelectedIndex();
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getWeightingOption()==0) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.setWeightingOption(0);
			weightingValueTextField.setEditable(true);
			weightingValueTextField.setText("0");
			weightingValueTextField.setEditable(false);
			
		}
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getWeightingOption()== 1) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.setWeightingOption(1);
			weightingValueTextField.setEditable(true);
			weightingValueTextField.setText("-1");
			weightingValueTextField.setEditable(false);
		}
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getWeightingOption() == 2) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.setWeightingOption(2);
			weightingValueTextField.setEditable(true);
			weightingValueTextField.setText("-2");
			weightingValueTextField.setEditable(false);
		}
	
	}
}