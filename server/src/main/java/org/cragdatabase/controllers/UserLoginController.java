package org.cragdatabase.controllers;

import org.cragdatabase.domain.UserLoginService;
import org.cragdatabase.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {

    @Autowired
    private final UserLoginService userLoginService;

    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    //TODO return proper response
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userLoginService.register(user);
    }

    //TODO return proper response
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userLoginService.login(user) + "";
    }

}
