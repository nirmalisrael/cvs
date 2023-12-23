package com.algoriant.cvs.service;

import com.algoriant.cvs.dto.JwtRequest;
import com.algoriant.cvs.dto.JwtResponse;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {

    JwtResponse login(JwtRequest jwtRequest);
}
