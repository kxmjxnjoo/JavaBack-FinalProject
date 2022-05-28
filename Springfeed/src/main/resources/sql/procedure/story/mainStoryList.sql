-- select follower
select followed from follow where follower='jinkpark'

-- select following's stories
select * from story where userid in (select followed from follow where follower='jinkpark')

-- select unique userid by story create_date
select distinct userid  from story where userid in
                                         (select followed from follow where follower='jinkpark')

-- select user's info from stories
select userid, img from member where userid in (select distinct userid from story where userid in (select followed from follow where follower='jinkpark'))

create or replace procedure getStoryList(
    p_userid in member.userid%TYPE,
    p_cur out sys_refcursor
)
is
begin
    open p_cur for
        select userid, img from member where userid in
        (select distinct userid from story where userid in
        (select followed from follow where follower='jinkpark'));
end;