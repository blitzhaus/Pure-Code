package Common;

import org.codecompany.jeha.ExceptionHandler;
import org.codecompany.jeha.core.HandlerUtil;

class MyException extends Exception {

	int myRetVal;

	public int getMyRetVal() {
		return myRetVal;
	}

	public void setMyRetVal(int myRetVal) {
		this.myRetVal = myRetVal;
	}

	private String errorCode = new String();
	MyException(String errCode) {
		errorCode = errCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}

public class TestException 
{
	@ExceptionHandler(handler=MyHandler.class)
	private void throwIndexOutOfBoundsException()
	{
		try 
		{
			String[] arr = {};
			String arrItem = arr[1];
		} 
		catch (Exception ex) 
		{
			HandlerUtil.handle(ex);
		}
	}
	
	@ExceptionHandler(handler=MyHandler.class)
	public int throwCustomException(int errorCode)
	{
		int n;
		try 
		{
			throw new MyException(errorCode+"");
		} 
		catch (MyException ex) 
		{
			MyException ee = (MyException)HandlerUtil.handle(ex);
			n = ee.getMyRetVal();
		}
		return n;
	}
	
	private void throwExceptions()
	{
		throwIndexOutOfBoundsException();
		int rand = Math.round( (float) (Math.random()* 20));
		System.out.println("random number generated"+rand);
		throwCustomException(rand);
	}
} 
