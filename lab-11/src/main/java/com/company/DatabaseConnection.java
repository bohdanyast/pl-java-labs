package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {
    private final Connection connection;

    private static Connection connect() throws IOException, SQLException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            props.load(fis);
        }

        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        return DriverManager.getConnection(url, username, password);
    }

    public DatabaseConnection() throws SQLException, IOException {
        this.connection = connect();
    }

    public DatabaseConnection(Connection connection) {
        this.connection = connection;
    }

    public void close() throws SQLException {
        connection.close();
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }
}