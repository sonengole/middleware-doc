package com.sbs.kyudo.middlewaredoc.service;

import org.springframework.stereotype.Service;

import com.sbs.kyudo.middlewaredoc.client.ExternalRestClient;
import com.sbs.kyudo.middlewaredoc.dto.KyudoDocResponse;
import com.sbs.kyudo.middlewaredoc.dto.KyudoToMiddleWareDocRequest;
import com.sbs.kyudo.middlewaredoc.dto.MiddleWareToCrelanDocRequest;

@Service
public class SendKyudoDocumentService {
	
	private ExternalRestClient externalRestClient;
	
	public SendKyudoDocumentService(ExternalRestClient externalRestClient) {
		this.externalRestClient = externalRestClient;
	}
	
	public KyudoDocResponse sendDocumentToCrelan (KyudoToMiddleWareDocRequest request) {
		MiddleWareToCrelanDocRequest requestToCrelan = new MiddleWareToCrelanDocRequest();
		KyudoDocResponse response = new KyudoDocResponse();
		requestToCrelan.setDocumentBase64Pdf(request.getDocumentBase64()); 
		requestToCrelan.setAgentUnumber(request.getAgentUnumber());
		requestToCrelan.setClientNumber(request.getClientNumber());
		requestToCrelan.setLanguage(request.getLanguage());
		requestToCrelan.setNatureOfOperationKyudo(request.getNatureOfOperationKyudo());
		requestToCrelan.setOperationReferenceKyudo(request.getOperationReferenceKyudo());
		requestToCrelan.setSessionReferenceKyudo(request.getSessionReferenceKyudo());
		requestToCrelan.setThirdPartyNumber(request.getThirdPartyNumber());
		
		
		response = externalRestClient.sendDocument(requestToCrelan);
		return response;
	}
	
	
		// transform KyudoToMiddleWareDocRequest to MiddlewareTCrelan Request
		

}
