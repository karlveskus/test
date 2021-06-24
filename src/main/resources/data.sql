insert into account (number, balance, currency_code) values ('3141', 300,  'GBP') on conflict (number) do update set balance=300,  currency_code='GBP';
insert into account (number, balance, currency_code) values ('1415', -20,  'GBP') on conflict (number) do update set balance=-20,  currency_code='GBP';
insert into account (number, balance, currency_code) values ('2718', 2.50, 'GBP') on conflict (number) do update set balance=2.50, currency_code='GBP';
delete from transaction;
