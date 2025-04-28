package com.example.security_study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member")
@RestController
public class MemberController {
    @GetMapping
    public String exam01() {
        System.out.println("Member - exam01");
        return "Member - exam01";
    }
}
