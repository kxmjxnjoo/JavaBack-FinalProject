create table notification(
    user_to varchar(30),
    user_from varchar(30),

    post_num int,

    num serial,
    noti_type int,
    reply_num int,
    create_date Date default now(),
    content varchar(100),
    checked int,

    primary key(num),
    foreign key(user_to) references member,
    foreign key(user_from) references member,
    foreign key(post_num) references post
);

create sequence notification_seq start with 1 increment by 1;