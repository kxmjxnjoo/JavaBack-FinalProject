
--로그인
CREATE OR REPLACE PROCEDURE getMember(
    p_userid IN member.userid%TYPE, 
    p_curvar OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_curvar FOR SELECT * FROM member WHERE userid=p_userid;
END;


--회원 추가
CREATE OR REPLACE PROCEDURE insertMember(
    p_userid IN member.userid%TYPE, 
    p_userpwd IN member.password%type,
    p_name IN member.name%type,
    p_email IN member.email%type,
    p_phone IN member.phone%type
)
IS
BEGIN
    insert into member (userid, password, name, email, phone)
    values (p_userid,p_userpwd,p_name,p_email,p_phone);
    commit;
END;

--아이디 중복확인
create or replace PROCEDURE idCheck(
    p_cnt OUT NUMBER,
    p_userid IN member.userid%TYPE
)
IS 
    v_cnt number(2) := 0;
BEGIN
    select count(*) into v_cnt from member where userid=p_userid;
    p_cnt := v_cnt;
END;


--팔로우 체크
create or replace PROCEDURE getFollow(
    p_followed IN follow.followed%TYPE,
    p_follower IN follow.follower%TYPE,
    p_result OUT number
)
IS 
    v_result number(5) := 0;
BEGIN
    select count(*) into v_result from follow where followed=p_followed and follower=p_follower;
    p_result := v_result;
END;


--팔로우
CREATE OR REPLACE PROCEDURE insertFollow(
    p_follower IN follow.follower%TYPE, 
    p_followed IN follow.followed%TYPE,
    p_result OUT NUMBER
)
IS 
BEGIN
    insert into follow (follow_num, follower, followed)
    values (follow_seq.nextval,p_follower,p_followed);
    commit;
    
END;   
    
--알림 추가
CREATE OR REPLACE PROCEDURE addNotification(
    p_followed IN notification.user_to%TYPE, 
    p_follower IN notification.user_from%TYPE,
    p_notitype IN notification.noti_type%TYPE,
    p_result OUT NUMBER
)
IS 
    v_result number(2) := '0';
BEGIN
    insert into notification (num, user_to, user_from)
    values (notification_seq.nextval,p_followed,p_follower);
    commit;
    
    v_result := '1';
    v_result := p_result;
    
EXCEPTION WHEN OTHERS THEN
    v_result := '0';
    v_result := p_result;
END;   


--언팔로우
CREATE OR REPLACE PROCEDURE unfollow(
    p_follower IN follow.follower%TYPE, 
    p_followed IN follow.followed%TYPE,
    p_result OUT NUMBER
)
IS 
    v_result number(2) := '0';
BEGIN
    delete from follow where follower=p_follower and followed=p_followed;
    commit;
    
    v_result := '1';
    v_result := p_result;
    
EXCEPTION WHEN OTHERS THEN
    v_result := '0';
    v_result := p_result;
END;

--getNotification : notification 리스트 호출
CREATE OR REPLACE PROCEDURE getNotification(
    p_cur OUT SYS_REFCURSOR,
    p_userid IN notification.user_to%TYPE
)
IS 
    
BEGIN
    open p_cur for
        select n.user_to, n.user_from as userfrom, n.num, noti_type as notitype, n.post_num, p.img as postImg, r.content as replyContent, n.create_date as create_date  
        from notification n 
            left outer join post p on p.post_num = n.post_num
            left outer join reply r on r.reply_num = n.reply_num    
        where n.user_to = p_userid;   
END;   


--회원 정보 수정
create or replace PROCEDURE userEdit(
    p_userid IN member.userid%type,
    p_password IN member.password%type,
    p_name IN member.name%type,
    p_email IN member.email%type,
    p_phone IN member.phone%type,
    p_introduce IN member.introduce%type,
    p_img IN member.img%type
)
IS
BEGIN
    update member set password=p_password, name=p_name, email=p_email, phone=p_phone, 
    introduce=p_introduce, img=p_img
    where userid=p_userid;
END;

SELECT * FROM MEMBER
--계정 비활성화
create or replace PROCEDURE deleteAcount(
    p_userid IN member.userid%type
)
IS
BEGIN
    update member set useyn = 'n' where userid=p_userid;
END;


--계정 활성화
create or replace PROCEDURE activateAccount(
    p_userid IN member.userid%type
)
IS
BEGIN
    update member set useyn = 'y' where userid=p_userid;
END;


--블락

CREATE TABLE BLOCKMEMBER(
    USERID VARCHAR2(20) REFERENCES MEMBER(USERID) ON DELETE CASCADE,
    BLOCKED VARCHAR2(20) REFERENCES MEMBER(USERID) ON DELETE CASCADE
)

--checkAdmin
create or replace PROCEDURE checkAdmin (
    p_adminid IN admin.adminid%TYPE,
     p_rc OUT SYS_REFCURSOR
     )
IS 
BEGIN 
    OPEN p_rc FOR
        select * from admin where adminid = p_adminid;
END;


select * from story