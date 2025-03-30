package com.test.emr.model;

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
public class Result {

	private Patients patients;
	private PatientsDTO patientsDTO;
	
}

