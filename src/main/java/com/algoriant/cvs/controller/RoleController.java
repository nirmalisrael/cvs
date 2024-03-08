package com.algoriant.cvs.controller;

import com.algoriant.cvs.entity.Role;
import com.algoriant.cvs.service.RoleService;
import com.algoriant.cvs.service.impl.RoleServiceImpl;
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
@PreAuthorize("hasAuthority('admin')")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/createRole")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        try {
            return new ResponseEntity<>(roleService.createRole(role), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/removeRole")
    public ResponseEntity<Map<String, String>> removeRole(@RequestParam String roleName) {
        Map<String, String> response = new HashMap<>();
        try {
            String deleteResponse = roleService.removeRole(roleName);
            if (deleteResponse == null)
                response.put("message", roleName + " NOT FOUND");
            else
                response.put("message", deleteResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getRoleById")
    public ResponseEntity<Role> getRoleById(@RequestParam String roleName) {
        try {
            return new ResponseEntity<>(roleService.getRoleById(roleName), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/getAllRoles")
    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

}
