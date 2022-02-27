# 이젠 백엔드 과정 조별 프로젝트
## 기간
2월 14일 ~ 2월 25일 (12일)

## 공지사항
*진주 깃 허브 문제 생길까봐 커밋 안 하고 있습니다

# View 역할 분배
- [x] userPage.jsp 진겸
- [x] feedDetail.jsp 세인
- [x] main.jsp 진겸
- [x] story.jsp 세인
- [x] accountManage.jsp 진겸
- [x] message.jsp 진겸
- [x] messageDetail.jsp 진겸
- [x] explore.jsp 세인
- [x] notification.jsp 진겸
- [x] search.jsp 진겸
- [x] addPostForm.jsp 세인
- [x] editPostForm.jsp 세인
- [x] login.jsp 진주
- [x] joinForm.jsp 진주
- [x] adminLogin.jsp 진주
- [x] faqList.jsp 진주
- [ ] qnaForm.jsp 진주 -> css 오류로 수정 중
- [x] adminReportList.jsp 진주
- [x] adminMemberList.jsp 진주

# TODO
- [x] 아이디 비밀번호 찾기
- [x] 아이디 중복확인
- [x] 스토리/포스트 팔로우
- [x] 본인 스토리 보기
- [x] 스토리 수정
- [x] 북마크 추가 - 2월 23일 11시, dao/dto/jsp/action 작성 완료
- [x] 프로필 이미지 수정
- [x] 업로드 이미지
- [x] 메인에서 스토리 한 건이라도 있는사람만 sub bar 목록에 뜨기 - 2월 23일 11시 30분
- [x] 좋아요했을 때 알림
- [x] 유저페이지 팔로잉/팔로우 수 버그 수정 - 2월 23일 12시
- [x] 유저페이지 팔로우 확인해서 팔로우/언팔로우 버튼 수정 - 2월 23일 12시
- [x] 유저페이지에서 포스트 상세보기
- [ ] 유저페이지 포스트 이미지 비율 맞추기
- [x] 포스트 상세보기 css
- [x] spring.css 쪼개기
- [x] 메인에서 스토리가 없을 때 스토리 등록 페이지로
- [x] 스토리 작성자 전부 hong으로 뜸
- [x] 알람이 안오는 버그 수정 - 2월 23일 2시, sql문이 잘못 됐었음
- [x] 메인 페이지에서 자기자신 팔로우 인식 안하게 - 2월 23일 2시 30분, action에서 followList가 null이 아님을 확인하고 size가 1일 경우에도 info 페이지로 이동
- [x] 포스트 없을 때 안내 문구 추가  - 2월 23일 2시 30분, c:choose를 사용해서 안내문구 추가
- [x] 메인페이지 모달창 테두리 둥글게, 글씨체 볼드 - 2월 23일 2시 40분
- [x] 상세보기 모달창 신고 빨간 글씨
- [x] 상세보기 topnav 추가
- [x] 스토리/게시물 업로드에 topnav 추가
- [x] 로그인안했을 때 tonav 프로필 이동 안하게 수정 -> 로그인 안했을 때 프로필 두개로 나오는 거 해결 하면 됨 
- [x] 로그인 안해도 검색되게 검색 기능 수정
- [x] 회원 탈퇴 버그 수정 - 2월 23일 2시, 회원의 모든 정보를 먼저 삭제하게 수정
- [x] 유저 페이스 포스트 좋아요 댓글
- [ ] 메인 페이지에 페이징
- [x] 모든 페이지에 footer - 2월 23일 3시, footer가 있어야 할 자리에 footer 추가, 아직 css 조금 수정해야 함

- [x] 메세지 - 2월 24일 1시, dto, dao, action, jsp, css, js 추가

# Admin To do list

- [ ] memberList User 검색
- [ ] QnaView, QnaList Comment 작성
- [ ] ReportList 데이터 미출력
- [ ] ReportList Detail -> 해당 게시물, 스토리 내역?  
- [ ] ReportList jsp 유저 CheckBox 출력
- [ ] FaqList 작성 button CSS 위치 가운데로 수정  
- [ ] Faq View
- [ ] Faq Upload
- [ ] Faq Delete
- [ ] List.jsp button들 css미적용 되는 부분들 수정 필요함
- [ ] ()님 로그인 하셨습니다 문구 조금 더 가운데로 위치하게 수정

위 파일들 Dao, Action 작성된 것도 있고 작성되었으나 수정 필요한 것도 있음

# 버그 수정
- [ ] Admin 로그인 안 됨
- [ ] 자기 자신 팔로우 취소 가능
    - [ ] 검색에서 팔로우 취소 안 뜨게 하기
    - [ ] action에서 확인해서 막기
- [ ] 저장하기 안 됨 (아마 DB가 없음)
- [ ] 메세지에 자기자신 안 뜨게
- [ ] Explore 같은 좋아요 수이면 댓글 많은 순서대로
- [ ] 정보수정에서 비밀번호 갯수만큼 *
- [ ] 정보수정에서 textarea에 자기소개 안 뜸
- [ ] 비로그인 상태에서 userPage에 들어가서 포스트 클릭하면 로그인 안내 메세지가 아니라 그냥 아이디가 없다고 나옴
- [ ] QNA에서 답변이 안 달려도 자세히 볼 수 있음
- [ ] 회원탈퇴 안 됨(Action에서 관련 테이블 전체 삭제 해야 함)
- [ ] postdetail에서 유저 이미지 hover 하면 opacity + userPage로 이동


# 시연모습

