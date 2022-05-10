package com.jbklenterpirse.petsGoApp.services;

import com.auth0.jwt.JWT;
import com.jbklenterpirse.petsGoApp.security.JWTConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JWTService {

    private final JWTConfig jwtConfig;

    public JWTService(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateAccessJWTToken(UserDetails userDetails,
                                         HttpServletRequest request){
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfig.getSecondsInDay()))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(jwtConfig.getSecretKey());
    }

    public String generateRefreshJWTToken(UserDetails userDetails,
                                          HttpServletRequest request){
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfig.getSecondsInWeek()))
                .withIssuer(request.getRequestURL().toString())
                .sign(jwtConfig.getSecretKey());
    }



}
