# Version 2 목표
- Version 1에서 진행한 프로젝트를 Spring을 사용해 개선.
- DB 연결, RDB Schema-자바 객체 Mapping을 MyBatis를 사용해 자동화

# Version 2에서 사용된 기술
## Backend
- JSP
- Spring Boot
- MyBatis
- Oracle DB, Oracle Procedure
  
## Frontend
  - HTML
  - CSS
    - Bootstrap
  - JS

## DevOps
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

- 보안
  - DB에 Hash 알고리즘으로 Encoding하지 않은 Password를 저장함
  - 모든 로그인 처리에 Session을 사용해 서버에 부담
  - Session에 Password가 포함된 유저 객체를 저장함

- Git
    - 일정이 빠듯해 Git을 제대로 공부하지 않고 사용해 다른 팀원의 작업물을 날리거나, 자신의 작업물을 날렸던 경우가 많았음.
    - commit을 일정 단위로 하지 않고 여러 수정 사항을 같이 함
    - main branch를 항상 배포가능한 상태로 하지 않음