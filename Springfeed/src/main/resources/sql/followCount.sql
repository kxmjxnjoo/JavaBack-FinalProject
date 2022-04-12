create or replace procedure getFollowingCount(
    p_userid in member.userid%TYPE,
    p_followingCount out NUMBER
)
is
    v_cnt number(3) := 0;
begin
select count(follow_num) into v_cnt from follow where follower=p_userid;
p_followingCount := v_cnt;
end;

create or replace procedure getFollowerCount(
    p_userid in member.userid%TYPE,
    p_followerCount out NUMBER
)
is
    v_cnt number(3) := 0;
begin
select count(follow_num) into v_cnt from follow where followed=p_userid;
p_followerCount := v_cnt;
end;