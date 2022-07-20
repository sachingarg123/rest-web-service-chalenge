package com.example.smaato.restwebservicechallenge.exception;

public class ApplicationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ApplicationException(String errorMessage) {
        super(errorMessage);
    }

	public ApplicationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
