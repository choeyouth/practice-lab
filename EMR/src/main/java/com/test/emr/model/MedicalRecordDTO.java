package com.test.emr.model;

import java.time.LocalDateTime;

import com.test.emr.entity.Patients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDTO {

	private Long id;
    private String diagnosis; // 진단 내용
    private String treatment; // 치료 내용
    private String recordDate; // 진료 날짜
    private LocalDateTime createdAt; // 생성일시
    private LocalDateTime updatedAt; // 수정일시
    private Patients patients;
	
}
