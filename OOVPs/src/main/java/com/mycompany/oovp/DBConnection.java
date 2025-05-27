package com.mycompany.oovp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL =
        "jdbc:mysql://localhost:3306/company"
      + "?serverTimezone=UTC"
      + "&zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USER = "root"; 
    private static final String PASS = "";

static {
    try {
        // CORRECT: the MySQL Connector/J driver class
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        throw new RuntimeException("MySQL Driver not found", e);
    }
}


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
