create view follow_view as
select f.following, f.follower, m.name as followingName, m.img as followingImg
from follow as f, member as m
where m.userid = following;

select * from follow_view;

select count(follow_num) as follower_count from follow group by follower having follower='hong';

create view post_view as
select p.post_num, p.img as post_img, p.content, p.address, m.userid, p.create_date, m.img as user_img
from member as m, post as p
where m.userid = p.userid
order by p.create_date desc;

select * from post_view;

select count(post_num) as likes from post_like group by post_num having post_num=1;

