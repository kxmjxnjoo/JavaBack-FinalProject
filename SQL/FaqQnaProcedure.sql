
--faqList
CREATE OR REPLACE PROCEDURE faqList(
    p_curvar OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_curvar FOR SELECT * FROM faq order by faq_num desc;
END;


--add Qna
create or replace PROCEDURE addQna(
    p_status out number, 
    p_qna_id IN qna.qna_id%TYPE,
    p_qna_subject IN qna.qna_subject%TYPE,
    p_qna_content IN qna.qna_content%TYPE
    )
IS
    v_status number(5) := 1;
BEGIN
    insert into qna(qna_num, qna_id, qna_subject, qna_content)
    values(qna_seq.nextVal, p_qna_id, p_qna_subject, p_qna_content);
    commit;
    p_status := v_status;
exception when others then
    v_status := 0;
    p_status := v_status;
    rollback;    
END;


--getQna
create or replace PROCEDURE getQna(
    p_qna_num IN qna.qna_num%TYPE,
    p_cur OUT SYS_REFCURSOR
    )
IS
BEGIN
    open p_cur for select * from qna where qna_num = p_qna_num;
END;

select * from qna;

--getQnaList : 유저가 작성한 QNA
create or replace PROCEDURE getQnaList(
    p_userid IN qna.qna_id%TYPE,
    p_cur OUT SYS_REFCURSOR
    )
IS
BEGIN
    open p_cur for select * from qna where qna_id = p_userid;
END;


--getAllQna : 모든 qna List
create or replace PROCEDURE getAllQna(
    p_cur OUT SYS_REFCURSOR,
    p_userid IN qna.qna_id%TYPE
    )
IS
BEGIN
    open p_cur for select * from qna 
    where rep='y' and qna_id!=p_userid order by qna_num desc;
END;
