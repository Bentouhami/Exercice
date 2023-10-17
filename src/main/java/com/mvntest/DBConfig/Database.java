package com.mvntest.DBConfig;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    static Connection connexion = null;
    static Statement statement = null;

    public static String url = "jdbc:postgresql://localhost/";

    public static void setUrl(String urlDb) {
        url = urlDb;
    }

    private static String user = "postgres";
    private static String password = "120420t";

    private Database() {

<<<<<<< HEAD
    }

    public static Connection getConnection() throws SQLException {
        if (connexion == null || connexion.isClosed()) {
            try {
                connexion = DriverManager.getConnection(url, user, password);

=======
public static Connection getConnection() throws SQLException{
        Connection connection = null;
        if(connection == null || connection.isClosed()){
            try {
                connection = DriverManager.getConnection(url, user, password);
                //System.out.println("You're connected to the database!");
                
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return connexion;
    }

    private static void createDatabase(String DatabaseName) throws SQLException {

        String createDb = "CREATE DATABASE " + DatabaseName;
        statement = connexion.createStatement();
        statement.executeUpdate(createDb);

    }

    public static Connection getConnection(String databaseName) throws SQLException {
        String create = databaseName;
        createDatabase(create);

        return DriverManager.getConnection(url + create, user, password);
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null)
            preparedStatement.close();
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null)
            connection.close();
    }

}
