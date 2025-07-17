package com.korniev.stas.user_management_system.controller;

import com.korniev.stas.user_management_system.model.User;
import com.korniev.stas.user_management_system.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final UserRepository userRepository;

    public SearchController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/search")
    public String searchUsers(@RequestParam("username") String username, Model model) {
        List<User> users = userRepository.findByUsernameContainingIgnoreCase(username);
        model.addAttribute("users", users);
        return "search-results"; // назва твого HTML-файлу (без .html)
    }
}
