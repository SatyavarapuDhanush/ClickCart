package com.clickcart.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickcart.app.entity.User;

public interface  UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);
}
