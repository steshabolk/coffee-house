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

--changeset steshabolk:2
CREATE TABLE IF NOT EXISTS coffee_houses
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    manager_id BIGINT       REFERENCES users (id) ON DELETE SET NULL,
    city       VARCHAR(64)  NOT NULL,
    address    VARCHAR(128) NOT NULL
);

--changeset steshabolk:3
CREATE TABLE IF NOT EXISTS categories
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    category_name VARCHAR(32) UNIQUE NOT NULL
);

--changeset steshabolk:4
CREATE TABLE IF NOT EXISTS products
(
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    product_name VARCHAR(64)  NOT NULL,
    category     BIGINT REFERENCES categories (id) ON DELETE CASCADE,
    size_value   VARCHAR(10)  NOT NULL,
    price        INT          NOT NULL,
    description  VARCHAR(250) NOT NULL
);

--changeset steshabolk:5
CREATE TABLE IF NOT EXISTS menu
(
    coffee_house_id BIGINT REFERENCES coffee_houses (id) ON DELETE CASCADE,
    product_id      BIGINT REFERENCES products (id) ON DELETE CASCADE,
    is_available    BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (coffee_house_id, product_id)
);

--changeset steshabolk:6
CREATE TABLE IF NOT EXISTS orders
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id         BIGINT REFERENCES users (id) ON DELETE CASCADE,
    coffee_house_id BIGINT REFERENCES coffee_houses (id) ON DELETE CASCADE,
    total_cost      INT         NOT NULL,
    created_at      TIMESTAMP   NOT NULL,
    pick_up_at      TIMESTAMP   NOT NULL,
    closed_at       TIMESTAMP,
    status          VARCHAR(20) NOT NULL
);

--changeset steshabolk:7
CREATE TABLE IF NOT EXISTS order_details
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_id   BIGINT REFERENCES orders (id) ON DELETE CASCADE,
    product_id BIGINT REFERENCES products (id) ON DELETE CASCADE,
    quantity   INT NOT NULL,
    cost       INT NOT NULL
);

--changeset steshabolk:8
CREATE TABLE IF NOT EXISTS order_detail_additives
(
    order_detail_id BIGINT REFERENCES order_details (id) ON DELETE CASCADE,
    product_id      BIGINT REFERENCES products (id) ON DELETE CASCADE,
    PRIMARY KEY (order_detail_id, product_id)
);
