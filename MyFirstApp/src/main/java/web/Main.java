package web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import web.config.ConfigDAO;
import web.dao.UserDao;
import web.model.User;
import web.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigDAO.class);


        UserService userService = context.getBean(UserService.class);
//

        userService.getAllUsers().stream().forEach(System.out::println);

    }
}
