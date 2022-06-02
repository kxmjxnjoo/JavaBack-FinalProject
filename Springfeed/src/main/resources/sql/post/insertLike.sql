create or replace procedure insertLike(
    p_postnum in post.num%TYPE,
    p_cur out sys_cur
)
is
    isLiked number(1);

    cursor c1 is
        select * from post_like
        where post_num=p_postnum;
begin
    open c1;
    fetch c1 into isLiked;
    close c1;

    IF c1 == null THEN

    ELSE

end;