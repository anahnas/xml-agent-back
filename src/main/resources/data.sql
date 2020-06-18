insert into user_table(username, password) values ('agent', 'agent');

insert into fuel_type(type) values('Benzin');
insert into fuel_type(type) values('Dizel');
insert into fuel_type(type) values('Plin');

insert into car_brand(name) values ('Nissan');
insert into car_brand(name) values ('Chevrolet');
insert into car_brand(name) values ('Audi');

insert into transmission(type) values('Manual');
insert into transmission(type) values('Automatic');
insert into transmission(type) values('Semiautomatic');

insert into car_class(car_class) values('Limo');
insert into car_class(car_class) values('Old timer');
insert into car_class(car_class) values('SUV');
insert into car_class(car_class) values('Minivan');
insert into car_class(car_class) values('Caravan');
insert into car_class(car_class) values('Cabriolet');
insert into car_class(car_class) values('Pick up');

insert into car_model(name, car_brand_id, car_class_id) values('A7', 3, 1);
insert into car_model(name, car_brand_id, car_class_id) values('A6', 3, 2);
insert into car_model(name, car_brand_id, car_class_id) values('A4', 3, 3);
insert into car_model(name, car_brand_id, car_class_id) values('Aveo', 2, 4);
insert into car_model(name, car_brand_id, car_class_id) values('Versa', 1, 5);

insert into car(owner_id, car_model_id, fuel_type_id, transmission_id, waiver, limited_kms) values (1, 1, 1, 1, 'false', 'false');
insert into car(owner_id, car_model_id, fuel_type_id, transmission_id, waiver, limited_kms) values (1, 2, 2, 2, 'false', 'false');

insert into rent_request(start_date, end_date, client_id, status) values ('2020-05-05 19:58:00.000000', '2020-06-10 02:00', '2', 'PAID');
insert into rent_request(start_date, end_date, client_id, status) values ('2020-05-05 17:58:00.000000', '2020-06-10 02:00:00', '2', 'PAID');
insert into rent_request(start_date, end_date, client_id, status) values ('2020-02-05 07:58:00.000000', '2020-09-10 02:00:00', '1', 'RESERVED');
insert into rent_request(start_date, end_date, client_id, status) values ('2020-02-05 07:58:00.000000', '2020-09-10 02:00:00', '1', 'PENDING');

insert into car_rating(comment, rating, rating_status, car_id, user_id) values('Valja', 10.0, 0, 1, 1);
