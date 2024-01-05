--liquibase formatted sql

--changeset steshabolk:1
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71865001866'), 3, 670,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '72 days' + TIME '17:13:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '72 days' + TIME '18:00:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '72 days' + TIME '18:05:00'),
        'RECEIVED');

--changeset steshabolk:2
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (45, (SELECT id FROM products WHERE product_name = 'Coconut chocolate cheesecake'), 1, 180),
       (45, (SELECT id FROM products WHERE product_name = 'Salted caramel eclair'), 2, 190),
       (45, (SELECT id FROM products WHERE product_name = 'Turkey and tomato sandwich'), 1, 200),
       (45, (SELECT id FROM products WHERE product_name = 'Chicken and mushroom pie'), 1, 100);

--changeset steshabolk:3
INSERT INTO orders (user_id, coffee_house_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71865001866'), 3, 1070,
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '30 days' + TIME '15:16:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '30 days' + TIME '16:44:00'),
        (CURRENT_DATE - INTERVAL '1 year' - INTERVAL '30 days' + TIME '16:45:00'),
        'RECEIVED');

--changeset steshabolk:4
INSERT INTO order_details (order_id, product_id, quantity, cost)
VALUES (46, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 240),
       (46, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 210),
       (46, (SELECT id FROM products WHERE product_name = 'Pancakes'), 1, 300),
       (46, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);

--changeset steshabolk:5
INSERT INTO order_detail_additives (order_detail_id, product_id)
VALUES (114, (SELECT id FROM products WHERE product_name = 'Chocolate Chips'));
