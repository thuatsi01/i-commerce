package com.icommerceapis.restapis.controller;

import com.icommerceapis.common.ResultInformation;
import com.icommerceapis.common.Routes;
import com.icommerceapis.restapis.model.request.LoginRequest;
import com.icommerceapis.restapis.model.response.LoginResponse;
import com.icommerceapis.restapis.service.UserService;
import com.icommerceapis.restapis.testconfig.TestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @LocalServerPort
    private int localPort;

    @Test
    void login_throwException() {
        Mockito.doThrow(RuntimeException.class).when(userService).login(any());
        var url = TestConfiguration.BASE_URL + localPort + Routes.User.BASE_URL + Routes.User.LOGIN;
        var request = new LoginRequest();
        var testRestTemplate = new TestRestTemplate();
        var result = testRestTemplate.postForEntity(url, request, String.class);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

    @Test
    void login_success() {
        Mockito.doReturn(
                LoginResponse.builder()
                        .resultInformation(ResultInformation.success())
                        .token("tokentest")
                        .build()).when(userService).login(any());
        var url = TestConfiguration.BASE_URL + localPort + Routes.User.BASE_URL + Routes.User.LOGIN;
        var request = new LoginRequest();
        var testRestTemplate = new TestRestTemplate();
        var result = testRestTemplate.postForEntity(url, request, String.class);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertTrue(Objects.requireNonNull(result.getBody()).contains("tokentest"));
    }
}
