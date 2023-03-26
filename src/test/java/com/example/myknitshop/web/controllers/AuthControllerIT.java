package com.example.myknitshop.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistration() throws Exception {
        mockMvc.perform (post("/register")
                .param ("username", "test")
                .param ("firstName", "Test")
                .param ("lastName", "Testov")
                .param ("email", "test@examle.com")
                .param ("password", "test")
                .param ("confirmPassword", "test")
                .with (csrf())
        ).andExpect (status ()
                .is3xxRedirection ())
                .andExpect (redirectedUrl ("/login"));
    }
}
