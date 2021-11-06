package com.viswateja.farmstead.controller;

import com.viswateja.farmstead.entity.User;
import com.viswateja.farmstead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    
    @GetMapping("/user")
    public User getUserByEmail(@RequestBody User user) {
        return userService.getUserWithEmailAddress(user.getEmailAddress());
    }
}
