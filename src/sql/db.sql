CREATE TABLE SplitRequest (
  split_request_id serial PRIMARY KEY,
  transaction_id varchar(200) not null,
  account_id varchar(200) not null,
  friends_list varchar(2000) not null);