// Member Dummy Data
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

insert into reply(userid, content, reply_num, post_num) values('jojo','test', reply_seq.nextval, 19);
insert into reply(userid, content, reply_num, post_num) 
values('love','testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest', reply_seq.nextval, 19);
insert into reply(userid, content, reply_num, post_num) values('hong','test', reply_seq.nextval, 19);
insert into reply(userid, content, reply_num, post_num) values('jojo','test', reply_seq.nextval, 19);

insert into post_like values(19, 'hong');
insert into post_like values(20, 'hong');
insert into post_like values(21, 'hong');
insert into post_like values(27, 'hong');

insert into admin values('admin', 'admin', '관리자', 'admin@abc.com', '010-1111-1111');
insert into admin values('scott', 'tiger', '관리자', 'scott@abc.com', '010-2222-3333');

insert into faq values(faq_seq.nextVal, '회원 가입은 어떻게 하나요?', '회원 가입 링크를 클릭하고, 약관을 읽어보신 뒤 [동의]에 체크합니다.
기본정보와 추가정보를 정확하게 입력하신 뒤 [회원가입] 버튼을 클릭하면 회원가입이 완료됩니다.');

insert into qna (qna_num, qna_subject, qna_content, qna_reply, qna_id) values(qna_seq.nextVal, '이거 뭐하는 사이트에여?', '검색하다 발견했는데 이거 뭐하는 사이트임?','스프링피드는 서로의 일상을 공유하는 사이트에요.', 'hong');

insert into post (img, content, address, userid, post_num) values('/images/post1.png', '바다 놀러 갔다 옴', '서울시 송파구 올림픽로 141', 'nari', post_seq.nextVal);
insert into post (img, content, address, userid, post_num) values('/images/post2.png', '산 놀러 갔다 옴', '서울시 송파구 올림픽로 141', 'choi', post_seq.nextVal);
insert into post (img, content, address, userid, post_num) values('/images/post3.png', '천국 놀러 갔다 옴', '서울시 송파구 올림픽로 141', 'jojo', post_seq.nextVal);
insert into post (img, content, address, userid, post_num) values('/images/post4.png', '지옥 놀러 갔다 옴', '서울시 송파구 올림픽로 141', 'love', post_seq.nextVal);
insert into post (img, content, address, userid, post_num) values('/images/post5.png', '머리카락 좀 내려주삼', '아리넬 왕국 숲 속', 'hong', post_seq.nextVal);
insert into post (img, content, address, userid, post_num) values('/images/post6.png', '아아 몰라', '뉴욕 맨헤튼', 'nari', post_seq.nextVal);
insert into post (img, content, address, userid, post_num) values('/images/post7.png', 'LET IT GO PLEASE', '중국 상하이', 'nari', post_seq.nextVal);
insert into post (img, content, address, userid, post_num) values('/images/post8.png', '영국은 피시앤 칩스지', '영국 런던', 'nari', post_seq.nextVal);
insert into post (img, content, address, userid, post_num) values('/images/post9.png', '일본 벗꽃 보고 싶당', '일본 도쿄', 'nari', post_seq.nextVal);

select * from post;

