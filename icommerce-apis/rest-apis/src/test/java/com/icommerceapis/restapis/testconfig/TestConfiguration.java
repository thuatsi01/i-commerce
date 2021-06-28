package com.icommerceapis.restapis.testconfig;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class TestConfiguration {
    public static final String PATH_UNIT_TEST_CONFIG = "spring.config.location=classpath:application-test.yml";
    public static final String BASE_URL = "http://localhost:";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    public static <T> HttpEntity<T> createHttpEntityWithoutHeader(T body) {
        var headers = new HttpHeaders();
        headers.set(AUTHORIZATION, BEARER + "tokentest");
        return new HttpEntity<>(body, headers);
    }

    public static <T> HttpEntity<T> createHttpEntity(T body) {
        var headers = new HttpHeaders();
        headers.set(AUTHORIZATION, BEARER + "tokentest");
        return new HttpEntity<>(body, headers);
    }
}
