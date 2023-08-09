package com.example.bff.rest.controller;

import com.example.bff.api.operations.orderConfirmation.OrderConfirmationInput;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OrderControllerTest {

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
                .firstName("fName")
                .lastName("lName")
                .build();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

//    Коментирания код следва да бъде коригиран и допълнен
//    @Test
//    void orderConfirmation_ShouldReturn_StatusOk_WhenArgumentsAreValid() throws Exception {
//
//       OrderConfirmationInput orderConfirmationInput = OrderConfirmationInput
//                .builder()
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .cardID("3423232323")
//                .build();
//
//        mockMvc.perform(post("/order")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(orderConfirmationInput))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }


}
