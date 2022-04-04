
--스토리 추가
CREATE OR REPLACE PROCEDURE addStory(
    p_story_img IN story.img%type, 
    p_content IN story.story_content%type,
    p_fontcolor IN story.fontcolor%type,
    p_userid IN story.userid%type,
    p_story_num OUT number
)
IS
    v_story_num number(5) := 0;
BEGIN
    insert into story (story_num, img, story_content, fontcolor, userid)
    values (story_seq.nextVal, p_story_img,p_content,p_fontcolor,p_userid);
    commit;
    
    select max(story_num) into v_story_num from story where userid = p_userid;
    p_story_num := v_story_num;
END;


--스토리 상세보기 (getStory)
CREATE OR REPLACE PROCEDURE getStory(
    p_story_num IN story.story_num%TYPE,
    p_curvar OUT SYS_REFCURSOR,
    p_prev out number,
    p_next out number
)
IS
    v_userid varchar2(50) := '';
    v_prev number(5) := 0;
    v_next number(5) := 0;
BEGIN
    OPEN p_curvar FOR SELECT * FROM story WHERE story_num=p_story_num;
    
    select userid into v_userid from story where story_num = p_story_num;
    
    select max(story_num) into v_prev
    from story_view where story_num < p_story_num group by userid having userid=v_userid;
    p_prev := v_prev;
    
    select min(story_num) into v_next
    from story_view where story_num > p_story_num group by userid having userid=v_userid;
    p_next := v_next; 
EXCEPTION
    WHEN NO_DATA_FOUND THEN
    Null;
END;

select * from story where userid='king';

--스토리 삭제
create or replace PROCEDURE deleteStory(
    p_story_num IN story.story_num%type
)
IS
BEGIN
    delete from story_like where story_num=p_story_num;
    delete from story  where story_num=p_story_num;
    commit;
END;

SELECT * FROM STORY_LIKE