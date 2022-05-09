package com.jbklenterpirse.petsGoApp.security;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTConfig {

    private final String secret;
    private final Integer secondsInDay;
    private final Integer secondsInWeek;
    private final String tokenPrefix;

    public JWTConfig(@Value("${jwt.secret}") String secret,
                     @Value("${jwt.secondsInDay}") Integer secondsInDay,
                     @Value("${jwt.secondsInWeek}") Integer secondsInWeek,
                     @Value("${jwt.tokenPrefix}")String tokenPrefix) {
        this.secret = secret;
        this.secondsInDay = secondsInDay;
        this.secondsInWeek = secondsInWeek;
        this.tokenPrefix = tokenPrefix;
    }

    public Algorithm getSecretKey(){
        return Algorithm.HMAC256(secret.getBytes());
    }

    public Integer getSecondsInDay() {
        return secondsInDay;
    }

    public Integer getSecondsInWeek() {
        return secondsInWeek;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }
}
