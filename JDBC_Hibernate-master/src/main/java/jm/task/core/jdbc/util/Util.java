package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/testdb";
    private static final String name = "root";
    private static final String password = "root";
    public static final String NAME_OF_TABLE = "users";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, name, password);
    }
}
