package Common;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class OptionalAndRequiredDecimals {
	
	public OptionalAndRequiredDecimals()
	{
		
	}
	
	public String performFormatting(double weightingIndex, int optDec,
			String reqDec) {
		String weightIndexStr = new String();
		
		if(optDec == 0){
			if(reqDec.equals("")){
				weightIndexStr = weightingIndex+"";
			} else{
				DecimalFormat dec = new DecimalFormat(reqDec);
				dec.setRoundingMode(RoundingMode.DOWN);
				weightIndexStr = dec.format(weightingIndex);
			}
		} else if(reqDec.equals("")){
			if(optDec != 0){
				
					weightIndexStr = DataManipulationFunctions.createDataManFuncInst().truncateDouble(weightingIndex, optDec)+"";
				
				
			} else if(optDec == 0){
				weightIndexStr = weightingIndex+"";
			}
		}
		
		return weightIndexStr;
	}

}
