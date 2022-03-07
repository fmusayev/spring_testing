package com.fintech.UniTech.controller;

import com.fintech.UniTech.dto.LoginDto;
import com.fintech.UniTech.dto.RegisterDto;
import com.fintech.UniTech.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public RegisterDto registerUser(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @PostMapping("/login")
    public LoginDto loginUser(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }
}

