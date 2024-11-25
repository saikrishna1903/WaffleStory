package com.wafflestory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wafflestory.entities.UserDetailCred;
import com.wafflestory.repo.LoginCredRepo;

@Service
public class UserDetailService {

    @Autowired
    private LoginCredRepo userRepository;
    
    @Autowired
    private PasswordEncoder encoder;

    // Get all users
    public List<UserDetailCred> getAllUsers() {
        return userRepository.findAll();
    }

    // Add a new user
    public UserDetailCred addUser(UserDetailCred user) {
    	user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        Optional<UserDetailCred> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}