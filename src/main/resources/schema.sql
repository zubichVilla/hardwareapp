create table if not exists hardware(
    id identity,
    hardware_name varchar(40) not null,
    code varchar(40) not null,
    price decimal not null,
    hardware_type varchar(40) not null,
    quantity_available integer not null
    );

