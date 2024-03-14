package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        User user1 = new User("Perso1", "LastNamePerson1", (byte) 10);
        User user2 = new User("Perso2", "LastNamePerson2", (byte) 15);
        User user3 = new User("Perso3", "LastNamePerson3", (byte) 14);
        User user4 = new User("Perso4", "LastNamePerson4", (byte) 13);

        userService.createUsersTable();

        userService.saveUser(user1);
        System.out.println("User с именем — " + user1.getName() + " добавлен в базу данных");
        userService.saveUser(user2);
        System.out.println("User с именем — " + user2.getName() + " добавлен в базу данных");
        userService.saveUser(user3);
        System.out.println("User с именем — " + user3.getName() + " добавлен в базу данных");
        userService.saveUser(user4);
        System.out.println("User с именем — " + user4.getName() + " добавлен в базу данных");

        List<User> userList = userService.getAllUsers();
        System.out.println(userList);

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
