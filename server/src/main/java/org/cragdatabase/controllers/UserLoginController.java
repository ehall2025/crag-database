package org.cragdatabase.controllers;

import org.cragdatabase.domain.UserLoginService;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.models.User;
import org.cragdatabase.models.UserWithJWT;
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
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        Result<UserWithJWT> result = null;
        try {
            result = userLoginService.login(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == null) {
            return new ResponseEntity<>("email or password was incorrect", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(result.getpayload(), HttpStatus.OK);
    }

    @PutMapping("/register/admin/{userId}")
    public ResponseEntity registerAdmin (@PathVariable int userId) {
        Result result = userLoginService.registerAdminAccount(userId);

        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result.getpayload(), HttpStatus.CREATED);
    }
}
