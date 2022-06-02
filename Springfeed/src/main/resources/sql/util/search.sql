create or replace procedure getMemberSearch(
    p_key varchar2,
    p_page int,
    p_cur out sys_refcursor
)
is
begin
    open p_cur for
        select * from member where userid like '%'||p_key||'%' or name like '%'||p_key||'%' or email like '%'||p_key||'%'
        order by indate desc;
end;


create or replace procedure getPostSearch(
    p_key varchar2,
    p_page int,
    p_cur out sys_refcursor
)
is
begin
    open p_cur for
        select * from post where content like '%'||p_key||'%' or address like '%'||p_key||'%'
        order by create_date desc;
end;