--liquibase formatted sql

--changeset steshabolk:1
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71865001866'), 3, 670,
        '2022-09-15 17:13:00', '2022-09-15 18:00:00', '2022-09-15 18:05:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 44;

--changeset steshabolk:2
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (44, (SELECT id FROM products WHERE product_name = 'Coconut chocolate cheesecake'), 1, 180),
       (44, (SELECT id FROM products WHERE product_name = 'Salted caramel eclair'), 2, 190),
       (44, (SELECT id FROM products WHERE product_name = 'Turkey and tomato sandwich'), 1, 200),
       (44, (SELECT id FROM products WHERE product_name = 'Chicken and mushroom pie'), 1, 100);
--rollback DELETE FROM orders_details WHERE order_id = 44;

--changeset steshabolk:3
INSERT INTO orders (user_id, coffeehouse_id, total_cost, created_at, pick_up_at, closed_at, status)
VALUES ((SELECT id FROM users WHERE phone = '71865001866'), 3, 1070,
        '2022-11-27 15:16:00', '2022-11-27 16:44:00', '2022-11-27 16:45:00', 'RECEIVED');
--rollback DELETE FROM orders WHERE id = 45;

--changeset steshabolk:4
INSERT INTO orders_details (order_id, product_id, quantity, cost)
VALUES (45, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 240),
       (45, (SELECT id FROM products WHERE product_name = 'Latte Grande'), 1, 210),
       (45, (SELECT id FROM products WHERE product_name = 'Pancakes'), 1, 300),
       (45, (SELECT id FROM products WHERE product_name = 'Avocado toast'), 1, 320);
--rollback DELETE FROM orders_details WHERE order_id = 45;

--changeset steshabolk:5
INSERT INTO orders_details_additives (order_details_id, product_id)
VALUES (114, (SELECT id FROM products WHERE product_name = 'Chocolate Chips'));
--rollback DELETE FROM orders_details_additives WHERE order_details_id = 114;
