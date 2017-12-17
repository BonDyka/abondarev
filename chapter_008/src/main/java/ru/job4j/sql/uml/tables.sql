CREATE TABLE roles (
  id SERIAL PRIMARY KEY,
  name VARCHAR (200)
);

CREATE TABLE rules (
  id SERIAL PRIMARY KEY,
  name VARCHAR (200)
);

CREATE TABLE role_rules (
  role_id INTEGER REFERENCES roles(id),
  rules_id INTEGER REFERENCES rules(id)
);

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name VARCHAR (200),
  role_id INTEGER REFERENCES roles(id)
);

CREATE TABLE categories (
  id SERIAL PRIMARY KEY,
  name VARCHAR (200)
);

CREATE TABLE states (
  id SERIAL PRIMARY KEY,
  name VARCHAR (200)
);

CREATE TABLE items (
  id SERIAL PRIMARY KEY ,
  item_id VARCHAR (10),
  name VARCHAR (200),
  description TEXT,
  create_date TIMESTAMP,
  user_id INTEGER REFERENCES users(id),
  category_id INTEGER REFERENCES categories(id),
  state_id INTEGER REFERENCES states(id)
);

CREATE TABLE attaches (
  id SERIAL PRIMARY KEY,
  name VARCHAR (200),
  content BYTEA,
  item_id INTEGER REFERENCES items(id)
);

CREATE TABLE comments (
  id SERIAL PRIMARY KEY,
  name TEXT,
  create_date TIMESTAMP,
  item_id INTEGER REFERENCES items(id)
);
