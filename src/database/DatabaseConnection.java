package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/bus_booking";
    private static final String USER = "bususer";
    private static final String PASSWORD = "ab1234";

    public static Connection getConnection() {

        Connection con = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully!");
        } 
        catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }

        return con;
    }
}
