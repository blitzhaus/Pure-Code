package Model;


public class CalculateWagNelsonFracAbsorption {
	
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
		
		wagner();

	}
	
	public void wagner() {
		
		if(timeVal[0] !=0)
			addZeroConc();
		
		
		prepareAucInfo();
		
		deConvoVarInst.cumulAmtAbsVInf = deConvoVarInst.lambdaZ *deConvoVarInst.aucInf;
		
		deConvoVarInst.cumulAmtAbsV = new double[timeVal.length];
		deConvoVarInst.fracAbsorption = new double[timeVal.length];
		
		for (int i = 0; i < timeVal.length; i++) {
			deConvoVarInst.cumulAmtAbsV[i] = concVal[i] + deConvoVarInst.lambdaZ *deConvoVarInst.auc[i];
		}

		for (int i = 0; i < timeVal.length; i++) {
			deConvoVarInst.fracAbsorption[i] = deConvoVarInst.cumulAmtAbsV[i]/deConvoVarInst.cumulAmtAbsVInf;
		}
		
		
	}

	private void addZeroConc() {
		
		double[] tempTime = new double[timeVal.length];
		double[] tempConc = new double[timeVal.length];
		
		for (int i = 0; i < tempConc.length; i++) {
			tempTime[i] = timeVal[i];
			tempConc[i] = concVal[i];
		}
		
		timeVal = new double[tempTime.length +1];
		concVal = new double[tempTime.length +1];
		
		for (int i = 0; i < tempConc.length; i++) {
			timeVal[i+1] = tempTime[i];
			concVal[i+1] = tempConc[i];
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
