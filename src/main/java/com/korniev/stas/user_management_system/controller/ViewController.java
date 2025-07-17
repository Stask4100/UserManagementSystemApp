package com.korniev.stas.user_management_system.controller;

import com.korniev.stas.user_management_system.model.User;
import com.korniev.stas.user_management_system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ViewController {

    private final UserService userService;

    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/view")
    public String viewUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/users/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/users/create")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users/view"; // після створення повертаємо на список
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users/view";
    }

    @GetMapping("/users/update/{id}")
    public String showUpdatePage(@PathVariable("id") Long id, Model model) {
        Optional<User> optionalUser = userService.getUserById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Тут ви можете працювати з об'єктом 'user'
            model.addAttribute("user", user);
        } else {
            // Об'єкт не знайдено, потрібно обробити цю ситуацію
            // Наприклад, перенаправити на сторінку помилки або на список користувачів
            return "redirect:/users/view";
        }
        return "update-user";
    }

    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user.getId(), user);
        return "redirect:/users/view";
    }

}
