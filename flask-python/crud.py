# crud.py
from flask import Flask, request, redirect
import random

app = Flask(__name__)

nextId = 4
topics = [
    {'id': 1, 'title': 'html', 'body': 'html is ...'},
    {'id': 2, 'title': 'css', 'body': 'css is ...'},
    {'id': 3, 'title': 'javascript', 'body': 'javascript is ...'}
]

def template(contents, content, id=None): # id=None > id가 없을 경우 대비 
    contextUI = ''
    if id != None:
        contextUI = f'''
            <li><a href="/update/{id}/">update</a></li>
            <li>
                <form action="/delete/{id}/" method="POST">
                    <input type="submit" value="delete">
                </form>
            </li>
        ''' 
    return f'''<!doctype html>
    <html>
        <head>
            <title>CRUD</title>
        </head>
        <body>
            <h1><a href="/">WEB</a></h1>
            <ol>   
                {contents}
            </ol>
            {content}
            <ul>
                <li><a href="/create/">create</a></li>
                {contextUI}
            </ul>
        </body>
    </html>
    '''

def getContents():
    liTags = ''
    for topic in topics:
        liTags = liTags + f'<li><a href="/read/{topic["id"]}">{topic["title"]}</a></li>'
    return liTags

@app.route('/')
def index():
    return template(getContents(), '<h2>Welcome</h2>Hello, WEB')

@app.route('/read/<int:id>/') # ('/read/<id>') > 문자열로 인식 > int로 변환
def read(id):
    # print(type(id)) # <class 'str'> > int로 변환
    title = ''
    body = ''
    for topic in topics: 
        if id == topic['id']:
            title = topic['title']
            body = topic['body']
            break
    return template(getContents(), f'<h2>{title}</h2>{body}', id)

@app.route('/create/', methods=['GET', 'POST'])
def create():

    # print('request.method', request.method) # 결과: 처음 GET, submit 직후 POST > request의 메서드가 GET인지 POST인지 등을 구분할 수 있다. 
    
    if request.method == 'GET':
        content = '''
            <form action="/create/" method="POST">
                <p><input type="text" name="title" placeholder="title"></p>
                <p><textarea name="body"  placeholder="body"></textarea></p>
                <p><input type="submit" value="create"></p>
            </form>
        '''
        return template(getContents(), content)
    
    elif request.method == 'POST':
        
        global nextId # 전역 변수 선언 > 전역으로 변경 가능하도록
        
        title = request.form['title']
        body = request.form['body']
        newTopic = {'id': nextId, 'title': title, 'body': body}
        topics.append(newTopic)

        url = '/read/'+ str(nextId) + '/'
        nextId += 1

        return redirect(url)


@app.route('/update/<int:id>/', methods=['GET', 'POST'])
def update(id):
    if request.method == 'GET':
        title = ''
        body = ''
        for topic in topics: 
            if id == topic['id']:
                title = topic['title']
                body = topic['body']
                break
        content = f'''
            <form action="/update/{id}/" method="POST">
                <p><input type="text" name="title" placeholder="title" value="{title}"></p>
                <p><textarea name="body"  placeholder="body">{body}</textarea></p>
                <p><input type="submit" value="create"></p>
            </form>
        '''
        return template(getContents(), content)
    
    elif request.method == 'POST':
        
        global nextId # 전역 변수 선언 > 전역으로 변경 가능하도록
        
        title = request.form['title']
        body = request.form['body']

        for topic in topics:
            if id == topic['id']:
                topic['title'] = title
                topic['body'] = body   
                break

        url = '/read/'+ str(id) + '/'
        return redirect(url)

@app.route('/delete/<int:id>/', methods=['POST'])
def delete(id):
    for topic in topics:
        if id == topic['id']:
            topics.remove(topic)
            break
    return redirect('/') 

app.run(debug=True)