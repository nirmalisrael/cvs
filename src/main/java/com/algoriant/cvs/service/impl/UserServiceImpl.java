package com.algoriant.cvs.service.impl;

import com.algoriant.cvs.entity.User;
import com.algoriant.cvs.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

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
        if (optionalUser.isPresent()) {
            userRepository.deleteById(username);
            return username;
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<User> optionalUser = userRepository.findByUsername(username);
            return optionalUser.orElseThrow(null);
        } catch (UsernameNotFoundException exception) {
            LOGGER.error("Error! While loadUserByUsername: " + username, exception);
            return null;
        }
    }

    public User changePasswordByUsername(User user) {
        User updateUser = (User) loadUserByUsername(user.getUsername());
        updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(updateUser);
    }
}
