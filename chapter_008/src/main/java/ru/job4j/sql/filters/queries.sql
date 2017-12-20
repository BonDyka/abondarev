-- В системе заданы таблицы
-- product(id, name, type_id, expired_date, price)
-- type(id, name)

-- продукты типа сыр
SELECT * FROM product WHERE type_id = (SELECT id FROM type WHERE name = 'СЫР');
-- продукты где в названии есть 'мороженое'
SELECT * FROM product WHERE name LIKE 'мороженое';
-- Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product WHERE expired_date >= (current_timestamp + INTERVAL '1 month')
                            AND expired_date <= (current_timestamp + INTERVAL '2 month');
-- Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product WHERE price = (SELECT max(price) FROM product);
-- Написать запрос, который выводит количество всех продуктов определенного типа
SELECT count, name FROM (SELECT type_id, count(type_id) FROM product GROUP BY type_id) AS c(type_id, count), type
WHERE c.type_id = type.id;
-- Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM product WHERE type_id = (SELECT id FROM type WHERE name = 'СЫР')
                            OR type_id = (SELECT id FROM type WHERE name = 'МОЛОКО');
-- Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT count, name FROM (SELECT type_id, count(type_id) FROM product GROUP BY type_id) AS c(type_id, count), type
WHERE c.type_id = type.id AND c.count < 10;
-- Вывести все продукты и их тип.
SELECT product.id, product.name, product.expired_date, product.price, type.name
  FROM product, type
  WHERE product.type_id = type.id;