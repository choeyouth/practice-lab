package com.test.emr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.emr.entity.MedicalRecord;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

}
