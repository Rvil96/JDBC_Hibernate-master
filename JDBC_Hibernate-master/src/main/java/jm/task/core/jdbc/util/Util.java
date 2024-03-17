package jm.task.core.jdbc.util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static String url;
    private static String name;
    private static String password;
    public static final String NAME_OF_TABLE = "users";
    private static Properties properties = new Properties();
    private static FileInputStream fis;

    static {
        loadFile();
    }

    private static void loadFile() {
        try {
            fis = new FileInputStream("resources/application.properties");
            properties.load(fis);
            url = properties.getProperty("db.url");
            name = properties.getProperty("db.username");
            password = properties.getProperty("db.password");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, name, password);
    }
}
