package com.banking.exceptions;

public class InvalidPinNumberException extends Exception{

	public InvalidPinNumberException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidPinNumberException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidPinNumberException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidPinNumberException() {
		super();
		// TODO Auto-generated constructor stub
	}

}
