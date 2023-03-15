--liquibase formatted sql

--changeset steshabolk:1
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 595,
        NOW()::TIMESTAMP - INTERVAL '2 hours 40 minutes', NOW()::TIMESTAMP - INTERVAL '1 hours', 'ACTIVE');
--rollback DELETE FROM orders WHERE id = 47;

--changeset steshabolk:2
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (47, (SELECT id FROM products WHERE product_name = 'Raf Grande'), 1, 250),
       (47, (SELECT id FROM products WHERE product_name = 'Croissant with salmon and cream cheese'), 1, 250),
       (47, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95);
--rollback DELETE FROM orders_details WHERE order_id = 47;

--changeset steshabolk:3
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71873001877'), 1, 380,
        NOW()::TIMESTAMP - INTERVAL '2 hours', NOW()::TIMESTAMP - INTERVAL '1 hours', 'ACTIVE');
--rollback DELETE FROM orders WHERE id = 48;

--changeset steshabolk:4
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (48, (SELECT id FROM products WHERE product_name = 'Berry tart'), 1, 155),
       (48, (SELECT id FROM products WHERE product_name = 'Brownie'), 1, 140),
       (48, (SELECT id FROM products WHERE product_name = 'Cinnamon roll'), 1, 85);
--rollback DELETE FROM orders_details WHERE order_id = 48;

--changeset steshabolk:5
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 2, 1040,
        NOW()::TIMESTAMP - INTERVAL '3 hours 13 minutes', NOW()::TIMESTAMP - INTERVAL '53 minutes', 'ACTIVE');
--rollback DELETE FROM orders WHERE id = 49;

--changeset steshabolk:6
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (49, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 2, 420),
       (49, (SELECT id FROM products WHERE product_name = 'Pancakes'), 1, 300),
       (49, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);
--rollback DELETE FROM orders_details WHERE order_id = 49;

--changeset steshabolk:7
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71928001940'), 1, 400,
        NOW()::TIMESTAMP - INTERVAL '1 hours', NOW()::TIMESTAMP - INTERVAL '30 minutes', 'ACTIVE');
--rollback DELETE FROM orders WHERE id = 50;

--changeset steshabolk:8
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (50, (SELECT id FROM products WHERE product_name = 'Pumpkin Spice Latte'), 1, 400);
--rollback DELETE FROM orders_details WHERE order_id = 50;

--changeset steshabolk:9
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71865001866'), 3, 980,
        NOW()::TIMESTAMP - INTERVAL '3 hours 41 minutes', NOW()::TIMESTAMP - INTERVAL '1 hours 5 minutes', 'ACTIVE');
--rollback DELETE FROM orders WHERE id = 51;

--changeset steshabolk:10
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (51, (SELECT id FROM products WHERE product_name = 'Cappuccino "Almond"'), 2, 700),
       (51, (SELECT id FROM products WHERE product_name = 'Puff with cranberries, cloudberries and mascarpone'), 1, 135),
       (51, (SELECT id FROM products WHERE product_name = 'Mango eclair'), 1, 95);
--rollback DELETE FROM orders_details WHERE order_id = 51;
