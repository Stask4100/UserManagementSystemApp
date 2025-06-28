package com.korniev.stas.user_management_system.service;

import com.korniev.stas.user_management_system.TelegramBot;
import com.korniev.stas.user_management_system.model.User;
import com.korniev.stas.user_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Value("${telegram.bot.chatId}")
    private String chatId;

    @Autowired
    private TelegramBot telegramBot;

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    public User createUser(User user) {
        User saved = repository.save(user);

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Створено користувача: " + user.getUsername());

        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace(); // або логер
        }

        return saved;
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        return repository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setRole(updatedUser.getRole());
            return repository.save(user);
        }).orElseGet(() -> {
            updatedUser.setId(id);
            return repository.save(updatedUser);
        });
    }
}
