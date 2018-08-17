package com.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {

    private Connection connection = null;

    public void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:D:\\weatherDB.db";

        connection = DriverManager.getConnection(url);
        System.out.println("Connection established");
        connection.close();
    }

}
