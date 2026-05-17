package org.cragdatabase.controllers;

import org.cragdatabase.domain.UserLoginService;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserLoginController {

    @Autowired
    private final UserLoginService userLoginService;

    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        Result<User> result = userLoginService.register(user);

        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        String result = userLoginService.login(user);

        if (result == null) {
            return new ResponseEntity<>("failed to login", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity testJwt() {
        return new ResponseEntity("success!", HttpStatus.OK);
    }

}
