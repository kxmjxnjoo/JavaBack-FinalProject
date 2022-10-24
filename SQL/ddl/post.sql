create table post(
    userid int,

    post_num int,
    img varchar(100),
    content varchar(300),
    address varchar(100),
    create_date Date default now(),
    useyn varchar(5),

    primary key(post_num),
    foreign key(userid) references member
);