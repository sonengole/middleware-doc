package com.sbs.kyudo.middlewaredoc.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.sbs.kyudo.middlewaredoc.example1.GetCountryRequest;
import com.sbs.kyudo.middlewaredoc.example1.GetCountryResponse;

@Component
public class CountryClient extends WebServiceGatewaySupport {

  private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

  public GetCountryResponse getCountry(GetCountryRequest request) {

    //GetCountryRequest request = new GetCountryRequest();
    //request.setName(request.getName());

    log.info("Requesting location for " + request.getName());

    GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
        .marshalSendAndReceive("http://localhost:8080/ws", request);

    return response;
  }

}