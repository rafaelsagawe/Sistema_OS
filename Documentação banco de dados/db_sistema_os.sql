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

-- teste da função de login
select * from tb_usuario where login='admin' and senha='123';

-- Adição do campo perfil do usuario
alter table tb_usuario add column perfil varchar(20) not null;

-- verificar se foi criada a coluna
describe tb_usuario;

-- para deletar a coluna
alter table tb_usuario drop column perfil;

-- atualizar usuários existente
update tb_usuario set perfil='admin' where id_user=1;
update tb_usuario set perfil='normal' where id_user=2;
update tb_usuario set perfil='normal' where id_user=3;

-- Teste para a criação do formulario de usuário
describe tb_usuario; -- descrição da tabela
select * from tb_usuario; -- seleção de todos os usuários
select * from tb_usuario where id_user=1; -- seleciona apanas o usuário com id 1
DELETE FROM tb_usuario WHERE id_user=123; -- Deletar usuário de teste

-- Teste para a criação do formulario de clientes
describe tb_cliente;
select * from tb_cliente;
select * from tb_cliente where nome_cliente like 'l%';

-- Teste para a criação do formulario de OS
select * from tb_cliente;
select id_cliente, nome_cliente, fone_cliente from tb_cliente where nome_cliente like 'l%';
-- Linha com uso de alias
select id_cliente as ID, nome_cliente as Nome, fone_cliente as Telefone from tb_cliente where nome_cliente like 'l%';

describe tb_os;

-- Linhas adicionando campos tipo e situação
alter table tb_os add tipo varchar(15) not null after data_os;
alter table tb_os add situacao varchar(20) not null after tipo;

select * from tb_os where id_os = 2;

select * from tb_cliente;

-- Criação do query do relatorio das OS
-- A variavel osRel contem os dados da tabela tb_os
-- A variavel cliRel contem os dados da tabela tb_cliente
select
osRel.id_os, data_os, tipo, situacao, equipamentos, valor,
cliRel.nome_cliente, fone_cliente
from tb_os as osRel
inner join tb_cliente as cliRel
on osRel.id_cliente=cliRel.id_cliente


