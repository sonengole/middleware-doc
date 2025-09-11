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

import com.sbs.kyudo.middlewaredoc.dto.KyudoDocResponse;
import com.sbs.kyudo.middlewaredoc.dto.KyudoToMiddleWareDocRequest;
import com.sbs.kyudo.middlewaredoc.exception.SendKyudoDocumentServiceException;
import com.sbs.kyudo.middlewaredoc.service.SendKyudoDocumentService;

@RestController
@RequestMapping("/api/kyudo")
public class SendKyudoDocumentController {
	
	private SendKyudoDocumentService sendKyudoDocumentService;;
	
	public SendKyudoDocumentController(SendKyudoDocumentService sendKyudoDocumentService) {
		this.sendKyudoDocumentService = sendKyudoDocumentService;
	}
	
	@PostMapping("/signature")
	public ResponseEntity<KyudoDocResponse> uploadPdf(@RequestBody KyudoToMiddleWareDocRequest request) {
		  KyudoDocResponse response = sendKyudoDocumentService.sendDocumentToCrelan(request);
		    return ResponseEntity.ok(response);  
	  }
	
	@ExceptionHandler(SendKyudoDocumentServiceException.class)
	public ResponseEntity<Map<String,String>> handleException(SendKyudoDocumentServiceException ex){
		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
		
	}

}
