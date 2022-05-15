delete from hardware;
delete from review;

delete from user_authority;
delete from user;
delete from authority;


insert into hardware(id, hardware_name, code, price, hardware_type, quantity_available)
values (1, 'Logitech Keyboard', '1111', 550.50, 'OTHER', 90);

insert into hardware(id, hardware_name, code, price, hardware_type, quantity_available)
values (2, 'DELL Monitor', '2222', 1200, 'OTHER', 15);

insert into hardware(id, hardware_name, code, price, hardware_type, quantity_available)
values (3, 'NVIDIA 1080', '3333', 2500, 'GPU', 8);

insert into hardware(id, hardware_name, code ,price, hardware_type, quantity_available)
values (4, 'Intel Core i7', '4444', 2800, 'CPU', 20);


insert into review(id, title, text, grade, hardware_id)
values (1, 'Super stvar', 'Najbolja ikad', 'FIVE', 1);

insert into review(id, title, text, grade, hardware_id)
values (2, 'Odlicno', 'Svima se svidjelo','FIVE', 1);

insert into review(id, title, text, grade, hardware_id)
values (3, 'Zadovoljan', 'Nemam komentara','FOUR', 2);

insert into review(id, title, text, grade, hardware_id)
values (4, 'Dobar', 'Zadovoljan kupnjom','FOUR', 2);

insert into review(id, title, text, grade, hardware_id)
values (5, 'Li la', 'Moze bolje','THREE', 3);

insert into review(id, title, text, grade, hardware_id)
values (6, 'Osrednje', 'Ok za cijenu','THREE', 3);

insert into review(id, title, text, grade, hardware_id)
values (7, 'Osrednje', 'Ok za cijenu','TWO', 4);

insert into review(id, title, text, grade, hardware_id)
values (8, 'Lose', 'Vratio u trgovinu','ONE', 4);


insert into authority (id, authority_name) values (1, 'ROLE_ADMIN');
insert into authority (id, authority_name) values (2, 'ROLE_USER');

insert into user (id, username, password)
values (1, 'admin', '$2a$10$Ub7v1c3xfpDhkey7sMtDBuOdFaP/XGKZmS1wh3Itr86uB9qCJzRZa');

insert into user (id, username, password)
values (2, 'user', '$2a$10$Ub7v1c3xfpDhkey7sMtDBuOdFaP/XGKZmS1wh3Itr86uB9qCJzRZa');

insert into user_authority (user_id, authority_id)
values (1, 1);
insert into user_authority (user_id, authority_id)
values (2, 2);