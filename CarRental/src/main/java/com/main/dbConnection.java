package com.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    public static Connection doDBConnection() throws SQLException {
        final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "SYSTEM";
        String password = "Oracle@123";
        Connection con = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(URL, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
