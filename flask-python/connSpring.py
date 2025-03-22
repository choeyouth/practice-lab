from flask import Flask, request, render_template
import boto3, json
from werkzeug.utils import secure_filename
from socket import *

app = Flask(__name__)

# *** 주의 route 뒤에 경로는 위에 Spring에서 적은 경로와 같아야함! ***
# /receive_string 경로로 들어오는 POST 요청을 처리하는 엔드포인트
@app.route('/receive_string', methods=['POST'])
def receive_string():
    # Spring으로부터 JSON 객체를 전달받음
    dto_json = request.get_json()
    
    # dto_json을 dumps 메서드를 활용하여 reponse에 문자열로 저장 
    # 예시: "{"name": "홍길동", "tel": "010-1111-1111"}"
    response = json.dumps(dto_json, ensure_ascii=False) #ensure_ascii > 한글이 깨지지 않도록 처리

    # Spring에서 받은 데이터를 출력하여 확인 
    # 예시: {'name': '홍길동', 'tel': '010-1111-1111'}
    print("json data: " + response)

    # Spring으로 reponse 전달
    return response

# 0.0.0.0 으로 모든 IP에 대한 연결을 허용해놓고 포트는 8082로 설정 
if __name__  == '__main__':
    app.run('0.0.0.0', port = 8082, debug = True)
    # 0.0.0.0: Flask 서버가 모든 네트워크 인터페이스에서 접근 가능하도록 설정 즉, 로컬뿐만 아니라 외부에서도 접근 가능
    # Spring Boot와 다른 포트를 사용해야 함 (Spring: 8080, Flask: 8082)    