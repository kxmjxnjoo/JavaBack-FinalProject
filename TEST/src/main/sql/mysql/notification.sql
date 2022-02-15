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