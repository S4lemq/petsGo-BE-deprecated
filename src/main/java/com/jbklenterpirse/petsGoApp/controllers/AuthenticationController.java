package com.jbklenterpirse.petsGoApp.controllers;

import com.jbklenterpirse.petsGoApp.repositories.entities.UserEntity;
import com.jbklenterpirse.petsGoApp.services.UserServiceImpl;
import com.jbklenterpirse.petsGoApp.services.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final UserServiceImpl userService;

    public AuthenticationController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/user/save")
    public UUID saveUser(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }




}


