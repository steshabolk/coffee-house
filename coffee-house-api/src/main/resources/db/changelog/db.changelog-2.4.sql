--liquibase formatted sql

--changeset steshabolk:1
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 540,
        '2021-04-02 15:45:00', '2021-04-02 16:17:00', '2021-04-02 16:19:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 9;

--changeset steshabolk:2
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (9, (SELECT id FROM products WHERE product_name = 'Cappuccino'), 1, 220),
       (9, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);
--rollback DELETE FROM orders_details WHERE order_id = 9;

--changeset steshabolk:3
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (21, (SELECT id FROM products WHERE product_name = 'Banana Milk'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 21;

--changeset steshabolk:4
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 585,
        '2021-05-26 13:17:00', '2021-05-26 13:41:00', '2021-05-26 13:31:00', 'CANCELLED');
--rollback DELETE FROM orders WHERE id = 10;

--changeset steshabolk:5
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (10, (SELECT id FROM products WHERE product_name = 'Latte "Lavender-Coconut"'), 1, 290),
       (10, (SELECT id FROM products WHERE product_name = 'Turkey and tomato sandwich'), 1, 200),
       (10, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95);
--rollback DELETE FROM orders_details WHERE order_id = 10;

--changeset steshabolk:6
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 670,
        '2022-01-17 16:01:00', '2022-01-17 16:22:00', '2022-01-17 16:22:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 11;

--changeset steshabolk:7
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (11, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 1, 350),
       (11, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);
--rollback DELETE FROM orders_details WHERE order_id = 11;

--changeset steshabolk:8
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (26, (SELECT id FROM products WHERE product_name = 'Soy Milk')),
       (26, (SELECT id FROM products WHERE product_name = 'Marshmallows'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 26;

--changeset steshabolk:9
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 430,
        '2022-07-18 19:10:00', '2022-07-18 19:33:00', '2022-07-18 19:39:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 12;

--changeset steshabolk:10
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (12, (SELECT id FROM products WHERE product_name = 'Croissant with salmon and cream cheese'), 1, 250),
       (12, (SELECT id FROM products WHERE product_name = 'Coconut chocolate cheesecake'), 1, 180);
--rollback DELETE FROM orders_details WHERE order_id = 12;

--changeset steshabolk:11
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 695,
        '2022-10-21 16:08:00', '2022-10-21 16:48:00', '2022-10-21 16:51:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 13;

--changeset steshabolk:12
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (13, (SELECT id FROM products WHERE product_name = 'Raf'), 2, 400),
       (13, (SELECT id FROM products WHERE product_name = 'Puff with cheese and spinach'), 1, 95),
       (13, (SELECT id FROM products WHERE product_name = 'Ham and cheese sandwich'), 1, 200);
--rollback DELETE FROM orders_details WHERE order_id = 13;

--changeset steshabolk:13
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (30, (SELECT id FROM products WHERE product_name = 'Vanilla Syrup'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 32;

--changeset steshabolk:14
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 460,
        '2022-11-13 09:11:00', '2022-11-13 09:29:00', '2022-11-13 09:29:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 14;

--changeset steshabolk:15
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (14, (SELECT id FROM products WHERE product_name = 'Raff "Salted caramel"'), 1, 300),
       (14, (SELECT id FROM products WHERE product_name = 'Croissant'), 1, 75),
       (14, (SELECT id FROM products WHERE product_name = 'Cinnamon roll'), 1, 85);
--rollback DELETE FROM orders_details WHERE order_id = 14;

--changeset steshabolk:16
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (33, (SELECT id FROM products WHERE product_name = 'Marshmallows'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 33;

--changeset steshabolk:17
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 525,
        '2022-11-25 17:42:00', '2022-11-25 18:15:00', '2022-11-25 18:25:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 15;

--changeset steshabolk:18
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (15, (SELECT id FROM products WHERE product_name = 'Walnut poppy seed roll'), 3, 270),
       (15, (SELECT id FROM products WHERE product_name = 'Cinnamon roll'), 3, 255);
--rollback DELETE FROM orders_details WHERE order_id = 15;
