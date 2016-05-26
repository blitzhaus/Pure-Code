package view;

import java.io.Serializable;
import java.util.HashMap;

import Common.MyComparator;

public class AsciiDeclareVariablesInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String, String> asciiDecVarHM;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((asciiDecVarHM == null) ? 0 : asciiDecVarHM.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		MyComparator comp = MyComparator.createMyCompInst();
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AsciiDeclareVariablesInfo other = (AsciiDeclareVariablesInfo) obj;
		if (asciiDecVarHM == null) {
			if (other.asciiDecVarHM != null)
				return false;
		} else if (!comp.compareStrStr(asciiDecVarHM, other.asciiDecVarHM))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AsciiDecVariables [asciiDecVarHM=" + asciiDecVarHM + "]";
	}
	public HashMap<String, String> getAsciiTempVarHM() {
		return asciiDecVarHM;
	}
	public void setAsciiTempVarHM(HashMap<String, String> asciiTempVarHM) {
		this.asciiDecVarHM = asciiTempVarHM;
	}
	public AsciiDeclareVariablesInfo(){
		setAsciiTempVarHM(new HashMap<String, String>());
	}
}
