package com.test.emr.model;

import java.time.LocalDateTime;
import java.util.List;

import com.test.emr.entity.MedicalRecord;

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
public class PatientsDTO {

	private Long id;
	
    private String name; 
    private String birthdate; 
    private String gender;
    private String contact;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    private Integer age;
    private String genderKR;
    
    private List<MedicalRecord> medicalRecords;
	
}

