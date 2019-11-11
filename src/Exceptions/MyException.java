package Exceptions;

public class MyException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg;
	
	public MyException() {
		msg="Standard exception!";
	}
	
	public MyException(String msg) {
		this.msg=msg;
	}
	
	public String toString() {
		return this.msg;
	}
}
