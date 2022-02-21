insert into member (userid, password, name, email, phone, introduce, img)
values('hong','1234', '홍길동','hong@abc.com','010-1234-3456','안녕하세요', '/images/profile1.png');
insert into member (userid, password, name, email, phone, introduce, img)
values('kim','1234', '김길동','kim@abc.com','010-2345-8765','반갑습니다', '/images/profile2.png');
insert into member (userid, password, name, email, phone, introduce, img)
values('user12','1234', '이유저','user12@abc.com','010-4342-5555','이유저입니다.', '/images/profile3.png');
insert into member (userid, password, name, email, phone, introduce, img)
values('nari','1234', '박나리','nari@abc.com','010-5555-2347','좋은하루 되세요.', '/images/profile4.png');
insert into member (userid, password, name, email, phone, introduce, img)
values('yoon','1234', '윤성모','yoon@abc.com','010-2525-4745','안녕하세요', '/images/profile5.png');
insert into member (userid, password, name, email, phone, introduce, img)
values('jojo','1234', '조장혁','jojo@abc.com','010-1534-7577','반갑습니다', '/images/profile6.png');
insert into member (userid, password, name, email, phone, introduce, img)
values('choi','1234', '최유리','choi@abc.com','010-3545-1588','최유리입니다.', '/images/profile7.png');
insert into member (userid, password, name, email, phone, introduce, img)
values('love','1234', '김사랑','love@abc.com','010-5555-2347','좋은하루 되세요.', '/images/profile8.png');

select * from member

insert into follow (following, follower) values('jojo', 'choi');
insert into follow (following, follower) values('choi', 'jojo');
insert into follow (following, follower) values('hong', 'user12');
insert into follow (following, follower) values('hong', 'nari');
insert into follow (following, follower) values('hong', 'choi');
insert into follow (following, follower) values('hong', 'jojo');
insert into follow (following, follower) values('hong', 'love');
insert into follow (following, follower) values('choi', 'yoon');
insert into follow (following, follower) values('nari', 'hong');
insert into follow (following, follower) values('choi', 'hong');
insert into follow (following, follower) values('jojo', 'hong');
insert into follow (following, follower) values('love', 'hong');

insert into post (img, content, address, userid) values('/images/post1.png', '바다 놀러 갔다 옴', '서울시 송파구 올림픽로 141', 'nari');
insert into post (img, content, address, userid) values('/images/post2.png', '산 놀러 갔다 옴', '서울시 송파구 올림픽로 141', 'choi');
insert into post (img, content, address, userid) values('/images/post3.png', '천국 놀러 갔다 옴', '서울시 송파구 올림픽로 141', 'jojo');
insert into post (img, content, address, userid) values('/images/post4.png', '지옥 놀러 갔다 옴', '서울시 송파구 올림픽로 141', 'love');
insert into post (img, content, address, userid) values('/images/post5.png', '머리카락 좀 내려주삼', '아리넬 왕국 숲 속', 'hong');
insert into post (img, content, address, userid) values('/images/post6.png', '아아 몰라', '뉴욕 맨헤튼', 'nari');
insert into post (img, content, address, userid) values('/images/post7.png', 'LET IT GO PLEASE', '중국 상하이', 'nari');
insert into post (img, content, address, userid) values('/images/post8.png', '영국은 피시앤 칩스지', '영국 런던', 'nari');
insert into post (img, content, address, userid) values('/images/post9.png', '일본 벗꽃 보고 싶당', '일본 도쿄', 'nari');

insert into post_like (post_num, userid) values(1, 'choi');
insert into post_like (post_num, userid) values(1, 'jojo');
insert into post_like (post_num, userid) values(1, 'love');
insert into post_like (post_num, userid) values(2, 'hong');
insert into post_like (post_num, userid) values(4, 'hong');

insert into notification (user_to, user_from, noti_type) values('hong', 'nari', 1);
insert into notification (user_to, user_from, noti_type, post_num) values('hong', 'nari', 2, 5);
insert into notification (user_to, user_from, noti_type, reply_num) values('hong', 'nari', 3, 1);