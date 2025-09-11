package com.sbs.kyudo.middlewaredoc.exception;

public class ExecuteKyudoActionServiceException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String faultCode;
	private final String faultMessage;
	
	public ExecuteKyudoActionServiceException(String message) {
		super(message);
		this.faultCode = null;
		this.faultMessage = null;
	}
	
	public ExecuteKyudoActionServiceException(String message, String faultCode, String faultMessage) {
		super(message);
		this.faultCode = faultCode;
		this.faultMessage = faultMessage;
	}
	
	public ExecuteKyudoActionServiceException (String message, Throwable cause) {
		super(message, cause);
		this.faultCode = null;
		this.faultMessage = null;
	}
    public String getFaultCode() {
        return faultCode;
    }

    public String getFaultMessage() {
        return faultMessage;
    }
}
