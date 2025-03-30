package com.test.emr.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@AllArgsConstructor
@NoArgsConstructor
@Table(name="patients")
public class Patients {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
    private String name; 

    @Column(nullable = false)
    private String birthdate; 

    @Column(nullable = false, length = 1)
    private String gender;

    @Column(length = 20)
    private String contact;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    //private Integer age;
    
    @OneToMany(mappedBy = "patients")
    private List<MedicalRecord> medicalRecords;
	
    //생성 일시와 수정 일시 자동 생성
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
	
}
