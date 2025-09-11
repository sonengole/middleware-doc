package com.sbs.kyudo.middlewaredoc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.sbs.kyudo.middlewaredoc.client.ExternalSoapClient;

@Configuration
public class ExternalSoapCallConfig {
	
	@Value("${external.soap.context.path}")
	private String externalSoapContextPath;
	
	@Value("${default.external.soap.uri}")
	private String defaultExternalSoapUri;

	  @Bean
	  public Jaxb2Marshaller marshaller() {
	    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	    // this package must match the package in the <generatePackage> specified in
	    // pom.xml
	    marshaller.setContextPath(externalSoapContextPath);
	    return marshaller;
	  }
	  @Bean
	  public ExternalSoapClient externalSoapClient(Jaxb2Marshaller marshaller) {
	    ExternalSoapClient client = new ExternalSoapClient();
	    client.setDefaultUri(defaultExternalSoapUri);
	    client.setMarshaller(marshaller);
	    client.setUnmarshaller(marshaller);
	    return client;
	  }
}
