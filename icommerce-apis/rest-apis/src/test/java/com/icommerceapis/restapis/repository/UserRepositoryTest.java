package com.icommerceapis.restapis.repository;

import com.icommerceapis.restapis.testconfig.TestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {TestConfiguration.PATH_UNIT_TEST_CONFIG})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @ParameterizedTest
    @CsvSource({"tester01@gmail.com", "tester02@gmail.com"})
    void findByEmail_hasData(String email) {
        var actual = userRepository.findByEmail(email);
        Assertions.assertTrue(actual.isPresent(), "Email existed");
    }

    @ParameterizedTest
    @CsvSource({"test01", "abc@gmail.com"})
    void findByEmail_hasEmpty(String email) {
        var actual = userRepository.findByEmail(email);
        Assertions.assertTrue(actual.isEmpty(), "Email not existed or not valid");
    }
}
