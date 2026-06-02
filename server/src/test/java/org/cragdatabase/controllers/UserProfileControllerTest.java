package org.cragdatabase.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cragdatabase.data.UserLoginRepository;
import org.cragdatabase.domain.JwtService;
import org.cragdatabase.domain.UserProfileService;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.domain.results.ResultType;
import org.cragdatabase.models.ListEntry;
import org.cragdatabase.models.Route;
import org.cragdatabase.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @MockBean
    private UserProfileService userProfileService;

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
    void addListEntryShouldReturn201OnSuccess() throws Exception {
        Result<List<Route>> result = new Result<>();
        result.setpayload(List.of());

        when(userProfileService.addListEntry(anyInt(), anyInt())).thenReturn(result);

        ListEntry listEntry = new ListEntry(1, 1);
        mockMvc.perform(post("/api/profile")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(listEntry)))
                .andExpect(status().isCreated());
    }

    @Test
    void addListEntryShouldReturn400OnFailure() throws Exception {
        Result<List<Route>> result = new Result<>();
        result.addErrorMessage("ids must be greater than or equal to 1", ResultType.INVALID);

        when(userProfileService.addListEntry(anyInt(), anyInt())).thenReturn(result);

        ListEntry listEntry = new ListEntry(0, 0);
        mockMvc.perform(post("/api/profile")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(listEntry)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void removeListEntryShouldReturn200OnSuccess() throws Exception {
        Result<List<Route>> result = new Result<>();
        result.setpayload(List.of());

        when(userProfileService.removeListEntry(anyInt(), anyInt())).thenReturn(result);

        ListEntry listEntry = new ListEntry(1, 1);
        mockMvc.perform(delete("/api/profile")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(listEntry)))
                .andExpect(status().isOk());
    }

    @Test
    void removeListEntryShouldReturn400OnFailure() throws Exception {
        Result<List<Route>> result = new Result<>();
        result.addErrorMessage("ids must be greater than or equal to 1", ResultType.INVALID);

        when(userProfileService.removeListEntry(anyInt(), anyInt())).thenReturn(result);

        ListEntry listEntry = new ListEntry(0, 0);
        mockMvc.perform(delete("/api/profile")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(listEntry)))
                .andExpect(status().isBadRequest());
    }
}