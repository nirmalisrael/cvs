package com.algoriant.cvs.util;

import com.algoriant.cvs.entity.Role;
import com.algoriant.cvs.entity.User;
import com.algoriant.cvs.repository.RoleRepository;
import com.algoriant.cvs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        // Create a default admin user
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("admin"));

        Role adminRole = new Role("admin", "default role for admin");
        Role userRole = new Role("user", "default role for students");
        roleRepository.saveAll(Arrays.asList(adminRole, userRole));

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        roles.add(userRole);
        adminUser.setRoles(roles);

        userRepository.save(adminUser);
    }
}
