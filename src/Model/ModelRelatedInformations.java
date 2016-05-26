package Model;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class ModelRelatedInformations {
	String[] modelinformations;
	ApplicationInfo appInfo ;
	PkParamEstimator caParamCalInst;
	PkPdInfo pkpdInst;
	ProcessingInputsInfo  procInputInst;
	
	public ModelRelatedInformations()
 {
	
		pkpdInst = PkPdInfo.createPKPDInstance();
		procInputInst = pkpdInst.copyProcessingInput();
		
		if(pkpdInst.analysisType.equals("pk"))
		{
			caParamCalInst = PkParamEstimator.createPkParamEstimateInstance();
			appInfo = caParamCalInst.appInfoinst;

			if (procInputInst.getModelInputTab1Obj()
					.getAlgebraicModel() == true) {
				modelinformations = new String[19];
				modelinformations[0] = "1 (One Compartment, IV-Bolus)";
				modelinformations[1] = "2 (One Compartment, IV-Infusion)";
				modelinformations[2] = "3 (One Compartment, 1st Order, Without Lag Time)";
				modelinformations[3] = "4 (One Compartment, 1st Order, With Lag Time)";
				modelinformations[4] = "5 (One Compartment, 1st Order, K10=K01, Without Lag Time)";
				modelinformations[5] = "6 (One Compartment, 1st Order, K10=K01,With Lag Time)";
				modelinformations[6] = "7 (Two Compartments, IV-bolus, Micro)";
				modelinformations[7] = "8 (Two Compartments, IV-bolus, Macro)";
				modelinformations[8] = "9 (Two Compartments, IV-Infusion, Micro)";
				modelinformations[9] = "10 (Two Compartments, IV-Infusion, Macro)";
				modelinformations[10] = "11 (Two Compartments, 1st Order, Micro, Without Lag Time)";
				modelinformations[11] = "12 (Two Compartments, 1st Order, Micro, With Lag Time)";
				modelinformations[12] = "13 (Two Compartments, 1st Order, Macro, Without Lag Time)";
				modelinformations[13] = "14 (Two Compartments, 1st Order, Macro, With Lag Time)";
				modelinformations[14] = "15 (One Compartment, IV-bolus+IV-Infusion, Micro)";
				modelinformations[15] = "16 (Two Compartments, IV-bolus+IV-Infusion, Micro)";
				modelinformations[16] = "17 (Two Compartments, IV-bolus+IV-Infusion, Macro)";
				modelinformations[17] = "18 (Three Compartments, IV-bolus, Macro)";
				modelinformations[18] = "19 (Three Compartments, IV-Infusion, Macro)";
			} else {

				modelinformations = new String[30];
				modelinformations[0] = "1 (One Compartment, IV-Bolus,  Rate Constants Parametrization)";
				modelinformations[1] = "2 (One Compartment, IV-Bolus, Clearance Parametrization)";
				modelinformations[2] = "3 (One Compartment, Oral, Rate Constants Parametrization, Without Lag Time)";
				modelinformations[3] = "4 (One Compartment, Oral, Clearance Parametrization, Without Lag Time)";
				modelinformations[4] = "5 (One Compartment, Oral, Rate Constants Parametrization, With Lag Time)";
				modelinformations[5] = "6 (One Compartment, Oral, Clearance Parametrization, With Lag Time)";
				modelinformations[6] = "7 (One Compartment, IV-Infusion, Rate Constants Parametrization)";
				modelinformations[7] = "8 (One Compartments, IV-Infusion, Clearance Parametrization)";
				modelinformations[8] = "9 (Two Compartments, IV-Bolus, Rate Constants Parametrization)";
				modelinformations[9] = "10 (Two Compartments, IV-Bolus, Clearance Parametrization)";
				modelinformations[10] = "11 (Two Compartments, Oral, Rate Constants Parametrization, Without Lag Time)";
				modelinformations[11] = "12 (Two Compartments, Oral, Clearance Parametrization, Without Lag Time)";
				modelinformations[12] = "13 (Two Compartments, Oral, Rate Constants Parametrization, With Lag Time)";
				modelinformations[13] = "14 (Two Compartments, Oral, Clearance Parametrization, With Lag Time)";
				modelinformations[14] = "15 (Two Compartments, IV-Infusion, Rate Constants Parametrization)";
				modelinformations[15] = "16 (Two Compartment, IV-Infusion, Clearance Parametrization)";
				modelinformations[16] = "17 (Three Compartments, IV-Bolus, Rate Constants Parametrization)";
				modelinformations[17] = "18 (Three Compartments, IV-Bolus, Clearance Parametrization)";
				modelinformations[18] = "19 (Three Compartments, IV-Infusion, Rate Constants Parametrization)";
				modelinformations[19] = "20 (Three Compartments, IV-Infusion, Clearance Parametrization)";
				modelinformations[20] = "21 (One Compartments, IV-Bolus + IV-Infusion, Rate Constants Parametrization)";
				modelinformations[21] = "22 (One Compartments, IV-Bolus + IV-Infusion, Clearance Parametrization)";
				modelinformations[22] = "23 (Two Compartments, IV-Bolus + IV-Infusion, Rate Constants Parametrization)";
				modelinformations[23] = "24 (Two Compartments, IV-Bolus + IV-Infusion, Clearance Parametrization)";
				modelinformations[24] = "25 (One Compartments, Oral + IV-Infusion, Rate Constants Parametrization)";
				modelinformations[25] = "26 (One Compartments, Oral + IV-Infusion, Clearance Parametrization)";
				modelinformations[26] = "27 (Two Compartments, Oral + IV-Infusion, Rate Constants Parametrization)";
				modelinformations[27] = "28 (Two Compartments, Oral + IV-Infusion, Clearance Parametrization)";

			}
			
		}
		else if(pkpdInst.analysisType.equals("pd") || pkpdInst.analysisType.equals("pkpdlink"))
		{
			modelinformations = new String[8];
			modelinformations[0] = "1 (Simple Emax model)";
			modelinformations[1] = "2 (Simple Emax model with a baseline effect parameter)";
			modelinformations[2] = "3 (Inhibitory effect model)";
			modelinformations[3] = "4 (Inhibitory effect model with a baseline effect parameter)";
			modelinformations[4] = "5 (Sigmoid Emax model)";
			modelinformations[5] = "6 (Sigmoid Emax model with a baseline effect parameter)";
			modelinformations[6] = "7 (Sigmoid inhibitory effect model)";
			modelinformations[7] = "8 (Sigmoid inhibitory effect model with a baseline effect parameter)";
			
		}
		else if(pkpdInst.analysisType.equals("mm"))
		{
			modelinformations = new String[5];
			modelinformations[0] = "1 (One compartment with bolus input and Michaelis-Menten output)";
			modelinformations[1] = "2 (One compartment with constant IV input and Michaelis-Menten output)";
			modelinformations[2] = "3 (One compartment with 1st order input, Michaelis-Menten output and no time lag)";
			modelinformations[3] = "4 (One compartment with 1st order input, Michaelis-Menten output and a time lag)";
			modelinformations[4] = "5 (Two compartment with constant IV input, Michaelis-Menten output)";
			
		}
		else if(pkpdInst.analysisType.equals("irm"))
		{
			modelinformations = new String[4];
			modelinformations[0] = "1 (Inhibition of input)";
			modelinformations[1] = "2 (Inhibition of output)";
			modelinformations[2] = "3 (Stimulation of input)";
			modelinformations[3] = "4 (Stimulation of output)";
		
		}
			
		
		
	}
	
	public String getModelinformationForModelNo(int modelNo) {
		return modelinformations[modelNo];
	}
	
	public String[] getModelinformation() {
		return modelinformations;
	}
	public void setModelinformation(String[] modelinformation) {
		this.modelinformations = modelinformation;
	}

}
