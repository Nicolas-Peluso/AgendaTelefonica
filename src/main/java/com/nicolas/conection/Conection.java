package com.nicolas.conection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conection{
    private static String url = "jdbc:mysql://localhost:3306/agendaTelefonica?user=root&password=123456789";

    public static Connection Conect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return conn;
    }
}