drop table users;
drop sequence seq_users_no;

create table users(
    userNo      number,
    id          varchar2(50)    not null unique,
    userName    varchar2(100)   not null,
    password    varchar2(50)    not null,
    joinDate    date            not null,
    primary key (userNo)
);


create sequence seq_users_no
increment by 1
start with 1
nocache;

select * 
from users;

delete users
where userno > 2;



select count(id)
from users
where id = 'aaaa';


insert into users
values(
    seq_users_no.nextval,
    'id',
    'userName',
    'password',
    sysdate
);

select userno
from users
where id = 'aaaa'
and password = '1234';

-----------blog
drop table blog;

create table blog(
    id      varchar2(50),
    blogTitle   varchar2(200) not null,
    logoFile    varchar2(200),
    primary key (id),
    CONSTRAINT blog_fk foreign key (id)
    references users(id)
);

select *
from blog;

insert into blog
values(
    'aaaa',
    '성찬의 블로그입니다.',
    null
);

select id
        ,blogtitle
        ,logofile
from blog
where id = 'aaaa';

-------category
