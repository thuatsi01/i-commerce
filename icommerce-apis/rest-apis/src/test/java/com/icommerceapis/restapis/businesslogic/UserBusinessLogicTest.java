package com.icommerceapis.restapis.businesslogic;

import com.icommerceapis.common.exception.BusinessException;
import com.icommerceapis.restapis.configuration.jwt.Auth;
import com.icommerceapis.restapis.configuration.jwt.JwtTokenValidator;
import com.icommerceapis.restapis.model.request.LoginRequest;
import com.icommerceapis.restapis.testconfig.TestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {TestConfiguration.PATH_UNIT_TEST_CONFIG})
public class UserBusinessLogicTest {

    @Autowired
    private JwtTokenValidator jwtTokenValidator;

    @Autowired
    private UserBusinessLogic userBusinessLogic;

    @ParameterizedTest
    @CsvSource({"1", "2", "3"})
    void generateToken_HasData(Integer userId) {
        var token = userBusinessLogic.generateToken(userId);
        var parsedToken = jwtTokenValidator.parseToken(token);
        Assertions.assertTrue(parsedToken.isPresent());
        Assertions.assertEquals(parsedToken.map(Auth::getUserId).orElse(null), userId, "Compare user_id with jwt token");
    }


    static Stream<Arguments> validateLoginRequestThrowExceptionDataProvider() {
        LoginRequest loginRequest1 = null;

        var loginRequest2 = new LoginRequest();

        var loginRequest3 = new LoginRequest();
        loginRequest3.setUsername("abc");

        var loginRequest4 = new LoginRequest();
        loginRequest4.setUsername("abcdefgh");
        loginRequest4.setPassword("12345678");

        var loginRequest5 = new LoginRequest();
        loginRequest5.setUsername("abcdefgh@gmail.com");
        loginRequest5.setPassword("1234");

        var loginRequest6 = new LoginRequest();
        loginRequest6.setUsername("abc");
        loginRequest4.setPassword("1234");

        return Stream.of(
                Arguments.of(loginRequest1, "Login request is null"),
                Arguments.of(loginRequest2, "Login request is null for username and password"),
                Arguments.of(loginRequest3, "Login request is null for password"),
                Arguments.of(loginRequest4, "Login request is invalid for email"),
                Arguments.of(loginRequest5, "Login request is valid for password length"),
                Arguments.of(loginRequest6, "Login request is not valid for username and password length")
        );
    }

    @ParameterizedTest
    @MethodSource("validateLoginRequestThrowExceptionDataProvider")
    void validateLoginRequest_throwException(LoginRequest request, String message) {
        Assertions.assertThrows(BusinessException.class, () -> userBusinessLogic.validateLoginRequest(request), message);
    }
}
