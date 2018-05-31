# Sistema de Ordem de Serviços

O sistema será feito com base através do curso de Java com Banco de Dados do portal AulaEAD, mas o ambiente de desenvolvimento será o Ubuntu 18.04 no lugar do Windows.

## Instalação dos programas
  sudo apt install mysql-server mysql-workbench mysql-workbench-data 
  
  O Netbeans foi instalado através do pacote .deb obtido do site do Netbeans, mas ele está apresentando um bug na versão 8.2 quanto ao JDK9, assim foi necessário reinstalá-lo usando a outra JDK8.
  
  sudo add-apt-repository ppa:webupd8team/java
  sudo apt-get install oracle-java8-installer

  
# MYSQL
## Criação do Usuário do sistema
O mysql foi necessário ajustes iniciais 
sudo mysql -u root # para acessar o mysql

mysql> SELECT User, Host, plugin FROM mysql.user; # para verificar os usuários do mysql

mysql> CREATE USER 'sistema'@'localhost' IDENTIFIED BY'sistema'; # *cria usuário para o sistema de OS's

mysql> GRANT ALL PRIVILEGES ON *.* TO 'sistema'@'localhost'; # *dá todos os privilégios ao usuário sistema-os

mysql> FLUSH PRIVILEGES; # *atualiza os privilegias

### Deletar usuário do Mysql 
mysql> DROP USER 'sistema-os'@'localhost'

# Bibliografia
Compatibilidade do Netbeans
https://www.digitalocean.com/community/tutorials/como-instalar-o-java-com-apt-get-no-ubuntu-16-04-pt

Configurações do Mysql
https://www.digitalocean.com/community/tutorials/como-criar-um-novo-usuario-e-conceder-permissoes-no-mysql-pt
