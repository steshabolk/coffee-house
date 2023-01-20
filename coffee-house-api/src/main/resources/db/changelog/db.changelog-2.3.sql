--liquibase formatted sql

--changeset steshabolk:1
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 415,
        '2021-02-19 12:23:00', '2021-02-19 12:50:00', '2021-02-19 12:51:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 1;

--changeset steshabolk:2
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (1, (SELECT id FROM products WHERE product_name = 'Latte "Lavender-Coconut"'), 1, 290),
       (1, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125);
--rollback DELETE FROM orders_details WHERE order_id = 1;

--changeset steshabolk:3
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 740,
        '2021-05-28 17:05:00', '2021-05-28 17:30:00', '2021-05-28 17:32:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 2;

--changeset steshabolk:4
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (2, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 300),
       (2, (SELECT id FROM products WHERE product_name = 'Pancakes'), 1, 300),
       (2, (SELECT id FROM products WHERE product_name = 'Walnut poppy seed roll'), 2, 140);
--rollback DELETE FROM orders_details WHERE order_id = 2;

--changeset steshabolk:5
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (3, (SELECT id FROM products WHERE product_name = 'Coconut Syrup')),
       (3, (SELECT id FROM products WHERE product_name = 'Almond Milk'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 3;

--changeset steshabolk:6
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 980,
        '2022-03-17 16:14:00', '2022-03-17 18:00:00', '2022-03-17 18:04:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 3;

--changeset steshabolk:7
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (3, (SELECT id FROM products WHERE product_name = 'Cappuccino "Almond"'), 2, 700),
       (3, (SELECT id FROM products WHERE product_name = 'Puff with cranberries, cloudberries and mascarpone'), 1, 135),
       (3, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95);
--rollback DELETE FROM orders_details WHERE order_id = 3;

--changeset steshabolk:8
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 3, 700,
        '2022-05-01 19:13:00', '2022-05-01 20:00:00', '2022-05-01 20:11:00', 'CANCELLED');
--rollback DELETE FROM orders WHERE id = 4;

--changeset steshabolk:9
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (4, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 2, 500);
--rollback DELETE FROM orders_details WHERE order_id = 4;

--changeset steshabolk:10
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 3, 565,
        '2022-05-29 20:04:00', '2022-05-29 20:58:00', '2022-05-29 20:59:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 5;

--changeset steshabolk:11
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (5, (SELECT id FROM products WHERE product_name = 'Puff with cheese and spinach'), 3, 285),
       (5, (SELECT id FROM products WHERE product_name = 'Brownie'), 2, 280);
--rollback DELETE FROM orders_details WHERE order_id = 5;

--changeset steshabolk:12
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 365,
        '2022-06-14 09:05:00', '2022-06-14 09:30:00', '2022-06-14 09:35:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 6;

--changeset steshabolk:13
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (6, (SELECT id FROM products WHERE product_name = 'Raff "Salted caramel"'), 1, 270),
       (6, (SELECT id FROM products WHERE product_name = 'Salted caramel eclair'), 1, 95);
--rollback DELETE FROM orders_details WHERE order_id = 6;

--changeset steshabolk:14
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 700,
        '2022-08-13 12:30:00', '2022-08-13 13:05:00','2022-08-13 13:09:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 7;

--changeset steshabolk:15
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (7, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 280),
       (7, (SELECT id FROM products WHERE product_name = 'Croissant with salmon and cream cheese'), 1, 250),
       (7, (SELECT id FROM products WHERE product_name = 'Classic cheesecake'), 1, 170);
--rollback DELETE FROM orders_details WHERE order_id = 7;

--changeset steshabolk:16
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (14, (SELECT id FROM products WHERE product_name = 'Banana Milk'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 14;

--changeset steshabolk:17
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 685,
        '2022-10-21 19:43:00', '2022-10-21 20:07:00', '2022-10-21 20:07:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 8;

--changeset steshabolk:18
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (8, (SELECT id FROM products WHERE product_name = 'Raf'), 1, 230),
       (8, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 260),
       (8, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125),
       (8, (SELECT id FROM products WHERE product_name = 'Walnut poppy seed roll'), 1, 70);
--rollback DELETE FROM orders_details WHERE order_id = 8;

--changeset steshabolk:19
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (17, (SELECT id FROM products WHERE product_name = 'Vanilla Syrup')),
       (17, (SELECT id FROM products WHERE product_name = 'Whipped Cream')),
       (18, (SELECT id FROM products WHERE product_name = 'Chocolate Syrup')),
       (18, (SELECT id FROM products WHERE product_name = 'Chocolate Chips'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 17 OR order_details_id = 18;
