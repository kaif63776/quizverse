package com.kaif.quizbackend.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Database credentials - update with your MySQL username and password
    private static final String URL = "jdbc:mysql://localhost:3306/quiz_app";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678"; 

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }
        return connection;
    }
}