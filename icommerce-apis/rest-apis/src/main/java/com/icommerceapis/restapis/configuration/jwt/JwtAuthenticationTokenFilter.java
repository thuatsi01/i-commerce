package com.icommerceapis.restapis.configuration.jwt;

import com.icommerceapis.common.exception.ErrorCode;
import com.icommerceapis.restapis.exception.AuthenticationBusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    private final static String HEADER_PREFIX = "Bearer ";

    @Value("${jwt.header}")
    private String tokenHeader;

    public JwtAuthenticationTokenFilter(RequestMatcher matcher) {
        super(matcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        var header = request.getHeader(this.tokenHeader);

        if (header == null || !header.startsWith(HEADER_PREFIX)) {
            throw AuthenticationBusinessException.of(ErrorCode.JWT_MISSING_ERROR);
        }

        var authToken = header.substring(HEADER_PREFIX.length());
        var authRequest = new JwtAuthenticationToken(authToken);

        return getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        super.unsuccessfulAuthentication(request, response, failed);
    }
}