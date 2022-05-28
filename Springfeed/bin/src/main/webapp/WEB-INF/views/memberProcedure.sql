
CREATE OR REPLACE PROCEDURE getMember(
    p_userid IN member.userid%TYPE, 
    p_curvar OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_curvar FOR SELECT * FROM member WHERE userid=p_userid;
END;

select * from member;

--ȸ�� �߰�
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

--���̵� �ߺ�Ȯ��
CREATE OR REPLACE PROCEDURE idCheck(
    p_userid IN member.userid%TYPE,
    p_cnt OUT NUMBER
)
IS 
    v_cnt number(2) := 0;
BEGIN
    select count(*) into v_cnt from member where userid=p_userid;
    p_cnt := v_cnt;
        
END;

--�ȷο�
CREATE OR REPLACE PROCEDURE insertFollow(
    p_follower IN follow.follower%TYPE, 
    p_followed IN follow.followed%TYPE,
    p_result OUT NUMBER
)
IS 
    v_result number(2) := '0';
BEGIN
    insert into follow (follow_num, follower, followed)
    values (follow_seq.nextval,p_follower,p_followed);
    commit;
    
    v_result := '1';
    v_result := p_result;
    
EXCEPTION WHEN OTHERS THEN
    v_result := '0';
    v_result := p_result;
END;   
    
--�˸� �߰�
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