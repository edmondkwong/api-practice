Drop database company;
CREATE DATABASE company;

CREATE USER 'generatedata'@'localhost'
  IDENTIFIED BY 'generatedata';

GRANT ALL PRIVILEGES ON company.* TO generatedata@localhost
IDENTIFIED BY 'generatedata';

FLUSH PRIVILEGES;

USE company;

CREATE TABLE customers (
  id         INT         NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(14) NOT NULL,
  last_name  VARCHAR(16) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE addresses (
  id            INT NOT NULL AUTO_INCREMENT,
  customer_id   INT NOT NULL,
  address_line1 VARCHAR(255),
  PRIMARY KEY (id),
  FOREIGN KEY (customer_id)
  REFERENCES customers (id)
    ON DELETE CASCADE
);

ALTER TABLE customers
  AUTO_INCREMENT = 1001;
ALTER TABLE addresses
  AUTO_INCREMENT = 1;

INSERT INTO customers (first_name, last_name) VALUES ('edmond', 'kwong');
INSERT INTO addresses (customer_id, address_line1) VALUES (1001, '123 test street');
INSERT INTO addresses (customer_id, address_line1) VALUES (1001, '222 test street');
INSERT INTO customers (first_name, last_name) VALUES ('edmond2', 'kwong2');
INSERT INTO addresses (customer_id, address_line1) VALUES (1002, '3 test street');
INSERT INTO addresses (customer_id, address_line1) VALUES (1002, '4 test street');
INSERT INTO customers (first_name, last_name) VALUES ('edmond3', 'kwong3');
INSERT INTO addresses (customer_id, address_line1) VALUES (1003, '5 test street');
INSERT INTO addresses (customer_id, address_line1) VALUES (1003, '6 test street');
INSERT INTO customers (first_name, last_name) VALUES ('edmond4', 'kwong4');
INSERT INTO addresses (customer_id, address_line1) VALUES (1004, '7 test street');
INSERT INTO addresses (customer_id, address_line1) VALUES (1004, '8 test street');