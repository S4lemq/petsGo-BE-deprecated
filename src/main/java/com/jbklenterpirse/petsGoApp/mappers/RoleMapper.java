package com.jbklenterpirse.petsGoApp.mappers;

import com.jbklenterpirse.petsGoApp.repositories.entities.RoleEntity;
import com.jbklenterpirse.petsGoApp.services.dtos.RoleDto;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleEntity fromDtoToEntity(RoleDto roleDto){
        var entity = new RoleEntity();
        entity.setName(roleDto.getName());
        return entity;
    }
}
