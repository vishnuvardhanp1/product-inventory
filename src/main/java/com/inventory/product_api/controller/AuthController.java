package com.inventory.product_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.product_api.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        if (username.equals("admin") && password.equals("1234")) {
           // return "mysecrettoken123"; // token
        	return JwtUtil.generateToken(username);
        } else {
            return "Invalid credentials";
        }
    }
}
