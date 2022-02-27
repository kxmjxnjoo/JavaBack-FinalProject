select * from notification;

insert into notification (user_to, user_from, noti_type) values('hong', 'jinkpark', 1);


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