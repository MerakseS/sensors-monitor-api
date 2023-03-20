create table types
(
    id   bigserial,
    name varchar(30) not null,
    primary key (id)
);

insert into types (name)
values ('Pressure'),
       ('Voltage'),
       ('Temperature'),
       ('Humidity');

create table units
(
    id   bigserial,
    name varchar(30) not null,
    primary key (id)
);

insert into units (name)
values ('bar'),
       ('voltage'),
       ('°С'),
       ('%');

create table sensors
(
    id          bigserial,
    description varchar(200),
    location    varchar(40),
    model       varchar(15) not null,
    name        varchar(30) not null,
    range_from  integer,
    range_to    integer,
    type_id     bigint      not null,
    unit_id     bigint      not null,

    primary key (id),
    constraint sensor_type_fk foreign key (type_id) references types,
    constraint sensor_unit_fk foreign key (unit_id) references units
);
