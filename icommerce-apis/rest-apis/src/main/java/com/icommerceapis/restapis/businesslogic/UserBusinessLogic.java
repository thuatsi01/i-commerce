package com.icommerceapis.restapis.businesslogic;

import com.icommerceapis.restapis.model.request.LoginRequest;

public interface UserBusinessLogic {
    /**
     * Generate jwt token by user id
     */
    String generateToken(Integer userId);

    /**
     * Validate request information
     */
    void validateLoginRequest(LoginRequest request);
}
