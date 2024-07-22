package com.algoriant.cvs.controller;

import com.algoriant.cvs.entity.User;
import com.algoriant.cvs.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cvs", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping(value = "/createUser")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/removeUser")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Map<String, String>> removeUser(String username) {
        Map<String, String> response = new HashMap<>();
        try {
            String deleteResponse = userService.removeUser(username);
            if (deleteResponse == null)
                response.put("message", username + " NOT FOUND");
            else
                response.put("message", deleteResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getUserByUsername")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        try {
            User user = (User) userService.loadUserByUsername(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getAllUsers")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
