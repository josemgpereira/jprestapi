insert into employee(name,email,phone) values ('jose','jose@gmail.com','912222222');
insert into employee(name,email,phone) values ('maria','maria@gmail.com','913333333');
insert into employee(name,email,phone) values ('carlos','carlos@gmail.com','914444444');
insert into employee(name,email,phone) values ('page','1@gmail.com','914444444');
insert into employee(name,email,phone) values ('page','2@gmail.com','914444444');
insert into employee(name,email,phone) values ('page','3@gmail.com','914444444');
insert into employee(name,email,phone) values ('page','4@gmail.com','914444444');
insert into employee(name,email,phone) values ('page','5@gmail.com','914444444');
insert into employee(name,email,phone) values ('page','6@gmail.com','914444444');
insert into employee(name,email,phone) values ('page','7@gmail.com','914444444');
insert into employee(name,email,phone) values ('page','8@gmail.com','914444444');
insert into employee(name,email,phone) values ('page','9@gmail.com','914444444');
insert into employee(name,email,phone) values ('page','10@gmail.com','914444444');
insert into employee(name,email,phone) values ('page','11@gmail.com','914444444');
insert into employee(name,email,phone) values ('page','12@gmail.com','914444444');

insert into user(name,email,login,password) values ('jose','jose@gmail.com','jose','$2a$10$EM/vx7EVFQiWIfPsn.jNH.3nXxKtsSXUkt.VLqGGeFunz.6VY6oiG');
insert into user(name,email,login,password) values ('manuel','manuel@gmail.com','manuel','$2a$10$EM/vx7EVFQiWIfPsn.jNH.3nXxKtsSXUkt.VLqGGeFunz.6VY6oiG');

insert into role(id,name) values (1, 'ROLE_USER');
insert into role(id,name) values (2, 'ROLE_ADMIN');

insert into user_roles(user_id,role_id) values(1, 1);
insert into user_roles(user_id,role_id) values(2, 2);