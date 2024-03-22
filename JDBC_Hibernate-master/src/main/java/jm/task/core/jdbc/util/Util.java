package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/testdb";
    private final static String NAME = "root";
    private final static String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, NAME, PASSWORD);
    }

    public static SessionFactory getSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
