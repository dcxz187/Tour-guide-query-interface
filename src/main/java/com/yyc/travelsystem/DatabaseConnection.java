package com.yyc.travelsystem;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/tourism_db");
        config.setUsername("root");
        config.setPassword("123456");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}