package com.binarybachelor.genlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.binarybachelor.genlink.dto.UserLoginDTO;
import com.binarybachelor.genlink.dto.UserRegisterDTO;
import com.binarybachelor.genlink.dto.UserResponseDTO;
import com.binarybachelor.genlink.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegisterDTO request) {
        UserResponseDTO response = authService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody UserLoginDTO request) {
        UserResponseDTO response = authService.loginUser(request);
        return ResponseEntity.ok(response);
    }
}
