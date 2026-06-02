package org.cragdatabase.domain;

import org.cragdatabase.data.UserLoginRepository;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.domain.results.ResultType;
import org.cragdatabase.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserLoginServiceTest {

    @Autowired
    UserLoginService service;

    @MockBean
    UserLoginRepository userLoginRepository;

    @Test
    void shouldRegister() {
        User user = new User("new@user.com", "password");
        User mockOut = new User(1, "new@user.com", "encodedPassword", "ROLE_USER");

        when(userLoginRepository.createUser(any())).thenReturn(mockOut);

        Result<User> actual = service.register(user);
        assertEquals(ResultType.SUCCESS, actual.getResultType());
        assertEquals(mockOut, actual.getpayload());
    }

    @Test
    void shouldNotRegisterWhenEmailInUse() {
        User user = new User("new@user.com", "password");

        when(userLoginRepository.createUser(any())).thenReturn(null);

        Result<User> actual = service.register(user);
        assertEquals(ResultType.NOT_FOUND, actual.getResultType());
    }

    @Test
    void shouldRegisterAdminAccount() {
        when(userLoginRepository.registerAdminAccount(5)).thenReturn(true);

        Result actual = service.registerAdminAccount(5);
        assertEquals(ResultType.SUCCESS, actual.getResultType());
    }

    @Test
    void shouldNotRegisterAdminAccountWhenNotFound() {
        when(userLoginRepository.registerAdminAccount(999)).thenReturn(false);

        Result actual = service.registerAdminAccount(999);
        assertEquals(ResultType.NOT_FOUND, actual.getResultType());
    }
}