package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import Common.MyComparator;

public class AsciiParametersInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HashMap<Integer, String> getPriParameters() {
		return priParameters;
	}

	public void setPriParameters(HashMap<Integer, String> priParameters) {
		this.priParameters = priParameters;
	}

	public HashMap<Integer, String> getSecParameters() {
		return secParameters;
	}

	public void setSecParameters(HashMap<Integer, String> secParameters) {
		this.secParameters = secParameters;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((priParameters == null) ? 0 : priParameters.hashCode());
		result = prime * result
				+ ((secParameters == null) ? 0 : secParameters.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AsciiParametersInfo other = (AsciiParametersInfo) obj;
		if (priParameters == null) {
			if (other.priParameters != null)
				return false;
		} else if (!MyComparator.createMyCompInst().compareasciiPrimaryPar(priParameters, other.priParameters))
			return false;
		if (secParameters == null) {
			if (other.secParameters != null)
				return false;
		} else if (!MyComparator.createMyCompInst().compareasciiPrimaryPar(secParameters, other.secParameters))
			return false;
		return true;
	}



	MyComparator comp;
	

	HashMap<Integer, String> priParameters;
	HashMap<Integer, String> secParameters;
	
	public AsciiParametersInfo(){
		setPriParameters(new HashMap<Integer, String>());
		setSecParameters(new HashMap<Integer, String>());
	}
}
