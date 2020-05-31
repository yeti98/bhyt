package com.devculi.bhyt.controllers;

import com.devculi.bhyt.dto.LoginRequest;
import com.devculi.bhyt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest loginRequest){
        System.out.println("HERE");
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
