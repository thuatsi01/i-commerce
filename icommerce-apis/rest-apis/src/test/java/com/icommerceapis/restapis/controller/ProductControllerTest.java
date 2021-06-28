package com.icommerceapis.restapis.controller;

import com.icommerceapis.common.ResultInformation;
import com.icommerceapis.common.Routes;
import com.icommerceapis.restapis.configuration.jwt.Auth;
import com.icommerceapis.restapis.configuration.jwt.JwtTokenValidator;
import com.icommerceapis.restapis.model.request.ProductListRequest;
import com.icommerceapis.restapis.model.response.ProductListResponse;
import com.icommerceapis.restapis.service.ProductService;
import com.icommerceapis.restapis.testconfig.TestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int localPort;

    @MockBean
    private ProductService productService;

    @MockBean
    private JwtTokenValidator jwtTokenValidator;

    @ParameterizedTest
    @CsvSource({"null", "token"})
    void getProductList_unAuthorize(String token) {
        Mockito.doReturn(Optional.empty()).when(jwtTokenValidator).parseToken(any());

        var requestBody = new ProductListRequest();
        var request = new HttpEntity<>(requestBody);
        if (!token.equals("null")) {
            var headers = new HttpHeaders();
            headers.set(TestConfiguration.AUTHORIZATION, TestConfiguration.BEARER + token);
            request = new HttpEntity<>(requestBody, headers);
        }

        var url = TestConfiguration.BASE_URL + localPort + Routes.Product.BASE_URL + Routes.Product.LIST;
        var testRestTemplate = new TestRestTemplate();
        var result = testRestTemplate.postForEntity(url, request, String.class);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        Mockito.verify(productService, Mockito.never()).getProductList(any());
    }

    @Test
    void getProductList_success() {
        Mockito.doReturn(Optional.of(Auth.builder().userId(1).build())).when(jwtTokenValidator).parseToken(any());
        Mockito.doReturn(
                ProductListResponse.builder()
                        .resultInformation(ResultInformation.success())
                        .build()
        ).when(productService).getProductList(any());

        var requestBody = new ProductListRequest();
        var request = TestConfiguration.createHttpEntity(requestBody);
        var url = TestConfiguration.BASE_URL + localPort + Routes.Product.BASE_URL + Routes.Product.LIST;
        var testRestTemplate = new TestRestTemplate();
        var result = testRestTemplate.postForEntity(url, request, String.class);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertTrue(Objects.requireNonNull(result.getBody()).contains("Success"));
    }
}