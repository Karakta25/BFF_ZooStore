package com.example.bff.rest.controller;

import com.example.bff.api.operations.user.logInUser.LogInUserInput;
import com.example.bff.api.operations.user.registerUser.RegisterUserInput;
import com.example.bff.persistence.entities.User;
import com.example.bff.persistence.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("2@2")
                .password("pass")
                .firstName("User")
                .lastName("Userov")
                .phoneNumber("0889233930")
                .build();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void registerUser_ShouldReturn_StatusCreated_WhenArgumentsAreValid() throws Exception {
        RegisterUserInput userRegisterInput = RegisterUserInput
                .builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterInput))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['email']").value(user.getEmail()))
                .andExpect(jsonPath("$['firstName']").value(user.getFirstName()))
                .andExpect(jsonPath("$['lastName']").value(user.getLastName()))
                .andExpect(jsonPath("$['phoneNumber']").value(user.getPhoneNumber()));
    }


    @Test
    void registerUser_ShouldReturn_BadRequest_WhenArgumentsAreInvalid() throws Exception {
        RegisterUserInput invalidUserData = RegisterUserInput
                .builder()
                .email(" ")
                .password(" ")
                .firstName(" ")
                .lastName(" ")
                .phoneNumber(" ")
                .build();

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUserData))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void registerUser_ShouldReturn_BadRequest_WhenTheUserIsAlreadyRegistered() throws Exception {
        userRepository.save(user);

        RegisterUserInput existingUser = RegisterUserInput
                .builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(existingUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void loginUser_ShouldReturns_StatusOk_WhenArgumentsAreValid() throws Exception {

        mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
                        .accept(MediaType.APPLICATION_JSON)
        );

        LogInUserInput logInUserInput = LogInUserInput
                .builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        mockMvc.perform(
                        post("/user/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(logInUserInput))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void loginUser_ShouldReturn_StatusForbidden_WhenArgumentsAreInvalid() throws Exception {
        LogInUserInput logInUserInput = LogInUserInput
                .builder()
                .email(" ")
                .password(user.getPassword())
                .build();

        mockMvc.perform(
                        post("/user/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(logInUserInput))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}



