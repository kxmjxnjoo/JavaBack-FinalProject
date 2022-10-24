# Springfeed : Instagram 기반 SNS 서비스

# 소개
인스타그램을 참고해 만든 봄 테마의 SNS 서비스입니다.

# 사용된 기술 (Version 2 기준)
- Back
  - JSP
  - Spring
    - Spring Boot
    - MyBatis
  - Oracle DB, Procedure

- Front
  - HTML
  - CSS
  - JS

- DevOps
  - Oracle Cloud
  - Github Pages

# Version History
## V1 - 22년 2월 14일 ~ 2월 27일 (총 14일)
- Spring 배우지 않은 상태에서 개발
### 구조
1. HTTP Request 받음 (URL은 /command=COMMAND_NAME 형태)
2. Servlet Container가 HTTP Reuqest 받아 HttpServletRequest, HttpServletResponse 생성
3. HttpServlet 상속한 객체 호출
4. HTTP Request의 Method에 따라 doGet, doPost 호출
5. doGet
    1. URL Parsing해 command 찾음
    2. ActionFactory 패턴으로 된 Action 객체 생성
    3. Action의 execute method 실행
6. execute
    1. (필요한 경우) Session에 저장되어 있는 로그인 정보 확인
    2. (필요한 경우) Parameter에서 데이터 가져옴
    3. 필요한 DAO 호출
    4. 얻은 DTO로 적절하게 응답
7. DAO
    1. driver, url, id, pw를 사용해 Connection 생성
    2. Connection 객체 받아와 PreparedStatement에 적절한 데이터 넣어서 실행
    3. 실행 후 받은 ResultSet을 DTO로 변환
8. execute로 적절하게 처리된 HttpServletRequest를 jsp로 보냄
9. jsp에서 정의된 대로 Servlet 객체 생성
10. HttpServletResponse로 응답
11. Servlet Container에서 HTTP Response로 변환

## V2 - 22년 3월 28일 ~ 4월 18일 (총 22일)
- Spring Boot, JSP
- Oracle DB Procedure 사용
### 문제점
- 전체적인 문제점
  - css, js, html, jsp 등 모든 파일이 너무 김
  - 파일이 길어서 dependency 관리가 잘 되고, 유지보수가 힘듦
  - Spring의 기능을 거의 활용하지 않음
- Oracle Proceudre
  - Business Logic이 DB에 종속됨
- MyBatis
  - ORM을 사용하지 않아 DTO를 DB의 schema에 하나하나 mapping 해 줘야 함
- 로그인
  - DB에 Hash 알고리즘으로 Encoding하지 않은 Password를 저장함
  - 모든 로그인 처리에 Session을 사용해 서버에 부담
  - Session에 Password가 포함된 유저 객체를 저장함

## V3 - 22년 6월 ~
- Front/Back 분리
- Back : Spring Boot, JPA 사용 REST API
- Front : React, Bootstrap 사용