package com.icommerceapis.restapis.configuration.jwt;

import com.icommerceapis.common.exception.ErrorCode;
import com.icommerceapis.restapis.exception.AuthenticationBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtTokenValidator jwtTokenValidator;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();
        var auth = jwtTokenValidator.parseToken(token)
                .orElseThrow(() -> AuthenticationBusinessException.of(ErrorCode.JWT_PARSE_ERROR));
        return new JwtAuthenticationToken(auth, auth.getAuthorities());
    }
}
