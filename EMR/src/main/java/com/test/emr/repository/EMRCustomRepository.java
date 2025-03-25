package com.test.emr.repository;

import static com.test.emr.entity.QPatients.patients;

import java.util.List;

import static com.test.emr.entity.QMedicalRecord.medicalRecord;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.emr.entity.MedicalRecord;
import com.test.emr.entity.Patients;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EMRCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public List<MedicalRecord> findById(Long id) {
		return jpaQueryFactory
				.selectFrom(medicalRecord)
				.leftJoin(medicalRecord.patients, patients).fetchJoin()
				.where(patients.id.eq(id))
				.fetch();
	
	}
	
}

