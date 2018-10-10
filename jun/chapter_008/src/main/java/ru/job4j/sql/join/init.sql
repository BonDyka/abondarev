INSERT INTO car_body (name) VALUES ('Седан');
INSERT INTO car_body (name) VALUES ('Купе');
INSERT INTO car_body (name) VALUES ('Хетчбек');
INSERT INTO car_body (name) VALUES ('Минивен');
INSERT INTO car_body (name) VALUES ('Универсал');
INSERT INTO car_body (name) VALUES ('Кабриолет');
INSERT INTO car_body (name) VALUES ('Внедорожник');

INSERT INTO transmission (name) VALUES ('Механика');
INSERT INTO transmission (name) VALUES ('Вариатор');
INSERT INTO transmission (name) VALUES ('Робот');
INSERT INTO transmission (name) VALUES ('Автомат');

INSERT INTO engine (name) VALUES ('Бензиновый');
INSERT INTO engine (name) VALUES ('Дизельный');
INSERT INTO engine (name) VALUES ('Газовый');
INSERT INTO engine (name) VALUES ('Гибридный');

INSERT INTO car (name, body_id, engine_id, transmission_id)
VALUES ('Volkswagen Passat 1.4 TSI EcoFuel', 1, 3, 1);
INSERT INTO car (name, body_id, engine_id, transmission_id)
VALUES ('HYUNDAI Grand Starex', 4, 2, 1);
INSERT INTO car (name, body_id, engine_id, transmission_id)
VALUES ('Volkswagen Golf VII', 2, 1, 3);
INSERT INTO car (name, body_id, engine_id, transmission_id)
VALUES ('Volkswagen Transporter T5', 3, 2, 1);
INSERT INTO car (name, body_id, engine_id, transmission_id)
VALUES ('Mercedes-Benz C-klasse III (W204)', 1, 2, 4);
INSERT INTO car (name, body_id, engine_id, transmission_id)
VALUES ('Toyota Corolla X (E140, E150)', 1, 1, 1);
INSERT INTO car (name, body_id, engine_id, transmission_id)
VALUES ('Subaru Outback II', 5, 1, 4);
INSERT INTO car (name, body_id, engine_id, transmission_id)
VALUES ('Audi Q5 I', 7, 1, 4);

