create table member(
    id serial,
    userid varchar(20),
    password varchar(20),
    name varchar(100),
    email varchar(100),
    phone varchar(20),
    img varchar(100),
    useyn varchar(5),
    introduce varchar(1000),
    indate Date,

    primary key(id)
);

create sequence member_seq start with 1 increment by 1;