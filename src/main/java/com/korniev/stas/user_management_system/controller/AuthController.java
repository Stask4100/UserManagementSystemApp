package com.korniev.stas.user_management_system.controller;

import com.korniev.stas.user_management_system.dto.AuthRequest;
import com.korniev.stas.user_management_system.dto.AuthResponse;
import com.korniev.stas.user_management_system.model.User;
import com.korniev.stas.user_management_system.repository.UserRepository;
import com.korniev.stas.user_management_system.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Користувач з таким ім'ям вже існує");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword()); // У майбутньому — шифруємо!
        newUser.setEmail(request.getEmail());
        newUser.setRole("USER");

        userRepository.save(newUser);
        return ResponseEntity.ok("Користувач зареєстрований");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());

        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            String token = jwtService.generateToken(user.get().getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        }

        return ResponseEntity.status(401).build(); // Unauthorized
    }
}
