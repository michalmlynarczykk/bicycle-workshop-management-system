package com.michalmlynarczyk.authenticationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michalmlynarczyk.authenticationservice.exception.UserAlreadyExistsException;
import com.michalmlynarczyk.authenticationservice.exception.handler.AuthenticationControllerHandler;
import com.michalmlynarczyk.authenticationservice.model.dto.request.UserLoginRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.request.UserRegistrationRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.response.JwtTokenResponse;
import com.michalmlynarczyk.authenticationservice.service.UserService;
import com.michalmlynarczyk.common.model.dto.authentication.WorkshopPosition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {AuthenticationControllerImpl.class, AuthenticationControllerHandler.class})
class AuthenticationControllerImplTest {

    private static final String LOGIN_URL = "/external/v1/auth/login";

    private static final String REGISTER_URL = "/external/v1/auth/register";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;


    @Test
    void login_shouldReturnToken() throws Exception {
        final UserLoginRequest userLoginRequest = new UserLoginRequest(
                "test@test.com",
                "password");

        final JwtTokenResponse mockResponse = new JwtTokenResponse("mockToken");
        when(userService.login(userLoginRequest)).thenReturn(mockResponse);

        mockMvc.perform(post(LOGIN_URL)
                        .content(objectMapper.writeValueAsString(
                                userLoginRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void register_shouldRegisterUserAndReturnToken() throws Exception {
        final UserRegistrationRequest userRegistrationRequest = getUserRegistrationRequest("test@example.com");
        final JwtTokenResponse mockResponse = new JwtTokenResponse("mockToken");
        when(userService.register(userRegistrationRequest)).thenReturn(mockResponse);


        mockMvc.perform(post(REGISTER_URL)
                        .content(objectMapper.writeValueAsString(userRegistrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    void register_shouldReturnConflictWhenEmailIsAlreadyRegistered() throws Exception {
        final UserRegistrationRequest userRegistrationRequest = getUserRegistrationRequest("test@example.com");
        doThrow(new UserAlreadyExistsException("User already exists"))
                .when(userService).register(userRegistrationRequest);


        mockMvc.perform(post(REGISTER_URL)
                        .content(objectMapper.writeValueAsString(userRegistrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }


    @Test
    void register_shouldReturnBadRequestWhenEmailIsNotValid() throws Exception {
        final String email = "test@examplecom212121";
        final UserRegistrationRequest userRegistrationRequest = getUserRegistrationRequest(email);

        mockMvc.perform(post(REGISTER_URL)
                        .content(objectMapper.writeValueAsString(userRegistrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    private static UserRegistrationRequest getUserRegistrationRequest(final String email) {
        final UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest(
                email,
                "password",
                "John",
                "Doe",
                WorkshopPosition.OWNER);
        return userRegistrationRequest;
    }
}