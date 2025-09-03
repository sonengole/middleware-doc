package com.sbs.kyudo.middlewaredoc.exception;

public class KyudoDocErrorResponse {
	private int status;
	private String message;
	private String messageNumber;
	private long timestamp;
	
	public KyudoDocErrorResponse() {
		
	}

	public KyudoDocErrorResponse(int status, String message, String messageNumber, long timestamp) {
		this.status = status;
		this.message = message;
		this.messageNumber = messageNumber;
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(String messageNumber) {
		this.messageNumber = messageNumber;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
