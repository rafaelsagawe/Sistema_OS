-- Bando de dados do Sistema de Ordem de Serviços
create database db_sistema_os;

-- Usar banco criado
use db_sistema_os;

-- Criação da tabela de usuários

create table tb_usuario(
id_user int primary key,
usuario varchar(50) not null,
fone varchar(15),
login varchar(15) not null unique,
senha varchar(15) not null 
);

-- Descrição da tabela

describe tb_usuario;

-- Dados de teste

insert into tb_usuario (id_user, usuario, fone, login, senha)
value (1, 'Rafael Sagawe', '98745-6321', 'rafael', '123');

-- verificar dados inseridos

select * from tb_usuario;

-- outros usuários

insert into tb_usuario (id_user, usuario, fone, login, senha)
value 
(2, 'Administrador', '000-000', 'admin', '123'),
(3, 'Tecnico', '000-000', 'tec', '123'),
(4, 'Atendente', '000-000', 'atende', '123');

-- alterar item da tabela

update tb_usuario set fone='8888-4444' where id_user=2;

-- Apagar registro

delete from tb_usuario where id_user=1;