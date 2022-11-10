create or replace procedure getIsFollowing(
    p_userid in member.userid%TYPE,
    p_follower in member.userid%TYPE,
    p_isFollowing out number
)
is
    v_result number(1) := 0;
begin
    select count(follow_num) into v_result from follow where follower=p_follower and following=p_userid;
    p_isFollowing := v_result;
end;