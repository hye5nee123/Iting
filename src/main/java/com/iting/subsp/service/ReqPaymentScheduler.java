package com.iting.subsp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReqPaymentScheduler {
	public String getData(String url) {
		// RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		// 서버로 요청할 Header
		HttpHeaders header = new HttpHeaders();
		//@Value("${IAMPORT-KEY}")
		//private String IAMPORT-KEY;
		header.setContentType(MediaType.APPLICATION_JSON);
		header.add("Authorization", "PortOne HlqMtdjNfDt6fEhGksazVWdRpTLby0yFlMjjm7R30SalvLd6CX4uY7JjsgxOfetFAfqNYUfaS2I3W4gu");
		
		
		return "";
	}
	
}
