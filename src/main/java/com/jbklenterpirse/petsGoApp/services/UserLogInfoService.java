package com.jbklenterpirse.petsGoApp.services;

import com.jbklenterpirse.petsGoApp.exceptions.UserNotFoundException;
import com.jbklenterpirse.petsGoApp.repositories.UserRepository;
import com.jbklenterpirse.petsGoApp.repositories.entities.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class UserLogInfoService {
    private final UserRepository userRepository;

    public UserLogInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getLoggedUserEntity(){
        /*var authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        String username = principal.getUsername();*/

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }
}
