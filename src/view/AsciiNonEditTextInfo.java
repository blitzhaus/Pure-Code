package view;

import java.io.Serializable;

public class AsciiNonEditTextInfo implements Serializable{

	private String nonEditText;
	public String getNonEditText() {
		return nonEditText;
	}
	public void setNonEditText(String string) {
		this.nonEditText = string;
	}
	public AsciiNonEditTextInfo(){
		nonEditText = new String();
	}
}
