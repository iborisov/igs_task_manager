create table users (
  user_id serial primary key,
  first_name text not null,
  last_name text not null,
  username text not null,
  password text not null
);
