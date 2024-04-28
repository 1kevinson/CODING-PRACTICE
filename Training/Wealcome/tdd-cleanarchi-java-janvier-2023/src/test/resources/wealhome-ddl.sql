
create table fiscal_years
(
    id     uuid    not null
        constraint fiscal_years_pk
            primary key,
    budget decimal not null
);


create table calls_for_funds
(
    id             bigint  not null
        constraint calls_for_funds_pk
            primary key,
    amount         decimal not null,
    fiscal_year_id uuid    not null
        constraint calls_for_funds_fiscal_years_id_fk
            references fiscal_years
);
