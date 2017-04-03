
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
    CONSTRAINT transaction_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: transaction_products (table: transaction)
ALTER TABLE transaction ADD CONSTRAINT transaction_products
    FOREIGN KEY (id_product)
    REFERENCES product (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: transaction_shops (table: transaction)
ALTER TABLE transaction ADD CONSTRAINT transaction_shops
    FOREIGN KEY (id_shop)
    REFERENCES shop (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

INSERT INTO product (id,name) VALUES
  (1,'product1'),
  (2,'product2'),
  (3,'product3'),
  (4,'product4'),
  (5,'product5'),
  (6,'product6');

INSERT INTO shop (id,name,address) VALUES
  (1,'shop1','shop_address1'),
  (2,'shop2','shop_address2'),
  (3,'shop3','shop_address3'),
  (4,'shop4','shop_address4'),
  (5,'shop5','shop_address5'),
  (6,'shop6','shop_address6');

INSERT INTO transaction (id,id_product,id_shop,product_count,
"check",date,time) VALUES
  (1,1,1,2,148.1,DATE '2001-12-21',TIME '17:12:28.5'),
  (2,3,2,21,1318.1,DATE '2001-12-21',TIME '17:12:28.5'),
  (3,6,4,1,143.2,DATE '2001-12-21',TIME '17:12:28.5'),
  (4,1,6,10,1499.11,DATE '2001-12-21',TIME '17:12:28.5');