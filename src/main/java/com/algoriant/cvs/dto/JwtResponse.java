package com.algoriant.cvs.dto;

import com.algoriant.cvs.entity.Role;

import java.util.HashSet;
import java.util.Set;

public class JwtResponse {

    private String username;

    private Set<Role> roles = new HashSet<>();

    private String jwtToken;

    public JwtResponse(){

    }

    public JwtResponse(String username, Set<Role> roles, String jwtToken) {
        this.username = username;
        this.roles = roles;
        this.jwtToken = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}