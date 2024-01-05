--liquibase formatted sql

--changeset steshabolk:1
INSERT INTO users (user_name, phone, password, role)
VALUES ('ManagerBerthold', '71234567890',
        '$2a$10$SK1k3RL5wSesalxB4QAD8OMVyRgdYMtnHSsFLSNvbfxFVS5nE8tpK', 'ROLE_MANAGER'),
       ('ManagerSevkabel', '79876543210',
        '$2a$10$t/w6QjCuHIdpH4oaJ8UIA.bxdgLCKjAVKB0QwBTtQu5PD719rGCgy', 'ROLE_MANAGER'),
       ('ManagerFligel', '79317624850',
        '$2a$10$F.jv0SvK9uAL5VBv4NR3wOmNQDvwej8TkR.WAspYE4p5o8pRfBwJi', 'ROLE_MANAGER');

--changeset steshabolk:2
INSERT INTO users (user_name, phone, password, role)
VALUES ('Anna_K', '71873001877',
        '$2a$10$LiWl6OUDYoHheC.4C/98AePqaSlD6UfNIpaMN7xSd3UikE/tN9ZlW', 'ROLE_USER'),
       ('Margarita', '71928001940',
        '$2a$10$0XsSmUUgW7K3IgbP25SDWOG.a.FxY6IgFncseIHKtyCWY8UJbDDky', 'ROLE_USER'),
       ('Rodion_R', '71865001866',
        '$2a$10$//S9lxR79eI72cROHk2T7ejQT20yxODFrvAET7KVaFqQQqcfEJyua', 'ROLE_USER'),
       ('Andrei_B', '71863001869',
        '$2a$10$obN5EoVeHuFYwHwZgLSMROoNhXHrzyBYka.nLQ6B0RIlLoYVGtJNm', 'ROLE_USER');

--changeset steshabolk:3
INSERT INTO coffee_houses (manager_id, city, address)
VALUES ((SELECT id FROM users WHERE user_name = 'ManagerBerthold'), 'St.Petersburg',
        'st.Grazhdanskaya 15, "Berthold Centre"'),
       ((SELECT id FROM users WHERE user_name = 'ManagerSevkabel'), 'St.Petersburg',
        'Kozhevennaya liniya 40, "Sevkabel Port"'),
       ((SELECT id FROM users WHERE user_name = 'ManagerFligel'), 'St.Petersburg', 'st.Vosstaniya 24, "Fligel"');

--changeset steshabolk:4
INSERT INTO categories (category_name)
VALUES ('Coffee Classic'),
       ('Coffee Specialty'),
       ('Syrup'),
       ('Topping'),
       ('Milk'),
       ('Food'),
       ('Pastries'),
       ('Desserts');
