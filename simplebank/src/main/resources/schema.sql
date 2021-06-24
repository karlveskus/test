create table if not exists account (number varchar(255) not null, balance numeric(19, 2) not null, currency_code varchar(255) not null, primary key (number))
create table if not exists transaction (uuid uuid not null, amount numeric(19, 2) not null, currency varchar(255) not null, credit_number varchar(255), debit_number varchar(255), primary key (uuid))
alter table transaction drop constraint if exists transaction_credit
alter table transaction drop constraint if exists transaction_debit
alter table if exists transaction add constraint transaction_credit foreign key (credit_number) references account
alter table if exists transaction add constraint transaction_debit foreign key (debit_number) references account
