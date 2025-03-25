# app.py

from dotenv import load_dotenv
import os
from flask import Flask, request, jsonify
from flask_mysqldb import MySQL
from datetime import datetime

# .env 파일 로드
load_dotenv()

app = Flask(__name__)

# 환경 변수로 MySQL 연결 정보 설정
app.config['MYSQL_HOST'] = os.getenv('MYSQL_HOST')
app.config['MYSQL_USER'] = os.getenv('MYSQL_USER')
app.config['MYSQL_PASSWORD'] = os.getenv('MYSQL_PASSWORD')
app.config['MYSQL_DB'] = os.getenv('MYSQL_DB')

mySQL = MySQL(app)

# 환자 정보 전처리하는 함수
def process_patient_data(patient_data):

    # 생일을 기준으로 나이 계산
    birthdate = patient_data['birthdate']
    birthdae_obj = datetime.strptime(birthdate, "%Y-%m-%d")
    age = calculate_age(birthdae_obj)

    # 연락처 포맷 처리
    contact = patient_data['contact']
    formatted_contact = format_contact(contact)
    
    # 전처리된 데이터 반환
    processed_date = {
        "name": patient_data['name'],
        "birthdate": birthdate,
        "age": age, # 계산된 나이 추가
        "gender": patient_data['gender'],
        "contact": formatted_contact
    }
    return processed_date

# 생일을 기준으로 나이 계산하는 함수
def calculate_age(birthdate_obj):
    today = datetime.now()
    age = today.year - birthdate_obj.year
    if today.month < birthdate_obj.month or (today.month == birthdate_obj.month and today.day < birthdate_obj.day):
        age -= 1
    return age

# 연락처 포맷팅 함수
def format_contact(contact):
    # 연락처를 "010-xxxx-xxxx" 형식으로 변경
    return contact[:3] + '-' + contact[3:7] + '-' + contact[7:]

# Flask 엔드포인트 - 환자 데이터 전처리
@app.route('/process_patient', methods=['POST'])
def process_patient():
    # 요청에서 환자 데이터 받기
    patient_data = request.get_json()

    if not patient_data:
        return jsonify({"error": "No patient data provided"}), 400
    
    # 환자 데이터 전처리
    processed_data = process_patient_data(patient_data)

    # 전처리된 데이터를 반환
    return jsonify(processed_data)

@app.route('/process_patient_record', methods=['GET'])
def get_patients_list():
    # 요청에서 환자 데이터 받기
    patient_data = request.get_json()

    if not patient_data:
        return jsonify({"error": "No patient data provided"}), 400
    
    # 환자 데이터 전처리
    processed_data = process_patient_data(patient_data)

    # 전처리된 데이터를 반환
    return jsonify(processed_data)
    
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5050)
