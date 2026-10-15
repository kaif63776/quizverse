package com.kaif.quizbackend.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // MySQL JDBC Driver load karo
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Render / Cloud ke environment variables check karo
            String host = System.getenv("DB_HOST");
            String port = System.getenv("DB_PORT");
            String dbName = System.getenv("DB_NAME");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");

            String url;

            // Agar cloud environment variables set hain (Render par chalega toh ye chalega)
            if (host != null && !host.isEmpty()) {
                if (port == null || port.isEmpty()) {
                    port = "15523"; // Aiven default port
                }
                if (dbName == null || dbName.isEmpty()) {
                    dbName = "defaultdb"; // Aiven ka default database naam
                }
                
                // Aiven SSL secure connection URL
                url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useSSL=true&requireSSL=true&verifyServerCertificate=false";
                
                System.out.println("Connecting to Aiven Cloud Database...");
            } else {
                // Localhost (Jab apne laptop par chalaoge)
                String localHost = "localhost";
                String localPort = "3306";
                String localDb = "quiz_app"; // <-- YAHAN APNE LOCAL DATABASE KA NAAM LIKHNA (jaise quiz_app ya jo bhi ho)
                String localUser = "root";
                String localPass = "12345678"; // <-- YAHAN APNA LOCAL PASSWORD LIKHNA

                url = "jdbc:mysql://" + localHost + ":" + localPort + "/" + localDb + "?useSSL=false&serverTimezone=UTC";
                user = localUser;
                password = localPass;
                
                System.out.println("Connecting to Local Database...");
            }

            // Connection establish karo
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connected Successfully!");

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database Connection Failed!");
            e.printStackTrace();
        }
        
        return conn;
    }
}