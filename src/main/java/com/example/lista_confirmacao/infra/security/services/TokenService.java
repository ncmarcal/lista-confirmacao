package com.example.lista_confirmacao.infra.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.lista_confirmacao.domain.user.User;
import com.example.lista_confirmacao.exceptions.SystemErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        String claims = userDetails.getAuthorities().iterator().next().getAuthority();
        return createToken(claims, userDetails.getUsername());
    }

    public String createToken(String claims, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("role", claims)
                    .withIssuer("lista-confirmacao")
                    .withSubject(username)
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SystemErrors.ErrorTokenGeneration(e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("lista-confirmacao")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            LOGGER.error(e.getMessage(), e);
            //retornando vazio, pois a classe do spring security lidará com a exceção de não autorizado
            return "";
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}

