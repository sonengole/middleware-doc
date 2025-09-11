package com.sbs.kyudo.middlewaredoc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sbs.kyudo.middlewaredoc.exception.ExecuteKyudoActionServiceException;
import com.sbs.kyudo.middlewaredoc.service.ExecuteKyudoActionService;
import com.sbs.kyudo.middlewaredoc.wsdl.ExecuteKyudoActionInRequest;
import com.sbs.kyudo.middlewaredoc.wsdl.ExecuteKyudoActionResponse;

/*
 * Responsibilties:
 * 1- Accepts POST from with refopn/sessionID etc
 * 2- processes the request
 * 3- calls the KyudoExecuteAction CBS SOAP  webservice to execute the kyudo action on CBS BES
 */
@RestController
@RequestMapping("/api/kyudo")
public class ExecuteKyudoActionController {
	

	private ExecuteKyudoActionService executeKyudoActionService;
	

	public ExecuteKyudoActionController(ExecuteKyudoActionService executeKyudoActionService) {
		this.executeKyudoActionService = executeKyudoActionService;
	}
	
	@PostMapping("/action")
	public ResponseEntity<ExecuteKyudoActionResponse> triggerCBSSoapCall(@RequestBody ExecuteKyudoActionInRequest request){
		ExecuteKyudoActionResponse responseWrapper = new ExecuteKyudoActionResponse();
		//ExternalSoapApiCaller kyudoActionExecutionApiCaller = new KyudoActionExecutionApiCaller();
		responseWrapper = executeKyudoActionService.executeOperationCBS(request);
		
		if (responseWrapper.getExecuteKyudoActionOut() != null) {
			return ResponseEntity.ok(responseWrapper);
		}
		
		return ResponseEntity.ok(responseWrapper);
	}
	
	@ExceptionHandler(ExecuteKyudoActionServiceException.class)
	public ResponseEntity<Map<String,String>> handleException(ExecuteKyudoActionServiceException ex){
		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getMessage());
		
	    if (ex.getFaultCode() != null) {
	        error.put("faultCode", ex.getFaultCode());
	    }
	    if (ex.getFaultMessage() != null) {
	        error.put("faultMessage", ex.getFaultMessage());
	    }
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
		
	}


}
