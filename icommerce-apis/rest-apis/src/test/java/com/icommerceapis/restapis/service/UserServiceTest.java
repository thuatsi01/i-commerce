package com.icommerceapis.restapis.service;

import com.icommerceapis.common.ResultInformation;
import com.icommerceapis.common.exception.BusinessException;
import com.icommerceapis.restapis.businesslogic.UserBusinessLogic;
import com.icommerceapis.restapis.model.request.LoginRequest;
import com.icommerceapis.restapis.repository.UserRepository;
import com.icommerceapis.restapis.testconfig.TestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {TestConfiguration.PATH_UNIT_TEST_CONFIG})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserBusinessLogic userBusinessLogic;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void login_ThrowException() {
        doThrow(BusinessException.class).when(userBusinessLogic).validateLoginRequest(any());
        Assertions.assertThrows(BusinessException.class, () -> userService.login(new LoginRequest()));
    }

    @Test
    void login_notHasUser() {
        doReturn(Optional.empty()).when(userRepository).findByEmail(any());
        Assertions.assertThrows(BusinessException.class, () -> userService.login(new LoginRequest()));
    }

    @Test
    void login_wrongPassword() {
        doReturn(Optional.of(new UserRepository.UserPassword() {
            @Override
            public Integer getId() {
                return 1;
            }

            @Override
            public String getPassword() {
                return "password";
            }
        })).when(userRepository).findByEmail(any());
        doReturn(false).when(bCryptPasswordEncoder).matches(any(), any());
        Assertions.assertThrows(BusinessException.class, () -> userService.login(new LoginRequest()));
    }

    @Test
    void login_success() {
        doReturn(Optional.of(new UserRepository.UserPassword() {
            @Override
            public Integer getId() {
                return 1;
            }

            @Override
            public String getPassword() {
                return "password";
            }
        })).when(userRepository).findByEmail(any());
        doReturn(true).when(bCryptPasswordEncoder).matches(any(), any());
        doReturn("token1").when(userBusinessLogic).generateToken(1);

        var actual = userService.login(new LoginRequest());
        Assertions.assertEquals(ResultInformation.success(), actual.getResultInformation());
        Assertions.assertEquals("token1", actual.getToken());
    }
}
