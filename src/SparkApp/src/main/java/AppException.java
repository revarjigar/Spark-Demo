package main.java;

public class AppException extends Exception{

	private static final long serialVersionUID = -2300766411249095676L;

	public AppException(String message){
	super(message);	
	}
	
	public AppException(String message, Throwable cause){
	super(message, cause);	
	}
	
}
