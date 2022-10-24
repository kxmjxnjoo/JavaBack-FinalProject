create or replace procedure selectPostByNum(
    p_postnum in post.post_num%TYPE,
    p_userid in member.userid%TYPE,
    p_cur out SYS_REFCURSOR
)
is
begin
    select p.img, p.content, p.address, p.userid, p.create_date, m.img as userimg, count(l.post_num) as like_count, count(b.post_num) as isSaved, count(pl.post_num) as isLiked
    from post p inner join member m on m.userid=p.userid
                inner join post_like l on p.post_num=l.post_num
                left join bookmark b on (b.post_num=p_postnum and b.userid=p_userid)
                left join post_like pl on (pl.post_num=p_postnum and pl.userid=p_userid)
    where p.post_num=p_postnum
    group by p.img, p.content, p.address, p.userid, p.create_date, m.img;
end;