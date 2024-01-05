--liquibase formatted sql

--changeset steshabolk:1
INSERT INTO products (product_name, category, size_value, price, description)
VALUES ('Latte', (SELECT id FROM categories WHERE category_name = 'Coffee Classic'),
        '250 ml', 150, 'Espresso, milk'),
       ('Latte Grande', (SELECT id FROM categories WHERE category_name = 'Coffee Classic'),
        '400 ml', 210, 'Espresso, milk'),
       ('Cappuccino', (SELECT id FROM categories WHERE category_name = 'Coffee Classic'),
        '250 ml', 150, 'Espresso, milk'),
       ('Cappuccino Grande', (SELECT id FROM categories WHERE category_name = 'Coffee Classic'),
        '400 ml', 210, 'Espresso, milk'),
       ('Raf', (SELECT id FROM categories WHERE category_name = 'Coffee Classic'),
        '250 ml', 180, 'Espresso, milk, cream, vanilla sugar'),
       ('Raf Grande', (SELECT id FROM categories WHERE category_name = 'Coffee Classic'),
        '400 ml', 250, 'Espresso, milk, cream, vanilla sugar');

--changeset steshabolk:2
INSERT INTO products (product_name, category, size_value, price, description)
VALUES ('Raff "Salted caramel"', (SELECT id FROM categories WHERE category_name = 'Coffee Specialty'),
        '350 ml', 270, 'Espresso, milk, cream, caramel, salt'),
       ('Latte "Lavender-Coconut"', (SELECT id FROM categories WHERE category_name = 'Coffee Specialty'),
        '350 ml', 290, 'Espresso, coconut milk, coconut sugar, lavender'),
       ('Cappuccino "Almond"', (SELECT id FROM categories WHERE category_name = 'Coffee Specialty'),
        '350 ml', 350, 'Espresso, almond milk, brown sugar, almond paste, chocolate chips'),
       ('Pumpkin Spice Latte', (SELECT id FROM categories WHERE category_name = 'Coffee Specialty'),
        '400 ml', 400,
        'Espresso, coconut milk, pumpkin purée, maple syrup, vanilla extract, spices (cinnamon, nutmeg, ginger)');

--changeset steshabolk:3
INSERT INTO products (product_name, category, size_value, price, description)
VALUES ('Caramel Syrup', (SELECT id FROM categories WHERE category_name = 'Syrup'),
        '20 ml', 20, 'Caramel syrup'),
       ('Coconut Syrup', (SELECT id FROM categories WHERE category_name = 'Syrup'),
        '20 ml', 20, 'Coconut syrup'),
       ('Gingerbread Syrup', (SELECT id FROM categories WHERE category_name = 'Syrup'),
        '20 ml', 20, 'Gingerbread syrup'),
       ('Irish Cream Syrup', (SELECT id FROM categories WHERE category_name = 'Syrup'),
        '20 ml', 20, 'Irish Cream syrup'),
       ('Almond Syrup', (SELECT id FROM categories WHERE category_name = 'Syrup'),
        '20 ml', 20, 'Almond syrup'),
       ('Hazelnut Syrup', (SELECT id FROM categories WHERE category_name = 'Syrup'),
        '20 ml', 20, 'Hazelnut syrup'),
       ('Chocolate Syrup', (SELECT id FROM categories WHERE category_name = 'Syrup'),
        '20 ml', 20, 'Chocolate syrup'),
       ('Vanilla Syrup', (SELECT id FROM categories WHERE category_name = 'Syrup'),
        '20 ml', 20, 'Vanilla syrup'),
       ('Raspberry Syrup', (SELECT id FROM categories WHERE category_name = 'Syrup'),
        '20 ml', 20, 'Raspberry syrup');

--changeset steshabolk:4
INSERT INTO products (product_name, category, size_value, price, description)
VALUES ('Chocolate Chips', (SELECT id FROM categories WHERE category_name = 'Topping'),
        '10 g', 30, 'Chocolate chips'),
       ('Whipped Cream', (SELECT id FROM categories WHERE category_name = 'Topping'),
        '10 g', 30, 'Whipped cream'),
       ('Marshmallows', (SELECT id FROM categories WHERE category_name = 'Topping'),
        '10 g', 30, 'Marshmallows');

--changeset steshabolk:5
INSERT INTO products (product_name, category, size_value, price, description)
VALUES ('Lactose-free Milk', (SELECT id FROM categories WHERE category_name = 'Milk'),
        '100 ml', 70, 'Lactose-free milk'),
       ('Coconut Milk', (SELECT id FROM categories WHERE category_name = 'Milk'),
        '100 ml', 70, 'Coconut milk'),
       ('Almond Milk', (SELECT id FROM categories WHERE category_name = 'Milk'),
        '100 ml', 70, 'Almond milk'),
       ('Soy Milk', (SELECT id FROM categories WHERE category_name = 'Milk'),
        '100 ml', 70, 'Soy milk'),
       ('Banana Milk', (SELECT id FROM categories WHERE category_name = 'Milk'),
        '100 ml', 70, 'Banana milk');

--changeset steshabolk:6
INSERT INTO products (product_name, category, size_value, price, description)
VALUES ('Pancakes', (SELECT id FROM categories WHERE category_name = 'Food'),
        '190 g', 300, 'Pancakes with custard, caramel and almond petals'),
       ('Chicken and mushroom pie', (SELECT id FROM categories WHERE category_name = 'Food'),
        '150 g', 100, 'Pastry pie stuffed with chicken fillet, champignons and cream sauce'),
       ('Turkey and tomato sandwich', (SELECT id FROM categories WHERE category_name = 'Food'),
        '190 g', 200, 'Snack of ciabatta with turkey, tomatoes and cream cheese'),
       ('Ham and cheese sandwich', (SELECT id FROM categories WHERE category_name = 'Food'),
        '170 g', 200, 'Sandwich bun with ham, cheese, lettuce leaf and mayonnaise sauce with mustard'),
       ('Croissant with salmon and cream cheese', (SELECT id FROM categories WHERE category_name = 'Food'),
        '140 g', 250, 'Classic airy croissant with a slice of lightly salted trout, cream sauce and lettuce leaf. ' ||
                      'The sauce is made on the basis of cottage cheese with the addition of natural cream.'),
       ('Avocado toast', (SELECT id FROM categories WHERE category_name = 'Food'),
        '240 g', 320, 'Classic toast with avocado and cottage cheese');

--changeset steshabolk:7
INSERT INTO products (product_name, category, size_value, price, description)
VALUES ('Croissant', (SELECT id FROM categories WHERE category_name = 'Pastries'),
        '65 g', 75, 'Classic French croissant made of puff pastry with natural butter'),
       ('Almond croissant', (SELECT id FROM categories WHERE category_name = 'Pastries'),
        '100 g', 125, 'Fragrant croissant with almond cream, covered with peanut petals'),
       ('Cinnamon roll', (SELECT id FROM categories WHERE category_name = 'Pastries'),
        '150 g', 85, 'Tender, fluffy and gooey cinnamon rolls with vanilla silky cream cheese icing on top'),
       ('Walnut poppy seed roll', (SELECT id FROM categories WHERE category_name = 'Pastries'),
        '95 g', 70, 'Sweet bun stuffed with poppy seeds and walnuts, glazed with sugar syrup'),
       ('Puff with cheese and spinach', (SELECT id FROM categories WHERE category_name = 'Pastries'),
        '160 g', 95, 'Puff pastry with soft pickled cheese and spinach'),
       ('Puff with cranberries, cloudberries and mascarpone',
        (SELECT id FROM categories WHERE category_name = 'Pastries'),
        '145 g', 135,
        'Crispy pastry with an air cream based on mascarpone cheese, tart cloudberry sauce and sour-sweet cranberries');

--changeset steshabolk:8
INSERT INTO products (product_name, category, size_value, price, description)
VALUES ('Classic cheesecake', (SELECT id FROM categories WHERE category_name = 'Desserts'), '120 g', 170,
        'The delicate sand base is made with natural butter, and the air cream is made of cream cheese and natural cream'),
       ('Coconut chocolate cheesecake', (SELECT id FROM categories WHERE category_name = 'Desserts'), '125 g',
        180,
        'Cheesecake with a coconut base and classic chocolate-covered cream cheese mousse'),
       ('Brownie', (SELECT id FROM categories WHERE category_name = 'Desserts'), '120 g', 140,
        'Classic American chocolate brownie with peanuts and walnuts'),
       ('Berry tart', (SELECT id FROM categories WHERE category_name = 'Desserts'), '130 g', 155,
        'Dessert based on caramel shortcrust pastry, delicate almond cream and juicy raspberries, blueberries and cherries'),
       ('Mango eclair', (SELECT id FROM categories WHERE category_name = 'Desserts'), '70 g', 95,
        'Delicate and airy eclair with mango cream, covered with white chocolate fondant'),
       ('Salted caramel eclair', (SELECT id FROM categories WHERE category_name = 'Desserts'), '70 g', 95,
        'Eclair is filled with creamy caramel cream and covered with caramel glaze. Sea salt flakes works nicely in the sweet caramel flavour');
