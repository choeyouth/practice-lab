DROP TABLE medical_records;
DROP TABLE patients;

CREATE TABLE patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,   -- 환자 ID (자동 증가)
    name VARCHAR(100) NOT NULL,              -- 환자 이름
    birthdate DATE NOT NULL,                 -- 생년월일
    gender CHAR(1) NOT NULL,                 -- 성별 ('M' 또는 'F')
    contact VARCHAR(20) NULL,                -- 연락처
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 생성일시
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 수정일시
);

CREATE TABLE medical_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,   -- 진료 기록 ID (자동 증가)
    patients_id BIGINT NOT NULL,             -- 환자 ID (환자 테이블과 연관)
    diagnosis TEXT NOT NULL,                -- 진단 내용
    treatment TEXT NOT NULL,                -- 치료 내용
    recordDate DATE NOT NULL,              -- 진료 날짜
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 생성일시
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 수정일시
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE  -- 외래키 제약조건 (환자 삭제 시 진료 기록도 삭제)
);

INSERT INTO patients (name, birthdate, gender, contact)
VALUES
('홍길동', '1980-05-12', 'M', '010-1234-5678'),
('김영희', '1995-10-25', 'F', '010-9876-5432'),
('이철수', '1975-08-17', 'M', '010-2468-1357'),
('박지은', '1988-03-30', 'F', '010-3697-2580'),
('최민수', '2000-12-05', 'M', '010-1357-2468');

INSERT INTO medical_records (patient_id, diagnosis, treatment, recordDate)
VALUES
(1, '고혈압', '약물 처방', '2024-03-20'),
(1, '당뇨', '운동 및 식이 조절', '2024-02-15'),
(2, '감기', '휴식 및 해열제 복용', '2024-03-10'),
(2, '스트레스', '심리 상담', '2024-03-12'),
(3, '허리 통증', '물리 치료', '2024-03-05'),
(3, '고혈압', '약물 처방', '2024-02-25'),
(4, '독감', '항바이러스제 처방', '2024-03-18'),
(4, '위염', '약물 처방 및 식이 요법', '2024-03-22'),
(5, '두통', '진통제 복용', '2024-03-12'),
(5, '알레르기 비염', '항히스타민제 처방', '2024-03-14');

-- 환자 목록 확인
SELECT * FROM patients;

-- 의료 기록 확인 
SELECT * FROM medical_records; 

-- 환자별 진료 기록 확인
SELECT * FROM medical_records WHERE patient_id = 1;  -- 예시: 홍길동 환자 진료 기록

ALTER TABLE medical_records CHANGE COLUMN patient_id patients_id BIGINT;




commit;
