package com.sbs.kyudo.middlewaredoc.token;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class CrelanKeycloakTokenReteieverKyudo {
	private static final String KEYCLOAK_SERVER_URL = "https://keycloak-dev.crelan-int.be/auth/realms/application";
	private static final String KEYCLOAK_CLIENT_ID = "app-akyu-kyudo";
	private static final String KEYCLOAK_CLIENT_SECRET = "893f7c6e-1e7d-4ffd-8630-fea85d89867a";
	private static final String KEYCLOAK_TOKEN_ENDPOINT = KEYCLOAK_SERVER_URL + "/protocol/openid-connect/token";
    public CrelanKeycloakToken retrieveNewAccessToken() throws IOException{
		
		System.out.println("CrelanKeycloakTokenReteieverKyudo --> KEYCLOAK_SERVER_URL : " + KEYCLOAK_SERVER_URL);
		System.out.println("CrelanKeycloakTokenReteieverKyudo --> KEYCLOAK_CLIENT_ID : " + KEYCLOAK_CLIENT_ID);
		System.out.println("CrelanKeycloakTokenReteieverKyudo --> KEYCLOAK_TOKEN_ENDPOINT : " + KEYCLOAK_TOKEN_ENDPOINT);
		

		
    	System.out.println("CrelanKeycloakTokenReteieverKyudo --> in retrieveNewAccessToken ");
    	CrelanKeycloakToken newToken = new CrelanKeycloakToken();

        String credentials = KEYCLOAK_CLIENT_ID + ":" + KEYCLOAK_CLIENT_SECRET;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(KEYCLOAK_TOKEN_ENDPOINT);

        // Set headers
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");

        // Set request parameters
        StringEntity params = new StringEntity("grant_type=client_credentials");
        httpPost.setEntity(params);

        // Execute the request
        HttpResponse response = httpClient.execute(httpPost);
        
        //get status code
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200){
        	// Read the OK response
        	System.out.println("CrelanKeycloakTokenReteieverKyudo --> NEW token retrieval : SUCESS");
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            String fullStringToken = result.toString();
            //System.out.println("ATSAFE-DEBUG -CrelanKeycloakTokenReteieverKyudo-------> full string token is : -->" + fullStringToken);
            JSONObject tokenJson = new JSONObject(fullStringToken);
            System.out.println("ATSAFE-DEBUG -CrelanKeycloakTokenReteieverKyudo-------> full string token is AFTER ");
            newToken.setAccessToken(tokenJson.getString("access_token"));
            newToken.setExpiresIn(tokenJson.get("expires_in").toString());
            newToken.setNotBeforePolicy(tokenJson.get("not-before-policy").toString());
            newToken.setRefreshExpiresIn(tokenJson.get("refresh_expires_in").toString());
            newToken.setScope(tokenJson.getString("scope"));
            newToken.setTokenType(tokenJson.getString("token_type"));
            
            
            System.out.println("CrelanKeycloakTokenReteieverKyudo -------> expires_in is  : -->" + newToken.getExpiresIn());
            System.out.println("CrelanKeycloakTokenReteieverKyudo -------> refresh_expires_in is  : -->" + newToken.getRefreshExpiresIn());
            System.out.println("CrelanKeycloakTokenReteieverKyudo -------> token_typeis  : -->" + newToken.getTokenType());
            System.out.println("CrelanKeycloakTokenReteieverKyudo -------> not-before-policy is  : -->" + newToken.getNotBeforePolicy());
            System.out.println("CrelanKeycloakTokenReteieverKyudo -------> scope is  : -->" + newToken.getScope());
            
            return newToken;
           
        }
        else {
        	System.out.println("CrelanKeycloakTokenReteiever --> KyudoNew Token retrieval : FAILURE");
        	return null;
        }

    }
}
