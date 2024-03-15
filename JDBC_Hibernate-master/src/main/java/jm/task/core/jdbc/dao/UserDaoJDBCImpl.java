package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;

    public UserDaoJDBCImpl() {
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   public void setStatement(String sql) {
       try (PreparedStatement statement = connection.prepareStatement(sql)) {
           statement.executeUpdate();
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
        createUsersTable();
        String sql = "INSERT INTO " + Util.NAME_OF_TABLE + " (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void removeUserById(long id) {
        String sql = "DELETE FROM " + Util.NAME_OF_TABLE + " WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM " + Util.NAME_OF_TABLE;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");

                User user = new User(name, lastName, age);
                user.setId(id);
                userList.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при извлечении данных из таблицы: " + e.getMessage());
        }

        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM " + Util.NAME_OF_TABLE;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void connectionClose() {
        try {
            if (connection != null) {
            connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
