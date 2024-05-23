create table json_schema
(
    id          bigint generated
        by default as identity primary key,
    pid         uuid not null,
    name        text not null,
    description text
);

create table json_schema_revision
(

)
