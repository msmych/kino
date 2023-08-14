create schema if not exists kino;

create table if not exists kino.movies
(
    id    uuid   not null primary key,
    title text   not null,
    year  bigint not null
);
