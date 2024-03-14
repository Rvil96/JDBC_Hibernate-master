package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.dropUsersTable();
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.saveUser("Ravil", "Ishkulov", (byte) 28);
    }
}
