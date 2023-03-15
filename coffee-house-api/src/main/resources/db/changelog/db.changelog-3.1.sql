--liquibase formatted sql

--changeset steshabolk:1
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 540,
        '2021-04-02 15:45:00', '2021-04-02 16:17:00', '2021-04-02 16:19:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 25;

--changeset steshabolk:2
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (25, (SELECT id FROM products WHERE product_name = 'Cappuccino'), 1, 220),
       (25, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);
--rollback DELETE FROM orders_details WHERE order_id = 25;

--changeset steshabolk:3
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (63, (SELECT id FROM products WHERE product_name = 'Banana Milk'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 63;

--changeset steshabolk:4
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 585,
        '2021-05-26 13:17:00', '2021-05-26 13:41:00', '2021-05-26 13:31:00', 'CANCELLED');
--rollback DELETE FROM orders WHERE id = 26;

--changeset steshabolk:5
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (26, (SELECT id FROM products WHERE product_name = 'Latte "Lavender-Coconut"'), 1, 290),
       (26, (SELECT id FROM products WHERE product_name = 'Turkey and tomato sandwich'), 1, 200),
       (26, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95);
--rollback DELETE FROM orders_details WHERE order_id = 26;

--changeset steshabolk:6
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 670,
        '2021-06-17 16:01:00', '2021-06-17 16:22:00', '2021-06-17 16:22:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 27;

--changeset steshabolk:7
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (27, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 1, 350),
       (27, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);
--rollback DELETE FROM orders_details WHERE order_id = 27;

--changeset steshabolk:8
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (68, (SELECT id FROM products WHERE product_name = 'Soy Milk')),
       (68, (SELECT id FROM products WHERE product_name = 'Marshmallows'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 68;

--changeset steshabolk:9
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 430,
        '2021-07-18 19:10:00', '2021-07-18 19:33:00', '2021-07-18 19:39:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 28;

--changeset steshabolk:10
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (28, (SELECT id FROM products WHERE product_name = 'Croissant with salmon and cream cheese'), 1, 250),
       (28, (SELECT id FROM products WHERE product_name = 'Coconut chocolate cheesecake'), 1, 180);
--rollback DELETE FROM orders_details WHERE order_id = 28;

--changeset steshabolk:11
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 695,
        '2021-10-21 16:08:00', '2021-10-21 16:48:00', '2021-10-21 16:51:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 29;

--changeset steshabolk:12
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (29, (SELECT id FROM products WHERE product_name = 'Raf'), 2, 400),
       (29, (SELECT id FROM products WHERE product_name = 'Puff with cheese and spinach'), 1, 95),
       (29, (SELECT id FROM products WHERE product_name = 'Ham and cheese sandwich'), 1, 200);
--rollback DELETE FROM orders_details WHERE order_id = 29;

--changeset steshabolk:13
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (72, (SELECT id FROM products WHERE product_name = 'Vanilla Syrup'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 72;

--changeset steshabolk:14
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 460,
        '2021-11-13 09:11:00', '2021-11-13 09:29:00', '2021-11-13 09:29:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 30;

--changeset steshabolk:15
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (30, (SELECT id FROM products WHERE product_name = 'Raff "Salted caramel"'), 1, 300),
       (30, (SELECT id FROM products WHERE product_name = 'Croissant'), 1, 75),
       (30, (SELECT id FROM products WHERE product_name = 'Cinnamon roll'), 1, 85);
--rollback DELETE FROM orders_details WHERE order_id = 30;

--changeset steshabolk:16
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (75, (SELECT id FROM products WHERE product_name = 'Marshmallows'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 75;

--changeset steshabolk:17
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 525,
        '2021-11-25 17:42:00', '2021-11-25 18:15:00', '2021-11-25 18:25:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 31;

--changeset steshabolk:18
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (31, (SELECT id FROM products WHERE product_name = 'Walnut poppy seed roll'), 3, 270),
       (31, (SELECT id FROM products WHERE product_name = 'Cinnamon roll'), 3, 255);
--rollback DELETE FROM orders_details WHERE order_id = 31;

--changeset steshabolk:19
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 690,
        '2022-01-25 14:11:00', '2022-01-25 14:33:00', '2022-01-25 14:44:00', 'CANCELLED');
--rollback DELETE FROM orders WHERE id = 32;

--changeset steshabolk:20
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (32, (SELECT id FROM products WHERE product_name = 'Pumpkin Spice Latte'), 1, 400),
       (32, (SELECT id FROM products WHERE product_name = 'Latte "Lavender-Coconut"'), 1, 290);
--rollback DELETE FROM orders_details WHERE order_id = 32;

--changeset steshabolk:21
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 1040,
        '2022-01-31 09:11:00', '2022-01-31 09:40:00', '2022-01-31 09:44:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 33;

--changeset steshabolk:22
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (33, (SELECT id FROM products WHERE product_name = 'Pancakes'), 1, 300),
       (33, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320),
       (33, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 2, 420);
--rollback DELETE FROM orders_details WHERE order_id = 33;

--changeset steshabolk:23
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 615,
        '2022-03-18 19:31:00', '2022-03-18 20:00:00', '2022-03-18 20:04:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 34;

--changeset steshabolk:24
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (34, (SELECT id FROM products WHERE product_name = 'Puff with cranberries, cloudberries and mascarpone'), 1, 135),
       (34, (SELECT id FROM products WHERE product_name = 'Croissant with salmon and cream cheese'), 1, 250),
       (34, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 230);
--rollback DELETE FROM orders_details WHERE order_id = 34;

--changeset steshabolk:25
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (87, (SELECT id FROM products WHERE product_name = 'Gingerbread Syrup'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 87;

--changeset steshabolk:26
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 685,
        '2022-04-12 20:43:00', '2022-04-12 21:21:00', '2022-04-12 21:24:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 35;

--changeset steshabolk:27
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (35, (SELECT id FROM products WHERE product_name = 'Cinnamon roll'), 1, 85),
       (35, (SELECT id FROM products WHERE product_name = 'Walnut poppy seed roll'), 1, 70),
       (35, (SELECT id FROM products WHERE product_name = 'Latte'), 1, 220),
       (35, (SELECT id FROM products WHERE product_name = 'Cappuccino Grande'), 1, 310);
--rollback DELETE FROM orders_details WHERE order_id = 35;

--changeset steshabolk:28
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (90, (SELECT id FROM products WHERE product_name = 'Lactose-free Milk')),
       (91, (SELECT id FROM products WHERE product_name = 'Chocolate Chips')),
       (91, (SELECT id FROM products WHERE product_name = 'Coconut Milk'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id IN (90, 91);

--changeset steshabolk:29
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 350,
        '2022-04-22 18:43:00', '2022-04-22 19:05:00', '2022-04-22 19:07:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 36;

--changeset steshabolk:30
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (36, (SELECT id FROM products WHERE product_name = 'Coconut chocolate cheesecake'), 1, 180),
       (36, (SELECT id FROM products WHERE product_name = 'Classic cheesecake'), 1, 170);
--rollback DELETE FROM orders_details WHERE order_id = 36;

--changeset steshabolk:31
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 275,
        '2022-05-25 19:02:00', '2022-05-25 19:42:00', '2022-05-25 19:49:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 37;

--changeset steshabolk:32
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (37, (SELECT id FROM products WHERE product_name = 'Raf'), 1, 200),
       (37, (SELECT id FROM products WHERE product_name = 'Croissant'), 1, 75);
--rollback DELETE FROM orders_details WHERE order_id = 37;

--changeset steshabolk:33
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (94, (SELECT id FROM products WHERE product_name = 'Hazelnut Syrup'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 94;

--changeset steshabolk:34
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 325,
        '2022-06-13 15:13:00', '2022-06-13 15:38:00', '2022-06-13 15:36:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 38;

--changeset steshabolk:35
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (38, (SELECT id FROM products WHERE product_name = 'Cappuccino Grande'), 1, 230),
       (38, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95);
--rollback DELETE FROM orders_details WHERE order_id = 38;

--changeset steshabolk:36
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (96, (SELECT id FROM products WHERE product_name = 'Raspberry Syrup'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 96;

--changeset steshabolk:37
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 150,
        '2022-07-20 16:26:00', '2022-07-20 16:55:00', '2022-07-20 16:59:00', 'CANCELLED');
--rollback DELETE FROM orders WHERE id = 39;

--changeset steshabolk:38
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (39, (SELECT id FROM products WHERE product_name = 'Croissant'), 2, 150);
--rollback DELETE FROM orders_details WHERE order_id = 39;

--changeset steshabolk:39
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 760,
        '2022-08-03 15:03:00', '2022-08-03 15:40:00', '2022-08-03 15:42:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 40;

--changeset steshabolk:40
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (40, (SELECT id FROM products WHERE product_name = 'Turkey and tomato sandwich'), 1, 200),
       (40, (SELECT id FROM products WHERE product_name = 'Ham and cheese sandwich'), 1, 200),
       (40, (SELECT id FROM products WHERE product_name = 'Raf'), 2, 360);
--rollback DELETE FROM orders_details WHERE order_id = 40;

--changeset steshabolk:41
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 465,
        '2022-08-29 12:04:00', '2022-08-29 12:24:00', '2022-08-29 12:20:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 41;

--changeset steshabolk:42
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (41, (SELECT id FROM products WHERE product_name = 'Raff "Salted caramel"'), 1, 340),
       (41, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125);
--rollback DELETE FROM orders_details WHERE order_id = 41;

--changeset steshabolk:43
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (102, (SELECT id FROM products WHERE product_name = 'Lactose-free Milk'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 102;

--changeset steshabolk:44
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 295,
        '2022-10-09 17:54:00', '2022-10-09 18:40:00', '2022-10-09 18:45:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 42;

--changeset steshabolk:45
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (42, (SELECT id FROM products WHERE product_name = 'Brownie'), 1, 140),
       (42, (SELECT id FROM products WHERE product_name = 'Berry tart'), 1, 155);
--rollback DELETE FROM orders_details WHERE order_id = 42;

--changeset steshabolk:46
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 670,
        '2022-11-17 10:01:00', '2022-11-17 10:22:00', '2022-11-17 10:22:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 42;

--changeset steshabolk:47
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (42, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 1, 350),
       (42, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);
--rollback DELETE FROM orders_details WHERE order_id = 42;

--changeset steshabolk:48
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 275,
        '2023-01-25 19:02:00', '2023-01-25 19:42:00', '2023-01-25 19:49:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 43;

--changeset steshabolk:49
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (43, (SELECT id FROM products WHERE product_name = 'Raf'), 1, 200),
       (43, (SELECT id FROM products WHERE product_name = 'Croissant'), 1, 75);
--rollback DELETE FROM orders_details WHERE order_id = 43;

--changeset steshabolk:50
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (108, (SELECT id FROM products WHERE product_name = 'Vanilla Syrup'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 108;
