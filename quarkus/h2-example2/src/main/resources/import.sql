-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');
insert into Carteira (idCarteira, mesCarteira, anoCarteira, nomeCarteira, dataAtualizacao, dataVingencia)
 values (1, 10, 2022, 'Dividendos', {ts '2012-09-17 18:47:52.69'}, {ts '2012-05-10 05:01:05.11'});
insert into Carteira (idCarteira, mesCarteira, anoCarteira, nomeCarteira, dataAtualizacao, dataVingencia)
 values (2, 09, 2022, 'SmallCaps', parsedatetime('2022-12-10', 'yyyy-MM-dd'), {ts '2012-05-10 05:01:05.11'});



 