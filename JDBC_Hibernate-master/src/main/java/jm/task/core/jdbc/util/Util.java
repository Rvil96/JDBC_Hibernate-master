package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/testdb";
    private final static String NAME = "root";
    private final static String PASSWORD = "root";
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String DIALECT = "org.hibernate.dialect.MySQL8Dialect";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, NAME, PASSWORD);
    }

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, DRIVER);
        properties.put(Environment.URL, URL);
        properties.put(Environment.USER, NAME);
        properties.put(Environment.PASS, PASSWORD);
        properties.put(Environment.DIALECT, DIALECT);


        return new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
