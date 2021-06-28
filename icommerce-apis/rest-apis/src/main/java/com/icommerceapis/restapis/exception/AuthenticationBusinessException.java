package com.icommerceapis.restapis.exception;

import com.icommerceapis.common.exception.ErrorCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class AuthenticationBusinessException extends AuthenticationException {

    private final ErrorCode errorCode;

    public AuthenticationBusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public static AuthenticationBusinessException of(ErrorCode errorCode) {
        return new AuthenticationBusinessException(errorCode);
    }
}
