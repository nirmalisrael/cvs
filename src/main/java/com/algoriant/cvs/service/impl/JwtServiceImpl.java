package com.algoriant.cvs.service.impl;

import com.algoriant.cvs.service.JwtService;
import com.algoriant.cvs.dto.JwtRequest;
import com.algoriant.cvs.dto.JwtResponse;
import com.algoriant.cvs.entity.User;
import com.algoriant.cvs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public JwtResponse login(JwtRequest jwtRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
                        jwtRequest.getPassword()));

        User user = (User) authentication.getPrincipal();
        String jwt = "Bearer " + jwtUtil.generateAccessToken(user);
        return new JwtResponse(user.getUsername(), user.getRoles(), jwt);
    }
}