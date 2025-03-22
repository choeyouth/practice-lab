package com.test.conflask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.conflask.model.RequestSendToFlaskDto;
import com.test.conflask.service.FlaskService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FlaskController {

	private final FlaskService flaskService;
	
	@GetMapping("/flask")
	public String showContactForm() {
		return "contactForm";
	}
	
	@PostMapping("/flask")
	@ResponseBody
	public String sendToFlask(@RequestBody RequestSendToFlaskDto dto) throws JsonProcessingException {
	    return flaskService.sendToFlask(dto);  
	}

}
