/* 똑같은 sql mysql 버전 */

/*member*/
create table member(
	userid varchar(20) not null,
	password varchar(20) not null,
	name varchar(20) not null, 
	email varchar(20) not null,
	phone varchar(20) not null,
	useyn varchar(5) default 'y', /*y:사용중 b:차단됨 n:비활성화*/
	introduce varchar(1000),
	indate datetime default now(),
	img varchar(30) default 'tmpUserIcon';

	primary key (userid)
);

select * from MEMBER

/*follow*/
create table follow(
	follow_num int(5) primary key auto_increment,
	following varchar(20) REFERENCES member(userid),
	follower varchar(20) REFERENCES member(userid)
)

select * from follow

/*post*/
create table post (
	post_num int(5) auto_increment, 
	img varchar(30) not null,
	content varchar(1000),
	address varchar(100),
	userid varchar(20) references member(userid),
	create_date datetime default now(),

	primary key(post_num)
);

select count(post_num) as post_count from post group by userid having userid='hong';

select * from post

/*img_upload*/
create table img_upload (
	post_num int(5) references post(post_num),
	img1 varchar(100),
	img2 varchar(100),
	img3 varchar(100),
	img4 varchar(100),
	primary key (post_num)
)

select * from img_upload

/*reply*/

create table reply (
	userid varchar(20) not null,
	content varchar(500) not null,
	post_num int(5) not null,
	reply_num int(5) auto_increment,

	primary key(reply_num),
	foreign key(userid) references member(userid),
	foreign key(post_num) references post(post_num)
);

select * from reply

SELECT LAST_INSERT_ID();

/*post_like*/

create table post_like (
	post_num int(5) references post(post_num),
	userid varchar(20) references member(userid),
	primary key (userid, post_num)
)
select * from post_like

/*reply_like*/
create table reply_like (
	reply_num int(5),
	userid varchar(20),
	primary key (userid, reply_num),
	foreign key(reply_num) references reply(reply_num),
	foreign key(userid) references member(userid)
);

select * from reply_like


/*story*/ 
create table story(
	story_num int(5) auto_increment,
	img varchar(50) not null,
	userid varchar(20) references member(userid),
	create_date datetime default now(),

	primary key(story_num)
);

select * from story


/*admin*/
create table admin(
	adminid varchar(20), 
	password varchar(20) not null,
	name varchar(20) not null,
	email varchar(20) not null,
	phone varchar(20) not null,

	primary key(adminid)
)

insert into admin values('admin', 'admin', '관리자', 'admin@abc.com', '010-1111-1111');
insert into admin values('scott', 'tiger', '관리자', 'scott@abc.com', '010-2222-3333');

select * from admin


/*FAQ*/
create table faq (
	faq_num int(5) auto_increment, 
	faq_subject varchar(300) not null,
	faq_content varchar(4000) not null,

	primary key(faq_num)
)
select * from faq;

insert into faq (faq_subject, faq_content) values('회원 가입은 어떻게 하나요?', '회원 가입 링크를 클릭하고, 약관을 읽어보신 뒤 [동의]에 체크합니다.
기본정보와 추가정보를 정확하게 입력하신 뒤 [회원가입] 버튼을 클릭하면 회원가입이 완료됩니다.');


/*QnA*/
create table qna (
	qna_num int(5) auto_increment,
	qna_subject varchar(300) not null,
	qna_content varchar(2000) not null,
	qna_reply varchar(1000),
	qna_id varchar(20) references member(userid),
	rep varchar(5) default 'n',
	indate datetime default now(),
	qna_password varchar(20),

	primary key(qna_num)
)
select * from qna;


/*report*/
create table report(	
	reporter_id varchar(20) references member(userid),
	reported_id varchar(20) references member(userid),
	indate datetime default now(),
	reason varchar(100) not null,
	report_num int(5) auto_increment,

	primary key(report_num)
)
select * from report;


-- TEST
select * from follow;