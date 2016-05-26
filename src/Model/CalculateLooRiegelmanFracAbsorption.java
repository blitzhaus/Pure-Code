package Model;

public class CalculateLooRiegelmanFracAbsorption {

	DeConvolutionVariables deConvoVarInst;
	double[] timeVal;
	double[] concVal;
	
	double cLast;
	double cLastPred;
	double aucLast;
	
	void fracAbsorptionCalculation() {
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		timeVal = deConvoVarInst.time;
		concVal = deConvoVarInst.conc;
		
		deConvoVarInst.cumulAmtAbsV = new double[timeVal.length];
		deConvoVarInst.fracAbsorption = new double[timeVal.length];
		deConvoVarInst.amtPeripheralV = new double[timeVal.length];
		
		double k10 = deConvoVarInst.parameter[0];
		double k12 = deConvoVarInst.parameter[1];
		double k21 = deConvoVarInst.parameter[2];
		
		prepareAucInfo();
		
		deConvoVarInst.cumulAmtAbsVInf = 	k10 * deConvoVarInst.aucInf;
		
		for (int i = 1; i < timeVal.length; i++) {
			
			double term1 = deConvoVarInst.amtPeripheralV[i-1] * Math.exp(-k21*(timeVal[i] - timeVal[i-1]));
			
			double term2 = (k12*concVal[i-1]/k21)*(1 - Math.exp(-k21*(timeVal[i] - timeVal[i-1])));
			
			double term3 = k12 * (timeVal[i] - timeVal[i-1])*(concVal[i] - concVal[i-1])/2;
			
			deConvoVarInst.amtPeripheralV[i] = term1 + term2 + term3;
			
		}
		
		for (int i = 0; i < timeVal.length; i++) {
			deConvoVarInst.cumulAmtAbsV[i] = concVal[i] + k10 * deConvoVarInst.auc[i] + deConvoVarInst.amtPeripheralV[i];
			
		}
		
		for (int i = 0; i < timeVal.length; i++) {
			deConvoVarInst.fracAbsorption[i] = deConvoVarInst.cumulAmtAbsV[i] / deConvoVarInst.cumulAmtAbsVInf;
		}

	}
	
	
	

	private void prepareAucInfo() {
		DeConvoAucCalculation deConvoAucCalInst = new DeConvoAucCalculation();
			
			if(deConvoVarInst.aucCalMethodNo == 0)
				deConvoAucCalInst.linearlogTrapezoidal(timeVal, concVal);
			else if(deConvoVarInst.aucCalMethodNo == 1)
				deConvoAucCalInst.linearUplogDownTrapezoidal(timeVal, concVal);
			else if(deConvoVarInst.aucCalMethodNo == 2 || deConvoVarInst.aucCalMethodNo == 3)
				deConvoAucCalInst.linearTrapezoidal(timeVal, concVal);
			
			deConvoVarInst.auc = deConvoAucCalInst.AUCValue;
			aucLast = deConvoAucCalInst.AUClast;
			
			if(deConvoVarInst.ifAucInfProvided == false)
			{
				cLast = concVal[concVal.length-1];
				
				if(deConvoVarInst.ifCLastPred == true)
				{
					double intercept = deConvoVarInst.lambdaZ * timeVal[timeVal.length - 1] + Math.log(cLast);
					double cLastPred = Math.exp(intercept - deConvoVarInst.lambdaZ);
					
					deConvoVarInst.aucInf = aucLast + cLastPred/deConvoVarInst.lambdaZ;
				}else
					deConvoVarInst.aucInf = aucLast + cLast/deConvoVarInst.lambdaZ;
			
				
			}
	}

	
	
}
