create table notification(
	num int(5) auto_increment,
	user_to varchar(30) not null,
	user_from varchar(30) not null,
	noti_type int(2) not null,
	-- 1 : follow
	-- 2 : like post
	-- 3 : comment
	-- 4 : comment like
		
	post_num int(5),
	reply_num int(5),
	
	create_date datetime default now(),
	
	primary key(num),
	foreign key(user_to) references member(userid),
	foreign key(user_from) references member(userid),
	foreign key(post_num) references post(post_num),
	foreign key(reply_num) references reply(reply_num)
<<<<<<< HEAD
);
=======
);

drop table notification;
drop view notification_view;

select * from member;
select * from post;
select * from reply;

select * from notification_view where user_to='hong' order by create_date;

SELECT LAST_INSERT_ID();

select * from notification;
>>>>>>> 1b74fada461274b784da0525aea019ff603aedda
