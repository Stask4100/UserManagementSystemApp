package com.korniev.stas.user_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ім'я користувача не може бути порожнім")
    @Size(min = 3, max = 20, message = "Ім'я має бути від 3 до 20 символів")
    private String username;

    @Email(message = "Некоректна електронна пошта")
    @NotBlank(message = "Пошта не може бути порожньою")
    private String email;

    @NotBlank(message = "Роль не може бути порожньою")
    private String role;

    @NotBlank(message = "Пароль не може бути порожнім")
    @Size(min = 5, message = "Пароль має бути щонайменше 5 символів")
    private String password;

    // Порожній конструктор (обов'язковий для JPA)
    public User() {
    }

    // Повний конструктор
    public User(Long id, String username, String email, String role, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    // Гетери та Сетери

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
