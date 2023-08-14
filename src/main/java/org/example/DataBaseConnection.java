package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {
    private static String url = "jdbc:mysql://localhost:3307/gestionetudian";
    private static String user = "root";
    private static String password = "";
    private static Connection connection;
    public static Connection connectToDB() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return connection;
    }
}
