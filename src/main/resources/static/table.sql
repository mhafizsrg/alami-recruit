create table cif
(
    id            text           not null
        constraint cif_pk
            primary key,
    first_name    text           not null,
    last_name     text           not null,
    tanggal_lahir integer        not null,
    bulan_lahir   integer        not null,
    tahun_lahir   integer        not null,
    alamat        text           not null,
    balance       numeric(19, 2) not null
);

alter table cif
    owner to mhafizsir;

create table transaction
(
    id           text                    not null
        constraint transaction_pk
            primary key,
    cif_id       text                    not null,
    timestamp    timestamp default now() not null,
    amount       numeric(19, 2)          not null,
    credit_debit text                    not null
);

alter table transaction
    owner to mhafizsir;

