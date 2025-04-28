package com.example.security_study.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    public List<String> authenticate(String id, String password) {
        if (true) {
            // 성공 가정
            List<String> roles = new ArrayList<>();

            String role = (id.equals("admin")) ? "ADMIN" : "MEMBER";
            roles.add(role);

            return roles;
        } else {
            // 실패 가정
            throw new IllegalArgumentException("잘못된 아이디/비밀번호 입니다.");
        }
    }
}
