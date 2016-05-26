package Common;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.codecompany.jeha.core.Handler;
import org.codecompany.jeha.populator.Populator;

import view.DDViewLayer;

import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;

import jxl.read.biff.BiffException;

public class MyHandler extends JFrame implements Handler 
{
	Hashtable errIdAndMessages = new Hashtable();
	
	public MyHandler()
	{


	    loadErrorCodes(); 
	}
	
	@Override
	public Populator getPopulator() 
	{
		// TODO Auto-generated method stub
		ErrorCodeReader errCodeReader = new ErrorCodeReader("errorcodes.txt");
		errIdAndMessages = errCodeReader.getErrorMessageDetails();
		return null;
	}
	
	public void loadErrorCodes() 
	{
		// TODO Auto-generated method stub
		ErrorCodeReader errCodeReader = new ErrorCodeReader("errorcodes.txt");
		errIdAndMessages = errCodeReader.getErrorMessageDetails();
	}
	
	
	public Throwable handle(Throwable arg, Object...obj)
	{
		MyException arg1 = (MyException) arg;
		System.out.println("CustomException Occured!"+arg1.getErrorCode());
		
		//if(errorCode)
		
		String errorMessage = (String) errIdAndMessages.get(arg1.getErrorCode());
		System.out.println("Error Detail  ...."+errorMessage);
		int n = 0;
		if(Integer.parseInt(arg1.getErrorCode()) < 9){
			JOptionPane.showMessageDialog(DDViewLayer.createViewLayerInstance(), errorMessage, "Error", JOptionPane.OK_OPTION);
		} else{
			n = JOptionPane.showConfirmDialog(DDViewLayer.createViewLayerInstance(), errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		arg1.setMyRetVal(n);
		return arg1;
	}

	public Throwable handle(IOException arg,Object obj){
		JOptionPane.showMessageDialog(DDViewLayer.createViewLayerInstance(), arg.getMessage(), "Error", JOptionPane.OK_OPTION);
	return arg;	
	}
	
	public Throwable handle(IOError arg, Object obj){
		JOptionPane.showMessageDialog(DDViewLayer.createViewLayerInstance(), arg.getMessage(), "Error", JOptionPane.OK_OPTION);
		return arg;
	}
	
	public Throwable handle(NullPointerException arg, Object obj) 
	{
		System.out.println("NullPointerException Occured!");
		return arg;
	}
	public Throwable handle(IndexOutOfBoundsException arg, Object obj) 
	{
			System.out.println("IndexOutOfBoundsException Occured");
			return arg;
	} 
	
	public Throwable handle(NumberFormatException arg, Object obj){
		
		String errorMessage = "DDP cannot unable to convert input to number :: User action will terminalte";
		JOptionPane.showMessageDialog(this, errorMessage, "Number format Exception", JOptionPane.ERROR_MESSAGE);
		return arg;
	}
	
	public Throwable handle(StackOverflowError arg, Object obj){
		String errorMessage = arg.getMessage() + "Memory insufficient :: Please contact administrator";
		JOptionPane.showMessageDialog(this, errorMessage, "Memory not sufficient", JOptionPane.ERROR_MESSAGE);
		return arg;
	}
	
	public Throwable handle(FileNotFoundException arg, Object obj){
		String errorMessage = "File not found :: Please provide a valid file name";
		JOptionPane.showMessageDialog(this, errorMessage, "File not found", JOptionPane.ERROR_MESSAGE);
		return arg;
	}
	
	public Throwable handle(BiffException arg, Object obj){
		String errorMessage = arg.getMessage();
		JOptionPane.showMessageDialog(this, errorMessage, "Problem reading the file", JOptionPane.ERROR_MESSAGE);
		return arg;
	}
	
} 
