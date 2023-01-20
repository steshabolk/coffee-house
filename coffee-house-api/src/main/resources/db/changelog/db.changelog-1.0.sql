--liquibase formatted sql

--changeset steshabolk:1
CREATE TABLE IF NOT EXISTS users
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_name VARCHAR(64)        NOT NULL,
    phone     VARCHAR(11) UNIQUE NOT NULL,
    password  VARCHAR            NOT NULL,
    role      VARCHAR(20)        NOT NULL
);
--rollback DROP TABLE users;

--changeset steshabolk:2
CREATE TABLE IF NOT EXISTS coffeehouses
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    manager_id BIGINT REFERENCES users (id) ON DELETE SET NULL,
    city       VARCHAR(64)  NOT NULL,
    address    VARCHAR(128) NOT NULL
);
--rollback DROP TABLE coffeehouses;

--changeset steshabolk:3
CREATE TABLE IF NOT EXISTS orders
(
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id        BIGINT REFERENCES users (id) ON DELETE CASCADE,
    coffeehouse_id BIGINT REFERENCES coffeehouses (id) ON DELETE CASCADE,
    total_cost     INT          NOT NULL,
    created_at     TIMESTAMP    NOT NULL,
    pick_up_at     TIMESTAMP    NOT NULL,
    closed_at      TIMESTAMP,
    status         VARCHAR(20)  NOT NULL
);
--rollback DROP TABLE orders;

--changeset steshabolk:4
CREATE TABLE IF NOT EXISTS products_categories
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    category_name VARCHAR(32) UNIQUE NOT NULL
);
--rollback DROP TABLE products_categories;

--changeset steshabolk:5
CREATE TABLE IF NOT EXISTS products
(
    id               BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    product_name     VARCHAR(64)        NOT NULL,
    product_category BIGINT REFERENCES products_categories (id) ON DELETE CASCADE,
    size_value       VARCHAR(10)        NOT NULL,
    price            INT                NOT NULL,
    description      VARCHAR(250)       NOT NULL
);
--rollback DROP TABLE products;

--changeset steshabolk:6
CREATE TABLE IF NOT EXISTS coffeehouses_products
(
    coffeehouse_id BIGINT REFERENCES coffeehouses (id) ON DELETE CASCADE,
    product_id     BIGINT REFERENCES products (id) ON DELETE CASCADE,
    is_available   BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (coffeehouse_id, product_id)
);
--rollback DROP TABLE coffeehouses_products;

--changeset steshabolk:7
CREATE TABLE IF NOT EXISTS orders_details
(
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_id     BIGINT REFERENCES orders (id) ON DELETE CASCADE,
    product_id   BIGINT REFERENCES products (id) ON DELETE CASCADE,
    quantity     INT NOT NULL,
    cost         INT NOT NULL
    );
--rollback DROP TABLE orders_details;

--changeset steshabolk:8
CREATE TABLE IF NOT EXISTS orders_details_additives
(
    order_details_id BIGINT REFERENCES orders_details (id) ON DELETE CASCADE,
    product_id       BIGINT REFERENCES products (id) ON DELETE CASCADE,
    PRIMARY KEY (order_details_id, product_id)
    );
--rollback DROP TABLE orders_details_additives;
