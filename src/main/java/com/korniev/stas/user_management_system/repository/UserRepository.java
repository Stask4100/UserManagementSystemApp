package com.korniev.stas.user_management_system.repository;

import com.korniev.stas.user_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
