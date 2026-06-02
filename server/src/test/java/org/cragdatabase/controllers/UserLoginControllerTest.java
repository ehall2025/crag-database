package org.cragdatabase.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cragdatabase.data.UserLoginRepository;
import org.cragdatabase.domain.JwtService;
import org.cragdatabase.domain.UserLoginService;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.domain.results.ResultType;
import org.cragdatabase.models.User;
import org.cragdatabase.models.UserWithJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @MockBean
    private UserLoginService userLoginService;

    @MockBean
    private UserLoginRepository userLoginRepository;

    String token;

    @BeforeEach
    void setup() {
        User user = new User(1, "test", "password", "ROLE_USER");
        when(userLoginRepository.findByUsername("test")).thenReturn(user);
        token = jwtService.generateToken("test");
    }

    @Test
    void registerShouldReturn201OnSuccess() throws Exception {
        Result<User> result = new Result<>();
        result.setpayload(new User("test", "password"));

        when(userLoginService.register(any(User.class))).thenReturn(result);

        User user = new User("new", "password");
        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    void registerShouldReturn409WhenEmailTaken() throws Exception {
        Result<User> result = new Result<>();
        result.addErrorMessage("could not create account, email already in use", ResultType.NOT_FOUND);

        when(userLoginService.register(any(User.class))).thenReturn(result);

        User user = new User("taken", "password");
        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isConflict());
    }

    @Test
    void loginShouldReturn200OnSuccess() throws Exception {
        Result<UserWithJWT> result = new Result<>();
        result.setpayload(new UserWithJWT(new User("test", "password"), token));

        when(userLoginService.login(any(User.class))).thenReturn(result);

        User user = new User("test", "password");
        mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void loginShouldReturn401WhenInvalid() throws Exception {
        when(userLoginService.login(any(User.class))).thenThrow(new RuntimeException("bad credentials"));

        User user = new User("invalid", "password");
        mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isUnauthorized());
    }
}