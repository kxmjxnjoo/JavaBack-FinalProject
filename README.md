# Springfeed : Instagram 기반 SNS 서비스

# 소개
인스타그램을 참고해 만든 봄 테마의 SNS 서비스입니다.

# 사용된 기술
- Back
  - Servlet / JSP
  - Spring Framework
    - JPA

- Front
  - Bootstrap
  - React.js

# Development History

## 22년 3월 ~ 5월 (v1)
- JSP 기반 SSR 서버로 개발
- 게시물(포스트, 스토리) CRUD
- 유저 관련 기능(알림, 팔로우 등)

## 22년 5월 ~ (v2)
- ORM을 사용해 DTO 단순화 및 Query 제거
- RESTful API 디자인 채용
  - Controller의 class 단위로 @RequestMapping을 주고, HTTP method로 동작을 표현
- 모든 Service 및 Repository class에 Unit Test 작성 (진행중)
- Spring Security 사용해 보안성 높은 인증/인가 구현

# INFO
## DB 관련
- 코드 Refactoring을 진행중인데 cloud db가 너무 느려서 임시로 local DB 사용중입니다. 테스트가 필요하시면 application.properties에서 postgre comment out 처리 하고 oracle cloud 사용 하시면 됩니다.
