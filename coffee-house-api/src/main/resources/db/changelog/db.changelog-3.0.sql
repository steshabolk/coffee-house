--liquibase formatted sql

--changeset steshabolk:1
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 415,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '270 days' + TIME '12:23:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '270 days' + TIME '12:50:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '270 days' + TIME '12:51:00'),
        'RECEIVED');

--changeset steshabolk:2
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (1, (SELECT id FROM products WHERE product_name = 'Latte "Lavender-Coconut"'), 1, 290),
       (1, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125);

--changeset steshabolk:3
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 740,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '258 days' + TIME '17:05:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '258 days' + TIME '17:30:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '258 days' + TIME '17:32:00'),
        'RECEIVED');

--changeset steshabolk:4
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (2, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 300),
       (2, (SELECT id FROM products WHERE product_name = 'Pancakes'), 1, 300),
       (2, (SELECT id FROM products WHERE product_name = 'Walnut poppy seed roll'), 2, 140);

--changeset steshabolk:5
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (3, (SELECT id FROM products WHERE product_name = 'Coconut Syrup')),
       (3, (SELECT id FROM products WHERE product_name = 'Almond Milk'));

--changeset steshabolk:6
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 980,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '231 days' + TIME '16:14:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '231 days' + TIME '18:00:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '231 days' + TIME '18:04:00'),
        'RECEIVED');

--changeset steshabolk:7
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (3, (SELECT id FROM products WHERE product_name = 'Cappuccino "Almond"'), 2, 700),
       (3, (SELECT id FROM products WHERE product_name = 'Puff with cranberries, cloudberries and mascarpone'), 1, 135),
       (3, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95);

--changeset steshabolk:8
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 3, 700,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '207 days' + TIME '19:13:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '207 days' + TIME '20:00:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '207 days' + TIME '20:11:00'),
        'CANCELLED');

--changeset steshabolk:9
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (4, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 2, 500);

--changeset steshabolk:10
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 3, 565,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '193 days' + TIME '20:04:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '193 days' + TIME '20:58:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '193 days' + TIME '20:59:00'),
        'RECEIVED');

--changeset steshabolk:11
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (5, (SELECT id FROM products WHERE product_name = 'Puff with cheese and spinach'), 3, 285),
       (5, (SELECT id FROM products WHERE product_name = 'Brownie'), 2, 280);

--changeset steshabolk:12
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 365,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '172 days' + TIME '09:05:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '172 days' + TIME '09:30:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '172 days' + TIME '09:35:00'),
        'RECEIVED');

--changeset steshabolk:13
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (6, (SELECT id FROM products WHERE product_name = 'Raff "Salted caramel"'), 1, 270),
       (6, (SELECT id FROM products WHERE product_name = 'Salted caramel eclair'), 1, 95);

--changeset steshabolk:14
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 700,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '154 days' + TIME '12:30:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '154 days' + TIME '13:05:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '154 days' + TIME '13:09:00'),
        'RECEIVED');

--changeset steshabolk:15
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (7, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 280),
       (7, (SELECT id FROM products WHERE product_name = 'Croissant with salmon and cream cheese'), 1, 250),
       (7, (SELECT id FROM products WHERE product_name = 'Classic cheesecake'), 1, 170);

--changeset steshabolk:16
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (14, (SELECT id FROM products WHERE product_name = 'Banana Milk'));

--changeset steshabolk:17
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 685,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '96 days' + TIME '19:43:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '96 days' + TIME '20:07:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '96 days' + TIME '20:07:00'),
        'RECEIVED');

--changeset steshabolk:18
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (8, (SELECT id FROM products WHERE product_name = 'Raf'), 1, 230),
       (8, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 260),
       (8, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125),
       (8, (SELECT id FROM products WHERE product_name = 'Walnut poppy seed roll'), 1, 70);

--changeset steshabolk:19
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (17, (SELECT id FROM products WHERE product_name = 'Vanilla Syrup')),
       (17, (SELECT id FROM products WHERE product_name = 'Whipped Cream')),
       (18, (SELECT id FROM products WHERE product_name = 'Chocolate Syrup')),
       (18, (SELECT id FROM products WHERE product_name = 'Chocolate Chips'));

--changeset steshabolk:20
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 320,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '79 days' + TIME '10:15:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '79 days' + TIME '11:13:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '79 days' + TIME '11:22:00'),
        'RECEIVED');

--changeset steshabolk:21
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (9, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);

--changeset steshabolk:22
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 475,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '30 days' + TIME '12:28:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '30 days' + TIME '13:00:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '30 days' + TIME '13:00:00'),
        'RECEIVED');

--changeset steshabolk:23
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (10, (SELECT id FROM products WHERE product_name = 'Pumpkin Spice Latte'), 1, 400),
       (10, (SELECT id FROM products WHERE product_name = 'Croissant'), 1, 75);

--changeset steshabolk:24
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 3, 765,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '17 days' + TIME '10:02:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '17 days' + TIME '10:35:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '17 days' + TIME '10:39:00'),
        'RECEIVED');

--changeset steshabolk:25
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (11, (SELECT id FROM products WHERE product_name = 'Cappuccino "Almond"'), 1, 420),
       (11, (SELECT id FROM products WHERE product_name = 'Croissant with salmon and cream cheese'), 1, 250),
       (11, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95);

--changeset steshabolk:26
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (24, (SELECT id FROM products WHERE product_name = 'Lactose-free Milk'));

--changeset steshabolk:27
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 520,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '9 days' + TIME '17:15:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '9 days' + TIME '17:55:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '9 days' + TIME '17:59:00'),
        'RECEIVED');

--changeset steshabolk:28
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (12, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 1, 300),
       (12, (SELECT id FROM products WHERE product_name = 'Puff with cheese and spinach'), 1, 95),
       (12, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125);

--changeset steshabolk:29
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (27, (SELECT id FROM products WHERE product_name = 'Whipped Cream')),
       (27, (SELECT id FROM products WHERE product_name = 'Gingerbread Syrup'));

--changeset steshabolk:30
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 650,
        (CURRENT_DATE - INTERVAL '340 days' + TIME '15:25:00'),
        (CURRENT_DATE - INTERVAL '340 days' + TIME '15:47:00'),
        (CURRENT_DATE - INTERVAL '340 days' + TIME '15:40:00'),
        'CANCELLED');

--changeset steshabolk:31
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (13, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 2, 460),
       (13, (SELECT id FROM products WHERE product_name = 'Salted caramel eclair'), 2, 190);

--changeset steshabolk:32
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (30, (SELECT id FROM products WHERE product_name = 'Irish Cream Syrup'));

--changeset steshabolk:33
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 580,
        (CURRENT_DATE - INTERVAL '313 days' + TIME '16:11:00'),
        (CURRENT_DATE - INTERVAL '313 days' + TIME '16:41:00'),
        (CURRENT_DATE - INTERVAL '313 days' + TIME '16:36:00'),
        'RECEIVED');

--changeset steshabolk:34
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (14, (SELECT id FROM products WHERE product_name = 'Coconut chocolate cheesecake'), 1, 180),
       (14, (SELECT id FROM products WHERE product_name = 'Ham and cheese sandwich'), 2, 400);

--changeset steshabolk:35
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 1865,
        (CURRENT_DATE - INTERVAL '288 days' + TIME '19:13:00'),
        (CURRENT_DATE - INTERVAL '288 days' + TIME '19:27:00'),
        (CURRENT_DATE - INTERVAL '288 days' + TIME '19:28:00'),
        'RECEIVED');

--changeset steshabolk:36
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (15, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 240),
       (15, (SELECT id FROM products WHERE product_name = 'Latte "Lavender-Coconut"'), 1, 320),
       (15, (SELECT id FROM products WHERE product_name = 'Pumpkin Spice Latte'), 1, 470),
       (15, (SELECT id FROM products WHERE product_name = 'Chicken and mushroom pie'), 2, 200),
       (15, (SELECT id FROM products WHERE product_name = 'Turkey and tomato sandwich'), 1, 200),
       (15, (SELECT id FROM products WHERE product_name = 'Coconut chocolate cheesecake'), 1, 180),
       (15, (SELECT id FROM products WHERE product_name = 'Classic cheesecake'), 1, 170),
       (15, (SELECT id FROM products WHERE product_name = 'Cinnamon roll'), 1, 85);

--changeset steshabolk:37
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (34, (SELECT id FROM products WHERE product_name = 'Marshmallows')),
       (35, (SELECT id FROM products WHERE product_name = 'Marshmallows')),
       (36, (SELECT id FROM products WHERE product_name = 'Coconut Milk'));

--changeset steshabolk:38
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 415,
        (CURRENT_DATE - INTERVAL '266 days' + TIME '18:00:00'),
        (CURRENT_DATE - INTERVAL '266 days' + TIME '18:30:00'),
        (CURRENT_DATE - INTERVAL '266 days' + TIME '18:31:00'),
        'RECEIVED');

--changeset steshabolk:39
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (16, (SELECT id FROM products WHERE product_name = 'Brownie'), 2, 280),
       (16, (SELECT id FROM products WHERE product_name = 'Puff with cranberries, cloudberries and mascarpone'), 1, 135);

--changeset steshabolk:40
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 910,
        (CURRENT_DATE - INTERVAL '251 days' + TIME '13:19:00'),
        (CURRENT_DATE - INTERVAL '251 days' + TIME '13:43:00'),
        (CURRENT_DATE - INTERVAL '251 days' + TIME '13:44:00'),
        'RECEIVED');

--changeset steshabolk:41
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (17, (SELECT id FROM products WHERE product_name = 'Cappuccino Grande'), 1, 280),
       (17, (SELECT id FROM products WHERE product_name = 'Turkey and tomato sandwich'), 1, 200),
       (17, (SELECT id FROM products WHERE product_name = 'Classic cheesecake'), 1, 170),
       (17, (SELECT id FROM products WHERE product_name = 'Cappuccino Grande'), 1, 260);

--changeset steshabolk:42
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (44, (SELECT id FROM products WHERE product_name = 'Banana Milk')),
       (47, (SELECT id FROM products WHERE product_name = 'Whipped Cream')),
       (47, (SELECT id FROM products WHERE product_name = 'Irish Cream Syrup'));

--changeset steshabolk:43
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 665,
        (CURRENT_DATE - INTERVAL '202 days' + TIME '14:21:00'),
        (CURRENT_DATE - INTERVAL '202 days' + TIME '14:43:00'),
        (CURRENT_DATE - INTERVAL '202 days' + TIME '14:45:00'),
        'RECEIVED');

--changeset steshabolk:44
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (18, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95),
       (18, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320),
       (18, (SELECT id FROM products WHERE product_name = 'Raf'), 1, 250);

--changeset steshabolk:45
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (50, (SELECT id FROM products WHERE product_name = 'Lactose-free Milk'));

--changeset steshabolk:46
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 3, 1250,
        (CURRENT_DATE - INTERVAL '197 days' + TIME '17:58:00'),
        (CURRENT_DATE - INTERVAL '197 days' + TIME '18:18:00'),
        (CURRENT_DATE - INTERVAL '197 days' + TIME '18:21:00'),
        'RECEIVED');

--changeset steshabolk:47
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (19, (SELECT id FROM products WHERE product_name = 'Pumpkin Spice Latte'), 2, 800),
       (19, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125),
       (19, (SELECT id FROM products WHERE product_name = 'Croissant'), 1, 75),
       (19, (SELECT id FROM products WHERE product_name = 'Croissant with salmon and cream cheese'), 1, 250);

--changeset steshabolk:48
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 300,
        (CURRENT_DATE - INTERVAL '188 days' + TIME '20:15:00'),
        (CURRENT_DATE - INTERVAL '188 days' + TIME '20:27:00'),
        (CURRENT_DATE - INTERVAL '188 days' + TIME '20:32:00'),
        'CANCELLED');

--changeset steshabolk:49
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (20, (SELECT id FROM products WHERE product_name = 'Pancakes'), 1, 300);

--changeset steshabolk:50
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 1200,
        (CURRENT_DATE - INTERVAL '163 days' + TIME '17:41:00'),
        (CURRENT_DATE - INTERVAL '163 days' + TIME '17:52:00'),
        (CURRENT_DATE - INTERVAL '163 days' + TIME '17:52:00'),
        'RECEIVED');

--changeset steshabolk:51
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (21, (SELECT id FROM products WHERE product_name = 'Pumpkin Spice Latte'), 3, 1200);

--changeset steshabolk:52
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 415,
        (CURRENT_DATE - INTERVAL '68 days' + TIME '12:23:00'),
        (CURRENT_DATE - INTERVAL '68 days' + TIME '12:50:00'),
        (CURRENT_DATE - INTERVAL '68 days' + TIME '12:51:00'),
        'RECEIVED');

--changeset steshabolk:53
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (22, (SELECT id FROM products WHERE product_name = 'Latte "Lavender-Coconut"'), 1, 290),
       (22, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125);

--changeset steshabolk:54
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 2, 425,
        (CURRENT_DATE - INTERVAL '31 days' + TIME '18:15:00'),
        (CURRENT_DATE - INTERVAL '31 days' + TIME '18:55:00'),
        (CURRENT_DATE - INTERVAL '31 days' + TIME '18:59:00'),
        'RECEIVED');

--changeset steshabolk:55
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (23, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 1, 300),
       (23, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125);

--changeset steshabolk:56
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 365,
        (CURRENT_DATE - INTERVAL '10 days' + TIME '09:09:00'),
        (CURRENT_DATE - INTERVAL '10 days' + TIME '09:39:00'),
        (CURRENT_DATE - INTERVAL '10 days' + TIME '09:39:00'),
        'RECEIVED');

--changeset steshabolk:57
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (24, (SELECT id FROM products WHERE product_name = 'Raff "Salted caramel"'), 1, 270),
       (24, (SELECT id FROM products WHERE product_name = 'Salted caramel eclair'), 1, 95);
