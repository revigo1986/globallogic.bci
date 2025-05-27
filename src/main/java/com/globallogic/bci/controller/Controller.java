package com.globallogic.bci.controller;

import com.globallogic.bci.dto.UserDto;
import com.globallogic.bci.model.User;
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
    public UserDto signUp(@RequestBody UserDto userDto) {

        userDto = userService.createUser(userDto);

        return userDto;
    }

    @GetMapping("/login")
    public UserDto login(@RequestParam String token) {

        return userService.getUser(token);
    }
}
