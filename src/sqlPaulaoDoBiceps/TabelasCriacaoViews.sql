
delimiter /
drop table if exists compra;
drop table if exists venda;
drop table if exists teste_drive;
drop table if exists unidade;
drop table if exists advertencia;
drop table if exists registro_ponto;
drop table if exists funcionario;
drop table if exists cliente;
drop table if exists versao;
drop table if exists modelo;
drop table if exists marca;
drop table if exists categoria_modelo;
drop table if exists transmissao;
drop table if exists cor;
drop table if exists turno;
drop table if exists cargo;
drop table if exists forma_pagamento;
drop table if exists endereco;
/
delimiter ;

delimiter /
create table marca(
id int auto_increment,
nome varchar(50) unique,
primary key(id)
);

create table categoria_modelo(
id int auto_increment,
nome varchar(20) unique,
primary key(id)
);

create table modelo(
id int auto_increment,
id_marca int,
id_categoria_modelo int,
nome varchar(40) unique,
foreign key(id_marca) references marca(id),
foreign key(id_categoria_modelo) references categoria_modelo(id),
primary key(id)
);

create table versao(
id int auto_increment,
id_modelo int,
nome varchar(30),
unique(id_modelo, nome),
lancamento date,
foreign key(id_modelo) references modelo(id),
primary key(id)
);

create table cor(
id int auto_increment,
nome varchar(30) unique,
primary key(id)
);

create table transmissao(
id int auto_increment,
tipo varchar(30) unique,
primary key(id)
);

create table unidade(
id int auto_increment,
id_transmissao int,
id_cor int,
id_versao int,
ano smallint,
placa varchar(8) unique,
quilometragem int,
valor_unitario decimal(9,2),
disponibilidade boolean,
foreign key(id_transmissao) references transmissao(id),
foreign key(id_cor) references cor(id),
foreign key(id_versao) references versao(id),
primary key(id)
);

create table turno(
id int auto_increment,
inicio time,
fim time,
unique(inicio, fim),
primary key(id)
);

create table cargo(
id int auto_increment,
nome varchar(40) unique,
primary key(id)
);

create table forma_pagamento(
id int auto_increment,
nome varchar(30) unique,
primary key(id)
);

create table endereco(
id int auto_increment,
logradouro varchar(200),
numero smallint,
complemento varchar(100),
unique(logradouro, numero, complemento),
cep char(9),
primary key(id)
);

create table cliente(
id int auto_increment,
id_endereco int,
nome varchar(100),
email varchar(256) unique,
contato char(15) unique,
cpf char(14) unique,
data_nascimento date,
data_registro date,
primary key(id),
foreign key(id_endereco) references endereco(id)
);

create table funcionario(
id int auto_increment,
id_endereco int,
id_cargo int,
id_turno int,
nome varchar(100),
email varchar(256) unique,
contato char(15) unique,
cpf char(14) unique,
data_nascimento date,
data_registro date,
dia_pagamento tinyint,
salario_fixo decimal(10,2),
comissao decimal(10,2),
intervalo1 time,
intervalo2 time,
duracao_intervalo tinyint,
disponivel boolean,
primary key(id),
foreign key(id_endereco) references endereco(id),
foreign key(id_cargo) references cargo(id),
foreign key(id_turno) references turno(id)
);

create table advertencia(
id int auto_increment, 
id_funcionario int,
motivo text,
gravidade tinyint,
foreign key(id_funcionario) references funcionario(id),
primary key(id)
);

create table registro_ponto(
id int auto_increment, 
id_funcionario int,
entrada datetime,
saida datetime,
primary key(id),
foreign key(id_funcionario) references funcionario(id)
);

create table teste_drive(
id int auto_increment,
id_funcionario int,
id_cliente int,
id_unidade int,
inicio datetime,
fim datetime,
primary key(id),
foreign key(id_funcionario) references funcionario(id),
foreign key(id_cliente) references cliente(id),
foreign key(id_unidade) references unidade(id)
);

create table compra(
id int auto_increment,
id_funcionario int,
id_cliente int,
id_unidade int,
dia_horario datetime,
primary key(id),
foreign key(id_funcionario) references funcionario(id),
foreign key(id_cliente) references cliente(id),
foreign key(id_unidade) references unidade(id)
);

create table venda(
id int auto_increment,
id_funcionario int,
id_cliente int,
id_unidade int,
id_forma_pagamento int,
dia_horario datetime,
desconto decimal(9,2),
parcelas tinyint,
juros decimal(4,2),
primary key(id),
foreign key(id_funcionario) references funcionario(id),
foreign key(id_cliente) references cliente(id),
foreign key(id_unidade) references unidade(id)
);
/
delimiter ;

create view unidades_para_venda as
select ma.nome as marca, mo.nome as modelo, ve.nome as versao, co.nome as cor, un.valor_unitario
from unidade un
inner join cor co on co.id = un.id_cor
inner join versao ve on ve.id = un.id_versao
inner join modelo mo on mo.id = ve.id_modelo
inner join marca ma on ma.id = mo.id_marca
where un.disponibilidade = 1
order by un.valor_unitario;

select *
from unidades_para_venda;

create view melhores_funcionarios_ultimo_mes as
select fu.nome as funcionario, fu.cpf, count(ve.id_funcionario) as numero_vendas, sum(un.valor_unitario+(un.valor_unitario*(ve.juros/100))-ve.desconto) as valor_vendas
from funcionario fu
inner join venda ve on fu.id = ve.id_funcionario
inner join unidade un on un.id = ve.id_unidade
where ve.dia_horario >= now() - interval 1 month
group by fu.nome, fu.cpf
order by numero_vendas;

select *
from vendas_funcionarios;

create view compras_clientes as
select cl.nome as cliente, cl.cpf, count(ve.id_cliente) as total_compras
from cliente cl
inner join venda ve on ve.id_cliente = cl.id
group by cl.nome, cl.cpf
order by total_compras;

select *
from compras_clientes;

create view modelos_mais_vendidos_ultimo_ano as
select mo.nome as modelo, count(ve.id_unidade) as total_vendas_ultimo_ano
from modelo mo
inner join versao ver on ver.id_modelo = mo.id
inner join unidade un on un.id_versao = ver.id
inner join venda ve on ve.id_unidade = un.id
where ve.dia_horario >= now() - interval 1 year
group by mo.nome
order by total_vendas_ultimo_ano desc;

select *
from modelos_mais_vendidos_ultimo_ano;

create view dados_funcionarios as 
select fu.nome, fu.cpf, fu.contato, fu.email, ca.nome as cargo, concat(en.logradouro, " ", en.numero) as endereco
from funcionario fu
inner join cargo ca on fu.id_cargo = ca.id
inner join endereco en on fu.id_endereco = en.id;

select *
from dados_funcionarios;

create view funcionarios_mais_advertidos as
select fu.nome, fu.cpf, count(ad.id_funcionario) as advertencias, avg(ad.gravidade) as media_gravidade_advertencias
from funcionario fu
inner join advertencia ad on ad.id_funcionario = fu.id
group by fu.nome, fu.cpf
order by advertencias desc;

select *
from funcionarios_mais_advertidos;
