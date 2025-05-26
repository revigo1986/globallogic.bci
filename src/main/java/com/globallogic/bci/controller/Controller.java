package com.globallogic.bci.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from controller";
    }

    @PostMapping("/sign-up")
    public String signUp() {
        return null;
    }

    @GetMapping("/login")
    public String login() {
        return null;
    }
}
