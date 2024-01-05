--liquibase formatted sql

--changeset steshabolk:1
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 595,
        (CURRENT_DATE + TIME '12:25:00'),
        (CURRENT_DATE + TIME '14:05:00'),
        'ACTIVE');

--changeset steshabolk:2
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (47, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 1, 250),
       (47, (SELECT id FROM products WHERE product_name = 'Croissant with salmon and cream cheese'), 1, 250),
       (47, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95);

--changeset steshabolk:3
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 380,
        (CURRENT_DATE + TIME '13:05:00'),
        (CURRENT_DATE + TIME '14:05:00'),
        'ACTIVE');

--changeset steshabolk:4
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (48, (SELECT id FROM products WHERE product_name = 'Berry tart'), 1, 155),
       (48, (SELECT id FROM products WHERE product_name = 'Brownie'), 1, 140),
       (48, (SELECT id FROM products WHERE product_name = 'Cinnamon roll'), 1, 85);

--changeset steshabolk:5
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 1040,
        (CURRENT_DATE + TIME '11:52:00'),
        (CURRENT_DATE + TIME '14:12:00'),
        'ACTIVE');

--changeset steshabolk:6
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (49, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 2, 420),
       (49, (SELECT id FROM products WHERE product_name = 'Pancakes'), 1, 300),
       (49, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);

--changeset steshabolk:7
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 400,
        (CURRENT_DATE + TIME '14:05:00'),
        (CURRENT_DATE + TIME '14:35:00'),
        'ACTIVE');

--changeset steshabolk:8
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (50, (SELECT id FROM products WHERE product_name = 'Pumpkin Spice Latte'), 1, 400);

--changeset steshabolk:9
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71865001866'), 3, 980,
        (CURRENT_DATE + TIME '11:24:00'),
        (CURRENT_DATE + TIME '14:00:00'),
        'ACTIVE');

--changeset steshabolk:10
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (51, (SELECT id FROM products WHERE product_name = 'Cappuccino "Almond"'), 2, 700),
       (51, (SELECT id FROM products WHERE product_name = 'Puff with cranberries, cloudberries and mascarpone'), 1,
        135),
       (51, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95);
