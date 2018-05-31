/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.*;
/**
 *
 * @author rafael
 */
public class Conexao {
    // Metodo responsavel pela conexão
    public static Connection conector(){ //metodo conector
        java.sql.Connection conexao = null; // variavel conexao
        // chama do drive Mysql
        //String driver = "com.mysql.jdbc.Driver"; // Variavel driver para ligação com a biblioteca importada
        String driver = "com.mysql.cj.jdbc.Driver"; // Atualização para utilização de conexão com SSl
        // Armazenando informaçoes sobre o Banco
        String url="jdbc:mysql://localhost:3306/db_sistema_os";// Url do banco de dados
        String user = "sistema"; //usuário do banco de dados 
        String passowrd = "sistema"; // Senha do banco de dado
        // Estabelecendo a conexão
        // Tratamento de erro
           try { //Caso a conexao tenha sucesso
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, passowrd);
            return conexao;
        } catch (Exception e) {// casso tenha agum erro
            System.out.println("Erro de conexão");
               System.out.println(e);
            return null;
        }
     
    }
}
