CREATE TABLE IF NOT EXISTS items (
  id VARCHAR (10) UNIQUE NOT NULL,
  name VARCHAR (255) NOT NULL,
  description TEXT NOT NULL,
  create_date TIMESTAMP NOT NULL
);
CREATE TABLE IF NOT EXISTS comments (
  author VARCHAR(100),
  comment TEXT,
  item_id VARCHAR (10) REFERENCES items (id)
);
INSERT INTO items(id, name, description, create_date) VALUES (?, ?, ?, ?);
INSERT INTO comments(comment, item_id) VALUES (?, ?);
SELECT id, name, description, create_date FROM items;
SELECT comment FROM comments WHERE item_id = ?;