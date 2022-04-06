
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
    p_qna_id IN qna.qna_id%TYPE,
    p_qna_subject IN qna.qna_subject%TYPE,
    p_qna_content IN qna.qna_content%TYPE,
    p_qna_num OUT number
    )
IS
    v_qna_num number(5) := 0;
BEGIN
    insert into qna(qna_num, qna_id, qna_subject, qna_content)
    values(qna_seq.nextVal, p_qna_id, p_qna_subject, p_qna_content);
    commit;
    
    select max(qna_num) into v_qna_num from qna where qna_id = p_qna_id;
    p_qna_num := v_qna_num;
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
    p_cur OUT SYS_REFCURSOR
    )
IS
BEGIN
    open p_cur for select * from qna order by qna_num desc;
END;
