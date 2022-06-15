package com.jbklenterpirse.petsGoApp.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbklenterpirse.petsGoApp.security.JWTConfig;
import com.jbklenterpirse.petsGoApp.services.dtos.UserDto;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
public class JWTService {

    private final JWTConfig jwtConfig;
    private final UserServiceImpl userService;
    public JWTService(JWTConfig jwtConfig, UserServiceImpl userService) {
        this.jwtConfig = jwtConfig;
        this.userService = userService;
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
    public String getRefreshJWTToken(UserDto userDto,
                                     HttpServletRequest request){
        return JWT.create()
                .withSubject(userDto.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfig.getSecondsInWeek()))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", String.valueOf(userDto.getRole()))
                .sign(jwtConfig.getSecretKey());
    }
    public void doRefresh(String authorizationHeader, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(authorizationHeader != null && authorizationHeader.startsWith(jwtConfig.getTokenPrefix())){
            try{
                String refresh_token = authorizationHeader.substring(jwtConfig.getTokenPrefix().length());
                JWTVerifier verifier = JWT.require(jwtConfig.getSecretKey()).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                UserDto user = userService.getUser(username);
                String access_token = getRefreshJWTToken(user, request);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch(Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else{
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
