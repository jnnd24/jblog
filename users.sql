create table users(
    userNo      number,
    id          varchar2(50)    not null,
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
from users;
