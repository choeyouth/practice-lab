package com.test.emr.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.emr.entity.MedicalRecord;
import com.test.emr.entity.Patients;
import com.test.emr.model.PatientsDTO;
import com.test.emr.repository.EMRCustomRepository;
import com.test.emr.repository.MedicalRecordRepository;
import com.test.emr.repository.PatientsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmrService {

	private final EMRCustomRepository emrCustomRepository;
	private final PatientsRepository patientsRepository;
	private final MedicalRecordRepository medicalRecordRepository;
	private final RestTemplate restTemplate;
	
	public Patients savePatient(Patients patient) {
		return patientsRepository.save(patient);
	}
	
	public Patients processAndSavePatient(PatientsDTO patientsDTO) throws JsonProcessingException {
		
		// FlaskAPI 호출
		String flaskUrl = "http://127.0.0.1:5050/process_patient";
		
		ResponseEntity<Map> response = restTemplate.exchange(
						flaskUrl, 
						HttpMethod.POST, 
						new HttpEntity<>(patientsDTO, createHeaders()), 
						Map.class
					);

		// Flask에서 전처리된 데이터를 Patients 갹채로 변환
		Map<String, Object> processedData = response.getBody();

		// PatientDTO 또는 processedData를 Patients 객체로 변환
	    String name = (String) processedData.get("name");
	    String birthdate = (String) processedData.get("birthdate");
	    String gender = (String) processedData.get("gender");
	    String contact = (String) processedData.get("contact");
	    Integer age = (Integer) processedData.get("age");

	    // createdAt과 updatedAt은 서버에서 처리되므로 따로 지정하지 않아도 됩니다.
	    Patients newPatient = Patients.builder()
	            .name(name)
	            .birthdate(birthdate)
	            .gender(gender)
	            .contact(contact)
	            .build();
	    
		return savePatient(newPatient);
	}
	
	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	public List<Patients> getPatients() {
		return patientsRepository.findAll();
	}

	public List<MedicalRecord> getPatientById(Long id) {
		List<MedicalRecord> list = emrCustomRepository.findById(id);
		ResponseEntity<Map> response = getProcessPatient(list);
		return list;
	}

	private ResponseEntity<Map> getProcessPatient(List<MedicalRecord> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
