package com.sbs.kyudo.middlewaredoc.service;

import org.springframework.stereotype.Service;

import com.sbs.kyudo.middlewaredoc.client.CountryClient;
import com.sbs.kyudo.middlewaredoc.example1.GetCountryRequest;
import com.sbs.kyudo.middlewaredoc.example1.GetCountryResponse;

@Service
public class CountryService {
	
	private  CountryClient countryClient;
	
	public CountryService(CountryClient countryClient) {
		this.countryClient = countryClient;
	}
	
	public GetCountryResponse callSoapCBSService(GetCountryRequest request) {
		  GetCountryResponse response = null;
		
		try {
			 response = countryClient.getCountry(request);
			return response;
		} catch (Exception e) {
			return response; 
		}
	}
	

}
