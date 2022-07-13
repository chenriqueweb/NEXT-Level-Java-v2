-- drop table microzona
-- drop table rotaentrega
-- drop table municipio
-- drop table filial
-- drop table faixascepmicrozona
-- drop table estado
-- drop table empresa

------------------------------------------------------
-- Empresa
-- DROP SEQUENCE codigoEmpresa_id;
-- CREATE SEQUENCE codigoEmpresa_id AS Long Start With 1;
-- SELECT NEXT VALUE FOR codigoEmpresa_id;
-- SELECT CURRENT VALUE FOR codigoEmpresa_id;
-- ALTER SEQUENCE codigoEmpresa_id RESTART WITH 1;

-- Municipio
-- DROP SEQUENCE codigoMunicipio_id;
-- CREATE SEQUENCE codigoMunicipio_id AS Long START WITH 1;
-- SELECT NEXT VALUE FOR codigoMunicipio_id;
-- SELECT CURRENT VALUE FOR codigoMunicipio_id;
-- ALTER SEQUENCE codigoMunicipio_id RESTART WITH 1;

-- Filial
-- DROP SEQUENCE codigoFilial_id;
-- CREATE SEQUENCE codigoFilial_id AS Long Start With 1;
-- SELECT NEXT VALUE FOR codigoFilial_id;
-- SELECT CURRENT VALUE FOR codigoFilial_id;
-- ALTER SEQUENCE codigoFilial_id RESTART WITH 1;

-- Rota de Entrega
-- DROP SEQUENCE codigoRotaEntrega_id;
-- CREATE SEQUENCE codigoRotaEntrega_id AS Long Start With 1;
-- SELECT NEXT VALUE FOR codigoRotaEntrega_id;
-- SELECT CURRENT VALUE FOR codigoRotaEntrega_id;
-- ALTER SEQUENCE codigoRotaEntrega_id RESTART WITH 1;

-- Microzona
-- DROP SEQUENCE codigoMicrozona_id;
-- CREATE SEQUENCE codigoMicrozona_id AS Long Start With 1;
-- SELECT NEXT VALUE FOR codigoMicrozona_id;
-- SELECT CURRENT VALUE FOR codigoMicrozona_id;
-- ALTER SEQUENCE codigoMicrozona_id RESTART WITH 1;

-- Faixas de CEP Microzona
-- DROP SEQUENCE codigofaixasCEPMicrozona_id;
-- CREATE SEQUENCE codigofaixasCEPMicrozona_id AS Long Start With 1;
-- SELECT NEXT VALUE FOR codigofaixasCEPMicrozona_id;
-- SELECT CURRENT VALUE FOR codigofaixasCEPMicrozona_id;
-- ALTER SEQUENCE codigofaixasCEPMicrozona_id RESTART WITH 1;

-------------------------------------------------------
-- Empresa
-- drop table empresa;
-- create table empresa (
-- 	codigo Long not null auto_increment,
-- 	razaoSocial varchar not null,
-- 	raizCNPJ varchar not null,
-- 	dataAbertura varchar not null,
-- 
-- 	primary key (codigo)
-- );
-- DELETE FROM empresa;
-- INSERT INTO empresa (codigo, razaoSocial, raizCNPJ, dataAbertura) VALUES((SELECT NEXT VALUE FOR codigoEmpresa_id), 'Empresa 1', '123.456.0002/38', '2022-06-27');
-- INSERT INTO empresa (codigo, razaoSocial, raizCNPJ, dataAbertura) VALUES((SELECT NEXT VALUE FOR codigoEmpresa_id), 'Empresa 2', '123.456.0003/47', '2022-06-27');
-- INSERT INTO empresa (codigo, razaoSocial, raizCNPJ, dataAbertura) VALUES((SELECT NEXT VALUE FOR codigoEmpresa_id), 'Empresa 3', '123.456.0001/28', '2022-06-27');


-- Estado
-- drop table municipio;
-- drop table estado;
-- create table estado (
-- 	sigla varchar not null,
-- 	nome varchar not null,
-- 	
-- 	primary key (sigla)
-- );
-- DELETE FROM estado;
-- INSERT INTO estado (sigla, nome) VALUES ('RJ','Rio de Janeiro');
-- INSERT INTO estado (sigla, nome) VALUES ('SP','S�o Paulo');
-- INSERT INTO estado (sigla, nome) VALUES ('MG','Minas Gerais');
-- INSERT INTO estado (sigla, nome) VALUES ('ES','Esp�rito Santo');


-- Municipio
-- drop table municipio;
-- create table municipio (
-- 	codigo Long not null auto_increment,
-- 	nome varchar not null,
-- 	siglaEstado varchar not null,
-- 	
-- 	primary key (codigo)
-- );
-- alter table municipio add constraint FK_municipio
-- 	foreign key(siglaEstado) references estado(sigla);
	
-- DELETE FROM municipio;
-- INSERT INTO municipio (codigo, nome, siglaEstado) VALUES ((SELECT NEXT VALUE FOR codigoMunicipio_id), 'Macae', 'RJ');
-- INSERT INTO municipio (codigo, nome, siglaEstado) VALUES ((SELECT NEXT VALUE FOR codigoMunicipio_id), 'Volta Redonda', 'RJ');
-- INSERT INTO municipio (codigo, nome, siglaEstado) VALUES ((SELECT NEXT VALUE FOR codigoMunicipio_id), 'S�o Caetano do Sul', 'SP');
-- INSERT INTO municipio (codigo, nome, siglaEstado) VALUES ((SELECT NEXT VALUE FOR codigoMunicipio_id), 'Campinas', 'SP');


-- Filial
-- drop table filial;
-- create table filial (
-- 	empresa Long not null,
-- 	codigo Long not null auto_increment,
-- 	nome varchar not null,
-- 	CNPJ varchar not null,
-- 	municipio Long not null,
-- 	
-- 	primary key (empresa, codigo)
-- );
-- alter table filial add constraint FK_filial
-- 	foreign key(empresa) references empresa(codigo);
	
-- DELETE FROM filial;
-- INSERT INTO filial (empresa, codigo, nome, CNPJ, municipio)
--        VALUES (1, (SELECT NEXT VALUE FOR codigoFilial_id), 'Filial-01', '123.456/0001-01', 1);
-- INSERT INTO filial (empresa, codigo, nome, CNPJ, municipio)
--        VALUES (1, (SELECT NEXT VALUE FOR codigoFilial_id), 'Filial-01A', '123.456/0002-02', 1);
-- INSERT INTO filial (empresa, codigo, nome, CNPJ, municipio)
--        VALUES (3, (SELECT NEXT VALUE FOR codigoFilial_id), 'Filial-02', '222.456/0001-01', 2);
-- INSERT INTO filial (empresa, codigo, nome, CNPJ, municipio)
--        VALUES (3, (SELECT NEXT VALUE FOR codigoFilial_id), 'Filial-03', '333.456/0002-02', 3);


-- Rota de Entrega
-- drop table rotaEntrega;
-- create table rotaEntrega (
-- 	siglaEstado varchar not null,
-- 	codigo Long not null auto_increment,
-- 	nome varchar not null,
-- 	status varchar not null,
-- 	codigoEmpresa Long not null,
-- 	codigoFilial Long not null,
-- 	prazoExpedicao int not null,
-- 	
-- 	primary key (siglaEstado, codigo)
-- );
-- alter table rotaEntrega add constraint FK_rotaEntrega
-- 	foreign key(codigoEmpresa) references empresa(codigo);
-- alter table rotaEntrega add constraint FK_rotaEntrega2
-- 	foreign key(codigoEmpresa,codigoFilial) references filial(empresa,codigo);
	
-- DELETE FROM rotaEntrega;
-- INSERT INTO rotaEntrega (siglaEstado, codigo, nome, status, codigoEmpresa, codigoFilial, prazoExpedicao)
--        VALUES('RJ', (SELECT NEXT VALUE FOR codigoRotaEntrega_id), 'Rota-01', 'xx', 1, 2, 12);
-- INSERT INTO rotaEntrega (siglaEstado, codigo, nome, status, codigoEmpresa, codigoFilial, prazoExpedicao)
--        VALUES('RJ', (SELECT NEXT VALUE FOR codigoRotaEntrega_id), 'Rota-02', 'yy', 1, 1, 10);
-- INSERT INTO rotaEntrega (siglaEstado, codigo, nome, status, codigoEmpresa, codigoFilial, prazoExpedicao)
--        VALUES('SP', (SELECT NEXT VALUE FOR codigoRotaEntrega_id), 'Rota-02', 'BB', 3, 3, 5);
-- INSERT INTO rotaEntrega (siglaEstado, codigo, nome, status, codigoEmpresa, codigoFilial, prazoExpedicao)
--        VALUES('SP', (SELECT NEXT VALUE FOR codigoRotaEntrega_id), 'Rota-02', 'AA', 3, 5, 2);


-- Microzona
-- drop table microzona;
-- create table microzona (
-- 	codigo Long not null auto_increment,
-- 	nome varchar not null,
-- 	status varchar not null,
-- 	atendimentoDiario varchar not null,
-- 	atendeSegunda varchar,
-- 	atendeTerca varchar,
-- 	atendeQuarta varchar,
-- 	atendeQuinta varchar,
-- 	atendeSexta varchar,
-- 	atendeSabado varchar,
-- 	codigoMunicipio Long not null,
-- 	estadoRota varchar not null,
-- 	codigoRota Long not null,
-- 	
-- 	primary key (codigo)
-- );
-- alter table microzona add constraint FK_microzona
-- 	foreign key(codigoMunicipio) references municipio(codigo);
-- alter table microzona add constraint FK_microzona2
-- 	foreign key(estadoRota,codigoRota) references rotaEntrega(siglaEstado,codigo);
	
-- DELETE FROM microzona;
-- INSERT INTO microzona (codigo, nome, status, atendimentoDiario, atendeSegunda, atendeTerca, atendeQuarta, atendeQuinta,
--                        atendeSexta, atendeSabado, codigoMunicipio, estadoRota, codigoRota)
--        VALUES ((SELECT NEXT VALUE FOR codigoMicrozona_id), 'MicroZona-1', 'xx', 'N', 'S', 'S', 'S', 'S',
--                        'S', 'N', 1, 'RJ', 1);
-- INSERT INTO microzona (codigo, nome, status, atendimentoDiario, atendeSegunda, atendeTerca, atendeQuarta, atendeQuinta,
--                        atendeSexta, atendeSabado, codigoMunicipio, estadoRota, codigoRota)
--        VALUES ((SELECT NEXT VALUE FOR codigoMicrozona_id), 'MicroZona-2', 'xx', 'N', 'S', 'S', 'S', 'S',
--                        'S', 'N', 2, 'RJ', 4);
-- INSERT INTO microzona (codigo, nome, status, atendimentoDiario, atendeSegunda, atendeTerca, atendeQuarta, atendeQuinta,
--                        atendeSexta, atendeSabado, codigoMunicipio, estadoRota, codigoRota)
--        VALUES ((SELECT NEXT VALUE FOR codigoMicrozona_id), 'MicroZona-3', 'xx', 'N', 'S', 'S', 'S', 'S',
--                        'S', 'N', 3, 'SP', 5);
	
	
-- Faixas CEP da Microzona
-- drop table faixasCEPMicrozona;
-- create table faixasCEPMicrozona(
-- 	codigoMicrozona Long not null,
-- 	codigoSequencial Long not null auto_increment,
-- 	CEPinicial int not null,
-- 	CEPFinal int not null,
-- 	
-- 	primary key (codigoMicrozona, codigoSequencial)
-- );

-- DELETE FROM faixasCEPMicrozona;
-- INSERT INTO faixasCEPMicrozona(codigoMicrozona, codigoSequencial, CEPInicial, CEPFinal)
--        VALUES (8, (SELECT NEXT VALUE FOR codigofaixasCEPMicrozona_id),        0, 20000001);
-- INSERT INTO faixasCEPMicrozona(codigoMicrozona, codigoSequencial, CEPInicial, CEPFinal)
--        VALUES (9, (SELECT NEXT VALUE FOR codigofaixasCEPMicrozona_id), 20000002, 40000001);
-- INSERT INTO faixasCEPMicrozona(codigoMicrozona, codigoSequencial, CEPInicial, CEPFinal)
--        VALUES (10, (SELECT NEXT VALUE FOR codigofaixasCEPMicrozona_id), 40000002, 50000001);
