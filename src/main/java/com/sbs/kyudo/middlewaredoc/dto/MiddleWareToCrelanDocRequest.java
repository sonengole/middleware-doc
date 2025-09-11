package com.sbs.kyudo.middlewaredoc.dto;

public class MiddleWareToCrelanDocRequest {
	private String agentUnumber;
	private String callMode;
	private String clientNumber;
	private String natureOfOperationKyudo;
	private String operationReferenceKyudo;
	private String sessionReferenceKyudo;
	private String DocumentBase64Pdf;
	private String thirdPartyNumber;
	private String language;
	
	public String getThirdPartyNumber() {
		return thirdPartyNumber;
	}
	public void setThirdPartyNumber(String thirdPartyNumber) {
		this.thirdPartyNumber = thirdPartyNumber;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getAgentUnumber() {
		return agentUnumber;
	}
	public void setAgentUnumber(String agentUnumber) {
		this.agentUnumber = agentUnumber;
	}
	public String getCallMode() {
		return callMode;
	}
	public void setCallMode(String callMode) {
		this.callMode = callMode;
	}
	public String getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	public String getNatureOfOperationKyudo() {
		return natureOfOperationKyudo;
	}
	public void setNatureOfOperationKyudo(String natureOfOperationKyudo) {
		this.natureOfOperationKyudo = natureOfOperationKyudo;
	}
	public String getOperationReferenceKyudo() {
		return operationReferenceKyudo;
	}
	public void setOperationReferenceKyudo(String operationReferenceKyudo) {
		this.operationReferenceKyudo = operationReferenceKyudo;
	}
	public String getSessionReferenceKyudo() {
		return sessionReferenceKyudo;
	}
	public void setSessionReferenceKyudo(String sessionReferenceKyudo) {
		this.sessionReferenceKyudo = sessionReferenceKyudo;
	}
	public String getDocumentBase64Pdf() {
		return DocumentBase64Pdf;
	}
	public void setDocumentBase64Pdf(String documentBase64Pdf) {
		DocumentBase64Pdf = documentBase64Pdf;
	}
	
	
	

}
