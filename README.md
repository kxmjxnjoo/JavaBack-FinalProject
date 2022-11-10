# Version 2
Spring을 사용해 

# Version 2에서 사용된 기술
- Back
  - JSP
  - Spring
    - Spring Boot
    - MyBatis
  - Oracle DB, Procedure
  
- Front
  - HTML
  - CSS
    - Bootstrap
  - JS

- DevOps
  - Oracle Cloud
  - Github Pages

# Version 2에서의 문제점/개선점
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
