package com.example.security_study.util;

import java.util.Date;
import java.util.List;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(String loginID, List<String> roles) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + 86400000);

        return JWT.create()
                .withSubject(loginID)
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }

    public boolean validation(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    public String getLoginId(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(secret)).build()
                .verify(token);
        return jwt.getSubject();
    }

    public List<String> getRoles(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(secret)).build()
                .verify(token);
        return jwt.getClaim("roles").asList(String.class);
    }
}