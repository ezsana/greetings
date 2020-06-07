package com.codecool.zsana.tdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    public static Connection connect() {
        Connection conn;

        try {
            String url = "";

            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static Connection closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }
}