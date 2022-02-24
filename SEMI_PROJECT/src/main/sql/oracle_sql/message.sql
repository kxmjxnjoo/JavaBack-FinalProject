create table message(
	num number(5) primary key,
	
	message_to varchar2(20) not null references member(userid),
	message_from varchar2(20) not null references member(userid),
	
	send_date date default sysdate,
	
	content varchar2(300) not null
);
create sequence message_seq start with 1;