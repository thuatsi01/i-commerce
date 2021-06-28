package com.icommerceapis.restapis.service.impl;

import com.icommerceapis.common.ResultInformation;
import com.icommerceapis.common.exception.BusinessException;
import com.icommerceapis.common.exception.ErrorCode;
import com.icommerceapis.restapis.businesslogic.UserBusinessLogic;
import com.icommerceapis.restapis.model.request.LoginRequest;
import com.icommerceapis.restapis.model.response.LoginResponse;
import com.icommerceapis.restapis.repository.UserRepository;
import com.icommerceapis.restapis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBusinessLogic userBusinessLogic;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public LoginResponse login(LoginRequest request) {
        userBusinessLogic.validateLoginRequest(request);
        var userPassword = userRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> BusinessException.of(ErrorCode.USER_NOT_FOUND));
        if (!bCryptPasswordEncoder.matches(request.getPassword(), userPassword.getPassword())) {
            throw BusinessException.of(ErrorCode.WRONG_PASSWORD);
        }

        return LoginResponse.builder()
                .resultInformation(ResultInformation.success())
                .token(userBusinessLogic.generateToken(userPassword.getId()))
                .build();
    }
}
