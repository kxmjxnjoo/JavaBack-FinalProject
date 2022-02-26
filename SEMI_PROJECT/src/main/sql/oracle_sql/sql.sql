/*member*/
create table member(
	userid varchar2(20) not null,
	password varchar2(20) not null,
	name varchar2(100) not null, 
	email varchar2(100) not null,
	phone varchar2(20) not null,
	img varchar2(100),
	useyn varchar2(5) default 'y', /*y:사용중 b:차단됨 n:비활성화*/
	introduce varchar2(1000),
	indate date default sysdate,
)

alter table member modify (email varchar2(100));
alter table member modify (img varchar2(100));

select * from member

select * from member where (userid like '%'||'hong'||'%' or name like '%'||'hong'||'%') order by indate desc;

/*follow*/
create table follow(
	follow_num number(5) primary key,
	following varchar2(20) REFERENCES member(userid),
	follower varchar2(20) REFERENCES member(userid)
);
CREATE SEQUENCE follow_seq START WITH 1;

select * from follow

/*post*/
create table post (
	post_num number(5) primary key, 
	img varchar2(100) not null,
	content varchar2(300),
	address varchar2(100),
	userid varchar2(20) references member(userid),
	create_date date default sysdate
)
create sequence post_seq start with 1;	

alter table post modify (content varchar2(280)) 
select * from post

/* post의 img와 member img를 함께 출력하기 위한 view 입니다.*/
create view post_view as
select p.post_num, p.img as post_img, p.content, p.address, m.userid, p.create_date, m.img as user_img
from member m, post p
where m.userid = p.userid
order by p.create_date desc;

/*img_upload*/
create table img_upload (
	post_num number(5) references post(post_num),
	img1 varchar2(100),
	img2 varchar2(100),
	img3 varchar2(100),
	img4 varchar2(100),
	primary key (post_num)
)
select * from img_upload

/*reply*/
create table reply (
	userid varchar2(20) references member(userid),
	content varchar2(200) not null,
	post_num number(5) references post(post_num),
	reply_num number(5) primary key,
	reply_date date default sysdate
)
create sequence reply_seq start with 1;

select * from reply

create or replace view reply_view 
as select m.userid, r.content, p.post_num, reply_num, reply_date, m.img from member m, reply r, post p 
where m.userid=r.userid and p.post_num=r.post_num;

select * from reply_view

/*post_like*/
create table post_like (
	post_num number(5) references post(post_num),
	userid varchar2(20) references member(userid),
	primary key (userid, post_num)
)
select * from post_like


/*reply_like*/
create table reply_like (
	reply_num number(5) references reply(reply_num),
	userid varchar2(20) references member(userid),
	primary key (userid, reply_num)
)
select * from reply_like


/*story_like*/
create table story_like(
	story_num number(5) references story,
	userid varchar2(20) references member(userid),
	primary key(story_num, userid)
)

select * from story_like;


/*story*/
create table story(
	story_num number(5) primary key,
	img varchar2(100) not null,
	story_content varchar2(240),
	userid references member(userid),
	fontcolor varchar2(20), 
	create_date date default sysdate
)

create sequence story_seq start with 1;
select * from story

create view story_view as
select s.story_num, s.img as story_img, s.story_content, m.userid, s.create_date, m.img as user_img
from member m, story s
where m.userid = s.userid
order by s.create_date desc;

/*admin*/
create table admin(
	adminid varchar2(20) primary key, 
	password varchar2(20) not null,
	name varchar2(20) not null,
	email varchar2(20) not null,
	phone varchar2(20) not null
)

select * from admin


/*FAQ*/
create table faq (
	faq_num number(5) primary key, 
	faq_subject varchar2(300) not null,
	faq_content varchar2(4000) not null
);
create sequence faq_seq start with 1;
select * from faq;


/*QnA*/
create table qna (
	qna_num number(5) primary key,
	qna_subject varchar2(300) not null,
	qna_content varchar2(2000) not null,
	qna_reply varchar2(1000),
	qna_id varchar2(20) references member(userid),
	rep varchar2(5) default 'n',
	indate date default sysdate,
	qna_password varchar2(20)
);
create sequence qna_seq start with 1;
select * from qna;

drop table qna;


/*report*/
create table report(	
	reporter_id varchar2(20) references member(userid) on delete cascade,
	reported_id varchar2(20) references member(userid) on delete cascade,
	post_num number(5) references post(post_num) on delete cascade, 
	story_num number(5) references story(story_num) on delete cascade,
	report_type varcher2(20),
	indate date default sysdate,
	handled varchar2(5) default 'n',
	reason varchar2(100) not null,
	report_num varchar2(5) primary key
)

alter table report add(post_num number(5) references post(post_num));
alter table report RENAME COLUMN post_type TO report_type;
alter table report add(story_num number(5) );
alter table report add(handled varchar2(5) default 'n');


create sequence report_seq start with 1;
select * from report;


/*notification*/

create table notification(
	num number(5) primary key,
	user_to varchar2(30) not null references member(userid) on delete cascade,
	user_from varchar2(30) not null references member(userid) on delete cascade,
	noti_type number(2) not null ,
	post_num number(5) references post(post_num) on delete cascade,
	reply_num number(5)  references reply(reply_num),
	story_num number(5) references story(story_num) on delete cascade,
	create_date date default sysdate
);

alter table notification add(content varchar2(100))
alter table notification add(story_num number(5) references story(story_num) on delete cascade)
create sequence notification_seq start with 1;

create view notification_view as
select n.user_to, n.user_from, n.noti_type, n.content, n.create_date, m.img as member_img
from notification n, member m
where n.user_from = m.userid;

create view follow_view as
select f.following, f.follower, m.name as followingName, m.img as followingImg
from follow f, member m
where m.userid = following;

create table bookmark(
	num number(5) primary key,
	userid not null references member(userid),
	post_num not null references post(post_num),
	save_date date default sysdate
);
create sequence bookmark_seq start with 1;


/*테스트*/

select max(post_num) from post where userid='hong' group by userid
select max(post_num) from post group by userid having userid='hong';


select * from post;
update post set address='11', img='1.png', content='222' where post_num=3

select * from post;
delete post_like where post_num = 21 and userid = 'hong';

select * from story_view
select min(story_num) as next from story_view where story_num > 1 group by userid having userid='hong';
delete story where story_num=4;

select max(post_num) as max from post group by userid having userid='hong'

create view follow_view as
select f.following, f.follower, m.name as followingName, m.img as followingImg
from follow f, member m
where m.userid = f.following;

select count(post_num) as likes from post_like group by post_num having post_num=57
select * from post_view order by create_date desc

select max(story_num) as max from story group by userid having userid='hong'


select s.* from story s left outer join (select count(*) as count, userid  from story group by userid) a on s.userid = a.userid ;


insert into follow values(follow_seq.nextval, 'love', 'love');

select * from 
((select distinct userid from story) a, follow f where a.userid = f.following) where follower 

select f.* from (select distinct userid from story) a, follow_view f where a.userid = f.following and follower='nari'

select * from (
select * from (select * from (select rownum as rn, m.* from 
((select * from report where reporter_id like '%%' or reported_id like '%%' order by indate desc) m) 
) where rn>=1) where rn<=10 ) where report_type = 'user'

select * from (
 select * from (select * from (select rownum as rn, m.* from 
((select * from report where report_type='user') m) 
 ) where rn>=1) where rn<=10 ) order by indate desc
 
select * from report where report_type='user' order by indate desc)