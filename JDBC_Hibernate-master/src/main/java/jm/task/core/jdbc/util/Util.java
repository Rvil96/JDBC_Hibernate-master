package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String url = "jdbc:mysql://localhost:3306/testdb";
    private static String name = "root";
    private static String password = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, name, password);
    }

    public static SessionFactory getSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
