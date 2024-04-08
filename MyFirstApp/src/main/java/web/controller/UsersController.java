package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("allUser", users);
        return "users";
    }

    @GetMapping("/profile")
    public String showUserProfile(@RequestParam("id") Long id,
                           Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/create")
    public String newUser(Model model) {
        model.addAttribute("newUser", new User());
        return "createUser";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("updatableUser", userService.getUserById(id));
        return "/edit";
    }
    @PatchMapping("/update")
    public String update(@ModelAttribute User user, @RequestParam("id") Long id) {
        userService.updateUserById(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long id) {
        userService.removeUserById(id);
        return "redirect:/";
    }

}
