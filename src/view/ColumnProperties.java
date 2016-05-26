package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;

import view.ApplicationInfo;


 public class ColumnProperties implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6321845228646987305L;
	private String dataType;// 1 indicates text type and 0 indicates Numeric type
	private String dataFormatCustom;//temporarily 1 indicates the G8 custom format
	private String requiredDecimals;
	private int optionalDecimals;
	
	public String getRequiredDecimals() {
		return requiredDecimals;
	}
	public void setRequiredDecimals(String requiredDecimals) {
		this.requiredDecimals = requiredDecimals;
	}
	public int getOptionalDecimals() {
		return optionalDecimals;
	}
	public void setOptionalDecimals(int optDecimals) {
		this.optionalDecimals = optDecimals;
	}
	
	private UnitBuilderData unitBuilderDataObj;

	
	@Override
	public String toString() {
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String consoleS = "";
		String s = "\t//column properties........ ";

		s = s + "\t\tdata type = "+ dataType+"\n";
		
		s = s +"\t\t//unit builder object"+ unitBuilderDataObj+"\n\t\t}";
		
		//p.append(s);
		return s;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColumnProperties other = (ColumnProperties) obj;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (dataFormatCustom == null) {
			if (other.dataFormatCustom != null)
				return false;
		} else if (!dataFormatCustom.equals(other.dataFormatCustom))
			return false;
		if (dataType == null) {
			if (other.dataType != null)
				return false;
		} else if (!dataType.equals(other.dataType))
			return false;
		if (unitBuilderDataObj == null) {
			if (other.unitBuilderDataObj != null)
				return false;
		} else if (!unitBuilderDataObj.equals(other.unitBuilderDataObj))
			return false;
		return true;
	}
	private String columnName;
	
	public ColumnProperties(){

		dataType = new String();
		dataFormatCustom =  "G8";
		
		unitBuilderDataObj = new UnitBuilderData();
		columnName = new String();
		requiredDecimals = new String();
		optionalDecimals = 0;
	}

	public String getColumnName() {
		return columnName;
	}


	public void setColumnNames(String columnName) {
		this.columnName = columnName;
	}

	public UnitBuilderData getUnitBuilderDataObj() {
		return unitBuilderDataObj;
	}


	public void setUnitBuilderDataObj(UnitBuilderData unitBuilderDataObj) {
		this.unitBuilderDataObj = unitBuilderDataObj;
	}


	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	

	public String getDataFormatCustom() {
		return dataFormatCustom;
	}
	public void setDataFormatCustom(String dataFormatCustom) {
		this.dataFormatCustom = dataFormatCustom;
	}
	


}