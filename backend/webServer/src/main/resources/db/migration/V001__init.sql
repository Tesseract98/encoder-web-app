create table if not exists hibernate_sequence (
   next_val bigint
);

insert into hibernate_sequence values ( 25 );

create table users (
    id bigint not null,
    created_date datetime default current_timestamp(),
    email varchar(255) not null,
    enabled boolean default true not null,
    last_modified_date datetime default current_timestamp(),
    login varchar(255) not null,
    name varchar(255),
    password varchar(255) not null,
    patronymic varchar(255),
    surname varchar(255),
    primary key (id),
    index login_idx (login),
    unique (email),
    unique (login)
);

create table privilege (
    id bigint not null,
    name varchar(255) not null,
    primary key (id),
    index privilege_idx (name)
);

create table role (
    id bigint not null,
    name varchar(255) not null,
    primary key (id),
    index role_idx (name)
);

create table roles_privileges (
    role_fk bigint not null,
    privilege_fk bigint not null,
    foreign key (role_fk) references role (id),
    foreign key (privilege_fk) references privilege (id)
);

create table users_roles (
    user_fk bigint not null,
    role_fk bigint not null,
    foreign key (user_fk) references users (id),
    foreign key (role_fk) references role (id)
);
