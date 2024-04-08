package com.iting.subsp.service;

import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ReqPaymentScheduler {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${IAMPORT-KEY}")
	private String apiKey;
	
	String paymentId =  "iting_1month_" + new Date().getTime();
	
	public HttpEntity<String> test() {
		
		// URL 생성
		URI url = UriComponentsBuilder
				.fromUriString("https://api.portone.io")
				.path("/payments/{paymentId}/billing-key")
				.encode()
				.build()
				.expand(paymentId) // {paymentId}에 들어갈 값을 순차적으로 입력
				.toUri();
		
		
		// 서버로 요청할 Header
		HttpHeaders header = new HttpHeaders();
		
		header.setContentType(MediaType.APPLICATION_JSON); // content-type
		header.add("Authorization", "PortOne " + apiKey); 	// 인증키
		
		// Body set
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("billingKey", "billing-key-018ead6e-c7e4-dc67-ddc8-7da9c246a61a");
		map.put("orderName", "아이팅 1개월 구독권 2회차 결제");
		
		map.put("currency", "KRW");
		map.put("amount", Collections.singletonMap("total", 39800));

		
		// Message
        HttpEntity<Map<String, Object>> requestMessage = new HttpEntity<>(map, header);	
		
        // Request
        HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);
	
	    return response;
	}
	
	
	
	
}
