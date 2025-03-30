package com.test.emr.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.emr.entity.MedicalRecord;
import com.test.emr.entity.Patients;
import com.test.emr.model.PatientsDTO;
import com.test.emr.service.EmrService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmrController {
	
	private final EmrService emrService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/patients")
	public String getPatients(Model model) {
		List<Patients> list = emrService.getPatients();
		model.addAttribute("patients", list);
		return "patients";
	}
	
	@GetMapping("/patient/{id}")
	public String getPatient(@PathVariable("id") Long id, Model model) {
		Patients patient = emrService.getPatientById(id);
		model.addAttribute("patient", patient);
		return "patient";
	}
	
	@GetMapping("/addPatient")
    public String addPatient() {
        return "addPatient";  // 환자 추가 페이지 반환
    }
	
	@PostMapping("/addPatient")
	public ResponseEntity<String> addPatient(@RequestBody PatientsDTO patientsDTO) throws JsonProcessingException {
		Patients savedPatient = emrService.processAndSavePatient(patientsDTO);
		return new ResponseEntity<>("Patient added successfully wirh ID: " + savedPatient.getId(), HttpStatus.CREATED);
	}
	
}
