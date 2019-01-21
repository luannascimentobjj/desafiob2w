package com.b2w.apistarwars.client;


import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.b2w.apistarwars.DesafioB2WRestfulWebApp;
import com.b2w.apistarwars.exception.ServiceUnavailable;
import com.b2w.apistarwars.models.ApiSwarWarsResult;
import com.b2w.apistarwars.models.StarWarsApiConstants;

@Service
public class StarWarsApiTemplate {
	
    
	protected static final Logger LOGGER = LoggerFactory.getLogger(DesafioB2WRestfulWebApp.class);
	
	public RestTemplate geraRestTeamplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	public ResponseEntity<ApiSwarWarsResult> returnAppears() {
		try { 
			return geraRestTeamplate().exchange(StarWarsApiConstants.URI, HttpMethod.GET,geraHeader(),ApiSwarWarsResult.class);
   		}catch(Exception e) {
   			throw new ServiceUnavailable(StarWarsApiConstants.SWAPI_INDISPONIVEL);
   		}
   	}
	
	public HttpEntity<String> geraHeader(){
		
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add(StarWarsApiConstants.USER_AGENT, StarWarsApiConstants.HEADER_TEMPLATE);
        HttpEntity<String> entity = new HttpEntity<String>(StarWarsApiConstants.PARAMETERS, headers);
		
		return entity;
	}
}
	    	

