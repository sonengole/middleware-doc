package com.sbs.kyudo.middlewaredoc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.kyudo.middlewaredoc.dto.ExecuteKyudoActionRequest;
import com.sbs.kyudo.middlewaredoc.dto.ExecuteKyudoActionResponse;
import com.sbs.kyudo.middlewaredoc.exception.ExecuteKyudoActionErrorResponse;
import com.sbs.kyudo.middlewaredoc.exception.ExecuteKyudoActionFailureException;
import com.sbs.kyudo.middlewaredoc.externalservice.caller.ExternalSoapApiCaller;
import com.sbs.kyudo.middlewaredoc.externalservice.caller.impl.KyudoActionExecutionApiCaller;

/*
 * Responsibilties:
 * 1- Accepts POST from with refopn/sessionID etc
 * 2- processes the request
 * 3- calls the KyudoExecuteAction CBS SOAP  webservice to execute the kyudo action on CBS BES
 */
@RestController
@RequestMapping("/api/execute")
public class ExecuteKyudoActionController {
	
	private final ExternalSoapApiCaller externalSoapApiCaller;
	
	public ExecuteKyudoActionController(ExternalSoapApiCaller externalSoapApiCaller ) {
		this.externalSoapApiCaller = externalSoapApiCaller;
	}
	
	@PostMapping("/session")
	public ResponseEntity<ExecuteKyudoActionResponse> triggerCBSSoapCall(){
		ExecuteKyudoActionRequest kyudoActionRequest = new ExecuteKyudoActionRequest();
		ExecuteKyudoActionResponse kyudoActionResponse = new ExecuteKyudoActionResponse();
		ExternalSoapApiCaller kyudoActionExecutionApiCaller = new KyudoActionExecutionApiCaller();
		kyudoActionResponse = kyudoActionExecutionApiCaller.callExternalSoapApi(kyudoActionRequest);
		
		return ResponseEntity.ok(kyudoActionResponse);
	}
	
	@ExceptionHandler
	public ResponseEntity<ExecuteKyudoActionErrorResponse> handleException(ExecuteKyudoActionFailureException exc){
		
		
		return null;
	}


}
