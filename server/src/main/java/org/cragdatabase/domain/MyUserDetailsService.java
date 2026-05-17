package org.cragdatabase.domain;

import org.cragdatabase.data.UserLoginRepository;
import org.cragdatabase.models.User;
import org.cragdatabase.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserLoginRepository userLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;
        try {
            user = userLoginRepository.findByUsername(username);
        } catch (SQLIntegrityConstraintViolationException e) { //TODO handle gracefully
            throw new RuntimeException(e);
        }

        if (user == null) {
            System.out.println("User not found"); //TODO refactor to use result
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user);
    }
}
