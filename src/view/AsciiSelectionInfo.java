package view;

import java.io.Serializable;

public class AsciiSelectionInfo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getNparam() {
		return nparam;
	}

	public void setNparam(int nparam) {
		this.nparam = nparam;
	}

	public int getnSecParam() {
		return nSecParam;
	}

	public void setnSecParam(int nSecParam) {
		this.nSecParam = nSecParam;
	}

	public int getnCon() {
		return nCon;
	}

	public void setnCon(int nCon) {
		this.nCon = nCon;
	}

	public int getnFunc() {
		return nFunc;
	}

	public void setnFunc(int nFunc) {
		this.nFunc = nFunc;
	}

	public int getnDer() {
		return nDer;
	}

	public void setnDer(int nDer) {
		this.nDer = nDer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nCon;
		result = prime * result + nDer;
		result = prime * result + nFunc;
		result = prime * result + nSecParam;
		result = prime * result + nparam;
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
		AsciiSelectionInfo other = (AsciiSelectionInfo) obj;
		if (nCon != other.nCon)
			return false;
		if (nDer != other.nDer)
			return false;
		if (nFunc != other.nFunc)
			return false;
		if (nSecParam != other.nSecParam)
			return false;
		if (nparam != other.nparam)
			return false;
		return true;
	}

	private int nparam;
	private int nSecParam;
	private int nCon;
	private int nFunc;
	private int nDer;
	
	public AsciiSelectionInfo(){
		
		setNparam(0);
		setnSecParam(0);
		setnCon(0);
		setnFunc(1);
		setnDer(0);
		
	}
}
