package com.icommerceapis.restapis.businesslogic.impl;

import com.icommerceapis.common.Constant;
import com.icommerceapis.common.exception.BusinessException;
import com.icommerceapis.common.exception.ErrorCode;
import com.icommerceapis.common.utils.StringUtils;
import com.icommerceapis.restapis.businesslogic.UserBusinessLogic;
import com.icommerceapis.restapis.model.request.LoginRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserBusinessLogicImpl implements UserBusinessLogic {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String generateToken(Integer userId) {
        var currentTime = new Date().getTime();
        var expirationTime = new Date(currentTime + Constant.JWT_EXPIRATION_TIME);
        var claims = Jwts.claims().setSubject(String.valueOf(userId)).setExpiration(expirationTime);
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @Override
    public void validateLoginRequest(LoginRequest request) {
        if (request == null)
            throw BusinessException.of(ErrorCode.REQUEST_NULL);

        if (StringUtils.isBlank(request.getUsername()) || StringUtils.isBlank(request.getPassword()))
            throw BusinessException.of(ErrorCode.USERNAME_PASSWORD_NOT_BLANK);

        if (request.getUsername().length() <= Constant.MIN_PASSWORD_LENGTH
                || request.getPassword().length() <= Constant.MIN_PASSWORD_LENGTH)
            throw BusinessException.of(ErrorCode.USERNAME_PASSWORD_LENGTH);
    }
}
