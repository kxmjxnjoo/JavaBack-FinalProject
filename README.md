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

## V2 - 22년 5월
- Spring Boot, JSP, 
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

# Development History
## 22년 3월 ~ 5월 (v1)
- JSP 기반 SSR 서버로 개발
- 게시물(포스트, 스토리) CRUD
- 유저 관련 기능(알림, 팔로우 등)

## 22년 6월 ~ (v3)
- ORM을 사용해 DTO 단순화 및 Query 제거
- RESTful API 디자인 채용
  - Controller의 class 단위로 @RequestMapping을 주고, HTTP method로 동작을 표현
- 모든 Service 및 Repository class에 Unit Test 작성 (진행중)
- Spring Security 사용해 보안성 높은 인증/인가 구현
