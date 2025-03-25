package com.test.emr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.emr.entity.Patients;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Long> {

}
