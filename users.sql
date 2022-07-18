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
    '������ ��α��Դϴ�.',
    null
);

select id
        ,blogtitle
        ,logofile
from blog
where id = 'aaaa';

update blog
set blogTitle = '������α� �����Դϴ�',
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
from category
order by cateno desc;

insert into category
values(
    seq_category_no.nextval,
    'aaaa',
    '�̺з�',
    '�⺻���� ��������� ī�װ��Դϴ�.',
    sysdate
);


select cateno
        ,id
        ,catename
        ,description
        ,regdate
from category
where id = 'aaaa';

-------post
drop table post;
create table post(
    postNo      number,
    cateNo      number,
    postTitle   varchar2(300)   not null,
    postContent varchar2(4000),
    regDate     date    not null,
    primary key (postNo),
    constraint post_fk foreign key (cateNo)
    references category(cateNo)
);

create sequence seq_post_no
increment by 1
start with 1
nocache;

select *
from post;

insert into post
values (
    seq_post_no.nextval,
    2,
    '��Ʈ��Ÿ��Ʋ',
    '�Խ��ǳ��볻�볻��',
    sysdate
);


select postNo
        ,cateNo
        ,postTitle
        ,postContent
        ,regDate
from post;

--ī�װ� �� �Խù����� ���
select postTitle
        ,regDate
        ,cateNo
        ,postNo
from post
where cateNo = 2
order by postNo desc;

--�ֱٱ� �ϳ��� �ҷ�����
select postNo
        ,postTitle
        ,postContent
        ,regDate
        ,rownum rn
from post
where cateNo = 0
order by postNo desc
;

select postNo
        ,postTitle
        ,postContent
        ,regDate
        ,rn
from (  select postNo
                ,postTitle
                ,postContent
                ,regDate
                ,rownum rn
        from post
        where cateNo = 2
        order by postNo desc
        )
where rn = 1
;

--�Խù� �ҷ�����
select postTitle
        ,postContent
        ,regDate
from post
where postNo = 7
;




-------comments
drop table comments;
create table comments(
    cmtNo       number,
    postNo      number,
    userNo      number,
    cmtContent  varchar2(1000) not null,
    regDate     date    not null,
    primary key (cmtNo),
    constraint comments_fk foreign key (userNo)
    references users(userNo)
);

create sequence seq_comments_no
increment by 1
start with 1
nocache;








