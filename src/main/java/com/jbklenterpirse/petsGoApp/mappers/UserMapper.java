package com.jbklenterpirse.petsGoApp.mappers;

import com.jbklenterpirse.petsGoApp.repositories.entities.UserEntity;
import com.jbklenterpirse.petsGoApp.services.dtos.UserDto;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity fromDtoToEntity(UserDto userDto){
        var entity = new UserEntity();

        entity.setFirstName(userDto.getFirstName());
        entity.setLastName(userDto.getLastName());
        entity.setUsername(userDto.getUsername());
        entity.setPassword(encodedPassword(userDto.getPassword()));

        return entity;
    }

    private String encodedPassword(String password){
        var salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

}
