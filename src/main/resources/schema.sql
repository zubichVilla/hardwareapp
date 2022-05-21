create table if not exists hardware(
    id identity,
    hardware_name varchar(40) not null,
    code varchar(40) not null,
    price decimal not null,
    hardware_type varchar(40) not null,
    quantity_available integer not null
    );


create table if not exists review(
    id identity,
    title varchar(40) not null,
    text varchar(256) not null,
    rating varchar(20) not null,
    hardware_id integer not null,
    primary key (id),
    foreign key (hardware_id) references hardware(id)
);

create table if not exists user(
    id identity,
    username varchar(100) not null,
    password varchar(256) not null
);

create table if not exists authority(
    id identity,
    authority_name varchar(100) not null
);

create table if not exists user_authority(
    user_id bigint not null,
    authority_id bigint not null,
    constraint fk_user foreign key (user_id) references user(id),
    constraint fk_authority foreign key (authority_id) references authority(id)
);
