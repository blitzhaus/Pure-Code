package view;

import java.io.File;
import java.io.Serializable;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.read.biff.PasswordException;

public class MyWorkBook extends Workbook implements Serializable {

	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Range[] findByName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell findCellByName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell getCell(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfSheets() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getRangeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sheet getSheet(int arg0) throws IndexOutOfBoundsException {
		//super.getWorkbook(new File("")).gets
		return null;
	}

	@Override
	public Sheet getSheet(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getSheetNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sheet[] getSheets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isProtected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void parse() throws BiffException, PasswordException {
		// TODO Auto-generated method stub
		
	}
	

}
