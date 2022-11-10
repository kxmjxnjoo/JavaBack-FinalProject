create table notification(
	num int(5) auto_increment,
	user_to varchar(30) not null,
	user_from varchar(30) not null,
	noti_type int(2) not null,
	-- 1 : follow
	-- 2 : like post
	-- 3 : comment
		
	post_num int(5),
	reply_num int(5),
	
	create_date datetime default now(),
	
	primary key(num),
	foreign key(user_to) references member(userid),
	foreign key(user_from) references member(userid),
	foreign key(post_num) references post(post_num),
	foreign key(reply_num) references reply(reply_num)
);

create view notification_view as
select n.user_to, n.user_from, n.noti_type, n.content, n.create_date, m.img as member_img
from notification as n, member as m
where n.user_from = m.userid;

drop table notification;
drop view notification_view;

select * from member;
select * from post;
select * from reply;

select * from notification_view where user_to='hong' order by create_date;

select * from notification where user_to='hong' order by create_date;