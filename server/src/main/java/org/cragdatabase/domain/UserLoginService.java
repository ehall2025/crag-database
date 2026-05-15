package org.cragdatabase.domain;

import org.cragdatabase.data.UserLoginRepository;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.domain.results.ResultType;
import org.cragdatabase.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static org.cragdatabase.config.SecurityConfig.BCRYPT_ENCODER_STRENGTH;

@Service
public class UserLoginService {
    @Autowired
    private final UserLoginRepository userLoginRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    //TODO decide if use @Bean or @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(BCRYPT_ENCODER_STRENGTH);

    public UserLoginService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    //TODO return result
    public Result<User> register(User user) {
        Result<User> result = new Result<>();

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        result.setpayload(userLoginRepository.createUser(user));

        if (result.getpayload() == null) {
            result.addErrorMessage("could not create user", ResultType.NOT_FOUND);
        }

        return result;
    }

    //TODO return result
    public String login(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername()); //TODO make sure to validate the token when connecting everything together
        }

        return null;
    }
}
