package com.mvntest.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Database {
    
    private static String url = "jdbc:postgresql://localhost/ecole";
    private static String user = "postgres";
    private static String password = "120420t";
public Database(){

}

public static Connection getConnection() throws SQLException{
        Connection connection = null;
        if(connection == null){
            try {
                connection = DriverManager.getConnection(url, user, password);
                
                //System.out.println("You're connected to the database!");
                
            } catch (SQLException e) {
                System.out.println("Error, can't connect to the datadabe: " + e.getMessage());
            }
        }   
        return connection;
    }

public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
    if(preparedStatement != null)
        preparedStatement.close();
       // System.out.println("PreparedStatement closed.");
    }
    public static void closeConnection(Connection connection) throws SQLException {
    if(connection != null)
        connection.close();
       // System.out.println("Connection closed.");
    }
    
}
