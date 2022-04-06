
--로그인
CREATE OR REPLACE PROCEDURE getMember(
    p_userid IN member.userid%TYPE, 
    p_curvar OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_curvar FOR SELECT * FROM member WHERE userid=p_userid;
END;

select * from member
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
    insert into notification (num, user_to, user_from, noti_type)
    values (notification_seq.nextval,p_followed,p_follower, p_notitype);
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
        select n.user_to, n.user_from as userfrom, n.num, noti_type as notitype, n.post_num, 
        p.img as postImg, r.content as replyContent, n.create_date as create_date, m.img AS IMG
        from notification n 
            left outer join post p on p.post_num = n.post_num
            left outer join reply r on r.reply_num = n.reply_num    
            left outer join member m on m.userid = n.user_from
        where n.user_to = p_userid order by num desc;      
        
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
    update story set useyn = 'n' where userid=p_userid;
    commit;
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


--notiCount 
create or replace PROCEDURE getNotiCount (
    p_userid IN notification.user_to%TYPE,
    p_notiCount OUT number
     )
IS 
    v_notiCount number(30) := 0;
BEGIN 
    select count(*) into v_notiCount from notification where user_to=p_userid and checked=0;
    p_notiCount := v_notiCount;
END;

--아이디 찾기 
CREATE OR REPLACE PROCEDURE findId(
    p_name IN member.name%TYPE, 
    p_phone IN member.phone%TYPE, 
    p_userid OUT VARCHAR    
)
IS
    v_count number(5) := 0;
    v_userid varchar2(30) := ''; 
BEGIN
    SELECT count(*) into v_count FROM member WHERE name=p_name and phone=p_phone;
    
    if v_count = 1 then
       SELECT userid into v_userid FROM member WHERE name=p_name and phone=p_phone;
        p_userid := v_userid;
    else 
        p_userid := '';
    end if;
END;

--비밀번호 찾기 - 이메일 확인
CREATE OR REPLACE PROCEDURE userEmailCheck(
    p_name IN member.name%TYPE, 
    p_email IN member.email%TYPE, 
    pwFindCheck OUT boolean    
)
IS
    v_count number(5) := 0;
BEGIN
    SELECT count(*) into v_count FROM member WHERE name=p_name and email=p_email;
    
    if v_count = 1 then
        pwFindCheck := true;
    else 
        pwFindCheck := false;
    end if;
END;

--임시 비밀번호로 비밀번호 변경
CREATE OR REPLACE PROCEDURE updatePassword(
    p_str IN member.password%TYPE, 
    p_email IN member.email%TYPE
)
IS
BEGIN
    update member set password=p_str where email = p_email;
END;
