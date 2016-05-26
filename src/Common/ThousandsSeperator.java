package Common;

import org.apache.commons.lang.StringUtils;

public class ThousandsSeperator {  
	
	public static ThousandsSeperator THOU_SEP = null;
	public static ThousandsSeperator createThouSepInst(){
		if(THOU_SEP == null){
			THOU_SEP = new ThousandsSeperator("just object creation");
		}
		return THOU_SEP;
	}
    
	public ThousandsSeperator(String dummy){
		
	}

	public String insertCommas(String str) {
		String str1;
		String str2;

		if (str.contains(".")) {
			str1 = str.substring(0, str.indexOf("."));
			str2 = str.substring(str.indexOf("."), str.length());

		} else {
			str1 = str;
			str2 = "";
		}

		if (str1.length() < 4) {
			return str1.concat(str2);
		}
		return insertCommas(str1.substring(0, str1.length() - 3)) + ","
				+ str1.substring(str1.length() - 3, str1.length()).concat(str2);
	}
    
    
    public String removeComma(String str){
    	str = StringUtils.remove(str, ",");
    	return str;
    }
 
}  