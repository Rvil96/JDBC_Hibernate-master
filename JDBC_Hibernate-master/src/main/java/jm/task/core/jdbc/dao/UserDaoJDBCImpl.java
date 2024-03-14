package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

   }
   public boolean tableExist() {
       try (Connection connection = Util.getConnection()){
           DatabaseMetaData dmd = connection.getMetaData();
           ResultSet rs = dmd.getTables(null, null,Util.NAME_OF_TABLE, null);
           if(rs.next()) {
               return true;
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return false;
   }

   public void setStatement(String sql) {
       try (Connection connection = Util.getConnection()){
           Statement statement = connection.createStatement();
           statement.execute(sql);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

   }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + Util.NAME_OF_TABLE + " ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(50) NOT NULL,"
                + "lastName VARCHAR(50) NOT NULL,"
                + "age INT"
                + ")";
        setStatement(sql);

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS " + Util.NAME_OF_TABLE;
        setStatement(sql);
    }

    public void saveUser(String name, String lastName, byte age) {
        if (!tableExist()){
            createUsersTable();
        }
        try (Connection connection = Util.getConnection()) {
            String sql = "INSERT INTO " + Util.NAME_OF_TABLE + " (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
