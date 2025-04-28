package com.example.security_study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/guest")
@RestController
public class GuestController {
    @GetMapping
    public String exam01() {
        System.out.println("Guest - exam01");
        return "Guest - exam01";
    }
}
