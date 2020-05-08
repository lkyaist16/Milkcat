package com.part6.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public static final String URL = "jdbc:mysql://122.51.97.239:3306/demo?useUnicode=true&characterEncoding=utf8";

    public static final String USER = "root";

    public static final String PASSWORD = "123456";

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
