package com.jbklenterpirse.petsGoApp.services;

import com.jbklenterpirse.petsGoApp.repositories.UserRepository;
import com.jbklenterpirse.petsGoApp.repositories.entities.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserLoggedService {

    private final UserRepository userRepository;

    public UserLoggedService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getLoggedUserEntity(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var username = authentication.getPrincipal();

        var user = new UserEntity();
        user.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));

        return user;
    }
}
