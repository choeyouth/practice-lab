package com.test.conflask.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true) // 기본 생성자 강제 생성
@RequiredArgsConstructor
public class RequestSendToFlaskDto {
	
	private final Integer id;
	private final String name;
	private final String tel;

}
