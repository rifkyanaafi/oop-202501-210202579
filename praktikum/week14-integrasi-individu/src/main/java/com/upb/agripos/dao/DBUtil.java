package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBUtil {
    private static Connection connection;
    private static final String DB_URL = "jdbc:sqlite:agripos.db";

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(DB_URL);
                initializeDatabase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void initializeDatabase() {
        try (Statement stmt = connection.createStatement()) {
            // Buat tabel jika belum ada
            stmt.execute("CREATE TABLE IF NOT EXISTS product (" +
                    "code TEXT PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "price REAL NOT NULL, " +
                    "stock INTEGER NOT NULL" +
                    ")");
            System.out.println("Database initialized successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
