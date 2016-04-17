CREATE TABLE split_request (
  split_request_id serial PRIMARY KEY,
  transaction_id VARCHAR(200) not null,
  account_id VARCHAR(200) not null,
  amount BIGINT not null
);

CREATE TABLE friend (
  friend_id serial PRIMARY KEY,
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