create or replace procedure getReplyByPostNum(
    p_postnum in post.post_num%TYPE,
    p_page in number,
    p_cur out SYS_REFCURSOR
)
is
begin
    open p_cur for
        select * from reply where post_num=p_postnum
        offset (10 * p_page) rows fetch next 10 rows only;
end;