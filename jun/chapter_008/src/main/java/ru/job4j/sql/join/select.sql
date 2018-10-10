-- Вывести все машины
SELECT c.name AS name, b.name AS body, e.name AS engine, t.name AS transmission
FROM car AS c INNER JOIN car_body AS b ON c.body_id = b.id
INNER JOIN engine AS e ON c.engine_id = e.id
INNER JOIN transmission AS t ON c.transmission_id = t.id;
-- Вывести неиспользуемые типы кузова
SELECT b.name FROM car_body AS b
LEFT OUTER JOIN car ON b.id = car.body_id
WHERE car.body_id IS NULL;
-- Вывести неиспользуемые типы двигателей
SELECT e.name FROM engine AS e
  LEFT OUTER JOIN car ON e.id = car.engine_id
WHERE car.engine_id IS NULL;
-- Вывести неиспользуемые трансмиссии
SELECT t.name FROM transmission AS t
LEFT OUTER JOIN car ON t.id = car.transmission_id
WHERE car.transmission_id IS NULL;