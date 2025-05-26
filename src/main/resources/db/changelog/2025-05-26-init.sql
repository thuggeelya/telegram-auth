--liquibase formatted sql

--changeset thuggeelya:1

create table if not exists users
(
    id         bigint primary key,
    first_name varchar(55),
    last_name  varchar(55),
    username   varchar not null unique,
    photo_url  varchar
);