package com.algoriant.cvs.service.impl;

import com.algoriant.cvs.entity.User;
import com.algoriant.cvs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        if (user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            return userRepository.save(user);
        }
        return null;
    }

    public String removeUser(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()){
            userRepository.deleteById(username);
            return username;
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
