
-- DROP DATABASE shops;

CREATE DATABASE shops
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'ru_RU.UTF-8'
       LC_CTYPE = 'ru_RU.UTF-8'
       CONNECTION LIMIT = -1;

-- tables
-- Table: product
CREATE TABLE product (
    id int  NOT NULL,
    name varchar(256)  NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id)
);

-- Table: shop
CREATE TABLE shop (
    id int  NOT NULL,
    name varchar(256)  NOT NULL,
    address varchar(256)  NOT NULL,
    CONSTRAINT shop_pk PRIMARY KEY (id)
);

-- Table: transaction
CREATE TABLE transaction (
    id int  NOT NULL,
    id_product int  NOT NULL,
    id_shop int  NOT NULL,
    product_count int  NOT NULL,
    "check" real  NOT NULL,
    date date  NOT NULL,
    time time  NOT NULL,
    products_id int  NOT NULL,
    shops_id int  NOT NULL,
    CONSTRAINT transaction_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: transaction_products (table: transaction)
ALTER TABLE transaction ADD CONSTRAINT transaction_products
    FOREIGN KEY (products_id)
    REFERENCES product (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: transaction_shops (table: transaction)
ALTER TABLE transaction ADD CONSTRAINT transaction_shops
    FOREIGN KEY (shops_id)
    REFERENCES shop (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

INSERT INTO product (id, name) VALUES
  (1,'product1'),
  (2,'product2'),
  (3,'product3'),
  (4,'product4'),
  (5,'product5'),
  (6,'product6');

INSERT INTO shop (id, name, address) VALUES
  (1,'product1','address1'),
  (2,'product2','address2'),
  (3,'product3','address3'),
  (4,'product4','address4'),
  (5,'product5','address5'),
  (6,'product6','address6');

INSERT INTO transaction (id, id_product, id_shop, product_count, products_id, shops_id) VALUES
  (1, 1, 2, 2, 2, 1),
  (2, 1, 3, 1, 1, 1),
  (3, 1, 1, 1, 3, 1);
