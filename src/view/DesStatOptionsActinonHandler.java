package view;

public class DesStatOptionsActinonHandler {
	
	
	public void includePercentileActHandler()
	{
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		if(DesStatOptComp.createDesStatOptInst().includePercentileRadioButtonForDS.isSelected() == true)
		{
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
			.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getDesStatsInfo()
			.getProcessingInputs().getModelInputsObj().setIfIncludePercentile(true);
		}else
		{
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
			.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getDesStatsInfo()
			.getProcessingInputs().getModelInputsObj().setIfIncludePercentile(false);
		}
	}
	
	
	public void confidenceIntCbActionHandler()
	{
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		if(DesStatOptComp.createDesStatOptInst().confidenceIntervalCheckBox.isSelected() == true)
		{
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
			.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getDesStatsInfo()
			.getProcessingInputs().getModelInputsObj().setIfIncludeConInterval(true);
		}else
		{
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
			.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getDesStatsInfo()
			.getProcessingInputs().getModelInputsObj().setIfIncludeConInterval(false);
		}
	}
	
	
	public void numberofSDCbActionHandler()
	{
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		if(DesStatOptComp.createDesStatOptInst().numberOfSDCheckBox.isSelected() == true)
		{
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
			.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getDesStatsInfo()
			.getProcessingInputs().getModelInputsObj().setIfIncludeNumberofSD(true);
		}else
		{
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
			.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getDesStatsInfo()
			.getProcessingInputs().getModelInputsObj().setIfIncludeNumberofSD(false);
		}
	}

}
