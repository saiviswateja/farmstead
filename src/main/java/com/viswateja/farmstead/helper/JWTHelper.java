package com.viswateja.farmstead.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JWTHelper {
    public String createJWT(Long userPk) {
        Algorithm algorithm = Algorithm.HMAC256("secret-token");
        String token = JWT.create()
                .withIssuer("auth0")
                .withSubject("teja")
                .withClaim("user_pk", userPk)
                .sign(algorithm);
        return token;
    }

    public boolean isTokenValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret-token");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception){
            return false;
        }
        return true;
    }
}
