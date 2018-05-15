
CREATE TABLE IF NOT EXISTS bidder
(
   id integer not null,
   guid varchar(64) not null,
   name varchar(64) not null,
   create_date date not null,
   primary key(id)
);

CREATE TABLE IF NOT EXISTS bid
(
   id integer not null,
   bidder_id integer not null,
   bid_price float not null,
   bid_qty integer not null,
   orig_qty integer not null,
   limit_price float not null,
   create_date date not null,
   primary key(id)
);

CREATE TABLE IF NOT EXISTS offer
(
   id integer not null,
   bidder_id integer not null,
   offer_price float not null,
   offer_qty integer not null,
   orig_qty integer not null,
   create_date date not null,
   primary key(id)
);

CREATE TABLE IF NOT EXISTS offer_ledger
(
   id integer not null,
   price_point float not null,
   db_offer_id integer not null,
   db_offer_qty integer not null,
   cr_bid_id integer not null,
   cr_bid_qty integer not null,
   create_date date not null,
   primary key(id)
);


CREATE TABLE IF NOT EXISTS person
(
   id integer not null,
   name varchar(255) not null,
   passport_number varchar(255) not null,
   primary key(id)
);
