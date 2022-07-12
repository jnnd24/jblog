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

update blog
set blogTitle = '성찬블로그 수정입니다',
logofile = '16575054778210ab4f693-87be-4afa-9037-296dc3d5c920.jpg'
where id = 'aaaa';

-------category
drop table category;

create table category(
    cateNo      number,
    id          varchar2(50),
    cateName    varchar2(200)   not null,
    description varchar2(500),
    regDate     date            not null,
    primary key (cateNo),
    constraint category_fk foreign key (id)
    references blog (id)
);

create sequence seq_category_no
increment by 1
start with 1
nocache;

select * 
from category;

insert into category
values(
    seq_category_no.nextval,
    'aaaa',
    '미분류',
    '기본으로 만들어지는 카테고리입니다.',
    sysdate
);


select cateno
        ,id
        ,catename
        ,description
        ,regdate
from category
where id = 'aaaa';