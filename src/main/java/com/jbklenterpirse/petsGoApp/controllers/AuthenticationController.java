package com.jbklenterpirse.petsGoApp.controllers;

import com.jbklenterpirse.petsGoApp.services.JWTService;
import com.jbklenterpirse.petsGoApp.services.UserServiceImpl;
import com.jbklenterpirse.petsGoApp.services.dtos.UserDto;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthenticationController {

    private final UserServiceImpl userService;
    private final JWTService jwtService;
    public AuthenticationController(UserServiceImpl userService, JWTService jwtService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/user/save")
    public UUID saveUser(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        jwtService.doRefresh(authorizationHeader, request, response);
    }
}


