package com.test.emr.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="medical_records")
public class MedicalRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, columnDefinition = "TEXT")
    private String diagnosis; // 진단 내용

    @Column(nullable = false, columnDefinition = "TEXT")
    private String treatment; // 치료 내용

    @Column(nullable = false)
    private String recordDate; // 진료 날짜

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt; // 생성일시

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 수정일시
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patients_id", nullable = false)
    private Patients patients;

    @PrePersist
    public void prePersist() {
    	LocalDateTime now = LocalDateTime.now();
    	this.createdAt = now;
    	this.updatedAt = now;
    }
    
    @PreUpdate
    public void preUpdate() {
    	this.updatedAt = LocalDateTime.now();
    }
	
    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                ", recordDate='" + recordDate + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", patientsId=" + (patients != null ? patients.getId() : null) + 
                '}';
    }
}
