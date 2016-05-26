package Common;

public class DataManipulationFunctions {

	public static DataManipulationFunctions DATA_MAN_FUNC = null;
	public static DataManipulationFunctions createDataManFuncInst(){
		if(DATA_MAN_FUNC == null){
			DATA_MAN_FUNC = new DataManipulationFunctions();
		}
		return DATA_MAN_FUNC;
	}
	
	public double round(double value, int decimalPlace)
	{
		double power_of_ten = 1;
		while (decimalPlace-- > 0)
		{
			power_of_ten *= 10.0;
		}
		return Math.round(value * power_of_ten)/ power_of_ten;
	}
	
	public double truncateDouble(double number, int numDigits) {
	    double result = number;
	    String arg = "" + number;
	    int idx = arg.indexOf('.');
	    if (idx!=-1) {
	        if (arg.length() > idx+numDigits) {
	            arg = arg.substring(0,idx+numDigits+1);
	            result  = Double.parseDouble(arg);
	        }
	    }
	    return result ;
	}

}


