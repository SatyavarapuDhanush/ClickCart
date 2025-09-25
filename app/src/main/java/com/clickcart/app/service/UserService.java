package com.clickcart.app.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clickcart.app.dto.UserDTO;
import com.clickcart.app.entity.User;
import com.clickcart.app.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public User registerUser(UserDTO userDTO) throws Exception {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new Exception("Username already exists");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new Exception("Email already exists");
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }
    public Optional<User> getUserbyUsername(String username){
        return userRepository.findByUsername(username);
    }
    public User updateUser(Long id, UserDTO userDTO) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
