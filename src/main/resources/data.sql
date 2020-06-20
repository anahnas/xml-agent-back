insert into user_table(username, password) values ('agent', 'agent');

insert into fuel_type(type) values('Gasoline');
insert into fuel_type(type) values('Diesel');
insert into fuel_type(type) values('Gas');

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

insert into rent_request(start_date, end_date, client_id, status) values ('2020-05-05 02:00:00', '2020-06-10 02:00:00', 2, 'PAID');
insert into rent_request(start_date, end_date, client_id, status) values ('2020-05-05 02:00:00', '2020-06-10 02:00:00', 2, 'PAID');
insert into rent_request(start_date, end_date, client_id, status) values ('2020-02-05 02:00:00', '2020-09-10 02:00:00', 1, 'RESERVED');
insert into rent_request(start_date, end_date, client_id, status) values ('2020-02-05 02:00:00', '2020-09-10 02:00:00', 1, 'PENDING');

insert into rental(start_date, end_date, car_calendar_id) values ('2020-06-08 02:00:00', '2020-06-20 02:00:00',1);
insert into rental(start_date, end_date, car_calendar_id) values ('2020-07-08 02:00:00', '2020-07-20 02:00:00',1);
insert into rental(start_date, end_date, car_calendar_id) values ('2020-08-08 02:00:00', '2020-08-20 02:00:00',1);
insert into rental(start_date, end_date, car_calendar_id) values ('2020-09-08 02:00:00', '2020-09-20 02:00:00',1);
insert into rental(start_date, end_date, car_calendar_id) values ('2020-10-08 02:00:00', '2020-10-20 02:00:00',1);
insert into rental(start_date, end_date, car_calendar_id) values ('2020-11-08 02:00:00', '2020-11-20 02:00:00',1);
insert into rental(start_date, end_date, car_calendar_id) values ('2020-12-08 02:00:00', '2020-12-20 02:00:00',1);


insert into car_model(name, car_brand_id, car_class_id) values('A7', 3, 1);
insert into car_model(name, car_brand_id, car_class_id) values('A6', 3, 2);
insert into car_model(name, car_brand_id, car_class_id) values('A4', 3, 3);
insert into car_model(name, car_brand_id, car_class_id) values('Aveo', 2, 4);
insert into car_model(name, car_brand_id, car_class_id) values('Versa', 1, 5);

insert into car(owner_id, car_model_id, fuel_type_id, transmission_id, waiver, limited_kms, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage) values (1, 1, 1, 1, 1, 1, 2, 2500.45, 123.45, 333.33, 33.33);
insert into car(owner_id, car_model_id, fuel_type_id, transmission_id, waiver, limited_kms, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage) values (1, 2, 2, 2, 1, 1, 2, 2500.45, 123.45, 333.33, 33.33);

insert into advertisement(car_id, advertiser_id, start_date, end_date) values (1, 1, '2020-05-05 02:00:00', '2020-07-07 02:00:00');
insert into advertisement(car_id, advertiser_id, start_date, end_date) values (2, 1, '2020-05-05 02:00:00', '2020-07-07 02:00:00');

insert into car_rating(comment, rating, rating_status, car_id, user_id) values('Odlicno!', 10.0, 0, 1, 1);

insert into car_calendar(car_id) values (1);
insert into car_calendar(car_id) values (2);
