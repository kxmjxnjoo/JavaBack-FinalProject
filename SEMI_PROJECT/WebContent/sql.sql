/*member*/
create table member(
	userid varchar2(20) not null,
	password varchar2(20) not null,
	name varchar2(20) not null, 
	email varchar2(20) not null,
	phone varchar2(20) not null,
	useyn varchar2(5) default 'y', /*y:사용중 b:차단됨 n:비활성화*/
	introduce varchar2(1000),
	indate date default sysdate,
	primary key (userid)
)

insert into member (userid, password, name, email, phone, introduce)
values('hong','1234', '홍길동','hong@abc.com','010-1234-3456','안녕하세요');
insert into member (userid, password, name, email, phone, introduce)
values('kim','1234', '김길동','kim@abc.com','010-2345-8765','반갑습니다');
insert into member (userid, password, name, email, phone, introduce)
values('user12','1234', '이유저','user12@abc.com','010-4342-5555','이유저입니다.');
insert into member (userid, password, name, email, phone, introduce)
values('nari','1234', '박나리','nari@abc.com','010-5555-2347','좋은하루 되세요.');
insert into member (userid, password, name, email, phone, introduce)
values('yoon','1234', '윤성모','yoon@abc.com','010-2525-4745','안녕하세요');
insert into member (userid, password, name, email, phone, introduce)
values('jojo','1234', '조장혁','jojo@abc.com','010-1534-7577','반갑습니다');
insert into member (userid, password, name, email, phone, introduce)
values('choi','1234', '최유리','choi@abc.com','010-3545-1588','최유리입니다.');
insert into member (userid, password, name, email, phone, introduce)
values('love','1234', '김사랑','love@abc.com','010-5555-2347','좋은하루 되세요.');

select * from MEMBER

/*follow*/
create table follow(
	follow_num number(5) primary key,
	following varchar2(20) REFERENCES member(userid),
	follower varchar2(20) REFERENCES member(userid)
)
CREATE SEQUENCE follow_seq START WITH 1;

insert into follow values(follow_seq.nextval, 'jojo', 'choi');
insert into follow values(follow_seq.nextval, 'choi', 'jojo');
insert into follow values(follow_seq.nextval, 'hong', 'user12');
insert into follow values(follow_seq.nextval, 'hong', 'nari');
insert into follow values(follow_seq.nextval, 'hong', 'choi');
insert into follow values(follow_seq.nextval, 'hong', 'jojo');
insert into follow values(follow_seq.nextval, 'hong', 'love');
insert into follow values(follow_seq.nextval, 'choi', 'yoon',);
insert into follow values(follow_seq.nextval, 'nari', 'hong');
insert into follow values(follow_seq.nextval, 'choi', 'hong');
insert into follow values(follow_seq.nextval, 'jojo', 'hong');
insert into follow values(follow_seq.nextval, 'love', 'hong');

select * from follow


/*post*/
create table post (
	post_num number(5) primary key, 
	img varchar2(20) not null,
	content varchar2(1000),
	address varchar2(100),
	userid varchar2(20) references member(userid),
	create_date date default sysdate
)

create sequence post_seq start with 1;

select * from post

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
	content varchar2(500) not null,
	reply_num number(5) primary key 
)

create sequence reply_seq start with 1;
select * from reply

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


/*story*/
create table story(
	story_num number(5) primary key,
	img varchar2(50) not null,
	userid references member(userid),
	create_date date default sysdate
)
create sequence story_seq start with 1;
select * from story


/*admin*/
create table admin(
	adminid varchar2(20) primary key, 
	password varchar2(20) not null,
	name varchar2(20) not null,
	email varchar2(20) not null,
	phone varchar2(20) not null
)

insert into admin values('admin', 'admin', '관리자', 'admin@abc.com', '010-1111-1111');
insert into admin values('scott', 'tiger', '관리자', 'scott@abc.com', '010-2222-3333');

select * from admin


/*FAQ*/
create table faq (
	faq_num number(5) primary key, 
	faq_subject varchar2(300) not null,
	faq_content varchar2(4000) not null
)
create sequence faq_seq start with 1;
select * from faq;

insert into faq values(faq_seq.nextVal, '회원 가입은 어떻게 하나요?', '회원 가입 링크를 클릭하고, 약관을 읽어보신 뒤 [동의]에 체크합니다.
기본정보와 추가정보를 정확하게 입력하신 뒤 [회원가입] 버튼을 클릭하면 회원가입이 완료됩니다.');


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
)
create sequence qna_seq start with 1;
select * from qna;


/*report*/
create table report(	
	reporter_id varchar2(20) references member(userid),
	reported_id varchar2(20) references member(userid),
	indate date default sysdate,
	reason varchar2(100) not null,
	report_num varchar2(5) primary key
)
create sequence report_seq start with 1;
select * from report;

