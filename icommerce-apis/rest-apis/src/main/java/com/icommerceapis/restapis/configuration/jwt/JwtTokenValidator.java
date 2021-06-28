package com.icommerceapis.restapis.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtTokenValidator {

    @Value("${jwt.secret}")
    private String secret;

    public Optional<Auth> parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            var auth = Auth.builder()
                    .userName(body.getSubject())
                    .userId((Integer) body.get("userId"))
                    .build();

            return Optional.of(auth);
        } catch (JwtException ignore) {
            //Ignore exception when token expire
        }
        return Optional.empty();
    }
}
