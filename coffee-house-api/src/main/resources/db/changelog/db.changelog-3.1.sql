--liquibase formatted sql

--changeset steshabolk:1
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 540,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '164 days' + TIME '15:45:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '164 days' + TIME '16:17:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '164 days' + TIME '16:19:00'),
        'RECEIVED');

--changeset steshabolk:2
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (25, (SELECT id FROM products WHERE product_name = 'Cappuccino'), 1, 220),
       (25, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);

--changeset steshabolk:3
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (63, (SELECT id FROM products WHERE product_name = 'Banana Milk'));

--changeset steshabolk:4
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 585,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '152 days' + TIME '13:17:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '152 days' + TIME '13:41:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '152 days' + TIME '13:31:00'),
        'CANCELLED');

--changeset steshabolk:5
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (26, (SELECT id FROM products WHERE product_name = 'Latte "Lavender-Coconut"'), 1, 290),
       (26, (SELECT id FROM products WHERE product_name = 'Turkey and tomato sandwich'), 1, 200),
       (26, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95);

--changeset steshabolk:6
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 670,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '133 days' + TIME '16:01:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '133 days' + TIME '16:22:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '133 days' + TIME '16:22:00'),
        'RECEIVED');

--changeset steshabolk:7
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (27, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 1, 350),
       (27, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);

--changeset steshabolk:8
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (68, (SELECT id FROM products WHERE product_name = 'Soy Milk')),
       (68, (SELECT id FROM products WHERE product_name = 'Marshmallows'));

--changeset steshabolk:9
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 430,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '111 days' + TIME '19:10:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '111 days' + TIME '19:33:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '111 days' + TIME '19:39:00'),
        'RECEIVED');

--changeset steshabolk:10
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (28, (SELECT id FROM products WHERE product_name = 'Croissant with salmon and cream cheese'), 1, 250),
       (28, (SELECT id FROM products WHERE product_name = 'Coconut chocolate cheesecake'), 1, 180);

--changeset steshabolk:11
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 695,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '96 days' + TIME '16:08:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '96 days' + TIME '16:48:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '96 days' + TIME '16:51:00'),
        'RECEIVED');

--changeset steshabolk:12
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (29, (SELECT id FROM products WHERE product_name = 'Raf'), 2, 400),
       (29, (SELECT id FROM products WHERE product_name = 'Puff with cheese and spinach'), 1, 95),
       (29, (SELECT id FROM products WHERE product_name = 'Ham and cheese sandwich'), 1, 200);

--changeset steshabolk:13
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (72, (SELECT id FROM products WHERE product_name = 'Vanilla Syrup'));

--changeset steshabolk:14
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 460,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '77 days' + TIME '09:11:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '77 days' + TIME '09:29:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '77 days' + TIME '09:29:00'),
        'RECEIVED');

--changeset steshabolk:15
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (30, (SELECT id FROM products WHERE product_name = 'Raff "Salted caramel"'), 1, 300),
       (30, (SELECT id FROM products WHERE product_name = 'Croissant'), 1, 75),
       (30, (SELECT id FROM products WHERE product_name = 'Cinnamon roll'), 1, 85);

--changeset steshabolk:16
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (75, (SELECT id FROM products WHERE product_name = 'Marshmallows'));

--changeset steshabolk:17
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 525,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '30 days' + TIME '17:42:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '30 days' + TIME '18:15:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '30 days' + TIME '18:25:00'),
        'RECEIVED');

--changeset steshabolk:18
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (31, (SELECT id FROM products WHERE product_name = 'Walnut poppy seed roll'), 3, 270),
       (31, (SELECT id FROM products WHERE product_name = 'Cinnamon roll'), 3, 255);

--changeset steshabolk:19
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 690,
        (CURRENT_DATE - INTERVAL '345 days' + TIME '14:11:00'),
        (CURRENT_DATE - INTERVAL '345 days' + TIME '14:33:00'),
        (CURRENT_DATE - INTERVAL '345 days' + TIME '14:44:00'),
        'CANCELLED');

--changeset steshabolk:20
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (32, (SELECT id FROM products WHERE product_name = 'Pumpkin Spice Latte'), 1, 400),
       (32, (SELECT id FROM products WHERE product_name = 'Latte "Lavender-Coconut"'), 1, 290);

--changeset steshabolk:21
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 1040,
        (CURRENT_DATE - INTERVAL '306 days' + TIME '09:11:00'),
        (CURRENT_DATE - INTERVAL '306 days' + TIME '09:40:00'),
        (CURRENT_DATE - INTERVAL '306 days' + TIME '09:44:00'),
        'RECEIVED');

--changeset steshabolk:22
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (33, (SELECT id FROM products WHERE product_name = 'Pancakes'), 1, 300),
       (33, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320),
       (33, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 2, 420);

--changeset steshabolk:23
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 615,
        (CURRENT_DATE - INTERVAL '278 days' + TIME '19:31:00'),
        (CURRENT_DATE - INTERVAL '278 days' + TIME '20:00:00'),
        (CURRENT_DATE - INTERVAL '278 days' + TIME '20:04:00'),
        'RECEIVED');

--changeset steshabolk:24
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (34, (SELECT id FROM products WHERE product_name = 'Puff with cranberries, cloudberries and mascarpone'), 1,
        135),
       (34, (SELECT id FROM products WHERE product_name = 'Croissant with salmon and cream cheese'), 1, 250),
       (34, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 230);

--changeset steshabolk:25
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (87, (SELECT id FROM products WHERE product_name = 'Gingerbread Syrup'));

--changeset steshabolk:26
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 685,
        (CURRENT_DATE - INTERVAL '218 days' + TIME '20:43:00'),
        (CURRENT_DATE - INTERVAL '218 days' + TIME '21:21:00'),
        (CURRENT_DATE - INTERVAL '218 days' + TIME '21:24:00'),
        'RECEIVED');

--changeset steshabolk:27
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (35, (SELECT id FROM products WHERE product_name = 'Cinnamon roll'), 1, 85),
       (35, (SELECT id FROM products WHERE product_name = 'Walnut poppy seed roll'), 1, 70),
       (35, (SELECT id FROM products WHERE product_name = 'Latte'), 1, 220),
       (35, (SELECT id FROM products WHERE product_name = 'Cappuccino Grande'), 1, 310);

--changeset steshabolk:28
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (90, (SELECT id FROM products WHERE product_name = 'Lactose-free Milk')),
       (91, (SELECT id FROM products WHERE product_name = 'Chocolate Chips')),
       (91, (SELECT id FROM products WHERE product_name = 'Coconut Milk'));

--changeset steshabolk:29
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 350,
        (CURRENT_DATE - INTERVAL '200 days' + TIME '18:43:00'),
        (CURRENT_DATE - INTERVAL '200 days' + TIME '19:05:00'),
        (CURRENT_DATE - INTERVAL '200 days' + TIME '19:07:00'),
        'RECEIVED');

--changeset steshabolk:30
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (36, (SELECT id FROM products WHERE product_name = 'Coconut chocolate cheesecake'), 1, 180),
       (36, (SELECT id FROM products WHERE product_name = 'Classic cheesecake'), 1, 170);

--changeset steshabolk:31
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 275,
        (CURRENT_DATE - INTERVAL '114 days' + TIME '19:02:00'),
        (CURRENT_DATE - INTERVAL '114 days' + TIME '19:42:00'),
        (CURRENT_DATE - INTERVAL '114 days' + TIME '19:49:00'),
        'RECEIVED');

--changeset steshabolk:32
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (37, (SELECT id FROM products WHERE product_name = 'Raf'), 1, 200),
       (37, (SELECT id FROM products WHERE product_name = 'Croissant'), 1, 75);

--changeset steshabolk:33
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (94, (SELECT id FROM products WHERE product_name = 'Hazelnut Syrup'));

--changeset steshabolk:34
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 325,
        (CURRENT_DATE - INTERVAL '94 days' + TIME '15:13:00'),
        (CURRENT_DATE - INTERVAL '94 days' + TIME '15:38:00'),
        (CURRENT_DATE - INTERVAL '94 days' + TIME '15:36:00'),
        'RECEIVED');

--changeset steshabolk:35
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (38, (SELECT id FROM products WHERE product_name = 'Cappuccino Grande'), 1, 230),
       (38, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95);

--changeset steshabolk:36
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (96, (SELECT id FROM products WHERE product_name = 'Raspberry Syrup'));

--changeset steshabolk:37
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 150,
        (CURRENT_DATE - INTERVAL '77 days' + TIME '16:26:00'),
        (CURRENT_DATE - INTERVAL '77 days' + TIME '16:55:00'),
        (CURRENT_DATE - INTERVAL '77 days' + TIME '16:59:00'),
        'CANCELLED');

--changeset steshabolk:38
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (39, (SELECT id FROM products WHERE product_name = 'Croissant'), 2, 150);

--changeset steshabolk:39
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 760,
        (CURRENT_DATE - INTERVAL '51 days' + TIME '15:03:00'),
        (CURRENT_DATE - INTERVAL '51 days' + TIME '15:40:00'),
        (CURRENT_DATE - INTERVAL '51 days' + TIME '15:42:00'),
        'RECEIVED');

--changeset steshabolk:40
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (40, (SELECT id FROM products WHERE product_name = 'Turkey and tomato sandwich'), 1, 200),
       (40, (SELECT id FROM products WHERE product_name = 'Ham and cheese sandwich'), 1, 200),
       (40, (SELECT id FROM products WHERE product_name = 'Raf'), 2, 360);

--changeset steshabolk:41
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 465,
        (CURRENT_DATE - INTERVAL '43 days' + TIME '12:04:00'),
        (CURRENT_DATE - INTERVAL '43 days' + TIME '12:24:00'),
        (CURRENT_DATE - INTERVAL '43 days' + TIME '12:20:00'),
        'RECEIVED');

--changeset steshabolk:42
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (41, (SELECT id FROM products WHERE product_name = 'Raff "Salted caramel"'), 1, 340),
       (41, (SELECT id FROM products WHERE product_name = 'Almond croissant'), 1, 125);

--changeset steshabolk:43
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (102, (SELECT id FROM products WHERE product_name = 'Lactose-free Milk'));

--changeset steshabolk:44
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 295,
        (CURRENT_DATE - INTERVAL '27 days' + TIME '17:54:00'),
        (CURRENT_DATE - INTERVAL '27 days' + TIME '18:40:00'),
        (CURRENT_DATE - INTERVAL '27 days' + TIME '18:45:00'),
        'RECEIVED');

--changeset steshabolk:45
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (42, (SELECT id FROM products WHERE product_name = 'Brownie'), 1, 140),
       (42, (SELECT id FROM products WHERE product_name = 'Berry tart'), 1, 155);

--changeset steshabolk:46
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 3, 670,
        (CURRENT_DATE - INTERVAL '15 days' + TIME '10:01:00'),
        (CURRENT_DATE - INTERVAL '15 days' + TIME '10:22:00'),
        (CURRENT_DATE - INTERVAL '15 days' + TIME '10:22:00'),
        'RECEIVED');

--changeset steshabolk:47
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (43, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 1, 350),
       (43, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);

--changeset steshabolk:48
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 275,
        (CURRENT_DATE - INTERVAL '5 days' + TIME '19:02:00'),
        (CURRENT_DATE - INTERVAL '5 days' + TIME '19:42:00'),
        (CURRENT_DATE - INTERVAL '5 days' + TIME '19:49:00'),
        'RECEIVED');

--changeset steshabolk:49
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (44, (SELECT id FROM products WHERE product_name = 'Raf'), 1, 200),
       (44, (SELECT id FROM products WHERE product_name = 'Croissant'), 1, 75);

--changeset steshabolk:50
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (108, (SELECT id FROM products WHERE product_name = 'Vanilla Syrup'));
