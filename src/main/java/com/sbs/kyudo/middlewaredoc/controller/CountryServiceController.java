package com.sbs.kyudo.middlewaredoc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.kyudo.middlewaredoc.example1.GetCountryRequest;
import com.sbs.kyudo.middlewaredoc.example1.GetCountryResponse;
import com.sbs.kyudo.middlewaredoc.service.CountryService;

@RestController
@RequestMapping("/api/country")
public class CountryServiceController {
	
	private CountryService countryService;
	
	public CountryServiceController( CountryService countryService) {
		this.countryService=countryService;
	}
	
	@PostMapping("/info")
	public ResponseEntity<GetCountryResponse> triggerSoap(@RequestBody GetCountryRequest request){
		GetCountryResponse response = countryService.callSoapCBSService(request);
		
		return ResponseEntity.ok(response);
	}
	

}
