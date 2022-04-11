
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
    p_fontcolor out varchar,
    p_useyn out varchar
)
IS
    v_fontcolor varchar2(50) := '';
    v_useyn varchar2(5) := '';
BEGIN
    OPEN p_curvar FOR SELECT * FROM story_view WHERE story_num=p_story_num;
    
    select fontcolor,useyn into v_fontcolor,v_useyn from story where story_num = p_story_num;
    p_fontcolor := v_fontcolor;
    p_useyn := v_useyn;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
    Null;
END;

--이전 스토리 번호 리턴
CREATE OR REPLACE PROCEDURE getStoryPrev(
    p_story_num IN story.story_num%TYPE,
    p_prev out number,
    p_next out number
)
IS
    v_userid varchar2(50) := '';
    v_prev number(5) := 0;
    v_next number(5) := 0;
BEGIN
    select userid into v_userid from story where story_num = p_story_num;

    select max(story_num) into v_prev
    from story where story_num < p_story_num group by userid having userid=v_userid;
    p_prev := v_prev;
      
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
    Null;
END;


--다음 스토리 번호 리턴
CREATE OR REPLACE PROCEDURE getStoryNext(
    p_story_num IN story.story_num%TYPE,
    p_prev out number,
    p_next out number
)
IS
    v_userid varchar2(50) := '';
    v_prev number(5) := 0;
    v_next number(5) := 0;
BEGIN
    select userid into v_userid from story where story_num = p_story_num;

    select max(story_num) into v_prev
    from story where story_num < p_story_num group by userid having userid=v_userid;
    p_prev := v_prev;
      
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
    Null;
END;


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

--스토리 수정
CREATE OR REPLACE PROCEDURE editStory(
    p_story_img IN story.img%type, 
    p_content IN story.story_content%type,
    p_fontcolor IN story.fontcolor%type,
    p_story_num story.story_num%type
)
IS
    v_story_num number(5) := 0;
BEGIN
    update story set img= p_story_img, story_content= p_content, fontcolor= p_fontcolor
    where story_num = p_story_num; 
    commit;
END;
