package com.sbs.kyudo.middlewaredoc.exception;

public class SendKyudoDocumentServiceException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SendKyudoDocumentServiceException(String message) {
		super(message);
		
	}

	public SendKyudoDocumentServiceException(String message, Throwable cause) {
		super(message, cause);
	
	}


}
