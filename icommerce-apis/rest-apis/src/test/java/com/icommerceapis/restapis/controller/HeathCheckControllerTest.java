package com.icommerceapis.restapis.controller;

import com.icommerceapis.common.Routes;
import com.icommerceapis.restapis.testconfig.TestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HeathCheckControllerTest {

    @LocalServerPort
    private int localPort;

    @Test
    void heathCheck() {
        var url = TestConfiguration.BASE_URL + localPort + Routes.HEATH_CHECK;
        var testRestTemplate = new TestRestTemplate();
        var result = testRestTemplate.getForEntity(url, String.class);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertTrue(Objects.requireNonNull(result.getBody()).contains("OK"));
    }

}