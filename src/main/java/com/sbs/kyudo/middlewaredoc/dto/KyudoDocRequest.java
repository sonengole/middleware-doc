package com.sbs.kyudo.middlewaredoc.dto;

import java.util.Date;

public class KyudoDocRequest {
	  private String appCod;
	  
	  private String batchMode;
	  
	  private String cpyCod;
	  
	  private String externalbankingOpRef;
	  
	  private String internalbankingOpRef;
	  
	  private String languageCod;
	  
	  private String msgHeaderType;
	  
	  private String msgVersionNb;
	  
	  private String operationalPosId;
	  
	  private String structureEltId;
	  
	  private Date treatmentDt;
	  
	  private String treatmentTime;
	  
	  private String userId;
	  
	  private String documentType;
	  
	  private String documentName;
	  
	  private String thrdPtyId;
	  
	  private String clientId;
	  
	  private String contractProdRef;
	  
	  private String contractDistrRef;
	  
	  private String signerTransactionNumber;
	  
	  private String documentStatus;
	  
	  private Date startDate;
	  
	  private Date endDate;
	  
	  private Date expirationDate;
	  

	public KyudoDocRequest(String appCod, String batchMode, String cpyCod, String externalbankingOpRef,
			String internalbankingOpRef, String languageCod, String msgHeaderType, String msgVersionNb,
			String operationalPosId, String structureEltId, Date treatmentDt, String treatmentTime, String userId,
			String documentType, String documentName, String thrdPtyId, String clientId, String contractProdRef,
			String contractDistrRef, String signerTransactionNumber, String documentStatus, Date startDate,
			Date endDate, Date expirationDate) {
		super();
		this.appCod = appCod;
		this.batchMode = batchMode;
		this.cpyCod = cpyCod;
		this.externalbankingOpRef = externalbankingOpRef;
		this.internalbankingOpRef = internalbankingOpRef;
		this.languageCod = languageCod;
		this.msgHeaderType = msgHeaderType;
		this.msgVersionNb = msgVersionNb;
		this.operationalPosId = operationalPosId;
		this.structureEltId = structureEltId;
		this.treatmentDt = treatmentDt;
		this.treatmentTime = treatmentTime;
		this.userId = userId;
		this.documentType = documentType;
		this.documentName = documentName;
		this.thrdPtyId = thrdPtyId;
		this.clientId = clientId;
		this.contractProdRef = contractProdRef;
		this.contractDistrRef = contractDistrRef;
		this.signerTransactionNumber = signerTransactionNumber;
		this.documentStatus = documentStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.expirationDate = expirationDate;
	}

	public String getAppCod() {
		return appCod;
	}

	public void setAppCod(String appCod) {
		this.appCod = appCod;
	}

	public String getBatchMode() {
		return batchMode;
	}

	public void setBatchMode(String batchMode) {
		this.batchMode = batchMode;
	}

	public String getCpyCod() {
		return cpyCod;
	}

	public void setCpyCod(String cpyCod) {
		this.cpyCod = cpyCod;
	}

	public String getExternalbankingOpRef() {
		return externalbankingOpRef;
	}

	public void setExternalbankingOpRef(String externalbankingOpRef) {
		this.externalbankingOpRef = externalbankingOpRef;
	}

	public String getInternalbankingOpRef() {
		return internalbankingOpRef;
	}

	public void setInternalbankingOpRef(String internalbankingOpRef) {
		this.internalbankingOpRef = internalbankingOpRef;
	}

	public String getLanguageCod() {
		return languageCod;
	}

	public void setLanguageCod(String languageCod) {
		this.languageCod = languageCod;
	}

	public String getMsgHeaderType() {
		return msgHeaderType;
	}

	public void setMsgHeaderType(String msgHeaderType) {
		this.msgHeaderType = msgHeaderType;
	}

	public String getMsgVersionNb() {
		return msgVersionNb;
	}

	public void setMsgVersionNb(String msgVersionNb) {
		this.msgVersionNb = msgVersionNb;
	}

	public String getOperationalPosId() {
		return operationalPosId;
	}

	public void setOperationalPosId(String operationalPosId) {
		this.operationalPosId = operationalPosId;
	}

	public String getStructureEltId() {
		return structureEltId;
	}

	public void setStructureEltId(String structureEltId) {
		this.structureEltId = structureEltId;
	}

	public Date getTreatmentDt() {
		return treatmentDt;
	}

	public void setTreatmentDt(Date treatmentDt) {
		this.treatmentDt = treatmentDt;
	}

	public String getTreatmentTime() {
		return treatmentTime;
	}

	public void setTreatmentTime(String treatmentTime) {
		this.treatmentTime = treatmentTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getThrdPtyId() {
		return thrdPtyId;
	}

	public void setThrdPtyId(String thrdPtyId) {
		this.thrdPtyId = thrdPtyId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getContractProdRef() {
		return contractProdRef;
	}

	public void setContractProdRef(String contractProdRef) {
		this.contractProdRef = contractProdRef;
	}

	public String getContractDistrRef() {
		return contractDistrRef;
	}

	public void setContractDistrRef(String contractDistrRef) {
		this.contractDistrRef = contractDistrRef;
	}

	public String getSignerTransactionNumber() {
		return signerTransactionNumber;
	}

	public void setSignerTransactionNumber(String signerTransactionNumber) {
		this.signerTransactionNumber = signerTransactionNumber;
	}

	public String getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


}
