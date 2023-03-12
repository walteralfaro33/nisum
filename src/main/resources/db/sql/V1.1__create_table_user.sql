create sequence seq_phones start with 1 increment by 1;
create sequence seq_users start with 1 increment by 1;

    create table phones (
       id integer not null,
        city_code varchar(255) not null,
        country_code varchar(255) not null,
        number varchar(255) not null,
        user_id integer,
        primary key (id)
    );

    create table users (
       id integer not null,
        created timestamp not null,
        email varchar(255) not null,
        is_active boolean not null,
        last_login timestamp not null,
        modified timestamp not null,
        name varchar(255) not null,
        password varchar(255) not null,
        token varchar(255) not null,
        primary key (id)
    );

    alter table users
       add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);

    alter table phones
       add constraint FKmg6d77tgqfen7n1g763nvsqe3
       foreign key (user_id)
       references users
       on delete cascade;
