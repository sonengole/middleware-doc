package com.sbs.kyudo.middlewaredoc.service;


import org.springframework.stereotype.Service;

import com.sbs.kyudo.middlewaredoc.client.ExternalSoapClient;
import com.sbs.kyudo.middlewaredoc.wsdl.ExecuteKyudoActionInRequest;
import com.sbs.kyudo.middlewaredoc.wsdl.ExecuteKyudoActionResponse;

@Service
public class ExecuteKyudoActionService {
	
	private  ExternalSoapClient externalSoapClient;
	
	public ExecuteKyudoActionService(ExternalSoapClient externalSoapClient) {
		this.externalSoapClient = externalSoapClient;
	}
	
	public ExecuteKyudoActionResponse executeOperationCBS(ExecuteKyudoActionInRequest request) {
		ExecuteKyudoActionResponse response = new ExecuteKyudoActionResponse();
		
	    response = externalSoapClient.executeKyudoAction(request);
	    return response;

	}
	

}