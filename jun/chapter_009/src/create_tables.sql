CREATE TABLE IF NOT EXISTS country(
  id SERIAL PRIMARY KEY NOT NULL ,
  name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS cities(
  id SERIAL PRIMARY KEY NOT NULL,
  name TEXT NOT NULL,
  country_id INTEGER REFERENCES country(id)
);

CREATE TABLE roles(
  id SERIAL PRIMARY KEY NOT NULL,
  name TEXT NOT NULL
);

CREATE TABLE users(
  id SERIAL PRIMARY KEY NOT NULL,
  name TEXT NOT NULL,
  login TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL,
  email TEXT NOT NULL UNIQUE,
  create_date TIMESTAMP NOT NULL,
  role_id INTEGER REFERENCES roles(id),
  city_id INTEGER REFERENCES cities(id)
);

/* fill table roles */
INSERT INTO roles(name) SELECT 'admin' WHERE NOT EXISTS (SELECT name FROM roles WHERE name = 'admin');
INSERT INTO roles(name) SELECT 'user' WHERE NOT EXISTS (SELECT name FROM roles WHERE name = 'user');

/* fill table countries */
INSERT INTO country(name) SELECT 'Russia' WHERE NOT EXISTS (SELECT name FROM country WHERE name = 'Russia');
INSERT INTO country(name) SELECT 'Ukraine' WHERE NOT EXISTS (SELECT name FROM country WHERE name = 'Ukraine');
INSERT INTO country(name) SELECT 'USA' WHERE NOT EXISTS (SELECT name FROM country WHERE name = 'USA');
INSERT INTO country(name) SELECT 'Belarus' WHERE NOT EXISTS (SELECT name FROM country WHERE name = 'Belarus');

/* fill table cities */
INSERT INTO cities(name, country_id) SELECT 'Moscow', (SELECT id FROM country WHERE name='Russia')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'Moscow');
INSERT INTO cities(name, country_id) SELECT 'Nizhny Novgorod', (SELECT id FROM country WHERE name='Russia')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'Nizhny Novgorod');
INSERT INTO cities(name, country_id) SELECT 'Saint-Petersburg', (SELECT id FROM country WHERE name='Russia')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'Saint-Petersburg');
INSERT INTO cities(name, country_id) SELECT 'Krasnoyarsk', (SELECT id FROM country WHERE name='Russia')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'Krasnoyarsk');

INSERT INTO cities(name, country_id) SELECT 'Kyiv', (SELECT id FROM country WHERE name='Ukraine')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'Kyiv');
INSERT INTO cities(name, country_id) SELECT 'Kharkiv', (SELECT id FROM country WHERE name='Ukraine')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'Kharkiv');
INSERT INTO cities(name, country_id) SELECT 'Lviv', (SELECT id FROM country WHERE name='Ukraine')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'Lviv');

INSERT INTO cities(name, country_id) SELECT 'Los Angeles', (SELECT id FROM country WHERE name='USA')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'Los Angeles');
INSERT INTO cities(name, country_id) SELECT 'Boston', (SELECT id FROM country WHERE name='USA')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'Boston');
INSERT INTO cities(name, country_id) SELECT 'New York City', (SELECT id FROM country WHERE name='USA')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'New York City');
INSERT INTO cities(name, country_id) SELECT 'Philadelphia', (SELECT id FROM country WHERE name='USA')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'Philadelphia');

INSERT INTO cities(name, country_id) SELECT 'Minsk', (SELECT id FROM country WHERE name='Belarus')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'Minsk');
INSERT INTO cities(name, country_id) SELECT 'Gomel', (SELECT id FROM country WHERE name='Belarus')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'Gomel');
INSERT INTO cities(name, country_id) SELECT 'Vitebsk', (SELECT id FROM country WHERE name='Belarus')
                                     WHERE NOT EXISTS (SELECT name FROM cities WHERE name = 'Vitebsk');


/* fill table users */
INSERT INTO users(name, login, password, email, create_date, role_id, city_id)
    SELECT 'admin', 'admin', 'admin', 'admin@mail.ru', now(), 1, 1
    WHERE NOT EXISTS (SELECT login, email FROM users WHERE login='admin' AND email='admin@mail.ru');
INSERT INTO users(name, login, password, email, create_date, role_id, city_id)
    SELECT 'test', 'test', 'test', 'test@mail.ru', now(), 2, 1
    WHERE NOT EXISTS (SELECT login, email FROM users WHERE login='admin' AND email='admin@mail.ru');