create or replace procedure selectPostByUserid(
	p_userid in member.userid%TYPE,
	p_cur out sys_refcursor
	)
is
begin
	open p_cur for 
		select * 
		from post 
		where userid=p_userid
		order by create_date desc;
end;