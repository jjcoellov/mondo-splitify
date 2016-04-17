CREATE TABLE split_request (
  split_request_id serial PRIMARY KEY,
  transaction_id VARCHAR(200) not null,
  account_id VARCHAR(200) not null,
  amount BIGINT not null
);

CREATE TABLE friend (
  friend_id VARCHAR(200) not null,
  name VARCHAR(200) not null,
  account_id VARCHAR(200) not null,
  access_token VARCHAR(1000) not null,
  phone_number VARCHAR(100)
);

CREATE TABLE split_request_friend (
  split_request_id BIGINT NOT NULL ,
  friend_id BIGINT NOT NULL,
  amount BIGINT not null,
  status INT default 0
);

ALTER TABLE split_request ADD COLUMN currency VARCHAR(25) DEFAULT 'GBP';

ALTER TABLE split_request_friend ADD COLUMN currency VARCHAR(25) DEFAULT 'GBP';