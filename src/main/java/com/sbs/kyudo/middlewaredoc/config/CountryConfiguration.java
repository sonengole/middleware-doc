package com.sbs.kyudo.middlewaredoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.sbs.kyudo.middlewaredoc.client.CountryClient;

@Configuration
public class CountryConfiguration {
	
	  @Bean
	  public Jaxb2Marshaller marshaller() {
	    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	    // this package must match the package in the <generatePackage> specified in
	    // pom.xml
	    marshaller.setContextPath("com.sbs.kyudo.middlewaredoc.example1");
	    return marshaller;
	  }
	  @Bean
	  public CountryClient countryClient(Jaxb2Marshaller marshaller) {
	    CountryClient client = new CountryClient();
	    client.setDefaultUri("http://localhost:8091/ws");
	    client.setMarshaller(marshaller);
	    client.setUnmarshaller(marshaller);
	    return client;
	  }

}
