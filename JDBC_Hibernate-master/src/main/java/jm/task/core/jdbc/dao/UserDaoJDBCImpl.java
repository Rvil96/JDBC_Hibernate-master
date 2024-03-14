package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

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
        createUsersTable();
        try (Connection connection = Util.getConnection()) {
            String sql = "INSERT INTO " + Util.NAME_OF_TABLE + " (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(User user) {
        saveUser(user.getName(), user.getLastName(), user.getAge());
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();){
            String sql = "DELETE FROM " + Util.NAME_OF_TABLE + " WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Util.getConnection()) {
            String sql = "SELECT * FROM " + Util.NAME_OF_TABLE;
            PreparedStatement statement = connection.prepareStatement(sql);

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
        try (Connection connection = Util.getConnection()) {
            String sql = "DELETE FROM " + Util.NAME_OF_TABLE;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
