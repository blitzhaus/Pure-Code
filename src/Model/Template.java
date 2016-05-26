package Model;

import java.util.ArrayList;

import view.TableWizardOpMetricOptions;

public interface Template {

	public abstract void setTabWizIpOptions(ArrayList<String> groupVariables,
			ArrayList<String> idVariables, ArrayList<String> crossVariables,
			ArrayList<String> ordinaryVariables, int templateType);

	/*public abstract void setTabWizOpOptions(
			TableWizardOpMetricOptions opMetListing);*/

	public abstract void prepareTemplate();

}