create or replace procedure insertFollow(
    p_followed in member.userid%TYPE,
    p_follower in member.userid%TYPE
)
is
begin
insert into follow (followed, follower) values (p_followed, p_follower);
commit;
end;