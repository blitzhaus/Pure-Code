package Model;

import view.ApplicationInfo;

public class PlasmaPOSerialAtleastOneProfileHasNoTauParamCal {

	NCA ncaInst;
	ApplicationInfo appInfo;
	String missingParamDisp;

	void calculateParmeters(int j)
	{
		
		ncaInst = NCA.creaetNCAInst();
		appInfo = ncaInst.getAppInfo();
		missingParamDisp = "Missing";
		
	}
}
