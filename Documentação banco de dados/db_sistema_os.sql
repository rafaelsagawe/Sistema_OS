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

-- cadastro de clientes
create table tb_cliente(
id_cliente int primary key auto_increment,
nome_cliente varchar(50) not null,
end_cliente varchar(100),
fone_cliente varchar(15) not null,
email_cliente varchar(50)
);

-- Descrição da tabela criada
describe tb_cliente;

-- Inserindo clientes
insert into tb_cliente(nome_cliente, end_cliente, fone_cliente, email_cliente)
values
('Blil Gates', 'Rua MS', '95-98-7-8-10', 'bill@ms.com'),
('Linus Torvald', 'Rua Tux', '0.2', 'linux@kernel.com');

-- Verificar tabela cliente
select * from tb_cliente;

-- Tabela de ordem de serviço 

create table tb_os(
id_os int primary key auto_increment,
data_os timestamp default current_timestamp, -- dada e hora gerada automaticamente
equipamentos varchar(150) not null,
defeito varchar(150) not null,
servico varchar(150),
tecnico varchar(30),
valor decimal(10,2),
id_cliente int not null,
foreign key(id_cliente) references tb_cliente(id_cliente)
);

describe tb_os;

-- Dados de teste
insert into tb_os (equipamentos, defeito, servico, tecnico, valor, id_cliente)
values
("Computador", "Não Liga", "Toca da fonte", "Zé" , 50.8, 2),
("Notebook", "Não Liga", "Toca da fonte", "João" , 50.8, 2),
("Computador", "Tela azul", "Formatação", "Zé" , 75, 2);

select * from tb_os;

-- União das tabelas para criação do relatorio das ordens de serviços
select
O.id_os, equipamentos, defeito, servico, tecnico, valor,
C.nome_cliente, end_cliente, fone_cliente, email_cliente
from tb_os as O
inner join tb_cliente as C
on O.id_cliente=C.id_cliente;

select * from tb_usuario where login='admin' and senha='123';

