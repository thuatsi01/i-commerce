CREATE TABLE IF NOT EXISTS `users`
(
    `id`           int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `display_name` varchar(20),
    `email`        varchar(50)  NOT NULL UNIQUE,
    `password`     varchar(100) NOT NULL,
    `address`      varchar(100),
    `create_date`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE INDEX idx_user_01 ON `users` (`email`);

CREATE TABLE IF NOT EXISTS `product_category`
(
    `category_code`    varchar(20)  NOT NULL PRIMARY KEY,
    `category_english` varchar(100) NOT NULL,
    `is_delete`        bool         NOT NULL DEFAULT false
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE TABLE IF NOT EXISTS `product_brand`
(
    `id`        int         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `brand`     varchar(20) NOT NULL,
    `is_delete` bool        NOT NULL DEFAULT false
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE TABLE IF NOT EXISTS `product_colour`
(
    `colour_code`    varchar(20)  NOT NULL PRIMARY KEY,
    `colour_english` varchar(100) NOT NULL,
    `hex`            varchar(20)  NOT NULL,
    `is_delete`      bool         NOT NULL DEFAULT false
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE TABLE IF NOT EXISTS `product`
(
    `sku`           varchar(20) NOT NULL,
    `brand_id`      int         NOT NULL,
    `category_code` varchar(20) NOT NULL,
    `colour_code`   varchar(20),
    `display_name`  varchar(20) NOT NULL,
    `price`         bigint      NOT NULL,
    `is_delete`     bool        NOT NULL DEFAULT false,
    `create_date`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`sku`, `brand_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

ALTER TABLE product
    ADD CONSTRAINT fk_product_product_category_01 FOREIGN KEY (category_code) REFERENCES product_category (category_code);
ALTER TABLE product
    ADD CONSTRAINT fk_product_product_brand_01 FOREIGN KEY (brand_id) REFERENCES product_brand (id);
ALTER TABLE product
    ADD CONSTRAINT fk_product_product_colour_01 FOREIGN KEY (colour_code) REFERENCES product_colour (colour_code);

CREATE TABLE IF NOT EXISTS `order_shopping_cart`
(
    `id`             int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id`        int          NOT NULL,
    `sku`            varchar(20)  NOT NULL,
    `brand_id`       int          NOT NULL,
    `brand_name`     varchar(20)  NOT NULL,
    `colour_hex`     varchar(20)  NOT NULL,
    `colour_english` varchar(100) NOT NULL,
    `display_name`   varchar(20)  NOT NULL,
    `price`          bigint       NOT NULL,
    `quantity`       bigint       NOT NULL,
    `create_date`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `version`        bigint
);

CREATE INDEX idx_order_shopping_cart_01 ON `order_shopping_cart` (`user_id`);
CREATE UNIQUE INDEX idx_order_shopping_cart_02 ON `order_shopping_cart` (`user_id`, `brand_id`, `sku`);
