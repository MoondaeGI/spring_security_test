package com.example.security_study.controller;

import com.example.security_study.service.AuthService;
import com.example.security_study.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired private AuthService authService;
    @Autowired private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> request) {
        String id = (String) request.get("id");
        String password = (String) request.get("password");

        try {
            List<String> roles = authService.authenticate(id, password);

            String token = jwtUtil.createToken(id, roles);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
