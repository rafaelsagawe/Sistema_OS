# Sistema de Ordem de Serviços

O sistema será feito com base através do curso de Java com Banco de Dados do portal AulaEAD, mas o ambiente de desenvolvimento será o Ubuntu 18.04 no lugar do Windows.

## Instalação dos programas
  sudo apt install mysql-server mysql-workbench mysql-workbench-data 
  
  O Netbeans foi instalado através do pacote .deb obtido do site do Netbeans.

# Criação do Usuário do sistema
O mysql foi necessário ajustes iniciais 
sudo mysql -u root # para acessar o mysql

mysql> SELECT User, Host, plugin FROM mysql.user; # para verificar os usuários do mysql

mysql> CREATE USER 'sistema-os'@'localhost' IDENTIFIED BY''; # *cria usuário para o sistema de OS's
mysql> GREAT ALL PRIVILEGES ON *.* TO 'sistema-os'@'localhost'; # *dá todos os privilégios ao usuário sistema-os
mysql> UPDATE user SET plugin='auth_socket' WHERE User= 'sistema-os'; # *atualiza o plugin de autenticação
mysql> FLUSH PRIVILEGES; # *atualiza os privilegias

