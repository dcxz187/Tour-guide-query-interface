package com.yyc.TourGuideQueryInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/tourism_db";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    // 根据用户名和密码创建数据库连接
    public static Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(JDBC_URL, username, password);
    }
}
