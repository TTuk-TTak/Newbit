# 1) 버전 정리
* JVM : 1.8.0
* 웹서버 : Tomcat v9.0.52
* IDE : STS 3.9.14 
* JDK 1.8
* mybatis 2.2.1
* lombok 
* VueJs : 2.6.11 <br>
* front 환경변수 (중요)

  * frontend/newbit/.env.local 파일 생성 후 다음과 같이 작성
    
    1. 서버에 올라간 백엔드와 통신하는 경우
    ```
    VUE_APP_SERVER_URL=https://i6a101.p.ssafy.io/api
    VUE_APP_CLIENT_URL=http://localhost:8080
    ```
    
    2. 로컬 백엔드와 통신하는 경우 
    ```
    VUE_APP_SERVER_URL=http://localhost:9999
    VUE_APP_CLIENT_URL=http://localhost:8080
    ```

<br>

* DB 접속 정보
  * S06P12A101\backend\Newbit\src\main\resources\application.properties 파일에 정의되어 있음
  * hostname : i6a101.p.ssafy.io
  * username : newbit
  * password : 팀원 문의
<br>

* 외부 서비스 정보
  - 사용한 외부 서비스 없습니다.
<br>

* DB 덤프 파일 최신본
  - exec 첨부하였습니다.


<br><br>

# 2. 설치 및 사용법

깃랩에 접속해 `clone` 받으신 후, `clone` 받은 디렉토리에서 아래의 순서대로 설치를 진행합니다.  

[깃랩 주소](https://lab.ssafy.com/s06-webmobile2-sub2/S06P12A101)

## 1) DB설정

- 첨부한 덤프파일을 사용하여 Workbench 상에서 Data Import를 진행합니다.
- DB의 hostname, username, password 등이 변경된 경우 
  - S06P12A101\backend\Newbit\src\main\resources\application.properties 의 DB 설정을 수정

## 2) 백엔드

- [http://i6a101.p.ssafy.io:9999/](https://i6a101.p.ssafy.io/api) 이하로 요청 시 테스트 가능
- 로컬 설치 방법
  - clone 받은 후 IDE에서 프로젝트 우클릭
  - Run as SpringBoot App으로 실행
  - 오류가 뜨는 경우 lombok이 설치되어 있는지 확인 후 Maven Update


## 3) 프론트엔드

- Node.js 설치

[Node.js](https://nodejs.org/ko/)

- 프론트엔드 디렉토리로 이동.

```bash
cd {클론 받은 폴더}/frontend/newbit
```

- 아래의 명령어를 실행

```bash
npm install
npm run serve
```

# 3. 배포하기
- AWS EC2를 이용하여 배포하였습니다.
- 사전에 AWS EC2 계정과 인스턴스를 생성해주세요.

* backend
  ```
  Maven Build
  ```
  * 명령어 통해서 .jar 파일로 빌드합니다.

  * S06P12A101\backend\Newbit\target 이하에 생성된 .jar 파일을 EC2의 Ubuntu 환경으로 이동시킵니다. (mobaxterm사용)

  * 실행 방법

  ```
  sudo apt-get install openjdk-8-jdk
  java -jar [생성된 jar파일명.jar] &
  ```

  * 프로세스 종료 방법
  
  ```
  ps -ef | grep java
  sudo kill -9 [pid]
  ```

* front
  ```
  npm run Build
  ```
  * 명령어를 통해 S06P12A101\frontend\newbit 이하에 dist 폴더를 빌드합니다.
  * 해당 폴더를 EC2의 Ubuntu 환경으로 이동시킵니다. (mobaxterm사용)
  
  * nginx 설치
  ```
    sudo apt-get install nginx
  ```

  * nginx 설정
  ```
    server {
      listen 80;
      location / {
        root /home/ubuntu/dist;
        index index.html index.htm;
        try_files $uri $uri/ /index.html =404;
      }
    }
      
    server {
      listen 443;
      listen [::]:443;
      ssl on;
      server_name i6a101.p.ssafy.io www.i6a101.p.ssafy.io;

      ssl_certificate /etc/letsencrypt/live/i6a101.p.ssafy.io/fullchain.pem;
      ssl_certificate_key /etc/letsencrypt/live/i6a101.p.ssafy.io/privkey.pem;

      location / {
        root /home/ubuntu/dist;
        index index.html index.htm;
        try_files $uri $uri/ /index.html =404;
      }

      location /api {
        proxy_pass http://localhost:9999;
      }
    }
  ```




