CREATE TABLE users(
	username varchar(128) NOT NULL PRIMARY KEY,
	password varchar(128) NOT NULL,
	enabled boolean NOT null
);

CREATE TABLE authorities(
	username varchar(128) NOT NULL,
	authority varchar(128) NOT NULL
);

CREATE UNIQUE INDEX idx_auth_username ON authorities(username, authority);

CREATE TABLE public.t_owner(
	id bigint NOT NULL,
	first_name varchar(255),
	last_name varchar(255)
);

CREATE TABLE public.t_pet(
	id bigint NOT NULL,
	name varchar(255),
	birth_date date,
	owner_id bigint
);


ALTER TABLE public.t_owner ADD CONSTRAINT public.constraint_1 PRIMARY KEY(id);

ALTER TABLE public.t_pet ADD CONSTRAINT public.constraint_2 PRIMARY KEY(id);

ALTER TABLE public.t_pet ADD CONSTRAINT public.constraint_3 FOREIGN KEY(owner_id) REFERENCES public.t_owner(id);

CREATE SEQUENCE public.petclinic_sequence START WITH 100;