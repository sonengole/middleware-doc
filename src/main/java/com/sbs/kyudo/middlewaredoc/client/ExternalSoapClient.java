package com.sbs.kyudo.middlewaredoc.client;

import javax.xml.transform.Source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.SoapFaultClientException;

import com.sbs.kyudo.middlewaredoc.exception.ExecuteKyudoActionServiceException;
import com.sbs.kyudo.middlewaredoc.wsdl.CWFaultInfo;
import com.sbs.kyudo.middlewaredoc.wsdl.CWRMFaultInfo;
import com.sbs.kyudo.middlewaredoc.wsdl.CWTechnicalFaultInfo;
import com.sbs.kyudo.middlewaredoc.wsdl.CwContext;
import com.sbs.kyudo.middlewaredoc.wsdl.CwReliability;
import com.sbs.kyudo.middlewaredoc.wsdl.CwSecurity;
import com.sbs.kyudo.middlewaredoc.wsdl.ExecuteKyudoAction;
import com.sbs.kyudo.middlewaredoc.wsdl.ExecuteKyudoActionInRequest;
import com.sbs.kyudo.middlewaredoc.wsdl.ExecuteKyudoActionResponse;

import jakarta.xml.bind.JAXBElement;

@Component
public class ExternalSoapClient extends WebServiceGatewaySupport {

  private static final Logger log = LoggerFactory.getLogger(ExternalSoapClient.class);
  
  @Value("${kyudo.action.execution.soap.uri}")
  private String kyudoActionExecutionSoapURI;
  
  @Value("${kyudo.action.execution.soap.cwuser}")
  private String cwUser;
  
  @Value("${kyudo.action.execution.soap.cwpassword}")
  private String cwPassword;
  
  @Value("${kyudo.action.execution.soap.cwchannel}")
  private String cwChannel;
  
  @Value("${kyudo.action.execution.soap.cwcompany}")
  private String cwCompany;
  
  @Value("${kyudo.action.execution.soap.cwlanguage}")
  private String cwLanguage;
  
  //private ExecuteKyudoActionServiceException parseSoapFault (S)
  private ExecuteKyudoActionServiceException parseSoapFault(SoapFaultClientException sfce) {
	    SoapFaultDetail detail = sfce.getSoapFault().getFaultDetail();

	    if (detail != null && detail.getDetailEntries().hasNext()) {
	        try {
	            Source source = detail.getDetailEntries().next().getSource();
	            Object faultObj = getUnmarshaller().unmarshal(source);

	            if (faultObj instanceof JAXBElement) {
	                Object value = ((JAXBElement<?>) faultObj).getValue();

	                if (value instanceof CWFaultInfo ) {
	                	CWFaultInfo cf = (CWFaultInfo) value;
	                    return new ExecuteKyudoActionServiceException(
	                            "Execute Kyudo Action service SOAP fault: " + cf.getErrorMessage(),
	                            cf.getErrorMessageId(),
	                            cf.getErrorMessage());
	                } else if (value instanceof CWRMFaultInfo ) {
	                	CWRMFaultInfo rmf = (CWRMFaultInfo) value;
	                    return new ExecuteKyudoActionServiceException(
	                            "Execute Kyudo Action service SOAP reliability fault: " + rmf.getErrorMessage(),
	                            rmf.getErrorMessageId(),
	                            rmf.getErrorMessage());
	                } else if (value instanceof CWTechnicalFaultInfo ) {
	                	CWTechnicalFaultInfo tech = (CWTechnicalFaultInfo) value;
	                    return new ExecuteKyudoActionServiceException(
	                            "Execute Kyudo Action service SOAP technical fault: " + tech.getErrorMessage(),
	                            tech.getErrorMessageId(),
	                            tech.getErrorMessage());
	                }
	            }
	        } catch (Exception ex) {
	            log.warn("Failed to parse SOAP fault detail", ex);
	        }
	    }

	    // fallback if no detail parsed
	    return new ExecuteKyudoActionServiceException("SOAP fault from Execute Kyudo Action Service", sfce);
	}

  public ExecuteKyudoActionResponse executeKyudoAction(ExecuteKyudoActionInRequest request) {

    log.info("Soap request: agentUnumber : " );
    ExecuteKyudoAction requestWrapper = new ExecuteKyudoAction();
/*    ExecuteKyudoActionInRequest request = new ExecuteKyudoActionInRequest();
    request.setAgentUnumber(agentUnumber);
    request.setCallMode(callMode);
    request.setClientNumber(clientNumber);
    request.setNatureOfOperationKyudo(natureOfOperationKyudo);
    request.setOperationReferenceKyudo(operationReferenceKyudo);
    request.setSessionReferenceKyudo(sessionReferenceKyudo);
    request.setTpNameKyudo(tpNameKyudo);*/
    requestWrapper.setExecuteKyudoActionIn(request);
    
    CwSecurity security = new CwSecurity();
    security.setCWUser(cwUser);
    security.setCWPassword(cwPassword);
    security.setCWChannel(cwChannel);
    security.setCWCompany(cwCompany);
    security.setCWLanguage(cwLanguage);
    
    CwReliability reliability = new CwReliability();
    reliability.setCWCancellationSwitch(false);
    
    
    CwContext context = new CwContext();
    context.setCWNewUpdateReference(false);
    
    
    try {
        ExecuteKyudoActionResponse response = (ExecuteKyudoActionResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                		kyudoActionExecutionSoapURI,
                		requestWrapper,
                        message -> {
                            SoapHeader header = ((SoapMessage) message).getSoapHeader();
                            getMarshaller().marshal(security, header.getResult());
                            getMarshaller().marshal(reliability, header.getResult());
                            getMarshaller().marshal(context, header.getResult());
                        });

            return response;
    	
    } catch(SoapFaultClientException sfce) {
    	
    	log.error("SOAP Fault occured: {}", sfce.getFaultStringOrReason(), sfce);
    	throw parseSoapFault(sfce);
    	
    } catch (WebServiceIOException wioe) {
    	log.error("I/O error while communicating with CBS execute Kyudo action Service: {}", wioe.getMessage(), wioe);
    	throw new ExecuteKyudoActionServiceException("Connection error when calling CBS execute Kyudo action Service", wioe);
    	
    } catch (Exception e) {
    	log.error("unexpected error: {}", e.getMessage(),e);
    	throw new ExecuteKyudoActionServiceException("Unexpected error when calling CBS execute Kyudo action Service", e);
    }
    
   
    


  }

}
