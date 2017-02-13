CREATE DATABASE chat
    WITH
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE SCHEMA chat;

CREATE TABLE chat.users
(
    id SERIAL NOT NULL,
    name character varying(100),
    password character varying(200),
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE chat.messages
(
    id SERIAL NOT NULL,
    text character varying(500),
    create_time timestamp with time zone,
    user_id bigint,
    CONSTRAINT messages_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_MSG_USER_ID" FOREIGN KEY (user_id)
        REFERENCES chat.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE chat.roles
(
    name character varying(100) NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (name)
);

CREATE TABLE chat.user_roles
(
    user_id bigint NOT NULL,
    role_name character varying(100) NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (role_name, user_id),
    CONSTRAINT "FKR_ROLE_NAME" FOREIGN KEY (role_name)
        REFERENCES chat.roles (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FKR_USER_ID" FOREIGN KEY (user_id)
        REFERENCES chat.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);