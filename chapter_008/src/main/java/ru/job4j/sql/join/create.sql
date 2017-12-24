CREATE TABLE transmission (
  id SERIAL PRIMARY KEY,
  name TEXT
);

CREATE TABLE car_body (
  id SERIAL PRIMARY KEY,
  name TEXT
);

CREATE TABLE engine (
  id SERIAL PRIMARY KEY,
  name TEXT
);

CREATE TABLE car (
  id SERIAL PRIMARY KEY,
  name TEXT,
  body_id INTEGER REFERENCES car_body(id) NOT NULL,
  engine_id INTEGER REFERENCES engine(id) NOT NULL,
  transmission_id INTEGER REFERENCES transmission(id) NOT NULL
);