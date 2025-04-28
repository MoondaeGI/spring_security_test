package com.example.security_study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {
    @GetMapping("/exam01")
    public String exam01() {
        System.out.println("Admin - exam01");
        return "Admin - exam01";
    }
}
