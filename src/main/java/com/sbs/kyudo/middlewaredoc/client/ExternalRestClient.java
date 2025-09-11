package com.sbs.kyudo.middlewaredoc.client;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.sbs.kyudo.middlewaredoc.dto.KyudoDocResponse;
import com.sbs.kyudo.middlewaredoc.dto.MiddleWareToCrelanDocRequest;
import com.sbs.kyudo.middlewaredoc.exception.SendKyudoDocumentServiceException;
import com.sbs.kyudo.middlewaredoc.token.CrelanKeycloakToken;
import com.sbs.kyudo.middlewaredoc.token.CrelanKeycloakTokenManagerKyudo;

@Component
public class ExternalRestClient {
	
	private RestTemplate restTemplate;
	private CrelanKeycloakTokenManagerKyudo crelanKeycloakTokenManagerKyudo;
	private static final String CRELAN_DOCUMENT_API_URL = "hhhhh";
	private static final long CRELAN_DOCUMENT_API_CALL_CONNECTION_TIMEOUT_SECONDS = 3;
	private static final long CRELAN_DOCUMENT_API_CALL_READ_TIMEOUT_SECONDS = 10;

	

	
	
	
	public ExternalRestClient(RestTemplateBuilder builder, CrelanKeycloakTokenManagerKyudo crelanKeycloakTokenManagerKyudo) {
		this.restTemplate = builder
				.setConnectTimeout(Duration.ofSeconds(CRELAN_DOCUMENT_API_CALL_CONNECTION_TIMEOUT_SECONDS))
		        .setReadTimeout(Duration.ofSeconds(CRELAN_DOCUMENT_API_CALL_READ_TIMEOUT_SECONDS))
		        .build();
		this.crelanKeycloakTokenManagerKyudo = crelanKeycloakTokenManagerKyudo;
		
		
	}
	
	
	
	public KyudoDocResponse sendDocument(MiddleWareToCrelanDocRequest request) {
		// retrieve access token from crelan Keycloak
    	String accessToken = null;
		CrelanKeycloakToken crelanKeycloakToken = crelanKeycloakTokenManagerKyudo.getAccessToken();
		accessToken = crelanKeycloakToken.getAccessToken();
		
		//build the header to make the call
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		HttpEntity<MiddleWareToCrelanDocRequest> entity = new HttpEntity<>(request, headers);
		//KyudoDocResponse response = new KyudoDocResponse();
		try {
			ResponseEntity<KyudoDocResponse> response = restTemplate.postForEntity(CRELAN_DOCUMENT_API_URL, entity, KyudoDocResponse.class);
			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				return response.getBody();
			} else {
				throw new SendKyudoDocumentServiceException("unexpected response status: " + response.getStatusCode());
			}

			
		} catch (HttpClientErrorException  httpClEx) {
			throw new SendKyudoDocumentServiceException("API returned error: " + httpClEx.getStatusCode() +
                    " - " + httpClEx.getResponseBodyAsString(), httpClEx);
			
		} catch (HttpServerErrorException  httpSvEx) {
			throw new SendKyudoDocumentServiceException("API returned error: " + httpSvEx.getStatusCode() +
                    " - " + httpSvEx.getResponseBodyAsString(), httpSvEx);
		} catch (ResourceAccessException ioEx) {
			throw new SendKyudoDocumentServiceException("Connection error: " + ioEx.getMessage(), ioEx);
		} catch (Exception ex) {
			throw new SendKyudoDocumentServiceException("Unexpected error: " + ex.getMessage(), ex);
		}
		
		
	}

}
