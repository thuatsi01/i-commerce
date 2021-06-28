package com.icommerceapis.restapis.service;

import com.icommerceapis.restapis.model.request.LoginRequest;
import com.icommerceapis.restapis.model.response.LoginResponse;

public interface UserService {

    /**
     * Return token after validate username and password
     */
    LoginResponse login(LoginRequest request);
}
