package com.part6.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public static final String URL = "jdbc:mysql://";

    public static final String USER = "";

    public static final String PASSWORD = "";

    private static Connection connection = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
