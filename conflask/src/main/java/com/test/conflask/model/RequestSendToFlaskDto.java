package com.test.conflask.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestSendToFlaskDto {
	
	private final Integer id;
	private final String name;
	private final String tel;

}
