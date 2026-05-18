package org.cragdatabase.domain;

import org.cragdatabase.data.UserLoginRepository;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.domain.results.ResultType;
import org.cragdatabase.models.User;
import org.cragdatabase.models.UserWithJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.cragdatabase.config.SecurityConfig.BCRYPT_ENCODER_STRENGTH;

@Service
public class UserLoginService {
    @Autowired
    private final UserLoginRepository userLoginRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(BCRYPT_ENCODER_STRENGTH);

    public UserLoginService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    public Result<User> register(User user) {
        Result<User> result = new Result<>();

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        result.setpayload(userLoginRepository.createUser(user));

        if (result.getpayload() == null) {
            result.addErrorMessage("could not create account, email already in use", ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<UserWithJWT> login(User user) {
        Result<UserWithJWT> result = new Result<UserWithJWT>();
        Authentication authentication;

        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            result.setpayload(new UserWithJWT(user, jwtService.generateToken(user.getUsername())));
            return result;
        }

        return null;
    }
}
