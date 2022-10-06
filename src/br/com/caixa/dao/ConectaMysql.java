package br.com.caixa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaMysql {

    static Connection con;

    public static Connection conectar() {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/controlecaixa?user=root&password=1234");
            con = DriverManager.getConnection("jdbc:mysql://localhost/controlecaixa", "root", "1234");

            System.out.println("Conexao realizada!!!");

        } catch (ClassNotFoundException e) {

            System.out.println("Class.forName, incorreto\n" + e.getMessage());

        } catch (SQLException e) {

            System.out.println("Erro de escrita, Driver MySql nao encontrado ou,");
            System.out.println("Usuario e Senha incorretos!!!\n" + e.getMessage());

        } 

        return con;

    }

    public static void main(String args[]) {

        //ConectaMysql conexao = new ConectaMysql();
        ConectaMysql.conectar();

    }

}
