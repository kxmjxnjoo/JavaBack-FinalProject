-- Get following
select followed from follow where follower='jinkpark';
-- Get Post where userid=following
-- Order by date
-- select latest 10
select * from post where userid in (select followed from follow where follower='jinkpark')
order by create_date desc
offset (10 * 1) rows fetch next 10 rows only;



-- PROCEDURE
create or replace procedure selectPostFeed(
    p_userid in member.userid%TYPE,
    p_page int,
    p_cur out sys_refcursor
    )
is
begin
    open p_cur for
    select * from post where userid in (select followed from follow where follower=p_userid)
    order by create_date desc
    offset (10 * p_page) rows fetch next 10 rows only;
end;