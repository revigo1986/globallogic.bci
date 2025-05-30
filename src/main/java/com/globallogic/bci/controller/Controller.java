package com.globallogic.bci.controller;

import com.globallogic.bci.dto.UserDto;
import com.globallogic.bci.dto.UserGetDto;
import com.globallogic.bci.dto.UserPostDto;
import com.globallogic.bci.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private IUserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from controller";
    }

    @PostMapping("/sign-up")
    public UserPostDto signUp(@RequestBody UserDto userDto) {

        return userService.createUser(userDto);

    }

    @GetMapping("/login")
    public UserGetDto login(@RequestParam String token) {

        return userService.getUser(token);

    }
}
