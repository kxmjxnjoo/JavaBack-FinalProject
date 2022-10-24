create or replace procedure insertSavePost(
    p_userid in member.userid%TYPE,
    p_postnum in post.post_num%TYPE,
    p_result out number
)
is
    v_result number(1) := 0;
begin
    insert into bookmark (userid, post_num) values (p_userid, p_postnum);
    commit;

    v_result := 1;
    p_result := v_result;

Exception when others then
    v_result := 0;
    p_result := v_result;
end;