package org.cragdatabase.domain;

import org.cragdatabase.data.UserLoginRepository;
import org.cragdatabase.models.User;
import org.cragdatabase.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserDetailsService {
    @Autowired
    private final UserLoginRepository userLoginRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    //TODO decide if use @Bean or @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12); //TODO de-magic bcrypt strength

    public UserLoginService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userLoginRepository.findByUsername(username);

        if (user == null) {
            System.out.println("User not found"); //TODO refactor to use result
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user);
    }

    //TODO return result
    public User register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user = userLoginRepository.createUser(user);

        return user;
    }

    //TODO return result
    public String login(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken();
        }
        return "failed to login";
    }
}
