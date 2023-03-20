create table users
(
    id       bigserial,
    login    varchar(30)  not null,
    password varchar(255) not null,
    role     varchar(30)  not null,
    primary key (id)
);

insert into users (login, password, role)
values ('user', '$2a$10$SIZ01cbdiKqMIos6xtMgZuV/KmxnlHkHrjC0c1vjI5ApgWMfE04Wq', 'VIEWER'),
       ('admin', '$2a$10$VkuhAdgvgfsIvt7fo5TJ7O6Tj1l9aGHhULK2dTV1dMtPS/OmPkEqG', 'ADMINISTRATOR');