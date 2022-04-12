create or replace procedure exploreFeed(
    p_page int,
    p_cur out SYS_REFCURSOR
)
is
begin
    open p_cur for
    select p.post_num, p.img, count(l.post_num) as num_like, count(r.post_num) as num_reply
    from ((post p inner join post_like l on p.post_num = l.post_num)
         inner join reply r on r.post_num = p.post_num)
        order by num_like desc
        group by p.post_num, p.img
        offset (12 * p_page) rows fetch next 12 rows only;
end;