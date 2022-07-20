package com.example.smaato.restwebservicechallenge.httpclient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class RestClient {
	
	static final String URL_SCHEME = "http://localhost:8080";
	
	public void postcall(List<Integer> ids, String url) {
		String newUrl =  URL_SCHEME + url;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestMessage message = new RestMessage(ids.size(), ids);
			// HttpEntity<RestMessage>: To get result.
			HttpEntity<RestMessage> entity = new HttpEntity<RestMessage>(message,headers);

			// RestTemplate
			RestTemplate restTemplate = new RestTemplate();

			// Send request with POST method, and Headers.
			ResponseEntity<String> response = restTemplate.exchange(newUrl, //
					HttpMethod.POST, entity, String.class);

			HttpStatus statusCode = response.getStatusCode();
			System.out.println("Response Satus Code: " + statusCode);

			
		}

}
