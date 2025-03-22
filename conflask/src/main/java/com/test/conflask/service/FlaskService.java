package com.test.conflask.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.conflask.model.RequestSendToFlaskDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlaskService {
	
	
	//데이터를 JSON 객체로 변환하기 위해 사용 
	private final ObjectMapper objectMapper;
	
	public String sendToFlask(RequestSendToFlaskDto dto) throws JsonProcessingException {

		
		/*
		 	RestTemplate 이란, Spring에서 지원하는 객체로 REST API를 호출할 수 있습니다.
			Spring 3 이상부터 사용할 수 있으며 동기, 비동기 REST Client을 제공합니다.
		 */
		RestTemplate restTemplate = new RestTemplate();
		
		//헤더를 JSON으로 설정함 
		HttpHeaders headers = new HttpHeaders();
		
		//파라미터로 들어온 dto를 JSON 객체로 변환 
		//Content-Type: application/json을 설정하여 JSON 형식의 데이터를 전송하도록 지정
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//DTO 객체를 JSON 문자열로 변환
		//objectMapper > dto 객체를 JSON으로 변환하기 위한 jackson 라이브러리 
		//변환된 JSON 예시 > {"name":"홍길동","tel":"010-1234-1234"}
		String param = objectMapper.writeValueAsString(dto);
		
		//HttpEntity를 사용하여 요청 본문(Body)과 헤더를 함께 전달
		//param: JSON으로 변환된 데이터
		//headers: Content-Type: application/json 포함
		HttpEntity<String> entity = new HttpEntity<String>(param, headers);
		
		//실제 Flask 서버와 연결하기 위한 URL 
		String url = "http://127.0.0.1:8082/receive_string";
		
		//Flask 서버로 데이터를 전송하고 받은 응답 값을 return 
		//postForObject()를 사용하여 Flask 서버로 POST 요청을 보냄 
		//url, entity: 요청 본문 + 헤더 
		//String.class: 응답을 String 타입으로 받음 
		return restTemplate.postForObject(url, entity, String.class);
	}

}











