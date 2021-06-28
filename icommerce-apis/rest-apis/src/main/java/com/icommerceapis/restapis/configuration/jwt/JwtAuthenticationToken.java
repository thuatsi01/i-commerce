package com.icommerceapis.restapis.configuration.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1L;
    private Object principal;
    private String token;

    public JwtAuthenticationToken(String token) {
        super(null);
        this.token = token;
    }

    public JwtAuthenticationToken(Auth auth, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);

        this.eraseCredentials();
        principal = auth;
        super.setAuthenticated(true);
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.token = null;
    }
}