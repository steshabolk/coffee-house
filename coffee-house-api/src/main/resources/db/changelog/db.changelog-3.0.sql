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
        '2021-02-28 17:05:00', '2021-02-28 17:30:00', '2021-02-28 17:32:00', 'RECEIVED');
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
        '2021-03-17 16:14:00', '2021-03-17 18:00:00', '2021-03-17 18:04:00', 'RECEIVED');
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
        '2021-05-01 19:13:00', '2021-05-01 20:00:00', '2021-05-01 20:11:00', 'CANCELLED');
--rollback DELETE FROM orders WHERE id = 4;

--changeset steshabolk:9
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (4, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 2, 500);
--rollback DELETE FROM orders_details WHERE order_id = 4;

--changeset steshabolk:10
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 3, 565,
        '2021-05-29 20:04:00', '2021-05-29 20:58:00', '2021-05-29 20:59:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 5;

--changeset steshabolk:11
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (5, (SELECT id FROM products WHERE product_name = 'Puff with cheese and spinach'), 3, 285),
       (5, (SELECT id FROM products WHERE product_name = 'Brownie'), 2, 280);
--rollback DELETE FROM orders_details WHERE order_id = 5;

--changeset steshabolk:12
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 365,
        '2021-06-14 09:05:00', '2021-06-14 09:30:00', '2021-06-14 09:35:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 6;

--changeset steshabolk:13
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (6, (SELECT id FROM products WHERE product_name = 'Raff "Salted caramel"'), 1, 270),
       (6, (SELECT id FROM products WHERE product_name = 'Salted caramel eclair'), 1, 95);
--rollback DELETE FROM orders_details WHERE order_id = 6;

--changeset steshabolk:14
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 700,
        '2021-08-13 12:30:00', '2021-08-13 13:05:00','2021-08-13 13:09:00', 'RECEIVED');
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
        '2021-10-21 19:43:00', '2021-10-21 20:07:00', '2021-10-21 20:07:00', 'RECEIVED');
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

--changeset steshabolk:20
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 320,
        '2021-10-30 10:15:00', '2021-10-30 11:13:00', '2021-10-30 11:22:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 9;

--changeset steshabolk:21
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (9, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);
--rollback DELETE FROM orders_details WHERE order_id = 9;

--changeset steshabolk:22
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 475,
        '2021-11-03 12:28:00', '2021-11-03 13:00:00', '2021-11-03 13:00:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 10;

--changeset steshabolk:23
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (10, (SELECT id FROM products WHERE product_name = 'Pumpkin Spice Latte'), 1, 400),
       (10, (SELECT id FROM products WHERE product_name = 'Croissant'), 1, 75);
--rollback DELETE FROM orders_details WHERE order_id = 10;

--changeset steshabolk:24
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 3, 765,
        '2021-11-23 10:02:00', '2021-11-23 10:35:00', '2021-11-23 10:39:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 11;

--changeset steshabolk:25
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (11, (SELECT id FROM products WHERE product_name = 'Cappuccino "Almond"'), 1, 420),
       (11, (SELECT id FROM products WHERE product_name = 'Croissant with salmon and cream cheese'), 1, 250),
       (11, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95);
--rollback DELETE FROM orders_details WHERE order_id = 11;

--changeset steshabolk:26
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (24, (SELECT id FROM products WHERE product_name = 'Lactose-free Milk'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 24;

--changeset steshabolk:27
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 520,
        '2021-12-13 17:15:00', '2021-12-13 17:55:00', '2021-12-13 17:59:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 12;

--changeset steshabolk:28
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (12, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 1, 300),
       (12, (SELECT id FROM products WHERE product_name = 'Puff with cheese and spinach'), 1, 95),
       (12, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125);
--rollback DELETE FROM orders_details WHERE order_id = 12;

--changeset steshabolk:29
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (27, (SELECT id FROM products WHERE product_name = 'Whipped Cream')),
       (27, (SELECT id FROM products WHERE product_name = 'Gingerbread Syrup'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 27;

--changeset steshabolk:30
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 650,
        '2022-01-17 15:25:00', '2022-01-17 15:47:00', '2022-01-17 15:40:00', 'CANCELLED');
--rollback DELETE FROM orders WHERE id = 13;

--changeset steshabolk:31
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (13, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 2, 460),
       (13, (SELECT id FROM products WHERE product_name = 'Salted caramel eclair'), 2, 190);
--rollback DELETE FROM orders_details WHERE order_id = 13;

--changeset steshabolk:32
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (30, (SELECT id FROM products WHERE product_name = 'Irish Cream Syrup'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 30;

--changeset steshabolk:33
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 580,
        '2022-01-21 16:11:00', '2022-01-21 16:41:00', '2022-01-21 16:36:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 14;

--changeset steshabolk:34
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (14, (SELECT id FROM products WHERE product_name = 'Coconut chocolate cheesecake'), 1, 180),
       (14, (SELECT id FROM products WHERE product_name = 'Ham and cheese sandwich'), 2, 400);
--rollback DELETE FROM orders_details WHERE order_id = 14;

--changeset steshabolk:35
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 1865,
        '2022-03-15 19:13:00', '2022-03-15 19:27:00', '2022-03-15 19:28:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 15;

--changeset steshabolk:36
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (15, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 240),
       (15, (SELECT id FROM products WHERE product_name = 'Latte "Lavender-Coconut"'), 1, 320),
       (15, (SELECT id FROM products WHERE product_name = 'Pumpkin Spice Latte'), 1, 470),
       (15, (SELECT id FROM products WHERE product_name = 'Chicken and mushroom pie'), 2, 200),
       (15, (SELECT id FROM products WHERE product_name = 'Turkey and tomato sandwich'), 1, 200),
       (15, (SELECT id FROM products WHERE product_name = 'Coconut chocolate cheesecake'), 1, 180),
       (15, (SELECT id FROM products WHERE product_name = 'Classic cheesecake'), 1, 170),
       (15, (SELECT id FROM products WHERE product_name = 'Cinnamon roll'), 1, 85);
--rollback DELETE FROM orders_details WHERE order_id = 15;

--changeset steshabolk:37
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (34, (SELECT id FROM products WHERE product_name = 'Marshmallows')),
       (35, (SELECT id FROM products WHERE product_name = 'Marshmallows')),
       (36, (SELECT id FROM products WHERE product_name = 'Coconut Milk'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id IN (34, 35, 36);

--changeset steshabolk:38
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 415,
        '2022-03-27 18:00:00', '2022-03-27 18:30:00', '2022-03-27 18:31:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 16;

--changeset steshabolk:39
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (16, (SELECT id FROM products WHERE product_name = 'Brownie'), 2, 280),
       (16, (SELECT id FROM products WHERE product_name = 'Puff with cranberries, cloudberries and mascarpone'), 1, 135);
--rollback DELETE FROM orders_details WHERE order_id = 16;

--changeset steshabolk:40
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 910,
        '2022-04-20 13:19:00', '2022-04-20 13:43:00', '2022-04-20 13:44:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 17;

--changeset steshabolk:41
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (17, (SELECT id FROM products WHERE product_name = 'Cappuccino Grande'), 1, 280),
       (17, (SELECT id FROM products WHERE product_name = 'Turkey and tomato sandwich'), 1, 200),
       (17, (SELECT id FROM products WHERE product_name = 'Classic cheesecake'), 1, 170),
       (17, (SELECT id FROM products WHERE product_name = 'Cappuccino Grande'), 1, 260);
--rollback DELETE FROM orders_details WHERE order_id = 17;

--changeset steshabolk:42
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (44, (SELECT id FROM products WHERE product_name = 'Banana Milk')),
       (47, (SELECT id FROM products WHERE product_name = 'Whipped Cream')),
       (47, (SELECT id FROM products WHERE product_name = 'Irish Cream Syrup'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id IN (44, 47);

--changeset steshabolk:43
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 665,
        '2022-05-06 14:21:00', '2022-05-06 14:43:00', '2022-05-06 14:45:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 18;

--changeset steshabolk:44
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (18, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95),
       (18, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320),
       (18, (SELECT id FROM products WHERE product_name = 'Raf'), 1, 250);
--rollback DELETE FROM orders_details WHERE order_id = 18;

--changeset steshabolk:45
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (50, (SELECT id FROM products WHERE product_name = 'Lactose-free Milk'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 50;

--changeset steshabolk:46
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 3, 1250,
        '2022-05-23 17:58:00', '2022-05-23 18:18:00', '2022-05-23 18:21:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 19;

--changeset steshabolk:47
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (19, (SELECT id FROM products WHERE product_name = 'Pumpkin Spice Latte'), 2, 800),
       (19, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125),
       (19, (SELECT id FROM products WHERE product_name = 'Croissant'), 1, 75),
       (19, (SELECT id FROM products WHERE product_name = 'Croissant with salmon and cream cheese'), 1, 250);
--rollback DELETE FROM orders_details WHERE order_id = 19;

--changeset steshabolk:48
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 300,
        '2022-06-10 20:15:00', '2022-06-10 20:27:00', '2022-06-10 20:32:00', 'CANCELLED');
--rollback DELETE FROM orders WHERE id = 20;

--changeset steshabolk:49
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (20, (SELECT id FROM products WHERE product_name = 'Pancakes'), 1, 300);
--rollback DELETE FROM orders_details WHERE order_id = 20;

--changeset steshabolk:50
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 1200,
        '2022-07-01 17:41:00', '2022-07-01 17:52:00', '2022-07-01 17:52:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 21;

--changeset steshabolk:51
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (21, (SELECT id FROM products WHERE product_name = 'Pumpkin Spice Latte'), 3, 1200);
--rollback DELETE FROM orders_details WHERE order_id = 21;

--changeset steshabolk:52
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 415,
        '2022-10-19 12:23:00', '2022-10-19 12:50:00', '2022-10-19 12:51:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 22;

--changeset steshabolk:53
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (22, (SELECT id FROM products WHERE product_name = 'Latte "Lavender-Coconut"'), 1, 290),
       (22, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125);
--rollback DELETE FROM orders_details WHERE order_id = 22;

--changeset steshabolk:54
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 425,
        '2022-12-18 18:15:00', '2022-12-18 18:55:00', '2022-12-18 18:59:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 23;

--changeset steshabolk:55
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (23, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 1, 300),
       (23, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125);
--rollback DELETE FROM orders_details WHERE order_id = 23;

--changeset steshabolk:56
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 365,
        '2023-01-17 09:09:00', '2023-01-17 09:39:00', '2023-01-17 09:39:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 24;

--changeset steshabolk:57
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (24, (SELECT id FROM products WHERE product_name = 'Raff "Salted caramel"'), 1, 270),
       (24, (SELECT id FROM products WHERE product_name = 'Salted caramel eclair'), 1, 95);
--rollback DELETE FROM orders_details WHERE order_id = 24;
