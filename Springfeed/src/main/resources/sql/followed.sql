create or replace procedure getFollowedList(
    p_userid in member.userid%TYPE,
    p_page int,
    p_load int,
    p_cur out sys_refcursor
)
is
begin
    open p_cur for
    select * from MEMBER where userid in (select followed from follow where follower=p_userid)
    offset (10 * p_page) rows fetch next p_load rows only;
end;
