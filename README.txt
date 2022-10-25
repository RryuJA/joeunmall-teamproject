# joeunmall-teamproject

* 개발환경: 전자정부 표준프레임워크 3.9
* DB: Oracle 11g
* WAS : tomcat 9.0 (Port : 8282)

** C 드라이브에 파일 다운로드

<초기 설정> 
* lombok에 전자정부 표준프레임워크 3.9 등록
* Oracle 11g 계정생성
  1) User ID: project
  2) User PW: 1111
    -> create user project identified by 1111;
  3) Grant: connect, resource
    -> grant connect, resource to project;
